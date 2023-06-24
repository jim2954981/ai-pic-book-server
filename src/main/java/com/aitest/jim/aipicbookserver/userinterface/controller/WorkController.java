package com.aitest.jim.aipicbookserver.userinterface.controller;

import com.aitest.jim.aipicbookserver.domain.work.WorkDomain;
import com.aitest.jim.aipicbookserver.service.facade.WorkFacade;
import com.aitest.jim.aipicbookserver.userinterface.vo.BaseResponse;
import com.aitest.jim.aipicbookserver.userinterface.vo.WorkCreateRequestVO;
import com.aitest.jim.aipicbookserver.userinterface.vo.WorkVO;
import com.aitest.jim.aipicbookserver.userinterface.vo.WorkVoConverter;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Liuyi58
 * @since 2023-06-20  23:16
 **/
@RestController
@RequestMapping("/work")
public class WorkController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkController.class);
	@Resource
	private WorkFacade workFacade;
	@Resource
	private WorkVoConverter workVoConverter;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public BaseResponse<WorkVO> createPicWork(@RequestBody WorkCreateRequestVO requestVO) {
		if (Objects.isNull(requestVO)) {
			return BaseResponse.failure();
		}
		if (Strings.isNullOrEmpty(requestVO.getTitle())) {
			return BaseResponse.failure("请输入绘本标题");
		}
		if (Strings.isNullOrEmpty(requestVO.getContentAbstract())) {
			return BaseResponse.failure("请输入故事梗概");
		}
		try {
			WorkDomain workDomain = workFacade.createWork(requestVO);
			if (Objects.isNull(workDomain)) {
				return BaseResponse.failure("系统异常，请重试");
			}
			return BaseResponse.success(workVoConverter.convertFromDomain(workDomain));
		} catch (Exception e) {
			LOGGER.error("create work failed with reqeust:{}", requestVO, e);
			return BaseResponse.failure("系统异常，请重试");
		}
	}

	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public BaseResponse<List<WorkVO>> getList(@RequestParam("userId") long userId) {
		if (userId <= 0) {
			return BaseResponse.failure("请登录，游客无法查看");
		}
		List<WorkDomain> workDomains = workFacade.getWorkList(userId);
		List<WorkVO> workVOS = workDomains.stream().map(e -> workVoConverter.convertFromDomain(e)).collect(Collectors.toList());
		return BaseResponse.success(workVOS);
	}

	@RequestMapping(value = "/queryWorkById", method = RequestMethod.GET)
	public BaseResponse<WorkVO> queryWorkById(@RequestParam("id") long id) {
		if (id <= 0) {
			return BaseResponse.failure("系统异常，请重试");
		}
		WorkDomain workDomain = workFacade.queryWorkById(id);
		if (Objects.isNull(workDomain)) {
			return BaseResponse.failure("未找到该绘本，请重试");
		}
		WorkVO workVO = workVoConverter.convertFromDomain(workDomain);
		return BaseResponse.success(workVO);
	}
}
