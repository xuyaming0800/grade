package cn.com.hzbank.grade.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hzbank.grade.bean.UserInfo;
import cn.com.hzbank.grade.constant.GradeConstant;
import cn.com.hzbank.grade.dao.UserInfoDao;
import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.exception.BusinessExceptionEnum;
import cn.com.hzbank.grade.service.UserInfoService;
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	public boolean checkUserLogin(UserInfo userInfo) throws BusinessException {
		userInfo.setDsKey(GradeConstant.getSingleDataSourceKey());
		try {
			UserInfo info=(UserInfo)userInfoDao.getUserInfoByUid(userInfo);
			if(info==null){
				BusinessException e=new BusinessException(BusinessExceptionEnum.USER_IS_NOT_FOUND);
				throw e;
			}
			if(!info.getUserPass().equals(userInfo.getUserPass())){
				return false;
			}
		} catch (Exception e) {
			if(!(e instanceof BusinessException)){
				logger.error(e.getMessage(),e);
				BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
				throw e1;
			}else{
				throw (BusinessException)e;
			}
			
		}
		return true;
	}

}
