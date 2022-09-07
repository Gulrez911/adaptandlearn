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
                            <li class="nav-item"> <a class="nav-link  " href="question-bank.html">
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
        <div class="question_bank">
            <div class="container">
                <div class="dashboard_heading">
                    <div class="question_heading">
                        <h3>Java Test Part 1 <button class="open close">Close</button></h3>
                        <ul>
                            <li> <img src="quaintpro/admin/assets/img/probleam.png"> 20 Attempts</li>
                            <li> <img src="quaintpro/admin/assets/img/hr.png"> 1 hour 30 mins</li>
                            <li> <img src="quaintpro/admin/assets/img/point.png"> 100 Points</li>
                        </ul>
                    </div>
                    <div class="create_test">
                        <button class="test mr-2"><img src="quaintpro/admin/assets/img/test_con.png"> Try This Test</button>
                        <button class="test" onclick="location.href = 'invite-condidate.html';"><img src="quaintpro/admin/assets/img/condidate.png"> Invite Candidate</button>
                        
                    </div>
                </div>

                <div class="question_bank_tab">
                   
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active" id="Problems-tab" data-bs-toggle="tab" data-bs-target="#Problems" type="button"
                                role="tab" aria-controls="Problems" aria-selected="true">Problems</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="GeneralSettings-tab" data-bs-toggle="tab" data-bs-target="#GeneralSettings" type="button"
                                role="tab" aria-controls="GeneralSettings" aria-selected="false">General Settings</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="AdvancedSettings-tab" data-bs-toggle="tab" data-bs-target="#AdvancedSettings" type="button"
                                role="tab" aria-controls="AdvancedSettings" aria-selected="false">Advanced Settings</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="Candidates-tab" data-bs-toggle="tab" data-bs-target="#Candidates" type="button"
                                role="tab" aria-controls="Candidates" aria-selected="false">Candidates</button>
                        </li>
                    </ul>

                    <div class="tab-content mt-4" id="myTabContent">
                      
                        <div class="tab-pane fade show active" id="Problems" role="tabpanel" aria-labelledby="Problems-tab">
                            <div class="Problems_tab">
                                <div class="section_1 mb-4">
                                    <h3>Section 1</h3>
                                    <div class="info">
                                        <p><img src="quaintpro/admin/assets/img/info_test.png" class="mr-2"> Using 2 of 2 problem in this section</p>
                                    </div>
                                    <div class="test_sub">
                                        <h3>Java Code Test</h3>
                                        <ul>
                                            <li> <img src="quaintpro/admin/assets/img/probleam.png"> Code</li>
                                            <li> <img src="quaintpro/admin/assets/img/hr.png"> Easy</li>
                                            <li> <img src="quaintpro/admin/assets/img/point.png"> 70 Points</li>
                                        </ul>
                                    </div>
                                    <div class="test_sub pb-0 border-0">
                                        <h3>Java Code Test</h3>
                                        <ul>
                                            <li> <img src="quaintpro/admin/assets/img/probleam.png"> Code</li>
                                            <li> <img src="quaintpro/admin/assets/img/hr.png"> Easy</li>
                                            <li> <img src="quaintpro/admin/assets/img/point.png"> 70 Points</li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="section_1">
                                    <h3>Section 2</h3>
                                    <div class="info">
                                        <p><img src="quaintpro/admin/assets/img/info_test.png" class="mr-2"> Using 2 of 2 problem in this section</p>
                                    </div>
                                    <div class="test_sub">
                                        <h3>Java Code Test</h3>
                                        <ul>
                                            <li> <img src="quaintpro/admin/assets/img/probleam.png"> Code</li>
                                            <li> <img src="quaintpro/admin/assets/img/hr.png"> Easy</li>
                                            <li> <img src="quaintpro/admin/assets/img/point.png"> 70 Points</li>
                                        </ul>
                                    </div>
                                    <div class="test_sub pb-0 border-0">
                                        <h3>Java Code Test</h3>
                                        <ul>
                                            <li> <img src="quaintpro/admin/assets/img/probleam.png"> Code</li>
                                            <li> <img src="quaintpro/admin/assets/img/hr.png"> Easy</li>
                                            <li> <img src="quaintpro/admin/assets/img/point.png"> 70 Points</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="tab-pane fade" id="GeneralSettings" role="tabpanel" aria-labelledby="GeneralSettings-tab">
                            <div class="Problems_tab">
                              <div class="general">
                                <h3>Details</h3>
                                <div class="general_form">
                                    <form>
                                        <div class="row">
                                            <div class="col-md-12 mb-4">
                                                <div class="general_field">
                                                    <div class="form-group">
                                                        <label for="Test Name">Test Name <br><small> Descriptive name helps.</small></label>
                                                        <input type="text" class="form-control"  id="Test Name">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <div class="general_field">
                                                    <div class="form-group">
                                                        <label for="Test Name">Duration (in minutes) <br><small> Must be between 1-180. The time candidate gets to finish this test.</small></label>
                                                        <input type="text" class="form-control"  id="Test Name">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <div class="general_field">
                                                    <div class="form-group">
                                                        <label for="Test Name">Cut-off Percentage <br><small> Candidates scoring more than this would be marked as Passed. </small></label>
                                                        <input type="text" class="form-control"  id="Test Name">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12 mb-4">
                                                <div class="general_field">
                                                    <div class="form-group">
                                                        <label for="Test Name">Instructions <br><small> Be as decriptive as possible, but no more. This will be shown on the test’s front page. </small></label>
                                                        <textarea class="form-control" rows="5" id="comment"></textarea>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-12 mb-4">
                                                <div class="general_field">
                                                    <div class="form-group">
                                                        <label for="Test Name">Difficulty Level <br><small> Proper difficulty level helps categorizing tests.</small></label>
                                                       
                                                        <div class="form-check">
                                                            <input type="radio" id="test1" name="radio-group"> <label for="test1">Easy</label>
                                                          </div>

                                                          <div class="form-check">
                                                            <input type="radio" id="test2" name="radio-group"> <label for="test2">Medium</label>
                                                          </div>

                                                          <div class="form-check">
                                                            <input type="radio" id="test3" name="radio-group"> <label for="test3">Hard</label>
                                                          </div>
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="col-md-12 mb-4">
                                                <div class="general_field">
                                                    <div class="form-group">
                                                        <label for="Test Name">Tag <br><small> Tags let you organize your tests better. Add theme based or campaign tags.</small></label>
                                                        <input type="text" class="form-control"  id="Test Name">
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="col-md-12 mb-4">
                                                <div class="general_field_btn text-center">
                                                    <button class="save">Save Details</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                              </div>
                            </div>
                        </div>


                        <div class="tab-pane fade" id="AdvancedSettings" role="tabpanel" aria-labelledby="AdvancedSettings-tab">
                            <div class="Problems_tab">
                                <div class="section_1 mb-4 general border-0">
                                    <h3>Public Access Settings</h3>
                                    <div class="info">
                                        <p><img src="quaintpro/admin/assets/img/info_test.png" class="mr-2"> Create a publicly accessible link for this test, and share it with candidate. Optionally, you can password protect it. Anyone can take this test using this URL. </p>
                                    </div>

                                    <div class="general_form">
                                        <form>
                                            <div class="row">
                                                <div class="col-md-12 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Public access URL <br><small> Anyone with this URL would be able to take this test.</small></label>
                                                            <input type="text" class="form-control"  id="Test Name" placeholder="http://t.dos.lc/java-test-part 1">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-12 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Access Password <br><small> If this is set, anyone with the link needs to enter this pa                   ssword to access the test.    </small></label>
                                                            <input type="text" class="form-control"  id="Test Name" placeholder="password@1234">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-12 mt-2 mb-4">
                                                    <h3 class="mb-3">Candidate Data Setting</h3>
                                                    <div class="info">
                                                        <p><img src="quaintpro/admin/assets/img/info_test.png" class="mr-2">Customize what data you’re capturing from the candidates who
                                                            take this test. All this data will be part of the test report. If you’ve enabled eAssess profile completion,
                                                            you shall be able to see extra data about the candidate
                                                            from sources like GitHub and Stack Overflow. </p>
                                                    </div>
                                                </div>
                                                <div class="col-md-12 mb-4">
                                                    <div class="general_field recent_test">
                                                        <div class="recent_test_body filters_box border-0 pb-0">
                                                            <div class="filter_checkbox mb-0">
                                                                <div class="form-group">
                                                                    <input type="checkbox" id="ask">
                                                                    <label for="ask">Ask candidates to complete their eAssess profile </label>
                                                                </div>
                                                                <p><b>This will capture the following data:</b></p>
                                                                <ul>
                                                                    <li>Geographical information</li>
                                                                    <li>Verified phone number</li>
                                                                    <li>School and work info</li>
                                                                    <li>Social profiles</li>
                                                                    <li>Proficiency and activity metrics based on GitHub and Stack Overflow</li>
                                                                </ul>
                                                            </div>
                                                    
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-12 mb-4">
                                                    <div class="general_field recent_test">
                                                        <div class="recent_test_body filters_box border-0">
                                                            <div class="filter_checkbox">
                                                                <div class="form-group mb-1">
                                                                    <input type="checkbox" id="test_t1">
                                                                    <label for="test_t1">Ask candidates to submit custom data </label>
                                                                </div>
                                                            </div>
                                                    
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Custom Fields <br><small> Customeize fields/Questions to be asked.</small></label>
                                                            <select class="form-select">
                                                                <option>Fields Type</option>
                                                                <option>Fields Type 1</option>
                                                                <option>Fields Type 2</option>
                                                              </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">&ensp; <br><small>&ensp;</small></label>
                                                            <input type="text" class="form-control"  id="Test Name" placeholder="Fields name">
                                                            <small>A descriptive field helps</small>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-12 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Skills </label>
                                                            <div class="skill_active">
                                                                <ul>
                                                                    <li><a href="javascript:void(0);">Java</a></li>
                                                                    <li><a href="javascript:void(0);">Python</a></li>
                                                                    <li><a href="javascript:void(0);">Julia</a></li>
                                                                    <li><a href="javascript:void(0);">Lorem ipsum</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-12 mb-4">
                                                    <h3 class="mb-3">Proctoring Settings</h3>
                                                    <div class="general_field recent_test">
                                                        <div class="recent_test_body filters_box border-0 pb-0">
                                                            <div class="filter_checkbox mb-0">
                                                                <div class="form-group">
                                                                    <input type="checkbox" id="web">
                                                                    <label for="web">Web Proctoring</label>
                                                                </div>

                                                                <div class="info">
                                                                    <p style="padding-inline-start: 0;"><img src="quaintpro/admin/assets/img/info_test.png" class="mr-2">Web proctoring features:</p>
                                                                </div>
                                                                <ul class="mt-3">
                                                                    <li>Enforce full screen during test</li>
                                                                    <li>Track tab activity</li>
                                                                    <li>Disable copy paste of solutions</li>
                                                                    <li>Prevent multi window test sessions</li>
                                                                    <li>Capture browser fingerprint</li>
                                                                </ul>
                                                            </div>
                                                    
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Browser Tolerance </label>
                                                            <select class="form-select">
                                                                <option>Not applicable</option>
                                                                <option>Not applicable 1</option>
                                                                <option>Not applicable 2</option>
                                                              </select>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-3 mb-4">
                                                    <div class="general_field recent_test">
                                                        <div class="form-group ">
                                                            <label for="Test Name">Image Proctoring </label>
                                                            <div class="recent_test_body filters_box border-0 pb-0">
                                                                <div class="filter_checkbox mb-0">
                                                                    <div class="form-group">
                                                                        <input type="checkbox" id="img">
                                                                        <label for="img">Image proctoring</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-3 mb-4">
                                                    <div class="general_field recent_test">
                                                        <div class="form-group ">
                                                            <label for="Test Name">Video Proctoring </label>
                                                            <div class="recent_test_body filters_box border-0 pb-0">
                                                                <div class="filter_checkbox mb-0">
                                                                    <div class="form-group">
                                                                        <input type="checkbox" id="video">
                                                                        <label for="video">Video proctoring</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="col-md-12 mb-4">
                                                    <div class="time_setting_heading">
                                                        <h3 class="mb-3">Time Setting</h3>
                                                        <div class="info">
                                                            <p style="padding-inline-start: 0;"><img src="quaintpro/admin/assets/img/info_test.png" class="mr-2">By adding a start and
                                                                end time for this test, you restrict the duration in which this test will be accessiable to the
                                                                candidates. Your account time zone is Asia/Kolkata. You can change it from your account setting.s</p>
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="col-md-6 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">Start Time <br><small> This test would not be accessiable before this.</small></label>
                                                            <div class="date_time">
                                                            <input type="date" class="form-control"  id="Test Name">
                                                            <input type="time" class="form-control"  id="Test Name">
                                                        </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6 mb-4">
                                                    <div class="general_field">
                                                        <div class="form-group">
                                                            <label for="Test Name">End Time <br><small> This test would not be accessiable                after this.</small></label>
                                                            <div class="date_time">
                                                                <input type="date" class="form-control"  id="Test Name">
                                                                <input type="time" class="form-control"  id="Test Name">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>


                                             
    
    
                                              


                                            </div>
                                        </form>
                                    </div>
                                  
                                   
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="Candidates" role="tabpanel" aria-labelledby="Candidates-tab">
                            <div class="Problems_tab Candidates">
                                <div class="row">
                                   
                                    <div class="col-md-12 col-lg-3">
                                        <div class="recent_test mb-4">
                                            <div class="recent_test_heading filters">
                                                <p><b>Filters</b></p>
                                                <p class="clear"><a href="javascript:void(0);">Clear</a></p>
                                            </div>

                                            <div class="recent_test_body filters_box">

                                                <div class="dropdown mb-3">
                                                    <div class="menu_heading" data-bs-toggle="dropdown">
                                                        <p>Status</p>
                                                        <img src="quaintpro/admin/assets/img/down_arrow.png">
                                                    </div>
                                                    <ul class="dropdown-menu">
                                                        <li>
                                                            <a class="dropdown-item" href="#">
                                                                <div class="filter_checkbox">
                                                                    <div class="form-group">
                                                                        <input type="checkbox" id="test">
                                                                        <label for="test">Tests Created by me</label>
                                                                    </div>
                                                                </div>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>

                                                <div class="dropdown mb-3">
                                                    <div class="menu_heading" data-bs-toggle="dropdown">
                                                        <p>Invited on</p>
                                                        <img src="quaintpro/admin/assets/img/down_arrow.png">
                                                    </div>
                                                    <ul class="dropdown-menu">
                                                        <li>
                                                            <a class="dropdown-item" href="#">
                                                                <div class="filter_checkbox">
                                                                    <div class="form-group">
                                                                        <input type="checkbox" id="test">
                                                                        <label for="test">Tests Created by me</label>
                                                                    </div>
                                                                </div>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>


                                                <div class="dropdown mb-3">
                                                    <div class="menu_heading" data-bs-toggle="dropdown">
                                                        <p>Lorem ipsum dolor</p>
                                                        <img src="quaintpro/admin/assets/img/down_arrow.png">
                                                    </div>
                                                    <ul class="dropdown-menu">
                                                        <li>
                                                            <a class="dropdown-item" href="#">
                                                                <div class="filter_checkbox">
                                                                    <div class="form-group">
                                                                        <input type="checkbox" id="Invited">
                                                                        <label for="Invited">Invited by me</label>
                                                                    </div>
                                                                </div>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>

                                        
                                            </div>
                                        </div>
                                    </div>
                
                
                
                                <div class="col-md-12 col-lg-9">
                                   
                                    <div class="question_bank">
                                        <div class="search">
                                            <div class="wrap">
                                                <div class="search">
                                                    <input type="text" class="searchTerm" placeholder="Search test here">
                                                    <button type="submit" class="searchButton">
                                                        <i class="fa fa-search" aria-hidden="true"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="download_report">
                                            <button class="report"><img src="quaintpro/admin/assets/img/download_report.png"> Download Report</button>
                                        </div>
                                    </div>

                                    <div class="condidate_table">
                                        <div class="table_scroll">
                                        <table class="table table-borderless table-sm">
                                            <thead>
                                              <tr>
                                                <th><input class="form-check-input" type="checkbox" id="check1" name="option1" value="something"></th>
                                                <th>Candidate</th>
                                                <th>Status</th>
                                                <th>Time</th>
                                                <th>Total Score</th>
                                                <th>%Score</th>
                                              </tr>
                                            </thead>
                                            <tbody>
                                              <tr>
                                                <td><input class="form-check-input" type="checkbox" id="check1" name="option1" value="something"></td>
                                                <td>
                                                    <div class="condidate_box">
                                                        <div class="condidate_icon">
                                                            <p><b>AC</b></p>
                                                        </div>
                                                        <div class="condidate_name">
                                                            <h4>Aarti Chaudhary</h4>
                                                            <p>aarti_choudhary@abc.com</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td><p class="accept">Accepted</p></td>
                                                <td>9:30 am</td>
                                                <td>157 points</td>
                                                <td>48%</td>
                                              </tr>

                                              <tr>
                                                <td><input class="form-check-input" type="checkbox" id="check1" name="option1" value="something"></td>
                                                <td>
                                                    <div class="condidate_box">
                                                        <div class="condidate_icon ac_1">
                                                            <p><b>AC</b></p>
                                                        </div>
                                                        <div class="condidate_name">
                                                            <h4>Aarti Chaudhary</h4>
                                                            <p>aarti_choudhary@abc.com</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td><p class="pend">Pending</p></td>
                                                <td>9:30 am</td>
                                                <td>157 points</td>
                                                <td>48%</td>
                                              </tr>


                                              <tr>
                                                <td><input class="form-check-input" type="checkbox" id="check1" name="option1" value="something"></td>
                                                <td>
                                                    <div class="condidate_box">
                                                        <div class="condidate_icon ">
                                                            <p><b>AC</b></p>
                                                        </div>
                                                        <div class="condidate_name">
                                                            <h4>Aarti Chaudhary</h4>
                                                            <p>aarti_choudhary@abc.com</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td><p class="accept">Accepted</p></td>
                                                <td>9:30 am</td>
                                                <td>157 points</td>
                                                <td>48%</td>
                                              </tr>


                                              <tr>
                                                <td><input class="form-check-input" type="checkbox" id="check1" name="option1" value="something"></td>
                                                <td>
                                                    <div class="condidate_box">
                                                        <div class="condidate_icon ac_2">
                                                            <p><b>AC</b></p>
                                                        </div>
                                                        <div class="condidate_name">
                                                            <h4>Aarti Chaudhary</h4>
                                                            <p>aarti_choudhary@abc.com</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td><p class="accept">Accepted</p></td>
                                                <td>9:30 am</td>
                                                <td>157 points</td>
                                                <td>48%</td>
                                              </tr>

                                              <tr>
                                                <td><input class="form-check-input" type="checkbox" id="check1" name="option1" value="something"></td>
                                                <td>
                                                    <div class="condidate_box">
                                                        <div class="condidate_icon">
                                                            <p><b>AC</b></p>
                                                        </div>
                                                        <div class="condidate_name">
                                                            <h4>Aarti Chaudhary</h4>
                                                            <p>aarti_choudhary@abc.com</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td><p class="rejec">Rejected</p></td>
                                                <td>9:30 am</td>
                                                <td>157 points</td>
                                                <td>48%</td>
                                              </tr>

                                              <tr>
                                                <td><input class="form-check-input" type="checkbox" id="check1" name="option1" value="something"></td>
                                                <td>
                                                    <div class="condidate_box">
                                                        <div class="condidate_icon ac_3">
                                                            <p><b>AC</b></p>
                                                        </div>
                                                        <div class="condidate_name">
                                                            <h4>Aarti Chaudhary</h4>
                                                            <p>aarti_choudhary@abc.com</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td><p class="pend">Pending</p></td>
                                                <td>9:30 am</td>
                                                <td>157 points</td>
                                                <td>48%</td>
                                              </tr>

                                              <tr>
                                                <td><input class="form-check-input" type="checkbox" id="check1" name="option1" value="something"></td>
                                                <td>
                                                    <div class="condidate_box">
                                                        <div class="condidate_icon">
                                                            <p><b>AC</b></p>
                                                        </div>
                                                        <div class="condidate_name">
                                                            <h4>Aarti Chaudhary</h4>
                                                            <p>aarti_choudhary@abc.com</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td><p class="accept">Accepted</p></td>
                                                <td>9:30 am</td>
                                                <td>157 points</td>
                                                <td>48%</td>
                                              </tr>

                                             
                                            </tbody>
                                          </table>
                                        </div>

                                          <div class="pagination_box mt-4 text-center">
                                            <div class="pagination">
                                                <button class="btn">
                                                    <i class="fa-solid fa-chevron-left"></i>
                                                </button>
                                                <div class="pages">
                                                    <a class="page active">1</a>
                                                    <a class="page">2</a>
                                                    <a class="page">3</a>
                                                    <a class="page">...</a>
                                                    <a class="page">9</a>
                                                </div>
                                                <button class="btn active">
                                                    <i class="fa-solid fa-chevron-right"></i>
                                                </button>
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
    </section>



    <script src="quaintpro/admin/assets/js/jquery.slim.min.js"></script>
    <script src="quaintpro/admin/assets/js/bootstrap.bundle.min.js"></script>
    <script src="quaintpro/admin/assets/js/jquery-slider-min.js"></script>
    <script src="quaintpro/admin/assets/js/apexcharts.js"></script>
    <script src="quaintpro/admin/assets/js/fontawesome.min.js"></script>
    <script src="quaintpro/admin/assets/js/custom.js"></script>
</body>
</html>