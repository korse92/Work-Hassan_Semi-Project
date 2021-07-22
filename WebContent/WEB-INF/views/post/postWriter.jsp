<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="con" value="${pageContext.request.contextPath}"/>
    
      <!-- 글쓰기 -->
        <div class="container-fluid">
             <h1 class="h3 mb-2 text-gray-600">글작성</h1>
             <div class="card shadow mb-4">
                <form class="bs-example bs-example-form postFrm" name="postFrm" action="${con}/post/add" method="post" 
                 enctype="multipart/form-data" id="sendFrm">
                 <div class="card-body">
                 	<div class="input-group">
					  <div class="input-group-prepend">
                         <select name="category" class="custom-select mr-3">
                            <option selected>카테고리 선택</option>
                            <c:forEach var="list" items="${listData}">
                            	<option value="${list.categoryName}">${list.categoryName}</option>
                         	</c:forEach>
                         </select>
                         </div>
					  </div>
                         <br>
                         <div class="input-group">
	                         <span class="input-group-addon mr-2 align-self-center">제목 : </span>
	                         <input type="text" class="form-control" name="title" placeholder="제목 입력">
                    	 </div>
                      <br>
	                      <div class="form-group">
	                          <label for="exampleFormControlTextarea1">글내용</label>
	                          <textarea class="form-control" name="content" rows="15" placeholder="내용 입력"></textarea>
                        </div>
       					<div class="row ml-1 mb-5">	
							<span class="input-group-addon align-self-center">해시태그 : </span>
							<div class="col-sm-3">
								<input type="text" name="hashtag" id="tag" class="from-control" placeholder="해시태그 입력" data-toggle="tooltip"
								data-placement="bottom" title="입력 후 스페이스바로 동작" >   
			        		</div>
			        	</div>
							<ul id="tag-list">
							</ul>
                      	<br>
    	                  <div class="mb-3 mt-3 float-left">
	                          <label for="formFile" class="form-label">첨부파일</label>
	                          <input class="form-control" type="file" name="upFile">
       		              </div>
                      <br>

                  </div>
	                  <div align="right">
	                      <input type="submit" value="저장" class="btn btn-primary mr-1 mb-5 ">
	                      <input type="reset" value="취소" class="btn btn-primary mr-4 mb-5" onclick="location.href='${con}/post'">
	                  </div>
              	</form>
              </div>
          </div>
          <style>
          	ul li {
          		display : inline-block;
          		font-size : 14px; 
          	}
          	ul li.tag-item {
	        	padding: 4px 8px;
	        	background-color: gray;
	        	color: #000;
	        	border-radius: 10px;
	    	}
		    .tag-item:hover {
		        background-color: #000;
		        color: #fff;
		        margin: 1px;
		    }
          </style>
          <script>
	    	$(document).ready(function () {
	   		$("[name=postFrm]").submit(boardConfirm);
	   		function boardConfirm(){
	   			var boardTitle = $("[name=title]");
	   			if(/^.{1,}$/.test(boardTitle.val()) == false){
	   				alert("제목 미입력");
	   				return false;
	   			}
	   			var boardContent = $("[name=content]");
	   			if(/^(.|\n){1,}$/.test(boardContent.val()) == false){
	   				alert("내용을 입력하세요");
	   				return false;
	   			}
	            $("#tag").val(marginTag()); 
	   		}
	        var tag = {};
	        var counter = 0;
	
	        // 태그를 추가한다.
	        function addTag (value) {
	            tag[counter] = value; // 태그를 Object 안에 추가
	            counter++; // counter 증가 삭제를 위한 del-btn 의 고유 id 가 된다.
	        }
	
	        // 최종적으로 서버에 넘길때 tag 안에 있는 값을 array type 으로 만들어서 넘긴다.
	        function marginTag () {
	            return Object.values(tag).filter(function (word) {
	                return word !== "";
	            });
	        }
	
	        $("#tag").on("keypress", function (e) {
	            var self = $(this);
	
	            // input 에 focus 되있을 때 엔터 및 스페이스바 입력시 구동
	            if (e.keyCode == 32) {
	
	                var tagValue = self.val(); // 값 가져오기
	
	                // 값이 없으면 동작 ㄴㄴ
	                if (tagValue !== "") {
	
	                    // 같은 태그가 있는지 검사한다. 있다면 해당값이 array 로 return 된다.
	                    var result = Object.values(tag).filter(function (word) {
	                        return word === tagValue;
	                    })
	                
	                    // 태그 중복 검사
	                    if (result.length == 0) { 
	                        $("#tag-list").append("<li class='tag-item ml-1'>"+tagValue+"<span class='del-btn' idx='"+counter+"'>x</span></li>");
	                        addTag(tagValue);
	                        self.val("");
	                    } else {
	                        alert("태그값이 중복됩니다.");
	                    }
	                }
	                e.preventDefault(); // SpaceBar 시 빈공간이 생기지 않도록 방지
	            }
	        });
	
	        // 삭제 버튼 
	        $(document).on("click", ".del-btn", function (e) {
	            var index = $(this).attr("idx");
	            tag[index] = "";
	            $(this).parent().remove();
	        });
	        $('[data-toggle="tooltip"]').tooltip();

		});
	   	
	   	
</script>
<%@ include file='/WEB-INF/views/common/footer.jsp'%>