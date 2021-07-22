package channel.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import channel.model.service.ChannelService;
import channel.model.vo.Channel;

/**
 * Servlet implementation class ChannelCreateServlet
 */
@WebServlet("/channel/channelCreate")
public class ChannelCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChannelService channelService = new ChannelService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int refWorkspaceId = Integer.parseInt(request.getParameter("refWorkspaceId"));
		String channelName = request.getParameter("channelName");
		HttpSession session = request.getSession();
		
		Channel channel = new Channel(0, channelName, refWorkspaceId, "N");
		
		int result = channelService.insertChannel(channel);
		
		if(result > 0) {
			session.setAttribute("msg", "채널 생성에 성공하였습니다.");
			session.removeAttribute("channelList");
			session.removeAttribute("currentChannel");			
		} else {
			session.setAttribute("msg", "채널 생성에 실패하였습니다.");
		}
		
		response.sendRedirect(request.getContextPath() + "/main");		
	}

}
