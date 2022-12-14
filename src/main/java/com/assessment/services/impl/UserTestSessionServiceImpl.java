package com.assessment.services.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.common.PropertyConfig;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.QuestionType;
import com.assessment.data.Test;
import com.assessment.data.UserTestSession;
import com.assessment.reports.manager.AssessmentTestData;
import com.assessment.repositories.QuestionMapperInstanceRepository;
import com.assessment.repositories.UserTestSessionRepository;
import com.assessment.services.TestService;
import com.assessment.services.UserTestSessionService;

@Service
@Transactional
public class UserTestSessionServiceImpl implements UserTestSessionService {

	@Autowired
	UserTestSessionRepository userTestSessionRep;

	@Autowired
	TestService testService;

	@Autowired
	QuestionMapperInstanceRepository questionMapperInstanceRepository;

	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	@Autowired
	PropertyConfig config;

	private void validateMandatoryFields(UserTestSession userTestSession) {
		Set<ConstraintViolation<UserTestSession>> violations = validator.validate(userTestSession);
		if (violations.size() > 0) {
			throw new AssessmentGenericException("NOT_SUFFICIENT_PARAMS");
		}

	}

	@Override
	public UserTestSession findUserTestSession(String user, String testName, String companyId) {
		// TODO Auto-generated method stub
		return userTestSessionRep.findByPrimaryKey(user, testName, companyId);
	}

	@Override
	public UserTestSession saveOrUpdate(UserTestSession userTestSession) {
		// TODO Auto-generated method stub
		validateMandatoryFields(userTestSession);
		Test test = testService.findbyTest(userTestSession.getTestName(), userTestSession.getCompanyId());

		UserTestSession userTestSession2 = findUserTestSession(userTestSession.getUser(), userTestSession.getTestName(),
				userTestSession.getCompanyId());
		if (userTestSession2 == null) {
			// create
			userTestSession.setTest(test);
			userTestSession.setNoOfAttempts(1);
			if (config.getSubjectiveScoring() != null && config.getSubjectiveScoring().equalsIgnoreCase("yes")) {
				userTestSession = calculateResultsWithSubjective(userTestSession, test);
			} else {
				userTestSession = calculateResults(userTestSession, test);
			}

			// userTestSession.setCreateDate(new Date());
			userTestSessionRep.save(userTestSession);
			return userTestSession;
		} else {
			// update
			if (config.getSubjectiveScoring() != null && config.getSubjectiveScoring().equalsIgnoreCase("yes")) {
				userTestSession2 = calculateResultsWithSubjective(userTestSession2, test);
			} else {
				userTestSession2 = calculateResults(userTestSession2, test);
			}
			// userTestSession2 = calculateResults(userTestSession2, test);
			userTestSession2.setUpdateDate(new Date());
			userTestSession2.setWeightedScorePercentage(userTestSession2.getWeightedScorePercentage());
			userTestSession2.setMarkComplete(userTestSession.getMarkComplete());
			// userTestSession2.setNoOfAttempts(userTestSession2.getNoOfAttempts() + 1);
			// userTestSession2.setUpdateDate(new Date());
			userTestSessionRep.save(userTestSession2);
			return userTestSession2;
		}
	}

	private UserTestSession calculateResults(UserTestSession userTestSession, Test test) {
		List<QuestionMapperInstance> questionMapperInstances = questionMapperInstanceRepository
				.findQuestionMapperInstancesForUserForTest(userTestSession.getTestName(), userTestSession.getUser(),
						userTestSession.getCompanyId());
		Integer totalMarks = 0;
		Integer totalMarksRecieved = 0;
		for (QuestionMapperInstance questionMapperInstance : questionMapperInstances) {
			totalMarks += questionMapperInstance.getQuestionMapper().getPointsToAward();
			if (questionMapperInstance.getCorrect()) {
				totalMarksRecieved += questionMapperInstance.getQuestionMapper().getPointsToAward();
			}
		}
		float per = new Float(totalMarksRecieved) / new Float(totalMarks) * 100;
		DecimalFormat df = new DecimalFormat("##.##");
		userTestSession.setPercentageMarksRecieved(Float.parseFloat(df.format(per)));
		userTestSession.setTotalMarks(totalMarks);
		userTestSession.setTotalMarksRecieved(totalMarksRecieved);
		Float weightedScore = getWeightedScoreForTest(questionMapperInstances);
		userTestSession.setWeightedScorePercentage(weightedScore);
		// if(per >= test.getPassPercent() ) {
		if (weightedScore >= test.getPassPercent()) {
			userTestSession.setPass(true);
		} else {
			userTestSession.setPass(false);
		}
		return userTestSession;
	}

	private Float getWeightedScoreForTest(List<QuestionMapperInstance> instances) {
		Map<Integer, List<QuestionMapperInstance>> map = new HashMap<Integer, List<QuestionMapperInstance>>();
		for (QuestionMapperInstance instance : instances) {
			Integer weight = instance.getQuestionMapper().getQuestion().getQuestionWeight();
			if (weight == null) {
				weight = 1;
			}
			if (map.get(weight) == null) {
				List<QuestionMapperInstance> list = new ArrayList<QuestionMapperInstance>();
				list.add(instance);
				map.put(weight, list);
			} else {
				map.get(weight).add(instance);
			}
		}
		Map<Integer, Float> map_weight_percentage = new HashMap<Integer, Float>();
		for (Integer weight : map.keySet()) {
			List<QuestionMapperInstance> instances2 = map.get(weight);
			Integer noOfCorrect = 0;
			for (QuestionMapperInstance instance : instances2) {
				if (instance.getCorrect()) {
					noOfCorrect++;
				}
			}
			Float percentageForWeightQs = (float) (100 * noOfCorrect / instances2.size());
			map_weight_percentage.put(weight, percentageForWeightQs);
		}
		Integer totalWeight = 0;
		Float totalScore = 0f;
		for (Integer weight : map_weight_percentage.keySet()) {
			totalWeight += weight;
			Float percentageForWeight = map_weight_percentage.get(weight);
			totalScore += percentageForWeight * weight;
		}
		Float weightedScoreForTest = totalScore / totalWeight;
		return weightedScoreForTest;
	}

	private UserTestSession calculateResultsWithSubjective(UserTestSession userTestSession, Test test) {
		List<QuestionMapperInstance> questionMapperInstances = questionMapperInstanceRepository
				.findQuestionMapperInstancesForUserForTest(userTestSession.getTestName(), userTestSession.getUser(),
						userTestSession.getCompanyId());
		Float totalMarks = 0f;
		Float totalMarksRecieved = 0f;
		for (QuestionMapperInstance questionMapperInstance : questionMapperInstances) {
			totalMarks += questionMapperInstance.getQuestionMapper().getPointsToAward();
			if (questionMapperInstance.getCorrect()) {
				String type = questionMapperInstance.getQuestionMapper().getQuestion().getType();
				if (type.equals(QuestionType.CODING.getType())) {
					Float codeScore = questionMapperInstance.getCodingScore() / 100;
					totalMarksRecieved += codeScore;
				} else {
					totalMarksRecieved += questionMapperInstance.getQuestionMapper().getPointsToAward();
				}

			} else if ((questionMapperInstance.getSubjective() == null ? false : questionMapperInstance.getSubjective())
					&& questionMapperInstance.getMarksAssignedInPercentIncaseSubjective() != null) {
				Float percentage = questionMapperInstance.getMarksAssignedInPercentIncaseSubjective() * 1.0f;
				Float marksGot = percentage / 100;
				totalMarksRecieved += marksGot;
			}

		}
		float per = new Float(totalMarksRecieved) / new Float(totalMarks) * 100;
		DecimalFormat df = new DecimalFormat("##.##");
		userTestSession.setPercentageMarksRecieved(Float.parseFloat(df.format(per)));
		Integer totMarksInt = Math.round(totalMarks);
		userTestSession.setTotalMarks(totMarksInt);
		Integer totalMarksRecievedInt = Math.round(totalMarksRecieved);
		userTestSession.setTotalMarksRecieved(totalMarksRecievedInt);
		Float weightedScore = getWeightedScoreForTestWithSubjective(questionMapperInstances);
		userTestSession.setWeightedScorePercentage(weightedScore);
		// if(per >= test.getPassPercent() ) {
		if (weightedScore >= test.getPassPercent()) {
			userTestSession.setPass(true);
		} else {
			userTestSession.setPass(false);
		}
		return userTestSession;
	}

	private Float getWeightedScoreForTestWithSubjective(List<QuestionMapperInstance> instances) {
		Map<Integer, List<QuestionMapperInstance>> map = new HashMap<Integer, List<QuestionMapperInstance>>();
		for (QuestionMapperInstance instance : instances) {
			Integer weight = instance.getQuestionMapper().getQuestion().getQuestionWeight();
			if (weight == null) {
				weight = 1;
			}
			if (map.get(weight) == null) {
				List<QuestionMapperInstance> list = new ArrayList<QuestionMapperInstance>();
				list.add(instance);
				map.put(weight, list);
			} else {
				map.get(weight).add(instance);
			}
		}
		Map<Integer, Float> map_weight_percentage = new HashMap<Integer, Float>();
		for (Integer weight : map.keySet()) {
			List<QuestionMapperInstance> instances2 = map.get(weight);
			Float noOfCorrect = 0f;
			for (QuestionMapperInstance instance : instances2) {
				if (instance.getCorrect()) {
					noOfCorrect++;
				} else if ((instance.getSubjective() == null ? false : instance.getSubjective())
						&& instance.getMarksAssignedInPercentIncaseSubjective() != null) {
					Float percentage = instance.getMarksAssignedInPercentIncaseSubjective() * 1.0f;
					Float marksGot = percentage / 100;
					noOfCorrect += marksGot;
				}
			}
			Float percentageForWeightQs = (float) (100 * noOfCorrect / instances2.size());
			map_weight_percentage.put(weight, percentageForWeightQs);
		}
		Integer totalWeight = 0;
		Float totalScore = 0f;
		for (Integer weight : map_weight_percentage.keySet()) {
			totalWeight += weight;
			Float percentageForWeight = map_weight_percentage.get(weight);
			totalScore += percentageForWeight * weight;
		}
		Float weightedScoreForTest = totalScore / totalWeight;
		return weightedScoreForTest;
	}

	@Override
	public List<AssessmentTestData> getAllResultsData(String companyId) {
		return userTestSessionRep.getAllResultsData(companyId);
	}

	@Override
	public List<UserTestSession> findUserSessionsForTest(String testName, String companyId) {
		// TODO Auto-generated method stub
		return userTestSessionRep.findUserSessionsForTest(testName, companyId);
	}

	@Override
	public List<UserTestSession> findTestListForUser(String companyId, String user) {
		// TODO Auto-generated method stub
		return userTestSessionRep.findTestListForUser(companyId, user);
	}

	@Override
	public List<UserTestSession> findResultsForDay(String companyId, Date start, Date end) {
		// TODO Auto-generated method stub
		return userTestSessionRep.findResultsForDay(companyId, start, end);
	}

	@Override
	public List<UserTestSession> findSubjectiveResultsNotMarkedAsCompleteForInstitution(String companyId,
			String collegeName, String grade, String classifier) {
		// TODO Auto-generated method stub
		return userTestSessionRep.findSubjectiveResultsNotMarkedAsCompleteForInstitution(companyId, collegeName, grade,
				classifier);
	}

	@Override
	public List<UserTestSession> findSubjectiveResultsMarkedAsCompleteForInstitution(String companyId,
			String collegeName, String grade, String classifier) {
		// TODO Auto-generated method stub
		return userTestSessionRep.findSubjectiveResultsMarkedAsCompleteForInstitution(companyId, collegeName, grade,
				classifier);
	}

	@Override
	public UserTestSession findSessinById(Long sessionId) {
		// TODO Auto-generated method stub
		return userTestSessionRep.findById(sessionId).get();
	}

	@Override
	public List<String> findUniqueTestNamesForUser(String email, String companyId) {
		// TODO Auto-generated method stub
		return userTestSessionRep.findUniqueTestNamesForUser(email, companyId);
	}

	@Override
	public UserTestSession findByPrimaryKey(String email, String testName, String companyId) {
		// TODO Auto-generated method stub
		return userTestSessionRep.findByPrimaryKey(email, testName, companyId);
	}

	@Override
	public List<UserTestSession> findByTestNamePart(String email, String testName, String companyId) {
		// TODO Auto-generated method stub
		return userTestSessionRep.findByTestNamePart(email, testName, companyId);
	}

	@Override
	public List<UserTestSession> findTop10(String companyId) {
		// TODO Auto-generated method stub
		return userTestSessionRep.findTop10User(companyId);
	}
}
