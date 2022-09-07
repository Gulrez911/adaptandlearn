<%@page import="com.onelogin.saml2.Auth"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<%
		Auth auth = new Auth(request, response);
		if (request.getParameter("attrs") == null) {
			//auth.login();//testId=37059&
			//auth.login(request.getContextPath() + "/yakshaspconsumerendpoint?testId=37059");
			auth.login(request.getContextPath() + "/yakshaspconsumerendpoint?testId=172407&companyId=IH&sharedDirect=yes&startDate=MTU4NDAzNzgwMDAwMA%3D%3D&endDate=MTg5OTU3MDYwMDAwMA%3D%3D");
		} else {
			//auth.login(request.getContextPath() + "/attrs.jsp");
			//auth.login(request.getContextPath() + "/yakshaspconsumerendpoint?testId=37059");
			auth.login(request.getContextPath() + "/yakshaspconsumerendpoint?testId=172407&companyId=IH&sharedDirect=yes&startDate=MTU4NDAzNzgwMDAwMA%3D%3D&endDate=MTg5OTU3MDYwMDAwMA%3D%3D");
			
		}
	%>
</body>
</html>
