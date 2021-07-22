package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {

	private String memberId;
	private String memberPwd;
	private String memberName;
	private Date birthday;
	private String gender;
	private String email;
	private String phone;
	private Date enrollDate;
	private String memberStatus;
	private String originalProfile;
	private String renamedProfile;
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String memberId, String memberPwd, String memberName, Date birthday, String gender, String email,
			String phone, Date enrollDate, String memberStatus, String originalProfile, String renamedProfile) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.enrollDate = enrollDate;
		this.memberStatus = memberStatus;
		this.originalProfile = originalProfile;
		this.renamedProfile = renamedProfile;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getOriginalProfile() {
		return originalProfile;
	}

	public void setOriginalProfile(String originalProfile) {
		this.originalProfile = originalProfile;
	}

	public String getRenamedProfile() {
		return renamedProfile;
	}

	public void setRenamedProfile(String renamedProfile) {
		this.renamedProfile = renamedProfile;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName=" + memberName
				+ ", birthday=" + birthday + ", gender=" + gender + ", email=" + email + ", phone=" + phone
				+ ", enrollDate=" + enrollDate + ", memberStatus=" + memberStatus + ", originalProfile="
				+ originalProfile + ", renamedProfile=" + renamedProfile + "]";
	}

	
	
}