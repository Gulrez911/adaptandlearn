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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.common.AssessmentGenericException;
import com.assessment.common.ExcelReader;
import com.assessment.data.Question;
import com.assessment.data.Swaal;
import com.assessment.services.QuestionService;

@Controller
public class SwaalController {

	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/swaalBooksContent", method = RequestMethod.GET)
	public @ResponseBody String generatedrishContent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		File file = new File("Sawaal.xlsx");
		InputStream stream = FileUtils.openInputStream(file);
		File f = ResourceUtils.getFile("classpath:swaal.xml");
		System.out.println("processing excel file " + f.getName());
		List<Swaal> records = ExcelReader.parseExcelFileToBeans(stream, f);
		for (Swaal swaal : records) {
//			System.out.println("............................                      " + swaal.getLink()+"......"+swaal.getPages());
			for (int k = 1; k <= swaal.getPages(); k++) {
				List<Question> questions = new ArrayList<Question>();
				String link = swaal.getLink() + "?page=" + k;
				Document doc = Jsoup.connect(link).get();
				System.out.println(".............    " + link);
				Elements elements = doc.getAllElements();

				for (int i = 0; i < elements.size(); i++) {
//			System.out.println("...........  "+count);
					Element element = elements.get(i);

					if (element.className().equals("page-content") || element.className().equals("featTeaserText_customized")
							|| element.className().equals("featTeaserText_customized span7 page-content")) {

//						System.out.println(";;;;;;;;;;;;;;;;;;;;;;;          " + element);

						Element element2 = element;
						element2.childNodes();
						Question question = new Question();
						int flag = 0;
						try {

							for (Node node : element2.childNodes()) {

								if (node.getClass().getName().equals("org.jsoup.nodes.Element")) {
									Element el = (Element) node;
									if (el.className().equals("question_and_options_space5")) {
										Elements eles2 = el.getElementsByTag("p");
										String text = eles2.text();
										if (!text.equals("")) {
											System.out.println("qqqqqqqqqqqqqqqqq:    "
													+ eles2.html().replaceAll("&nbsp;", "").replaceAll("</strong>", ""));
											String q = eles2.html().replaceAll("&nbsp;", "").replaceAll("</strong>", "");
											question.setQuestionText(q);
										}
									}

									if (el.className().equals("desktopMul")) {
										Elements eles2 = el.getElementsByTag("td");
										for (Element ele : eles2) {
											for (Node sd : ele.childNodes()) {
//											sd.childNode(1);
												if (sd.outerHtml().contains("A)")) {
													flag = 1;
													String aopt = sd.outerHtml();
													String a = aopt.substring(2, aopt.length());
													question.setChoice1(a);
//												System.out.println("C1 " + sd.outerHtml());
												}
												if (sd.outerHtml().contains("B)")) {
													flag = 1;
													String aopt = sd.outerHtml();
													String a = aopt.substring(2, aopt.length());
													question.setChoice2(a);
//												System.out.println("C2 " + sd.outerHtml());
												}
												if (sd.outerHtml().contains("C)")) {
													flag = 1;
													String aopt = sd.outerHtml();
													String a = aopt.substring(2, aopt.length());
													question.setChoice3(a);
//												System.out.println("C3 " + sd.outerHtml());
												}
												if (sd.outerHtml().contains("D)")) {
													flag = 1;
													String aopt = sd.outerHtml();
													String a = aopt.substring(2, aopt.length());
													question.setChoice4(a);
//												System.out.println("C4 " + sd.outerHtml());
												}
											}
										}

									}
									if (el.className().equals("ansDivBox")) {
										Elements eles2 = el.getElementsByTag("div");

										String ss = eles2.text();
										if (ss.contains("Answer & Explanation")) {
											String ansexpl = eles2.text();
											String tt = ansexpl.substring(ansexpl.indexOf("Answer & Explanation Answer:"),
													ansexpl.indexOf("Explanation:"));
//										System.out.println("Ans: "+tt.replaceAll("Answer & Explanation Answer: ", ""));
											tt = tt.replaceAll("Answer & Explanation Answer: ", "");
//										System.out.println("Ans: " + tt.substring(0, 2));
											String right = getRightAnswer(tt.substring(0, 2));

//										System.out.println( eles2.text());

											ansexpl = ansexpl.substring(ansexpl.indexOf("Explanation:"), ansexpl.length());
//										System.out.println( "........."+ansexpl.substring(12));
											ansexpl = ansexpl.substring(12);
//										System.out.println("........." + ansexpl);

											question.setRightChoices(right);
											question.setJustification(ansexpl);

//										System.out.println("sss    " + question);
//										questions.add(question);
//										System.out.println("........." + questions);
										}

									}
								}

//						}
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
							// TODO: handle exception
						}
						if (flag == 1) {
							question.setQualifier1(swaal.getMainCategory());
							question.setQualifier2(swaal.getMasterCategory());
							question.setQualifier3(swaal.getBranchLevel());
							question.setQualifier4(swaal.getSubjectLevel());
							question.setQualifier5(swaal.getTopicLevel());

							question.setCompanyId("e-assess");
							question.setCompanyName("e-assess");
							questions.add(question);
						}
//						questionService.createQuestion(question);

					}
				}
				int count4 = 0;
				for (Question q : questions) {
					count4++;
					try {
						q.setup();
						FileUtils.write(new File("gulrez_swal.csv"), q.getQualifier1()+","+(q.getQualifier2()==null?"NA":q.getQualifier2())+","+(q.getQualifier3()==null?"NA":q.getQualifier3())+","+(q.getQualifier4()==null?"NA":q.getQualifier4())+","+(q.getQualifier5()==null?"NA":q.getQualifier5()+"\n"), true);
						
						String imageUrl = extractImageUrl(q.getQuestionText());
						if(imageUrl != null){
							q.setImageUrl(imageUrl);
						}
					
						if(q.getImageUrl() != null && q.getImageUrl().trim().length() != 0){
							FileUtils.write(new File("gulrez_swal_image.csv"), q.getQuestionText()+","+q.getImageUrl()+"\n",true);
						}
						System.out.println("????????????????????????????????                       Gulrez " + count4);
						questionService.createQuestion(q);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
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
	private String getRightAnswer(String answers) throws Exception {
		String temp2 = "";

		if (answers.equals("a)") || answers.equals("A)")) {
			temp2 = "Choice 1";
		} else if (answers.equals("b)") || answers.equals("B)")) {
			temp2 = "Choice 2";
		} else if (answers.equals("c)") || answers.equals("C)")) {
			temp2 = "Choice 3";
		} else if (answers.equals("d)") || answers.equals("D)")) {
			temp2 = "Choice 4";
		} else {
			throw new AssessmentGenericException("Problem - no right answer");
		}

//		System.out.println("....                     "+temp2);
		return temp2;
	}
}
