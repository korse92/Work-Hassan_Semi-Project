package workspace.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Workspace implements Serializable {
	
//	Workspace_Id          Not Null Number        
//	Workspace_Name        Not Null Varchar2(40)  
//	Workspace_Invite_Link Not Null Varchar2(300) 
//	Workspace_Reg_Date             Date          
//	Workspace_Icon                 Varchar2(50)  
	
	private int WorkspaceId;
	private String WorkspaceName;
	private String WorkspaceInviteLink;
	private Date WorkspaceRegDate;
	private String WorkspaceIcon;
	
	public Workspace() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Workspace(int workspaceId, String workspaceName, String workspaceInviteLink, Date workspaceRegDate,
			String workspaceIcon) {
		super();
		WorkspaceId = workspaceId;
		WorkspaceName = workspaceName;
		WorkspaceInviteLink = workspaceInviteLink;
		WorkspaceRegDate = workspaceRegDate;
		WorkspaceIcon = workspaceIcon;
	}

	public int getWorkspaceId() {
		return WorkspaceId;
	}

	public void setWorkspaceId(int workspaceId) {
		WorkspaceId = workspaceId;
	}

	public String getWorkspaceName() {
		return WorkspaceName;
	}

	public void setWorkspaceName(String workspaceName) {
		WorkspaceName = workspaceName;
	}

	public String getWorkspaceInviteLink() {
		return WorkspaceInviteLink;
	}

	public void setWorkspaceInviteLink(String workspaceInviteLink) {
		WorkspaceInviteLink = workspaceInviteLink;
	}

	public Date getWorkspaceRegDate() {
		return WorkspaceRegDate;
	}

	public void setWorkspaceRegDate(Date workspaceRegDate) {
		WorkspaceRegDate = workspaceRegDate;
	}

	public String getWorkspaceIcon() {
		return WorkspaceIcon;
	}

	public void setWorkspaceIcon(String workspaceIcon) {
		WorkspaceIcon = workspaceIcon;
	}

	@Override
	public String toString() {
		return "Workspace [WorkspaceId=" + WorkspaceId + ", WorkspaceName=" + WorkspaceName + ", WorkspaceInviteLink="
				+ WorkspaceInviteLink + ", WorkspaceRegDate=" + WorkspaceRegDate + ", WorkspaceIcon=" + WorkspaceIcon
				+ "]";
	}
}
