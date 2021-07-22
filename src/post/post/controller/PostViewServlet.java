package post.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.File.model.service.FileService;
import post.File.model.vo.Files;
import post.category.model.service.CateoryService;
import post.category.model.vo.Category;
import post.cmt.model.service.CmtService;
import post.cmt.model.vo.Cmt;
import post.hashtag.model.service.HashtagService;
import post.hashtag.model.vo.Hashtag;
import post.post.model.service.PostService;
import post.post.model.vo.Post;

@WebServlet("/post/view")
public class PostViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostService();
	private FileService fileService = new FileService();
	private CmtService cmtService = new CmtService();
	private HashtagService tagService = new HashtagService();
	private CateoryService cateoryService = new CateoryService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("postId"));
		System.out.println(id);
		int result = postService.Count(id);
		Post post = postService.getPost(id);
		String fileName = post.getRefFileRenameedName();
		Files file = fileService.getFile(fileName);
		String writer = postService.getWriter(id);
		
		Category cat = new Category();
		
		if(file != null) {
			String orgFile = file.getFileOriginalName();
			request.setAttribute("org", orgFile);
		}
		
		cat = cateoryService.getCategory(id);
		if(cat != null) {
			request.setAttribute("cat", cat);
		}
		
		List<Cmt> cmtList = cmtService.getListCmt(id);
		List<Hashtag> hashtag= tagService.getListPost(post.getPostId());
		
		request.setAttribute("writer", writer);
		request.setAttribute("post", post);
		request.setAttribute("cmt", cmtList);
		request.setAttribute("tag", hashtag);
		request.getRequestDispatcher("/WEB-INF/views/post/postView.jsp").forward(request, response);
	}

}
