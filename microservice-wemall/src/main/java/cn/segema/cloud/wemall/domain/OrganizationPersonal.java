package cn.segema.cloud.wemall.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 组织-人员关系
 * @author wangyong
 *
 */
@Table(name = "SYS_ORGANIZATION_PERSONAL")
@Entity
public class OrganizationPersonal {
	@Id
	@Column(name = "ORGANIZATIONPERSONALID")
	private String organizationPersonalId;

	@OneToOne
	@JoinColumn(name = "ORGANIZATIONID")
	private Organization organization;

	@OneToOne
	@JoinColumn(name = "PERSONALID")
	private Personal personal;
	
	@Column(name = "TYPE")
	private Integer type;

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrganizationPersonalId() {
		return organizationPersonalId;
	}

	public void setOrganizationPersonalId(String organizationPersonalId) {
		this.organizationPersonalId = organizationPersonalId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
