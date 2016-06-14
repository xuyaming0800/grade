package cn.com.hzbank.grade.dao;

import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.dao.constant.ReservedWord;
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
		sql.append(" values(");
		sql.append(" #{");
		sql.append(ReservedWord.snowflake);
		sql.append("},");
		sql.append("#{info.orgId},#{info.userName},#{info.uid},#{info.userPass},UNIX_TIMESTAMP(now()),UNIX_TIMESTAMP(now()),#{info.status}");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = UserInfo.class)
	public Object getAllUserInfo(@SqlParameter("dsKey") Integer dsKey) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,org_id orgId,user_name userName,uid,user_pass userPass,user_type userType,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from user_info ");
		sql.append(" where status=");
		sql.append(USER_INFO_STATUS.USED.getCode());
		sql.append("  order by id asc ");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = UserInfo.class)
	public Object getUserInfoByPage(@SqlParameter("dsKey") Integer dsKey,@SqlParameter("start") Integer start,@SqlParameter("size") Integer size) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,org_id orgId,user_name userName,uid,user_pass userPass,user_type userType,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from user_info ");
		sql.append(" where status=");
		sql.append(USER_INFO_STATUS.USED.getCode());
		sql.append(" order by id asc ");
		sql.append(" limit #{start},#{size} ");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = UserInfo.class)
	public Object getUserInfoWithOrgByPage(@SqlParameter("dsKey") Integer dsKey,@SqlParameter("start") Integer start,@SqlParameter("size") Integer size) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id,a.orgId,a.userName,a.uid,a.userPass,a.userType,a.createTime,a.updateTime,a.status,b.org_name orgName from ");
		sql.append("(select id,org_id orgId,user_name userName,uid,user_pass userPass,user_type userType,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from user_info ");
		sql.append(" where status=");
		sql.append(USER_INFO_STATUS.USED.getCode());
		sql.append(" and id>0 ");
		sql.append(" order by id asc ");
		sql.append(" limit #{start},#{size}) a ");
		sql.append(" left join org_info b on a.orgId=b.id ");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = UserInfo.class)
	public Object getUserInfoWithOrgCountByPage(@SqlParameter("dsKey") Integer dsKey) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(0) ");
		sql.append(" from user_info ");
		sql.append(" where status=");
		sql.append(USER_INFO_STATUS.USED.getCode());
		sql.append(" and id>0 ");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = UserInfo.class)
	public Object getUserInfoWithOrgCountByName(@SqlParameter("dsKey") Integer dsKey,@SqlParameter("userName") String userName) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(0) ");
		sql.append(" from user_info ");
		sql.append(" where status=");
		sql.append(USER_INFO_STATUS.USED.getCode());
		sql.append(" and id>0 ");
		sql.append(" and user_name like #{userName} "); 
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = UserInfo.class)
	public Object getUserInfoWithOrgByName(@SqlParameter("dsKey") Integer dsKey,@SqlParameter("userName") String userName,@SqlParameter("start") Integer start,@SqlParameter("size") Integer size) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id,a.orgId,a.userName,a.uid,a.userPass,a.userType,a.createTime,a.updateTime,a.status,b.org_name orgName from ");
		sql.append("(select id,org_id orgId,user_name userName,uid,user_pass userPass,user_type userType,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from user_info ");
		sql.append(" where status=");
		sql.append(USER_INFO_STATUS.USED.getCode());
		sql.append(" and id>0 ");
		sql.append(" and user_name like #{userName} "); 
		sql.append(" order by id asc ");
		sql.append(" limit #{start},#{size}) a ");
		sql.append(" left join org_info b on a.orgId=b.id ");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="info.dsKey")
	@Select(collectionType = CollectionType.bean, resultType = UserInfo.class)
	public Object getUserInfoByUid(@SqlParameter("info") UserInfo info) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,org_id orgId,user_name userName,uid,user_pass userPass,user_type userType,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from user_info  ");
		sql.append(" where uid=#{info.uid} ");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="info.dsKey")
	@Select(collectionType = CollectionType.bean, resultType = UserInfo.class)
	public Object getUserInfoById(@SqlParameter("info") UserInfo info) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,org_id orgId,user_name userName,uid,user_pass userPass,user_type userType,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from user_info  ");
		sql.append(" where id=#{info.id} ");
		return sql.toString();
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName="info.dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = UserInfo.class)
	public Object getUserInfoByOrgId(@SqlParameter("info") UserInfo info) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,org_id orgId,user_name userName,uid,user_pass userPass,user_type userType,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
		sql.append(" from user_info  ");
		sql.append(" where org_id=#{info.orgId} and status=");
		sql.append(USER_INFO_STATUS.USED.getCode());
		return sql.toString();
	}

}
