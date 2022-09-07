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
.opt1 {
	width: 100%;
	border: none;
	overflow: auto;
}

.opt2 {
	background-color: white;
	border: 1px solid #e2e2e2;
	color: black;
	padding: 5px 10px;
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
										<a class="dropdown-item" href="login.html">Logout</a>
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
							<li class="nav-item"><a class="nav-link" href="index.html"> <img
									src="quaintpro/admin/assets/img/dashboard_active.png" class="active"> <img
									src="quaintpro/admin/assets/img/dashboard.png" class="disactive"> Dashboard
							</a></li>
							<li class="nav-item"><a class="nav-link" href="question-bank.html"> <img
									src="quaintpro/admin/assets/img/question_active.png" class="active"> <img
									src="quaintpro/admin/assets/img/question.png" class="disactive"> Question Bank
							</a></li>
							<li class="nav-item"><a class="nav-link active" href="tests.html"> <img
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
								<button class="nav-link active" id="pills-Set_your_Test-tab" data-bs-toggle="pill"
									data-bs-target="#pills-Set_your_Test" type="button" role="tab" aria-controls="pills-Set_your_Test"
									aria-selected="true">
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
								<button class="nav-link" id="pills-Add_Questions-tab" data-bs-toggle="pill"
									data-bs-target="#pills-Add_Questions" type="button" role="tab" aria-controls="pills-Add_Questions"
									aria-selected="false">
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
								<button class="nav-link" id="pills-Add_Candidates-tab" data-bs-toggle="pill"
									data-bs-target="#pills-Add_Candidates" type="button" role="tab" aria-controls="pills-Add_Candidates"
									aria-selected="false">
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
								<button class="nav-link" id="pills-Send_Invitation-tab" data-bs-toggle="pill"
									data-bs-target="#pills-Send_Invitation" type="button" role="tab" aria-controls="pills-Send_Invitation"
									aria-selected="false">
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
									<form id="step1" method="POST" action="saveAndGoToStep2" modelAttribute="test">
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

<!-- 																<ul> -->
<!-- 																	<li><input class="cl-custom-check" id="marking_016" type="checkbox" /> <label -->
<!-- 																		class="cl-custom-check-label" for="marking_016" title="Click for select/unselect">Java</label></li> -->

<!-- 																	<li><input class="cl-custom-check" id="marking_0135" type="checkbox" /> <label -->
<!-- 																		class="cl-custom-check-label" for="marking_0135" title="Click for select/unselect">Microsoft -->
<!-- 																			Technologies</label></li> -->

<!-- 																	<li><input class="cl-custom-check" id="marking_0134" type="checkbox" /> <label -->
<!-- 																		class="cl-custom-check-label" for="marking_0134" title="Click for select/unselect">C/C++</label></li> -->


<!-- 																	<li><input class="cl-custom-check" id="marking_0133" type="checkbox" /> <label -->
<!-- 																		class="cl-custom-check-label" for="marking_0133" title="Click for select/unselect">Python</label></li> -->

<!-- 																	<li><input class="cl-custom-check" id="marking_0132" type="checkbox" /> <label -->
<!-- 																		class="cl-custom-check-label" for="marking_0132" title="Click for select/unselect">General -->
<!-- 																			knowledge</label></li> -->

<!-- 																	<li><input class="cl-custom-check" id="marking_0131" type="checkbox" /> <label -->
<!-- 																		class="cl-custom-check-label" for="marking_0131" title="Click for select/unselect">Composite -->
<!-- 																			Test</label></li> -->
<!-- 																</ul> -->
																
																
																
																
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
															</div>
														</div>
													</div>
												</div>

												<div class="col-md-12 col-lg-12">
													<div class="question_save text-center">
														<button class="cancle_btn">Cancel</button>
														<button class="save_btn">Next</button>
													</div>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>

							<div class="tab-pane fade" id="pills-Add_Questions" role="tabpanel" aria-labelledby="pills-Add_Questions-tab">
								<div class="Set_your_Test_box">
									<div class="present_box">
										<div class="pre_left">
											<p>Percent of Qs to be asked</p>
											<div class="precentage">
												<p>
													<b>80%</b>
												</p>
											</div>
										</div>
										<div class="pre_right">
											<button class="Categories">Show All Parent Categories</button>
											<button class="Categories">Save Section</button>
											<button class="Categories Selected">Show Selected</button>
											<button class="Categories Clear">Clear All</button>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12 col-lg-8">
											<div class="question_bank_sec Add_Sections_box">
												<h3>Import Questions into Section</h3>
												<div class="question_bank_sec mb-4">
													<div class="search">
														<div class="wrap">
															<div class="search">
																<input type="text" class="searchTerm" placeholder="Search test here">
																<button type="submit" class="searchButton">
																	<i class="fa fa-search" aria-hidden="true"></i>
																</button>
															</div>
														</div>
													</div>
												</div>
												<div class="question_sec_table">
													<table class="table table-borderless">
														<thead>
															<tr>
																<th>
																	<div class="form-check">
																		<input class="form-check-input" type="checkbox" id="check1" name="option1" value="something">
																	</div>

																</th>
																<th>Question</th>
																<th>Category</th>
																<th>Level</th>
																<th>Weight</th>
																<th>Select All Qs in Category</th>
																<th>Remove All Qs in Category</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>
																	<div class="form-check">
																		<input class="form-check-input" type="checkbox" id="check1" name="option1" value="something">
																	</div>
																</td>
																<td>(2D Translation) A translation is applied to an object by</td>
																<td><a href="javascript:void(0)">2D Transformation and Viewing / 2D Translation</a></td>
																<td>Very Difficult</td>
																<td>10</td>
																<td class="text-center"><a hre="javascript:void(0)"> <img
																		src="quaintpro/admin/assets/img/library_add.png" class="disactive">
																</a></td>
																<td class="text-center"><a hre="javascript:void(0)"> <img
																		src="quaintpro/admin/assets/img/delete_icon.png" class="disactive">
																</a></td>
															</tr>


														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="col-md-12 col-lg-4">
											<div class="question_filter All_Sections">
												<div class="filter_heading question_sec">
													<h3>Sections</h3>
													<div class="add_ques">
														<a href="javascript:void(0);"> <img src="quaintpro/admin/assets/img/new_Add.png">
														</a>
													</div>
												</div>
												<div class="question_bank_sec mb-4">
													<div class="search">
														<div class="wrap">
															<div class="search">
																<input type="text" class="searchTerm" placeholder="Search test here">
																<button type="submit" class="searchButton">
																	<i class="fa fa-search" aria-hidden="true"></i>
																</button>
															</div>
														</div>
													</div>
												</div>


												<div class="question_type_sec mb-4">
													<div class="flex">
														<div class="question_type_heading">
															<p>
																<b>Question Type</b>
															</p>
															<p>12 questions</p>
														</div>
														<div class="delete">
															<a href="javascript:void()"> <img src="quaintpro/admin/assets/img/delete.png">
															</a>
														</div>
													</div>
												</div>





												<div class="question_type_sec mb-4">
													<div class="flex">
														<div class="question_type_heading">
															<p>
																<b>Question Type</b>
															</p>
															<p>12 questions</p>
														</div>
														<div class="delete">
															<a href="javascript:void()"> <img src="quaintpro/admin/assets/img/delete.png">
															</a>
														</div>
													</div>
												</div>


											</div>
										</div>
										<div class="col-md-12 col-lg-12 mt-4">
											<div class="question_save text-center">
												<button class="cancle_btn">Cancel</button>
												<button class="save_btn">Next</button>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="tab-pane fade" id="pills-Add_Candidates" role="tabpanel" aria-labelledby="pills-Add_Candidates-tab">
								<div class="Set_your_Test_box">
									<div class="present_box">
										<div class="pre_left">
											<div class="question_bank_sec mb-4">
												<div class="search">
													<div class="wrap">
														<div class="search">
															<input type="text" class="searchTerm" placeholder="Search test here">
															<button type="submit" class="searchButton">
																<i class="fa fa-search" aria-hidden="true"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="pre_right">

											<button class="Categories">Save Section</button>
											<button class="Categories Selected">Show Selected</button>
											<button class="Categories Clear">Clear All</button>
										</div>
									</div>




									<div class="row">
										<div class="col-md-12 col-lg-12">
											<div class="question_bank_sec Add_Sections_box">
												<h3>All Candidate</h3>

												<div class="question_table">
													<div class="scroll_table">
														<table class="table table-borderless">
															<thead>
																<tr>
																	<th>Candidate Name</th>
																	<th>mail id</th>
																	<th>Department</th>
																	<th>Group</th>
																	<th></th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>Aashna Rehman</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>rehman_aashna@gmail.com</td>
																	<td>Lorem ipsum dolor amet</td>
																	<td>Lorem Ipsum</td>
																	<td>
																		<div class="add_btn">
																			<button class="add">Add</button>
																		</div>
																	</td>
																</tr>

																<tr>
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img profile_bg1">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>Aashna Rehman</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>rehman_aashna@gmail.com</td>
																	<td>Lorem ipsum dolor amet</td>
																	<td>Lorem Ipsum</td>
																	<td>
																		<div class="add_btn">
																			<button class="add">Add</button>
																		</div>
																	</td>
																</tr>

																<tr>
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img profile_bg2">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>Aashna Rehman</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>rehman_aashna@gmail.com</td>
																	<td>Lorem ipsum dolor amet</td>
																	<td>Lorem Ipsum</td>
																	<td>
																		<div class="add_btn">
																			<button class="add">Add</button>
																		</div>
																	</td>
																</tr>

																<tr>
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img profile_bg3">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>Aashna Rehman</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>rehman_aashna@gmail.com</td>
																	<td>Lorem ipsum dolor amet</td>
																	<td>Lorem Ipsum</td>
																	<td>
																		<div class="add_btn">
																			<button class="add">Add</button>
																		</div>
																	</td>
																</tr>

																<tr>
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>Aashna Rehman</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>rehman_aashna@gmail.com</td>
																	<td>Lorem ipsum dolor amet</td>
																	<td>Lorem Ipsum</td>
																	<td>
																		<div class="add_btn">
																			<button class="add">Add</button>
																		</div>
																	</td>
																</tr>

																<tr>
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img profile_bg1">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>Aashna Rehman</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>rehman_aashna@gmail.com</td>
																	<td>Lorem ipsum dolor amet</td>
																	<td>Lorem Ipsum</td>
																	<td>
																		<div class="add_btn">
																			<button class="add">Add</button>
																		</div>
																	</td>
																</tr>

																<tr>
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img profile_bg2">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>Aashna Rehman</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>rehman_aashna@gmail.com</td>
																	<td>Lorem ipsum dolor amet</td>
																	<td>Lorem Ipsum</td>
																	<td>
																		<div class="add_btn">
																			<button class="add">Add</button>
																		</div>
																	</td>
																</tr>

																<tr>
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img profile_bg3">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>Aashna Rehman</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>rehman_aashna@gmail.com</td>
																	<td>Lorem ipsum dolor amet</td>
																	<td>Lorem Ipsum</td>
																	<td>
																		<div class="add_btn">
																			<button class="add">Add</button>
																		</div>
																	</td>
																</tr>

																<tr>
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>Aashna Rehman</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>rehman_aashna@gmail.com</td>
																	<td>Lorem ipsum dolor amet</td>
																	<td>Lorem Ipsum</td>
																	<td>
																		<div class="add_btn">
																			<button class="add">Add</button>
																		</div>
																	</td>
																</tr>

																<tr>
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img profile_bg1">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>Aashna Rehman</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>rehman_aashna@gmail.com</td>
																	<td>Lorem ipsum dolor amet</td>
																	<td>Lorem Ipsum</td>
																	<td>
																		<div class="add_btn">
																			<button class="add">Add</button>
																		</div>
																	</td>
																</tr>

																<tr>
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img profile_bg2">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>Aashna Rehman</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>rehman_aashna@gmail.com</td>
																	<td>Lorem ipsum dolor amet</td>
																	<td>Lorem Ipsum</td>
																	<td>
																		<div class="add_btn">
																			<button class="add">Add</button>
																		</div>
																	</td>
																</tr>

																<tr>
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img profile_bg3">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>Aashna Rehman</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>rehman_aashna@gmail.com</td>
																	<td>Lorem ipsum dolor amet</td>
																	<td>Lorem Ipsum</td>
																	<td>
																		<div class="add_btn">
																			<button class="add">Add</button>
																		</div>
																	</td>
																</tr>

															</tbody>
														</table>
													</div>
													<div class="pagination_box mt-4">
														<div class="pagination">
															<button class="btn">
																<i class="fa-solid fa-chevron-left"></i>
															</button>
															<div class="pages">
																<a class="page active">1</a> <a class="page">2</a> <a class="page">3</a> <a class="page">...</a> <a
																	class="page">9</a>
															</div>
															<button class="btn active">
																<i class="fa-solid fa-chevron-right"></i>
															</button>
														</div>
													</div>
												</div>

											</div>
										</div>

										<div class="col-md-12 col-lg-12 mt-3">
											<div class="question_save text-center mt-4">
												<button class="cancle_btn">Cancel</button>
												<button class="save_btn">Next</button>
											</div>
										</div>

									</div>
								</div>
							</div>

							<div class="tab-pane fade" id="pills-Send_Invitation" role="tabpanel" aria-labelledby="pills-Send_Invitation-tab">
								<div class="Set_your_Test_box">
									<div class="question_bank_sec Add_Sections_box mt-4">
										<h3 class="mt-4">Send Invitation</h3>
										<div class="row">
											<div class="col-md-12 col-lg-8">
												<div class="question_table_sec_box">
													<div class="question_table Send_Invitation">
														<div class="invited_test">
															<div class="row">
																<div class="col-md-12">
																	<div class="invited_box">
																		<label>Test Name</label>
																		<p>
																			<b>Lorem ipsum dolor amet Demo Test 1.0</b>
																		</p>
																	</div>
																</div>

																<div class="col-md-6">
																	<div class="invited_box">
																		<label>Category Name</label>
																		<p>
																			<b>Aptitude/ General</b>
																		</p>
																	</div>
																</div>

																<div class="col-md-6">
																	<div class="invited_box">
																		<label>Duration</label>
																		<p>
																			<b>30 Mins</b>
																		</p>
																	</div>
																</div>


																<div class="col-md-6">
																	<div class="invited_box">
																		<label>Attemps</label>
																		<p>
																			<b>10</b>
																		</p>
																	</div>
																</div>

																<div class="col-md-12">
																	<div class="accordian_section">
																		<div class="accordion" id="accordionExample">
																			<div class="accordion-item">
																				<h2 class="accordion-header" id="headingOne">
																					<button class="accordion-button" type="button" data-bs-toggle="collapse"
																						data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Section 1
																					</button>
																				</h2>
																				<div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne"
																					data-bs-parent="#accordionExample">
																					<div class="accordion-body">
																						<div class="accordian_body_sec">
																							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas atque nemo voluptate
																								consectetur. Possimus culpa corrupti qui. Autem minima maiores corporis rerum magnam! Veniam,
																								harum pariatur. Rerum officia maiores odit!</p>
																						</div>
																					</div>
																				</div>
																			</div>
																			<div class="accordion-item">
																				<h2 class="accordion-header" id="headingTwo">
																					<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
																						data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">Section 2
																					</button>
																				</h2>
																				<div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
																					data-bs-parent="#accordionExample">
																					<div class="accordion-body">
																						<div class="accordian_body_sec">
																							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas atque nemo voluptate
																								consectetur. Possimus culpa corrupti qui. Autem minima maiores corporis rerum magnam! Veniam,
																								harum pariatur. Rerum officia maiores odit!</p>
																						</div>
																					</div>
																				</div>
																			</div>
																			<div class="accordion-item">
																				<h2 class="accordion-header" id="headingThree">
																					<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
																						data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
																						Section 3</button>
																				</h2>
																				<div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
																					data-bs-parent="#accordionExample">
																					<div class="accordion-body">
																						<div class="accordian_body_sec">
																							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas atque nemo voluptate
																								consectetur. Possimus culpa corrupti qui. Autem minima maiores corporis rerum magnam! Veniam,
																								harum pariatur. Rerum officia maiores odit!</p>
																						</div>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>

													<div class="row mt-4">
														<div class="col-md-6 mt-3">
															<div class="Create_New_Question_field">
																<div class="mb-3">
																	<label for="text" class="form-label">Select Date</label> <input type="date" class="form-control"
																		id="text" name="text">
																</div>
															</div>
														</div>
														<div class="col-md-6 mt-3">
															<div class="Create_New_Question_field">
																<div class="mb-3">
																	<label for="text" class="form-label">&nbsp;</label> <input type="date" class="form-control" id="text"
																		name="text">
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="col-md-12 col-lg-4">
												<div class="invite_condidate">
													<div class="recent_test results">
														<div class="condidate_table">
															<div class="table_scroll">
																<table class="table table-borderless table-sm">
																	<tbody>
																		<tr>
																			<td>
																				<div class="test_result">
																					<a href="test-analytics.html"> <!-- <span>Java Test Part 1</span> -->
																						<div class="condidate_box">
																							<div class="condidate_icon">
																								<p>
																									<b>AC</b>
																								</p>
																							</div>
																							<div class="condidate_name">
																								<h4>Aarti Chaudhary</h4>
																								<p>aarti_choudhary@abc.com</p>
																							</div>
																						</div>
																					</a>
																				</div>
																			</td>
																		</tr>

																		<tr>
																			<td>
																				<div class="test_result">
																					<a href="test-analytics.html"> <!-- <span>Java Test Part 1</span> -->
																						<div class="condidate_box">
																							<div class="condidate_icon ac_1">
																								<p>
																									<b>AC</b>
																								</p>
																							</div>
																							<div class="condidate_name">
																								<h4>Aarti Chaudhary</h4>
																								<p>aarti_choudhary@abc.com</p>
																							</div>
																						</div>
																					</a>
																				</div>
																			</td>
																		</tr>

																		<tr>
																			<td>
																				<div class="test_result">
																					<a href="test-analytics.html"> <!-- <span>Java Test Part 1</span> -->
																						<div class="condidate_box">
																							<div class="condidate_icon ac_2">
																								<p>
																									<b>AC</b>
																								</p>
																							</div>
																							<div class="condidate_name">
																								<h4>Aarti Chaudhary</h4>
																								<p>aarti_choudhary@abc.com</p>
																							</div>
																						</div>
																					</a>
																				</div>
																			</td>
																		</tr>

																		<tr>
																			<td>
																				<div class="test_result">
																					<a href="test-analytics.html"> <!-- <span>Java Test Part 1</span> -->
																						<div class="condidate_box">
																							<div class="condidate_icon ac_3">
																								<p>
																									<b>AC</b>
																								</p>
																							</div>
																							<div class="condidate_name">
																								<h4>Aarti Chaudhary</h4>
																								<p>aarti_choudhary@abc.com</p>
																							</div>
																						</div>
																					</a>
																				</div>
																			</td>
																		</tr>

																		<tr>
																			<td>
																				<div class="test_result">
																					<a href="test-analytics.html"> <!-- <span>Java Test Part 1</span> -->
																						<div class="condidate_box">
																							<div class="condidate_icon ac_4">
																								<p>
																									<b>AC</b>
																								</p>
																							</div>
																							<div class="condidate_name">
																								<h4>Aarti Chaudhary</h4>
																								<p>aarti_choudhary@abc.com</p>
																							</div>
																						</div>
																					</a>
																				</div>
																			</td>
																		</tr>

																		<tr>
																			<td>
																				<div class="test_result">
																					<a href="test-analytics.html"> <!-- <span>Java Test Part 1</span> -->
																						<div class="condidate_box">
																							<div class="condidate_icon ac_5">
																								<p>
																									<b>AC</b>
																								</p>
																							</div>
																							<div class="condidate_name">
																								<h4>Aarti Chaudhary</h4>
																								<p>aarti_choudhary@abc.com</p>
																							</div>
																						</div>
																					</a>
																				</div>
																			</td>
																		</tr>

																		<tr>
																			<td>
																				<div class="test_result">
																					<a href="test-analytics.html"> <!-- <span>Java Test Part 1</span> -->
																						<div class="condidate_box">
																							<div class="condidate_icon ac_6">
																								<p>
																									<b>AC</b>
																								</p>
																							</div>
																							<div class="condidate_name">
																								<h4>Aarti Chaudhary</h4>
																								<p>aarti_choudhary@abc.com</p>
																							</div>
																						</div>
																					</a>
																				</div>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="col-md-12 col-lg-12 mt-3">
												<div class="question_save text-center mt-4">
													<button class="cancle_btn">Cancel</button>
													<button class="save_btn">Send Invitation</button>
												</div>
											</div>

										</div>
									</div>
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
</body>

</html>