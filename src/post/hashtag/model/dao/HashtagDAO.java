package post.hashtag.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import post.hashtag.model.vo.Hashtag;
import post.post.model.vo.Post;

public class HashtagDAO {
	PreparedStatement pt = null;
	Statement st = null;
	
	
	public List<Hashtag> getTag(Connection con) {
		List<Hashtag> list = new ArrayList<Hashtag>();
		String sql = "select * from hashtag";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();	
			while(rs.next()) {
				String hashtagName = rs.getString("hashtag_name");
				int hashtagRefPostId = rs.getInt("hashtag_ref_post_id");
				int hashtagRefChatId = rs.getInt("hashtag_ref_chat_id");
				String hashtagPoChseparator = rs.getString("hashtag_po_ch_separator");
				
				Hashtag tag = new Hashtag(hashtagName, hashtagRefPostId, hashtagRefChatId, hashtagPoChseparator);
				list.add(tag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(rs);
			close(pt);
		}
		return list;
	}
	
	public List<Hashtag> getTag(Connection con, String search) {
		List<Hashtag> list = new ArrayList<Hashtag>();
		String sql = "select * from hashtag where hashtag_name like '%"+ search +"%'";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();	
			while(rs.next()) {
				String hashtagName = rs.getString("hashtag_name");
				int hashtagRefPostId = rs.getInt("hashtag_ref_post_id");
				int hashtagRefChatId = rs.getInt("hashtag_ref_chat_id");
				String hashtagPoChseparator = rs.getString("hashtag_po_ch_separator");
				
				Hashtag tag = new Hashtag(hashtagName, hashtagRefPostId, hashtagRefChatId, hashtagPoChseparator);
				list.add(tag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(rs);
			close(pt);
		}
		return list;
	}
	
	public List<Hashtag> getTag(Connection con, int id) {
		List<Hashtag> list = new ArrayList<Hashtag>();
		String sql = "select * from hashtag where hashtag_ref_post_id = ?";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, id);
			rs = pt.executeQuery();	
			while(rs.next()) {
				String hashtagName = rs.getString("hashtag_name");
				int hashtagRefPostId = rs.getInt("hashtag_ref_post_id");
				int hashtagRefChatId = rs.getInt("hashtag_ref_chat_id");
				String hashtagPoChseparator = rs.getString("hashtag_po_ch_separator");
				Hashtag tag = new Hashtag(hashtagName, hashtagRefPostId, hashtagRefChatId, hashtagPoChseparator);
				list.add(tag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(rs);
			close(pt);
		}
		return list;
	}
	
	
	public Hashtag getName(Connection con, int id) {
		Hashtag result = new Hashtag();
		String sql = "select * from hashtag where hashtag_ref_post_id = ?";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, id);
			rs = pt.executeQuery();	
			while(rs.next()) {
				String hashtagName = rs.getString("hashtag_name");
				int hashtagRefPostId = rs.getInt("hashtag_ref_post_id");
				int hashtagRefChatId = rs.getInt("hashtag_ref_chat_id");
				String hashtagPoChseparator = rs.getString("hashtag_po_ch_separator");
				result = new Hashtag(hashtagName, hashtagRefPostId, hashtagRefChatId, hashtagPoChseparator);
				System.out.println("getName ?? +" + result);
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(rs);
			close(pt);
		}
		return result;
	}
	public int insertTag(Connection con, Hashtag tag) {
		int result = 0;
		String sql = "insert into hashtag values(?,?,?,?)";
			try {
				pt = con.prepareStatement(sql);
				pt.setString(1, tag.getHashtagName());
				pt.setObject(2, tag.getHashtagRefPostId() != 0 ? tag.getHashtagRefPostId() : null);
				pt.setObject(3, tag.getHashtagRefChatId() != 0 ? tag.getHashtagRefChatId() : null);
				pt.setString(4, tag.getHashtagPoChseparator() == "P" ? "P" : "C");
				result = pt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pt);
			}
		return result;
	}
	public int updateTag(Connection con,String name, int id) {
		int result = 0;
		String sql = "update hashtag set hashtag_name =? where hashtag_ref_chat_id= ?";
		try {
			pt = con.prepareStatement(sql);
			pt.setString(1, name);
			pt.setInt(2, id);
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pt);
		}
		return result;
	}
	
	
	public int deleteTagPost(Connection con, int id) {
		int result = 0;
		String sql = "delete hashtag where hashtag_ref_post_id = ?";
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
	
	public int deleteTagChat(Connection con, int id) {
		int result = 0;
		String sql = "delete post where hashtag_ref_chat_id = ?";
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

}
