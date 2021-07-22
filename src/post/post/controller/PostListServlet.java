package post.post.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.category.model.service.CateoryService;
import post.category.model.vo.Category;
import post.cmt.model.service.CmtService;
import post.cmt.model.vo.Cmt;
import post.hashtag.model.service.HashtagService;
import post.hashtag.model.vo.Hashtag;
import post.post.model.service.PostService;
import post.post.model.vo.Post;

@WebServlet("/post")
public class PostListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CateoryService catService = new CateoryService();
		PostService postService = new PostService();
		
		List<Category> catList = catService.getCategoryList();
		System.out.println(catList);
		List<Post> postList = postService.getListPost();
		System.out.println(postList);
		
		request.setAttribute("catList", catList);
		request.setAttribute("postlist", postList);
		
		
		request.getRequestDispatcher("/WEB-INF/views/post/post.jsp")
		   .forward(request, response);
	}
	
}
