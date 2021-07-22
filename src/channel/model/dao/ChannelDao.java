package channel.model.dao;

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
import member.model.dao.MemberDao;

public class ChannelDao {
	
	private Properties prop = new Properties();
	
	public ChannelDao() {
		String fileName = "/sql/channel/channel-query.properties";
		String path = MemberDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertChannelByInviteLink(Connection conn, Channel channel, String workspaceInviteLink) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertChannelByInviteLink");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, channel.getChannelName());
			pstmt.setString(2, workspaceInviteLink);
			pstmt.setString(3, channel.getChannelPriOrNot());			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Channel> selectChListByWrksId(Connection conn, int workspaceId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectChListByWrksId");
		
		List<Channel> list = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, workspaceId);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				Channel ch = new Channel();
				ch.setChannelId(rset.getInt("channel_id"));
				ch.setChannelName(rset.getString("channel_name"));
				ch.setRefWorkspaceId(rset.getInt("ref_workspace_id"));
				ch.setChannelPriOrNot(rset.getString("channel_pri_or_not"));
				
				list.add(ch);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertChannel(Connection conn, Channel channel) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertChannel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, channel.getChannelName());
			pstmt.setInt(2, channel.getRefWorkspaceId());
			pstmt.setString(3, channel.getChannelPriOrNot());		
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
