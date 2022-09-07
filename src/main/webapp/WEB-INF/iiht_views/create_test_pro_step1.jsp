<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Quantpro</title>
<link rel="shortcut icon" href="quaintpro/admin/assets/img/favicon.png" type="image/x-icon">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--     <link href="assets/css/bootstrap.min.css" rel="stylesheet"> -->
<!--     <link href="assets/css/style.css" rel="stylesheet"> -->

<link href="quaintpro/admin/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="quaintpro/admin/assets/css/style.css" rel="stylesheet">

<style type="text/css">
.back{
    background: #ffffff;
    border: 1px solid #4782BC;
    border-radius: 40px;
    padding: 8px 35px;
    font-weight: 400;
    font-size: 16px;
    margin-right: 12px;
    
    text-decoration: none;
    color: #4782BC;
    padding-bottom: 11px;
    text-align: center;
}
.back:hover{
    background: #5695d3;
    color: #ffffff;
    }

.opt1 {
	width: 100%;
	border: none;
	overflow: auto;
	    height: 100px !important;
}

.opt1:focus-visible {
    outline: -webkit-focus-ring-color auto 0px;
}

.opt2 {
	background-color: white;
	border: 1px solid #e2e2e2;
	color: black;
	padding: 16px 11px;
	
	font-size: 14px;
	font-family: sans-serif;
	cursor: pointer;
	user-select: none;
	border-radius: 7px;
	display: inline-block;
	margin: 0 10px 10px 0;
	backface-visibility: hidden;
	transition: all 0.6s ease;
}

.opt3 {
	display: inline-block;
	margin-right: 10px;
}

itemLabel {
	font-size: larger;
}
.form-opt{
	font-size: 14px;
}

select#level {
    background: #ffffff;
    border: 1px solid #e2e2e2;
    -webkit-border-radius: 16px;
    -moz-border-radius: 16px;
    border-radius: 16px;
    height: 55px;
}
select#testType{
    background: #ffffff;
    border: 1px solid #e2e2e2;
    -webkit-border-radius: 16px;
    -moz-border-radius: 16px;
    border-radius: 16px;
    height: 55px;
}

</style>
</head>

<body>

	<header>
		<div class="top_header">
			<div class="container">
				<div class="top_header_box">
					<div class="logo">
						<a href="javascript:void(0);"> <img src="quaintpro/admin/assets/img/admin_logo.png">
						</a>
					</div>
					<div class="menu_bar navbar-dark">
						<button class="navbar-toggler my-2" type="button" data-bs-toggle="collapse" data-bs-target="#main_nav"
							aria-expanded="false" aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
					</div>
					<div class="profile_box">
						<div class="setting_notification">
							<div class="notification">
								<a href="javascript:void(0)"><i class="fa-solid fa-bell"></i></a>
							</div>
							<div class="notification">
								<a href="javascript:void(0)"><i class="fa-solid fa-gear"></i></a>
							</div>
							<div class="notification">
								<div class="profile_dropdown">
									<a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
										<div class="media">
											<img src="quaintpro/admin/assets/img/profile.png" alt="profile" class="rounded">
											<div class="media-body">
												<h4>Anubhav Shah</h4>
												<p>Mumbai, India</p>
											</div>
										</div>
									</a>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="proSignOut">Logout</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="buttom_footer">
			<div class="container">
				<nav class="navbar navbar-expand-lg navbar-dark p-0">

					<div class="collapse navbar-collapse justify-content-center lh-lg" id="main_nav">
						<ul class="navbar-nav p-3 p-md-0">
							<li class="nav-item"><a class="nav-link active" href="proindex"> <img
									src="quaintpro/admin/assets/img/dashboard_active.png" class="active"> <img
									src="quaintpro/admin/assets/img/dashboard.png" class="disactive"> Dashboard
							</a></li>
							<li class="nav-item"><a class="nav-link" href="proquestionbank"> <img
									src="quaintpro/admin/assets/img/question_active.png" class="active"> <img
									src="quaintpro/admin/assets/img/question.png" class="disactive"> Question Bank
							</a></li>
							<li class="nav-item"><a class="nav-link" href="protestbank"> <img
									src="quaintpro/admin/assets/img/test_active.png" class="active"> <img
									src="quaintpro/admin/assets/img/test.png" class="disactive">Tests
							</a></li>
							<li class="nav-item dropdown ktm-mega-menu"><a class="nav-link" href="#" data-bs-toggle="dropdown"> <img
									src="quaintpro/admin/assets/img/more.png" class="disactive"> More
							</a></li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
	</header>

	<section class="qb_section Test_section">
		<div class="container">
			<div class="row">
				<div class="col-md-12 mb-4">
					<div class="question_bank_heading">
						<h3>Create New Test</h3>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="test_tab_section">
						<ul class="nav nav-pills mb-3 text-center" id="pills-tab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="nav-link active"  >
									<div class="flex">
										<div class="left_box">
											<i class="fa-solid fa-file-lines"></i><br> Set your Test
										</div>
										<div class="right_box">
											<i class="fa-solid fa-arrow-right-long"></i>
										</div>
									</div>
								</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link"  >
									<div class="flex">
										<div class="left_box">
											<i class="fa-solid fa-file-medical"></i><br> Add Questions
										</div>
										<div class="right_box">
											<i class="fa-solid fa-arrow-right-long"></i>
										</div>
									</div>
								</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link"  >
									<div class="flex">
										<div class="left_box">
											<i class="fa-solid fa-users"></i><br> Add Candidates
										</div>
										<div class="right_box">
											<i class="fa-solid fa-arrow-right-long"></i>
										</div>
									</div>
								</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link"  >
									<div class="flex1">
										<div class="left_box">
											<i class="fa-solid fa-paper-plane"></i><br> Send Invitation
										</div>
									</div>
								</button>
							</li>
						</ul>

						<div class="tab-content tab_con_box" id="pills-tabContent">

							<div class="tab-pane fade show active" id="pills-Set_your_Test" role="tabpanel"
								aria-labelledby="pills-Set_your_Test-tab">
								<div class="Set_your_Test_box">
									<!-- 									<form> -->
									<form id="step1" method="POST" action="saveAndGoToStep2Pro" modelAttribute="test">
										<div class="Set_your_Test_left_box">
											<div class="row">
												<div class="col-md-12">
													<div class="Set_your_Test_left_box_field">
														<div class="mb-3">
															<label for="text" class="form-label">Test Title</label>

															<form:input path="test.testName" name="testName" class="form-control" id="testName" required="true"
																placeholder="Test Name" disabled='${(test.id == null) ? "false":"true"}' />

															<!-- 															<input type="text" class="form-control" id="text" -->
															<!-- 																name="text"> -->

														</div>
													</div>
												</div>

												<div class="col-md-4">
													<div class="Set_your_Test_left_box_field">
														<div class="mb-3">
															<label for="text" class="form-label">Category</label>

															<form:input path="test.qualifier1" name="qualifier1" class="form-control" id="qualifier1" required="true"
																placeholder="Choose Category" />

															<!-- 															<input type="text" class="form-control" id="text" -->
															<!-- 																name="text"> -->

														</div>
													</div>
												</div>

												<div class="col-md-4">
													<div class="Set_your_Test_left_box_field">
														<div class="mb-3">
															<label for="text" class="form-label">Sub Category</label>

															<form:input path="test.qualifier2" name="qualifier2" class="form-control" id="qualifier2"
																placeholder="Choose Sub Category" />

															<!-- 															<input type="text" class="form-control" -->
															<!-- 																id="text" name="text"> -->

														</div>
													</div>
												</div>

												<div class="col-md-4">
													<div class="Set_your_Test_left_box_field">
														<div class="mb-3">
															<label for="text" class="form-label">Category of Sub Category</label>

															<form:input path="test.qualifier3" name="qualifier3" class="form-control" id="qualifier3"
																placeholder="Choose Category of a Sub Category" />

															<!-- 															<input type="text" -->
															<!-- 																class="form-control" id="text" name="text"> -->

														</div>
													</div>
												</div>

												<div class="col-md-12">
													<div class="Set_your_Test_left_box_field1">
														<div class="mb-3">
															<label for="text" class="form-label">Skills</label>


																	<form:select path="test.skls" multiple="multiple" class="opt1">
<!-- 																	 <div class="opt3"> -->
																		<form:options items="${skls}" itemValue="id" itemLabel="label" class="opt2" />
<!-- 																	 </div> -->
																		 
																	</form:select>
																	

<!-- 															<div class="custom_checkbox"> -->
<!-- 																<ul> -->
<!-- 																	<li><input class="cl-custom-check" id="marking_01" type="checkbox" /> <label -->
<!-- 																		class="cl-custom-check-label" for="marking_01" title="Click for select/unselect">General-BASIC</label> -->
<!-- 																	</li> -->

<!-- 																	<li><input class="cl-custom-check" id="marking_013" type="checkbox" /> <label -->
<!-- 																		class="cl-custom-check-label" for="marking_013" title="Click for select/unselect">General-BASIC</label> -->
<!-- 																	</li> -->

<!-- 																	<li><input class="cl-custom-check" id="marking_06" type="checkbox" /> <label -->
<!-- 																		class="cl-custom-check-label" for="marking_06" title="Click for select/unselect">General-BASIC</label> -->
<!-- 																	</li> -->
<!-- 																	<li><input class="cl-custom-check" id="marking_07" type="checkbox" /> <label -->
<!-- 																		class="cl-custom-check-label" for="marking_07" title="Click for select/unselect">General-BASIC</label> -->
<!-- 																	</li> -->
<!-- 																	<li><input class="cl-custom-check" id="marking_08" type="checkbox" /> <label -->
<!-- 																		class="cl-custom-check-label" for="marking_08" title="Click for select/unselect">General-BASIC</label> -->
<!-- 																	</li> -->
<!-- 																	<li><input class="cl-custom-check" id="marking_09" type="checkbox" /> <label -->
<!-- 																		class="cl-custom-check-label" for="marking_09" title="Click for select/unselect">General-BASIC</label> -->
<!-- 																	</li> -->
<!-- 																</ul> -->
<!-- 															</div> -->


														</div>
													</div>
												</div>

												<div class="col-md-12">
													<div class="Set_your_Test_left_box_field1">
														<div class="mb-3">
															<label for="text" class="form-label">Difficulty Level</label>
															<div class="flex-box">

																   <form:select path="test.level" class="form-check">
																												<form:options items="${levels}"   class="form-opt"/>
																											 </form:select>

															 


															</div>
														</div>
													</div>
												</div>


												<div class="col-md-4">
													<div class="Set_your_Test_left_box_field">
														<div class="mb-3">
															<label for="text" class="form-label">Duration</label>
															<div class="d-flex">
																<select>
																	<option>Mins</option>
																</select>

																<form:input path="test.testTimeInMinutes" name="testTimeInMinutes" class="form-control" id="testTimeInMinutes" value=""
																	placeholder="Enter time in minutes" />


																<!-- 																<input type="text" class="form-control" id="text" name="text"> -->

															</div>
														</div>
													</div>
												</div>

												<div class="col-md-4">
													<div class="Set_your_Test_left_box_field">
														<div class="mb-3">
															<label for="text" class="form-label">Passmark in Percentage</label>

															<form:input path="test.passPercent" class="form-control" name="passPercent" id="passPercent" value="" />

															<!-- 															<input type="text" -->
															<!-- 																class="form-control" id="text" name="text"> -->

														</div>
													</div>
												</div>

												<div class="col-md-4">
													<div class="Set_your_Test_left_box_field">
														<div class="mb-3">
															<label for="text" class="form-label">Conducted by</label>

															<form:input path="user.email" name="email" class="form-control" id="email" value="" disabled="true" />

															<!-- 															 <input type="email" class="form-control" -->
															<!-- 																id="text" name="text" placeholder="admin@abc.com"> -->
														</div>
													</div>
												</div>

												<div class="col-md-12">
													<div class="Set_your_Test_left_box_field">
														<div class="mb-3">
															<label for="text" class="form-label">Image URL for Test</label>
															<form:input path="test.imageUrl" name="imageUrl" id="imageUrl" class="form-control" value="" />
															<!-- 															<input type="text" class="form-control" -->
															<!-- 																id="text" name="text" placeholder="https://452.3697-lorem ipsum dolor"> -->
														</div>
													</div>
												</div>

												<div class="col-md-12">
													<div class="Set_your_Test_left_box_field1">
														<div class="mb-3">
															<label for="text" class="form-label">Displayed upon exam completion and email communications
																relating to the exam</label>
															<div class="flex-box">
																<div class="form-check">
																	<form:checkbox path="test.sentToStudent" class="form-check-input" />
																	<!-- 																	<input type="checkbox" class="form-check-input" id="FullstackTest" name="option1" value="something"> -->
																	<label class="form-check-label" for="FullstackTest">Email a copy of the student's results to
																		above contact</label>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div class="col-md-12">
													<div class="Set_your_Test_left_box_field1">
														<div class="mb-3">
															<label for="text" class="form-label"></label>
															<div class="flex-box">
																<div class="form-check">
																	<form:checkbox path="test.sendRecommReport" id="sendRecommReport" class="form-check-input" />

																	<!-- 																	<input type="checkbox" class="form-check-input" id="FullstackTest1" name="option1" value="something"> -->
																	<label class="form-check-label" for="FullstackTest1">Share Recommendation Engine by Email</label>
																</div>
																
																	<div class="form-check">
																	<form:checkbox path="test.displayResultToStudent" id="displayResultToStudent" class="form-check-input" />
																	<label class="form-check-label" for="FullstackTest1">Display Result To Student</label>
																</div>
																
																
																<div class="form-check">

																	<form:checkbox path="test.randomQuestions" id="random" class="form-check-input" />

																	<!-- 																	<input type="checkbox" class="form-check-input" id="FullstackTest2" name="option1" value="something"> -->
																	<label class="form-check-label" for="FullstackTest2">Shuffle Questions in Test</label>
																</div>
															</div>
														</div>
													</div>
												</div>


												<div class="col-md-6">
													<div class="Set_your_Test_left_box_field">
														<div class="mb-3">
															<label for="text" class="form-label">No. of Attempts</label>
															<form:input path="test.noOfConfigurableAttempts" name="noOfConfigurableAttempts"
																id="noOfConfigurableAttempts" class="form-control" value="" onkeypress='validate(event)' />

															<!-- 															<input type="text" class="form-control" -->
															<!-- 																id="text" name="text"> -->
														</div>
													</div>
												</div>

												<div class="col-md-6">
													<div class="Set_your_Test_left_box_field">
														<div class="mb-3">
															<label for="text" class="form-label">Email Domain for Test Givers</label>
															<form:input path="test.domainEmailSupported" name="domainEmailSupported" id="domainEmailSupported"
																class="form-control" value="" placeholder="Enter an email domain" />
															<!-- 															<input type="text" -->
															<!-- 																class="form-control" id="text" name="text"> -->
														</div>
													</div>
												</div>


												<div class="col-md-12">
													<div class="Set_your_Test_left_box_field1">
														<div class="mb-3">
															<label for="text" class="form-label"></label>
															<div class="flex-box">
																<div class="form-check">
																	<form:checkbox path="test.justification" id="justification" class="form-check-input" />
																	<!-- 																	<input type="checkbox" class="form-check-input" id="FullstackTest4" name="option1" value="something"> -->
																	<label class="form-check-label" for="FullstackTest4">Display Justification for Answers</label>
																</div>
																<div class="form-check">
																	<form:checkbox path="test.considerConfidence" id="considerConfidence" class="form-check-input" />
																	<!-- 																	<input type="checkbox" class="form-check-input" id="FullstackTest5" name="option1" value="something"> -->
																	<label class="form-check-label" for="FullstackTest5">Confidence Based Assessment Flag</label>
																</div>
																<div class="form-check">
																	<form:checkbox path="test.fullStackTest" id="fullStackTest" class="form-check-input" />
																	<!-- 																	<input type="checkbox" class="form-check-input" id="FullstackTest6" name="option1" value="something"> -->
																	<label class="form-check-label" for="FullstackTest6">Is this a full stack Test?</label>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div class="col-md-12">
													<div class="Set_your_Test_left_box_field1">
														<div class="mb-3">
															<label for="text" class="form-label">Test Type</label>
															<div class="custom_checkbox">

																															  <form:select path="test.testType">
																												<form:options items="${testTypes}" />
																											</form:select>

  						
																
															</div>
														</div>
													</div>
												</div>


												<div class="col-md-12">
													<div class="Set_your_Test_left_box_field1">
														<div class="mb-3">
															<label for="text" class="form-label"></label>
															<div class="flex-box">
																<div class="form-check">
																	<form:checkbox path="test.onboardingTest" id="onboardingTest" class="form-check-input" />

																	<!-- 																	<input type="checkbox" class="form-check-input" id="FullstackTest_p1" name="option1" value="something"> -->
																	<label class="form-check-label" for="FullstackTest_p1">Is this onboarding test (For Web
																		Proctoring)</label>
																</div>
																<div class="form-check">
																	<form:checkbox path="test.webProctoring" id="webProctoring" class="form-check-input" />
																	<!-- 																	<input type="checkbox" class="form-check-input" id="FullstackTest_p" name="option1" value="something"> -->
																	<label class="form-check-label" for="FullstackTest_p">Proctor Settings<br> <span>Allow
																			webcam proctoring</span></label>
																</div>
															</div>
														</div>
													</div>
												</div>


												<div class="col-md-12">
													<div class="Set_your_Test_left_box_field">
														<div class="mb-3">
															<p>
																<b>Notification & Messages</b>
															</p>
															<label for="text" class="form-label">Exam Introduction Text (Users see this text just before they
																begin the exam)</label>
															<form:textarea path="test.intro" class="form-control" />
															<!-- 															<textarea class="form-control" rows="5" id="comment" name="text" -->
															<!-- 																placeholder="The Test aims to assess understanding of Concepts or Reasoning or Visual language elements or Code. Different Questions represent different challenges. In case of Coding assessments, the test aims to measure functional compliance plus positive/negative/boundary conditions of the Code as written."></textarea> -->
														</div>
													</div>
												</div>

												<div class="col-md-12">
													<div class="Set_your_Test_left_box_field">
														<div class="mb-3">
															<p>
																<b>Text - Completion Success</b>
															</p>
															<label for="text" class="form-label">Exam Introduction Text (Shown to the user if they pass the
																exam. A copy is also sent via email)</label>
															<form:textarea path="test.postTestCompletionText" class="form-control" />
															<!-- 															<textarea class="form-control" rows="5" id="comment" name="text"></textarea> -->
														</div>
													</div>
												</div>


												<div class="col-md-12">
													<div class="Set_your_Test_left_box_field1">
														<div class="mb-3">
															<label for="text" class="form-label">Test Type</label>
															<div class="flex-box">
																<div class="form-check">
																	<form:checkbox path="test.leoForce" id="leoForce" class="form-check-input" />
																	<!-- 																	<input type="checkbox" class="form-check-input" id="FullstackTest_1" name="option1" value="something"> -->
																	<label class="form-check-label" for="FullstackTest_1">Is the test for Leoforce?</label>
																</div>
																<div class="form-check">
																	<form:checkbox path="test.newUi" id="newUi" class="form-check-input" />
																	<!-- 																	<input type="checkbox" class="form-check-input" id="FullstackTest_2" name="option1" value="something"> -->
																	<label class="form-check-label" for="FullstackTest_2">Is the test based on new UI?</label>
																</div>
																
																<div class="form-check">
																	<form:checkbox path="test.newUiPro" id="newUi" class="form-check-input" />
																	<!-- 																	<input type="checkbox" class="form-check-input" id="FullstackTest_2" name="option1" value="something"> -->
																	<label class="form-check-label" for="FullstackTest_2">Is the test based on new Pro UI?</label>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div class="col-md-12 col-lg-12">
													<div class="question_save text-center">
<!-- 														<button class="cancle_btn">Cancel</button> -->
<!-- 														<button class="save_btn">Next</button> -->
														
<!-- 														      <a   class="cancle_btn" href="testlist">Cancel</a> -->
<!--                          							       <a   class="save_btn" href="#" id="next">Next</a> -->
                         							       
                         							      <a class="cancle_btn back" href="#" onclick="location.href = 'protestbank';">Cancel</a>
                         							       <button   class="save_btn"   id="next">Next</button>
                                
													</div>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>

							 

							 

							 

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<script src="quaintpro/admin/assets/js/jquery.slim.min.js"></script>
	<script src="quaintpro/admin/assets/js/bootstrap.bundle.min.js"></script>
	<script src="quaintpro/admin/assets/js/jquery-slider-min.js"></script>
	<script src="quaintpro/admin/assets/js/apexcharts.js"></script>
	<script src="quaintpro/admin/assets/js/fontawesome.min.js"></script>
	<script src="quaintpro/admin/assets/js/custom.js"></script>
	
	 <script>
	 $('#next').on('click',function(){
	   //addteststep2
	   document.getElementById("step1").submit();
	    });
		
		function validate(evt) {
		  var theEvent = evt || window.event;

		  // Handle paste
		  if (theEvent.type === 'paste') {
			  key = event.clipboardData.getData('text/plain');
		  } else {
		  // Handle key press
			  var key = theEvent.keyCode || theEvent.which;
			  key = String.fromCharCode(key);
		  }
		  var regex = /[0-9]|\./;
		  if( !regex.test(key) ) {
			theEvent.returnValue = false;
			if(theEvent.preventDefault) theEvent.preventDefault();
		  }
		}
		
		</script>
</body>

</html>