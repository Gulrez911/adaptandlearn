package com.assessment.services.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.assessment.services.GithubService;
import com.assessment.web.dto.github.MyArray;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GithubServiceImpl implements  GithubService{
	static Logger logger = LoggerFactory.getLogger(GithubServiceImpl.class);

	@Override
	public List<String> fetchGithubPublicRepos(String gitUser) {
		List<String> ret = new ArrayList<String>();
		try {
			
			String ul = "https://api.github.com/users/${git_user}/repos";
			ul = ul.replace("${git_user}", gitUser);
			URL url = new URL(ul); 
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET"); 
			conn.connect(); 
			int responsecode = conn.getResponseCode(); 

			if(responsecode != 200)
			throw new RuntimeException("HttpResponseCode: " +responsecode);
			else
			{
			   InputStream inputStream = conn.getInputStream();
			   StringWriter writer = new StringWriter();
			   IOUtils.copy(inputStream, writer);
			   String json = writer.toString();
			   ObjectMapper om = new ObjectMapper();
			   om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			   List<MyArray> array = om.readValue(json,new TypeReference<List<MyArray>>(){});
			  // String url2 = "https://github.com/${rep}/archive/${branch_name}.tar.gz";
			   	for(MyArray arr : array){
			   		ret.add(arr.getFull_name() +" ### "+arr.getDefault_branch());
			   		System.out.println("name "+arr.getName()+" full name "+arr.getFull_name()+" default branch "+arr.getDefault_branch());
			   	}
			   System.out.println("done");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("problem", e);
			ret.add("NA_Fail_To_Connect");
			return ret;
		} 
		return ret;
	}
	
	

	
	private void downloadFile(String urlActual, String fileName) throws Exception{
		File file = new File("test");
			if(!file.isDirectory()){
				FileUtils.forceMkdir(new File("test"));
			}
				
		
		try (BufferedInputStream in = new BufferedInputStream(new URL(urlActual).openStream());
				  FileOutputStream fileOutputStream = new FileOutputStream("test"+File.separator+fileName)) {
				    byte dataBuffer[] = new byte[1024];
				    int bytesRead;
				    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				        fileOutputStream.write(dataBuffer, 0, bytesRead);
				    }
				} catch (IOException e) {
				    // handle exception
					e.printStackTrace();
					logger.error("problem", e);
				}
	}




	@Override
	public boolean downloadZip(String name, String branch) {
		// TODO Auto-generated method stub
		String url = "https://github.com/${GIT_REP}/archive/${GIT_BRANCH}.zip";
		url = url.replace("${GIT_REP}", name);
		url = url.replace("${GIT_BRANCH}", branch);
		String nm = name.substring(name.indexOf("/")+1, name.length());
		try {
			downloadFile(url, nm+".zip");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("problem", e);
			return false;
		}
	}
}
