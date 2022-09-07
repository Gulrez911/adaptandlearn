<%@page import="com.onelogin.saml2.Auth"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<%
		Long testId = (Long)request.getAttribute("testId");
		String companyId = (String)request.getAttribute("companyId");
		
		
		String startDate = (String)request.getAttribute("startDate");
		String endDate = (String)request.getAttribute("endDate");
	Auth auth = new Auth(request, response);
		auth.login(request.getContextPath() + "/yakshaspconsumerendpoint?testId="+testId+"&companyId="+companyId+"&sharedDirect=yes&startDate="+startDate+"&endDate="+endDate);
	%>
</body>
</html>
