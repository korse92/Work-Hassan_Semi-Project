<%@page import="todo.model.vo.Todo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Todo todo = (Todo)request.getAttribute("todo");
	List<Todo> todoList = (List<Todo>)request.getAttribute("todoList");
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<script>
function boardView(){
	history.go(-1);
}
</script>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5 ">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="p-5 text-center">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">What are you going to do?</h1>
                            </div>
                            <form class="user" 
                            	  name="todoAddEvent"
                            	  action="<%=request.getContextPath() %>/todo/todoAddEvent"
                            	  method="post">
                            	  
                            	<input type="hidden" name="workspaceIdRef" value="<%= currentWrkspace.getWorkspaceId() %>" />
                            	<input type="hidden" name="workspaceMemberNoRef" value="<%= currentWrksMember.getWorksMemberNo() %>"/>

                                <div class="row">
                                    <div class="col-lg-8" style="float: none; margin: 0 auto;">
                                        <input 
                                        	type="text" 
                                        	class="form-control form-control-user" 
                                        	name="todoTitle" 
                                        	id="todoTitle_"
                                            placeholder="Title">
                                            <br />
                                    </div>
                                    <br />
                                    <div class="col-lg-8" style="float: none; margin: 0 auto;">
                                        <textarea 
                                        	class="form-control form-control-user" 
                                        	name="todoContent" 
                                        	id="todoContent_"
                                            placeholder="Content"></textarea>
                                            <br />
                                    </div>
                                    
                                    <div class="my-4" ></div>
                                    
	                                <div class="col-lg-8 my-3" style="float: none; margin: 0 auto;">
                                    	<div class="btn-group btn-group-toggle" data-toggle="buttons">
													<label class="btn btn-primary" for="todo0">
													<input type="radio" name="todoStatus" id="todo0" class="custom-control-input" checked="checked" value = "TODO"> TODO
													</label> 
													
													<label class="btn btn-warning" for="todo1">
													<input type="radio" name="todoStatus" id="todo1" class="custom-control-input" value = "DOING"> DOING
													</label> 
													
													<label class="btn btn-success" for="todo2">
													<input type="radio" name="todoStatus" id="todo2" class="custom-control-input" value = "DONE"> DONE
													</label> 
	                                    </div>
	                                </div>  
                                    
                                    <div class="col-lg-8 my-3" style="float: none; margin: 0 auto;">
                                    		Start<br />
						   					<input type="date" class="form-control form-control-user" name="todoStartDate" id="todoStartDate_" ><br />
                                    </div>
                                    <div class="my-4"></div>
                                    
                                    <div class="col-lg-8 my-3" style="float: none; margin: 0 auto;">
                                    		End<br />
						   					<input type="date" class="form-control form-control-user" name="todoEndDate" id="todoEndDate_" ><br />
                                    </div>
                                    <div class="my-4"></div>

                                </div>
                                
                            	<hr>
	                                <input type="submit" class="btn btn-primary btn-split center" value="등록" >
	                                <input type="reset" class="btn btn-danger btn-split center" value="취소" onclick="boardView();">
                                <hr>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="<%= request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
    <script src="<%= request.getContextPath()%>/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="<%= request.getContextPath()%>/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%= request.getContextPath()%>/js/sb-admin-2.min.js"></script>

</body>
</html>