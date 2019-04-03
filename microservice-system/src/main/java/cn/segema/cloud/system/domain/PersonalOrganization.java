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

@ApiModel("人员机构机构关系")
@Data
@Table(name = "sys_personal_organization")
@Entity
public class PersonalOrganization {
	@ApiModelProperty(value="人员组织机构关系id")
	@Id
	@Column(name = "personal_organization_id")
	private BigInteger PersonalOrganizationId;

	@ApiModelProperty(value="人员id")
	@OneToOne
	@JoinColumn(name = "personal_id")
	private Personal personal;

	@ApiModelProperty(value="组织机构id")
	@OneToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;

}
