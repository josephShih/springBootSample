package com.springBoot.sample.demo.util;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementAsyncClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;


@Component
public class SSMClient {
	
	public String getParameter(String key, boolean encryption) {
		AWSSimpleSystemsManagement ssmClient = AWSSimpleSystemsManagementAsyncClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_1)
				.withCredentials(new DefaultAWSCredentialsProviderChain()).build();
		GetParameterRequest getParameterRequest = new GetParameterRequest().withName(key).withWithDecryption(encryption);
		GetParameterResult result = ssmClient.getParameter(getParameterRequest);
		System.out.println("ssmClient==");
		return result.getParameter().getValue();
	}
}
