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

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
	integrity="sha512-c42qTSw/wPZ3/5LBzD+Bw5f7bSF2oxou6wEb+I/lqeaKV5FDIfMvvRp772y4jcJLKuGUOpbJMdg/BTl50fJYAw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.1.9/sweetalert2.min.css"
	integrity="sha512-cyIcYOviYhF0bHIhzXWJQ/7xnaBuIIOecYoPZBgJHQKFPo+TOBA+BY1EnTpmM8yKDU4ZdI3UGccNGCEUdfbBqw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.1.9/sweetalert2.all.min.js"
	integrity="sha512-IZ95TbsPTDl3eT5GwqTJH/14xZ2feLEGJRbII6bRKtE/HC6x3N4cHye7yyikadgAsuiddCY2+6gMntpVHL1gHw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
								<button class="nav-link"  >
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
								<button class="nav-link active" >
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
								<button class="nav-link" >
									<div class="flex1">
										<div class="left_box">
											<i class="fa-solid fa-paper-plane"></i><br> Send Invitation
										</div>
									</div>
								</button>
							</li>
						</ul>

						<div class="tab-content tab_con_box" id="pills-tabContent">



							<div class="tab-pane fade active show" id="pills-Add_Candidates" role="tabpanel"
								aria-labelledby="pills-Add_Candidates-tab">
								<div class="Set_your_Test_box">
									<div class="present_box">
										<div class="pre_left">
											<div class="question_bank_sec mb-4">
												<div class="search">
													<div class="wrap">
														<div class="search">
															<input type="text" class="searchTerm" placeholder="Search a User" id="searchText" value="${param.searchText}">
															<button type="submit" class="searchButton">
																<i class="fa fa-search" aria-hidden="true" id="search"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="pre_right">
											<button class="Categories" onclick="location.href = 'showUsersPro';">Show All Users</button>
											<button class="Categories Selected" onclick="location.href = 'showSelectedUsersPro';">Show Selected</button>
											<button class="Categories Clear" onclick="location.href = 'removeAllUsersPro';">Clear All</button>
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
															
															<c:forEach items="${users}" var="us">
																<tr  style="${us.selected? 'background: #47c1e5;border-radius: 16px;':'background: transparent;'}">
																	<td>
																		<div class="Test_Takers">
																			<div class="profile_img">
																				<p>
																					<b>AR</b>
																				</p>
																			</div>
																			<div class="profile_name">
																				<p>
																					<b>${us.firstName} ${us.lastName}</b>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>${us.email}</td>
																	<td>${us.department}</td>
																	<td>${us.groupOfUser}</td>
																	<td style="${us.selected? 'display: none;':''}">
																		<div class="add_btn">
																			<button class="add" onclick="addU('${us.id}')">Add</button>
																		</div>
																	</td>
																	<td style="${us.selected? '':'display: none;'}">
																		<div class="add_btn">
																			<button class="add" onclick=" removeU('${us.id}')">Remove</button>
																		</div>
																	</td>
																</tr>
																</c:forEach>
 

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
												<button class="cancle_btn" onclick="location.href = 'protestbank';">Cancel</button>
												<button class="save_btn" onclick="location.href = 'addteststep2Pro';">Back</button>
												<button class="save_btn" onclick="location.href = 'addteststep4Pro';">Next</button>
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
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	
		<script>
	$('#search').on('click',function(){
	    var text = document.getElementById("searchText").value;
		if(text.length != 0){
		window.location="searchUsersPro?searchText="+text;
		}
	    });
	
	function addU(uid){
	
			    
		window.location = "addUserToTestPro?userId="+uid;
		    
	}

function removeU(uid){
	window.location = "removeUserToTestPro?userId="+uid;
	    
}

function showSelected(){
	
	window.location = "showSelectedUsersPro";
}
	
	</script>
	
</body>

</html>