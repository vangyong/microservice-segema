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

@ApiModel("用户资源关系")
@Data
@Table(name = "sys_user_resource")
@Entity
public class UserResource {
	@ApiModelProperty(value="用户资源关系id")
	@Id
	@Column(name = "user_resource_id")
	private BigInteger userResourceId;

	@ApiModelProperty(value="用户id")
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ApiModelProperty(value="资源id")
	@OneToOne
	@JoinColumn(name = "resource_id")
	private Resource resource;


}
