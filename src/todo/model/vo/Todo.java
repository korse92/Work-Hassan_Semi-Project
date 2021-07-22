package todo.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Todo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	/*
	 	이름                  널?       유형            
		------------------- -------- ------------- 
		TODO_ID             NOT NULL NUMBER        
		REF_WORKSPACE_ID             NUMBER        
		REF_WORKS_MEMBER_NO NOT NULL NUMBER        
		TODO_TITLE          NOT NULL VARCHAR2(100) 
		TODO_CONTENT        NOT NULL VARCHAR2(500) 
		TODO_REG_DATE                DATE          
		TODO_STATUS         NOT NULL VARCHAR2(30)  
		TODO_START_DATE              DATE          
		TODO_END_DATE                DATE   
	 */

	
	private int todoId; //not null
	private int workspaceIdRef;
	private int workspaceMemberNoRef; //not null
	private String todoTitle; //not null
	private String todoContent; //not null
	private Date todoRegDate;
	private String todoStatus; //not null
	private Date todoStartDate;
	private Date todoEndDate;
	
	public Todo() {
		super();
	}
	
	public Todo(int todoId, int workspaceIdRef, int workspaceMemberNoRef, String todoTitle, String todoContent,
			Date todoRegDate, String todoStatus, Date todoStartDate, Date todoEndDate) {
		super();
		this.todoId = todoId;
		this.workspaceIdRef = workspaceIdRef;
		this.workspaceMemberNoRef = workspaceMemberNoRef;
		this.todoTitle = todoTitle;
		this.todoContent = todoContent;
		this.todoRegDate = todoRegDate;
		this.todoStatus = todoStatus;
		this.todoStartDate = todoStartDate;
		this.todoEndDate = todoEndDate;
	}

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

	public int getWorkspaceIdRef() {
		return workspaceIdRef;
	}

	public void setWorkspaceIdRef(int workspaceIdRef) {
		this.workspaceIdRef = workspaceIdRef;
	}

	public int getWorkspaceMemberNoRef() {
		return workspaceMemberNoRef;
	}

	public void setWorkspaceMemberNoRef(int workspaceMemberNoRef) {
		this.workspaceMemberNoRef = workspaceMemberNoRef;
	}

	public String getTodoTitle() {
		return todoTitle;
	}

	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}

	public String getTodoContent() {
		return todoContent;
	}

	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}

	public Date getTodoRegDate() {
		return todoRegDate;
	}

	public void setTodoRegDate(Date todoRegDate) {
		this.todoRegDate = todoRegDate;
	}

	public String getTodoStatus() {
		return todoStatus;
	}

	public void setTodoStatus(String todoStatus) {
		this.todoStatus = todoStatus;
	}

	public Date getTodoStartDate() {
		return todoStartDate;
	}

	public void setTodoStartDate(Date todoStartDate) {
		this.todoStartDate = todoStartDate;
	}

	public Date getTodoEndDate() {
		return todoEndDate;
	}

	public void setTodoEndDate(Date todoEndDate) {
		this.todoEndDate = todoEndDate;
	}

	@Override
	public String toString() {
		return "Todo [todoId=" + todoId + ", workspaceIdRef=" + workspaceIdRef + ", workspaceMemberNoRef="
				+ workspaceMemberNoRef + ", todoTitle=" + todoTitle + ", todoContent=" + todoContent + ", todoRegDate="
				+ todoRegDate + ", todoStatus=" + todoStatus + ", todoStartDate=" + todoStartDate + ", todoEndDate="
				+ todoEndDate + "]";
	}
}
