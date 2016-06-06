package cn.com.hzbank.grade.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hzbank.grade.exception.BusinessException;
import cn.com.hzbank.grade.exception.BusinessExceptionEnum;
import cn.com.hzbank.grade.service.GradeService;
import cn.com.hzbank.grade.web.bean.ResultEntity;

@Controller
@RequestMapping("/common")
public class GradeController extends BaseController {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	GradeService gradeService;

	@RequestMapping("/indexCommon")
	public String indexCommon() throws Exception {
		return "common/indexCommon";
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
}
