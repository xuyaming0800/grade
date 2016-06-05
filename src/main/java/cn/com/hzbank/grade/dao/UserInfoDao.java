package cn.com.hzbank.grade.dao;

import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.entry.aspect.annotation.Author;
import autonavi.online.framework.sharding.entry.aspect.annotation.Insert;
import autonavi.online.framework.sharding.entry.aspect.annotation.Select;
import autonavi.online.framework.sharding.entry.aspect.annotation.SingleDataSource;
import autonavi.online.framework.sharding.entry.aspect.annotation.SqlParameter;
import autonavi.online.framework.sharding.entry.entity.CollectionType;
import cn.com.hzbank.grade.bean.UserInfo;
import cn.com.hzbank.grade.constant.GradeConstant.USER_INFO_STATUS;

@Repository
public class UserInfoDao {

	@Author("yaming.xu")
	@SingleDataSource(keyName="info.dsKey")
	@Insert
	public Object addUserInfo(@SqlParameter("info") UserInfo info) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into user_info(id,org_id,user_name,uid,user_pass,CREATE_TIME,UPDATE_TIME,status) ");
		sql.append(" values(#{info.id},#{info.orgId},#{info.userName},#{info.uid},#{info.userPass},UNIX_TIMESTAMP(now()),UNIX_TIMESTAMP(now()),#{info.status}");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = UserInfo.class)
	public Object getAllUserInfo(@SqlParameter("deKey") Integer dsKey) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,org_id orgId,user_name userName,uid,user_pass userPass,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" where status=");
		sql.append(USER_INFO_STATUS.USED.getCode());
		sql.append(" from user_info order by id asc ");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = UserInfo.class)
	public Object getUserInfoByPage(@SqlParameter("deKey") Integer dsKey,@SqlParameter("start") Integer start,@SqlParameter("size") Integer size) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,org_id orgId,user_name userName,uid,user_pass userPass,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" where status=");
		sql.append(USER_INFO_STATUS.USED.getCode());
		sql.append(" from user_info order by id asc ");
		sql.append(" limit #{start},#{size} ");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="info.dsKey")
	@Select(collectionType = CollectionType.bean, resultType = UserInfo.class)
	public Object getUserInfoByUid(@SqlParameter("info") UserInfo info) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,org_id orgId,user_name userName,uid,user_pass userPass,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from user_info  ");
		sql.append(" where uid=#{info.uid} ");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="info.dsKey")
	@Select(collectionType = CollectionType.bean, resultType = UserInfo.class)
	public Object getUserInfoById(@SqlParameter("info") UserInfo info) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,org_id orgId,user_name userName,uid,user_pass userPass,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from user_info  ");
		sql.append(" where id=#{info.id} ");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="info.dsKey")
	@Select(collectionType = CollectionType.bean, resultType = UserInfo.class)
	public Object getUserInfoByOrgId(@SqlParameter("info") UserInfo info) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,org_id orgId,user_name userName,uid,user_pass userPass,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from user_info  ");
		sql.append(" where org_id=#{info.orgId} and status=");
		sql.append(USER_INFO_STATUS.USED.getCode());
		return sql.toString();
	}

}
