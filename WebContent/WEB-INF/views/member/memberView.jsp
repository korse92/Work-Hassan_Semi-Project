<%@page import="member.model.vo.Member"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member member = (Member)request.getAttribute("member");
	
%>

    <!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MyPage</title>

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
	$(function(){   

	    
		/**
		* 폼유효성 검사
		*/
	    $("#memberUpdateFrm").submit(function(e){
	    	   	
	        
	        //memberName
	        var $memberName = $("#memberName");
	        if(/^[가-힣]{2,}$/.test($memberName.val()) == false){
	        	alert("이름은 한글 2글자 이상이어야 합니다.");
	        	$memberName.select();
	        	return false;
	        }
	        
	        //phone
	        var $phone = $("#phone");
	        //-제거하기
	        $phone.val($phone.val().replace(/[^0-9]/g, ""));//숫자아닌 문자(복수개)제거하기
	        
	        if(/^010[0-9]{8}$/.test($phone.val()) == false){
	        	alert("유효한 전화번호가 아닙니다.");
	        	$phone.select();
	        	return false;
	        }
	        
	        return true;
	        
	    });
		    
	});


	function updateMember(){
		var url = "<%=request.getContextPath() %>/member/memberUpdate";
		$("#memberUpdateFrm")
			.attr('action',url)
			.submit();	
	}

	function deleteMember(){
	    var bool = confirm("정말로 탈퇴하시겠습니까?");
	    if(bool)
	        location.href = "<%=request.getContextPath() %>/member/memberDelete?memberId=<%=member.getMemberId()%>";
	}

	function updatePassword(){
		location.href = "<%= request.getContextPath() %>/member/updatePassword?memberId=<%= member.getMemberId() %>";
	}

	function setThumbnail(event) { 
	    var reader = new FileReader();
	    reader.onload = function(event) { 
	        var img = document.createElement("img"); 
	        img.setAttribute("src", event.target.result); 
	        document.querySelector("div#profile_container").appendChild(img); 
	        $(profile).hide();
	        $(oldImg).val('').hide();
	        
	    };
	    var readerimg = reader.readAsDataURL(event.target.files[0]);
	    $(profile_container).show();
	   
	} 
	</script>
	<style>
			img{
	            width: 100px;
	            height: 100px; 
	            border-radius: 70%;
	        }
	</style>
</head>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">My Page</h1>
                            </div>
                            
                            <form class="user" id="memberUpdateFrm"  method="post" enctype="multipart/form-data">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    <!-- 프로필 -->
                                    	
                                    <% if(member.getOriginalProfile() != null){ %>
                                    	<input type="hidden" value="<%= member.getOriginalProfile() %>" name="oldOriginalProfile">
                                    	<input type="hidden" value="<%= member.getRenamedProfile() %>" name="oldRenamedProfile">
                                        <img id="oldImg"  alt="<%= member.getOriginalProfile() %>" src="<%= request.getContextPath() %>/upload/profile/<%= member.getRenamedProfile() %>"/>
									<% } %>
									<input type="file" name="upFile" id="profile" accept="image/*" onchange="setThumbnail(event);" value="">
										<div id="profile_container" style="text-align: center;"></div>
                                    </div>
                                    <div class="col-sm-6">
                                    <!-- 아아디 -->
                                        <input type="text" class="form-control form-control-user" name="memberId" id="memberId_" value="<%= member.getMemberId() %>" readonly>
									</div>
                                </div>
                                
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    <!-- 이름-->
                                        <input type="text" class="form-control form-control-user" name="memberName"
                                            id="memberName" value="<%= member.getMemberName() %>"  required>
                                    </div>
                                    <div class="col-sm-6">
                                    <!-- 생일-->
                                        <input type="date" class="form-control form-control-user" name="birthDay"
                                            id=birthDay value="<%= member.getBirthday() %>">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                    <!-- 메일-->
                                        <input type="email" class="form-control form-control-user" id="email" name="email"
                                        value="<%= member.getEmail() != null ? member.getEmail() : "" %>">
                                    </div>
                                    <div class="col-sm-6">
                                    <!-- 폰-->
                                        <input type="tel" class="form-control form-control-user" id="phone" name="phone"  maxlength="11"
                                        value="<%= member.getPhone() %>" required>
                                    </div>
                                </div>
                                <div class="form-group" style="text-align: center;">
                                <!-- 성별-->
                                    <input type="radio" name="gender" id="gender0" value="M" <%= member.getGender().equals("M") ? "checked" : "" %>>
									<label for="gender0">M</label>
									&emsp;
									<input type="radio" name="gender" id="gender1" value="F" <%= member.getGender().equals("F") ? "checked" : "" %>>
									<label for="gender1">F</label>
                                </div>
                                
                                
                                    
                                <hr>
                                <div class="form-group row">
                                    <div class="col-sm-5 mb-3 mb-sm-0">
	                                <input type="button" class="btn btn-primary btn-user btn-block" onclick="updateMember();" value="M o d i f y"/>
	                                </div>
	                                <div class="col-sm-5">
						        	<input type="button" class="btn btn-info btn-user btn-block" onclick="updatePassword();" value="M o d i f y&emsp;P a s s w o r d"/>
						        	</div>
	                                <div class="col-sm-2">
							        <input type="button" class="btn btn-danger btn-circle" onclick="deleteMember();" value="del"/>
							        </div>
						        </div>
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="<%= request.getContextPath() %>/main">Main Page</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>


</body>
</html>