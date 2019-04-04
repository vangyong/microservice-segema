package cn.segema.cloud.system.domain;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("租户信息")
@Data
@Table(name = "sys_tenant")
@Entity
public class Tenant {
	@ApiModelProperty(value="租户id")
	@Id
	@Column(name = "tenant_id")
	private BigInteger tenantId;
	
	@ApiModelProperty(value="用户id",required = true)
	@Column(name = "user_id")
	private BigInteger userId;

	@ApiModelProperty(value="租户名称",required = true)
	@Column(name = "tenant_name")
	private String tenantName;
	
	@ApiModelProperty(value="租户编码")
	@Column(name = "tenant_code")
	private String tenantCode;

	@ApiModelProperty(value="手机号",required = true)
	@Column(name = "mobile_number")
	private String mobileNumber;

	@ApiModelProperty(value="办公室电话")
	@Column(name = "office_phone")
	private String officePhone;

	@ApiModelProperty(value="删除标示")
	@Column(name = "delete_status")
	private Integer deleteStatus;

	@ApiModelProperty(value="租户地址id")
	@Column(name = "tenant_address_id")
	private BigInteger tenantAddressId;

	@ApiModelProperty(value="创建时间")
	@Column(name = "create_time")
	private BigInteger createTime;

	@ApiModelProperty(value="经营模式(1:生产厂家 2:经销商 3:种植户 4:养殖户 5:合作社 6:个体户)")
	@Column(name = "business_model")
	private Integer businessModel;

	@ApiModelProperty(value="主营产品")
	@Column(name = "main_product")
	private String mainProduct;
	
	@ApiModelProperty(value="状态(1:注册,2:待审核通过,3:正常经营,4:暂停经营,5:注销)")
    @Column(name = "status")
    private Integer status;

}
