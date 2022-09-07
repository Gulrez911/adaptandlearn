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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
     	
     	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
<!--         <link rel="stylesheet" href="new/css/font-awesome.min.css"> -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="new/css/bootstrap.min.css">
        <link rel="stylesheet" href="new/css/style.css">
         <link rel="stylesheet" href="new/new_css/style.css">
        <script src="new/js/jquery.min.js"></script>
        <script src="new/js/bootstrap.min.js"></script>
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
                        <a href="dashboard.html">
                            Dashboard
                        </a>
                        <a href="questions.html">
                            Question Bank
                        </a>
                        <a href="tests.html">
                            Tests
                        </a>
                        <a href="#" class="view-more active">
                            Verify Data <i class="fa fa-angle-down"></i>
                        </a>
                        <div class="more-nav">
                            <a href="skills.html">
                                Skills
                            </a>
                            <a href="cluster-management.html">
                                Cluster Management
                            </a>
                            <a href="multi-file-test-result.html">
                                Multi-file Test Results
                            </a>
                            <a href="#">
                                Domain based Results
                            </a>
                            <a href="#">
                                Modules Management
                            </a>
                            <a href="#">
                                License Management
                            </a>
                            <a href="#">
                                Results
                            </a>
                            <a href="#">
                                Code Analysis Reports
                            </a>
                            <a href="#">
                                Skill based Reports
                            </a>
                            <a href="#">
                                Recomm Settings
                            </a>
                            <a href="#">
                                Single File Test Reports
                            </a>
                            <a href="#">
                                Test Links Management
                            </a>
                            <a href="#">
                                LMS Admin Users
                            </a>
                            <a href="#">
                                Job Description Management
                            </a>
                            <a href="#">
                                Test Domain Attemps Mangement
                            </a>
                            <a href="#">
                                Schedule Management
                            </a>
                            <a href="#">
                                Verify Data
                            </a>
                            <a href="tenants.html">
                                Tenant Management
                            </a>
                            <a href="users.html">
                                Users
                            </a>
                        </div>
                    </nav>
                    <div class="user-info">
                        <span class="my-auto">Firstname, Lastname</span>
                        <div class="thumbnail my-auto">
                            <span>TJ</span>
                        </div>
                        <button class="sign-out" type="button">
                            <i class="fa fa-sign-out"></i>
                        </button>
                    </div>
                </div>
            </header>
            <section class="content-section">
                <div class="container-fluid pt-5">
<c:choose>
								<c:when test="${results != null && results.size() > 0}">
									<div class="questiontablelist" >
									<table class="table" >
                                        <thead>
                                            <tr>
                                                <th><b>No</b></th>
                                                <th>Question</th>
												<th>Problem</th>
											</tr>
										</thead>
															
										<tbody>
						                     
						                       <c:forEach  items="${results}" var="res" varStatus="loop">  
												
						                      	<tr>

										<td>${loop.count}</td>		
						                      		<td><c:out value="${res.questionText}"></c:out>  </td>
						                      		<td> ${res.questionProblem}</td>
						                      		
						                      	</tr>
						                      	</c:forEach>   
						                      </tbody>
                                           
                                        </tbody>
                                    </table>
									</div>
									
								</c:when>   
								<c:otherwise>
									<h5> <b>Data good to be uploaded.</b> </h5> 
									<h5> <b><i>{This utility tests the data on following parameters <br/>
									 1.	Validity of Company Id for all records<br/>
									2.	Correct Choices Entry in all records}</i></b><br/></h5>
								</c:otherwise>
							</c:choose>
								
								
								<c:if test = "${problem != null}">
								<div class="starttestinfo loginformnew">
									<h3>Problem - </h3><br/>
									 <h4>${problem}</h4>
								</div>
								</c:if>
                </div>
            </section>
            <footer>
                <div class="container-fluid text-right">
                    <span class="copyright">
                        &copy; Copyright 2020-2021 - Yaksha
                    </span>
                    <a href="#">
                        Terms and Conditions
                    </a>
                    <a href="#">
                        Privacy Policy
                    </a>
                </div>
            </footer>
            <div class="drop-down-bg"></div>
        </div>
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
        </script>
    </body>
</html>