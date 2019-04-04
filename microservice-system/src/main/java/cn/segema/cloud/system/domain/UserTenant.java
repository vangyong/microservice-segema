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

@ApiModel("用户租户关系")
@Data
@Table(name = "sys_user_tenant")
@Entity
public class UserTenant {
	@ApiModelProperty(value="用户租户关系id")
	@Id
	@Column(name = "user_tenant_id")
	private BigInteger userTenantId;

	@ApiModelProperty(value="用户id")
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ApiModelProperty(value="租户id")
	@OneToOne
	@JoinColumn(name = "tenant_id")
	private Tenant tenant;

}
