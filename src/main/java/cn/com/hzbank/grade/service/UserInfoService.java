package cn.com.hzbank.grade.service;

import java.util.List;

import cn.com.hzbank.grade.bean.UserInfo;
import cn.com.hzbank.grade.exception.BusinessException;

/**
 * 用户信息相关服务
 * @author Xuyaming
 *
 */
public interface UserInfoService {
	/**
	 * 检测用户登录
	 * @param userInfo
	 * @return
	 * @throws BusinessException
	 */
	public UserInfo checkUserLogin(UserInfo userInfo)throws BusinessException;
	/**
	 * 根据部门获取用户信息
	 * @param orgId
	 * @return
	 * @throws BusinessException
	 */
	public List<UserInfo> getUserInfoByOrg(UserInfo userInfo)throws BusinessException;

}
