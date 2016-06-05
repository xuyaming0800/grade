package cn.com.hzbank.grade.bean;

import java.io.Serializable;

/**
 * 测评批次类
 * 
 * @author Xuyaming
 *
 */
public class GradeBatchInfo extends GradeAbstractBean implements Serializable {

	private static final long serialVersionUID = 4568205435959754857L;
	private String batchName;

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

}
