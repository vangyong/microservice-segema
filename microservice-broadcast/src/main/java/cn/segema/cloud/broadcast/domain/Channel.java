package cn.segema.cloud.broadcast.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 频道
 * @author wangyong
 */
@Table(name = "BRO_CHANNEL")
@Entity
public class Channel {
	@Id
	@Column(name = "CHANNELID")
	private String channelId;
	@Column(name = "CHANNELNAME")
	private String channelName;
	@Column(name = "STATE")
	private Integer state;
	@Column(name = "DESCRIPTION")
	private String description;
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
