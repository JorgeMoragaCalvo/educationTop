# Education Top Project  
This project simulates an educational center for university preparation, managing enrollments, fees, and discounts based on specific criteria. The following functionalities have been implemented:
* __Student Management__: Allows entering and listing student data.  
* __Enrollment and Payments__: Ability to register students and specify payment types.  
* __Notes Import__: Integration to import notes from an Excel file.  
* __Report Generation__: A summary report is generated with relevant information for each student.

Discounts are calculated based on the type of school, year of graduation, and average score.  

### Technical Specifications   
This project is a monolithic web application based on the layered architectural pattern. It utilizes for:  
* __Backend__: Spring Boot and PostgreSQL.
* __Frontend__: Thymeleaf.


### Deployment  
The application is deployed on an AWS server using a Terraform script for cloud server provisioning. Docker Compose is used for deployment, with the following components deployed:
* PostgreSQL database.  
* 2 replicas of the web application.  
* Nginx as a load balancer.  


### Development and Testing Automation  
For development and testing of the application, a continuous delivery pipeline in Jenkins is used to automate the process. The pipeline performs the following tasks:  
1. Automatically retrieves source code from GitHub.
2. Executes associated unit tests (Junit) for the project.
3. Encapsulates the application in a Docker container and uploads it to DockerHub.
