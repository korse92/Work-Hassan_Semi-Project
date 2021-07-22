<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />

                <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <h2 class="mt-4">회원 목록 조회</h2>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table mr-1"></i>
                                회원 정보
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    
                                        <!-- 예시 데이터 삽입(회원정보.vo가 구현되어 있지 않기 때문) -->
                                        <thead>
                                        <tr>
                                            <th>아이디</th>
                                            <th>이름</th>
                                            <th>생년월일</th>
                                            <th>성별</th>
                                            <th>이메일</th>
                                            <th>휴대폰</th>
                                            <th>가입일</th>
                                            <th>회원상태</th>
                                            <th>활성화/탈퇴</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="row" items="${memberlist}">
                                        <tr>
                                            <td>${row.memberId}</td>
                                            <td>${row.memberName}</td>
                                            <td>${row.birthday}</td>
                                            <td>${row.gender}</td>
                                            <td>${row.email}</td>
                                            <td>${row.phone}</td>
                                            <td>${row.enrollDate}</td>
                                            <td>${row.memberStatus}</td>
                                           	<td align="center">
    	                                        <form action="memberList" method="post">
                                            		<input type="hidden" id="memberId" name="memberId" value=${row.memberId}>
                                            		<button type="submit" class="btn btn-primary">탈퇴</button>
	                                            </form>
                                           	</td>
                                       </tr>
                                       </c:forEach>   
                                    </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                   </div>
                
            <!-- End of Main Content -->
            
<%@ include file="footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="../js/datatables-demo.js"></script>
</body>
</html>