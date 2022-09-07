<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*,com.assessment.web.dto.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>eAssess</title>
      <link href="images/E-assess_E.png" rel="shortcut icon">
	
	  <link rel="stylesheet" href="newstudentjourney/css/font-awesome.min.css">
        <link rel="stylesheet" href="newstudentjourney/css/bootstrap.min.css">
        <link rel="stylesheet" href="newstudentjourney/css/style_1.css">
	
	
	
	<link href="css_new/pnotify.custom.min.css" rel="stylesheet" type="text/css">
		
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.nonblock.js"></script>
	<script type="text/javascript" src="scripts/pnotify.buttons.js"></script>
	<script src="scripts/src-min-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>		
	<style type="text/css">
	
body.post-login form section .match-following {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
}

body.post-login form section .match-following ul:first-child {
    padding-bottom: 20px;
    margin-bottom: 20px;
/*     border-bottom: 1px solid #ccc; */
}

body.post-login form section .match-following ul li {
    background-color: #f3f3f3;
    border-radius: 3px;
    margin-bottom: 5px;
    padding: 10px;
    color: #000;
    text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.4);
}

body.post-login form section .match-following ul li#q1 {
    background-color: red;
}

body.post-login form section .match-following ul li#q2 {
    background-color: green;
}

body.post-login form section .match-following ul li#q3 {
    background-color: blue;
}

body.post-login form section .match-following ul li#q4 {
    background-color: pink;
}

body.post-login form section .match-following ul li#q5 {
    background-color: #eb1587;
}

body.post-login form section .match-following ul li#q6 {
    background-color: #bafc35;
}

body.post-login form section .assessment-progress {
    background-color: #f3f3f3;
    padding: 10px;
}
	</style>
	<script type="text/javascript">
		// PNotify.prototype.options.styling = "fontawesome";
		var correctNumberOfChoices = ('${currentQuestion.questionMapperInstance.questionMapper.question.rightChoices}'.match(/-/g) || []).length  + 1;		var active = 'true';
		var studentNameTaken = localStorage.getItem('${studentTestForm.firstName}${studentTestForm.lastName}');
		var testNameTaken = localStorage.getItem('testName-${studentTestForm.firstName}${studentTestForm.lastName}');
		var tc= localStorage.getItem('timeCounter-${studentTestForm.firstName}${studentTestForm.lastName}');
		var firsttime = '${firstpage}';

		// console.log('firsttime '+firsttime);
			if(firsttime != null && firsttime == 'yes'){
				console.log('timeCounter getting');	
				timeCounter = ${timeCounter};
			} else {
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
		//document.getElementById("hours").value = hours;
		//document.getElementById("min").value = minutes;
		//document.getElementById("sec").value = seconds;
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
			//if(!notificationsend){
				var time = localStorage.getItem('not10min-${studentTestForm.emailId}-${studentTestForm.testName}');
				var diffInMinutes = null;
				if(time != null){
					var d1 = new Date();
					var n1 = d1.valueOf();
					diffInMinutes = Math.floor( (n1 - time) / 60000);
				}
				
				
				if((${studentTestForm.duration} * 60) - timeCounter <= 600 ){
					console.log('trying to notify 10 minutes before diffInMinutes is '+diffInMinutes);
					if(diffInMinutes == null || diffInMinutes >= 3){
						console.log('notify 10 minutes before');
					var d = new Date();
					var n = d.valueOf();
					localStorage.setItem('not10min-${studentTestForm.emailId}-${studentTestForm.testName}', n);
				
					tme = (${studentTestForm.duration} * 60) - timeCounter;
					mnts =(tme/60) % 60;
					notify('Info', 'You have about '+Math.round(mnts)+' minutes to complete the test');
					notificationsend = true;
						
					}
					
				}
			//}
			
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
		//var myVar2 = setInterval(takeScreenShot, 5000);
		
		var myVar3 = setInterval(updateTimeInBackEnd, 45000);
		
	</script>
</head>
    <body id="bodyid" class="post-login" onload="setTimeOnLoad();">
	<%
QuestionInstanceDto dto = (QuestionInstanceDto)request.getAttribute("currentQuestion");
//System.out.println("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
//System.out.println("type ios "+dto.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionType().getType());
//System.out.println("type ios "+dto.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionText());

%>
        <div class="master-container">
            <header class="assessment">
                <div class="container-fluid">
                    <a href="#" class="yaksha-logo">
                        E<span>ASSESS</span>
                    </a>
                </div>
                <div class="subheader">
                    <div class="container-fluid">
                        <h1 class="my-auto">
                            ${studentTestForm.testName}
                        </h1>
                        <div class="quick-actions my-auto">
                            <span class="my-auto mr-2">${journeyUTF.timeRemaining}</span>
                            <span class="times" id="hours">
                                00
                            </span>
                            <span class="times" id="min">
                                00
                            </span><span class="times" id="sec">
                                00
                            </span>
                        </div>
                    </div>
                </div>
            </header>
			<form:form id="testForm" name="testForm" method="POST" modelAttribute="currentQuestion" enctype="multipart/form-data">
            <section class="content-section">
			
                <div class="container-fluid">
                    <div class="test-page">
                        <div class="main-section">
                            <ul class="sections">
							<c:forEach var="sectionInstance" varStatus="status" items="${sectionInstanceDtos}">
								<!--<li ${sectionInstance.style} onclick="javascript:changeSection('${sectionInstance.section.sectionName}');">
									<a href="#">
										${sectionInstance.section.sectionName}
									</a>
								</li> -->
								<li>
             <a href="#" ${sectionInstance.style} onclick="javascript:changeSection('${sectionInstance.section.sectionName}');" >
                                        ${sectionInstance.section.sectionName}
                                    </a>
                                </li>
							</c:forEach>
							
                                
                                
                            </ul>
                        <!--    <div class="mcq d-none"> -->
						<c:choose>
						<c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='MCQ' || currentQuestion.questionMapperInstance.questionMapper.question.type ==null}">
						  <div class="mcq">
                                <div class="question mb-3">
                                    <div class="thumbnail mr-2">
                                        <span>${currentQuestion.position}</span>
                                    </div>
                                    <div class="question-title my-auto">
                                        <p>${currentQuestion.questionMapperInstance.questionMapper.question.questionText}</p>
                                   <!--     <img src="images/fullstack.jpeg" width="320" alt="" class="mb-3">-->
                                        
                                    
										<c:if test="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl != null && currentQuestion.questionMapperInstance.questionMapper.question.imageUrl.trim().length() > 0}">
											<img src="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl}" width="320" class="mt-3">
										</c:if>

										<c:if test="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL != null && currentQuestion.questionMapperInstance.questionMapper.question.audioURL.trim().length() > 0}">
											<audio controls src="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL}" width="320">
												Your browser does not support the <code>audio</code> element.
											</audio>
										</c:if>

										<c:if test="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL != null && currentQuestion.questionMapperInstance.questionMapper.question.videoURL.trim().length() > 0}">
											<video controls>
												<source src="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL}" width="320"> 
												Your browser does not support the video tag.
											</video>
										</c:if>
									</div> 
                                </div>
								<%
								QuestionInstanceDto currentQuestion = (QuestionInstanceDto) request.getAttribute("currentQuestion");
								System.out.println(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getRightChoices());
								int cc = currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getRightChoices().split("-").length;
								String check = "checkbox";
									if(cc == 1) {
										check = "radio";
									System.out.println(" cc is "+cc);
									
									}
								request.setAttribute("check", check);
								String c3 = currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getChoice3();	
								String c4 = currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getChoice4();	
								String c5 = currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getChoice5();	
								String c6 = currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getChoice6();	
								String stylec3 = "display:''";
									if(c3 == null || c3.trim().length() == 0){
										stylec3 = "display:none";
									}
									
								String stylec4 = "display:''";
									if(c4 == null || c4.trim().length() == 0){
										stylec4 = "display:none";
									}
									
								String stylec5 = "display:''";
									if(c5 == null || c5.trim().length() == 0){
										stylec5 = "display:none";
									}
									
								String stylec6 = "display:''";
									if(c6 == null || c6.trim().length() == 0){
										stylec6 = "display:none";
									}
								%>
                                <ul class="answers ml-5">
                                    <li class="mb-3">
                                        <div class="checkbox my-auto mr-3">
                                            <!-- <input type="checkbox" name="check1" id="check1">  ---> <!-- radio --->
											
											<c:choose>
											  <c:when test="${check == 'checkbox'}">
												<form:checkbox path="one"  id="check1" />
											  </c:when>
											  <c:otherwise>
												<form:radiobutton path="radioAnswer" id="one" value="one"  />
											  </c:otherwise>
											</c:choose>
											
											
											
											
                                        </div>
                                        <p class="my-auto">${currentQuestion.questionMapperInstance.questionMapper.question.choice1}</p>
                                    </li>
                                    <li class="mb-3">
                                        <div class="checkbox my-auto mr-3">
                                            <c:choose>
											  <c:when test="${check == 'checkbox'}">
												<form:checkbox path="two"  id="check2" />
											  </c:when>
											  <c:otherwise>
												<form:radiobutton path="radioAnswer" id="two" value="two"  />
											  </c:otherwise>
											</c:choose>
                                        </div>
                                        <p class="my-auto">${currentQuestion.questionMapperInstance.questionMapper.question.choice2}</p>
                                    </li>
                                    <li class="mb-3" style="<%= stylec3%>">
                                        <div class="checkbox my-auto mr-3">
                                            <c:choose>
											  <c:when test="${check == 'checkbox'}">
												<form:checkbox path="three"  id="check3" />
											  </c:when>
											  <c:otherwise>
												<form:radiobutton path="radioAnswer" id="three" value="three"  />
											  </c:otherwise>
											</c:choose>
                                        </div>
                                        <p class="my-auto">${currentQuestion.questionMapperInstance.questionMapper.question.choice3}</p>
                                    </li>
                                    <li class="mb-3" style="<%= stylec4%>">
                                        <div class="checkbox my-auto mr-3">
                                            <c:choose>
											  <c:when test="${check == 'checkbox'}">
												<form:checkbox path="four"  id="check4" />
											  </c:when>
											  <c:otherwise>
												<form:radiobutton path="radioAnswer" id="four" value="four"  />
											  </c:otherwise>
											</c:choose>
                                        </div>
                                        <p class="my-auto">${currentQuestion.questionMapperInstance.questionMapper.question.choice4}</p>
                                    </li>
									<li class="mb-3" style="<%= stylec5%>">
                                        <div class="checkbox my-auto mr-3">
                                            <c:choose>
											  <c:when test="${check == 'checkbox'}">
												<form:checkbox path="five"  id="check5" />
											  </c:when>
											  <c:otherwise>
												<form:radiobutton path="radioAnswer" id="five" value="five"  />
											  </c:otherwise>
											</c:choose>
                                        </div>
                                        <p class="my-auto">${currentQuestion.questionMapperInstance.questionMapper.question.choice5}</p>
                                    </li>
									<li class="mb-3" style="<%= stylec6%>">
                                        <div class="checkbox my-auto mr-3">
                                            <c:choose>
											  <c:when test="${check == 'checkbox'}">
												<form:checkbox path="six"  id="check6" />
											  </c:when>
											  <c:otherwise>
												<form:radiobutton path="radioAnswer" id="six" value="six"  />
											  </c:otherwise>
											</c:choose>
                                        </div>
                                        <p class="my-auto">${currentQuestion.questionMapperInstance.questionMapper.question.choice6}</p>
                                    </li>
                                </ul>
                            </div>
							</c:when>
							<c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='CODING'}">
                            <div class="coding">
                                <div class="question mb-5">
                                    <div class="thumbnail mr-2">
                                        <span>${currentQuestion.position}</span>
                                    </div>
                                    <div class="question-title my-auto">
                                        <p>
										<ul>${currentQuestion.questionMapperInstance.questionMapper.question.qualifier1} ${currentQuestion.questionMapperInstance.questionMapper.question.qualifier2} ${currentQuestion.questionMapperInstance.questionMapper.question.qualifier3} ${currentQuestion.questionMapperInstance.questionMapper.question.qualifier4} ${currentQuestion.questionMapperInstance.questionMapper.question.qualifier5}
										</ul> - ${currentQuestion.questionMapperInstance.questionMapper.question.questionText}
										</p>
										<c:if test="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl != null && currentQuestion.questionMapperInstance.questionMapper.question.imageUrl.trim().length() > 0}">
											<img src="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl}" width="320" class="mt-4">
										</c:if>

										<c:if test="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL != null && currentQuestion.questionMapperInstance.questionMapper.question.audioURL.trim().length() > 0}">
											<audio controls src="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL}" width="320">
												Your browser does not support the <code>audio</code> element.
											</audio>
										</c:if>

										<c:if test="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL != null && currentQuestion.questionMapperInstance.questionMapper.question.videoURL.trim().length() > 0}">
											<video controls>
												<source src="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL}" width="320"> 
												Your browser does not support the video tag.
											</video>
										</c:if>
                                    </div>
                                </div>
                                <div class="answer">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="form-group">
                                                <label for="">Code</label>
                                                <!--<textarea name="" id="" cols="30" rows="5" class="form-control"></textarea> -->
												<form:textarea id="editor" path="code" wrap="physical" rows="10" style="height: 300px;" />
												<form:hidden path="code" id="codeOfEditor" wrap="physical"/>
                                            </div>
										</div>
										<div class="col-xs-12 col-md-6">
											<div class="form-group">
												<label for="input">Input</label>
												<form:input path="input" id="input" class="form-control" placeholder="Enter input" />
											</div>
										</div>
										<div class="col-xs-12 col-md-6 text-right my-auto">
											<button class="btn btn-primary mr-2" type="button" onClick="runCodeSystemTestCase()">Run System Test Case</button>
											<button class="btn btn-primary" type="button" onClick="runCode()">Run Code</button>
										</div>
										<div class="col-12">
                                            <div class="form-group">
                                                <label for="">Output</label>
                                               <!-- <textarea name="" id="" cols="30" rows="10" class="form-control"></textarea> -->
											   <form:textarea path="output" id="output" disabled="true" class="form-control" rows="10" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
							</c:when>
							<c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='FILL_BLANKS_MCQ'}">
							    <div class="mcq">
                                <div class="question mb-3">
                                    <div class="thumbnail mr-2">
                                        <span>${currentQuestion.position}</span>
                                    </div>
                                    <div class="question-title my-auto">
                                        <p>${currentQuestion.questionMapperInstance.questionMapper.question.questionText}</p>
                                   <!--     <img src="images/fullstack.jpeg" width="320" alt="" class="mb-3">-->
                                        <% 
										QuestionInstanceDto currentQuestion = (QuestionInstanceDto) request.getAttribute("currentQuestion");
										System.out.println("fill in the blanks "+currentQuestion.getFillInBlanks().size());
									%>
                                    
										 <c:forEach varStatus="s" items="${currentQuestion.fillInBlanks}">
										<div class="fill-up mb-2">
												<form:input path="fillInBlanks[${s.index}]" placeholder="Fill the Blank" class="form-control" style=" width: 300%;"/>
											</div>
										</c:forEach>

										 

										 
									</div> 
                                </div>
							  
							  </div>
					</c:when>
					<c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='MATCH_FOLLOWING_MCQ'}">
						<div class="mcq">
                                <div class="question mb-3">
                                    <div class="thumbnail mr-2">
                                        <span>${currentQuestion.position}</span>
                                    </div>
                                    <div class="question-title my-auto">
                                        <p>${currentQuestion.questionMapperInstance.questionMapper.question.questionText}</p>
                                    </div>
                            </div>
                       </div>
						
						<div class="match-following mt-3 ml-5" style="flex-direction: row;">
							<ul class="question2" style="width: 50%">
								<li id="q1">
									${currentQuestion.questionMapperInstance.questionMapper.question.matchLeft1}
								</li>
								<li id="q2">
									${currentQuestion.questionMapperInstance.questionMapper.question.matchLeft2}
								</li>
								<c:if test="${currentQuestion.mtfSize > 2}">
									<li id="q3">${currentQuestion.questionMapperInstance.questionMapper.question.matchLeft3}</li>
								</c:if>
						
								<c:if test="${currentQuestion.mtfSize > 3}">
									<li id="q4">${currentQuestion.questionMapperInstance.questionMapper.question.matchLeft4}</li>
								</c:if>
						
								<c:if test="${currentQuestion.mtfSize > 4}">
									<li id="q5">${currentQuestion.questionMapperInstance.questionMapper.question.matchLeft5}</li>
								</c:if>
						
								<c:if test="${currentQuestion.mtfSize > 5}">
									<li id="q6">${currentQuestion.questionMapperInstance.questionMapper.question.matchLeft6}</li>
								</c:if>
							</ul>
							<ul class="answer ml-5" style="width: 50%">
								<li id="a1">${currentQuestion.mtf.matchRight1Display}</li>
								<form:hidden path="mtf.matchRight1" value="${currentQuestion.mtf.matchRight1}"  />

								<li id="a2">${currentQuestion.mtf.matchRight2Display}</li>
								<form:hidden path="mtf.matchRight2" value="${currentQuestion.mtf.matchRight2}" />

								<c:if test="${currentQuestion.mtfSize > 2}">
									<li id="a3">${currentQuestion.mtf.matchRight3Display}</li>
									<form:hidden path="mtf.matchRight3" value="${currentQuestion.mtf.matchRight3}" />
								</c:if>
								<c:if test="${currentQuestion.mtfSize > 3}">
									<li id="a4">${currentQuestion.mtf.matchRight4Display}</li>
									<form:hidden path="mtf.matchRight4" value="${currentQuestion.mtf.matchRight4}" />
								</c:if>
								<c:if test = "${currentQuestion.mtfSize > 4}">
									<li id="a5">${currentQuestion.mtf.matchRight5Display}</li>
									<form:hidden path="mtf.matchRight5" value="${currentQuestion.mtf.matchRight5}" />
								</c:if>
								<c:if test="${currentQuestion.mtfSize > 5}">
									<li id="a6">${currentQuestion.mtf.matchRight6Display}</li>
									<form:hidden path="mtf.matchRight6" value="${currentQuestion.mtf.matchRight6}" />
								</c:if>
							</ul>
						</div>
					</c:when>
					<c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='IMAGE_UPLOAD_BY_USER' || currentQuestion.questionMapperInstance.questionMapper.question.type=='VIDEO_UPLOAD_BY_USER'}">
						<div class="mcq mt-3">
							<div class="formfield">
						<label class="quetestcases">Upload ${currentQuestion.questionMapperInstance.questionMapper.question.type=='IMAGE_UPLOAD_BY_USER'?'Image':'Video'}</label>
                                        
                       <input type="file" name="imageVideoData" id="imageVideoData" > 
					   <form:hidden path = "type" value = "${currentQuestion.questionMapperInstance.questionMapper.question.type}" />
					   
					   <form:hidden path = "questionMapperId" value = "${currentQuestion.questionMapperInstance.questionMapper.id}" />
					   
					  <!--  <form:input type="file" path="imageVideoData" id="imageVideoData" accept="image/*" />-->
						</div>
						
						</div>
					</c:when>
					<c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='SUBJECTIVE_TEXT'}">
					<div class="mcq">
                                <div class="question mb-3">
                                    <div class="thumbnail mr-2">
                                        <span>${currentQuestion.position}</span>
                                    </div>
                                    <div class="question-title my-auto">
                                        <p>${currentQuestion.questionMapperInstance.questionMapper.question.questionText}</p>
                                   <!--     <img src="images/fullstack.jpeg" width="320" alt="" class="mb-3">-->
                                   <label > Answer the question</label><br/>
                                        <form:textarea path="questionMapperInstance.subjectiveText" class="form-control" style=" width: 424%;"/>    

										 
									</div> 
                                </div>
							  
							  </div>
					
					
					</c:when>
							</c:choose>
                            <div class="test-navigation">
								<div>
									<c:choose>
									<c:when test="${currentSection.first==true}"></c:when>
									<c:otherwise>
										<button type="button" class="btn btn-outline-secondary" onClick="prev()">
											${journeyUTF.prev}
										</button>
									</c:otherwise>
									</c:choose>
								</div>
							
                                
                                <div class="my-auto">
                                    ${noAnswered} / ${totalQuestions}
                                </div>
								<!--
                                <div>
                                    <button class="btn btn-primary" type="button">
                                        NEXT
                                    </button>
                                    <button class="btn btn-secondary" type="button">
                                        SKIP
                                    </button>
                                    <button class="btn btn-primary d-none" type="button">
                                        SUBMIT
                                    </button>
                                </div-->
								<c:choose>
									<c:when test="${currentSection.last==true}">
										<button id="next" type="button" class="btn btn-primary" onClick="submitTestCheckNoAnswer();" >
											${journeyUTF.submit}
										</button>
									</c:when>
									<c:otherwise>
										<button id="next" type="button" class="btn btn-primary" onClick="eerrtt()">
											${journeyUTF.next}
										</button>
									</c:otherwise>
								</c:choose>		
                            </div>
                        </div>
                        <div class="quick-info">
                            <a href="#">${journeyUTF.reviewInstructions}</a>
                            <div class="test-stats mt-4">
                                <h3 class="mb-3">${journeyUTF.questions} <!-- <small>(25)</small> --></h3>
                                <ul class="questionbookmark">
								<c:forEach var="instance" varStatus="loop" items="${currentSection.questionInstanceDtos}">
                                    <li>
                                        <a href="javascript:goToQuestion('${loop.count}')" class="${(instance.questionMapperInstance.answered != null && instance.questionMapperInstance.answered)?'completed':'skipped'}">
                                            ${loop.count}
                                        </a>
										<!--
										skipped
										completed
										active
										-->
                                    </li>
								</c:forEach>
                                    
                                </ul>
                                <h3 class="mt-3"> ${journeyUTF.testStats}</h3>
                                <div class="stats mt-2">
                                    <div class="completed">
                                        
                                    </div>
                                    <div class="my-auto ml-3">${journeyUTF.answered}</div>
                                </div>
                                <div class="stats mt-2">
                                    <div class="skipped">
                                        
                                    </div>
                                    <div class="my-auto ml-3">${journeyUTF.notAnswered}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
			</form:form>
            <footer>
                <div class="container-fluid text-right">
                    <span class="copyright">
                        &copy; Copyright 2020-2021 - eAssess
                    </span>
                    <a href="#">
                        ${journeyUTF.termsAndConditions}
                    </a>
                    <a href="#">
						${journeyUTF.privacyPolicy}
                    </a>
                </div>
            </footer>
        </div>
		
		<script>
	var randomNumber = Math.floor(Math.random() * (6 - 1 + 1)) + 1;
	$('header').attr('style', "background-image: url(images_new/banner_" + randomNumber + ".jpg);");

	$('.des-toggler').on('click', function () {
		var parent = $(this).closest('.descriptions');
		if(parent.hasClass('hidden')) {
			parent.removeClass('hidden');
			$('.main-content').removeClass('expand');
		} else {
			parent.addClass('hidden');
			$('.main-content').addClass('expand');
		}
		
		// var $this = $(this),
		// 	attr = $this.closest('.descriptions').attr('style');

		// if (typeof attr !== typeof undefined && attr !== false) {
		// 	$this.find('i').html('keyboard_arrow_right');
		// 	$this.closest('.descriptions').find('.des-content').show();
		// 	$this.closest('.descriptions').removeAttr('style');
		// 	$this.removeAttr('style');
		// 	$('.session-content').removeAttr('style');
		// } else {
		// 	$this.find('i').html('keyboard_arrow_left');
		// 	$this.closest('.descriptions').find('.des-content').hide();
		// 	$this.closest('.descriptions').attr('style', 'width:0px; margin-left:100%; padding: 0');
		// 	$this.attr('style', 'left: -40px');
		// 	$('.session-content').attr('style', 'width: 95%');
		// }
	});

	var editor = ace.edit("editor");
	editor.setTheme("ace/theme/tomorrow_night_blue");
	//editor.setTheme("ace/theme/mono_industrial");
	
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
	minLines: 10,
	fontSize: "100%" // ensures that the editor fits in the environment
});

</script>
<script>
	var questionColor = '';
	$(document).ready(function(){
		$('.question2 li').click(function(){
			questionColor = $(this).css("background-color");
			$('.question2 li').removeAttr('style');
			$(this).css('box-shadow', '0 0 10px 3px rgba(0,0,0,0.4)');
		});
		$('.answer li').click(function(){
			$(".answer li").each(function( i ) {
				if($(this).css("background-color") == questionColor){
					$(this).removeAttr('style');
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
			window.location = 'changeSectionNew?sectionName='+sectionName+"&timeCounter="+timeCounter;
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
		window.location = 'changeSectionNew?sectionName='+sectionName+"&timeCounter="+timeCounter;
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
var input = "${currentQuestion.questionMapperInstance.questionMapper.question.hiddenInputPositive}";
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
				if(data == "${currentQuestion.questionMapperInstance.questionMapper.question.hiddenOutputPositive}"){
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

document.testForm.action = "prevQuestionNew?questionId=${currentQuestion.questionMapperInstance.questionMapper.id}&timeCounter="+timeCounter;
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

document.testForm.action = "submitTestNew?questionId=${currentQuestion.questionMapperInstance.questionMapper.id}&timeCounter="+timeCounter;
resetTimeLocal();

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
var uanswered = '${totalQuestions - (noAnswered+1)}';
	if(uanswered == '0'){
		notify('Information', 'DO NOT REFRESH the page post submission');
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

function goToQuestion(no){
	
	(new PNotify({
		title: 'Confirmation Needed',
		text: 'If you are sure if the answer is saved, please proceed. you can save a answer by a simple click on Next or Prev button ',
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
		window.location = "goToQuestion?questionSeq="+no+"&timeCounter="+timeCounter;
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

<script>

	function eerrtt(){
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
		document.testForm.action = "nextQuestionNew?questionId=${currentQuestion.questionMapperInstance.questionMapper.id}&timeCounter="+timeCounter;
		storeTimeLocal();
		document.testForm.submit();
		}

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

	// Questions answered and skipped count
	var skippedCount = $('ul.questionbookmark li a.skipped').length;
	var answeredCount = $('ul.questionbookmark li a.completed').length;
	$('.stats .completed').html(answeredCount);
	$('.stats .skipped').html(skippedCount);

</script>
	<script src="scripts/src-min-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
    </body>
</html>