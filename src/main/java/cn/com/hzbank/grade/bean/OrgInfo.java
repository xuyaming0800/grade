package cn.com.hzbank.grade.bean;

import java.io.Serializable;

/**
 * 组织部门类
 * 
 * @author Xuyaming
 *
 */
public class OrgInfo extends GradeAbstractBean implements Serializable {

	private static final long serialVersionUID = 3475828549111694395L;
	private String orgName;
	private String orgDesc;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

}
