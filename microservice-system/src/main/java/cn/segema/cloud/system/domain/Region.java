package cn.segema.cloud.system.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("行政地区")
@Data
@Table(name = "sys_region")
@Entity
public class Region {
	@ApiModelProperty(value="地区id")
	@Id
	@Column(name = "region_id")
	private BigInteger regionId;
	
	@ApiModelProperty(value="编码")
	@Column(name = "region_code")
	private String regionCode;

	@ApiModelProperty(value="名称")
	@Column(name = "region_name")
	private String regionName;
	
	@ApiModelProperty(value="父级编码")
	@Column(name = "parent_code")
	private String parentCode;

	@ApiModelProperty(value="类型")
	@Column(name = "code_type")
	private Integer codeType;
	
	@ApiModelProperty(value="删除标示")
	@Column(name = "delete_status")
	private Integer deleteStatus;
	
	@ApiModelProperty(value="创建时间")
	@Column(name = "create_time")
	private BigInteger createTime;
	
	@ApiModelProperty(value="国家编码")
	@Column(name = "country_code")
	private String countryCode;
	
	@ApiModelProperty(value="描述")
	@Column(name = "description")
	private String description;

}
