package com.springBoot.sample.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.springBoot.sample.demo.controller.model.FormDataVo;
import com.springBoot.sample.demo.controller.model.ResultVo;
import com.springBoot.sample.demo.util.SSMClient;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "common")
@RestController
@RequestMapping("api")
@Slf4j
public class SampleRestController {
	
	@Autowired
	SSMClient ssmClient;
	
	@Operation(summary = "不指定 requestType，回傳值為字串")
	@RequestMapping("/helloworld")
	public String hello() {
		log.info("hello word log");
		return "hello world";
	}
	
	@Operation(summary = "錯誤訊息")
	@GetMapping("/exception")
	public String helloException() {
		log.error("404 error log");
		// 回傳 404  錯誤訊息
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"customer message");
	}
	
	@Operation(summary = "物件回傳值")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "查無此頁"),
		@ApiResponse(responseCode = "500", description = "Server 發生錯誤")
	})
	@GetMapping("/result")
	public ResultVo getResult() {
		ResultVo result = new ResultVo();
		result.setMessage("success message");
		result.setStatus("200");
		return result;
	}
	
	@Operation(summary = "url path  帶參數查詢")
	@GetMapping("/pathdata/{pid}")
	public ResultVo getPathData(@PathVariable String pid) {
		System.out.println("pid=="+ pid);
		ResultVo result = new ResultVo();
		result.setMessage("path data = " + pid);
		result.setStatus("200");
		return result;
	}
	
	@Operation(summary = "form data 查詢")
	@PostMapping(path = "/formdata", produces= "application/json")
	public FormDataVo postParam(@RequestBody FormDataVo data) {
		System.out.println("name =="+ data.getNickName());
		
		return data;
	}
	
	@Operation(summary = "頁面重導向")
	@GetMapping("/redirectTo")
	// request path = domian:port/api/redirectTo?queryData=xx&info=yy
	public RedirectView redirectTo(RedirectAttributes attributes,HttpServletResponse res, @RequestParam String queryData, @RequestParam("info") String data ) {
		log.info("info message");
		// 直接使用物件名稱
		log.info("queryData===" + queryData);
		// 使用 name attribute
		log.info("info===" + data);
		attributes.addAttribute("attr1", queryData);
		attributes.addAttribute("attr", data);
		return new RedirectView("http://google.com.tw");
	}
	@GetMapping("/getParameter")
	public String getSsmParameter() {
		String result = ssmClient.getParameter("/cloud-rds/db0050101_f0113_wi_aws", true);
		return result;
	}
}
