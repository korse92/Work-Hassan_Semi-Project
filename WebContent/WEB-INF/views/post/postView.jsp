<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="con" value="${pageContext.request.contextPath}"/>
<c:set var="p" value="${post}"/>
<c:set var="org" value="${org}"/>
<c:set var="cmt" value="${cmt}"/>
<c:set var="cat" value="${cat}" />
<c:set var="writer" value="${writer}" />
<c:set var="cmtWriter" value="${cmtWriter}" />

<script>
	function fileDownload(oName, rName){
		console.log(oName, rName);
		location.href = "${con}/post/fileDown?oName=" + oName + "&rName=" + rName; 
	}
	</script>
	<div class="container-fluid">
	    <h1 class="h3 mb-2 text-gray-600">게시판</h1>
	    <div class="card shadow mb-4">
	        <div class="card-body">
	            <div class="table-responsive">
            		<div class="text-right">조회수 : ${p.postHit}<br>작성일  / ${p.postRegDate} /작성자 : ${writer}</div>
            		<c:if test="${not empty cat}">
	            		<h2 class="text-center">[${cat.categoryName}]${p.postTitle}</h2>
            		</c:if>
					<c:if test="${empty cat}">
	            		<h2 class="text-center">${p.postTitle}</h2>
					</c:if>            		
            		<hr>
                  	<div>${p.postContent}</div>
                  	<br />
                  	<c:if test="${not empty p.refFileRenameedName}">
                  		첨부파일$ : <a href="javascript:fileDownload('${org}','${p.refFileRenameedName}')">${org}</a>	    
                  	</c:if>
                  	<br>
              		해시태그:
					<c:forEach var="tag" items="${tag}">
						<span>${tag.hashtagName}</span>
					</c:forEach>
	            </div>
        </div>
	        <hr />
	        <div align="right">
	            <button type="button" class="btn btn-primary mb-3 ml-2" onclick="location.href='${con}/post'">목록</button>
	            <button type="button" class="btn btn-primary mb-3 " onclick="location.href='${con}/post/update?postId=${p.postId}'">수정</button>
	            <button type="button" class="btn btn-primary mb-3 mr-4 " onclick="location.href='${con}/post/delete?postId=${p.postId}'">삭제</button>
	        </div>
		    </div>
			<h1 class="h5 mb-2 text-gray-500">댓글</h1>
			<div class="card shadow mb-4">
		        <div class="card-body">
		            <div class="table-responsive">
		            	<c:forEach var="cmt" items="${cmt}">
			            	<div class="card-body">
			            		${cmt.ref_works_member_no} : ${cmt.cmt_content} ${cmt.cmt_reg_date} <a href="${con}/cmt/delete?cmt_id=${cmt.cmt_id}">삭제</a>
			            	</div>
		            	</c:forEach>
		            	<form action="${con}/post/cmt" method="post">
							<input type="hidden" name="cmtWriter" value="${p.refWorksMemberNo}">
							<input type="hidden" name="postCmtId" value="${p.postId}">
			            	<div class="input-group mb-3">
							  <input type="text" class="form-control" name="cmtContent" placeholder="Comment 입력" aria-describedby="basic-addon2">
							  <div class="input-group-append">
							    <input class="btn btn-outline-secondary" type="submit" value="등록">
							  </div>
							</div>
		            	</form>
	           		</div>
		        </div>
		        <div align="right">
		        </div>
		    </div>
		</div>

<%@ include file='/WEB-INF/views/common/footer.jsp'%>