<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.assessment.data.*, java.text.*, java.util.*,com.assessment.web.dto.*, org.apache.commons.lang3.StringEscapeUtils"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adaptive Assessment - ${test.adaptiveAssessmentName}</title>
    
    <!-- Bootstrap core CSS -->
    <link href="adaptive/css/bootstrap.min.css" rel="stylesheet" />
    <link href="adaptive/css/style.css" rel="stylesheet" />

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100;0,200;0,400;0,600;0,700;0,800;1,300&display=swap" rel="stylesheet">
</head>
<body>
    <div class="fluid-container">
        <div class="header gray-bg shadow-custom p-4">
            <div class="row">
                <div class="col-md-7 pr-4">
                    <div class="row">
                        <div class="col-5"> <img src="adaptive/images/banner-img.png" alt="Thum" width="300px" /> </div>
                        <div class="col-7 pr-4">
                            <div class="p-3 border-right">
                                <h1>${test.adaptiveAssessmentName}</h1>
                                <div class="row my-4">
                                    <div class="col-md-6 my-2 d-flex align-items-center"> 
                                        <img src="adaptive/images/clock.png" alt="clock">
                                        <span class="ml-2">${test.testTimeInMinutes}</span>
                                    </div>

                                    <div class="col-md-6 my-2 d-flex align-items-center"> 
                                        <img src="adaptive/images/java.png" alt="Java">
                                        <span class="ml-2">${test.coreSkills}</span>
                                    </div>

                                    <div class="col-md-6 my-2 d-flex align-items-center">
                                        <img src="adaptive/images/intermediate.png" alt="Intermediate">
                                        <span class="ml-2">Level 1</span>
                                    </div>

                                    <div class="col-md-6 my-2 d-flex align-items-center">
                                        <img src="adaptive/images/files.png" alt="Files">
                                        <span class="ml-2">P/R (${test.noOfProgressions}/${test.noOfRegressions})</span>
                                    </div>

                                    <div class="col-md-6 my-2 d-flex align-items-center">
                                        <img src="adaptive/images/refresh.png" alt="Refresh">
                                        <span class="ml-2">${test.noOfConfigurableAttempts}</span>
                                    </div>
                                </div>
                                <button type="button" class="btn btn-primary font-weight-bold font23 px-4" data-toggle="modal" data-target="#staticBackdrop">Start Assesment</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-5 d-flex justify-content-between flex-column">
                    <div class="logo"> <img src="adaptive/images/logo2.png" alt="logo" /> </div>
                        <div>
                            <h3>Description </h3>
                            <p class="mb-0">
							${test.intro}
							<!--Java is one of the most popular programming languages used in professional application development As such. there is an extensive job. market available to those who master its syntax. However. mastering a language as complex and vast as Java requires a developer to start with a solid programming foundation. This 12-hour course provides that foundation. Those who are looking to start a career in software development. or developers who are looking to increase their marketability by learning the nuts and bolts of Java will benefit from taking this course.-->
							
							</p>
                        </div>
                    </div>
            </div>
        </div>

    <div class="row services my-4 px-2">
        <div class="col-md-3 px-1">
            <div class="card border-gradient border-gradient-purple">
                <h5 class="card-header"> Instructions </h5>
                <div class="card-body">
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> Please Do Not refresh the page</p>
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> Test Results MI be sent to you on Completion</p>
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> Click Submit for Submission of your Test</p>
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> System will auto Submit Test    if Timer Expires</p>
                </div>
            </div>
        </div>
        <div class="col-md-3 px-1">
            <div class="card border-gradient border-gradient-purple">
                <h5 class="card-header"> WEB Proctoring </h5>
                <div class="card-body">
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> Please Do Not refresh the page</p>
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> Test Results MI be sent to you on Completion</p>
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> Click Submit for Submission of your Test</p>
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> System will auto Submit Test    if Timer Expires</p>
                </div>
            </div>
        </div>
        <div class="col-md-3 px-1">
            <div class="card border-gradient border-gradient-purple">
                <h5 class="card-header"> Tenants </h5>
               <div class="card-body">
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> Please Do Not refresh the page</p>
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> Test Results MI be sent to you on Completion</p>
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> Click Submit for Submission of your Test</p>
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> System will auto Submit Test    if Timer Expires</p>
                </div>
            </div>
        </div>
        <div class="col-md-3 px-1">
            <div class="card border-gradient border-gradient-purple">
                <h5 class="card-header"> Yaksha </h5>
                <div class="card-body">
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> Please Do Not refresh the page</p>
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> Test Results MI be sent to you on Completion</p>
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> Click Submit for Submission of your Test</p>
                    <p class="card-text d-flex align-items-center"><span class="pr-2 font-weight-bold font23"> • </span> System will auto Submit Test  if Timer Expires</p>
                </div>
            </div>
        </div>
    </div>
    </div>


<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Terms and Conditions</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="row d-flex align-items-center">
            <div class="col-md-7 condition-points">
                <p>Please Do Not refresh the page. </p>
                <p>Test Results will be sent to you on Completion.</p>
                <p>Click Submit for Submission of your Test </p>
                <p>System will auto Submit Test if Timer Expires. </p>
            </div>
            <div class="col-md-5"> <img src="adaptive/images/assesment-img.png" width="100%"> </div>
        </div>
        <div class="d-flex justify-content-center border-0 mb-2">
            <a href="javascript:startAss()" type="button" class="btn btn-primary font-weight-bold font23" >Start Assesment</a>
        </div>
      </div>
      
    </div>
  </div>
</div>



    <script src="adaptive/js/jquery-3.5.1.min.js"></script>
    <script src="adaptive/js/popper.min.js"></script>
    <script src="adaptive/js/bootstrap.min.js"></script>
	
	<script>
	function startAss(){
		url = 'adaptiveAssessment2?startDate=${startDate}&endDate=${endDate}&userId=${userId}&companyId=${companyId}&testId=${test.id}';
		window.location = url;
	}
	
	
	</script>
</body>
</html>