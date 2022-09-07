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
    <title>Assessment - Popup </title>

    <!-- Bootstrap core CSS -->
    <link href="b2c/css/bootstrap.min.css" rel="stylesheet" />
    <link href="b2c/css/assessment.css" rel="stylesheet" />

    <!-- Google Font -->
      <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;1,200;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">
  </head>
  <body> 
    <header>
      <div class="logo"> <img src="b2c/images/logo.png" alt="LOGO"> </div>
      <h1>Assessment Page Link- <span class="font-weight400"> Add Your Points </span>
        <div class="sub-text">(Testing may lead to failure, but failure leads to understanding.)</div>
      </h1>
    </header>
    <div class="container">
        <div class="row mb-4">
          <div class="col-md-7">
            <div class="form-wrapper assessment-points h-auto pb-1">
                <div class="evaluation-box">
                      <div> Try Assessment MCQ 1</div>
                      <div><img class="align-baseline mr-3" src="b2c/images/arrow.svg" width="20"></div>
                      <div> Link Target Area - Python</div>
                </div>
                <div class="evaluation-box">
                      <div> Try Assessment MCQ 2</div>
                      <div><img class="align-baseline mr-3" src="b2c/images/arrow.svg" width="20"></div>
                      <div> Link- Target Area - Jara</div>
                </div>
                <div class="evaluation-box">
                    <div> Try Assessment MCQ 3</div>
                    <div><img class="align-baseline mr-3" src="b2c/images/arrow.svg" width="20"></div>
                    <div> Link Target Area - Jara Script</div>
                </div>
                  <div class="evaluation-box">
                      <div> Try Assessment MCQ 4</div>
                      <div><img class="align-baseline mr-3" src="b2c/images/arrow.svg" width="20"></div>
                      <div> Link - Target Area - Bootstrap</div>
                </div>
                <div class="evaluation-box">
                    <div> Try Assessment MCQ 5</div>
                    <div><img class="align-baseline mr-3" src="b2c/images/arrow.svg" width="20"></div>
                    <div> Link - Target Area - CSS</div>
                </div>
                <p class="font20 mt-5">Also Try an adaptive assessment below -</p>
                <div class="evaluation-box">
                    <div> Try Assessment MCQ 6</div>
                    <div><img class="align-baseline mr-3" src="b2c/images/arrow.svg" width="20"></div>
                    <div> Target Area - Python, Java,
Java Script, Bootstrap, CSS</div>
                </div>
                <p class="font18">Adaptive testing involves modifying the assessment on the fly to take
                account of the candidate's ability.</p>

                <p class="font18">In most cases, this involves choosing questions for candidates based
                on the score they got on previous sections in order to maximise the
                precision in measuring their level.</p>

            </div>
          </div>
          <div class="col-md-5">
              <div class="instructions-text popup-text">
                  <div>
                    <div class="heading mt-5"><img src="b2c/images/education.png" width="18" class="align-baseline mr-2"> Further</div>
                    <ul>
                      <li> 
                          You can trigger your journey by-
                          <div class="my-3">
                            <span class="d-block mb-2">- Preparing your self in your selected topics</span>
                            <span class="d-block">- Take assessments</span>
                          </div>
                      </li>
                      <li>We will show your strengths & area of improvement in the Result Page</li>
                      <li>We will recommend vidos that we think can help
you in preparing in areas that you need improvement</li>
                    </ul>
                  </div>
                  <div>
                    <div class="heading mt-5"> <img src="b2c/images/guideline.png" width="18" class="align-baseline mr-2"> Guidelines </div>
                    <ul>
                      <li>You can give assessments as many times as you want.</li>
                      <li>We will provide you a login credentials that will allow
you to login and see your history of till assessments.
You will also see your skill profile listing your strengths
& 	improvement areas based on your past scores.</li>
                      <li>We add new content pretty regularly. Doesnt matter,
if you are a school/college student or a job seeker or
an experienced professional seeking to retain the edge
by measuring your skills - this platform is for you.</li>
                    </ul>
                  </div>
              </div>
          </div>
        </div>
        <div class="text-center my-3 pointer"> <img src="b2c/images/back-button.png" width="40"> </div>
    </div>
    <footer>© All rights reserved. Made by Your Name</footer>
    <script src="b2c/js/jquery-3.5.1.min.js"></script>
    <script src="b2c/js/popper.min.js"></script>
    <script src="b2c/js/bootstrap.min.js"></script>
    
  </body>
</html>
