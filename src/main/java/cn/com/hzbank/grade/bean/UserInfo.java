package cn.com.hzbank.grade.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户信息类
 * 
 * @author Xuyaming
 *
 */
public class UserInfo extends GradeAbstractBean implements Serializable {

	private static final long serialVersionUID = -8479697070512574187L;
	@NotNull(message = "{user.orgId.null}", groups = { AddUserInfo.class })
	@NotEmpty(message = "{user.orgId.null}", groups = { AddUserInfo.class })
	private Long orgId;
	@NotNull(message = "{user.userName.null}", groups = { AddUserInfo.class })
	@NotEmpty(message = "{user.userName.null}", groups = { AddUserInfo.class })
	private String userName;
	@NotNull(message = "{user.uid.null}", groups = { LoginCheck.class,
			AddUserInfo.class })
	@NotEmpty(message = "{user.uid.null}", groups = { LoginCheck.class,
			AddUserInfo.class })
	private String uid;
	@NotNull(message = "{user.userPass.null}", groups = { LoginCheck.class,
			AddUserInfo.class })
	@NotEmpty(message = "{user.userPass.null}", groups = { LoginCheck.class,
			AddUserInfo.class })
	private String userPass;
	@NotNull(message = "{user.userType.null}", groups = { AddUserInfo.class })
	@NotEmpty(message = "{user.userType.null}", groups = { AddUserInfo.class })
	private String userType;

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

	public interface LoginCheck {
	}

	public interface AddUserInfo {
	}

}
