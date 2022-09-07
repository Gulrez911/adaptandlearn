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
<link rel="shortcut icon" href="assets/img/favicon.png" type="image/x-icon">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="quaintpro/admin/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="quaintpro/admin/assets/css/style.css" rel="stylesheet">


<link href="css/responsive.css" rel="stylesheet" type="text/css">
<!-- 	<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css"> -->

<link href="css/circle.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/FileSaver.js"></script>
<script type="text/javascript" src="scripts/dom-to-image.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- 	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script> -->
<!-- 	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
</head>

<body>

	<%
	com.assessment.data.Test test = (com.assessment.data.Test) request.getSession().getAttribute("test");
	String testName = test.getTestName();
	System.out.println("leoforce " + test.getLeoForce());
	request.setAttribute("test", test);
	%>

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
							<li class="nav-item"><a class="nav-link " href="question-bank.html"> <img
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




	<section class="qb_section create_question_sec result_sec" id="dom">
		<div class="container">
			<div class="row">
				<div class="col-md-12 mb-4">
					<div class="question_bank_heading">
						<div class="result_heading">
							<%
							Integer noOfAttempts = (Integer) request.getSession().getAttribute("noOfAttempts");
							String att = "";
							if (noOfAttempts == null) {
								noOfAttempts = 0;

							} else {
								noOfAttempts = noOfAttempts + 1;
							}

							if (noOfAttempts == 1) {
								// 									att =  "attempt";
								// 									att = ${completionUTF.attempt};

							} else {
								// 								att =  "attempts";
								// 								att = ${completionUTF.attempt};
							}
							%>

							<h3>Hey ${studentTestForm.firstName} ${studentTestForm.lastName} - ${completionUTF.completeTest },</h3>
							<p>
								${completionUTF.completeTestIn }
								<%=noOfAttempts%>
								${completionUTF.attempt} You completed the evaluation in 2 attempt
							</p>
						</div>
						<div class="question_btn">
							<button class="custom_btn" onclick="download()">
								<img src="quaintpro/admin/assets/img/Download_Report.svg"> Download Report
							</button>
						</div>
					</div>
				</div>
			</div>

			<div class="result_box_Sec">
				<div class="box_heading">
					<span style="text-align: center;" id="currtime"> </span>
					<!--                     <h4>Configuration layout and loft-configuration layout-Conic lofting</h4> -->
					<div class="result_data_Sec">
						<div class="data">
							<div class="data_list">
								<div class="data_icon">
									<img src="quaintpro/admin/assets/img/question_list.svg">
								</div>
								<div class="data_text">
									<p>${completionUTF.totalQuestions }</p>
									<h3>${TOTAL_QUESTIONS} Question</h3>
									<!--                                     <p>Total no. of Question</p> -->
									<!--                                     <h3>20 Question</h3> -->
								</div>
							</div>
							<div class="data_list">
								<div class="data_icon">
									<img src="quaintpro/admin/assets/img/time.svg">
								</div>
								<div class="data_text">
									<p>Time Spend</p>
									<h3>00:02:45</h3>
								</div>
							</div>
							<div class="data_list">
								<div class="data_icon">
									<img src="quaintpro/admin/assets/img/star.svg">
								</div>
								<div class="data_text">
									<p>Status</p>
									<h3>${STATUS}</h3>
								</div>
							</div>
							<div class="data_list">
								<div class="data_icon">
									<img src="quaintpro/admin/assets/img/star.svg">
								</div>
								<div class="data_text">
									<p>Your Score</p>
									<h3>${RESULT_PERCENTAGE} %</h3>
									<!--                                     <p>Your Score</p> -->
									<!--                                     <h3>35%</h3> -->
								</div>
							</div>
						</div>
						<div class="try_btn">
							<button class="try">Try Again</button>
						</div>
					</div>
					<div class="graph_box">
						<div id="Result2"></div>
						<div class="ques_score">
							<h3>${TOTAL_QUESTIONS}
								<br>Questions
							</h3>
						</div>
					</div>
					<div class="note">
						<p>Note: Your results have been recorded. In a short time from now, we will share your User credentials, if
							not shared earlier, that will allow you login and see your past scores and more.</p>
					</div>
				</div>
			</div>

			<c:if test="${justification}">
				<div class="answer_box_sec">
					<div class="answer_heading">
						<h3>Answer Summary</h3>
						<p>Section-Configuration layout and loft</p>
						<c:forEach var="section" items="${sectionInstanceDtos}">

							<%
							int count = 1;
							%>
							<c:forEach var="ques" varStatus="status" items="${section.questionInstanceDtos}">

								<div class="question_box mb-4">
									<div class="question_flex">
										<div class="question_heading">
											<h3>Question <%= count %>.</h3>
											<p>
												${section.section.sectionName} -
												<%=count%>. ${ques.questionMapperInstance.questionMapper.question.questionText}
											</p>
										</div>
										<div class="question_btn">
											<button class="incorrect" style="${ques.questionMapperInstance.correct == true? " background: #92F390;":"background: #ff8d8d;"}">${ques.questionMapperInstance.correct == true? "Correct":"Incorrect"}</button>
										</div>
									</div>
									<div class="your_ans_box">
										<div class="answer_option"  style="${ques.questionMapperInstance.correct == true? " background: #92F390;":"background: #ff8d8d;"}">
											<p>Your Answer</p>
											<h4>${ques.questionMapperInstance.userChoices}</h4>
										</div>
										<div class="answer_option correct_ans">
											<p>Correct Answer</p>
											<h4>${ques.questionMapperInstance.questionMapper.question.rightChoices}</h4>
										</div>
									</div>
									<%--                         <div class="answer_justification" style="${ques.questionMapperInstance.correct == true? "color:green":"color:red"}"> --%>
									<!--                             <h3>Answer Justification</h3> -->
									<%--                                 <p>${ques.questionMapperInstance.questionMapper.question.justification == null ?"NA":ques.questionMapperInstance.questionMapper.question.justification}</p> --%>
									<!--                         </div> -->
								</div>

							</c:forEach>
						</c:forEach>
					</div>
				</div>
			</c:if>

		</div>
	</section>





	<script src="quaintpro/admin/assets/js/jquery.slim.min.js"></script>
	<script src="quaintpro/admin/assets/js/bootstrap.bundle.min.js"></script>
	<script src="quaintpro/admin/assets/js/jquery-slider-min.js"></script>
	<script src="quaintpro/admin/assets/js/apexcharts.js"></script>
	<script src="quaintpro/admin/assets/js/fontawesome.min.js"></script>
	<script src="quaintpro/admin/assets/js/custom.js"></script>

	<script>
		
		var d = new Date();
	//var d = dt.toString('dd-MMM-yyyy hh:mm');
	var date_format_str =  (d.getDate().toString().length==2?d.getDate().toString():"0"+d.getDate().toString())+"-"+((d.getMonth()+1).toString().length==2?(d.getMonth()+1).toString():"0"+(d.getMonth()+1).toString())+"-"+d.getFullYear().toString()+" "+(d.getHours().toString().length==2?d.getHours().toString():"0"+d.getHours().toString())+":"+((parseInt(d.getMinutes()/5)*5).toString().length==2?(parseInt(d.getMinutes()/5)*5).toString():"0"+(parseInt(d.getMinutes()/5)*5).toString());
	
	
	
	
	var disp = '<h3>Test  - <%=testName%> ${completionUTF.completeByYouAt} ';
	disp += date_format_str +'</h3>';
	document.getElementById("currtime").innerHTML =disp ;	
	
		function download(){
			domtoimage.toBlob(document.getElementById('dom'))
			.then(function (blob) {
				//var FileSaver = require('file-saver');
				//window.saveAs(blob, 'image.png');
				console.log('blob is '+blob);
				saveAs(blob, "image.png");
			});
		}
		
		$("a").each(function(i, el) {
		  const $el = $(el);
		  $el.click(function(e) {
			const href = $el.attr("href");
			// Find an element that has href as an ID
			// Scroll to that element
			$("html, body").scrollTop($("#" + href).offset().top);
			// Prevent default action, i.e., don't follow the hyperlink
			// to a new location
			e.preventDefault();
		  });
		});
		
		
		///chart
		${TOTAL_MARKS}/${TOTAL_QUESTIONS}
		
		var res =  ${TOTAL_MARKS}+", "+${TOTAL_QUESTIONS};
		console.log(res);
		
		var options = {
// 				  series: [44, 55, 41, 17, 15],
				  series: [${TOTAL_QUESTIONS}, ${TOTAL_MARKS}],
// 				  series: [res],
				  chart: {
				  type: 'donut',
				},
				responsive: [{
				  breakpoint: 480,
				  options: {
				    chart: {
				      width: 200
				    },
				    legend: {
				      position: 'bottom'
				    }
				  }
				}]
				};

				var chart = new ApexCharts(document.querySelector("#Result2"), options);
				chart.render();
				
		</script>
</body>

</html>