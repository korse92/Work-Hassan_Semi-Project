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
import post.hashtag.model.service.HashtagService;
import post.hashtag.model.vo.Hashtag;
import post.post.model.service.PostService;
import post.post.model.vo.Post;

@WebServlet("/post/search")
public class PostSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HashtagService tagservice = new HashtagService();
	CateoryService service = new CateoryService();
	PostService postService = new PostService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String keyword = request.getParameter("keyword");
		List<Hashtag> keyList = tagservice.getListPost(keyword);
		List<Integer> hashtagList = new ArrayList<>();
		for(Hashtag l : keyList) {
			hashtagList.add(l.getHashtagRefPostId());
		}
		if(!hashtagList.isEmpty()) {
			PostService postService = new PostService();
			List<Post> result = postService.getListPost(hashtagList);
			request.setAttribute("result", result);
		} else {
			String msg = "검색된 결과가 없습니다.";
			request.setAttribute("msg", msg);
		}
		
		service.getCategoryList();
		List<Category> list = new ArrayList<>();
		list = service.getCategoryList();
		List<Post> postlist = new ArrayList<>();
		postlist = postService.getListPost();
		
		
		request.setAttribute("category", list);
		request.setAttribute("postlist", postlist);
		
		request.getRequestDispatcher("/WEB-INF/views/post/postSearchResult.jsp").forward(request, response);
	}

}
