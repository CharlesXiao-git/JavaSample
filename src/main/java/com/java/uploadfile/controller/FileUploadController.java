package com.java.uploadfile.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.java.uploadfile.service.ImageResizerService;
import com.java.uploadfile.service.S3Service;

@Controller
public class FileUploadController {
	
	@Value("${image.width1}")
    private int width1;
	
	@Value("${image.height1}")
    private int height1;
	
	@Value("${image.width2}")
    private int width2;
	
	@Value("${image.height2}")
    private int height2;
	
	@Value("${image.width3}")
    private int width3;
	
	@Value("${image.height3}")
    private int height3;
	
	@Value("${aws.aws_access_key_id}")
    private  String aws_access_key_id;
	
	@Value("${aws.aws_secret_access_key}")
    private  String aws_secret_access_key;
	
	@Value("${aws.bucketName}")
    private  String bucketName;
	
	@Autowired
	ImageResizerService imageResizer;
	
	@RequestMapping(value="/upload", method= RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(@RequestParam("file")MultipartFile file){
		int count = 0;
		long sumBucket = 0 ;
		if(!file.isEmpty()){
			try {
				
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
				out.write(file.getBytes());
				
				imageResizer = new ImageResizerService();
				
				File image1 = imageResizer.resizeImage(new File(file.getOriginalFilename()).getAbsolutePath(), "./"+width1+file.getOriginalFilename(), width1, height1);
				
				File image2 =  imageResizer.resizeImage(new File(file.getOriginalFilename()).getAbsolutePath(), "./"+width2+file.getOriginalFilename(), width2, height2);
				
				File image3 = imageResizer.resizeImage(new File(file.getOriginalFilename()).getAbsolutePath(), "./"+width3+file.getOriginalFilename(), width3, height3);
				
				
				S3Service s3Service = S3Service.getInstance(aws_access_key_id, aws_secret_access_key, bucketName);
				
				List <File> files = new ArrayList<File>();
				
				files.add(image1);
				
				files.add(image2);
				
				files.add(image3);
				
				count = s3Service.uploadFileListToS3(files);
				sumBucket = s3Service.getAllBucketInS3();
				
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "failed "+e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "failed "+e.getMessage();
			} catch (Exception e) {
				
				e.printStackTrace();
				return "failed "+e.getMessage();
			}
			return "Succeed in uploading : " + count + " new files to S3. and Total : " + sumBucket + " Buckets !" ;
		}else{
			return "file is empty!";
		}
	}
	
	
	@Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();
        
        factory.setMaxFileSize("512KB"); 
     
        factory.setMaxRequestSize("10MB");  
      
        return factory.createMultipartConfig();  
    }  
	
	
}