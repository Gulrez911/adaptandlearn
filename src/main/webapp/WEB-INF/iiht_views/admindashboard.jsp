<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*,com.assessment.web.dto.*"%>
<html>
    <head>
     <title>eAssess</title>
      <link href="images/E-assess_E.png" rel="shortcut icon">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="admindashboard/css/font-awesome.min.css">
        <link rel="stylesheet" href="admindashboard/css/bootstrap.min.css">
        <link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="admindashboard/css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
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
                        <a href="#" class="active">
                            Dashboard
                        </a>
                        <a href="questionssnew">
                            Question Bank
                        </a>
                        <a href="testsnew">
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
                            <a href="recruiters">
                                Manager Recruiters
                            </a>
                            <a href="jobDescriptions">
                                Share Job Descriptions
                            </a>
                            <a href="profileForJobDescription">
                                Next Steps for Job Applications 
                            </a>
							<a href="applicationTracking">
                                Job Applications Tracker 
                            </a>
							<a href="apprepository">
                                Candidate Repository
                            </a>
							<a href="showSlots">
                                Booking Slot Manager
                            </a>
                            
                        </div>
                    </nav>
                    <div class="user-info">
                        <span class="my-auto">${firstName}, ${lastName}</span>
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
                        <h1 class="my-auto">Dashboard</h1>
                    </div>
                    <div class="contents">
                        <article>
                            <div class="row">
                                <div class="col-xs-12 col-md-6 col-xl-4 mb-3">
                                    <div class="card text-center">
                                        <div class="card-body coding-bg">
                                            <div class="count text-alternate">
                                                ${countCoding}
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <i class="fa fa-file-code-o mr-2"></i>
                                            Coding Questions
                                          </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-6 col-xl-4 mb-3">
                                    <div class="card text-center">
                                        <div class="card-body mcq-bg">
                                            <div class="count text-alternate">
                                                ${countMCQ}
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <i class="fa fa-file-text-o mr-2"></i>
                                            MCQ Questions
                                          </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-6 col-xl-4 mb-3">
                                    <div class="card text-center">
                                        <div class="card-body mtf-bg">
                                            <div class="count text-alternate">
                                                ${countMTF}
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <i class="fa fa-file-code-o mr-2"></i>
                                            MTF Questions
                                          </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-6 col-xl-4 mb-3">
                                    <div class="card text-center">
                                        <div class="card-body fib-bg">
                                            <div class="count text-alternate">
                                                ${countFib}
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <i class="fa fa-file-text-o mr-2"></i>
                                            FIB Questions
                                          </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-6 col-xl-4 mb-3">
                                    <div class="card text-center">
                                        <div class="card-body fullstack-bg">
                                            <div class="count text-alternate">
                                               ${countFullStack}
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <i class="fa fa-file-code-o mr-2"></i>
                                            Full Stack Questions
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-6 col-xl-4 mb-3">
                                    <div class="card text-center">
                                        <div class="card-body subjective-bg">
                                            <div class="count text-alternate">
                                                ${countSub}    
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <i class="fa fa-file-text-o mr-2"></i>
                                            Subjective Questions
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12 col-md-6 mb-3">
                                    <div class="card">
                                        <div class="card-header bg-primary text-white">
                                            Most Frequent Test Takers
                                        </div>
                                        <div class="card-body">
                                            <ul class="users">
											<c:forEach  items="${popularUsers}" var="testtaker" varStatus="loop"> 
                                                <li>
                                                    <a href="#" class="user-info">
                                                        <div class="thumbnail my-auto">
                                                            <span>FL</span>
                                                        </div>
                                                        <span class="ml-2 my-auto">${testtaker.firstName} ${testtaker.lastName}</span>
                                                    </a>
                                                    <span class="total-test">Test Taken: ${testtaker.count}</span>
                                                </li>
                                               </c:forEach>
                                            </ul>
                                        </div>
                                       
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-6 mb-3">
                                    <div class="card">
                                        <div class="card-header bg-primary text-white">
                                            Most Popular Assessments
                                        </div>
                                        <div class="card-body">
                                            <ul class="users">
											<c:forEach  items="${popTests}" var="test" varStatus="loop"> 
                                                <li>
                                                    <a href="#" class="user-info">
                                                        <span class="my-auto">${test.testName}</span>
                                                    </a>
                                                    <span class="total-test">Test Takers: ${test.count}</span>
                                                </li>
                                               </c:forEach>
                                            </ul>
                                        </div>
                                        
                                    </div>
                                </div>
                            </div>
                        </article>
                        <aside class="pt-0">
                            <div class="filter-block">
                                <h2>Most Popular Skill Categories</h2>
                                <div class="lists">
                                    <c:forEach  items="${popSkills}" var="skill" varStatus="loop"> 
                                        <a href="#">
                                            <span class="title">${skill.skillCategory}</span>
                                            <small>${skill.count} Takers</small>
                                        </a>
                                    </c:forEach>    
                                </div>
                            </div>
                            <div class="filter-block mt-3">
                                <h2>Most Popular Questions</h2>
                                <div class="lists">
                                    <c:forEach  items="${popularquestions}" var="quest" varStatus="loop"> 
                                        <a href="#">
                                            <span class="title">${quest.questionText}</span>
                                            <small>${quest.count} Takers</small>
                                        </a>
                                    </c:forEach>
                                </div>
                            </div>
                        </aside>
                    </div>
                </div>
            </section>
            <footer>
                <div class="container-fluid text-right">
                    <span class="copyright">
                        &copy; Copyright 2020-2021 - eAssess
                    </span>
                    <a href="#">
                        Terms and Conditions
                    </a>
                    <a href="#">
                        Privacy Policy
                    </a>
                </div>
            </footer>
            <div class="drop-down-bg">
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Nisi voluptatem sunt officiis nulla beatae quo quod est ab! Exercitationem quasi iusto, enim alias placeat sunt nulla sapiente omnis odio voluptate!
            </div>
        </div>
		
		 <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="new/js/bootstrap.min.js"></script>  
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
			function signoff(){
				window.location = 'signoff';
			}
			
			function notify(msg){
				var notification = 'Information';
				$(function() {
				    Swal.fire(
			       'Information',
			       msg,
			       'warning'
			    )
			});
			}
        </script> 
    </body>
</html>