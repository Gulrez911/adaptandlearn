package com.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.glassfish.jersey.internal.guava.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.common.AssessmentGenericException;
import com.assessment.data.Question;
import com.assessment.services.QuestionService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestPdf {
	
	@Autowired
	QuestionService questionService;
	
	@Test
	public void testReadPDFLastPageAnswers(){
		try {
            PDDocument doc = PDDocument.load(new File("sample.pdf"));
            PDFTextStripper reader = new PDFTextStripper();
            
            reader.setStartPage(doc.getNumberOfPages() -1);
            String text = reader.getText(doc);
            //System.out.println(text);
            
            String pattern = "\\d{1,2}([\\.])[ ]{1,}[(]([a-d])[)]";
            
            Pattern p = Pattern.compile(pattern);
            Matcher matcher = p.matcher(text);
            //int count = matcher.groupCount();
            int count = 0;
            while(matcher.find()) {
                count++;
                int start = matcher.start();
                int end =  matcher.end();
                System.out.println("found: " + count +"- "+text.substring(start, end));
            }
		}
		catch(Exception e){
			System.out.println(e.getMessage() +" in get answers");
		}
	}
	
	@Test
	@Rollback(value=false)
	public void startQGeneration() throws Exception{
		String loc = "C:\\del\\drishti_ias";
		File dir = new File(loc);
		List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for(File file : files){
			List<Question> qs = extractQuestions(file);
			System.out.println("Questions size for "+file+" is "+qs.size());
			if(qs.size() >= 25){
				List<List<Question>> partions = new ArrayList<>();
				int bucket = qs.size() / 5;
				int cnt = 0;
				List<Question> part1 = qs.subList(0, bucket );
				List<Question> part2 = qs.subList(bucket, (bucket *2) );
				List<Question> part3 = qs.subList((bucket *2), (bucket *3) );
				List<Question> part4 = qs.subList((bucket *3), (bucket *4) );
				List<Question> part5 = qs.subList((bucket *4), qs.size());
				partions.add(part1);	
				partions.add(part2);
				partions.add(part3);
				partions.add(part4);
				partions.add(part5);
				
				if(partions.size() != 5){
					System.out.println("some problem");
					
				}
				else{
					for(int i=0; i<5; i++){
						List<Question> qsBatch = partions.get(i);
						for(Question q: qsBatch){
							q.setQualifier4("Section "+(i+1));
							q.setup();
							questionService.createQuestion(q);
						}
					}
				}
			}
		}
	}
	
	private List<String> extractAnswers(File file) throws Exception{
		List<String> answers = new ArrayList<>();
		try {
            PDDocument doc = PDDocument.load(file);
            PDFTextStripper reader = new PDFTextStripper();
            
            reader.setStartPage(doc.getNumberOfPages() -1);
            String text = reader.getText(doc);
            //System.out.println(text);
            
            String pattern = "\\d{1,2}([\\.])[ ]{1,}[(]([a-d])[)]";
            
            Pattern p = Pattern.compile(pattern);
            Matcher matcher = p.matcher(text);
            //int count = matcher.groupCount();
            int count = 0;
            while(matcher.find()) {
                count++;
                int start = matcher.start();
                int end =  matcher.end();
                answers.add(text.substring(start, end));
               // System.out.println("found: " + count +"- "+text.substring(start, end));
                
            }
		}
		catch(Exception e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			throw e;
		}
		return answers;
	}
	
	private String getRightAnswer(String questionText,  List<String> answers) throws Exception{
		 String pattern = "\\d{1,2}([\\.])[ ]{1,}";
         
         Pattern p = Pattern.compile(pattern);
         Matcher matcher = p.matcher(questionText);
         if(matcher.find()){
        	 int start = matcher.start();
             int end =  matcher.end();
        	 String init = questionText.substring(start, end);
        	 for(String ans : answers){
        		 init = init.trim();
        		 StringTokenizer stk = new StringTokenizer(ans, " ");
        		 String qInit = stk.nextToken();
        		 qInit = qInit.trim();
        		 if(init.equals(qInit)){
        			String aInit =  stk.nextToken().trim();
        			if(aInit.equals("(a)")){
        				return "Choice 1";
        			}
        			else if(aInit.equals("(b)")){
        				return "Choice 2";
        			}
        			else if(aInit.equals("(c)")){
        				return "Choice 3";
        			}
        			else if(aInit.equals("(d)")){
        				return "Choice 4";
        			}
        			else{
        				throw new AssessmentGenericException("Problem - no right answer");
        			}
        		 }
        	 }
        	 throw new AssessmentGenericException("Problem - no right answer");
         }
         else{
        	 throw new AssessmentGenericException("Problem");
         }
	}
	
	public List<Question> extractQuestions(File file) throws Exception{
		List<Question> questions = new ArrayList<Question>();
		List<String> answers = extractAnswers(file);
//		StringTokenizer stk = new StringTokenizer(file.getName(), " ");
//		String one = stk.nextToken();
//		String two = stk.nextToken();
		try {
            PDDocument doc = PDDocument.load(file);
            PDFTextStripper reader = new PDFTextStripper();
            reader.setStartPage(2);
            //reader.setEndPage(i);
            String text = reader.getText(doc);
            text = text.substring(text.indexOf(1+". "), text.length());
            
	            for(int i=1; i<=100; i++){
	            	String q = "";
	            	String a1;
					String b1;
					String c1;
					String d1 = "";
					Question question = new Question();
					try {
						q = text.substring(text.indexOf(i+". "), text.indexOf("(a)"));
						a1 = text.substring(text.indexOf("(a)"), text.indexOf("(b)"));
						b1 = text.substring(text.indexOf("(b)"), text.indexOf("(c)"));
						c1 = text.substring(text.indexOf("(c)"), text.indexOf("(d)"));
						
						
						try {
							
							question.setQuestionText(q);
							String right = getRightAnswer(q, answers);
							question.setRightChoices(right);
							if(question.getOne() == false && question.getTwo() == false && question.getThree() == false && question.getFour() == false ){
								System.out.println("wrong Q since no right ans "+file.getName());
							}
							else{
								question.setQualifier1("Competitive  Exams");
								question.setQualifier2("Current Affairs");
								question.setQualifier3(file.getName());
								//question.setQualifier4(two);
								question.setCompanyId("e-assess");
								question.setCompanyName("e-assess");
								questions.add(question);
							}
							
							if(text.indexOf((i+1)+". ") < text.indexOf("(d)")){
								String tempQ = q.replace((i+1)+". ", ""+(i+1));
								String tempa1 = a1.replace((i+1)+". ", ""+(i+1));
								String tempb1 = b1.replace((i+1)+". ", ""+(i+1));
								String tempc1 = c1.replace((i+1)+". ", ""+(i+1));
								String tempText = text.replace((q + a1 + b1 + c1), (tempQ + tempa1 + tempb1 + tempc1));
								d1 = tempText.substring(tempText.indexOf("(d)"), tempText.indexOf((i+1)+". "));
							}
							else{
								d1 = text.substring(text.indexOf("(d)"), text.indexOf((i+1)+". "));
							}
						
						text = text.substring((q + a1 + b1 + c1 + d1).length(), text.length());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							System.out.println(e.getMessage());
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println(e.getMessage());
						break;
					}
	            	//System.out.println("Q "+q);
	            	//System.out.println("Option 1 "+a1);
	            	//System.out.println("Option 2 "+b1);
	            	//System.out.println("Option 3 "+c1);
	            	//System.out.println("Option 4 "+d1);
	            }
            //System.out.println("Text in PDF\n---------------------------------");
           // System.out.println(text);
            } 
	 	catch (IOException e) {
            e.printStackTrace();
        }
		 return questions;
	}
	
	
	
	@Test
	public void testReadPdf() throws IOException{
		 try {
	            PDDocument doc = PDDocument.load(new File("sample.pdf"));
	            PDFTextStripper reader = new PDFTextStripper();
	            reader.setStartPage(2);
	            //reader.setEndPage(i);
	            String text = reader.getText(doc);
	            text = text.substring(text.indexOf(1+". "), text.length());
		            for(int i=1; i<=100; i++){
		            	String q = "";
		            	String a1;
						String b1;
						String c1;
						String d1;
						try {
							q = text.substring(text.indexOf(i+". "), text.indexOf("(a)"));
							a1 = text.substring(text.indexOf("(a)"), text.indexOf("(b)"));
							b1 = text.substring(text.indexOf("(b)"), text.indexOf("(c)"));
							c1 = text.substring(text.indexOf("(c)"), text.indexOf("(d)"));
								if(text.indexOf((i+1)+". ") < text.indexOf("(d)")){
									String tempQ = q.replace((i+1)+". ", ""+(i+1));
									String tempa1 = a1.replace((i+1)+". ", ""+(i+1));
									String tempb1 = b1.replace((i+1)+". ", ""+(i+1));
									String tempc1 = c1.replace((i+1)+". ", ""+(i+1));
									String tempText = text.replace((q + a1 + b1 + c1), (tempQ + tempa1 + tempb1 + tempc1));
									d1 = tempText.substring(tempText.indexOf("(d)"), tempText.indexOf((i+1)+". "));
								}
								else{
									d1 = text.substring(text.indexOf("(d)"), text.indexOf((i+1)+". "));
								}
							
							text = text.substring((q + a1 + b1 + c1 + d1).length(), text.length());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							break;
						}
		            	//System.out.println("Q "+q);
		            	//System.out.println("Option 1 "+a1);
		            	//System.out.println("Option 2 "+b1);
		            	//System.out.println("Option 3 "+c1);
		            	//System.out.println("Option 4 "+d1);
		            }
	            //System.out.println("Text in PDF\n---------------------------------");
	            //System.out.println(text);
	            } 
		 	catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}

}
