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

        <link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	
	   <script src="new/campaign/js/jquery.min.js"></script>
        <script src="new/campaign/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
     	
     	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="new/css/font-awesome.min.css">
        <link rel="stylesheet" href="new/campaign/css/bootstrap.min.css">
        <link rel="stylesheet" href="new/campaign/css/style.css">
     <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
     <style>
	.add-tests .card {
    margin: 10px;
    width: 28%;
    height: 78%;
	}
	
	.campaign-test .steps-block ul li {
		display: block;
		padding: 6px;
		border-bottom:none;
	}
	 
	 </style>
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
                            Campaigns <i class="fa fa-angle-down"></i>
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
                    <form action="">
                        <div class="row">
                            <div class="col-xs-12 col-md-10 mx-auto">
                                <div class="page-header">
                                    <h1 class="my-auto">Create New Test Campaign</h1>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-10 mx-auto form-steps mt-5">
                                <div class="d-flex flex-sm-column flex-md-row justify-content-between">
                                    <a href="#step-1" class="steps-link active d-flex pt-3 pr-3 pb-3">
                                        <span class="icon mr-2">
                                            <i class="fa fa-file-text"></i>
                                        </span>
                                        <span class="my-auto">
                                            Test Campaign Details
                                        </span>
                                    </a>
                                    <a href="#step-2" class="steps-link d-flex p-3">
                                        <span class="icon mr-2">
                                            <i class="fa fa-file-text-o"></i>
                                        </span>
                                        <span class="my-auto">
                                            Add Tests Campaigns
                                        </span>
                                    </a>
                                    <a href="#step-3" class="steps-link d-flex p-3">
                                        <span class="icon mr-2">
                                            <i class="fa fa-users"></i>
                                        </span>
                                        <span class="my-auto">
                                            Add Candidates and Reviewers
                                        </span>
                                    </a>
                                    <a href="#step-4" class="steps-link d-flex pt-3 pl-3 pb-3">
                                        <span class="icon mr-2">
                                            <i class="fa fa-paper-plane-o"></i>
                                        </span>
                                        <span class="my-auto">
                                            Send Invitation
                                        </span>
                                    </a>
                                </div>
                                <div class="border-line"></div>
                            </div>
							
							
							
							
                            <div class="col-xs-12 col-md-10 mx-auto mt-5" id="step-1">
                                <div class="d-flex flex-column">
                                    <div class="form-group">
                                        <label for="">Campaign Name</label>
                                        <input id="campaignName" type="text" class="form-control" value="${campaign.campaignName}" ${disabled == false?'':'disabled'}>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="">Campaign Language</label>
                                        <form:select path="campaign.language" class="form-control"   id="campaignLanguage" >
										<form:options items="${langs}" />
											</form:select> 
<%--                                         <input id="campaignName" type="text" class="form-control" value="${campaign.campaignName}" ${disabled == false?'':'disabled'}> --%>
                                    </div>
                                    
                                    
                                    
                                    <div class="form-group mt-3">
                                        <label for="">Campaign Description</label>
                                        <textarea  id="campaignDesc" class="form-control" >${campaign.campaignDesc}</textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-10 mx-auto mt-5 d-none" id="step-2">
							
								
								
								
                                <div class="d-flex flex-wrap justify-content-center campaign-test">
									<div id="campaignTests">
										
									</div>
                                    <div class="steps-block d-flex justify-content-center align-items-center mx-2">
                                       <!-- <button class="btn btn-primary" type="button" data-toggle="modal" data-target=".bd-example-modal-lg" onClick="openCampaignTestsDialog()">
                                            Add Test
                                        </button> -->
										<button class="btn btn-primary" type="button" onClick="openCampaignTestsDialog()">
                                            Add Test
                                        </button>
                                    </div>
                                </div>
                                <div class="mt-3 d-flex justify-content-center">
                                    <div class="form-check mr-3">
                                        <input type="checkbox" class="form-check-input">
                                        <label class="form-check-label">Non Sequenced</label>
                                    </div>
                                    <div class="form-check">
                                        <input type="checkbox" class="form-check-input">
                                        <label class="form-check-label">Sequenced</label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-10 mx-auto mt-5 d-none" id="step-3">
                                <div class="d-flex flex-nowrap justify-content-center campaign-test">
                                    <div class="steps-block d-flex p-3 mx-2" style="min-width: 600;">
                                        <!--<button class="btn btn-primary btn-sm candidates" type="button" data-toggle="modal" data-target=".add-candidates">
                                            Update Candidate
                                        </button> -->
										<button class="btn btn-primary btn-sm candidates" type="button" onclick="searchAndPopulateCandidatesTable('')" >
                                            Add Candidates
                                        </button>
											<div id = "candidatesId">
											
											</div>
										
                                    </div>
                                    <div class="steps-block d-flex justify-content-center align-items-center mx-2" style="min-width:600">
                                        <!-- <button class="btn btn-primary" type="button" data-toggle="modal" data-target=".add-candidates">
                                            Add Reviewers
                                        </button> -->
										<button class="btn btn-primary" type="button" onclick="searchAndPopulateReviewersTable('')">
                                            Add Reviewers
                                        </button>
										<div id = "reviewersId">
											
											</div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-10 mx-auto mt-5 d-none" id="step-4">
                                <div class="block mb-4">
                                    <h1>Send Invitation</h1>
                                </div>
                                <div class="d-flex flex-sm-column flex-md-row justify-content-between">
                                    <div class="d-flex flex-column mt-4">
                                        <h3>${campaign.campaignName}</h3>
                                        <p class="mt-3">${campaign.skillsForCampaign}</p>
                                        <div class="d-flex flex-nowrap justify-content-center campaign-test mr-3" id="campaignItemsSummaryId">
                                            
                                            
                                        </div>
                                        <div class="d-flex flex-sm-column flex-md-row justify-content-between campaign-test preview mt-5">
                                            <div class="steps-block d-flex flex-column p-3 mx-2" id="campaignCandidatesSummaryId">
                                                
                                            </div>
                                            <div class="steps-block d-flex flex-column p-3 mx-2" id="campaignReviewersSummaryId">
                                                
												
                                            </div>
                                        </div>
										<br/>
										<div class="d-flex flex-column duration-block">
											<div class="form-group">
												<label for=""><b>Link Start Date and Time</b></label>
												<input type="date" class="form-control" id="startDate">
											</div>
											<div class="form-group mt-3">
												<label for=""><b>Link End Date and Time</b></label>
												<input type="date" class="form-control" id="endDate">
											</div>
										</div>
                                    </div>
                                    
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-10 mx-auto mt-5 mb-5">
                                <hr class="mb-3">
                                <div class="d-flex flex-sm-column flex-md-row justify-content-between campaign-btn">
                                    <span>
                                        <button class="btn btn-secondary mr-3" type="button" onClick="gotoCampaigns();">
                                            Cancel
                                        </button>
                                        <button class="btn btn-outline-secondary" data-val="back" type="button">
                                            Back
                                        </button>
                                    </span>
                                    <span>
                                        <button class="btn btn-primary" data-val="next" type="button" id="nextid" onClick="saveCampaign();">
                                            Next
                                        </button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="contents create-test d-none">
                            <div class="row steps">
                                <div class="col-12 pb-5 mb-5">
                                    <ul class="form-steps">
                                        <li class="active">
                                            <div class="thumbnail">
                                                <i class="fa fa-list-ol"></i>
                                            </div>
                                            <div class="step-name">Test Campaign Details</div>
                                        </li>
                                        <li>
                                            <div class="thumbnail">
                                                <i class="fa fa-users"></i>
                                            </div>
                                            <div class="step-name">Add Tests</div>
                                        </li>
                                        <li>
                                            <div class="thumbnail">
                                                <i class="fa fa-users"></i>
                                            </div>
                                            <div class="step-name">Add Candidates</div>
                                        </li>
                                        <li>
                                            <div class="thumbnail">
                                                <i class="fa fa-users"></i>
                                            </div>
                                            <div class="step-name">Send Invite</div>
                                        </li>
                                    </ul>
                                </div>
                                
                                <div class="col-12 d-none" id="step-2">
                                    <div class="steps-form">
                                        <div class="row">
                                            <div class="col-12 mb-4">
                                                <div class="page-heading">
                                                    <h2>Add Tests</h2>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 mb-2">
                                                <div class="input-group mb-3">
                                                    <div class="input-group-prepend">
                                                        <div class="input-group-text">
                                                            <input type="radio" aria-label="Radio for following text input">
                                                        </div>
                                                    </div>
                                                    <div class="input-group-text">
                                                      Sequenced
                                                    </div>
                                                    <div class="input-group-text">
                                                        <input type="radio" aria-label="Radio for following text input">
                                                    </div>
                                                    <div class="input-group-append">
                                                        <div class="input-group-text">
                                                            Non Sequenced
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="input-group mt-4">
                                                    <input type="text" class="form-control" placeholder="Step 1">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text text-danger">
                                                            <i class="fa fa-minus"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="input-group mt-2">
                                                    <input type="text" class="form-control" placeholder="Step 2">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text text-white bg-success">
                                                            <i class="fa fa-plus"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-8 mb-2">
                                               
                                                <div class="row">
                                  
													
                                                    <div class="col-12 text-center">
                                                        <ul class="pagination">
                                                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                                                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                                                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                                                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                                                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 d-none" id="step-3">
                                    <div class="steps-form">
                                        <div class="row">
                                            <div class="col-12 mb-4">
                                                <div class="page-heading">
                                                    <h2>Add Candidates</h2>
                                                </div>
                                            </div>
                                            <div class="col-12 mb-4">
                                                <div class="block">
                                                    <a href="#" class="ml-3">View Selected</a>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-6 col-xl-4 mb-2">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h5>Firstname Lastname</h5>
                                                        <p>username@email.com</p>
                                                    </div>
                                                    <div class="card-footer">
                                                        <button class="btn btn-primary" type="button">
                                                            <i class="fa fa-plus mr-1"></i> Add
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-6 col-xl-4 mb-2">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h5>Firstname Lastname</h5>
                                                        <p>username@email.com</p>
                                                    </div>
                                                    <div class="card-footer">
                                                        <button class="btn btn-primary" type="button">
                                                            <i class="fa fa-plus mr-1"></i> Add
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-6 col-xl-4 mb-2">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h5>Firstname Lastname</h5>
                                                        <p>username@email.com</p>
                                                    </div>
                                                    <div class="card-footer">
                                                        <button class="btn btn-primary" type="button">
                                                            <i class="fa fa-plus mr-1"></i> Add
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-6 col-xl-4 mb-2">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h5>Firstname Lastname</h5>
                                                        <p>username@email.com</p>
                                                    </div>
                                                    <div class="card-footer">
                                                        <button class="btn btn-primary" type="button">
                                                            <i class="fa fa-plus mr-1"></i> Add
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 text-center">
                                                <ul class="pagination">
                                                    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                                                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 d-none" id="step-4">
                                    <div class="steps-form">
                                        <div class="row">
                                            <div class="col-12 mb-4">
                                                <div class="page-heading">
                                                    <h2>Send Invitation</h2>
                                                </div>
                                            </div>
                                             
                                            
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 mt-3">
                                    <div class="steps-form">
                                        <div class="form-actions">
                                            <span>
                                                <button class="btn btn-outline-secondary mr-3" type="button">
                                                    Cancel
                                                </button>
                                                <button class="btn btn-secondary back d-none" type="button">
                                                    Back
                                                </button>
                                            </span>
                                            <button class="btn btn-primary next" type="button">
                                                Next
                                            </button>
                                            <button class="btn btn-primary send-invitation d-none" type="button">
                                                Send Invitation
                                            </button>
                                        </div>
                                    </div>
                                </div>
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
            <div class="modal fade bd-example-modal-lg" id="step2Modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" >
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <form action="">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Add Tests</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body add-tests">
                                <div class="d-flex justify-content-between">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Campaign Section Title" id="campaignTestName">
                                    </div>
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Search Tests..." id="searchText">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button" onClick="searchTests()"><i class="fa fa-search"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex align-content-start flex-wrap mt-3" id="campaignTestsDialogId">
                                    <!-- <div class="card">
                                        <div class="card-body">
                                            <h5>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsa delectus quos perspiciatis eveniet eos ut officia magnam provident laborum</h5>
                                            <p class="mb-0"><span class="keyword mr-1">Skills</span><span class="keyword mr-1">Skills</span><span class="keyword mr-1">Skills</span><span class="keyword mr-1">Skills</span><span class="keyword mr-1">Skills</span></p>
                                        </div>
                                        <div class="card-footer d-flex justify-content-between">
                                            <div class="my-auto">
                                                <i class="fa fa-clock-o mr-1"></i> 90 Min <i class="fa fa-list ml-3 mr-1"></i> 34 
                                            </div>
                                            <button class="btn btn-primary" type="button">
                                                <i class="fa fa-plus mr-1"></i> Add
                                            </button>
                                        </div>
                                    </div> -->
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal fade bd-example-modal-lg add-candidates" tabindex="-1" role="dialog" aria-labelledby="myLargeModalCandidate" aria-hidden="true" id="candidatesModal">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <form action="">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Search and Add Candidates</h5>
                                <!--<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button> -->
								 <button type="button" class="close" onclick="goToStep2AndCloseCandModal()">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="d-flex mb-3">
                                    <div class="input-group">
                                        <input id="candidateSearchId" type="text" class="form-control" placeholder="search candidate by name or email">
										<button class="btn btn-primary btn-sm" type="button" onclick="getCandidates()" style="width:100;color:white;" value="Search">
                                    </div>
                                </div>
								<div id="candidatesTableId">
									
								</div>
                            </div>
                            <div class="modal-footer">
                               <!-- <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
                               <button type="button" class="btn btn-secondary"  onclick="goToStep2AndCloseCandModal()">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
			
			
			<!--Add modal for reviewers -->
			<div class="modal fade bd-example-modal-lg add-reviewers" tabindex="-1" role="dialog" aria-labelledby="myLargeModalCandidate" aria-hidden="true" id="reviewersModal">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <form action="">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Search and Add Reviewers</h5>
                                <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button> -->
								<button type="button" class="close" onClick="goToStep2AndCloseReviewModal()">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="d-flex mb-3">
                                    <div class="input-group">
                                        <input type="text" id="reviewerSearchId" class="form-control" placeholder="search reviewer by name or email">
										<button class="btn btn-primary btn-sm" type="button" onclick="getReviewers()" style="width:100;color:white;" value="Search" >
                                    </div>
                                </div>
								<div id="reviewersTableId">
									
								</div>
                            </div>
                            <div class="modal-footer">
                                <!--<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>  -->
                               <button type="button" class="btn btn-secondary" onClick="goToStep2AndCloseReviewModal()">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
			
			<!-- end modal for reviewers -->
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
            $('.form-steps a.steps-link').on('click', function(){
                $('#step-1, #step-2, #step-3, #step-4').addClass('d-none');
                $('.form-steps a.steps-link').removeClass('active');
                $(this).addClass('active');
                var $target = $(this).attr('href');
                $($target).removeClass('d-none');
            });
            $('.campaign-btn button.btn').on('click', function(){
                var $this = $(this),
                    $curval = $('.form-steps a.steps-link.active').attr('href'),
                    $current = parseInt($curval.slice(6,7)),
                    $target = 0;
                if('back' == $this.attr('data-val')) {
                    $target = $current - 1;
					
					if($target < 4){
						var button = document.getElementById('nextid');
						button.innerText = button.textContent = 'Next';
						
					}
					
					
                    if($target > 0) {
                        $('#step-1, #step-2, #step-3, #step-4').addClass('d-none');
                        $('#step-'+$target).removeClass('d-none');
                    } else {
                        return;
                    }
                } 
				else if('cancel' == $this.attr('data-val')){
					window.location = 'showCampaigns';
				}
				else {
					var val = $this.attr('data-val');
                    $target = $current + 1;
					
					if($target == 2){
						var ret = goToStep2();
						
						if(ret == 'fail'){
							return;
						}
						
					}
					
					if($target == 3){
						var ret = goToStep3();
						
					}
					
					if($target == 4){
						goToStep4();
						var button = document.getElementById('nextid');
						button.innerText = button.textContent = 'Save and Share Invite';
					}
					
					
					
					
					
                    if($target <= 4) {
                        $('#step-1, #step-2, #step-3, #step-4').addClass('d-none');
                        $('#step-'+$target).removeClass('d-none');
                    } else {
                        return;
                    }
                }
				
				
				
				
                $('.form-steps a.steps-link').removeClass('active');
                $('.form-steps a.steps-link[href="#step-'+$target+'"]').addClass('active');
            });
			
			
			function gotoCampaigns(){
				
				window.location = 'showCampaigns';
			}
			
			function saveCampaign(){
				var button = document.getElementById('nextid');
				
				
				if(button.innerText == 'Save and Share Invite' || button.textContent =='Save and Share Invite'){
					
					var sd = document.getElementById("startDate").value;
					var ed = document.getElementById("endDate").value;
				
					if(!sd){
						notify("Enter a valid Start Date");
						return;
					}
				
					if(!ed){
						notify("Enter a valid End Date");
						return;
					}
					
					sd =  encodeURIComponent(sd);
					ed =  encodeURIComponent(ed);
					window.location = 'saveCampaign?startDate='+sd+'&endDate='+ed;
				}
				
				
			}
			
			
			function goToStep2AndCloseCandModal(){
				$("#candidatesModal").modal('hide');
				goToStep3();
			}
			
			function goToStep2AndCloseReviewModal(){
				$("#reviewersModal").modal('hide');
				goToStep3();
			}
			
			function step2(){
				
				var campaignLanguage = document.getElementById("campaignLanguage").value;
				var campaignName = document.getElementById("campaignName").value;
				var campaignDesc = document.getElementById("campaignDesc").value;
				if(campaignName){
					campaignName = encodeURIComponent(campaignName);
				}
				else{
					notify("Campaign Name can not be empty or null");
					return "fail";
				}
				
				if(campaignDesc){
					campaignDesc = encodeURIComponent(campaignDesc);
				}
				else{
					campaignDesc = 'NA';
				}
				// else{
					// notify("Campaign Desc can not be empty or null");
					// return "fail";
				// }
				
				$.get("goToStep2?campaignName="+campaignName+"&campaignDesc="+campaignDesc+"&campaignLanguage="+campaignLanguage, function(data, status){
				   console.log(data);
					$("#campaignTests").empty();
					$("#campaignTests").append(data);
					$("#step2Modal").modal('hide');
					return "ok"
				});
			}
			
			function goToStep2(){
			var campaignName = document.getElementById("campaignName").value;
				$.get("checkForCampaignName?campaignName="+campaignName, function(data, status){
				   if(data =='ok'){
					   step2();
				   }
				   else{
					  notify('Use a different name for the campaign. This name already exists');
					   $('#step-1, #step-2, #step-3, #step-4').addClass('d-none');
                        $('#step-1').removeClass('d-none');
					  return "fail";
				   }
				});
				
			
			
			}
			
			
			function goToStep3(){
			var campaignName = document.getElementById("campaignName").value;
			
				
				$.get("getSelectedCandidatesForCampaign?campaignName="+campaignName, function(data, status){
				   console.log(data);
					$("#candidatesId").empty();
					$("#candidatesId").append(data);
						$.get("getSelectedReviewersForCampaign?campaignName="+campaignName, function(data, status){
						   console.log(data);
							$("#reviewersId").empty();
							$("#reviewersId").append(data);
							
							return "ok"
						});
				});
			}
			
			function deleteCampaignTest(tid){
				$.get("removeCampaignStep?testid="+tid, function(data, status){
				   console.log(data);
					$("#campaignTests").empty();
					$("#campaignTests").append(data);
					$("#step2Modal").modal('hide');
				}); 
			}
			
			
			
			
			function addTestToCampaign(tid){
			var stepName = document.getElementById("campaignTestName").value;
				if(stepName){
					stepName = encodeURIComponent(stepName);
				}
				else{
					notify("Test Campaign Step or Name can not be empty or null");
					return;
				}
			
				$.get("addCampaignStep?testid="+tid+"&stepName="+stepName, function(data, status){
				   console.log(data);
				$("#campaignTests").empty();
				$("#campaignTests").append(data);
				$("#step2Modal").modal('hide');
				}); 
			}
			
			//addMeetingToCampaign
			function addMeetingToCampaign(mid){
			var stepName = document.getElementById("campaignTestName").value;
				if(stepName){
					stepName = encodeURIComponent(stepName);
				}
				else{
					notify("Test Campaign Step or Name can not be empty or null");
					return;
				}
			
				$.get("addMeetingToCampaign?meetId="+mid+"&stepName="+stepName, function(data, status){
				   console.log(data);
				$("#campaignTests").empty();
				$("#campaignTests").append(data);
				$("#step2Modal").modal('hide');
				}); 
			}
			
			function openCampaignTestsDialog(){
				$.get("showCampaignStepDialog", function(data, status){
					   console.log(data);
					$("#campaignTestsDialogId").empty();
					$("#campaignTestsDialogId").append(data);
					$("#step2Modal").modal('show');
					//$("#bd-example-modal-lg").modal('show');
					//.bd-example-modal-lg
				});
			}
			
			function searchTests(){
				var srch = document.getElementById("searchText").value;
				if(srch){
					srch = encodeURIComponent(srch);
				}
				else{
				notify("Test Campaign Step or Name can not be empty or null");
				}
				
				$.get("showCampaignStepDialog?searchTest="+srch, function(data, status){
					   console.log(data);
					$("#campaignTestsDialogId").empty();
					$("#campaignTestsDialogId").append(data);
					$("#step2Modal").modal('show');
					
				});
			}
			
			
			function changeReviewerCheckbox(checkboxElem, campaignName){
				campaignName = encodeURIComponent(campaignName);
				 if (checkboxElem.checked) {
					 
						$.get("selectReviewerForCampaign?rid="+checkboxElem.id+"&campaignName="+campaignName, function(data, status){
						   console.log(data);
						notify("Candidate is selected for the campaign ");
						var cs = document.getElementById("T"+checkboxElem.id).getElementsByTagName("td");
							for (var i = 0; i < cs.length; i++) {
								cs[i].style.backgroundColor = "lightblue";
							}
						});
				  } else {
						$.get("unselectReviewerForCampaign?rid="+checkboxElem.id+"&campaignName="+campaignName, function(data, status){
						   console.log(data);
						notify("Candidate is selected for the campaign ");
						var cs = document.getElementById("T"+checkboxElem.id).getElementsByTagName("td");
						notify("Candidate is de-selected for the campaign ");
							for (var i = 0; i < cs.length; i++) {
								cs[i].style.backgroundColor = "";
							}
						});
				  }
			}
			
			function changeCandidateCheckbox(checkboxElem, campaignName){
				campaignName = encodeURIComponent(campaignName);
				 if (checkboxElem.checked) {
					 
						$.get("selectCandidateForCampaign?cid="+checkboxElem.id+"&campaignName="+campaignName, function(data, status){
						   console.log(data);
						notify("Candidate is selected for the campaign ");
						var cs = document.getElementById("T"+checkboxElem.id).getElementsByTagName("td");
							for (var i = 0; i < cs.length; i++) {
								cs[i].style.backgroundColor = "lightblue";
							}
						});
				  } else {
						$.get("unselectCandidateForCampaign?cid="+checkboxElem.id+"&campaignName="+campaignName, function(data, status){
						   console.log(data);
						notify("Candidate is selected for the campaign ");
						var cs = document.getElementById("T"+checkboxElem.id).getElementsByTagName("td");
						notify("Candidate is de-selected for the campaign ");
							for (var i = 0; i < cs.length; i++) {
								cs[i].style.backgroundColor = "";
							}
						});
				  }
				
			}
			
			function getReviewers(){
				/* if(event.key === 'Enter') {
					searchAndPopulateReviewersTable(ele.value);        
				} */
				var search = document.getElementById("reviewerSearchId").value;
				searchAndPopulateReviewersTable(search);
			}
			
			function getCandidates(){
				/* if(event.key === 'Enter') {
					searchAndPopulateCandidatesTable(ele.value);        
				} */
				var search = document.getElementById("candidateSearchId").value;
				searchAndPopulateCandidatesTable(search); 
			}
			
			function searchAndPopulateCandidatesTable(search){
				url = '';
				var campaignName = document.getElementById("campaignName").value;
				campaignName = encodeURIComponent(campaignName);
				
				if(search){
					search = encodeURIComponent(search);
					url = "searchAndPopulateCandidatesTable?search="+search+"&campaignName="+campaignName;
				}
				else{
					url = "searchAndPopulateCandidatesTable?campaignName="+campaignName;
				}
				
					$.get(url, function(data, status){
						console.log(data);
						$("#candidatesTableId").empty();
						$("#candidatesTableId").append(data);
						$("#candidatesModal").modal('show');
					});
			}
			
			function searchAndPopulateReviewersTable(search){
				url = '';
				var campaignName = document.getElementById("campaignName").value;
				campaignName = encodeURIComponent(campaignName);
				
				if(search){
					search = encodeURIComponent(search);
					url = "searchAndPopulateRviewersTable?search="+search+"&campaignName="+campaignName;
				}
				else{
					url = "searchAndPopulateRviewersTable?campaignName="+campaignName;
				}
				
					$.get(url, function(data, status){
						console.log(data);
						$("#reviewersTableId").empty();
						$("#reviewersTableId").append(data);
						$("#reviewersModal").modal('show');
					});
			}
			
			function goToStep4(){
				var campaignName = document.getElementById("campaignName").value;
				campaignName = encodeURIComponent(campaignName);
				$.get("getCampaigStepsSummary?campaignName="+campaignName, function(data, status){
				   console.log(data);
					$("#campaignItemsSummaryId").empty();
					$("#campaignItemsSummaryId").append(data);
					
				});
				
				$.get("getSelectedCandidatesSummary?campaignName="+campaignName, function(data, status){
				   console.log(data);
					$("#campaignCandidatesSummaryId").empty();
					$("#campaignCandidatesSummaryId").append(data);
					
				});
				
				$.get("getSelectedReviewersSummary?campaignName="+campaignName, function(data, status){
				   console.log(data);
					$("#campaignReviewersSummaryId").empty();
					$("#campaignReviewersSummaryId").append(data);
					
				});
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