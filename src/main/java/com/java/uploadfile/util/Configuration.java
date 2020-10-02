package com.java.uploadfile.util;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:config/images.properties")
@ConfigurationProperties(prefix = "image")
@Component
public class Configuration {
	
	private String width1;
	
	private String height1;
	
	private String width2;
	
	private String height2;
	
	private String width3;
	
	private String height3;
	
	private String keyID;
	
	private String accessKey;
	

	

	public String getKeyID() {
		return keyID;
	}

	public void setKeyID(String keyID) {
		this.keyID = keyID;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getWidth1() {
		return width1;
	}

	public void setWidth1(String width1) {
		this.width1 = width1;
	}

	public String getHeight1() {
		return height1;
	}

	public void setHeight1(String height1) {
		this.height1 = height1;
	}

	public String getWidth2() {
		return width2;
	}

	public void setWidth2(String width2) {
		this.width2 = width2;
	}

	public String getHeight2() {
		return height2;
	}

	public void setHeight2(String height2) {
		this.height2 = height2;
	}

	public String getWidth3() {
		return width3;
	}

	public void setWidth3(String width3) {
		this.width3 = width3;
	}

	public String getHeight3() {
		return height3;
	}

	public void setHeight3(String height3) {
		this.height3 = height3;
	}
	
	
	
	

}
