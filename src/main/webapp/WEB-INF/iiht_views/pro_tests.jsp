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

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
	integrity="sha512-c42qTSw/wPZ3/5LBzD+Bw5f7bSF2oxou6wEb+I/lqeaKV5FDIfMvvRp772y4jcJLKuGUOpbJMdg/BTl50fJYAw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.1.9/sweetalert2.min.css"
	integrity="sha512-cyIcYOviYhF0bHIhzXWJQ/7xnaBuIIOecYoPZBgJHQKFPo+TOBA+BY1EnTpmM8yKDU4ZdI3UGccNGCEUdfbBqw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.1.9/sweetalert2.all.min.js"
	integrity="sha512-IZ95TbsPTDl3eT5GwqTJH/14xZ2feLEGJRbII6bRKtE/HC6x3N4cHye7yyikadgAsuiddCY2+6gMntpVHL1gHw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<style type="text/css">
.pagination2 {
	display: flex;
}

.btn2 {
	background-color: #4782BC;
	border: 1px solid #4782BC;
	color: #ffffff;
	height: 42px;
	width: 45px;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 0 10px;
}

.wrap {
    display: flex;
    justify-content: space-between;
    align-items: center;
    /* margin-left: 25%; */
     width: auto !important; 
    margin: 0px 0 30px 25% !important;
}
section .search .search {
    width: 50%;
    position: relative;
    display: flex;
}

.pageCount{
    line-height: 40px;
	font-size: 18px;
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
							<li class="nav-item"><a class="nav-link" href="proindex"> <img
									src="quaintpro/admin/assets/img/dashboard_active.png" class="active"> <img
									src="quaintpro/admin/assets/img/dashboard.png" class="disactive"> Dashboard
							</a></li>
							<li class="nav-item"><a class="nav-link" href="proquestionbank"> <img
									src="quaintpro/admin/assets/img/question_active.png" class="active"> <img
									src="quaintpro/admin/assets/img/question.png" class="disactive"> Question Bank
							</a></li>
							<li class="nav-item"><a class="nav-link active" href="protestbank"> <img
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
			<div class="question_bank">
				<div class="dashboard_heading">
					<h3>Test</h3>
					<div class="create_test">
						<!--                         <button class="test" onclick="location.href = 'test-details-problems.html';">Create New Test</button> -->
						<button class="test" onclick="location.href = 'pro_test_add';">Create New Test</button>
					</div>
				</div>

				<div class="search">
					<div class="wrap">
						<div class="search">
							<input type="text" class="searchTerm" placeholder="Search test here" id="searchText">
							<button type="submit" class="searchButton" id="search">
								<i class="fa fa-search"></i>
							</button>
						</div>
					
					
									<div class="pagination2">

							<c:if test="${showPreviousPage}">
								<button class="btn btn2" onclick="location.href = '${callingMethod}?page=${previousPage}${queryParam}&qualifier=${qualifier}';">
									<i class="fa-solid fa-chevron-left"></i>
								</button>
								<%--                                     	<a href="${callingMethod}?page=${previousPage}${queryParam}"><i class="fa fa-arrow-left"></i></a> --%>
							</c:if>
   <c:if test="${selectedPage != null &&  selectedPage > 0}">
						  <div class="pageCount">
						    ${selectedPage} / ${totalNumberOfPages}
						   </div>
					    </c:if>
							<c:if test="${showNextPage}">
								<button class="btn btn2" onclick="location.href = '${callingMethod}?page=${nextPage}${queryParam}&qualifier=${qualifier}';">
									<i class="fa-solid fa-chevron-right"></i>
								</button>
								<%-- 	                                    <a href="${callingMethod}?page=${nextPage}${queryParam}"><i class="fa fa-arrow-right"></i></a> --%>
							</c:if>

							 </div>

						</div>
					
					
				</div>
			</div>

			<div class="row">
				<div class="col-md-12 col-lg-3">

					<div class="recent_test mb-4">
						<div class="recent_test_heading filters">
							<p>
								<b>Test Type</b>
							</p>
						</div>
						<div class="recent_test_body filters_box General_Tests">
							<div class="question_filter">
								<a href="javascript:void(0);">
									<div class="ques_type">
										<p>General Tests</p>
										<img src="quaintpro/admin/assets/img/check_box_active.png">
									</div>
								</a>
							</div>

							<div class="question_filter">
								<a href="javascript:void(0);">
									<div class="ques_type">
										<p>Fullstack Tests</p>
										<img src="quaintpro/admin/assets/img/check_box.png">
									</div>
								</a>
							</div>

							<div class="question_filter">
								<a href="javascript:void(0);">
									<div class="ques_type">
										<p>Confidence Based Tests</p>
										<img src="quaintpro/admin/assets/img/check_box.png">
									</div>
								</a>
							</div>

							<div class="question_filter">
								<a href="javascript:void(0);">
									<div class="ques_type">
										<p>Web Proctor Onboarding Tests</p>
										<img src="quaintpro/admin/assets/img/check_box.png">
									</div>
								</a>
							</div>

							<div class="question_filter mb-0">
								<a href="javascript:void(0);">
									<div class="ques_type">
										<p>Recommendation Based Tests</p>
										<img src="quaintpro/admin/assets/img/check_box.png">
									</div>
								</a>
							</div>





						</div>
					</div>

					<div class="recent_test mb-4">
						<div class="recent_test_heading filters">
							<p>
								<b>Difficulty</b>
							</p>
						</div>
						<div class="recent_test_body difficulty_box">
							<div class="diff_range">
								<div class="preview4"></div>
								<div class="text_sec">
									<p>Very Easy</p>
									<p>Easy</p>
									<p>Medium</p>
								</div>
							</div>
							<div class="diff_range mb-0">
								<div class="preview4"></div>
								<div class="text_sec">
									<p>Hard</p>
									<p>Very Hard</p>
								</div>
							</div>
						</div>
					</div>


					<div class="recent_test mb-4">
						<div class="recent_test_heading filters">
							<p>
								<b>Pass Percentage</b>
							</p>
						</div>
						<div class="recent_test_body difficulty_box">
							<div class="diff_range mb-0">
								<div class="preview4"></div>
								<div class="text_sec">
									<p>20</p>
									<p>40</p>
									<p>60</p>
									<p>80</p>
									<p>100</p>
								</div>
							</div>
						</div>
					</div>


					<div class="recent_test mb-4">
						<div class="recent_test_heading filters">
							<p>
								<b>Problem Type</b>
							</p>
						</div>
						<div class="recent_test_body problem_type">
							<ul>
								<li><a href="javascript:void(0);">All</a></li>
								<li><a href="javascript:void(0);">Coding</a></li>
								<li><a href="javascript:void(0);">Lorem</a></li>
								<li><a href="javascript:void(0);">Language-specific</a></li>
								<li><a href="javascript:void(0);">Database</a></li>
								<li><a href="javascript:void(0);">Data Science</a></li>
								<li><a href="javascript:void(0);">Fill Based Evaluation</a></li>
								<li><a href="javascript:void(0);">Fill in the blanks </a></li>
								<li><a href="javascript:void(0);">Full-Stack </a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="col-md-12 col-lg-9">
					<div class="recent_test">
						<div class="recent_test_body ques_bank">




							<%-- 							<c:forEach items="${tests}" var="test"> --%>
							<!-- 								<tr> -->
							<!-- 									<td><input type="checkbox"></td> -->

							<%-- 									<td><c:out value="${test.testName}"></c:out></td> --%>

							<%-- 									<td>${test.category}</td> --%>
							<%-- 									<td>${test.testTimeInMinutes}</td> --%>
							<%-- 									<td>${test.passPercent}</td> --%>

							<%-- 									<td><c:out value="${test.cDate}"></c:out></td> --%>
							<%-- 									<td><c:out value="${test.uDate}"></c:out></td> --%>
							<%-- 									<td><a onClick="confirm(${test.id}); return false;" href="#">Click to Expire</a></td> --%>
							<%-- 									<td><a href="updateTest?testId=${test.id}">Click to Update</a></td> --%>
							<%-- 									<td><a href="javascript:void(0);" class="testname" data-name="${test.testName}" data-toggle="modal" --%>
							<%-- 										onClick="javascript:duplicateOpen('${test.testName}', '${test.companyId}')"><i class="fa fa-copy"></i></a></td> --%>
							<%-- 									<td><a href="javascript:void(0);" class="testname" data-name="${test.testName}" data-toggle="modal" --%>
							<%-- 										onClick="javascript:shareOpen('${test.testName}', '${test.publicUrl}', '${test.id}')"><i --%>
							<!-- 											class="fa fa-share-alt"></i></a></td> -->
							<!-- 								</tr> -->
							<%-- 							</c:forEach> --%>


							<c:forEach items="${tests}" var="test">
							<div class="recent_test_card border-0 pb-0">
								 <div class="ques_bank_box">
									<div class="question_left">
										<div class="top">
											<h3><c:out value="${test.testName}"></c:out></h3>
											<button class="open close">Close</button>
										</div>
										<ul>
											<li><img src="quaintpro/admin/assets/img/probleam.png">  ${test.noOfConfigurableAttempts} Attempts</li>
											<li><img src="quaintpro/admin/assets/img/hr.png"> ${test.testTimeInMinutes}</li>
											<li><img src="quaintpro/admin/assets/img/point.png"> 100 Points</li>
										</ul>
									</div>
									<div class="question_right">
										<ul>
											<li><a href="updateTestPro?testId=${test.id}"> <img src="quaintpro/admin/assets/img/pc.png">
											</a></li>
											<li><a href="javascript:void(0);"> <img src="quaintpro/admin/assets/img/delete_profile.png">
											</a></li>

										</ul>
									</div>
								</div>
								
<%-- 									<td>${test.category}</td> --%>
<%-- 									<td>${test.testTimeInMinutes}</td> --%>
<%-- 									<td>${test.passPercent}</td> --%>

<%-- 									<td><c:out value="${test.cDate}"></c:out></td> --%>
<%-- 									<td><c:out value="${test.uDate}"></c:out></td> --%>
<%-- 									<td><a onClick="confirm(${test.id}); return false;" href="#">Click to Expire</a></td> --%>
<%-- 									<td><a href="updateTest?testId=${test.id}">Click to Update</a></td> --%>
<%-- 									<td><a href="javascript:void(0);" class="testname" data-name="${test.testName}" data-toggle="modal" --%>
<%-- 										onClick="javascript:duplicateOpen('${test.testName}', '${test.companyId}')"><i class="fa fa-copy"></i></a></td> --%>
<%-- 									<td><a href="javascript:void(0);" class="testname" data-name="${test.testName}" data-toggle="modal" --%>
<%-- 										onClick="javascript:shareOpen('${test.testName}', '${test.publicUrl}', '${test.id}')"><i --%>
<!-- 											class="fa fa-share-alt"></i></a></td> -->
<!-- 								</tr> -->
								</div>
							</c:forEach>



							<div class="recent_test_card border-0 pb-0">
								<div class="ques_bank_box">
									<div class="question_left">
										<div class="top">
											<h3>New test lorem ipsum dolor</h3>
											<button class="open close">Close</button>
										</div>
										<ul>
											<li><img src="quaintpro/admin/assets/img/probleam.png"> 20 Attempts</li>
											<li><img src="quaintpro/admin/assets/img/hr.png"> 1 hour 30 mins</li>
											<li><img src="quaintpro/admin/assets/img/point.png"> 100 Points</li>
										</ul>
									</div>
									<div class="question_right">
										<ul>
											<li><a href="javascript:void(0);"> <img src="quaintpro/admin/assets/img/pc.png">
											</a></li>
											<li><a href="javascript:void(0);"> <img src="quaintpro/admin/assets/img/delete_profile.png">
											</a></li>

										</ul>
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

<script type="text/javascript">
$('#search').on('click',function(){
    var text = document.getElementById("searchText").value;
	if(text.length != 0){
	window.location="searchTestsPro?searchText="+text;
	}
    });
    
</script>
	<c:if test="${msgtype != null}">
		<script>
			var notification = 'Information';
			$(function() {
				Swal.fire('${msgtype}', '${message}', '${icon}')
			});
		</script>
	</c:if>


</body>
</html>