package com.java.uploadfile.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.java.uploadfile.service.S3Service;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    
    @Value("${aws.aws_access_key_id}")
    private  String aws_access_key_id;
	
	@Value("${aws.aws_secret_access_key}")
    private  String aws_secret_access_key;
	
	@Value("${aws.bucketName}")
    private  String bucketName;
	
	S3Service s3Service = null;
	
	@Before
	public void init(){
		s3Service = S3Service.getInstance(aws_access_key_id, aws_secret_access_key, bucketName);
	}

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        FormLoginRequestBuilder login = formLogin()
            .user("user")
            .password("password");

        mockMvc.perform(login)
            .andExpect(authenticated().withUsername("user"));
    }

    @Test
    public void loginWithInvalidUserThenUnauthenticated() throws Exception {
        FormLoginRequestBuilder login = formLogin()
            .user("invalid")
            .password("invalidpassword");

        mockMvc.perform(login)
            .andExpect(unauthenticated());
    }

    @Test
    public void accessUnsecuredResourceThenOk() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk());
    }

    @Test
    public void accessSecuredResourceUnauthenticatedThenRedirectsToLogin() throws Exception {
        mockMvc.perform(get("/hello"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    public void accessSecuredResourceAuthenticatedThenOk() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void listAllBucketsTest(){
		long result = s3Service.getAllBucketInS3();
		System.out.println(result);
    }
    
    @Test
    public void uploadFileListToS3Test() throws Exception{
    	List <File> files = new ArrayList<File>();
    	File newFile = new File("./392344.0.0.gif");
    	files.add(newFile);
		int result = s3Service.uploadFileListToS3(files);
		System.out.println(result);
    }
    
    /**
     * Need to start the server to run this case.
     */
    @Test
    @Ignore
    public void uploadRestfulTest() {
 	   
 	   RestTemplate template = new RestTemplate();
 	   
 	   MultiValueMap<String, Object> parts = new LinkedMultiValueMap
 			   <String, Object>();
 	   
 	   parts.add("file", new FileSystemResource("./392344.0.0.gif"));
 	   
 	   String response = template.postForObject
 			   ("http://localhost:8080/upload",parts, String.class);
 	   
 	   System.out.println("Result : "+response);
 	   
    }
    
    /**
     * Need to start the server to run this case.
     */
    @Test
    @Ignore
    public void getBucketNumberTest() {
 	   
 	   RestTemplate template = new RestTemplate();
 	   String response = template.getForObject("http://localhost:8080/getBucketNumber", String.class);
 	   System.out.println("Result : "+response);
 	   
    }
    
    
}

