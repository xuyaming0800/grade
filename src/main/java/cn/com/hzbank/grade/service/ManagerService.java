package cn.com.hzbank.grade.service;

import cn.com.hzbank.grade.bean.OrgInfo;
import cn.com.hzbank.grade.bean.UserInfo;
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

}
