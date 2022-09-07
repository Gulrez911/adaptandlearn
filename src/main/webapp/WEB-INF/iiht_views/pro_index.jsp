<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>

<html lang="en">
<head>
<title>Quantpro</title>
<link rel="shortcut icon" href="quaintpro/admin/assets/img/favicon.png" type="image/x-icon">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="quaintpro/admin/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="quaintpro/admin/assets/css/style.css" rel="stylesheet">

<style type="text/css">

section .recent_test.results .condidate_table .table_scroll {
  overflow: inherit;
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
										<!--                                         <a class="dropdown-item" href="prologin">Logout</a> -->
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
							<li class="nav-item"><a class="nav-link" href="listTestLinksPro"> <img
									src="quaintpro/admin/assets/img/List_Test_Links.png" class="disactive"> List Test Links
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


	<section class="dashboard_sec">
		<div class="container">

			<div class="dashboard_heading">
				<h3>Dashboard</h3>
				<div class="create_test">
					<button class="test" onclick="location.href = 'pro_test_add';">Create New Test</button>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12 col-lg-8 mb-4">
					<div class="recent_test">
						<div class="recent_test_heading">
							<p>
								<b>Recent Tests</b>
							</p>
							<p>
								<a href="javascript:void(0);">View All Tests</a>
							</p>
						</div>

						<!--                        open  -->

						<div class="recent_test_body">


							<c:forEach items="${tests}" var="test" varStatus="loop">

								<div class="recent_test_card">
									<div class="top">
										<h3>${test.testName}</h3>
										<button class="open">Open</button>
									</div>
									<ul>
										<li><img src="quaintpro/admin/assets/img/probleam.png"> 20 Problems</li>
										<li><img src="quaintpro/admin/assets/img/hr.png"> ${test.testTimeInMinutes} Duration</li>
										<li><img src="quaintpro/admin/assets/img/condi.png"> 20 Problems</li>
										<li><img src="quaintpro/admin/assets/img/point.png"> 20 Problems</li>
										<li><img src="quaintpro/admin/assets/img/user1.png"> 20 Problems</li>
									</ul>
								</div>



							</c:forEach>

							<!--                             <div class="recent_test_card"> -->
							<!--                                 <div class="top"> -->
							<!--                                     <h3>New test lorem ipsum dolor</h3> -->
							<!--                                     <button class="open">Open</button> -->
							<!--                                 </div> -->
							<!--                                 <ul> -->
							<!--                                     <li> <img src="quaintpro/admin/assets/img/probleam.png"> 20 Problems</li> -->
							<!--                                     <li> <img src="quaintpro/admin/assets/img/hr.png"> 20 Problems</li> -->
							<!--                                     <li> <img src="quaintpro/admin/assets/img/condi.png"> 20 Problems</li> -->
							<!--                                     <li> <img src="quaintpro/admin/assets/img/point.png"> 20 Problems</li> -->
							<!--                                     <li> <img src="quaintpro/admin/assets/img/user1.png"> 20 Problems</li> -->
							<!--                                 </ul> -->
							<!--                             </div> -->


						</div>

						<!--                         close  -->

					</div>
				</div>
				<div class="col-md-12 col-lg-4 mb-4">
					<div class="recent_test results">
						<div class="condidate_table">
							<div class="table_scroll">
								<table class="table table-borderless table-sm">
									<thead>
										<tr>
											<th>Results</th>
											<th>%Score</th>
										</tr>
									</thead>
									<tbody>
											<c:forEach items="${list}" var="user" varStatus="loop">
										<tr>
											<td>
												<div class="test_result">
													<a href="getResult?email=${user.user}&testName=${user.testName}"><span>${user.testName}</span>
														<div class="condidate_box">
															<div class="condidate_icon">
																<p>
																	<b>AC</b>
																</p>
															</div>
															<div class="condidate_name">
<!-- 																<h4>Aarti Chaudhary</h4> -->
																<h4>${user.firstName} ${user.lastName}</h4>
<!-- 																<p>aarti_choudhary@abc.com</p> -->
																<p>${user.user}</p>
															</div>
														</div> </a>
												</div>
											</td>
											<td class="text-center">
												<p class="accept fail">
<!-- 													<a href="javascript:void(0);">10% <span>Fail</span></a> -->
													<a href="javascript:void(0);">${user.percentageMarksRecieved} % <span>Fail</span></a>
												</p>
											</td>
										</tr>
										</c:forEach>
<!-- 										<tr> -->
<!-- 											<td> -->
<!-- 												<div class="test_result"> -->
<!-- 													<a href="test-analytics.html"><span>Java Test Part 1</span> -->
<!-- 														<div class="condidate_box"> -->
<!-- 															<div class="condidate_icon"> -->
<!-- 																<p> -->
<!-- 																	<b>AC</b> -->
<!-- 																</p> -->
<!-- 															</div> -->
<!-- 															<div class="condidate_name"> -->
<!-- 																<h4>Aarti Chaudhary</h4> -->
<!-- 																<p>aarti_choudhary@abc.com</p> -->
<!-- 															</div> -->
<!-- 														</div> </a> -->
<!-- 												</div> -->
<!-- 											</td> -->
<!-- 											<td class="text-center"> -->
<!-- 												<p class="accept fail"> -->
<!-- 													<a href="javascript:void(0);">10% <span>Fail</span></a> -->
<!-- 												</p> -->
<!-- 											</td> -->
<!-- 										</tr> -->

										 

									 

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12 col-lg-12">
					<div class="recent_test">
						<div class="recent_test_heading">
							<p>
								<b>Activity Feed</b>
							</p>
						</div>
						<div class="recent_test_body Activity_Feed pb-0">

							<div class="feed_box">
								<div class="condidate_box">
									<div class="condidate_icon bg-white">
										<img src="quaintpro/admin/assets/img/profile_circle.png">
									</div>
									<div class="condidate_name">
										<p>You added 2 problems to the new test lorem ipsum</p>
									</div>
								</div>

								<div class="time">
									<p>2 hours ago</p>
								</div>
							</div>

							<div class="feed_box">
								<div class="condidate_box">
									<div class="condidate_icon">
										<p>
											<b>AC</b>
										</p>
									</div>
									<div class="condidate_name">
										<p>You added 2 problems to the new test lorem ipsum</p>
									</div>
								</div>

								<div class="time">
									<p>4 hours ago</p>
								</div>
							</div>

							<div class="feed_box">
								<div class="condidate_box">
									<div class="condidate_icon bg-white">
										<img src="quaintpro/admin/assets/img/profile_circle.png">
									</div>
									<div class="condidate_name">
										<p>You added 2 problems to the new test lorem ipsum</p>
									</div>
								</div>

								<div class="time">
									<p>4 hours ago</p>
								</div>
							</div>

							<div class="feed_box">
								<div class="condidate_box">
									<div class="condidate_icon ac">
										<p>
											<b>AC</b>
										</p>
									</div>
									<div class="condidate_name">
										<p>You added 2 problems to the new test lorem ipsum</p>
									</div>
								</div>

								<div class="time">
									<p>4 hours ago</p>
								</div>
							</div>

							<div class="feed_box border-0">
								<div class="condidate_box">
									<div class="condidate_icon bg-white">
										<img src="quaintpro/admin/assets/img/profile_circle.png">
									</div>
									<div class="condidate_name">
										<p>You added 2 problems to the new test lorem ipsum</p>
									</div>
								</div>

								<div class="time">
									<p>4 hours ago</p>
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