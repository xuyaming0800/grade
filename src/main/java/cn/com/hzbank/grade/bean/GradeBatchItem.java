package cn.com.hzbank.grade.bean;

import java.io.Serializable;

public class GradeBatchItem extends GradeAbstractBean implements Serializable {

	private static final long serialVersionUID = -8032141561451322762L;
	private String batchId;
	private String itemId;

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}
