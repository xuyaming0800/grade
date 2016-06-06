package cn.com.hzbank.grade.service;

import java.util.List;

import cn.com.hzbank.grade.bean.GradeBatchInfo;
import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.web.bean.ResultEntity;

/**
 * 评分相关服务接口
 * @author Xuyaming
 *
 */
public interface GradeService {
	/**
	 * 获取所有开放的评分
	 * @return
	 * @throws BusinessException
	 */
	public List<GradeBatchInfo> getOpenBatchInfo()throws BusinessException;
	/**
	 * 分页获取开发的评分
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public ResultEntity getOpenBatchInfoByPage(Integer pageNum,Integer pageSize)throws BusinessException;
}
