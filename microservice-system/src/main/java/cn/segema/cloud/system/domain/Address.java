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

@ApiModel("地址")
@Data
@Table(name = "sys_address")
@Entity
public class Address {
	@ApiModelProperty(value="地址id")
	@Id
	@Column(name = "address_id")
	private BigInteger addressId;
	
	@ApiModelProperty(value="地区编码")
	@Column(name = "region_code")
	private String regionCode;
	
	@ApiModelProperty(value="详细(社区街道及门牌号)")
    @Column(name = "detail_content")
    private String detailContent;
	
	@ApiModelProperty(value="描述")
	@Column(name = "description")
	private String description;
	
	@ApiModelProperty(value="经度")
	@Column(name = "longitude")
	private BigDecimal longitude;
	
	@ApiModelProperty(value="纬度")
	@Column(name = "latitude")
	private BigDecimal latitude;
	
	@ApiModelProperty(value="删除标示")
	@Column(name = "delete_status")
	private Integer deleteStatus;
	
	@ApiModelProperty(value="创建时间")
	@Column(name = "create_time")
	private BigInteger createTime;
	
}
