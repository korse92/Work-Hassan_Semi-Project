package post.post.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import post.post.model.dao.PostDAO;
import post.post.model.vo.Post;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;


public class PostService {
	PostDAO boardDAO = new PostDAO();
	
		public List<Post> getListPost() {
			List<Post> list = new ArrayList<Post>();
			Connection con = getConnection();
			list = boardDAO.getListPost(con);
			close(con);
			return list;
		}
		public List<Post> getListPost(List<Integer> hashtagList) {
			List<Post> list = new ArrayList<Post>();
			Connection con = getConnection();
			list = boardDAO.search(con, hashtagList);
			close(con);
			return list;
		}

		public Post getPost(int id) {
			Post post = null;
			Connection con = getConnection();
			post = boardDAO.getPost(con, id);
			close(con);
			return post;
		}
		public int getPost(String file) {
			int postId = 0;
			Connection con = getConnection();
			postId = boardDAO.getPost(con, file);
			close(con);
			return postId;
		}
		
		public int insertPost(Post post) {
			int result = 0;
			Connection con = getConnection();
			result = boardDAO.insertPost(con, post);
			if(result>0) commit(con);
			else rollback(con);
			close(con);
			return result;
		}
		public int updatePost(Post post) {
			Connection con = getConnection();
			int result = boardDAO.updatePost(con, post);
			if(result>0) commit(con);
			else rollback(con);
			close(con);
			return result;
		}

		public int deletePost(int id) {
			int result = 0;
			Connection con = getConnection();
			result = boardDAO.deletePost(con, id);
			if(result>0) commit(con);
			else rollback(con);
			close(con);
			return result;
		}

		public int Count(int id) {
			int result = 0;
			Connection con = getConnection();
			result = boardDAO.Count(con, id);
			if(result>0) commit(con);
			else rollback(con);
			close(con);
			return result;
		}
		public String getWriter(int id) {
			String result = null;
			Connection con = getConnection();
			result = boardDAO.getWriter(con, id);
			if(result != null) commit(con);
			else rollback(con);
			close(con);
			return result;
		}

}
