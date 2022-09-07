package com.assessment.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.data.LangUTF;
import com.assessment.repositories.LangUTFRepository;

@Controller
public class LangUTFController {

	@Autowired
	LangUTFRepository langUTFRepository;

	@GetMapping("/fetchLang")
	@ResponseBody
	public Map<String, Object> fetchLang(){
		Map<String, Object> map = new HashMap<>();
		List<LangUTF> langutfs = langUTFRepository.findAll();
		map.put("list", langutfs);
		return map;
	}
}
