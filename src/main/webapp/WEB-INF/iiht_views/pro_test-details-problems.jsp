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
                            <li class="nav-item"> <a class="nav-link" href="proindex">
                                    <img src="quaintpro/admin/assets/img/dashboard_active.png" class="active">
                                    <img src="quaintpro/admin/assets/img/dashboard.png" class="disactive">
                                    Dashboard</a> </li>
                            <li class="nav-item"> <a class="nav-link" href="question-bank.html">
                                    <img src="quaintpro/admin/assets/img/question_active.png" class="active">
                                    <img src="quaintpro/admin/assets/img/question.png" class="disactive"> Question Bank</a> </li>
                            <li class="nav-item"> <a class="nav-link active" href="tests.html">
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
        <div class="question_bank problem_page_sec">
            <div class="problem_page">
                <div class="container">
                    <div class="dashboard_heading">
                        <div class="question_heading">
                            <h3>New test lorem ipsum dolor <button class="open close op">Open</button></h3>
                            <ul>
                                <li> <img src="quaintpro/admin/assets/img/machine.png"> Machine Learning / AI</li>
                                <li> <img src="quaintpro/admin/assets/img/easy.png"> Easy</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-lg-8">
                        <div class="Problem_Details question_bank_tab">
                            <div class="tab-content mt-1">
                                <div class="Problems_tab">
                                    <div class="general">
                                        <h3>Problem Details</h3>
                                        <small>Problem Name*</small>
                                        <div class="general_form mt-1">
                                            <div class="general_field">
                                                <div class="form-group">
                                                    <label for="Test Name">New test lorem ipsum dolor <br><small> A
                                                            descriptive name helps.</small></label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="general_form">
                                            <div class="row">

                                                <div class="col-md-4 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Category <br><small> Lorem ipsum
                                                                    dolor site amet</small></label>
                                                            <input type="text" class="form-control" id="Test Name"
                                                                placeholder="Lorem ipsum dolor">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-4 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Sub Category <br><small> Lorem ipsum
                                                                    dolor site amet</small></label>
                                                            <input type="text" class="form-control" id="Test Name"
                                                                placeholder="Lorem ipsum dolor">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-4 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Category of Sub Category <br><small>
                                                                    Lorem ipsum dolor site amet</small></label>
                                                            <input type="text" class="form-control" id="Test Name"
                                                                placeholder="Lorem ipsum dolor">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-12 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Public access URL <br><small> Anyone
                                                                    with this URL would be able to take this
                                                                    test.</small></label>
                                                            <input type="text" class="form-control" id="Test Name"
                                                                placeholder="http://t.dos.lc/java-test-part 1">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Expected solving time <small>(in
                                                                    minutes)*</small> <br><small> Time should normally
                                                                    be required to solve this problem?</small></label>
                                                            <input type="text" class="form-control" id="Test Name"
                                                                placeholder="12 minutes">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">No. of Attempts<br><small> No. of
                                                                    Attempts</small></label>
                                                            <input type="text" class="form-control" id="Test Name"
                                                                placeholder="5 attempts only">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">&nbsp;<br><small>
                                                                    &nbsp;</small></label>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <div class="general_field recent_test">
                                                                        <div
                                                                            class="recent_test_body filters_box border-0 p-0">
                                                                            <div class="filter_checkbox mb-0">
                                                                                <div class="form-group">
                                                                                    <input type="checkbox"
                                                                                        id="Fullstack">
                                                                                    <label for="Fullstack">Fullstack
                                                                                        Test </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="general_field recent_test">
                                                                        <div
                                                                            class="recent_test_body filters_box border-0 p-0">
                                                                            <div class="filter_checkbox mb-0">
                                                                                <div class="form-group">
                                                                                    <input type="checkbox" id="Shuffle">
                                                                                    <label for="Shuffle">Shuffle
                                                                                        Test</label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Select test type<br><small> Lorem
                                                                    ipsum dolor site amet</small></label>
                                                            <input type="text" class="form-control" id="Test Name"
                                                                placeholder="Composite Test">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Test launguage<br><small> Lorem ipsum
                                                                    dolor site amet</small></label>
                                                            <input type="text" class="form-control" id="Test Name"
                                                                placeholder="English">
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="col-md-6 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Pass Percentage<br><small> Lorem
                                                                    ipsum dolor site amet</small></label>
                                                            <input type="text" class="form-control" id="Test Name"
                                                                placeholder="65%">
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="col-md-12 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Instructions<br><small> Be as
                                                                    decriptive as possible, but no more.</small></label>
                                                            <textarea class="form-control h-auto" rows="5"
                                                                id="comment"></textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-12 mb-4">
                                                    <div class="general_field recent_test">
                                                        <div class="recent_test_body filters_box border-0 p-0">
                                                            <div class="filter_checkbox mb-0">
                                                                <div class="form-group">
                                                                    <p style="padding-inline-start:0px;font-weight: 500;
                                                                    font-size: 16px;color: #1B1B1B;">Protocol Setting
                                                                    </p>
                                                                    <input type="checkbox" id="ask">
                                                                    <label for="ask">Ask candidates to complete their
                                                                        eAssess profile </label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 col-lg-4">
                        <div class="Problem_Details question_bank_tab border-0 p-0">

                            <div class="recent_test mb-4">
                                <div class="recent_test_body filters_box">
                                    <div class="conducted">
                                        <h4>Exam Conducted by</h4>
                                        <p>rajeshwari_kamat@abc.com</p>
                                    </div>
                                </div>
                            </div>


                            <div class="recent_test mb-4">
                                <div class="recent_test_body filters_box">
                                    <div class="conducted">
                                        <h4>Select Skills</h4>
                                        <ul>
                                            <li><a href="javascript:void(0);">General-Basic</a></li>
                                            <li><a href="javascript:void(0);">Aptitude-Basic</a></li>
                                            <li><a href="javascript:void(0);">English Communication-Basic</a></li>
                                            <li><a href="javascript:void(0);">Hydraulics-Basic</a></li>
                                            <li><a href="javascript:void(0);">Pneumatics-Basic</a></li>
                                            <li><a href="javascript:void(0);">Java-Basic</a></li>
                                            <li><a href="javascript:void(0);">Python-Basic</a></li>
                                            <li><a href="javascript:void(0);">R-Basic</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>


                            <div class="recent_test mb-4">
                                <div class="recent_test_body filters_box">
                                    <div class="question_filter">
                                        <a href="javascript:void(0);">
                                            <div class="ques_type">
                                                <p>MCQ-Objective</p>
                                                <img src="quaintpro/admin/assets/img/check_box_active.png">
                                            </div>
                                        </a>
                                    </div>

                                    <div class="question_filter">
                                        <a href="javascript:void(0);">
                                            <div class="ques_type">
                                                <p>MCQ-Fill in Blanks</p>
                                                <img src="quaintpro/admin/assets/img/check_box.png">
                                            </div>
                                        </a>
                                    </div>

                                    <div class="question_filter">
                                        <a href="javascript:void(0);">
                                            <div class="ques_type">
                                                <p>MCQ-Match the Following</p>
                                                <img src="quaintpro/admin/assets/img/check_box.png">
                                            </div>
                                        </a>
                                    </div>

                                    <div class="question_filter">
                                        <a href="javascript:void(0);">
                                            <div class="ques_type">
                                                <p>MCQ-Image Based</p>
                                                <img src="quaintpro/admin/assets/img/check_box.png">
                                            </div>
                                        </a>
                                    </div>

                                    <div class="question_filter">
                                        <a href="javascript:void(0);">
                                            <div class="ques_type">
                                                <p>MCQ-Video Based</p>
                                                <img src="quaintpro/admin/assets/img/check_box.png">
                                            </div>
                                        </a>
                                    </div>

                                    <div class="question_filter mb-0">
                                        <a href="javascript:void(0);">
                                            <div class="ques_type">
                                                <p>MCQ-Subjective</p>
                                                <img src="quaintpro/admin/assets/img/check_box.png">
                                            </div>
                                        </a>
                                    </div>




                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 mb-1 mt-4">
                        <div class="general_field_btn text-center">
                            <button class="save">Save Details</button>
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