package post.File.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import post.File.model.vo.Files;

public class FileDAO {
	PreparedStatement pt = null;
	Statement st = null;

	
	public Files getFile(Connection con, String title) {
		Files file = null;
		ResultSet rs = null;
		String sql = "select * from files where file_renamed_name = ?";
		try {
			pt = con.prepareStatement(sql);
			pt.setString(1, title);
			rs = pt.executeQuery();
			if(rs.next()) {
				String re = rs.getString("file_renamed_name");
				String org = rs.getString("file_original_name");
				String separator = rs.getString("file_po_ch_separator");
				int postId = rs.getInt("file_ref_post_id");
				int chatId = rs.getInt("file_ref_chat_id");
				file = new Files(re,org,separator,postId,chatId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pt);
		}
		return file;
	}


	public int insertFile(Connection con, Files file) {
		int result = 0;
		String sql = "insert into files values(?,?,?,?,?)";
		try {
			pt = con.prepareStatement(sql);
			pt.setString(1, file.getFileRenamedName());
			System.out.println();
			pt.setString(2, file.getFileOriginalName());
			pt.setString(3, file.getFilePoChSeparator());
			pt.setObject(4, (file.getFileRefPostId() != 0) ? file.getFileRefPostId() : null );
			pt.setObject(5, (file.getFileRefChatId() != 0) ? file.getFileRefChatId() : null );
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pt);
		}
		return result;
	}

	public int removerFile(Connection con, String title) {
		int result =0;
		String sql = "delete from files where file_renamed_name = ?";
				try {
					pt = con.prepareStatement(sql);
					pt.setString(1, title);
					result = pt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(pt);
				}
		return result;
	}

	public int updateFile(Connection con, String title, String org, int postId) {
		int result = 0;
		String sql = "update files set file_renamed_name=?,file_original_name=? where  file_ref_post_id= ?";
				try {
					pt = con.prepareStatement(sql);
					pt.setString(1, title);
					pt.setString(2, org);
					pt.setInt(3, postId);
					result = pt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(pt);
				}
		return result;
	}

}
