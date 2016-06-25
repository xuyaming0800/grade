package cn.com.hzbank.grade.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 测评批次类
 * 
 * @author Xuyaming
 *
 */
public class GradeBatchInfo extends GradeAbstractBean implements Serializable {

	private static final long serialVersionUID = 4568205435959754857L;
	@NotNull(message = "{batch.batchName.null}", groups = { AddGradeBatchInfo.class})
	@NotEmpty(message = "{batch.batchName.null}", groups = { AddGradeBatchInfo.class })
	private String batchName;
	private String userId;
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public interface AddGradeBatchInfo{

	}

}
