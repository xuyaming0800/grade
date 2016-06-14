package cn.com.hzbank.grade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autonavi.online.framework.sharding.dao.DaoHelper;
import cn.com.hzbank.grade.bean.OrgInfo;
import cn.com.hzbank.grade.bean.UserInfo;
import cn.com.hzbank.grade.constant.GradeConstant;
import cn.com.hzbank.grade.constant.GradeConstant.ORG_INFO_STATUS;
import cn.com.hzbank.grade.dao.OrgInfoDao;
import cn.com.hzbank.grade.dao.UserInfoDao;
import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.exception.BusinessExceptionEnum;
import cn.com.hzbank.grade.service.ManagerService;
import cn.com.hzbank.grade.web.bean.ResultEntity;
@Service
public class ManagerServiceImpl implements ManagerService {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private OrgInfoDao orgInfoDao;
	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	public OrgInfo addOrgInfo(OrgInfo orgInfo) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			orgInfo.setStatus(ORG_INFO_STATUS.USED.getCode());
			orgInfoDao.addOrgInfo(orgInfo);
			Long id=DaoHelper.createPrimaryKey();
			orgInfo.setId(id.toString());
			return orgInfo;
		} catch (Exception e) {
			if(!(e instanceof BusinessException)){
				logger.error(e.getMessage(),e);
				BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
				throw e1;
			}else{
				throw (BusinessException)e;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultEntity queryOrgInfoByName(String query, Integer pageNo,
			Integer pageSize) throws BusinessException {
		try {
			Integer start=(pageNo-1)*pageSize;
			query="%"+query+"%";
			List<OrgInfo> list=(List<OrgInfo>)orgInfoDao.getOrgInfoByName(GradeConstant.getSingleDataSourceKey(), query, start, pageSize);
			if (list == null)
				list = new ArrayList<OrgInfo>();
			ResultEntity entity = new ResultEntity();
			entity.setResult(list);
			entity.setTotalCount(((Long) orgInfoDao
					.getOrgInfoCountByName(GradeConstant
							.getSingleDataSourceKey(),query)).toString());
			return entity;
		} catch (Exception e) {
			if(!(e instanceof BusinessException)){
				logger.error(e.getMessage(),e);
				BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
				throw e1;
			}else{
				throw (BusinessException)e;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultEntity queryOrgInfo(Integer pageNo, Integer pageSize)
			throws BusinessException {
		try {
			Integer start=(pageNo-1)*pageSize;
			List<OrgInfo> list=(List<OrgInfo>)orgInfoDao.getOrgInfoByPage(GradeConstant.getSingleDataSourceKey(), start, pageSize);
			if (list == null)
				list = new ArrayList<OrgInfo>();
			ResultEntity entity = new ResultEntity();
			entity.setResult(list);
			entity.setTotalCount(((Long) orgInfoDao
					.getOrgInfoCountByPage(GradeConstant
							.getSingleDataSourceKey())).toString());
			return entity;
		} catch (Exception e) {
			if(!(e instanceof BusinessException)){
				logger.error(e.getMessage(),e);
				BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
				throw e1;
			}else{
				throw (BusinessException)e;
			}
		}
	}

	@Override
	public UserInfo addUserInfo(UserInfo userInfo) throws BusinessException {
		try {
			userInfoDao.addUserInfo(userInfo);
			Long id=DaoHelper.createPrimaryKey();
			userInfo.setId(id.toString());
			return userInfo;
		} catch (Exception e) {
			if(!(e instanceof BusinessException)){
				logger.error(e.getMessage(),e);
				BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
				throw e1;
			}else{
				throw (BusinessException)e;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultEntity queryUserInfoByName(String query, Integer pageNo,
			Integer pageSize) throws BusinessException {
		try {
			Integer start=(pageNo-1)*pageSize;
			query="%"+query+"%";
			List<UserInfo> list=(List<UserInfo>)userInfoDao.getUserInfoWithOrgByName(GradeConstant.getSingleDataSourceKey(), query, start, pageSize);
			if (list == null)
				list = new ArrayList<UserInfo>();
			ResultEntity entity = new ResultEntity();
			entity.setResult(list);
			entity.setTotalCount(((Long) userInfoDao
					.getUserInfoWithOrgCountByName(GradeConstant
							.getSingleDataSourceKey(),query)).toString());
			return entity;
		} catch (Exception e) {
			if(!(e instanceof BusinessException)){
				logger.error(e.getMessage(),e);
				BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
				throw e1;
			}else{
				throw (BusinessException)e;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultEntity queryUserInfo(Integer pageNo, Integer pageSize)
			throws BusinessException {
		try {
			Integer start=(pageNo-1)*pageSize;
			List<UserInfo> list=(List<UserInfo>)userInfoDao.getUserInfoWithOrgByPage(GradeConstant.getSingleDataSourceKey(), start, pageSize);
			if (list == null)
				list = new ArrayList<UserInfo>();
			ResultEntity entity = new ResultEntity();
			entity.setResult(list);
			entity.setTotalCount(((Long) userInfoDao
					.getUserInfoWithOrgCountByPage(GradeConstant
							.getSingleDataSourceKey())).toString());
			return entity;
		} catch (Exception e) {
			if(!(e instanceof BusinessException)){
				logger.error(e.getMessage(),e);
				BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
				throw e1;
			}else{
				throw (BusinessException)e;
			}
		}
	}

	@Override
	public OrgInfo getOrgInfoById(String id) throws BusinessException {
		try {
			OrgInfo info=(OrgInfo)orgInfoDao.getOrgInfoById(GradeConstant.getSingleDataSourceKey(), Long.valueOf(id));
			return info;
		} catch (Exception e) {
			if(!(e instanceof BusinessException)){
				logger.error(e.getMessage(),e);
				BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
				throw e1;
			}else{
				throw (BusinessException)e;
			}
		}
	}

	@Override
	public OrgInfo removeOrgInfo(OrgInfo orgInfo) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			orgInfoDao.deleteOrgInfo(GradeConstant.getSingleDataSourceKey(), orgInfo);
			return orgInfo;
		} catch (Exception e) {
			if(!(e instanceof BusinessException)){
				logger.error(e.getMessage(),e);
				BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
				throw e1;
			}else{
				throw (BusinessException)e;
			}
		}
	}

	@Override
	public OrgInfo updateOrgInfo(OrgInfo orgInfo) throws BusinessException {
		try {
			orgInfoDao.updateOrgInfo(GradeConstant.getSingleDataSourceKey(), orgInfo);
			return orgInfo;
		} catch (Exception e) {
			if(!(e instanceof BusinessException)){
				logger.error(e.getMessage(),e);
				BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
				throw e1;
			}else{
				throw (BusinessException)e;
			}
		}
	}

}
