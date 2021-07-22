<%@page import="todo.model.vo.Todo"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<Todo> todoList = (List<Todo>)request.getAttribute("todoList");
	Todo todo = (Todo)request.getAttribute("todo");
%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>

          <!-- Begin Page Content -->
      <div class="container-fluid">
      
          <!-- 제목 시작 -->
          <div class="d-sm-flex mb-4">
              <h1 class="h3 mb-0 text-gray-800">Calendar</h1>

          </div>
          <!-- 여기까지 제목 -->
          
	  <script>

      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
        	
        	themeSystem: 'bootstrap',
        	
            initialView: 'dayGridMonth',
            
            selectable: true,
            selectMirror: true,
            
            editable: false,
           
            //연월 표기 한국어 설정
/*             titleFormat : function(date) {
        	   return date.date.year +"년 "+(date.date.month +1)+"월"; 
        	   }, */
        	
        	//콘텐츠 높이
        	//contentHeight: 600,
        	 
        	//주수 표기 : 디폴트 6주를 false로
			fixedWeekCount: false,
			
			//다음달 날짜 랜더링 여부
            showNonCurrentDates: true,
            
            //요일 표기 한국어 설정
            dayHeaderContent: function (date) {
            let weekList = ["일", "월", "화", "수", "목", "금", "토"];
            	return weekList[date.dow];
            },
            
            dateClick: function(info) {
                //console.log('Date: ' + info.dateStr);
            },

        });
        
        
        <% for(Todo t : todoList) {
        		String todoColor = null;
        		switch(t.getTodoStatus()) {
	        		case "TODO": todoColor = "#3E80FF"; break;
	        		case "DOING": todoColor = "#f6c23e"; break;
	        		case "DONE": todoColor = "#1cc88a"; break;
        		}
        %>
	        calendar.addEvent({
	        	id : '<%= t.getTodoId() %>',
				title : '<%= t.getTodoTitle() %>', 
				start : '<%= t.getTodoStartDate() %>', 
				end : '<%= t.getTodoEndDate() %>',
				color : '<%= todoColor %>',
				url : '<%= request.getContextPath() %>/todo/todoUpdate?todoId=<%= t.getTodoId() %>'
			});
        	<%-- <% if (t.getTodoStatus().equals("TODO")) { %>
        		console.log('.fc-event-title-container');
	        	calendar.addEvent({'title':'<%= t.getTodoTitle()%>', 
	        					   'start':'<%= t.getTodoStartDate()%>', 
	        					   'end':'<%= t.getTodoEndDate()%>',
	        					   'color':'#3E80FF',
	        					   'url':'<%=request.getContextPath()%>/todo/todoUpdate?todoId=<%= t.getTodoId()%>'});
        	<% } %>
        	<% if (t.getTodoStatus().equals("DOING")) { %>
	        	calendar.addEvent({'title':'<%= t.getTodoTitle()%>', 
	        					   'start':'<%= t.getTodoStartDate()%>', 
	        					   'end':'<%= t.getTodoEndDate()%>',
	        					   'color':'#f6c23e',
	        					   'url':'<%=request.getContextPath()%>/todo/todoUpdate?todoId=<%= t.getTodoId()%>'});
	    	<% } %>
	    	<% if (t.getTodoStatus().equals("DONE")) { %>
		    	calendar.addEvent({'title':'<%= t.getTodoTitle()%>', 
		    					   'start':'<%= t.getTodoStartDate()%>', 
		    					   'end':'<%= t.getTodoEndDate()%>',
		    					   'color':'#1cc88a',
		    					   'url':'<%=request.getContextPath()%>/todo/todoUpdate?todoId=<%= t.getTodoId()%>'});
			<% } %> --%>
		<% } %>
        calendar.render();
        
        
        var eventArr = calendar.getEvents();
        console.log(eventArr);
        
        eventArr.forEach((event, idx) => {
        	console.log(idx, event);
        	console.log(idx, event.id);
        	console.log(idx, event.title);
        	console.log(idx, "dateObject = ", event.start);
        	console.log(idx, "dateObject = ", event.end);
        	console.log(idx, "jsonStr = " + JSON.stringify(event.start));
        	console.log(idx, "jsonStr = " + JSON.stringify(event.end));
        });
      });
     
    </script>
    
    		
    
			<div class="col-sm-10 text-center" style="float: none; margin: 0 auto;">
               <div id='calendar'></div>
            	</div>
 
            </div>
            <!-- End of Main Content -->
					
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
					
</body>

</html>