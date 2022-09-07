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
	
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
    
        <link rel="stylesheet" href="admindashboard/css/font-awesome.min.css">
        <link rel="stylesheet" href="admindashboard/css/bootstrap.min.css">
        <link rel="stylesheet" href="admindashboard/css/style.css">
    <!--  -->
    
    
    
				<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
				<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
				
				<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
				
				<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
				
				<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">

        <style type="text/css">
        .mybtn{
            padding: 6px 11px;
        }
         </style>
         
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
            <%
					Test test = (Test) request.getSession().getAttribute("test");
			%>
             <section class="main-section">
                <div class="container-fluid">
                        <div class="contents create-test">
                            <div class="row steps">
                                <div class="col-12 pb-5 mb-5">
                                    <ul class="form-steps">
                                        <li class="completed">
                                            <div class="thumbnail">
                                                <i class="fa fa-cog"></i>
                                            </div>
                                            <div class="step-name">Set your Test</div>
                                        </li>
                                        <li class="completed">
                                            <div class="thumbnail">
                                                <i class="fa fa-list-ol"></i>
                                            </div>
                                            <div class="step-name">Add Questions</div>
                                        </li>
                                        <li class="completed">
                                            <div class="thumbnail">
                                                <i class="fa fa-users"></i>
                                            </div>
                                            <div class="step-name">Add Candidates</div>
                                        </li>
                                        <li class="active">
                                            <div class="thumbnail">
                                                <i class="fa fa-send-o"></i>
                                            </div>
                                            <div class="step-name">Send Invitation</div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="col-12 " id="step-4">
                                    <div class="steps-form">
                                        <div class="row">
                                            <div class="col-12 mb-4">
                                                <div class="page-heading">
                                                    <h2>Send Invitation</h2>
                                                </div>
                                            </div>
                                            <div class="col-12 mb-3">
                                                <h3 class="mb-3">Test : <%= test.getTestName() %></h3>
                                                <div id="accordion" class="mb-3 test-overview">
                                                    <c:forEach var="section" items="${test.sectionDtos}">
																		<%
																			int counter = 1;
																		%>
																		<div class="panel panel-default">
																				<div class="panel-heading">
																						<h3 class="panel-title">
																								<a class="collapsed" data-toggle="collapse" data-parent="#accordion-one"
																										href="#${section.sectionId}" aria-expanded="false">${section.sectionName}</a>
																						</h3>
																				</div>

																				<%
																					int count = 1;
																				%>
																				<div id="${section.sectionId}" class="panel-collapse collapse" aria-expanded="false"
																						style="height: 0px;">

																						<c:forEach var="ques" varStatus="status" items="${section.questions}">
																								<div class="panel-body qus">
																										<h3>
																												<span><%=count%></span>&nbsp;&nbsp;${ques.questionText}
																										</h3>
																										<h4
																												style="${ques.choice1 == null || ques.choice1.length() == 0? 'display: none;':''}">Choice
																												1: &nbsp;&nbsp; ${ques.choice1}</h4>
																										<h4
																												style="${ques.choice2 == null || ques.choice2.length() == 0? 'display: none;':''}">Choice
																												2: &nbsp;&nbsp; ${ques.choice2}</h4>
																										<h4
																												style="${ques.choice3 == null || ques.choice3.length() == 0? 'display: none;':''}">Choice
																												3: &nbsp;&nbsp; ${ques.choice3}</h4>
																										<h4
																												style="${ques.choice4 == null || ques.choice4.trim().length() == 0? 'display: none;':''}">Choice
																												4: &nbsp;&nbsp; ${ques.choice4}</h4>
																										<h4
																												style="${ques.choice5 == null || ques.choice5.trim().length() == 0? 'display: none;':''}">Choice
																												5: &nbsp;&nbsp; ${ques.choice5}</h4>
																										<h4
																												style="${ques.choice6 == null || ques.choice6.length() == 0? 'display: none;':''}">Choice
																												6: &nbsp;&nbsp; ${ques.choice6}</h4>
																								</div>

																								<%
																									count++;
																								%>
																						</c:forEach>

																				</div>
																		</div>
																		<%
																			counter++;
																		%>
																</c:forEach>
                                                     
                                                     
                                                </div>
                                                <p>Category: <strong>${test.category}</strong></p>
<!--                                                 <p>Skills: <span class="tags mr-2">Skill Name</span><span class="tags mr-2">Skill Name</span><span class="tags mr-2">Skill Name</span><span class="tags mr-2">Skill Name</span></p> -->
                                                <p>Duartion: <strong>${test.testTimeInMinutes} Mins</strong> <span class="ml-4">Attemps: <strong>${test.noOfConfigurableAttempts}</strong></span></p>
                                                <p>Invited Candidates: <strong>${invitedusers}</strong></p>
                                            </div>
                                            <div class="col-xs-12 col-md-6">
                                                <div class="form-group">
                                                    
                                                    <div class='input-group date' id='datetimepicker1'>
																		<input type='text' class="form-control" id="startDate" /> <span class="input-group-addon">
																				<span class="glyphicon glyphicon-calendar"></span>
																		</span>
																</div>
                                                    
                                                    
<!--                                                     <input type="date" class="form-control"> -->
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-6">
                                                <div class="form-group">
                                                <div class='input-group date' id='datetimepicker2'>
																		<input type='text' class="form-control" id="endDate" /> <span class="input-group-addon">
																				<span class="glyphicon glyphicon-calendar"></span>
																		</span>
																</div>
<!--                                                     <label for="">Link End Date and Time</label> -->
<!--                                                     <input type="date" class="form-control"> -->
                                                </div>
                                            </div>
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
                                                <button class="btn btn-secondary back" type="button" onclick="window.location.href = 'newAddteststep3'">
                                                    Back
                                                </button>
                                            </span>
                                            <button class="btn btn-primary" type="button" onclick="shareTests()">
                                               Send Invitation
                                            </button>
                                             
                                        </div>
                                    </div>
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
    
<!-- 		       <script src="new/js/jquery.min.js"></script> -->
<!--         <script src="new/js/bootstrap.min.js"></script> -->
<!--         <script src="new/js/scripts.js"></script> -->
<!--                	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script> -->
<!-- 	<script type="text/javascript" src="scripts/custom.js"></script>> -->
	  
	 <script type="text/javascript">
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
			
			
         $('#datetimepicker1').datetimepicker({
				format: 'DD/MM/YYYY hh:mm a'
				//minDate:new Date()
				
			});
         
			
			
          $('#datetimepicker2').datetimepicker({
				format: 'DD/MM/YYYY hh:mm a'
				
			});
//      });

	 
	 function shareTests(){
		 console.log('in sharetests');
		var startDate = document.getElementById("startDate").value; 
		console.log('in sharetests '+startDate);
		var endDate = document.getElementById("endDate").value; 
		if(startDate == null || startDate == ''){
			sweetAlert('Information', 'Start Date and Time not present for the Test Link to be shared with the Candidate(s)',"error");
			return;
		}
		
		if(endDate == null || startDate == ''){
			sweetAlert('Information', 'End Date and Time not present for the Test Link to be shared with the Candidate(s)',"error");
			return;
		}
		startDate = encodeURI(startDate);
		endDate = encodeURI(endDate);
		window.location = "newShareTestWithUsers?startDate="+startDate+"&endDate="+endDate;
	 }
	 
	 function notify(messageType, message){
		 var notification = 'Information';
			 $(function(){
				 new PNotify({
				 title: notification,
				 text: message,
				 type: messageType,
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
		}

	 function sweetAlert(msgtype,message,icon){
		  Swal.fire(
			       msgtype,
			       message,
			       icon
			    )
		}
      </script>
	  
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