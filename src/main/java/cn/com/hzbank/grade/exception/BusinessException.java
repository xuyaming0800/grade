package cn.com.hzbank.grade.exception;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5636326402890518248L;

	// error code
	private String errorCode = null;

	// error message
	private String errorMessage = null;

	public BusinessException(String message) {
		super(message);
	}
	public BusinessException(String message, Throwable cause) {
		super(message,cause);
	}

	/**
	 * 构造函数说明： 
	 * @param errorCode
	 * @param errorMessage
	 * @param sqlExpEnum
	 */
	public BusinessException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public BusinessException(String errorCode, String errorMessage,Throwable cause) {
		super(errorMessage,cause);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public BusinessException(BusinessExceptionEnum enums,Throwable cause) {
		super(enums.getMessage(),cause);
		this.errorCode=enums.getCode();
		this.errorMessage=enums.getMessage();
	}
	
	public BusinessException(BusinessExceptionEnum enums) {
		super(enums.getMessage());
		this.errorCode=enums.getCode();
		this.errorMessage=enums.getMessage();
	}
	
	public BusinessException(BusinessExceptionEnum enums,String extraMessage) {
		super(enums.getMessage()+"["+extraMessage+"]");
		this.errorCode=enums.getCode();
		this.errorMessage=enums.getMessage();
	}
	
	public BusinessException(BusinessExceptionEnum enums,String extraMessage,Throwable cause) {
		super(enums.getMessage()+"["+extraMessage+"]",cause);
		this.errorCode=enums.getCode();
		this.errorMessage=enums.getMessage();
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}
}
