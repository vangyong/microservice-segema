package cn.segema.cloud.broadcast.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 客户类型
 * @author wangyong
 */
@Table(name = "BRO_CUSTOMERTYPE")
@Entity
public class CustomerType {
	@Id
	@Column(name = "CUSTOMERTYPEID")
	private String customerTypeId;
	@Column(name = "CUSTOMERTYPENAME")
	private String customerTypeName;
	@Column(name = "STATE")
	private Integer state;
	@Column(name = "DESCRIPTION")
	private String description;

	public String getCustomerTypeId() {
		return customerTypeId;
	}

	public void setCustomerTypeId(String customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public String getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
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
