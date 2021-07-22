<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
    <c:set var="con" value="${pageContext.request.contextPath}"/>
               <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- 404 Error Text -->
                    <div class="text-center">
                        <div class="error mx-auto" data-text="ERROR">ERROR</div>
                        <p class="lead text-gray-800 mb-5">Page Not Found</p>
                        <p class="text-gray-500 mb-0">오 마이 갓! 핫산 오류 발생했다.</p>
                        <a href="<%= request.getContextPath() %>">&larr; 메인으로 돌아가기</a>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->
            
<%@ include file="/WEB-INF/views/common/footer.jsp"%>