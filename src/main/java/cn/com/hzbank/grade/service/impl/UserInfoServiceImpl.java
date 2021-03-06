package cn.com.hzbank.grade.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.com.hzbank.grade.component.BusinessExceptionUtil;
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
	@Autowired
	private BusinessExceptionUtil businessExceptionUtil;

	@Override
	public UserInfo checkUserLogin(UserInfo userInfo) throws BusinessException {
		userInfo.setDsKey(GradeConstant.getSingleDataSourceKey());
		try {
			UserInfo info=(UserInfo)userInfoDao.getUserInfoByUid(userInfo);
			if(info==null){
				BusinessException e=new BusinessException(BusinessExceptionEnum.USER_IS_NOT_FOUND);
				throw e;
			}
			if(!info.getUserPass().equals(userInfo.getUserPass())){
				return null;
			}
			return info;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getUserInfoByOrg(UserInfo userInfo) throws BusinessException {
		try {
			List<UserInfo> l=(List<UserInfo>)userInfoDao.getUserInfoByOrgId(userInfo);
			if(l==null){
				l=new ArrayList<UserInfo>();
			}
			return l;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
			
		}
	}

}
