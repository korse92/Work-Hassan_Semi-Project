package post.post.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.MvcFileRenamePolicy;
import post.File.model.service.FileService;
import post.File.model.vo.Files;
import post.category.model.service.CateoryService;
import post.category.model.vo.Category;
import post.hashtag.model.service.HashtagService;
import post.hashtag.model.vo.Hashtag;
import post.post.model.service.PostService;
import post.post.model.vo.Post;


@WebServlet("/post/add")
public class PostWriterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CateoryService service = new CateoryService();
		service.getCategoryList();
		List<Category> list = new ArrayList<Category>();
		list = service.getCategoryList();
		request.setAttribute("listData", list);
		request.getRequestDispatcher("/WEB-INF/views/post/postWriter.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostService postService = new PostService();
		CateoryService service = new CateoryService();
		FileService fileService = new FileService();
		HashtagService tagService = new HashtagService();
		String saveDir = getServletContext().getRealPath("/upload/post");
		int maxSize = 10 * 1024 * 1024;
		String encoding = "utf-8";
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		MultipartRequest mul = new MultipartRequest(request, saveDir, maxSize, encoding, policy);
		int result =0;
		int postId =0;
		Post post = null;
		int id = 0;
		
		int wrkNo =1;
		int memberNO = 1;
		String org = mul.getOriginalFileName("upFile");
		String rename = mul.getFilesystemName("upFile");
		String category = mul.getParameter("category");
		String title = mul.getParameter("title");
		String content = mul.getParameter("content");
		String tag = mul.getParameter("hashtag");
		Category cat = service.getCategory(category);
		
		//카테고리값
		if(cat != null) {
			id = cat.getCategoryId();
		}

		//파일 확인용
//		Files file = fileService.getFile(rename);
		if(org != null) {
			Files file = new Files(rename, org, "P", 0, 0);
			System.out.println(file);
			fileService.insertFile(file);
		}
		//파일 먼저 집어넣어두고
		post = new Post(0, wrkNo, memberNO, id, title, content, null, 0, rename);
		postService.insertPost(post);
		//해시태그도 집어넣고
		//포스트값 인서트하고
		postId = postService.getPost(rename);
		System.out.println(postId);
		result = fileService.updateFile(rename,org,postId);
		if(tag != null){
			Hashtag hashtag = new Hashtag(tag,postId, 0, "P");
			System.out.println(hashtag);
			tagService.insertTag(hashtag);
		}
		response.sendRedirect(request.getContextPath() + "/post");
		
	}
}
