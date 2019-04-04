package cn.segema.cloud.demo.domain;

/**
 * 用户人员关系
 * @author wangyong
 *
 */
public class DemoUserPersonal {
	private String userPersonalId;

	private DemoUser user;

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
