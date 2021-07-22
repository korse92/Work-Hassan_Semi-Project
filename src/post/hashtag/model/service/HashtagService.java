package post.hashtag.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import post.hashtag.model.dao.HashtagDAO;
import post.hashtag.model.vo.Hashtag;

public class HashtagService {
	HashtagDAO hashtagDAO = new HashtagDAO();
	
	public int insertTag(Hashtag tag) {
		int result = 0;
		Connection con = getConnection();
		result = hashtagDAO.insertTag(con, tag);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}
	
	public List<Hashtag> getListPost(String search) {
		List<Hashtag> list = new ArrayList<Hashtag>();
		Connection con = getConnection();
		list = hashtagDAO.getTag(con,search);
		close(con);
		return list;
	}
	public List<Hashtag> getListPost() {
		List<Hashtag> list = new ArrayList<Hashtag>();
		Connection con = getConnection();
		list = hashtagDAO.getTag(con);
		close(con);
		return list;
	}
	public List<Hashtag> getListPost(int id) {
		List<Hashtag> list = new ArrayList<Hashtag>();
		Connection con = getConnection();
		list = hashtagDAO.getTag(con,id);
		close(con);
		return list;
	}	
	public Hashtag getName(int id) {
		Hashtag name;
		Connection con = getConnection();
		name = hashtagDAO.getName(con,id);
		close(con);
		return name;
	}
	public int updateTag(String tag, int id) {
		int result = 0;
		Connection con = getConnection();
		result = hashtagDAO.updateTag(con, tag,id);
		if(result>0) commit(con);
		else rollback(con);
		close(con);
		return result;
	}
}
