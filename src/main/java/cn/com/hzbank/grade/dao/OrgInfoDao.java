package cn.com.hzbank.grade.dao;

import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.dao.constant.ReservedWord;
import autonavi.online.framework.sharding.entry.aspect.annotation.Author;
import autonavi.online.framework.sharding.entry.aspect.annotation.Insert;
import autonavi.online.framework.sharding.entry.aspect.annotation.Select;
import autonavi.online.framework.sharding.entry.aspect.annotation.SingleDataSource;
import autonavi.online.framework.sharding.entry.aspect.annotation.SqlParameter;
import autonavi.online.framework.sharding.entry.aspect.annotation.Update;
import autonavi.online.framework.sharding.entry.entity.CollectionType;
import cn.com.hzbank.grade.bean.OrgInfo;
import cn.com.hzbank.grade.constant.GradeConstant.ORG_INFO_STATUS;

@Repository
public class OrgInfoDao {
	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = OrgInfo.class)
	public Object getAllOrgInfo(@SqlParameter("dsKey") Integer dsKey) {
		return "select id,org_name orgName,org_desc orgDesc,create_time createTime,update_time updateTime,status from org_info ";
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.bean, resultType = OrgInfo.class)
	public Object getOrgInfoById(@SqlParameter("dsKey") Integer dsKey,@SqlParameter("id") Long id) {
		return "select id,org_name orgName,org_desc orgDesc,create_time createTime,update_time updateTime,status from org_info where id=#{id} ";
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Update
	public Object deleteOrgInfo(@SqlParameter("dsKey") Integer dsKey,@SqlParameter("orgInfo") OrgInfo orgInfo) {
		return "update org_Info set update_time=UNIX_TIMESTAMP(now()),status="+ORG_INFO_STATUS.UNUSED.getCode()+" where id=#{orgInfo.id} ";
	}
	
	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Update
	public Object updateOrgInfo(@SqlParameter("dsKey") Integer dsKey,@SqlParameter("orgInfo") OrgInfo orgInfo) {
		return "update org_Info set org_name=#{orgInfo.orgName},org_desc=#{orgInfo.orgDesc},update_time=UNIX_TIMESTAMP(now()) where id=#{orgInfo.id} ";
	}
	
	

	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = OrgInfo.class)
	public Object getOrgInfoByPage(@SqlParameter("dsKey") Integer dsKey,
			@SqlParameter("start") Integer start,
			@SqlParameter("end") Integer end) {
		return "select id,org_name orgName,org_desc orgDesc,create_time createTime,update_time updateTime,status from org_info where status="
				+ ORG_INFO_STATUS.USED.getCode() + " limit #{start},#{end} ";
	}

	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.column, resultType = Long.class)
	public Object getOrgInfoCountByPage(@SqlParameter("dsKey") Integer dsKey) {
		return "select count(0) from org_info where status="
				+ ORG_INFO_STATUS.USED.getCode();
	}

	@Author("yaming.xu")
	@SingleDataSource(keyName = "info.dsKey")
	@Insert
	public Object addOrgInfo(@SqlParameter("info") OrgInfo info) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into org_info(id,org_name,org_desc,CREATE_TIME,UPDATE_TIME,status) ");
		sql.append(" values(");
		sql.append(" #{");
		sql.append(ReservedWord.snowflake);
		sql.append("},");
		sql.append("#{info.orgName},#{info.orgDesc},UNIX_TIMESTAMP(now()),UNIX_TIMESTAMP(now()),#{info.status})");
		return sql.toString();
	}

	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = OrgInfo.class)
	public Object getOrgInfoByName(@SqlParameter("dsKey") Integer dsKey,
			@SqlParameter("orgName") String orgName,
			@SqlParameter("start") Integer start,
			@SqlParameter("end") Integer end) {
		return "select id,org_name orgName,org_desc orgDesc,create_time createTime,update_time updateTime,status from org_info where org_name like #{orgName} and status="
				+ ORG_INFO_STATUS.USED.getCode()+" limit #{start},#{end} ";
	}

	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.column, resultType = Long.class)
	public Object getOrgInfoCountByName(@SqlParameter("dsKey") Integer dsKey,
			@SqlParameter("orgName") String orgName) {
		return "select count(0) from org_info where org_name like #{orgName} and status="
				+ ORG_INFO_STATUS.USED.getCode();
	}

}
