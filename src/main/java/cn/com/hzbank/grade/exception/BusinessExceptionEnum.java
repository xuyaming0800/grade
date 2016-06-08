package cn.com.hzbank.grade.exception;

public enum BusinessExceptionEnum {
	//返回前端异常100-10000
	PARAM_VAILD_ERROR("101","参数校验错误,缺少必填参数"),
	SYSTEM_ERROR("500","系统存在异常"),
	USER_IS_NOT_FOUND("102","用户未找到"),
	USER_PASS_IS_ERROR("103","用户名或者密码错误"),
	GRADE_ITEM_IS_NULL("104","评分项目未设定"),
	ORG_USER_IS_NULL("105","部门下没有员工"),
	;

	private String code;
	private String message;

	public String getCode() {
		return this.code;
	}

//	public void setCode(String code) {
//		this.code = code;
//	}

	public String getMessage() {
		return this.message;
	}

//	public void setMessage(String message) {
//		this.message = message;
//	}

	private BusinessExceptionEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static String getMessage(String code) {
		for (BusinessExceptionEnum exp : values()) {
			if (exp.getCode().equals(code)) {
				return exp.getMessage();
			}
		}
		return null;
	}
}
