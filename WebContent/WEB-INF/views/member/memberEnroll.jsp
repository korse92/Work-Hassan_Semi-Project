<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Join Hassan</title>

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
		* 비밀번호 일치여부 검사
		*/
	    $("#password2").blur(function(){
	        var $p1 = $("#password_");
	        var $p2 = $("#password2");
	        
	        if($p1.val() != $p2.val()){
	            alert("패스워드가 일치하지 않습니다.");
	            $p1.select();
	        }
	    });
	    
		/**
		* 폼유효성 검사
		*/
	    $("[name=memberEnrollFrm]").submit(function(e){
	    	//memberId
	    	var $memberId = $("#memberId_");
	    	//아이디는 영문자/숫자  4글자이상만 허용 
	        if(/^[a-zA-Z0-9]{4,}$/.test($memberId.val()) == false){
	            alert("아이디는 최소 4자리이상이어야 합니다.");
	            $memberId.select();
	            return false;
	        }
	    	
	    	//아이디 중복검사
	    	var $idValid = $("#idValid");
	    	if($idValid.val() != 1){ //0일때 가입버튼누르면 중복검사하라하고 1이면 가입되게
	    		alert("아이디 중복 검사해주세요");
	    		$idValid.focus();
	    		return false;
	    	}    	
	    	
	        //password
	        var $p1 = $("#password_");
	        var $p2 = $("#password2");
	        if(/^[a-zA-Z0-9!@#$$%^&*()]{4,}/.test($p1.val()) == false){
	        	alert("유효한 패스워드를 입력하세요.");
	        	$p1.select();
	            return false;
	        }
	        
	        if($p1.val() != $p2.val()){
	            alert("패스워드가 일치하지 않습니다.");
	            $p1.select();
	            return false;
	        }
	        
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
		
		/**
		* 중복 검사 이후 아이디를 변경한 경우, 다시 중복검사를 해야한다.
		*/
	    $("#memberId_").change(function(){
			$("#idValid").val(0); //중복검사 전까지 다시 가입되지 못하게 value를 0으로 고쳐줌
		});
	    
	});
	
	//아이디 중복검사
	function checkIdDuplicate(){
		//1.아이디 유효성 검사
		var $memberId = $(memberId_);
		if(/^[a-zA-Z0-9_]{4,}$/.test($memberId.val()) == false){
			alert("유효한 아이디를 입력해주세요.");
			$memberId.select();//커서놔줌
			return;
		}
		//2. 팝업을 통해 중복검사
		//폼제출 을 팜업에..
		//open(url, title, spec); spec은 팝업창 크기
		var title = "checkIdDuplicatePopup";
		var spec = "left=500px, top=300px, width=300px, height=200px";
		open("", title, spec);
		
		//var $frm = $("[name=checkIdDuplicateFrm]");
		var $frm = $(document.checkIdDuplicateFrm);//name값은 document에서 바로 접근 가능 위에와 같음
		//아이디값 세팅
		$frm.find("[name=memberId]")
			.val($memberId.val());
		//.attr()은 요소(element)의 속성(attribute)의 값을 가져오거나 속성을 추가
		$frm.attr("action", "<%= request.getContextPath() %>/member/checkIdDuplicate")
			.attr("method", "POST")
			.attr("target", title) //폼과 팝업 연결 설정
			.submit();
		
		
	}
	
	function setThumbnail(event) { 
        var reader = new FileReader();
        reader.onload = function(event) { 
            var img = document.createElement("img"); 
            img.setAttribute("src", event.target.result); 
            document.querySelector("div#profile_container").appendChild(img); 
            $(profile).hide();
        };
        var readerimg = reader.readAsDataURL(event.target.files[0]);
        $(profile_container).show();
       
    } 
    
	</script>
	<style>
		#profile_container img{
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
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            <!-- 아이디 중복 검사용 폼 -->
							<form name="checkIdDuplicateFrm">
								<input type="hidden" name="memberId"/>
							</form>
                            
                            <form class="user" name="memberEnrollFrm" action="<%= request.getContextPath() %>/member/memberEnroll" method="post" enctype="multipart/form-data">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="file" name="upFile" id="profile" accept="image/*" onchange="setThumbnail(event);"/> 
										<div id="profile_container" style="text-align: center;"></div>
                                    </div>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control form-control-user" placeholder="ID (4글자 이상)" name="memberId" id="memberId_" required>
									</div>
									<div class="col-sm-1" style="padding-left: 0px; padding-top: 8px">	
										<input type="button" class="btn btn-success btn-circle btn-sm" onclick="checkIdDuplicate();" value="✔" />
										<input type="hidden" id="idValid" value="0" />
										<%-- 중복검사 통과인경우 1 / 통과하지 못한 경우 0 --%>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" class="form-control form-control-user" name="password"
                                            id="password_" placeholder="Password" required>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control form-control-user" 
                                            id="password2" placeholder="Repeat Password" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" name="memberName"
                                            id="memberName" placeholder="Name" required>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="date" class="form-control form-control-user" name="birthDay"
                                            id=birthDay>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="email" class="form-control form-control-user" id="email" name="email"
                                        placeholder="Email Address" required>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="tel" class="form-control form-control-user" id="phone" name="phone"  maxlength="11"
                                        placeholder="(without - )01012345678" required>
                                    </div>
                                </div>
                                <div class="form-group" style="text-align: center;">
                                    <input type="radio" name="gender" id="gender0" value="M" checked>
									<label for="gender0">M</label>
									&emsp;
									<input type="radio" name="gender" id="gender1" value="F">
									<label for="gender1">F</label>
                                </div>
                                
                                <input type="submit" class="btn btn-primary btn-user btn-block" value="J o i n&emsp;H a s s a n" >
                                    
                                <hr>
                                <a href="<%= request.getContextPath() %>" class="btn btn-google btn-user btn-block">
                                    B a c k
                                </a>
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="<%= request.getContextPath() %>/member/memberLog">Already have an account? Login!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>


</body>
</html>