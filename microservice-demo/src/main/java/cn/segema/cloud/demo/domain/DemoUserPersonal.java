package cn.segema.cloud.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户人员关系
 * @author wangyong
 *
 */
@Table(name = "DEMO_USER_PERSONAL")
@Entity
public class DemoUserPersonal {
	@Id
	@Column(name = "USERPERSONALID")
	private String userPersonalId;

	@OneToOne
	@JoinColumn(name = "USERID")
	private DemoUser user;

	@OneToOne
	@JoinColumn(name = "PERSONALID")
	private DemoPersonal personal;

	public String getUserPersonalId() {
		return userPersonalId;
	}

	public void setUserPersonalId(String userPersonalId) {
		this.userPersonalId = userPersonalId;
	}

	public DemoUser getUser() {
		return user;
	}

	public void setUser(DemoUser user) {
		this.user = user;
	}

	public DemoPersonal getPersonal() {
		return personal;
	}

	public void setPersonal(DemoPersonal personal) {
		this.personal = personal;
	}

}
