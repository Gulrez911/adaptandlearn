<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.assessment.data.*, java.text.*, java.util.*,com.assessment.web.dto.*, org.apache.commons.lang3.StringEscapeUtils"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adaptive Assessment for ${test.adaptiveAssessmentName} - ${currentLevelSkills}</title>

    <!-- Bootstrap core CSS -->
    <link href="adaptive/css/bootstrap.min.css" rel="stylesheet" />
    <link href="adaptive/css/style.css" rel="stylesheet" />

    <!-- Google Font -->
    <link
        href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100;0,200;0,400;0,600;0,700;0,800;1,300&display=swap"
        rel="stylesheet">
		
		
</head>

<body>
    <div class="fluid-container">
        <div class="row">
            <div class="col-md-9 d-flex">
                <div class="question gray-bg shadow-custom w-100 position-relative">
                    <div class="title">${test.adaptiveAssessmentName}. ${currentLevel} - ${currentLevelSkills} </div>
    <div>
                    <form:form id="testForm" name="testForm" method="POST" 	modelAttribute="currentQuestion" action="nextAdaptive">
					<div class="mb-5">
                        <div class="qestion-option">
                            <span class="q-number">${sequence}</span> ${responseDto.instanceDTO.questionMapperInstance.questionText}</span>
                        </div>
                        <div class="px-5 options">
                            <!-- <div class="custom-control custom-radio mx-5 mb-4">
                                <input type="radio" id="customRadio1" name="customRadio" class="custom-control-input">
                                <label class="custom-control-label" for="customRadio1">75</label>
                            </div> -->
                           
							
							<c:forEach  items="${choices}" var="choice" varStatus="loop">   
							<div class="custom-control custom-radio mx-5 mb-4">
                                
								<form:radiobutton path="radioAnswer" id="choice${loop.index+1}" value="choice${loop.index+1}" class="custom-control-input" />
                                <label class="custom-control-label" for="choice${loop.index+1}">${choice}</label>
                            </div>
							 </c:forEach>
							 
                        </div>
                    </div>

                    <div class="p-4 d-flex justify-content-between btn-footer">
                        <!--<button type="button" class="btn btn-primary font-weight-bold font18"> <img
                                src="adaptive/images/back.png" alt="back"> Back</button> -->
                        <button type="submit" class="btn btn-primary font-weight-bold font18"  style="width: 165px;align-self: center;margin-left: 40%;">Next <img
                                src="adaptive/images/next.png" alt="Next"></button>
                    </div>
					</form:form>
</div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="shadow-custom bg-white p-3 d-flex justify-content-center rounded border mb-4">
                    <div class="logo"> <img src="adaptive/images/logo.png" alt="logo"></div>
                </div>

                <div class="shadow-custom bg-white p-3 d-flex justify-content-center rounded border mb-4">
                    <div> <img src="adaptive/images/graph.png" alt="graph" width="220" /></div>
                </div>

                <div class="shadow-custom bg-white p-3  rounded border">
                    <label for="level">Result as per Level</label>
                    <div class="level-bars">
                        <div class="mb-3">
                            <label for="level">Level 1</label>
                            <div class="progress" style="height: 8px;">
                                <div class="progress-bar bg-danger" role="progressbar" style="width: ${level1Score}%"
                                    aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="level">Level 2</label>
                            <div class="progress" style="height: 8px;">
                                <div class="progress-bar" role="progressbar" style="width: ${level2Score}%"
                                    aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="level">Level 3</label>
                            <div class="progress" style="height: 8px;">
                                <div class="progress-bar bg-warning" role="progressbar" style="width: ${level3Score}%"
                                    aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="level">Level 4</label>
                            <div class="progress" style="height: 8px;">
                                <div class="progress-bar bg-success" role="progressbar" style="width: ${level4Score}%"
                                    aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="level">Level 5</label>
                            <div class="progress" style="height: 8px;">
                                <div class="progress-bar bg-success" role="progressbar" style="width: ${level5Score}%"
                                    aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <div class="row level my-4 px-2">

            <div class="col-md-6 px-1">
                <div class="card border-gradient border-gradient-purple">
                    <h5 class="card-header"> Assessment Statistics</h5>
                    <div class="card-body">
                        <h6 class="card-title">Progressions & Regressions</h6>
                        <p class="card-text">A progression is an event when you transit from the current level to a slightly advanced level of answering questions. A regression is quite the opposite. The adaptive system asks questions, and after some time, will transit you to advanced or preliminary level depending upon your performance in the current level. </p>
						<h5 style="color:red"><b> You have ${progressions} progressions and ${regressions} regressions for the current assessment.</b></h5>
                    </div>
                </div>
            </div>

            <div class="col-md-6 px-1">
                <div class="card border-gradient border-gradient-purple">
                    <h5 class="card-header"> Recommendations (${currentLevel}) </h5>
                    <div class="card-body">
                        <h6 class="card-title">Quick Summary</h6>
                        <p class="card-text">
						<c:choose>
							<c:when test="${norecomm != null && norecomm == true}">
								You need to complete atleast a single level to start getting system recommendations.
							</c:when>    
							<c:otherwise>
								<table class="table table-sm">
									<thead>
										<tr>
											<th>Row</th>
											<th>Skill</th>
											<th>Score %</th>
											<th>Recommendation</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach  items="${recommendations}" var="rec" varStatus="loop">   
							
										<tr>
											<td>${loop.index+1}</td>
											<td>${rec.allQualifiers}</td>
											<td>${rec.percentage}</td>
											<td>${rec.overAll}</td>
											
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:otherwise>
						</c:choose>
						
						</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="adaptive/js/jquery-3.5.1.min.js"></script>
    <script src="adaptive/js/popper.min.js"></script>
    <script src="adaptive/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
		
		 <c:if test="${msgtype != null}">
		<script>
			var notification = 'Information';
			$(function() {
				    Swal.fire(
			       '${msgtype}',
			       '${message}',
			       '${icon}'
			    )
			});
		</script>
	</c:if>
	
</body>

</html>