package cn.segema.cloud.common.page;

import java.io.Serializable;

/**
 * 分页参数VO
 * 
 * @author wangyong
 *
 */
public class PagerParamVO implements Serializable {

	private static final long serialVersionUID = -1361479156610385624L;
	//当前页码
	private int curr;
	//每页数量
	private int nums;
	//排序字段
	private String order;

	public int getCurr() {
		return curr;
	}

	public void setCurr(int curr) {
		this.curr = curr;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	
	
}
