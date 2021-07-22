package todo.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import todo.model.exception.TodoException;
import todo.model.vo.Todo;

public class TodoDao {

	private Properties prop = new Properties();
	
	public TodoDao() {
		
		String fileName = "/sql/todo/todo-query.properties";
		String absPath = TodoDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(absPath));

		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}

	public List<Todo> todoListView(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("todoListView");
		List<Todo> list = null;
		
		try {
			
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Todo>();
			
			while(rset.next()) {
				Todo t = new Todo();

				t.setTodoId(rset.getInt("todo_id"));
				t.setWorkspaceIdRef(rset.getInt("ref_workspace_id"));
				t.setWorkspaceMemberNoRef(rset.getInt("ref_works_member_no"));
				t.setTodoTitle(rset.getString("todo_title"));
				t.setTodoContent(rset.getString("todo_content"));
				t.setTodoRegDate(rset.getDate("todo_reg_date"));
				t.setTodoStatus(rset.getString("todo_status"));
				t.setTodoStartDate(rset.getDate("todo_start_date"));
				t.setTodoEndDate(rset.getDate("todo_end_date"));
				list.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
		
	}

	public int insertTodoEvent(Connection conn, Todo todo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertTodoEvent"); 
		//insertTodoEvent = insert into todo values(seq_todo_no.nextval, ?, ?, ?, ?, default, ?, ?, ?)
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setObject(1, todo.getWorkspaceIdRef());
			pstmt.setObject(2, todo.getWorkspaceMemberNoRef());
			pstmt.setString(3, todo.getTodoTitle());
			pstmt.setString(4, todo.getTodoContent());
			pstmt.setString(5, todo.getTodoStatus());
			pstmt.setDate(6, todo.getTodoStartDate());
			pstmt.setDate(7, todo.getTodoEndDate());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt);
		}
		
		System.out.println("잘 들어갔니?" + " " + result);
		return result;
	}

	public int deleteTodo(Connection conn, int todoId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteTodo");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, todoId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Todo selectOne(Connection conn, int todoId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");
		//select * from todo where todo_id = ?
		Todo t = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, todoId);
			
			rset = pstmt.executeQuery();
			while(rset.next()){
				t = new Todo();
				
				t.setTodoId(rset.getInt("todo_id"));
				t.setWorkspaceIdRef(rset.getInt("ref_workspace_id"));
				t.setWorkspaceMemberNoRef(rset.getInt("ref_works_member_no"));
				t.setTodoTitle(rset.getString("todo_title"));
				t.setTodoContent(rset.getString("todo_content"));
				t.setTodoRegDate(rset.getDate("todo_reg_date"));
				t.setTodoStatus(rset.getString("todo_status"));
				t.setTodoStartDate(rset.getDate("todo_start_date"));
				t.setTodoEndDate(rset.getDate("todo_end_date"));
			}
			
		} catch (Exception e) {
			//런타임예외, 구체적인 의미를 가진 예외객체로 전환해서 다시 던지기
			throw new TodoException("게시물 조회 오류", e);
			
		}finally{
			close(rset);
			close(pstmt);
		}
		return t;
	}

	public int updateTodo(Connection conn, Todo todo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateTodo"); 
		//update todo set ref_workspace_id = ?, ref_works_member_no = ?, todo_title = ?, todo_content = ?, todo_status = ?, todo_start_date = ?, todo_end_date = ? where todo_id = ?
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setObject(1, todo.getWorkspaceIdRef());
			pstmt.setObject(2, todo.getWorkspaceMemberNoRef());
			pstmt.setString(3, todo.getTodoTitle());
			pstmt.setString(4, todo.getTodoContent());
			pstmt.setString(5, todo.getTodoStatus());
			pstmt.setDate(6, todo.getTodoStartDate());
			pstmt.setDate(7, todo.getTodoEndDate());
			pstmt.setInt(8, todo.getTodoId());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
