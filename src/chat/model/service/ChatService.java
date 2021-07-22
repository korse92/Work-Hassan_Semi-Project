package chat.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import chat.model.dao.ChatDao;
import chat.model.vo.Chat;

public class ChatService {
	
	private ChatDao chatDao = new ChatDao();

	public int insertChat(Chat chat) {
		Connection conn = getConnection();
		int result = chatDao.insertChat(conn, chat);
		
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}

	public List<Chat> selectChatListByChannel(int channelId) {
		Connection conn = getConnection();
		List<Chat> list = chatDao.selectChatListByChannel(conn, channelId);
		close(conn);
		return list;
	}

}
