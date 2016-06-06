package cn.com.hzbank.grade.bean;

import cn.com.hzbank.grade.constant.GradeConstant;

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
	private Integer dsKey=GradeConstant.getSingleDataSourceKey();

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final Long getCreateTime() {
		return createTime;
	}

	public final void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	

	public Integer getDsKey() {
		return dsKey;
	}

	public void setDsKey(Integer dsKey) {
		this.dsKey = dsKey;
	}

	public final Long getUpdateTime() {
		return updateTime;
	}

	public final void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public final Integer getStatus() {
		return status;
	}

	public final void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
