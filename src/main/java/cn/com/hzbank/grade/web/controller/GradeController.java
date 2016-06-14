package cn.com.hzbank.grade.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import autonavi.online.framework.util.json.JsonBinder;
import cn.com.hzbank.grade.bean.GradeItemInfo;
import cn.com.hzbank.grade.bean.GradeUserInfo;
import cn.com.hzbank.grade.bean.UserInfo;
import cn.com.hzbank.grade.constant.GradeConstant;
import cn.com.hzbank.grade.constant.GradeConstant.GRADE_USER_INFO_STATUS;
import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.exception.BusinessExceptionEnum;
import cn.com.hzbank.grade.service.GradeService;
import cn.com.hzbank.grade.service.UserInfoService;
import cn.com.hzbank.grade.web.bean.ResultEntity;

@Controller
@RequestMapping("/common")
public class GradeController extends BaseController {
	private Logger logger = LogManager.getLogger(this.getClass());
	private JsonBinder binder = JsonBinder.buildNonNullBinder(false);
	@Autowired
	GradeService gradeService;
	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/indexCommon")
	public String indexCommon() throws Exception {
		return "common/indexCommon";
	}

	@RequestMapping("/userGrade")
	public ModelAndView userGrade(
			@RequestParam(value = "batchId", required = true) Long batchId)
			throws Exception {
		ModelAndView mv = new ModelAndView();		
		mv.setViewName("common/userGrade");
		mv.addObject("batchId", batchId);
		return mv;
	}

	@RequestMapping("/getOpenBatch")
	@ResponseBody
	public Object getOpenBatch(
			@RequestParam(value = "pageNum", required = true) Integer pageNum,
			@RequestParam(value = "pageSize", required = true) Integer pageSize,
			HttpServletRequest request)
			throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			UserInfo user = (UserInfo) request.getSession().getAttribute(
					GradeConstant.USER_SESSION);
			entity = gradeService.getOpenBatchInfoByPage(user.getId(),pageNum, pageSize);
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
			@RequestParam(value = "batchId", required = true) String batchId)
			throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			List<GradeItemInfo> l = gradeService
					.getGradeItemInfoByBatch(batchId);
			if (l.size() == 0) {
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
			@RequestParam(value = "orgId", required = true) String orgId)
			throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			UserInfo user = new UserInfo();
			user.setOrgId(orgId);
			List<UserInfo> l = userInfoService.getUserInfoByOrg(user);
			if (l.size() == 0) {
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

	@SuppressWarnings("unchecked")
	@RequestMapping("/submitGradeUserInfo")
	@ResponseBody
	public Object submitGradeUserInfo(
			@RequestParam(value = "orgId", required = true) String orgId,
			@RequestParam(value = "batchId", required = true) String batchId,
			@RequestParam(value = "content", required = true) String content,
			HttpServletRequest request) throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			UserInfo user = (UserInfo) request.getSession().getAttribute(
					GradeConstant.USER_SESSION);
			Map<String, Object> map = binder.fromJson(content, Map.class,
					binder.getCollectionType(Map.class, String.class,
							Object.class));
			List<GradeUserInfo> list=new ArrayList<GradeUserInfo>();
			for(String key:map.keySet()){
				List<String> value=(List<String>)map.get(key);
				int count=1;
				for(String userId:value){
					GradeUserInfo info=new GradeUserInfo();
					info.setBatchId(batchId);
					info.setStatus(GRADE_USER_INFO_STATUS.USED.getCode());
					info.setOpUserId(user.getId());
					info.setOrgId(orgId);
					info.setUserId(userId);
					info.setItemId(key);
					info.setGrade(count);
					count++;
					list.add(info);
				}
				
			}
			gradeService.addGraadUserInfos(list, user, batchId);
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
