package cn.segema.cloud.system.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("组织机构人员关系")
@Data
@Table(name = "sys_organization_personal")
@Entity
public class OrganizationPersonal {
	@ApiModelProperty(value="组织机构人员关系id")
	@Id
	@Column(name = "organization_personal_id")
	private BigInteger organizationPersonalId;

	@ApiModelProperty(value="组织机构id")
	@OneToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;

	@ApiModelProperty(value="人员id")
	@OneToOne
	@JoinColumn(name = "personal_id")
	private Personal personal;
	
	@ApiModelProperty(value="类型")
	@Column(name = "type")
	private Integer type;

}
