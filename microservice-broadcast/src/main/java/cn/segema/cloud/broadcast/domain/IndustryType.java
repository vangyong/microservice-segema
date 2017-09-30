package cn.segema.cloud.broadcast.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 行业类型
 * @author wangyong
 */
@Table(name = "BRO_INDUSTRYTYPE")
@Entity
public class IndustryType {
	@Id
	@Column(name="INDUSTRYTYPEID")
	private String industryTypeId;
	@Column(name="INDUSTRYTYPENAME")
	private String industryTypeName;
	@Column(name = "STATE")
	private Integer state;
	@Column(name = "DESCRIPTION")
	private String description;
	public String getIndustryTypeId() {
		return industryTypeId;
	}
	public void setIndustryTypeId(String industryTypeId) {
		this.industryTypeId = industryTypeId;
	}
	public String getIndustryTypeName() {
		return industryTypeName;
	}
	public void setIndustryTypeName(String industryTypeName) {
		this.industryTypeName = industryTypeName;
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
