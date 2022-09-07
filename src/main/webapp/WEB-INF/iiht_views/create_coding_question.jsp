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
        <link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
     	
     	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="new/css/font-awesome.min.css">
        <link rel="stylesheet" href="new/css/bootstrap.min.css">
        <link rel="stylesheet" href="new/css/style.css">
        <script src="new/js/jquery.min.js"></script>
        <script src="new/js/bootstrap.min.js"></script>
		<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js"
	type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />
     
    </head>
    <body>
        <div class="master-container">
            <header>
                <div class="container-fluid">
                    <a href="#" class="nav-bars">
                        <i class="fa fa-bars"></i>
                    </a>
                    <a href="#" class="yaksha-logo my-auto">
                        E<span>ASSESS</span>
                    </a>
                    <nav>
                        <a href="#" class="close-menu">
                            <i class="fa fa-close"></i>
                        </a>
                        <div class="main-nav">
                            <a href="dashboardnew">
                                dashboard
                            </a>
                            <a href="questionssnew" class="active">
                                question bank
                            </a>
                            <a href="testsnew">
                                tests
                            </a>
                           <a href="#" class="view-more">
                            More <i class="fa fa-angle-down"></i>
							</a>
                        </div>
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
                                Code Analysis Reports
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
                        </div>
                    </nav>
                    <div class="user-info">
                        <div class="my-auto">
                             eAssess Admin
                        </div>
                        <div class="thumbnail ml-2 my-auto">
                            <span class="avatar">IA</span>
                        </div>
                    </div>
                </div>
            </header>
            <section class="main-section">
                <div class="container-fluid">
                    <div class="page-heading">
                        <h2>Create new question</h2>
                    </div>
                        <form name="questionForm"  method="post" modelAttribute="question" action="saveQuestion2" enctype="multipart/form-data">
                        <div class="contents">
                            <div class="main-forms">
                                <div class="row">
                                    <div class="col-12 mb-2">
                                        <div class="form-group">
                                            <label for="question">Question</label>
                                                   <form:textarea path="question.questionText" required="true" class="form-control" id="question" rows="3"/>
					    							<form:hidden path="question.id" />
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-6 mb-2">
                                        <div class="form-group">
                                            <label for="difficultyLevel">Difficulty Level</label>
                                            <form:select path="question.level" class="form-control" id="difficultyLevel">
						  
										     <form:options items="${levels}" itemValue="level" itemLabel="level" />
										</form:select>
                                          </div>
                                    </div>
                                    <div class="col-xs-12 col-md-6 mb-2">
                                        <div class="form-group">
                                            <label for="questionLevel">Question Type</label>
                                             <form:select id="questionType" path="question.type" class="form-control"  >
						  
										     	<form:option value="${types }" ></form:option>
												</form:select>
                                          </div>
                                    </div>
                                    <!-- MCQ -->
                                     
                                     
                                    <!-- Coding -->
                                    <div class="col-12 mb-2">
                                        <div class="form-group">
                                            <label>Programing Language</label>
                                             <form:select path="question.lang" class="form-control">
											     <form:options items="${languages}" itemValue="language" itemLabel="language" />
											</form:select>
                                          </div>
                                    </div>
                                    <div class="col-12 mb-2">
                                        <div class="form-group">
                                            <label for="answer">Code Input</label>
                                              <form:textarea path="question.inputCode" style="height:150px;overflow-y: scroll" class="form-control" id="answer" />
<!--                                             <textarea class="form-control" id="answer" rows="3"></textarea> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-5 mb-2">
                                        <div class="form-group">
                                            <label for="">System Input 1</label>
                                              <form:input path="question.hiddenInputPositive"  id="hiddenInputPositive" class="form-control"/>
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-5 mb-2">
                                        <div class="form-group">
                                            <label for="">System Output 1</label>
                                            	<form:input path="question.hiddenOutputPositive"  id="hiddenOutputPositive"  class="form-control"/>
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-2 mb-2">
                                        <div class="form-group">
                                            <label for="">Weight</label>
                                            <form:input path="question.weightInputPositive" style="height:30px" id="weightInputPositive" class="form-control"/>
<!--                                             <input type="number" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-5 mb-2">
                                        <div class="form-group">
                                            <label for="">System Input 2</label>
                                             <form:input path="question.hiddenInputNegative" id="hiddenInputNegative" class="form-control"/>
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-5 mb-2">
                                        <div class="form-group">
                                            <label for="">System Output 2</label>
                                            <form:input path="question.hiddenOutputNegative" id="hiddenOutputNegative" class="form-control"/>
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-2 mb-2">
                                        <div class="form-group">
                                            <label for="">Weight</label>
                                            <form:input path="question.weightInputNegative"  id="weightInputNegative" class="form-control"/>
<!--                                             <input type="number" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-5 mb-2">
                                        <div class="form-group">
                                            <label for="">Extreme Minimal Input</label>
                                            <form:input path="question.hiddenInputExtremeMinimalValue"  id="hiddenInputExtremeMinimalValue" class="form-control"/>
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-5 mb-2">
                                        <div class="form-group">
                                            <label for="">Extreme Minimal Output</label>
                                            <form:input path="question.hiddenOutputExtremeMinimalValue" id="hiddenOutputExtremeMinimalValue" class="form-control"/>
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-2 mb-2">
                                        <div class="form-group">
                                            <label for="">Weight</label>
                                            <form:input path="question.weightExtremeMinimalValue"  id="weightExtremeMinimalValue" class="form-control"/>
<!--                                             <input type="number" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-5 mb-2">
                                        <div class="form-group">
                                            <label for="">Extreme Positive Input</label>
                                             <form:input path="question.hiddenInputExtremePositiveValue" id="hiddenInputExtremePositiveValue" class="form-control"/>
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-5 mb-2">
                                        <div class="form-group">
                                            <label for="">Extreme Positive Output</label>
                                            <form:input path="question.hiddenOutputExtremePositiveValue" id="hiddenOutputExtremePositiveValue" class="form-control"/>
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-2 mb-2">
                                        <div class="form-group">
                                            <label for="">Weight</label>
                                            	<form:input path="question.weightExtremePositiveValue" id="weightExtremePositiveValue" class="form-control"/>
<!--                                             <input type="number" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-5 mb-2">
                                        <div class="form-group">
                                            <label for="">Invaid Data Input</label>
                                    	   <form:input path="question.hiddenInputInvalidDataValue" id="hiddenInputInvalidDataValue" class="form-control"/>
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-5 mb-2">
                                        <div class="form-group">
                                            <label for="">Invalid Data Output</label>
                                            	<form:input path="question.hiddenOutputInvalidDataValue" class="form-control" id="hiddenOutputInvalidDataValue" />
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-2 mb-2">
                                        <div class="form-group">
                                            <label for="">Weight</label>
                                            <form:input path="question.weightInvalidDataValue"  id="weightInvalidDataValue" class="form-control"/>
<!--                                             <input type="number" class="form-control"> -->
                                        </div>
                                    </div>
                                    
                                    <!-- Fullstack -->
                                     
                                    <!-- Fill Blanks (MCQ) -->
                                     
                                    <!-- Match Following (MCQ) -->
                                     
                                </div>
                            </div>
                            <div class="side-form pt-1">
                                <div class="text-center">
                                    <div class="btn-group mt-4">
                                        <button type="button" class="btn btn-sm btn-outline-secondary addimage">Add Image</button>
                                        <button type="button" class="btn btn-sm btn-outline-secondary addaudio">Add Audio</button>
                                        <button type="button" class="btn btn-sm btn-outline-secondary addvideo">Add Video</button>
                                        
                                        
                                           <label class="queimage"></label>
										 
                                        <label class="queaudio"></label>
                                        <label class="quevideo"></label>
                                        
                                       <input type="file" name="addimage" id="addimage" style="display: none;">
                                        <input type="file" name="addaudio" id="addaudio" style="display: none;">
                                        <input type="file" name="addvideo" id="addvideo" style="display: none;">
                                    </div>
                                </div>
                                <div class="mt-3">Choose categories for Question</div>
                                <div class="input-group mt-2">
                                    <div class="input-group-prepend">
                                      <div class="input-group-text">
                                        A
                                      </div>
                                    </div>
<!--                                     <input type="text" class="form-control"> -->
                                    <form:input path="question.qualifier1" name="qualifier1" id="qualifier1" required="true" class="form-control"/>
                                    <div class="input-group-append">
                                        <span class="input-group-text text-danger">
                                            <i class="fa fa-minus"></i>
                                        </span>
                                    </div>
                                </div>
                                <div class="input-group mt-2">
                                    <div class="input-group-prepend">
                                      <div class="input-group-text">
                                        B
                                      </div>
                                    </div>
<!--                                     <input type="text" class="form-control"> -->
                                     <form:input path="question.qualifier2" name="qualifier2" id="qualifier2"   class="form-control"/>
                                    <div class="input-group-append">
                                        <span class="input-group-text text-danger">
                                            <i class="fa fa-minus"></i>
                                        </span>
                                    </div>
                                </div>
                                <div class="input-group mt-2">
                                    <div class="input-group-prepend">
                                      <div class="input-group-text">
                                        C
                                      </div>
                                    </div>
<!--                                     <input type="text" class="form-control"> -->
                                     <form:input path="question.qualifier3" name="qualifier3" id="qualifier3"   class="form-control"/>
                                    <div class="input-group-append">
                                        <span class="input-group-text text-danger">
                                            <i class="fa fa-minus"></i>
                                        </span>
                                    </div>
                                </div>
                                
                                <div class="form-group mt-3">
                                    <label for="constraint">Constraints</label>
                                        <form:textarea path="question.constrnt"  class="form-control" id="constraint" rows="3" />
                                </div>
                                <div class="form-group mt-3">
                                    <label>Question Weight</label>
                                      <form:input path="question.questionWeight" name="questionWeight" id="questionWeight" placeholder="Enter a number between 1 to 10" onkeypress="return isNumberKey(event)" class="form-control" />
                                </div>
                                <div class="form-group mt-3">
                                    <label>Instructions, If any</label>
                                    <form:textarea path="question.instructionsIfAny" style="overflow-y: scroll" id="output" class="form-control" rows="3"/>
                                </div>
                                <div class="form-group mt-3">
                                    <label>Course Qualifier Description, if any</label>
                                     <form:input path="question.qualifierDescription" name="qualifierDescription" id="qualifierDescription" class="form-control" rows="2"/>
                                </div>
                                <div class="form-group mt-3">
                                    <label>Course Context, if any</label>
                                    <form:input path="question.courseContext" name="courseContext" id="courseContext"  class="form-control" rows="2"/>
                                </div>
                                <div class="form-group mt-3">
                                    <label>Weight for Course Context, if any</label>
                                        <form:input path="question.courseWeight" name="courseWeight" id="courseWeight" placeholder="Enter a number between 1 to 10" class="form-control" />
                                </div>
                                 <div class="form-group mt-3" style="padding-top: 10px;">
                                            <label for="">Pass Criteria for Coding Question</label>
                                            <form:input path="question.passPercentforCodingQAsPerWeightedScore"  id="passPercentforCodingQAsPerWeightedScore" class="form-control"/>
<!--                                             <input type="number" class="form-control"> -->
                                    </div>
                            </div>
                        </div>
                        <div class="mt-4 text-center">
                            <button class="btn btn-primary mr-3" type="submit">
                                Save
                            </button>
                            <button class="btn btn-secondary" type="button" onClick="goback()">
                                Cancel
                            </button>
                        </div>
                    </form>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="new/js/bootstrap.min.js"></script>
        <script src="new/js/scripts.js"></script>
        <script>
            function signoff(){
				window.location = 'signoff';
			}
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
        $(function () {
            $(".addimage").on('click', function (e) {
                e.preventDefault();
                $("#addimage").trigger('click');
            });
            $(".addaudio").on('click', function (e) {
                e.preventDefault();
                $("#addaudio").trigger('click');
            });
            $(".addvideo").on('click', function (e) {
                e.preventDefault();
                $("#addvideo").trigger('click');
            });
        });
        $('#addimage').change(function () {
            var file = $('#addimage')[0].files[0].name;
            $('.queimage').text('Image: '+file);
        });
        $('#addaudio').change(function () {
            var file = $('#addaudio')[0].files[0].name;
            $('.queaudio').text('Audio: '+file);
        });
        $('#addvideo').change(function () {
            var file = $('#addvideo')[0].files[0].name;
            $('.quevideo').text('Video: '+file);
        });
        function goback(){
        	window.location = "questionssnew";
        	}
        </script>
        
        <c:if test="${msgtype != null}">
		 <script>
	 var notification = 'Information';
	 $(function(){
		 new PNotify({
	         title: notification,
	         text: '${message}',
	         type: '${msgtype}',
	         styling: 'bootstrap3',
	         hide: true
	     });
	 }); 	 
      </script>
</c:if>
    </body>
</html>