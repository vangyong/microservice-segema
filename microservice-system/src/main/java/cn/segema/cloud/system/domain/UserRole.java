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

@ApiModel("用户角色关系")
@Data
@Table(name = "sys_user_role")
@Entity
public class UserRole {
	@ApiModelProperty(value="用户角色关系id")
	@Id
	@Column(name = "user_role_id")
	private BigInteger userRoleId;

	@ApiModelProperty(value="用户id")
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ApiModelProperty(value="角色id")
	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;

}
