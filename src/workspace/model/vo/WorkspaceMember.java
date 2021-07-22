package workspace.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class WorkspaceMember implements Serializable {
	
	private int worksMemberNo;
	private String refMemberId;
	private int refWorkspaceId;
	private String worksMemberNickname;
	private String worksMemberRole;
	private Date worksMemberRegDate;
	private String renamedProfileImg;
		
	public WorkspaceMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkspaceMember(int worksMemberNo, String refMemberId, int refWorkspaceId, String worksMemberNickname,
			String worksMemberRole, Date worksMemberRegDate) {
		super();
		this.worksMemberNo = worksMemberNo;
		this.refMemberId = refMemberId;
		this.refWorkspaceId = refWorkspaceId;
		this.worksMemberNickname = worksMemberNickname;
		this.worksMemberRole = worksMemberRole;
		this.worksMemberRegDate = worksMemberRegDate;
	}
	
	public String getRenamedProfileImg() {
		return renamedProfileImg;
	}

	public void setRenamedProfileImg(String renamedProfileImg) {
		this.renamedProfileImg = renamedProfileImg;
	}

	public int getWorksMemberNo() {
		return worksMemberNo;
	}

	public void setWorksMemberNo(int worksMemberNo) {
		this.worksMemberNo = worksMemberNo;
	}

	public String getRefMemberId() {
		return refMemberId;
	}

	public void setRefMemberId(String refMemberId) {
		this.refMemberId = refMemberId;
	}

	public int getRefWorkspaceId() {
		return refWorkspaceId;
	}

	public void setRefWorkspaceId(int refWorkspaceId) {
		this.refWorkspaceId = refWorkspaceId;
	}

	public String getWorksMemberNickname() {
		return worksMemberNickname;
	}

	public void setWorksMemberNickname(String worksMemberNickname) {
		this.worksMemberNickname = worksMemberNickname;
	}

	public String getWorksMemberRole() {
		return worksMemberRole;
	}

	public void setWorksMemberRole(String worksMemberRole) {
		this.worksMemberRole = worksMemberRole;
	}

	public Date getWorksMemberRegDate() {
		return worksMemberRegDate;
	}

	public void setWorksMemberRegDate(Date worksMemberRegDate) {
		this.worksMemberRegDate = worksMemberRegDate;
	}

	@Override
	public String toString() {
		return "WorkspaceMember [worksMemberNo=" + worksMemberNo + ", refMemberId=" + refMemberId
				+ ", refWorkspaceId=" + refWorkspaceId + ", worksMemberNickname=" + worksMemberNickname
				+ ", worksMemberRole=" + worksMemberRole + ", worksMemberRegDate=" + worksMemberRegDate + "]";
	}
}
