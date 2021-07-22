package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.util.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberLog
 */
@WebServlet("/member/memberLogin")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberLogin.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		String memberPwd = MvcUtils.getEncryptedPassword(request.getParameter("memberPwd"));
		
		String saveId = request.getParameter("saveId");
		
		Member member = new MemberService().selectId(memberId);
		
		//System.out.println("member@MemberLoginServlet = " + member);
		
		String location = null;
		//로그인 성공 & 비밀번호 일치 여부
		if(member != null && memberPwd.equals(member.getMemberPwd())) {
			/**
			 * session 객체에 로그인한 사용자정보 기록
			 * create 여부 (기본값은 true) , 세션객체가 존재시 해당객체 리턴, 없으면 생성 후 리턴 
			 */
			HttpSession session = request.getSession(true);
			session.setAttribute("memberLoggedIn", member);
			
			session.setMaxInactiveInterval(60*30);//(초단위) 아무것도 아나면 세션 종료 (30분으로 임시 지정)
			
			//saveId 쿠키 설정
			Cookie c = new Cookie("saveId", memberId);
			c.setPath(request.getContextPath());//쿠키 전송 디렉토리
			
			//saveId체크한 경우
			if(saveId != null) {				
				//클라이언트에서 쿠키 보관할 시간 설정
				c.setMaxAge(7 * 24 * 60 * 60);//임시 일주일 설정				
			}
			
			//saveId체크하지 않은 경우 : 브라우져의 쿠키 삭제
			else {
				c.setMaxAge(0);//즉시 삭제
			}
			response.addCookie(c);//컨테이너에서 클라이언트로 생성된 쿠키 전송
			
			//워크스페이스 초대링크를 통해 로그인 화면에 왔는지 확인
			String workspaceInviteLink = (String)session.getAttribute("workspaceInviteLink");
			if(workspaceInviteLink == null) {
				location = request.getContextPath() + "/main";
			} else {
				location = request.getContextPath() + "/wrks/invite?workspaceInviteLink=" + workspaceInviteLink;
				session.removeAttribute("workspaceInviteLink");
			}
		}		
		//로그인 실패 : 아이디존재 x, 비밀번호 틀린겅우
		else {
			request.getSession().setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");			
			location = request.getContextPath() + "/member/memberLogin";
		}
		
		//redirection처리 : 요청 url 변경
		response.sendRedirect(location);
	}
}
