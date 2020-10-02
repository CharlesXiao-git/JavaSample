package com.java.uploadfile.util;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:config/aws.properties")
@ConfigurationProperties(prefix = "aws")
@Component
public class AWSConfiguration {
	
	private String aws_access_key_id;
	
	private String aws_secret_access_key;
	
	private String bucketName;
	
	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getAws_access_key_id() {
		return aws_access_key_id;
	}

	public void setAws_access_key_id(String aws_access_key_id) {
		this.aws_access_key_id = aws_access_key_id;
	}

	public String getAws_secret_access_key() {
		return aws_secret_access_key;
	}

	public void setAws_secret_access_key(String aws_secret_access_key) {
		this.aws_secret_access_key = aws_secret_access_key;
	}
	
	
}
