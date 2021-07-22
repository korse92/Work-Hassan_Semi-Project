package workspace.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import channel.model.service.ChannelService;
import channel.model.vo.Channel;
import common.mail.SendMail;
import member.model.vo.Member;
import workspace.model.service.WorkspaceService;
import workspace.model.vo.Workspace;
import workspace.model.vo.WorkspaceMember;

/**
 * Servlet implementation class WorkspaceCreateServlet
 */
@WebServlet("/wrks/wrksCreate")
public class WorkspaceCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WorkspaceService workspaceService = new WorkspaceService();
	private ChannelService channelService = new ChannelService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/workspace/worksCreate.jsp")
			   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.입력값 처리
		Member memberLoggedIn = (Member)request.getSession().getAttribute("memberLoggedIn");
		String workspaceName = request.getParameter("workspaceName");
		
		String[] inviteMembers = request.getParameterValues("inviteMembers"); 
		
		String workspaceInviteLink = UUID.randomUUID().toString().replace("-", "");		
		//System.out.println("workspaceInviteLink@EncodingBefore = " + workspaceInviteLink);
		
		byte[] bytes = workspaceInviteLink.getBytes("UTF-8");
		
		workspaceInviteLink = Base64.getEncoder().encodeToString(bytes);		
		//System.out.println("workspaceInviteLink@EncodingAfter = " + workspaceInviteLink);
		
		//2.비지니스 로직
		Workspace workspace = new Workspace(0, workspaceName, workspaceInviteLink, null, null);
		//System.out.println("workspace@WorkspaceCreateServlet = " + workspace);
		
		
		int result = workspaceService.insertWorkspace(workspace);
		
		//피드백 메시지
		String msg = null;
		
		if(result > 0) {
			msg = "워크스페이스 생성 성공!";
			//여기에 들어가야함 메일보내기, 만든사람 관리자로 워크스멤버로 추가
			
			//초대멤버에게 메일보내기
			if(inviteMembers != null) {
				InternetAddress[] toEmails = new InternetAddress[inviteMembers.length];
				
				for(int i = 0; i < inviteMembers.length; i++) {
					String email = workspaceService.selectMemberEmail(inviteMembers[i]);
					try {
						toEmails[i] = new InternetAddress(email);
					} catch (AddressException e) {
						e.printStackTrace();
					}				
					
				}
				SendMail sendMail = new SendMail();
				sendMail.sendWrksInviteMail(toEmails, workspace);	
			}
			
			//관리자 멤버로 설정
			WorkspaceMember wrksMember = new WorkspaceMember(0, memberLoggedIn.getMemberId(), 0, memberLoggedIn.getMemberName(), "A", null);
			
			int insMemberResult = workspaceService.insertWrksMember(wrksMember, workspaceInviteLink);
			
			if(insMemberResult > 0) {
				//msg += "\\n워크스페이스 기본멤버(만든사람) 삽입 성공!";
			}
			else {
				//msg += "\\n워크스페이스 기본멤버(만든사람) 삽입 실패!";
			}
			
			Channel channel = new Channel(0, "General", 0, "N");
			
			int insDefaultChResult = channelService.insertChannelByInviteLink(channel, workspaceInviteLink);
			
			if(insDefaultChResult > 0) {
				//msg += "\\n기본채널(General) 생성 성공!";
			} else {
				//msg += "\\n기본채널(General) 생성 실패!";
			}			
		}
		else {
			msg = "워크스페이스 생성 실패!";
		}
				
		System.out.println("msg@WorkspaceCreateServlet = " + msg);
		
		//3.페이지 이동
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/main");
	}

}
