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
public class CareericonsControllers {

	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/careericonsContent", method = RequestMethod.GET)
	public @ResponseBody String careericonsContent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		File file = new File("Data careericons.com.xlsx");
		InputStream stream = FileUtils.openInputStream(file);
		File f = ResourceUtils.getFile("classpath:swaal.xml");
		System.out.println("processing excel file " + f.getName());
		List<Swaal> records = ExcelReader.parseExcelFileToBeans(stream, f);
		for (Swaal swaal : records) {
//			System.out.println("............................                      " + swaal.getLink()+"......"+swaal.getPages());
			for (int k = 1; k <= swaal.getPages(); k++) {
				List<Question> questions = new ArrayList<Question>();

				String link = swaal.getLink();
				link = link.substring(0, link.length() - 2);
				link = link + k + "/";
				Document doc = Jsoup.connect(link).get();
				System.out.println(".............    " + link);
				Elements elements = doc.getAllElements();

				for (int i = 0; i < elements.size(); i++) {

					Element element = elements.get(i);
					if (element.className().equals("panel-body table-responsive")) {
//						if (element.className().equals("ft_sz_20 text-justify")) {
						Question question = new Question();
						int flag = 0;
						String q = "";
						try {
							Elements eles3 = element.getElementsByTag("div");
							for (Element element2 : eles3) {
								if (element2.className().equals("ft_sz_20 text-justify")) {
									q += element2.html().replaceAll("&nbsp;", "").replaceAll("<strong>", "").replaceAll("</strong>", "")
											.replaceAll("</p>", "").replaceAll("<p>", "");
//									System.out.println("......" + q);
								}
								if (element2.className().equals("table-responsive")) {
									if (q.equals("")) {
										q += element2.html().replaceAll("&nbsp;", "").replaceAll("<strong>", "")
												.replaceAll("</strong>", "").replaceAll("</p>", "").replaceAll("<p>", "");
//										System.out.println(".aaaaaaaaaaaaaaaaaaaaaaa." + q);
									}
								}
								for (Node node : element2.childNodes()) {
									if (node.getClass().getName().equals("org.jsoup.nodes.Element")) {
										Element el = (Element) node;
										if (el.className().equals("list-unstyled text-justify")
												|| el.className().equals("list-unstyled")) {
											q += el.text() + " \n";
										}
									}
								}
							}
							Elements eles2 = element.getElementsByTag("p");
							for (Element element2 : eles2) {
//								System.out.println("Q: "+element2);
								if (element2.className().equals("ft_sz_20 text-justify")) {
									if (q.equals("")) {
										q += element2.html().replaceAll("&nbsp;", "").replaceAll("<strong>", "")
												.replaceAll("</strong>", "").replaceAll("</p>", "").replaceAll("<p>", "");
//										System.out.println("Q: " + q);
									}
								}
								if (element2.className().equals("ft_sz_18")) {
									if (element2.html().contains("a)")) {
										flag = 1;
										question.setChoice1(element2.html());
									}
									if (element2.html().contains("b)")) {
										flag = 1;
										question.setChoice2(element2.html());
									}
									if (element2.html().contains("c)")) {
										flag = 1;
										question.setChoice3(element2.html());
									}
									if (element2.html().contains("d)")) {
										flag = 1;
										question.setChoice4(element2.html());
									}
									if (element2.html().contains("e)")) {
										flag = 1;
										question.setChoice5(element2.html());
									}
//									System.out.println("Opt: " + element2.html());
								}

								if (element2.className().equals("ft_sz_20")) {
									if (element2.html().contains("Answer")) {
										String ans = element2.html().replaceAll("&nbsp;", "");
										ans = ans.substring(ans.length() - 3);
										String right = getRightAnswer(ans);
										question.setRightChoices(right);
//										System.out.println("Answer: " + ans.substring(ans.length() - 3));
									}
								} else if (element2.html().contains("Answer")) {

									String ans = element2.html().replaceAll("&nbsp;", "");
									ans = ans.substring(ans.length() - 3);
									String right = getRightAnswer(ans);
									question.setRightChoices(right);
//									System.out.println("Answer: " + ans);

								}
							}
							if (flag == 1) {
								System.out.println("QQQQQQQQQ: " + q);
								if (!q.equals("")) {

									question.setQuestionText(q);

									question.setQualifier1(swaal.getMainCategory());
									question.setQualifier2(swaal.getMasterCategory());
									question.setQualifier3(swaal.getBranchLevel());
									question.setQualifier4(swaal.getSubjectLevel());
									question.setQualifier5(swaal.getTopicLevel());

									question.setCompanyId("e-assess");
									question.setCompanyName("e-assess");
									questions.add(question);
								}
							}

						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}

				}
				int count4 = 0;
				for (Question q : questions) {
					count4++;
					try {
						q.setup();
						FileUtils.write(new File("gulrez_careerIcons.csv"), q.getQualifier1()+","+(q.getQualifier2()==null?"NA":q.getQualifier2())+","+(q.getQualifier3()==null?"NA":q.getQualifier3())+","+(q.getQualifier4()==null?"NA":q.getQualifier4())+","+(q.getQualifier5()==null?"NA":q.getQualifier5()+"\n"), true);
						String imageUrl = extractImageUrl(q.getQuestionText());
						if(imageUrl != null){
							q.setImageUrl(imageUrl);
						}
					
						if(q.getImageUrl() != null && q.getImageUrl().trim().length() != 0){
							FileUtils.write(new File("gulrez_careerIcons_image.csv"), q.getQuestionText()+","+q.getImageUrl()+"\n",true);
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
