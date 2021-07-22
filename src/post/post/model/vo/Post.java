package post.post.model.vo;

import java.sql.Date;
import java.util.Arrays;

public class Post{
	private int postId;
	private int refChannelId;
	private int refWorksMemberNo;
	private int refCategoryId;
	private String postTitle;
	private String postContent;
	private Date postRegDate;
	private int postHit;
	private String refFileRenameedName;
	
	public Post() {
	}

	public Post(int postId, int refChannelId, int refWorksMemberNo, int refCategoryId, String postTitle,
			String postContent, Date postRegDate, int postHit, String refFileRenameedName) {
		this.postId = postId;
		this.refChannelId = refChannelId;
		this.refWorksMemberNo = refWorksMemberNo;
		this.refCategoryId = refCategoryId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postRegDate = postRegDate;
		this.postHit = postHit;
		this.refFileRenameedName = refFileRenameedName;
	}

	public Post(int postId, int refCategoryId, String postTitle, String postContent, String refFileRenameedName) {
		this.postId = postId;
		this.refCategoryId = refCategoryId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.refFileRenameedName = refFileRenameedName;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getRefChannelId() {
		return refChannelId;
	}

	public void setRefChannelId(int refChannelId) {
		this.refChannelId = refChannelId;
	}

	public int getRefWorksMemberNo() {
		return refWorksMemberNo;
	}

	public void setRefWorksMemberNo(int refWorksMemberNo) {
		this.refWorksMemberNo = refWorksMemberNo;
	}

	public int getRefCategoryId() {
		return refCategoryId;
	}

	public void setRefCategoryId(int refCategoryId) {
		this.refCategoryId = refCategoryId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Date getPostRegDate() {
		return postRegDate;
	}

	public void setPostRegDate(Date postRegDate) {
		this.postRegDate = postRegDate;
	}

	public int getPostHit() {
		return postHit;
	}

	public void setPostHit(int postHit) {
		this.postHit = postHit;
	}

	public String getRefFileRenameedName() {
		return refFileRenameedName;
	}

	public void setRefFileRenameedName(String refFileRenameedName) {
		this.refFileRenameedName = refFileRenameedName;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", refChannelId=" + refChannelId + ", refWorksMemberNo=" + refWorksMemberNo
				+ ", refCategoryId=" + refCategoryId + ", postTitle=" + postTitle + ", postContent=" + postContent
				+ ", postRegDate=" + postRegDate + ", postHit=" + postHit
				+ ", refFileRenameedName=" + refFileRenameedName + "]";
	}
	
}
