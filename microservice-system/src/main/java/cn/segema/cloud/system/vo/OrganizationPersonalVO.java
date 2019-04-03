package cn.segema.cloud.system.vo;

import java.io.Serializable;
import java.math.BigInteger;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("组织机构人员信息")
@Data
public class OrganizationPersonalVO implements Serializable{

	private static final long serialVersionUID = -4808032457150304100L;

	private BigInteger organizationId;

	private String organizationName;

	private BigInteger organizationCode;

	private BigInteger personalId;

	private String personalName;

	private Integer type;

	public OrganizationPersonalVO(BigInteger organizationId, String organizationName, BigInteger organizationCode,
			BigInteger personalId, String personalName, Integer type) {
		super();
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.organizationCode = organizationCode;
		this.personalId = personalId;
		this.personalName = personalName;
		this.type = type;
	}
}
