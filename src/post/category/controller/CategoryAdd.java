package post.category.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.category.model.service.CateoryService;


@WebServlet("/category/add")
public class CategoryAdd extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CateoryService service = new CateoryService();
		
		String title = request.getParameter("Categorytitle");
		boolean idx = service.confirmCategory(title);
		
		//카테고리 생성
		if(idx) {
			service.insertCategory(title);
		} 
		
		response.sendRedirect(request.getContextPath()+"/post");
	}
}
