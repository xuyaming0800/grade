package cn.com.hzbank.grade.web.controller;

import javax.servlet.http.HttpServletRequest;

import cn.com.hzbank.grade.bean.GradeBatchInfo;
import cn.com.hzbank.grade.bean.GradeItemInfo;
import cn.com.hzbank.grade.bean.UserInfo;
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
	public Object getOrgInfoList(@RequestParam(value = "pageNum", required = true) Integer pageNum,
			@RequestParam(value = "pageSize", required = true) Integer pageSize,
			@RequestParam(value = "query", required = true) String query) throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			if (StringUtils.isEmpty(query)) {
				entity = managerService.queryOrgInfo(pageNum, pageSize);
			} else {
				entity = managerService.queryOrgInfoByName(query, pageNum, pageSize);
			}

			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;
	}

	@RequestMapping("/getUserInfoList")
	@ResponseBody
	public Object getUserInfoList(@RequestParam(value = "pageNum", required = true) Integer pageNum,
			@RequestParam(value = "pageSize", required = true) Integer pageSize,
			@RequestParam(value = "query", required = true) String query) throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			if (StringUtils.isEmpty(query)) {
				entity = managerService.queryUserInfo(pageNum, pageSize);
			} else {
				entity = managerService.queryUserInfoByName(query, pageNum, pageSize);
			}

			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;
	}

	@RequestMapping("/getItemInfoList")
	@ResponseBody
	public Object getItemInfoList(@RequestParam(value = "pageNum", required = true) Integer pageNum,
			@RequestParam(value = "pageSize", required = true) Integer pageSize) throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			entity = managerService.queryItemInfo(pageNum, pageSize);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;
	}

	@RequestMapping("/getBatchInfoList")
	@ResponseBody
	public Object getBatchList(@RequestParam(value = "pageNum", required = true) Integer pageNum,
			@RequestParam(value = "pageSize", required = true) Integer pageSize) throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			entity = managerService.queryBatchInfo(pageNum, pageSize);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;
	}

	@RequestMapping("/getOrgInfo")
	@ResponseBody
	public Object getOrgInfo(@RequestParam(value = "orgId", required = true) String orgId) throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			OrgInfo info = managerService.getOrgInfoById(orgId);
			entity.setResult(info);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;
	}

	@RequestMapping("/getUserInfo")
	@ResponseBody
	public Object getUserInfo(@RequestParam(value = "userId", required = true) String userId) throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			UserInfo info = managerService.getUserInfoById(userId);
			entity.setResult(info);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;
	}

	@RequestMapping("/getItemInfo")
	@ResponseBody
	public Object getItemInfo(@RequestParam(value = "itemId", required = true) String itemId) throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			GradeItemInfo info = managerService.getItemInfoById(itemId);
			entity.setResult(info);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;
	}

	@RequestMapping("/getBatchInfo")
	@ResponseBody
	public Object getBatchInfo(@RequestParam(value = "batchId", required = true) String batchId) throws Exception {
		ResultEntity entity = new ResultEntity();
		try {
			GradeBatchInfo info = managerService.getBatchInfoById(batchId);
			entity.setResult(info);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;
	}

	@RequestMapping("/addOrgInfo")
	@ResponseBody
	public Object addOrgInfo(@Validated({ AddOrgInfo.class }) OrgInfo orgInfo, BindingResult result,
			HttpServletRequest request) {
		ResultEntity entity = new ResultEntity();
		try {
			if (result.hasErrors()) {
				return this.getBindResult(result, entity);
			}
			managerService.addOrgInfo(orgInfo);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

	@RequestMapping("/addUserInfo")
	@ResponseBody
	public Object addUserInfo(@Validated({ UserInfo.AddUserInfo.class }) UserInfo userInfo, BindingResult result,
			HttpServletRequest request) {
		ResultEntity entity = new ResultEntity();
		try {
			if (result.hasErrors()) {
				return this.getBindResult(result, entity);
			}
			managerService.addUserInfo(userInfo);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

	@RequestMapping("/addItemInfo")
	@ResponseBody
	public Object addItemInfo(@Validated({ GradeItemInfo.AddGradeItemInfo.class }) GradeItemInfo itemInfo,
			BindingResult result, HttpServletRequest request) {
		ResultEntity entity = new ResultEntity();
		try {
			if (result.hasErrors()) {
				return this.getBindResult(result, entity);
			}
			managerService.addItemInfo(itemInfo);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

	@RequestMapping("/addBatchInfo")
	@ResponseBody
	public Object addBatchInfo(@Validated({ GradeBatchInfo.AddGradeBatchInfo.class }) GradeBatchInfo batchInfo,
			BindingResult result, HttpServletRequest request) {
		ResultEntity entity = new ResultEntity();
		try {
			if (result.hasErrors()) {
				return this.getBindResult(result, entity);
			}
			managerService.addBatchInfo(batchInfo);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

	@RequestMapping("/modifyOrgInfo")
	@ResponseBody
	public Object modifyOrgInfo(@Validated({ AddOrgInfo.class }) OrgInfo orgInfo, BindingResult result,
			HttpServletRequest request) {
		ResultEntity entity = new ResultEntity();
		try {
			if (result.hasErrors()) {
				return this.getBindResult(result, entity);
			}
			managerService.updateOrgInfo(orgInfo);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

	@RequestMapping("/modifyUserInfo")
	@ResponseBody
	public Object modifyUserInfo(@Validated({ UserInfo.ModifyUserInfo.class }) UserInfo userInfo, BindingResult result,
			HttpServletRequest request) {
		ResultEntity entity = new ResultEntity();
		try {
			if (result.hasErrors()) {
				return this.getBindResult(result, entity);
			}
			managerService.updateUserInfo(userInfo);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

	@RequestMapping("/modifyItemInfo")
	@ResponseBody
	public Object modifyItemInfo(@Validated({ GradeItemInfo.AddGradeItemInfo.class }) GradeItemInfo itemInfo,
			BindingResult result, HttpServletRequest request) {
		ResultEntity entity = new ResultEntity();
		try {
			if (result.hasErrors()) {
				return this.getBindResult(result, entity);
			}
			managerService.updateItemInfo(itemInfo);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

	@RequestMapping("/modifyBatchInfo")
	@ResponseBody
	public Object modifyBatchInfo(@Validated({ GradeBatchInfo.AddGradeBatchInfo.class }) GradeBatchInfo batchInfo,
			BindingResult result, HttpServletRequest request) {
		ResultEntity entity = new ResultEntity();
		try {
			if (result.hasErrors()) {
				return this.getBindResult(result, entity);
			}
			managerService.updateBatchInfo(batchInfo);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

	@RequestMapping("/deleteOrgInfo")
	@ResponseBody
	public Object deleteOrgInfo(OrgInfo orgInfo) {
		ResultEntity entity = new ResultEntity();
		try {
			managerService.removeOrgInfo(orgInfo);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

	@RequestMapping("/deleteUserInfo")
	@ResponseBody
	public Object deleteUserInfo(UserInfo userInfo) {
		ResultEntity entity = new ResultEntity();
		try {
			managerService.removeUserInfo(userInfo);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

	@RequestMapping("/deleteItemInfo")
	@ResponseBody
	public Object deleteItemInfo(GradeItemInfo itemInfo) {
		ResultEntity entity = new ResultEntity();
		try {
			managerService.removeItemInfo(itemInfo);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

	@RequestMapping("/score")
	@ResponseBody
	public Object score(@RequestParam("batchId") String batchId,@RequestParam("orgId") String orgId) {
		ResultEntity entity = new ResultEntity();
		try {
			entity = managerService.getUserScore(batchId,orgId);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

	@RequestMapping("/scoreAvg")
	@ResponseBody
	public Object scoreAvg(@RequestParam("batchId") String batchId,@RequestParam("orgId") String orgId) {
		ResultEntity entity = new ResultEntity();
		try {
			entity = managerService.getUserScoreAvg(batchId,orgId);
			entity = this.writeSuccess(entity);
		} catch (BusinessException e) {
			entity = this.writeError(entity, e);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			BusinessException e1 = new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
			entity = this.writeError(entity, e1);
		}
		return entity;

	}

}
