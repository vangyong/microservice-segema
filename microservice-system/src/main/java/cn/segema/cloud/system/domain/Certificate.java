package cn.segema.cloud.system.domain;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("证件信息")
@Data
@Table(name = "sys_certificate")
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Certificate{

    @ApiModelProperty(value = "证件id")
    @Id
    @Column(name = "certificate_id")
    private BigInteger certificateId;

    @ApiModelProperty(value = "证件类型", required = true)
    @Column(name = "certificate_type")
    private String certificateType;

    @ApiModelProperty(value = "证件号码", required = true)
    @Column(name = "certificate_code")
    private String certificateCode;

    @ApiModelProperty(value = "证件名称", required = true)
    @Column(name = "certificate_name")
    private String certificateName;

    @ApiModelProperty(value = "删除标示")
    @Column(name = "delete_status")
    private Integer deleteStatus;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private BigInteger createTime;

    @ApiModelProperty(value = "证件url")
    @Column(name = "certificate_url")
    private String certificateUrl;

    @ApiModelProperty(value = "描述")
    @Column(name = "description")
    private String description;

    @ApiModelProperty(value = "有效期始")
    @Column(name = "start_time")
    private BigInteger startTime;
    
    @ApiModelProperty(value = "有效期止")
    @Column(name = "end_time")
    private BigInteger endTime;

    @ApiModelProperty(value = "证件明细")
    @Type( type = "json" )
    @Column(name = "detail_content")
    private List<Map> detailContent;
    
    @ApiModelProperty(value = "业务数据")
    @Column(name = "business_id")
    private BigInteger businessId;

}
