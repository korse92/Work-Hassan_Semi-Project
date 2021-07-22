package post.cmt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.cmt.model.service.CmtService;
import post.cmt.model.vo.Cmt;
import post.post.model.service.PostService;

/**
 * Servlet implementation class Cmtdelete
 */
@WebServlet("/cmt/delete")
public class Cmtdelete extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CmtService cmtService = new CmtService();
		int id = Integer.parseInt(request.getParameter("cmt_id"));
	 	Cmt Cmt = cmtService.getCmt(id);
	 	int postId = Cmt.getCmt_ref_post_id();
	 	cmtService.deleteCmt(id);
	 	response.sendRedirect(request.getContextPath() + "/post/view?postId="+postId);
	}

}
