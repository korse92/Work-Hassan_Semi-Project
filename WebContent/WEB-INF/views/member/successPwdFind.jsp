<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	Member member = (Member)request.getAttribute("member");
	String msg = (String)request.getAttribute("msg");
%>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Find Password</title>

    <!-- 메인폰트 영역 -->
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- 개별 상세 스타일시트 별도(admin-2.min은 조작하지 말 것) -->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet">
        <!-- Bootstrap core JavaScript-->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../js/sb-admin-2.min.js"></script>
    
    <script src="<%=request.getContextPath()%>/js/jquery-3.5.1.js"></script>
	
<script>
<% if(msg != null) { %>	alert("<%= msg %>"); <% } %>

$(function(){
	//폼제출 유효성검사
	$("[name=successPwdFindFrm]").submit(passwordValidate);
	//신규비밀번호 일치 검사
	$("#newPasswordCheck").blur(passwordValidate);
});

function passwordValidate(){
	var $newPassword = $("#newPassword");
	var $newPasswordCheck = $("#newPasswordCheck");
	
	if($newPassword.val() != $newPasswordCheck.val()){
		alert("입력한 비밀번호가 일치하지 않습니다.");
		$newPassword.select();
		return false;
	}
	
	return true;	
}
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
                            <div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-2">Find Your Password!</h1>
                                        <p class="mb-4">Please check your email and change your password</p>
                                    </div>
                                    <form class="user"
                                    	name="successPwdFindFrm" 
										action="<%=request.getContextPath()%>/member/successPwdFind" 
										method="post">
                                    	<div class="form-group">
											<input type="text" class="form-control form-control-user" name="AuthenticationUser" id="AuthenticationUser" placeholder="Authentication number" tabindex="1" required>                                       
                                        </div>
                                        <div class="form-group">
											<input type="password" class="form-control form-control-user" name="newPassword" id="newPassword" placeholder="newPassword" tabindex="1" required>                                       
                                        </div>
                                        <div class="form-group">
											<input type="password" class="form-control form-control-user" id="newPasswordCheck" placeholder="check password" required>                                       
                                        </div>
											<hr>
											<input type="submit" class="btn btn-primary btn-user btn-block" value="C h a n g e&emsp;P a s s w o r d" >	
											
											<input type="hidden" name="memberId" value="<%= request.getParameter("memberId") %>"/>	
											
										</form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="<%= request.getContextPath() %>">MainPage</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>


</body>

</html>