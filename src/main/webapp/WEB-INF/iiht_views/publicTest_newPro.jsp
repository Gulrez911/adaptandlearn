<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Quantpro</title>
<link rel="shortcut icon" href="quaintpro/admin/assets/img/favicon.png" type="image/x-icon">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="quaintpro/admin/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="quaintpro/admin/assets/css/style.css" rel="stylesheet">


<!--  gulrez -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

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
.swal2-styled.swal2-confirm {
	padding: 10px 17px;
}

.submit2 {
	font-size: 1.1rem;
	border-radius: 5px;
	outline: none;
	border: none;
	width: 85%;
	background: #4782BC;
	color: white;
}

body.login_page .wrapper {
	width: 451px;
	padding: 2rem 0 2rem 0;
	margin: 50px auto;
	background: #ffffff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	text-align: center;
	box-shadow: 0 20px 35px rgb(0 0 0/ 10%);
}
</style>
</head>
<body class="login_page">

	<div class="wrapper">
		<h1>Hello Again!</h1>
		<p>
			Welcome back you've <br> been missed!
		</p>

		<!-- 		<span>Sign in</span> -->

		<%-- 		<form:form id="loginForm" method="post" modelAttribute="user" action="proindex"> --%>
		<%-- 			<form:input type="email" path="email" name="email" id="username" placeholder="Enter username" required="true" /> --%>
		<%-- 			<form:password path="password" name="password" id="password" placeholder="Password" required="true" /> --%>
		<!-- 			<button type="button" id="myButton">Sign in</button> -->
		<%-- 		</form:form> --%>

		<form name="testloginform" class="userform" id="testloginform" method="post" modelAttribute="testUserData"
			action="publicTestAuthenticatePro">
			<form:hidden path="testUserData.testId" />
			<form:hidden path="testUserData.user.companyName" />
			<form:hidden path="testUserData.user.companyId" />
			<form:hidden path="testUserData.startTime" value="${startTime}" />
			<form:hidden path="testUserData.endTime" value="${endTime}" />
			<!--                             <label>User Name</label> -->
			<form:input type="email" path="testUserData.user.email" name="email" id="username" placeholder="Email"
				required="true" />
			<!--                             <label>First Name</label> -->
			<form:input path="testUserData.user.firstName" name="firstName" id="firstName" placeholder="First Name"
				required="true" />
			<!--                             <label>Last Name</label> -->
			<form:input path="testUserData.user.lastName" name="lastName" id="lastName" placeholder="Last Name" required="true" />
			<!--                             <label>Test Name</label> -->
			<form:input path="testUserData.testName" name="testName" id="testName" required="true" disabled="true" />

			<!-- 						  <label id="otpLabel" style="display:none">Enter OTP</label> -->
			<form:input path="testUserData.user.password" name="testName" id="otpLabelPass" style="display:none" />

			<!--                             <label>Company</label> -->
			<form:input path="testUserData.user.companyName" name="companyName" id="companyName" disabled="true" />
			<input id="submitFormButton" type="button" class="subbutton" onclick="checkMandatoryAndSubmit()" value="SIGN IN"
				style="background: #4782BC; color: white;"> <a href="javascript:clickform();" id="login_otp"
				class="btn btn-secondary" style="display: none">Get OTP</a> <a href="javascript:verifyOtp();" id="verify_otp"
				class="btn btn-secondary" style="display: none">Verify OTP</a>
		</form>
		<%--     <form> --%>
		<!--       <input type="text" placeholder="Enter username"> -->
		<!--       <input type="password" placeholder="Password"> -->
		<%--     </form> --%>
		<!--     <button type="submit" id="myButton">Sign in</button> -->








	</div>


	<script>
		function checkMandatoryAndSubmit() {
			var email = document.getElementById("username").value;
			var firstname = document.getElementById("firstName").value;
			var lastname = document.getElementById("lastName").value;
			if (!email || 0 === email.length) {
				notify('Enter a valid email id');
				return;
			}

			if (!firstname || 0 === firstname.length) {
				notify('First Name is mandatory');
				return;
			}

			if (!lastname || 0 === lastname.length) {
				notify('Last Name is mandatory');
				return;
			}

			fullScreen();
			$("#testloginform").submit();

		}

		function notify(text) {
			var notification = 'Information';
			$(function() {
				new PNotify({
					title : notification,
					text : text,
					type : 'Information',
					width : '20%',
					hide : false,
					buttons : {
						closer : true,
						sticker : false
					},
					history : {
						history : false
					}
				});

			});
		}

		// define a handler

		function fullScreen() {
			console.log('iner fullscreen called');
			// window.frames.resetCount();
			// window.frames.fullScreen();
			// parent.resetCount();  //enable it later for full screen to work
			//parent.fullScreen(); //enable it later for full screen to work
			// $("#cls").click()
			// var elem = document.documentElement;
			// if (elem.requestFullscreen) {
			// elem.requestFullscreen();
			// } else if (elem.mozRequestFullScreen) { /* Firefox */
			// elem.mozRequestFullScreen();
			// } else if (elem.webkitRequestFullscreen) { /* Chrome, Safari and Opera */
			// elem.webkitRequestFullscreen();
			// } else if (elem.msRequestFullscreen) { /* IE/Edge */
			// elem.msRequestFullscreen();
			// }
			// } 
		}
	</script>

</body>
</html>
