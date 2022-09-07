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
							<li class="nav-item"><a class="nav-link active" href="proquestionbank"> <img
									src="quaintpro/admin/assets/img/question_active.png" class="active"> <img
									src="quaintpro/admin/assets/img/question.png" class="disactive"> Question Bank
							</a></li>
							<li class="nav-item"><a class="nav-link " href="protestbank"> <img
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

	<section class="qb_section create_question_sec">
		<div class="container">
			<form name="questionForm" method="post" modelAttribute="question" action="saveQuestionPro"
				enctype="multipart/form-data">
				<div class="row">
					<div class="col-md-12 mb-4">
						<div class="question_bank_heading">
							<h3>Create New Question</h3>
							<div class="question_btn">

								<input type="file" name="addimage" id="addimage" style="display: none;"> <input type="file"
									name="addaudio" id="addaudio" style="display: none;"> <input type="file" name="addvideo" id="addvideo"
									style="display: none;">


								<button class="custom_btn mr-3 addimage">
									<img src="quaintpro/admin/assets/img/img.png"> Add Image
								</button>
								<label class="queimage"></label>

								<button class="custom_btn mr-3 addvideo">
									<img src="quaintpro/admin/assets/img/video.png"> Add Video
								</button>
								<label class="queaudio"></label>
								<button class="custom_btn addvideo" id="myButton1">
									<img src="quaintpro/admin/assets/img/audio.png"> Add Audio
								</button>

								<label class="quevideo"></label>
							</div>







						</div>
					</div>
				</div>

				<div class="Create_New_Question_box">
					<%--                 <form> --%>
					<div class="row">
						<div class="col-md-12 col-lg-8">
							<div class="Create_New_Question_form">
								<div class="row">
									<div class="col-md-12">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="comment" class="form-label">Write a Question</label>
												<form:textarea path="question.questionText" required="true" class="form-control" rows="5"
													placeholder="Enter a question" />
												<form:hidden path="question.id" />
												<!--                                                 <textarea class="form-control" rows="5" id="comment" name="text"></textarea> -->
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="select" class="form-label">Difficulty Level</label>


												<form:select path="question.level" class="form-select">

													<form:options items="${levels}" itemValue="level" itemLabel="level" />
												</form:select>

												<!--                                                 <select class="form-select"> -->
												<!--                                                     <option>Select</option> -->
												<!--                                                 </select> -->
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="comment" class="form-label">Question Type</label>


												<form:select id="questionType" path="question.type" onchange="changeQType()" class="form-select">

													<form:options items="${types}" itemValue="type" itemLabel="type" />
												</form:select>

												<!--                                                 <select class="form-select"> -->
												<!--                                                     <option>Select</option> -->
												<!--                                                 </select> -->
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="comment" class="form-label">Options</label>
												<div class="input-group multi_check_box mb-3">
													<div class="input-group-prepend">
														<div class="input-group-text">
															<form:checkbox path="question.one" />
															<!--                                                             <input type="checkbox" value=""> -->
														</div>
													</div>
													<form:input path="question.choice1" name="choice1" id="choice1" required="true" class="form-control" />
													<!--                                                     <input class="form-control" type="text" value=""> -->
												</div>
												<div class="input-group multi_check_box mb-3">
													<div class="input-group-prepend">
														<div class="input-group-text">
															<form:checkbox path="question.two" />
															<!--                                                             <input type="checkbox" value=""> -->
														</div>
													</div>
													<form:input path="question.choice2" name="choice2" id="choice2" required="true" class="form-control" />
													<!--                                                     <input class="form-control" type="text" value=""> -->
												</div>
												<div class="input-group multi_check_box mb-3">
													<div class="input-group-prepend">
														<div class="input-group-text">
															<form:checkbox path="question.three" />
															<!--                                                             <input type="checkbox" value=""> -->
														</div>
													</div>
													<form:input path="question.choice3" name="choice3" id="choice3" class="form-control" />
													<!--                                                     <input class="form-control" type="text" value=""> -->
												</div>


												<div class="input-group multi_check_box mb-3">
													<div class="input-group-prepend">
														<div class="input-group-text">
															<form:checkbox path="question.four" />
															<!--                                                             <input type="checkbox" value=""> -->
														</div>
													</div>
													<form:input path="question.choice4" name="choice4" id="choice4" class="form-control" />
													<!--                                                     <input class="form-control" type="text" value=""> -->
												</div>
												<div class="input-group multi_check_box mb-3">
													<div class="input-group-prepend">
														<div class="input-group-text">
															<form:checkbox path="question.five" />
															<!--                                                             <input type="checkbox" value=""> -->
														</div>
													</div>
													<form:input path="question.choice5" name="choice5" id="choice5" class="form-control" />
													<!--                                                     <input class="form-control" type="text" value=""> -->
												</div>

												<div class="input-group multi_check_box mb-3">
													<div class="input-group-prepend">
														<div class="input-group-text">
															<form:checkbox path="question.six" />
															<!--                                                             <input type="checkbox" value=""> -->
														</div>
													</div>
													<form:input path="question.choice6" name="choice6" id="choice6" class="form-control" />
													<!--                                                     <input class="form-control" type="text" value=""> -->
												</div>





											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="comment" class="form-label">Justification for Answer</label>
												<form:textarea path="question.justification" id="justification" class="form-control answer" rows="5" />
												<!--                                                 <textarea class="form-control answer" rows="5" id="comment" name="text"></textarea> -->
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="text" class="form-label">Programing Language</label>
												<form:select path="question.lang" class="form-control">

													<form:options items="${languages}" itemValue="language" itemLabel="language" />
												</form:select>
												<!--                                                 <input type="text" class="form-control" id="text" name="text"> -->
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-lg-4">
							<div class="Create_New_Question_form">
								<div class="row">
									<div class="col-md-12">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="comment" class="form-label">Choose categories for Question</label>
												<div class="input-group multi_check_box mb-3">
													<div class="input-group-prepend">
														<div class="input-group-text">A.</div>
													</div>
													<form:input path="question.qualifier1" name="qualifier1" id="qualifier1" required="true"
														class="form-control" />
													<!--                                                     <input class="form-control" type="text" value=""> -->
												</div>
												<div class="input-group multi_check_box mb-3">
													<div class="input-group-prepend">
														<div class="input-group-text">B.</div>
													</div>
													<form:input path="question.qualifier2" name="qualifier2" id="qualifier2" class="form-control" />
													<!--                                                     <input class="form-control" type="text" value=""> -->
												</div>
												<div class="input-group multi_check_box mb-3">
													<div class="input-group-prepend">
														<div class="input-group-text">C.</div>
													</div>
													<form:input path="question.qualifier3" name="qualifier3" id="qualifier3" class="form-control" />
													<!--                                                     <input class="form-control" type="text" value=""> -->
												</div>
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="comment" class="form-label">Constraints</label>
												<form:textarea path="question.constrnt" rows="5" class="form-control" />
												<!--                                                 <textarea class="form-control" rows="5" id="comment" name="text"></textarea> -->
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="text" class="form-label">Question Weight</label>
												<form:input path="question.questionWeight" name="questionWeight" id="questionWeight"
													placeholder="Enter a number between 1 to 10" onkeypress="return isNumberKey(event)" class="form-control" />
												<!--                                                 <input type="text" class="form-control" id="text" name="text" placeholder="Enter a number between 1 to 10"> -->
											</div>
										</div>
									</div>


									<div class="col-md-12">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="comment" class="form-label">Instructions, If any</label>
												<form:textarea path="question.instructionsIfAny" style="overflow-y: scroll" id="output" class="form-control" />
												<!--                                                 <textarea class="form-control" rows="5" id="comment" name="text"></textarea> -->
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="text" class="form-label">Course Qualifier Description, if any</label>
												<form:input path="question.qualifierDescription" name="qualifierDescription" id="qualifierDescription"
													class="form-control" />
												<!--                                                 <input type="text" class="form-control" id="text" name="text"> -->
											</div>
										</div>
									</div>


									<div class="col-md-12">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="text" class="form-label">Course Context, if any</label>
												<form:input path="question.courseContext" name="courseContext" id="courseContext" class="form-control" />
												<!--                                                 <input type="text" class="form-control" id="text" name="text"> -->
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="Create_New_Question_field">
											<div class="mb-3">
												<label for="text" class="form-label">Weight for Course Context, if any</label>
												<form:input path="question.courseWeight" name="courseWeight" id="courseWeight"
													placeholder="Enter a number between 1 to 10" class="form-control" />
												<!--                                                 <input type="text" class="form-control" id="text" name="text"> -->
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12 col-lg-12">
							<div class="question_save text-center">
								<button class="cancle_btn" onclick="location.href = 'proquestionbank';">Cancel</button>
								<button class="save_btn">Save</button>
							</div>
						</div>
					</div>
					<%--             </form> --%>
				</div>
			</form>
		</div>
	</section>





	<script src="quaintpro/admin/assets/js/jquery.slim.min.js"></script>
	<script src="quaintpro/admin/assets/js/bootstrap.bundle.min.js"></script>
	<script src="quaintpro/admin/assets/js/jquery-slider-min.js"></script>
	<!--     <script src="quaintpro/admin/assets/js/apexcharts.js"></script> -->
	<script src="quaintpro/admin/assets/js/fontawesome.min.js"></script>
	<!--     <script src="quaintpro/admin/assets/js/custom.js"></script> -->


	<script type="text/javascript">
	
	 window.onload = function() {
		var qtype = '${question.type}';
// 		manageQuestionTypeUI(qtype);
			/* if(qtype == 'CODING'){
			document.getElementById("choice1").required = false;
			document.getElementById("choice2").required = false;
			
			document.getElementById("input").required = true;
			document.getElementById("output").required = true;
			}
			else if(qtype == 'FULL_STACK_JAVA' || qtype == 'FULLSTACK'){
			document.getElementById("choice1").required = false;
			document.getElementById("choice2").required = false;
			
			document.getElementById("input").required = false;
			document.getElementById("output").required = false;
			
			}
			else{
				document.getElementById("choice1").required = true;
			document.getElementById("choice2").required = true;
			
			document.getElementById("input").required = false;
			document.getElementById("output").required = false;
			} */
		
	    }
		
	
            $(document).on('click', '#addanother', function () {
                var alphabet = nextString($("#maindivforaddmore").children().last().children().first().text());
                $("#maindivforaddmore").append("<div class='option'><span>" + alphabet + "</span><input type='text'><div class='choice'><input name='option' type='radio'><a href='javascript:void(0);' class='removenewdiv'>-</a></div></div>");
            });

            $(document).on('click', '.removenewdiv', function () {
                $(this).parent().parent().remove();
            });

            function nextString(str) {
                if (!str)
                    return 'A'  // return 'A' if str is empty or null

                let tail = ''
                let
                i = str.length - 1
                let
                char = str[i]
                // find the index of the first character from the right that is not a 'Z'
                while (char === 'Z' && i > 0) {
                    i--
                    char = str[i]
                    tail = 'A' + tail   // tail contains a string of 'A'
                }
                if (char === 'Z')   // the string was made only of 'Z'
                    return 'AA' + tail
                // increment the character that was not a 'Z'
                return str.slice(0, i) + String.fromCharCode(char.charCodeAt(0) + 1) + tail
            }

        </script>

	<script>
            $(function () {
                $(".addimage").on('click', function (e) {
                    e.preventDefault();
                    $("#addimage").trigger('click');
                });
                $(".addaudio").on('click', function (e) {
                    e.preventDefault();
                    $("#addaudio").trigger('click');
                });
                $(".addvideo").on('click', function (e) {
                    e.preventDefault();
                    $("#addvideo").trigger('click');
                });
            });


            $('#addimage').change(function () {
                var file = $('#addimage')[0].files[0].name;
                $('.queimage').text('Image: '+file);
            });
            $('#addaudio').change(function () {
                var file = $('#addaudio')[0].files[0].name;
                $('.queaudio').text('Audio: '+file);
            });
            $('#addvideo').change(function () {
                var file = $('#addvideo')[0].files[0].name;
                $('.quevideo').text('Video: '+file);
            });

	    
	      $('#search').on('click',function(){
	    var text = document.getElementById("searchText").value;
		if(text.length != 0){
		window.location="searchQuestions2?searchText="+text;
		}
	    });
	    
	    function notify(messageType, message){
		 var notification = 'Information';
			 $(function(){
				 new PNotify({
				 title: notification,
				 text: message,
				 type: messageType,
				 styling: 'bootstrap3',
				 hide: true
			     });
			 }); 	
		}
		
		function confirm(id) {
           (new PNotify({
		    title: 'Confirmation Needed',
		    text: 'Are you sure? Do you really want to delete this Q?',
		    icon: 'glyphicon glyphicon-question-sign',
		    hide: false,
		    confirm: {
			confirm: true
		    },
		    buttons: {
			closer: false,
			sticker: false
		    },
		    history: {
			history: false
		    }
		})).get().on('pnotify.confirm', function() {
		    window.location = "removeQuestion?qid="+id;
		}).on('pnotify.cancel', function() {
		   
		});
        }
		
		
	
	function changeQType(){
	var  selectedValue= $("#questionType").val();
		manageQuestionTypeUI(selectedValue);
	}
	
	
		function isNumberKey(e){
			var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
			  var value = Number(e.target.value + e.key) || 0;

			  if ((keyCode >= 37 && keyCode <= 40) || (keyCode == 8 || keyCode == 9 || keyCode == 13) || (keyCode >= 48 && keyCode <= 57)) {
				return isValidNumber(value);
			  }
			  return false;
		}   
		
		function isValidNumber (number) {
		  return (1 <= number && number <= 10 )
		}
	
		
		function sweetAlert(msgtype, message, icon) {
			Swal.fire(msgtype, message, icon)
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