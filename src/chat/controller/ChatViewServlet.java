package chat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import channel.model.vo.Channel;
import chat.model.service.ChatService;
import chat.model.vo.Chat;

/**
 * Servlet implementation class ChatViewServlet
 */
@WebServlet("/chat/view")
public class ChatViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChatService chatService = new ChatService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Channel currentChannel = (Channel)session.getAttribute("currentChannel");
		
		List<Chat> chatListByChannel = null;
		
		if(currentChannel == null) {
			chatListByChannel = new ArrayList<Chat>();
		} else {
			chatListByChannel = chatService.selectChatListByChannel(currentChannel.getChannelId());
		}
		
		session.setAttribute("chatListByChannel", chatListByChannel);
		//System.out.println("chatListByChannel@ChatViewServlet = " + chatListByChannel);
		
		request.getRequestDispatcher("/WEB-INF/views/chat/chat.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
