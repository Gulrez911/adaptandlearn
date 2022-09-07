package com.assessment.web.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CommonUtil;
import com.assessment.common.ExcelReader;
import com.assessment.common.PropertyConfig;
import com.assessment.data.CandidateProfileParams;
import com.assessment.data.Company;
import com.assessment.data.CrawlerTopic;
import com.assessment.data.Question;
import com.assessment.data.User;
import com.assessment.repositories.CandidateProfileParamsRepository;
import com.assessment.repositories.CrawlerTopicRepository;
import com.assessment.repositories.QuestionRepository;
import com.assessment.services.CandidateProfileParamsService;
import com.assessment.services.CompanyService;
import com.assessment.services.CrawlerTopicService;
import com.assessment.services.QuestionService;

@Controller
public class CrawlerController {
	@Autowired
	CrawlerTopicService crawlerTopicService;
	
	@Autowired
	CrawlerTopicRepository crawlerRep;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	CandidateProfileParamsService profileParamsService;
	
	@Autowired
	CandidateProfileParamsRepository profileParamsRepository;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	CompanyService companyService;
	
	private Element traverseChildElementsThroughClassMatch(Element element, String match, int count){
		if(count == 10){
			return null;
		}
		
		if(element.className().equals(match)){
			return element;
		}
		
		Elements elements = element.getAllElements();
		for(int i=0;i<elements.size();i++){
			Element ele = elements.get(i);
			if(ele.className().equals(match)){
				return ele;
			}
		}
		
		for(int i=0;i<elements.size();i++){
			Element ele = elements.get(i);
			return traverseChildElementsThroughClassMatch(ele, match, ++count);
		}
		
		return null;
	}
	
	/**
	 * Returns a list with all links contained in the input
	 */
	public static List<String> extractUrls(String text)
	{
	    List<String> containedUrls = new ArrayList<String>();
	    String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
	    Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
	    Matcher urlMatcher = pattern.matcher(text);

	    while (urlMatcher.find())
	    {
	        containedUrls.add(text.substring(urlMatcher.start(0),
	                urlMatcher.end(0)));
	    }

	    return containedUrls;
	}
	
	private String convertToChoice(String answer){
		answer= answer.trim().toLowerCase();
		answer = answer.substring("answer".length(), answer.length());
		if(answer.contains("a")){
			return "Choice 1";
		}
		else if(answer.contains("b")){
			return "Choice 2";
		}
		else if(answer.contains("c")){
			return "Choice 3";
		}
		else if(answer.contains("d")){
			return "Choice 4";
		}
		else if(answer.contains("e")){
			return "Choice 5";
		}
		else if(answer.contains("f")){
			return "Choice 6";
		}
		
		return "";
	}
	
	private Question parseQuestion(String text, String qNo){
		Question q = new Question();
		String q1 = text.substring(text.indexOf(qNo), text.indexOf("<br"));
		String question1 = q1.substring(qNo.length(), q1.length()).trim();
		String restQ1 = text.substring((q1+"<br>").length(), text.length());
		String imageUrlQ = "";
		Boolean imageAvailable = false;
			if(restQ1.contains("<a href")){
				imageAvailable = true;
				List<String> extractedUrls = extractUrls(restQ1);
				imageUrlQ = extractedUrls.get(0);
			}
		String a1 = "";
			if(!imageAvailable){
				a1 = restQ1.substring(restQ1.indexOf("a)"), restQ1.indexOf("<br"));
			}
			else{
				restQ1 = restQ1.substring(restQ1.indexOf("a)"), restQ1.length());
				a1 = restQ1.substring(restQ1.indexOf("a)"), restQ1.indexOf("<br"));
			}
		 
		String answera1 = a1.substring("a)".length(), a1.length()).trim();
		restQ1 = restQ1.substring((a1+"<br>").length(), restQ1.length());
		
		String a2 = restQ1.substring(restQ1.indexOf("b)"), restQ1.indexOf("<br"));
		String answera2 = a2.substring("b)".length(), a2.length()).trim();
		
		restQ1 = restQ1.substring((a2+"<br>").length(), restQ1.length());
		
		String a3 = "";
		String a4 = "";
		String a5 = "";
		String a6 = "";
		String answera3 = "";
		String answera4 = "";
		String answera5 = "";
		String answera6 = "";
		
		
		if(restQ1.contains("c)")){
			a3 = restQ1.substring(restQ1.indexOf("c)"), restQ1.indexOf("<br"));
			answera3 = a3.substring("c)".length(), a3.length()).trim();
			restQ1 = restQ1.substring((a3+"<br>").length(), restQ1.length());
			
			if(restQ1.contains("d)")){
				a4 = restQ1.substring(restQ1.indexOf("d)"), restQ1.indexOf("<br"));
				answera4 = a4.substring("d)".length(), a4.length()).trim();
				restQ1 = restQ1.substring((a4+"<br>").length(), restQ1.length());
				
				if(restQ1.contains("e)")){
					a5 = restQ1.substring(restQ1.indexOf("e)"), restQ1.indexOf("<br"));
					answera5 = a5.substring("e)".length(), a5.length()).trim();
					restQ1 = restQ1.substring((a5+"<br>").length(), restQ1.length());
					
					if(restQ1.contains("f")){
						a6 = restQ1.substring(restQ1.indexOf("f)"), restQ1.indexOf("<br"));
						answera6 = a6.substring("f)".length(), a6.length()).trim();
						restQ1 = restQ1.substring((a6+"<br>").length(), restQ1.length());
					}
				}
			}
		}
		
		String answerQ1 = restQ1.substring(restQ1.indexOf("</span>Answer"), restQ1.indexOf("\n<br>"));//</span>Answer d
		answerQ1 = answerQ1.substring("</span>".length(), answerQ1.length());
		//String explanation = restQ1.substring(restQ1.indexOf(answerQ1+"\n<br>"), restQ1.length());
		answerQ1 = convertToChoice(answerQ1);
		String explanation= restQ1.substring(restQ1.indexOf("Explanation:"), restQ1.length());
		
		q.setQuestionText(question1);
		q.setChoice1(answera1);
		q.setChoice2(answera2);
		q.setChoice3(answera3);
		q.setChoice4(answera4);
		q.setChoice5(answera5);
		q.setChoice6(answera6);
		q.setImageUrl(imageUrlQ);
		q.setRightChoices(answerQ1);
		q.setJustification(explanation);
		return q;
	}
	
	private List<Question> goToExamPage(String url, String companyId, String companyName) throws IOException{
		List<Question> list = new ArrayList<>();
		org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
		Elements elements = doc.getAllElements();
		Boolean check2 = false;
		int countCheck2 = 0;
			for(int i=0;i<elements.size();i++){
				if(countCheck2 > 15 || check2 == true){
					break;
				}
				Element element = elements.get(i);
				Element ele = traverseChildElementsThroughClassMatch(element, "entry-content", 0);
				if(ele != null){
					Elements eles = ele.getAllElements();
					if(eles != null && eles.size() > 0){
						ele = eles.get(0);
						Elements eles2 =  ele.getElementsByTag("p");
							for(int j=0; j<eles2.size(); j++){
								countCheck2++;
								if(countCheck2 > 15 || check2 == true){
									break;
								}
								Element e = eles2.get(j);
								//System.out.println(e.outerHtml());
								//System.out.println(e.html());
							//	System.out.println(e.ownText());
								String text = e.html();
								
								if(text.contains("1. ") ){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									if(e2 != null && text != null){
										text = text+ e2.html();
										try {
											Question q1 = parseQuestion(text, "1. ");
											q1.setCompanyId(companyId);
											q1.setCompanyName(companyName);
											System.out.println(q1.getQuestionText());
											list.add(q1);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									
								}
								else if(text.contains("2. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									if(e2 != null && text != null){
										text = text+ e2.html();
										try {
										Question q2= parseQuestion(text, "2. ");
										q2.setCompanyId(companyId);
										q2.setCompanyName(companyName);
										System.out.println(q2.getQuestionText());
										
											list.add(q2);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									
								}
								else if(text.contains("3. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									if(e2 != null && text != null){
										text = text+ e2.html();
										try {
											Question q3= parseQuestion(text, "3. ");
											q3.setCompanyId(companyId);
											q3.setCompanyName(companyName);
											System.out.println(q3.getQuestionText());
											list.add(q3);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									
								}
								else if(text.contains("4. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									if(e2 != null && text != null){
										text = text+ e2.html();
										try {
											Question q= parseQuestion(text, "4. ");
											q.setCompanyId(companyId);
											q.setCompanyName(companyName);
											System.out.println(q.getQuestionText());
											list.add(q);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									
								}
								else if(text.contains("5. ")){
									Element e2 = e.nextElementSibling();
									if(e2 != null && text != null){
										//System.out.println(e2.html());
										text = text+ e2.html();
										try {
											Question q= parseQuestion(text, "5. ");
											q.setCompanyId(companyId);
											q.setCompanyName(companyName);
											System.out.println(q.getQuestionText());
											list.add(q);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									
								}
								else if(text.contains("6. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									if(e2 != null && text != null){
										text = text+ e2.html();
										try {
											Question q= parseQuestion(text, "6. ");
											q.setCompanyId(companyId);
											q.setCompanyName(companyName);
											System.out.println(q.getQuestionText());
											list.add(q);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									
								}
								else if(text.contains("7. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									if(e2 != null && text != null){
										text = text+ e2.html();
										try {
											Question q= parseQuestion(text, "7. ");
											q.setCompanyId(companyId);
											q.setCompanyName(companyName);
											System.out.println(q.getQuestionText());
											list.add(q);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									
								}
								else if(text.contains("8. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									if(e2 != null && text != null){
										text = text+ e2.html();
										try {
											Question q= parseQuestion(text, "8. ");
											q.setCompanyId(companyId);
											q.setCompanyName(companyName);
											System.out.println(q.getQuestionText());
											list.add(q);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									
								}
								else if(text.contains("9. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									if(e2 != null && text != null){
										text = text+ e2.html();
										try {
											Question q= parseQuestion(text, "9. ");
											q.setCompanyId(companyId);
											q.setCompanyName(companyName);
											System.out.println(q.getQuestionText());
											list.add(q);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									
								}
								else if(text.contains("10. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									if(e2 != null && text != null){
										text = text+ e2.html();
										try {
											Question q= parseQuestion(text, "10. ");
											q.setCompanyId(companyId);
											q.setCompanyName(companyName);
											System.out.println(q.getQuestionText());
											check2 = true;
											list.add(q);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									
								}
								
							}
					}
				}
				
			}
		return list;
	}
	
	void processQuestionsForSimilarContent(List<Question> questions, String qualifier1, String qualifier2){
		Map<String, Question> map = new HashMap<>();
		int count = 0;
		for(Question question : questions){
			question.setQuestionText("("+qualifier2+") "+question.getQuestionText());
			if(map.get(question.getQuestionText()) == null){
				map.put(question.getQuestionText(), question);
			}
			else{
				count++;
				question.setQuestionText(question.getQuestionText()+" ("+count+")");
				map.put(question.getQuestionText(), question);
			}
		}
	}
	
	@RequestMapping(value = "/bulkProcessCrawlerData1", method = RequestMethod.POST)
	public @ResponseBody String bulkProcessCrawlerData1(@RequestParam(name= "companyId") String companyId, @RequestParam(name= "folderLocation") String folderLocation, HttpServletResponse response, HttpServletRequest request) throws Exception {
		folderLocation = URLDecoder.decode(folderLocation);
		File dir = new File(folderLocation);
		File files[] = dir.listFiles();
		System.out.println("no of files to be processed "+files.length);
		Company company = companyService.findByCompanyId(companyId);
			if(company == null){
				return "Invalid Company";
			}
			for(File f:files){
				InputStream stream = FileUtils.openInputStream(f);
				File file = ResourceUtils.getFile("classpath:crawler.xml");
				System.out.println("processing excel file "+f.getName());
				List<CrawlerTopic> records = ExcelReader.parseExcelFileToBeans(stream, file);
				System.out.println("records size for "+f.getName()+" - "+records.size());
				File filestatus = new File("fileStatus"+File.separator+"status.txt");
				String filesProcessedData = FileUtils.readFileToString(filestatus);
				if(!filesProcessedData.contains(f.getName())){
					for(CrawlerTopic topic : records){
						List<Question> questions = new ArrayList<>();
							try{
								questions = goToExamPage(topic.getUrl(), company.getCompanyId(), company.getCompanyName());
							}
							catch(Exception e){
								e.printStackTrace();
								FileUtils.write(filestatus, " problem in processing "+f.getName()+" url - "+topic.getUrl()+" \n", true);
							}
						
						System.out.println("questions size for "+topic.getUrl()+" - "+questions.size());
						processQuestionsForSimilarContent(questions, topic.getQualifier1(), topic.getQualifier2());
							for(Question q: questions){
								
								if(q.getImageUrl() != null && q.getImageUrl().trim().length() > 0){

								}
								q.setQualifier1(topic.getQualifier1());
								q.setQualifier2(topic.getQualifier2());
								questionService.createQuestion(q);
								CandidateProfileParams params = profileParamsService.findUniqueCandidateProfileParams(company.getCompanyId(), q.getQualifier1(), q.getQualifier2()==null?"NA":q.getQualifier2().isEmpty()?"NA":q.getQualifier2(), q.getQualifier3()==null?"NA":q.getQualifier3().isEmpty()?"NA":q.getQualifier3(), q.getQualifier4()==null?"NA":q.getQualifier4().isEmpty()?"NA":q.getQualifier4(), q.getQualifier5()==null?"NA":q.getQualifier5().isEmpty()?"NA":q.getQualifier5());
								if(params == null){
									CandidateProfileParams candidateProfileParam = new CandidateProfileParams();
									candidateProfileParam.setCompanyId(company.getCompanyId());
									candidateProfileParam.setCompanyName(company.getCompanyName());
									candidateProfileParam.setCreateDate(new Date());
									candidateProfileParam.setLESS_THAN_TWENTY_PERCENT("Need to work a bit. Not good!");
									candidateProfileParam.setBETWEEN_TWENTY_AND_FIFTY("Knowledge Quotient inadequate for the given topic!!");
									candidateProfileParam.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average Grade for a given topic!!");
									candidateProfileParam.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good knowledge. Potential to be an expert in this area!!");
									candidateProfileParam.setMORE_THAN_NINETY("Excellent knowledge on the theory behind the topic.");
									candidateProfileParam.setQualifier1(q.getQualifier1());
									candidateProfileParam.setQualifier2(q.getQualifier2()==null?"NA":q.getQualifier2().isEmpty()?"NA":q.getQualifier2());
									candidateProfileParam.setQualifier3(q.getQualifier3()==null?"NA":q.getQualifier3().isEmpty()?"NA":q.getQualifier3());
									candidateProfileParam.setQualifier4(q.getQualifier4()==null?"NA":q.getQualifier4().isEmpty()?"NA":q.getQualifier4());
									candidateProfileParam.setQualifier5(q.getQualifier5()==null?"NA":q.getQualifier5().isEmpty()?"NA":q.getQualifier5());
									profileParamsRepository.save(candidateProfileParam);
								}
							}
							Integer count = questionRepository.findQuestionsCountByQualifier2(company.getCompanyId(),topic.getQualifier1(), topic.getQualifier2() );
							System.out.println("Q count for "+topic.getQualifier1()+" "+topic.getQualifier2()+" - "+count);
							topic.setNoOfQuestions(count);
							topic.setCompanyId(company.getCompanyId());
							topic.setCompanyName(company.getCompanyName());
							crawlerTopicService.saveOrUpdate(topic);
					}
				}
				else{
					System.out.println("File "+f.getName()+" already processed");
				}
				
				String sts = f.getName()+"processed. \n";
				FileUtils.write(filestatus, sts, true);
			}
			FileUtils.write(new File("bulkUpload-"+System.currentTimeMillis()+".txt"), "Upload Complete");
			return "ok";
	}
	
	@RequestMapping(value = "/uploadCrawlerData1", method = RequestMethod.POST)
	public @ResponseBody String uploadCrawlerData1(HttpServletResponse response, MultipartHttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("webdataExt");
		MultipartFile multipartFile = request.getFile("fileCrawler");
		Long size = multipartFile.getSize();
		String fileName = multipartFile.getName();
		String contentType = multipartFile.getContentType();
		InputStream stream = multipartFile.getInputStream();
		File file = ResourceUtils.getFile("classpath:crawler.xml");

		List<CrawlerTopic> records = ExcelReader.parseExcelFileToBeans(stream, file);
			for(CrawlerTopic topic : records){
				List<Question> questions = goToExamPage(topic.getUrl(), user.getCompanyId(), user.getCompanyName());
				processQuestionsForSimilarContent(questions, topic.getQualifier1(), topic.getQualifier2());
					for(Question q: questions){
						
						if(q.getImageUrl() != null && q.getImageUrl().trim().length() > 0){
//							String ext = q.getImageUrl().substring(q.getImageUrl().lastIndexOf(".")+1, q.getImageUrl().length());
//							String fn = ""+System.currentTimeMillis()+"."+ext;
//							String destination = propertyConfig.getFileServerLocation()+File.separator+"images"+File.separator+fn;
//							
//							try(InputStream in = new URL(q.getImageUrl()).openStream()){
//							    Files.copy(in, Paths.get(destination));
//							}
//							 String imageUrl = propertyConfig.getFileServerWebUrl()+"images/"+fn;
//							 q.setExternalImageUrl(q.getImageUrl());
//							 q.setImageUrl(imageUrl);
						}
						q.setQualifier1(topic.getQualifier1());
						q.setQualifier2(topic.getQualifier2());
						questionService.createQuestion(q);
						CandidateProfileParams params = profileParamsService.findUniqueCandidateProfileParams(user.getCompanyId(), q.getQualifier1(), q.getQualifier2()==null?"NA":q.getQualifier2().isEmpty()?"NA":q.getQualifier2(), q.getQualifier3()==null?"NA":q.getQualifier3().isEmpty()?"NA":q.getQualifier3(), q.getQualifier4()==null?"NA":q.getQualifier4().isEmpty()?"NA":q.getQualifier4(), q.getQualifier5()==null?"NA":q.getQualifier5().isEmpty()?"NA":q.getQualifier5());
						if(params == null){
							CandidateProfileParams candidateProfileParam = new CandidateProfileParams();
							candidateProfileParam.setCompanyId(user.getCompanyId());
							candidateProfileParam.setCompanyName(user.getCompanyName());
							candidateProfileParam.setCreateDate(new Date());
							candidateProfileParam.setLESS_THAN_TWENTY_PERCENT("Need to work a bit. Not good!");
							candidateProfileParam.setBETWEEN_TWENTY_AND_FIFTY("Knowledge Quotient inadequate for the given topic!!");
							candidateProfileParam.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average Grade for a given topic!!");
							candidateProfileParam.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good knowledge. Potential to be an expert in this area!!");
							candidateProfileParam.setMORE_THAN_NINETY("Excellent knowledge on the theory behind the topic.");
							candidateProfileParam.setQualifier1(q.getQualifier1());
							candidateProfileParam.setQualifier2(q.getQualifier2()==null?"NA":q.getQualifier2().isEmpty()?"NA":q.getQualifier2());
							candidateProfileParam.setQualifier3(q.getQualifier3()==null?"NA":q.getQualifier3().isEmpty()?"NA":q.getQualifier3());
							candidateProfileParam.setQualifier4(q.getQualifier4()==null?"NA":q.getQualifier4().isEmpty()?"NA":q.getQualifier4());
							candidateProfileParam.setQualifier5(q.getQualifier5()==null?"NA":q.getQualifier5().isEmpty()?"NA":q.getQualifier5());
							profileParamsRepository.save(candidateProfileParam);
						}
					}
					Integer count = questionRepository.findQuestionsCountByQualifier2(user.getCompanyId(),topic.getQualifier1(), topic.getQualifier2() );
					System.out.println("Q count for "+topic.getQualifier1()+" "+topic.getQualifier2()+" - "+count);
					topic.setNoOfQuestions(count);
					topic.setCompanyId(user.getCompanyId());
					topic.setCompanyName(user.getCompanyName());
					crawlerTopicService.saveOrUpdate(topic);
			}
			
			//return webdataExt(null, "Upload Success", response, request);
			return "ok";
	}
	
	@RequestMapping(value = "/webdataGetQualifiers", method = RequestMethod.GET)
	public @ResponseBody List<String> webdataGetQualifiers(@RequestParam(name= "topicName", required = true) String topicName,  HttpServletResponse response, HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		List<String> qualifiers = crawlerRep.getDistinctQualifier1ForTopicName(user.getCompanyId(), topicName);
		return qualifiers;
	}
	
	@RequestMapping(value = "/webdataExt", method = RequestMethod.GET)
	public ModelAndView webdataExt(@RequestParam(name= "qualifier1", required = false) String qualifier1, @RequestParam(name= "topicName", required = false) String topicName, @RequestParam(name= "page", required = false) Integer pageNumber, @RequestParam(name="searchLabel", required=false) String searchLabel, @RequestParam(name="msg", required=false) String msg, HttpServletResponse response, HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("webdataExt");
		List<CrawlerTopic> topics = new ArrayList<>();
		if(pageNumber == null) {
			pageNumber = 0;
		}
		
		List<String > topicNames = crawlerRep.getDistinctTopicNames(user.getCompanyId());
		List<String> qualifier1s = new ArrayList<>();
		if(topicNames.size() > 0){
			qualifier1s = crawlerRep.getDistinctQualifier1ForTopicName(user.getCompanyId(), topicNames.get(0));
		}
		
		mav.addObject("topicNames", topicNames);
		mav.addObject("qualifier1s", qualifier1s);
		
		org.springframework.data.domain.Page<CrawlerTopic> topicsPage = null;
			if(searchLabel == null){
				//topics = crawlerTopicService.getCrawlerTopics(user.getCompanyId());
				if(topicName != null && qualifier1 != null){
					topicsPage = crawlerRep.getCrawlerTopicsForTopicAndqualifier1(user.getCompanyId(), topicName, qualifier1, PageRequest.of(pageNumber, 30));
					topics = topicsPage.getContent();
				}
				else{
					topicsPage = crawlerRep.getCrawlerTopicsPage(user.getCompanyId(), PageRequest.of(pageNumber, 30));
					topics = topicsPage.getContent();
				}
				
			}
			else{
				//topics = crawlerTopicService.searchCrawlerTopics(searchLabel, user.getCompanyId());
				topicsPage = crawlerRep.searchCrawlerTopicsPage(searchLabel, user.getCompanyId(), PageRequest.of(pageNumber, 30));
				topics = topicsPage.getContent();
			}
			
			if(msg != null){
				mav.addObject("message", msg);// later put it as label
				mav.addObject("msgtype", "Information");
			}
		mav.addObject("topics", topics);
		CommonUtil.setCommonAttributesOfPagination(topicsPage, mav.getModelMap(), pageNumber, "webdataExt", null);
		return mav;
	}

}
