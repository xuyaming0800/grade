package cn.com.hzbank.grade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hzbank.grade.bean.GradeBatchInfo;
import cn.com.hzbank.grade.bean.GradeItemInfo;
import cn.com.hzbank.grade.bean.GradeUserInfo;
import cn.com.hzbank.grade.bean.UserInfo;
import cn.com.hzbank.grade.constant.GradeConstant;
import cn.com.hzbank.grade.dao.GradeBatchInfoDao;
import cn.com.hzbank.grade.dao.GradeItemInfoDao;
import cn.com.hzbank.grade.dao.GradeUserInfoDao;
import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.exception.BusinessExceptionEnum;
import cn.com.hzbank.grade.service.GradeService;
import cn.com.hzbank.grade.web.bean.ResultEntity;

@Service
public class GradeServiceImpl implements GradeService {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private GradeBatchInfoDao gradeBatchInfoDao;
	@Autowired
	private GradeItemInfoDao gradeItemInfoDao;
	@Autowired
	private GradeUserInfoDao gradeUserInfoDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<GradeBatchInfo> getOpenBatchInfo() throws BusinessException {
		try {
			List<GradeBatchInfo> list = (List<GradeBatchInfo>) gradeBatchInfoDao
					.getAllOpenBatchInfo(GradeConstant.getSingleDataSourceKey());
			if (list == null)
				list = new ArrayList<GradeBatchInfo>();
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			BusinessException e1 = new BusinessException(
					BusinessExceptionEnum.SYSTEM_ERROR);
			throw e1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultEntity getOpenBatchInfoByPage(Integer pageNum, Integer pageSize)
			throws BusinessException {
		try {
			Integer start = (pageNum - 1) * pageSize;
			List<GradeBatchInfo> list = (List<GradeBatchInfo>) gradeBatchInfoDao
					.getOpenBatchInfoByPage(
							GradeConstant.getSingleDataSourceKey(), start,
							pageSize);
			if (list == null)
				list = new ArrayList<GradeBatchInfo>();
			ResultEntity entity = new ResultEntity();
			entity.setResult(list);
			entity.setTotalCount(((Long) gradeBatchInfoDao
					.getOpenBatchInfoCount(GradeConstant
							.getSingleDataSourceKey())).toString());
			return entity;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			BusinessException e1 = new BusinessException(
					BusinessExceptionEnum.SYSTEM_ERROR);
			throw e1;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResultEntity getOpenBatchInfoByPage(String userId,Integer pageNum, Integer pageSize)
			throws BusinessException {
		try {
			Integer start = (pageNum - 1) * pageSize;
			List<GradeBatchInfo> list = (List<GradeBatchInfo>) gradeBatchInfoDao
					.getOpenBatchInfoByPage(
							GradeConstant.getSingleDataSourceKey(),Long.valueOf(userId), start,
							pageSize);
			if (list == null)
				list = new ArrayList<GradeBatchInfo>();
			ResultEntity entity = new ResultEntity();
			entity.setResult(list);
			entity.setTotalCount(((Long) gradeBatchInfoDao
					.getOpenBatchInfoCount(GradeConstant
							.getSingleDataSourceKey())).toString());
			return entity;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			BusinessException e1 = new BusinessException(
					BusinessExceptionEnum.SYSTEM_ERROR);
			throw e1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GradeItemInfo> getGradeItemInfoByBatch(String batchId)
			throws BusinessException {
		try {
			List<GradeItemInfo> list = (List<GradeItemInfo>) gradeItemInfoDao
					.getGradeItemInfoByBatchId(
							GradeConstant.getSingleDataSourceKey(), Long.valueOf(batchId));
			if (list == null)
				list = new ArrayList<GradeItemInfo>();
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			BusinessException e1 = new BusinessException(
					BusinessExceptionEnum.SYSTEM_ERROR);
			throw e1;
		}
	}

	@Override
	public void addGraadUserInfos(List<GradeUserInfo> list,UserInfo info,String batchId)
			throws BusinessException {
		try {
			//检测是否已经提交过
			GradeUserInfo _info=new GradeUserInfo();
			_info.setBatchId(batchId);
			_info.setOpUserId(info.getId());
			_info.setOrgId(info.getOrgId());
			Integer count=(Integer)gradeUserInfoDao.getGradeUserInfoCountByOp(_info);
			if(count>0){
				BusinessException e=new BusinessException(BusinessExceptionEnum.GRADE_USER_IS_SUBMIT);
				throw e;
			}
			//批量入库
			gradeUserInfoDao.addGradeUserInfos(list);
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
	public boolean checkGradeUserInfoSubmit(UserInfo info, String batchId)
			throws BusinessException {
		try {
			GradeUserInfo _info=new GradeUserInfo();
			_info.setBatchId(batchId);
			_info.setOpUserId(info.getId());
			_info.setOrgId(info.getOrgId());
			Integer count=(Integer)gradeUserInfoDao.getGradeUserInfoCountByOp(_info);
			if(count>0){
				BusinessException e=new BusinessException(BusinessExceptionEnum.GRADE_USER_IS_SUBMIT);
				throw e;
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
