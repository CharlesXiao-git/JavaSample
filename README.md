# Restful FileUpload service exercise

#################### Requirements 

As a member of the engineering team, you are asked to write a web application that accepts image upload via RESTful API. The application needs to  
Accept the image data via RESTful interface

1. Crop the image into different dimensions, including (100x100, 500x500, 800x800 in pixels)
2. Store each dimension on to S3 bucket 
3. Here is the S3 bucket credentials (key: AKIAI7CNOJVDFGS3GKKQ, pass: oSnlKwhhRpvz3S/booMG/+aQwQPOwsvw6ZSGLdCm)
   Feel free to use your own AWS account
   
   
In this exercise, you need to 
1. Implement the web application using your preferred framework and tech stack
2. Provide instruction and test solution for your submission
3. Please spend no more than 2 days for this exercise

#################### Solution

I would like to implement a 'Full Stack Web Application' in this exercise. It look like simple, but has many parts
can be extended.

# Skills used in this exercise
   Backend
1. Spring Boot.       As Spring boot can quick start a web application.
2. Spring MVC         To develop the Restful API
3. Spring Security    To protect the Restful API
3. Maven			  To manage the Jar files.
4. AWS				  To store the images 
5. JUnit			  To test the code and generate test report	
   Frontend
1. Angular            Using MVC to develop the front end. 
2. BootStrap          User-friendly UI Interface.

#################### Develop the application

#  First Stage
1. Using Spring MVC as restful framework to develop the Restful API
2. When get the image from client, develop a core java function to resize the image to three images.
3. Using AWS SDK to upload the images to AWS S3 server.
4. Develop a Restful client to upload the image to server. 

#  Second Stage
1. For the security consideration, it is not good to make all the persons access all the resources.
   Choose Spring Security to protect the Restful API
2. Develop a login function to give person permision. Only the allowed URL can access the application
3. Develop some test cases on the security part

#  Third Stage
1. Develop a frontend framework, and the frontend developer do not need to wait for the backend.
2. Using Angular and Node.js to develop the javascript

#################### Development Planning
1. Using half a day to finish the first stage.
2. Using a day to finish the second stage.
3. Using half a day to work on the frontend
4. Using half a day to test the system

#################### Prerequisites:
- Install Maven and JDK1.8

#################### Run and Test the application
1.mvn clean package spring-boot:run
2.Using http://127.0.0.1:8080/file to upload image(MaxFileSize 512k) to AWS S3
3.http://127.0.0.1:8080 to visit home
4. Click here to login page
5.Using user/password to login the system
6. (Using Angular&BootStrap to improve)Visit http://127.0.0.1:8080/angular  and click upload to load the image to S3 and can get the total buckets in the servers.
   When upload a image to the server, a new bucket will be created in S3. So the count will plus 1.
   
#################### Need to consider on the development
1. Design Pattern. Using Singleton on S3 connection, so we do not need to create connect all the time to access the S3. Also MVC Design Pattern is used.
2. Dependence Injecttion. Using Spring to manage the DI
3. Spring Boot customize reading properties file
4. Handle exception in controller layer, and other layers throws all the exception out.
5. Frontend seperate the Backend on the development


#################### Extention on the development
1. Develop more function on S3
2. Develop more function on frontend
3. Using DB to configuration the security

#################### Get Test Result
From testResult folder to get screenshot of the test result.


