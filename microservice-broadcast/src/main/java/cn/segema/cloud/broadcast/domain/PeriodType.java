package cn.segema.cloud.broadcast.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 时段类型
 * @author wangyong
 */
@Table(name = "BRO_PERIODTYPE")
@Entity
public class PeriodType {
	@Id
	@Column(name="PERIODTYPEID")
	private String periodTypeId;
	@Column(name="PERIODTYPENAME")
	private String periodTypeName;
	@Column(name = "STATE")
	private Integer state;
	@Column(name = "CACULATETYPE")
	private Integer caculateType;
	@Column(name = "DESCRIPTION")
	private String description;
	public String getPeriodTypeId() {
		return periodTypeId;
	}
	public void setPeriodTypeId(String periodTypeId) {
		this.periodTypeId = periodTypeId;
	}
	public String getPeriodTypeName() {
		return periodTypeName;
	}
	public void setPeriodTypeName(String periodTypeName) {
		this.periodTypeName = periodTypeName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getCaculateType() {
		return caculateType;
	}
	public void setCaculateType(Integer caculateType) {
		this.caculateType = caculateType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
