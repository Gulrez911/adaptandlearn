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
        <link href="https://fonts.googleapis.com/css?family=Segoe+UI" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="css/responsive.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
		
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
		<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
        <script src="https://kit.fontawesome.com/dcf0f9173b.js" crossorigin="anonymous"></script>
		
		<link href="user_profile_j/css/styles.css" rel="stylesheet" type="text/css">
		<link href="user_profile_j/css/dashboard.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="user_profile_j/js/scripts.js"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		 <script src="scripts/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.js"></script>
    </head>
    <body>
        <div class="master-container">
            <header>
                <div class="container">
		<div class="logo">
                    <a href="#">
                        <img src="user_profile_j/images/logo.jpg" alt="Yaksha Assessment Platform">
                    </a>
		</div>
                    <div class="navmenu">
			<nav>
                        <button class="btn btn-primary" type="button" onClick="javascript:notify('Registration for Retail User - Coming Soon!');">
                            sign up
                        </button>
			</nav>
                    </div>
                </div>
            </header>
            <section>
                <div class="container">
                    <div class="mx-auto login-form">
                         <form id="loginForm" method="post" modelAttribute="user" action="authenticateLearner">
                            <h2>
                                Login to <span>Yaksha</span>
                            </h2>
                            <div>
                                <form:input type="email" path="user.email" name="email" id="username" cssClass="form-control" required="true" placeholder="User"/>	
                            </div>
                            <div>
                                <form:password path="user.password" name="password" id="password" cssClass="form-control" required="true" placeholder="Password"/>
                            </div>
							<div>
                                <form:input  path="user.companyName" name="companyName" id="companyName" cssClass="form-control" required="true" placeholder="Company" disabled="true"/>
                                <form:hidden path="user.companyName" />
                            </div>
                            <div class="login-btn">
                               
                                <button class="btn btn-primary" type="submit" style="width:100%" >
                                    LOGIN
                                </button>
                            </div>
                            <div class="social-login">
                                <hr>
                                <div>
                                    Login with social accounts
                                </div>
                                <ul>
                                    <li>
                                        <a href="javascript:notify('Retail User Login - Coming Soon!');" class="social linkedin">

                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:notify('Retail User Login - Coming Soon!');" class="social google">

                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:notify('Retail User Login - Coming Soon!');" class="social facebook">

                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:notify('Retail User Login - Coming Soon!');" class="social github">

                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:notify('Retail User Login - Coming Soon!');" class="social twitter">

                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
            <footer>
                <div class="container">
                    <a href="#">
                        &copy; Copyright 2020
                    </a>
                    <div>
                        <a href="#">
                            Terms and Conditions
                        </a>
                        <a href="#">
                            Privacy Policy
                        </a>
                        <a href="#">
                            Contact us
                        </a>
                    </div>
                </div>
            </footer>
        </div>
    </body>
	
	<script>
		function login(){
			document.getElementById('loginForm').submit();
		}
		
		
		function notify(msg){
			bootbox.alert({
				message: msg,
				size: 'large',
				backdrop: true
			});
		}	
	</script>
	
	<c:if test="${msgtype != null}">
		 <script>
	 notify('${message}');
      </script>
</c:if>

</html>