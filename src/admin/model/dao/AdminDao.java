package admin.model.dao;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.model.vo.Admin;
/**
 * DAO Data Access Object
 * 
 * PreparedStatement 객체 생성(쿼리필요)
 * 쿼리실행 및 결과 Service단으로 리턴
 *
 */

public class AdminDao {
//   private Connection conn = null; //DB연결 객체
//   private PreparedStatement ps = null; //SQL수행 객체
//   private ResultSet rs = null; //SQL조회 결과 객체

	public Admin selectOne(Connection conn, String adminid) {		
		PreparedStatement ps = null; // SQL수행 객체
		ResultSet rs = null;
		
		System.out.println("adminid@DAO = "+adminid);

		// SQL 작성
		String sql = "select * from admin where admin_id = ?";
		Admin admin = null;

		try {
			// SQL 수행객체
			ps = conn.prepareStatement(sql);
			ps.setString(1, adminid);
			// SQL 수행 및 결과 집합 저장
			rs = ps.executeQuery();
					
			// 조회 결과 처리
			if(rs.next()) {
				// 결과값 한 행 처리
				admin = new Admin();
				System.out.println(rs.getString("admin_id"));
				admin.setAdminId(rs.getString("admin_id"));
				admin.setAdminPassword(rs.getString("admin_password"));
				admin.setAdminEmail(rs.getString("admin_email"));
				admin.setAdminRole(rs.getString("admin_role"));				
			}
			
			System.out.println("admin@DAO = " + admin);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			close(rs);
			close(ps);
		}

		return admin;
	}
   
   public int insertOne(Connection conn, Admin admin) {

		PreparedStatement ps = null; // SQL수행 객체		
		int result = 0;
		// SQL 작성
		String sql = "";
		sql += "INSERT INTO ADMIN VALUES(";
		sql += "'" + admin.getAdminId() + "', ";
		sql += "'" + admin.getAdminPassword() + "', ";
		sql += "'" + admin.getAdminEmail() + "', ";
		sql += "'A')";

		System.out.println(sql);

		try {
			// SQL 수행객체
			ps = conn.prepareStatement(sql);
			// SQL 수행 및 결과 집합 저장
			result = ps.executeUpdate();
			// 결과값 한 행 처리

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			close(ps);
		}
		
		return result;
	}
}