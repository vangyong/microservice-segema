package cn.segema.cloud.demo.vo;

import java.io.Serializable;
/**
 * 测试用户VO
 * 
 * @author wangyong
 *
 */
public class TestEmployeeVO implements Serializable {

	private static final long serialVersionUID = 3336026114894190728L;

	private String ID;
	private String DepartmentID;
	private String RealName;
	private String DepartmentName;
	private String Sex;
	private String Age;
	private String IncomeDay;
	private String Phone;
	private String Address;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDepartmentID() {
		return DepartmentID;
	}

	public void setDepartmentID(String departmentID) {
		DepartmentID = departmentID;
	}

	public String getRealName() {
		return RealName;
	}

	public void setRealName(String realName) {
		RealName = realName;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getIncomeDay() {
		return IncomeDay;
	}

	public void setIncomeDay(String incomeDay) {
		IncomeDay = incomeDay;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

}
