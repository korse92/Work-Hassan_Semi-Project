package post.cmt.model.vo;

import java.sql.Date;

public class Cmt {
	private int cmt_id;
	private String cmt_content;
	private Date cmt_reg_date;
	private int cmt_ref_post_id;
	private int ref_works_member_no;
	
	public Cmt() {
	}

	public Cmt(int cmt_id, String cmt_content, Date cmt_reg_date, int cmt_ref_post_id, int ref_works_member_no) {
		this.cmt_id = cmt_id;
		this.cmt_content = cmt_content;
		this.cmt_reg_date = cmt_reg_date;
		this.cmt_ref_post_id = cmt_ref_post_id;
		this.ref_works_member_no = ref_works_member_no;
	}

	public int getCmt_id() {
		return cmt_id;
	}

	public void setCmt_id(int cmt_id) {
		this.cmt_id = cmt_id;
	}

	public String getCmt_content() {
		return cmt_content;
	}

	public void setCmt_content(String cmt_content) {
		this.cmt_content = cmt_content;
	}

	public Date getCmt_reg_date() {
		return cmt_reg_date;
	}

	public void setCmt_reg_date(Date cmt_reg_date) {
		this.cmt_reg_date = cmt_reg_date;
	}

	public int getCmt_ref_post_id() {
		return cmt_ref_post_id;
	}

	public void setCmt_ref_post_id(int cmt_ref_post_id) {
		this.cmt_ref_post_id = cmt_ref_post_id;
	}

	public int getRef_works_member_no() {
		return ref_works_member_no;
	}

	public void setRef_works_member_no(int ref_works_member_no) {
		this.ref_works_member_no = ref_works_member_no;
	}

	@Override
	public String toString() {
		return "Cmt [cmt_id=" + cmt_id + ", cmt_content=" + cmt_content + ", cmt_reg_date=" + cmt_reg_date
				+ ", cmt_ref_post_id=" + cmt_ref_post_id + ", ref_works_member_no=" + ref_works_member_no + "]";
	}
	
	
}
