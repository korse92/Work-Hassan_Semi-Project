package channel.model.vo;

import java.io.Serializable;

public class Channel implements Serializable {
	
	private int ChannelId;
	private String ChannelName;
	private int RefWorkspaceId;  
	private String ChannelPriOrNot;
	
	public Channel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Channel(int channelId, String channelName, int refWorkspaceId, String channelPriOrNot) {
		super();
		ChannelId = channelId;
		ChannelName = channelName;
		RefWorkspaceId = refWorkspaceId;
		ChannelPriOrNot = channelPriOrNot;
	}

	public int getChannelId() {
		return ChannelId;
	}

	public void setChannelId(int channelId) {
		ChannelId = channelId;
	}

	public String getChannelName() {
		return ChannelName;
	}

	public void setChannelName(String channelName) {
		ChannelName = channelName;
	}

	public int getRefWorkspaceId() {
		return RefWorkspaceId;
	}

	public void setRefWorkspaceId(int refWorkspaceId) {
		RefWorkspaceId = refWorkspaceId;
	}

	public String getChannelPriOrNot() {
		return ChannelPriOrNot;
	}

	public void setChannelPriOrNot(String channelPriOrNot) {
		ChannelPriOrNot = channelPriOrNot;
	}

	@Override
	public String toString() {
		return "Channel [ChannelId=" + ChannelId + ", ChannelName=" + ChannelName + ", RefWorkspaceId=" + RefWorkspaceId
				+ ", ChannelPriOrNot=" + ChannelPriOrNot + "]";
	}

}
