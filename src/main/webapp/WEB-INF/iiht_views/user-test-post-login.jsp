<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*" %>
<html>
    <head>
<title>eAssess</title>
      <link href="images/E-assess_E.png" rel="shortcut icon">        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="newstudentjourney/css/font-awesome.min.css">
        <link rel="stylesheet" href="newstudentjourney/css/bootstrap.min.css">
        <link rel="stylesheet" href="newstudentjourney/css/style_1.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
    </head>
    <body>
        <div class="master-container">
            <header>
                <div class="container-fluid">
                    <a href="#" class="yaksha-logo">
                        E<span>ASSESS</span>
                    </a>
                </div>
            </header>
			<form:form id="studentIntro" method="POST" action="preStudentJourneyNew"  modelAttribute="studentTestForm">
            <section class="content-section">
                <div class="container-fluid">
                    <div class="page-header mb-4">
                        <h1 class="text-center">
                            <a href="#" class="yaksha-logo">
                                E<span>ASSESS</span>
                            </a>
                   ${testIntro.isReadyTOask }
                        </h1>
                    </div>
                    <div class="container">
					
                        <div class="row mt-5">
                            <div class="col-12 text-center mb-2">
                                <h2>
                                    ${studentTestForm.testName}
                                </h2>
                            </div>
                            <div class="col-12 text-center mb-5">
                                ${testIntro.publishedOn } : 04-Nov-2020 17:38:36
                            </div>
                        </div>
                        <div class="row mb-5">
							
                            <div class="col-xs-12 col-md-6 col-xl-4 mb-3">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <div class="count text-alternate">
                                             ${studentTestForm.totalQuestions}
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-file-code-o mr-2"></i>
                                      ${testIntro.totalNoOfQuestion }
                                      </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 col-xl-4 mb-3">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <div class="count text-alternate">
                                             ${studentTestForm.duration} ${testIntro.minutes }
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-clock-o mr-2"></i>
                                        ${testIntro.duration }
                                      </div>
                                </div>
                            </div>
							<%
							Integer noOfAttempts = (Integer) request.getSession().getAttribute("noOfAttempts");
								System.out.println("att "+noOfAttempts);
								
								if(noOfAttempts == null){
									noOfAttempts = 0;
									
								}
								else{
									noOfAttempts = noOfAttempts + 1;
								}
							%>
                            <div class="col-xs-12 col-md-6 col-xl-4 mb-3">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <div class="count text-alternate">
                                             <%= noOfAttempts %>  / ${studentTestForm.noOfAttemptsAvailable}
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-info mr-2"></i>
<!--                                         Number of Attempts -->
									${testIntro.attempt }
                                      </div>
                                </div>
                            </div>
                            <div class="col-12 text-center mt-3 mb-5">
                                <p><i class="fa fa-warning text-warning"></i> ${testIntro.instruction }</p>
								<c:choose>
								<c:when test="${practice != null && practice == true}">
									 <button type="button" class="btn btn-primary" onClick="start2()">Start Practice</button>
									
								</c:when>    
								<c:when test="${test.webProctoring}">
									 <button type="button" class="btn btn-primary" onClick="checkCamera()" id="cam">Check Web Cam</button>
									<br />
								</c:when>    
								<c:otherwise>
									 <button type="button" class="btn btn-primary" onClick="start2()" id="ss">${testIntro.startAssessment }</button>
									<br />
								</c:otherwise>
							</c:choose>
                                
                            </div>
							
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-6 mb-3">
                                <div class="card">
                                    <div class="card-header">
                                       ${footerUTF.instructions}
                                    </div>
                                    <div class="card-body">
                                        <p>${footerUTF.instruct1}</p>
                                        <p>${footerUTF.instruct2}</p>
                                        <p>${footerUTF.instruct3}</p>
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
			</form:form>
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
        </div>
		
		<script type="text/javascript">
		 function start2(){
		        //fullScreen();
					document.getElementById("studentIntro").submit();
				}
				 
		
		function checkCamera(){

			navigator.getMedia = ( navigator.getUserMedia || // use the proper vendor prefix
		            navigator.webkitGetUserMedia ||
		            navigator.mozGetUserMedia ||
		            navigator.msGetUserMedia);
		
				navigator.getMedia({video: true}, function() {
					sweetAlert("Info","You can start test Now","success")
					$("#cam").attr("onclick","start2()");
					$("#cam").text("Start Assessment");
				}, function() {
					sweetAlert("Information","You can not start test without camera","warning")
// 							alert("Test cannot start without camera");
		});	
		}
		
		
		 function sweetAlert(msgtype,message,icon){
			  Swal.fire(
				       msgtype,
				       message,
				       icon
				    )
			}
		</script>
    </body>
</html>