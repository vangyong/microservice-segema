package cn.segema.cloud.system.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("树结构VO")
@Data
public class TreeVO implements Serializable{

	private static final long serialVersionUID = -3952423354743264992L;

	private BigInteger id;

	private String text;

	private List<TreeVO> children;

}
