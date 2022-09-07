package com.assessment.webservices.newlookandfeel;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.common.newlookandfeel.CommonUtil;
import com.assessment.common.util.RSAEncrypterDecrypter;
import com.assessment.data.User;
import com.assessment.data.UserType;
import com.assessment.services.UserService;
import com.assessment.web.dto.newlookandfeel.AuthenticationRequest;
import com.assessment.web.dto.newlookandfeel.AuthenticationResponse;
@Controller
@RequestMapping(CommonUtil.API_Version)
public class AuthenticationService {
	
@Autowired	
UserService userService;	
	

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/authenticateAdmin", method = RequestMethod.POST)
	@ResponseBody
	public AuthenticationResponse authenticateAdmin(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response, HttpServletRequest request){
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
		response.addHeader("Access-Control-Expose-Headers", "xsrf-token");	
			if(authenticationRequest.getUser() == null){
				authenticationResponse.setError("No_User_Passed");
			}
			
			if(authenticationRequest.getPassword() == null){
				authenticationResponse.setError("No_Password_Passed");
			}
			
			if(authenticationRequest.getCompanyName() == null){
				authenticationResponse.setError("No_Company_Passed");
			}
			
			
		User user = userService.authenticate(authenticationRequest.getUser(), authenticationRequest.getPassword(), authenticationRequest.getCompanyName());
			if(user == null){
				authenticationResponse.setError("INVALID_CREDENTIALS");
			}
			
			if(user != null){
				//System.out.println(user.getEmail()+" "+user.getUserType());
			}
			else{
				//System.out.println("null user");
				return authenticationResponse;
			}
			
			
			if(user.getUserType().getType().equals(UserType.ADMIN.getType()) || user.getUserType().getType().equals(UserType.ADMIN_NEW.getType())){
				String token = ""+System.currentTimeMillis();
				try {
					//System.out.println("1");
					RSAEncrypterDecrypter encrypterDecrypter = RSAEncrypterDecrypter.getRSAEncrypterDecrypter();
					//System.out.println("2");
					PrivateKey privateKey = encrypterDecrypter.getPrivate("KeyPair/privateKey");
					//System.out.println("3");
			        PublicKey publicKey = encrypterDecrypter.getPublic("KeyPair/publicKey");

					token = encrypterDecrypter.encryptText(token, privateKey);
					//System.out.println("4");
					authenticationResponse.setToken(token);
					authenticationResponse.setError("NA");
					System.out.println("toekn passed in response of auth service "+token);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					authenticationResponse.setError("Authentication_Failed");
				}
			}
			else{
				authenticationResponse.setError("Not_Admin_User");
			}
//			response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200,http://beforesubmit.com/**,*/**");
//			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//			response.setHeader("Access-Control-Max-Age", "3600");
//			response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
//			response.addHeader("Access-Control-Expose-Headers", "xsrf-token");	
		return authenticationResponse;
	}

}
