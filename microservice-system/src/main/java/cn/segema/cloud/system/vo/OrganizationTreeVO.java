package cn.segema.cloud.system.vo;

import java.util.List;

public class OrganizationTreeVO {

	private String organizationId;

	private String organizationName;

	private Integer organizationCode;

	private List<OrganizationTreeVO> children;

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

	public List<OrganizationTreeVO> getChildren() {
		return children;
	}

	public void setChildren(List<OrganizationTreeVO> children) {
		this.children = children;
	}

}
