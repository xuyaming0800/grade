package cn.com.hzbank.grade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hzbank.grade.bean.GradeBatchInfo;
import cn.com.hzbank.grade.constant.GradeConstant;
import cn.com.hzbank.grade.dao.GradeBatchInfoDao;
import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.exception.BusinessExceptionEnum;
import cn.com.hzbank.grade.service.GradeService;
@Service
public class GradeServiceImpl implements GradeService {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private GradeBatchInfoDao gradeBatchInfoDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<GradeBatchInfo> getOpenBatchInfo() throws BusinessException {
		try {
			List<GradeBatchInfo> list=(List<GradeBatchInfo>)gradeBatchInfoDao.getAllOpenBatchInfo(GradeConstant.getSingleDataSourceKey());
			if(list==null)list=new ArrayList<GradeBatchInfo>();
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			throw e1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GradeBatchInfo> getOpenBatchInfoByPage(Integer pageNum,
			Integer pageSize) throws BusinessException {
		try {
			Integer start=(pageNum-1)*pageSize;
			List<GradeBatchInfo> list=(List<GradeBatchInfo>)gradeBatchInfoDao.getOpenBatchInfoByPage(GradeConstant.getSingleDataSourceKey(),start,pageSize);
			if(list==null)list=new ArrayList<GradeBatchInfo>();
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			throw e1;
		}
	}

}
