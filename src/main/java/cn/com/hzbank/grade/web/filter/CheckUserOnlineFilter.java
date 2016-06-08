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
//		HttpServletResponse repo=(HttpServletResponse)response;
//	    String xhr=req.getHeader("x-requested-with");
//	    boolean isAjax=false;
//	    if(xhr!=null&&xhr.toLowerCase().equals("xmlhttprequest")){
//	    	//JS AJAX
//	    	isAjax=true;
//	    }
	    UserInfo user=(UserInfo)req.getSession().getAttribute(GradeConstant.USER_SESSION);
	    if(user==null){
//	    	if(isAjax){
//	    		ResultEntity entity=new ResultEntity();
//	    		entity=this.writeError(entity, BusinessExceptionEnum.USER_LOGIN_TIMEOUT);
//	    		PrintWriter printOut=null;
//	    		try {
//					printOut = repo.getWriter();
//					printOut.println(binder.toJson(entity));
//					printOut.flush();
//				} catch (IOException e) {
//					throw e;
//				}finally{
//					if(printOut!=null);
//					printOut.close();
//				}
//	    		
//	    	}else{
//	    		req.getRequestDispatcher("/session_timeout").forward(request, response);
//	    	}
	    	req.getRequestDispatcher("/session_timeout").forward(request, response);
	    }else{
	    	chain.doFilter(request, response);
	    }

		

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
