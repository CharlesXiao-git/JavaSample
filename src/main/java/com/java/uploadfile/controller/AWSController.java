package com.java.uploadfile.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.java.uploadfile.service.S3Service;


@Controller
public class AWSController {
	
	@Value("${aws.aws_access_key_id}")
    private  String aws_access_key_id;
	
	@Value("${aws.aws_secret_access_key}")
    private  String aws_secret_access_key;
	
	@Value("${aws.bucketName}")
    private  String bucketName;
	
	@RequestMapping(value = "/getBucketNumber", method = RequestMethod.GET)
	@ResponseBody
	public String getBucketNumber() {
		
		S3Service s3Service = S3Service.getInstance(aws_access_key_id, aws_secret_access_key, bucketName);
		
		long result = s3Service.getAllBucketInS3();

		return result + "";
	}
	
}