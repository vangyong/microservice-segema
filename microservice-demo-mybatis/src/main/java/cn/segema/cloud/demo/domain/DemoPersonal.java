package cn.segema.cloud.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 人员
 * @author wangyong
 *
 */
public class DemoPersonal {
	private String personalId;
	
	private String personalName;
	
	private String mobileNumber;
	
	private String gender;
	
	private String bornAddressId;
	
	private LocalDateTime bornTime;
	
	private BigDecimal weight;
	
	private BigDecimal height;
	
	private BigDecimal bloodType;
	
	private String email;
	
	private String homeAddressId;
	
	private String groupId;
	
	private String nation;
	
	private String identityNumber;
	
	private LocalDateTime createTime;


	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBornAddressId() {
		return bornAddressId;
	}

	public void setBornAddressId(String bornAddressId) {
		this.bornAddressId = bornAddressId;
	}

	public LocalDateTime getBornTime() {
		return bornTime;
	}

	public void setBornTime(LocalDateTime bornTime) {
		this.bornTime = bornTime;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getBloodType() {
		return bloodType;
	}

	public void setBloodType(BigDecimal bloodType) {
		this.bloodType = bloodType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeAddressId() {
		return homeAddressId;
	}

	public void setHomeAddressId(String homeAddressId) {
		this.homeAddressId = homeAddressId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

}
