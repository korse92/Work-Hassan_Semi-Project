package post.post.model.dao;

import static common.JDBCTemplate.close;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import post.post.model.vo.Post;

public class PostDAO {
	PreparedStatement pt = null;
	Statement st = null;
	
	public List<Post> getListPost(Connection con) {
		List<Post> list = new ArrayList<Post>();
		String sql = "select * from post order by post_reg_date desc";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();	
			while(rs.next()) {
				int postId = rs.getInt("post_id");
				int refChannelId = rs.getInt("ref_channel_id");
				int refWorksMemberNo = rs.getInt("ref_works_member_no");
				int refCategoryId = rs.getInt("ref_category_id");
				String postTitle = rs.getString("post_title");
				String postContent = rs.getString("post_content");
				Date postRegDate = rs.getDate("post_reg_date");
				int postHit = rs.getInt("post_hit");
				String refFileRenameedName = rs.getString("ref_file_renamed_name");
				Post board = new Post(
								postId, 
								refChannelId, 
								refWorksMemberNo, 
								refCategoryId,
								postTitle,
								postContent,
								postRegDate,
								postHit,
								refFileRenameedName
								);
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(rs);
			close(pt);
		}
		return list;
	}
	public List<Post> getListPost(Connection con, int id) {
		List<Post> list = new ArrayList<Post>();
		String sql = "select * from post where post_id = ?";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, id);
			rs = pt.executeQuery();	
			while(rs.next()) {
				int postId = rs.getInt("post_id");
				int refChannelId = rs.getInt("ref_channel_id");
				int refWorksMemberNo = rs.getInt("ref_works_member_no");
				int refCategoryId = rs.getInt("ref_category_id");
				String postTitle = rs.getString("post_title");
				String postContent = rs.getString("post_content");
				Date postRegDate = rs.getDate("post_reg_date");
				int postHit = rs.getInt("post_hit");
				String refFileRenameedName = rs.getString("ref_file_renamed_name");
				Post board = new Post(
								postId, 
								refChannelId, 
								refWorksMemberNo, 
								refCategoryId,
								postTitle,
								postContent,
								postRegDate,
								postHit,
								refFileRenameedName
								);
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(rs);
			close(pt);
		}
		return list;
	}
	public List<Post> search(Connection con, List<Integer> hashtagList) {
		List<Post> list = new ArrayList<Post>();
		String sql = "select * from post where post_id = ?";
		ResultSet rs = null;
		int idx = 0;
		try {
			pt = con.prepareStatement(sql);
			for(int i =0; i < hashtagList.size(); i++) {
				idx = hashtagList.get(i);
				pt.setInt(1, idx);
			rs = pt.executeQuery();	
			while(rs.next()) {
				int postId = rs.getInt("post_id");
				int refChannelId = rs.getInt("ref_channel_id");
				int refWorksMemberNo = rs.getInt("ref_works_member_no");
				int refCategoryId = rs.getInt("ref_category_id");
				String postTitle = rs.getString("post_title");
				String postContent = rs.getString("post_content");
				Date postRegDate = rs.getDate("post_reg_date");
				int postHit = rs.getInt("post_hit");
				String refFileRenameedName = rs.getString("ref_file_renamed_name");
				Post board = new Post(
								postId, 
								refChannelId, 
								refWorksMemberNo, 
								refCategoryId,
								postTitle,
								postContent,
								postRegDate,
								postHit,
								refFileRenameedName
								);
				list.add(board);
				}
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(rs);
			close(pt);
		}
		return list;
	}
	
	public Post getPost(Connection con, int id) {
		Post post = null;
		String sql = "select * from post where post_id = ?";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, id);
			rs = pt.executeQuery();
			if(rs.next()) {
				int chId = rs.getInt("ref_channel_id");
				int memberNo = rs.getInt("ref_works_member_no");
				int catId = rs.getInt("ref_category_id");
				String title = rs.getString("post_title");
				String content = rs.getString("post_content");
				Date regdate = rs.getDate("post_reg_date");
				int hit = rs.getInt("post_hit");
				String file = rs.getString("ref_file_renamed_name");
				
				post = new Post(id, chId, memberNo, catId, title, content, regdate,hit,file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pt);
		}
		return post;
	}
	public int getPost(Connection con, String file) {
		int postId = 0;
		String sql = "select * from post where ref_file_renamed_name = ?";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setString(1,file);
			rs = pt.executeQuery();
			if(rs.next()) {
				postId = rs.getInt("post_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pt);
		}
		return postId;
	}
	
	
	public int insertPost(Connection con, Post post) {
		int result = 0;
		String sql = "insert into post values(SEQ_POST_NO.NEXTVAL,?,?,?,?,?,SYSDATE,0,?)";

			try {
				pt = con.prepareStatement(sql);
				pt.setInt(1, post.getRefChannelId());
				pt.setInt(2, post.getRefWorksMemberNo());
				pt.setObject(3, post.getRefCategoryId() != 0 ? post.getRefCategoryId() : null);
				pt.setString(4, post.getPostTitle());
				pt.setString(5, post.getPostContent());
				pt.setObject(6, post.getRefFileRenameedName() != null ? post.getRefFileRenameedName() : null);
				result = pt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pt);
			}
		return result;
	}
	
	public int updatePost(Connection con, Post post) {
		int result = 0;
		String sql = "update post set ref_category_id = ?, post_title = ?, post_content =?, ref_file_renamed_name = ? where post_id =?";
		try {
			pt = con.prepareStatement(sql);
			pt.setObject(1, post.getRefCategoryId() == 0 ? null : post.getRefCategoryId());
			pt.setString(2, post.getPostTitle());
			pt.setString(3, post.getPostContent());
			pt.setObject(4, post.getRefFileRenameedName() != null ? post.getRefFileRenameedName() : null);
			pt.setInt(5, post.getPostId());
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pt);
		}
		return result;
	}
	public int deletePost(Connection con, int id) {
		int result = 0;
		String sql = "delete post where post_id = ?";
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
	public int Count(Connection con, int id) {
		int result = 0;
		String sql = "update post set post_hit = post_hit+1 where post_id = ?";

			try {
				pt = con.prepareStatement(sql);
				pt.setInt(1, id);
				result = pt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}  finally {
				close(pt);
			}
		return result;
	}
	public String getWriter(Connection con, int id) {
		String result = null;
		String sql = "select w.works_member_nickname from wrks_member w, post p where w.works_member_no = "
				 	+ " (select ref_works_member_no from post where post_id = ?)";
		ResultSet rs = null;
			try {
				pt = con.prepareStatement(sql);
				pt.setInt(1, id);
				rs = pt.executeQuery();
				if(rs.next()) {
					result = rs.getString("works_member_nickname");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}  finally {
				close(pt);
			}
		return result;
	}

}	
