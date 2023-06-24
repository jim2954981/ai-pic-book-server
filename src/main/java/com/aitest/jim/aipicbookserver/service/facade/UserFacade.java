package com.aitest.jim.aipicbookserver.service.facade;

import com.aitest.jim.aipicbookserver.domain.user.UserPO;
import com.aitest.jim.aipicbookserver.service.gateway.UserInfo;
import com.aitest.jim.aipicbookserver.service.gateway.UserGateway;
import com.aitest.jim.aipicbookserver.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Liuyi58
 * @since 2023-06-18  20:29
 **/
@Service
public class UserFacade {
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserGateway userGateway;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserFacade.class);

	public UserInfo doLogin(String cCode) {
		UserInfo userInfo = userGateway.getOpenIdInfo(cCode);
		UserPO userPO = userMapper.selectByOpenId(userInfo.getOpenId());
		if (Objects.isNull(userPO)) {
			userPO = new UserPO().setWxOpenId(userInfo.getOpenId());
			userMapper.insertNewUser(userPO);
			if (Objects.isNull(userPO.getId()) || userPO.getId() <= 0) {
				LOGGER.error("insert user failed with request:{}", userInfo);
				return null;
			}
		}
		return userInfo.setUserId(userPO.getId());
	}
}
