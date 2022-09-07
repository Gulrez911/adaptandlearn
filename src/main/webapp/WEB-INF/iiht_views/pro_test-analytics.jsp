<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<html lang="en">

<head>
    <title>Quantpro</title>
    <link rel="shortcut icon" href="quaintpro/admin/assets/img/favicon.png" type="image/x-icon">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="quaintpro/admin/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="quaintpro/admin/assets/css/style.css" rel="stylesheet">
</head>

<body>

    <header>
        <div class="top_header">
            <div class="container">
                <div class="top_header_box">
                    <div class="logo">
                        <a href="javascript:void(0);">
                            <img src="quaintpro/admin/assets/img/admin_logo.png">
                        </a>
                    </div>
                    <div class="menu_bar navbar-dark">
                        <button class="navbar-toggler my-2" type="button" data-bs-toggle="collapse"
                            data-bs-target="#main_nav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                    </div>
                    <div class="profile_box">
                        <div class="setting_notification">
                            <div class="notification">
                                <a href="javascript:void(0)"><i class="fa-solid fa-bell"></i></a>
                            </div>
                            <div class="notification">
                                <a href="javascript:void(0)"><i class="fa-solid fa-gear"></i></a>
                            </div>
                            <div class="notification">
                                <div class="profile_dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                                        <div class="media">
                                            <img src="quaintpro/admin/assets/img/profile.png" alt="profile" class="rounded">
                                            <div class="media-body">
                                                <h4>Anubhav Shah</h4>
                                                <p>Mumbai, India</p>
                                            </div>
                                        </div>
                                    </a>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="login.html">Logout</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="buttom_footer">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-dark p-0">

                    <div class="collapse navbar-collapse justify-content-center lh-lg" id="main_nav">
                        <ul class="navbar-nav p-3 p-md-0">
                            <li class="nav-item"> <a class="nav-link" href="index.html">
                                    <img src="quaintpro/admin/assets/img/dashboard_active.png" class="active">
                                    <img src="quaintpro/admin/assets/img/dashboard.png" class="disactive">
                                    Dashboard</a> </li>
                            <li class="nav-item"> <a class="nav-link" href="question-bank.html">
                                    <img src="quaintpro/admin/assets/img/question_active.png" class="active">
                                    <img src="quaintpro/admin/assets/img/question.png" class="disactive"> Question Bank</a> </li>
                            <li class="nav-item"> <a class="nav-link" href="tests.html">
                                    <img src="quaintpro/admin/assets/img/test_active.png" class="active">
                                    <img src="quaintpro/admin/assets/img/test.png" class="disactive">Tests</a> </li>
                            <li class="nav-item dropdown ktm-mega-menu">
                                <a class="nav-link" href="#" data-bs-toggle="dropdown">
                                    <img src="quaintpro/admin/assets/img/more.png" class="disactive"> More</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </header>

    <section class="dashboard_sec">
        <div class="question_bank">
            <div class="container">
                <div class="dashboard_heading">
                    <div class="question_heading">
                        <h2>Test Analytics</h2>
                        <h3 class="mb-3">Java Test Part 1</h3>
                        <ul>
                            <li> <img src="quaintpro/admin/assets/img/probleam.png"> 20 Attempts</li>
                            <li> <img src="quaintpro/admin/assets/img/hr.png"> 1 hour 30 mins</li>
                            <li> <img src="quaintpro/admin/assets/img/condi.png"> 45 Candidates</li>
                            <li> <img src="quaintpro/admin/assets/img/point.png"> 100 Points</li>
                            <li> <img src="quaintpro/admin/assets/img/user1.png"> Ankita Raghuvanshi</li>
                        </ul>
                    </div>
                </div>

                <div class="question_bank_tab Test_Analytics_sec">

                    <div class="Test_Statistic_box">
                        <p>Test Statistic</p>
                        <div class="row">
                            <div class="col-md-4 mb-2">
                                <div class="condidate_test">
                                    <h3>45</h3>
                                    <p>Candidates in test</p>
                                </div>
                            </div>
                            <div class="col-md-4 mb-2">
                                <div class="condidate_test submi">
                                    <h3>346</h3>
                                    <p>Submission Count</p>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="condidate_test correct">
                                    <h3>215</h3>
                                    <p>Correct Submission Count</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="Score_Distribution_box">
                        <p>Score Distribution</p>
                        <div class="Score_graph">
                            <div id="chart"></div>
                        </div>
                    </div>
                  
                </div>
            </div>
        </div>
        </div>
    </section>



    <script src="quaintpro/admin/assets/js/jquery.slim.min.js"></script>
    <script src="quaintpro/admin/assets/js/bootstrap.bundle.min.js"></script>
    <script src="quaintpro/admin/assets/js/jquery-slider-min.js"></script>
    <script src="quaintpro/admin/assets/js/apexcharts.js"></script>
    <script src="quaintpro/admin/assets/js/fontawesome.min.js"></script>
    <script src="quaintpro/admin/assets/js/custom.js"></script>
</body>

</html>