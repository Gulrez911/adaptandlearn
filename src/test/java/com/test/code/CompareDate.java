package com.test.code;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompareDate {

	public static void main(String[] args) throws ParseException {
//		String sdate="2021-03-03T17:00";
//		String edate="2021-03-05T17:00";
//		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//		try {
//			Date d1 = dateFormat.parse(sdate);
//			Date d2 = dateFormat.parse(edate);
//			if (d2.after(d1)) {
//				System.out.println("inside If");
//			} else {
//				System.out.println("Insside Else");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		String sdate="2021-03-03T17:00";
		String edate="2021-03-05T17:00";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		try {
			Date d1 = dateFormat.parse(sdate);
			Date d2 = dateFormat.parse(edate);
			if (d2.after(d1)) {
				System.out.println("inside If");
			} else {
				System.out.println("Insside Else");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//		 Date date = dateFormat.parse("2021-03-03T17:00");//You will get date object relative to server/client timezone wherever it is parsed
//		 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //If you need time just put specific format for time like 'HH:mm:ss'
//		 String dateStr = formatter.format(date);
//		 System.out.println(dateStr);
	}
}
