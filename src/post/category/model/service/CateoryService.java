package post.category.model.service;

import java.sql.Connection;
import java.util.List;

import post.category.model.dao.CategoryDAO;
import post.category.model.vo.Category;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;


public class CateoryService {
	CategoryDAO catDAO = new CategoryDAO();
	
	public boolean confirmCategory(String title) {
		boolean result = true;
		Connection con = getConnection();
		result = catDAO.confirmCategory(con, title);
		close(con);
		return result;
	}
	
	public Category getCategory(String title) {
		Category category = null;
		Connection con = getConnection();
		category = catDAO.getCategory(con, title);
		close(con);
		return category;
	}
	public Category getCategory(int id) {
		Category category = null;
		Connection con = getConnection();
		category = catDAO.getCategory(con, id);
		close(con);
		return category;
	}

	public List<Category> getCategoryList() {
		Connection con = getConnection();
		List<Category> list = catDAO.getCategoryList(con);
		close(con);
		return list;
	}
	
	public int insertCategory(String title) {
		int result = 0;
		Connection con = getConnection();
		result = catDAO.addCategory(con, title);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}
	
	public int removeCategory(int id) {
		int result = 0;
		Connection con = getConnection();
		result = catDAO.removeCategory(con, id);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}

}
