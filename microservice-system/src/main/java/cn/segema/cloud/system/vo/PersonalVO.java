package cn.segema.cloud.system.vo;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("人员信息VO")
@Data
@JsonInclude(value = Include.NON_EMPTY)
public class PersonalVO implements Serializable {

	private static final long serialVersionUID = 3336026114894190728L;

	private String personalName;

	private Integer gender;

	private String mobileNumber;
}
