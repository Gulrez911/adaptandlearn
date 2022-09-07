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
      <link href="images/E-assess_E.png" rel="shortcut icon">        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    
        <link rel="stylesheet" href="new/css/font-awesome.min.css">

     
	
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

 </style>
    </head>
    <body>
        <div class="master-container">
            <header>
            </header>
            <section class="content-section mt-5">
                <div class="container-fluid">
                    <div class="page-header mb-4 user-login">
                        <h1 class="text-center">
                            <a href="#" class="yaksha-logo">
                                 E<span>ASSESS</span>
                            </a>
                             <span>${campaignTestUTF.isReadyToAsk}</span>
                        </h1>
                    </div>
                    <div class="container">
                        <div class="row mt-5">
                            <div class="col-12 text-center mb-2">
                                <h3>
                                   ${campaign.campaignName} 
                                </h3>
                            </div>
							 <div class="col-12 text-center mb-2">
                                
                                    ${campaign.campaignDesc}
                                
                            </div>
                            <div class="col-12 text-center mb-2">
                                ${campaignTestUTF.publishedOn} : ${campaign.publishedDate}
                            </div>
                            <div class="col-12 text-center mb-2">
                                <i class="fa fa-info mr-2"></i>
                                ${campaignTestUTF.skillAssociated} : ${campaign.skillsConcatenated}
                            </div>
                            <div class="col-12 text-center mb-4 text-danger">
                               ${campaignTestUTF.campaignLinkExpire} <strong>${expireDays} ${campaignTestUTF.days}</strong>
                            </div>
                        </div>
                        <div class="row mb-5">
                            <c:forEach  items="${campaign.rounds}" var="test" varStatus="loop">   
							<div class="col-xs-12 col-md-6 mb-3" >
                                <div class="card text-center">
                                    <div class="card-header">
                                        ${test.testName} 
                                    </div>
                                    <div class="card-body" style="background-color:${test.meeting == true?'lightblue':''}">
                                      ${test.testType.equals("")?"Meeting":test.testType}  -   ${test.noOfMCQQuestions + test.noOfCodingQuestions + test.noOfFullStackQuestions} 	${campaignTestUTF.questions}
                                    </div>
									<c:choose>
											<c:when test="${test.complete==false}">
											<div class="card-footer">
											</c:when> 
											<c:otherwise>
												<div class="card-footer" style="background-color:lightgreen">
											</c:otherwise>
									</c:choose>
                                    
                                        <span class="my-auto mr-5">
                                            <i class="fa fa-clock-o mr-1"></i>
											<c:choose>
												<c:when test="${test.meeting == true}">
													${campaignTestUTF.minutes} - ${test.meetingStartTime}
												</c:when>
												<c:otherwise>
													 ${test.testDuration} ${campaignTestUTF.minutes}
												</c:otherwise>
											</c:choose>
                                            
                                        </span>
                                        <!-- <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#exampleModalCenter"> -->
										
										<c:choose>
											<c:when test="${test.complete==false && test.meeting == false}">
												<button class="btn btn-primary" type="button" onClick="startAssessment('${test.url}')" >
												  ${campaignTestUTF.startAssessment}
												</button>
											</c:when> 
											<c:when test="${test.complete==false && test.meeting == true}">
												<button class="btn btn-primary" type="button" onClick="startMeeting('${test.url}')" >
												 ${campaignTestUTF.startMeeting}
												</button>
											</c:when> 
											<c:otherwise>
												${campaignTestUTF.completedThisRound}
											</c:otherwise>
										</c:choose>
										
										  
                                    </div>
                                </div>
                            </div>
							</c:forEach>
							
                            <div class="col-12 text-center mt-5">
                                <p><i class="fa fa-warning text-warning mr-2"></i> ${campaignTestUTF.readTheInstruction}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-6 mb-3">
                                <div class="card">
                                    <div class="card-header">
<!--                                         INSTRUCTIONS -->
							${footerUTF.instructions}
                                    </div>
                                    <div class="card-body">
                                        <p>		${footerUTF.instruct1}</p>
                                        <p>		${footerUTF.instruct2}</p>
                                        <p>		${footerUTF.instruct3}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 mb-3">
                                <div class="card">
                                    <div class="card-header">
                                       	${footerUTF.webProctoring}
                                    </div>
                                    <div class="card-body">
                                        <p>${footerUTF.webProctor1}</p>
                                        <p>${footerUTF.webProctor2}</p>
                                        <p>${footerUTF.webProctor3}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 mb-3">
                                <div class="card">
                                    <div class="card-header">
                                     ${footerUTF.tenants}
                                    </div>
                                    <div class="card-body">
                                        <p>${footerUTF.tenants1}</p>
                                        <p>${footerUTF.tenants2}</p>
                                        <p>${footerUTF.tenants3}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 mb-3">
                                <div class="card">
                                    <div class="card-header">
                                        EASSESS
                                    </div>
                                    <div class="card-body">
                                        <p>${footerUTF.eassess1}</p>
                                        <p>${footerUTF.eassess2}</p>
                                        <p>${footerUTF.eassess3}</p>
                                    </div>
                                </div>
                            </div>
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
                      	${footerUTF.termsAndConditions}
                    </a>
                    <a href="#">
                        ${footerUTF.privacyPolicy}
                    </a>
                </div>
            </footer>
            <div class="drop-down-bg"></div>
            <div class="modal fade bd-example-modal-lg" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Assessment Modal</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p class="text-center p-5">Modal Content</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
			
				<div class="modal fade" id="assessModal" tabindex="-1" role="dialog" aria-labelledby="assessModal">
					<div class="modal-dialog" role="document" >
						<div class="modal-content" style="width:1020;height:720;right:50%">
							<div class="modal-body">
								<iframe id="iframeYoutube" width="1000" height="600" src="" frameborder="0" allowfullscreen></iframe>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
        </div>
		<script>
			$(document).ready(function() {
				$("#assessModal").on("hidden.bs.modal", function() {
					$("#iframeYoutube").attr("src", "#");
				})
			})

		
			function startAssessment(url){
				var iframe = document.getElementById("iframeYoutube");
				iframe.src =url
				$("#assessModal").modal("show");;
			}
			
			function startMeeting(url){
				if(!url){
					notify('Meeting not yet Scheduled');
					return;
				}
				window.open(url, '_blank');
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