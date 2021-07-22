package post.File.model.vo;

public class Files {
	private String fileRenamedName;
	private String fileOriginalName;
	private String filePoChSeparator;
	private int fileRefPostId;
	private int fileRefChatId;

	public Files(String fileRenamedName, String fileOriginalName, String filePoChSeparator, int fileRefPostId,
			int fileRefChatId) {
		this.fileRenamedName = fileRenamedName;
		this.fileOriginalName = fileOriginalName;
		this.filePoChSeparator = filePoChSeparator;
		this.fileRefPostId = fileRefPostId;
		this.fileRefChatId = fileRefChatId;
	}

	public String getFileRenamedName() {
		return fileRenamedName;
	}
	
	public void setFileRenamedName(String fileRenamedName) {
		this.fileRenamedName = fileRenamedName;
	}

	public String getFileOriginalName() {
		return fileOriginalName;
	}

	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}

	public String getFilePoChSeparator() {
		return filePoChSeparator;
	}

	public void setFilePoChSeparator(String filePoChSeparator) {
		this.filePoChSeparator = filePoChSeparator;
	}

	public int getFileRefPostId() {
		return fileRefPostId;
	}

	public void setFileRefPostId(int fileRefPostId) {
		this.fileRefPostId = fileRefPostId;
	}

	public int getFileRefChatId() {
		return fileRefChatId;
	}

	public void setFileRefChatId(int fileRefChatId) {
		this.fileRefChatId = fileRefChatId;
	}

	@Override
	public String toString() {
		return "Files [fileRenamedName=" + fileRenamedName + ", fileOriginalName=" + fileOriginalName
				+ ", filePoChSeparator=" + filePoChSeparator + ", fileRefPostId=" + fileRefPostId + ", fileRefChatId="
				+ fileRefChatId + "]";
	}
}
