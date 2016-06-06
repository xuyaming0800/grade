package cn.com.hzbank.grade.dao;

import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.entry.aspect.annotation.Author;
import autonavi.online.framework.sharding.entry.aspect.annotation.Select;
import autonavi.online.framework.sharding.entry.aspect.annotation.SingleDataSource;
import autonavi.online.framework.sharding.entry.aspect.annotation.SqlParameter;
import autonavi.online.framework.sharding.entry.entity.CollectionType;
import cn.com.hzbank.grade.bean.GradeBatchInfo;
import cn.com.hzbank.grade.constant.GradeConstant.BATCH_INFO_STATUS;

@Repository
public class GradeBatchInfoDao {
	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = GradeBatchInfo.class)
	public Object getAllOpenBatchInfo(@SqlParameter("dsKey") Integer dsKey) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,batch_name batchName,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from grade_batch_info ");
		sql.append(" where status=");
		sql.append(BATCH_INFO_STATUS.OPEN.getCode());
		sql.append(" order by id asc ");
		return sql.toString();
	}

	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = GradeBatchInfo.class)
	public Object getOpenBatchInfoByPage(@SqlParameter("dsKey") Integer dsKey,
			@SqlParameter("start") Integer start,
			@SqlParameter("size") Integer size) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,batch_name batchName,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from grade_batch_info ");
		sql.append(" where status=");
		sql.append(BATCH_INFO_STATUS.OPEN.getCode());
		sql.append(" order by id desc ");
		sql.append(" limit #{start},#{size} ");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.column, resultType = Long.class)
	public Object getOpenBatchInfoCount(@SqlParameter("dsKey") Integer dsKey) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(0) ");
		sql.append(" from grade_batch_info ");
		sql.append(" where status=");
		sql.append(BATCH_INFO_STATUS.OPEN.getCode());
		return sql.toString();
	}
}
