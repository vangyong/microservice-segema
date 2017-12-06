package cn.segema.cloud.wemall.vo;

import java.util.List;

public class TreeVO {

	private String id;

	private String text;

	private List<TreeVO> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TreeVO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeVO> children) {
		this.children = children;
	}

	

}
