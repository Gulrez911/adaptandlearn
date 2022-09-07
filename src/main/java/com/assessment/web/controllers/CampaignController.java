package com.assessment.web.controllers;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.common.PropertyConfig;
import com.assessment.common.Qualifiers;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.Campaign;
import com.assessment.data.CampaignCandidate;
import com.assessment.data.CampaignReviewer;
import com.assessment.data.CampaignTest;
import com.assessment.data.ReviewerCampaignMapping;
import com.assessment.data.User;
import com.assessment.repositories.CampaignRepository;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.services.CampaignCandidateService;
import com.assessment.services.CampaignReviewerService;
import com.assessment.services.CampaignService;
import com.assessment.services.ReviewerCampaignMappingService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;

@Controller
public class CampaignController {

	@Autowired
	CampaignService campaignSevice;

	@Autowired
	CampaignRepository campaignRep;

	@Autowired
	TestService testService;

	@Autowired
	QuestionMapperRepository questionMapperRep;

	@Autowired
	CampaignCandidateService campaignCandidateService;

	@Autowired
	ReviewerCampaignMappingService reviewerMappingService;

	@Autowired
	UserService userService;

	@Autowired
	CampaignReviewerService campaignReviewerService;

	@Autowired
	PropertyConfig propertyConfig;

	@RequestMapping(value = "/showCampaigns", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView showClusters(@RequestParam(name = "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response)
								throws Exception {
		ModelAndView mav = new ModelAndView("campaigns");
		User user = (User) request.getSession().getAttribute("user");
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Page<Campaign> campaigns = campaignSevice.getCampaignsByCompany(user.getCompanyId(), PageRequest.of(pageNumber, 15));

		List<Campaign> campaigns2 = campaigns.getContent();
		for (Campaign c : campaigns2) {
			List<CampaignTest> cts = c.getRounds();
			System.out.println(cts.size());
		}

		mav.addObject("campaigns", campaigns2);

		CommonUtil.setCommonAttributesOfPagination(campaigns, mav.getModelMap(), pageNumber, "showCampaigns", null);
		return mav;
	}

	@RequestMapping(value = "/goToStep2", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String goToStep2(@RequestParam("campaignLanguage") String campaignLanguage, @RequestParam("campaignName") String campaignName,
								@RequestParam("campaignDesc") String campaignDesc, HttpServletRequest request,
								HttpServletResponse response) {
		Campaign campaign = (Campaign) request.getSession().getAttribute("campaign");
//		campaignName = URLDecoder.decode(campaignName);
		campaign.setLanguage(campaignLanguage);
		campaign.setCampaignName(campaignName);
		campaign.setCampaignDesc(campaignDesc);
		String html = getAllCampaignTests(campaign);
		return html;
	}

	@RequestMapping(value = "/addCampaignStep", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String addCampaignStep(@RequestParam("stepName") String stepName, @RequestParam("testid") Long testid, HttpServletRequest request,
								HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		Campaign campaign = (Campaign) request.getSession().getAttribute("campaign");

		com.assessment.data.Test test = testService.findTestById(testid);
		Set<Qualifiers> qualifiers = questionMapperRep.getAllUniqueQualifiersForTest(user.getCompanyId(), test.getTestName());
		String skills = "";
		for (Qualifiers q : qualifiers) {
			skills += q.toString() + ",";
		}
		if (skills.contains(",")) {
			skills = skills.substring(0, skills.lastIndexOf(","));
		}
		CampaignTest campaignTest = new CampaignTest();
		campaignTest.setCompanyId(user.getCompanyId());
		campaignTest.setCompanyName(user.getCompanyName());
		campaignTest.setTestName(test.getTestName());
		campaignTest.setTestSkills(skills);
		campaignTest.setTestId(test.getId());
		campaignTest.setCampaignTestName(stepName);
		campaign.getRounds().add(campaignTest);
		String html = getAllCampaignTests(campaign);

		return html;
	}

	@RequestMapping(value = "/addMeetingToCampaign", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String addMeetingToCampaign(@RequestParam("stepName") String stepName, @RequestParam("meetId") Long meetId, HttpServletRequest request,
								HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		Campaign campaign = (Campaign) request.getSession().getAttribute("campaign");

		CampaignTest campaignTest = new CampaignTest();
		campaignTest.setCompanyId(user.getCompanyId());
		campaignTest.setCompanyName(user.getCompanyName());
		campaignTest.setTestName("Meeting Round");
		campaignTest.setMeeting(true);
		campaignTest.setTestSkills("NA");
		campaignTest.setTestId(meetId);
		campaignTest.setCampaignTestName(stepName);
		campaign.getRounds().add(campaignTest);
		String html = getAllCampaignTests(campaign);

		return html;
	}

	@RequestMapping(value = "/removeCampaignStep", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String removeCampaignStep(@RequestParam("testid") Long testid, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		Campaign campaign = (Campaign) request.getSession().getAttribute("campaign");

		for (CampaignTest round : campaign.getRounds()) {
			if (round.getTestId().equals(testid)) {
				campaign.getRounds().remove(round);
				break;
			}
		}
		String html = getAllCampaignTests(campaign);

		return html;
	}

	private String getAllCampaignTests(Campaign campaign) {
		List<CampaignTest> rounds = campaign.getRounds();
		String tot = "";
		for (CampaignTest round : rounds) {
			String html = "";
			if (round.getMeeting() != null && round.getMeeting() == true) {
				html = "<div class=\"steps-block d-flex flex-column p-3 mx-2\" style=\"background-color: lightgreen;\" >\n\t<button class=\"quick-btn\" type=\"button\" title=\"Delete\" onClick=\"deleteCampaignTest('${TEST_ID}')\" >\n\t\t<i class=\"fa fa-trash-o\"></i>\n\t</button>\n\t<h5>${STEP_NAME}</h5>\n\t<p class=\"mt-2\">${TEST_NAME}</p>\n\t<p class=\"mt-2\">${SKILLS}</p>\n\t<p><a href=\"#\">View Details</a></p>\n</div>";
			} else {
				html = "<div class=\"steps-block d-flex flex-column p-3 mx-2\">\n\t<button class=\"quick-btn\" type=\"button\" title=\"Delete\" onClick=\"deleteCampaignTest('${TEST_ID}')\" >\n\t\t<i class=\"fa fa-trash-o\"></i>\n\t</button>\n\t<h5>${STEP_NAME}</h5>\n\t<p class=\"mt-2\">${TEST_NAME}</p>\n\t<p class=\"mt-2\">${SKILLS}</p>\n\t<p><a href=\"#\">View Details</a></p>\n</div>";
			}
			html = html.replace("${STEP_NAME}", round.getCampaignTestName() == null ? "NA" : round.getCampaignTestName());
			html = html.replace("${TEST_NAME}", round.getTestName());
			html = html.replace("${SKILLS}", round.getTestSkills() == null ? "NA" : round.getTestSkills());
			html = html.replace("${TEST_ID}", "" + (round.getTestId() == null ? 936 : round.getTestId()));
			tot += html + "\n";
		}
		return tot;
	}

	//

	@RequestMapping(value = "/showCampaignStepDialog", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String showCampaignStepDialog(@RequestParam(required = false, name = "searchTest") String searchTest, HttpServletRequest request,
								HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		List<com.assessment.data.Test> tests = testService.searchTests(user.getCompanyId(), searchTest == null ? "java" : searchTest, 0).getContent();
		String tot = "";
		for (com.assessment.data.Test t : tests) {
			String html = "<div class=\"card\" id=\"${TEST_ID}\">	<div class=\"card-body\">		<h5>${TEST_NAME}</h5>		<p class=\"mb-0\">			${SKILLS}		</p>	</div>	<div class=\"card-footer d-flex justify-content-between\">		<div class=\"my-auto\">			<i class=\"fa fa-clock-o mr-1\"></i> ${TEST_DURATION} Min <i class=\"fa fa-list ml-3 mr-1\"></i> ${TOTAL_MARKS} 		</div>		<button class=\"btn btn-primary\" type=\"button\" onClick=\"addTestToCampaign('${TEST_ID}');\">			<i class=\"fa fa-plus mr-1\"></i> Add		</button>	</div></div>";
			html = html.replace("${TEST_NAME}", t.getTestName());
			html = html.replace("${SKILLS}", getSkillsForTestInHtmlFormat(t.getTestName(), t.getCompanyId()));
			html = html.replace("${TEST_DURATION}", (t.getTestTimeInMinutes() == null ? 60 : t.getTestTimeInMinutes()) + "");
			html = html.replace("${TOTAL_MARKS}", (t.getTotalMarks() == null ? 0 : t.getTotalMarks()) + "");
			html = html.replace("${TEST_ID}", "" + t.getId());
			tot += html;
		}
		String meetingHtml = "<div class=\"card\" style=\"background-color: lightgreen;\" id=\"${TEST_ID}\">                                        <div class=\"card-body\" >                                            <h5>Meeting Round between Candidate and a Set of Reviewers (Select Reviewers in Next Step)</h5>                                                                            </div>                                        <div class=\"card-footer d-flex justify-content-between\">                                             <button class=\"btn btn-primary\" type=\"button\" style=\"background-color: #f2c01d !important;\" onClick=\"addMeetingToCampaign('${MEETING_ID}');\">                                                <i class=\"fa fa-plus mr-1\"></i> Add Meeting Round                                            </button>                                        </div> </div>";
		Long time = System.currentTimeMillis();
		meetingHtml = meetingHtml.replace("${MEETING_ID}", "" + time);
		meetingHtml = meetingHtml.replace("${TEST_ID}", "" + time);
		return tot + meetingHtml;
	}

	private String getSkillsForTestInHtmlFormat(String testName, String companyId) {
		Set<Qualifiers> qualifiers = questionMapperRep.getAllUniqueQualifiersForTest(companyId, testName);
		String htmlSkills = "";
		for (Qualifiers q : qualifiers) {
			String htmlSkill = "<span class=\"keyword mr-1\">${SKILL}</span>";
			htmlSkill = htmlSkill.replace("${SKILL}", q.toString());
			htmlSkills += htmlSkill;
		}

		return htmlSkills;
	}

	@RequestMapping(value = "/campaign", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView addOrUpdatecampaign(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("test-campaign");
		Campaign campaign = new Campaign();
		User user = (User) request.getSession().getAttribute("user");
		campaign.setCompanyId(user.getCompanyId());
		if (id != null) {
			campaign = campaignRep.findById(id).get();
			mav.addObject("disabled", true);
			request.getSession().setAttribute("newCampaign", false);
		} else {
			mav.addObject("disabled", false);
			request.getSession().setAttribute("newCampaign", true);
		}
		request.getSession().setAttribute("campaign", campaign);
		LinkedHashMap<String, String> langs = new LinkedHashMap<>();
		langs.put("eng", "English");
		langs.put("arabic", "Arabic");
		mav.addObject("langs", langs);
		mav.addObject("campaign", campaign);
		return mav;
	}

	@RequestMapping(value = "/checkForCampaignName", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String checkForCampaignName(@RequestParam("campaignName") String campaignName, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
//		campaignName = URLDecoder.decode(campaignName);

		Boolean newCam = (Boolean) request.getSession().getAttribute("newCampaign");
		if (newCam) {
			Campaign campaign = campaignSevice.findUniqueCampaign(user.getCompanyId(), campaignName);
			if (campaign != null) {
				return "nok";
			} else {
				return "ok";
			}
		}

		return "ok";
	}

	@RequestMapping(value = "/getSelectedCandidatesForCampaign", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getSelectedCandidatesForCampaign(@RequestParam("campaignName") String campaignName, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
//		 campaignName = URLDecoder.decode(campaignName);
		List<CampaignCandidate> candidates = campaignCandidateService.getCampaignCandidatesForCampaignMeetingByCompany(user.getCompanyId(), campaignName);
		String parent = "<ul class=\"mt-3\" style=\"width: 250%;\">	${CANDIDATES}</ul>";

		String childs = "";
		for (CampaignCandidate can : candidates) {
			String child = "<li style=\"border-bottom:none\">	<b>${FIRST} ${LAST}	</b>&nbsp;&nbsp;	(<small>  ${EMAIL}</small>)</li>\n";
			child = child.replace("${FIRST}", can.getFirstName() == null ? "NA" : can.getFirstName());
			child = child.replace("${LAST}", can.getLastName() == null ? "NA" : can.getLastName());
			child = child.replace("${EMAIL}", can.getEmail());
			childs += child;
		}
		parent = parent.replace("${CANDIDATES}", childs);
		return parent;
	}

	// reviewerMappingService
	@RequestMapping(value = "/getSelectedReviewersForCampaign", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getSelectedReviewersForCampaign(@RequestParam("campaignName") String campaignName, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
//		 campaignName = URLDecoder.decode(campaignName);
		List<ReviewerCampaignMapping> reviewers = reviewerMappingService.getReviewerCampaignMapping(user.getCompanyId(), campaignName);
		String parent = "<ul class=\"mt-3\" style=\"width: 250%;\">	${REVIEWERS}</ul>";

		String childs = "";
		for (ReviewerCampaignMapping rev : reviewers) {
			String child = "<li style=\"border-bottom:none\">	<b> ${FIRST} ${LAST} </b>	&nbsp;&nbsp;	(<small>  ${EMAIL}</small>)</li>\n";
			child = child.replace("${FIRST}", rev.getFirstName() == null ? "NA" : rev.getFirstName());
			child = child.replace("${LAST}", rev.getLastName() == null ? "NA" : rev.getLastName());
			child = child.replace("${EMAIL}", rev.getEmail());
			childs += child;
		}
		parent = parent.replace("${REVIEWERS}", childs);
		return parent;
	}

	// reviewerMappingService
	@RequestMapping(value = "/searchAndPopulateCandidatesTable", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String searchAndPopulateCandidatesTable(@RequestParam(required = false, name = "search") String search,
								@RequestParam(required = true, name = "campaignName") String campaignName, HttpServletRequest request,
								HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		List<User> users = userService.searchUsers(user.getCompanyId(), search == null ? "test" : search);
//			 campaignName = URLDecoder.decode(campaignName);
		String parent = "<table class=\"table\">	<thead>		<tr>			<th>				<input type=\"checkbox\" name=\"\" >			</th>			<th>				Name			</th>			<th>				Email			</th>		</tr>	</thead>	<tbody>		${ROWS}			</tbody></table>";

		String childs = "";
		for (User usr : users) {
			String child = "<tr id=\"T${ID}\">	<td>		<input type=\"checkbox\" name=\"${ID}\" id=\"${ID}\" onchange=\"changeCandidateCheckbox(this, '${CAMPAIGN_NAME}')\">	</td>	<td>		${FIRSTNAME} ${LASTNAME}	</td>	<td>		${EMAIL}	</td></tr>";
			child = child.replace("${FIRSTNAME}", usr.getFirstName() == null ? "NA" : usr.getFirstName());
			child = child.replace("${LASTNAME}", usr.getLastName() == null ? "NA" : usr.getLastName());
			child = child.replace("${EMAIL}", usr.getEmail());
			child = child.replace("${ID}", usr.getId() + "");
			child = child.replace("${CAMPAIGN_NAME}", campaignName);
			childs += child;
		}
		parent = parent.replace("${ROWS}", childs);
		return parent;
	}

	@RequestMapping(value = "/searchAndPopulateRviewersTable", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String searchAndPopulateRviewersTable(@RequestParam(required = false, name = "search") String search,
								@RequestParam(required = true, name = "campaignName") String campaignName, HttpServletRequest request,
								HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
//			 campaignName = URLDecoder.decode(campaignName);
		List<CampaignReviewer> users = campaignReviewerService.searchCampaignReviewersWithoutPagination(user.getCompanyId(), search == null ? "a" : search);
		String parent = "<table class=\"table\">	<thead>		<tr>			<th>				<input type=\"checkbox\" name=\"\" >			</th>			<th>				Name			</th>			<th>				Email			</th>		</tr>	</thead>	<tbody>		${ROWS}			</tbody></table>";

		String childs = "";
		for (CampaignReviewer usr : users) {
			String child = "<tr id=\"T${ID}\">	<td>		<input type=\"checkbox\" name=\"${ID}\" id=\"${ID}\"  onchange=\"changeReviewerCheckbox(this, '${CAMPAIGN_NAME}')\">	</td>	<td>		${FIRSTNAME} ${LASTNAME}	</td>	<td>		${EMAIL}	</td></tr>";
			child = child.replace("${FIRSTNAME}", usr.getFirstName() == null ? "NA" : usr.getFirstName());
			child = child.replace("${LASTNAME}", usr.getLastName() == null ? "NA" : usr.getLastName());
			child = child.replace("${EMAIL}", usr.getEmail());
			child = child.replace("${ID}", usr.getId() + "");
			child = child.replace("${CAMPAIGN_NAME}", campaignName);
			childs += child;
		}
		parent = parent.replace("${ROWS}", childs);
		return parent;
	}

	@RequestMapping(value = "/selectCandidateForCampaign", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String selectCandidateForCampaign(@RequestParam(required = true, name = "cid") Long cid,
								@RequestParam(required = true, name = "campaignName") String campaignName, HttpServletRequest request,
								HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		User can = userService.findById(cid);
//			campaignName = URLDecoder.decode(campaignName);
		// campaignCandidateService
		CampaignCandidate campaignCandidate = new CampaignCandidate();
		campaignCandidate.setCompanyId(user.getCompanyId());
		campaignCandidate.setCompanyName(user.getCompanyName());
		campaignCandidate.setCampaignName(campaignName);
		campaignCandidate.setEmail(can.getEmail());
		campaignCandidate.setFirstName(can.getFirstName());
		campaignCandidate.setLastName(can.getLastName());
		Campaign campaign = campaignSevice.findUniqueCampaign(user.getCompanyId(), campaignName);
		if (campaign != null) {
			campaignCandidate.setCampaignId(campaign.getId());
		}
		campaignCandidateService.saveOrUpdate(campaignCandidate);
		return "ok";
	}

	@RequestMapping(value = "/selectReviewerForCampaign", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String selectReviewerForCampaign(@RequestParam(required = true, name = "rid") Long rid,
								@RequestParam(required = true, name = "campaignName") String campaignName, HttpServletRequest request,
								HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		CampaignReviewer rev = campaignReviewerService.getById(rid);
		// campaignCandidateService
//			campaignName = URLDecoder.decode(campaignName);
		ReviewerCampaignMapping reviewerCampaignMapping = new ReviewerCampaignMapping();
		reviewerCampaignMapping.setCompanyId(user.getCompanyId());
		reviewerCampaignMapping.setCompanyName(user.getCompanyName());
		reviewerCampaignMapping.setCampaignName(campaignName);
		reviewerCampaignMapping.setEmail(rev.getEmail());
		reviewerCampaignMapping.setFirstName(rev.getFirstName());
		reviewerCampaignMapping.setLastName(rev.getLastName());
		Campaign campaign = campaignSevice.findUniqueCampaign(user.getCompanyId(), campaignName);
		if (campaign != null) {
			reviewerCampaignMapping.setCampaignId(campaign.getId());
		}
		reviewerMappingService.saveOrUpdate(reviewerCampaignMapping);
		return "ok";
	}

	@RequestMapping(value = "/unselectCandidateForCampaign", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String unselectCandidateForCampaign(@RequestParam(required = true, name = "cid") Long cid,
								@RequestParam(required = true, name = "campaignName") String campaignName, HttpServletRequest request,
								HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		User can = userService.findById(cid);
//			campaignName = URLDecoder.decode(campaignName);
		CampaignCandidate campaignCandidate = campaignCandidateService.findUniqueCampaignCandidate(user.getCompanyId(), campaignName, can.getEmail());

		campaignCandidateService.deleteCampaignCandidate(campaignCandidate.getId());
		return "ok";
	}

	@RequestMapping(value = "/unselectReviewerForCampaign", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String unselectReviewerForCampaign(@RequestParam(required = true, name = "rid") Long rid,
								@RequestParam(required = true, name = "campaignName") String campaignName, HttpServletRequest request,
								HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
//			campaignName = URLDecoder.decode(campaignName);
		CampaignReviewer rev = campaignReviewerService.getById(rid);
		ReviewerCampaignMapping reviewerCampaignMapping = reviewerMappingService.findUniqueReviewerCampaignMapping(rev.getCompanyId(), rev.getEmail(), campaignName);
		reviewerMappingService.deleteReviewerCampaignMapping(reviewerCampaignMapping.getId());
		return "ok";
	}

	@RequestMapping(value = "/getCampaigStepsSummary", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getCampaigStepsSummary(@RequestParam("campaignName") String campaignName, HttpServletRequest request, HttpServletResponse response) {
		Campaign campaign = (Campaign) request.getSession().getAttribute("campaign");
		List<CampaignTest> items = campaign.getRounds();
//			 campaignName = URLDecoder.decode(campaignName);

		String allcand = "";
		for (CampaignTest item : items) {
			String candidatehtml = "<div class=\"steps-block d-flex flex-column p-3 mx-3\" style=\"background-color:${COLOR};\"><h5>${CAMPAIGN_STEP_NAME}</h5>	<p class=\"mt-2\">${SKILLS}</p>	<p>Is this step a Meeting Round - ${MEETING}</p></div>";
			if (item.getMeeting() != null && item.getMeeting() == true) {
				candidatehtml = candidatehtml.replace("${COLOR}", "lightblue");
			}
			candidatehtml = candidatehtml.replace("${CAMPAIGN_STEP_NAME}", item.getCampaignTestName() == null ? "NA" : item.getCampaignTestName());
			candidatehtml = candidatehtml.replace("${SKILLS}", item.getTestSkills());
			candidatehtml = candidatehtml.replace("${MEETING}", (item.getMeeting() != null && item.getMeeting() == true) ? "Yes" : "No");
			allcand += candidatehtml + "\n";
		}
		return allcand;
	}

	@RequestMapping(value = "/getSelectedCandidatesSummary", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getSelectedCandidatesSummary(@RequestParam("campaignName") String campaignName, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		Campaign campaign = (Campaign) request.getSession().getAttribute("campaign");
//			campaignName = URLDecoder.decode(campaignName);
		List<CampaignTest> items = campaign.getRounds();
		List<CampaignCandidate> candidates = campaignCandidateService.getCampaignCandidatesForCampaignMeetingByCompany(user.getCompanyId(), campaignName);
		String tot = "<h1>Candidates</h1><ul class=\"mt-3\">	 ${ROWS}  </ul>";
		String rows = "";
		for (CampaignCandidate candidate : candidates) {
			String row = "<li>	<b${FIRSTNAME},&nbsp;&nbsp; ${LASTNAME}</b>&nbsp;&nbsp;(<small>${EMAIL}</small>)</li>";
			row = row.replace("${FIRSTNAME}", candidate.getFirstName() == null ? "NA" : candidate.getFirstName());
			row = row.replace("${LASTNAME}", candidate.getLastName() == null ? "NA" : candidate.getLastName());
			row = row.replace("${EMAIL}", candidate.getEmail());
			rows += row + "\n";
		}
		tot = tot.replace("${ROWS}", rows);
		return tot;
	}

	@RequestMapping(value = "/getSelectedReviewersSummary", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getSelectedReviewersSummary(@RequestParam("campaignName") String campaignName, HttpServletRequest request, HttpServletResponse response) {
//			campaignName = URLDecoder.decode(campaignName);
		User user = (User) request.getSession().getAttribute("user");
		Campaign campaign = (Campaign) request.getSession().getAttribute("campaign");
		List<ReviewerCampaignMapping> reviewers = reviewerMappingService.getReviewerCampaignMapping(user.getCompanyId(), campaignName);
		String tot = "<h1>Reviewers</h1><ul class=\"mt-3\">	 ${ROWS}  </ul>";
		// String tot = "<h1>Candidates</h1><ul class=\"mt-3\"> ${ROWS} </ul>";
		String rows = "";
		for (ReviewerCampaignMapping mapping : reviewers) {
			String row = "<li>	<b${FIRSTNAME}&nbsp;&nbsp; ${LASTNAME}</b>&nbsp;&nbsp;(<small>${EMAIL}</small>)</li>";
			row = row.replace("${FIRSTNAME}", mapping.getFirstName() == null ? "NA" : mapping.getFirstName());
			row = row.replace("${LASTNAME}", mapping.getLastName() == null ? "NA" : mapping.getLastName());
			row = row.replace("${EMAIL}", mapping.getEmail());
			rows += row + "\n";
		}
		tot = tot.replace("${ROWS}", rows);
		return tot;
	}

	@RequestMapping(value = "/saveCampaign", method = RequestMethod.GET)
	public ModelAndView saveCampaign(@RequestParam String startDate, @RequestParam String endDate, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("campaigns");
		User user = (User) request.getSession().getAttribute("user");
		Campaign campaign = (Campaign) request.getSession().getAttribute("campaign");
		List<ReviewerCampaignMapping> reviewers = reviewerMappingService.getReviewerCampaignMapping(user.getCompanyId(), campaign.getCampaignName());
		campaign.getReviewers().clear();

		Boolean dateValidate = validateDates(startDate, endDate);
		if (!dateValidate) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			cal.add(Calendar.YEAR, 1); // to get previous year add -1
			Date nextYear = cal.getTime();
			startDate = dateFormat.format(today);
			endDate = dateFormat.format(nextYear);
		}

		for (ReviewerCampaignMapping mapping : reviewers) {
			CampaignReviewer reviewer = new CampaignReviewer();
			reviewer.setCompanyId(user.getCompanyId());
			reviewer.setCompanyName(mapping.getCompanyName());
			reviewer.setEmail(mapping.getEmail());
			reviewer.setFirstName(mapping.getFirstName());
			reviewer.setLastName(mapping.getLastName());
			reviewer.setMobile(mapping.getMobile());
			reviewer.setPassword(mapping.getEmail().hashCode() + "");
			campaign.getReviewers().add(reviewer);
		}

		for (CampaignTest campaignTest : campaign.getRounds()) {
			if (campaignTest.getMeeting() != null && campaignTest.getMeeting()) {
				campaign.setMeetingRound(true);
			}
			campaignTest.setCompanyId(user.getCompanyId());
			campaignTest.setCompanyName(user.getCompanyName());
			campaignTest.setId(null);// need it for hibernte purposse
			campaignTest.setCampaignName(campaign.getCampaignName());
		}
		campaign.setCompanyId(user.getCompanyId());
		campaign.setCompanyName(user.getCompanyName());
		checkDuplicateRoundNames(campaign.getRounds());
		campaignSevice.saveOrUpdate(campaign);

		List<CampaignCandidate> candidates = campaignCandidateService.getCampaignCandidatesForCampaignMeetingByCompany(user.getCompanyId(), campaign.getCampaignName());

		for (CampaignCandidate candidate : candidates) {
			String file = propertyConfig.getCampaignInviteHtmlLocation();
			String html = FileUtils.readFileToString(new File(file));
			html = html.replace("{FULL_NAME}", candidate.getFirstName() == null ? "NA"
										: candidate.getFirstName() + " " + candidate.getLastName() == null ? "NA"
																	: candidate.getLastName());
			html = html.replace("${CAMPAIGN_NAME}", campaign.getCampaignName());
			html = html.replace("${MEETING_ROUND}", (campaign.getMeetingRound() != null && campaign.getMeetingRound() == true) ? "Yes" : "No");
//						String candidateurl = getCampaignLinkForTestTaker(campaign.getCampaignName(), user.getCompanyId(), candidate.getEmail(), candidate.getFirstName()==null?"NA":candidate.getFirstName(), candidate.getLastName()==null?"NA":candidate.getLastName(), startDate, endDate) ;
			String candidateurl = getCampaignLinkForTestTaker(campaign.getCampaignName(), user.getCompanyId(), candidate.getEmail(),
										candidate.getFirstName() == null ? "NA" : candidate.getFirstName(),
										candidate.getLastName() == null ? "NA" : candidate.getLastName(), startDate, endDate,
										campaign.getLanguage());

			html = html.replace("{URL}", candidateurl);
			html = html.replace("${CAMPAIGN_DESC}", campaign.getCampaignDesc());
			EmailGenericMessageThread client = new EmailGenericMessageThread(candidate.getEmail(),
										"Campaign Link - " + campaign.getCampaignName() + " Sent by E-ASSESS", html,
										propertyConfig);
			// client.setEmailSentCC(emailSentCC);
			// client.setSetStatus(true);
			Thread th = new Thread(client);
			th.start();
		}
		Page<Campaign> campaigns = campaignSevice.getCampaignsByCompany(user.getCompanyId(), PageRequest.of(0, 15));

		List<Campaign> campaigns2 = campaigns.getContent();
		for (Campaign c : campaigns2) {
			List<CampaignTest> cts = c.getRounds();
			System.out.println(cts.size());
		}

		mav.addObject("campaigns", campaigns2);
		if (dateValidate) {
			mav.addObject("message", "Campaign - " + campaign.getCampaignName() + " has been saved");// later put it as label
		} else {
			mav.addObject("message", "Campaign - " + campaign.getCampaignName()
										+ " has been saved. However Dates configured seem to be invalid. So we will share the link with 1 year validity from today with Candidates.");// later
																										// put
																										// it
																										// as
																										// label
		}
		mav.addObject("icon", "success");
		mav.addObject("msgtype", "Information");
		CommonUtil.setCommonAttributesOfPagination(campaigns, mav.getModelMap(), 0, "showCampaigns", null);
		return mav;

	}

	private String getCampaignLinkForTestTaker(String campaignName, String companyId, String email, String firstName, String lastName, String sdate, String edate,
								String language) {
		String url = propertyConfig.getBaseUrl();
		String controllerMethod = "testtaker-campaign";
		campaignName = URLEncoder.encode(Base64.getEncoder().encodeToString(campaignName.getBytes()));
		companyId = URLEncoder.encode(Base64.getEncoder().encodeToString(companyId.getBytes()));
		email = URLEncoder.encode(Base64.getEncoder().encodeToString(email.getBytes()));
		firstName = URLEncoder.encode(Base64.getEncoder().encodeToString(firstName.getBytes()));
		lastName = URLEncoder.encode(Base64.getEncoder().encodeToString(lastName.getBytes()));
		sdate = URLEncoder.encode(Base64.getEncoder().encodeToString(sdate.getBytes()));
		edate = URLEncoder.encode(Base64.getEncoder().encodeToString(edate.getBytes()));
		url += controllerMethod + "?campaignName=" + campaignName + "&companyId=" + companyId + "&email=" + email + "&firstName=" + firstName + "&lastName=" + lastName
									+ "&startdate=" + sdate + "&enddate=" + edate + "&lang=" + language;
		return url;
	}

	private List<CampaignTest> checkDuplicateRoundNames(List<CampaignTest> list) {
		for (CampaignTest test : list) {
			List<CampaignTest> listtemp = new ArrayList<CampaignTest>();
			listtemp.addAll(list);
			boolean b = listtemp.remove(test);
			if (listtemp.contains(test)) {
				test.setCampaignTestName(test.getCampaignTestName() + "-" + System.currentTimeMillis());
			}
		}
		return list;
	}

	Logger logger = LoggerFactory.getLogger(CampaignController.class);

	private boolean validateDates(String sdate, String edate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = dateFormat.parse(sdate);
			Date d2 = dateFormat.parse(edate);
			if (d2.after(d1)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("invalid dates", e);
			return false;
		}
	}

}
