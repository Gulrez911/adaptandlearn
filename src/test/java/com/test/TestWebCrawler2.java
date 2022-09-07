package com.test;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.jni.File;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.assessment.data.CrawlerTopic;

public class TestWebCrawler2 {
	private Element traverseChildElementsThroughClassMatch(Element element, String match){
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
			return traverseChildElementsThroughClassMatch(ele, match);
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
	
	public void createExcelforTopics(List<CrawlerTopic> topics, String name) throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Crawler");
        sheet.setDefaultColumnWidth(100);
         
        CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFont(font);
		style.setWrapText(true);
		// create header row
		XSSFRow header = sheet.createRow(0);

		header.createCell(0).setCellValue("Topic Name");
		header.getCell(0).setCellStyle(style);
		
		header.createCell(1).setCellValue("Qualifier 1");
		header.getCell(1).setCellStyle(style);
		
		header.createCell(2).setCellValue("Qualifier 2");
		header.getCell(2).setCellStyle(style);
		
		header.createCell(3).setCellValue("URL");
		header.getCell(3).setCellStyle(style);
		
		header.createCell(4).setCellValue("Course Video Link");
		header.getCell(4).setCellStyle(style);
 
        int rowCount = 0;
         
        for (CrawlerTopic topic : topics) {
            Row row = sheet.createRow(++rowCount);
             
             
            Cell cell = row.createCell(0);
            cell.setCellValue(topic.getTopicName());
            cell.setCellStyle(style);
            
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(topic.getQualifier1());
            cell1.setCellStyle(style);
            
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(topic.getQualifier2());
            cell2.setCellStyle(style);
            
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(topic.getUrl());
            cell3.setCellStyle(style);
        }
        XSSFRow aRow = sheet.createRow(rowCount++);
		aRow.createCell(0).setCellValue("End Rows:");
		name = name.replace("\\", "_");
		String fileName = "ContentData2"+java.io.File.separator+name+".xlsx";
		
		
		try {
			java.io.File file = new java.io.File(fileName);
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			System.out.println("save success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("problem filename "+fileName);
		}
	}
	
	public void testGetTopicLinks(String url, String topicName) throws Exception{
		topicName = StringEscapeUtils.unescapeHtml(topicName);
		org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
		Elements eles = doc.getElementsByClass("sf-section");
		String exp = "^[1-9][0-9]?.";
		Pattern pattern = Pattern.compile(exp);
		List<CrawlerTopic> topics = new ArrayList<CrawlerTopic>();
			for(Element ele : eles){
				
				Element h2 = ele.getElementsByTag("h2").first();
				if(h2 != null){
					String html = h2.html();	
					Matcher matcher = pattern.matcher(html);
				    boolean matches = matcher.find();
					
			       
			        	if(matches){
			        		
			        		Elements eles2= ele.getElementsByTag("li");
			        		for(Element e2 : eles2){
			        			Element a = e2.getElementsByTag("a").first();
			        			if(a != null){
			        				CrawlerTopic crawlerTopic = new CrawlerTopic();
									crawlerTopic.setTopicName(topicName);
					        		String qualifier1 = html.replaceFirst(exp, "").trim();
									qualifier1 = StringEscapeUtils.unescapeHtml(qualifier1);
									crawlerTopic.setQualifier1(qualifier1);
									
			        				String testUrl = a.attr("href").trim();
			        				String  qualifier2 = a.html().trim();
			        				qualifier2 = StringEscapeUtils.unescapeHtml(qualifier2);
			        				crawlerTopic.setQualifier2(qualifier2);
			        				crawlerTopic.setUrl(testUrl);
			        				
			        				if(crawlerTopic.getUrl() != null && crawlerTopic.getUrl().trim().length() > 0){
			    			        	topics.add(crawlerTopic);
			    			        }
			        			}
			        		}
			        	}
			        
				}
				
			}
				if(topics.size() != 0){
					createExcelforTopics(topics, topicName);
				}
			
	}
	
	
	@Test
	public void testGetParentLinks() throws Exception{
		String url = "https://www.sanfoundry.com/";
		org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
		Elements eles = doc.getElementsByTag("h3");
			for(Element ele : eles){
				String align = ele.attr("align");
					if(align!= null && align.equals("center")){
						System.out.println("html "+ele.html());
						Element ul = ele.nextElementSibling();
						Elements eles2 = ul.getElementsByTag("li");
						for(Element ele2 : eles2){
							//String html = ele2.html();
							Element a = ele2.getElementsByTag("a").first();
							if(a != null){
								String link = a.attr("href");
								String value = a.html();
								if(value.toLowerCase().contains("mcqs")){
									System.out.println("               "+link);
									testGetTopicLinks(link, value);
								}
							}
							
						}
					}
			}
	}
}
