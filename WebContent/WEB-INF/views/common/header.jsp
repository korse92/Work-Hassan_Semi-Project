<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="member.model.vo.Member"%>
<%@page import="workspace.model.vo.Workspace"%>
<%@page import="java.util.HashMap"%>
<%@page import="workspace.model.vo.WorkspaceMember"%>
<%@page import="channel.model.vo.Channel"%>
<%@page import="java.util.List"%>
<%
	String msg = (String)session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");//1회 사용후 폐기
	
	//로그인 유저
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
	
	//로그인한 유저의 워크스페이스 목록
	List<Workspace> workspaceList = (List<Workspace>)session.getAttribute("workspaceList");
	//System.out.println("workspaceList@header.jsp = " + workspaceList);
	
	//현재 워크스페이스 객체
	Workspace currentWrkspace = (Workspace)session.getAttribute("currentWrkspace");
	//System.out.println("currentWrkspace@header.jsp = " + currentWrkspace);
	//현재 워크스페이스 멤버 리스트
	HashMap<Integer, WorkspaceMember> wksMemberHashMap = (HashMap<Integer, WorkspaceMember>)session.getAttribute("wksMemberHashMap");
	//System.out.println("wksMemberHashMap@header.jsp = " + wksMemberHashMap);
	//로그인한 유저의 현재 워크스페이스 내의 멤버 객체와 매칭
	WorkspaceMember currentWrksMember = (WorkspaceMember)session.getAttribute("currentWrksMember");
	//System.out.println("currentWrksMember@header.jsp = " + currentWrksMember);
	
	//현재 워크스페이스의 채널 리스트
	List<Channel> channelList = (List<Channel>)session.getAttribute("channelList");
	//System.out.println("channelList@header.jsp = " + channelList);
	//현재 채널 객체
	Channel currentChannel = (Channel)session.getAttribute("currentChannel");
	//System.out.println("currentChannel@header.jsp = " + currentChannel);
%>

<!DOCTYPE html>
<html lang="ko">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Work, hassan!</title>

<!-- 메인폰트 영역 -->
<link
	href="<%= request.getContextPath() %>/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- 통합 템플릿 스타일시트  -->
<link href="<%= request.getContextPath() %>/css/sb-admin-2.css"	rel="stylesheet">
<!-- 개별 상세 스타일시트 별도 -->

<link href='<%= request.getContextPath()%>/fullcalendar/main.css' rel='stylesheet' />
<script src='<%= request.getContextPath()%>/fullcalendar/main.js'></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.5.1.js"></script>
    
<!-- 헤드부분 스크립트 -->
<script>
	<% if(msg != null) { %>	alert("<%= msg %>"); <% } %>
</script>

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- 슬라이드바 -->
		<ul	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">
			
			<!-- 슬라이드바 상단 좌측 아이콘 및 프로젝트명 영역 -->
			<div class="dropdown no-arrow">
				<a class="sidebar-brand d-flex align-items-center justify-content-center"
				   href="#" id="worksDropdown"
				   role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<div class="sidebar-brand-icon">
						<!-- 파비콘 i로 번개 모양 삽입되었음 -->
						<img style="width: 40px;"
							src="<%= request.getContextPath() %>/img/logo.png">
					</div>
					<div class="sidebar-brand-text mx-1">
						Work, hassan!
					</div>
					<i>
						<svg viewBox="-1 -1 9 11" style="width: 20px; height: 18px; fill: white;">
							<path d="M 3.5 0L 3.98809 -0.569442L 3.5 -0.987808L 3.01191 -0.569442L 3.5 0ZM 3.5 9L 3.01191 9.56944L 3.5 9.98781L 3.98809 9.56944L 3.5 9ZM 0.488094 3.56944L 3.98809 0.569442L 3.01191 -0.569442L -0.488094 2.43056L 0.488094 3.56944ZM 3.01191 0.569442L 6.51191 3.56944L 7.48809 2.43056L 3.98809 -0.569442L 3.01191 0.569442ZM -0.488094 6.56944L 3.01191 9.56944L 3.98809 8.43056L 0.488094 5.43056L -0.488094 6.56944ZM 3.98809 9.56944L 7.48809 6.56944L 6.51191 5.43056L 3.01191 8.43056L 3.98809 9.56944Z">
							</path>
						</svg>
					</i>
				</a>
				<!-- 워크스페이스 드롭다운 메뉴 -->
				<div class="dropdown-menu animated--fade-in" aria-labelledby="worksDropdown" style="width: 13.5rem;">
					<h6 class="dropdown-header">워크스페이스 목록</h6>
					<!-- 워크스페이스 목록 -->
					<% if(workspaceList == null || workspaceList.isEmpty()) { %>
						<a class="dropdown-item disabled px-0 text-center"><span>가입된 워크스페이스가 없습니다.</span></a>
				    <% } else { %>
						<% for(Workspace wrks : workspaceList) { %>
							<% if(wrks.getWorkspaceId() != currentWrkspace.getWorkspaceId()) { %>
								<a class="dropdown-item"
								   href="<%= request.getContextPath() %>/workspace/wrksChange?wrksId=<%= wrks.getWorkspaceId() %>">
								   		<span><%= wrks.getWorkspaceName() %></span>
								</a>
							<% } else { %>
								<a class="dropdown-item text-primary disabled d-flex align-items-center justify-content-between"
								   href="<%= request.getContextPath() %>/workspace/wrksChange?wrksId=<%= wrks.getWorkspaceId() %>">
								   		<span><%= wrks.getWorkspaceName() %></span>
								   		<i class="fas fa-fw fa-check"></i>
								</a>
							<% } %>
				    	<% } %>				    
				    <% } %>
				    <!-- 워크스페이스 드롭다운 Divider -->
				    <div class="dropdown-divider"></div>
				    <a class="dropdown-item" href="<%= request.getContextPath() %>/wrks/wrksCreate">새 워크스페이스 생성</a>
				    <% if(currentWrkspace != null) { %><a class="dropdown-item" href="#" data-toggle="modal" data-target="#checkInviteLink">워크스페이스 초대링크 확인</a><% } %>
				</div>
			</div>
			
			<% if(currentWrkspace != null) { %>
			<div class="modal fade" id="checkInviteLink" tabindex="-1" role="dialog" aria-labelledby="checkInviteLinkLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="checkInviteLinkLabel">초대링크 확인</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								  <span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div class="container">
									<div class="row">
										<span>
											워크스페이스 이름 : <%= currentWrkspace.getWorkspaceName() %>
										</span>
										<hr />
									</div>
									<div class="row">
										<a href="<%= "http://localhost:9090/hassan/wrks/invite?workspaceInviteLink=" + currentWrkspace.getWorkspaceInviteLink() %>">
											<%= "http://localhost:9090/hassan/wrks/invite?invite?workspaceInviteLink=" + currentWrkspace.getWorkspaceInviteLink() %>
										</a>
									</div>
								</div>
							</div>						
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary btn-user btn-block col" data-dismiss="modal">닫기</button>
							</div>
						</div>
					</div>
				</div>
				<% } %>

            <!-- 현재 워크스페이스 이름 -->
            <hr class="sidebar-divider">
            <div class="sidebar-heading">
				<span>Workspace</span>
            </div>
			<li class="nav-item active">
				<div class="bg-primary rounded mb-2">
						<a class="nav-link py-1" href="<%= request.getContextPath() %>/main">
							<i class="fas fa-fw fa-users"></i>
							<span><%= currentWrkspace != null ? currentWrkspace.getWorkspaceName() : "" %></span>
						</a>
				</div>			
			</li>
			
			<!-- Divider -->
			<% if(currentWrkspace != null) { %>
			<!-- Todo -->
			<hr class="sidebar-divider">
            <div class="sidebar-heading">
				<span>Main menu</span>
            </div>
            
            <li class="nav-item active">
                <a class="nav-link py-1" href="<%= request.getContextPath()%>/todo/todoMain">
                    <span>TODO</span></a>
            </li>
            
  			<!-- Calendar -->          
            <li class="nav-item active">
                <a class="nav-link py-1" href="<%= request.getContextPath()%>/calendar/calendarMain">
                    <span>CALENDAR</span></a>
            </li>
      		<li class="nav-item active">
              <a class="nav-link py-1" href="<%= request.getContextPath() %>/post">
              <span>POST</span></a>
            </li>
            <hr class="sidebar-divider">
			
			<!-- 채널 목록 메뉴 -->
			<div class="sidebar-heading">
				<span>Chat channel</span>
            </div>
            <% } %>
            
			<% if(channelList == null || channelList.isEmpty()) { %>
		    <% } else { %>
				<% for(Channel ch : channelList) { %>
					<% if(ch.getChannelId() != currentChannel.getChannelId()) { %>
						<li class="nav-item">
							<a class="nav-link py-1"
							   href="<%= request.getContextPath() %>/channel/channelChange?channelId=<%= ch.getChannelId() %>">
							   		<i class="fas fa-fw"></i>
							   		<span><%= ch.getChannelName() %></span>
							</a>
						</li>
					<% } else { %>
						<li class="nav-item active">
							<a class="nav-link py-1 disabled"
							   href="<%= request.getContextPath() %>/channel/channelChange?channelId=<%= ch.getChannelId() %>">
							   		<i class="fas fa-fw fa-check"></i>
							   		<span><%= ch.getChannelName() %></span>
							</a>
						</li>
					<% } %>
		    	<% } %>		    
		    <% } %>
		    
		    <% if(currentWrkspace != null) { %>
			    <li class="nav-item">
					<a class="nav-link py-0 text-right" href="#" data-toggle="modal" data-target="#channelAddModal">
						<i class="fas fa-fw fa-plus-circle"></i>
					</a>
				</li>
				
				<!-- Channel Add Modal -->
				<div class="modal fade" id="channelAddModal" tabindex="-1" role="dialog" aria-labelledby="channelAddModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="channelAddModalLabel">채널생성</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								  <span aria-hidden="true">&times;</span>
								</button>
							</div>
							<form 
								class="user" name="channelCreateFrm"
	                           	action="<%= request.getContextPath() %>/channel/channelCreate"
	                           	method="post">
								<div class="form-group container">
									<div class="modal-body row">
										<input type="hidden" name="refWorkspaceId" value="<%= currentWrkspace.getWorkspaceId() %>" />										
										<input
											type="text"
											class="form-control form-control-user col"
											id="channelName" name="channelName"
											placeholder="채널 이름 입력">
									</div>							
									<div class="modal-footer row">
										<button type="button" class="btn btn-secondary btn-user btn-block col" data-dismiss="modal">닫기</button>
										<button type="submit" class="btn btn-primary btn-user btn-block col">채널생성</button>
									</div>						
								</div>
							</form>
						</div>
					</div>
				</div>
			<% } %>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Search -->
                    <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>


						<% if(memberLoggedIn != null) { %>
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">똑바로 서라, <%= memberLoggedIn.getMemberName() %>!</span>
                                <img class="img-profile rounded-circle"
                                    src="<%= 
                                    		memberLoggedIn.getRenamedProfile() != null
                                    			?	request.getContextPath() + "/upload/profile/" + memberLoggedIn.getRenamedProfile()
                                    			:	request.getContextPath() + "/img/logo.png"
                                    	%>">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/member/memberView?memberId=<%= memberLoggedIn.getMemberId() %>">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
									회원정보 수정
                                </a>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									로그아웃
                                </a>
                            </div>
                        </li>
                        <% } %>

                    </ul>

                </nav>
                <!-- End of Topbar -->