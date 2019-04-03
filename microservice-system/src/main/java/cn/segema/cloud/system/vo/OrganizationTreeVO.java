package cn.segema.cloud.system.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("组织机构人员信息")
@Data
public class OrganizationTreeVO implements Serializable{

	private static final long serialVersionUID = 7846035097759913796L;

	private BigInteger organizationId;

	private String organizationName;

	private BigInteger organizationCode;

	private List<OrganizationTreeVO> children;

}
