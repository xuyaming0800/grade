package cn.com.hzbank.grade.exception;

import java.io.Serializable;

public class BusinessExceptionBean implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4111546202417299759L;
	private boolean isSuccess=false;
	private String code="";
	private String message="";
	
	
	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setCode(BusinessExceptionEnum enums) {
		this.code = enums.getCode();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void appnedMessage(String message) {
		StringBuffer sb=new StringBuffer(this.message);
		sb.append("[");
		sb.append(message);
		sb.append("]");
		this.message = sb.toString();
	}
	
	public void setMessage(BusinessExceptionEnum enums) {
		this.message = enums.getMessage();
	}

	public BusinessExceptionBean(){
		
	}
	
    public BusinessExceptionBean(String code,String message){
		this.code=code;
		this.message=message;
	}
	
    public BusinessExceptionBean(BusinessExceptionEnum enums){
		this.code=enums.getCode();
		this.message=enums.getMessage();
	}
    
    public BusinessExceptionBean(BusinessException e){
		this.code=e.getErrorCode();
		this.message=e.getErrorMessage();
	}
}
