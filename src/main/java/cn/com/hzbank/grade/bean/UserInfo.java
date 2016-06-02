package cn.com.hzbank.grade.bean;

import java.io.Serializable;

/**
 * 用户信息类
 * 
 * @author Xuyaming
 *
 */
public class UserInfo extends GradeAbstractBean implements Serializable {

	private static final long serialVersionUID = -8479697070512574187L;
	private Long orgId;
	private String userName;
	private String uid;
	private String userPass;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

}
