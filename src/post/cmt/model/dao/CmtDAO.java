package post.cmt.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import post.cmt.model.vo.Cmt;

public class CmtDAO {
	
	
	PreparedStatement pt = null;
	Statement st = null;
	public List<Cmt> getListCmt(Connection con) {
		List<Cmt> list = new ArrayList<Cmt>();
		String sql = "select * from cmt";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();	
			while(rs.next()) {
				int cmt_id = rs.getInt("cmt_id");
				String cmt_content = rs.getString("cmt_content");
				Date cmt_reg_date = rs.getDate("cmt_reg_date");
				int cmt_ref_post_id = rs.getInt("cmt_ref_post_id");
				int ref_works_member_no = rs.getInt("ref_works_member_no");;
				
				Cmt cmt = new Cmt(cmt_id, cmt_content, cmt_reg_date, cmt_ref_post_id, ref_works_member_no);
				list.add(cmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(rs);
			close(pt);
		}
		return list;
	}
	
	public List<Cmt> getListCmt(Connection con, int id) {
		List<Cmt> list = new ArrayList<Cmt>();
		String sql = "select * from cmt where cmt_ref_post_id = ?";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, id);
			rs = pt.executeQuery();	
			while(rs.next()) {
				int cmt_id = rs.getInt("cmt_id");
				String cmt_content = rs.getString("cmt_content");
				Date cmt_reg_date = rs.getDate("cmt_reg_date");
				int cmt_ref_post_id = rs.getInt("cmt_ref_post_id");
				int ref_works_member_no = rs.getInt("ref_works_member_no");;
				
				Cmt cmt = new Cmt(cmt_id, cmt_content, cmt_reg_date, cmt_ref_post_id, ref_works_member_no);
				list.add(cmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(rs);
			close(pt);
		}
		return list;
	}
	
	public Cmt getcmt(Connection con, int id) {
		Cmt cmt = null;
		String sql = "select * from cmt where cmt_id = ?";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, id);
			rs = pt.executeQuery();
			if(rs.next()) {
				int cmt_id = rs.getInt("cmt_id");
				String cmt_content = rs.getString("cmt_content");
				Date cmt_reg_date = rs.getDate("cmt_reg_date");
				int cmt_ref_post_id = rs.getInt("cmt_ref_post_id");
				int ref_works_member_no = rs.getInt("ref_works_member_no");;
				
				cmt = new Cmt(cmt_id, cmt_content, cmt_reg_date, cmt_ref_post_id, ref_works_member_no);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pt);
		}
		return cmt;
	}
	public Cmt getListCmtPost(Connection con, int id) {
		Cmt cmt = null;
		String sql = "select * from cmt where cmt_ref_post_id = ?";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, id);
			rs = pt.executeQuery();
			if(rs.next()) {
				int cmt_id = rs.getInt("cmt_id");
				String cmt_content = rs.getString("cmt_content");
				Date cmt_reg_date = rs.getDate("cmt_reg_date");
				int cmt_ref_post_id = rs.getInt("cmt_ref_post_id");
				int ref_works_member_no = rs.getInt("ref_works_member_no");;
				
				cmt = new Cmt(cmt_id, cmt_content, cmt_reg_date, cmt_ref_post_id, ref_works_member_no);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pt);
		}
		return cmt;
	}

	public int insertCmt(Connection con, Cmt cmt) {
		int result = 0;
		String sql = "insert into cmt values(SEQ_CMT_NO.NEXTVAL,?,SYSDATE,?,?)";

			try {
				pt = con.prepareStatement(sql);
				pt.setString(1, cmt.getCmt_content());
				pt.setInt(2, cmt.getCmt_ref_post_id());
				pt.setInt(3, cmt.getRef_works_member_no());
				result = pt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pt);
			}
		return result;
	}
	
	public int updateCmt(Connection con, Cmt cmt) {
		int result = 0;
		String sql = "update cmt set cmt_content=? where cmt_id = ?";
		try {
			pt = con.prepareStatement(sql);
			pt.setObject(1, cmt.getCmt_content());
			pt.setObject(2, cmt.getCmt_id());
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pt);
		}
		return result;
	}
	public int deleteCmt(Connection con, int id) {
		int result = 0;
		String sql = "delete cmt where cmt_id = ?";
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, id);
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pt);
		}
		return result;
	}
	public String cmtWriter(Connection con, int id) {
		String result = null;
		String sql = "select w.works_member_nickname from wrks_member w, cmt c where w.works_member_no =" 
					+" (select ref_works_member_no from cmt where cmt_id = ?);";
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, id);
			ResultSet rs = null;
			rs = pt.executeQuery();
			while(rs.next()) {
				rs.getString("works_member_nickname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pt);
		}
		return result;
	}
}
