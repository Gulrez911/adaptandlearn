package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.User;
import com.assessment.data.UserType;
import com.assessment.repositories.UserRepository;
import com.assessment.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestUser {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRep;
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testaddUser()
	{
		User user=new User();
		user.setEmail("cgi@iiht.com");
		user.setMobileNumber("989878");
		user.setFirstName("Tikam");
		user.setLastName("Singh");
		user.setPassword("12345");
		user.setCompanyId("CGI");
		user.setCompanyName("CGI");
		user.setDepartment("IT");
		user.setUserType(UserType.ADMIN);
		userService.addUser(user);
	}
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testaddUserAdmin()
	{
		User user=new User();
		user.setEmail("system@iiht.com");
		user.setMobileNumber("989878");
		user.setFirstName("System");
		user.setLastName("Admin");
		user.setPassword("1234");
		user.setCompanyId("IH");
		user.setCompanyName("IIHT");
		user.setDepartment("IT");
		user.setUserType(UserType.ADMIN);
		userService.addUser(user);
	}
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testGetUserLmsstudentsColl()
	{
		List<String> res = userRep.findInstituteGradeClassifier("IH", "SSRVM");
		for(String str: res){
			System.out.println(str);
		}
	}
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testaddUserLmsAdmin()
	{
		User user=new User();
		user.setEmail("lmsadmin@eassess.com");
		user.setMobileNumber("989878");
		user.setFirstName("LMS");
		user.setLastName("Student1");
		user.setPassword("12345");
		user.setCompanyId("IH");
		user.setCompanyName("IIHT");
		user.setDepartment("Training");
		user.setUserType(UserType.LMS_ADMIN);
		user.setCollegeName("SSRVM");
		user.setGrade("8");
		user.setClassifier("D");;
			if(userService.findByPrimaryKey("lmsstudent2@eassess.com", "IH") == null)
			{
				userService.addUser(user);
			}
			else{
				userService.updateUser(user);
			}
		
		
	}
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testaddUserLmsStudent()
	{
		User user=new User();
		user.setEmail("lmsstudent2@eassess.com");
		user.setMobileNumber("989878");
		user.setFirstName("LMS");
		user.setLastName("Student1");
		user.setPassword("12345");
		user.setCompanyId("IH");
		user.setCompanyName("IIHT");
		user.setDepartment("Training");
		user.setUserType(UserType.LMS_STUDENT);
		user.setCollegeName("SSRVM");
		user.setGrade("8");
		user.setClassifier("D");;
			if(userService.findByPrimaryKey("lmsstudent2@eassess.com", "IH") == null)
			{
				userService.addUser(user);
			}
			else{
				userService.updateUser(user);
			}
		
		
		user=new User();
		user.setEmail("lmsstudent3@eassess.com");
		user.setMobileNumber("989878");
		user.setFirstName("LMS");
		user.setLastName("Student1");
		user.setPassword("12345");
		user.setCompanyId("IH");
		user.setCompanyName("IIHT");
		user.setDepartment("Training");
		user.setUserType(UserType.LMS_STUDENT);
		user.setCollegeName("SSRVM");
		user.setGrade("8");
		user.setClassifier("C");;
		if(userService.findByPrimaryKey("lmsstudent3@eassess.com", "IH") == null)
		{
			userService.addUser(user);
		}
		else{
			userService.updateUser(user);
		}
		
		user=new User();
		user.setEmail("lmsstudent4@eassess.com");
		user.setMobileNumber("989878");
		user.setFirstName("LMS");
		user.setLastName("Student1");
		user.setPassword("12345");
		user.setCompanyId("IH");
		user.setCompanyName("IIHT");
		user.setDepartment("Training");
		user.setUserType(UserType.LMS_STUDENT);
		user.setCollegeName("SSRVM");
		user.setGrade("7");
		user.setClassifier("C");;
		if(userService.findByPrimaryKey("lmsstudent4@eassess.com", "IH") == null)
		{
			userService.addUser(user);
		}
		else{
			userService.updateUser(user);
		}
		
		user=new User();
		user.setEmail("lmsstudent5@eassess.com");
		user.setMobileNumber("989878");
		user.setFirstName("LMS");
		user.setLastName("Student1");
		user.setPassword("12345");
		user.setCompanyId("IH");
		user.setCompanyName("IIHT");
		user.setDepartment("Training");
		user.setUserType(UserType.LMS_STUDENT);
		user.setCollegeName("SSRVM");
		user.setGrade("6");
		user.setClassifier("C");;
		if(userService.findByPrimaryKey("lmsstudent5@eassess.com", "IH") == null)
		{
			userService.addUser(user);
		}
		else{
			userService.updateUser(user);
		}
		
		user=new User();
		user.setEmail("lmsstudent6@eassess.com");
		user.setMobileNumber("989878");
		user.setFirstName("LMS");
		user.setLastName("Student1");
		user.setPassword("12345");
		user.setCompanyId("IH");
		user.setCompanyName("IIHT");
		user.setDepartment("Training");
		user.setUserType(UserType.LMS_STUDENT);
		user.setCollegeName("SSRVM");
		user.setGrade("5");
		user.setClassifier("C");;
		if(userService.findByPrimaryKey("lmsstudent6@eassess.com", "IH") == null)
		{
			userService.addUser(user);
		}
		else{
			userService.updateUser(user);
		}
		
		
	}
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testupdateUser()
	{
		User user=new User();
		user.setEmail("new@admin.com");
		user.setMobileNumber("989878");
		user.setFirstName("IIHT");
		user.setLastName("Admin");
		user.setPassword("1234");
		user.setCompanyId("IH");
		user.setCompanyName("IIHT");
		user.setDepartment("IT");
		user.setUserType(UserType.ADMIN_NEW);
		userService.addUser(user);
	}
	

}
