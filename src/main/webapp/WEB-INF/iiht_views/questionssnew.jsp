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
                    </nav>
                    <div class="user-info">
                        <span class="my-auto">EASSESS, Admin</span>
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
                        <h1 class="my-auto">Question Bank</h1>
			                            	<form id="fileFormQuestions" method="POST" enctype="multipart/form-data" >
												<input type="file" name="fileQuestions" id="fileQuestions" style="display:none" />
												<input type="file" name="fileQuestions1" id="fileQuestions1" style="display: none" />
												<input type="file" name="fileQuestions2" id="fileQuestions2" style="display: none" />
											</form>
                			  	<div class="row" style="width:auto">
                                    <div class="col-6">
				                            <button type="button" class="btn btn-primary dropdown-toggle" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				                                   Question Upload
				                            </button>
				                            
					                             <div class="dropdown-menu dropdown-primary" aria-labelledby="dropdownMenu2">
					                                <a class="dropdown-item" href="javascript:mcqUplooad()">MCQ</a>
					                                <a class="dropdown-item" href="javascript:fillUplooad()">Fill In the Blanks</a>
					                                <a class="dropdown-item" href="javascript:matchUplooad()">Match the Following</a>
				                              </div>
		                              </div>
		                              <div class="col-6" style="padding-left: 0; ">
		 		                            <button type="button" class="btn btn-primary dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				                                Create Question
				                            </button>
				                            <div class="dropdown-menu dropdown-primary" aria-labelledby="dropdownMenu1">
				                                <a class="dropdown-item" href="createquestionnew?type=MCQ">MCQ</a>
				                                <a class="dropdown-item" href="createquestionnew?type=CODING">Coding</a>
				                                <a class="dropdown-item" href="createquestionnew?type=FULL_STACK_JAVA">Full Stack Java</a>
				                                <a class="dropdown-item" href="createquestionnew?type=FULLSTACK">Full Stack</a>
				                                <a class="dropdown-item" href="createquestionnew?type=FILL_BLANKS_MCQ">Fill In Blanks</a>
				                                <a class="dropdown-item" href="createquestionnew?type=MATCH_FOLLOWING_MCQ">Match the Following</a>
				                                <a class="dropdown-item" href="createquestionnew?type=IMAGE_UPLOAD_BY_USER">Image Based</a>
				                                <a class="dropdown-item" href="createquestionnew?type=VIDEO_UPLOAD_BY_USER">Video Based</a>
				                                <a class="dropdown-item" href="createquestionnew?type=SUBJECTIVE_TEXT">Subjective</a>
				                              </div>
                              		</div>
                        	</div>
                    </div>
                    <div class="contents">
                        <aside>
                            <div class="quick-search">
                                <form action="newSearchQuestions" method="get">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Search here..." name="searchText" id="searchText" value="${param.searchText}">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button" id="search"><i class="fa fa-search"></i></button>
                                            <button class="btn btn-secondary" type="button"><i class="fa fa-filter"></i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="filter-block mt-3">
                                <h2>Question Type</h2>
                                <ul>
                                    <li>
                                        <a href="questionssnew?filterkey=MCQ">
                                            MCQ - Objective
                                        </a>
                                    </li>
									 <li>
                                        <a href="questionssnew?filterkey=FILL_BLANKS_MCQ">
                                            MCQ - Fill in Blanks
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=MATCH_FOLLOWING_MCQ">
                                            MCQ - Match the Following
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=IMAGE_UPLOAD_BY_USER">
                                            MCQ - Image Based
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=VIDEO_UPLOAD_BY_USER">
                                            MCQ - Video Based
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=SUBJECTIVE_TEXT">
                                            MCQ - Subjective
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=CODING">
                                            Coding
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=JAVA">
                                            Coding - Java
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=C">
                                            Coding - C
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=CPLUSPLUS">
                                            Coding - C++
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=DotNet">
                                            Coding - DotNet
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=CHASH">
                                            Coding - C#
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=PYTHON">
                                            Coding - Python
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=PHP">
                                            Coding - PHP
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=JAVASCRIPT">
                                            Coding - JavaScript
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=CLOJURE">
                                            Coding - Clojure
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=GO">
                                            Coding - GO
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=BASH">
                                            Coding - Bash
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=OBJECTIVE_C">
                                            Coding - ObjectiveC
                                        </a>
                                    </li>
                                    <!-- <li>
                                        <a href="questionssnew?filterkey=MYSQL">
                                            MySQL
                                        </a>
                                    </li> -->
                                    <li>
                                        <a href="questionssnew?filterkey=PERL">
                                            Coding - PERL
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=RUST">
                                            Coding - RUST
                                        </a>
                                    </li>
									
									<li>
                                        <a href="questionssnew?filterkey=FULLSTACK">
                                            Full Stack
                                        </a>
                                    </li>
                                    
                                    <li>
                                        <a href="questionssnew?filterkey=FULL_STACK_JAVA">
                                            Full Stack - Java
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="filter-block mt-3">
                                <h2>Difficulty</h2>
                                <ul>
                                    <li>
                                        <a href="questionssnew?filterkey=VERY_EASY">
                                            very easy
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=EASY">
                                            easy
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=MEDIUM">
                                            medium
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=DIFFICULT">
                                            hard
                                        </a>
                                    </li>
                                    <li>
                                        <a href="questionssnew?filterkey=VERY_DIFFICULT">
                                            very hard
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </aside>
                        <article>
                            <h3 class="mb-4 text-center">Filter by: ${filterType == null?"All":filterType}</h3>
                            <div class="row">
                                <c:forEach  items="${questions}" var="question" varStatus="loop"> 
                                    <div class="col-xs-12 col-md-6 col-xl-4 mb-3">
                                        <div class="card">
                                            <div class="card-header">
                                                Type: ${question.type}
                                                <div class="actions">
                                                    <a href="#" class="admin-view">
                                                        <i class="fa fa-ellipsis-h"></i>
                                                    </a>
                                                    <div class="options">
                                                        <a href="#" class="text-warning" onclick="window.location='createquestionnew?qid=${question.id}'">
                                                            <i class="fa fa-edit mr-2"></i> Edit
                                                        </a>
                                                        <a href="#" class="text-danger" onclick="confirm('${question.id}')">
                                                            <i class="fa fa-trash-o mr-2"></i> Delete
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <h5 class="card-title">${question.questionText}</h5>
                                                <p class="card-text">Parent Category: <span class="text-primary">${question.qualifier1}</span></p>
                                            </div>
                                            <div class="card-footer text-muted">
                                                <ul>
                                                    <li>
                                                        <small>Created On</small> <br>
                                                        ${question.cDate}
                                                    </li>
                                                    <li>
                                                        <small>Updated On</small> <br>
                                                        ${question.uDate}
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
                        </article>
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
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Iste consequatur accusantium perspiciatis omnis blanditiis repudiandae enim officiis vel? Recusandae, blanditiis itaque? Quia aut architecto odio voluptatibus, repellat distinctio consequatur veniam.
            </div>
        </div>
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="new/js/bootstrap.min.js"></script>        
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
        $('.admin-view').on('click', function(){
            var options = $(this).closest('.actions').find('.options');
            if(options.hasClass('open')) {
                options.removeClass('open');
            } else {
                options.addClass('open');
            }
        });
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

	     function    confirm(id){
        	 Swal.fire({
				  title: 'Confirmation Needed',
				  text: 'Are you sure? Do you really want to delete this Q?',
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Yes, delete it!'
				}).then((result) => {
				  if (result.value) {
					 window.location = "newRemoveQuestion?qid="+id;
				  }
				})
            }
	     
	      function   fillUplooad(){
        	 Swal.fire({
				  title: 'Confirmation Needed',
				  text: 'Do you really want to Upload Q?',
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Yes!'
				}).then((result) => {
				  if (result.value) {
						$("#fileQuestions1").click();
				  }
				})
            }

	     function  matchUplooad(){
        	 Swal.fire({
				  title: 'Confirmation Needed',
				  text: 'Do you really want to Upload Q?',
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Yes!'
				}).then((result) => {
				  if (result.value) {
						$("#fileQuestions2").click();
				  }
				})
            }
         
	     function   mcqUplooad(){
        	 Swal.fire({
				  title: 'Confirmation Needed',
				  text: 'Do you really want to Upload Q?',
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Yes!'
				}).then((result) => {
				  if (result.value) {
						$("#fileQuestions").click();
				  }
				})
            }
         
	     	var isXlsx = function(name) {
	 	    	return name.match(/xlsx$/i) || name.match(/xls$/i)
	 	    };


	 	 //fill in the blanks
	     $(document).ready(function() {
	 	    
	 	    var file = $('[name="fileQuestions"]');
// 	 	    var imgContainer = $('#imgContainer');
	 		
	 		var fileU = document.getElementById('fileQuestions');
	 		fileU.addEventListener("change", function () {
	 			  if (fileU.files.length > 0) {
	 			   var filename = $.trim(file.val());
	 			
	 			if (!(isXlsx(filename) )) {
		 			
	 				sweetAlert('Warning', 'Please select an xlsx file to upload','error');
	 				return;
	 			}
	 			
	 			$.ajax({
	 			   xhr: function() {
	 				var xhr = new window.XMLHttpRequest();

	 			  

	 				return xhr;
	 			  },
	 			   url: 'upload',
	 				type: "POST",
	 				data: new FormData(document.getElementById("fileFormQuestions")),
	 				enctype: 'multipart/form-data',
	 				processData: false,
	 				contentType: false
	 			  }).done(function(data) {
	 				 sweetAlert('Information', 'File Upload Successful','success');
	 			   
	 			  }).fail(function(jqXHR, textStatus) {
	 				 sweetAlert('Failure', 'File Upload Failed. Please contact Administrator','error');
	 			  });
	 			  document.getElementById('fileQuestions').value = null;
	 				return;
	 			  }
	 			 
	 			});
	 	  
	 	});

	   //fill in the blanks
	 	$(document).ready(function() {

	 					var file = $('[name="fileQuestions1"]');
	 					var fileU = document.getElementById('fileQuestions1');
	 					fileU.addEventListener("change",function() {
	 						if (fileU.files.length > 0) {
	 							var filename = $.trim(file.val());

	 							if (!(isXlsx(filename))) {
	 								sweetAlert('Warning', 'Please select an xlsx file to upload','error');
	 								return;
	 							}

	 						$.ajax({
	 							xhr : function() {
	 												var xhr = new window.XMLHttpRequest();

	 												return xhr;
	 											},
	 											url : 'uploadFillBlank',
	 											type : "POST",
	 											data : new FormData(
	 													document
	 															.getElementById("fileFormQuestions")),
	 											enctype : 'multipart/form-data',
	 											processData : false,
	 											contentType : false
	 										})
	 								.done(function(response) {
	 									console.log(response.error);
	 									if(response.hasOwnProperty('error')){
	 										//window.location='errorUpload';
	 										//console.log(response.error);
	 										var err = response.error;
	 										err = err.replace(/"/g, '\\\"');
	 										sweetAlert('Warning',err,'error');
// 	 										notify('Information', err);
	 									}
	 									else{
	 						 				 sweetAlert('Information', 'File Upload Successful','success');
// 	 										notify('Success','File Upload Successful');
	 									}
	 											

	 										})
	 								.fail(
	 										function(
	 												jqXHR,
	 												textStatus) {
	 							 				 sweetAlert('Failure', 'File Upload Failed. Please contact Administrator','error');

	 										});
	 						document
	 								.getElementById('fileQuestions1').value = null;
	 						return;
	 					}

	 				});
	 			});
	 	//end fill in blanks
	 	
	 	//start match the following
	$(document).ready(function() {

					var file = $('[name="fileQuestions2"]');

					var fileU = document.getElementById('fileQuestions2');
					fileU.addEventListener("change", function() {
						if (fileU.files.length > 0) {
							var filename = $.trim(file.val());

							if (!(isXlsx(filename))) {
 								sweetAlert('Warning', 'Please select an xlsx file to upload','error');
								return;
							}

							$.ajax({
								xhr : function() {
													var xhr = new window.XMLHttpRequest();

													return xhr;
												},
												url : 'uploadMacherQuestion',
												type : "POST",
												data : new FormData(
														document.getElementById("fileFormQuestions")),
												enctype : 'multipart/form-data',
												processData : false,
												contentType : false
											})
									.done(function(response) {
										console.log(response.error);
										if(response.hasOwnProperty('error')){
										//window.location='errorUpload';
										//console.log(response.error);
											var err = response.error;
											err = err.replace(/"/g, '\\\"');
											sweetAlert('Warning',err,'error');										}
										else{
	 						 				 sweetAlert('Information', 'File Upload Successful','success');

										}
										
											})
									.fail(
											function(
													jqXHR,
													textStatus) {
												 sweetAlert('Failure', 'File Upload Failed. Please contact Administrator','error');
											});
							document
									.getElementById('fileQuestions2').value = null;
							return;
						}
					});
			  });
	//end matc the following
	
	
	     function sweetAlert(msgtype,message,icon){
			  Swal.fire(
				       msgtype,
				       message,
				       icon
				    )
			}
        
//         function searchText(){
        	$('#search').click(function(){
    	    var text = document.getElementById("searchText").value;
    		if(text.length != 0){
        		//alert(text)
    		window.location="newSearchQuestions?searchText="+text;
		    		}
        	})
        	
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