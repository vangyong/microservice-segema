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

@ApiModel("角色资源关系")
@Data
@Table(name = "sys_role_resource")
@Entity
public class RoleResource {
	@ApiModelProperty(value="角色资源关系id")
	@Id
	@Column(name = "role_resource_id")
	private BigInteger roleResourceId;

	@ApiModelProperty(value="角色id")
	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@ApiModelProperty(value="资源id")
	@OneToOne
	@JoinColumn(name = "resource_id")
	private Resource resource;

}
