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

@ApiModel("用户人员关系")
@Data
@Table(name = "sys_user_personal")
@Entity
public class UserPersonal {
	@ApiModelProperty(value="用户人员关系id")
	@Id
	@Column(name = "user_personal_id")
	private BigInteger userPersonalId;

	@ApiModelProperty(value="用户id")
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ApiModelProperty(value="人员id")
	@OneToOne
	@JoinColumn(name = "personal_id")
	private Personal personal;

}
