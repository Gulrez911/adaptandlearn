package com.assessment.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.AdaptiveAssessmentQuestionMappperInstance;
import com.assessment.data.AdaptiveAssessmentSkillLevel;
import com.assessment.data.QuestionType;
import com.assessment.repositories.AdaptiveAssessmentQuestionMappperInstanceRepository;
import com.assessment.services.AdaptiveAssessmentQuestionMappperInstanceService;
@Service
@Transactional
public class AdaptiveAssessmentQuestionMappperInstanceServiceImpl implements AdaptiveAssessmentQuestionMappperInstanceService{

	@Autowired
	AdaptiveAssessmentQuestionMappperInstanceRepository rep;
	
	Logger logger = LoggerFactory.getLogger(AdaptiveAssessmentQuestionMappperInstanceServiceImpl.class);
	
	
	@Override
	public AdaptiveAssessmentQuestionMappperInstance findUniqueAdaptiveAssessmentQuestionMappperInstance(
			String companyId, String questionText, String adaptiveAssessmentName, String email,
			AdaptiveAssessmentSkillLevel level, Integer attempt) {
		// TODO Auto-generated method stub
		return rep.findUniqueAdaptiveAssessmentQuestionMappperInstance(companyId, questionText, adaptiveAssessmentName, email, level, attempt);
	}

	@Override
	public Integer findCountAdaptiveAssessmentQuestionMappperInstanceForLevel(String companyId,
			String adaptiveAssessmentName, String email, AdaptiveAssessmentSkillLevel level) {
		// TODO Auto-generated method stub
		return rep.findCountAdaptiveAssessmentQuestionMappperInstanceForLevel(companyId, adaptiveAssessmentName, email, level);
	}

	@Override
	public AdaptiveAssessmentQuestionMappperInstance saveOrUpdate(AdaptiveAssessmentQuestionMappperInstance questionMapperInstance) {
		// TODO Auto-generated method stub
		
		AdaptiveAssessmentQuestionMappperInstance questionMapperInstance2 = removeDublicateAndGetInstance(questionMapperInstance.getCompanyId(), questionMapperInstance.getQuestionText(), questionMapperInstance.getTestName(), questionMapperInstance.getUser(), questionMapperInstance.getAdaptiveAssessmentSkillLevel(), questionMapperInstance.getAttempt());
		if(questionMapperInstance2 != null) {
			//update answer
			questionMapperInstance2.setUserChoices(questionMapperInstance.getUserChoices());
			String type = questionMapperInstance2.getQuestion().getType();
			/**
			 * 
			 */
			if(type.equals(QuestionType.CODING.getType()) || type.equals(QuestionType.MATCH_FOLLOWING_MCQ.getType()) || type.equals(QuestionType.FILL_BLANKS_MCQ.getType())){
				Mapper mapper = new DozerBeanMapper();
				Long id = questionMapperInstance2.getId();
				mapper.map(questionMapperInstance, questionMapperInstance2);
				questionMapperInstance2.setId(id);
			}
			else if(type.equals(QuestionType.IMAGE_UPLOAD_BY_USER.getType())){
				if(questionMapperInstance.getImageUploadUrl() == null){
					//just making sure in prev calls if file is not uploaded,...we save the last updated file in the system
					//no need
					questionMapperInstance.setImageUploadUrl(questionMapperInstance2.getImageUploadUrl());
				}
				else{
					questionMapperInstance2.setSubjective(true);
					questionMapperInstance2.setImageUploadUrl(questionMapperInstance.getImageUploadUrl());
				}
				questionMapperInstance2.setSubjective(true);
			}
			else if(type.equals(QuestionType.VIDEO_UPLOAD_BY_USER.getType())){
				if(questionMapperInstance.getVideoUploadUrl() == null){
					//just making sure in prev calls if file is not uploaded,...we save the last updated file in the system
					//no need
					questionMapperInstance.setVideoUploadUrl(questionMapperInstance2.getVideoUploadUrl());
				}
				else{
					questionMapperInstance2.setSubjective(true);
					questionMapperInstance2.setVideoUploadUrl(questionMapperInstance.getVideoUploadUrl());
				}
				questionMapperInstance2.setSubjective(true);
			}
			else if(type.equals(QuestionType.SUBJECTIVE_TEXT.getType())){
				questionMapperInstance2.setSubjective(true);
				questionMapperInstance2.setSubjectiveText(questionMapperInstance.getSubjectiveText());
			}
			questionMapperInstance2.setUpdateDate(new Date());
			checkAnswer(questionMapperInstance2);
			return rep.save(questionMapperInstance2);
		}
		else {
			//new answer
			questionMapperInstance.setCreateDate(new Date());
			checkAnswer(questionMapperInstance);
			questionMapperInstance.setQuestionText(questionMapperInstance.getQuestion().getQuestionText());
			return rep.save(questionMapperInstance);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public AdaptiveAssessmentQuestionMappperInstance removeDublicateAndGetInstance(String companyId,
			String questionText, String adaptiveAssessmentName, String email, AdaptiveAssessmentSkillLevel level,
			Integer attempt) {
		// TODO Auto-generated method stub
		AdaptiveAssessmentQuestionMappperInstance questionMapperInstance = null;
		// TODO Auto-generated method stub
		try {
			logger.info("questiontext "+questionText);
			logger.info("adaptiveAssessmentName "+adaptiveAssessmentName);
			logger.info("user "+email);
			
			List<AdaptiveAssessmentQuestionMappperInstance> annswers = rep.findUniqueAdaptiveAssessmentsQuestionMappperInstanceUserSet(companyId, questionText, adaptiveAssessmentName, email, level, attempt);
			//questionMapperInstance = 	questionMapperInstanceRepository.findUniqueQuestionMapperInstanceForUser(questionText, testName, sectionName, user, companyId);
			logger.info("annswers size "+annswers.size());
			if(annswers == null || annswers.size() == 0){
				logger.info("no ans");
				if(questionText == null){
					logger.info("**************");
					return null;
				}
				if(questionText != null && questionText.contains("\r") || questionText.contains("\t") || questionText.contains("\n")){
					System.out.println(questionText);
					questionText = questionText.replaceAll("[" + System.lineSeparator() + "]", "%");
					//System.out.println(questionText);
					logger.info("questiontext "+questionText);
					logger.info("searcUniqueQuestionMapperInstanceForUserSet called");
					annswers = rep.findUniqueAdaptiveAssessmentsQuestionMappperInstanceUserSet(companyId, questionText, adaptiveAssessmentName, email, level, attempt);
					//System.out.println("annswers "+annswers);
					logger.info("searcUniqueQuestionMapperInstanceForUserSet called "+(annswers==null?0:annswers.size()));
					if(annswers == null || annswers.size() == 0){
						logger.info("0000 1");
						return null;
					}
					else if(annswers.size() > 0){
						QuestionType type = annswers.get(0).getQuestion().getQuestionType();
						logger.info("size returned "+annswers.size());
						if(type.getType().equalsIgnoreCase(QuestionType.FULL_STACK_JAVA.getType()) || type.getType().equalsIgnoreCase(QuestionType.FULLSTACK.getType())){
							logger.info("size returned xxx "+annswers.size());
							return annswers.get((annswers.size()-1));
						}
						
					}
				}
				else{
					logger.info("0000 2");
					return null;
				}
				
			}
			
			if(annswers.size() == 1){
				return annswers.get(0);
			}
			
			if(annswers.size() > 1){
				deleteDuplicateAnswers(annswers);
			}
			
			
		} catch (javax.persistence.NonUniqueResultException e) {
			// TODO Auto-generated catch block
			//should not come here
			e.printStackTrace();
			logger.error("should not come here duplicate anss for "+questionText+"-"+ adaptiveAssessmentName+"-"+email+"-"+  companyId);
			List<AdaptiveAssessmentQuestionMappperInstance> qms = rep.findUniqueAdaptiveAssessmentsQuestionMappperInstanceUserSet(companyId, questionText, adaptiveAssessmentName, email, level, attempt);
			for(AdaptiveAssessmentQuestionMappperInstance q : qms){
				rep.delete(q);
			}
		}
		return questionMapperInstance;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteDuplicateAnswers(List<AdaptiveAssessmentQuestionMappperInstance> qms) {
		// TODO Auto-generated method stub
		for(AdaptiveAssessmentQuestionMappperInstance q : qms){
			rep.delete(q);
		}
	}
	
	private void checkAnswer(AdaptiveAssessmentQuestionMappperInstance instance) {
		/**Added if condition code for coding question type answer verification
		 * 
		 */
		if(instance.getQuestion().getQuestionType() != null && instance.getQuestion().getQuestionType().getType().equals(QuestionType.CODING.getType())){
			/**String op = instance.getCodingOuputBySystemTestCase();
			op = (op == null)?"":op.trim();
			//System.out.println(" saving section check answer op "+op+" instance.getQuestionMapper().getQuestion().getHiddenOutputNegative() "+instance.getQuestionMapper().getQuestion().getHiddenOutputNegative());
			if(instance.getQuestionMapper().getQuestion().getHiddenOutputNegative().equalsIgnoreCase(op)){
				//System.out.println("in check ansewr "+true);
				instance.setCorrect(true);
				
			}
			else{
				//System.out.println("in check ansewr "+false);
				instance.setCorrect(false);
			}
			instance.setAnswered(true);**/
			//for coding questions getCorrect() will determine whether answer is correct or not
			return;
		}
		/**End Added if condition code for coding question type answer verification
		 * 
		 */
		
		if(instance.getQuestion().getQuestionType() != null && instance.getQuestion().getQuestionType().getType().equals(QuestionType.FILL_BLANKS_MCQ.getType())){
			String systemChoices = instance.getQuestion().getFillInBlankOptions();
			String userEnteredBlanks = instance.getUserChoices();
			
			if(userEnteredBlanks== null || userEnteredBlanks.trim().length() == 0){
				instance.setCorrect(false);
				instance.setAnswered(false);
				return;
			}
			instance.setAnswered(true);
			
			if(systemChoices != null && userEnteredBlanks != null && systemChoices.equalsIgnoreCase(userEnteredBlanks)){
				instance.setCorrect(true);
				
			}
			else{
				instance.setCorrect(false);
			}
			return;
		}
		
		if(instance.getQuestion().getQuestionType() != null && instance.getQuestion().getQuestionType().getType().equals(QuestionType.MATCH_FOLLOWING_MCQ.getType())){
			instance = evaluateMTF(instance);
			return;
		}
		
		
		if(instance.getQuestion().getQuestionType() != null && instance.getQuestion().getQuestionType().getType().equals(QuestionType.SUBJECTIVE_TEXT.getType())){
			if(instance.getSubjectiveText() != null && instance.getSubjectiveText().trim().length() > 0){
				instance.setAnswered(true);
			}
			return;
		}
		
		/**
		 * Check if right
		 */
		if(instance.getAnswered()) {
			String rightChoices = instance.getQuestion().getRightChoices();
			String rt[] = rightChoices.split("-");
			String userChoices[] = instance.getUserChoices().split("-");
			instance.setCorrect(Arrays.equals(rt, userChoices));
		}
	}
	
	private AdaptiveAssessmentQuestionMappperInstance evaluateMTF(AdaptiveAssessmentQuestionMappperInstance questionMapperInstance){
		 Map<String, String> correctCombinations = new HashMap<String, String>();
		 String left1 = questionMapperInstance.getQuestion().getMatchLeft1();
		 String right1 = questionMapperInstance.getQuestion().getMatchRight1();
		 correctCombinations.put(left1, right1);
		 
		 String left2 = questionMapperInstance.getQuestion().getMatchLeft2();
		 String right2 = questionMapperInstance.getQuestion().getMatchRight2();
		 correctCombinations.put(left2, right2);
		 
		 String left3 = questionMapperInstance.getQuestion().getMatchLeft3();
		 String right3 = questionMapperInstance.getQuestion().getMatchRight3();
		 if(left3 != null && right3 != null && left3.trim().length()!= 0 && right3.trim().length() != 0){
			 correctCombinations.put(left3, right3);
		 }
		 
		 
		 String left4 = questionMapperInstance.getQuestion().getMatchLeft4();
		 String right4 = questionMapperInstance.getQuestion().getMatchRight4();
		 if(left4 != null && right4 != null && left4.trim().length()!= 0 && right4.trim().length() != 0){
			 correctCombinations.put(left4, right4);
		 }
		 
		 String left5 = questionMapperInstance.getQuestion().getMatchLeft5();
		 String right5 = questionMapperInstance.getQuestion().getMatchRight5();
		 if(left5 != null && right5 != null && left5.trim().length()!= 0 && right5.trim().length() != 0){
			 correctCombinations.put(left5, right5);
		 }
		 
		 String left6 = questionMapperInstance.getQuestion().getMatchLeft6();
		 String right6 = questionMapperInstance.getQuestion().getMatchRight6();
		 if(left6 != null && right6 != null && left6.trim().length()!= 0 && right6.trim().length() != 0){
			 correctCombinations.put(left6, right6);
		 }
		 
		 String rightA = questionMapperInstance.getMatchRight1();
		 if(rightA != null){
			 String expected = correctCombinations.get(left1);
			 if(expected == null){
				 questionMapperInstance.setAnswered(false);
				 questionMapperInstance.setCorrect(false);
				 return questionMapperInstance;
			 }
			 else if(!expected.equals(rightA)){
				 questionMapperInstance.setAnswered(true);
				 questionMapperInstance.setCorrect(false);
				 return questionMapperInstance;
			 }
		 }
		 
		 rightA = questionMapperInstance.getMatchRight2();
		
		 if(rightA != null){
			 String expected = correctCombinations.get(left2);
			 if(expected == null){
				 questionMapperInstance.setAnswered(false);
				 questionMapperInstance.setCorrect(false);
				 return questionMapperInstance;
			 }
			 else if(!expected.equals(rightA)){
				 questionMapperInstance.setAnswered(true);
				 questionMapperInstance.setCorrect(false);
				 return questionMapperInstance;
			 }
		 }
		 
		 rightA = questionMapperInstance.getMatchRight3();
		
		 if(rightA != null){
			 if(left3 != null){
				 String expected = correctCombinations.get(left3);
				 if(expected == null){
					 questionMapperInstance.setAnswered(false);
					 questionMapperInstance.setCorrect(false);
					 return questionMapperInstance;
				 }
				 else if(!expected.equals(rightA)){
					 questionMapperInstance.setAnswered(true);
					 questionMapperInstance.setCorrect(false);
					 return questionMapperInstance;
				 }
			 }
			 
		 }
		 
		 rightA = questionMapperInstance.getMatchRight4();
		
		 if(rightA != null){
			 if(left4 != null){
				 String expected = correctCombinations.get(left4);
				 if(expected == null){
					 questionMapperInstance.setAnswered(false);
					 questionMapperInstance.setCorrect(false);
					 return questionMapperInstance;
				 }
				 else if(!expected.equals(rightA)){
					 questionMapperInstance.setAnswered(true);
					 questionMapperInstance.setCorrect(false);
					 return questionMapperInstance;
				 }
			 }
			 
		 }
		 
		 rightA = questionMapperInstance.getMatchRight5();
		
		 if(rightA != null){
			 if(left5 != null){
				 String expected = correctCombinations.get(left5);
				 if(expected == null){
					 questionMapperInstance.setAnswered(false);
					 questionMapperInstance.setCorrect(false);
					 return questionMapperInstance;
				 }
				 else if(!expected.equals(rightA)){
					 questionMapperInstance.setAnswered(true);
					 questionMapperInstance.setCorrect(false);
					 return questionMapperInstance;
				 }
			 }
			 
		 }
		 
		 rightA = questionMapperInstance.getMatchRight6();
		 
		 if(rightA != null){
			 if(left6 != null){
				 String expected = correctCombinations.get(left6);
				 if(expected == null){
					 questionMapperInstance.setAnswered(false);
					 questionMapperInstance.setCorrect(false);
					 return questionMapperInstance;
				 }
				 else if(!expected.equals(rightA)){
					 questionMapperInstance.setAnswered(true);
					 questionMapperInstance.setCorrect(false);
					 return questionMapperInstance;
				 }
			 }
			 
		 }
		 
		 questionMapperInstance.setAnswered(true);
		 questionMapperInstance.setCorrect(true);
		 return questionMapperInstance;
	}

	@Override
	public Integer findCorrectAnswersAdaptiveAssessmentQuestionMappperInstanceForLevel(String companyId,
			String adaptiveAssessmentName, String email, AdaptiveAssessmentSkillLevel level, Integer attempt) {
		// TODO Auto-generated method stub
		return rep.findCorrectAnswersAdaptiveAssessmentQuestionMappperInstanceForLevel(companyId, adaptiveAssessmentName, email, level, attempt);
	}

	@Override
	public List<AdaptiveAssessmentQuestionMappperInstance> findAdaptiveAssessmentQuestionMappperInstancesForLevelAndAttempt(
			String companyId, String adaptiveAssessmentName, String email, AdaptiveAssessmentSkillLevel level,
			Integer attempt) {
		// TODO Auto-generated method stub
		return rep.findAdaptiveAssessmentQuestionMappperInstancesForLevelAndAttempt(companyId, adaptiveAssessmentName, email, level, attempt);
	}

}
