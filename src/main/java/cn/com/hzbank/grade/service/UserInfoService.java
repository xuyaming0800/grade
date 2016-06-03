package cn.com.hzbank.grade.service;

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
	public boolean checkUserLogin(UserInfo userInfo)throws BusinessException;

}
