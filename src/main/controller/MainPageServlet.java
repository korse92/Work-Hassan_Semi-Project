package main.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import channel.model.service.ChannelService;
import channel.model.vo.Channel;
import member.model.vo.Member;
import workspace.model.service.WorkspaceService;
import workspace.model.vo.Workspace;
import workspace.model.vo.WorkspaceMember;

/**
 * Servlet implementation class MainPageServlet
 */
@WebServlet("/main")
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WorkspaceService workspaceService = new WorkspaceService();
	private ChannelService channelService = new ChannelService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		Workspace currentWrkspace = (Workspace)session.getAttribute("currentWrkspace");
		Channel currentChannel = (Channel)session.getAttribute("currentChannel");
		
		HashMap<Integer, WorkspaceMember> wksMemberHashMap = new HashMap<Integer, WorkspaceMember>();
		
		if(memberLoggedIn != null) {
			
			List<Workspace> workspaceList = workspaceService.selectWrksListByMemberId(memberLoggedIn.getMemberId());
//			System.out.println("workspaceList@MainPageServlet = " + workspaceList);			
			
			session.setAttribute("workspaceList", workspaceList);
			
			if(currentWrkspace == null 
					&& (workspaceList != null && !workspaceList.isEmpty()) ) {
				currentWrkspace = workspaceList.get(0);
				session.setAttribute("currentWrkspace", currentWrkspace);			
			}
			
			if(currentWrkspace != null) {
				List<WorkspaceMember> wrksMemberList = workspaceService.selectWrksMemListByWrksId(currentWrkspace.getWorkspaceId());
				for(WorkspaceMember wrksMember : wrksMemberList) {
					if(wrksMember.getRefMemberId().equals(memberLoggedIn.getMemberId())) {
						session.setAttribute("currentWrksMember", wrksMember);
					}
					
					wksMemberHashMap.put(wrksMember.getWorksMemberNo(), wrksMember);
				}
				
				List<Channel> channelList = channelService.selectChListByWrksId(currentWrkspace.getWorkspaceId());
//				System.out.println("wrksMemberList@MainPageServlet = " + wrksMemberList);
//				System.out.println("channelList@MainPageServlet = " + channelList);
				session.setAttribute("wksMemberHashMap", wksMemberHashMap);
				session.setAttribute("channelList", channelList);
				
				if(currentChannel == null 
						&& (channelList != null && !channelList.isEmpty()) ) {
					currentChannel = channelList.get(0);
//					System.out.println("currentChannel@MainPageServlet = " + currentChannel);
					session.setAttribute("currentChannel", currentChannel);
				}
			}
			
			response.sendRedirect(request.getContextPath() + "/chat/view");
			
		} else {
			session.setAttribute("msg", "로그인 후 이용해주세요.");
			response.sendRedirect(request.getContextPath() + "/chat/view");
		}		
	}
}
