package post.cmt.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import post.cmt.model.dao.CmtDAO;
import post.cmt.model.vo.Cmt;


public class CmtService {
	CmtDAO cmtDAO = new CmtDAO();
	
	public Cmt getCmt(int id) {
		Cmt Cmt = null;
		Connection con = getConnection();
		Cmt = cmtDAO.getcmt(con, id);
		close(con);
		return Cmt;
	}
	public Cmt getListCmtPost(int id) {
		Cmt Cmt = null;
		Connection con = getConnection();
		Cmt = cmtDAO.getListCmtPost(con, id);
		close(con);
		return Cmt;
	}
	public List<Cmt> getListCmt(int id){
		List<Cmt> list = null;
		Connection con = getConnection();
		list = cmtDAO.getListCmt(con,id);
		close(con);
		return list;
	}
	public List<Cmt> getListCmt(){
		List<Cmt> list = null;
		Connection con = getConnection();
		list = cmtDAO.getListCmt(con);
		close(con);
		return list;
	}
	
	public int insertCmt(Cmt cmt) {
		int result = 0;
		Connection con = getConnection();
		result = cmtDAO.insertCmt(con, cmt);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}
	
	public int deleteCmt(int id) {
		int result = 0;
		Connection con = getConnection();
		result = cmtDAO.deleteCmt(con, id);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}
	public int updateCmt(Cmt cmt) {
		int result = 0;
		Connection con = getConnection();
		result = cmtDAO.updateCmt(con,cmt);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}
	public String cmtWriter(int id) {
		String result = null;
		Connection con = getConnection();
		result = cmtDAO.cmtWriter(con,id);
		if(result!=null) commit(con);
		else rollback(con);
		close(con);
		return result;
	}
}
