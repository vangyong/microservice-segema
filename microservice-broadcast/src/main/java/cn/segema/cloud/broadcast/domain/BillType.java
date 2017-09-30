package cn.segema.cloud.broadcast.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 票据类型
 * @author wangyong
 */
@Table(name = "BRO_BILLTYPE")
@Entity
public class BillType {
	@Id
	@Column(name="BILLTYPEID")
	private String billTypeId;
	@Column(name="BILLTYPENAME")
	private String billTypeName;
	@Column(name = "STATE")
	private Integer state;
	@Column(name = "DESCRIPTION")
	private String description;
	public String getBillTypeId() {
		return billTypeId;
	}
	public void setBillTypeId(String billTypeId) {
		this.billTypeId = billTypeId;
	}
	public String getBillTypeName() {
		return billTypeName;
	}
	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
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
