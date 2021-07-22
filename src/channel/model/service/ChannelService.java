package channel.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import channel.model.dao.ChannelDao;
import channel.model.vo.Channel;
import workspace.model.vo.WorkspaceMember;

public class ChannelService {
	
	ChannelDao channelDao = new ChannelDao();

	public int insertChannelByInviteLink(Channel channel, String workspaceInviteLink) {
		Connection conn = getConnection();
		int result = channelDao.insertChannelByInviteLink(conn, channel, workspaceInviteLink);
		
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}

	public List<Channel> selectChListByWrksId(int workspaceId) {
		//1. Connection 객체 생성
		Connection conn = getConnection();
		// 2. dao 요청
		List<Channel> list = channelDao.selectChListByWrksId(conn, workspaceId);
		// 3. 자원반납
		close(conn);
		return list;
	}

	public int insertChannel(Channel channel) {
		Connection conn = getConnection();
		int result = channelDao.insertChannel(conn, channel);
		
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}

}
