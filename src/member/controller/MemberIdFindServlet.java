package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberIdFindServlet
 */
@WebServlet("/member/memberIdFind")
public class MemberIdFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/member/memberIdFind.jsp");
		reqDispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberName = request.getParameter("memberName");
		String phone = request.getParameter("phone");
		
		Member member = memberService.findId(memberName, phone);		
		
		if(member == null) {
			request.getSession().setAttribute("msg", "존재하지 않는 이름 또는 번호입니다.");
			response.sendRedirect(request.getContextPath() + "/member/memberIdFind");
		}
		else {
			request.setAttribute("member", member);		
			request.getRequestDispatcher("/WEB-INF/views/member/successIdFind.jsp")
				   .forward(request, response);
		}
	}

}
