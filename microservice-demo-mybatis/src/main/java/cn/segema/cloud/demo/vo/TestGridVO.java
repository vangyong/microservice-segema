package cn.segema.cloud.demo.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试用户VO
 * 
 * @author wangyong
 *
 */
public class TestGridVO implements Serializable {

	List<TestEmployeeVO> employees = new ArrayList<TestEmployeeVO>();

	public List<TestEmployeeVO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<TestEmployeeVO> employees) {
		this.employees = employees;
	}

}
