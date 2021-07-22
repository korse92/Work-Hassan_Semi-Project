package channel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import channel.model.vo.Channel;
import workspace.model.vo.Workspace;

/**
 * Servlet implementation class ChannelChangeServlet
 */
@WebServlet("/channel/channelChange")
public class ChannelChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int channelId = Integer.parseInt(request.getParameter("channelId"));
		HttpSession session = request.getSession();		
		
		List<Channel> channelList = (List<Channel>)session.getAttribute("channelList");
		
		for(Channel ch : channelList) {
			if(ch.getChannelId() == channelId) {
				session.setAttribute("currentChannel", ch);
				break;
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/chat/view");
	}

}
