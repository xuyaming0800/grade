package cn.com.hzbank.grade.bean;

/**
 * 评分系统抽象基类
 * 
 * @author Xuyaming
 *
 */
public abstract class GradeAbstractBean {
	private Long id;
	private Long createTime;
	private Long updateTime;
	private Integer status;

	protected final Long getId() {
		return id;
	}

	protected final void setId(Long id) {
		this.id = id;
	}

	protected final Long getCreateTime() {
		return createTime;
	}

	protected final void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	protected final Long getUpdateTime() {
		return updateTime;
	}

	protected final void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	protected final Integer getStatus() {
		return status;
	}

	protected final void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
