package cn.com.hzbank.grade.web.controller;

import javax.servlet.http.HttpServletRequest;

import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.exception.BusinessExceptionEnum;
import cn.com.hzbank.grade.web.bean.ResultDesc;
import cn.com.hzbank.grade.web.bean.ResultEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


public class BaseController {
	protected static final String SUCCESS = "success";
	protected static final String CODE = "0";
	
	protected ResultEntity writeError(ResultEntity entity,BusinessException e) {
		ResultDesc desc=new ResultDesc();
		desc.setCode(e.getErrorCode());
		desc.setMsg(e.getErrorMessage());
		entity.setStatus(desc);
		return entity;
	}
	
	protected ResultEntity writeError(ResultEntity entity,BusinessExceptionEnum e) {
		ResultDesc desc=new ResultDesc();
		desc.setCode(e.getCode());
		desc.setMsg(e.getMessage());
		entity.setStatus(desc);
		return entity;
	}
	
	protected ResultEntity writeSuccess(ResultEntity entity) {
		ResultDesc desc=new ResultDesc();
		desc.setCode(CODE);
		desc.setMsg(SUCCESS);
		entity.setStatus(desc);
		return entity;
	}
	
	protected ResultEntity writeSuccess(ResultEntity entity,String code,String message) {
		ResultDesc desc=new ResultDesc();
		desc.setCode(code);
		desc.setMsg(message);
		entity.setStatus(desc);
		return entity;
	}


	protected ResultEntity writeErrorResult(ResultEntity entity, String errorCode,
			String result) {
		ResultDesc desc=new ResultDesc();
		desc.setCode(errorCode);
		desc.setMsg(result);
		entity.setStatus(desc);
		entity.setResult(result);
		return entity;

	}

	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	protected ResultEntity getBindResult(BindingResult result,ResultEntity entity){
		StringBuffer sb=new StringBuffer();
		for(ObjectError error :result.getAllErrors()){
			sb.append(error.getDefaultMessage());
			sb.append(" ");
		}
		entity=this.writeErrorResult(entity, BusinessExceptionEnum.PARAM_VAILD_ERROR.getCode(),sb.toString());
		return entity;

	}

}
