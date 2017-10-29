package cn.segema.cloud.system.vo;

/**
 * 组织机构人员信息
 * @author wangyong
 */
public class OrganizationPersonalVO {

	private String organizationId;

	private String organizationName;

	private Integer organizationCode;

	private String personalId;

	private String personalName;

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

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public OrganizationPersonalVO(String organizationId, String organizationName, Integer organizationCode,
			String personalId, String personalName, Integer type) {
		super();
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.organizationCode = organizationCode;
		this.personalId = personalId;
		this.personalName = personalName;
		this.type = type;
	}

}
