<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>eAssess</title>
      	<link href="images/E-assess_E.png" rel="shortcut icon">   
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Open+Sans+Condensed:wght@300&display=swap">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="css_new/bootstrap.min.css">
	<link rel="stylesheet" href="css_new/styles_new.css">
	<link href="css_new/pnotify.custom.min.css" media="all" rel="stylesheet" type="text/css">
	<% String cid=request.getParameter("companyId"); %>
	
	<style>
 
	</style>
</head>
<body class="pre-login">
	<header>
		<div class="container">
<!-- 					  <div class="text-left"> -->
<!-- 			        Language:<a href="javascript:changeLang('eng')">Eng</a> -->
<!-- 			        <a href="javascript:changeLang('arabic')">العربية</a> -->
<!-- 			    	</div> -->
			<h1 class="text-center">
				<img src="images_new/yaksha.png" width="210px" alt="Yaksha Online"> ${publicTestUTF.isReadyTOask } <br>
				<span>${publicTestUTF.eAssessQuotes }</span>
			</h1>
			<h2>
				${testUserData.testName}
				<!--
					<div class="company">
					</div>
				-->
			</h2>
			<div class="user-details form">
				<form name="testloginform" class="userform" id="testloginform" method="post" modelAttribute="testUserData" action="publicTestAuthenticate">
					<form:hidden path="testUserData.testId" />
					<form:hidden path="testUserData.user.companyName" />
					<form:hidden path="testUserData.user.companyId" />	
					<form:hidden path="testUserData.startTime" value="${startTime}" />
					<form:hidden path="testUserData.endTime" value="${endTime}" />
					<ul>
						<li>
							<label for="userName">${publicTestUTF.email}</label>
							<form:input  type="email" path="testUserData.user.email" name="email" id="userName" required="true" style="width: 210px;"/>
							<div class="error-msg col-red mt-2 d-none">
							${publicTestUTF.enterValidEmail}
							</div>
						</li>
						<li>
							<label for="firstName">${publicTestUTF.firstName}</label>
							<form:input path="testUserData.user.firstName" name="firstName" id="firstName" required="true" style="width: 210px;"/>
							<div class="error-msg col-red mt-2 d-none">
							${publicTestUTF.enterValidFirstName}
							</div>
						</li>
						<li>
							<label for="lastName">${publicTestUTF.lastName}</label>
							<form:input path="testUserData.user.lastName" name="lastName" id="lastName" required="true" style="width: 210px;"/>
							<div class="error-msg col-red mt-2 d-none">
									${publicTestUTF.enterValidLastName}
							</div>
						</li>
						<li>
							<label for="lastName">(${publicTestUTF.optional})${publicTestUTF.candidateId}</label>
							<form:input path="testUserData.user.candidateId" name="candidateId" id="candidateId" required="true" style="width: 210px;"/>
							<div class="error-msg col-red mt-2 d-none">
								Enter Valid Lastname
							</div>
						</li>
						
							</ul>
						 <div class="mt-3">
						</div>
						 <ul>
						<li  >
							<label for="lastName">(${publicTestUTF.optional}) ${publicTestUTF.degree}</label>
							<form:input  type="email" path="testUserData.user.degree" name="degree" id="degree"  style="width: 210px;"/>
						</li>
						<li  >
							<label for="firstName">(${publicTestUTF.optional})${publicTestUTF.passingYear}</label>
							<form:input path="testUserData.user.passingYear" name="passingYear" id="passingYear"  style="width: 210px;"/>
						</li>
						<li  >
							<label for="lastName">(${publicTestUTF.optional}) ${publicTestUTF.mobile}</label>
							<form:input path="testUserData.user.mobileNumber" name="mobileNumber" id="mobileNumber"   style="width: 210px;"/>
						</li>
						<li style="width: 18%">
						 </li>
						<li style="width: 14%">
						 	</li>

						
							</ul>
							
						
				
					<div class="mt-3 text-right">
						<button id="submitFormButton" type="button" class="btn btn-primary" style="margin:0 auto;display:block;">
						${publicTestUTF.signIn}
						</button>
				
						<button id="login_otp" type="button" class="btn btn-secondary" onclick="clickform()" style="display:none">
							Get OTP
						</button>
				
						<button id="verify_otp" type="button" class="btn btn-secondary" onclick="verifyOtp()" style="display:none">
							Verify OTP
						</button>
					</div>
				</form>
			</div>
			<div class="enter-otp form d-none">
				<div class="row">
					<div class="col-4 my-auto">
						<span>Enter OTP mailed to the provided email id</span>
						<span class="col-red error-otp-msg d-none">
							Enter Vaild OTP
						</span>
					</div>
					<div class="div col-1">
						<input type="text" class="text-center" maxlength="1">
					</div>
					<div class="div col-1">
						<input type="text" class="text-center" maxlength="1">
					</div>
					<div class="div col-1">
						<input type="text" class="text-center" maxlength="1">
					</div>
					<div class="div col-1">
						<input type="text" class="text-center" maxlength="1">
					</div>
					<div class="col-4">
						<button class="btn btn-primary mr-3" type="button" id="otpSubmit">
							Login
						</button>
						<button class="btn btn-secondary" type="button">
							Resend Otp
						</button>
					</div>
				</div>
			</div>
		</div>
		<a href="javascript:void" class="contact-us">
			reachus@eassess.com
		</a>
	</header>
	<section>
		<div class="container">
			<ul>
				<li class="mb-3">
					<h3 class="mb-2">
					${footerUTF.instructions}
<!-- 						instructions -->
					</h3>
					<ul>
						<li class="mb-2">
						${footerUTF.instruct1}
<!-- 							Test Results will be sent to you on Completion -->
						</li>
						<li class="mb-2">
						${footerUTF.instruct2}
<!-- 							Click Submit for Submission of your Test -->
						</li>
						<li class="mb-2">
						${footerUTF.instruct3}
<!-- 							System will auto Submit Test if Timer Expires -->
						</li>
					</ul>
				</li>
				<li class="mb-3">
					<h3 class="mb-2">
					${footerUTF.webProctoring}
<!-- 						web proctoring -->
					</h3>
					<ul>
						<li class="mb-2">
						${footerUTF.webProctor1}
<!-- 							Do not move mouse pointer to a different tab -->
						</li>
						<li class="mb-2">
						${footerUTF.webProctor2}
<!-- 							Use F11 windows for Test if required -->
						</li>
						<li class="mb-2">
						${footerUTF.webProctor3}
<!-- 							Non Compliance can result in your Test Declared Invalid -->
						</li>
					</ul>
				</li>
			</ul>
			<ul>
				<li class="mb-3">
					<h3 class="mb-2">
					${footerUTF.tenants}
<!-- 						tenants -->
					</h3>
					<ul>
						<li class="mb-2">
						${footerUTF.tenants1}
<!-- 							Domain specific Users are advise to login using Corporate Credentials -->
						</li>
						<li class="mb-2">
						${footerUTF.tenants2}
<!-- 							Every User is directed to provide Login data for Individual Reporting -->
						</li>
						<li class="mb-2">
						${footerUTF.tenants3}
<!-- 							Tenant Admin will see Tenant specfic data only -->
						</li>
					</ul>
				</li>
				<li class="mb-3">
					<h3 class="mb-2">
						EASSESS
					</h3>
					<ul>
						<li class="mb-2">
						${footerUTF.eassess1}
<!-- 							Multi Technology Assessments -->
						</li>
						<li class="mb-2">
						${footerUTF.eassess2}
<!-- 							Test Cases Based Evaluation -->
						</li>
						<li class="mb-2">
						${footerUTF.eassess3}
<!-- 							Weighted Adaptive Assessments -->
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</section>
	<footer>
		<div class="container pt-3 pb-3">
			<span>
				&copy; Copyright 2020 :: eAssess :: Powered by eAssess
			</span>
			<a href="javascript:void(0)" class="mr-5">
			${footerUTF.termsAndConditions}
<!-- 				Terms and Conditions -->
			</a>
			<a href="javascript:void(0)">
			${footerUTF.privacyPolicy}
<!-- 				Privacy Policy -->
			</a>
		</div>
	</footer>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="scripts/custom.js"></script>
<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
<script type="text/javascript" src="scripts/pnotify.nonblock.js"></script>
<script type="text/javascript" src="scripts/pnotify.buttons.js"></script>
<script>
	var validateEmail = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
	var randomNumber = Math.floor(Math.random() * (6 - 1 + 1)) + 1;

	$('header').attr('style', "background-image: url(images_new/banner_" + randomNumber + ".jpg);");

	$('.form input').on('focus', function () {
		$(this).siblings('label').addClass('active');
	}).on('focusout', function () {
		if ($.trim($(this).val()) == "") {
			$(this).val('').siblings('label').removeClass('active');
		}
	});

	$('button#submitFormButton').on('click', function () {
		var status = true,
			$this = $(this),
			userElm = $('#userName'),
			firstElm = $('#firstName'),
			lastElm = $('#lastName'),
			userName = $.trim(userElm.val()),
			firstName = $.trim(firstElm.val()),
			lastName = $.trim(lastElm.val());
		
		$this.closest('.user-details').find('.error-msg').addClass('d-none');

		if (!validateEmail.test(userName)) {
			userElm.siblings('.error-msg').removeClass('d-none');
			status = false
		}
		if (firstName.length == 0) {
			firstElm.siblings('.error-msg').removeClass('d-none');
			status = false
		}
		if (lastName.length == 0) {
			lastElm.siblings('.error-msg').removeClass('d-none');
			status = false
		}
		if (status) {
			fullScreen();
			$("#testloginform").submit();
		} else {
			return false;
		}
	});

	function clickform() {
		var email = document.getElementById("username").value;
		var firstname = document.getElementById("firstName").value;
		var lastname = document.getElementById("lastName").value;
		
		if(!email || 0 === email.length){
			notify('Enter a valid email id');
			return;
		}

		if(!firstname || 0 === firstname.length){
			notify('First Name is mandatory');
			return;
		}
		if(!lastname || 0 === lastname.length){
			notify('Last Name is mandatory');
			return;
		}
				
		var companyId = '<%= request.getParameter("companyId") %>';
		var testName = document.getElementById("testName").value;
		var userDetails = {};
		
		userDetails.user = email;
		userDetails.testName = testName;
		userDetails.companyId = companyId;
		
		var url = "getotpfortest";
					
		$.ajax({
			url : url,
			type: "POST",
			//dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(userDetails),
			//processData: false,
			success : function(data) {
				// console.log("SUCCESS: ", data);
				if(data == "success"){
					document.getElementById("verify_otp").style.display = "";
					document.getElementById("login_otp").style.display = "none";
					document.getElementById("otpLabel").style.display = "";
					document.getElementById("otpLabelPass").style.display = "";
					notify('Check your inbox for OTP');
					//otpLabel
				} else {
					notify("Check your details. OTP generation failed. Try again or contact Test Admin");
				}
				//document.getElementById("no-"+sectionName).innerHTML = data;
			},
			error : function(e) {
				// console.log("ERROR: ", e);
			}
		});	
	}
	
	function verifyOtp(){
		var email = encodeURIComponent(document.getElementById("username").value);
		var companyId = encodeURIComponent('<%= request.getParameter("companyId") %>');
		var testName = encodeURIComponent(document.getElementById("testName").value);
		var otp = document.getElementById("otpLabelPass").value;
		var firstname = document.getElementById("firstName").value;
		var lastname = document.getElementById("lastName").value;
				
		if(!email || 0 === email.length){
			notify('Enter a valid email id');
			return;
		}
		
		if(!firstname || 0 === firstname.length){
			notify('First Name is mandatory');
			return;
		}
		
		if(!lastname || 0 === lastname.length){
			notify('Last Name is mandatory');
			return;
		}
		if(!otp || 0 === otp.length){
			notify('OTP is mandatory');
			return;
		}

		var url = "validateotpfortest?otp="+otp+"&email="+email+"&companyId="+companyId+"&test="+testName;
		// console.log('here url '+url);
		$.ajax({
			url : url,
			success : function(data) {
				// console.log("SUCCESS: ", data);
				if(data == "success"){
					document.getElementById("verify_otp").style.display = "none";
					document.getElementById("login_otp").style.display = "none";
					document.getElementById("otpLabel").style.display = "none";
					document.getElementById("otpLabelPass").style.display = "none";
					document.getElementById("submitFormButton").style.display = "";
					notify('OTP validation successful! Now click to start test.');
				}
				else{
					notify("Invalid OTP Entered. Either enter a correct OTP or refresh the page and click to generate a new OTP");
				}
				//document.getElementById("no-"+sectionName).innerHTML = data;
			},
			error : function(e) {
				// console.log("ERROR: ", e);
			}
		});
	}

	function notify(text){
		var notification = 'Information';
		$(function(){
			new PNotify({
				title: notification,
				text: text,
				type: 'Information',
				width: '20%',
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
	function changeLang(lang){
		var url = new URL(window.location.href);
		url.searchParams.set('lang',lang);
		var new_url = url.toString();
		window.location = new_url
// 		var search_params = url.searchParams;
// 		alert(new_url);
	}
	function fullScreen(){
		console.log('iner fullscreen called');
	}
	
	
</script>
   
</html>