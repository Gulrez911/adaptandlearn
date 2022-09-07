<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*,com.assessment.web.dto.*"%>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Adapt and Learn MCQ </title>

    <!-- Bootstrap core CSS -->
    <link href="b2c/css/bootstrap.min.css" rel="stylesheet" />
    <link href="b2c/css/assessment.css" rel="stylesheet" />

    <!-- Google Font -->
      <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;1,200;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">
	  
	 <script src="scripts/src-min-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>	
	<script type="text/javascript">
		
		var correctNumberOfChoices = ('${currentQuestion.questionMapperInstance.questionMapper.question.rightChoices}'.match(/-/g) || []).length  + 1;		var active = 'true';
		var studentNameTaken = localStorage.getItem('${studentTestForm.firstName}${studentTestForm.lastName}');
		var testNameTaken = localStorage.getItem('testName-${studentTestForm.firstName}${studentTestForm.lastName}');
		var tc= localStorage.getItem('timeCounter-${studentTestForm.firstName}${studentTestForm.lastName}');
		var firsttime = '${firstpage}';

		
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
		document.getElementById("hourSpan").innerHTML = hours;
		document.getElementById("minSpan").innerHTML = minutes;
		document.getElementById("secSpan").innerHTML = seconds;
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
  <body> 
  	<%
	QuestionInstanceDto dto = (QuestionInstanceDto)request.getAttribute("currentQuestion");


	%>
    <header>
      <div class="logo"> <img src="b2c/images/logo.png" alt="LOGO"> </div>
          <h1>${studentTestForm.testName}</h1>
          <div class="time-board">
            <label>Time Remaining</label>
            <!--<span>01:11:30</span> -->
				<span  id="hourSpan">
					00
				</span>
				<span  id="minSpan">
					00
				</span>
				<span  id="secSpan">
					00
				</span>
          </div>
    </header>
	<form:form id="testForm" name="testForm" method="POST" modelAttribute="currentQuestion" enctype="multipart/form-data">
    <div class="container assessment-wrapper">
        <div class="row my-4">
          <div class="col-md-2">
              <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
			  <c:forEach var="sectionInstance" varStatus="status" items="${sectionInstanceDtos}">
                <!--<a class="nav-link" id="v-pills-1-tab" data-toggle="pill" href="#v-pills-1" role="tab" aria-controls="v-pills-1" aria-selected="true"> <span> Main Section </span></a>
                <a class="nav-link active" id="v-pills-2-tab" data-toggle="pill" href="#v-pills-2" role="tab" aria-controls="v-pills-2" aria-selected="false"> <span> Java Coding </span></a> -->
				
				 <a href="#" ${sectionInstance.style} onclick="javascript:changeSection('${sectionInstance.section.sectionName}');" >
                                      <span> ${sectionInstance.section.sectionName}</span>  
                                    </a>
				
              </c:forEach> 
              </div>
          </div>
          <div class="col-md-10">
              <div class="tab-wrapper">
                <div class="tab-content" id="v-pills-tabContent">
				 <!--    <div class="mcq d-none"> -->
				  <%
						QuestionInstanceDto currentQ = (QuestionInstanceDto) request.getAttribute("currentQuestion");
						boolean audio = false;
						boolean vid = false;
						boolean img = false;
						
							if(currentQ.getQuestionMapperInstance().getQuestionMapper().getQuestion().getAudioURL() != null && currentQ.getQuestionMapperInstance().getQuestionMapper().getQuestion().getAudioURL().trim().length() > 0){
								audio = true;
							}
							
							if(currentQ.getQuestionMapperInstance().getQuestionMapper().getQuestion().getImageUrl() != null && currentQ.getQuestionMapperInstance().getQuestionMapper().getQuestion().getImageUrl().trim().length() > 0){
								img = true;
							}
							
							if(currentQ.getQuestionMapperInstance().getQuestionMapper().getQuestion().getVideoURL() != null && currentQ.getQuestionMapperInstance().getQuestionMapper().getQuestion().getVideoURL().trim().length() > 0){
								vid = true;
							}
							
						boolean secNeeded = false;
							if(audio || img || vid){
								secNeeded = true;
							}
						%>
				 
				<c:choose>
					<c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='MCQ' || currentQuestion.questionMapperInstance.questionMapper.question.type ==null}">
                  <div  id="v-pills-1"  aria-labelledby="v-pills-1-tab">
                      <div class="question-header">
                          <div class="question-parent"> <span class="q-number">${currentQuestion.position}</span> ${currentQuestion.questionMapperInstance.questionMapper.question.questionText}</div>
                          <div> ${noAnswered} / ${totalQuestions}</div>
                      </div>
					  
					 
						
						<c:if test="${secNeeded == true}">
					  <div class="col-md-6">
						
						
						<c:if test="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl != null && currentQuestion.questionMapperInstance.questionMapper.question.imageUrl.trim().length() > 0}">
							<img src="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl}" width="100%">
						</c:if>

						<c:if test="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL != null && currentQuestion.questionMapperInstance.questionMapper.question.audioURL.trim().length() > 0}">
							<audio controls src="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL}" width="100%">
								Your browser does not support the <code>audio</code> element.
							</audio>
						</c:if>

						<c:if test="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL != null && currentQuestion.questionMapperInstance.questionMapper.question.videoURL.trim().length() > 0}">
							<video controls>
								<source src="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL}" width="100%"> 
								Your browser does not support the video tag.
							</video>
						</c:if>
						
						
					  </div>
					</c:if>
					
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
					  
                      <div class="question-content">
							
					  
					  
                          <div class="question-options active">
                            <div class="custom-control custom-radio">
                              <c:choose>
								  <c:when test="${check == 'checkbox'}">
									<form:checkbox path="one"  style="margin-right:15px;margin-left: -30px;"  />
									<label style="word-wrap: break-word; ">${currentQuestion.questionMapperInstance.questionMapper.question.choice1}</label>
								  </c:when>
								  <c:otherwise>
								  
									<form:radiobutton path="radioAnswer" id="one" value="one"   class="custom-control-input" />
									<label class="custom-control-label text-break" for="one" style="font-size: 13px;">${currentQuestion.questionMapperInstance.questionMapper.question.choice1}
										</label>
								  </c:otherwise>
								</c:choose>
				
                            </div>
                          </div>
                          <div class="question-options">
                            <div class="custom-control custom-radio">
                              <c:choose>
								  <c:when test="${check == 'checkbox'}">
									<form:checkbox path="two"  style="margin-right:15px;margin-left: -30px;"  />
									${currentQuestion.questionMapperInstance.questionMapper.question.choice2}
								  </c:when>
								  <c:otherwise>
								  
									<form:radiobutton  path="radioAnswer" id="two" value="two"   class="custom-control-input" />
									<label class="custom-control-label text-break" for="two" style="font-size: 13px;">${currentQuestion.questionMapperInstance.questionMapper.question.choice2} 
										</label>
								  </c:otherwise>
								</c:choose>
                            </div>
                          </div>
                          <div class="question-options" style="<%= stylec3%>">
                            <div class="custom-control custom-radio">
                              <c:choose>
								  <c:when test="${check == 'checkbox'}">
									<form:checkbox path="three"  style="margin-right:15px;margin-left: -30px;"  />
									${currentQuestion.questionMapperInstance.questionMapper.question.choice3}
								  </c:when>
								  <c:otherwise>
								  
									<form:radiobutton  path="radioAnswer" id="three" value="three"  class="custom-control-input"   />
									<label class="custom-control-label text-break" for="three" style="font-size: 13px;">${currentQuestion.questionMapperInstance.questionMapper.question.choice3} 
										</label>
								  </c:otherwise>
								</c:choose>
                            </div>
                          </div>
                          <div class="question-options" style="<%= stylec4%>">
                            <div class="custom-control custom-radio">
                              <c:choose>
								  <c:when test="${check == 'checkbox'}">
									<form:checkbox path="four"  style="margin-right:15px;margin-left: -30px;"  />
									${currentQuestion.questionMapperInstance.questionMapper.question.choice4}
								  </c:when>
								  <c:otherwise>
								  
									<form:radiobutton  path="radioAnswer" id="four" value="four"   class="custom-control-input"  />
									<label class="custom-control-label text-break" for="four" style="font-size: 13px;">${currentQuestion.questionMapperInstance.questionMapper.question.choice4} 
										</label>
								  </c:otherwise>
								</c:choose>
                            </div>
                          </div>
                          <div class="question-options" style="<%= stylec5%>">
                            <div class="custom-control custom-radio">
                              <c:choose>
								  <c:when test="${check == 'checkbox'}">
									<form:checkbox path="five"  style="margin-right:15px;margin-left: -30px;"  />
									<label style="word-wrap: break-word; ">${currentQuestion.questionMapperInstance.questionMapper.question.choice5}</label>
								  </c:when>
								  <c:otherwise>
								  
									<form:radiobutton  path="radioAnswer" id="five" value="five" class="custom-control-input"   />
									<label class="custom-control-label text-break" for="five" style="font-size: 13px;">${currentQuestion.questionMapperInstance.questionMapper.question.choice5} 
										</label>
								  </c:otherwise>
								</c:choose>
                            </div>
                          </div>
                          <div class="question-options" style="<%= stylec6%>">
                            <div class="custom-control custom-radio">
                              <c:choose>
								  <c:when test="${check == 'checkbox'}">
									<form:checkbox path="six"  style="margin-right:15px;margin-left: -30px;"  />
									${currentQuestion.questionMapperInstance.questionMapper.question.choice6}
								  </c:when>
								  <c:otherwise>
								  
									<form:radiobutton  path="radioAnswer" id="six" value="six"  class="custom-control-input" />
									<label class="custom-control-label text-break" for="six" style="font-size: 13px;">${currentQuestion.questionMapperInstance.questionMapper.question.choice6} 
										</label>
								  </c:otherwise>
								</c:choose>
                            </div>
                          </div>
                      </div>
                  </div>
				  </c:when>
				  <c:when test="${currentQuestion.questionMapperInstance.questionMapper.question.type=='CODING'}">
                  <div class="tab-pane fade show active" id="v-pills-2" role="tabpanel" aria-labelledby="v-pills-2-tab">
                    <div class="question-header">
                          <div class="question-parent"> <span class="q-number">${currentQuestion.position}</span> ${currentQuestion.questionMapperInstance.questionMapper.question.questionText}</div>
                          <div> ${noAnswered} / ${totalQuestions}</div>
                      </div>
					  
					  <c:if test="${secNeeded == true}">
					  <div class="col-md-6">
						
						
						<c:if test="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl != null && currentQuestion.questionMapperInstance.questionMapper.question.imageUrl.trim().length() > 0}">
							<img src="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl}" width="100%">
						</c:if>

						<c:if test="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL != null && currentQuestion.questionMapperInstance.questionMapper.question.audioURL.trim().length() > 0}">
							<audio controls src="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL}" width="100%">
								Your browser does not support the <code>audio</code> element.
							</audio>
						</c:if>

						<c:if test="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL != null && currentQuestion.questionMapperInstance.questionMapper.question.videoURL.trim().length() > 0}">
							<video controls>
								<source src="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL}" width="100%"> 
								Your browser does not support the video tag.
							</video>
						</c:if>
						
						
					  </div>
					</c:if>
					  
                      <div class="question-codesection">
                          <div id="main" class="push">
                            <span class="toggle-arrow" > <img src="b2c/images/toggle-arrow.png" alt=""></span>
                            <form>
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">Code</label>
                                <div class="codebox">
                                   <form:textarea id="editor" path="code" wrap="physical" rows="10" style="width:100%" />
									<form:hidden path="code" id="codeOfEditor" wrap="physical"/>
                                </div>
                              </div>
                              <div class="form-group">
                                <label for="input">Input</label>
                                <form:input path="input" id="input" class="form-control" placeholder="Enter input" />
                              </div>
                              <div class="form-group mb-5">
                                <button type="button" class="btn btn-primary" id="idSystemTestCase" onclick="runCodeSystemTestCase();">Run System Test Case</button>
                                <button type="button" class="btn btn-primary" id="idRunCode" onclick="runCode();">Run Code</button>
                              </div>
                              <div class="form-group">
                                <label for="output">Output</label>
                                <form:textarea path="output" id="output" disabled="true" class="form-control" rows="10" placeholder="Output Generated by running your program"/>
                              </div>
                              
                            
                            </div>
                            <div id="mySidenav" class="sidenav open">
							
							<%
								QuestionInstanceDto currentQuestion = (QuestionInstanceDto) request.getAttribute("currentQuestion");
								String ins = currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getInstructionsIfAny();
								//ins = null;
								if(ins == null || ins.trim().length() == 0){
									ins = "<p>1.	System test case is triggered by a click on 'Run System Test Case' button. The input data needed for this is provided by the back end.</p><p>2.	A click on the 'Run Code' button will run your program against the input entered by you in the input box (Enter Input).</p><p>3.	Your program will only be submitted if you click on Prev or Next/Submit buttons in the bottom of the page. </p><p>4.	Points 1 and 2 above only help you in testing your program prior to a submit.</p>";
								}
							
							%>
				
                            <label>Click on the arrow to hide the instructions.</label>
                            <div class="nav-inner">
                              <h6>Instructions:</h6>
                              <%= ins %>
                            </div>
                            </div>
                      </div>
                  </div>
                 </c:when>
				 </c:choose>
                </div>
                <div class="questions-btns">
					<c:choose>
						<c:when test="${currentSection.first==true}"></c:when>
						<c:otherwise>
							<button onClick="prev()"  >
								<!--${journeyUTF.prev} -->
								Previous
							</button>
						</c:otherwise>
					</c:choose>
                   <!--  <button>Back</button>  -->
				   
				  <button class="review" id="openReviewDialog" >Flag Q</button> 
				  <!-- <a href="#" class="mx-4 font18" onclick="openMarkReview();">
					<img src="b2c/images/review.png" class="mr-1" width="20px" /> Mark for
					Review
				  </a> -->
				   
                    <!-- <button class="review">Mark for Review</button> -->
					<c:choose>
						<c:when test="${currentSection.last==true}">
							<button id="next"  class="notanswered" onClick="submitTestCheckNoAnswer();" type="button">
								<!-- ${journeyUTF.submit} -->
								Submit
							</button>
						</c:when>
						<c:otherwise>
							<button id="next"  class="notanswered" onClick="eerrtt()">
								
								Next
							</button>
						</c:otherwise>
					</c:choose>		
                   <!-- <button class="notanswered">Next</button>-->
                </div>
              </div>
          </div>
        </div>
        <div class="question-legends">
            <div>Test States:</div>
            <div class="status">
              <span class="current">${currentQuestion.position}</span>
              <span>Current</span>
            </div>
            <div class="status">
              <span class="answerd" id="answeredCount"></span>
              <span>Answerd </span>
            </div>
            <div class="status">
              <span class="review" id="markedCount">00</span>
              <span>Marked for Review</span>
            </div>
            <div class="status">
              <span class="notanswered" id="skippedCount"></span>
              <span>Not Answered</span>
            </div>
        </div>
      <div class="circle-steps">
      <div class="title">Questions</div>
        <ul>
		<c:forEach var="instance" varStatus="loop" items="${currentSection.questionInstanceDtos}">
			
			
				
				
			<li  class="${loop.count == currentQuestion.position?'active current':(instance.questionMapperInstance.answered != null && instance.questionMapperInstance.answered)?'':'notanswered'}"  onclick="javascript:goToQuestion('${loop.count}')"  style="cursor:pointer;"> ${loop.count} </li>
				
				
			</c:forEach>
		
          
        </ul>
      </div>
    </div>
	
	<!--Modal for captured question mark review comment -->
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
		  <div class="modal-header">
			
			<h4 class="modal-title"> Bring it to out notice if you feel we need a relook at the question</h4>
		  </div>
		  <div class="modal-body">
			<p style="font-size: 13px;">Please continue to answer the question even if you want to mark it for review. If you haven't answered yet, close the window and answer the Q first.</p>
			<!--<textarea id="form10" ></textarea> -->
			<form:textarea style="font-size: 13px;" path = "questionMarkedReviewText" class="md-textarea form-control" rows="8" placeholder="Enter Review Comment" />
		  </div>
		  <div class="modal-footer">
		    
			
			<button id="markForReview" type="button" class="btn btn-primary" onClick="flagQuestion()">
					  Mark it for review 
					</button>
					
			<button id="markForReviewClose" type="button" class="btn btn-primary"  data-dismiss="modal">
					  Close Window
					</button>
		  </div>
		</div>

	  </div>
	</div>
	
	<!--end Modal for captured question mark review comment -->
	
	</form:form>
		
	

    <footer>© All rights reserved. Made by Your Name</footer>
    <script src="b2c/js/jquery-3.5.1.min.js"></script>
    <script src="b2c/js/popper.min.js"></script>
    <script src="b2c/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
	
	<link href="css_new/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.nonblock.js"></script>
	<script type="text/javascript" src="scripts/pnotify.buttons.js"></script>
	<script src="scripts/src-min-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
	
	<script>
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
      (function ($) {

  $('.toggle-arrow').on('click', function clickHandler(e) {
    e.preventDefault();
    $('#mySidenav').toggleClass('open');
    $('#main').toggleClass('push');
  });

  })(jQuery);
  
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
	
	

	function flagQuestion(){
		if(${currentSection.last}){
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
				document.testForm.action = "markQuestionReviewVanNew?questionId=${currentQuestion.questionMapperInstance.questionMapper.id}&timeCounter="+timeCounter+"&finalQ=${currentSection.last==true?true:false}";
				storeTimeLocal();
				document.testForm.submit();
			}).on('pnotify.cancel', function() {
				
			});
		}
		else{
			document.testForm.action = "markQuestionReviewVanNew?questionId=${currentQuestion.questionMapperInstance.questionMapper.id}&timeCounter="+timeCounter+"&finalQ=${currentSection.last==true?true:false}";
			storeTimeLocal();
			document.testForm.submit();
		}
	}
	
  
  $('#openReviewDialog').click(function(e){
		e.preventDefault();
		// Code goes here
		openMarkReview();
	});
  function openMarkReview(){
		$('#myModal').modal('show');
		console.log('review modal opened');
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

	

	document.testForm.action = "prevQuestionNew?questionId=${currentQuestion.questionMapperInstance.questionMapper.id}&timeCounter="+timeCounter;
	storeTimeLocal();
	document.testForm.submit();
	}
	
	function changeSection(sectionName){
		//window.location = 'changeSection?sectionName='+sectionName+"&timeCounter="+timeCounter;
		//localStorage.setItem('timeCounter', timeCounter);
		var tp = '${currentQuestion.questionMapperInstance.questionMapper.question.type}';
			if(tp == 'CODING'){
				confirm(sectionName);
			}
			else{
				localStorage.setItem('timeCounter', timeCounter);
				window.location = 'changeSectionNew?sectionName='+sectionName+"&timeCounter="+timeCounter;
				
			}
		
	}
	
	function confirm(sectionName) {
		/**(new PNotify({
				title: 'Confirmation Needed',
				text: 'For a coding Question if you change a section you may loose your changes. Either copy your code somewhere and then change section or use Next/Back to navigate',
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
				window.location = 'changeSectionNew?sectionName='+sectionName+"&timeCounter="+timeCounter;
				localStorage.setItem('timeCounter', timeCounter);
			}).on('pnotify.cancel', function() {
				
			});*/
		window.location = 'changeSectionNew?sectionName='+sectionName+"&timeCounter="+timeCounter;
				localStorage.setItem('timeCounter', timeCounter);
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
			

		
			if(count == correctNo){
				//notify('INFO', 'go ahead');
			}
			else{
				//do nothing
			//notify('INFO', 'No of Answers chosen are not equal to no of correct answers. Please select '+correctNo+' option(s)');
			//return;
			}
		}
		
	
	

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
		$(function() {
				    Swal.fire(
			       messageType,
			       message,
			       '${icon}'
			    )
			});
	}
	
	// Questions answered and skipped count
	//var skippedCount = $('ul.questionbookmark li a.skipped').length;
	//var answeredCount = $('ul.questionbookmark li a.completed').length;
	document.getElementById('answeredCount').innerHTML = '${answered}';
    document.getElementById('skippedCount').innerHTML = '${skippedOrNotAnswered}';
	document.getElementById('markedCount').innerHTML = '${markedQs}';
	
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
