package com.aitest.jim.aipicbookserver.service.mapper;

import com.aitest.jim.aipicbookserver.domain.user.UserPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Liuyi58
 * @since 2023-06-18  19:38
 **/
@Repository
public interface UserMapper {
	UserPO selectByOpenId(@Param("openId") String openId);

	void insertNewUser(@Param("user")UserPO userPO);
}

