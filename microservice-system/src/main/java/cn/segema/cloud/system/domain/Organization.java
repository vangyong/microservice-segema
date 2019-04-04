package cn.segema.cloud.system.domain;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("组织机构")
@Data
@Table(name = "sys_organization")
@Entity
public class Organization {
	@ApiModelProperty(value="组织机构id")
	@Id
	@Column(name = "organization_id")
	private BigInteger organizationId;

	@ApiModelProperty(value="组织机构名称")
	@Column(name = "organization_name")
	private String organizationName;

	@ApiModelProperty(value="组织机构编码")
	@Column(name = "organization_code")
	private BigInteger organizationCode;

	@ApiModelProperty(value="描述")
	@Column(name = "description")
	private String description;

	@ApiModelProperty(value="父级id")
	@ManyToOne
    @JoinColumn(name="parent_id")
    private Organization parent;
	
	@ApiModelProperty(value="类型")
	@Column(name = "type")
	private Integer type;
	
	@ApiModelProperty(value="删除标示")
	@Column(name = "delete_status")
	private Integer deleteStatus;
	
	@ApiModelProperty(value="创建时间")
	@Column(name = "create_time")
	private BigInteger createTime;
	
    @ApiModelProperty(value="租户id")
    @Column(name = "tenant_id")
    private BigInteger tenantId;
	
	@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Set<Organization> children = new HashSet<Organization>();

}
