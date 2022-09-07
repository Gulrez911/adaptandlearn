<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Adapt and Learn MCQ</title>

    <!-- Bootstrap core CSS -->
    <link href="b2c/css/bootstrap.min.css" rel="stylesheet" />
    <link href="b2c/css/assessment.css" rel="stylesheet" />

    <!-- Google Font -->
      <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;1,200;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">
<style>
.instructions-text .heading {
    font-size: 23px;
    font-weight: 500;
    margin-top: 0rem;
}
</style>
  </head>
  <body> 
    <header>
      <div class="logo"> <img src="b2c/images/logo.png" alt="LOGO"> </div>
      <h1>${studentTestForm.testName}</h1>
    </header>
    <div class="container">
	<form:form id="studentIntro" method="POST" action="studentJourneyNew"  modelAttribute="studentTestForm">
        <div class="row my-4">
          <div class="col-md-6">
            <div class="form-wrapper">
              <div class="evaluation-box">
                  <div class="row">
                    <div class="col-7 d-flex align-items-center"> <embed class="align-text-top mr-3" src="b2c/images/no-of-questions.svg" width="30"> Number of Questions</div>
                    <div class="col-5 font23"> <img class="align-baseline mr-3" src="b2c/images/arrow.svg" width="20"> ${studentTestForm.totalQuestions}</div>
                  </div>
              </div>
              <div class="evaluation-box">
                  <div class="row">
                    <div class="col-7 d-flex align-items-center"> <embed class="align-text-top mr-3" src="b2c/images/duartaion.svg" width="30"> Duration</div>
                    <div class="col-5 font23"> <img class="align-baseline mr-3" src="b2c/images/arrow.svg" width="20"> ${studentTestForm.duration} Minutes</div>
                  </div>
              </div>
			  <%
							Integer noOfAttempts = (Integer) request.getSession().getAttribute("noOfAttempts");
								System.out.println("att "+noOfAttempts);
								
								if(noOfAttempts == null){
									noOfAttempts = 0;
									
								}
								else{
									noOfAttempts = noOfAttempts + 1;
								}
							%>
              <div class="evaluation-box">
                  <div class="row">
                    <div class="col-7 d-flex align-items-center"> <embed class="align-text-top mr-3" src="b2c/images/no-of-attempts.svg" width="30"> Number of Attempts</div>
                    <div class="col-5 font23"> <img class="align-baseline mr-3" src="b2c/images/arrow.svg" width="20">  <%= noOfAttempts %>  / ${studentTestForm.noOfAttemptsAvailable}</div>
                  </div>
              </div>
              <div class="d-flex justify-content-center mt-5 mb-3"> <button>Start Assessment</button> </div>
            </div>
          </div>
          <div class="col-md-6">
              <div class="instructions-text">
                  <div>
                    <div class="heading"><img src="b2c/images/instruction.svg" width="18" class="align-baseline mr-2"> Instructions</div>
                    <ul>
                      <li>Evaluation Results will be displayed post the completion of the assessment</li>
                      <li>Click Submit for Submission of your assessment</li>
                      <li>System will auto Submit assessment if you exceed the time assigned to the assessment</li>
                    </ul>
                  </div>
                  <div>
                    <div class="heading"> <img src="b2c/images/web.svg" width="18" class="align-baseline mr-2"> Further </div>
                    <ul>
                      <li>We may record your attempts to move away from the test screen:)</li>
                      <li>Please take this assessment from a computer or a laptop</li>
                      <li>We are getting mobile friendly pretty soon</li>
                    </ul>
                  </div>
                  <div>
                    <div class="heading"> <img src="b2c/images/tenant.svg" width="18" class="align-baseline mr-2"> General</div>
                    <ul>
                      <li>Your identity data is safe with us</li>
                      <li>Pretty soon, we will share you your credentials to login to your User Area</li>
                      <li>In your User Area, you will see your history of assessments, scores, course recommendations & more</li>
                    </ul>
                  </div>
                  <div>
                    <div class="heading"> <img src="b2c/images/yaksha.svg" width="18" class="align-baseline mr-2"> Costing, if any</div>
                    <ul>
                      <li>Free Assessments</li>
                      <li>Free Recommendations</li>
                      <li>Free Analysis of your profile</li>
                    </ul>
                  </div>
              </div>
              
          </div>
        
        </div>
	</form:form>
    </div>
    <footer>© All rights reserved. Made by Your Name</footer>
    <script src="b2c/js/jquery-3.5.1.min.js"></script>
    <script src="b2c/js/popper.min.js"></script>
    <script src="b2c/js/bootstrap.min.js"></script>
	 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
    <script type="text/javascript">
		 function start2(){
		        //fullScreen();
					document.getElementById("studentIntro").submit();
				}
				 
		
		function checkCamera(){

			navigator.getMedia = ( navigator.getUserMedia || // use the proper vendor prefix
		            navigator.webkitGetUserMedia ||
		            navigator.mozGetUserMedia ||
		            navigator.msGetUserMedia);
		
				navigator.getMedia({video: true}, function() {
					sweetAlert("Info","You can start test Now","success")
					$("#cam").attr("onclick","start2()");
					$("#cam").text("Start Assessment");
				}, function() {
					sweetAlert("Information","You can not start test without camera","warning")
// 							alert("Test cannot start without camera");
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
  </body>
</html>
