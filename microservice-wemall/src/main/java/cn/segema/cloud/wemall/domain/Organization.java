package cn.segema.cloud.wemall.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@ManyToOne
    @JoinColumn(name="PARENTID")
    private Organization parent;
	
	@Column(name = "TYPE")
	private Integer type;
	
	@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENTID")
    private Set<Organization> children = new HashSet<Organization>();

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

	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	public Set<Organization> getChildren() {
		return children;
	}

	public void setChildren(Set<Organization> children) {
		this.children = children;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
