package cn.segema.cloud.system.vo;

import java.math.BigDecimal;
import java.math.BigInteger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("地址VO")
@Data
public class AddressVO {
    @ApiModelProperty(value = "地址id")
    private BigInteger addressId;

    @ApiModelProperty(value = "地区编码")
    private String regionCode;

    @ApiModelProperty(value = "详细(社区街道及门牌号)")
    private String detailContent;
    
    @ApiModelProperty(value = "国家编码")
    private String countryCode;

    @ApiModelProperty(value = "国家名称")
    private String countryName;

    @ApiModelProperty(value = "省级编码")
    private String provinceCode;

    @ApiModelProperty(value = "省级名称")
    private String provinceName;

    @ApiModelProperty(value = "地市级编码")
    private String cityCode;

    @ApiModelProperty(value = "地市级名称")
    private String cityName;

    @ApiModelProperty(value = "区县级编码")
    private String countyCode;

    @ApiModelProperty(value = "区县级名称")
    private String countyName;

    @ApiModelProperty(value = "乡镇级编码")
    private String townsCode;

    @ApiModelProperty(value = "乡镇级名称")
    private String townsName;
    
    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "删除标示")
    private Integer deleteStatus;

    @ApiModelProperty(value = "创建时间")
    private BigInteger createTime;

}
