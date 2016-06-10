package cn.com.hzbank.grade.bean;

import java.io.Serializable;

public class GradeUserInfo extends GradeAbstractBean implements Serializable {
	private static final long serialVersionUID = 5672162333202873781L;
	private Long opUserId;
	private Long userId;
	private Long itemId;
	private Long batchId;
	private Long orgId;
	private Integer grade;

	public Long getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(Long opUserId) {
		this.opUserId = opUserId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

}
