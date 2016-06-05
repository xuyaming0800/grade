package cn.com.hzbank.grade.bean;

import java.io.Serializable;

public class GradeBatchItem extends GradeAbstractBean implements Serializable {

	private static final long serialVersionUID = -8032141561451322762L;
	private Long batchId;
	private Long itemId;

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}
