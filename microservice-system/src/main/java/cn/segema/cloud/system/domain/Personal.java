package cn.segema.cloud.system.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("人员信息")
@Data
@Table(name = "sys_personal")
@Entity
public class Personal {
	@ApiModelProperty(value="人员id")
	@Id
	@Column(name = "personal_id")
	private BigInteger personalId;

	@ApiModelProperty(value="人员名字")
	@Column(name = "personal_name")
	private String personalName;

	@ApiModelProperty(value="手机号码")
	@Column(name = "mobile_number")
	private String mobileNumber;

	@ApiModelProperty(value="性别")
	@Column(name = "gender")
	private Integer gender;

	@ApiModelProperty(value="出生地址")
	@Column(name = "born_address_id")
	private BigInteger bornAddressId;

	@ApiModelProperty(value="出生时间")
	@Column(name = "born_time")
	private BigInteger bornTime;

	@ApiModelProperty(value="体重")
	@Column(name = "weight")
	private BigDecimal weight;

	@ApiModelProperty(value="身高")
	@Column(name = "height")
	private BigDecimal height;

	@ApiModelProperty(value="血型")
	@Column(name = "blood_type")
	private BigDecimal bloodType;

	@ApiModelProperty(value="邮件")
	@Column(name = "email")
	private String email;

	@ApiModelProperty(value="家庭住址")
	@Column(name = "home_address_id")
	private BigInteger homeAddressId;

	@ApiModelProperty(value="组id")
	@Column(name = "group_id")
	private BigInteger groupId;

	@ApiModelProperty(value="证件类型")
	@Column(name = "id_card_type")
	private Integer idCardType;

	@ApiModelProperty(value="证件号码")
	@Column(name = "identity_card")
	private String identityCard;

	@ApiModelProperty(value="删除标示")
	@Column(name = "delete_status")
	private Integer deleteStatus;

	@ApiModelProperty(value="创建时间")
	@Column(name = "create_time")
	private BigInteger createTime;

    @ApiModelProperty(value="租户id")
    @Column(name = "tenant_id")
    private BigInteger tenantId;
}
