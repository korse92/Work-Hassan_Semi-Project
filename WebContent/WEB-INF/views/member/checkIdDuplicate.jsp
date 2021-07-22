<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String memberId = request.getParameter("memberId");
	boolean available = (boolean)request.getAttribute("available");
%>

<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
<title>아이디중복검사</title>
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
//아이디 중복검사
function checkIdDuplicate(){
	//1.아이디 유효성 검사
	var $memberId = $(memberId_);
	if(/^[a-zA-Z0-9_]{4,}$/.test($memberId.val()) == false){
		alert("유효한 아이디를 입력해주세요.");
		$memberId.select();//커서놔줌
		return;
	}
	//2.중복검사
	//var $frm = $("[name=checkIdDuplicateFrm]");
	var $frm = $(document.checkIdDuplicateFrm);//name값은 document에서 바로 접근 가능 위에와 같음
	//아이디값 세팅
	$frm.find("[name=checkIdDuplicateFrm]")
		.val($memberId.val());
	//.attr()은 요소(element)의 속성(attribute)의 값을 가져오거나 속성을 추가
	$frm.submit();	
}

/**
 * 1. 부모창 #memberId_에 값대입
 * 2. 부모창 #idValid에 값 대입(1)
 */
function confirmMemberId(){
	//opener : 팝업을 연 부모창의 window객체
	var $frm = $(opener.document.memberEnrollFrm);
	$frm.find("#memberId_").val("<%= memberId %>");
	$frm.find("#idValid").val(1);
	//현재 팝업 윈도우 닫기
	//console.log($frm.find("#idValid").val());
	close();
}

</script>
<style>
	body{
		    background-color: #f8f9fc;
		    background-image: linear-gradient(
		180deg
		,#f8f9fc 10%,#c2cbe5 100%);
		    background-size: cover;
	}
	div#checkId-container{text-align:center; padding-top:50px;}
	span#duplicated{color:red; font-weight:bold;}
</style>
</head>
<body>
	<div id="checkId-container" style=".bg-gradient-warning">
		<% if(available) { %>
			[<%= memberId %>]는 사용 가능합니다.
			<br/><br/>
			<input type="button" class="btn btn-success btn-circle btn-lg" value="use" onclick="confirmMemberId();">
			
		<% } else { %>
			[<span id="duplicated"><%= memberId %></span>]는 이미 사용중입니다.
			<br/><br/>
			<form class="user"
				action="<%= request.getContextPath() %>/member/checkIdDuplicate"
				method="POST"
				name="checkIdDuplicateFrm">
				<input type="text" name="memberId" id="memberId_" placeholder="아이디"/>
				<input type="button" class="btn btn-danger btn-circle" value="check" onclick="checkIdDuplicate();">	
				
			</form>
		<% } %>
	</div>
</body>
</html>
