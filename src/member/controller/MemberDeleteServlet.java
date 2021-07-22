package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. encoding처리
		request.setCharacterEncoding("utf-8");
				
		//2. 사용자 입력 값 처리
		String memberId = request.getParameter("memberId");
		
		int result = memberService.memberDelete(memberId);
		
		
		String msg = "";
		String loc = request.getContextPath();
		HttpSession session = request.getSession();
		if(result>0) {
//			msg = "성공적으로 회원정보를 삭제했습니다.";
			//세션무효화후 새로 생성
			session.invalidate();

			session = request.getSession();
		}
		else {
			msg = "회원정보삭제에 실패했습니다.";
			loc = request.getContextPath() + "/member/memberView?memberId=" + memberId;
		}
		
		session.setAttribute("msg", msg);
		
		response.sendRedirect(loc);
	}

}
