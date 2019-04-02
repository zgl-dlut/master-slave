package com.zgl.masterslave.controller;

import com.alibaba.fastjson.JSON;
import com.zgl.masterslave.mapper.UserMapper;
import com.zgl.masterslave.model.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zgl
 * @date 2019/4/2 上午10:35
 */
@RestController
@RequestMapping("/master-slave")
public class UserController {

	@Resource
	private UserMapper userMapper;

	@PostMapping("/insert")
	public String insertOne(@RequestBody User user){
		userMapper.insertOne(user);
		return "SUCCESS";
	}

	@GetMapping("/select")
	public User selectOne(@RequestParam String id){
		return userMapper.selectOneById(id);
	}

	public static void main(String[] args) {
		User user=User.builder().userName("zyy").age(28).birthday(new Date()).id("123456").password("1111111").sex("female").build();
		System.out.println(JSON.toJSONString(user));
	}
}