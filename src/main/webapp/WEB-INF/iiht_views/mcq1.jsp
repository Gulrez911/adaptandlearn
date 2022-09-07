<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Adapt and Learn MCQ</title>

    <!-- Bootstrap core CSS -->
    <link href="b2c/css/bootstrap.min.css" rel="stylesheet" />
    <link href="b2c/css/assessment.css" rel="stylesheet" />

    <!-- Google Font -->
      <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;1,200;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">
	  
<style>
.instructions-text .heading {
    font-size: 23px;
    font-weight: 500;
    margin-top: 0rem;
}
</style>
  </head>
  <body> 
    <header>
      <div class="logo"> <img src="b2c/images/logo.png" alt="LOGO"> </div>
      <h1>${testUserData.testName}</h1>
    </header>
    <div class="container">
	<form name="testloginform" class="userform" id="testloginform" method="post" modelAttribute="testUserData" action="publicTestAuthenticateNew">
        <div class="row my-4">
			<form:hidden path="testUserData.testId" />
			<form:hidden path="testUserData.user.companyName" />
			<form:hidden path="testUserData.user.companyId" />	
			<form:hidden path="testUserData.startTime" value="${startTime}" />
			<form:hidden path="testUserData.endTime" value="${endTime}" />
          <div class="col-md-6">
            <div class="form-wrapper">
             
              <div class="form-group">
                <label for="email">Email</label>
               <!-- <input type="text" class="form-control" id="email"> -->
				<form:input class="form-control"  type="email" path="testUserData.user.email" name="email" id="userName" required="true" placeholder="${publicTestUTF.email}" />
              </div>
              <div class="form-group">
                <label for="firstName">First Name</label>
               <!-- <input type="text" class="form-control" id="name"> -->
			   <form:input class="form-control" path="testUserData.user.firstName" name="firstName" id="firstName" required="true" placeholder="${publicTestUTF.firstName}" />
              </div>
              <div class="form-group">
                <label for="surname">Last Name</label>
                <!-- <input type="text" class="form-control" id="surname"> -->
				<form:input class="form-control" path="testUserData.user.lastName" name="surname" id="surname" required="true" placeholder="${publicTestUTF.lastName}"/>
              </div>
              <div class="d-flex justify-content-center mt-5 mb-3">
				<!-- <button>Submit</button>  -->
				<button id="submitFormButton">
<!--                                     Submit -->
					Sign in
				</button>
			  </div>
              <div class="note-text mb-5">If you need any assistance please contact us at adapt@learn.online</div>
            
            </div>
          </div>
		  
          <div class="col-md-6">
              <div class="instructions-text">
                  <div>
                    <div class="heading"><img src="b2c/images/instruction.svg" width="18" class="align-baseline mr-2"> Instructions</div>
                    <ul>
                      <li>Evaluation Results will be displayed post the completion of the assessment</li>
                      <li>Click Submit for Submission of your assessment</li>
                      <li>System will auto Submit assessment if you exceed the time assigned to the assessment</li>
                    </ul>
                  </div>
                  <div>
                    <div class="heading"> <img src="b2c/images/web.svg" width="18" class="align-baseline mr-2"> Further </div>
                    <ul>
                      <li>We may record your attempts to move away from the test screen:)</li>
                      <li>Please take this assessment from a computer or a laptop</li>
                      <li>We are getting mobile friendly pretty soon</li>
                    </ul>
                  </div>
                  <div>
                    <div class="heading"> <img src="b2c/images/tenant.svg" width="18" class="align-baseline mr-2"> General</div>
                    <ul>
                      <li>Your identity data is safe with us</li>
                      <li>Pretty soon, we will share you your credentials to login to your User Area</li>
                      <li>In your User Area, you will see your history of assessments, scores, course recommendations & more</li>
                    </ul>
                  </div>
                  <div>
                    <div class="heading"> <img src="b2c/images/yaksha.svg" width="18" class="align-baseline mr-2"> Costing, if any </div>
                    <ul>
                      <li>Free Assessments</li>
                      <li>Free Recommendations</li>
                      <li>Free Analysis of your profile</li>
                    </ul>
                  </div>
              </div>
              
          </div>
        </div>
	</form>
    </div>
    <footer>© All rights reserved. Made by Adapt & Learn Inc</footer>
    <script src="b2c/js/jquery-3.5.1.min.js"></script>
    <script src="b2c/js/popper.min.js"></script>
    <script src="b2c/js/bootstrap.min.js"></script>
    
  </body>
  
  <script type="text/javascript" src="scripts/custom.js"></script>
<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
<script type="text/javascript" src="scripts/pnotify.nonblock.js"></script>
<script type="text/javascript" src="scripts/pnotify.buttons.js"></script>
<script>
	var validateEmail = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
	var randomNumber = Math.floor(Math.random() * (6 - 1 + 1)) + 1;

	

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
