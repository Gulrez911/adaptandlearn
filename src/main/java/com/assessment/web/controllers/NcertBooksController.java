package com.assessment.web.controllers;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.common.AssessmentGenericException;
import com.assessment.common.ExcelReader;
import com.assessment.data.NcertBook;
import com.assessment.data.Question;
import com.assessment.services.QuestionService;

@Controller
public class NcertBooksController {

	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/ncertBooksContent", method = RequestMethod.GET)
	public @ResponseBody String generatedrishContent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		File file = new File("Question Website Data.xlsx");
		InputStream stream = FileUtils.openInputStream(file);
		File f = ResourceUtils.getFile("classpath:ncertBook.xml");
		System.out.println("processing excel file " + f.getName());
		List<NcertBook> records = ExcelReader.parseExcelFileToBeans(stream, f);
		for (NcertBook ncertBook : records) {
			System.out.println("............................                      " + ncertBook.getLink());

			List<Question> questions = new ArrayList<Question>();
			Document doc = Jsoup.connect(ncertBook.getLink()).get();
			Elements elements = doc.getAllElements();
			int count = 0;
			String myStr3 = "Question";
			for (int i = 0; i < elements.size(); i++) {

				Element element = elements.get(i);
				// System.out.println(" .......... "+element );
				if (element.className().equals("entry-content") || element.className().equals("entry-content clear")) {
					Elements eles2 = element.getElementsByTag("p");
					String text = eles2.html().replaceAll("<br>", " ");
//				System.out.println(text);
					// System.out.println("...................... rrrr");

					String[] words = text.trim().replaceAll("\n", " ").replaceAll("<br>", " ").split(" "); // Split

					for (int j = 0; j < words.length; j++) // Outer loop for
					{
						// System.out.println(words[j]);
						if (words[j].equals(myStr3)) {
							// System.out.println(words[j]+"found");
							count++;
						}
					}
					System.out.println(count);
					int count2 = 1;
					int ans2 = 0;
					Pattern patterna = Pattern.compile("Answer:\\s\\(");
					Matcher maa = patterna.matcher(text);
					while (maa.find()) {
						ans2++;
					}
					for (int k = 1; k <= count; k++) {
						String q;
						String a1;
						String b1;
						String c1;
						String d1;
						String ans;
						String expl;
						Question question = new Question();
						try {
							Pattern pattern = Pattern.compile("\\s\\([Aa)]\\)\\s");

							Matcher m = pattern.matcher(text);
							while (m.find()) {
//								System.out.println("Pattern found from " + m.start() + " to " + (m.end() - 1));
								break;
							}
//							System.out.println("... " + text.substring(m.start(), m.end() - 1));
							a1 = text.substring(m.start(), m.end() - 1);
							q = text.substring(text.indexOf("Question " + k), text.indexOf((a1)));
							String s1 = "Question " + k;
							int sepPos1 = q.indexOf(s1);
							String q2 = q.substring(sepPos1 + s1.length()).trim();
//							String s11 = "Question " + k;
//							int sepPos11 = q.indexOf;
							String q22 = q2.substring(1, q2.length()).trim();

							Pattern pattern2 = Pattern.compile("\\s\\([Bb)]\\)\\s");
							Matcher m2 = pattern2.matcher(text);
							while (m2.find()) {
//								System.out.println("Pattern found from " + m2.start() + " to " + (m2.end() - 1));
								break;
							}
							b1 = text.substring(m2.start(), m2.end() - 1);
							a1 = text.substring(text.indexOf(a1), text.indexOf(b1));
//							String s2 = a1;
//							int sepPos2 = a1.indexOf(s2);
//							String a2 = a1.substring(sepPos2 + s2.length()).trim();
							Pattern pattern3 = Pattern.compile("\\s\\([Cc)]\\)\\s");
							Matcher m3 = pattern3.matcher(text);
							while (m3.find()) {
//								System.out.println("Pattern found from " + m3.start() + " to " + (m3.end() - 1));
								break;
							}
							c1 = text.substring(m3.start(), m3.end() - 1);
							b1 = text.substring(text.indexOf(b1), text.indexOf(c1));

							Pattern pattern4 = Pattern.compile("\\s\\([Dd)]\\)\\s");
							Matcher m4 = pattern4.matcher(text);
							while (m4.find()) {
//								System.out.println("Pattern found from " + m4.start() + " to " + (m4.end() - 1));
								break;
							}
							d1 = text.substring(m4.start(), m4.end() - 1);
							c1 = text.substring(text.indexOf(c1), text.indexOf(d1));

							d1 = text.substring(text.indexOf(d1), text.indexOf("Answer: "));

							ans = text.substring(text.indexOf("Answer:"), text.indexOf("Answer:") + 11);
							ans = ans.substring(8);

							String right = getRightAnswer(ans);
							question.setQuestionText(q22);
							question.setChoice1(a1);
							question.setChoice2(b1);
							question.setChoice3(c1);
							question.setChoice4(d1);

							question.setRightChoices(right);
							question.setQualifier1(ncertBook.getMainCategory());
							question.setQualifier2(ncertBook.getMasterCategory());
							question.setQualifier3(ncertBook.getBranchLevel());
							question.setQualifier4(ncertBook.getSubjectLevel());
							question.setQualifier5(ncertBook.getTopicLevel());
							System.out.println("sss    " + q);

//							question.setCompanyId("AL");
							question.setCompanyId("e-assess");
							question.setCompanyName("e-assess");
							questions.add(question);

							count2++;
							int temp = k + 1;

							if (count2 <= count) {
								if (k <= ans2 - 1) {
									text = text.substring(text.indexOf("Question " + temp), text.length());
								}

							}
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println(e.getMessage());
						}

					}

				}

			}
			int count4 = 0;
			for (Question q : questions) {
				count4++;
				try {
					q.setup();
					FileUtils.write(new File("gulrez_ncert.csv"), q.getQualifier1()+","+(q.getQualifier2()==null?"NA":q.getQualifier2())+","+(q.getQualifier3()==null?"NA":q.getQualifier3())+","+(q.getQualifier4()==null?"NA":q.getQualifier4())+","+(q.getQualifier5()==null?"NA":q.getQualifier5()+"\n"), true);
					String imageUrl = extractImageUrl(q.getQuestionText());
						if(imageUrl != null){
							q.setImageUrl(imageUrl);
						}
					
						if(q.getImageUrl() != null && q.getImageUrl().trim().length() != 0){
							FileUtils.write(new File("gulrez_ncert_image.csv"), q.getQuestionText()+","+q.getImageUrl()+"\n",true);
						}
					System.out.println("????????????????????????????????                       Gulrez " + count4);
					questionService.createQuestion(q);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}

		}

		System.out.println("Process Complete");
		// FileUtils.write(new File("drishti.out"), "********* file processing
		// complete", true);
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

		if (answers.equals("(a)") || answers.equals("(A)")) {
			temp2 = "Choice 1";
		} else if (answers.equals("(b)") || answers.equals("(B)")) {
			temp2 = "Choice 2";
		} else if (answers.equals("(c)") || answers.equals("(C)")) {
			temp2 = "Choice 3";
		} else if (answers.equals("(d)") || answers.equals("(D)")) {
			temp2 = "Choice 4";
		} else {
			throw new AssessmentGenericException("Problem - no right answer");
		}

//		System.out.println("....                     "+temp2);
		return temp2;
	}
}
