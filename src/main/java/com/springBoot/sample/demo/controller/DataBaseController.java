package com.springBoot.sample.demo.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springBoot.sample.demo.controller.model.PagingVo;
import com.springBoot.sample.demo.controller.model.ResultVo;
import com.springBoot.sample.demo.controller.model.SuccessResultVo;
import com.springBoot.sample.demo.controller.model.user.UserVo;
import com.springBoot.sample.demo.manager.UserManager;
import com.springBoot.sample.demo.repository.sampleDB.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Api(tags="DB")
@RestController
@RequestMapping("db")
@Slf4j
public class DataBaseController {
	
	@Autowired
	UserManager userManager;
	
	private <T> SuccessResultVo prepareSuccessResult(T data) {
		List<T> list = new ArrayList<T>();
		list.add(data);
		SuccessResultVo result = new SuccessResultVo();
		result.setData(list);
		return result; 
	}
	
	@ApiOperation("使用者資料")
	@GetMapping("/user/{pid}")
	public SuccessResultVo getUser(@PathVariable String pid) {
		UserVo profile = null;
		try {
			profile = userManager.getUserByPid(pid);
		} catch (Exception e) {
			log.error("exception ="+ e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"customer message"); 
		}
		SuccessResultVo result = prepareSuccessResult(profile);
		return result;
	}
	
	@ApiOperation("分頁取得所有使用者")
	@GetMapping("/users")
	public SuccessResultVo getUsers(@ApiParam(value = "頁數 從 0 開始 " , example = "0") @RequestParam("p") int page) {
		Page<User> userData =  userManager.getAllUser(page);
		System.out.println("data =="+ userData);
		PagingVo paging = new PagingVo();
		paging.setOffset(userData.getSize());
		paging.setPage(userData.getNumberOfElements());
		paging.setTotalPage(userData.getTotalPages());
		SuccessResultVo result = new SuccessResultVo();
		result.setData(userData.getContent());
		result.setPaging(paging);
		return result;
	}
	
	
	
	@Operation(summary = "更新使用者年齡")
	@GetMapping("/user/{pid}/u/{age}")
	public SuccessResultVo updateUser(@PathVariable("pid") String pid,@PathVariable("age") int age) {
		User user = userManager.updateUserAge(pid, age);
		SuccessResultVo result =  prepareSuccessResult(user);
		return result;
	}
	
	@Operation(summary = "新增使用者")
	@PostMapping(path = "/user/add", produces= "application/json")
	public SuccessResultVo insertUser(@RequestBody UserVo user) {
		User addResult =  userManager.addUser(user);
		SuccessResultVo result =  prepareSuccessResult(addResult);
		return result;
	}
	
}
