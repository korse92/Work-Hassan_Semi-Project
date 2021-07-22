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

    <title>Forgot Password</title>

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
		 $("[name=memberPwdFindFrm]").submit(function(e){
			//memberId
	    	var $memberId = $("#memberId_");
	    	//아이디는 영문자/숫자  4글자이상만 허용 
	        if(/^[a-zA-Z0-9]{4,}$/.test($memberId.val()) == false){
	            alert("아이디는 최소 4자리이상이어야 합니다.");
	            $memberId.select();
	            return false;
	        }
	    	
	      //memberName
	        var $memberName = $("#memberName");
	        if(/^[가-힣]{2,}$/.test($memberName.val()) == false){
	        	alert("이름은 한글 2글자 이상이어야 합니다.");
	        	$memberName.select();
	        	return false;
	        }
	        
	        return true;
		 });
	});


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
                                        <h1 class="h4 text-gray-900 mb-2">Forgot Your Password?</h1>
                                        <p class="mb-4">We get it, stuff happens. Just enter your email address below
                                            and we'll send you a link to reset your password!</p>
                                    </div>
                                    <form class="user"
                                    	name="memberPwdFindFrm" 
										action="<%=request.getContextPath()%>/member/memberPwdFind" 
										method="post" >
										<div class="form-group">
											<input type="text" class="form-control form-control-user" name="memberId" id="memberId" placeholder="ID" tabindex="1" required>                                       
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user" name="memberName" id="memberName" placeholder="your name" required>
                                        </div>
                                         <div class="form-group">   
                                         <input type="email" class="form-control form-control-user" placeholder="Enter Email Address..." name="email" id="email" required>                  
                                        </div>
                                        <input class="btn btn-primary btn-user btn-block" type="submit"  value="F i n d&emsp;Y o u r&emsp;P a s s w o r d" />
                                        <input type="button" class="btn btn-google btn-user btn-block" value="B a c k" onclick="location.href='<%= request.getContextPath() %>'"/>
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="<%= request.getContextPath() %>/member/memberEnroll">Create an Account!</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="<%= request.getContextPath() %>/member/memberLogin">Already have an account? Login!</a>
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