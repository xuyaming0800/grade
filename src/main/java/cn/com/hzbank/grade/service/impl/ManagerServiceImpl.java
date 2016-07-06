package cn.com.hzbank.grade.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.com.hzbank.grade.bean.*;
import cn.com.hzbank.grade.component.BusinessExceptionUtil;
import cn.com.hzbank.grade.dao.GradeBatchInfoDao;
import cn.com.hzbank.grade.dao.GradeItemInfoDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autonavi.online.framework.sharding.dao.DaoHelper;
import cn.com.hzbank.grade.constant.GradeConstant;
import cn.com.hzbank.grade.constant.GradeConstant.ORG_INFO_STATUS;
import cn.com.hzbank.grade.dao.OrgInfoDao;
import cn.com.hzbank.grade.dao.UserInfoDao;
import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.service.ManagerService;
import cn.com.hzbank.grade.web.bean.ResultEntity;
@Service
public class ManagerServiceImpl implements ManagerService {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private OrgInfoDao orgInfoDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private GradeItemInfoDao gradeItemInfoDao;
	@Autowired
	private GradeBatchInfoDao gradeBatchInfoDao;
	@Autowired
	private BusinessExceptionUtil businessExceptionUtil;

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
			throw businessExceptionUtil.getBusinessException(e);
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
			throw businessExceptionUtil.getBusinessException(e);
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
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public UserInfo addUserInfo(UserInfo userInfo) throws BusinessException {
		try {
			userInfo.setStatus(GradeConstant.USER_INFO_STATUS.USED.getCode());
			userInfoDao.addUserInfo(userInfo);
			Long id=DaoHelper.createPrimaryKey();
			userInfo.setId(id.toString());
			return userInfo;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
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
			throw businessExceptionUtil.getBusinessException(e);
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
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public OrgInfo getOrgInfoById(String id) throws BusinessException {
		try {
			OrgInfo info=(OrgInfo)orgInfoDao.getOrgInfoById(GradeConstant.getSingleDataSourceKey(), Long.valueOf(id));
			return info;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public OrgInfo removeOrgInfo(OrgInfo orgInfo) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			orgInfoDao.deleteOrgInfo(GradeConstant.getSingleDataSourceKey(), orgInfo);
			return orgInfo;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public OrgInfo updateOrgInfo(OrgInfo orgInfo) throws BusinessException {
		try {
			orgInfoDao.updateOrgInfo(GradeConstant.getSingleDataSourceKey(), orgInfo);
			return orgInfo;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}
	@Override
	public UserInfo getUserInfoById(String id)throws BusinessException{
		try {
			UserInfo info=new UserInfo();
			info.setId(id);
			info=(UserInfo)userInfoDao.getUserInfoById(info);
			return info;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public UserInfo updateUserInfo(UserInfo userInfo)throws BusinessException{
		try {
			userInfoDao.updateUserInfo(userInfo);
			return userInfo;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}
	@Override
	public UserInfo removeUserInfo(UserInfo userInfo)throws BusinessException{
		try {
			userInfoDao.deleteUserInfo(userInfo);
			return userInfo;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}


	@Override
	public ResultEntity queryItemInfo(Integer pageNo, Integer pageSize) throws BusinessException {
		try {
			Integer start=(pageNo-1)*pageSize;
			List<GradeItemInfo> list=(List<GradeItemInfo>)gradeItemInfoDao.getGradeItemInfoByPage(GradeConstant.getSingleDataSourceKey(), start, pageSize);
			if (list == null)
				list = new ArrayList<GradeItemInfo>();
			ResultEntity entity = new ResultEntity();
			entity.setResult(list);
			entity.setTotalCount(((Long) gradeItemInfoDao
					.getGradeItemInfoByPageCount(GradeConstant
							.getSingleDataSourceKey())).toString());
			return entity;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public ResultEntity queryBatchInfo(Integer pageNo, Integer pageSize) throws BusinessException {
		try {
			Integer start=(pageNo-1)*pageSize;
			List<GradeBatchInfo> list=(List<GradeBatchInfo>)gradeBatchInfoDao.getGradeBatchInfoByPage(GradeConstant.getSingleDataSourceKey(), start, pageSize);
			if (list == null)
				list = new ArrayList<GradeBatchInfo>();
			ResultEntity entity = new ResultEntity();
			entity.setResult(list);
			entity.setTotalCount(((Long) gradeBatchInfoDao
					.getGradeBatchInfoByPageCount(GradeConstant
							.getSingleDataSourceKey())).toString());
			return entity;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public GradeItemInfo getItemInfoById(String id) throws BusinessException {
		try {
			GradeItemInfo info=(GradeItemInfo)gradeItemInfoDao.getGradeItemInfoById(GradeConstant.getSingleDataSourceKey(),id);
			return info;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public GradeBatchInfo getBatchInfoById(String id) throws BusinessException {
		try {
			GradeBatchInfo info=(GradeBatchInfo)gradeBatchInfoDao.getGradeBatchInfoById(GradeConstant.getSingleDataSourceKey(),id);
			return info;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public GradeItemInfo addItemInfo(GradeItemInfo itemInfo) throws BusinessException {
		try {
			itemInfo.setStatus(GradeConstant.GRADE_ITEM_INFO_STATUS.USED.getCode());
			gradeItemInfoDao.addGradeItemInfo(itemInfo);
			Long id=DaoHelper.createPrimaryKey();
			itemInfo.setId(id.toString());
			return itemInfo;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public GradeBatchInfo addBatchInfo(GradeBatchInfo batchInfo) throws BusinessException {
		try {
			batchInfo.setStatus(GradeConstant.BATCH_INFO_STATUS.OPEN.getCode());
			gradeBatchInfoDao.addGradeBatchInfo(batchInfo);
			//获取所有的测评项目
			Long id=DaoHelper.createPrimaryKey();
			List<GradeItemInfo> l1=(List<GradeItemInfo>)gradeItemInfoDao.getGradeItemInfo(GradeConstant.getSingleDataSourceKey());
			List<GradeBatchItem> l2=new ArrayList<GradeBatchItem>();
			for(GradeItemInfo info:l1){
				GradeBatchItem bi=new GradeBatchItem();
				bi.setStatus(0);
				bi.setBatchId(id.toString());
				bi.setItemId(info.getId());
				l2.add(bi);
			}
			gradeBatchInfoDao.addGradeBatchItem(GradeConstant.getSingleDataSourceKey(),l2);
			batchInfo.setId(id.toString());
			return batchInfo;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public GradeItemInfo updateItemInfo(GradeItemInfo itemInfo) throws BusinessException {
		try {
			gradeItemInfoDao.updateGradeItemInfo(itemInfo);
			return itemInfo;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public GradeItemInfo removeItemInfo(GradeItemInfo itemInfo) throws BusinessException {
		try {
			gradeItemInfoDao.removeGradeItemInfo(itemInfo);
			return itemInfo;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}

	@Override
	public GradeBatchInfo updateBatchInfo(GradeBatchInfo batchInfo) throws BusinessException {
		try {
			gradeBatchInfoDao.updateGradeBatchInfo(batchInfo);
			return batchInfo;
		} catch (Exception e) {
			throw businessExceptionUtil.getBusinessException(e);
		}
	}
}
