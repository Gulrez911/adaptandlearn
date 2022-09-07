package com.test.eclipseche.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.che.WorkspaceChe;
import com.assessment.common.AssessmentGenericException;
import com.assessment.common.PropertyConfig;
import com.assessment.data.User;
import com.assessment.eclipseche.config.response.WorkspaceResponse;
import com.assessment.eclipseche.services.EclipseCheService;
import com.assessment.services.CheService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestWorkspaceService {
	EclipseCheService eclipseCheService = new EclipseCheService();
	ObjectMapper mapper = new ObjectMapper();
	
	
	@Autowired
	CheService cheService;
	
	String url = "https://che-che.eclipse11.yaksha.online";
	
	@Autowired
	PropertyConfig config;
	
	@Test
	public void testCheCapacity() throws Exception{
		
		User user = new User();
		user.setEmail("test");
		user.setFirstName("abc");
		user.setLastName("def");
		user.setMobileNumber("111");
		String token = eclipseCheService.getAuthTokenKeyCloak("https://keycloak-che.eclipse11.yaksha.online/auth/realms/che/protocol/openid-connect/token", user);

		ObjectMapper mapper = new ObjectMapper();
		//String urlall = url+"/api/workspace?skipCount=0&maxItems=40";
		String urlall = "https://che-che.eclipse11.yaksha.online/api/workspace?skipCount=0&maxItems=40";
		URL url2 = new URL(urlall);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer "+token);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	
	     String data = new EclipseCheService().getResponse(conn);
	     List<WorkspaceChe> workspaceResponses = mapper.readValue(data.getBytes(), new TypeReference<List<WorkspaceChe>>() {});
	     System.out.println("size "+workspaceResponses.size());
	}
	
	
	@Test
	public void testJavaCassandra() throws Exception{
		//String url = cheService.getApiURLAndSaveDetailsForCluster("IH");
		//String url = "https://d1reqch96di5ou.cloudfront.net";
		//String url = "https://eclipse-cf.yaksha.online";
		String url = "https://che-che.eclipse6.yaksha.online";
		if(url == null){
 			throw new AssessmentGenericException("NO_CLUSTER_AVAILABLE_CHECK_CLUSTER_ADMIN");
 		}
 		eclipseCheService.setPosition(url.length());
 		url += "/api/workspace/devfile?start-after-create=false&namespace=che";
 		eclipseCheService.setUrl(url);
	
 		//String json = FileUtils.readFileToString(new File("assessment/eclipseChe/Java_Cassandra.json"));
 		String json = FileUtils.readFileToString(new File("assessment/eclipseChe/test_dotnet_codeenvy.json"));
 		
		String name = "cass"+System.currentTimeMillis();
		Integer code = name.hashCode();
		if(code < 0){
			code = code * -1;
		}
	
	json = json.replace("${APP_USER}", name);
	json = json.replace("${APP_USER_CODE}", "cass"+code);
	json = json.replace("TIME_STAMP", System.currentTimeMillis()+"");
		//json = json.replace("${APP_USER}", "a01");
		json = json.replace("${APP_DESC}", "Sample............................Project\n\n\n.........");
	json = json.replace("${GIT_CODEBASE_LINK}", "https://github.com/che-samples/console-java-simple.git");
		
		WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json);
		System.out.println(workspaceResponse.getLinks().getIde());
	}
	
	@Test
	public void testDotNetWithMySQLService() throws Exception{
		//String url = cheService.getApiURLAndSaveDetailsForCluster("IH");
		//String url = "https://d1reqch96di5ou.cloudfront.net";
		//String url = "https://eclipse-cf.yaksha.online";
		String url = "https://che-che.eclipse6.yaksha.online";
		if(url == null){
 			throw new AssessmentGenericException("NO_CLUSTER_AVAILABLE_CHECK_CLUSTER_ADMIN");
 		}
 		eclipseCheService.setPosition(url.length());
 		url += "/api/workspace/devfile?start-after-create=false&namespace=che";
 		eclipseCheService.setUrl(url);
		//String json = FileUtils.readFileToString(new File("assessment/eclipseChe/Java_FullStack.json"));
		
	//	String json = FileUtils.readFileToString(new File("assessment/eclipseChe/Aspnet_4.8.json"));
 		//String json = FileUtils.readFileToString(new File("assessment/eclipseChe/DotNet_NewEclipseChe_MONGODB.json"));
 		String json = FileUtils.readFileToString(new File("assessment/eclipseChe/dotnet_mssql.json"));
 		
		String name = "dott"+System.currentTimeMillis();
		Integer code = name.hashCode();
		if(code < 0){
			code = code * -1;
		}
	
	json = json.replace("${APP_USER}", name);
	json = json.replace("${APP_USER_CODE}", "dotnet"+code);
	json = json.replace("TIME_STAMP", System.currentTimeMillis()+"");
		//json = json.replace("${APP_USER}", "a01");
		json = json.replace("${APP_DESC}", "Sample............................Project\n\n\n.........");
	json = json.replace("${GIT_CODEBASE_LINK}", "https://github.com/che-samples/dotnet-web-simple");
		
		WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpaceWithCloudfront(json,"https://eclipse-cf.yaksha.online");
		System.out.println(workspaceResponse.getLinks().getIde());
	}
	
	@Test
	public void testStartWorkspace() throws Exception{
		String workspaceId = "workspaceraprof0f0jkme4va";
		String url = "https://eclipse-cf.yaksha.online";
		EclipseCheService cheService = new EclipseCheService();
		cheService.startWorkspace(url, workspaceId);
	}
	
	
	@Test
	public void testDotNetWithHigherVersion() throws Exception{
		String url = cheService.getApiURLAndSaveDetailsForCluster("IH");
		if(url == null){
 			throw new AssessmentGenericException("NO_CLUSTER_AVAILABLE_CHECK_CLUSTER_ADMIN");
 		}
 		eclipseCheService.setPosition(url.length());
 		url += "/api/workspace/devfile?start-after-create=false&namespace=che";
 		eclipseCheService.setUrl(url);
		//String json = FileUtils.readFileToString(new File("assessment/eclipseChe/Java_FullStack.json"));
		
		String json = FileUtils.readFileToString(new File("assessment\\eclipseChe\\DotNet_NewEclipseChe_MySQL.json"));
		
		String name = "dotnet"+System.currentTimeMillis();
		json = json.replace("${APP_USER}", name);
		
		Integer code = name.hashCode();
			if(code < 0){
				code = code * -1;
			}
		
		json = json.replace("${APP_USER}", "dotnet"+System.currentTimeMillis());
		json = json.replace("${APP_USER_CODE}", "dotnet"+code);
		//${APP_USER_CODE}
		//json = json.replace("${APP_USER}", "a01");
		json = json.replace("${APP_DESC}", "Sample............................Project\n\n\n.........");
		
		WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json);
		System.out.println(workspaceResponse.getLinks().getIde());
	}
	
	
	@Test
	public void testLoadTestEclipseChe() throws Exception{
		User user = new User();
		user.setEmail("abc@def.com");
		user.setFirstName("abc");
		user.setLastName("def");
		user.setMobileNumber("111");
		String token = eclipseCheService.getAuthTokenKeyCloak("https://keycloak-che.eclipse11.yaksha.online/auth/realms/che/protocol/openid-connect/token", user);

		try {
			for(int i=0;i<75;i++){
				//String url = cheService.getApiURLAndSaveDetailsForCluster("IH");
				String url = "https://che-che.eclipse11.yaksha.online";
		 		
		 		eclipseCheService.setPosition(url.length());
		 		//url += "/api/workspace/devfile?start-after-create=false&namespace=che";
		 		eclipseCheService.setUrl(url);
				//String json = FileUtils.readFileToString(new File("assessment/eclipseChe/Java_FullStack.json"));
				
				String json = FileUtils.readFileToString(new File("assessment/eclipseChe/Java_FullStack_NewEclipseChe.json"));
				
				json = json.replace("${APP_USER}", "def"+i+System.currentTimeMillis());
				//json = json.replace("${APP_USER}", "a01");
				json = json.replace("${APP_DESC}", "Sample............................Project\n\n\n.........");
				
				//WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json);
				WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpaceWithAccessToken(json, url, token);
				System.out.println(workspaceResponse.getLinks().getIde());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testCreateWorkSpace() throws Exception{
		try {
			//for(int i=0;i<32;i++){
				//String json = FileUtils.readFileToString(new File("assessment/eclipseChe/Java_FullStack.json"));
				
				String json = FileUtils.readFileToString(new File("assessment/eclipseChe/new_eclipseche/angular_chrome.json"));
				
				json = json.replace("${APP_USER}", "Testnew_16Oct");
				
				eclipseCheService.setPosition(url.length());
		 		url += "/api/workspace/devfile?start-after-create=false&namespace=che";
		 		eclipseCheService.setUrl(url);
				String name = "Testnew"+System.currentTimeMillis();
				Integer code = name.hashCode();
				if(code < 0){
					code = code * -1;
				}
				
				json = json.replace("${APP_USER_CODE}", "ang"+code);
				//json = json.replace("${APP_USER}", "a01");
				json = json.replace("${APP_DESC}", "Sample............................Project\n\n\n.........");
				String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJlMjNGc3kzRlI5dnRUZms3TGlkX1lQOGU0cDNoY0psM20wQTRnckIzNnJJIn0.eyJqdGkiOiI1NThmZjdiZS03NjAzLTQ4YmMtODdjZi1mMTM4YTk0NzE0ZjEiLCJleHAiOjE2MDI4NTI3MjEsIm5iZiI6MCwiaWF0IjoxNjAyODUyNDIxLCJpc3MiOiJodHRwczovL2tleWNsb2FrLWNoZS5lY2xpcHNlMTEueWFrc2hhLm9ubGluZS9hdXRoL3JlYWxtcy9jaGUiLCJzdWIiOiIwMWFmZGIwNC03NGYzLTRiMzktODUxYi1lYWFkODU1MjJkMTYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhZG1pbi1jbGkiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiI0MzRmNGE1Yi02YjViLTQ2ZTQtYjQzNy0yNWMyY2IzNjAyODAiLCJhY3IiOiIxIiwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiVGVzdCBVc2VyMiBUZXN0MiIsInByZWZlcnJlZF91c2VybmFtZSI6InRlc3QiLCJnaXZlbl9uYW1lIjoiVGVzdCBVc2VyMiIsImZhbWlseV9uYW1lIjoiVGVzdDIiLCJlbWFpbCI6Im5ld3VzZXJAZm9vLmNvbSJ9.gDjo2GpjxcFU_zfikK0uDXY2j9sHd9alYNt2j9SmZo6wAaedfWDJDhi1kcGP2CTDgGuN0ed9l1IZF9Qy_dedrMVO9q6IoX4XIu-yX38aIdsspnQarA336cL49WtN5jC0rqDML6WAla8FQVsA8Hnh0eud-rfXtp_t9UVEtV0he1q5x0wlo-iRZDMc4mnKysBq0l9W2bqYoJQ75SYnc4ZXoDe-CvR4tUwy6d9bpbNyjCbroxGnEIt62_qw0dA-wSTW_KBf0BsWlmstqUzFmzBaJNC4IkhCL7iePxYp2ifiw4A6FCHNdZRn27rd6X7BqWPuLNgDpPLHRGi6RpaEkRcS9Q";
				WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json, token);
				System.out.println(workspaceResponse.getLinks().getIde());
			//}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateWorkSpacePHP() throws Exception{
		String json = FileUtils.readFileToString(new File("assessment/eclipseChe/PHP_MySQL.json"));
		json = json.replace("${APP_USER}", "php-3456-67894224"+System.currentTimeMillis());
		//json = json.replace("${APP_USER}", "a01");
		json = json.replace("${APP_DESC}", "Sample............................Project\n\n\n.........");
		
		WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json);
		System.out.println(workspaceResponse.getLinks().getIde());
	}
	
	@Test
	public void testCreateWorkSpaceAngularJavascript() throws Exception{
		String json = FileUtils.readFileToString(new File("assessment/eclipseChe/ANGULAR_JAVASCRIPT_MYSQL.json"));
		json = json.replace("${APP_USER}", "angular-3456-67894224"+System.currentTimeMillis());
		//json = json.replace("${APP_USER}", "a01");
		json = json.replace("${APP_DESC}", "Sample............................Project\n\n\n.........");
		
		WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json);
		System.out.println(workspaceResponse.getLinks().getIde());
	}
	
	@Test
	public void testCreateWorkSpaceCSharp() throws Exception{
		String json = FileUtils.readFileToString(new File("assessment/eclipseChe/dotnet_visual_studio.json"));
		json = json.replace("${APP_USER}", "ado-"+System.currentTimeMillis());
		//json = json.replace("${APP_USER}", "a01");
		json = json.replace("${APP_DESC}", "Sample............................Project\n\n\n.........");
		
		WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json);
		System.out.println(workspaceResponse.getLinks().getIde());
	}
	
	
	@Test
	public void testWorkspacesFetch() throws Exception{
		URL url2 = new URL("https://che-che.eclipse.yaksha.online/api/workspace?skipCount=0&maxItems=900");
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	
	     String data = getResponse(conn);
	     List<WorkspaceResponse> workspaceResponses = mapper.readValue(data.getBytes(), new TypeReference<List<WorkspaceResponse>>() {});
//	     for(WorkspaceResponse response : workspaceResponses){
//	    	 System.out.println(response.getLinks().getIde());
//	     }
	     
	     	     	for(WorkspaceResponse response : workspaceResponses){
	     	     		Map<String, Object> map = response.getAdditionalProperties();
	     	     		Map metadata = (Map) map.get("devfile");
	     	     		Map name = (Map) metadata.get("metadata");
	     	     		String nm = (String)name.get("name");
	     	     		System.out.println("name "+nm);
	     	     		
	     		if(nm.contains("lemon") ){
	     			System.out.println(nm);
	     			deleteWorkspace(response.getId());
	     		}
	     		
	     	}
	     System.out.println(workspaceResponses.size());
	}
	
	private void deleteWorkspace(String id) throws Exception{
		String url = "https://che-che.eclipse.yaksha.online/api/workspace/"+id;
		URL url2 = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("DELETE");
		conn.setRequestProperty("Content-Type", "application/json");
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String data = getResponse(conn);
		System.out.println(data);
	}
	
	public String getResponse(HttpURLConnection con) {
		if(con!=null){
			
			try {
				
			   BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(con.getInputStream()));
						
			   String input;
			   String output="";
						
			   while ((input = br.readLine()) != null){
				   output +=input;
			   }
			   br.close();
			   return output;
						
			} catch (IOException e) {
			   e.printStackTrace();
			}
					
		       }
				
		   return null;
	}
	
	@Test
	public void testGetWorkspaces()throws Exception{
		URL url2 = new URL("https://che-che.eclipse.vijaygalaxy.org/api/workspace?skipCount=0&maxItems=100");
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	
	     String data = getResponse(conn);
	     List<WorkspaceChe> workspaceResponses = mapper.readValue(data.getBytes(), new TypeReference<List<WorkspaceChe>>() {});
	     System.out.println(workspaceResponses.size());
	     
	  
	}
	@Test
	public void testGetKeyCloakAccessToken() throws Exception{
		User user = new User();
		user.setEmail("abc@def.com");
		user.setFirstName("abc");
		user.setLastName("def");
		user.setMobileNumber("111");
		String res = eclipseCheService.getAuthTokenKeyCloak("https://keycloak-che.eclipse11.yaksha.online/auth/realms/che/protocol/openid-connect/token", user);
		System.out.println(res);
	}
	
	@Test
	public void testLdpUserCreate() throws Exception{
		EclipseCheService eclipseCheService = new EclipseCheService();
		User user = new User();
		user.setEmail("171@oct.com");
		user.setFirstName("17");
		user.setLastName("oct");
		//user.setMobileNumber("111");
		System.out.println(eclipseCheService.createLdapUser(config, user));
	}
	
	@Test
	public void testLdpUserExists(){
		EclipseCheService eclipseCheService = new EclipseCheService();
		System.out.println(eclipseCheService.checkIfUserExists(config, "abc@def.com", "P-1719430598"));
	}
}
