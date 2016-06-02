package cn.com.hzbank.grade.constant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import autonavi.online.framework.property.PropertiesConfigUtil;

public class GradeConstant {
	private static Logger logger = LogManager.getLogger(GradeConstant.class);
	public static final String PROP_SINGLE_DATASOURCE_KEY = "SINGLE_DATASOURCE_KEY";

	/**
	 * 获取单一来源数据库编号
	 * 
	 * @return
	 */
	public static int getSingleDataSourceKey() {
		Integer dsKey = 1;
		try {
			dsKey = new Integer((String) PropertiesConfigUtil
					.getPropertiesConfigInstance().getProperty(
							PROP_SINGLE_DATASOURCE_KEY));
		} catch (Exception e) {
			logger.error("获取单一数据源失败,请检查,将返回默认数据源编号1", e);
		}
		return dsKey.intValue();
	}
}
