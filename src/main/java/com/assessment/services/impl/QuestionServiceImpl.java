package com.assessment.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.Exceptions.ErrorCodes;
import com.assessment.common.ApplicationConstants;
import com.assessment.common.ExcelReader;
import com.assessment.data.DifficultyLevel;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapper;
import com.assessment.data.QuestionType;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.repositories.QuestionRepository;
import com.assessment.services.QuestionService;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	
	Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionMapperRepository questionMapperRepository;

	@Transactional
	public void createQuestion(Question question) {
		// questionRepository.f
//		Question q = questionRepository.findByPrimaryKey(
//				question.getQuestionText(), question.getCompanyId());
		
		Question q = findByPrimaryKeyAndQualifiers(question);
		
		
		
		
		
		if (q != null) {
//			throw new AssessmentGenericException("Question already exists");// Make
//																			// it
//																			// externalized
//																			// and
//			/**																// configurable
			/**
			 * To make multiple upload of same questions possible.
			 */
			question.setCreateDate(q.getCreateDate());
			Mapper mapper = new DozerBeanMapper();
			question.setId(q.getId());
			question.setCreateDate(q.getCreateDate());
			mapper.map(question, q);
			q.setUpdateDate(new Date());
			questionRepository.save(q);
		}
		else {
			question.setCreateDate(new Date());
			questionRepository.save(question);
		}
		
	}

	@Override
	public Page<Question> findQuestionsByQualifier1AndPage(String companyId,
			String qualifier1, Integer pageNumber) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier1(companyId,
				qualifier1, PageRequest.of(pageNumber, 10));
	}

	public Page<Question> findQuestionsByQualifier2AndPage(String companyId,
			String qualifier1, String qualifier2, Integer pageNumber) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier2(companyId,
				qualifier1, qualifier2, PageRequest.of(pageNumber, 10));
	}

	@Override
	public Page<Question> findQuestionsByQualifier3AndPage(String companyId,
			String qualifier1, String qualifier2, String qualifier3, Integer pageNumber) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier3(companyId,
				qualifier1, qualifier2, qualifier3, PageRequest.of(pageNumber, 10));
	}

	@Override
	public Page<Question> findQuestionsByQualifier4AndPage(String companyId,
			String qualifier1, String qualifier2, String qualifier3,
			String qualifier4, Integer pageNumber) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier4(companyId,
				qualifier1, qualifier2, qualifier3, qualifier4, PageRequest.of(pageNumber, 10));
	}

	@Override
	public Page<Question> findQuestionsByQualifier5AndPage(String companyId,
			String qualifier1, String qualifier2, String qualifier3,
			String qualifier4, String qualifier5, Integer pageNumber) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier5(companyId,
				qualifier1, qualifier2, qualifier3, qualifier4, qualifier5, PageRequest.of(pageNumber, 10));
	}

	@Transactional
	public List<Question> uploadQuestionsFromExcelDoc(
			FileInputStream fileInputStream, File mappingObjectFile) {
		List<Question> questions = null;
		try {
			questions = ExcelReader.parseExcelFileToBeans(fileInputStream,
					mappingObjectFile);
			if (questions.size()>0) {
				for (Question question : questions) {
					createQuestion(question);
				}
			}
		} catch (Exception e) {
			throw new AssessmentGenericException(
					ErrorCodes.ERROR_WHILE_PARSING_EXCEL_DOCUMENT);
		}
		return questions;
	}

	@Override
	public List<Question> findQuestions(String companyId) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByCompanyId(companyId);
	}

	public Page<Question> searchQuestions(@Param("companyId") String companyId, @Param("searchText")  String searchText, Integer pageNumber){
		return questionRepository.searchQuestions(companyId, searchText, PageRequest.of(pageNumber, 10));
	}

	@Override
	public Question findById(Long id) {
		// TODO Auto-generated method stub
		return questionRepository.findById(id).get();
	}

	

	@Override
	public Page<Question> findQuestionsByPage(String companyId, Integer pageNumber) {
		// TODO Auto-generated method stub
		//return questionRepository.findQuestionsByCompanyIdAndPageNumber(companyId, PageRequest.of(pageNumber, ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE));
		return questionRepository.findQuestionsByCompanyIdAndPageNumber(companyId, PageRequest.of(pageNumber, 10));
	}

	@Override
	public List<Question> findQuestionsByQualifier1(String companyId, String qualifier1) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier1(companyId, qualifier1);
	}

	@Override
	public List<Question> findQuestionsByQualifier2(String companyId, String qualifier1, String qualifier2) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier2(companyId, qualifier1, qualifier2);
	}

	@Override
	public List<Question> findQuestionsByQualifier3(String companyId, String qualifier1, String qualifier2,
			String qualifier3) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier3(companyId, qualifier1, qualifier2, qualifier3);
	}

	@Override
	public List<Question> findQuestionsByQualifier4(String companyId, String qualifier1, String qualifier2,
			String qualifier3, String qualifier4) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier4(companyId, qualifier1, qualifier2, qualifier3, qualifier4);
	}

	@Override
	public List<Question> findQuestionsByQualifier5(String companyId, String qualifier1, String qualifier2,
			String qualifier3, String qualifier4, String qualifier5) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier5(companyId, qualifier1, qualifier2, qualifier3, qualifier4, qualifier5);
	}

	@Override
	public List<Question> searchQuestions(String companyId, String searchText) {
		// TODO Auto-generated method stub
		return questionRepository.searchQuestions(companyId, searchText);
	}
	
	@Override
	public List<Question> getAllLevel1Questions(String companyId){
		return questionRepository.getAllLevel1Questions(companyId);
	}
	
	@Override
	public Page<Question> getAllLevel1Questions(String companyId, Integer pageNumber){
		return questionRepository.getAllLevel1Questions(companyId, PageRequest.of(pageNumber, 10));
		
	}
	
	public boolean canDeleteQuestion(Long qid) {
		List<QuestionMapper> mappers = questionMapperRepository.findByQuestion_id(qid);
			if(mappers.size() > 0) {
				return false;
			}
			else {
				return true;
			}
	}

	@Override
	public void updateQuestion(Question q) {
		logger.info("In updateQuestion");
		// TODO Auto-generated method stub
		if(q.getId() == null) {
			throw new AssessmentGenericException("QUESTION_WITHOUT_ID_CAN_NOT_BE_UPDATED");
		}
		
		Question q2  = questionRepository.findById(q.getId()).get();
		if(q2 == null) {
			throw new AssessmentGenericException("QUESTION_TO_BE_UPDATED_DOESNT_EXIST");
		}
		//System.out.println("constr...."+q.getConstrnt()+" database constr "+q2.getConstrnt());
		//System.out.println("instr...."+q.getInstructionsIfAny()+" database instr "+q2.getInstructionsIfAny());
		logger.info("database q constr "+q2.getConstrnt());
		logger.info("q constr "+q.getConstrnt());
			if(q.getImageUrl() == null ){
				q.setImageUrl(q2.getImageUrl());
			}
		
			if(q.getAudioURL() == null){
				q.setAudioURL(q2.getAudioURL());
			}
			
			if(q.getVideoURL() == null){
				q.setVideoURL(q2.getVideoURL());
			}
			
			if(q.getTestcasesXml() == null){
				q.setTestcasesXml(q2.getTestcasesXml());
			}
		
		
		Mapper mapper = new DozerBeanMapper();
		mapper.map(q, q2);
		q2.setConstrnt(q.getConstrnt());
		q2.setInstructionsIfAny(q.getInstructionsIfAny());
		q2.setUpdateDate(new Date());
		questionRepository.save(q2);
	}

	@Override
	public void removeQuestion(Long id) {
		// TODO Auto-generated method stub
		questionRepository.deleteById(id);
	}
	
	@Override
	public List<Question> getRandomQuestions(String companyId, String qualifier1, String qualifier2, String qualifier3,
			String qualifier4, String qualifier5, DifficultyLevel level, QuestionType type, Pageable pageable) {
		// TODO Auto-generated method stub
		if(qualifier2 == null || qualifier2.trim().length() == 0){
			return questionRepository.getRandomQuestionsForQualifiers1(companyId, qualifier1, type, pageable);
		}
		else if(qualifier3 == null || qualifier3.trim().length() == 0){
			return questionRepository.getRandomQuestionsForQualifiers1_2(companyId, qualifier1, qualifier2, type, pageable);
		}
		else if(qualifier4 == null || qualifier4.trim().length() == 0){
			return questionRepository.getRandomQuestionsForQualifiers1_2_3(companyId, qualifier1, qualifier2, qualifier3, type, pageable);
		}
		else if(qualifier5 == null || qualifier5.trim().length() == 0){
			return questionRepository.getRandomQuestionsForQualifiers1_2_3_4(companyId, qualifier1, qualifier2, qualifier3, qualifier4, type, pageable);
		}
		else{
			return questionRepository.getRandomQuestionsForQualifiers1_2_3_4_5(companyId, qualifier1, qualifier2, qualifier3, qualifier4, qualifier5, type, pageable);
		}
	}

	@Override
	public Question findByPrimaryKeyAndQualifiers(Question question) {
		Question q = null;
		
		if(question.getQualifier1() == null || question.getQualifier1().trim().length() == 0){
			throw new AssessmentGenericException("No qualifier 1 present");
		}
		
		if(question.getQualifier5() == null && question.getQualifier4() == null && question.getQualifier3() == null && question.getQualifier2() == null){
			q = questionRepository.findByPrimaryKeyTillQualifier1(question.getQuestionText(), question.getCompanyId(), question.getQualifier1());
		}
		else if(question.getQualifier5() == null && question.getQualifier4() == null && question.getQualifier3() == null && question.getQualifier2() != null){
			q = questionRepository.findByPrimaryKeyTillQualifier2(question.getQuestionText(), question.getCompanyId(), question.getQualifier1() ,question.getQualifier2());
		}
		else if(question.getQualifier5() == null && question.getQualifier4() == null && question.getQualifier3() != null ){
			q = questionRepository.findByPrimaryKeyTillQualifier3(question.getQuestionText(), question.getCompanyId(), question.getQualifier1() ,question.getQualifier2(), question.getQualifier3());
		}
		else if(question.getQualifier5() == null && question.getQualifier4() != null  ){
			q = questionRepository.findByPrimaryKeyTillQualifier4(question.getQuestionText(), question.getCompanyId(), question.getQualifier1() ,question.getQualifier2(), question.getQualifier3(), question.getQualifier4());
		}
		else if(question.getQualifier5() != null){
			q = questionRepository.findByPrimaryKeyAndAllQualifiers(question.getQuestionText(), question.getCompanyId(), question.getQualifier1() ,question.getQualifier2(), question.getQualifier3(), question.getQualifier4(), question.getQualifier5());
		}
		
		return q;
	}

	@Override
	public void updateQuestion(String displayq1, String parentGroupClassifier, String qualifier) {
		// TODO Auto-generated method stub
		questionRepository.updateQuestion(displayq1, parentGroupClassifier, qualifier);
	}

	@Override
	public List<String> uquiqeQualifier() {
		// TODO Auto-generated method stub
		return questionRepository.uquiqeQualifier();
	}

	@Override
	public Page<Question> searchQuestionsByQualifier(String companyId, List<String> listparentGroupClassifier, Integer pageNumber) {
		// TODO Auto-generated method stub
		return questionRepository.searchQuestionsByQualifier(companyId, listparentGroupClassifier, PageRequest.of(pageNumber, 10));
	}
}
