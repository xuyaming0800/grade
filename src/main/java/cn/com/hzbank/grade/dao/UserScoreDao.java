package cn.com.hzbank.grade.dao;

import autonavi.online.framework.sharding.entry.aspect.annotation.Author;
import autonavi.online.framework.sharding.entry.aspect.annotation.Select;
import autonavi.online.framework.sharding.entry.aspect.annotation.Shard;
import autonavi.online.framework.sharding.entry.aspect.annotation.SqlParameter;
import autonavi.online.framework.sharding.entry.entity.CollectionType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by xuyaming on 16/7/15.
 */
@Repository
public class UserScoreDao {


	@Author("yaming.xu")
	@Shard(indexName = "GRADE_BATCH_INDEX", indexColumn = "batchId")
	@Select(collectionType = CollectionType.mapList, resultType = Map.class)
	public Object getUserScore(List<String> itemList, @SqlParameter("orgId") String orgId,
			@SqlParameter("batchId") String batchId,@SqlParameter("scoreStr") String scoreStr) {
        StringBuffer sb=new StringBuffer("select d.user_Name userName,");
        for(String item:itemList){
            sb.append("sum(case d.item_name when '"+item+"' then d.score else 0 end) as "+item+"_score,");
            sb.append("sum(case d.item_name when '"+item+"' then d.grade else 0 end) as "+item+"_grade,");
        }
        sb.append("sum(d.score)as totalScore ");
        sb.append(" from ");
        sb.append(" (select a.*,generateScore(#{scoreStr},a.grade,b.percent) as score,b.item_name,c.user_name from  ");
        sb.append(" (SELECT op_user_id,user_id,item_id,grade FROM grade_user_info where org_id=#{orgId} and batch_id=#{batchId}) a ");
        sb.append(" inner join grade_item_info b ");
        sb.append(" on a.item_id=b.id ");
        sb.append(" inner join user_info c ");
        sb.append(" on a.user_id=c.id ");
        sb.append(" order by a.user_id,item_id)d ");
        sb.append(" group by d.user_name,d.op_user_id ");
		return sb.toString();
	}

    @Author("yaming.xu")
    @Shard(indexName = "GRADE_BATCH_INDEX", indexColumn = "batchId")
    @Select(collectionType = CollectionType.mapList, resultType = Map.class)
    public Object getUserScoreAvg(List<String> itemList, @SqlParameter("orgId") String orgId,
                               @SqlParameter("batchId") String batchId,@SqlParameter("scoreStr") String scoreStr) {
        StringBuffer sb=new StringBuffer("select e.userName userName,avg(e.totalScore) totalScore from ");
        sb.append("(select d.user_Name userName,");
        for(String item:itemList){
            sb.append("sum(case d.item_name when '"+item+"' then d.score else 0 end) as "+item+"_score,");
        }
        sb.append("sum(d.score)as totalScore ");
        sb.append(" from ");
        sb.append(" (select a.*,generateScore(#{scoreStr},a.grade,b.percent) as score,b.item_name,c.user_name from  ");
        sb.append(" (SELECT op_user_id,user_id,item_id,grade FROM grade_user_info where org_id=#{orgId} and batch_id=#{batchId}) a ");
        sb.append(" inner join grade_item_info b ");
        sb.append(" on a.item_id=b.id ");
        sb.append(" inner join user_info c ");
        sb.append(" on a.user_id=c.id ");
        sb.append(" order by a.user_id,item_id)d ");
        sb.append(" group by d.user_name,d.op_user_id)e ");
        sb.append(" group by e.userName ");
        sb.append(" order by totalScore desc ");
        return sb.toString();
    }
}
