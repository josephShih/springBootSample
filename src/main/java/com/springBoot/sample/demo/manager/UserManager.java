package com.springBoot.sample.demo.manager;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.springBoot.sample.demo.controller.model.user.UserVo;
import com.springBoot.sample.demo.repository.sampleDB.model.User;
import com.springBoot.sample.demo.repository.sampleDB.read.QueryUserRepository;
import com.springBoot.sample.demo.repository.sampleDB.write.UpdateUserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserManager {

	@Autowired
	QueryUserRepository userRepository;
	
	@Autowired
	UpdateUserRepository updateRepository;
	
	public UserVo getUserByPid(String pid) {
		User user = userRepository.findByPid(pid);
		UserVo profile = new UserVo();
		profile.setName(user.getName());
		profile.setEmail(user.getEmail());
		profile.setOtherInfo("otherInfo");
		profile.setAge(user.getAge());
		return profile;
	}
	
	public Page<User> getAllUser(int page){
		PageRequest paging = PageRequest.of(page, 10);
		
		Page<User> result = userRepository.findAll(paging);
		log.info("query result total = "+ result.getSize());
		return result;
	}
	
	public User updateUserAge(String pid, int age) {
		User user = userRepository.findByPid(pid);
		user.setAge(age);
		User result = updateRepository.save(user);
		return result;
	}
	
	
	public User addUser(UserVo user) {
		User data = new User();
		data.setName(user.getName());
		data.setAge(user.getAge());
		data.setEmail(user.getEmail());
		data.setPassword(user.getPassword());
		data.setPid(user.getPid());
		data.setCreateDate(new Date());
		User result = updateRepository.save(data);
		result.setPassword("");
		return result;
	}
	
}
