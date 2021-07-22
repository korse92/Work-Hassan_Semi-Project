package admin.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import admin.model.dao.AdminDao;
import admin.model.vo.Admin;

/**
 * 업무로직-서비스에서 주체적으로 처리 Connection 객체 생성 Dao에 업무로직 하달 트랜잭션 처리 자원반납
 * 
 */
public class AdminService {

	private AdminDao adminDao = new AdminDao();

	public Admin selectOne(String adminid) {
		Connection conn = getConnection();
		
		System.out.println("adminid@AdminService = " + adminid);
		
		Admin admin = adminDao.selectOne(conn, adminid);
		
		close(conn);

		return admin;
	}

	public int insertOne(Admin admin) {
		Connection conn = getConnection();		
		int result = adminDao.insertOne(conn, admin);
		
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		
		return result;
	}
}