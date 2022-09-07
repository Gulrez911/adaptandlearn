package com.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.services.ProctorTrackService;
import com.assessment.web.dto.proctortrack.ProctorTrackRequest;
import com.assessment.web.dto.proctortrack.ProctorTrackTokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestProctorTrack {
	@Autowired
	ProctorTrackService proctorTrackService;
	
	@Test
	public void testGetToken() throws Exception{
		String res = proctorTrackService.getAccessToken();
		ObjectMapper mapper = new ObjectMapper();
			ProctorTrackTokenResponse response = mapper.readValue(res.getBytes(), ProctorTrackTokenResponse.class);
		System.out.println(response.getAccess_token());
		System.out.println(response.getExpires_in());
	}
	
	@Test
	public void testGetProctoredUrl() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String json = FileUtils.readFileToString(new File("proctorTrack"+File.separator+"request.json"));
		ProctorTrackRequest request = mapper.readValue(json.getBytes(), ProctorTrackRequest.class);
		String url = proctorTrackService.getProctoredUrl(request);
		System.out.println(url);
	}
	
	@Test
	public void testGetInstructorProctoredUrl() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String json = FileUtils.readFileToString(new File("proctorTrack"+File.separator+"request_instructor.json"));
		ProctorTrackRequest request = mapper.readValue(json.getBytes(), ProctorTrackRequest.class);
		String url = proctorTrackService.getProctoredUrl(request);
		System.out.println(url);
	}

}
