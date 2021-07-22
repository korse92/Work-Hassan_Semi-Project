package post.post.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import post.category.model.service.CateoryService;
import post.category.model.vo.Category;
import post.hashtag.model.service.HashtagService;
import post.hashtag.model.vo.Hashtag;
import post.post.model.service.PostService;
import post.post.model.vo.Post;
import post.File.model.vo.Files;

@WebServlet("/post/update")
public class PostUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostService postService = new PostService();
	CateoryService catService = new CateoryService();
	FileService fileService = new FileService();
	HashtagService tagService = new HashtagService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		catService.getCategoryList();
		List<Category> list = new ArrayList<Category>();
		list = catService.getCategoryList();
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		Post post = postService.getPost(postId);
		Files file = fileService.getFile(post.getRefFileRenameedName());
		Hashtag tag = tagService.getName(post.getPostId());
		if(tag != null) {
			request.setAttribute("tag", tag);
		}
		request.setAttribute("file", file);
		request.setAttribute("listData", list);
		request.setAttribute("post", post);
		
		request.getRequestDispatcher("/WEB-INF/views/post/postUpdate.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = getServletContext().getRealPath("/upload/post");
		int maxPostSize = 10 * 1024 * 1024;
		String encoding = "utf-8";

		FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		MultipartRequest mul = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		int postId = Integer.parseInt(mul.getParameter("postId"));
		String category = mul.getParameter("category");
		int id = 0;
		Category cat = catService.getCategory(category);
		if(cat != null) {
			id = cat.getCategoryId();
		}
		System.out.println("업데이트 시작 -----------------");
		System.out.println(cat);
		String title = mul.getParameter("title");
		String content = mul.getParameter("content");
		String hashtag = mul.getParameter("hashtag");
		System.out.println(hashtag);
		String org = mul.getOriginalFileName("upFile");
		System.out.println(org);
		String rename = mul.getFilesystemName("upFile");
		System.out.println(rename);
		String delFile = mul.getParameter("delFile");
		System.out.println(delFile);
		//기존첨부파일정보
		String oldorg = mul.getParameter("oldFile");
		System.out.println(oldorg);
		String oldrename = mul.getParameter("reNameFile");
		System.out.println(oldrename);
		
		tagService.updateTag(hashtag,postId);
		System.out.println("확인용 update"+tagService.updateTag(hashtag,postId));
		
		//기존파일이 있는 경우
		if(oldorg != null) {
			//1.업로드한 파일 가져오기 | 2.기존파일제거
			File upFile = mul.getFile("upFile");
			if(upFile != null || delFile != null) {
				//새파일 업로드 또는 기존파일 제거하는 경우
				File realDelFile = new File(saveDirectory, delFile);
				boolean bool = realDelFile.delete();
				System.out.println(delFile + " : " + (bool ? "기존 파일 삭제 성공!" : "기존 파일 삭제 실패!"));
			}
			else {
				//3.기존파일정보를 유지하는 경우
				org = oldorg;
				rename = oldrename;
			}
		}
		
		Files file = fileService.getFile(rename);
		fileService.updateFile(rename,org,postId);
		Post post = new Post(postId,id,title,content,rename);
		//2. 업무로직 : Board객체 db수정 요청
		int result = postService.updatePost(post);
		
//		String msg = result > 0 ? "게시글 수정 성공!" : "게시글 수정 실패!"; 
		String location = request.getContextPath() + "/post/view?postId=" + postId;

		//3.사용자 피드백(msg) 및 redirect처리
		//DML이후 반드시 요청url을 변경할 것
//		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(location);
	}

}
