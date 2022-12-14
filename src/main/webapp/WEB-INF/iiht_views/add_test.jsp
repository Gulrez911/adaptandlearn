<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <title>eAssess</title>
      <link href="images/E-assess_E.png" rel="shortcut icon">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
    </head>
    <body>
        <div class="maincontainer">            
            <div class="wrapper">
                <div class="row row-offcanvas row-offcanvas-left">
                    <!-- sidebar -->
                     <%
					User user = (User) request.getSession().getAttribute("user");
					System.out.println("user is "+user.getEmail() );
						if(user == null){
								response.sendRedirect("login");
						}
					
					if(user.getUserType().getType().equals("LMS_ADMIN")){
						
						System.out.println("LMS_ADMIN true");
				  %>
					<jsp:include page="side_lms_admin.jsp" /> 
				   <%	  
					}
					else{
					%>
					<jsp:include page="side.jsp" /> 
					<%
					}
					%>
                    <!-- /sidebar -->
                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main">
                        <div class="rightside">
                            <div class="rightside">
                            <div class="rightdiv settest step1" style="width: 100%;padding-right: 15px;height: auto;overflow-x: visible;">
                                
                                
                                <div class="teststeps">
                                    <div class="steps active">
                                        <span>1</span>
                                        <label><img src="images/u1107.png">Set your test</label>
                                    </div>
                                    <div class="steps line">
                                        <img src="images/u1102.png">
                                    </div>
                                    <div class="steps">
                                        <span>2</span>
                                        <label><img src="images/u1105.png">Add Questions</label>
                                    </div>
                                    <div class="steps line">
                                        <img src="images/u1102.png">
                                    </div>
                                    <div class="steps">
                                        <span>3</span>
                                        <label><img src="images/u1114.png">Add Candidates</label>
                                    </div>
                                    <div class="steps line">
                                        <img src="images/u1102.png">
                                    </div>
                                    <div class="steps">
                                        <span>4</span>
                                        <label><img src="images/u1106.png">Send Invitation</label>
                                    </div>
                                </div>
                                
                                
                                <div class="addqueform" style="overflow-x:auto;height:420px;">
                                    <form id="step1" method="POST" action="saveAndGoToStep2" modelAttribute="test">
                                        <div class="formfield">
                                            <label>Test Title</label>
                                            
<form:input path="test.testName" name="testName" id="testName" required="true" placeholder="Test Name" disabled='${(test.id == null) ? "false":"true"}'/>
                                        </div>
                                        <div class="formfield">
                                            <label>Category</label>
                                            
					    <form:input path="test.qualifier1" name="qualifier1" id="qualifier1" required="true" placeholder="Choose Category"/>
                                        </div>
					 <div class="formfield">
                                            <label>Sub Category</label>
                                           
					   <form:input path="test.qualifier2" name="qualifier2" id="qualifier2"  placeholder="Choose Sub Category"/>
                                        </div>
					 <div class="formfield">
                                            <label>Category of Sub Category</label>
                                            
					    <form:input path="test.qualifier3" name="qualifier3" id="qualifier3"  placeholder="Choose Category of a Sub Category"/>
                                        </div>
                                        <div class="formfield">
                                            <label>Skills</label>
                                           <!-- <input type="text" placeholder="Eg. Hire Java Freshers" value="Java" disabled> -->
										    <form:select path="test.skls" multiple="multiple" class="multiselect col-md-4">
												<form:options items="${skls}" itemValue="id" itemLabel="label" />
											 </form:select>
                                        </div>
										
										<div class="formfield">
                                            <label>Difficulty Level</label>
                                           <!-- <input type="text" placeholder="Eg. Hire Java Freshers" value="Java" disabled> -->
										    <form:select path="test.level" >
												<form:options items="${levels}"  />
											 </form:select>
                                        </div>
										
                                        <div class="formfield">
                                            <label>Duration</label>
                                          
					    <form:input path="test.testTimeInMinutes" name="testTimeInMinutes" id="testTimeInMinutes"  style="width: 25%;" value="" placeholder="Enter time in minutes" />
                                            <select style="width: 25%;">
                                                   <option selected>Minutes</option>
                                            </select>
                                        </div>
                                       
                                        <div class="formfield">
                                            <label>Passmark in Percentage</label>
                                             <form:input path="test.passPercent" name="passPercent" id="passPercent"  style="width: 25%;" value=""  />
                                        </div>
                                        <div class="formfield">
                                            <label>Conducted by</label>
                                            <form:input path="user.email" name="email" id="email"  style="width: 25%;" value=""  disabled="true"/>
                                           
                                        </div>
										
										 <div class="formfield">
                                            <label>Image URL for Test</label>
                                             <form:input path="test.imageUrl" name="imageUrl" id="imageUrl"  style="width: 25%;" value=""  />
                                        </div>
										
                                        <div class="formfield">
                                            <label>Displayed upon exam completion and email communications relating to the exam</label>
                                           <form:checkbox path="test.sentToStudent" />
                                            Email a copy of the student's results to above contact
                                        </div>
										
										
										
										<div class="formfield">
                                            <label>Share Recommendation Engine by Email </label>
                                            <form:checkbox path="test.sendRecommReport" id="sendRecommReport"/>
                                           
                                        </div>
										<div class="formfield">
                                            <label>No of Attempts Allowed </label>
                                             <form:input path="test.noOfConfigurableAttempts" name="noOfConfigurableAttempts" id="noOfConfigurableAttempts"  style="width: 25%;" value="" onkeypress='validate(event)' />
                                        </div>
					
					 <div class="formfield">
                                            <label>Shuffle Questions in Test</label>
					    <form:checkbox path="test.randomQuestions" id="random"/>
                                             
                                        </div>
					
										<div class="formfield">
                                            <label>Email Domain for Test Givers </label>
					   <form:input path="test.domainEmailSupported" name="domainEmailSupported" id="domainEmailSupported"  style="width: 25%;" value="" placeholder="Enter an email domain" />
                                             
                                        </div>
										
										<div class="formfield">
                                            <label>Display Justification for Answers</label>
					   
					   <form:checkbox path="test.justification" id="justification"/>
                                             
                                        </div>
                                        <div class="formfield">
                                            <label>DisplayResultToStudent</label>
					   
					   <form:checkbox path="test.displayResultToStudent" id="displayResultToStudent"/>
                                             
                                        </div>
										
										<div class="formfield">
                                            <label>Confidence Based Assessment Flag</label>
					   
					   <form:checkbox path="test.considerConfidence" id="considerConfidence"/>
                                             
                                        </div>
										
										<div class="formfield">
                                            <label>Is this a full stack Test?</label>
					   
					   <form:checkbox path="test.fullStackTest" id="fullStackTest"/>
                                             
                                        </div>

										<div class="formfield">
                                            <label>Test Type </label>
										   <form:select path="test.testType">
												<form:options items="${testTypes}" />
											</form:select>
                                             
                                        </div>
										
										<div class="formfield">
                                            <label>Is this onboarding test (For Web Proctoring)</label>
					   
											<form:checkbox path="test.onboardingTest" id="onboardingTest"/>
                                             
                                        </div>
                                        
                                         <!--<div class="formfield">
                                            <label>Show Final Score</label>
                                            <input type="checkbox">
                                            Show participant their score upon completion
                                        </div>
                                       <div class="formfield">
                                            <label>Student Completion Report</label>
                                            <form:checkbox path="test.showFinalScoreToParticipants" />
                                            Displays the incorrect questions after a user submits their results 
                                            <br>
                                            <input type="checkbox">
                                            Include the answers when showing incorrectly answers questions
                                        </div> -->
                                        <div class="formfield">
                                            <label>Proctor Settings</label>
                                        <form:checkbox path="test.webProctoring" id="webProctoring" />
                                            Allow webcam proctoring
                                        </div>
                                        <div class="formfield">
                                            <label>Notification & Messages</label>
                                            <label>Exam Introduction Text</label>
                                            <p>Users see this text just before they begin the exam</p>
                                            <form:textarea path="test.intro" />
                                        </div>
                                        <div class="formfield">
                                            <label>Text - Completion Success</label>
                                            <label>Exam Introduction Text</label>
                                            <p>Shown to the user if they pass the exam. A copy is also sent via email</p>
                                            <form:textarea path="test.postTestCompletionText" />
                                        </div>
										
										<div class="formfield">
                                            <label>Is the test for Leoforce?</label>
					   
											<form:checkbox path="test.leoForce" id="leoForce"/>
                                             
                                        </div>
										
										<div class="formfield">
                                            <label>Is the test based on new UI?</label>
					   
											<form:checkbox path="test.newUi" id="newUi"/>
                                             
                                        </div>
                                        
                                        <div class="formfield">
                                            <label>Is the test based on new UI Pro?</label>
					   
											<form:checkbox path="test.newUiPro" id="newUi"/>
                                             
                                        </div>
                                        
                                    </form>
                                </div>
                            </div>
                            
                            <div class="nextbuttons">
                                <a class="cancelbtn" href="testlist">Cancel</a>
                                <a class="nextbtn add_test_step2" href="#" id="next">Next</a>
                            </div>
                            
                        </div>
                    <!-- /main contain -->
               
                        </div>
            </div>
        </div>
		<script src="scripts_login/jquery-3.1.1.min.js"></script>
      <script src="scripts_login/popper.min.js"></script>
      <script src="scripts_login/bootstrap.min.js"></script>
      <script src="scripts_login/smooth-scroll.js"></script>
      <script src="scripts_login/wow.js"></script>
      <script src="scripts_login/custom.js"></script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
       <script>
	 $('#next').on('click',function(){
	   //addteststep2
	   document.getElementById("step1").submit();
	    });
		
		function validate(evt) {
		  var theEvent = evt || window.event;

		  // Handle paste
		  if (theEvent.type === 'paste') {
			  key = event.clipboardData.getData('text/plain');
		  } else {
		  // Handle key press
			  var key = theEvent.keyCode || theEvent.which;
			  key = String.fromCharCode(key);
		  }
		  var regex = /[0-9]|\./;
		  if( !regex.test(key) ) {
			theEvent.returnValue = false;
			if(theEvent.preventDefault) theEvent.preventDefault();
		  }
		}
       </script>
        <script src="scripts/custom.js"></script>
		
		<c:if test="${msgtype != null}">
		 <script>
		 
			 var notification = 'Information';
			 $(function(){
				 new PNotify({
						 title: notification,
						 text: '${message}',
						 type: '${msgtype}',
						 width: '30%',
						 hide: false,
						 buttons: {
									closer: true,
									sticker: false
								 },
						 history: {
									history: false
							 }
					 });
			 }); 	 
      </script>
	</c:if>
    </body>
</html>
