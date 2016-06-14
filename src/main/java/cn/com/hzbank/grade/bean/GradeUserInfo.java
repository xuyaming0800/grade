package cn.com.hzbank.grade.bean;

import java.io.Serializable;

public class GradeUserInfo extends GradeAbstractBean implements Serializable {
	private static final long serialVersionUID = 5672162333202873781L;
	private String opUserId;
	private String userId;
	private String itemId;
	private String batchId;
	private String orgId;
	private Integer grade;
	public String getOpUserId() {
		return opUserId;
	}
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	

}
