package cn.com.hzbank.grade.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.dao.constant.ReservedWord;
import autonavi.online.framework.sharding.entry.aspect.annotation.Author;
import autonavi.online.framework.sharding.entry.aspect.annotation.Insert;
import autonavi.online.framework.sharding.entry.aspect.annotation.Select;
import autonavi.online.framework.sharding.entry.aspect.annotation.Shard;
import autonavi.online.framework.sharding.entry.aspect.annotation.SqlParameter;
import autonavi.online.framework.sharding.entry.entity.CollectionType;
import cn.com.hzbank.grade.bean.GradeUserInfo;
import cn.com.hzbank.grade.constant.GradeConstant.GRADE_USER_INFO_STATUS;

@Repository
public class GradeUserInfoDao {
	@Author("yaming.xu")
	@Shard(indexName = "GRADE_BATCH_INDEX", indexColumn = "info.batchId")
	@Insert
	public Object addGradeUserInfo(@SqlParameter("info") GradeUserInfo info) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into grade_user_info(id,op_user_id,user_id,item_id,batch_id,org_id,CREATE_TIME,UPDATE_TIME,grade,status) ");
		sql.append(" values( ");
		sql.append(ReservedWord.snowflake);
		sql.append("},");
		sql.append("#{info.opUserId},#{info.userId},#{info.itemId},#{info.batchId},#{info.orgId},UNIX_TIMESTAMP(now()),UNIX_TIMESTAMP(now()),#{info.grade},#{info.status}");
		sql.append(")");
		return sql.toString();
	}

	@Author("yaming.xu")
	@Shard(indexName = "GRADE_BATCH_INDEX", indexColumn = "list."
			+ ReservedWord.index + ".batchId")
	@Insert
	public Object addGradeUserInfos(
			@SqlParameter("list") List<GradeUserInfo> list) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into grade_user_info(id,op_user_id,user_id,item_id,batch_id,org_id,CREATE_TIME,UPDATE_TIME,grade,status) ");
		sql.append(" values(#{");
		sql.append(ReservedWord.snowflake);
		sql.append("},");
		sql.append("#{list." + ReservedWord.index + ".opUserId},#{list."
				+ ReservedWord.index + ".userId},#{list." + ReservedWord.index
				+ ".itemId},#{list." + ReservedWord.index + ".batchId},#{list."
				+ ReservedWord.index
				+ ".orgId},UNIX_TIMESTAMP(now()),UNIX_TIMESTAMP(now()),#{list."
				+ ReservedWord.index + ".grade},#{list." + ReservedWord.index
				+ ".status}");
		sql.append(")");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@Shard(indexName = "GRADE_BATCH_INDEX", indexColumn = "info.batchId")
	@Select(collectionType = CollectionType.column, resultType = Integer.class)
	public Object getGradeUserInfoCountByOp(@SqlParameter("info") GradeUserInfo info) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(0) from grade_user_info ");
		sql.append(" where op_user_id=#{info.opUserId} and batch_id=#{info.batchId} and org_id=#{info.orgId} and status=");
		sql.append(GRADE_USER_INFO_STATUS.USED.getCode());
		return sql.toString();
	}
}
