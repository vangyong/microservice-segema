package cn.segema.cloud.broadcast.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "BRO_ADVERTISEMENTTYPE")
@Entity
public class AdvertisementType {
	@Id
	@Column(name="ADVERTISEMENTTYPEID")
	private String advertisementTypeId;
	@Column(name="ADVERTISEMENTTYPENAME")
	private String advertisementTypeName;
	@Column(name = "STATE")
	private Integer state;
	@Column(name = "CACULATETYPE")
	private Integer caculateType;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "EDITABLE")
	private Integer editable;
	public String getAdvertisementTypeId() {
		return advertisementTypeId;
	}
	public void setAdvertisementTypeId(String advertisementTypeId) {
		this.advertisementTypeId = advertisementTypeId;
	}
	public String getAdvertisementTypeName() {
		return advertisementTypeName;
	}
	public void setAdvertisementTypeName(String advertisementTypeName) {
		this.advertisementTypeName = advertisementTypeName;
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
	public Integer getEditable() {
		return editable;
	}
	public void setEditable(Integer editable) {
		this.editable = editable;
	}
	
}
