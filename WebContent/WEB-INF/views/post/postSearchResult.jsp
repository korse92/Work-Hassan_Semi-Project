<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="con" value="${pageContext.request.contextPath}"/>
<c:set var="result" value="${result}" />
	
	<div class="container-fluid">
    <h1 class="h3 ml-3 mb-3">게시판 검색</h1>
		<c:if test="${not empty result}">
	      <div class="row" id="search">
			<c:forEach var="p" items="${result}">
	        <div class="col-6 mt-3">
	          <div class="ml-2">작성자 / ${p.refWorksMemberNo}</div>
	          <div class="card card-block">
	            <div class="card-header bg-primary text-white">
	              ${p.postRegDate}
	            </div>
	            <div class="card-body">
	              <h5 class="card-title">${p.postTitle}</h5>
	              <p class="card-text">${p.postContent}</p>
	              <a href="${con}/post/view?postId=${p.postId}" class="btn btn-primary">자세히</a>
	            </div>
	          </div>
	       	</div>
			</c:forEach>
	      </div>
		</c:if>
		<c:if test="${empty result}">
			<div class="container-fluid text-center mt-5 mb-5">
				<h1>검색결과가 없습니다.</h1>
			</div>    
		</c:if>
		<div >
	</div>
		<div>
			<div align="right">
			<form action="" >
				<input type="text" class="" name="keyword" placeholder="검색">
				<input type="submit" class="btn btn-primary" value="전송" />
			</form>
				<button id="exit" class="btn btn-primary" onclick="location.href='${con}/post'">나가기</button>
			</div>
		</div>
	</div>
	
<%@ include file='/WEB-INF/views/common/footer.jsp'%>