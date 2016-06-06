package cn.com.hzbank.grade.constant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import autonavi.online.framework.property.PropertiesConfigUtil;

public class GradeConstant {
	private static Logger logger = LogManager.getLogger(GradeConstant.class);
	public static final String PROP_SINGLE_DATASOURCE_KEY = "SINGLE_DATASOURCE_KEY";
	
	public enum BATCH_INFO_STATUS {
		// 0 开发 1关闭
		OPEN(0), CLOSE(1);
		private int code;

		public int getCode() {
			return code;
		}

		private BATCH_INFO_STATUS(int code) {
			this.code = code;
		}
	}
	
	public enum USER_INFO_STATUS {
		// 0 使用 1 不适用
		USED(0), UNUSED(1);
		private int code;

		public int getCode() {
			return code;
		}

		private USER_INFO_STATUS(int code) {
			this.code = code;
		}
	}
	
	public enum GRADE_ITEM_INFO_STATUS {
		// 0 使用 1 不适用
		USED(0), UNUSED(1);
		private int code;

		public int getCode() {
			return code;
		}

		private GRADE_ITEM_INFO_STATUS(int code) {
			this.code = code;
		}
	}

	/**
	 * 获取单一来源数据库编号
	 * 
	 * @return
	 */
	public static final int getSingleDataSourceKey() {
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
