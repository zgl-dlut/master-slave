package com.zgl.masterslave.mapper;

import com.zgl.masterslave.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author zgl
 * @date 2019/4/2 上午10:15
 */
public interface UserMapper {

	void insertOne(User user);

	User selectOneById(@Param("id") String id);
}