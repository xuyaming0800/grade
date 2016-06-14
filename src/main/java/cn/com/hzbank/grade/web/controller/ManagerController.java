package cn.com.hzbank.grade.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hzbank.grade.bean.OrgInfo;
import cn.com.hzbank.grade.bean.OrgInfo.AddOrgInfo;
import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.exception.BusinessExceptionEnum;
import cn.com.hzbank.grade.service.ManagerService;
import cn.com.hzbank.grade.web.bean.ResultEntity;

@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseController {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private ManagerService managerService;
	@RequestMapping("/indexManager")
	public String indexManager() throws Exception {
		return "manager/indexManager";
	}
	
	@RequestMapping("/getOrgInfoList")
	@ResponseBody
	public Object getOrgInfoList(
			@RequestParam(value = "pageNum", required = true) Integer pageNum,
			@RequestParam(value = "pageSize", required = true) Integer pageSize,
			@RequestParam(value = "query", required = true) String query)
			throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			if(StringUtils.isEmpty(query)){
				entity = managerService.queryOrgInfo(pageNum, pageSize);
			}else{
				entity =managerService.queryOrgInfoByName(query, pageNum, pageSize);
			}
			
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
	
	@RequestMapping("/getOrgInfo")
	@ResponseBody
	public Object getOrgInfo(
			@RequestParam(value = "orgId", required = true) String orgId)
			throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			OrgInfo info=managerService.getOrgInfoById(orgId);
			entity.setResult(info);
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
	
	@RequestMapping("/addOrgInfo")
	@ResponseBody
	public Object addOrgInfo(@Validated({ AddOrgInfo.class }) OrgInfo orgInfo,
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
			managerService.addOrgInfo(orgInfo);
			entity=this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity=this.writeError(entity, e);
		} catch (Exception e){
			logger.error(e.getLocalizedMessage(),e);
			BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity=this.writeError(entity, e1);
		}
		return entity;
		
	}
	
	@RequestMapping("/modifyOrgInfo")
	@ResponseBody
	public Object modifyOrgInfo(@Validated({ AddOrgInfo.class }) OrgInfo orgInfo,
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
			managerService.updateOrgInfo(orgInfo);
			entity=this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity=this.writeError(entity, e);
		} catch (Exception e){
			logger.error(e.getLocalizedMessage(),e);
			BusinessException e1=new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity=this.writeError(entity, e1);
		}
		return entity;
		
	}
	
	@RequestMapping("/deleteOrgInfo")
	@ResponseBody
	public Object modifyOrgInfo( OrgInfo orgInfo){
		ResultEntity entity=new ResultEntity();
		try {
			managerService.removeOrgInfo(orgInfo);
			entity=this.writeSuccess(entity);
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
