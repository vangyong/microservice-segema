package cn.segema.cloud.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "SYS_USER_PERSONAL")
@Entity
public class PersonalDepartment {
	@Id
	@Column(name = "PERSONALDEPARTMENTID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String PersonalDepartmentId;

	@OneToOne
	@JoinColumn(name = "PERSONALID")
	private Personal personal;

	@OneToOne
	@JoinColumn(name = "DEPARTMENTLID")
	private Department department;

	public String getPersonalDepartmentId() {
		return PersonalDepartmentId;
	}

	public void setPersonalDepartmentId(String personalDepartmentId) {
		PersonalDepartmentId = personalDepartmentId;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
