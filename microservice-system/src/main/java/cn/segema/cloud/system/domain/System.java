package cn.segema.cloud.system.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("子系统")
@Data
@Table(name = "sys_system")
@Entity
public class System {
	@ApiModelProperty(value="子系统id")
	@Id
	@Column(name = "system_id")
	private BigInteger systemId;
	
	@ApiModelProperty(value="子系统名称")
	@Column(name = "system_name")
	private String systemName;
	
	@ApiModelProperty(value="子系统编码")
	@Column(name = "system_code")
	private String systemCode;
	
	@ApiModelProperty(value="描述")
	@Column(name = "description")
	private String description;
	
	@ApiModelProperty(value="删除标示")
	@Column(name = "delete_status")
	private Integer deleteStatus;
	
	@ApiModelProperty(value="创建时间")
	@Column(name = "create_time")
	private BigInteger createTime;

}
