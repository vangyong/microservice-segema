package cn.segema.cloud.system.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import lombok.Data;


@ApiModel("角色信息VO")
@Data
@JsonInclude(value = Include.NON_EMPTY)
public class RoleVO implements Serializable {

	private static final long serialVersionUID = 3336026114894190728L;

	private String roleName;

	private String roleCode;

}
