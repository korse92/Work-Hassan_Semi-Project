package chat.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Chat implements Serializable {

	private static final long serialVersionUID = 1L;
	private int chatId;
	private int refChannelId;
	private int refWorksMemberNo;
	private String memberNickName;
	private String chatContent;
	private Date chatRegDate;
	private String refFileRenamedName;
	
	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}
			
	public Chat(int chatId, int refChannelId, int refWorksMemberNo, String chatContent, Date chatRegDate,
			String refFileRenamedName) {
		super();
		this.chatId = chatId;
		this.refChannelId = refChannelId;
		this.refWorksMemberNo = refWorksMemberNo;
		this.chatContent = chatContent;
		this.chatRegDate = chatRegDate;
		this.refFileRenamedName = refFileRenamedName;
	}

	public Chat(int chatId, int refChannelId, int refWorksMemberNo, String memberNickName, String chatContent,
			Date chatRegDate, String refFileRenamedName) {
		super();
		this.chatId = chatId;
		this.refChannelId = refChannelId;
		this.refWorksMemberNo = refWorksMemberNo;
		this.memberNickName = memberNickName;
		this.chatContent = chatContent;
		this.chatRegDate = chatRegDate;
		this.refFileRenamedName = refFileRenamedName;
	}

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
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

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public Date getChatRegDate() {
		return chatRegDate;
	}

	public void setChatRegDate(Date chatRegDate) {
		this.chatRegDate = chatRegDate;
	}

	public String getRefFileRenamedName() {
		return refFileRenamedName;
	}

	public void setRefFileRenamedName(String refFileRenamedName) {
		this.refFileRenamedName = refFileRenamedName;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	@Override
	public String toString() {
		return "Chat [chatId=" + chatId + ", refChannelId=" + refChannelId + ", refWorksMemberNo=" + refWorksMemberNo
				+ ", memberNickName=" + memberNickName + ", chatContent=" + chatContent + ", chatRegDate=" + chatRegDate
				+ ", refFileRenamedName=" + refFileRenamedName + "]";
	}	

}
