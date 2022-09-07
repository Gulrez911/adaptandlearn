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
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="new/css/font-awesome.min.css">
        <link rel="stylesheet" href="new/css/bootstrap.min.css">
        <link rel="stylesheet" href="admindashboard/css/style.css">
        
        <script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
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
                        <a href="questionssnew" class="active">
                            Question Bank
                        </a>
                        <a href="testsnew">
                            Tests
                        </a>
                        <a href="#" class="view-more">
                            More <i class="fa fa-angle-down"></i>
                        </a>
                        <div class="more-nav">
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Skills
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Cluster Management
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Multi-file Test Results
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Domain based Results
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Modules Management
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
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
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Recomm Settings
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Single File Test Reports
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Test Links Management
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
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
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Verify Data
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Tenant Management
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Users
                            </a>
                        </div>
                    </nav>
                    <div class="user-info">
                        <span class="my-auto">eAssess, Admin</span>
                        <div class="thumbnail my-auto">
                            <span><i class="fa fa-user-o"></i></span>
                        </div>
                        <button class="sign-out" type="button" onClick="javascript:signoff()">
                            <i class="fa fa-sign-out" ></i>
                        </button>
                    </div>
                </div>
            </header>
            <section class="content-section">
                <div class="container-fluid">
                    <div class="page-header mb-4">
                        <h1>Create new question</h1>
                    </div>
<%--                     <form action=""> --%>
<%--                           <form:form name="questionForm"  method="post" modelAttribute="question" action="saveQuestion" enctype="multipart/form-data"> --%>
                                         <form name="questionForm"  method="post" modelAttribute="question" action="saveQuestion2" enctype="multipart/form-data">
                        <div class="contents">
                            <article>
                                <div class="row">
                                    <div class="col-12 mb-2">
                                        <div class="form-group">
                                            <label for="question">Question</label>
                                                <form:textarea path="question.questionText" required="true" class="form-control" id="question" rows="5"/>
					    						<form:hidden path="question.id" />
<!--                                             <textarea class="form-control" id="question" rows="3"></textarea> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-6 mb-2">
                                        <div class="form-group">
                                            <label for="difficultyLevel">Difficulty Level</label>
                                         <form:select path="question.level" class="form-control" id="difficultyLevel">
						  
										     <form:options items="${levels}" itemValue="level" itemLabel="level" />
										</form:select>
<!--                                             <select class="form-control" id="difficultyLevel"> -->
<!--                                               <option>Select Level</option> -->
<!--                                               <option>Very Easy</option> -->
<!--                                               <option>Easy</option> -->
<!--                                               <option>Medium</option> -->
<!--                                               <option>Hard</option> -->
<!--                                               <option>Very Hard</option> -->
<!--                                             </select> -->
                                          </div>
                                    </div>
                                    <div class="col-xs-12 col-md-6 mb-2">
                                        <div class="form-group">
                                            <label for="questionLevel">Question Type</label>
                                            <form:select id="questionType" path="question.type" class="form-control"  >
						  
										     <form:option value="${types }" ></form:option>
										</form:select>
<!--                                             <select class="form-control" id="questionLevel"> -->
<!--                                               <option>Select Type</option> -->
<!--                                               <option>Multiple Choise Question</option> -->
<!--                                               <option>Coding</option> -->
<!--                                               <option>Fullstack</option> -->
<!--                                               <option>Fullstack Java</option> -->
<!--                                               <option>Fill Blanks (MCQ)</option> -->
<!--                                               <option>Match Following (MCQ)</option> -->
<!--                                             </select> -->
                                          </div>
                                    </div>
                                    <!-- MCQ -->
                                    <div class="col-12 mb-4">
                                        <label for="">Options</label>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                              <div class="input-group-text" id="s">
<!--                                                 <input type="checkbox"> -->
   										<form:checkbox path="question.one" /> 
                                              </div>
                                            </div>
<!--                                             <input type="text" class="form-control"> -->
                                                 <form:input path="question.choice1" name="choice1" id="choice1" required="true" class="form-control"/>
                                            <div class="input-group-append">
                                                <span class="input-group-text text-danger">
                                                    <i class="fa fa-minus"></i>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="input-group mt-2">
                                            <div class="input-group-prepend">
                                              <div class="input-group-text">
<!--                                                 <input type="checkbox"> -->
                                                  <form:checkbox path="question.two" /> 
                                              </div>
                                            </div>
<!--                                             <input type="text" class="form-control"> -->
										<form:input path="question.choice2" name="choice2" id="choice2" required="true" class="form-control"/>
                                            <div class="input-group-append">
                                                <span class="input-group-text text-danger">
                                                    <i class="fa fa-minus"></i>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="input-group mt-2">
                                            <div class="input-group-prepend">
                                              <div class="input-group-text">
                                                <form:checkbox path="question.three" /> 
                                              </div>
                                            </div>
                                             <form:input path="question.choice3" name="choice3" id="choice3" class="form-control"/>
                                            <div class="input-group-append">
                                                 <span class="input-group-text text-danger">
                                                    <i class="fa fa-minus"></i>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="input-group mt-2">
                                            <div class="input-group-prepend">
                                              <div class="input-group-text">
                                                  <form:checkbox path="question.four" /> 
                                              </div>
                                            </div>
                                           <form:input path="question.choice4" name="choice4" id="choice4" class="form-control"/>
                                            <div class="input-group-append">
                                                   <span class="input-group-text text-danger">
                                                    <i class="fa fa-minus"></i>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="input-group mt-2">
                                            <div class="input-group-prepend">
                                              <div class="input-group-text">
                                            <form:checkbox path="question.five" /> 
                                              </div>
                                            </div>
                                          <form:input path="question.choice5" name="choice5" id="choice5"  class="form-control"/>
                                            <div class="input-group-append">
                                             <span class="input-group-text text-danger">
                                                    <i class="fa fa-minus"></i>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="input-group mt-2">
                                            <div class="input-group-prepend">
                                              <div class="input-group-text">
                                             <form:checkbox path="question.six" /> 
                                              </div>
                                            </div>
                                             <form:input path="question.choice6" name="choice6" id="choice6" class="form-control"/>
                                            <div class="input-group-append">
                                                  <span class="input-group-text text-danger">
                                                    <i class="fa fa-minus"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 mb-2">
                                        <div class="form-group">
                                            <label for="answer">Justification for Answer</label>
                                            	<form:textarea path="question.justification"  id="justification"  rows="4" class="form-control" style="height: 120px;"/>
<!--                                             <textarea class="form-control" id="answer" rows="3"></textarea> -->
                                        </div>
                                    </div>
                                    <!-- Coding -->
                                     <form:textarea path="question.inputCode"   class="form-control" id="answer"  style="display: none;"/>
                                    <div class="col-12 mb-2">
                                        <div class="form-group">
                                            <label>Programing Language</label>
                                            <form:select path="question.lang" class="form-control">
											  
											     <form:options items="${languages}" itemValue="language" itemLabel="language" />
											</form:select>
                                          </div>
                                    </div>
                                            
                                                                         
                                </div>
                            </article>
                            <aside class="forms">
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
<!--                                     <textarea class="form-control" id="constraint" rows="3"></textarea> -->
                                </div>
                                <div class="form-group mt-3">
                                    <label>Question Weight</label>
                                      <form:input path="question.questionWeight" name="questionWeight" id="questionWeight" placeholder="Enter a number between 1 to 10" onkeypress="return isNumberKey(event)" class="form-control" />
<!--                                     <input type="number" class="form-control" placeholder="Between (1 to 10)"> -->
                                </div>
                                <div class="form-group mt-3">
                                    <label>Instructions, If any</label>
                                    <form:textarea path="question.instructionsIfAny" style="overflow-y: scroll" id="output" class="form-control" rows="3"/>
<!--                                     <textarea class="form-control" rows="3"></textarea> -->
                                </div>
                                <div class="form-group mt-3">
                                    <label>Course Qualifier Description, if any</label>
                                     <form:input path="question.qualifierDescription" name="qualifierDescription" id="qualifierDescription" class="form-control" rows="2"/>
<!--                                     <textarea class="form-control" rows="2"></textarea> -->
                                </div>
                                <div class="form-group mt-3">
                                    <label>Course Context, if any</label>
                                    <form:input path="question.courseContext" name="courseContext" id="courseContext"  class="form-control" rows="2"/>
<!--                                     <textarea class="form-control" rows="2"></textarea> -->
                                </div>
                                <div class="form-group mt-3">
                                    <label>Weight for Course Context, if any</label>
                                        <form:input path="question.courseWeight" name="courseWeight" id="courseWeight" placeholder="Enter a number between 1 to 10" class="form-control" />
<!--                                     <input type="number" class="form-control" placeholder="1"> -->
                                </div>
                            </aside>
                        </div>
                        <div class="mt-4 text-center">
                            <button class="btn btn-secondary mr-2" type="button" onClick="goback()">
                                Cancel
                            </button>
                            <button class="btn btn-primary" type="submit">
                                Save
                            </button>
<!--                              <input class="save" type="submit" value="Save" class="btn btn-primary mr-3" > -->
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
            <div class="drop-down-bg">
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Iste consequatur accusantium perspiciatis omnis blanditiis repudiandae enim officiis vel? Recusandae, blanditiis itaque? Quia aut architecto odio voluptatibus, repellat distinctio consequatur veniam.
            </div>
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