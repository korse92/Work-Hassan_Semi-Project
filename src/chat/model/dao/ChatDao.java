package chat.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import channel.model.vo.Channel;
import chat.model.vo.Chat;
import member.model.dao.MemberDao;

public class ChatDao {
	
private Properties prop = new Properties();
	
	public ChatDao() {
		String fileName = "/sql/chat/chat-query.properties";
		String path = MemberDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertChat(Connection conn, Chat chat) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertChat");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, chat.getRefChannelId());
			pstmt.setInt(2, chat.getRefWorksMemberNo());
			pstmt.setString(3, chat.getChatContent());		
			pstmt.setString(4, chat.getRefFileRenamedName());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Chat> selectChatListByChannel(Connection conn, int channelId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectChatListByChannel");
		
		List<Chat> list = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, channelId);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				Chat chat = new Chat();
				chat.setChatId(rset.getInt("chat_id"));
				chat.setRefChannelId(rset.getInt("ref_channel_id"));
				chat.setRefWorksMemberNo(rset.getInt("ref_works_member_no"));
				chat.setChatContent(rset.getString("chat_content"));
				chat.setChatRegDate(rset.getDate("chat_reg_date"));
				chat.setRefFileRenamedName(rset.getString("ref_file_renamed_name"));			
				
				list.add(chat);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

}
