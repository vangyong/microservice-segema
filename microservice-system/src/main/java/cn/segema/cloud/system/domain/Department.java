package cn.segema.cloud.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "SYS_DEPARTMENT")
@Entity
public class Department {
	@Id
	@Column(name = "DEPARTMENTID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String departmentId;

	@Column(name = "DEPARTMENTNAME")
	private String departmentName;

	@Column(name = "DEPARTMENTCODE")
	private String departmentCode;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PARENTID")
	private String parentId;

	@Column(name = "TYPE")
	private Integer type;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
