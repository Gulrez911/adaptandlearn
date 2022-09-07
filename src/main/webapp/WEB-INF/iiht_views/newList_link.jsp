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
     	<!--  -->
				<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- 				<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"> -->
				<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
				
				<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
				
				<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
				
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
                            Test Links Management <i class="fa fa-angle-down"></i>
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
							<a href="bookingSlot">
                                Booking Slot Manager
                            </a>
                        </div>
                    </nav>
                    <div class="user-info">
                        <span class="my-auto">EASSESS, Admin</span>
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
                    <div class="page-header mb-4" style="margin: 0px;border-bottom: none;">
                        <h1 class="my-auto">Test Link Mangement</h1>
                        <div class="quick-actions my-auto">
                            <button type="button" class="btn btn-primary" onclick="opeModal()">
                                Create New Public Test
                            </button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-6 mb-3">
                           <form action="searchTestLinks" method="get">
                                <div class="input-group mb-0">
                                    <input type="text" class="form-control" placeholder="Search Here" name="searchText" id="searchText" value="${param.searchText}">
									<div class="input-group-append" style="margin-left: 0px;height: 34px">
                                        <button class="btn btn-outline-primary" type="button" id="search" style="border-color: #007bff"><i class="fa fa-search"></i></button>                                        
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-12">

                        </div>
                        <c:forEach  items="${links}" var="link" varStatus="loop">  
			                        <div class="col-xs-12 col-md-4 col-xl-3 mb-3">
			                            <div class="card">
			                                <div class="card-header">
			                                    <c:out value="${link.testName}"></c:out> 
			                                    <div class="actions">
			                                        <a  class="admin-view" style="cursor: pointer;">
			                                            <i class="fa fa-ellipsis-h"></i>
			                                        </a>
			                                        <div class="options" style="cursor: pointer;">
			                                            <a  class="text-warning" onclick="getLink(${link.id})">
			                                                <i class="fa fa-edit mr-2"></i> Edit
			                                            </a>
			                                            
			                                        </div>
			                                    </div>
			                                </div>
			                                <div class="card-body">
			                                    <p class="card-text" id="${link.id}">${link.url}</p>
			                                    <p class="card-text"><a href="javascript:copyLink('${link.id}')"><i class="fa fa-copy"></i></a></p>
			                                </div>
			                                <div class="card-footer text-muted">
			                                    <ul>
			                                        <li>
			                                            <small>Start Date</small> <br>
			                                          ${link.stDate}
			                                        </li>
			                                        <li>
			                                            <small>End Date</small> <br>
			                                            ${link.edDate}
			                                        </li>
			                                    </ul>
			                                </div>
			                            </div>
			                        </div>
			                        </c:forEach>
                        <div class="col-12 text-center mt-5">
                            <ul class="custom-pagination">
                                <c:if test="${showPreviousPage}">
                                            <li class="page-item">
                                                <a class="page-link" href="${callingMethod}?page=${previousPage}${queryParam}">Previous</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${selectedPage != null &&  selectedPage > 0}">
                                            <li class="page-item">
                                                <a class="page-link">${selectedPage} / ${totalNumberOfPages}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${showNextPage}">
                                            <li class="page-item">
                                                <a class="page-link" href="${callingMethod}?page=${nextPage}${queryParam}">Next</a>
                                            </li>
                                        </c:if>
                            </ul>
                        </div>
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
            <div class="modal fade bd-example-modal-lg" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Test Details</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                          <form name="linkForm"  method="post" modelAttribute="link" action="newSaveTestLink">
                                <div class="row">
                                    <div class="col-xs-12 col-md-4 mx-auto">
                                        <div class="form-group">
                                            <label for="">Select Test</label>
                                          	<form:select id="testName" path="link.testName" class="form-control" >
											<form:options items="${tests}"  />
										</form:select>
										<form:hidden path="link.id" id="linkId"/>
<!--                                             <input type="date" class="form-control"> -->
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row">
                                    <div class="col-xs-12 col-md-4 mx-auto">
                                        <div class="form-group">
                                            <label for="">Start Date</label>
                                             <div class='input-group date' id='datetimepicker1'>
																		<form:input path="link.stDate"  class="form-control" id="startDate" required="true" /> <span class="input-group-addon">
																				<span class="glyphicon glyphicon-calendar"></span>
																		</span>
																</div>
<!--                                             <input type="date" class="form-control"> -->
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-md-4 mx-auto">
                                        <div class="form-group">
                                            <label for="">End Date</label>
                                            <div class='input-group date' id='datetimepicker2'>
																		<form:input path="link.edDate" class="form-control" id="endDate" required="true"  /> <span class="input-group-addon">
																				<span class="glyphicon glyphicon-calendar"></span>
																		</span>
																</div>
<!--                                             <input type="date" class="form-control"> -->
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-md-4 mx-auto">
                                        <div class="form-group">
                                           <form:checkbox path="link.dontCheckTimeValidity"  id="dontCheckTimeValidity" />
                                            <label for="">Don't Check Time Validity </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-md-4 mx-auto">
                                        <div class="form-group">
                                            <form:checkbox path="link.webProctored" id="webProctored"/> 
                                            <label for="">Web Proctoring ON</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-md-4 mx-auto text-right">
                                        <button class="btn btn-primary" type="submit">Save Test Link</button>
                                    </div>
                                </div>
                            </form>
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
 
            $('#datetimepicker1').datetimepicker({
				format: 'DD/MM/YYYY hh:mm a'
				//minDate:new Date()
				
			});
         
			
			
          $('#datetimepicker2').datetimepicker({
				format: 'DD/MM/YYYY hh:mm a'
				
			});

          function opeModal(){
        		$("#linkId").val("");
				$("#startDate").val("");
				$("#endDate").val("");
				$("#exampleModalCenter").modal("show");
              }
			function getLink(id){
				$.get("getLinkById?id="+id, function(data, status){
					$("#linkId").val(data.link.id);
					$("#testName").val(data.link.testName);
					var sdate=moment(data.link.startDate).format('DD/MM/YYYY hh:mm a');
					$("#startDate").val(sdate);
					var edate=moment(data.link.endDate).format('DD/MM/YYYY hh:mm a');
					$("#endDate").val(edate);
					$("#dontCheckTimeValidity").prop( "checked", data.link.dontCheckTimeValidity);
					$("#webProctored").prop( "checked", data.link.webProctored);
					$("#exampleModalCenter").modal("show");
				});
				}

			$('#search').on('click',function(){
        	    var text = document.getElementById("searchText").value;
        		if(text.length != 0){
        		window.location="searchTestLinks?searchText="+text;
        		}
        	   });
			
			function copyLink(id){
				  var text = $("#"+id).text();
				  var tempElement= $('<input>').val(text).appendTo('body').select();
				  document.execCommand('copy');
				  tempElement.remove();
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