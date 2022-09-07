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
							<li class="nav-item"><a class="nav-link active" href="proquestionbank"> <img
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
			<div class="question_bank">
				<div class="dashboard_heading">
					<h3>Question Bank</h3>
					<div class="create_test">
						<button class="test" onclick="location.href = 'creatQuestion';">Create Question Bank</button>
					</div>
				</div>

				<div class="search">
					<div class="wrap">
						<div class="search">
							<input type="text" class="searchTerm" placeholder="Search test here" id="searchText" value="${param.searchText}">

							<!--                               <input type="text" placeholder="Search a question" name="searchText" id="searchText"> -->
							<!--                                             <i class="fa fa-search" id="search"></i> -->

							<button type="submit" class="searchButton">
								<i class="fa fa-search" id="search"></i>
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
								<b>Filters</b>
							</p>
						</div>
						<div class="recent_test_body filters_box">
							<div class="filter_checkbox">
								<div class="form-group">
									<input type="checkbox" id="test"> <label for="test">Tests Created by me</label>
								</div>
							</div>

							<div class="filter_checkbox">
								<div class="form-group">
									<input type="checkbox" id="test1"> <label for="test1">Archived Test</label>
								</div>
							</div>


							<div class="filter_checkbox">
								<div class="form-group">
									<input type="checkbox" id="test2"> <label for="test2">Lorem ipsum</label>
								</div>
							</div>

							<div class="filter_checkbox mb-0">
								<div class="form-group">
									<input type="checkbox" id="test3"> <label for="test3">Lorem ipsum dolor</label>
								</div>
							</div>

						</div>
					</div>
					<div class="recent_test mb-4">
						<div class="recent_test_heading filters">
							<p>
								<b>Question Type</b>
							</p>
						</div>
						<div class="recent_test_body filters_box">
							<c:forEach items="${list22}" var="list" varStatus="loop">
								<c:forEach items="${listQualifier}" var="qualifier" varStatus="loop">
									<div class="question_filter" onclick="getQualifer('${qualifier}')">
										<a href="javascript:void(0);">
											<div class="ques_type">
												<p>${qualifier}</p>


												<c:choose>
													<c:when test="${list== qualifier}">
														<img src="quaintpro/admin/assets/img/check_box_active.png">
													</c:when>
													<c:otherwise>
														<img src="quaintpro/admin/assets/img/check_box.png">
													</c:otherwise>
												</c:choose>


											</div>
										</a>
									</div>
								</c:forEach>
							</c:forEach>






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
				</div>



				<div class="col-md-12 col-lg-9">
					<div class="recent_test">
						<div class="recent_test_body ques_bank">


							<%--      									<c:forEach  items="${qs}" var="question" varStatus="loop">    --%>
							<!-- 						                      	<tr> -->

							<%-- 										<td>${loop.count}</td>		 --%>


							<%-- 						                      		<td><c:out value="${question.questionText}"></c:out>  </td> --%>

							<%-- 						                      		<td> ${question.category}</td> --%>
							<%-- 						                      		<td><c:out value="${question.difficultyLevel.level}"></c:out>   </td> --%>
							<%-- 						                      		<td><c:out value="${question.updatedDate}"></c:out>   </td> --%>
							<%-- 						                      		<td><a  href="addQuestion?qid=${question.id}">Click </a>   </td> --%>
							<%-- 						                      		<td><a  href="javascript:confirm('${question.id}')">Click </a> </td> --%>
							<!-- 						                      	</tr> -->
							<%-- 						                      	</c:forEach>    --%>


							<c:forEach items="${listQuestions}" var="question" varStatus="loop">
								<div class="recent_test_card">
									<div class="ques_bank_box">
										<div class="question_left">
											<div class="top">
												<h3>
													<c:out value="${question.questionText}"></c:out>
												</h3>
												<button class="open">Open</button>
											</div>
											<ul>
												<li><img src="quaintpro/admin/assets/img/probleam.png"> 20 Problems</li>
												<li><img src="quaintpro/admin/assets/img/hr.png"> 20 Problems</li>
												<li><img src="quaintpro/admin/assets/img/condi.png"> 20 Problems</li>
												<li><img src="quaintpro/admin/assets/img/point.png"> 20 Problems</li>
												<li><img src="quaintpro/admin/assets/img/user1.png"> 20 Problems</li>
											</ul>
										</div>
										<div class="question_right">
											<ul>
												<li><a href="invite-condidate.html"> <img src="quaintpro/admin/assets/img/add_profile.png">
												</a></li>
												<li><a href="#" onclick="return  confirm('${question.id}');"> <!--                                                     <img src="quaintpro/admin/assets/img/delete.png" > -->
														<img src="quaintpro/admin/assets/img/delete_profile.png">
												</a></li>
												<li><a href="creatQuestion?qid=${question.id}"> <img
														src="quaintpro/admin/assets/img/list_profile.png">
												</a></li>
											</ul>
										</div>
									</div>
								</div>
							</c:forEach>


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
    
	function getQualifer(qualifier) {
// 		alert(qualifier);
		  window.location = "proquestionbank?qualifier="+qualifier;
	}
	function confirm(id) {
    
    Swal.fire({
    	  title: 'Are you sure?',
    	  text: "You won't be able to revert this!",
    	  icon: 'warning',
    	  showCancelButton: true,
    	  confirmButtonColor: '#3085d6',
    	  cancelButtonColor: '#d33',
    	  confirmButtonText: 'Yes, delete it!'
    	}).then((result) => {
    	  if (result.isConfirmed) {
    		  window.location = "removeQuestionPro?qid="+id;
    		  
//     	    Swal.fire(
//     	      'Deleted!',
//     	      'Your file has been deleted.',
//     	      'success'
//     	    )
    	    
    	  }
    	})
    
	}
    
// 	function confirm(id) {
//         (new PNotify({
// 		    title: 'Confirmation Needed',
// 		    text: 'Are you sure? Do you really want to delete this Q?',
// 		    icon: 'glyphicon glyphicon-question-sign',
// 		    hide: false,
// 		    confirm: {
// 			confirm: true
// 		    },
// 		    buttons: {
// 			closer: false,
// 			sticker: false
// 		    },
// 		    history: {
// 			history: false
// 		    }
// 		})).get().on('pnotify.confirm', function() {
// 		    window.location = "removeQuestionFromList?qid="+id;
// 		}).on('pnotify.cancel', function() {
		   
// 		});
//      }
	
	
    $('#search').on('click',function(){
	    var text = document.getElementById("searchText").value;
		if(text.length != 0){
		window.location="searchQuestionsPro?searchText="+text;
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