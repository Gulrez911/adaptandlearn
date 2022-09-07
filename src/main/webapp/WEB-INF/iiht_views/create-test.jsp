<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<html>
    <head>
      <title>eAssess</title>
      <link href="images/E-assess_E.png" rel="shortcut icon">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="new/css/font-awesome.min.css">
        <link rel="stylesheet" href="new/css/bootstrap.min.css">
        <link rel="stylesheet" href="new/css/style.css">
    </head>
    <body>
        <div class="master-container">
            <header>
                <div class="container-fluid">
                    <a href="#" class="nav-bars">
                        <i class="fa fa-bars"></i>
                    </a>
                    <a href="#" class="yaksha-logo my-auto">
                        YAK<span>SHA</span>
                    </a>
                    <nav>
                        <a href="#" class="close-menu">
                            <i class="fa fa-close"></i>
                        </a>
                        <div class="main-nav">
                            <a href="dashboardnew">
                                dashboard
                            </a>
                            <a href="questionssnew">
                                question bank
                            </a>
                            <a href="testsnew" class="active">
                                tests
                            </a>
                            <a href="#" class="more-menu">
                                more
                                <i class="fa fa-angle-down ml-1"></i>
                            </a>
                        </div>
                        <div class="sub-nav">
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                            <a href="#">
                                Navigation link
                            </a>
                        </div>
                    </nav>
                    <div class="user-info">
                        <div class="my-auto">
                             IIHT Admin
                        </div>
                        <div class="thumbnail ml-2 my-auto">
                            <span class="avatar">IA</span>
                        </div>
                    </div>
                </div>
            </header>
            <section class="main-section">
                <div class="container-fluid">
                    <form action="">
                        <div class="contents create-test">
                            <div class="row steps">
                                <div class="col-12 pb-5 mb-5">
                                    <ul class="form-steps">
                                        <li class="completed">
                                            <div class="thumbnail">
                                                <i class="fa fa-cog"></i>
                                            </div>
                                            <div class="step-name">Set your Test</div>
                                        </li>
                                        <li class="active">
                                            <div class="thumbnail">
                                                <i class="fa fa-list-ol"></i>
                                            </div>
                                            <div class="step-name">Add Questions</div>
                                        </li>
                                        <li>
                                            <div class="thumbnail">
                                                <i class="fa fa-users"></i>
                                            </div>
                                            <div class="step-name">Add Candidates</div>
                                        </li>
                                        <li>
                                            <div class="thumbnail">
                                                <i class="fa fa-send-o"></i>
                                            </div>
                                            <div class="step-name">Send Invitation</div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="col-12 step-1 d-none">
                                    <div class="steps-form">
                                        <div class="row">
                                            <div class="col-12 mb-4">
                                                <div class="page-heading">
                                                    <h2>Create new Test</h2>
                                                </div>
                                            </div>
                                            <div class="col-12 mb-2">
                                                <div class="form-group">
                                                    <label for="">Test Title</label>
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 mb-2">
                                                <div class="form-group">
                                                    <label for="">Category</label>
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 mb-2">
                                                <div class="form-group">
                                                    <label for="">Sub Category</label>
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 mb-2">
                                                <div class="form-group">
                                                    <label for="">Category of Sub Category</label>
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-2 mb-2 my-auto">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="">
                                                    <label class="form-check-label">
                                                      Fullstack test
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-2 mb-2 my-auto">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="">
                                                    <label class="form-check-label">
                                                      Shuffle test
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 md-2">
                                                <div class="form-group">
                                                    <label for="">No. of Attempts</label>
                                                    <input type="number" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 mb-2">
                                                <div class="form-group">
                                                    <label for="">Select Test Type</label>
                                                    <select class="form-control">
                                                        <option>General Knowledge</option>
                                                        <option>Angular</option>
                                                        <option>Php</option>
                                                        <option>Java</option>
                                                        <option>Node</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-8 mb-2">
                                                <div class="form-group">
                                                    <label for="">Select Skills</label>
                                                    <select multiple class="form-control" style="height: 194px;">
                                                        <option>JavaScript - Intermediate</option>
                                                        <option>Angular - Basic</option>
                                                        <option>Php - Intermediate</option>
                                                        <option>Java - Expert</option>
                                                        <option>Node - Advanced</option>
                                                        <option>Angular - Basic</option>
                                                        <option>Php - Intermediate</option>
                                                        <option>Java - Expert</option>
                                                        <option>Node - Advanced</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 mb-2">
                                                <div class="form-group mb-3">
                                                    <label for="">Duration</label>
                                                    <input type="text" class="form-control" placeholder="Enter time in minutes">
                                                </div>
                                                <div class="form-group mb-3">
                                                    <label for="">Pass Percentage</label>
                                                    <input type="text" class="form-control" placeholder="">
                                                </div>
                                                <div class="form-group mb-2">
                                                    <label for="">Conducted By</label>
                                                    <input type="email" class="form-control" placeholder="name@email.com" disabled >
                                                </div>
                                            </div>
                                            <div class="col-12 mb-2 mt-4 text-center">
                                                <p>Notification & Messages</p>
                                            </div>
                                            <div class="col-xs-12 col-md-6 mb-2">
                                                <div class="form-group">
                                                    <label for="">Users see this text just before they begin the exam</label>
                                                    <textarea class="form-control"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-6 mb-2">
                                                <div class="form-group">
                                                    <label for="">Shown to the user if they pass the exam. A copy is also sent via email</label>
                                                    <textarea class="form-control"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 mt-4">
                                                <p class="mb-4 text-center">Displayed upon exam completion and email communications relating to the exam</p>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="">
                                                    <label class="form-check-label">
                                                        Email a copy of the student's results to above contact
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="">
                                                    <label class="form-check-label">
                                                        Share Recommendation Engine by Email
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="">
                                                    <label class="form-check-label">
                                                        Display Justification for Answers
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="">
                                                    <label class="form-check-label">
                                                        Confidence Based Assessment Flag
                                                    </label>
                                                </div>
                                                <p class="mt-3">Protocol Setting</p>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="" checked disabled>
                                                    <label class="form-check-label">
                                                        Allow webcam proctoring
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 step-2">
                                    <div class="steps-form">
                                        <div class="row">
                                            <div class="col-12 mb-4">
                                                <div class="page-heading">
                                                    <h2>Import Questions into Section</h2>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 mb-2">
                                                <p class="mb-2">Sections</p>
                                                <div class="input-group mt-2">
                                                    <input type="text" class="form-control" placeholder="Section Name">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text">
                                                            x 132
                                                        </span>
                                                        <span class="input-group-text text-danger">
                                                            <i class="fa fa-minus"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="input-group mt-2">
                                                    <input type="text" class="form-control" placeholder="Section Name">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text">
                                                            x 132
                                                        </span>
                                                        <span class="input-group-text text-success">
                                                            <i class="fa fa-plus"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 step-3 d-none">
                                    <div class="steps-form">
                                        <div class="row">
                                            <div class="col-12 mb-4">
                                                <div class="page-heading">
                                                    <h2>Add Candidates</h2>
                                                </div>
                                            </div>
                                            <div class="col-12 mb-2">
                                                <div class="outline-block">
                                                    <div class="checkbox my-auto">
                                                        <input type="checkbox">
                                                    </div>
                                                    <div class="user-detail my-auto">
                                                        <p>
                                                            Select All
                                                        </p>
                                                    </div>
                                                    <div class="quick-action">
                                                        <button class="btn btn-sm btn-primary" type="button">
                                                            Add
                                                        </button>
                                                        <a href="#" class="ml-3">View Selected</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-6 col-xl-4 mb-2">
                                                <div class="outline-block">
                                                    <div class="checkbox my-auto">
                                                        <input type="checkbox">
                                                    </div>
                                                    <div class="user-detail my-auto">
                                                        <p>
                                                            firstname lastname
                                                        </p>
                                                        <p>
                                                            <span>name@email.com</span>
                                                            <span>Department: IT</span>
                                                            <span>Group: NA</span>
                                                        </p>
                                                    </div>
                                                    <div class="quick-action">
                                                        <button class="btn-icon bg-success text-white" type="button">
                                                            <i class="fa fa-check"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-6 col-xl-4 mb-2">
                                                <div class="outline-block">
                                                    <div class="checkbox my-auto">
                                                        <input type="checkbox">
                                                    </div>
                                                    <div class="user-detail my-auto">
                                                        <p>
                                                            firstname lastname
                                                        </p>
                                                        <p>
                                                            <span>name@email.com</span>
                                                            <span>Department: IT</span>
                                                            <span>Group: NA</span>
                                                        </p>
                                                    </div>
                                                    <div class="quick-action">
                                                        <button class="btn-icon text-success" type="button">
                                                            <i class="fa fa-plus"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-6 col-xl-4 mb-2">
                                                <div class="outline-block">
                                                    <div class="checkbox my-auto">
                                                        <input type="checkbox">
                                                    </div>
                                                    <div class="user-detail my-auto">
                                                        <p>
                                                            firstname lastname
                                                        </p>
                                                        <p>
                                                            <span>name@email.com</span>
                                                            <span>Department: IT</span>
                                                            <span>Group: NA</span>
                                                        </p>
                                                    </div>
                                                    <div class="quick-action">
                                                        <button class="btn-icon text-success" type="button">
                                                            <i class="fa fa-plus"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 text-center">
                                                <ul class="pagination">
                                                    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                                                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 step-4 d-none">
                                    <div class="steps-form">
                                        <div class="row">
                                            <div class="col-12 mb-4">
                                                <div class="page-heading">
                                                    <h2>Send Invitation</h2>
                                                </div>
                                            </div>
                                            <div class="col-12 mb-3">
                                                <h3>Test Title</h3>
                                                <p>Category: <strong>Category Name / Category Name / Category Name</strong></p>
                                                <p>Skills: <span class="tags mr-2">Skill Name</span><span class="tags mr-2">Skill Name</span><span class="tags mr-2">Skill Name</span><span class="tags mr-2">Skill Name</span></p>
                                                <p>Duartion: <strong>123 Mins</strong> <span class="ml-4">Attemps: <strong>3</strong></span></p>
                                                <p>Invited Candidates: <strong>123</strong></p>
                                            </div>
                                            <div class="col-xs-12 col-md-6">
                                                <div class="form-group">
                                                    <label for="">Link Start Date and Time</label>
                                                    <input type="date" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-6">
                                                <div class="form-group">
                                                    <label for="">Link End Date and Time</label>
                                                    <input type="date" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mt-4 text-center">
                            <button class="btn btn-outline-secondary mr-3" type="button">
                                Cancel
                            </button>
                            <button class="btn btn-secondary mr-3" type="button">
                                Back
                            </button>
                            <button class="btn btn-primary" type="button">
                                Next
                            </button>
                            <button class="btn btn-primary" type="button">
                                Send Invitation
                            </button>
                        </div>
                    </form>
                </div>
            </section>
            <footer>
                <div class="container-fluid">
                    <span>
                        &copy; copyright 2020-21
                    </span>
                    <span>
                        <a href="#">
                            terms and conditions
                        </a>
                        <a href="#">
                            privacy policy
                        </a>
                    </span>
                </div>
            </footer>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="new/js/bootstrap.min.js"></script>
        <script src="new/js/scripts.js"></script>
    </body>
</html>