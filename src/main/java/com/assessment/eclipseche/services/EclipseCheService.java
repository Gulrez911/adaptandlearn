package com.assessment.eclipseche.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assessment.common.PropertyConfig;
import com.assessment.data.User;
import com.assessment.eclipseche.config.response.WorkspaceResponse;
import com.assessment.web.dto.KeyCloakAccessTokenDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class EclipseCheService {
//String url = "http://13.233.2.169:8080/api/workspace?start-after-create=false&namespace=che";
	//String url = "http://165.22.223.25:8080/api/workspace?start-after-create=false&namespace=che";
	
	String url = "https://che-che.eclipse3.yaksha.online/api/workspace/devfile?start-after-create=false&namespace=che";
	//http://52.66.22.44:8080
	//String url = "http://52.66.22.44:8080/api/workspace?start-after-create=false&namespace=che";
	
	Logger logger = LoggerFactory.getLogger(EclipseCheService.class);
	
ObjectMapper mapper = new ObjectMapper();

Integer position = 0;


public WorkspaceResponse createWorkSpace(String  workspace, String authToken) throws Exception{
	//workspace.setName(user);
	URL url2 = new URL(url);
	HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
	conn.setRequestMethod("POST");
	conn.setRequestProperty("Authorization", "Bearer "+authToken);
	conn.setRequestProperty("Content-Type", "application/json");
	//conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
	conn.setRequestProperty("Accept","application/json");
	mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	//String postData = mapper.writeValueAsString(workspace);
	// String post = postData.toString();
	 String postData = workspace;
     byte[] postDataBytes = postData.toString().getBytes("UTF-8");
     conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
     conn.setDoOutput(true);
     conn.getOutputStream().write(postDataBytes);
     String data = getResponse(conn);
     System.out.println(data);
     WorkspaceResponse workspaceResponse = mapper.readValue(data.getBytes(), WorkspaceResponse.class);
     String first = "";
     try {
		//first = workspaceResponse.getLinks().getIde().substring(0, workspaceResponse.getLinks().getIde().indexOf("org")+4);
    	 first = workspaceResponse.getLinks().getIde().substring(0, position+1);
		// String last = workspaceResponse.getLinks().getIde().substring(workspaceResponse.getLinks().getIde().indexOf("org")+4, workspaceResponse.getLinks().getIde().length());
		 String last = workspaceResponse.getLinks().getIde().substring(position+1, workspaceResponse.getLinks().getIde().length());
	     String intermediate = "dashboard/#/ide/";
	     String url = first + intermediate + last;
	     workspaceResponse.getLinks().setIde(url);
    	// System.out.println("workspace url************* is "+workspaceResponse.getLinks().getIde());
    	 workspaceResponse.getLinks().setIde(workspaceResponse.getLinks().getIde());
     } catch (Exception e) {
		// TODO Auto-generated catch block
    	 logger.error("problem in mapping to workspaceResponse", e);
	}
    
//	Client client = ClientBuilder.newClient( new ClientConfig() );
//	 
//	WebTarget webTarget = client.target(url);
//	Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
//	String response = invocationBuilder.post(Entity.entity(mapper.writeValueAsString(workspace), MediaType.APPLICATION_JSON), String.class);
	return workspaceResponse;
}
	
	public WorkspaceResponse createWorkSpace(String  workspace) throws Exception{
		//workspace.setName(user);
		URL url2 = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		//conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
		conn.setRequestProperty("Accept","application/json");
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		//String postData = mapper.writeValueAsString(workspace);
		// String post = postData.toString();
		 String postData = workspace;
	     byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	     conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	     conn.setDoOutput(true);
	     conn.getOutputStream().write(postDataBytes);
	     String data = getResponse(conn);
	     WorkspaceResponse workspaceResponse = mapper.readValue(data.getBytes(), WorkspaceResponse.class);
	     String first = "";
	     try {
			//first = workspaceResponse.getLinks().getIde().substring(0, workspaceResponse.getLinks().getIde().indexOf("org")+4);
	    	 first = workspaceResponse.getLinks().getIde().substring(0, position+1);
			// String last = workspaceResponse.getLinks().getIde().substring(workspaceResponse.getLinks().getIde().indexOf("org")+4, workspaceResponse.getLinks().getIde().length());
			 String last = workspaceResponse.getLinks().getIde().substring(position+1, workspaceResponse.getLinks().getIde().length());
		     String intermediate = "dashboard/#/ide/";
		     String url = first + intermediate + last;
		     workspaceResponse.getLinks().setIde(url);
	    	// System.out.println("workspace url************* is "+workspaceResponse.getLinks().getIde());
	    	 workspaceResponse.getLinks().setIde(workspaceResponse.getLinks().getIde());
	     } catch (Exception e) {
			// TODO Auto-generated catch block
	    	 logger.error("problem in mapping to workspaceResponse", e);
		}
	    
//		Client client = ClientBuilder.newClient( new ClientConfig() );
//		 
//		WebTarget webTarget = client.target(url);
//		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
//		String response = invocationBuilder.post(Entity.entity(mapper.writeValueAsString(workspace), MediaType.APPLICATION_JSON), String.class);
		return workspaceResponse;
	}
	
	
	
	public WorkspaceResponse createWorkSpaceWithCloudfront(String  workspace, String baseUrl) throws Exception{
		//workspace.setName(user);
		url = baseUrl + "/api/workspace/devfile?start-after-create=false&namespace=che";
		URL url2 = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		//conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
		conn.setRequestProperty("Accept","application/json");
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		//String postData = mapper.writeValueAsString(workspace);
		// String post = postData.toString();
		 String postData = workspace;
	     byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	     conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	     conn.setDoOutput(true);
	     conn.getOutputStream().write(postDataBytes);
	     String data = getResponse(conn);
	     WorkspaceResponse workspaceResponse = mapper.readValue(data.getBytes(), WorkspaceResponse.class);
	     workspaceResponse.setActualClusterUrl(workspaceResponse.getLinks().getIde());
	     String first = "";
	     try {
	    	 String last = workspaceResponse.getLinks().getIde();
			String ide = baseUrl + "/dashboard/#/ide/online/che/"+last.substring(last.lastIndexOf("/")+1, last.length());
		   System.out.println("ide is "+ide);
			workspaceResponse.getLinks().setIde(ide);
	    	
	     } catch (Exception e) {
			// TODO Auto-generated catch block
	    	 e.printStackTrace();
	    	 logger.error("problem in mapping to workspaceResponse", e);
		}
	    
		return workspaceResponse;
	}
	
	public WorkspaceResponse createWorkSpaceWithAccessToken(String  workspace, String baseUrl, String accessToken) throws Exception{
		//workspace.setName(user);
		url = baseUrl + "/api/workspace/devfile?start-after-create=false";
		URL url2 = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer "+accessToken);
		//conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
		conn.setRequestProperty("Accept","application/json");
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		//String postData = mapper.writeValueAsString(workspace);
		// String post = postData.toString();
		 String postData = workspace;
	     byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	     conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	     conn.setDoOutput(true);
	     conn.getOutputStream().write(postDataBytes);
	     String data = getResponse(conn);
	     WorkspaceResponse workspaceResponse = mapper.readValue(data.getBytes(), WorkspaceResponse.class);
	     workspaceResponse.setActualClusterUrl(workspaceResponse.getLinks().getIde());
	     String first = "";
	     try {
	    	 String last = workspaceResponse.getLinks().getIde();
			String ide = baseUrl + "/dashboard/#/ide/online/che/"+last.substring(last.lastIndexOf("/")+1, last.length());
		   System.out.println("ide is "+ide);
			workspaceResponse.getLinks().setIde(ide);
	    	
	     } catch (Exception e) {
			// TODO Auto-generated catch block
	    	 e.printStackTrace();
	    	 logger.error("problem in mapping to workspaceResponse", e);
		}
	    
		return workspaceResponse;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
	
	public void startWorkspace(String ul, String workspaceId)throws Exception{
		ul = ul+ "/api/workspace/"+workspaceId+"/runtime";
		URL url2 = new URL(ul);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		//conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
		conn.setRequestProperty("Accept","application/json");
		String response = getResponse(conn);
		logger.info("response is "+response);
		System.out.println("response is "+response);
	}
	
	public void startWorkspaceWithToken(String ul, String workspaceId)throws Exception{
		ul = ul+ "/api/workspace/"+workspaceId+"/runtime";
		URL url2 = new URL(ul);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		//conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
		conn.setRequestProperty("Accept","application/json");
		String response = getResponse(conn);
		logger.info("response is "+response);
		System.out.println("response is "+response);
	}
	
	
	public String getAuthTokenKeyCloak(String keyCloakUrl, User user) throws Exception{
		String pwd = "P"+user.getEmail().hashCode();
		System.out.println("passwod "+pwd);
		URL url2 = new URL(keyCloakUrl);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		String urlParameters  = "username="+user.getEmail()+"&password="+pwd+"&grant_type=password&client_id=admin-cli";
		//String urlParameters  = "username="+user.getEmail()+"&password=12345&grant_type=password&client_id=admin-cli";
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestProperty("charset", "utf-8");
		byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int postDataLength = postData.length;
		conn.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
		conn.setUseCaches(false);
		try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
		   wr.write( postData );
		}
		String data = getResponse(conn);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		KeyCloakAccessTokenDTO token = mapper.readValue(data.getBytes(), KeyCloakAccessTokenDTO.class);
		return token.getAccess_token();
	}
	
	public Boolean createLdapUser(PropertyConfig config, User user) throws Exception{
		String pwd = "P"+user.getEmail().hashCode();
		System.out.println("pass is "+pwd);
		Boolean check = checkIfUserExists(config, user.getEmail(), pwd);
			if(check){
				System.out.println("user exists");
				return true;
			}
		Hashtable<String, Object> env = new Hashtable<String, Object>(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        String ldapUrl = config.getLdapServerUrl();
        env.put(Context.PROVIDER_URL, ldapUrl);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        String principal = config.getSecurityPrincipalLdap();
        env.put(Context.SECURITY_PRINCIPAL, principal);
        String ldapPassword = config.getLdapPassword();
        env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
        
		String entryDN = "uid="+user.getEmail()+",ou=people,dc=yaksha,dc=online";  
        Attribute cn = new BasicAttribute("cn", user.getFirstName());  
		Attribute sn = new BasicAttribute("sn", user.getLastName());  
		Attribute mail = new BasicAttribute("mail", user.getEmail());  
		//Attribute phone = new BasicAttribute("telephoneNumber", user.getMobileNumber()==null?"1234":user.getMobileNumber());   
		Attribute phone = new BasicAttribute("telephoneNumber", "1234");   
		Attribute oc = new BasicAttribute("objectClass");  
		Attribute userPassword = new BasicAttribute("userpassword", pwd);
		oc.add("top");  
		oc.add("person");  
		oc.add("organizationalPerson");  
		oc.add("inetOrgPerson");  
		DirContext ctx = null;  
		   try {  
		       // get a handle to an Initial DirContext  
		       ctx = new InitialDirContext(env);  

		       // build the entry  
		       BasicAttributes entry = new BasicAttributes();  
		       entry.put(cn);  
		       entry.put(sn);  
		       entry.put(mail);  
		       entry.put(phone);  

		       entry.put(oc); 
		       entry.put(userPassword); 

		       // Add the entry  

		       ctx.createSubcontext(entryDN, entry);  
//		          System.out.println( "AddUser: added entry " + entryDN + ".");  
		       return true;
		   } catch (NamingException e) {  
		       System.err.println("AddUser: error adding entry." + e); 
		       logger.info("AddUser: error adding entry." + e);
		       return false;
		   }  
	}
	
	public boolean checkIfUserExists(PropertyConfig config, String user, String password){
		try {
			Hashtable<String, Object> env = new Hashtable<String, Object>(11);
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			String ldapUrl = config.getLdapServerUrl();
			env.put(Context.PROVIDER_URL, ldapUrl);
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			String principal = config.getSecurityPrincipalLdap();
			env.put(Context.SECURITY_PRINCIPAL, "uid="+user+",ou=people,dc=yaksha,dc=online");
			//String ldapPassword = config.getLdapPassword();
			env.put(Context.SECURITY_CREDENTIALS, password);
			InitialDirContext ctx = new InitialDirContext(env);
			System.out.println("good");
			return true;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return false;
		}
        
        
	}
}
