package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.util.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class SuccessPwdFind
 */
@WebServlet("/member/successPwdFind")
public class SuccessPwdFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String AuthenticationKey = (String)request.getSession().getAttribute("AuthenticationKey");
		String AuthenticationUser = request.getParameter("AuthenticationUser");
		String memberId = request.getParameter("memberId");
		
		String newPassword = MvcUtils.getEncryptedPassword(request.getParameter("newPassword")); //암호화처리
		
		
		if(!AuthenticationKey.equals(AuthenticationUser))
        {
            System.out.println("인증번호 일치하지 않음");
            request.setAttribute("msg", "인증번호가 일치하지 않습니다");
            request.getRequestDispatcher("/WEB-INF/views/member/successPwdFind.jsp").forward(request, response);
            return;
        }
		else if(AuthenticationKey.equals(AuthenticationUser)) {
			Member member = memberService.selectId(memberId);
			String msg = "";
			String loc = request.getContextPath();
			//현재 member객체에 갱신할 비밀번호를 업데이트
			member.setMemberPwd(newPassword);
			int result = memberService.updatePassword(member);
			if(result > 0) {
				msg = "패스워드 변경 성공!";
			}
			else {
				msg = "패스워드 변경 실패!";
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			response.sendRedirect(loc);
		}
		
//		request.getRequestDispatcher("/WEB-INF/views/member/successPwdFind.jsp")
//		   .forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
//		request.setCharacterEncoding("utf-8");
		doGet(request, response);
//		String AuthenticationKey = (String)request.getSession().getAttribute("AuthenticationKey");
//		String AuthenticationUser = request.getParameter("AuthenticationUser");
//		
//		String memberId = request.getParameter("memberId");
//		
//		String newPassword = MvcUtils.getEncryptedPassword(request.getParameter("newPassword")); //암호화처리
//		
//		
//		if(!AuthenticationKey.equals(AuthenticationUser))
//        {
//            System.out.println("인증번호 일치하지 않음");
//            request.setAttribute("msg", "인증번호가 일치하지 않습니다");
//            request.setAttribute("loc", request.getContextPath());
//            request.getRequestDispatcher("/WEB-INF/views/member/successPwdFind.jsp").forward(request, response);
//            return;
//        }
//		else {
//			Member member = memberService.selectId(memberId);
//			String msg = "";
//			String loc = request.getContextPath();
//			//현재 member객체에 갱신할 비밀번호를 업데이트
//			member.setMemberPwd(newPassword);
//			int result = memberService.updatePassword(member);
//			if(result > 0) {
//				msg = "패스워드 변경 성공!";
//				loc += "/index.jsp";
//			}
//			else {
//				msg = "패스워드 변경 실패!";
//			}
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("msg", msg);
//			response.sendRedirect(loc);
//		}
		
	}

}
