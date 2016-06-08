package cn.com.hzbank.grade.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hzbank.grade.bean.UserInfo;
import cn.com.hzbank.grade.bean.UserInfo.LoginCheck;
import cn.com.hzbank.grade.constant.GradeConstant;
import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.exception.BusinessExceptionEnum;
import cn.com.hzbank.grade.service.UserInfoService;
import cn.com.hzbank.grade.web.bean.ResultEntity;

@Controller
public class IndexController extends BaseController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private UserInfoService userInfoService;
	@RequestMapping("/login")
	public String login() throws Exception {
		return "login";
	}
	
	@RequestMapping("/session_timeout")
	public String sessionTimeout() throws Exception {
		return "session_timeout";
	}
	
	@RequestMapping("/common/frame")
	public String frame() throws Exception {
		return "common/frame";
	}
	
	@RequestMapping("/common/manager_top")
	public String manager_top() throws Exception {
		return "common/manager_top";
	}

	@RequestMapping("/checkLogin")
	@ResponseBody
	public Object checklogin(@Validated({ LoginCheck.class }) UserInfo user,
			BindingResult result,HttpServletRequest request){
		ResultEntity entity=new ResultEntity();
		try {
			if(result.hasErrors()){
				StringBuffer sb=new StringBuffer();
				for(ObjectError error :result.getAllErrors()){
					sb.append(error.getDefaultMessage());
					sb.append(" ");
				}
				entity=this.writeErrorResult(entity, BusinessExceptionEnum.PARAM_VAILD_ERROR.getCode(),sb.toString());
				return entity;
			}
			UserInfo _user=userInfoService.checkUserLogin(user);
			if(_user==null){
				entity=this.writeError(entity, BusinessExceptionEnum.USER_PASS_IS_ERROR);
			}else{
				entity=this.writeSuccess(entity);
				_user.setUserPass(null);
				request.getSession().setAttribute(GradeConstant.USER_SESSION, _user);
			}
		} catch (BusinessException e) {
			entity=this.writeError(entity, e);
		} catch (Exception e){
			logger.error(e.getLocalizedMessage(),e);
			BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity=this.writeError(entity, e1);
		}
		return entity;
		
	}

}
