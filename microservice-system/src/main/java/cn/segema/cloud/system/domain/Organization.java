package cn.segema.cloud.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 组织机构
 * @author wangyong
 *
 */
@Table(name = "SYS_ORGANIZATION")
@Entity
public class Organization {
	@Id
	@Column(name = "ORGANIZATIONID")
	private String organizationId;

	@Column(name = "ORGANIZATIONNAME")
	private String organizationName;

	@Column(name = "ORGANIZATIONCODE")
	private Integer organizationCode;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PARENTCODE")
	private Integer parentCode;

	@Column(name = "TYPE")
	private Integer type;

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Integer getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(Integer organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
