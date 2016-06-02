package cn.com.hzbank.grade.bean;

import java.io.Serializable;

public class GradeOrgInfo extends GradeAbstractBean implements Serializable {
	private static final long serialVersionUID = 5672162333202873781L;
	private Long userId;
	private Long gradeId;
	private Long batchId;
	private Long orgId;
	private Double score;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
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

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

}
