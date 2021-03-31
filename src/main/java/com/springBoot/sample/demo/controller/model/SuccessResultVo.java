package com.springBoot.sample.demo.controller.model;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class SuccessResultVo {
	private HttpStatus status = HttpStatus.OK;
	protected List<?> data; 
	private PagingVo paging;
}
