package cn.segema.cloud.system.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;


@ApiModel("用户人员信息VO")
@Data
public class UserPersonalVO implements Serializable{

	private BigInteger userId;

	private String userName;

	private String nickName;
	
	private BigInteger personalId;
	
	private String personalName;
	
	public UserPersonalVO(BigInteger userId, String userName, String nickName, BigInteger personalId, String personalName) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.nickName = nickName;
		this.personalId = personalId;
		this.personalName = personalName;
	}
	
}
