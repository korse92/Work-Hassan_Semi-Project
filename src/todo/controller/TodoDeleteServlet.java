package todo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.model.service.TodoService;

@WebServlet("/todo/todoDelete")
public class TodoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoService todoService = new TodoService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int todoId = Integer.parseInt(request.getParameter("todoId"));
		
		System.out.println("삭제할 때 어떤 번호를 들고오니?" + todoId);
		int result = todoService.deleteTodo(todoId);
		String msg = result > 0 ? "삭제 성공!" : "삭제 실패!";
		
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/todo/todoMain");
	}

}
