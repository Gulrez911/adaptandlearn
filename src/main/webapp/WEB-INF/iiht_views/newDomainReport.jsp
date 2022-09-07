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
                        <a href="dashboardnew">
                            Dashboard
                        </a>
                        <a href="questionssnew" >
                            Question Bank
                        </a>
                        <a href="testsnew">
                            Tests
                        </a>
                       <a href="#" class="view-more active">
                            Domain Based Results <i class="fa fa-angle-down"></i>
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
							<a href="bookingSlot">
                                Booking Slot Manager
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
                   <form action="dateWiseDomainReport" method="get">
                        <div class="row">
                            <div class="col-xs-12 col-md-4 mx-auto">
                                <h1 class="text-center">Domain based Results</h1>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4 mx-auto">
                                <div class="form-group">
                                    <label for="">Start Date</label>
                                    <input id="datepicker1" name="startDate"	required="required" class="form-control"/>
<!--                                     <input type="date" class="form-control"> -->
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4 mx-auto">
                                <div class="form-group">
                                    <label for="">End Date</label>
                                    <input	id="datepicker2" name="endDate" required="required" class="form-control"/>
<!--                                     <input type="date" class="form-control"> -->
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4 mx-auto">
                                <div class="form-group">
                                    <label for="">Domain Name</label>
                                  <input type="text" id="domain" name="domain" required="required" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4 mx-auto text-right">
                                <button class="btn btn-primary" type="submit">Download Sessions</button>
                            </div>
                        </div>
                    </form>
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


         
        	function isNumberKey(evt)
              {
                 var charCode = (evt.which) ? evt.which : event.keyCode
                 if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                 return true;
              }
        	
        	function encodeAndSend(method, key, value, key1, value1){
        		val = encodeURIComponent(value);
        		val2 = null;
        		if(value1 != null && value1.length > 0){
        			if(value1 <=0 || value1 >=100){
        				notify('Info', 'Enter a valid Pass Percentage number (Between 1 to 99)');
        				return;
        			}
        			
        			val2 = encodeURIComponent(value1);
        			url = ''+method+'?'+key+'='+val+'&'+key1+'='+val2;
        			window.location = url;
        		}	
        		else{
        			notify('Info', 'Enter a valid Pass Percentage');
        		}
        		
        		
        	}
        	function notify(messageType, message){
        		 var notification = 'Information';
        			 $(function(){
        				 new PNotify({
        				 title: notification,
        				 text: message,
        				 type: messageType,
        				 styling: 'bootstrap3',
        				 hide: true
        			     });
        			 }); 	
        		}
        		
        		
        	</script>
        	<script>

        					function Change() {
        						var companyId = $('#name').val();
        						console.log(companyId)
        						$.ajax({
        							url : "fetchTestList?companyId=" + companyId,
        							type : 'GET',
        							success : function(response) {
        								console.log(response.listTest.length)
        								$('.opt').remove();
        								for (i = 0; i < response.listTest.length; i++) {
        									console.log(response.listTest[i]);
        									$("#slct").append(
        											"<option class='opt'>" + response.listTest[i].testName
        													+ "</option>");
        								}
        							},
        						});
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

        	<input id="datepicker" width="270" />
        	<script>
                $('#datepicker1').datepicker({
                    format: 'dd-mm-yyyy',
                    uiLibrary: 'bootstrap'
                });
                $('#datepicker2').datepicker({
                    format: 'dd-mm-yyyy',
                    uiLibrary: 'bootstrap'
                });
            </script>
    </body>
</html>