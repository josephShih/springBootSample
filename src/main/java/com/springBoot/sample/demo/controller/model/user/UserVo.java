package com.springBoot.sample.demo.controller.model.user;

import lombok.Data;

@Data
public class UserVo  {
	private String pid;
	private String name;
	private String password;
	private int age;
	private String email;
	private String otherInfo;
}
