package cn.segema.cloud.system.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("岗位")
@Data
@Table(name = "sys_post")
@Entity
public class post {
	@ApiModelProperty(value="岗位id")
	@Id
	@Column(name = "post_id")
	private BigInteger postId;
	
	@ApiModelProperty(value="岗位名称")
	@Column(name = "post_name")
	private String postName;
	
	@ApiModelProperty(value="岗位编码")
	@Column(name = "post_code")
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
	
    @ApiModelProperty(value="租户id")
    @Column(name = "tenant_id")
    private BigInteger tenantId;
	
}
