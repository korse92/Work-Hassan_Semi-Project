package post.post.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.File.model.service.FileService;
import post.post.model.service.PostService;
import post.post.model.vo.Post;


@WebServlet("/post/delete")
public class Postdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("postId"));
		//가져오고
		PostService service = new PostService();
		Post post = service.getPost(postId);
		System.out.println(post.getRefFileRenameedName());
		service.deletePost(postId);
		if(post.getRefFileRenameedName() != null) {
			String savDir = getServletContext().getRealPath("/upload/post");
			File delete = new File(savDir, post.getRefFileRenameedName());
			delete.delete();
			FileService file = new FileService();
			file.removeFile(post.getRefFileRenameedName());
		}
		response.sendRedirect(request.getContextPath() + "/post");
	}
}
