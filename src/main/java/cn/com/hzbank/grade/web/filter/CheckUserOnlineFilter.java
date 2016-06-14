package cn.com.hzbank.grade.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.com.hzbank.grade.bean.UserInfo;
import cn.com.hzbank.grade.constant.GradeConstant;
import cn.com.hzbank.grade.constant.GradeConstant.USER_INFO_TYPE;
import cn.com.hzbank.grade.web.controller.BaseController;

public class CheckUserOnlineFilter extends BaseController implements Filter {
//	private Logger logger = LogManager.getLogger(this.getClass());
//
//	private JsonBinder binder=JsonBinder.buildNonNullBinder(false);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
	    UserInfo user=(UserInfo)req.getSession().getAttribute(GradeConstant.USER_SESSION);
	    if(user==null){
	    	req.getRequestDispatcher("/session_timeout").forward(request, response);
	    	return;
	    }else{
	    	String uri=req.getRequestURI().replace(req.getContextPath(),"");
	    	if(uri.startsWith("/manager/")){
	    	  //管理员权限判定
	    	  if(user.getUserType()!=USER_INFO_TYPE.ADMIN.getCode()){
	    		  req.getRequestDispatcher("/session_timeout").forward(request, response);
	    		  return;
	    	  }
	    	}
	    	chain.doFilter(request, response);
	    }

		

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
