package cn.com.hzbank.grade.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hzbank.grade.bean.GradeItemInfo;
import cn.com.hzbank.grade.bean.UserInfo;
import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.exception.BusinessExceptionEnum;
import cn.com.hzbank.grade.service.GradeService;
import cn.com.hzbank.grade.service.UserInfoService;
import cn.com.hzbank.grade.web.bean.ResultEntity;

@Controller
@RequestMapping("/common")
public class GradeController extends BaseController {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	GradeService gradeService;
	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/indexCommon")
	public String indexCommon() throws Exception {
		return "common/indexCommon";
	}
	
	@RequestMapping("/userGrade")
	public ModelAndView userGrade(@RequestParam(value = "batchId", required = true) Long batchId) throws Exception {
		ModelAndView mv=new ModelAndView();
		mv.setViewName( "common/userGrade");
		mv.addObject("batchId", batchId);
		return mv;
	}

	@RequestMapping("/getOpenBatch")
	@ResponseBody
	public Object getOpenBatch(
			@RequestParam(value = "pageNum", required = true) Integer pageNum,
			@RequestParam(value = "pageSize", required = true) Integer pageSize)
			throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			entity=gradeService.getOpenBatchInfoByPage(pageNum, pageSize);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(
					BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;
	}
	
	@RequestMapping("/getGradeItem")
	@ResponseBody
	public Object getGradeItem(
			@RequestParam(value = "batchId", required = true) Long batchId)
			throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			List<GradeItemInfo> l=gradeService.getGradeItemInfoByBatch(batchId);
			if(l.size()==0){
				BusinessException e1 = new BusinessException(
						BusinessExceptionEnum.GRADE_ITEM_IS_NULL);
				throw e1;
			}
			entity.setResult(l);
			entity = this.writeSuccess(entity);
			
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(
					BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;
	}
	
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public Object getUserInfo(
			@RequestParam(value = "orgId", required = true) Long orgId)
			throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			UserInfo user=new UserInfo();
			user.setOrgId(orgId);
			List<UserInfo> l=userInfoService.getUserInfoByOrg(user);
			if(l.size()==0){
				BusinessException e1 = new BusinessException(
						BusinessExceptionEnum.ORG_USER_IS_NULL);
				throw e1;
			}
			entity.setResult(l);
			entity = this.writeSuccess(entity);
			
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(
					BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;
	}
	
	
}
