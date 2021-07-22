<%@page import="member.model.vo.Member"%>
<%@ page import="member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	
	String msg = (String)session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");//1회 사용후 폐기
	
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
	
	String saveId = null;
	Cookie[] cookies = request.getCookies();//쿠키배열을 돌려준다.
	if(cookies != null){
		for(Cookie c : cookies){
			if("saveId".equals(c.getName())){
				saveId = c.getValue();
				break;
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>로그인</title>
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
    
    <script>
    
	<% if(msg != null){ %> alert("<%= msg %> "); <% } %>
	
	$(function(){
		<% if(memberLoggedIn == null) { %>
			/* 온로드함수 : 모든 html코드 등록을 마치고 나서 실행될 것 */
			
			/*
			* 로그인폼 유효성 검사
			*
			* 폼 전송을 방지
			* return false
			* e.preventDefault()
			*/
			$(loginFrm).submit(function(e){
				//아이디 검사
				/* ^. 아무거나로 시작해서 
				 * {4,} 4글자~ 이상
				 * $ 아무거나로 끝나는거
				 * .test(memberId.val) memberId 넣고 검사 */
				var $memberId = $(memberId);
				if(/^.{4,}$/.test($memberId.val()) == false){
					alert("유효한 아이디를 입력하세요.");
					$memberId.select();
					return false;//폼 전송 방지
				}
				//비번검사
				var $memberPwd = $(memberPwd);
				if(/^.{4,}$/.test($memberPwd.val()) == false){
					alert("유효한 비밀번호를 입력하세요.");
					$memberPwd.select();
					e.preventDefault();//폼 전송 방지(위와 같다)
				}
			});
			
		<% } %>	
		
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
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Welcome Hassan!</h1>
                                    </div>
                                    <form class="user"
                                    	id="loginFrm"
										action="<%= request.getContextPath() %>/member/memberLogin"
										method="POST">
                                        <div class="form-group">
                                        	<input type="text" class="form-control form-control-user" name="memberId" id="memberId" placeholder="Enter Id" tabindex="1" value="<%= saveId != null ? saveId : ""%>">                                       
                                        </div>
                                        <div class="form-group">
                                        	<input type="password" class="form-control form-control-user" name="memberPwd" id="memberPwd" placeholder="Password" tabindex="2">
                                            
                                        </div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" name="saveId" id="saveId" <%= saveId != null ? "checked" : "" %>/>
                                                <label class="custom-control-label" for="saveId">Remember
                                                    Me</label>
                                            </div>
                                        </div>
                                        <input type="submit" class="btn btn-primary btn-user btn-block" value="L o g i n" tabindex="3">
                                      
                                        <hr>
                                        <a href="<%= request.getContextPath() %>/member/memberEnroll" class="btn btn-google btn-user btn-block">
                                            <img src="../img/logo.png" alt="" style="width: 23px"> J o i n&emsp;H a s s a n
                                        </a>
                                        <hr>
	                                    <div class="text-center">
	                                        <a class="small" href="<%= request.getContextPath() %>/member/memberIdFind">Forgot Id?</a>
	                                    </div>
	                                    <div class="text-center">
	                                        <a class="small" href="<%= request.getContextPath() %>/member/memberPwdFind">Forgot Password?</a>
	                                    </div>
                                    </form>
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