<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>

<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>



</head>
<body>

 <form name="fm" id="fm" method="post" modelAttribute="wrapper" action="">

	<form:textarea path="wrapper.customArgs" rows="5" cols="30" value="" style="width: 958px" /><br/><br/>


<input type="button" value="Click to start" onClick="javascript:startTest()" />

</form>

<script>
var json = '{ "courseId": "CO1", "enrollmentId": "EO1", "userId": "u01", "learningPathId": "lp01", "sectionModuleInstanceId": "secmodins01", "tenantId": "2", "messagingQueueUrl": "https://irispoc.azurewebsites.net/api/ServiceBusEnqueue" }';
	document.getElementById('customArgs').value = json;


	function startTest(){
	
	   
	    document.fm.action = "https://localhost:8443/assessment/startTestSession?userId=MjBAanVsLmNvbQ%3D%3D&companyId=IH&testId=9114&sharedDirect=yes&startDate=MTU3NDE0ODEyMDAwMA%3D%3D&endDate=MTY1OTIwNTgwMDAwMA%3D%3D";     
	
		$("#fm").submit();
	
		
	}
</script>

</body>

</html>