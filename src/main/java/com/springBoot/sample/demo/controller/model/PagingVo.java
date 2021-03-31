package com.springBoot.sample.demo.controller.model;

import lombok.Data;

@Data
public class PagingVo {
	private int totalPage;
	private int page;
	private int offset;
}
