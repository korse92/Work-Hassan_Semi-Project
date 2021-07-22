package workspace.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import workspace.model.service.WorkspaceService;
import workspace.model.vo.WorkspaceMember;

/**
 * Servlet implementation class WorkspaceInviteServlet
 */
@WebServlet("/wrks/invite")
public class WorkspaceInviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WorkspaceService workspaceService = new WorkspaceService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String workspaceInviteLink = request.getParameter("workspaceInviteLink");
		HttpSession session = request.getSession();
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		
		String msg = null;
		String location = null;
		int result = 0;
		
		if(memberLoggedIn == null) {
			System.out.println();
			msg = "로그인 화면으로 이동합니다.";
			session.setAttribute("workspaceInviteLink", workspaceInviteLink);
			location = request.getContextPath() + "/member/memberLogin";
		} else {
			String LoggedInMemberId = memberLoggedIn.getMemberId();
			String dupChkRefMemberId = workspaceService.selectRefMemIdByInviteLink(LoggedInMemberId, workspaceInviteLink);
			
			//워크스페이스에 동일한 회원이 여러번 가입하는 것 방지
			//로그인한 아이디가 해당 워크스페이스에 멤버로 이미 존재할 때
			if(LoggedInMemberId.equals(dupChkRefMemberId)) {
				msg = "해당 워크스페이스에 이미 가입되어 있습니다.";				
			} else {
				WorkspaceMember wrksMember = new WorkspaceMember(0, memberLoggedIn.getMemberId(), 0, memberLoggedIn.getMemberName(), "U", null);
				
				result = workspaceService.insertWrksMember(wrksMember, workspaceInviteLink);
				if(result > 0) {
					msg = "워크스페이스 가입에 성공하였습니다!";
				} else {
					msg = "워크스페이스 가입에 실패하였습니다!";
				}				
			}
			
			location = request.getContextPath() + "/main";
		}
		
		session.setAttribute("msg", msg);
		response.sendRedirect(location);		
	}

}
