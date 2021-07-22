package post.category.model.dao;

import static common.JDBCTemplate.close;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import post.category.model.vo.Category;


public class CategoryDAO {
	PreparedStatement pt = null;

	
	
	public List<Category> getCategoryList(Connection con) {
		List<Category> list = new ArrayList<>();
		String sql = "select * from category order by category_id desc";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();
			while(rs.next()) {
				Category cat = new Category();
				cat.setCategoryId(rs.getInt("category_id"));
				cat.setCategoryName(rs.getString("category_name"));
				list.add(cat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pt);
		}
		return list;
	}
	public Category getCategory(Connection con,int id) {
		Category category = null;
		ResultSet rs = null;
		String sql = "select c.category_id, c.category_name from category c, post p"+
				     " where c.category_id = p.ref_category_id and p.post_id=?";
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, id);
			rs = pt.executeQuery();
			
			if(rs.next()) {
				int catId = rs.getInt("category_id");
				String name = rs.getString("category_name");
				category = new Category(catId, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pt);
		}
		return category;
	}
	

	public Category getCategory(Connection con,String title) {
		Category category = null;
		ResultSet rs = null;
		String sql = "select * from category where category_name = ?";
		try {
			pt = con.prepareStatement(sql);
			pt.setString(1, title);
			rs = pt.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("category_id");
				String name = rs.getString("category_name");
				category = new Category(id, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pt);
		}
		return category;
	}
	
	public int addCategory(Connection con, String title) {
		int result =0;
		String sql = "insert into category values(SEQ_CAT_NO.NEXTVAL, ?)";
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
	
	public boolean confirmCategory(Connection con, String title) {
		boolean result = true;
		String sql = "select category_name from category where category_name = ?";
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setString(1, title);
			
			rs = pt.executeQuery();
			
			if(rs.next()) {
					result = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pt);
		}
		return result;
	}


	public int removeCategory(Connection con, int id) {
		int result = 0;
		String sql = "delete from category where category_id = ?";
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
