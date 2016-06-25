package cn.com.hzbank.grade.dao;

import autonavi.online.framework.sharding.dao.constant.ReservedWord;
import autonavi.online.framework.sharding.entry.aspect.annotation.*;
import cn.com.hzbank.grade.bean.GradeBatchItem;
import cn.com.hzbank.grade.bean.GradeItemInfo;
import cn.com.hzbank.grade.constant.GradeConstant;
import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.entry.entity.CollectionType;
import cn.com.hzbank.grade.bean.GradeBatchInfo;
import cn.com.hzbank.grade.constant.GradeConstant.BATCH_INFO_STATUS;

import java.util.List;

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
	@Select(collectionType = CollectionType.beanList, resultType = GradeBatchInfo.class)
	public Object getOpenBatchInfoByPage(@SqlParameter("dsKey") Integer dsKey,
			@SqlParameter("userId") Long userId,
			@SqlParameter("start") Integer start,
			@SqlParameter("size") Integer size) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id,batchName,createTime,updateTime,a.status,b.user_id userId from  (");
		sql.append("select id,batch_name batchName,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from grade_batch_info ");
		sql.append(" where status=");
		sql.append(BATCH_INFO_STATUS.OPEN.getCode());
		sql.append(" order by id desc ");
		sql.append(" limit #{start},#{size} ");
		sql.append(") a ");
		sql.append(" left join grade_user_flag b on a.id=b.batch_id and b.user_id=#{userId} order by a.id desc ");
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

	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = GradeBatchInfo.class)
	public Object getGradeBatchInfoByPage(@SqlParameter("dsKey") Integer dsKey,
										 @SqlParameter("start") Integer start, @SqlParameter("size") Integer size) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,batch_name batchName,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from grade_batch_info ");
		sql.append(" order by id desc ");
		sql.append(" limit #{start},#{size} ");
		return sql.toString();
	}

	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.column, resultType = Long.class)
	public Object getGradeBatchInfoByPageCount(@SqlParameter("dsKey") Integer dsKey) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(0) ");
		sql.append(" from grade_batch_info ");
		sql.append(" where status= ");
		sql.append(GradeConstant.GRADE_ITEM_INFO_STATUS.USED.getCode());
		return sql.toString();
	}


	@Author("yaming.xu")
	@SingleDataSource(keyName="info.dsKey")
	@Insert
	public Object addGradeBatchInfo(@SqlParameter("info") GradeBatchInfo info){
		StringBuffer sql = new StringBuffer();
		sql.append("insert into grade_batch_info(id,batch_name,CREATE_TIME,UPDATE_TIME,status) ");
		sql.append(" values(");
		sql.append(" #{");
		sql.append(ReservedWord.snowflake);
		sql.append("},");
		sql.append("#{info.batchName},UNIX_TIMESTAMP(now()),UNIX_TIMESTAMP(now()),#{info.status})");
		return sql.toString();
	}

	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.bean, resultType = GradeBatchInfo.class)
	public Object getGradeBatchInfoById(@SqlParameter("dsKey") Integer dsKey,@SqlParameter("id") String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,batch_name batchName,CREATE_TIME createTime,UPDATE_TIME updateTime,status  ");
		sql.append(" from grade_batch_info ");
		sql.append(" where id=#{id} ");
		return sql.toString();
	}

	@Author("yaming.xu")
	@SingleDataSource(keyName="dsKey")
	@Insert
	public Object addGradeBatchItem(@SqlParameter("dsKey") Integer dsKey,@SqlParameter("list") List<GradeBatchItem> list){
		StringBuffer sql = new StringBuffer();
		sql.append("insert into grade_batch_item(id,batch_id,item_id,CREATE_TIME,UPDATE_TIME,status) ");
		sql.append(" values(");
		sql.append(" #{");
		sql.append(ReservedWord.snowflake);
		sql.append("},");
		sql.append("#{list."+ReservedWord.index+".batchId},#{list."+ReservedWord.index+".itemId},UNIX_TIMESTAMP(now()),UNIX_TIMESTAMP(now()),#{list."+ReservedWord.index+".status})");
		return sql.toString();
	}

	@Author("yaming.xu")
	@SingleDataSource(keyName="info.dsKey")
	@Update
	public Object updateGradeBatchInfo(@SqlParameter("info") GradeBatchInfo info){
		StringBuffer sql = new StringBuffer();
		sql.append("update grade_batch_info set batch_name=#{info.batchName}, ");
		sql.append(" update_time= UNIX_TIMESTAMP(now()) ");
		sql.append(" where id=#{info.id}");
		return sql.toString();
	}
}
