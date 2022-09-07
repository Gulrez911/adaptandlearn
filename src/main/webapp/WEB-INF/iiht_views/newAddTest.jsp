<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<html>
   <head>
 		<title>eAssess</title>
      	<link href="images/E-assess_E.png" rel="shortcut icon">   
         <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link href="https://fonts.googleapis.com/new/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
                <link rel="stylesheet" href="new/css/font-awesome.min.css">
        <link rel="stylesheet" href="new/css/bootstrap.min.css">
        <link rel="stylesheet" href="admindashboard/css/style.css">
		
		 <link rel="stylesheet" href="new/css/style.css">
        <link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
    
        <link rel="stylesheet" href="admindashboard/css/font-awesome.min.css">
        <link rel="stylesheet" href="admindashboard/css/bootstrap.min.css">
        <link rel="stylesheet" href="admindashboard/css/style.css">
	
    
    </head>
 <body>
        <div class="master-container">
           <header>
                <div class="container-fluid">
                    <button class="nav-bar" type="button">
                        <i class="fa fa-bars"></i>
                    </button>
                    <a href="#" class="yaksha-logo my-auto">
                       E<span>ASSESS</span>
                    </a>
                    <nav class="">
                        <button class="nav-bar-close" type="button">
                            <i class="fa fa-close"></i>
                        </button>
                        <a href="dashboardnew">
                            Dashboard
                        </a>
                        <a href="questionssnew">
                            Question Bank
                        </a>
                        <a href="testsnew" class="active">
                            Tests
                        </a>
                        <a href="#" class="view-more">
                            More <i class="fa fa-angle-down"></i>
                        </a>
                        <div class="more-nav">
                            <a href="newSkills">
                                Skills
                            </a>
                            <a href="newShowClusters">
                                Cluster Management
                            </a>
                            <a href="newShowAllResultsforMFA">
                                Multi-file Test Results
                            </a>
                            <a href="newDomainReport">
                                Domain based Results
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Modules Management
                            </a>
                            <a href="newLicenses">
                                License Management
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Results
                            </a>
                            
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Skill based Reports
                            </a>
                            <a href="recomms">
                                Recomm Settings
                            </a>
                            <a href="newSingleFileSessions">
                                Single File Test Reports
                            </a>
                            <a href="newListTestLinks">
                                Test Links Management
                            </a>
                            <a href="newLmsAdmins">
                                LMS Admin Users
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Job Description Management
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Test Domain Attemps Mangement
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Schedule Management
                            </a>
                            <a href="newVerification">
                                Verify Data
                            </a>
                            <a href="newListTenants">
                                Tenant Management
                            </a>
                            <a href="newListUsers">
                                Users
                            </a>
							<a href="showCampaigns">
                                Campaigns
                            </a>
							<a href="reviewers">
                                 Campaign Reviewers
                            </a>
							<a href="showMeetings">
                                Meetings
                            </a>
							<a href="bookingSlot">
                                Booking Slot Manager
                            </a>
                        </div>
                    </nav>
                    <div class="user-info">
                        <span class="my-auto">EASSESS, Admin</span>
                        <div class="thumbnail my-auto">
                            <span><i class="fa fa-user-o"></i></span>
                        </div>
                        <button class="sign-out" type="button" onClick="javascript:signoff()">
                            <i class="fa fa-sign-out" ></i>
                        </button>
                    </div>
                </div>
            </header>
            <section class="main-section">
                <div class="container-fluid">
            
                        <div class="contents create-test">
                            <div class="row steps">
                                <div class="col-12 pb-5 mb-5">
                                    <ul class="form-steps">
                                        <li class="active">
                                            <div class="thumbnail">
                                                <i class="fa fa-cog"></i>
                                            </div>
                                            <div class="step-name">Set your Test</div>
                                        </li>
                                        <li>
                                            <div class="thumbnail">
                                                <i class="fa fa-list-ol"></i>
                                            </div>
                                            <div class="step-name">Add Questions</div>
                                        </li>
                                        <li>
                                            <div class="thumbnail">
                                                <i class="fa fa-users"></i>
                                            </div>
                                            <div class="step-name">Add Candidates</div>
                                        </li>
                                        <li>
                                            <div class="thumbnail">
                                                <i class="fa fa-send-o"></i>
                                            </div>
                                            <div class="step-name">Send Invitation</div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="col-12" id=" ">
                                         <form id="step1" method="POST" action="newSaveAndGoToStep2" modelAttribute="test">
                                    <div class="steps-form">
                                        <div class="row">
                                            <div class="col-12 mb-4">
                                                <div class="page-heading">
                                                    <h2>Create new Test</h2>
                                                </div>
                                            </div>
                                            <div class="col-12 mb-2">
                                                <div class="form-group">
                                                    <label for="">Test Title</label>
                                                    <form:input path="test.testName" name="testName" id="testName" required="true" placeholder="Test Name" disabled='${(test.id == null) ? "false":"true"}' class="form-control"/>
<!--                                                     <input type="text" class="form-control"> -->
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 mb-2">
                                                <div class="form-group">
                                                    <label for="">Category</label>
                                  					    <form:input path="test.qualifier1" name="qualifier1" id="qualifier1" required="true" placeholder="Choose Category" class="form-control"/>
                                                    
<!--                                                     <input type="text" class="form-control"> -->
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 mb-2">
                                                <div class="form-group">
                                                    <label for="">Sub Category</label>
                                                      <form:input path="test.qualifier2" name="qualifier2" id="qualifier2"  placeholder="Choose Sub Category" class="form-control"/>
<!--                                                     <input type="text" class="form-control"> -->
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 mb-2">
                                                <div class="form-group">
                                                    <label for="">Category of Sub Category</label>
                                                    
                                                        <form:input path="test.qualifier3" name="qualifier3" id="qualifier3"  placeholder="Choose Category of a Sub Category" class="form-control"/>
<!--                                                     <input type="text" class="form-control"> -->
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-2 mb-2 my-auto">
                                                <div class="checkbox">
                                                    <label for="">Fullstack Test</label>
                                                     <form:checkbox path="test.fullStackTest" id="fullStackTest"/>
<!--                                                     <input type="checkbox"> -->
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-2 mb-2 my-auto">
                                                <div class="checkbox">
                                                    <label for="">Shuffle Test</label>
                                                     <form:checkbox path="test.randomQuestions" id="random"/>
<!--                                                     <input type="checkbox"> -->
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 md-2">
                                                <div class="form-group">
                                                    <label for="">No. of Attempts</label>
                                                    <form:input path="test.noOfConfigurableAttempts" name="noOfConfigurableAttempts" id="noOfConfigurableAttempts"  style="width: 25%;" value="" onkeypress='validate(event)' class="form-control"/>
                                                    
<!--                                                     <input type="number" class="form-control"> -->
                                                </div>
                                            </div>
											<div class="col-xs-12 col-md-4 md-2">
                                                <div class="form-group">
                                                    <label for="">Image URL for Test</label>
                                                    <form:input path="test.imageUrl" name="imageUrl" id="imageUrl"  style="width: 100%;" value=""  class="form-control"/>
                                                    

                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 mb-2">
                                                <div class="form-group">
                                                    <label for="">Select Test Type</label>
                                                   <form:select path="test.testType" class="form-control">
												<form:options items="${testTypes}" />
											</form:select>
                                                   
<!--                                                     <select class="form-control"> -->
<!--                                                         <option>General Knowledge</option> -->
<!--                                                         <option>Angular</option> -->
<!--                                                         <option>Php</option> -->
<!--                                                         <option>Java</option> -->
<!--                                                         <option>Node</option> -->
<!--                                                     </select> -->
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-8 mb-2">
                                                <div class="form-group">
                                                    <label for="">Select Skills</label>
                                                    
                                                     <form:select path="test.skls" multiple="multiple" class="form-control" style="height: 194px;">
												<form:options items="${skls}" itemValue="id" itemLabel="label" />
											 </form:select>
                                                    
                                                    <!-- <select multiple class="form-control" style="height: 194px;">
                                                        <option>JavaScript - Intermediate</option>
                                                        <option>Angular - Basic</option>
                                                        <option>Php - Intermediate</option>
                                                        <option>Java - Expert</option>
                                                        <option>Node - Advanced</option>
                                                        <option>Angular - Basic</option>
                                                        <option>Php - Intermediate</option>
                                                        <option>Java - Expert</option>
                                                        <option>Node - Advanced</option>
                                                    </select> -->
                                                </div>
                                            </div>
                                            
                                            
                                            <div class="col-xs-12 col-md-4 mb-2">
                                            <div class="form-group mb-3">
                                             <label for="">Select Test Language</label>
                                                   <form:select path="test.testLanguage" class="form-control">
										<form:options items="${langs}" />
<!-- 											<option value="eng">English</option> -->
<!-- 											<option value="arabic">Arabic</option> -->
											</form:select> 
											  </div>
                                            
                                                <div class="form-group mb-3">
                                                    <label for="">Duration</label>
                                                    <form:input path="test.testTimeInMinutes" name="testTimeInMinutes" id="testTimeInMinutes"    placeholder="Enter time in minutes" class="form-control" required="true"/>
<!--                                                     <input type="text" class="form-control" placeholder="Enter time in minutes"> -->
                                                </div>
                                                <div class="form-group mb-3">
                                                    <label for="">Pass Percentage</label>
                                                    <form:input path="test.passPercent" name="passPercent" id="passPercent"    value=""  class="form-control"/>
<!--                                                     <input type="text" class="form-control" placeholder=""> -->
                                                </div>
                                                <div class="form-group mb-2">
                                                    <label for="">Conducted By</label>
                                                    <form:input path="user.email" name="email" id="email"   value=""  disabled="true" class="form-control"/>
<!--                                                     <input type="email" class="form-control" placeholder="name@email.com" disabled > -->
                                                </div>
                                            </div>
                                            <div class="col-12 mb-2 mt-4 text-center">
                                                <p>Notification & Messages</p>
                                            </div>
                                            <div class="col-xs-12 col-md-6 mb-2">
                                                <div class="form-group">
                                                    <label for="">Users see this text just before they begin the exam</label>
                                                      <form:textarea path="test.intro" class="form-control"/>
<!--                                                     <textarea class="form-control"></textarea> -->
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-6 mb-2">
                                                <div class="form-group">
                                                    <label for="">Shown to the user if they pass the exam. A copy is also sent via email</label>
                                                     <form:textarea path="test.postTestCompletionText" class="form-control"/>
<!--                                                     <textarea class="form-control"></textarea> -->
                                                </div>
                                            </div>
                                            <div class="col-12 mt-4">
                                                <p class="mb-4 text-center">Displayed upon exam completion and email communications relating to the exam</p>
                                                <div class="form-check">
                                                   <form:checkbox path="test.sentToStudent" class="form-check-input"/>
                                                    <label class="form-check-label">
<!--                                                         Email a copy of the student's results to above contact -->
                                                           Send Result to Student's Email
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                     <form:checkbox path="test.sendRecommReport" id="sendRecommReport" class="form-check-input"/>
<!--                                                     <input class="form-check-input" type="checkbox" value=""> -->
                                                    <label class="form-check-label">
                                                        Share Recommendation Engine by Email
                                                    </label>
                                                </div>
                                                
                                                  
                                                <div class="form-check">
                                                 <form:checkbox path="test.justification" id="justification" class="form-check-input"/>
<!--                                                     <input class="form-check-input" type="checkbox" value=""> -->
                                                    <label class="form-check-label">
                                                        Display Justification for Answers
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                        					   <form:checkbox path="test.considerConfidence" id="considerConfidence" class="form-check-input"/>
<!--                                                     <input class="form-check-input" type="checkbox" value=""> -->
                                                    <label class="form-check-label">
                                                        Confidence Based Assessment Flag
                                                    </label>
                                                </div>
                                                
                                                <div class="form-check">
                                        					   <form:checkbox path="test.leoForce" id="leoForce" class="form-check-input"/>
<!--                                                     <input class="form-check-input" type="checkbox" value=""> -->
                                                    <label class="form-check-label">
                                                        Is the test for Leoforce?
                                                    </label>
                                                </div>
                                                
												
												<div class="form-check">
                                        					   <form:checkbox path="test.newUi" id="newUi" class="form-check-input"/>
<!--                                                     <input class="form-check-input" type="checkbox" value=""> -->
                                                    <label class="form-check-label">
                                                        Is the test based on new UI?
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                        					   <form:checkbox path="test.displayResultToStudent" id="newUi" class="form-check-input"/>
<!--                                                     <input class="form-check-input" type="checkbox" value=""> -->
                                                    <label class="form-check-label">
                                                       Display result to student
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                        					   <form:checkbox path="test.sendResultToAny" id="sendResultToAny" class="form-check-input"/>
<!--                                                     <input class="form-check-input" type="checkbox" value=""> -->
                                                    <label class="form-check-label">
                                                    Send Result to 
                                                    </label>
                                                </div>
                                                <div class="form-group mb-3">
                                                <c:choose>
  													<c:when test="${test.sendResultToAny =='true'}">
  													<form:input path="test.sendResultToEmails" name="sendResultToEmails" id="sendResultToEmails"    class="form-control" />
                                                </c:when>
                                                 <c:otherwise>
                                                 <form:input path="test.sendResultToEmails" name="sendResultToEmails" id="sendResultToEmails"    class="form-control"  disabled="true"/>
                                                 </c:otherwise>
                                                </c:choose>
                                                    
<!--                                                     <input type="text" class="form-control" placeholder="Enter time in minutes"> -->
                                                </div>
                                                
                                                
                                                <p class="mt-3">Protocol Setting</p>
                                                <div class="form-check">
                                        					   <form:checkbox path="test.webProctoring" id="webProctoring" class="form-check-input"/>
                                                    <label class="form-check-label">
                                                     Allow webcam proctoring 
                                                    </label>
                                                </div>
<!--                                                 <div class="form-check"> -->
<!--                                                     <input class="form-check-input" type="checkbox" value="" checked disabled> -->
<!--                                                     <label class="form-check-label"> -->
<!--                                                         Allow webcam proctoring -->
<!--                                                     </label> -->
<!--                                                 </div> -->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 mt-5">
                                    <div class="steps-form">
                                        <div class="form-actions">
                                            <span>
                                                <button class="btn btn-outline-secondary mr-3" type="button" onclick="window.location.href = 'testsnew'">
                                                    Cancel
                                                </button>
                                                 
                                            </span>
                                            <button class="btn btn-primary next" type="button" id="next" >
                                                Next
                                            </button>
                                              
                                        </div>
                                    </div>
                                </div>
                                    </form>
                                </div>
                                 
                                 
                                 
                                 
                            </div>
                        </div>
                  
                </div>
            </section>
            <footer>
                <div class="container-fluid">
                    <span>
                        &copy; copyright 2020-21
                    </span>
                    <span>
                        <a href="#">
                            terms and conditions
                        </a>
                        <a href="#">
                            privacy policy
                        </a>
                    </span>
                </div>
            </footer>
        </div>
        <script src="new/js/jquery.min.js"></script>
        <script src="new/js/bootstrap.min.js"></script>
<!--         <script src="new/js/scripts.js"></script> -->
        	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
             <script>
             $('button.nav-bar, button.nav-bar-close').on('click', function () {
                 $(this).closest('.container-fluid').find('nav').toggleClass('open');
                 $('.drop-down-bg').toggleClass('open');
             });
             $('a.view-more').on('click', function () {
                 var angle = $(this).find('.fa');
                 if(angle.hasClass('fa-angle-down')) {
                     $(this).closest('nav').find('.more-nav').addClass('open');
                     $('.drop-down-bg').addClass('open');
                     angle.removeClass('fa-angle-down').addClass('fa-angle-up');
                 } else {
                     $('.drop-down-bg').removeClass('open');
                     $(this).closest('nav').find('.more-nav').removeClass('open');
                     angle.addClass('fa-angle-down').removeClass('fa-angle-up');
                 }
             });
             $('.drop-down-bg').on('click', function(){
                 $(this).closest('body').find('.open').removeClass('open');
                 if($('a.view-more i.fa').hasClass('fa-angle-up')) {
                     $('a.view-more i.fa').addClass('fa-angle-down').removeClass('fa-angle-up');
                 }
             });
             $('.admin-view').on('click', function(){
                 var options = $(this).closest('.actions').find('.options');
                 if(options.hasClass('open')) {
                     options.removeClass('open');
                 } else {
                     options.addClass('open');
                 }
             });
             
             
             $('#sendResultToAny').click(function() {
                 if($(this).is(":checked")) {
                	 $('#sendResultToEmails').prop('disabled', false);                	 
                 }else{
//                 	 $('#sendResultToEmails').val("");             
                	 $('#sendResultToEmails').prop('disabled', true);                	 
                 }
             });
             
	 $('#next').on('click',function(){
		 
		 var testName=$("#testName").val();
		 if(testName==="") {
			 sweetAlert("Warning","Test Name cannot be empty","error");
			 return
			}
		 
		 var testTimeInMinutes=$("#testTimeInMinutes").val();
		 if(testTimeInMinutes==""){
			 sweetAlert("Warning","Duration can't be empty","error");
			 return
			 }
		 
		 if(document.getElementById('sendResultToAny').checked) {
			 var email=$('#sendResultToEmails').val();
			 if(email===""){
			 sweetAlert("Warning","Send Results Email is empty","error");
			 return
				 
			 }
			}
		
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

		 function sweetAlert(msgtype,message,icon){
			  Swal.fire(
				       msgtype,
				       message,
				       icon
				    )
			}
       </script>
        <script src="scripts/custom.js"></script>
		
			<c:if test="${msgtype != null}">
		<script>
			var notification = 'Information';
			$(function() {
				    Swal.fire(
			       '${msgtype}',
			       '${message}',
			       '${icon}'
			    )
			});
		</script>
	</c:if>
 
    </body>
</html>