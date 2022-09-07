<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="materialize is a material design based mutipurpose responsive template">
        <meta name="keywords" content="material design, card style, material template, portfolio, corporate, business, creative, agency">
        <meta name="author" content="trendytheme.net">
        <title>eAssess</title>
        <!--  favicon -->
        <link rel="shortcut icon" href="./resources/eAssessLogin/assets/img/ico/favicon.png">
        <link href='https://fonts.googleapis.com/css?family=Raleway:400,300,500,700,900' rel='stylesheet' type='text/css'>
        <!-- Material Icons CSS -->
        <link href="./resources/eAssessLogin/assets/fonts/iconfont/material-icons.css" rel="stylesheet">
        <!-- owl.carousel -->
        <link href="./resources/eAssessLogin/assets/owl.carousel.css" rel="stylesheet">
        <link href="assets/owl.carousel/assets/owl.theme.default.min.css" rel="stylesheet">
        <!-- FontAwesome CSS -->
        <link href="./resources/eAssessLogin/assets/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- materialize -->
        <link href="./resources/eAssessLogin/assets/materialize/css/materialize.min.css" rel="stylesheet">
        <!-- Bootstrap -->
        <link href="./resources/eAssessLogin/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- shortcodes -->
        <link href="./resources/eAssessLogin/assets/css/shortcodes/shortcodes.css" rel="stylesheet">
        <link href="./resources/eAssessLogin/assets/css/shortcodes/login.css" rel="stylesheet">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="./resources/eAssessLogin/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="./resources/eAssessLogin/assets/materialize/js/materialize.min.js"></script>
        <script src="./resources/eAssessLogin/assets/owl.carousel/owl.carousel.min.js"></script>


      <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.js"></script>
        <!-- Style CSS -->
        <link href="./resources/eAssessLogin/assets/css/style.css" rel="stylesheet">
        <style type="text/css">
          .slick-slide {
              margin: 0px 20px;
          }

          .slick-slide img {
              width: 100%;
          }

          .slick-slider {
              position: relative;
              display: block;
              box-sizing: border-box;
              -webkit-user-select: none;
              -moz-user-select: none;
              -ms-user-select: none;
              user-select: none;
              -webkit-touch-callout: none;
              -khtml-user-select: none;
              -ms-touch-action: pan-y;
              touch-action: pan-y;
              -webkit-tap-highlight-color: transparent;
          }

          .slick-list {
              position: relative;
              display: block;
              overflow: hidden;
              margin: 0;
              padding: 0;
          }

          .slick-list:focus {
              outline: none;
          }

          .slick-list.dragging {
              cursor: pointer;
              cursor: hand;
          }

          .slick-slider .slick-track,
          .slick-slider .slick-list {
              -webkit-transform: translate3d(0, 0, 0);
              -moz-transform: translate3d(0, 0, 0);
              -ms-transform: translate3d(0, 0, 0);
              -o-transform: translate3d(0, 0, 0);
              transform: translate3d(0, 0, 0);
          }

          .slide {
              width: 160px;
          }

          .slick-track {
              position: relative;
              top: 0;
              left: 0;
              display: block;
          }

          .slick-track:before,
          .slick-track:after {
              display: table;
              content: '';
          }

          .slick-track:after {
              clear: both;
          }

          .slick-loading .slick-track {
              visibility: hidden;
          }

          .slick-slide {
              display: none;
              float: left;
              height: 100%;
              min-height: 1px;
          }

          [dir='rtl'] .slick-slide {
              float: right;
          }

          .slick-slide img {
              display: block;
          }

          .slick-slide.slick-loading img {
              display: none;
          }

          .slick-slide.dragging img {
              pointer-events: none;
          }

          .slick-initialized .slick-slide {
              display: block;
          }

          .slick-loading .slick-slide {
              visibility: hidden;
          }

          .slick-vertical .slick-slide {
              display: block;
              height: auto;
              border: 1px solid transparent;
          }

          .slick-arrow.slick-hidden {
              display: none;
          }
        </style>
    </head>

    <body id="top" class="has-header-search">    
        <!--header start-->
        <header id="header" class="tt-nav nav-border-bottom">
            <div class="header-sticky light-header my_header">
                <div class="container">
                    <div id="materialize-menu" class="menuzord">
                        <!--logo start-->
                        <a href="index.html" class="logo-brand">
                            <img class="retina" src="./resources/eAssessLogin/images/Logo.png" alt=""/>
                        </a>
                        <!--logo end-->
                        <div class="headerloginform">
                         <form id="loginForm" method="post" modelAttribute="user" action="authenticate">
							<form:input style="width:150px" type="email" path="user.email" name="email" id="username" cssClass="form-control" required="true" placeholder="User"/>
 <form:password style="width:150px" path="user.password" name="password" id="password" cssClass="form-control" required="true" placeholder="Password"/>
									<form:input style="width:150px" path="user.companyName" name="companyName" id="companyName" cssClass="form-control" required="true" placeholder="Company"/>					   
                                <button style="width:150px" type="submit">Login</button>
								<button style="width:250px" type="button" onClick="javascript:registerAss();">For IT Employees</button>
                              </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!--header end-->
        
        <section class="banner-wrapper parallax-bg banner-12   dark-5 valign-wrapper height-650" data-stellar-background-ratio="0.5">
        </section>

        <!-- <section class="section-padding gray-bg">
            <div class="container">
                <div class="row">
                    <div class="col-md-5">
                      <img class="img-responsive mb-sm-30" src="assets/img/creative/laptop.jpg" alt="">
                    </div>

                    <div class="col-md-7">
                        <h2 class="text-uppercase text-extrabold font-30 mb-30 ">materialize is a creative html template</h2>

                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit</p>

                        <a href="#." class="btn btn-lg waves-effect waves-light"> Buy Now</a>
                    </div>

                </div>
            </div>
        </section> -->

        <section class="section-padding managefeatures product_features">
            <div class="container">

              <!-- <div class="text-center mb-80">
                <h2 class="section-title text-uppercase">Product Features</h2>
              </div> -->
              <div class="row">
                    <div class="col-md-5">
                      <img class="img-responsive mb-sm-30" src="./resources/eAssessLogin/images/privatetrainer.jpg" alt="">
                    </div>

                    <div class="col-md-7">
                        <h2 class="text-uppercase">Assessment Platform</h2>
                        <ul>
                          <li>Conduct MCQ/Coding/Fullstack/Video assessments for Internal/External Employees to measure effectiveness of your Learning Programs/Recruitment</li>
                          <li>Create Highly Configurable Assessments to suit your needs</li>
                          <li>Supports Coding assessments for - Java/DotNet/C/C++/Python up to 20  Programming languages</li>
                          <li>Huge Content Repository - (Programming Languages/Communication/Aptitude/Reasoning/Competitive Exams/more)</li>
                          <li>Secured Assessments - Detects attempts to move away from Test Screen, identifies suspicious video patterns</li>
                          <li>Has an inbuild Skill Recommendation Engine</li>
                          <li>Can Integrate with your exisitng LMS/HRMS systems</li>
                        </ul>
                    </div>
              </div>
              <div class="row">
                    <div class="col-md-7">
                        <h2 class="text-uppercase">Candidate Profile Scanner & Management</h2>
                          <ul>
                            <li>Centralized Repository of Job descriptions/Job Applications</li>
                            <li>Manage External Recruitment Vendors</li>
                            <li>Skill Repository for Candidates appearing for positions</li>
                            <li>Centralized storage of Interviewer Feedback</li>
                            <li>360 degree view of Applications right from sourcing to assessmment/interview stage.</li>
                            <li>Multiple Stakeholders login - Talent Professional/HR/Vendor/Interviewer</li>
                            <li>Use AI/ML to transalate Profiles to a set of objective parameters and establish relevancy to job descriptions</li>
                          </ul>
                    </div>
                    <div class="col-md-5">
                      <img class="img-responsive mb-sm-30" src="./resources/eAssessLogin/images/video_upload1.jpg" alt="">
                    </div>
              </div>
              <div class="row">
                    <div class="col-md-5">
                      <img class="img-responsive mb-sm-30" src="./resources/eAssessLogin/images/cloud_onPremises_deployment.png" alt="">
                    </div>

                    <div class="col-md-7">
                        <h2 class="text-uppercase">Campaign Manager</h2>
                          <ul>
                            <li>Orchestrate multiple rounds of assessments(MCQ/Coding) with Candidates</li>
                            <li>Generate Skill profile for Candidates based on assessment feedback through automation</li>
                            <li>Integrate with your Meeting platforms to conduct one-on-one meetings with candidates</li>
                            <li>Access of Candidate's Skill Profile  with Interviewer when conducting interview</li>
                            <li>Subjective Candidate feedback input by Interviewer for subsequent decesions</li>
                          </ul>
                    </div>

              </div>
              <!-- <div class="row">

                  <div class="col-md-4">
                    <div class="featured-item border-box radius-4 hover brand-hover">
                        <div class="icon mb-30">
                            <i class="material-icons brand-icon">?</i>
                        </div>
                        <div class="desc">
                            <h2 class="text-uppercase">Candidate Profile Scanner & Management</h2>
                            <p>Centralized Repository of Job descriptions/Job Applications</p>
                            <p>Manage External Recruitment Vendors</p>
                            <p>Skill Repository for Candidates appearing for positions</p>
                            <p>Centralized storage of Interviewer Feedback</p>
                            <p>360 degree view of Applications right from sourcing to assessmment/interview stage.</p>
                            <p>Multiple Stakeholders login - Talent Professional/HR/Vendor/Interviewer</p>
                            <p>Use AI/ML to transalate Profiles to a set of objective parameters and establish relevancy to job descriptions</p>
                        </div>
                    </div>
                  </div>

                  <div class="col-md-4">
                    <div class="featured-item border-box radius-4 hover brand-hover">
                        <div class="icon mb-30">
                            <i class="material-icons brand-icon">?</i>
                        </div>
                        <div class="desc">
                            <h2 class="text-uppercase">Campaign Manager</h2>
                            <p>Orchestrate multiple rounds of assessments(MCQ/Coding) with Candidates</p>
                            <p>Generate Skill profile for Candidates based on assessment feedback through automation</p>
                            <p>Integrate with your Meeting platforms to conduct one-on-one meetings with candidates</p>
                            <p>Access of Candidate's Skill Profile  with Interviewer when conducting interview</p>
                            <p>Subjective Candidate feedback input by Interviewer for subsequent decesions</p>
                        </div>
                    </div>
                  </div>

                  <div class="col-md-4">
                    <div class="featured-item border-box radius-4 hover brand-hover">
                        <div class="icon mb-30">
                            <i class="material-icons brand-icon">?</i>
                        </div>
                        <div class="desc">
                            <h2 class="text-uppercase">Assessment Platform</h2>
                            <p>Conduct MCQ/Coding/Fullstack/Video assessments for Internal/External Employees to measure effectiveness of your Learning Programs/Recruitment</p>
                            <p>Create Highly Configurable Assessments to suit your needs</p>
                            <p>Supports Coding assessments for - Java/DotNet/C/C++/Python up to 20  Programming languages</p>
                            <p>Huge Content Repository - (Programming Languages/Communication/Aptitude/Reasoning/Competitive Exams/more)</p>
                            <p>Secured Assessments - Detects attempts to move away from Test Screen, identifies suspicious video patterns</p>
                            <p>Has an inbuild Skill Recommendation Engine</p>
                            <p>Can Integrate with your exisitng LMS/HRMS systems</p>
                        </div>
                    </div>
                  </div>
              </div> -->
            </div>
        </section>

        <section id="features" class="section-padding productfeatures">
            <div class="container">

              <div class="text-center mb-80 wow fadeInUp">
                  <h2 class="section-title text-uppercase">Product Features</h2>
              </div>

              <div class="row equal-height-row">
                  <div class="col-md-4 mb-30">
                      <div class="featured-item hover-outline brand-hover radius-4 equal-height-column">
                          <div class="icon">
                              <i class="material-icons colored brand-icon">&#xE0B7;</i>
                          </div>
                          <div class="desc">
                              <h2>rParser</h2>
                              <p>Parse Job Descriptions</p>
                              <p>Parse Candidate Profiles</p>
                              <p>Establish Relevancy to Job Description</p>
                              <p>Integrate with Campaign Manager & Assessment Engine</p>
                              <p>Integrate with your HRMS systems</p>
                          </div>
                      </div><!-- /.featured-item -->
                  </div><!-- /.col-md-4 -->

                  <div class="col-md-4 mb-30">
                      <div class="featured-item hover-outline brand-hover radius-4 equal-height-column">
                          <div class="icon">
                              <i class="material-icons colored brand-icon">&#xE912;</i>
                          </div>
                          <div class="desc">
                              <h2>Highly Configurable Assessments</h2>
                              <p>Attempts/Pass Threshold/Dispatching of Results to select people/etc</p>
                              <p>Weighted Scoring Model</p>
                              <p>Randomization</p>
                              <p>Recommndations</p>
                              <p>Show Quesions Justifications</p>
                          </div>
                      </div><!-- /.featured-item -->
                  </div><!-- /.col-md-4 -->

                  <div class="col-md-4 mb-30">
                      <div class="featured-item hover-outline brand-hover radius-4 equal-height-column">
                          <div class="icon">
                              <i class="material-icons colored brand-icon">&#xE8B8;</i>
                          </div>
                          <div class="desc">
                              <h2>Enterprise Integrations</h2>
                              <p>SSO</p>
                              <p>Can integrate with your LMS/HRMS/Meeting platforms</p>
                              <p>Role based Access Control</p>
                              <p>Access for Internal / External Users</p>
                              <p>Easy Candidate Onboarding</p>
                          </div>
                      </div><!-- /.featured-item -->
                  </div><!-- /.col-md-4 -->

                  <div class="col-md-4">
                      <div class="featured-item hover-outline brand-hover radius-4 equal-height-column">
                          <div class="icon">
                              <i class="material-icons colored brand-icon">&#xE323;</i>
                          </div>
                          <div class="desc algorithms">
                              <h2>Coding Assessments (Algorithms)</h2>
                              <p>Java</p>
                              <p>C</p>
                              <p>C++</p>
                              <p>Dot Net</p>
                              <p>C#</p>
                              <p>PHP</p>
                              <p>Python</p>
                              <p>JavaScript</p>
                              <p>Clojure</p>
                              <p>GO</p>
                              <p>Bash</p>
                              <p>Objective C</p>
                              <p>Perl</p>
                              <p>More</p>
                          </div>
                      </div><!-- /.featured-item -->
                  </div><!-- /.col-md-4 -->

                  <div class="col-md-4">
                      <div class="featured-item hover-outline brand-hover radius-4 equal-height-column">
                          <div class="icon">
                              <i class="material-icons colored brand-icon">&#xE412;</i>
                          </div>
                          <div class="desc">
                              <h2>Fullstack Assessments (Framework/DB Support)</h2>
                              <p>Java</p>
                              <p>DotNet</p>
                              <p>Python</p>
                              <p>PHP</p>
                              <p>Angular JS/React JS</p>
                              <p>NoSQL (MongoDB/Cassandra/etc)</p>
                              <p>More</p>
                          </div>
                      </div><!-- /.featured-item -->
                  </div><!-- /.col-md-4 -->

                  <div class="col-md-4">
                      <div class="featured-item hover-outline brand-hover radius-4 equal-height-column">
                          <div class="icon">
                              <i class="material-icons colored brand-icon">&#xE02C;</i>
                          </div>
                          <div class="desc">
                              <h2>Assessment Content</h2>
                              <p>MCQ/Coding/Fullstack - Programming Languages</p>
                              <p>Communications</p>
                              <p>Behaviour</p>
                              <p>Aptitude</p>
                              <p>Mock Entrance (JEE/NEET/MRNAT/CLAT/etc)</p>
                          </div>
                      </div><!-- /.featured-item -->
                  </div><!-- /.col-md-4 -->
              </div><!-- /.row -->

            </div><!-- /.container -->
        </section>

        <!-- <section class="padding-top-50 padding-bottom-50 brand-bg">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                  <div class="quote-carousel text-center">

                      <div class="carousel-item">
                          <div class="content">
                              <h2 class="white-text line-height-40">"My favorite things in life don't cost any money. It's really clear that the most precious resource we all have is time."</h2>

                              <div class="testimonial-meta font-20 text-extrabold white-text">
                                  Steve Jobes
                              </div>
                          </div>
                      </div>

                      <div class="carousel-item">
                          <div class="content">
                              <h2 class="white-text line-height-40">"My favorite things in life don't cost any money. It's really clear that the most precious resource we all have is time."</h2>

                              <div class="testimonial-meta font-20 text-extrabold white-text">
                                  Steve Jobes
                              </div>
                          </div>
                      </div>

                      <div class="carousel-item">
                          <div class="content">
                              <h2 class="white-text line-height-40">"My favorite things in life don't cost any money. It's really clear that the most precious resource we all have is time."</h2>

                              <div class="testimonial-meta font-20 text-extrabold white-text">
                                  Steve Jobes
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
            </div>
          </div>
        </section> -->

        <section class="whocanuse gray-bg" >
          <div class="container">
            <div class="text-center mt-50">
              <h2 class="section-title">Meet Our Clients</h2>
              <hr class="line">
            </div>
            
          </div>
        </section>
        <section class="waystouse section-padding gray-bg">
          
          <div class="container gray-bg">
            <div class="row">
              <div class="customer-logos">
                <div class="slide"><img src="./resources/eAssessLogin/images/logo-abm2.png" style="height: 90px;"></div>
                <div class="slide"><img src="./resources/eAssessLogin/images/LTI.png" style="height: 110px;"></div>
                <div class="slide"><img src="./resources/eAssessLogin/images/cognizant.png" style="height: 100px;"></div>
                <div class="slide"><img src="./resources/eAssessLogin/images/panorma.png" style="height: 100px;"></div>
                <div class="slide"><img src="./resources/eAssessLogin/images/Pearson-Institute.png" style="height: 100px;"></div>
                <div class="slide"><img src="./resources/eAssessLogin/images/AIE.png" style="height: 90px;"></div>
                <div class="slide"><img src="./resources/eAssessLogin/images/Infrasoft copy.png" style="height: 150px;"></div>
              </div></div></div>
        </section>

        <footer>
          <div class="myfooter">
            <div class="container">
              <div class="row"> 
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                  <a href="index.html" class="ftrlogo">
                      <img class="retina" src="./resources/eAssessLogin/images/Logo.png" alt="">
                  </a>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                  <div class="footer-inner">
                    <p>Contact Us</p>
                    <h6><a href="tel:+91 9768 36294"><i class="fa fa-phone" aria-hidden="true"></i>+91 9768 36294</a></h6>
                    <h6><a href="mailto:contact@eassess.in"><i class="fa fa-envelope" aria-hidden="true"></i>contact@eassess.in</a></h6>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                  <a href="https://eassess.in/prospect." target="_blank" class="btn info">Request For Demo</a>
                  <ul>
                    <li><a href="https://www.youtube.com/channel/UCViRq-7wQyjgU2eGSoQwfiQ" target="_blank"><i class="fa fa-youtube-play" aria-hidden="true"></i></a></li>
                    <li><a href="https://www.linkedin.com/company/cygone-tech" target="_blank"><i class="fa fa-linkedin-square" aria-hidden="true"></i></a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <div class="myfooterlast">
            <p>Copyright &copy; 2021 Cygone Tech Private Limited 2020</p>
          </div>
        </footer>

		</div>

        <!-- jQuery -->
       
        <script>
          if ($('.quote-carousel').length > 0) {

            $('.quote-carousel').owlCarousel({
                loop:true,
                autoHeight : true,
                responsive:{
                    0:{
                        items:1
                    },
                    600:{
                        items:1
                    },
                    1000:{
                        items:1
                    }
                }
            });
          }
        </script>
          <script>

        $(document).ready(function(){
      $('.customer-logos').slick({
        slidesToShow: 4,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 1000,
        arrows: false,
        dots: false,
          pauseOnHover: false,
          responsive: [{
          breakpoint: 768,
          settings: {
            slidesToShow: 3
          }
        }, {
          breakpoint: 520,
          settings: {
            slidesToShow: 2
          }
        }]
      });
    });

            if ($('.quote-carousel').length > 0) {

            $('.quote-carousel').owlCarousel({
                loop:true,
                autoHeight : true,
                responsive:{
                    0:{
                        items:1
                    },
                    600:{
                        items:1
                    },
                    1000:{
                        items:1
                    }
                }
            });
        }
		
		function registerAss(){
			window.location = 'registerAssessment';
		}
        
        </script>

  </body>
  
</html>