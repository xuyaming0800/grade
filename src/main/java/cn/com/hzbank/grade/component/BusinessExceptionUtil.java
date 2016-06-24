package cn.com.hzbank.grade.component;

import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.exception.BusinessExceptionEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by xuyaming on 16/6/24.
 */
@Component
public class BusinessExceptionUtil {
    private static Logger logger = LogManager.getLogger(BusinessExceptionUtil.class);
    public BusinessException getBusinessException(Exception e){
        if(!(e instanceof BusinessException)){
            logger.error(e.getMessage(),e);
            BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
            return e1;
        }else{
            return (BusinessException)e;
        }
    }
}
