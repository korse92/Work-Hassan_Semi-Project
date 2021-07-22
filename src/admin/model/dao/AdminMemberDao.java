package admin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static common.JDBCTemplate.*;
import admin.model.vo.Member;
import java.util.HashMap;
/**
 * DAO Data Access Object
 * 
 * PreparedStatement 객체 생성(쿼리필요)
 * 쿼리실행 및 결고 Service단으로 리턴
 *
 */

public class AdminMemberDao {//이부분 클래스명 바꾸기
   private Connection conn = null; //DB연결 객체
   private PreparedStatement ps = null; //SQL수행 객체
   private ResultSet rs = null; //SQL조회 결과 객체
   
   //여기부터!
   public List<Member> allMember() {
      
      //DB연결 객체
      conn = getConnection();
      
      //SQL 작성
      String sql = "";
      sql += "SELECT * FROM MEMBER";
      List<Member> memberlist = new ArrayList<>();

      try {
         //SQL 수행객체
         ps = conn.prepareStatement(sql);
         //SQL 수행 및 결과 집합 저장
         rs = ps.executeQuery();
         
         //조회 결과 처리
         while(rs.next()) {
        	 Member member = new Member();
        	 member.setMemberId(rs.getString("MEMBER_ID"));
        	 member.setMemberName(rs.getString("MEMBER_NAME"));
        	 member.setBirthday(rs.getDate("BIRTHDAY"));
        	 member.setGender(rs.getString("GENDER"));
        	 member.setEmail(rs.getString("EMAIL"));
        	 member.setPhone(rs.getString("PHONE"));
        	 member.setEnrollDate(rs.getDate("ENROLL_DATE"));
        	 member.setMemberStatus(rs.getString("MEMBER_STATUS"));
        	 
        	 memberlist.add(member);
        	 
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         //DB객체 닫기
         close(rs);
         close(ps);
         close(conn);
      }
      
      
      return memberlist;
   }
   
   //이부분 부터 맞나?
   public void deleteMember(String id) {
	      System.out.println("dao진입@deleteMember");
	      //DB연결 객체
	      conn = getConnection();
	      
	      //SQL 작성
	      String sql = "";
	      sql += "DELETE FROM MEMBER WHERE MEMBER_ID='" + id + "'";

	      try {
	         //SQL 수행객체
	         ps = conn.prepareStatement(sql);
	         //SQL 수행 및 결과 집합 저장
	         ps.executeQuery();
	         
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         //DB객체 닫기
	         close(rs);
	         close(ps);
	         close(conn);
	      }
	      
	   }
   
}