package cn.segema.cloud.broadcast.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 档位
 * @author wangyong
 */
@Table(name = "BRO_STALLS")
@Entity
public class Stalls {
	@Id
	@Column(name = "STALLSID")
	private String stallsId;
	@Column(name = "STALLSNAME")
	private String stallsName;
	@Column(name = "STATE")
	private Integer state;
	@Column(name = "DESCRIPTION")
	private String description;

	public String getStallsId() {
		return stallsId;
	}

	public void setStallsId(String stallsId) {
		this.stallsId = stallsId;
	}

	public String getStallsName() {
		return stallsName;
	}

	public void setStallsName(String stallsName) {
		this.stallsName = stallsName;
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
