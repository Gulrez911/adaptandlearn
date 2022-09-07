<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<html>
    <head>
		<title>eAssess</title>
		 <link href="images/E-assess_E.png" rel="shortcut icon">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <script src="new/js/jquery.min.js"></script>
        <script src="new/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="admindashboard/css/font-awesome.min.css">
        <link rel="stylesheet" href="admindashboard/css/bootstrap.min.css">
        <link rel="stylesheet" href="admindashboard/css/style.css">
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
                            License Management<i class="fa fa-angle-down"></i>
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
                    <div class="page-header mb-4">
                        <h1 class="my-auto">Licenses Management</h1>
                        <div class="quick-actions my-auto">
                            <button class="btn btn-primary" type="button" onclick="openModal()">
                                Create New License
                            </button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-6 mb-3">
                         <form action="searchLicenses" method="get">
                                <div class="input-group mb-0">
                                    <input type="text" class="form-control" placeholder="Search Here" name="searchText" id="searchText" value="${param.searchText}">
                                    <div class="input-group-append">
                                        <button class="btn btn-outline-primary" type="button" id="search"><i class="fa fa-search"></i></button>                                        
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-12">

                        </div>
                            <c:forEach  items="${licenses}" var="lic" varStatus="loop">  
			                        <div class="col-xs-12 col-md-4 col-xl-3 mb-3">
			                            <div class="card">
			                                <div class="card-header">
			                                    <div class="actions">
			                                        <a  class="admin-view" style="cursor: pointer;">
			                                            <i class="fa fa-ellipsis-h"></i>
			                                        </a>
			                                        <div class="options" style="cursor: pointer;">
			                                            <a  class="text-warning"  onclick="editLicense(${lic.id})">
			                                                <i class="fa fa-edit mr-2"></i> Edit
			                                            </a>
			                                            <a  class="text-danger" >
			                                                <i class="fa fa-trash-o mr-2"></i> Delete
			                                            </a>
			                                        </div>
			                                    </div>
			                                </div>
			                                <div class="card-body">
			                                    <h5 class="card-title"><c:out value="${lic.licenseName}"></c:out></h5>
			                                    <p class="card-text">${lic.licenseDesc}</p>
			                                </div>
			                                <div class="card-footer text-muted">
			                                    <ul>
			                                        <li>
			                                            <small>Created On</small> <br>
			                                          ${lic.formattedCreationDate} 
			                                        </li>
			                                        <li>
			                                            <small>Updated On</small> <br>
			                                           ${lic.formattedUpdationDate} 
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
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Create/Edit License</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                     <form name="licenseForm"  method="post" modelAttribute="license" action="newSavelicense">
				                        <div class="modal-body">
				                                <div class="row">
				                                    <div class="col-12">
				                                        <div class="form-group">
				                                            <label for="">License Name</label>
				                                            <form:input path="license.licenseName" class="form-control" required="true" readonly="${licenseNameReadOnly}"  id="licenseName"/>
											<form:hidden path="license.id" id="licenseId"/>
				<!--                                             <input type="text" class="form-control"> -->
				                                        </div>
				                                    </div>
				                                    <div class="col-12">
				                                        <div class="form-group">
				                                            <label for="">Description</label>
				                                            	<form:textarea path="license.licenseDesc" class="form-control" rows="5" id="licenseDesc"/>
				<!--                                             <textarea name="" id="" rows="5" class="form-control"></textarea> -->
				                                        </div>
				                                    </div>
				                                    <div class="col-12">
				                                        <div class="form-group">
				                                            <label for="">Image URL</label>
				                                            	<form:input path="license.licenseImage" class="form-control"  id="licenseImg"/>	
				<!--                                             <input type="text" class="form-control"> -->
				                                        </div>
				                                    </div>
				                                    
				                                     <div class="col-12">
				                                        <div class="form-group">
				                                           <label>Is this free?</label>
				                                            	<form:checkbox path = "license.free" id="isFree"/>	
				                                        </div>
				                                    </div>
				                                </div>
				                        </div>
				                        <div class="modal-footer">
				                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				                            <button type="submit" class="btn btn-primary">Save changes</button>
				                        </div>
                            </form>
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

            function editLicense(id){
            	
				$.get("editLicense?id="+id, function(data, status){
					console.log(data.license);
					$("#licenseId").val(data.license.id);
					$("#licenseName").val(data.license.licenseName);
					$("#licenseDesc").val(data.license.licenseDesc);
					$("#licenseImg").val(data.license.licenseImage);
					$("#isFree").prop( "checked", data.license.free);
					$("#exampleModalCenter").modal("show");
				});
                }
            function openModal(){
					$("#licenseId").val("");
					$("#licenseName").val("");
					$("#licenseDesc").val("");
					$("#licenseImg").val("");
					$("#exampleModalCenter").modal("show");
					
                }

    		$('#search').on('click',function(){
        	    var text = document.getElementById("searchText").value;
        		if(text.length != 0){
        		window.location="searchLicenses?searchText="+text;
        		}
        	    });
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