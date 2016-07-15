package cn.com.hzbank.grade.component;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuyaming on 16/7/15.
 */
@Component
public class ScoreUtil {
    private Integer[] resource=new Integer[]{100,90,80,70,60};
    private Map<Integer,Integer> map=new HashMap<Integer,Integer>(){
        {
            put(1,90);
            put(2,80);
            put(3,70);
            put(5,60);
            put(4,100);
        }
    };
    public String generateScores(Integer personCount){
        Integer base=personCount/resource.length;
        Integer mo=personCount%resource.length;
        Map<Integer,Integer> _map=new HashMap<>();
        if(base>1){
            for(Integer index:resource){
                _map.put(index,base-1);
            }
        }
        for (int i = mo; i >0 ; i--) {
            _map.put(map.get(i),base);

        }

        List<String> result=new ArrayList<String>();
        for(Integer index:resource){
            String _index=String.valueOf(index);
            result.add(_index);
            if(_map.containsKey(index)){
                for(int i=0;i<_map.get(index);i++){
                    result.add(_index);
                }
            }
        }
        return StringUtils.collectionToCommaDelimitedString(result);
    }

}
