<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="con" value="${pageContext.request.contextPath}"/>
<c:set var="p" value="${post}"/>    
<c:set var="f" value="${file}"/>    
<c:set var="tag" value="${tag}" />
<c:set var="cat" value="${listData}"/>
      <!-- 글쓰기 -->
        <div class="container-fluid">
             <h1 class="h3 mb-2 text-gray-600">글수정</h1>
             <div class="card shadow mb-4">
                 <form class="bs-example bs-example-form postFrm" name="postFrm" action="${con}/post/update" method="post" 
                 enctype="multipart/form-data" id="sendFrm">
                 <div class="card-body">
                 	<p >작성일 :${p.postRegDate} / 조회수 :${p.postHit} / 작성자 :${p.refWorksMemberNo}</p><br>
                 	<input type="hidden" value="${p.postId}" name="postId">
                 		<div class="input-group-prepend">
                         <select name="category" class="custom-select mr-3">
                       	  <option selected>카테고리 선택</option>
                            <c:forEach var="cat" items="${cat}">
                            	<option value="${cat.categoryName}">${cat.categoryName}</option>
                         	</c:forEach>
                         </select>
                         </div>
                         <br>
                         <div class="input-group">
	                         <span class="input-group-addon mr-2 align-self-center">제목 : </span>
                         	
	                         <input type="text" class="form-control" name="title" placeholder="제목 입력" value="${p.postTitle}">
                    	 </div>
                      <br>
	                      <div class="form-group">
	                          <label for="exampleFormControlTextarea1">글내용</label>
	                          <textarea class="form-control" name="content" rows="15" placeholder="내용 입력">${p.postContent}</textarea>
                        </div>
       					<div class="row ml-1 ">	
							<span class="input-group-addon align-self-center mr-1">해시태그 :</span>
								<c:forEach var="item" items="${fn:split(tag.hashtagName,',')}">
										${item}
								</c:forEach>
		                  </div>
                      	<br>
    	                  <div class="mb-3 mt-3 float-left">
	                          <label for="formFile" class="form-label">첨부파일</label>
	                          <input class="form-control" type="file" name="upFile">
							  <c:if test="${p.refFileRenameedName != null}">
		                          <input type="hidden" name="oldFile" value="${f.fileOriginalName}">
		                          <input type="hidden" name="reNameFile" value="${f.fileRenamedName}">
		                          <p>
		                          	  ${f.fileOriginalName}
		                          	  <span for="delFile">삭제</span>
			                          <input type="checkbox" name="delFile" id="delFile" value="${p.refFileRenameedName}">
		                          </p>
       		              	  </c:if>
       		              </div>
                      <br>

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
	        	padding: 4px 4px;
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
	   		$("[name=upFile]").change(function(){
	   			var upFile = $(this);
	   			if(upFile.val()){
	   				$("#delFile").prop("checked",true);
	   			} else{
	   				$("#delFile").prop("checked",false);
	   			}
	   		});
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
			</script>
<%@ include file='/WEB-INF/views/common/footer.jsp'%>