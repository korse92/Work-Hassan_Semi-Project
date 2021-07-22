package todo.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.model.service.TodoService;
import todo.model.vo.Todo;

@WebServlet("/todo/todoUpdate")
public class TodoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoService todoService = new TodoService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int todoId;
		
		todoId = Integer.parseInt(request.getParameter("todoId"));
		
		Todo todo = todoService.selectOne(todoId);
		
		request.setAttribute("todo", todo);
		request.getRequestDispatcher("/WEB-INF/views/todo/todoUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int todoId = Integer.parseInt(request.getParameter("todoId"));
		
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
		
		Todo todo = new Todo(todoId, workspaceIdRef, workspaceMemberNoRef, todoTitle, todoContent, 
								null, todoStatus, todoStartDate_, todoEndDate_);
		
		System.out.println("todoUpdate 동작여부 체크" + todo + "정상로드 확인");
		
		int result = todoService.updateTodo(todo);
		String msg = result > 0 ? "수정 성공!" : "수정 실패!"; 
		String location = request.getContextPath() + "/todo/todoMain;";
		
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(location);
	}

}
