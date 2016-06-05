package cn.com.hzbank.grade.dao;

import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.entry.aspect.annotation.Author;
import autonavi.online.framework.sharding.entry.aspect.annotation.Select;
import autonavi.online.framework.sharding.entry.aspect.annotation.SingleDataSource;
import autonavi.online.framework.sharding.entry.aspect.annotation.SqlParameter;
import autonavi.online.framework.sharding.entry.entity.CollectionType;
import cn.com.hzbank.grade.bean.OrgInfo;

@Repository
public class OrgInfoDao {
	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = OrgInfo.class)
	public Object getAllOrgInfo(@SqlParameter("dsKey") Integer dsKey) {
		return "select id,org_name orgName,org_desc orgDesc,create_time createTime,update_time,updateTime,status from org_info ";
	}

	@Author("yaming.xu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.beanList, resultType = OrgInfo.class)
	public Object getOrgInfoByPage(@SqlParameter("dsKey") Integer dsKey,
			@SqlParameter("start") Integer start,
			@SqlParameter("end") Integer end) {
		return "select id,org_name orgName,org_desc orgDesc,create_time createTime,update_time updateTime,status from org_info limit #{start},#{end} ";
	}

}
