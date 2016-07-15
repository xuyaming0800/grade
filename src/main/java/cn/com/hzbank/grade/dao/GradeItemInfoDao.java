package cn.com.hzbank.grade.dao;

import autonavi.online.framework.sharding.dao.constant.ReservedWord;
import autonavi.online.framework.sharding.entry.aspect.annotation.*;
import cn.com.hzbank.grade.constant.GradeConstant;
import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.entry.entity.CollectionType;
import cn.com.hzbank.grade.bean.GradeItemInfo;

@Repository
public class GradeItemInfoDao {

    @Author("yaming.xu")
    @SingleDataSource(keyName = "dsKey")
    @Select(collectionType = CollectionType.beanList, resultType = GradeItemInfo.class)
    public Object getGradeItemInfoByBatchId(
            @SqlParameter("dsKey") Integer dsKey,
            @SqlParameter("bacthId") Long bacthId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select b.id,b.item_name itemName,b.CREATE_TIME createTime,b.UPDATE_TIME updateTime,b.status ");
        sql.append(" from (select id,item_id from grade_batch_item where batch_id=#{bacthId}) a  ");
        sql.append("inner join grade_item_info b on a.item_id=b.id");
        return sql.toString();
    }

    @Author("yaming.xu")
    @SingleDataSource(keyName = "dsKey")
    @Select(collectionType = CollectionType.beanList, resultType = GradeItemInfo.class)
    public Object getGradeItemInfoByPage(@SqlParameter("dsKey") Integer dsKey,
                                   @SqlParameter("start") Integer start, @SqlParameter("size") Integer size) {
        StringBuffer sql = new StringBuffer();
        sql.append("select id,item_name itemName,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
        sql.append(" from grade_item_info ");
        sql.append(" where status= ");
        sql.append(GradeConstant.GRADE_ITEM_INFO_STATUS.USED.getCode());
        sql.append(" order by id desc ");
        sql.append(" limit #{start},#{size} ");
        return sql.toString();
    }

    @Author("yaming.xu")
    @SingleDataSource(keyName = "dsKey")
    @Select(collectionType = CollectionType.column, resultType = Long.class)
    public Object getGradeItemInfoByPageCount(@SqlParameter("dsKey") Integer dsKey) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(0) ");
        sql.append(" from grade_item_info ");
        sql.append(" where status= ");
        sql.append(GradeConstant.GRADE_ITEM_INFO_STATUS.USED.getCode());
        return sql.toString();
    }

    @Author("yaming.xu")
    @SingleDataSource(keyName = "dsKey")
    @Select(collectionType = CollectionType.bean, resultType = GradeItemInfo.class)
    public Object getGradeItemInfoById(@SqlParameter("dsKey") Integer dsKey,@SqlParameter("id") String id) {
        StringBuffer sql = new StringBuffer();
        sql.append("select  id,item_name itemName,percent,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
        sql.append(" from grade_item_info ");
        sql.append(" where id=#{id} ");
        return sql.toString();
    }

    @Author("yaming.xu")
    @SingleDataSource(keyName = "dsKey")
    @Select(collectionType = CollectionType.beanList, resultType = GradeItemInfo.class)
    public Object getGradeItemInfo(@SqlParameter("dsKey") Integer dsKey) {
        StringBuffer sql = new StringBuffer();
        sql.append("select id,item_name itemName,CREATE_TIME createTime,UPDATE_TIME updateTime,status ");
        sql.append(" from grade_item_info ");
        sql.append(" where status= ");
        sql.append(GradeConstant.GRADE_ITEM_INFO_STATUS.USED.getCode());
        return sql.toString();
    }

    @Author("yaming.xu")
    @SingleDataSource(keyName="info.dsKey")
    @Insert
    public Object addGradeItemInfo(@SqlParameter("info") GradeItemInfo info){
        StringBuffer sql = new StringBuffer();
        sql.append("insert into grade_item_info(id,item_name,percent,CREATE_TIME,UPDATE_TIME,status) ");
        sql.append(" values(");
        sql.append(" #{");
        sql.append(ReservedWord.snowflake);
        sql.append("},");
        sql.append("#{info.itemName},#{info.percent},UNIX_TIMESTAMP(now()),UNIX_TIMESTAMP(now()),#{info.status})");
        return sql.toString();
    }

    @Author("yaming.xu")
    @SingleDataSource(keyName="info.dsKey")
    @Update
    public Object updateGradeItemInfo(@SqlParameter("info") GradeItemInfo info){
        StringBuffer sql = new StringBuffer();
        sql.append("update grade_item_info set item_name=#{info.itemName},percent=#{info.percent}, ");
        sql.append(" update_time= UNIX_TIMESTAMP(now()) ");
        sql.append(" where id=#{info.id}");
        return sql.toString();
    }

    @Author("yaming.xu")
    @SingleDataSource(keyName="info.dsKey")
    @Update
    public Object removeGradeItemInfo(@SqlParameter("info") GradeItemInfo info){
        StringBuffer sql = new StringBuffer();
        sql.append("update grade_item_info set status= ");
        sql.append(GradeConstant.GRADE_ITEM_INFO_STATUS.UNUSED.getCode());
        sql.append(",");
        sql.append(" update_time= UNIX_TIMESTAMP(now()) ");
        sql.append(" where id=#{info.id}");
        return sql.toString();
    }



}
