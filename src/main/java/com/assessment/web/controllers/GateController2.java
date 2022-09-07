package com.assessment.web.controllers;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.common.AssessmentGenericException;
import com.assessment.common.ExcelReader;
import com.assessment.data.Question;
import com.assessment.data.Swaal;
import com.assessment.services.QuestionService;


@Controller
public class GateController2 {

	@Autowired
	QuestionService questionService;
	
	static Logger logger = LoggerFactory.getLogger(GateController2.class);

	@RequestMapping(value = "/gateContent2", method = RequestMethod.GET)
	public @ResponseBody String generatedrishContent(@RequestParam(required=false) String companyId, @RequestParam(required=false) String companyName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		File file = new File("17Sep.xlsx");
		InputStream stream = FileUtils.openInputStream(file);
		File f = ResourceUtils.getFile("classpath:swaal.xml");
		System.out.println("processing excel file " + f.getName());
		List<Swaal> records = ExcelReader.parseExcelFileToBeans(stream, f);
		if(companyId == null){
			companyId = "IH";
			
		}
		
		if(companyName == null){
			companyName = "IIHT";
		}
		
		for (Swaal swaal : records) {
			for (int k = 1; k <= swaal.getPages(); k++) {
				List<Question> questions = new ArrayList();

				String link = swaal.getLink();

				link += k + "/";

				System.out.println("............................                      " + link);
				Document doc = null;
				try{
					doc = Jsoup.connect(link).get();
					Elements elements = doc.getAllElements();
	
					for (int i = 0; i < elements.size(); i++) {
						Element element = elements.get(i);
						if (element.className().equals("mtq_question mtq_scroll_item-1")) {
							Question question = new Question();
							for (Node node : element.childNodes()) {
								if (node.getClass().getName().equals("org.jsoup.nodes.Element")) {
									Element el = (Element) node;
									if (el.className().equals("mtq_question_text")) {
										System.out.println("Q: " + el.html());
										question.setQuestionText(el.html().trim());
									}
									int count = 1;
									if (el.className().equals("mtq_answer_table")) {
										for (Node node2 : el.childNodes()) {
											if (node2.getClass().getName().equals("org.jsoup.nodes.Element")) {
												Element el2 = (Element) node2;
												for (Node node3 : el2.childNodes()) {
													Element el3 = (Element) node3;
													if (el3.className().equals("mtq_clickable")) {
														for (Node node4 : el3.childNodes()) {
															Element el4 = (Element) node4;
															if (el4.className().equals("mtq_letter_button_td")) {
																Element el5 = (Element) el4;
	//															System.out.println("Co:   "+el5.html());
																String correct = el5.text();
																for (Node node5 : el5.childNodes()) {
																	Element el6 = (Element) node5;
																	if (el6.className().equals(
																			"mtq_marker mtq_correct_marker")) {
																		System.out.println(
																				"correct:     " + correct);
																	String right=	getRightAnswer(correct);
																	question.setRightChoices(right);
	
																	}
																}
	//															System.out.println("$$$$$$$ :    " + el4.text());
															}
															if (el4.className().equals("mtq_answer_td")) {
																if (count == 1) {
																	System.out.println("1: " + el4.text());
																	question.setChoice1(el4.text());
																} else if (count == 2) {
																	System.out.println("2: " + el4.text());
																	question.setChoice2(el4.text());
																} else if (count == 3) {
																	System.out.println("3: " + el4.text());
																	question.setChoice3(el4.text());
																} else if (count == 4) {
																	System.out.println("4: " + el4.text());
																	question.setChoice4(el4.text());
																} else if (count == 5) {
																	System.out.println("5: " + el4.text());
																	question.setChoice5(el4.text());
																} else if (count == 6) {
																	System.out.println("6:  " + el4.text());
																	question.setChoice6(el4.text());
																}
																count++;
															}
														}
	
													}
													//
												}
											}
										}
									}
								}
	
							}
						 
							question.setQualifier1(toCamelCase(swaal.getMainCategory()));
							question.setQualifier2(toCamelCase(swaal.getMasterCategory()));
							question.setQualifier3(toCamelCase(swaal.getBranchLevel()));
							question.setQualifier4(toCamelCase(swaal.getSubjectLevel()));
							question.setQualifier5(toCamelCase(swaal.getTopicLevel()));
							question.setCompanyId(companyId);
							question.setCompanyName(companyName);
	
							System.out.println("//////////////////////               " + questions.add(question));
						}
					}
				
				}
				catch(Exception e){
					e.printStackTrace();
					System.out.println("Problem url "+link);
					logger.info("potential Problem url "+link, e);
				}
				
				int count4 = 0;
				for (Question q : questions) {
					count4++;
					try {
						if (q.getQuestionText().equals("") || q.getChoice1().equals("") || q.getChoice2().equals("")|| q.getRightChoices().equals("")) {
							System.out.println("Y");
							continue;
						}
					} catch (NullPointerException nullPointer) {
						System.out.println("X");
						continue;
					}
					System.out.println("????????????????????????????????                       Gulrez " + count4);
					
					q.setup();
					FileUtils.write(new File("gulrez_gate.csv"), q.getQualifier1()+","+(q.getQualifier2()==null?"NA":q.getQualifier2())+","+(q.getQualifier3()==null?"NA":q.getQualifier3())+","+(q.getQualifier4()==null?"NA":q.getQualifier4())+","+(q.getQualifier5()==null?"NA":q.getQualifier5())+"\n", true);
					
					String imageUrl = extractImageUrl(q.getQuestionText());
					if(imageUrl != null){
						q.setImageUrl(imageUrl);
					}
				
					if(q.getImageUrl() != null && q.getImageUrl().trim().length() != 0){
						FileUtils.write(new File("gulrez_gate_image.csv"), q.getQuestionText()+","+q.getImageUrl()+"\n",true);
					}
					System.out.println("????????????????????????????????                       Gulrez " + count4);
					
					try {
						questionService.createQuestion(q);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						FileUtils.write(new File("gulrez_gate_insertionError.csv"), q.getQuestionText()+","+e.getMessage()+"\n",true);
							if(q.getQuestionText().startsWith("Select a Choice")){
								q.setQuestionText(q.getQuestionText().replace(""+System.currentTimeMillis(), "Select a Choice"));
							}
							else{
								q.setQuestionText("Select a Choice -"+q.getQuestionText());
							}
						
						try {
							questionService.createQuestion(q);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							FileUtils.write(new File("gulrez_gate_insertionError.csv"), "Trying again "+q.getQuestionText()+","+e.getMessage()+"\n",true);
							e1.printStackTrace(); 
						}
					}
				}
			}
		}

		System.out.println("Process Complete");
		return "OK";
	}

	public static String extractImageUrl(String text){
		 final String s = text;
	        final String regex = "(?<=<img src=\")[^\"]*";
	        final Pattern p = Pattern.compile(regex);
	        final Matcher m = p.matcher(s);
	        while (m.find()) {
	        	String str = m.group();
	            System.out.println("image available "+str);
	            return str;
	        }
	      return null;
	}
	
	  public static String toCamelCase(final String init) {
		    if (init == null)
		        return null;

		    final StringBuilder ret = new StringBuilder(init.length());

		    for (final String word : init.split(" ")) {
		        if (!word.isEmpty()) {
		            ret.append(Character.toUpperCase(word.charAt(0)));
		            ret.append(word.substring(1));
		        }
		        if (!(ret.length() == init.length()))
		            ret.append(" ");
		    }

		    return ret.toString();
		}
	
	private String getRightAnswer(String answers) throws Exception {
		String temp2 = "";

		if (answers.equals("a)") || answers.equals("A)") || answers.equals("(A)") || answers.equals("(a)") || answers.equals("(A") || answers.equals("(a")
				|| answers.equals("a") || answers.equals("A")) {
			temp2 = "Choice 1";
		} else if (answers.equals("b)") || answers.equals("B)") || answers.equals("(B)") || answers.equals("(b)") || answers.equals("(B")
				|| answers.equals("(b") || answers.equals("b") || answers.equals("B")) {
			temp2 = "Choice 2";
		} else if (answers.equals("c)") || answers.equals("C)") || answers.equals("(C)") || answers.equals("(c)") || answers.equals("(C")
				|| answers.equals("(c") || answers.equals("c") || answers.equals("C")) {
			temp2 = "Choice 3";
		} else if (answers.equals("d)") || answers.equals("D)") || answers.equals("(D)") || answers.equals("(d)") || answers.equals("(D")
				|| answers.equals("(d") || answers.equals("d") || answers.equals("D")) {
			temp2 = "Choice 4";
		} else if (answers.equals("e)") || answers.equals("E)") || answers.equals("(E)") || answers.equals("(e)") || answers.equals("(E")
				|| answers.equals("(e") || answers.equals("e") || answers.equals("E")) {
			temp2 = "Choice 5";
		} else {
			throw new AssessmentGenericException("Problem - no right answer");
		}

//		System.out.println("....                     "+temp2);
		return temp2;
	}
}
