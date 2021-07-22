package post.File.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;


import post.File.model.dao.FileDAO;
import post.File.model.vo.Files;

public class FileService {
	FileDAO fileDAO = new FileDAO();
	
	public Files getFile(String title) {
		Files file = null;
		Connection con = getConnection();
		file = fileDAO.getFile(con, title);
		close(con);
		return file;
	}
	
	public int insertFile(Files file) {
		int result = 0;
		Connection con = getConnection();
		result = fileDAO.insertFile(con, file);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		System.out.println(con);
		return result;
	}
	
	public int removeFile(String title) {
		int result = 0;
		Connection con = getConnection();
		result = fileDAO.removerFile(con, title);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}
	public int updateFile(String title, String org,int postId) {
		int result = 0;
		Connection con = getConnection();
		result = fileDAO.updateFile(con,title,org, postId);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}


}
