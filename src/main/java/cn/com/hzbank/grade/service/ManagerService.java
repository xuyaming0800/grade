package cn.com.hzbank.grade.service;

import cn.com.hzbank.grade.bean.*;
import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.web.bean.ResultEntity;

public interface ManagerService {
	/**
	 * 添加组织信息
	 * @param orgInfo
	 * @return
	 * @throws BusinessException
	 */
	public OrgInfo addOrgInfo(OrgInfo orgInfo)throws BusinessException;
	/**
	 * 根据ID获取组织
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public OrgInfo getOrgInfoById(String id)throws BusinessException;
	/**
	 * 删除组织（逻辑）
	 * @param orgInfo
	 * @return
	 * @throws BusinessException
	 */
	public OrgInfo removeOrgInfo(OrgInfo orgInfo)throws BusinessException;
	/**
	 * 更新组织
	 * @param orgInfo
	 * @return
	 * @throws BusinessException
	 */
	public OrgInfo updateOrgInfo(OrgInfo orgInfo)throws BusinessException;
	/**
	 * 根据名称查询组织
	 * @param query
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public ResultEntity queryOrgInfoByName(String query,Integer pageNo,Integer pageSize)throws BusinessException;
	/**
	 * 查询组织
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public ResultEntity queryOrgInfo(Integer pageNo,Integer pageSize)throws BusinessException;
	/**
	 * 添加用户
	 * @param userInfo
	 * @return
	 * @throws BusinessException
	 */
	public UserInfo addUserInfo(UserInfo userInfo)throws BusinessException;
	/**
	 * 根据姓名查询用户
	 * @param query
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public ResultEntity queryUserInfoByName(String query,Integer pageNo,Integer pageSize)throws BusinessException;
	/**
	 * 查询用户
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public ResultEntity queryUserInfo(Integer pageNo,Integer pageSize)throws BusinessException;

	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 * @throws BusinessException
     */
	public UserInfo getUserInfoById(String id)throws BusinessException;

	/**
	 * 更新用户
	 * @param userInfo
	 * @return
	 * @throws BusinessException
     */
	public UserInfo updateUserInfo(UserInfo userInfo)throws BusinessException;

	/**
	 * 删除用户
	 * @param userInfo
	 * @return
	 * @throws BusinessException
     */
	public UserInfo removeUserInfo(UserInfo userInfo)throws BusinessException;
	/**
	 * 查询测评项目
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public ResultEntity queryItemInfo(Integer pageNo,Integer pageSize)throws BusinessException;

	/**
	 * 查询活动
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
     */
	public ResultEntity queryBatchInfo(Integer pageNo,Integer pageSize)throws BusinessException;

	/**
	 * 根据Id获取测评项目
	 * @param id
	 * @return
	 * @throws BusinessException
     */
	public GradeItemInfo getItemInfoById(String id)throws BusinessException;

	/**
	 * 根据ID获取活动
	 * @param id
	 * @return
	 * @throws BusinessException
     */
	public GradeBatchInfo getBatchInfoById(String id)throws BusinessException;

	/**
	 * 添加测评项目
	 * @param itemInfo
	 * @return
	 * @throws BusinessException
     */
	public GradeItemInfo addItemInfo(GradeItemInfo itemInfo)throws BusinessException;

	/**
	 * 添加活动
	 * @param batchInfo
	 * @return
	 * @throws BusinessException
     */
	public GradeBatchInfo addBatchInfo(GradeBatchInfo batchInfo)throws BusinessException;

	/**
	 * 更新测评项目
	 * @param itemInfo
	 * @return
	 * @throws BusinessException
     */
	public GradeItemInfo updateItemInfo(GradeItemInfo itemInfo)throws BusinessException;

	/**
	 * 删除测评项目
	 * @param itemInfo
	 * @return
	 * @throws BusinessException
	 */
	public GradeItemInfo removeItemInfo(GradeItemInfo itemInfo)throws BusinessException;

	/**
	 * 更新活动信息
	 * @param atchInfo
	 * @return
	 * @throws BusinessException
     */
	public GradeBatchInfo updateBatchInfo(GradeBatchInfo atchInfo)throws BusinessException;

	/**
	 * 计算分数
	 * @param orgId
	 * @param batchId
	 * @return
	 * @throws BusinessException
     */
	public ResultEntity getUserScore(String orgId,String batchId)throws BusinessException;

	/**
	 *
	 * @param orgId
	 * @param batchId
	 * @return
	 * @throws BusinessException
     */
	public ResultEntity getUserScoreAvg(String orgId,String batchId)throws BusinessException;
}
