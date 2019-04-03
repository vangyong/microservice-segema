package cn.segema.cloud.system.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("组织机构信息VO(排除chidren,parent)")
@Data
public class OrganizationVO implements Serializable{
	
	private static final long serialVersionUID = 3600765270415734135L;

	private BigInteger organizationId;

	private String organizationName;

	private BigInteger organizationCode;

	private String description;

	private Integer type;

	private BigInteger parentId;

}
