package member.model.service;

import java.sql.Connection;
import java.util.List;

import member.model.dao.MemberDao;
import member.model.vo.Member;
import static common.JDBCTemplate.*;

public class MemberService {
	
	public static final String ADMIN_MEMBER_ROLE = "A"; //관리자 롤
	public static final String USER_MEMBER_ROLE = "U"; //관리자 롤
	
	private MemberDao memberDao = new MemberDao();

	public Member selectId(String memberId) {
		//1. Connection 객체 생성
		Connection conn = getConnection();
		//2. dao 요청
		Member member = memberDao.selectId(conn, memberId);
		//3. 자원반납
		close(conn);
		return member;
	}

	public int memberInsert(Member member) {
		Connection conn = getConnection();
		int result = memberDao.memberInsert(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int memberUpdate(Member member) {
		Connection conn = getConnection();
		int result = memberDao.memberUpdate(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int memberDelete(String memberId) {
		Connection conn = getConnection();
		int result = memberDao.memberDelete(conn, memberId);
		
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int updatePassword(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updatePassword(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public Member findId(String memberName, String phone) {
		Connection conn = getConnection();
		Member member = memberDao.findId(conn, memberName, phone);
		close(conn);
		return member;
	}

	public List<Member> selectMembers() {
		Connection conn = getConnection();
		List<Member> list = memberDao.selectMembers(conn);
		close(conn);
		return list;
	}
	

}
