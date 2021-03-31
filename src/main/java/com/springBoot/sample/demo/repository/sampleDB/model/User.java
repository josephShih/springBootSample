package com.springBoot.sample.demo.repository.sampleDB.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
	@Id
	@Column(name= "pid", length = 20)
	private String pid;
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
	@Column(name="age")
	private int age;
	@Column(name="email")
	private String email;
	@Column(name="create_date")
	private Date createDate;
}
