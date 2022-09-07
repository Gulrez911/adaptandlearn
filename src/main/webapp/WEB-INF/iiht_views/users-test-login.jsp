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
         <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="newstudentjourney/css/font-awesome.min.css">
        <link rel="stylesheet" href="newstudentjourney/css/bootstrap.min.css">
        <link rel="stylesheet" href="newstudentjourney/css/style_1.css">
	<link href="css_new/pnotify.custom.min.css" media="all" rel="stylesheet" type="text/css">
	<% String cid=request.getParameter("companyId"); %>
</head>
    <body>
        <div class="master-container">
            <header>
                <div class="container-fluid">
                    <a href="#" class="yaksha-logo">
                        E<span>ASSESS</span>
                    </a>
                </div>
            </header>
			<form name="testloginform" class="userform" id="testloginform" method="post" modelAttribute="testUserData" action="publicTestAuthenticateNew">
            <section class="content-section">
                <div class="container-fluid">
                    <div class="page-header mb-4">
                        <h1 class="text-center">
                            <a href="#" class="yaksha-logo">
                                E<span>ASSESS</span>
                            </a>
                            ${publicTestUTF.isReadyTOask } 
                        </h1>
                    </div>
                    <div class="container">
                        <div class="row mt-5 mb-5">
                            <div class="col-12 text-center mb-5">
                                <h2>
                                    ${testUserData.testName}
                                </h2>
                            </div>
							<form:hidden path="testUserData.testId" />
							<form:hidden path="testUserData.user.companyName" />
							<form:hidden path="testUserData.user.companyId" />	
							<form:hidden path="testUserData.startTime" value="${startTime}" />
							<form:hidden path="testUserData.endTime" value="${endTime}" />
                            <div class="col-xs-12 col-md-6 col-xl-3 mb-3">
                                <div class="form-group">
<!--                                     <label>Email address</label> -->
                                         <label>${publicTestUTF.email}</label>
                                   <!-- <input type="email" class="form-control" placeholder="Email"> -->
								   <form:input class="form-control"  type="email" path="testUserData.user.email" name="email" id="userName" required="true" placeholder="${publicTestUTF.email}" />
<!--                                     <small class="form-text text-danger">We'll never share your email with anyone else.</small> -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 col-xl-3 mb-3">
                                <div class="form-group">
<!--                                     <label>First Name</label> -->
                                    <label>${publicTestUTF.firstName}</label>
                                   <!--  <input type="text" class="form-control" placeholder="First Name"> -->
								   <form:input class="form-control" path="testUserData.user.firstName" name="firstName" id="firstName" required="true" placeholder="${publicTestUTF.firstName}" />
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 col-xl-3 mb-3">
                                <div class="form-group">
                                    <label>${publicTestUTF.lastName}</label>
                                   <!-- <input type="text" class="form-control" placeholder="Last Name"> -->
								   <form:input class="form-control" path="testUserData.user.lastName" name="lastName" id="lastName" required="true" placeholder="${publicTestUTF.lastName}"/>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 col-xl-3 mb-3">
                                <div class="form-group">
                                    <label>${publicTestUTF.candidateId}</label>
                                   <!-- <input type="text" class="form-control" placeholder="Candidate ID"> -->
								   <form:input class="form-control"  path="testUserData.user.candidateId" name="candidateId" id="candidateId" required="true" placeholder="${publicTestUTF.candidateId}"/>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 col-xl-3 mb-3">
                                <div class="form-group">
                                    <label>${publicTestUTF.degree}</label>
                                   <!-- <input type="text" class="form-control" placeholder="Candidate ID"> -->
								   <form:input class="form-control"  path="testUserData.user.degree" name="degree" id="degree"  placeholder="${publicTestUTF.degree}" />
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 col-xl-3 mb-3">
                                <div class="form-group">
                                    <label>${publicTestUTF.passingYear}</label>
                                   <!-- <input type="text" class="form-control" placeholder="Candidate ID"> -->
								   <form:input class="form-control"  path="testUserData.user.passingYear" name="passingYear" id="passingYear" placeholder="${publicTestUTF.passingYear}"/>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 col-xl-3 mb-3">
                                <div class="form-group">
                                    <label>${publicTestUTF.mobile}</label>
                                   <!-- <input type="text" class="form-control" placeholder="Candidate ID"> -->
								   <form:input class="form-control"  path="testUserData.user.mobileNumber" name="mobileNumber" id="mobileNumber" placeholder="${publicTestUTF.mobile}"/>
                                </div>
                            </div>
                            <div class="col-12 text-center">
                                <button class="btn btn-primary" type="button" id="submitFormButton">
<!--                                     Submit -->
					${publicTestUTF.signIn}
                                </button>
                                <div class="mt-3">
                                	${publicTestUTF.ifYouNeedAny}
<!--                                     If you need any assistance please contact us at -->
                                    <a href="mailto:reachus@eassess.com">reachus@eassess.com</a>
                                </div>
                            </div>
							
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-6 mb-3">
                                <div class="card">
                                    <div class="card-header">
                                       ${footerUTF.instructions}
                                    </div>
                                    <div class="card-body">
                                        <p>${footerUTF.instruct1}</p>
                                        <p>${footerUTF.instruct2}</p>
                                        <p>${footerUTF.instruct3}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 mb-3">
                                <div class="card">
                                    <div class="card-header">
                                       ${footerUTF.webProctoring}
                                    </div>
                                    <div class="card-body">
                                        <p>${footerUTF.webProctor1}</p>
                                        <p>${footerUTF.webProctor2}</p>
                                        <p>${footerUTF.webProctor3}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 mb-3">
                                <div class="card">
                                    <div class="card-header">
                                       ${footerUTF.tenants}
                                    </div>
                                    <div class="card-body">
                                        <p>${footerUTF.tenants1}</p>
                                        <p>${footerUTF.tenants2}</p>
                                        <p>${footerUTF.tenants3}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6 mb-3">
                                <div class="card">
                                    <div class="card-header">
                                        EASSESS
                                    </div>
                                    <div class="card-body">
                                        <p>${footerUTF.eassess1}</p>
                                        <p>${footerUTF.eassess2}</p>
                                        <p>${footerUTF.eassess3}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
			</form>
            <footer>
                <div class="container-fluid text-right">
                    <span class="copyright">
                        &copy; Copyright 2020-2021 - eAssess
                    </span>
                    <a href="#">
                        ${footerUTF.termsAndConditions}
                    </a>
                    <a href="#">
                        ${footerUTF.privacyPolicy}
                    </a>
                </div>
            </footer>
        </div>
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
			notify('Invalid User name. Enter a valid email');
			userElm.siblings('.error-msg').removeClass('d-none');
			status = false;
			return;
		}
		if (firstName.length == 0) {
			notify('Invalid First Name');
			firstElm.siblings('.error-msg').removeClass('d-none');
			status = false;
			return;
		}
		if (lastName.length == 0) {
			notify('Invalid Last Name');
			lastElm.siblings('.error-msg').removeClass('d-none');
			status = false;
			return;
		}
		if (status) {
			//fullScreen();
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
	
	function fullScreen(){
		console.log('iner fullscreen called');
	}
	
	
</script>
	
   
</html>