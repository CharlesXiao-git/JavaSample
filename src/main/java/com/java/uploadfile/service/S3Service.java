package com.java.uploadfile.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * 
 * @author admin
 *
 */
public class S3Service {
	//instance of S3Service
	private static S3Service instance;
	//connect to S3
	private static AmazonS3 s3Client = null;
	//key_ID
	private String aws_access_key_id;
	//access_Key
	private String aws_secret_access_key;
	//bucketName
	private String bucketName;

	private S3Service() {
	}

	/**
	 * Using singleton to reduce connection to the S3 server 
	 * @param aws_access_key_id
	 * @param aws_secret_access_key
	 * @param bucketName
	 * @return
	 */
	public static synchronized S3Service getInstance(String aws_access_key_id,String aws_secret_access_key,String bucketName) {
		if (instance == null) {

			instance = new S3Service();
			
			instance.aws_access_key_id = aws_access_key_id;
			
			instance.aws_secret_access_key = aws_secret_access_key;
			
			instance.bucketName = bucketName;

			instance.setS3Client();
		}

		return instance;
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

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	private void setS3Client() {
		 s3Client = new AmazonS3Client(new BasicAWSCredentials(
				aws_access_key_id, aws_secret_access_key));
		 
		System.out.println("===========================================");
		System.out.println("Getting Started with Amazon S3");
		System.out.println("===========================================\n");
			
		Region defaultRegion = Region.getRegion(Regions.DEFAULT_REGION);
			s3Client.setRegion(defaultRegion);

	}

	/**
	 * list all Buckets
	 * @return
	 */
	public int listAllBuckets(){
		System.out.println("Listing buckets");
		int count = 0 ;
		for (Bucket bucket : s3Client.listBuckets()) {
			count ++ ;
			//System.out.println(" - " + bucket.getName());
		}
		return count;
		
	}
	
	public void uploadFileToS3(File file) throws Exception {
		// String key = "MyObjectKey" + UUID.randomUUID();
		String key = "MyObjectKey" + UUID.randomUUID();

		/*
		 * Upload an object to your bucket - You can easily upload a file to S3,
		 * or upload directly an InputStream if you know the length of the data
		 * in the stream. You can also specify your own metadata when uploading
		 * to S3, which allows you set a variety of options like content-type
		 * and content-encoding, plus additional metadata specific to your
		 * applications.
		 */
		
		s3Client.putObject(new PutObjectRequest(bucketName, key, file));
		System.out.println("Uploading a new object to S3 from a file\n");

	}
	
	/**
	 * upload the files in batch, so it is efficient
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public int uploadFileListToS3(List<File> files) throws Exception {
		// String key = "MyObjectKey" + UUID.randomUUID();
		String key = "MyObjectKey" + UUID.randomUUID();
		int count = 0 ;
		for(File file : files){
			s3Client.putObject(new PutObjectRequest(bucketName, key, file));
			count ++ ;
		}
		System.out.println("Uploading "+count +" new objects to S3 from a file\n");
		return count;
	}
	
	/**
	 * Get all Bucket and display the count on page.
	 * @return
	 */
	public long getAllBucketInS3(){
		long sum = 0 ;
		ObjectListing objects = s3Client.listObjects(bucketName);  
		do   
		{  
		    for (S3ObjectSummary objectSummary : objects.getObjectSummaries())   
		    {  
		        //System.out.println("Object: " + objectSummary.getKey());  
		        sum ++ ;
		    }                                 
		    objects = s3Client.listNextBatchOfObjects(objects);  
		} while (objects.isTruncated()); 
		
		return sum;
	}
	
	
	public static void main(String[] args) throws IOException {

		
	}


}
