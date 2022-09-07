<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*,com.assessment.web.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>eAssess</title>
      <link href="images/E-assess_E.png" rel="shortcut icon">
<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,700'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Muli:300,400,700'
	rel='stylesheet' type='text/css'>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">
<link href="css/font-awesome_new.css" rel="stylesheet" type="text/css">
<link href="css/style_new.css" rel="stylesheet" type="text/css">
<link href="css/responsive_new.css" rel="stylesheet" type="text/css">
<link href="css/style_testjourney.css" rel="stylesheet" type="text/css">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="scripts/custom.js"></script>
<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>

<script type="text/javascript" src="scripts/pnotify.nonblock.js"></script>
<script type="text/javascript" src="scripts/pnotify.buttons.js"></script>

<link href="css/pnotify.custom.min.css" media="all" rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/html2canvas.js"></script>
<script src="scripts/src-min-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>

<style>
.answers label label label:last-child{
      position: relative !important;
    left: -15px !important;
    padding-left: 18px !important;
}
	  input {
      border-top-style: hidden;
      border-right-style: hidden;
      border-left-style: hidden;
      border-bottom-style: dashed;
      background-color: #eee;
	  border-width: 0 0 4px;
	  border-color: blue
      }
	  input:focus {
	   border-color: green
	  }
      .no-outline:focus {
      outline: 0;
      }
	  
	  
	.question ul li{
		padding: 5px;
		list-style: none;
		color: white;
		margin: 10px;
		cursor: pointer;
	}
	.answer ul li{
		padding: 5px;
		list-style: none;
		color: white;
		margin: 10px;
		cursor: pointer;
		background-color: grey;
	}
	.question #q1{
		background-color: red;
	}
	.question #q2{
		background-color: green;
	}
	.question #q3{
		background-color: blue;
	}
	.question #q4{
		background-color: pink;
	}
	.question #q5{
		background-color: #eb1587;
	}
	.question #q6{
		background-color: #bafc35;
	}
</style>

<script type="text/javascript">
               // PNotify.prototype.options.styling = "fontawesome";
		var correctNumberOfChoices = ('${currentQuestion.questionMapperInstance.questionMapper.question.rightChoices}'.match(/-/g) || []).length  + 1;
		
		
		var active = 'true';
		var studentNameTaken = localStorage.getItem('${studentTestForm.firstName}${studentTestForm.lastName}');
		var testNameTaken = localStorage.getItem('testName-${studentTestForm.firstName}${studentTestForm.lastName}');
		var tc= localStorage.getItem('timeCounter-${studentTestForm.firstName}${studentTestForm.lastName}');
			
		var firsttime = '${firstpage}';
		console.log('firsttime '+firsttime);
			if(firsttime != null && firsttime == 'yes'){
				console.log('timeCounter getting');	
				timeCounter = ${timeCounter};
			}
			else{
				if(studentNameTaken == 'yes' && testNameTaken == '${studentTestForm.firstName}${studentTestForm.lastName}-${studentTestForm.testName}'  && tc != null){
				timeCounter=tc;
				}
				else{
					timeCounter = 0;
				}
				
				if(tc == null){
					timeCounter= 0;
				}
			
			}
			
			/***/
		
		
			if(timeCounter == null){
				timeCounter = 0;
			}
		console.log('timeCounter getting '+timeCounter);	
		console.log('timeCounter is '+timeCounter);
			
		function setTimeOnLoad(){
		timeProcess();
		}	
		
		function timeProcess(){
		if(timeCounter == null){
				timeCounter = 0;
		}
		timeCounter = parseInt(timeCounter) + 1;
		var end = new Date();
		var hours =  (${studentTestForm.duration}/60) % 60;
		var minutes = (${studentTestForm.duration}) % 60;
		var seconds = (${studentTestForm.duration} * 60) % 60;
		
		end.setMinutes(minutes);
		end.setHours(hours);
		end.setSeconds(seconds);
		
		var start = new Date();
		start.setMinutes((timeCounter/60) % 60);
		start.setHours((timeCounter/(60*60)) % 60);
		start.setSeconds(timeCounter % 60);
		
		var t = Date.parse(end) - Date.parse(start);
		seconds = Math.floor( (t/1000) % 60 );
		minutes = Math.floor( (t/1000/60) % 60 );
		hours = Math.floor( (t/(1000*60*60)) % 24 );
		
		 if (hours   < 10) {hours   = "0"+hours;}
		 
		  if (minutes < 10) {minutes = "0"+minutes;}
		  
		   if (seconds < 10) {seconds = "0"+seconds;}
		
		//document.getElementById("examTimer").innerHTML = hours+":"+minutes+":"+seconds;
		document.getElementById("hours").innerHTML = hours;
		document.getElementById("min").innerHTML = minutes;
		document.getElementById("sec").innerHTML = seconds;

		}
		 	
		var submitTest = 'false';
		
		function examTimer(){
			/**
			send notiication 10 mins before test
			**/
			sendNotification10MinBeforeTime();
			/**
			End 
			**/
			
			if(submitTest == 'true'){
				return;
			}
			timeProcess();
		
			if((${studentTestForm.duration} * 60) - timeCounter <= 3 ){
				notify('Info', 'Test Time exceeding shortly! Your test will be auto submitted. DO NOT REFRESH THE PAGE.');
			}		
				
			if( timeCounter >= (${studentTestForm.duration} * 60)  ){
			submitTest();
			}
		}
		
		var notificationsend = false;
		
		function sendNotification10MinBeforeTime(){
			if(!notificationsend){
				if((${studentTestForm.duration} * 60) - timeCounter <= 600 ){
					tme = (${studentTestForm.duration} * 60) - timeCounter;
					mnts =(tme/60) % 60;
					notify('Info', 'You have about '+Math.round(mnts)+' minutes to complete the test');
					notificationsend = true;
				}
			}
			
		}
		
		
		function updateTimeInBackEnd(){
			<%
			Test test = (Test) request.getSession().getAttribute("test");
			%>
			var testid = '<%= test.getId() %>';
			var cid = '${studentTestForm.companyId}';
			var emailid = '${studentTestForm.emailId}';
			 var url = "timecounterUpdate?timecounter="+timeCounter+"&testId="+testid+"&email="+emailid+"&companyId="+cid;
			console.log('here url '+url);
			$.ajax({
				url : url,
				type: "POST",
				success : function(data) {
				console.log(' updateTimeInBackEnd ret '+data);
					
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				}
			});
		}
		
		//var noncompCount = 0;
		
		
		
		
		function takeScreenShot(){
			 if(active == 'false'){
				//noncompCount ++;
				
				<!--notify('Info', 'The exam window looks to be in the background. This is a non-compliance. We are recording it in our system. If non-'+ -->

	<!-- 'compliances count exceeds 3, this test will auto-submit. Please beware! '); -->
	notify('Info', 'The exam window appears to be in the background. This is a non-compliance. We are recording it in our system and this might impact your score.');
				var datasend="user=${studentTestForm.emailId}\ntestName=${studentTestForm.testName}\ncompanyId=${studentTestForm.companyId}";
				$.ajax({
				    type: "POST",
				    url: "registerNonCompliance",
				    data: { 
					data:datasend
				    }
				}).done(function(fileName) {
					//alert("done");

				}); 
				
				
				//submitTest();
				//$.ajax({
				//    type: "POST",
				//    url: "getNonComplianceCount",
				//    data: { 
				//	data:datasend
				//    }
				//}).done(function(data) {
					//alert("done");
				//	console.log('no of non-compliances '+data);
						//if(data > 2){
						//	console.log('submiting because of many non-compliances');
						//	submitTest();
							
						//}
					
				//}); 
				
				
			}
			else if(active == 'true'){
				/* this.window.focus();
				 html2canvas(document.querySelector("#screenShotId"), {
				logging: true,
				allowTaint: true
			    }).then(function(canvas) {
				var dataImage = canvas.toDataURL("image/png");
				var testname = encodeURIComponent('${studentTestForm.testName}');
				$.ajax({
				    type: "POST",
				    url: "uploadScreenSnapShot?testName="+testname,
				    data: { 
					data:dataImage
				    }
				}).done(function(fileName) {
					alert("done");

				}); 
			    }); */
			} 
		
		
		}
		
		function activeScreen(){
		active = 'true';
		//alert(' active ' +active);
		}
		
		function passiveScreen(){
		active = 'false';
		//alert(' passicve ' +active);
		}
		
		
		window.addEventListener('focus', activeScreen);
		window.addEventListener('blur', passiveScreen);

		
		var myVar = setInterval(examTimer, 1000);
		var myVar2 = setInterval(takeScreenShot, 1000);
		
		var myVar3 = setInterval(updateTimeInBackEnd, 45000);
		
		</script>
		
		<style>
		body * { font-family: monospace !important }
		</style>
		
		
</head>
<body id="bodyid" onload="setTimeOnLoad();">



	<form:form id="testForm" name="testForm" method="POST"
		modelAttribute="currentQuestion">
		<div class="header">
				<div class="col-md-6">
					<div class="logo">
						<a href="#"><img src="images/logoiiht.png"></a>
					</div>
				</div>
				<div class="col-md-6">
					<div class="userheader">
						<div class="userinfo">
							<h4>
								Welcome ${studentTestForm.userName}<span>${studentTestForm.emailId}</span>
							</h4>
							<!-- <img src="images/userimg.png"> -->
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="testheader"  id="screenShotId">
			<div class="testinfo">
				<div class="testimg">
					<img src="images/testimage.png">
				</div>
				<div class="testname">
					<h3>${studentTestForm.testName}</h3>
					<span>Assessment</span>
				</div>
			</div>
			<div class="durationinfo">
				<span>Timer - <!-- <i class="fa fa-clock-o"> -->
				<span class="time" id="timer"><i id="hours"></i><sub>h</sub><i id="min"></i><sub> min</sub><i id="sec"></i> <sub> sec</sub></span> </i>
				   <!-- <c:choose>
						 <c:when test="${currentSection.last==true}">
						<span class="finish"><a href="javascript:submitTest();"><i></i>END</a></span>
						 </c:when>    
				    <c:otherwise>
				   		 <span class="finish">
						<a href="javascript:next();"><iclass="fa fa-flag-checkered"></i>FINISH</a></span>
				    </c:otherwise>
						</c:choose>
						-->
			</div>
		</div>


		<div class="examquestions">
			<div class="menuitem">
				<ul>
				<c:forEach var="sectionInstance" varStatus="status"
					items="${sectionInstanceDtos}">
					<li ${sectionInstance.style}
						onclick="javascript:changeSection('${sectionInstance.section.sectionName}');">

						<a>${sectionInstance.section.sectionName}</a></li>

				</c:forEach>
				</ul>
			</div>
			
			<c:choose>
					    <c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='MCQ' || currentQuestion.questionMapperInstance.questionMapper.question.type ==null}">
						
						<div class="queanscenter" id="section1_content">
							<div class="queprogress">
								<span style="float: left; width: 100%;">${noAnswered} of ${totalQuestions} answered</span>
								<div class="progressing">
									<span style="width: ${percentage}%;"></span>
								</div>
								<span class="quepercent">${percentage}%</span>
							</div>

							<div class="questionname">
								<div class="verticalline"></div>
								<div class="queno">
									<span>${currentQuestion.position}</span>
								</div>
								<h3 class="qname">${currentQuestion.questionMapperInstance.questionMapper.question.questionText}</h3>
								&nbsp; &nbsp; &nbsp;   <c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl != null && currentQuestion.questionMapperInstance.questionMapper.question.imageUrl.trim().length() > 0}">
								<img src="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl}" height="400" width="500">
							  </c:if>
							  
							  <c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.audioURL != null && currentQuestion.questionMapperInstance.questionMapper.question.audioURL.trim().length() > 0}">
							   &nbsp; &nbsp; &nbsp;  <audio controls src="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL}">
										Your browser does not support the
										<code>audio</code> element.
								</audio>
								
							  </c:if>
							  
							  <c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.videoURL != null && currentQuestion.questionMapperInstance.questionMapper.question.videoURL.trim().length() > 0}">
								&nbsp; &nbsp; &nbsp; <video width="400" height="300" controls>
									  <source src="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL}" >
									 
									  Your browser does not support the video tag.
									</video>
							  </c:if>
								
								<div class="answers">
								
									<ul>
										<li
											style="${currentQuestion.questionMapperInstance.questionMapper.question.choice1 == null || 

			currentQuestion.questionMapperInstance.questionMapper.question.choice1.trim().length() == 0? 'display: none;':'clear:left; font-size: 17px;'}">
										<%
										QuestionInstanceDto currentQuestion = (QuestionInstanceDto) request.getAttribute("currentQuestion");
										System.out.println(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getRightChoices());
							int cc = currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getRightChoices().split("-").length;
									if(cc == 1){
										System.out.println(" cc is "+cc);
										%>
											<form:radiobutton path="radioAnswer" id="one" value="one" />
											
<label for="one" style="float:right;width:400px;padding: 0px 4px;position: relative;font-size: 13px;font-weight: 400;text-align: left;margin-right: 50px;left:-17px;padding-left:20px;">${currentQuestion.questionMapperInstance.questionMapper.question.choice1}</label>
											<%
									}
									else {
											%>
											
											<form:checkbox path="one" />
											${currentQuestion.questionMapperInstance.questionMapper.question.choice1}
											<%
									}
											%>
										</li>
										<li
											style="${currentQuestion.questionMapperInstance.questionMapper.question.choice2 == null || 

			currentQuestion.questionMapperInstance.questionMapper.question.choice2.trim().length() == 0? 'display: none;':'clear:left;font-size: 17px;'}">
										<%
										if(cc == 1){
										%>
										<form:radiobutton  id="two" path="radioAnswer" value="two" />
<label for="two" style="float:right;width:400px;padding: 0px 4px;position: relative;font-size: 13px;font-weight: 400;text-align: left;margin-right: 50px;;left:-17px;padding-left:20px;">${currentQuestion.questionMapperInstance.questionMapper.question.choice2}</label>
										<%
										}
										else {
										%>
										
											<form:checkbox path="two" />
											${currentQuestion.questionMapperInstance.questionMapper.question.choice2}
										<%
										}
										%>
										</li>
										<li
											style="${currentQuestion.questionMapperInstance.questionMapper.question.choice3 == null || 

			currentQuestion.questionMapperInstance.questionMapper.question.choice3.trim().length() == 0? 'display: none;':'clear:left;font-size: 17px;'}">
										<%
										if(cc == 1){
										%>
										<form:radiobutton path="radioAnswer" value="three" id="three" />
<label for="three" style="float:right;width:400px;padding: 0px 4px;position: relative;font-size: 13px;font-weight: 400;text-align: left;margin-right: 50px;;left:-17px;padding-left:20px;">${currentQuestion.questionMapperInstance.questionMapper.question.choice3}</label>
										<%
										}
										else {
										%>
										
											<form:checkbox path="three" />
											${currentQuestion.questionMapperInstance.questionMapper.question.choice3}
										<%
										}
										%>
										</li>

										<li
											style="${currentQuestion.questionMapperInstance.questionMapper.question.choice4 == null || 

			currentQuestion.questionMapperInstance.questionMapper.question.choice4.trim().length() == 0? 'display: none;':'clear:left;font-size: 17px;'}">
										<%
										if(cc == 1){
										%>
										<form:radiobutton path="radioAnswer" value="four" id="four" />
<label for="four" style="float:right;width:400px;padding: 0px 4px;position: relative;font-size: 13px;font-weight: 400;text-align: left;margin-right: 50px;;left:-17px;padding-left:20px;">${currentQuestion.questionMapperInstance.questionMapper.question.choice4}</label>
										<%
										}
										else {
										%>
										
											<form:checkbox path="four" />
											${currentQuestion.questionMapperInstance.questionMapper.question.choice4}
										<%
										}
										%>
										</li>
										<li
											style="${currentQuestion.questionMapperInstance.questionMapper.question.choice5 == null || 

			currentQuestion.questionMapperInstance.questionMapper.question.choice5.trim().length() == 0? 'display: none;':'clear:left;font-size: 17px;'}">
										<%
										if(cc == 1){
										%>
										<form:radiobutton path="radioAnswer" value="five" id="five"/>
<label for="five" style="float:right;width:400px;padding: 0px 4px;position: relative;font-size: 13px;font-weight: 400;text-align: left;margin-right: 50px;;left:-17px;padding-left:20px;">${currentQuestion.questionMapperInstance.questionMapper.question.choice5}</label>
										<%
										}
										else {
										%>
										
											<form:checkbox path="five" />
											${currentQuestion.questionMapperInstance.questionMapper.question.choice5}
										<%
										}
										%>
										</li>
										<li
											style="${currentQuestion.questionMapperInstance.questionMapper.question.choice6 == null || 

			currentQuestion.questionMapperInstance.questionMapper.question.choice6.trim().length() == 0? 'display: none;':'clear:left;font-size: 17px;'}">
										<%
										if(cc == 1){
										%>
										<form:radiobutton path="radioAnswer" value="six" id="six"/>
<label for="six" style="float:right;width:400px;padding: 0px 4px;position: relative;font-size: 13px;font-weight: 400;text-align: left;margin-right: 50px;;left:-17px;padding-left:20px;">${currentQuestion.questionMapperInstance.questionMapper.question.choice6}</label>
										<%
										}
										else {
										%>
										
											<form:checkbox path="six" />
											${currentQuestion.questionMapperInstance.questionMapper.question.choice6}
										<%
										}
										%>
										</li>
									</ul>
								</div>
								
								
								
								
								
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								
								<c:if test = "${confidenceFlag != null && confidenceFlag == true}">
								
								&nbsp; &nbsp; &nbsp; <div class="answers">
								<label style="clear:left;font-size: 17px;color:red"><br/> Are you confident of your answer? <label>
								<form:radiobutton path = "confidence" value = "yes" label = "Yes"  style="width: 15px;border-radius: 15px;visibility: visible;position: relative;opacity:1"/>
								<form:radiobutton path = "confidence" value = "no" label = "No" style="width: 15px;border-radius: 15px;visibility: visible;position: relative;opacity:1" />
									
							  </c:if>

								

							</div>
						
					    </c:when>    
						<c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='FILL_BLANKS_MCQ'}">
						
						<div class="queanscenter" id="section1_content">
							<div class="queprogress">
								<span style="float: left; width: 100%;">${noAnswered} of ${totalQuestions} answered</span>
								<div class="progressing">
									<span style="width: ${percentage}%;"></span>
								</div>
								<span class="quepercent">${percentage}%</span>
							</div>

							<div class="questionname">
								<div class="verticalline"></div>
								<div class="queno">
									<span>${currentQuestion.position}</span>
								</div>
								<h3 class="qname">${currentQuestion.questionMapperInstance.questionMapper.question.questionText}</h3>
								&nbsp; &nbsp; &nbsp;   <c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl != null && currentQuestion.questionMapperInstance.questionMapper.question.imageUrl.trim().length() > 0}">
								<img src="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl}" height="400" width="500">
							  </c:if>
							  
							  <c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.audioURL != null && currentQuestion.questionMapperInstance.questionMapper.question.audioURL.trim().length() > 0}">
							   &nbsp; &nbsp; &nbsp;  <audio controls src="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL}">
										Your browser does not support the
										<code>audio</code> element.
								</audio>
								
							  </c:if>
							  
							  <c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.videoURL != null && currentQuestion.questionMapperInstance.questionMapper.question.videoURL.trim().length() > 0}">
								&nbsp; &nbsp; &nbsp; <video width="400" height="300" controls>
									  <source src="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL}" >
									 
									  Your browser does not support the video tag.
									</video>
							  </c:if>
								
								<div class="answers">
								<%
								QuestionInstanceDto currentQuestion = (QuestionInstanceDto) request.getAttribute("currentQuestion");
								System.out.println("fill in the blanks "+currentQuestion.getFillInBlanks().size());
								%>
	<c:forEach varStatus="s" items="${currentQuestion.fillInBlanks}">
		<form:input  path="fillInBlanks[${s.index}]" placeholder="Fill the Blank" style="color:red;size:20"  class="no-outline" />
	</c:forEach>
								
								</div>
							</div>
						
						</div>
						
						
						</c:when>
						
						
						<c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='MATCH_FOLLOWING_MCQ'}">
						
						
							
						
						<div class="queanscenter" id="section1_content">
							<div class="queprogress">
								<span style="float: left; width: 100%;">${noAnswered} of ${totalQuestions} answered</span>
								<div class="progressing">
									<span style="width: ${percentage}%;"></span>
								</div>
								<span class="quepercent">${percentage}%</span>
							</div>

							<div class="questionname">
								<div class="verticalline"></div>
								<div class="queno">
									<span>${currentQuestion.position}</span>
								</div>
								<h3 class="qname">${currentQuestion.questionMapperInstance.questionMapper.question.questionText}</h3>
								&nbsp; &nbsp; &nbsp;   <c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl != null && currentQuestion.questionMapperInstance.questionMapper.question.imageUrl.trim().length() > 0}">
								<img src="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl}" height="400" width="500">
							  </c:if>
							  
							  <c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.audioURL != null && currentQuestion.questionMapperInstance.questionMapper.question.audioURL.trim().length() > 0}">
							   &nbsp; &nbsp; &nbsp;  <audio controls src="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL}">
										Your browser does not support the
										<code>audio</code> element.
								</audio>
								
							  </c:if>
							  
							  <c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.videoURL != null && currentQuestion.questionMapperInstance.questionMapper.question.videoURL.trim().length() > 0}">
								&nbsp; &nbsp; &nbsp; <video width="400" height="300" controls>
									  <source src="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL}" >
									 
									  Your browser does not support the video tag.
									</video>
							  </c:if>
						
							
							<div class="row" style="padding: 10%;">
								<div class="col-md-6 question">
								  <ul>
							<li id="q1">${currentQuestion.questionMapperInstance.questionMapper.question.matchLeft1}</li>
							<li id="q2">${currentQuestion.questionMapperInstance.questionMapper.question.matchLeft2}</li>
							
							<c:if test = "${currentQuestion.mtfSize > 2}">
							<li id="q3">${currentQuestion.questionMapperInstance.questionMapper.question.matchLeft3}
							</li>
							</c:if>
							
							<c:if test = "${currentQuestion.mtfSize > 3}">
							<li id="q4">${currentQuestion.questionMapperInstance.questionMapper.question.matchLeft4}
							</li>
							</c:if>
							
							<c:if test = "${currentQuestion.mtfSize > 4}">
							<li id="q5">${currentQuestion.questionMapperInstance.questionMapper.question.matchLeft5}
							</li>
							</c:if>
							
							<c:if test = "${currentQuestion.mtfSize > 5}">
							<li id="q6">${currentQuestion.questionMapperInstance.questionMapper.question.matchLeft6}
							</li>
							</c:if>
							
								  </ul>
								</div>
								<div class="col-md-6 answer">
								  <ul>
									<li id="a1">${currentQuestion.mtf.matchRight1Display}</li>
								<form:hidden path="mtf.matchRight1" value="${currentQuestion.mtf.matchRight1}"  />
			
									<li id="a2">${currentQuestion.mtf.matchRight2Display}</li><form:hidden path="mtf.matchRight2" value="${currentQuestion.mtf.matchRight2}" />
									<c:if test = "${currentQuestion.mtfSize > 2}">
									<li id="a3">${currentQuestion.mtf.matchRight3Display}</li><form:hidden path="mtf.matchRight3" value="${currentQuestion.mtf.matchRight3}" />
									</c:if>
									<c:if test = "${currentQuestion.mtfSize > 3}">
									<li id="a4">${currentQuestion.mtf.matchRight4Display}</li><form:hidden path="mtf.matchRight4" value="${currentQuestion.mtf.matchRight4}" />
									</c:if>
									<c:if test = "${currentQuestion.mtfSize > 4}">
									<li id="a5">${currentQuestion.mtf.matchRight5Display}</li><form:hidden path="mtf.matchRight5" value="${currentQuestion.mtf.matchRight5}" />
									</c:if>
									<c:if test = "${currentQuestion.mtfSize > 5}">
									<li id="a6">${currentQuestion.mtf.matchRight6Display}</li><form:hidden path="mtf.matchRight6" value="${currentQuestion.mtf.matchRight6}" />
									</c:if>
								  </ul>
								</div>
							  </div>
						
						</div>
						
						</c:when>
						
						
					    <c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='CODING'}">
						
						
						<!--Code for coding Q -->
						<div class="queanscenter" id="section2_content" >

						<div class="col-md-12">
						    <div class="col-md-7 leftside">
							<b>${currentQuestion.questionMapperInstance.questionMapper.question.qualifier1} ${currentQuestion.questionMapperInstance.questionMapper.question.qualifier2} ${currentQuestion.questionMapperInstance.questionMapper.question.qualifier3} ${currentQuestion.questionMapperInstance.questionMapper.question.qualifier4} ${currentQuestion.questionMapperInstance.questionMapper.question.qualifier5}</b>
							<a class="runcode" href="javascript:runCodeSystemTestCase();">Run System Test Case  </a>
							<a class="runcode" href="javascript:runCode();">Run Code</a>
							<label>Code</label>
							
							<form:textarea id="editor" path="code" wrap="physical"/>
							<form:hidden path="code" id="codeOfEditor" wrap="physical"/>
							   
							
							
							

							<label>Input</label>
							 <form:textarea path="input"  style="height:30px" id="input" placeholder="Enter input"/>

							<label>Output</label>
							<form:textarea  style="overflow-y: scroll" path="output"   id="output" disabled="true"/>
						    </div>
						    <div class="col-md-5 rightside">
							<div class="description">
							    <label>DESCRIPTION</label>
							    <p>${currentQuestion.questionMapperInstance.questionMapper.question.questionText}</p>
							    <code>
								Update code in Code Editor 
							    </code>
							    <p>${currentQuestion.questionMapperInstance.questionMapper.question.instructionsIfAny}</p>
							    <h4>Constraint</h4>
							    <p>${currentQuestion.questionMapperInstance.questionMapper.question.constrnt}</p>

							    <h4>Input</h4>
							    <code>
								${currentQuestion.questionMapperInstance.questionMapper.question.hiddenInputPositive}
							    </code>

							    <h4>Output</h4>
							    <code>
								${currentQuestion.questionMapperInstance.questionMapper.question.hiddenOutputPositive}
							    </code>
							   
							</div>
						    </div>
						</div>

					    </div>

						
					    </c:when>   
						
						<c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='FULL_STACK_JAVA' || currentQuestion.questionMapperInstance.questionMapper.question.type=='FULLSTACK' }">
						
						
						
						<div class="queanscenter" id="section3_content" >

						<div class="col-md-12">
						    <div class="col-md-7 leftside">
							<b>${currentQuestion.questionMapperInstance.questionMapper.question.qualifier1} ${currentQuestion.questionMapperInstance.questionMapper.question.qualifier2} ${currentQuestion.questionMapperInstance.questionMapper.question.qualifier3} ${currentQuestion.questionMapperInstance.questionMapper.question.qualifier4} ${currentQuestion.questionMapperInstance.questionMapper.question.qualifier5}</b>
							&nbsp; &nbsp; &nbsp;   <c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl != null && currentQuestion.questionMapperInstance.questionMapper.question.imageUrl.trim().length() > 0}">
								<img src="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl}" height="400" width="500">
							  </c:if>
							  
							  <c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.audioURL != null && currentQuestion.questionMapperInstance.questionMapper.question.audioURL.trim().length() > 0}">
							   &nbsp; &nbsp; &nbsp;  <audio controls src="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL}">
										Your browser does not support the
										<code>audio</code> element.
								</audio>
								
							  </c:if>
							  
							  <c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.videoURL != null && currentQuestion.questionMapperInstance.questionMapper.question.videoURL.trim().length() > 0}">
								&nbsp; &nbsp; &nbsp; <video width="400" height="300" controls>
									  <source src="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL}" >
									 
									  Your browser does not support the video tag.
									</video>
							  </c:if>
							   
							<br/>
							<label>Click to Open</label>
							<a href="javascript:showAndNavigate();">Open Project Documentation Template</a>
							<br/>
							<label>Click to Open</label>
							 <a href="${currentQuestion.questionMapperInstance.workspaceUrl}" target="_blank">Open Code IDE in new Window</a>
							 <br/>
							<label>Upload Project Documentation</label>
							<a class="addimage" href="#">Upload Documentation</a>
							 <input type="file" name="addimage" id="addimage" style="display: none;">
							 <label class="queimage"></label>
							<br/>
							
							<a class="runcode" href="javascript:confirmWorkspace('${currentQuestion.questionMapperInstance.id}');">Submit my Workspace  </a>
						    </div>
						    <div class="col-md-5 rightside">
							<div class="description">
							    <label>DESCRIPTION</label>
							    <p>${currentQuestion.questionMapperInstance.questionMapper.question.questionText}</p>
							    <code>
								Click on 'Open Code IDE..' to start coding.
							    </code>
							    <p>${currentQuestion.questionMapperInstance.questionMapper.question.instructionsIfAny}</p>
							    <h4>Constraint</h4>
							    <p>${currentQuestion.questionMapperInstance.questionMapper.question.constrnt}</p>

							    
							   
							</div>
						    </div>
						</div>

					    </div>

						
					    </c:when>   
						
					</c:choose>
			
			

				

			</div>


			<!-- <div class="flagdiv">
				<a href="#"><i class="fa fa-flag-checkered"></i>FLAG</a>
			</div> -->

		</div>
		
		
            <div class="flagdiv">
              <!--  <a href="#"><i class="fa fa-flag-checkered"></i>FLAG</a> -->
            </div>

        </div>
		
		<!--  End code for coding Q-->


		<div class="backprevbtn">
			
			<div class="center">
				<c:choose>
					<c:when test="${currentSection.first==true}">
					</c:when>
					<c:otherwise>
						<a class="back" href="javascript:prev();">Back</a>
						<!-- <i class="fa fa-long-arrow-left"></i> -->
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${currentSection.last==true}">
					
					<a class="next" href="javascript:submitTestCheckNoAnswer();" id="next">SUBMIT TEST</a>
					</c:when>
					<c:otherwise>
						<a class="next" href="javascript:next();" id="next">Next</a>
						<!-- <i class="fa fa-long-arrow-right"></i>-->
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
	<div class="logincopyright">
		<div class="col-md-12">
			<p>Copyright 2018 IIHT. All Rights Reserved. Privacy Policy
				For Enterprise Solutions</p>
		</div>
	</div>

	<!-- <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script> -->

	<!-- <script>
			$(document).ready(function(){
				$('.questionname .answers input').change(function(){
					$('.backprevbtn .center a.next').show();
					$('li.highlight').removeClass('highlight');
					$(this).closest('li').addClass('highlight');
				});
			});
		</script> -->
		
	<script>
	    var editor = ace.edit("editor");
	    editor.setTheme("ace/theme/solarized_light");
	   //editor.setTheme("ace/theme/theme-github");
	   
	   var language = '${currentQuestion.questionMapperInstance.questionMapper.question.language.language}';
		if(language == 'JAVA'){
			editor.session.setMode("ace/mode/java");
		}
		else if(language == 'C'){
			editor.session.setMode("ace/mode/c_cpp");
		}
		else if(language == 'C++'){
			editor.session.setMode("ace/mode/c_cpp");
		}
		else if(language == 'C#'){
			editor.session.setMode("ace/mode/csharp");
		}
		else if(language == 'PYTHON'){
			editor.session.setMode("ace/mode/python");
		}
		else if(language == 'PHP'){
			editor.session.setMode("ace/mode/php");
			
		}
		else if(language == 'JAVASCRIPT'){
			editor.session.setMode("ace/mode/javascript");
		}
		else if(language == 'CLOJURE'){
			editor.session.setMode("ace/mode/clojure");
		}
		else if(language == 'GO'){
			editor.session.setMode("ace/mode/golang");
		}
		else if(language == 'BASH'){
			editor.session.setMode("ace/mode/batchfile");
		}
		else if(language == 'OBJECTIVE_C'){
			editor.session.setMode("ace/mode/objectivec");
		}
		else if(language == 'MYSQL'){
			editor.session.setMode("ace/mode/sql");
		}
		else if(language == 'PERL'){
			editor.session.setMode("ace/mode/perl");
		}
		else if(language == 'RUST'){
			editor.session.setMode("ace/mode/rust");
		}
		
	    
		
		editor.setValue('${currentQuestion.code}');
	    editor.setOptions({
	   enableBasicAutocompletion: true, // the editor completes the statement when you hit Ctrl + Space
	   enableLiveAutocompletion: true, // the editor completes the statement while you are typing
	   showPrintMargin: false, // hides the vertical limiting strip
	   maxLines: Infinity,
	   fontSize: "100%" // ensures that the editor fits in the environment
	});

	</script>

	<style>
	
	.ace_editor div {
	    font: inherit!important
	}
	
	  #editor {
	    height: 400px;
	    width: 100%;
	    color: #000;
	   margin: 0;
       
	    }
	    
    #editor {
	    font-family:monospace
	}
		
	.ace_editor {
	    font-family:monospace!important
	}
	
	editor.container.style.fontFamily = "monospace";
	</style>
	
	
	<script>
		var questionColor = '';
		$(document).ready(function(){
			$('.question li').click(function(){
				questionColor = $(this).css("background-color");
				$('.question li').css('box-shadow', 'none');
				$(this).css('box-shadow', '2px 2px 2px 2px  grey');
			});
			$('.answer li').click(function(){
				$(".answer li").each(function( i ) {
					if($(this).css("background-color") == questionColor){
						$(this).css("background-color", 'grey');
					}
				});
				$(this).css('background-color', questionColor);
			});
			
			var a1 = document.getElementById("a1");
			console.log(' in load ');
			if(a1 != null){
				
				var col1 = '<c:out value="${currentQuestion.mtf.matchRight1DisplayColour}"/>';
				console.log(' in load col1 '+col1);
				if(col1 != null){
					console.log(' in load col1 set'+col1);
					a1.style.backgroundColor = col1;
					console.log(' in load col1 set done'+col1);
				}
				
			}
			
			var a2 = document.getElementById("a2");
			if(a2 != null){
				var col2 = '<c:out value="${currentQuestion.mtf.matchRight2DisplayColour}"/>';
				if(col2 != null){
					a2.style.backgroundColor = col2;
				}
				
			}
			
			var a3 = document.getElementById("a3");
			if(a3 != null){
				var col3 = '<c:out value="${currentQuestion.mtf.matchRight3DisplayColour}"/>';
				if(col3 != null){
					a3.style.backgroundColor = col3;
				}
				
			}
			
			var a4 = document.getElementById("a4");
			if(a4 != null){
				var col4 = '<c:out value="${currentQuestion.mtf.matchRight4DisplayColour}"/>';
				if(col4 != null){
					a4.style.backgroundColor = col4;
				}
				
			}
			
			var a5 = document.getElementById("a5");
			if(a5 != null){
				var col5 = '<c:out value="${currentQuestion.mtf.matchRight5DisplayColour}"/>';
				if(col5 != null){
					a5.style.backgroundColor = col5;
				}
				
			}
			
			var a6 = document.getElementById("a6");
			if(a6 != null){
				var col6 = '<c:out value="${currentQuestion.mtf.matchRight6DisplayColour}"/>';
				if(col6 != null){
					a6.style.backgroundColor = col6;
				}
				
			}
			
			
		});
		
		
		

	
	function setMtfValues(qType){
		if(qType == 'MATCH_FOLLOWING_MCQ'){
		
		
		var cola1 = document.getElementById("a1").style.backgroundColor;
		var cola2 = document.getElementById("a2").style.backgroundColor;
		var cola3 = null;
			if(document.getElementById("a3") != null){
				cola3 = document.getElementById("a3").style.backgroundColor;
			}
		
		var cola4 = null;
			if(document.getElementById("a4") != null){
				cola4 = document.getElementById("a4").style.backgroundColor;
			}
			
		var cola5 = null;
			if(document.getElementById("a5") != null){
				cola5 = document.getElementById("a5").style.backgroundColor;
			}
		
		var cola6 = null;
			if(document.getElementById("a6") != null){
				cola6 = document.getElementById("a6").style.backgroundColor;
			}
			
			if(cola1 != null && cola1 == "rgb(255, 0, 0)"){
				document.getElementById("mtf.matchRight1").value = document.getElementById("a1").innerHTML;
			}
			else if(cola2 != null && cola2 == "rgb(255, 0, 0)"){
				document.getElementById("mtf.matchRight1").value = document.getElementById("a2").innerHTML;
			}
			else if(cola3 != null && cola3 == "rgb(255, 0, 0)"){
				document.getElementById("mtf.matchRight1").value = document.getElementById("a3").innerHTML;
			}
			else if(cola4 != null && cola4 == "rgb(255, 0, 0)"){
				var a1val = document.getElementById("a4").innerHTML;
				document.getElementById("mtf.matchRight1").value = a1val;
				console.log(' val '+document.getElementById("mtf.matchRight4").value);
				console.log(' a1 '+a1val);
			}
			else if(cola5 != null && cola5 == "rgb(255, 0, 0)"){
				document.getElementById("mtf.matchRight1").value = document.getElementById("a5").innerHTML;
			}
			else if(cola6 != null && cola6 == "rgb(255, 0, 0)"){
				document.getElementById("mtf.matchRight1").value = document.getElementById("a6").innerHTML;
			}
			
			if(cola1!= null && cola1 == "rgb(0, 128, 0)"){
				document.getElementById("mtf.matchRight2").value = document.getElementById("a1").innerHTML;
			}
			else if(cola2!= null && cola2 == "rgb(0, 128, 0)"){
				document.getElementById("mtf.matchRight2").value = document.getElementById("a2").innerHTML;
			}
			else if(cola3!= null && cola3 == "rgb(0, 128, 0)"){
				document.getElementById("mtf.matchRight2").value = document.getElementById("a3").innerHTML;
			}
			else if(cola4!= null && cola4 == "rgb(0, 128, 0)"){
				document.getElementById("mtf.matchRight2").value = document.getElementById("a4").innerHTML;
			}
			else if(cola5!= null && cola5 == "rgb(0, 128, 0)"){
				document.getElementById("mtf.matchRight2").value = document.getElementById("a5").innerHTML;
			}
			else if(cola6!= null && cola6 == "rgb(0, 128, 0)"){
				document.getElementById("mtf.matchRight2").value = document.getElementById("a6").innerHTML;
			}
			
			if(cola1 != null && cola1 == "rgb(0, 0, 255)"){
				document.getElementById("mtf.matchRight3").value = document.getElementById("a1").innerHTML;
			}
			else if(cola2 != null && cola2 == "rgb(0, 0, 255)"){
				document.getElementById("mtf.matchRight3").value = document.getElementById("a2").innerHTML;
			}
			else if(cola3 != null && cola3 == "rgb(0, 0, 255)"){
				document.getElementById("mtf.matchRight3").value = document.getElementById("a3").innerHTML;
			}
			else if(cola4 != null && cola4 == "rgb(0, 0, 255)"){
				document.getElementById("mtf.matchRight3").value = document.getElementById("a4").innerHTML;
			}
			else if(cola5 != null && cola5 == "rgb(0, 0, 255)"){
				document.getElementById("mtf.matchRight3").value = document.getElementById("a5").innerHTML;
			}
			else if(cola6 != null && cola6 == "rgb(0, 0, 255)"){
				document.getElementById("mtf.matchRight3").value = document.getElementById("a6").innerHTML;
			}
			
			if(cola1 != null && cola1 == "rgb(255, 192, 203)"){
				document.getElementById("mtf.matchRight4").value = document.getElementById("a1").innerHTML;
			}
			else if(cola2 != null && cola2 == "rgb(255, 192, 203)"){
				document.getElementById("mtf.matchRight4").value = document.getElementById("a2").innerHTML;
			}
			else if(cola3 != null && cola3 == "rgb(255, 192, 203)"){
				document.getElementById("mtf.matchRight4").value = document.getElementById("a3").innerHTML;
			}
			else if(cola4 != null && cola4 == "rgb(255, 192, 203)"){
				document.getElementById("mtf.matchRight4").value = document.getElementById("a4").innerHTML;
			}
			else if(cola5 != null && cola5 == "rgb(255, 192, 203)"){
				document.getElementById("mtf.matchRight4").value = document.getElementById("a5").innerHTML;
			}
			else if(cola6 != null && cola6 == "rgb(255, 192, 203)"){
				document.getElementById("mtf.matchRight4").value = document.getElementById("a6").innerHTML;
			}
			
			if(cola1 != null && cola1 == "rgb(235, 21, 135)"){
				document.getElementById("mtf.matchRight5").value = document.getElementById("a1").innerHTML;
			}
			else if(cola2 != null && cola2 == "rgb(235, 21, 135)"){
				document.getElementById("mtf.matchRight5").value = document.getElementById("a2").innerHTML;
			}
			else if(cola3 != null && cola3 == "rgb(235, 21, 135)"){
				document.getElementById("mtf.matchRight5").value = document.getElementById("a3").innerHTML;
			}
			else if(cola4 != null && cola4 == "rgb(235, 21, 135)"){
				document.getElementById("mtf.matchRight5").value = document.getElementById("a4").innerHTML;
			}
			else if(cola5 != null && cola5 == "rgb(235, 21, 135)"){
				document.getElementById("mtf.matchRight5").value = document.getElementById("a5").innerHTML;
			}
			else if(cola6 != null && cola6 == "rgb(235, 21, 135)"){
				document.getElementById("mtf.matchRight5").value = document.getElementById("a6").innerHTML;
			}
			
			if(cola1 != null && cola1 == "rgb(186, 252, 53)"){
				document.getElementById("mtf.matchRight6").value = document.getElementById("a1").innerHTML;
			}
			else if(cola2 != null && cola2 == "rgb(186, 252, 53)"){
				document.getElementById("mtf.matchRight6").value = document.getElementById("a2").innerHTML;
			}
			else if(cola3 != null && cola3 == "rgb(186, 252, 53)"){
				document.getElementById("mtf.matchRight6").value = document.getElementById("a3").innerHTML;
			}
			else if(cola4 != null && cola4 == "rgb(186, 252, 53)"){
				document.getElementById("mtf.matchRight6").value = document.getElementById("a4").innerHTML;
			}
			else if(cola5 != null && cola5 == "rgb(186, 252, 53)"){
				document.getElementById("mtf.matchRight6").value = document.getElementById("a5").innerHTML;
			}
			else if(cola6 != null && cola6 == "rgb(186, 252, 53)"){
				document.getElementById("mtf.matchRight6").value = document.getElementById("a6").innerHTML;
			}
		
		}
	}
	</script>
		
<script>
	$(function () {
                $(".addimage").on('click', function (e) {
                    e.preventDefault();
                    $("#addimage").trigger('click');
                });
               
            });
	
	 $('#addimage').change(function () {
                var file = $('#addimage')[0].files[0].name;
                $('.queimage').text('Document to upload: '+file);
				uploadProjectDocs();
            });

	function changeSection(sectionName){
		//window.location = 'changeSection?sectionName='+sectionName+"&timeCounter="+timeCounter;
		//localStorage.setItem('timeCounter', timeCounter);
		var tp = '${currentQuestion.questionMapperInstance.questionMapper.question.type}';
			if(tp == 'CODING'){
				confirm(sectionName);
			}
			else{
				window.location = 'changeSection?sectionName='+sectionName+"&timeCounter="+timeCounter;
				localStorage.setItem('timeCounter', timeCounter);
			}
		
	}
	
	function get_center_pos(width, top){
    // top is empty when creating a new notification and is set when recentering
		if (!top){
			top = 30;
			// this part is needed to avoid notification stacking on top of each other
			$('.ui-pnotify').each(function() { top += $(this).outerHeight() + 20; });
		}

		return {"top": top, "left": ($(window).width() / 2) - (width / 2)}
	}
	
	function confirm(sectionName) {
           (new PNotify({
		    title: 'Confirmation Needed',
		    text: 'For a coding Question if you change a section you may loose your changes. Either copy your code somewhere and then change section or use Next/Back to navigate',
		    icon: 'glyphicon glyphicon-question-sign',
		    hide: false,
		    confirm: {
			confirm: true
		    },
		    buttons: {
			closer: false,
			sticker: false
		    },
		    history: {
			history: false
		    },
			 before_open: function (PNotify) {
            PNotify.get().css(get_center_pos(PNotify.get().width()));
			}
		})).get().on('pnotify.confirm', function() {
		   window.location = 'changeSection?sectionName='+sectionName+"&timeCounter="+timeCounter;
			localStorage.setItem('timeCounter', timeCounter);
		}).on('pnotify.cancel', function() {
		   
		});
    }
	
	function runCode(){
	//editor
	//var editor = ace.edit("editor");
	var code = editor.getValue();
	var input = document.getElementById('input').value;
	var lang = '8';
	var language = '${currentQuestion.questionMapperInstance.questionMapper.question.language.language}';
		if(language == 'JAVA'){
			lang = '8';
		}
		else if(language == 'C'){
			lang = '7';
		}
		else if(language == 'CPLUSPLUS'){
			lang = '7';
		}
		else if(language == 'CHASH'){
			lang = '10';
		}
		else if(language == 'DotNet'){
			lang = '10';
		}
		else if(language == 'PYTHON'){
			lang = '0';
		}
		else if(language == 'PHP'){
			lang = '3';
		}
		else if(language == 'JAVASCRIPT'){
			lang = '4';
		}
		else if(language == 'CLOJURE'){
			lang = '2';
		}
		else if(language == 'GO'){
			lang = '6';
		}
		else if(language == 'BASH'){
			lang = '11';
		}
		else if(language == 'OBJECTIVE_C'){
			lang = '12';
		}
		else if(language == 'MYSQL'){
			lang = '13';
		}
		else if(language == 'PERL'){
			lang = '14';
		}
		else if(language == 'RUST'){
			lang = '15';
		}
	
	var url = 'compile';
	var data = {}; 
	data.language = lang;
	data.code = code;
	data.stdin = input;
	//var json = "{language:"+lang+", code:"+code+", stdin:"+input+"}";
	document.getElementById('output').value = 'Executing your code...';	
	document.getElementById("output").focus();
	document.getElementById("output").scrollIntoView();
	dta = JSON.stringify(data);
	//dta = dta.slice(1,-1);
	//dat = "'"+dta+"'";
		$.ajax({
				type : 'POST',
				url : url,
				contentType: "application/json; charset=utf-8",
				data: dta,
				success : function(data) {
					
					document.getElementById('output').value = data;
					//document.getElementById('output').value = 'eee';
					console.log("SUCCESS: ", data);
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				}
			});
	}
	
	function runCodeSystemTestCase(){
		//var editor = ace.edit("editor");
	var code = editor.getValue();
	var input = '${currentQuestion.questionMapperInstance.questionMapper.question.hiddenInputPositive}';
	document.getElementById('input').value = input;
	var lang = '8';
	var language = '${currentQuestion.questionMapperInstance.questionMapper.question.language.language}';
		if(language == 'JAVA'){
			lang = '8';
		}
		else if(language == 'C'){
			lang = '7';
		}
		else if(language == 'CPLUSPLUS'){
			lang = '7';
		}
		else if(language == 'CHASH'){
			lang = '10';
		}
		else if(language == 'DotNet'){
			lang = '10';
		}
		else if(language == 'PYTHON'){
			lang = '0';
		}
		else if(language == 'PHP'){
			lang = '3';
		}
		else if(language == 'JAVASCRIPT'){
			lang = '4';
		}
		else if(language == 'CLOJURE'){
			lang = '2';
		}
		else if(language == 'GO'){
			lang = '6';
		}
		else if(language == 'BASH'){
			lang = '11';
		}
		else if(language == 'OBJECTIVE_C'){
			lang = '12';
		}
		else if(language == 'MYSQL'){
			lang = '13';
		}
		else if(language == 'PERL'){
			lang = '14';
		}
		else if(language == 'RUST'){
			lang = '15';
		}
	
	var url = 'compileAndRunSystemTest';
	
	var data = {}; 
	data.language = lang;
	data.code = code;
	data.stdin = input;
	document.getElementById('output').value = 'Executing your code...';	
	document.getElementById("output").focus();
	document.getElementById("output").scrollIntoView();
	dta = JSON.stringify(data);
	
		$.ajax({
				type : 'POST',
				url : url,
				contentType: "application/json; charset=utf-8",
				data: dta,
				success : function(data) {
					console.log("SUCCESS: ", data);
					//document.getElementById('output').value = data;
					if(data == '${currentQuestion.questionMapperInstance.questionMapper.question.hiddenOutputPositive}'){
						document.getElementById('output').value = (data + '\n' +'SUCCESS');
					}
					else{
					document.getElementById('output').value = (data + '\n' +'FAILED');
					}
					
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				}
			});
	}
	
	function next(){
		//$('input[name="one"]').not(':checked').val(0);
		var qType = '${currentQuestion.questionMapperInstance.questionMapper.question.type}';
		if(qType == 'MCQ'){
			var one = $( 'input[name="one"]:checked' ).val();
			var two = $( 'input[name="two"]:checked' ).val();
			var three = $( 'input[name="three"]:checked' ).val();
			var four = $( 'input[name="four"]:checked' ).val();
			var five = $( 'input[name="five"]:checked' ).val();
			var six = $( 'input[name="six"]:checked' ).val();
			count = 0;
			if(one != null){
				count ++;
			}
			
			if(two != null){
				count ++;
			}
			
			if(three != null){
				count ++;
			}
			
			if(four != null){
				count ++;
			}
			
			if(five != null){
				count ++;
			}
			if(six != null){
				count ++;
			}
			
			var noOfConfiguredChoices = '${currentQuestion.questionMapperInstance.questionMapper.question.rightChoices}'.split(',').length;
			var correctNo = ('${currentQuestion.questionMapperInstance.questionMapper.question.rightChoices}'.match(/-/g) || []).length  + 1;
			

			//console.log('${currentQuestion.questionMapperInstance.questionMapper.question.rightChoices}');
			//console.log('correct choices'+correctNo);
			//console.log('no of chosen choices'+count);
			if(count == correctNo){
				//notify('INFO', 'go ahead');
			}
			else{
				//do nothing
			//notify('INFO', 'No of Answers chosen are not equal to no of correct answers. Please select '+correctNo+' option(s)');
			//return;
			}
			
			
		}
		
	
	if(qType == 'CODING'){
		var textarea = document.getElementById('codeOfEditor');
 		edt = editor.getSession().getValue();
		textarea.value = edt;
	}
	
	setMtfValues(qType);
 
//edt.value = editor.getSession().getValue();
	var linktext=document.getElementById('next').text;
		if(linktext == 'Finish Test'){
		submitTest();
		}
		else{
		document.testForm.action = "nextQuestion?questionId=${currentQuestion.questionMapperInstance.questionMapper.id}&timeCounter="+timeCounter;
		storeTimeLocal();
	 document.testForm.submit();
		}
	
	}
	
	
	
	function prev(){
	var qType = '${currentQuestion.questionMapperInstance.questionMapper.question.type}';
	//var qType = '${currentQuestion.questionMapperInstance.questionMapper.question.type}';
		if(qType == 'MCQ'){
			var one = $( 'input[name="one"]:checked' ).val();
			var two = $( 'input[name="two"]:checked' ).val();
			var three = $( 'input[name="three"]:checked' ).val();
			var four = $( 'input[name="four"]:checked' ).val();
			var five = $( 'input[name="five"]:checked' ).val();
			var six = $( 'input[name="six"]:checked' ).val();
			count = 0;
			if(one != null){
				count ++;
			}
			
			if(two != null){
				count ++;
			}
			
			if(three != null){
				count ++;
			}
			
			if(four != null){
				count ++;
			}
			
			if(five != null){
				count ++;
			}
			if(six != null){
				count ++;
			}
			
			var correctNo = ('${currentQuestion.questionMapperInstance.questionMapper.question.rightChoices}'.match(/-/g) || []).length  + 1;
			

			console.log('${currentQuestion.questionMapperInstance.questionMapper.question.rightChoices}');
			console.log('correct choices'+correctNo);
			console.log('no of chosen choices'+count);
			if(count == correctNo){
				//notify('INFO', 'go ahead');
			}
			else{
				//do nothing cognizant
			//notify('INFO', 'No of Answers chosen are not equal to no of correct answers. Please select '+correctNo+' option(s)');
			//return;
			}
			
			
		}
	
	if(qType == 'CODING'){
		var textarea = document.getElementById('codeOfEditor');
 		edt = editor.getSession().getValue();
		textarea.value = edt;
	}
	
	setMtfValues(qType);
	
	document.testForm.action = "prevQuestion?questionId=${currentQuestion.questionMapperInstance.questionMapper.id}&timeCounter="+timeCounter;
	storeTimeLocal();
	document.testForm.submit();
	}
	
	function submitTest(){
	var qType = '${currentQuestion.questionMapperInstance.questionMapper.question.type}';
//	var qType = '${currentQuestion.questionMapperInstance.questionMapper.question.type}';
		if(qType == 'MCQ'){
			var one = $( 'input[name="one"]:checked' ).val();
			var two = $( 'input[name="two"]:checked' ).val();
			var three = $( 'input[name="three"]:checked' ).val();
			var four = $( 'input[name="four"]:checked' ).val();
			var five = $( 'input[name="five"]:checked' ).val();
			var six = $( 'input[name="six"]:checked' ).val();
			count = 0;
			if(one != null){
				count ++;
			}
			
			if(two != null){
				count ++;
			}
			
			if(three != null){
				count ++;
			}
			
			if(four != null){
				count ++;
			}
			
			if(five != null){
				count ++;
			}
			if(six != null){
				count ++;
			}
			
			var correctNo = ('${currentQuestion.questionMapperInstance.questionMapper.question.rightChoices}'.match(/-/g) || []).length  + 1;
			

			console.log('${currentQuestion.questionMapperInstance.questionMapper.question.rightChoices}');
			console.log('correct choices'+correctNo);
			console.log('no of chosen choices'+count);
			if(count == correctNo){
				//notify('INFO', 'go ahead');
			}
			else{
			//notify('INFO', 'No of Answers chosen are not equal to no of correct answers. Please select '+correctNo+' option(s)');
			//return;
			}
			
			
		}
		
	if(qType == 'CODING'){
		var textarea = document.getElementById('codeOfEditor');
 		edt = editor.getSession().getValue();
		textarea.value = edt;
	}
	//var textarea = document.getElementById('codeOfEditor');
	//edt = editor.getSession().getValue();
	//textarea.value = edt;
	document.testForm.action = "submitTest?questionId=${currentQuestion.questionMapperInstance.questionMapper.id}&timeCounter="+timeCounter;
	resetTimeLocal();
	//modal.style.display = "block";
	//document.getElementById("showAlert").innerHTML = 'You have exceeded your time limit to complete the test. The test will auto submit in a moment...';
	document.testForm.submit();
	submitTest = 'true';
	notify('Info', 'The test is now submitted! Do not click the Submit button again incase you do not get a result screen fast because of a slow connection. DO NOT REFRESH THE PAGE.');
	//next
	var a = document.getElementById('next');
	a.href = "javascript:donothing();";
	}
	
	function donothing(){
		notify('Information', 'Your test is already getting submitted. Please Do not click on Submit button again');
	}
	
	function storeTimeLocal(){
	localStorage.setItem('${studentTestForm.firstName}${studentTestForm.lastName}', 'yes');
	localStorage.setItem('testName-${studentTestForm.firstName}${studentTestForm.lastName}', '${studentTestForm.firstName}${studentTestForm.lastName}-'+

'${studentTestForm.testName}');
	localStorage.setItem('timeCounter-${studentTestForm.firstName}${studentTestForm.lastName}', timeCounter);
	}
	
	function resetTimeLocal(){
	localStorage.setItem('${studentTestForm.firstName}${studentTestForm.lastName}', 'no');
	localStorage.setItem('testName-${studentTestForm.firstName}${studentTestForm.lastName}', null);
	localStorage.setItem('timeCounter-${studentTestForm.firstName}${studentTestForm.lastName}', 0);
	}
	
	function submitTestCheckNoAnswer(){
	notify('Information', 'DO NOT REFRESH the page post submission');
	var uanswered = '${totalQuestions - (noAnswered+1)}';
		if(uanswered == '0'){
			submitTest();
		}
		else{
			(new PNotify({
		    title: 'Confirmation Needed',
		    text: 'Are you sure you want to submit the test? You still have unanswered Questions? In the event of you submitting please DO NOT REFESH THE PAGE post submission',
		    icon: 'glyphicon glyphicon-question-sign',
		    hide: false,
		    confirm: {
			confirm: true
		    },
		    buttons: {
			closer: true,
			sticker: true
		    },
		    history: {
			history: false
		    }
		})).get().on('pnotify.confirm', function() {
		  // parent.closeFullScreen();	//uncomment it later
		   submitTest();
		}).on('pnotify.cancel', function() {
		   
		});
			
		}
		
	}
	
	function confirmWorkspace(qMapperInstanceId){
		(new PNotify({
		    title: 'Confirmation Needed',
		    text: 'Are you sure you have uploaded the project documentation. Your reviewer may take a dim view of your efforts if no documentation is found? If yes you can submit the workspace?',
		    icon: 'glyphicon glyphicon-question-sign',
		    hide: false,
		    confirm: {
			confirm: true
		    },
		    buttons: {
			closer: true,
			sticker: true
		    },
		    history: {
			history: false
		    }
		})).get().on('pnotify.confirm', function() {
		    submitFullStackCode(qMapperInstanceId);
		}).on('pnotify.cancel', function() {
		   
		});
	}
	
	function showAndNavigate(){
		(new PNotify({
		    title: 'About this',
		    text: 'Yes to this will download a Project Documentation template that you are expected to update and then upload. Kindly make sure the How-to use part is crystal clear for your reviewer to gro through and start your app. Do you want to proceed now?',
		    icon: 'glyphicon glyphicon-question-sign',
		    hide: false,
		    confirm: {
			confirm: true
		    },
		    buttons: {
			closer: false,
			sticker: false
		    },
		    history: {
			history: false
		    }
		})).get().on('pnotify.confirm', function() {
		    window.open('http://159.65.148.176/file-server/assessment/docs/Project_ReadMe.docx', '_blank');
		}).on('pnotify.cancel', function() {
		   
		});
	}
	
	
	function submitFullStackCode(qMapperInstanceId){
		
		
		
		var url = 'submitFullStackCode?qMapperInstanceId='+qMapperInstanceId;
	
	var data = {}; 
	
	
		$.ajax({
				type : 'GET',
				url : url,
				contentType: "application/json; charset=utf-8",
				data: data,
				success : function(data) {
					console.log("SUCCESS: ", data);
					notify('Information', data);
					
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				}
			});
	}
	
	function uploadProjectDocs(){
		qMapperInstanceId = '${currentQuestion.questionMapperInstance.id}';
		 var formData = new FormData();
		  var file = $('#addimage')[0].files[0];
		  console.log('1 file is '+file);
		  if(file != null){
			formData.append('addimage', file);
            console.log("form data " + formData);
            $.ajax({
                url : 'uploadProjectDocs?qMapperInstanceId='+qMapperInstanceId,
				enctype: 'multipart/form-data',
                data : formData,
                processData : false,
                contentType : false,
                type : 'POST',
                success : function(data) {
                     notify('Information','Your file has been uploaded. Check this link <br\>-<a href="'+data+'" >Click here</a> ');
                },
                error : function(err) {
                   notify('Information',err);
                }
            });
		  }
		  else{
			  notify('Information', 'Kindly upload the Project Documentations word file');
		  }
            
	}

	</script>
	
	<!-- The Modal -->
	<div id="myModal" class="modal">

	  <!-- Modal content -->
	  <div class="modal-content">
	    <span class="close">&times;</span>
	    <p id="showAlert">Some text in the Modal..</p>
	  </div>

	</div>
	
	
	
	
	
	
	<script>
		
		// Get the modal
		var modal = document.getElementById('myModal');

		// Get the button that opens the modal
		var btn = document.getElementById("myBtn");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];
			
		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
		    modal.style.display = "none";
		}	
		
	
		
		function notify(messageType, message){
		 var notification = 'Information';
		 //PNotify.prototype.options.styling = "jqueryui";
			 $(function(){
				 new PNotify({
				 title: notification,
				 text: message,
				 type: messageType,
				 width: '60%',
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


		$(document).ready(function () {
    			//Disable cut copy paste
    			$('body').bind('cut copy paste', function (e) {
        			e.preventDefault();
    			});
   
    			//Disable mouse right click
    			$("body").on("contextmenu",function(e){
       				return false;
    			});
				
				 function disablePrev() { window.history.forward() }
					window.onload = disablePrev();
					window.onpageshow = function(evt) { if (evt.persisted) disableBack() 
				}
		});
	</script>
</body>
</html>
