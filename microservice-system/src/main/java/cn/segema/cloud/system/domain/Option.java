package cn.segema.cloud.system.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("配置参数")
@Data
@Table(name = "sys_option")
@Entity
public class Option {
	@ApiModelProperty(value="配置参数id")
	@Id
	@Column(name = "option_id")
	private BigInteger optionId;
	
	@ApiModelProperty(value="参数类型")
	@Column(name = "option_type")
	private String optionType;

	@ApiModelProperty(value="参数key")
	@Column(name = "option_key")
	private String optionKey;

	@ApiModelProperty(value="参数value")
	@Column(name = "option_value")
	private String optionValue;
	
	@ApiModelProperty(value="删除标示")
	@Column(name = "delete_status")
	private Integer deleteStatus;
	
	@ApiModelProperty(value="创建时间")
	@Column(name = "create_time")
	private BigInteger createTime;
	
}
