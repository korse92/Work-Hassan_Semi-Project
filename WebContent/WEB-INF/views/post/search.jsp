<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="con" value="${pageContext.request.contextPath}"/>
<c:set var="post" value="${postlist}" />
	
	
	<button id="btn">검색</button>
	<button id="exit" onclick="location.href='/post'">나가기</button>
	<div id="result-container">
	</div>
	<script>
	$("#btn").click(function(){
		$.ajax({
			url : "<%= request.getContextPath() %>/post/search",
			method : "GET",
			dataType : "html",
			success : function(data){
				console.log(data);
				$("#result-container").html(data);
			},
			error : function(xhr, textStatus, err){
				console.log(xhr, textStatus, err);
			}
		});		
	});
	
	<%@ include file='/WEB-INF/views/common/footer.jsp'%>