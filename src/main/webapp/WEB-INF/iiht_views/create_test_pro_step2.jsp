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
<style type="text/css">
</style>

<c:if test="${msgtype != null}">
	<script>
		var notification = 'Information';
		var mtp = "${msgtype}";
		var msg = "${message}";
		console.log(mtp + ' ' + msg);
		$(function() {
			new PNotify({
				title : notification,
				text : msg,
				type : mtp,
				styling : 'bootstrap3',
				hide : true
			});
		});
	</script>
</c:if>

<script>
	var point = false;
	var count = 0;
	function check(e, value) {

		//Check Charater
		debugger;
		if (count == 3)
			return false;
		var unicode = e.charCode ? e.charCode : e.keyCode;

		if (unicode == 46 && point == true)
			return false;
		if (unicode == 46 && point == false) {
			point = true;
		}
		if (unicode != 8)
			if ((unicode<48||unicode>57) && unicode != 46)
				return false;
		if (point == true)
			count++;
	}
	function checkLength() {
		var fieldVal = document.getElementById('txtF').value;
		//Suppose u want 3 number of character
		if (fieldVal <= 100) {
			return true;
		} else {
			var str = document.getElementById('txtF').value;
			str = str.substring(0, str.length - 1);
			document.getElementById('txtF').value = str;
		}
	}
</script>
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
								<button class="nav-link" >
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
								<button class="nav-link active"  >
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



							<div class="tab-pane fade active show" id="pills-Add_Questions" role="tabpanel"
								aria-labelledby="pills-Add_Questions-tab">
								<div class="Set_your_Test_box">
									<div class="present_box">
										<div class="pre_left">
											<p>Percent of Qs to be asked</p>
											<!-- 											<div class="precentage"> -->
											<!-- 												<p> -->

											<input type="number" onKeyPress="return check(event,value)" onInput="checkLength()" id="txtF"
												class="precentage" placeholder="Enter percentage" value="${sectionDto.percentQuestionsAsked}" />
											<!-- 													<b>80%</b> -->
											<!-- 												</p> -->
											<!-- 											</div> -->
										</div>
										<div class="pre_right">


											<button onclick="location.href = 'addteststep2Pro';" id="showAllQs" class="Categories">Show All
												Parent Categories</button>
											<button onclick="saveSection()" class="Categories">Save Section</button>
											<button onclick="showSelected()" id="showSelected" class="Categories Selected">Show Selected</button>
											<button onclick="location.href = 'removeAllQuestionsPro';" class="Categories Clear">Clear All</button>



											<!-- 											<button class="Categories">Show All Parent Categories</button> -->
											<!-- 											<button class="Categories">Save Section</button> -->
											<!-- 											<button class="Categories Selected">Show Selected</button> -->
											<!-- 											<button class="Categories Clear">Clear All</button> -->
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
																<!-- 																	<div class="form-check"> -->
																<!-- 																		<input class="form-check-input" type="checkbox" id="check1" name="option1" value="something"> -->
																<!-- 																	</div> -->

																<th>Question</th>
																<th>Category</th>
																<th>Level</th>
<!-- 																<th>Weight</th> -->
																<th>Select</th>
<!-- 																<th>Select All Qs in Category</th> -->
<!-- 																<th>Remove All Qs in Category</th> -->
															</tr>
														</thead>
														<tbody>


															<%
															int count = 0;
															%>
															<c:forEach items="${qs}" var="ques">
																<tr id="${ques.id}" bgcolor="${ques.selected? '#33FFF9':'transparent'}">


																	<td><c:out value="${ques.questionText}"></c:out></td>

																	<td>${ques.testCategory}</td>


<%-- 																	<td><c:out value="${ques.difficultyLevel.level}"></c:out></td> --%>

																	<!-- <td><input type="text" placeholder="Marks, if Correct" value="1  "  disabled  style="width:100%;"></td> -->
<%-- 																	<td><c:out value="${ques.questionWeight == null?'1':ques.questionWeight}"></c:out></td> --%>


																	<td id="${ques.id}-add" style="${ques.selected? 'display: none;':''}"><a
																		href="javascript:addQ('${ques.id}', 'Core Java');">Click to Add</a></td>
																	<td id="${ques.id}-remove" style="${ques.selected? '':'display: none;'}"><a
																		href="javascript:removeQ('${ques.id}', 'Core Java');">Click to Remove</a></td>
<%-- 																	<td>${ques.testCategoryToAddQs}</td> --%>
<%-- 																	<td>${ques.testCategoryToRemoveQs}</td> --%>
																</tr>
																<%
																count++;
																%>
															</c:forEach>


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
														<a href="addNewSectionPro"> <img src="quaintpro/admin/assets/img/new_Add.png">
														</a>
													</div>
												</div>


												<div class="question_bank_sec mb-4">
													<div class="search">
														<div class="wrap">
															<div class="search">
																<input type="text" id="sectionTopic" placeholder="Enter Section Name" class="searchTerm"
																	value="${sectionDto.sectionName}" />
																<!--                                                                <input type="text" class="searchTerm" placeholder="Search test here"> -->
																<button type="submit" class="searchButton">
																	<i class="fa fa-search" aria-hidden="true"></i>
																</button>
															</div>
														</div>
													</div>
												</div>

												<%-- 												<c:forEach items="${test.sectionDtos}" var="section"> --%>
												<%-- 													<div style="${section.style}" onclick="highlight('${section.sectionName}');return false"> --%>
												<%-- 														<h4 style="font-size: 14; font-weight: bold;">${section.sectionName}-</h4> --%>
												<%-- 														<h4 id="no-${section.sectionName}" style="font-size: 14; font-weight: bold;">${section.noOfQuestions} --%>
												<!-- 														</h4> -->
												<%-- 														<a href="javascript:removeSection('${section.sectionName}');"> x </a> --%>
												<!-- 													</div> -->

												<%-- 												</c:forEach> --%>



												<c:forEach items="${test.sectionDtos}" var="section">
													<div class="question_type_sec mb-4" style="${section.style}"
														>
														<div class="flex">
															<div class="question_type_heading" onclick="highlight('${section.sectionName}');return false">
																<p>
																	<b>${section.sectionName} - </b>
																</p>
																<p>${section.noOfQuestions} questions</p>
															</div>
															<div class="delete">
																<a href="javascript:removeSection('${section.sectionName}');"> <img
																	src="quaintpro/admin/assets/img/delete.png" >
																</a>
															</div>
														</div>
													</div>
												</c:forEach>



											</div>
										</div>
										<div class="col-md-12 col-lg-12 mt-4">
											<div class="question_save text-center">
												<button class="cancle_btn" onclick="location.href = 'protestbank';">Cancel</button>
												<button class="save_btn" onclick="location.href = 'gobackStep1TestPro';">Back</button>
												<button class="save_btn" onclick="location.href = 'addteststep3Pro';">Next</button>
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
		function saveSection() {
			var name = document.getElementById('sectionTopic').value;
			var txtFValue = document.getElementById('txtF').value;
			if (name.trim().length == 0) {
				notify('Information',
						'Please enter a meaningful name for your section before saving. ');

			} else {
				window.location = 'saveSectionPro?sectionTopic=' + name
						+ '&percentage=' + txtFValue;

			}

		}

		function addQ(qid, sectionName) {

			//window.location = "addQuestionToSection?sectionName="+sectionName+"&questionId="+qid;
			var url = "addQuestionToSectionAjaxPro?sectionName=" + sectionName
					+ "&questionId=" + qid;
			console.log('here url ' + url);
			$.ajax({
				url : url,
				success : function(data) {
					console.log("SUCCESS: ", data);
					var tr = document.getElementById(qid);
					var tds = tr.getElementsByTagName("td");
					var tdadd = document.getElementById(qid + "-add");
					var tdremove = document.getElementById(qid + "-remove");
					console.log(tr);
					console.log(tds);
					console.log(tdadd);
					tr.style.backgroundColor = '#33FFF9';
					tdadd.style.display = "none";
					tdremove.style.display = "";

					//document.getElementById("no-"+sectionName).innerHTML = data;

				},
				error : function(e) {
					alert();
					console.log("ERROR: ", e);

				}
			});

		}

		function removeQ(qid, sectionName) {
			//window.location = "removeQuestionToSection?sectionName="+sectionName+"&questionId="+qid;
			var url = "removeQuestionToSectionAjaxPro?sectionName="
					+ sectionName + "&questionId=" + qid;
			console.log('here url ' + url);
			$.ajax({
				url : url,
				success : function(data) {
					console.log("SUCCESS: ", data);
					var tr = document.getElementById(qid);
					var tds = tr.getElementsByTagName("td");
					var tdadd = document.getElementById(qid + "-add");
					var tdremove = document.getElementById(qid + "-remove");
					console.log(tr);
					console.log(tds);
					console.log(tdadd);
					tr.style.backgroundColor = 'transparent';
					tdadd.style.display = "";
					tdremove.style.display = "none";

					//document.getElementById("no-"+sectionName).innerHTML = data;

				},
				error : function(e) {
					console.log("ERROR: ", e);

				}
			});
		}

		function showSelected() {
			//$.ajax({url: "showSectionsQuestions", success: function(result){
			//		notify("Success", "All Questions Selected so far..");
			//	    }});
			window.location = "showSectionsQuestionsPro";
		}

		function highlight(sectionName) {
			window.location = "goToSectionPro?sectionName=" + sectionName;
		}

		function removeSection(sectionName) {
		 	 Swal.fire({
				  title: 'Confirmation Needed',
				  text: "Are you sure you want to delete the section"+ sectionName,
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Yes, delete it!'
				}).then((result) => {
				  if (result.value) {
					  window.location = "removeSectionPro?sectionName="	+ sectionName;
				  }
				})
	}

		function notify(messageType, message) {
			var notification = 'Information';
			$(function() {
				new PNotify({
					title : notification,
					text : message,
					type : messageType,
					styling : 'bootstrap3',
					hide : true
				});
			});
		}

		$('#search').on('click', function() {
			var text = document.getElementById("searchText").value;
			if (text.length != 0) {
				window.location = "searchQs?searchText=" + text;
			}
		});

		$(function() {
			$(".addimage").on('click', function(e) {
				e.preventDefault();
				$("#addimage").trigger('click');
			});
			$(".addaudio").on('click', function(e) {
				e.preventDefault();
				$("#addaudio").trigger('click');
			});
			$(".addvideo").on('click', function(e) {
				e.preventDefault();
				$("#addvideo").trigger('click');
			});
		});

		$('#addimage').change(function() {
			var file = $('#addimage')[0].files[0].name;
			$('.queimage').text('Image: ' + file);
		});
		$('#addaudio').change(function() {
			var file = $('#addaudio')[0].files[0].name;
			$('.queaudio').text('Audio: ' + file);
		});
		$('#addvideo').change(function() {
			var file = $('#addvideo')[0].files[0].name;
			$('.quevideo').text('Video: ' + file);
		});

		function notify(messageType, message) {
			var notification = 'Information';
			$(function() {
				new PNotify({
					title : notification,
					text : message,
					type : messageType,
					styling : 'bootstrap3',
					hide : true
				});
			});
		}
	</script>

</body>

</html>