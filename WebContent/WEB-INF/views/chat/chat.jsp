<%@page import="chat.model.vo.Chat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
<%
   //System.out.println("currentChannel@chat.jsp = " + currentChannel);
   //System.out.println("currentWrksMember@chat.jsp = " + currentWrksMember);
   
   List<Chat> chatListByChannel = (List<Chat>)session.getAttribute("chatListByChannel");
   //System.out.println("chatListByChannel@chat.jsp = " + chatListByChannel);
   
%>

   <!-- Begin Page Content -->
   <% if(currentWrksMember == null || currentChannel == null) { %>
         <div class="container d-flex flex-column d-flex align-items-center justify-content-center" id="containerChat" style="padding-right: 0px; padding-bottom: 10px">
            <span>가입된 워크스페이스가 없거나 채널이 없습니다.</span>
            <span>워크스페이스를 만들거나 가입한 후 다시 접속 부탁드립니다.</span>
         </div>
   
   <% }  else { %>
   
         <div class="container" id="containerChat" style="padding-right: 0px; padding-bottom: 10px">         
            <div class="row w-100" style="overflow: hidden;">
               <ul class="chatting-list pl-0 w-100" style="height: 740px; overflow-y: scroll; margin-bottom: 5px">
               <%
                  if(chatListByChannel != null) {
                     if(!chatListByChannel.isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
   
                        for(Chat chat : chatListByChannel) {
                        	String memberProfileImg = wksMemberHashMap.get(chat.getRefWorksMemberNo()) == null 
                        			? null
                        			: wksMemberHashMap.get(chat.getRefWorksMemberNo())
                        				.getRenamedProfileImg();
                           //System.out.println("memberProfileImg@chat.jsp = " + memberProfileImg);
                           if(chat.getRefWorksMemberNo() == currentWrksMember.getWorksMemberNo()) { %>
                              <li class="sent">
               <%            } else { %>
                              <li class="received">
               <%            } %>
                                    <span class='profile'>
                                    <img class='image' src='<%= 
                                                         memberProfileImg != null
                                                            ? request.getContextPath() + "/upload/profile/" + memberProfileImg
                                                                     : request.getContextPath() + "/img/logo.png"
                                                               %>' alt='any'>
                                    <span class='user'><%=
                                    						wksMemberHashMap.get(chat.getRefWorksMemberNo()) == null 
                                    							? "탈퇴한 회원" 
                                    							: wksMemberHashMap.get(chat.getRefWorksMemberNo()).getWorksMemberNickname()
                                    					%>
                                    </span>
                                 </span>
                                 <span class='message'><%= chat.getChatContent() %></span>
                                 <span class='time'><%= sdf.format(chat.getChatRegDate()) %></span>
                              </li>
               <%         }
                     }
                  }
                %>
               </ul>
            </div>
            <div class="row w-100 mt-1">
               <div class="col px-2">
                  <input type="hidden" id="refChannelId" value="<%= currentChannel.getChannelId() %>" />
                  <input type="hidden" id="refWorksMemberNo" value="<%= currentWrksMember.getWorksMemberNo() %>" />               
                  <input type="hidden" id="memberNickName" value="<%= currentWrksMember.getWorksMemberNickname() %>" />               
                  <input type="text" id="chatContent" class="form-control form-control-user">
               </div>
               <div class="col-xm">
                  <button class="btn btn-primary btn-icon-split" onclick="sendMessage()">
                     <span class="icon text-white-50">
                        <i class="fas fa-comment-alt"></i>
                     </span>
                     <span class="text">전송</span>
                  </button>
               </div>
            </div>
          </div>
       
   <% } %>
       
   <!-- End of Main Content -->
   
<!-- 웹소켓 스크립트 -->
<% if(currentWrksMember != null || currentChannel != null) { %>

<script type="text/javascript">

   
   window.onload = function(){
      scrollBottom();
      //웹소켓 <-> 클라이언트 온로드끼리만 값 주고받을수 있는지 알아보기
   }

   chatContent.addEventListener("keypress",function(event){
      if(event.keyCode === 13){
         sendMessage();
           $(chatContent).value="";
           $(chatContent).focus();
       }
   });
   
   // 「WebSocketEx」는 프로젝트 명
   // 「websocket」는 호스트 명
   // WebSocket 오브젝트 생성 (자동으로 접속 시작한다. - onopen 함수 호출)
   var webSocket = new WebSocket("ws://<%= request.getRequestURL().toString().replace(request.getRequestURI(),"").replace("http://", "") + request.getContextPath() %>/websocket");

   // 콘솔 텍스트 에리어 오브젝트
   var messageTextArea = document.getElementById("messageTextArea");

   // WebSocket 서버와 접속이 되면 호출되는 함수
   webSocket.onopen = function(message) {
      // 콘솔 텍스트에 메시지를 출력한다.
      console.log("Server connect...");
   };
   // WebSocket 서버와 접속이 끊기면 호출되는 함수
   webSocket.onclose = function(message) {
      // 콘솔 텍스트에 메시지를 출력한다.
      console.log("Server Disconnect...");
   };
   // WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
   webSocket.onerror = function(message) {
      // 콘솔 텍스트에 메시지를 출력한다.
      console.log(message);
   };


   // WebSocket 서버로 부터 메시지가 오면 호출되는 함수 (return replymessage)
   webSocket.onmessage = function(event) {
      // 콘솔 텍스트에 메시지를 출력한다.
      //console.log(event.data);
      //console.log(event);
      
      var chatObj = JSON.parse(event.data);
      
      chatObj.chatRegDate = new Date(chatObj.chatRegDate);
      //console.log(chatObj);
      
      var $liElem = $(document.createElement("li"));
      $liElem.addClass(chatObj.refWorksMemberNo == $("#refWorksMemberNo").val() ? "sent" : "received");      
      
      var profileImg = ""
      if(chatObj.refFileRenamedName != "null") {
         profileImg = "<%= request.getContextPath() %>/upload/profile/" + chatObj.refFileRenamedName;
      } else 
         profileImg = "<%= request.getContextPath() %>/img/logo.png";

      var dom = 
              "<span class='profile'>"
               + "<img class='image' src='"+ profileImg + "' alt='any'>"
               + "<span class='user'>" + chatObj.memberNickName + "</span>"
            + "</span>"
            + "<span class='message'><pre>" + chatObj.chatContent + "</pre></span>"
            + "<span class='time'>" + chatObj.chatRegDate.format('MM-dd') +"</span>";
      
      //console.log(dom);

      $liElem.html(dom);

      $(".chatting-list").append($liElem);
      
      scrollBottom();
   };

   // Send 버튼을 누르면 호출되는 함수
   function sendMessage() {
      
      if($(chatContent).val() == "") {
         alert("내용을 입력해주세요!");
         return false;
      }
      
      var chat = {
            refChannelId : parseInt($(refChannelId).val()),
            refWorksMemberNo : parseInt($(refWorksMemberNo).val()),
            memberNickName : $(memberNickName).val(),
            chatContent : $(chatContent).val(),
            chatRegDate : new Date(),
				refFileRenamedName : "<%= currentWrksMember.getRenamedProfileImg() %>"
      };
      //console.log(chat);
      
      var jsonStr = JSON.stringify(chat);
      //console.log(jsonStr);
      
      // WebSocket 서버에 메시지를 송신한다.
      webSocket.send(jsonStr);
      
      // 송신 메시지를 작성하는 텍스트 박스를 초기화한다.
      $(chatContent).val("");
      $(chatContent).focus();
   }
   
   // Disconnect 버튼을 누르면 호출되는 함수
   function disconnect() {
      // WebSocket 접속 해제
      webSocket.close();
   }
   
   function scrollBottom(){
      
      var chattingList = document.querySelector(".chatting-list");
      
      chattingList.scrollTo(0, chattingList.scrollHeight);
   }

   Date.prototype.format = function (f) {
   
      if (!this.valueOf()) return " ";
      var weekKorName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
      var weekKorShortName = ["일", "월", "화", "수", "목", "금", "토"];
      var weekEngName = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
      var weekEngShortName = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
   
      var d = this;
   
      return f.replace(/(yyyy|yy|MM|dd|KS|KL|ES|EL|HH|hh|mm|ss|a\/p)/gi, function ($1) {
   
         switch ($1) {
            case "yyyy": return d.getFullYear(); // 년 (4자리)
            case "yy": return (d.getFullYear() % 1000).zf(2); // 년 (2자리)
            case "MM": return (d.getMonth() + 1).zf(2); // 월 (2자리)
            case "dd": return d.getDate().zf(2); // 일 (2자리)
            case "KS": return weekKorShortName[d.getDay()]; // 요일 (짧은 한글)
            case "KL": return weekKorName[d.getDay()]; // 요일 (긴 한글)
            case "ES": return weekEngShortName[d.getDay()]; // 요일 (짧은 영어)
            case "EL": return weekEngName[d.getDay()]; // 요일 (긴 영어)
            case "HH": return d.getHours().zf(2); // 시간 (24시간 기준, 2자리)
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2); // 시간 (12시간 기준, 2자리)
            case "mm": return d.getMinutes().zf(2); // 분 (2자리)
            case "ss": return d.getSeconds().zf(2); // 초 (2자리)
            case "a/p": return d.getHours() < 12 ? "오전" : "오후"; // 오전/오후 구분
            default: return $1;
   
         }
   
      });
};

   String.prototype.string = function (len) { var s = '', i = 0; while (i++ < len) { s += this; } return s; };
   String.prototype.zf = function (len) { return "0".string(len - this.length) + this; };
   Number.prototype.zf = function (len) { return this.toString().zf(len); };
</script>
<% } %>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>

</body>
</html>