package workspace.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import workspace.model.vo.Workspace;

/**
 * Servlet implementation class WorkspaceChangeServlet
 */
@WebServlet("/workspace/wrksChange")
public class WorkspaceChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int wrksId = Integer.parseInt(request.getParameter("wrksId"));
		HttpSession session = request.getSession();		
		
		List<Workspace> workspaceList = (List<Workspace>)session.getAttribute("workspaceList");
		
		for(Workspace wrks : workspaceList) {
			if(wrks.getWorkspaceId() == wrksId) {
				session.setAttribute("currentWrkspace", wrks);
				break;
			}
		}
		
		session.removeAttribute("channelList");
		session.removeAttribute("currentChannel");
		
		response.sendRedirect(request.getContextPath() + "/main");
	}
}
