<%@page import="todo.model.vo.Todo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Todo> todoList = (List<Todo>)request.getAttribute("todoList");
	Todo todo = (Todo)request.getAttribute("todo");
%>
    
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    
                    <!-- Begin Page Content -->
                <div class="container-fluid">
                
                    <!-- 제목 시작 -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">ToDo</h1>
                    </div>
                    <!-- 여기까지 제목 -->
                    
					<div class="row">
					
					
					<div class="col-4">
						<input 
							class="btn btn-primary btn-block"
							type="button" 
							value="+ TODO"
							onclick="location.href='<%= request.getContextPath() %>/todo/todoAddEvent';">
					<div>
					
					
                    <div class="my-4"></div>
                       
	                   <% for(Todo t : todoList) { %>
	                   
	                   <% if(t.getTodoStatus().equals("TODO")) { %>
                       <!-- 카드 시작 -->     
	                       <div class="card mb-4 border-left-primary">
	                       		<div class="card-header">
			                      		<dt><h4><%= t.getTodoTitle()%></h4></dt>
	                                <div class="card-body">
		                      			<dl>
			                               	<dd><%= t.getTodoContent()%><dd>
		                                	<hr class="sidebar-divider">
			                               	<dt>등록일</dt><dd><%= t.getTodoRegDate()%></dd>
			                               	<dt>진행기간</dt><dd><%= t.getTodoStartDate() %> ~ <%= t.getTodoEndDate() %></dd>
		                               		<hr class="sidebar-divider">
		                               	</dl>
		                        	</div>
		                        
	                               	<!-- 버튼시작 -->
	                               	<div class="d-flex justify-content-end">
		                               	<button 
		                               		type="button" 
		                               		value="<%= t.getTodoId()%>" 
		                               		class="btn btn-circle btn-secondary btn-sm" 
		                               		id="openModalBtn">
		                               		<i class="fas fa-edit fa-fw m-1"></i>
		                               	</button>
		                               	<button type="button" value="<%= t.getTodoId()%>" class="btn btn-circle btn-danger btn-sm mx-1" onclick="deleteTodo0(<%= t.getTodoId()%>);">
		                               		<i class="fas fa-trash fa-fw m-1"></i>
		                               	</button>
	                               	</div>
	                               	<!-- 버튼끝 -->
                                </div>
	                        </div>
	                	<% } %>
	                	<% } %>
	                	<!-- 카드 끝 -->
	                	
	                		<!-- todo삭제/수정 스크립트 -->
	                        <script>

	                        $(document).on("click","#openModalBtn",function(){	
	                        	var a = location.href="<%=request.getContextPath()%>/todo/todoUpdate?todoId="+$(this).val();
	                        	console.log(a);
	                            /* $(a).modal('show');	 */
	                          });
	                        
	                        <%-- js코드를 제이쿼리로 수정, 혹시 모르니 주석처리 --%>
<%-- 							function updateTodo0(todoId){
								console.log(todoId);
							    location.href="<%=request.getContextPath()%>/todo/todoUpdate?todoId="+todoId;
							}          --%>     	
							                		
							function deleteTodo0(todoId){
								console.log(todoId);
							    location.href="<%=request.getContextPath()%>/todo/todoDelete?todoId="+todoId;
							}
							</script>
							
	                	</div>
					</div>
	                	
					<div class="col-4">
						<input 
							class="btn btn-warning btn-block"
							type="button" 
							value="+ DOING"
							onclick="location.href='<%= request.getContextPath() %>/todo/todoAddEvent';">
					<div>
					
					<div class="my-4"></div>
                       
	                   <% for(Todo t : todoList) { %>
	                   
	                   <% if(t.getTodoStatus().equals("DOING")) { %>
                       <!-- 카드 시작 -->     
	                       <div class="card mb-4 border-left-warning">
	                       		<div class="card-header">
			                      		<dt><h4><%= t.getTodoTitle()%></h4></dt>
	                                <div class="card-body">
		                      			<dl>
			                               	<dd><%= t.getTodoContent()%><dd>
		                                	<hr class="sidebar-divider">
			                               	<dt>등록일</dt><dd><%= t.getTodoRegDate()%></dd>
			                               	<dt>진행기간</dt><dd><%= t.getTodoStartDate() %> ~ <%= t.getTodoEndDate() %></dd>
		                               		<hr class="sidebar-divider">
		                               	</dl>
		                        	</div>
	                               	<!-- 버튼시작 -->
	                               	<div class="d-flex justify-content-end">
		                               	<button type="button" value="<%= t.getTodoId()%>" class="btn btn-circle btn-secondary btn-sm" onclick="updateTodo1('<%= t.getTodoId()%>');">
		                               		<i class="fas fa-edit fa-fw m-1"></i>
		                               	</button>
		                               	<button type="button" value="<%= t.getTodoId()%>" class="btn btn-circle btn-danger btn-sm mx-1" onclick="deleteTodo1('<%= t.getTodoId()%>');">
		                               		<i class="fas fa-trash fa-fw m-1"></i>
		                               	</button>
	                               	</div>
	                               	<!-- 버튼끝 -->
	                               	
                                </div>
	                        </div>
	                	<% } %>
	                	<% } %>
	                	<!-- 카드 끝 -->
	                	
	                		<!-- todo삭제/수정 스크립트 -->
	                        <script>

							function updateTodo1(todoId){
								console.log(todoId);
							    location.href="<%=request.getContextPath()%>/todo/todoUpdate?todoId="+todoId;
							}              	
							                		
							function deleteTodo1(todoId){
								console.log(todoId);
							    location.href="<%=request.getContextPath()%>/todo/todoDelete?todoId="+todoId;
							}
							</script>
							
	                	</div>
					</div>
	                	
					<div class="col-4">
						<input 
							class="btn btn-success btn-block"
							type="button" 
							value="+ DONE :)"
							onclick="location.href='<%= request.getContextPath() %>/todo/todoAddEvent';">
					<div>
					
					<div class="my-4"></div>
                       
	                   <% for(Todo t : todoList) { %>
	                   
	                   <% if(t.getTodoStatus().equals("DONE")) { %>
                       <!-- 카드 시작 -->     
	                       <div class="card mb-4 border-left-success">
	                       		<div class="card-header">
			                      		<dt><h4><%= t.getTodoTitle()%></h4></dt>
	                                <div class="card-body">
		                      			<dl>
			                               	<dd><%= t.getTodoContent()%><dd>
		                                	<hr class="sidebar-divider">
			                               	<dt>등록일</dt><dd><%= t.getTodoRegDate()%></dd>
			                               	<dt>진행기간</dt><dd><%= t.getTodoStartDate() %> ~ <%= t.getTodoEndDate() %></dd>
		                               		<hr class="sidebar-divider">
		                               	</dl>
		                        	</div>
	                               	<!-- 버튼시작 -->
	                               	<div class="d-flex justify-content-end">
		                               	<button type="button" value="<%= t.getTodoId()%>" class="btn btn-circle btn-secondary btn-sm" onclick="updateTodo2('<%= t.getTodoId()%>');">
		                               		<i class="fas fa-edit fa-fw m-1"></i>
		                               	</button>
		                               	<button type="button" value="<%= t.getTodoId()%>" class="btn btn-circle btn-danger btn-sm mx-1" onclick="deleteTodo2('<%= t.getTodoId()%>');">
		                               		<i class="fas fa-trash fa-fw m-1"></i>
		                               	</button>
	                               	</div>
	                               	<!-- 버튼끝 -->
	                               	
                                </div>
	                        </div>		
	                	<% } %>
	                	<% } %>
	                	<!-- 카드 끝 -->
	                	
	                        <!-- todo삭제/수정 스크립트 -->
	                        <script>

							function updateTodo2(todoId){
								console.log(todoId);
							    location.href="<%=request.getContextPath()%>/todo/todoUpdate?todoId="+todoId;
							}              	
							                		
							function deleteTodo2(todoId){
								console.log(todoId);
							    location.href="<%=request.getContextPath()%>/todo/todoDelete?todoId="+todoId;
							}
							</script>
							
	                	</div>
	                	</div>
					</div>
					</div>
            <!-- End of Main Content -->
					
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>