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
	justify-content: space-around;
	align-items: center;
	/*     margin-left: 25%; */
	width: auto !important;
	margin: 0px auto 30px 25% !important;
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
							<li class="nav-item"><a class="nav-link " href="protestbank"> <img
									src="quaintpro/admin/assets/img/test_active.png" class="active"> <img
									src="quaintpro/admin/assets/img/test.png" class="disactive">Tests
							</a></li>
							<li class="nav-item"><a class="nav-link active" href="listTestLinksPro"> <!--                                         <img src="quaintpro/admin/assets/img/List_Test_Links.png" class="active"> -->
									<img src="quaintpro/admin/assets/img/List_Test_Links_active.png" class="active">List Test Links
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
				<div class="col-md-12">
					<div class="test_tab_section question_bank_sec">
						<div class="tab-content tab_con_box question_table" id="pills-tabContent">
							<div class="Set_your_Test_box">
								<div class="present_box">
									<div class="pre_left">
										<div class="question_bank_heading">
											<h3>Time Bound Public Test Links</h3>
										</div>
									</div>
									<div class="pre_right">
										<button class="Categories" onclick="location.href = 'addTestLinkPro';">Create New
											Public Test Link</button>
									</div>
									
										<div class="pagination2">

							<c:if test="${showPreviousPage}">
								<button class="btn btn2" onclick="location.href = '${callingMethod}?page=${previousPage}${queryParam}';">
									<i class="fa-solid fa-chevron-left"></i>
								</button>
								<%--                                     	<a href="${callingMethod}?page=${previousPage}${queryParam}"><i class="fa fa-arrow-left"></i></a> --%>
							</c:if>

							<c:if test="${showNextPage}">
								<button class="btn btn2" onclick="location.href = '${callingMethod}?page=${nextPage}${queryParam}';">
									<i class="fa-solid fa-chevron-right"></i>
								</button>
								<%-- 	                                    <a href="${callingMethod}?page=${nextPage}${queryParam}"><i class="fa fa-arrow-right"></i></a> --%>
							</c:if>

							 

						</div>
									
								</div>
								<div class="row">
									<div class="col-md-12 col-lg-12">
										<div class="question_bank_sec Add_Sections_box">
											<div class="question_sec_table shadow-none">
												<table class="table table-borderless text-center">
													<thead>
														<tr>
															<th>No</th>
															<th>Test Name</th>
															<th>Start Date</th>
															<th>End Date</th>
															<th>Time bound Test URL</th>
															<th>Modify Link</th>
															<th>Delete Link</th>
														</tr>
													</thead>
													<tbody>


														<c:forEach items="${links}" var="link" varStatus="loop">

															<tr>

																<td>${loop.count}</td>
																<td><c:out value="${link.testName}"></c:out></td>
																<td>${link.stDate}</td>
																<td>${link.edDate}</td>
																<td class="text-center"><a href="${link.url}">${link.url}</a></td>
																<td><a href="addTestLinkPro?linkId=${link.id}">Click </a></td>

																<td><a href="javascript:confirmDelete('${link.id}')">Click </a></td>
															</tr>
														</c:forEach>







														<tr>
															<td>1</td>
															<td>Java Test 1.0</td>
															<td>30/03/2021 <br> 12:00 AM
															</td>
															<td>31/03/2021 <br> 12:00 AM
															</td>
															<td class="text-center"><a hre="javascript:void(0)">
																	https://adaptandlearn.online/mcqPublic?&testId=24&companyId=AL&startTime=MTYxNzA0MjYwMDAwMA%3D%3D&endTime=MTYxNzEyOTAwMDAwMA%3D%3D&lang=arabic
															</a></td>
															<td class="text-center"><a hre="javascript:void(0)"> <img
																	src="quaintpro/admin/assets/img/edit_new.png" class="disactive">
															</a></td>
															<td class="text-center"><a hre="javascript:void(0)"> <img
																	src="quaintpro/admin/assets/img/delete_new.png" class="disactive">
															</a></td>
														</tr>






													</tbody>
												</table>
											</div>
										</div>
									</div>

									<div class="col-md-12 col-lg-12 ">
										<div class="pagination_box">
											<div class="pagination mt-3">
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