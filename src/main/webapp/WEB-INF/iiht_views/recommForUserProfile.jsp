<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*,com.assessment.web.dto.*" %>

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>eAssess</title>
      	<link href="images/E-assess_E.png" rel="shortcut icon">         
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		
		
		<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"></script>
		
		
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
                        <a href="showUserSessions" >
                            Assessments
                        </a>
                        <a href="getAllRecommendationsForUser" class="active">
                            User Profile
                        </a>
                    </div>
                </div>
            </header>
            <section>
                <div class="container mytable">
				<canvas id="bar-chart" width="500" height="250"></canvas>
				
                   <%
				   List<SkillRecommDto> skillRecommDtos = (List<SkillRecommDto>)request.getAttribute("skillRecommDtos");
				   StringBuffer sb = new StringBuffer();
					sb.append("[");
					
					StringBuffer sb2 = new StringBuffer();
					sb2.append("[");
					
					StringBuffer sb3 = new StringBuffer();
					sb3.append("[");
					for(int i=0; i<skillRecommDtos.size(); i++){
						SkillRecommDto dto = (SkillRecommDto) skillRecommDtos.get(i);
						sb.append("\"").append(dto.getSkillAlias()).append("\"");
						
						sb2.append("\"").append(dto.getColor()).append("\"");
						
						sb3.append("\"").append(dto.getScore()).append("\"");
						
						if(i+1 < skillRecommDtos.size()){
							sb.append(",");
							sb2.append(",");
							sb3.append(",");
						}
						
						
					}
					sb.append("]");
					sb2.append("]");
					sb3.append("]");
					String javascriptSkillLabel = sb.toString();
					String javascriptSkillColor = sb2.toString();
					String javascriptSkillScore = sb3.toString();
					
				   
				   
				   %>
				   <script>
				   var labelArray = <%= javascriptSkillLabel %>;
				   var colorArray = <%= javascriptSkillColor %>;
				   var scoreArray = <%= javascriptSkillScore %>;
				   
				   // Bar chart
					new Chart(document.getElementById("bar-chart"), {
						type: 'bar',
						data: {
						  labels: labelArray,
						  datasets: [
							{
							  label: "Skill Score",
							  backgroundColor: colorArray,
							  data: scoreArray
							}
						  ]
						},
						options: {
						  legend: { display: true },
						  title: {
							display: true,
							text: 'Skill Proficiency'
						  }
						}
					});
				   
				   </script>
				   
				   <h3>Skill Glossary</h3>
				    <table class="table">
                        <thead>
                          <tr>
                            <th>No</th>
                            <th>Skill Alias</th>
							<th>Skill Name</th>
							
                          </tr>
                        </thead>
                        <tbody>
						<c:forEach  items="${skillRecommDtos}" var="skill" varStatus="loop"> 
                          <tr style="background-color:${skill.color}">
                            <td>${loop.count}  </td>
                            <td>${skill.skillAlias} </td>
                            <td>${skill.skill} </td>
	
                          </tr>
                          
                        </tbody>
						</c:forEach>
                    </table>
					
					
					<c:forEach  items="${dtos}" var="dto" varStatus="loop"> 
					<h3>Skill Observations for Test -  ${dto.testName} </h3>
						
				    <table class="table">
                        <thead>
                          <tr>
                            <th>No</th>
                            <th>Skill</th>
							<th>Overview</th>
							<th>Percentage</th>
                          </tr>
                        </thead>
                        <tbody>
						<c:forEach  items="${dto.skills}" var="skill" varStatus="loop"> 
                          <tr>
                            <td>${loop.count}  </td>
                            <td>${skill.allQualifiers} </td>
                            <td>${skill.overAll} </td>
							 <td>${skill.percentage} </td>
                          </tr>
                          </c:forEach>
                        </tbody>
						
                    </table>
				    </c:forEach>
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