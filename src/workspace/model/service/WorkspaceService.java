package workspace.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import workspace.model.dao.WorkspaceDao;
import workspace.model.vo.Workspace;
import workspace.model.vo.WorkspaceMember;

public class WorkspaceService {
	private WorkspaceDao workspaceDao = new WorkspaceDao();
	
	public int insertWorkspace(Workspace workspace) {
		Connection conn = getConnection();
		int result = workspaceDao.insertWorkspace(conn, workspace);
		
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public String selectMemberEmail(String memberId) {
		//1. Connection 객체 생성
		Connection conn = getConnection();
		//2. dao 요청
		String email = workspaceDao.selectMemberEmail(conn, memberId);
		//3. 자원반납
		close(conn);
		return email;
	}

	public int insertWrksMember(WorkspaceMember wrksMember, String workspaceInviteLink) {
		Connection conn = getConnection();
		int result = workspaceDao.insertWrksMember(conn, wrksMember, workspaceInviteLink);
		
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public List<Workspace> selectWrksListByMemberId(String memberId) {
		//1. Connection 객체 생성
		Connection conn = getConnection();
		//2. dao 요청
		List<Workspace> list = workspaceDao.selectWrksListByMemberId(conn, memberId);
		//3. 자원반납
		close(conn);
		return list;
	}

	public List<WorkspaceMember> selectWrksMemListByWrksId(int workspaceId) {
		//1. Connection 객체 생성
		Connection conn = getConnection();
		// 2. dao 요청
		List<WorkspaceMember> list = workspaceDao.selectWrksMemListByWrksId(conn, workspaceId);
		// 3. 자원반납
		close(conn);
		return list;
	}

	public String selectRefMemIdByInviteLink(String MemberId, String workspaceInviteLink) {
		Connection conn = getConnection();		
		String refMemberId = workspaceDao.selectRefMemIdByInviteLink(conn, MemberId, workspaceInviteLink);
		close(conn);
		return refMemberId;
	}

}
