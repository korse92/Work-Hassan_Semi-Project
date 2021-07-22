package workspace.model.dao;

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

import member.model.dao.MemberDao;
import member.model.vo.Member;
import workspace.model.vo.Workspace;
import workspace.model.vo.WorkspaceMember;

public class WorkspaceDao {
	
	private Properties prop = new Properties();
	
	public WorkspaceDao() {
		String fileName = "/sql/workspace/workspace-query.properties";
		String path = MemberDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertWorkspace(Connection conn, Workspace workspace) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertWorkspace");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, workspace.getWorkspaceName());
			pstmt.setString(2, workspace.getWorkspaceInviteLink());
			pstmt.setString(3, workspace.getWorkspaceIcon());			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public String selectMemberEmail(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberEmail");
		String email = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				email = rset.getString(1);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return email;
	}

	public int insertWrksMember(Connection conn, WorkspaceMember wrksMember, String workspaceInviteLink) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertWrksMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, wrksMember.getRefMemberId());
			pstmt.setString(2, workspaceInviteLink);
			pstmt.setString(3, wrksMember.getWorksMemberNickname());			
			pstmt.setString(4, wrksMember.getWorksMemberRole());		
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Workspace> selectWrksListByMemberId(Connection conn, String memberId) {		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectWrksListByMemberId");
		
		List<Workspace> list = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				Workspace wrks = new Workspace();
				wrks.setWorkspaceId(rset.getInt("workspace_id"));
				wrks.setWorkspaceName(rset.getString("workspace_name"));
				wrks.setWorkspaceInviteLink(rset.getString("workspace_invite_link"));
				wrks.setWorkspaceRegDate(rset.getDate("workspace_reg_date"));
				wrks.setWorkspaceIcon(rset.getString("workspace_icon"));
				
				list.add(wrks);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<WorkspaceMember> selectWrksMemListByWrksId(Connection conn, int workspaceId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectWrksMemListByWrksId");
		
		List<WorkspaceMember> list = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, workspaceId);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				WorkspaceMember wrksMember = new WorkspaceMember();
				wrksMember.setWorksMemberNo(rset.getInt("works_member_no"));
				wrksMember.setRefMemberId(rset.getString("ref_member_id"));
				wrksMember.setWorksMemberNickname(rset.getString("works_member_nickname"));
				wrksMember.setWorksMemberRole(rset.getString("works_member_role"));
				wrksMember.setWorksMemberRegDate(rset.getDate("works_member_reg_date"));
				wrksMember.setRenamedProfileImg(rset.getString("renamed_profile_img"));
				
				list.add(wrksMember);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public String selectRefMemIdByInviteLink(Connection conn, String memberId, String workspaceInviteLink) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectRefMemIdByInviteLink");
		
		String refMemberId = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, workspaceInviteLink);
			pstmt.setString(2, memberId);
			rset = pstmt.executeQuery();			
			
			if(rset.next())
				refMemberId = rset.getString("ref_member_id");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return refMemberId;
	}

}
