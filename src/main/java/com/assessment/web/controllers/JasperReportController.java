package com.assessment.web.controllers;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.PropertyConfig;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.Campaign;
import com.assessment.data.CampaignCandidate;
import com.assessment.data.CampaignProfile;
import com.assessment.data.CampaignReviewer;
import com.assessment.data.CampaignTest;
import com.assessment.data.CandidateCampaignSchedule;
import com.assessment.data.CandidateProfileParams;
import com.assessment.data.LearnersProfiles1;
import com.assessment.data.LearnersProfiles2;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.ReviewerComment;
import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.repositories.CampaignRepository;
import com.assessment.repositories.UserRepository;
import com.assessment.services.CampaignCandidateService;
import com.assessment.services.CampaignProfileService;
import com.assessment.services.CandidateCampaignScheduleService;
import com.assessment.services.CandidateProfileParamsService;
import com.assessment.services.LearnersProfiles1Service;
import com.assessment.services.LearnersProfiles2Service;
import com.assessment.services.QuestionMapperInstanceService;
import com.assessment.services.ReviewerCommentService;
import com.assessment.services.UserTestSessionService;

@Controller
public class JasperReportController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserTestSessionService userTestSessionService;
	@Autowired
	CandidateProfileParamsService candidateProfileParamsService;
	@Autowired
	QuestionMapperInstanceService questionMapperInstanceService;
	@Autowired
	LearnersProfiles1Service profiles1Service;
	@Autowired
	LearnersProfiles2Service profiles2Service;
	@Autowired
	CampaignRepository campaignRepository;
	@Autowired
	CampaignCandidateService campaignCandidateService;
	@Autowired
	CampaignProfileService campaignProfileService;
	@Autowired
	ReviewerCommentService reviewerCommentService;
	@Autowired
	CandidateCampaignScheduleService campaignScheduleService;
	@Autowired
	PropertyConfig propertConfig;

	@GetMapping("/calculateProfileParamCampaignCandidate")
	@ResponseBody
	public Map<String, Object> calculateProfileParamCampaignCandidate(HttpServletRequest request) {
		User user1 = (User) request.getSession().getAttribute("user");
		Map<String, Object> map1 = new HashMap<>();
		List<User> listusers = userRepository.findByUserType();
		List<User> listusers2 = new ArrayList<>();

		Set<String> emails = new HashSet<>();
		for (User user : listusers) {
			emails.add(user.getEmail().replaceAll("\\[.*", ""));
		}
		for (String ee : emails) {
			for (User userr : listusers) {
				if (ee.equals(userr.getEmail())) {
					listusers2.add(userr);
					break;
				}
			}
		}
//		List<LearnersProfileParam> listParam = new ArrayList<LearnersProfileParam>();
		List<LearnersProfiles1> listParam = new ArrayList<LearnersProfiles1>();
//		List<PieChart1> chart1s = new ArrayList<>();
//		List<PieChart2> chart2s = new ArrayList<>();
		for (User user : listusers2) {
			List<UserTestSession> listUserTestSession = userTestSessionService.findTestListForUser(user1.getCompanyId(), user.getEmail().replaceAll("\\[.*", ""));
//			List<UserTestSession> listUserTestSession = new ArrayList<UserTestSession>();
//			for (UserTestSession session : userTestSessions) {
//				listUserTestSession.add(session);
//			}
//			System.out.println("size of user TestSession:    " + userTestSessions.size());

			System.out.println("size of All user's TestSession:    " + listUserTestSession.size());
			System.out.println("user Email:     " + user.getEmail());
			List<CandidateProfileParams> candidateProfileParams = candidateProfileParamsService.findCandidateProfileParamsByCompanyId(user1.getCompanyId());
			Map<CandidateProfileParams, List<QuestionMapperInstance>> map = new HashMap<>();
			List<QuestionMapperInstance> answers = new ArrayList<QuestionMapperInstance>();
			for (UserTestSession testSession : listUserTestSession) {
				List<QuestionMapperInstance> answers2 = questionMapperInstanceService.findQuestionMapperInstancesForUserForTest(testSession.getTestName(),
											testSession.getUser().replaceAll("\\[.*", ""), testSession.getCompanyId());
				for (QuestionMapperInstance instance : answers2) {
					answers.add(instance);
				}
			}

			for (QuestionMapperInstance ans : answers) {
				CandidateProfileParams param = new CandidateProfileParams(ans.getQuestionMapper().getQuestion().getQualifier1(),
											ans.getQuestionMapper().getQuestion().getQualifier2(),
											ans.getQuestionMapper().getQuestion().getQualifier3(),
											ans.getQuestionMapper().getQuestion().getQualifier4(),
											ans.getQuestionMapper().getQuestion().getQualifier5());
				if (map.get(param) == null) {
					List<QuestionMapperInstance> ins = new ArrayList<>();
					ins.add(ans);
					map.put(param, ins);
				} else {
					map.get(param).add(ans);
				}
			}
			DecimalFormat df = new DecimalFormat("#.##");
//			Map<CandidateProfileParams, Float> mapPer = new HashMap<>();
			Map<CandidateProfileParams, String> mapTrait = new HashMap<>();
//			List<LearnersProfiles1> learnersProfiles1s = new ArrayList<LearnersProfiles1>();
			for (CandidateProfileParams param : map.keySet()) {
				List<QuestionMapperInstance> answersForQualifier = map.get(param);
				int noOfCorrect = 0;
				for (QuestionMapperInstance ans : answersForQualifier) {
					if (ans.getCorrect()) {
						noOfCorrect++;
					}
				}
				LearnersProfiles1 lp = new LearnersProfiles1();
				Float percent = Float.parseFloat(df.format(noOfCorrect * 100 / answersForQualifier.size()));
//				mapPer.put(param, percent);
				int index = candidateProfileParams.indexOf(param);
				String trait = "";
				if (index != -1) {
					CandidateProfileParams paramWithData = candidateProfileParams.get(index);
					if (percent < 20) {
						trait = paramWithData.getLESS_THAN_TWENTY_PERCENT();
					} else if (percent >= 20 && percent < 50) {
						trait = paramWithData.getBETWEEN_TWENTY_AND_FIFTY();
					} else if (percent >= 50 && percent < 75) {
						trait = paramWithData.getBETWEEN_FIFTY_AND_SEVENTYFIVE();
					} else if (percent >= 75 && percent < 90) {
						trait = paramWithData.getBETWEEN_SEVENTYFIVE_AND_NINETY();
					} else if (percent > 90) {
						trait = paramWithData.getMORE_THAN_NINETY();
					}
					mapTrait.put(param, trait);
				}

//				for (CandidateProfileParams param2 : param) {
				String qual = param.getQualifier1();
				lp.setQualifier1(qual.toUpperCase());
				if (param.getQualifier2() != null && !param.getQualifier2().equals("NA")) {
					lp.setQualifier2(param.getQualifier2().toUpperCase());
				}
				if (param.getQualifier3() != null && !param.getQualifier3().equals("NA")) {
					lp.setQualifier3(param.getQualifier3().toUpperCase());
				}
				if (param.getQualifier4() != null && !param.getQualifier4().equals("NA")) {
					lp.setQualifier4(param.getQualifier4().toUpperCase());
				}
				if (param.getQualifier5() != null && !param.getQualifier5().equals("NA")) {
					lp.setQualifier5(param.getQualifier5().toUpperCase());
				}
//				}
				mapTrait.clear();
				lp.setQparamValue(trait);
				lp.setCompanyId(user.getCompanyId());
				lp.setCompanyName(user.getCompanyName());
				lp.setUserEmail(user.getEmail());
				lp.setPercent(percent);
				listParam.add(lp);
			}

		}
		profiles1Service.saveOrUpdate(listParam);
		map1.put("msg", "Done");
		return map1;
	}

	@GetMapping("/profileParam2")
	@ResponseBody
	public Map<String, Object> calculateProfileParam2(HttpServletRequest request, @RequestParam String testName) {
		User user1 = (User) request.getSession().getAttribute("user");
		Map<String, Object> map1 = new HashMap<>();
		List<User> listusers = userRepository.findByUserType();
		List<User> listusers2 = new ArrayList<>();

		Set<String> emails = new HashSet<>();
		for (User user : listusers) {
			emails.add(user.getEmail().replaceAll("\\[.*", ""));
		}
		for (String ee : emails) {
			for (User userr : listusers) {
				if (ee.equals(userr.getEmail())) {
					listusers2.add(userr);
					break;
				}
			}
		}
		List<LearnersProfiles2> listParam = new ArrayList<LearnersProfiles2>();
		for (User user : listusers2) {
			List<UserTestSession> listUserTestSession = userTestSessionService.findByTestNamePart(user.getEmail().replaceAll("\\[.*", ""), testName,
										user1.getCompanyId());

			System.out.println("size of All user's TestSession:    " + listUserTestSession.size());
			System.out.println("user Email:     " + user.getEmail());
			List<CandidateProfileParams> candidateProfileParams = candidateProfileParamsService.findCandidateProfileParamsByCompanyId(user1.getCompanyId());
			Map<CandidateProfileParams, List<QuestionMapperInstance>> map = new HashMap<>();
			List<QuestionMapperInstance> answers = new ArrayList<QuestionMapperInstance>();
			for (UserTestSession testSession : listUserTestSession) {
				List<QuestionMapperInstance> answers2 = questionMapperInstanceService.findQuestionMapperInstancesForUserForTest(testSession.getTestName(),
											testSession.getUser().replaceAll("\\[.*", ""), testSession.getCompanyId());
				for (QuestionMapperInstance instance : answers2) {
					answers.add(instance);
				}
			}

			for (QuestionMapperInstance ans : answers) {
				CandidateProfileParams param = new CandidateProfileParams(ans.getQuestionMapper().getQuestion().getQualifier1(),
											ans.getQuestionMapper().getQuestion().getQualifier2(),
											ans.getQuestionMapper().getQuestion().getQualifier3(),
											ans.getQuestionMapper().getQuestion().getQualifier4(),
											ans.getQuestionMapper().getQuestion().getQualifier5());
				if (map.get(param) == null) {
					List<QuestionMapperInstance> ins = new ArrayList<>();
					ins.add(ans);
					map.put(param, ins);
				} else {
					map.get(param).add(ans);
				}
			}
			DecimalFormat df = new DecimalFormat("#.##");
			Map<CandidateProfileParams, String> mapTrait = new HashMap<>();
			for (CandidateProfileParams param : map.keySet()) {
				List<QuestionMapperInstance> answersForQualifier = map.get(param);
				int noOfCorrect = 0;
				for (QuestionMapperInstance ans : answersForQualifier) {
					if (ans.getCorrect()) {
						noOfCorrect++;
					}
				}
				LearnersProfiles2 lp = new LearnersProfiles2();
				Float percent = Float.parseFloat(df.format(noOfCorrect * 100 / answersForQualifier.size()));
				int index = candidateProfileParams.indexOf(param);
				String trait = "";
				if (index != -1) {
					CandidateProfileParams paramWithData = candidateProfileParams.get(index);
					if (percent < 20) {
						trait = paramWithData.getLESS_THAN_TWENTY_PERCENT();
					} else if (percent >= 20 && percent < 50) {
						trait = paramWithData.getBETWEEN_TWENTY_AND_FIFTY();
					} else if (percent >= 50 && percent < 75) {
						trait = paramWithData.getBETWEEN_FIFTY_AND_SEVENTYFIVE();
					} else if (percent >= 75 && percent < 90) {
						trait = paramWithData.getBETWEEN_SEVENTYFIVE_AND_NINETY();
					} else if (percent > 90) {
						trait = paramWithData.getMORE_THAN_NINETY();
					}
					mapTrait.put(param, trait);
				}
				String qual = param.getQualifier1();
				lp.setQualifier1(qual.toUpperCase());
				if (param.getQualifier2() != null && !param.getQualifier2().equals("NA")) {
					lp.setQualifier2(param.getQualifier2().toUpperCase());
				}
				if (param.getQualifier3() != null && !param.getQualifier3().equals("NA")) {
					lp.setQualifier3(param.getQualifier3().toUpperCase());
				}
				if (param.getQualifier4() != null && !param.getQualifier4().equals("NA")) {
					lp.setQualifier4(param.getQualifier4().toUpperCase());
				}
				if (param.getQualifier5() != null && !param.getQualifier5().equals("NA")) {
					lp.setQualifier5(param.getQualifier5().toUpperCase());
				}
				mapTrait.clear();
				lp.setTestName(testName);
				lp.setQparamValue(trait);
				lp.setCompanyId(user.getCompanyId());
				lp.setCompanyName(user.getCompanyName());
				lp.setUserEmail(user.getEmail());
				lp.setPercent(percent);
				listParam.add(lp);
			}

		}
		profiles2Service.saveOrUpdate(listParam);
		map1.put("msg", "Done");
		return map1;
	}

	@GetMapping("/campaignProfileParam")
	@ResponseBody
	public Map<String, Object> campaignProfileParam(HttpServletRequest request) {
		User user1 = (User) request.getSession().getAttribute("user");
		Map<String, Object> map1 = new HashMap<>();
		List<Campaign> campaigns = campaignRepository.findByCompanyId(user1.getCompanyId());

		for (Campaign campaign : campaigns) {
			List<CampaignCandidate> campaignCandidates = campaignCandidateService.findByCampaignNameAndCompanyId(campaign.getCampaignName(), user1.getCompanyId());
			List<CampaignProfile> listParam = new ArrayList<CampaignProfile>();
			for (CampaignCandidate campaignCandidate : campaignCandidates) {
				List<UserTestSession> listUserTestSession = new ArrayList<UserTestSession>();
				for (CampaignTest campaignTest : campaign.getRounds()) {
					UserTestSession listUserTestSession2 = userTestSessionService.findByPrimaryKey(campaignCandidate.getEmail(),
												campaignTest.getTestName(), user1.getCompanyId());
					if (listUserTestSession2 != null) {
						listUserTestSession.add(listUserTestSession2);
					}
				}

//				
				System.out.println("size of All user's TestSession:    " + listUserTestSession.size());
				if (listUserTestSession.size() == 0) {
					continue;
				}

				List<CandidateProfileParams> candidateProfileParams = candidateProfileParamsService.findCandidateProfileParamsByCompanyId(user1.getCompanyId());
				Map<CandidateProfileParams, List<QuestionMapperInstance>> map = new HashMap<>();
				List<QuestionMapperInstance> answers = new ArrayList<QuestionMapperInstance>();
				for (UserTestSession testSession : listUserTestSession) {
					List<QuestionMapperInstance> answers2 = questionMapperInstanceService.findQuestionMapperInstancesForUserForTest(
												testSession.getTestName(),
												testSession.getUser().replaceAll("\\[.*", ""),
												testSession.getCompanyId());
					for (QuestionMapperInstance instance : answers2) {
						answers.add(instance);
					}
				}

				for (QuestionMapperInstance ans : answers) {
					CandidateProfileParams param = new CandidateProfileParams(ans.getQuestionMapper().getQuestion().getQualifier1(),
												ans.getQuestionMapper().getQuestion().getQualifier2(),
												ans.getQuestionMapper().getQuestion().getQualifier3(),
												ans.getQuestionMapper().getQuestion().getQualifier4(),
												ans.getQuestionMapper().getQuestion().getQualifier5());
					if (map.get(param) == null) {
						List<QuestionMapperInstance> ins = new ArrayList<>();
						ins.add(ans);
						map.put(param, ins);
					} else {
						map.get(param).add(ans);
					}
				}
				DecimalFormat df = new DecimalFormat("#.##");
				Map<CandidateProfileParams, String> mapTrait = new HashMap<>();
				for (CandidateProfileParams param : map.keySet()) {
					List<QuestionMapperInstance> answersForQualifier = map.get(param);
					int noOfCorrect = 0;
					for (QuestionMapperInstance ans : answersForQualifier) {
						if (ans.getCorrect()) {
							noOfCorrect++;
						}
					}
					CampaignProfile lp = new CampaignProfile();
					Float percent = Float.parseFloat(df.format(noOfCorrect * 100 / answersForQualifier.size()));
					int index = candidateProfileParams.indexOf(param);
					String trait = "";
					if (index != -1) {
						CandidateProfileParams paramWithData = candidateProfileParams.get(index);
						if (percent < 20) {
							trait = paramWithData.getLESS_THAN_TWENTY_PERCENT();
						} else if (percent >= 20 && percent < 50) {
							trait = paramWithData.getBETWEEN_TWENTY_AND_FIFTY();
						} else if (percent >= 50 && percent < 75) {
							trait = paramWithData.getBETWEEN_FIFTY_AND_SEVENTYFIVE();
						} else if (percent >= 75 && percent < 90) {
							trait = paramWithData.getBETWEEN_SEVENTYFIVE_AND_NINETY();
						} else if (percent > 90) {
							trait = paramWithData.getMORE_THAN_NINETY();
						}
						mapTrait.put(param, trait);
					}
					String qual = param.getQualifier1();
					lp.setQualifier1(qual.toUpperCase());
					if (param.getQualifier2() != null && !param.getQualifier2().equals("NA")) {
						lp.setQualifier2(param.getQualifier2().toUpperCase());
					}
					if (param.getQualifier3() != null && !param.getQualifier3().equals("NA")) {
						lp.setQualifier3(param.getQualifier3().toUpperCase());
					}
					if (param.getQualifier4() != null && !param.getQualifier4().equals("NA")) {
						lp.setQualifier4(param.getQualifier4().toUpperCase());
					}
					if (param.getQualifier5() != null && !param.getQualifier5().equals("NA")) {
						lp.setQualifier5(param.getQualifier5().toUpperCase());
					}
					mapTrait.clear();
					lp.setQparamValue(trait);
					lp.setCompanyId(user1.getCompanyId());
					lp.setCompanyName(user1.getCompanyName());
					lp.setUserEmail(campaignCandidate.getEmail());
					lp.setPercent(percent);
					lp.setCampaignName(campaignCandidate.getCampaignName());
					listParam.add(lp);
				}
				campaignProfileService.saveOrUpdate(listParam);
			}
		}

		map1.put("msg", "Done");
		return map1;
	}

	@GetMapping("/getUserReport")
//	@ResponseBody
	public ModelAndView getUserReport(@RequestParam(name = "reviewer", required = false) String reviewer, @RequestParam String campName, @RequestParam String candEmail,
								@RequestParam String compId) {
//		User user1 = (User) request.getSession().getAttribute("user");
		Map<String, Object> map1 = new HashMap<>();
		ModelAndView mav = new ModelAndView("reviewComment");
		User user = userRepository.findByPrimaryKey(candEmail, compId);
//		List<Campaign> campaigns = campaignRepository.findByCompanyId(user1.getCompanyId());
		Campaign campaign = campaignRepository.findUniqueCampaign(compId, campName);

//		for (Campaign campaign : campaigns) {
//			List<CampaignCandidate> campaignCandidates = campaignCandidateService.findByCampaignNameAndCompanyId(campaign.getCampaignName(), compId);
		List<CampaignProfile> listParam = new ArrayList<CampaignProfile>();
//			for (CampaignCandidate campaignCandidate : campaignCandidates) {
		CampaignCandidate campaignCandidate = campaignCandidateService.findCampaignCandidate(compId, campName, candEmail);
		List<UserTestSession> listUserTestSession = new ArrayList<UserTestSession>();
		for (CampaignTest campaignTest : campaign.getRounds()) {
			UserTestSession listUserTestSession2 = userTestSessionService.findByPrimaryKey(campaignCandidate.getEmail(), campaignTest.getTestName(), compId);
			if (listUserTestSession2 != null) {
				listUserTestSession.add(listUserTestSession2);
			}
		}

//				
		System.out.println("size of All user's TestSession:    " + listUserTestSession.size());
//				if(listUserTestSession.size()==0) {
//					continue;
//				}

		List<CandidateProfileParams> candidateProfileParams = candidateProfileParamsService.findCandidateProfileParamsByCompanyId(compId);
		for (CandidateProfileParams p : candidateProfileParams) {
			if (p.getQualifier1() != null) {
				p.setQualifier1(p.getQualifier1().trim());
			}

			if (p.getQualifier2() != null) {
				p.setQualifier2(p.getQualifier2().trim());
			}

			if (p.getQualifier3() != null) {
				p.setQualifier3(p.getQualifier3().trim());
			}

			if (p.getQualifier4() != null) {
				p.setQualifier4(p.getQualifier4().trim());
			}

			if (p.getQualifier5() != null) {
				p.setQualifier5(p.getQualifier5().trim());
			}
		}

		Map<CandidateProfileParams, List<QuestionMapperInstance>> map = new HashMap<>();
		List<QuestionMapperInstance> answers = new ArrayList<QuestionMapperInstance>();
		for (UserTestSession testSession : listUserTestSession) {
			List<QuestionMapperInstance> answers2 = questionMapperInstanceService.findQuestionMapperInstancesForUserForTest(testSession.getTestName(),
										testSession.getUser().replaceAll("\\[.*", ""), testSession.getCompanyId());
			for (QuestionMapperInstance instance : answers2) {
				answers.add(instance);
			}
		}

		for (QuestionMapperInstance ans : answers) {
			if (ans.getQuestionMapper().getQuestion().getQualifier1() != null) {
				String q = ans.getQuestionMapper().getQuestion().getQualifier1().trim();
				ans.getQuestionMapper().getQuestion().setQualifier1(q);
			}

			if (ans.getQuestionMapper().getQuestion().getQualifier2() != null) {
				String q = ans.getQuestionMapper().getQuestion().getQualifier2().trim();
				ans.getQuestionMapper().getQuestion().setQualifier2(q);
			}

			if (ans.getQuestionMapper().getQuestion().getQualifier3() != null) {
				String q = ans.getQuestionMapper().getQuestion().getQualifier3().trim();
				ans.getQuestionMapper().getQuestion().setQualifier3(q);
			}

			if (ans.getQuestionMapper().getQuestion().getQualifier4() != null) {
				String q = ans.getQuestionMapper().getQuestion().getQualifier4().trim();
				ans.getQuestionMapper().getQuestion().setQualifier4(q);
			}

			if (ans.getQuestionMapper().getQuestion().getQualifier5() != null) {
				String q = ans.getQuestionMapper().getQuestion().getQualifier5().trim();
				ans.getQuestionMapper().getQuestion().setQualifier5(q);
			}

			CandidateProfileParams param = new CandidateProfileParams(ans.getQuestionMapper().getQuestion().getQualifier1(),
										ans.getQuestionMapper().getQuestion().getQualifier2(),
										ans.getQuestionMapper().getQuestion().getQualifier3(),
										ans.getQuestionMapper().getQuestion().getQualifier4(),
										ans.getQuestionMapper().getQuestion().getQualifier5());
			if (map.get(param) == null) {
				List<QuestionMapperInstance> ins = new ArrayList<>();
				ins.add(ans);
				map.put(param, ins);
			} else {
				map.get(param).add(ans);
			}
		}
		DecimalFormat df = new DecimalFormat("#.##");
		Map<CandidateProfileParams, String> mapTrait = new HashMap<>();
		for (CandidateProfileParams param : map.keySet()) {
			List<QuestionMapperInstance> answersForQualifier = map.get(param);
			int noOfCorrect = 0;
			for (QuestionMapperInstance ans : answersForQualifier) {
				if (ans.getCorrect()) {
					noOfCorrect++;
				}
			}
			CampaignProfile lp = new CampaignProfile();
			Float percent = Float.parseFloat(df.format(noOfCorrect * 100 / answersForQualifier.size()));
			int index = candidateProfileParams.indexOf(param);
			String trait = "";
			if (index != -1) {
				CandidateProfileParams paramWithData = candidateProfileParams.get(index);
				if (percent < 20) {
					trait = paramWithData.getLESS_THAN_TWENTY_PERCENT();
				} else if (percent >= 20 && percent < 50) {
					trait = paramWithData.getBETWEEN_TWENTY_AND_FIFTY();
				} else if (percent >= 50 && percent < 75) {
					trait = paramWithData.getBETWEEN_FIFTY_AND_SEVENTYFIVE();
				} else if (percent >= 75 && percent < 90) {
					trait = paramWithData.getBETWEEN_SEVENTYFIVE_AND_NINETY();
				} else if (percent > 90) {
					trait = paramWithData.getMORE_THAN_NINETY();
				}
				mapTrait.put(param, trait);
			}
			String qual = param.getQualifier1();
			lp.setQualifier1(qual.toUpperCase());
			if (param.getQualifier2() != null && !param.getQualifier2().equals("NA")) {
				lp.setQualifier2(param.getQualifier2().toUpperCase());
			}
			if (param.getQualifier3() != null && !param.getQualifier3().equals("NA")) {
				lp.setQualifier3(param.getQualifier3().toUpperCase());
			}
			if (param.getQualifier4() != null && !param.getQualifier4().equals("NA")) {
				lp.setQualifier4(param.getQualifier4().toUpperCase());
			}
			if (param.getQualifier5() != null && !param.getQualifier5().equals("NA")) {
				lp.setQualifier5(param.getQualifier5().toUpperCase());
			}
			mapTrait.clear();
			lp.setQparamValue(trait);
			lp.setCompanyId(compId);
			lp.setCompanyName(compId);
			lp.setUserEmail(campaignCandidate.getEmail());
			lp.setPercent(percent);
			lp.setCampaignName(campaignCandidate.getCampaignName());
			listParam.add(lp);
		}

		if (listParam.size() == 0) {
			return new ModelAndView("errorPageReviewer", "msg", "Profile does not exist for the given User for the said Campaign");
		} else {
			campaignProfileService.saveOrUpdate(listParam);
		}
//			}
//		}
		ReviewerComment reviewerComment = reviewerCommentService.findByPrimaryKey(campName, candEmail, compId);
		if (reviewerComment == null) {
			reviewerComment = new ReviewerComment();
			reviewerComment.setCampaignName(campaign.getCampaignName());
			reviewerComment.setCampaignId(campaign.getId());
			reviewerComment.setCandidateEmail(candEmail);
			reviewerComment.setCandidateFirstName(user.getFirstName());
			reviewerComment.setCandidateLastName(user.getLastName());
			reviewerComment.setCompanyId(user.getCompanyId());
			reviewerComment.setCompanyName(user.getCompanyName());
			// reviewerComment.setReviewers(allReviewers);
			reviewerComment.setActualReviewerEmail(reviewer);
			mav.addObject("reviewerComment", reviewerComment);
		} else {
			// reviewerComment.setReviewers(allReviewers);
//			reviewerComment.setActualReviewerEmail(reviewer);
			mav.addObject("reviewerComment", reviewerComment);
		}
//		map1.put("msg", "Done");
//		return map1;
		String jasper = propertConfig.getJasperServerBaseUrl();
		mav.addObject("url", jasper + "CampaignProfilePie2.html?user=" + candEmail + "&campName=" + campName);
		mav.addObject("url2", jasper + "subCampaignReport2.html?user=" + candEmail + "&campName=" + campName);
		mav.addObject("url3", jasper + "CampaignProfilePie_Pie2.html?user=" + candEmail + "&campName=" + campName);
		return mav;
	}

	@PostMapping("saveReviewerComment")
	@ResponseBody
	public Map<String, Object> saveReviewerComment(@ModelAttribute("reviewerComment") ReviewerComment reviewerComment, HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("saveReviewer");
		Map<String, Object> map = new HashMap<>();
		reviewerCommentService.saveOrUpdate(reviewerComment);
//		mav.addObject("msg", "Saved Successfully");
//		return mav;
		CampaignReviewer campaignReviewer = (CampaignReviewer) request.getSession().getAttribute("campaignReviewer");
		map.put("msg", "Feedback Saved Successfully. Kindly close the window");
		sendEmailToReviewer(campaignReviewer, reviewerComment);
		return map;
	}

	private void sendEmailToReviewer(CampaignReviewer campaignReviewer, ReviewerComment reviewerComment) {
		try {
			String html = propertConfig.getCampaignStatusLinkLocation();
			String welcomeMailData = FileUtils.readFileToString(new File(html));
			welcomeMailData = welcomeMailData.replace("{FULL_NAME}", campaignReviewer.getFirstName() == null ? "" : campaignReviewer.getFirstName());
			welcomeMailData = welcomeMailData.replace("${CAMPAIGN_NAME}", reviewerComment.getCampaignName());
			String name = reviewerComment.getCandidateFirstName() == null ? ""
										: reviewerComment.getCandidateFirstName() + " " + reviewerComment
																	.getCandidateLastName() == null ? ""
																								: reviewerComment.getCandidateLastName();
			welcomeMailData = welcomeMailData.replace("${CAND_NAME}", name);
			welcomeMailData = welcomeMailData.replace("${technicalCompetencyInCoreAreas}", reviewerComment.getTechnicalCompetencyInCoreAreas());
			welcomeMailData = welcomeMailData.replace("${technicalCompetencyInAncillaryAreas}", reviewerComment.getTechnicalCompetencyInAncillaryAreas());
			welcomeMailData = welcomeMailData.replace("${analyticalSkills}", reviewerComment.getAnalyticalSkills());
			welcomeMailData = welcomeMailData.replace("${communicationSkills}", reviewerComment.getCommunicationSkills());
			welcomeMailData = welcomeMailData.replace("${overallReviewStatus}", reviewerComment.getOverallReviewStatus());
			welcomeMailData = welcomeMailData.replace("${status}", (reviewerComment.getStatus() != null && reviewerComment.getStatus() == true)
										? "Cleared to Proceed"
										: "Hold Back");

			EmailGenericMessageThread client = new EmailGenericMessageThread(campaignReviewer.getEmail(),
										"Feedback for Campaign - " + reviewerComment.getCampaignName() + " captured for " + name,
										welcomeMailData, propertConfig);
			// client.setSetStatus(true);
			Thread th = new Thread(client);
			th.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// logger.er
		}
	}

	@GetMapping("/getUserReport2")
//	@ResponseBody
	public ModelAndView getUserReport2(@RequestParam String campName, @RequestParam String candEmail, @RequestParam String compId) {
//		User user1 = (User) request.getSession().getAttribute("user");
		Map<String, Object> map1 = new HashMap<>();
		ModelAndView mav = new ModelAndView("reviewComment2");
		User user = userRepository.findByPrimaryKey(candEmail, compId);
//		List<Campaign> campaigns = campaignRepository.findByCompanyId(user1.getCompanyId());
		Campaign campaign = campaignRepository.findUniqueCampaign(compId, campName);

//		for (Campaign campaign : campaigns) {
//			List<CampaignCandidate> campaignCandidates = campaignCandidateService.findByCampaignNameAndCompanyId(campaign.getCampaignName(), compId);
		List<CampaignProfile> listParam = new ArrayList<CampaignProfile>();
//			for (CampaignCandidate campaignCandidate : campaignCandidates) {
		CandidateCampaignSchedule campaignCandidate = campaignScheduleService.findByEmailAndCampaignName(candEmail, campName, compId);

		List<UserTestSession> listUserTestSession = new ArrayList<UserTestSession>();
		if (campaignCandidate != null) {
			for (CampaignTest campaignTest : campaign.getRounds()) {
				UserTestSession listUserTestSession2 = userTestSessionService.findByPrimaryKey(campaignCandidate.getEmail(), campaignTest.getTestName(),
											compId);
				if (listUserTestSession2 != null) {
					listUserTestSession.add(listUserTestSession2);
				}
			}
		}

//				
		System.out.println("size of All user's TestSession:    " + listUserTestSession.size());
//				if(listUserTestSession.size()==0) {
//					continue;
//				}

		List<CandidateProfileParams> candidateProfileParams = candidateProfileParamsService.findCandidateProfileParamsByCompanyId(compId);
		for (CandidateProfileParams p : candidateProfileParams) {
			if (p.getQualifier1() != null) {
				p.setQualifier1(p.getQualifier1().trim());
			}

			if (p.getQualifier2() != null) {
				p.setQualifier2(p.getQualifier2().trim());
			}

			if (p.getQualifier3() != null) {
				p.setQualifier3(p.getQualifier3().trim());
			}

			if (p.getQualifier4() != null) {
				p.setQualifier4(p.getQualifier4().trim());
			}

			if (p.getQualifier5() != null) {
				p.setQualifier5(p.getQualifier5().trim());
			}
		}

		Map<CandidateProfileParams, List<QuestionMapperInstance>> map = new HashMap<>();
		List<QuestionMapperInstance> answers = new ArrayList<QuestionMapperInstance>();
		for (UserTestSession testSession : listUserTestSession) {
			List<QuestionMapperInstance> answers2 = questionMapperInstanceService.findQuestionMapperInstancesForUserForTest(testSession.getTestName(),
										testSession.getUser().replaceAll("\\[.*", ""), testSession.getCompanyId());
			for (QuestionMapperInstance instance : answers2) {
				answers.add(instance);
			}
		}

		for (QuestionMapperInstance ans : answers) {
			if (ans.getQuestionMapper().getQuestion().getQualifier1() != null) {
				String q = ans.getQuestionMapper().getQuestion().getQualifier1().trim();
				ans.getQuestionMapper().getQuestion().setQualifier1(q);
			}

			if (ans.getQuestionMapper().getQuestion().getQualifier2() != null) {
				String q = ans.getQuestionMapper().getQuestion().getQualifier2().trim();
				ans.getQuestionMapper().getQuestion().setQualifier2(q);
			}

			if (ans.getQuestionMapper().getQuestion().getQualifier3() != null) {
				String q = ans.getQuestionMapper().getQuestion().getQualifier3().trim();
				ans.getQuestionMapper().getQuestion().setQualifier3(q);
			}

			if (ans.getQuestionMapper().getQuestion().getQualifier4() != null) {
				String q = ans.getQuestionMapper().getQuestion().getQualifier4().trim();
				ans.getQuestionMapper().getQuestion().setQualifier4(q);
			}

			if (ans.getQuestionMapper().getQuestion().getQualifier5() != null) {
				String q = ans.getQuestionMapper().getQuestion().getQualifier5().trim();
				ans.getQuestionMapper().getQuestion().setQualifier5(q);
			}

			CandidateProfileParams param = new CandidateProfileParams(ans.getQuestionMapper().getQuestion().getQualifier1(),
										ans.getQuestionMapper().getQuestion().getQualifier2(),
										ans.getQuestionMapper().getQuestion().getQualifier3(),
										ans.getQuestionMapper().getQuestion().getQualifier4(),
										ans.getQuestionMapper().getQuestion().getQualifier5());
			if (map.get(param) == null) {
				List<QuestionMapperInstance> ins = new ArrayList<>();
				ins.add(ans);
				map.put(param, ins);
			} else {
				map.get(param).add(ans);
			}
		}
		DecimalFormat df = new DecimalFormat("#.##");
		Map<CandidateProfileParams, String> mapTrait = new HashMap<>();
		for (CandidateProfileParams param : map.keySet()) {
			List<QuestionMapperInstance> answersForQualifier = map.get(param);
			int noOfCorrect = 0;
			for (QuestionMapperInstance ans : answersForQualifier) {
				if (ans.getCorrect()) {
					noOfCorrect++;
				}
			}
			CampaignProfile lp = new CampaignProfile();
			Float percent = Float.parseFloat(df.format(noOfCorrect * 100 / answersForQualifier.size()));
			int index = candidateProfileParams.indexOf(param);
			String trait = "";
			if (index != -1) {
				CandidateProfileParams paramWithData = candidateProfileParams.get(index);
				if (percent < 20) {
					trait = paramWithData.getLESS_THAN_TWENTY_PERCENT();
				} else if (percent >= 20 && percent < 50) {
					trait = paramWithData.getBETWEEN_TWENTY_AND_FIFTY();
				} else if (percent >= 50 && percent < 75) {
					trait = paramWithData.getBETWEEN_FIFTY_AND_SEVENTYFIVE();
				} else if (percent >= 75 && percent < 90) {
					trait = paramWithData.getBETWEEN_SEVENTYFIVE_AND_NINETY();
				} else if (percent > 90) {
					trait = paramWithData.getMORE_THAN_NINETY();
				}
				mapTrait.put(param, trait);
			}
			String qual = param.getQualifier1();
			lp.setQualifier1(qual.toUpperCase());
			if (param.getQualifier2() != null && !param.getQualifier2().equals("NA")) {
				lp.setQualifier2(param.getQualifier2().toUpperCase());
			}
			if (param.getQualifier3() != null && !param.getQualifier3().equals("NA")) {
				lp.setQualifier3(param.getQualifier3().toUpperCase());
			}
			if (param.getQualifier4() != null && !param.getQualifier4().equals("NA")) {
				lp.setQualifier4(param.getQualifier4().toUpperCase());
			}
			if (param.getQualifier5() != null && !param.getQualifier5().equals("NA")) {
				lp.setQualifier5(param.getQualifier5().toUpperCase());
			}
			mapTrait.clear();
			lp.setQparamValue(trait);
			lp.setCompanyId(compId);
			lp.setCompanyName(compId);
			lp.setUserEmail(campaignCandidate.getEmail());
			lp.setPercent(percent);
			lp.setCampaignName(campaignCandidate.getCampaignName());
			listParam.add(lp);
		}

		if (listParam.size() == 0) {
			return new ModelAndView("errorPageReviewer", "msg", "Profile does not exist for the given User for the said Campaign");
		} else {
			campaignProfileService.saveOrUpdate(listParam);
		}

		String jasper = propertConfig.getJasperServerBaseUrl();
		mav.addObject("url", jasper + "CampaignProfilePie.html?user=" + candEmail + "&campName=" + campName);
		mav.addObject("url2", jasper + "subCampaignReport.html?user=" + candEmail + "&campName=" + campName);

		mav.addObject("url3", jasper + "CampaignProfilePie_Pie.html?user=" + candEmail + "&campName=" + campName);
		return mav;
	}
	

	
	@GetMapping("/getComment")
//	@ResponseBody
	public ModelAndView getComment(@RequestParam String campName, @RequestParam String candEmail, @RequestParam String compId) {
		ModelAndView mav = new ModelAndView("candidateComment");
		ReviewerComment reviewerComment = reviewerCommentService.findByPrimaryKey(campName, candEmail, compId);
		if(reviewerComment==null) {
			mav.addObject("reviewerComment", new ReviewerComment());
		}else {
			mav.addObject("reviewerComment", reviewerComment);
		}
		return mav;
	}
	
}
