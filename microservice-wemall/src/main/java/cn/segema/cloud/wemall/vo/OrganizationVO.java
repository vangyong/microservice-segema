package cn.segema.cloud.wemall.vo;

import cn.segema.cloud.wemall.domain.Organization;

/**
 * 组织机构信息VO(排除chidren,parent)
 * @author wangyong
 */
public class OrganizationVO{
	
	private String organizationId;

	private String organizationName;

	private Integer organizationCode;

	private String description;
    
	private Integer type;
	
	 private String parentId;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


}
