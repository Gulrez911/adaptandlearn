package com.assessment.services.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.assessment.services.JobDescriptionParser;
import com.assessment.web.dto.JobDescriptionParseResponseDto;
@org.springframework.stereotype.Service
public class JobDescriptionParserImpl implements JobDescriptionParser{

	@Override
	public JobDescriptionParseResponseDto parseJobDesc(String contents) {
		// TODO Auto-generated method stub
		JobDescriptionParseResponseDto ret = new JobDescriptionParseResponseDto();
		String regexJobDesc = "1.\\s*Job\\s*Description\\s*Description";
		
		String regexPrimrySkills = "2.\\s*Primary\\s*Skills\\s*[(]\\s*Comma\\s*Separated\\s*[)]";
		
		String regexSecSkills = "3.\\s*Secondary\\s*Skills\\s*[(]\\s*Comma\\s*Separated\\s*[)]";
		
		String regexEducation = "4.\\s*Education\\s*Expectations\\s*[(]\\s*Comma\\s*Separated\\s*[)]";
		
		String regexcert = "5.\\s*Certification\\s*Expectations\\s*[(]\\s*Comma\\s*Separated\\s*[)]";
		
		String regexDomain = "6.\\s*Domain\\s*Knowledge\\s*[(]\\s*Comma\\s*Separated\\s*[)]";
		
		String regexExperince = "7.\\s*Experience\\s*[-]\\s*No\\s*of\\s*years\\s*[(]\\s*Range\\s*Example\\s*[:]\\s*[0-9]+\\s*to\\s*[0-9]+\\s*years\\s*[)]";
		
		String regexLocation = "8.\\s*Location\\s*";
		
		String label_Desc = "";
		String label_PrimSkills = "";
		String label_SecSkills = "";
		String label_Education = "";
		String label_Cert = "";
		String label_Domain = "";
		String label_Exp = "";
		String label_Location = "";
		
		Pattern pattern = Pattern.compile(regexJobDesc, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(contents);
			
			
			while (matcher.find())
			{
			    System.out.print("Start index: " + matcher.start());
			    System.out.print(" End index: " + matcher.end() + " ");
			    
			    label_Desc = matcher.group();
			    System.out.println(label_Desc);
			}		
			
			if(label_Desc.length() == 0){
				ret.setErrorDescription(true);
				return ret;
			}
		
		
		pattern = Pattern.compile(regexPrimrySkills, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(contents);
			

			while (matcher.find())
			{
			    System.out.print("Start index: " + matcher.start());
			    System.out.print(" End index: " + matcher.end() + " ");
			    
			    label_PrimSkills = matcher.group();
			    System.out.println(label_PrimSkills);
			}	
			
			if(label_PrimSkills.length() == 0){
				ret.setErrorPrimarySkills(true);
				return ret;
			}
			
			pattern = Pattern.compile(regexSecSkills, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contents);
				
				while (matcher.find())
				{
				    System.out.print("Start index: " + matcher.start());
				    System.out.print(" End index: " + matcher.end() + " ");
				    
				    label_SecSkills = matcher.group();
				    System.out.println(label_SecSkills);
				}	
				
				if(label_SecSkills.length() == 0){
					ret.setErrorSecondarySkills(true);
					return ret;
				}
					
				
				
			pattern = Pattern.compile(regexEducation, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contents);
				
				
				while (matcher.find())
				{
				    System.out.print("Start index: " + matcher.start());
				    System.out.print(" End index: " + matcher.end() + " ");
				    
				    label_Education = matcher.group();
				    System.out.println(label_Education);
				}
				if(label_Education.length() == 0){
					ret.setErrorEducation(true);
					return ret;
				}
				
			pattern = Pattern.compile(regexcert, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contents);
				
				
				while (matcher.find())
				{
				    System.out.print("Start index: " + matcher.start());
				    System.out.print(" End index: " + matcher.end() + " ");
				    
				    label_Cert = matcher.group();
				    System.out.println(label_Cert);
				}
				
				if(label_Cert.length() == 0){
					ret.setErrorcertification(true);
					return ret;
				}
				
			pattern = Pattern.compile(regexDomain, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contents);
				
				
				while (matcher.find())
				{
				    System.out.print("Start index: " + matcher.start());
				    System.out.print(" End index: " + matcher.end() + " ");
				    
				    label_Domain = matcher.group();
				    System.out.println(label_Domain);
				}
				if(label_Domain.length() == 0){
					ret.setErrordomain(true);
					return ret;
				}
				
				
			pattern = Pattern.compile(regexExperince, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contents);
				
				
				while (matcher.find())
				{
				    System.out.print("Start index: " + matcher.start());
				    System.out.print(" End index: " + matcher.end() + " ");
				    
				    label_Exp = matcher.group();
				    System.out.println(label_Exp);
				}
				if(label_Exp.length() == 0){
					ret.setErrorExperience(true);
					return ret;
				}
				
			pattern = Pattern.compile(regexLocation, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contents);
				
				
				while (matcher.find())
				{
				    System.out.print("Start index: " + matcher.start());
				    System.out.print(" End index: " + matcher.end() + " ");
				    
				    label_Location = matcher.group();
				    System.out.println(label_Location);
				}
				if(label_Location.length() == 0){
					ret.setErrorLocation(true);
					return ret;
				}
				
		
		return populate(ret, contents, label_Desc, label_PrimSkills, label_SecSkills, label_Education, label_Cert, label_Domain, label_Exp, label_Location);
	}
	
	private JobDescriptionParseResponseDto populate(JobDescriptionParseResponseDto ret, String str, String desc, String prim, String sec, String edu, String cert, String domain, String exp, String loc){
		String jobDescWithTitle = str.substring(0, str.indexOf(prim));
		String jobDesc = jobDescWithTitle.substring(desc.length(), jobDescWithTitle.length());
		System.out.println(jobDesc.trim());
		ret.setDescription(jobDesc.trim());
		System.out.println("----------------");
		str = str.substring(jobDescWithTitle.length(), str.length());
		
		
		//str = str.substring(prim.length() ,str.length());
		String primarySkillsWithTitle = str.substring(0, str.indexOf(sec));
		String primaryskills = primarySkillsWithTitle.substring(prim.length(), primarySkillsWithTitle.length());
		System.out.println(primaryskills.trim());
		ret.setPrimarySkills(primaryskills.trim());
		System.out.println("----------------");
		str = str.substring(primarySkillsWithTitle.length(), str.length());
		
		//str = str.substring(sec.length() ,str.length());
		String secSkillsWithTitle = str.substring(0, str.indexOf(edu));
		String secSkills = secSkillsWithTitle.substring(sec.length(), secSkillsWithTitle.length());
		ret.setSecondarySkills(secSkills.trim());
		System.out.println(secSkills.trim());
		System.out.println("----------------");
		str = str.substring(secSkillsWithTitle.length(), str.length());
		
		//str = str.substring(edu.length() ,str.length());
		String edSkillsWithTitle = str.substring(0, str.indexOf(cert));
		String edSkills = edSkillsWithTitle.substring(edu.length(), edSkillsWithTitle.length());
		System.out.println(edSkills.trim());
		ret.setEducation(edSkills.trim());
		System.out.println("----------------");
		str = str.substring(edSkillsWithTitle.length(), str.length());
		
		//str = str.substring(cert.length() ,str.length());
		String cerSkillsWithTitle = str.substring(0, str.indexOf(domain));
		String cerSkills = cerSkillsWithTitle.substring(cert.length(), cerSkillsWithTitle.length());
		ret.setCertification(cerSkills.trim());
		System.out.println(cerSkills.trim());
		System.out.println("----------------");
		str = str.substring(cerSkillsWithTitle.length(), str.length());
		
		//str = str.substring(domain.length() ,str.length());
		String domainSkillsWithTitle = str.substring(0, str.indexOf(exp));
		String domainSkills = domainSkillsWithTitle.substring(domain.length(), domainSkillsWithTitle.length());
		System.out.println(domainSkills.trim());
		ret.setDomain(domainSkills.trim());
		System.out.println("----------------");
		str = str.substring(domainSkillsWithTitle.length(), str.length());
		
		//str = str.substring(exp.length() ,str.length());
		String expWithTitle = str.substring(0, str.indexOf(loc));
		String exp1 = expWithTitle.substring(exp.length(), expWithTitle.length());
		System.out.println(exp1.trim());
		ret.setExperience(exp1.trim());
		System.out.println("----------------");
		str = str.substring(expWithTitle.length(), str.length());
		
		//str = str.substring(loc.length() ,str.length());
		String locWithTitle = str.substring(0, str.length());
		String loc1 = locWithTitle.substring("8.	Location".length(), locWithTitle.length());
		ret.setLocation(loc1.trim());
		System.out.println(loc1.trim());
		System.out.println("----------------");
		//str = str.substring(expWithTitle.length(), str.length());
		return ret;
	}

}
