package todo.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import todo.model.dao.TodoDao;
import todo.model.vo.Todo;


public class TodoService {

	private TodoDao todoDao = new TodoDao();

	public List<Todo> todoListView() {
		Connection conn = getConnection();
		List<Todo> list = todoDao.todoListView(conn);
		System.out.println("서비스단 체크" + list);
		close(conn);
		return list;
	}

	public int insertTodoEvent(Todo todo) {
		Connection conn = getConnection();
		int result = todoDao.insertTodoEvent(conn, todo);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}

	public int deleteTodo(int todoId) {
		Connection conn = getConnection();
		int result = todoDao.deleteTodo(conn, todoId);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public Todo selectOne(int todoId) {
		Connection conn = getConnection();
		Todo todo = todoDao.selectOne(conn, todoId);
		close(conn);
		return todo;
	}

	public int updateTodo(Todo todo) {
		Connection conn = getConnection();
		int result = todoDao.updateTodo(conn, todo);
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
}
