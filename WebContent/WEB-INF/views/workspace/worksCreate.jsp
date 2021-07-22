<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
%>

<!DOCTYPE html>
<html lang="ko">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create Workspace</title>

    <!-- Custom fonts for this template-->
    <link
		href="<%= request.getContextPath() %>/vendor/fontawesome-free/css/all.min.css"
		rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%= request.getContextPath() %>/css/sb-admin-2.css"	rel="stylesheet">

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-hassan-image">
                    </div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900">Hello, Hassan!</h1>
                                <h1 class="h6 text-gray-900 mb-4">워크스페이스 생성</h1>
                            </div>
                            <form
                            	class="user" name="worksCreateFrm"
                            	action="<%= request.getContextPath() %>/wrks/wrksCreate"
                            	method="post">                            	
                                <div class="form-group">
                                    <input
                                    	type="text"
                                    	class="form-control form-control-user"
                                    	id="workspaceName" name="workspaceName"
                                        placeholder="워크스페이스 이름 입력">
                                </div>
                                <div class="form-group row">
                                	<!-- .mb-3 .mb-sm-0 : row당 margin-bottom 관련 클래스 -->
                                    <div class="col-sm-8 mb-3 mb-sm-0">
                                        <input
                                        	type="text"
                                        	class="form-control form-control-user"
                                        	id="searchId" name="searchId"
                                            placeholder="초대할 멤버 아이디 입력">
                                        <div class="invalid-feedback">
                                    		입력하신 회원이 존재하지 않습니다.
                                    	</div>
                                    </div>
                                    <div class="col-sm-4">
                                		<input type="button" value="초대할 멤버 추가"
                                			   class="btn btn-primary btn-user btn-block" onclick="addInviteMember();"/>
                                    </div>
                                </div>
                                <div class="my-3 row">
                                    <div class="col-sm">
                                    	<p class="h6 font-weight-bold">추가된 구성원 목록</p>
	                                    <ul class="list-group list-group-flush" id="memberList">
										</ul>                                    
                                    </div>
                                </div>
                                <div class="form-group row">
                                	<input type="submit" class="btn btn-primary btn-user btn-block" value="워크스페이스 생성"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
    <script src="<%= request.getContextPath() %>/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="<%= request.getContextPath() %>/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%= request.getContextPath() %>/js/sb-admin-2.min.js"></script>
    
    <script>
    $("[name=worksCreateFrm]").submit(function(){
    	var $workspaceName = $("#workspaceName");
    	
    	if(!$workspaceName.val()) {
    		alert("생성할 워크스페이스 이름을 입력해주세요.");
    		return false;
    	}
    	
    	return true;    	
    });
    
    function ajaxSearchName(request, response){
		//서버 비동기 통신
		$.ajax({
			url : "<%= request.getContextPath() %>/wrks/searchMember",
			data : {
				searchId : request.term
			},
			method : "GET",
			dataType : "json",
			//jQuery에서 자동으로 json문자열을 javascript객체로 변환해주었다.
			success : function(data){
				//console.log(data);
				
				var arr = new Array();
				
				$.each(data, function(index, obj) {
					var memberId = obj.memberId
					
					arr.push(memberId);				
				});
				
				var searchIdVal = $("#searchId").val()
				
				//console.log(arr);
				
				if(arr.includes(searchIdVal))
					$("#searchId").removeClass("is-invalid");
				else
					$("#searchId").addClass("is-invalid");
				
				arr = $.map(arr, function(str){
					//배열요소 -> 변경처리 -> 요소리턴
					return {
						label : str,
						value : str
					};
				});
				//console.log(arr);
				//검색어 목록 작성
				response(arr);
				
			},
			error : function(xhr, status, err){
				console.log(xhr, status, err);
			}
		});
	}
	
	//my : widget 요소의 위치 지정 "x축위치 y축위치"
	//at : widget요소의 부모 요소를 기준으로 어디에 위치할지 지정 "x축위치 y축위치"
	$("#searchId").autocomplete({
		source : function(request, response){
			//배열이 아닌 함수형태로도 처리가 가능하다.
			//console.log(request);//사용자 입력값을 포함하는 객체
			//console.log(response);//자동검색어 목록을 만들기 위한 함수
			ajaxSearchName(request, response);
		},
		minLength : 1,
		delay : 500,
		focus : function(e, focus){
			//포커스를 가져도 선택되지 않도록함.
			return false;
		},
		select : function(e, select){
			//console.log(select.item);
			$("#searchId").removeClass("is-invalid");
		}
	});
	
	function addInviteMember() {
		var $searchId = $("#searchId");
		
		if($searchId.hasClass("is-invalid") || !$searchId.val()) {
			alert("존재하는 회원ID를 입력해주세요");
		} else if($searchId.val() == "<%= memberLoggedIn.getMemberId() %>"){
			alert("현재 로그인 중인 고객님을 제외한 회원ID를 입력해주세요.");
		} else {
			var $inviteMembers = $("[name=inviteMembers]");
			var dupFlag = false;
			$.each($inviteMembers, function(index, elem) {
				if(elem.value == $searchId.val()) {
					dupFlag = true;
					return false;
				}
			});
			
			if(!dupFlag) {
				var liTag = "<li class='list-group-item'>"
						  + $searchId.val()
						  + "<input type='hidden' name='inviteMembers' value='" + $searchId.val() + "'/></li>";
				$("#memberList").append(liTag);				
			} else
				alert("이미 추가된 회원입니다.");
		}
		
	}	
    </script>

</body>
</html>