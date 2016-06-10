package cn.com.hzbank.grade.service;

import java.util.List;

import cn.com.hzbank.grade.bean.GradeBatchInfo;
import cn.com.hzbank.grade.bean.GradeItemInfo;
import cn.com.hzbank.grade.bean.GradeUserInfo;
import cn.com.hzbank.grade.bean.UserInfo;
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
	/**
	 * 根据评分活动ID获取评分项目
	 * @param batchId
	 * @return
	 * @throws BusinessException
	 */
	public List<GradeItemInfo> getGradeItemInfoByBatch(Long batchId)throws BusinessException;
	/**
	 * 添加用户评分结果
	 * @param list
	 * @param info
	 * @param batchId
	 * @throws BusinessException
	 */
	public void addGraadUserInfos(List<GradeUserInfo> list,UserInfo info,Long batchId)throws BusinessException;
	/**
	 * 检测用户是否已经提交过
	 * @param info
	 * @param batchId
	 * @return
	 * @throws BusinessException
	 */
	public boolean checkGradeUserInfoSubmit(UserInfo info,Long batchId)throws BusinessException;
}
