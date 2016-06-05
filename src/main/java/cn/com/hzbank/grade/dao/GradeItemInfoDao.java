package cn.com.hzbank.grade.dao;

import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.entry.aspect.annotation.Author;
import autonavi.online.framework.sharding.entry.aspect.annotation.Select;
import autonavi.online.framework.sharding.entry.aspect.annotation.SingleDataSource;
import autonavi.online.framework.sharding.entry.aspect.annotation.SqlParameter;
import autonavi.online.framework.sharding.entry.entity.CollectionType;
import cn.com.hzbank.grade.bean.GradeItemInfo;

@Repository
public class GradeItemInfoDao {
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="info.dsKey")
	@Select(collectionType = CollectionType.bean, resultType = GradeItemInfo.class)
	public Object getGradeItemInfoByBatchId(@SqlParameter("bacthId") Long bacthId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select b.id,b.item_name itemName,b.CREATE_TIME createTime,b.UPDATE_TIME updateTime,b.status ");
		sql.append(" from (select id,item_id from grade_batch_item where batch_id=#{bacthId}) a  ");
		sql.append("inner join grade_item_info b on a.item_id=b.id");
		return sql.toString();
	}
}
