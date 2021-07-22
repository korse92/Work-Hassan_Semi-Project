package post.hashtag.model.vo;

public class Hashtag {
	private String hashtagName;
	private int hashtagRefPostId;
	private int hashtagRefChatId;
	private String hashtagPoChseparator;
	
	public Hashtag() {
	}
	public Hashtag(String hashtagName, int hashtagRefPostId, int hashtagRefChatId, String hashtagPoChseparator) {
		this.hashtagName = hashtagName;
		this.hashtagRefPostId = hashtagRefPostId;
		this.hashtagRefChatId = hashtagRefChatId;
		this.hashtagPoChseparator = hashtagPoChseparator;
	}
	public String getHashtagName() {
		return hashtagName;
	}
	public void setHashtagName(String hashtagName) {
		this.hashtagName = hashtagName;
	}
	public int getHashtagRefPostId() {
		return hashtagRefPostId;
	}
	public void setHashtagRefPostId(int hashtagRefPostId) {
		this.hashtagRefPostId = hashtagRefPostId;
	}
	public int getHashtagRefChatId() {
		return hashtagRefChatId;
	}
	public void setHashtagRefChatId(int hashtagRefChatId) {
		this.hashtagRefChatId = hashtagRefChatId;
	}
	public String getHashtagPoChseparator() {
		return hashtagPoChseparator;
	}
	public void setHashtagPoChseparator(String hashtagPoChseparator) {
		this.hashtagPoChseparator = hashtagPoChseparator;
	}
	@Override
	public String toString() {
		return "Hashtag [hashtagName=" + hashtagName + ", hashtagRefPostId=" + hashtagRefPostId + ", hashtagRefChatId="
				+ hashtagRefChatId + ", hashtagPoChseparator=" + hashtagPoChseparator + "]";
	}
	
	
}
