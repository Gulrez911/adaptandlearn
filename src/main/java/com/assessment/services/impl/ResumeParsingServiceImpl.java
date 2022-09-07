package com.assessment.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.assessment.common.PropertyConfig;
import com.assessment.services.ResumeParsingService;
import com.assessment.web.dto.resumeparsing.HttpPostMultipart;
import com.assessment.web.dto.resumeparsing.ResumeParsingResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@org.springframework.stereotype.Service
public class ResumeParsingServiceImpl implements ResumeParsingService{

	@Autowired
	PropertyConfig propertyConfig;
	
	@Override
	public ResumeParsingResponse parseResume(String fileToParse, String jobdescName, Long jobdescId, String compId,
			String createdBy, String profileLoc, List<String> expectedPrimarySkills,
			List<String> expectedSecondarySkills) {
	
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
			headers.put("accept", "application/json");
			headers.put("X-CSRFToken", "DCFKV64yb7pef74AgYhcsxF7foRubMXQWhTqTTfSXJh31U77YCg6uPlH7a45OfpI");
			HttpPostMultipart multipart = new HttpPostMultipart(propertyConfig.getResumeParsingUrl(), "utf-8", headers);
			// Add form field
			multipart.addFormField("job_desc_name", jobdescName);
			multipart.addFormField("job_desc_id", ""+jobdescId);
			multipart.addFormField("comp_id", compId);
			multipart.addFormField("created_by", createdBy);
			multipart.addFormField("profile_loc", profileLoc);
			
			String primary = String.join(",", expectedPrimarySkills);
			String secondary = String.join(",", expectedSecondarySkills);
			
			multipart.addFormField("primary", primary);
			multipart.addFormField("secondary", secondary);
			// Add file
			multipart.addFilePart("file_to_process", new File(fileToParse));
			// Print result
			String response = multipart.finish();
			System.out.println(response);
			ObjectMapper mapper = new ObjectMapper();
			ResumeParsingResponse res = mapper.readValue(response, ResumeParsingResponse.class);
			return res;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
