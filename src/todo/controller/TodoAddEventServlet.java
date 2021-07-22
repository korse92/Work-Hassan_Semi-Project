package todo.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.model.service.TodoService;
import todo.model.vo.Todo;

@WebServlet("/todo/todoAddEvent")
public class TodoAddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoService todoService = new TodoService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		request
			.getRequestDispatcher("/WEB-INF/views/todo/todoAddEvent.jsp")
			.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int workspaceIdRef = Integer.parseInt(request.getParameter("workspaceIdRef"));
		int workspaceMemberNoRef = Integer.parseInt(request.getParameter("workspaceMemberNoRef"));
		String todoTitle = request.getParameter("todoTitle");
		String todoContent = request.getParameter("todoContent");
		String todoStartDate = request.getParameter("todoStartDate");
		String todoEndDate = request.getParameter("todoEndDate");
		
		String[] todoStatuses = request.getParameterValues("todoStatus");
		
		String todoStatus = "";
		if(todoStatuses != null) todoStatus = String.join(",", todoStatuses);
		
		//날짜타입으로 변경 : 0000-00-00
		Date todoStartDate_ = null;
		if(todoStartDate != null && !"".equals(todoStartDate))
			todoStartDate_ = Date.valueOf(todoStartDate);
		
		Date todoEndDate_ = null;
		if(todoEndDate != null && !"".equals(todoEndDate))
			todoEndDate_ = Date.valueOf(todoEndDate);
		
		Todo todo = new Todo(0, workspaceIdRef, workspaceMemberNoRef, todoTitle, todoContent, 
								null, todoStatus, todoStartDate_, todoEndDate_);
		
		System.out.println("todoAddEvent 동작여부 체크" + todo + "정상로드 확인");
		
		int result = todoService.insertTodoEvent(todo);
		
		String msg = "";
		
		if(result > 0)
			msg = "성공적으로 등록되었습니다.";
		else 
			msg = "등록에 실패했습니다.";	
		
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/todo/todoMain");
	}

}
