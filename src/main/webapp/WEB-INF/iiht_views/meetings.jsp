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
<!--       <link href="images/E-assess_E.png" rel="shortcut icon"> -->
<!--         <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!--         <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> -->
<!--         <link href="https://fonts.googleapis.com/new/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet"> -->
<!--                 <link rel="stylesheet" href="new/css/font-awesome.min.css"> -->
<!--         <link rel="stylesheet" href="new/css/bootstrap.min.css"> -->
<!--         <link rel="stylesheet" href="admindashboard/css/style.css"> -->
<!--         <link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css"> -->
	
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script> -->
<!-- 	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<!-- 	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script> -->
<!-- 	<script type="text/javascript" src="scripts/custom.js"></script> -->
<!-- 	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script> -->
<!--     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css"> -->
     	
<!--      	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet"> -->
<!--         <link rel="stylesheet" href="new/css/font-awesome.min.css"> -->
<!--         <link rel="stylesheet" href="new/css/bootstrap.min.css"> -->
<!--         <link rel="stylesheet" href="new/css/style.css"> -->
<!--         <script src="new/campaign/js/jquery.min.js"></script> -->
<!--         <script src="new/campaign/js/bootstrap.min.js"></script> -->
      
      	<link href="images/E-assess_E.png" rel="shortcut icon">     <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
     	
     	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
     	        <link rel="stylesheet" href="new/css/font-awesome.min.css">
        <link rel="stylesheet" href="resources/newrecomm/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/newrecomm/css/style.css">
         <link rel="stylesheet" href="resources/newrecomm/new_css/style.css">
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
                         <a href="dashboardnew">
                            Dashboard
                        </a>
                        <a href="questionssnew">
                            Question Bank
                        </a>
                        <a href="testsnew">
                            Tests
                        </a>
                        <a href="#" class="view-more active">
                            Meetings <i class="fa fa-angle-down"></i>
                        </a>
                        <div class="more-nav">
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
                        </div>
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
                <div class="container-fluid">
                    <div class="page-header mb-4">
                        <h1 class="my-auto">Campaign Meetings</h1>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-6 mb-3">
                            <form action="" class="mb-0">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search Campaign Meeting..." id="searchText">
                                    <div class="input-group-append">
                                        <button class="btn btn-outline-primary" type="button" id="search"><i class="fa fa-search"></i></button>                                        
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row">
						 <c:forEach  items="${campaigns}" var="camp" varStatus="loop"> 
							<div class="col-xs-12 col-md-4 col-xl-3 mb-3">
								<div class="card">
									<div class="card-header">
										${camp.campaignName}
										<div class="actions">
											<!--<button class="btn btn-primary btn-sm" type="button" data-toggle="modal" data-target="#exampleModalCenter"> -->
											<button class="btn btn-primary btn-sm" type="button" onclick="javascript:meetpop('${camp.campaignName}')">
												Schedule
											</button>
											
										</div>
									</div>
									<div class="card-body">
										<p>${camp.candidates.size()} Candidates</p>
										<p class="mb-0">${camp.reviewers.size()} Reviewers</p>
									</div>
								</div>
							</div>
						 </c:forEach>
                        
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
            <div class="drop-down-bg"></div>
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Schedule Meeting</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label id="campaignn"></label>
                                          <!--  <input type="text" class="form-control" readonly value="Campaign Name"> -->
                                        </div>
                                    </div>
									<div class="col-12">
                                        <div class="form-group">
                                            <label for="exampleFormControlSelect0">Select Meeting Round</label>
                                            <select class="form-control" id="exampleFormControlSelect0">
                                              
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label for="exampleFormControlSelect1">Select Candidates</label>
                                            <select class="form-control" id="exampleFormControlSelect1">
                                              
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label for="exampleFormControlSelect2"><b> Reviewers </b></label>
                                           <!-- <textarea id="reviewers" name="reviewers" style="width:100%" rows='8' /> -->
										   <div style="margin-left:auto; margin-right:auto; width:100%; height: 130px; overflow-y: scroll;">
											   <p id="reviewers" style="width:100%;">
											   
											   </p>
										   
										   </div>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="form-group">
                                            <label for="">Start Date and Time</label>
                                            <input type="datetime-local" class="form-control" id="startDate">
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="form-group">
                                            <label for="">End Date and Time</label>
                                            <input type="datetime-local" class="form-control" id="endDate">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" onclick="scheduleMeeting()">Schedule Meeting</button>
                        </div>
                    </div>
                </div>
            </div>
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
		
		 <script>
		 
		  $('#search').on('click',function(){
          	    var text = document.getElementById("searchText").value;
          		if(text.length != 0){
          		window.location="showMeetings?searchText="+text;
          		}
          	    });
		 
		 function meetpop(campaignName){
				document.getElementById("campaignn").innerHTML  = campaignName;
			 
				$.get("getCandidatesForMeeting?campaignName="+campaignName, function(data, status){
				   console.log(data);
					var select = document.getElementById("exampleFormControlSelect1");
					var length = select.options.length;
						for (i = length-1; i >= 0; i--) {
						  select.options[i] = null;
						}
						
						for(index in data) {
							select.options[select.options.length] = new Option(data[index], index);
						}
					return "ok"
				});
				
			
				$.get("getReviewersForMeeting?campaignName="+campaignName, function(data, status){
					   console.log(data);
						
						document.getElementById("reviewers").innerHTML  = data;
						//document.getElementById("reviewers").value = data;
						
						return "ok"
					});
					
				$.get("getMeetingRoundNames?campaignName="+campaignName, function(data, status){
					   console.log(data);
						var select = document.getElementById("exampleFormControlSelect0");
						var length = select.options.length;
						for (i = length-1; i >= 0; i--) {
						  select.options[i] = null;
						}
						
						for(index in data) {
							select.options[select.options.length] = new Option(data[index], index);
						}
						
						return "ok"
					});
		
			$("#exampleModalCenter").modal("show");
		}
		
		function scheduleMeeting(){
			var campaignName = document.getElementById("campaignn").innerHTML;
			var e = document.getElementById("exampleFormControlSelect0");
			var campaignTestName = e.options[e.selectedIndex].text;
			var e1 = document.getElementById("exampleFormControlSelect1");
			var candidate = e1.options[e1.selectedIndex].text;
			var sDate = document.getElementById("startDate").value;
			var eDate = document.getElementById("endDate").value;
			
				if(!campaignTestName){
					notify('Select a Meeting Round');
					return;
				}
				
				if(!candidate){
					notify('Select a Candidate');
					return;
				}
				
				if(!sDate){
					notify('Select a Start Date and Time');
					return;
				}
				
				if(!eDate){
					notify('Select a End Date and Time');
					return;
				}
			
			$.get("scheduleMeeting?campaignName="+campaignName+"&campaignTestName="+campaignTestName+"&candidate="+candidate+"&startDate="+sDate+"&endDate="+eDate, function(data, status){
					   console.log(data);
						
						if(data == 'OK'){
							notify('Meeting is successfully scheduled for '+candidate);
						}
						else{
							notify('There seems to be some problem scheduling this meet. Try another time in case of a schedule conflict. If problem persists, Contact Admin');
						}
					$("#exampleModalCenter").modal("hide");	
						return "ok"
					});
					
			
		}

		 
		function toJavascript(array){
			array=array.replace("[", "");
			array=array.replace("]", "");
			javascriptArray = array.split(",");
			return javascriptArray;
		}
		
		
       	 function sweetAlert(msgtype,message,icon){
			  Swal.fire(
				       msgtype,
				       message,
				       icon
				    )
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