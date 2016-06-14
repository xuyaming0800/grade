package cn.com.hzbank.grade.bean;

import java.io.Serializable;

public class GradeOrgInfo extends GradeAbstractBean implements Serializable {
	private static final long serialVersionUID = 5672162333202873781L;
	private String userId;
	private String gradeId;
	private String batchId;
	private String orgId;
	private Double score;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
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

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

}
