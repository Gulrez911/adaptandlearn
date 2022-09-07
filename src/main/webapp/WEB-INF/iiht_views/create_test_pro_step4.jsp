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

<!-- <link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">   -->
<link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">  


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
								<button class="nav-link">
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
								<button class="nav-link">
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
								<button class="nav-link">
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
								<button class="nav-link active" id="pills-Send_Invitation-tab" data-bs-toggle="pill"
									data-bs-target="#pills-Send_Invitation" type="button" role="tab" aria-controls="pills-Send_Invitation"
									aria-selected="true">
									<div class="flex1">
										<div class="left_box">
											<i class="fa-solid fa-paper-plane"></i><br> Send Invitation
										</div>
									</div>
								</button>
							</li>
						</ul>
<%

Test test = (Test) request.getSession().getAttribute("test");

%>
						<div class="tab-content tab_con_box" id="pills-tabContent">
						
						<div class="tab-pane fade active show" id="pills-Send_Invitation" role="tabpanel" aria-labelledby="pills-Send_Invitation-tab">
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
                                                                        <p><b><%= test.getTestName() %></b>
                                                                        </p>
                                                                    </div>
                                                                </div>

                                                                <div class="col-md-6">
                                                                    <div class="invited_box">
                                                                        <label>Category Name</label>
                                                                        <p><b><%= test.getQualifier1() %> / <%= test.getQualifier2() %> /<%= test.getQualifier3() %> &nbsp;&nbsp;   Skills: Java  &nbsp;&nbsp;</b></p>
                                                                    </div>
                                                                </div>

                                                                <div class="col-md-6">
                                                                    <div class="invited_box">
                                                                        <label>Duration</label>
                                                                        <p><b><%= test.getTestTimeInMinutes() %> Mins</b></p>
                                                                    </div>
                                                                </div>


                                                                <div class="col-md-6">
                                                                    <div class="invited_box">
                                                                        <label>Attemps</label>
                                                                        <p><b><%= test.getNoOfConfigurableAttempts() %> </b></p>
                                                                    </div>
                                                                </div>

                                                                <div class="col-md-12">
                                                                    <div class="accordian_section">
                                                                        <div class="accordion" id="accordionExample">
                                                                           
                                                                           
<%--                                                                            <c:forEach var="section"  items="${test.sectionDtos}" > --%>
                                                                           
<%--                                                                                <% --%>
<!-- // 																			    int count = 1; -->
<%-- 																			    %> --%>
<%-- 																				<c:forEach var="ques" varStatus="status" items="${section.questions}" > --%>
<%-- 																				 Answer: ${ques.rightChoices}  --%>
																				
<%-- 																					<% --%>
<!-- // 																					count ++; -->
<%-- 																				%> --%>
<%-- 																				</c:forEach> --%>
<%--                                                                            </c:forEach> --%>
                                                                           
                                                                           
                                                                           <c:forEach var="section"  items="${test.sectionDtos}" >
                                                                            <div class="accordion-item">
                                                                              <h2 class="accordion-header" id="headingOne">
                                                                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                                                ${section.sectionName}
                                                                                </button>
                                                                              </h2>
                                                                              <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                                                                <div class="accordion-body">
                                                                                <div class="accordian_body_sec">
   <%
		    int count = 1;
		    %>
<c:forEach var="ques" varStatus="status" items="${section.questions}" >
				<div class="title">
				    <span><%= count %></span>
				    <p>Question - ${ques.questionText}  </p>
				</div>
                        <div class="options">
                            <ul>
                                <li style="${ques.choice1 == null || ques.choice1.length() == 0? 'display: none;':''}">Choice 1: &nbsp;&nbsp;  ${ques.choice1}</li>
                                <li style="${ques.choice2 == null || ques.choice2.length() == 0? 'display: none;':''}">Choice 2: &nbsp;&nbsp;  ${ques.choice2}</li>
                                <li style="${quest.choice3 == null || quest.choice3.length() == 0? 'display: none;':''}">Choice 3: &nbsp;&nbsp;  ${ques.choice3}</li>
                                <li style="${ques.choice4 == null || ques.choice4.trim().length() == 0? 'display: none;':''}">Choice 4:  &nbsp;&nbsp;  ${ques.choice4}</li>
                                <li style="${ques.choice5 == null || ques.choice5.trim().length() == 0? 'display: none;':''}">Choice 5:  &nbsp;&nbsp;  ${ques.choice5}</li>
				<li style="${ques.choice6 == null || ques.choice6.length() == 0? 'display: none;':''}">Choice 6:  &nbsp;&nbsp;  ${ques.choice6}</li>
                            </ul>
                            Answer: ${ques.rightChoices} 
                        </div>
			<%
				count ++;
			%>
			</c:forEach>


                                                                                </div>
                                                                                </div>
                                                                              </div>
                                                                            </div>
                                                                            </c:forEach>
<!--                                                                             <div class="accordion-item"> -->
<!--                                                                               <h2 class="accordion-header" id="headingTwo"> -->
<!--                                                                                 <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo"> -->
<!--                                                                                     Section 2 -->
<!--                                                                                 </button> -->
<!--                                                                               </h2> -->
<!--                                                                               <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample"> -->
<!--                                                                                 <div class="accordion-body"> -->
<!--                                                                                     <div class="accordian_body_sec"> -->
<!--                                                                                         <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas atque nemo voluptate consectetur. Possimus culpa corrupti qui. Autem minima maiores corporis rerum magnam! Veniam, harum pariatur. Rerum officia maiores odit!</p> -->
<!--                                                                                     </div> -->
<!--                                                                                 </div> -->
<!--                                                                               </div> -->
<!--                                                                             </div> -->
                                                                          
<!--                                                                             <div class="accordion-item"> -->
<!--                                                                               <h2 class="accordion-header" id="headingThree"> -->
<!--                                                                                 <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree"> -->
<!--                                                                                     Section 3 -->
<!--                                                                                 </button> -->
<!--                                                                               </h2> -->
<!--                                                                               <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample"> -->
<!--                                                                                 <div class="accordion-body"> -->
<!--                                                                                     <div class="accordian_body_sec"> -->
<!--                                                                                         <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas atque nemo voluptate consectetur. Possimus culpa corrupti qui. Autem minima maiores corporis rerum magnam! Veniam, harum pariatur. Rerum officia maiores odit!</p> -->
<!--                                                                                     </div> -->
<!--                                                                                 </div> -->
<!--                                                                               </div> -->
<!--                                                                             </div> -->
                                                                            
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row mt-4">
                                                    
                                                    
<!--                                <div class="form-group"> -->
<!--             <div class='input-group date' id='datetimepicker1'> -->
<!--                <input type='text' class="form-control" /> -->
<!--                <span class="input-group-addon"> -->
<!--                <span class="glyphicon glyphicon-calendar"></span> -->
<!--                </span> -->
<!--             </div> -->
<!--          </div> -->
                                                    
                                                   
                                            
                                                    
                                                    
                                                        <div class="col-md-6 mt-3">
                                                            <div class="Create_New_Question_field">
                                                                <div class="mb-3">
                                                                    <label for="text" class="form-label">Select
                                                                        Date</label>
                                                                    <input type="datetime-local" class="form-control" id="startDate" name="text" required="true">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 mt-3">
                                                            <div class="Create_New_Question_field">
                                                                <div class="mb-3">
                                                                    <label for="text" class="form-label">&nbsp;</label>
                                                                    <input type="datetime-local" class="form-control" id="endDate" name="text" required="true">
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
                                                                        
                                                                          <c:forEach var="user" items="${selectedusers}">
                                                                          <tr>
                                                                            <td>
                                                                                <div class="test_result">
                                                                                    <a href="test-analytics.html">
                                                                                        <!-- <span>Java Test Part 1</span> -->
                                                                                <div class="condidate_box">
                                                                                    <div class="condidate_icon ac_6">
                                                                                        <p><b>AC</b></p>
                                                                                    </div>
                                                                                    <div class="condidate_name">
                                                                                        <h4>${user.firstName} ${user.lastName}</h4>
                                                                                        <p>${user.email}</p>
                                                                                    </div>
                                                                                </div>
                                                                            </a>
                                                                            </div>
                                                                            </td>
                                                                        </tr>
                                                                           </c:forEach>
                                
<!--                                                                         <tr> -->
<!--                                                                             <td> -->
<!--                                                                                 <div class="test_result"> -->
<!--                                                                                     <a href="test-analytics.html"> -->
<!--                                                                                         <span>Java Test Part 1</span> -->
<!--                                                                                 <div class="condidate_box"> -->
<!--                                                                                     <div class="condidate_icon ac_6"> -->
<!--                                                                                         <p><b>AC</b></p> -->
<!--                                                                                     </div> -->
<!--                                                                                     <div class="condidate_name"> -->
<!--                                                                                         <h4>Aarti Chaudhary</h4> -->
<!--                                                                                         <p>aarti_choudhary@abc.com</p> -->
<!--                                                                                     </div> -->
<!--                                                                                 </div> -->
<!--                                                                             </a> -->
<!--                                                                             </div> -->
<!--                                                                             </td> -->
<!--                                                                         </tr> -->
                                                                        
                                                                        
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
                                                         <button class="save_btn" onclick="location.href = 'showUsersPro';">Back</button>
                                                    <button class="save_btn" onclick="shareTests();">Send Invitation</button>
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

<!-- 	<script src="quaintpro/admin/assets/js/jquery.slim.min.js"></script> -->
<!-- 	<script src="quaintpro/admin/assets/js/bootstrap.bundle.min.js"></script> -->
<!-- 	<script src="quaintpro/admin/assets/js/jquery-slider-min.js"></script> -->
<!-- 	<script src="quaintpro/admin/assets/js/apexcharts.js"></script> -->
	<script src="quaintpro/admin/assets/js/fontawesome.min.js"></script>
<!-- 	<script src="quaintpro/admin/assets/js/custom.js"></script> -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script src = "https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>  
<script src = "https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>  
<script src = "https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>  
<script src = "https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>  



	<script>

	jQuery(document).ready(function($) {
	    if (window.jQuery().datetimepicker) {
	        $('.datetimepicker').datetimepicker({
	            // Formats
	            // follow MomentJS docs: https://momentjs.com/docs/#/displaying/format/
	            format: 'DD-MM-YYYY hh:mm A',
	            
	            // Your Icons
	            // as Bootstrap 4 is not using Glyphicons anymore
	            icons: {
	                time: 'fa fa-clock-o',
	                date: 'fa fa-calendar',
	                up: 'fa fa-chevron-up',
	                down: 'fa fa-chevron-down',
	                previous: 'fa fa-chevron-left',
	                next: 'fa fa-chevron-right',
	                today: 'fa fa-check',
	                clear: 'fa fa-trash',
	                close: 'fa fa-times'
	            }
	        });
	    }
	});
	
    $(function () {
		
		
        $('#datetimepicker1').datetimepicker({
			format: 'DD/MM/YYYY hh:mm a'
			//minDate:new Date()
			
		});
        
		
		
         $('#datetimepicker2').datetimepicker({
			format: 'DD/MM/YYYY hh:mm a'
			
		});
    });

</script>

<script>


function shareTests(){
 console.log('in sharetests');
var startDate = document.getElementById("startDate").value; 
console.log('in sharetests '+startDate);
var endDate = document.getElementById("endDate").value; 
if(startDate == null || startDate == ''){
	sweetAlert('Information', 'Start Date and Time not present for the Test Link to be shared with the Candidate(s)','error');
	return;
}

if(endDate == null || startDate == ''){
	sweetAlert('Information', 'End Date and Time not present for the Test Link to be shared with the Candidate(s)','error');
	return;
}
startDate = encodeURI(startDate);
endDate = encodeURI(endDate);
window.location = "shareTestWithUsersPro?startDate="+startDate+"&endDate="+endDate;
}

function sweetAlert(msgtype, message, icon) {
	Swal.fire(msgtype, message, icon)
}

function notify(messageType, message){
 var notification = 'Information';
 //PNotify.prototype.options.styling = "jqueryui";
	 $(function(){
		 new PNotify({
		 title: notification,
		 text: message,
		 type: messageType,
		// width: '60%',
		// styling: 'bootstrap3',
		 hide: false,
		 buttons: {
    				closer: true,
    				sticker: false
					 },
		 history: {
    				history: false
			 }
	 });
		 
	 }); 	
}
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