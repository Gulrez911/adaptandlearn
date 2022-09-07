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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

		
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
		<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
        <script src="https://kit.fontawesome.com/dcf0f9173b.js" crossorigin="anonymous"></script>
		
		<link href="user_profile_j/css/styles.css" rel="stylesheet" type="text/css">
		<link href="user_profile_j/css/dashboard.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="user_profile_j/js/scripts.js"></script>
    </head>
    <body>
        <div class="master-container">
            <header class="post-login">
                <div class="container">
				<div class="logo">
                    <a href="#" class="my-auto">
                        <img src="user_profile_j/images/logo.jpg" alt="Yaksha Assessment Platform">
                    </a>
					</div>
					<div class="navmenu">
						<nav>
                        <ul>
                            
                           <!-- <li class="search my-auto">
                                <form action="" class="search-form">
                                    <input type="text" placeholder="search...">
                                    <button class="icon" type="button">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </form>
                                <div class="drop-menu-bg"></div>
                                <a href="#" class="tempclick">
                                    <i class="fa fa-search"></i>
                                </a>
                            </li> -->
                            
                            <li class="user-info my-auto active">
                                <span class="my-auto">${user.firstName}, ${user.lastName}</span>
                                <span class="thumbnail"></span>
                                <div class="drop-menu-bg"></div>
                                <ul class="user-navs drop-menu">
                                    <li>
                                        <a href="learnerlogoff">
                                            Log Out
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
					</div>
                    
                </div>
                <div class="submenu">
                    <div class="container">
                        <a href="learnerDashboardJ" >
                            Dashboard
                        </a>
                        <a href="showUserSessions" class="active">
                            Assessments
                        </a>
                        <a href="getAllRecommendationsForUser">
                            User Profile
                        </a>
                    </div>
                </div>
            </header>
            <section>
                <div class="container mytable">
                    <h3>All Sessions so far</h3>
				    <table class="table">
                        <thead>
                          <tr>
                            <th>No</th>
                            <th>Test Name</th>
							<th>Atempt</th>
							<th>Time taken</th>
                            <th>Percentage</th>
                            <th>Total Marks</th>
							<th>Total Marks Recieved</th>
							<th>Section Wise</th>
							<th>Status</th>
							<th>Date & Time</th>
                          </tr>
                        </thead>
                        <tbody>
						<c:forEach  items="${sessions}" var="sess" varStatus="loop"> 
                          <tr>
                            <td>${loop.count}  </td>
                            <td>${sess.testName} </td>
                            <td>${sess.attempt} </td>
	<td>${(sess.timeTakenInMimnutes == null || sess.timeTakenInMimnutes == 0)?"NA":""+sess.timeTakenInMimnutes} </td>
                            <td>${sess.percentageMarksRecieved} </td>
							<td>${sess.totalMarks} </td>
							<td>${sess.totalMarksRecieved} </td>
							<td>${sess.sectionResults} </td>
							<td>${sess.pass == true?"Yes":"No"} </td>
							<td>${sess.formattedDt} </td>
                          </tr>
                          
                        </tbody>
						</c:forEach>
                    </table>
				</div>
		
            
            </section>
            <footer>
                <div class="container">
                    <a >
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
         <div class="dialog-modal-bg"></div>
        <div class="dialog-modal">
	   <div id="moduleItemsContent">

	   </div>
        </div>

    </body>
  <script>
	

		function notify(msg){
			bootbox.alert({
				message: msg,
				size: 'large',
				backdrop: true
			});
		}	

	</script>


    <script src="user_profile_j/js/scripts.js"></script>
</html>