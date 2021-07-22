<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");//1회 사용후 폐기
	//세션에서 MemberLoggedIn 가져옴
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>hassan에 오신걸 환영합니다.</title>
    
    <!-- 메인폰트 영역 -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- 개별 상세 스타일시트 별도(admin-2.min은 조작하지 말 것) -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
    
	
	<script>
		<% if(msg != null) { %>	alert("<%= msg %>"); <% } %>
		<% if(memberLoggedIn != null) { %> location.href = "<%= request.getContextPath() + "/main" %>" <% } %>
	</script>
</head>

<body class="bg-gradient-primary">
  
    <div class="container">
    	

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">
     

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Welcome Hassan!</h1>
                                    </div>
                                    <form class="user">
                                    	<hr>
                                        <a href="<%= request.getContextPath() %>/member/memberLogin" class="btn btn-primary btn-user btn-block">
                                            L o g i n
                                        </a>
                                        <hr>
                                        <a href="<%= request.getContextPath() %>/member/memberEnroll" class="btn btn-google btn-user btn-block">
                                            <img src="./img/logo.png" alt="" style="width: 23px"> J o i n&emsp;H a s s a n
                                        </a>
                                      </form>  
                                   
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="<%= request.getContextPath() %>/member/memberIdFind">Forgot Id?</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="<%= request.getContextPath() %>/member/memberPwdFind">Forgot Password?</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>
    
	<!-- footer 관리자 로그인-->
    <footer>
    	<a href="<%= request.getContextPath() %>/admin/login" class="btn btn-admin btn-login btn-block">
    	<i class="fas fa-fw fa-cog"></i>
          <span>Super-admin</span>
          </a>
    </footer>
</body>

</html>