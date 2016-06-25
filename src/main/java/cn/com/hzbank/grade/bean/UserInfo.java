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
	@NotNull(message = "{user.orgId.null}", groups = { AddUserInfo.class,ModifyUserInfo.class })
	@NotEmpty(message = "{user.orgId.null}", groups = { AddUserInfo.class,ModifyUserInfo.class })
	private String orgId;
	@NotNull(message = "{user.userName.null}", groups = { AddUserInfo.class,ModifyUserInfo.class })
	@NotEmpty(message = "{user.userName.null}", groups = { AddUserInfo.class,ModifyUserInfo.class })
	private String userName;
	@NotNull(message = "{user.uid.null}", groups = { LoginCheck.class,
			AddUserInfo.class,ModifyUserInfo.class })
	@NotEmpty(message = "{user.uid.null}", groups = { LoginCheck.class,
			AddUserInfo.class,ModifyUserInfo.class })
	private String uid;
	@NotNull(message = "{user.userPass.null}", groups = { LoginCheck.class,
			AddUserInfo.class })
	@NotEmpty(message = "{user.userPass.null}", groups = { LoginCheck.class,
			AddUserInfo.class })
	private String userPass;
	@NotNull(message = "{user.userType.null}", groups = { AddUserInfo.class,ModifyUserInfo.class })
	private Integer userType;
	//额外扩展
	private String orgName;

	
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
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

	public interface ModifyUserInfo {
	}

}
