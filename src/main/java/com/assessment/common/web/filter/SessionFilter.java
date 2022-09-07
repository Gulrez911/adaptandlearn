package com.assessment.common.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assessment.common.newlookandfeel.CommonUtil;
import com.assessment.common.util.RSAEncrypterDecrypter;
import com.assessment.data.User;

//@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {

	@Override
	public void destroy() {
		// ...
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	private void setNoSiteCookie(ServletResponse response) {
		HttpServletResponse res = (HttpServletResponse) response;
		String hed = res.getHeader("Set-Cookie");
		if (hed != null && hed.trim().length() > 0) {
			if (!hed.toLowerCase().contains("samesite")) {
				hed += ";SameSite=None";
			}
			if (!hed.toLowerCase().contains("secure")) {
				hed += ";Secure";
			}
			res.setHeader("Set-Cookie", hed);
		} else if (hed == null || hed.trim().length() == 0) {
			res.setHeader("Set-Cookie", "SameSite=None;Secure");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

//		HttpSession session = ((HttpServletRequest) request).getSession();
//		if (request.getParameter("JSESSIONID") != null) {
//		    Cookie userCookie = new Cookie("JSESSIONID", request.getParameter("JSESSIONID"));
//		    ((HttpServletResponse) response).addCookie(userCookie);
//		} else {
//		    String sessionId = session.getId();
//		    Cookie userCookie = new Cookie("JSESSIONID", sessionId);
//		    ((HttpServletResponse) response).addCookie(userCookie);
//		}
		// System.out.println("123 ");

		// setNoSiteCookie(response);

		try {
			String page = ((HttpServletRequest) request).getRequestURI();
			System.out.println("page is " + page);

			if (page.endsWith("healthcheck") || page.equalsIgnoreCase("/")) {
				chain.doFilter(request, response);
			} else if (page.endsWith("proquestionbank") || page.endsWith("proquestionbank")
					|| page.endsWith("proquestionbank")) {
				chain.doFilter(request, response);
			} else if (page.endsWith("publicTest2") || page.endsWith("showFailures") || page.endsWith("mcqPublic")) {
				chain.doFilter(request, response);
			} else if (page.endsWith("verify") || page.endsWith("/hackathon") || page.endsWith("multifileresults")
					|| page.endsWith("showComprehensiveReportForCourse")) {
				chain.doFilter(request, response);
			} else if (page.endsWith("/findLevel1Qs") || page.endsWith("/findLevel2Qs")) {
				chain.doFilter(request, response);
			} else if (page.endsWith("/searchQsWs") || page.endsWith("/init") || page.endsWith("/validateotp")
					|| page.endsWith("/savenewpassword") || page.endsWith("/getotp") || page.endsWith("/login")
					|| page.endsWith("/authenticate") || page.endsWith("publicTest") || page.contains("setUpTenant")
					|| page.contains("downloadUserSessionReportsForTest")) {
				chain.doFilter(request, response);
			} else if (page.endsWith("/testsByTag") || page.endsWith("/recommendedSkillsByTest")
					|| page.endsWith("lmsadmin") || page.endsWith("getAssessmentURLForLMSLearner")
					|| page.endsWith("getRecommendationsForTestForLmS")) {
				chain.doFilter(request, response);
			} else if (page.contains("scripts_login") || page.contains("images") || page.contains("css")
					|| page.contains("scripts") || page.contains("fonts") || page.contains("html")
					|| page.contains("startTestSession") || page.contains("mcq2") || page.contains("yakshacode")
					|| page.contains("compile") || page.contains("savePracticeCode")) {
				chain.doFilter(request, response);
			} else if (page.contains("multiFileReports") || page.contains("simulatePoststartTest")) {
				chain.doFilter(request, response);
			} else if (page.contains("startWorkspace") || page.contains("che123") || page.contains("samllogin")
					|| page.contains("samltest") || page.contains("metadata")
					|| page.contains("yakshaspconsumerendpoint") || page.contains("yakshasplogoff")
					|| page.contains("attrs") || page.contains("doLogin") || page.contains("bulkResults")) {
				chain.doFilter(request, response);
			} else if (page.contains("learner") || page.contains("authenticateLearner")
					|| page.contains("publicTestAuthenticate") || page.contains("mcqAuth")) {
				chain.doFilter(request, response);
			} else if (page.contains("dashboardnew") || page.contains("testsnew") || page.contains("questionssnew")) {
				chain.doFilter(request, response);
			} else if (page.contains("dashboardnew") || page.contains("testsnew") || page.contains("questionssnew")
					|| page.contains("createtestnew") || page.contains("createquestionnew")) {
				chain.doFilter(request, response);
			} else if (page.contains("testsByCompanyId") || page.contains("assessmentURLForLMSLearnerByTestId")
					|| page.contains("testtaker-campaign") || page.contains("campaignreviewerlogin")
					|| page.contains("campaignreviewerauthenticate")) {
				chain.doFilter(request, response);
			} else if (page.contains("saveSlot") || page.contains("errorPaymentGateway")
					|| page.contains("redirectBooking") || page.contains("webhookBooking")) {
				chain.doFilter(request, response);
			} else if (page.contains("webhookBooking2") || page.contains("adaptive")
					|| page.contains("adaptiveAssessment1") || page.contains("adaptiveAssessment2")) {
				System.out.println("page " + page);
				chain.doFilter(request, response);
			} else if (page.contains("loginDemoTest") || page.contains("demoIT")
					|| page.contains("bulkProcessCrawlerData1") || page.contains("initiateConversion")
					|| page.contains("getAssUploadDataRecordsNotInCategory")) {
				chain.doFilter(request, response);
			} else if (page.contains("landing") || page.contains("generatedrishContent")
					|| page.contains("generateDrishAssRec") || page.contains("contactOrFeedback")) {
				chain.doFilter(request, response);
			} else if (page.contains("/api/v1/")) {
				HttpServletRequest req = (HttpServletRequest) request;
				HttpSession session = req.getSession();
				HttpServletResponse res = (HttpServletResponse) response;
				if (request.getParameter("JSESSIONID") != null) {
					Cookie userCookie = new Cookie("JSESSIONID", request.getParameter("JSESSIONID"));
					res.addCookie(userCookie);
				} else {
					String sessionId = session.getId();
					Cookie userCookie = new Cookie("JSESSIONID", sessionId);
					res.addCookie(userCookie);
				}

				if (page.contains("authenticateAdmin")) {

					chain.doFilter(request, response);
				} else if (page.contains("startWithoutToken") || page.contains("generateContentForGroupOf5TillQual5")) {
					chain.doFilter(request, response);
				} else {
					// check for token and validate
					// HttpServletRequest req = (HttpServletRequest) request;
					String token = req.getHeader("token");
					if (token == null) {
						HttpServletResponse response2 = (HttpServletResponse) response;
						response2.setStatus(HttpServletResponse.SC_OK);
						response2.getWriter().write("Token Not Available");
						response2.getWriter().flush();
					}
					System.out.println("page called " + page);
					if (!validateToken(token)) {
						HttpServletResponse response2 = (HttpServletResponse) response;
						response2.setStatus(HttpServletResponse.SC_OK);
						response2.getWriter().write("Token Invalid");
						response2.getWriter().flush();
					} else {
						System.out.println("Token validated");

						chain.doFilter(request, response);
					}
				}
			} else {
				User user = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
				if (user == null) {
					System.out.println("no user info and hence logging out page is " + page);
//				 		((HttpServletResponse)response).sendRedirect("login");
					((HttpServletResponse) response).sendRedirect("prologin");
				} else {

					chain.doFilter(request, response);
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			// ((HttpServletResponse)response).sendRedirect("login");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string
			((HttpServletRequest) request).getSession().setAttribute("errorStack", sStackTrace);
			((HttpServletResponse) response).sendRedirect("problem");
		}

	}

	private boolean validateToken(String token) {
		System.out.println("token passed " + token);
		// return true;
		try {
			RSAEncrypterDecrypter encrypterDecrypter = RSAEncrypterDecrypter.getRSAEncrypterDecrypter();
			PrivateKey privateKey = encrypterDecrypter.getPrivate("KeyPair/privateKey");
			PublicKey publicKey = encrypterDecrypter.getPublic("KeyPair/publicKey");
			System.out.println("token passed " + token);
			token = encrypterDecrypter.decryptText(token, publicKey);
			Long time = Long.parseLong(token);
			Long timeNow = System.currentTimeMillis();
			Long timeDifference = TimeUnit.MILLISECONDS.toMinutes(timeNow - time);
			if (timeDifference > CommonUtil.getTokenAliveTime()) {
				return false;
			}

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}

}
