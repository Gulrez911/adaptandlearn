package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.assessment.data.Question;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:appContext.xml"})
//@Transactional
public class TestWebCrawler {
	
//	@Autowired
//	QuestionService questionService;
	
//	public InputStream download(WebDriver driver, String url) throws IOException {
//        String script = "var url = arguments[0];" +
//                "var callback = arguments[arguments.length - 1];" +
//                "var xhr = new XMLHttpRequest();" +
//                "xhr.open('GET', url, true);" +
//                "xhr.responseType = \"arraybuffer\";" + //force the HTTP response, response-type header to be array buffer
//                "xhr.onload = function() {" +
//                "  var arrayBuffer = xhr.response;" +
//                "  var byteArray = new Uint8Array(arrayBuffer);" +
//                "  callback(byteArray);" +
//                "};" +
//                "xhr.send();";
//        Object response = ((JavascriptExecutor) driver).executeAsyncScript(script, url);
//        // Selenium returns an Array of Long, we need byte[]
//        ArrayList<Long> byteList = (ArrayList<Long>) response;
//        byte[] bytes = new byte[byteList.size()];
//        for(int i = 0; i < byteList.size(); i++) {
//            bytes[i] = (byte)(long)byteList.get(i);
//        }
//        return new ByteArrayInputStream(bytes);
//    }
//	
//	@Test
//	public void testFetchImageFromWebsite() throws Exception{
//		System.setProperty("webdriver.chrome.driver", "E:\\fromMainLaptop\\softwares\\chromedriver_win32\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		String url = "http://www.sanfoundry.com/wp-content/uploads/2018/02/basic-electrical-engineering-questions-answers-series-circuits-q1.png";
//		URL myUrl = new URL(url);
//		File f = new File("imageSan.png");
//		InputStream is = download(driver, url);
//		FileUtils.copyInputStreamToFile(is, f);
//	}
	
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
	
	Question parseQuestion(String text, String qNo){
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
	
	private void goToExamPage(String url) throws IOException{
		org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
		Elements elements = doc.getAllElements();
		Boolean check2 = false;
		int countCheck2 = 0;
			for(int i=0;i<elements.size();i++){
				if(countCheck2 > 13 || check2 == true){
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
								if(countCheck2 > 13 || check2 == true){
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
									text = text+ e2.html();
									try {
										Question q1 = parseQuestion(text, "1. ");
										System.out.println(q1.getQuestionText());
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else if(text.contains("2. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									text = text+ e2.html();
									try {
										Question q2= parseQuestion(text, "2. ");
										System.out.println(q2.getQuestionText());
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else if(text.contains("3. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									text = text+ e2.html();
									try {
										Question q3= parseQuestion(text, "3. ");
										System.out.println(q3.getQuestionText());
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else if(text.contains("4. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									text = text+ e2.html();
									try {
										Question q= parseQuestion(text, "4. ");
										System.out.println(q.getQuestionText());
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else if(text.contains("5. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									text = text+ e2.html();
									try {
										Question q= parseQuestion(text, "5. ");
										System.out.println(q.getQuestionText());
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else if(text.contains("6. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									text = text+ e2.html();
									try {
										Question q= parseQuestion(text, "6. ");
										System.out.println(q.getQuestionText());
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else if(text.contains("7. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									text = text+ e2.html();
									try {
										Question q= parseQuestion(text, "7. ");
										System.out.println(q.getQuestionText());
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else if(text.contains("8. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									text = text+ e2.html();
									try {
										Question q= parseQuestion(text, "8. ");
										System.out.println(q.getQuestionText());
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else if(text.contains("9. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									text = text+ e2.html();
									try {
										Question q= parseQuestion(text, "9. ");
										System.out.println(q.getQuestionText());
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else if(text.contains("10. ")){
									Element e2 = e.nextElementSibling();
									//System.out.println(e2.html());
									text = text+ e2.html();
									try {
										Question q= parseQuestion(text, "10. ");
										System.out.println(q.getQuestionText());
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									check2 = true;
								}
								
							}
					} 
				}
				
			}
		
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
	
	@Test
	public void testStartExamPageCrawling() throws Exception{
		
		//String powerUrl = "https://www.sanfoundry.com/basic-electrical-engineering-questions-answers-power/";
		goToExamPage("https://www.sanfoundry.com/basic-electrical-engineering-questions-answers-aptitude-test/");
	}
	
	private void goToExamListingPage(String url) throws IOException{
		org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
		Elements elements = doc.getAllElements();
		Element matchedElement = null;

			for(int i=0;i<elements.size();i++){
				Element element = elements.get(i);
				Element ele = traverseChildElementsThroughClassMatch(element, "sf-section", 0);
				if(ele != null){
					Elements eles = ele.getElementsByAttributeValueMatching("border", "0");
					if(eles != null && eles.size() > 0){
						ele = eles.get(0);
						Elements eles2 = ele.getElementsByAttribute("href");
							for(int j=0; j<eles2.size(); j++){
								if(checkIfTestLinks(eles2.get(j), "https://www.sanfoundry.com/")){
									System.out.println(eles2.attr("href"));
								}
							}
					}
				}
				
			}
//			
		
	}
	
	private boolean checkIfTestLinks(Element matchedElement, String compareUrl){
		Elements eles = matchedElement.getElementsByAttribute("href");
		for(int i=0;i<eles.size();i++){
			Element element = eles.get(i);
				if(element.attr("href").startsWith(compareUrl)){
					return true;
				}
			
			
		}
		return false;
	}
	
	
	
	@Test
	public void testStartWebCrawling() throws Exception{
		String url = "https://www.sanfoundry.com/1000-basic-electrical-engineering-questions-answers/";
		org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
		Elements elements = doc.getAllElements();
		Element matchedElement = null;
			for(int i=0;i<elements.size();i++){
				Element element = elements.get(i);
				matchedElement = traverseChildElementsThroughClassMatch(element, "entry-header", 0);
				if(matchedElement != null){
					break;
				}
			}
			
			if(matchedElement != null){
				Elements eles = matchedElement.getElementsByClass("entry-title");
				String parentCategory = eles.get(0).text();
				System.out.println(parentCategory);
				matchedElement = null;
				for(int i=0;i<elements.size();i++){
					Element element = elements.get(i);
					matchedElement = traverseChildElementsThroughClassMatch(element, "desktop-content", 0);
					if(matchedElement != null){
						break;
					}
				}
				
				if(matchedElement != null){
					eles = matchedElement.getElementsByAttribute("href");
					for(int j=0;j<eles.size();j++){
						Element e = eles.get(j);
						String ext =e.attr("href");
						System.out.println(ext);
						goToExamListingPage(url+ext);
					}
				}
				
			}
			else{
				System.out.println("not found");
			}
		
		///Elements newsHeadlines = doc.select("#.sf-top-section, .sf-section");
		//System.out.println(newsHeadlines.toString());
		
	}
	
	private Element traverseChildElementsThroughClassMatch(Element element, String match, int count){
		System.out.println("in traverseChildElementsThroughClassMatch "+count);
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
	
	private Element traverseChildElementsThroughAttributeMatch(Element element, String match){
		if(element.attr("border").equals(match)){
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
			return traverseChildElementsThroughAttributeMatch(ele, match);
		}
		
		return null;
	}

}
