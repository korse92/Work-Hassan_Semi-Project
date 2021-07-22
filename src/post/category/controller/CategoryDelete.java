package post.category.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.category.model.service.CateoryService;

@WebServlet("/category/delete")
public class CategoryDelete extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("categoryId");
		int catNum = Integer.parseInt(num);
		
		CateoryService service =new CateoryService();
		int result = service.removeCategory(catNum);
		if(result == 0) {
			System.out.println("실패");
		}
		response.sendRedirect(request.getContextPath() + "/post");
	}
}
