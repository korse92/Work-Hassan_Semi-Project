package todo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.model.service.TodoService;
import todo.model.vo.Todo;
import workspace.model.vo.Workspace;


@WebServlet("/todo/todoMain")
public class TodoMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoService todoService = new TodoService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		List<Todo> todoList = new ArrayList<Todo>();
		todoList = todoService.todoListView();
		System.out.println("todoMain동작여부 체크" + todoList + "정상로드 확인");
		
		request.setAttribute("todoList", todoList);
		request.getRequestDispatcher("/WEB-INF/views/todo/todoMain.jsp")
			   .forward(request, response);
	
	}

}
