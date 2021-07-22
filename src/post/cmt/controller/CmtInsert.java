package post.cmt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.cmt.model.service.CmtService;
import post.cmt.model.vo.Cmt;

@WebServlet("/post/cmt")
public class CmtInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CmtService cmtService = new CmtService();
		int postId = Integer.parseInt(request.getParameter("postCmtId"));
		System.out.println("postId" + postId);
		int writer = Integer.parseInt(request.getParameter("cmtWriter"));
		System.out.println(writer);
		String content = request.getParameter("cmtContent");
		System.out.println("test1");
		Cmt cmt = new Cmt(0, content,null,postId,writer);
		System.out.println("test2");
		int result = cmtService.insertCmt(cmt);
		System.out.println("reuslt3 :" + result);
		System.out.println("-------" + writer);
		String cmtWriter = cmtService.cmtWriter(writer);
		System.out.println("reuslt :" + cmtWriter);
		request.setAttribute("cmtWriter", cmtWriter);
		response.sendRedirect(request.getContextPath()+"/post/view?postId="+postId);
	}
}
