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
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Results for ${test.adaptiveAssessmentName}</title>
 <link href="adaptive/css/style.css" rel="stylesheet" />
    <!-- Bootstrap core CSS -->
    <link href="adaptive/css/bootstrap.min.css" rel="stylesheet" />
    <link href="adaptive/css/css-circular-prog-bar.css" rel="stylesheet" />
   

    <!-- Google Font -->
    <link
      href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100;0,200;0,400;0,600;0,700;0,800;1,300&display=swap"
      rel="stylesheet"
    />

<style>
.logo {
    display: flex;
    justify-content: flex-end;
}

.final-result h1, .areaOfImprovement h1, .result h1 {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    font-size: 35px;
    color: #4f4f4f;
    font-weight: 300;
}

.level-wrapper {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    grid-gap: 52px;
    margin-top: 5rem;
}

.level-col {
    width: 200px;
    height: 180px;
    background-color: #ffffff;
    border-radius: 0.75rem;
    box-shadow: -7px -6px 6px rgb(0 0 0 / 8%);
    padding: 1rem;
    text-align: center;
    position: relative;
}

.level-arrow, .level-arrow-2 {
    position: absolute;
    right: -53px;
    top: 50%;
    transform: translateY(-50%);
}

.progress, .progress-bar {
    border-radius: 2rem;
}

.final-result h2 {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    font-size: 28px;
    color: #4f4f4f;
    font-weight: 400;
    margin: 18px 0;
}

.level-col p {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    font-size: 20px;
}

</style>
  </head>

  <body>
   
    <div class="container">
 
      <div class="final-result">
		<div class="logo" style="width:110%"><img src="adaptive/images/logo2.png" alt="logo" /></div>
         
        <h1 class="text-center mb-5">
          <img
            class="mr-2 align-baseline"
            src="adaptive/images/final-result.svg"
            alt="Result"
            width="32px"
          />
         Adaptive Assessment ${test.adaptiveAssessmentName} Results
        </h1>
        <div class="level-wrapper" style="margin-top: 0rem;">
		
		<c:forEach  items="${alllevels}" var="lev" varStatus="loop">   
          <div class="level-col">
            <span class="level-arrow">
              <img src="adaptive/images/arrow.svg" alt="arrow" />
            </span>
            <div class="progress" style="height: 5px">
              <div
                class="progress-bar bg-warning"
                role="progressbar"
                style="width: ${lev.averageScore}%"
                aria-valuenow="${lev.averageScore}"
                aria-valuemin="0"
                aria-valuemax="100"
              ></div>
            </div>
            <h2>${lev.level.level}</h2>
            <p style="color:${(lev.pass != null && lev.pass ==true)?'green':'red'}">${lev.skills}</p>
          </div>
         </c:forEach>

          <div class="pass">
            <div class="progress-circle over50 p78">
            <span>  ${instance.averageScore} ${(instance.complete == true &&  instance.currentLevel.level.equals("LEVEL5")) ?"Pass":"Fail"}</span>
              <div class="left-half-clipper">
                <div class="first50-bar"></div>
                <div class="value-bar"></div>
              </div>
            </div>
          </div>

         
        </div>
      </div>
    </div>

    <div class="areaOfImprovement my-5">
      <div class="container">
        <h1 class="text-center mb-5">
          <img
            class="mr-2 align-baseline"
            src="adaptive/images/Improve-icon.svg"
            alt="Improvement"
            width="32px"
          />
          Areas of Improvement
        </h1>
		
        <div class="d-flex justify-content-between">
		  <c:forEach  items="${improvements}" var="imp" varStatus="loop">
          <div class="ImprovementBox">
            <div class="Improvment-header">${imp.skillsAssociated}- ${imp.assessmentLevel}</div>
            <div class="Improvment-body">
              ${imp.message}
            </div>
            <div class="Improvment-footer">
              <a href="${imp.recommendedcourse}" target="_blank">
                <img
                  class="mr-2"
                  src="adaptive/images/course-link.svg"
                  alt="Course"
                  width="32px"
                />
                Click here</a
              >
              <a href="${imp.recommendedAssessment}" target="_blank">
                <img
                  class="mr-2"
                  src="adaptive/images/assesment-link.svg"
                  alt="Course"
                  width="32px"
                />
                Click here</a
              >
            </div>
          </div>
		  </c:forEach>
          
        </div>
      </div>
    </div>

    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-10">
          <div class="result">
            <h1 class="text-center mb-4">
              <img
                class="mr-2 align-baseline"
                src="adaptive/images/level-icon.svg"
                alt="Improvement"
                width="32px"
              />
              Result as per Level
            </h1>
            <div class="accordion" id="accordionExample">
			
			<c:forEach  items="${levels}" var="lev" varStatus="loop">
              <div class="card" style="width: 123%;margin-left: -100px;">
                <div class="card-header" id="headingOne">
                  <div class="row no-gutters">
                    <div class="col-md-7">
                      <h6 class="mb-0">
                        <img src="adaptive/images/level.svg" alt="Level" /> ${lev.level.level}
                      </h6>
                      <div class="progress my-2" style="height: 10px">
                        <div
                          class="progress-bar bg-success"
                          role="progressbar"
                          style="width: ${lev.averageScore}%"
                          aria-valuenow="${lev.averageScore}"
                          aria-valuemin="0"
                          aria-valuemax="100"
                        ></div>
                      </div>
                      <span>100%</span>
                    </div>
                    <div
                      class="
                        col-md-4
                        d-flex
                        justify-content-end
                        align-items-center
                      "
                    >
                      <span class="text-center font13">
                        Attempt <br />
                        <span class="font18">${lev.attempt}</span></span
                      >
                    </div>
                    <div
                      class="
                        col-md-1
                        d-flex
                        justify-content-end
                        align-items-center
                      "
                    >
                      <button
                        class="btn btn-link btn-block text-right pr-0"
                        type="button"
                        data-toggle="collapse"
                        data-target="#collapse${lev.id}"
                        aria-expanded="true"
                        aria-controls="collapse${lev.id}"
                      >
                        more...
                      </button>
                    </div>
                  </div>
                </div>

                <div
                  id="collapse${lev.id}"
                  class="collapse"
                  aria-labelledby="headingOne"
                  data-parent="#accordionExample"
                >
                  <div class="card-body pt-0">
                    <h2 class="text-center">Overall Score - ${lev.averageScore}%</h2>
                    <div class="row justify-content-center">
                      <div class="col-md-10">
                        <table class="table">
                          <tbody>
                            <tr>
                              <td width="32%">Level 1</td>
                              <td width="32%">Skills</td>
                              <td width="32%">Recommendations</td>
                            </tr>
							<c:forEach  items="${lev.recommendations}" var="rec" varStatus="loop">  
                            <tr>
                              <td>${lev.level.level}</td>
                              <td>${rec.allQualifiers}</td>
                              <td>${rec.overAll}</td>
                            </tr>
                            </c:forEach>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
			  </c:forEach>
             
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
