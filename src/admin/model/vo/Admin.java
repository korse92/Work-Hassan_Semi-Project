package admin.model.vo;

import java.io.Serializable;
/**
 * 
 * admin 테이블의 한행에 상응하는vo클래스
 * 
 */
public class Admin implements Serializable {

	private String adminId; //필수 (not null)
	private String adminPassword; //필수 (not null)
	private String adminEmail;
	private String adminRole;
	
	//생성자
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String adminId, String adminPassword, String adminEmail, String adminRole) {
		super();
		this.adminId = adminId;
		this.adminPassword = adminPassword;
		this.adminEmail = adminEmail;
		this.adminRole = adminRole;
	}

	//겟터,셋터
	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

	//toStirng
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminPassword=" + adminPassword + ", adminEmail=" + adminEmail
				+ ", adminRole=" + adminRole + "]";
	}
	
	
}
