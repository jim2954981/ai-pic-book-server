package com.aitest.jim.aipicbookserver.userinterface.controller;

import com.aitest.jim.aipicbookserver.service.facade.UserFacade;
import com.aitest.jim.aipicbookserver.service.gateway.UserInfo;
import com.aitest.jim.aipicbookserver.userinterface.vo.BaseResponse;
import com.aitest.jim.aipicbookserver.userinterface.vo.UserInfoVO;
import com.google.common.base.Strings;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Liuyi58
 * @since 2023-06-18  18:58
 **/
@RestController
@RequestMapping("/user")
public class LoginController {
	@Resource
	private UserFacade userFacade;

	@RequestMapping("/login")
	public BaseResponse<UserInfoVO> doLogin(@RequestParam("cCode") String cCode) {
		if (Strings.isNullOrEmpty(cCode)) {
			return BaseResponse.failure();
		}
		UserInfo userInfo = userFacade.doLogin(cCode);
		if (Objects.isNull(userInfo)) {
			return BaseResponse.failure();
		}
		return BaseResponse.success(new UserInfoVO().setUserId(userInfo.getUserId()).setSessionKey(userInfo.getSessionKey()));
	}
}
