<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Quantpro</title>
    <link rel="shortcut icon" href="assets/img/favicon.png" type="image/x-icon">
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
                                        <a class="dropdown-item" href="proSignOut">Logout</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </header>

 <form:form id="studentIntro" method="POST" action="preStudentJourneyPro"  modelAttribute="studentTestForm">
    <section class="dashboard_sec">
        <div class="container">
            <div class="quiz_section">
                <h3 class="text-center pt-4"> ${studentTestForm.testName}</h3>
                <div class="full-body-wrapper">
                    <div class="whole-wrapper">
                        <div class="logo">
                            <img src="quaintpro/admin/assets/img/3.png">
                        </div>
                        <div class="practise-test">
                            <h3>New practise test-1</h3>
                            <p>By the ABC</p>
                        </div>
                    </div>
                    <div class="image-wrapper">
                        <div class="time-zone">
                            <div class="logo-item">
                                <i class="fa-solid fa-clock"></i>
                            </div>
                            <div class="timer">
                                <h2>${testIntro.duration}</h2>
                                <p>To take this assessment ${studentTestForm.duration}  ${testIntro.minutes }
                                </p>
                            </div>
                        </div>
                        <div class="time-zone">
                            <div class="logo-item">
                                <i class="fa-solid fa-file-lines"></i>
                            </div>
                            <div class="problems">
                                <h2>${testIntro.totalNoOfQuestion }</h2>
                                <p>
                                    Problems to be solved
                                    ${studentTestForm.totalQuestions}
                                </p>
                            </div>
                        </div>
                        <div class="time-zone">
                            <div class="logo-item">
                                <i class="fa-solid fa-calendar-days"></i>
                            </div>
                            <div class="time-duration">
<!--                                 <p>Starts 18 May 2022, 7:04PM</p> -->
<!--                                 <P>Ends 18 May 2022, 7:04PM</P> -->
                                
                                <p>${testIntro.publishedOn }</p>
                                <P>${studentTestForm.formattedPublishedDate}</P>
                            </div>
                        </div>
                    </div>

                    <div class="instructions">
                        <h5>
                            ASSESSMENT INSTRUCTIONS
                        </h5>
                        <ol>
                            <li>This is an online test.</li>
                            <li>Please make sure that you are using the latest version of the browser We recommend
                                using Google Chrome,</li>
                            <li>It's mandatory to disable all the browser extensions and enabled Add-ons or open
                                the assessment in incognito mode.</li>
                            <li>If you are solving a coding problem, you will either be required to choose a
                                programming language from the options that have been
                                enabled by the gaministrator or choose your preferred programming language in case
                                no options have been enabled by the
                                administrator Note: In case you're solving coding problems: All inputs are from
                                STDIN and output to STDOUT.</li>
                            <li>If test mandates you to use the webcam, please provide the required permissions
                                and access.</li>
                            <li>To know the results, please contact the administrator.</li>
                            <li>To refer to the FAQ document, you can click on the HEIP button which is present in
                                the top right corner of the test environment.
                                best wishes from he Gene Box!</li>
                        </ol>
                    </div>


                    <div class="fully-wrapper">
                        <div class="selector-instruction">
                            <h3>Instructions From Quantpro</h3>
                        </div>
                        <div class="accord">
                            <div class="accordion" id="accordionExample">
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="headingOne">
                                        <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                            data-bs-target="#collapseOne" aria-expanded="true"
                                            aria-controls="collapseOne">
                                            <p>Which programming languages or frameworks should I be using to solve
                                                the assessment?</p>
                                        </button>
                                    </h2>
                                    <div id="collapseOne" class="accordion-collapse collapse show"
                                        aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                            <strong>This is the first item's accordion body.</strong> It is shown by
                                            default, until the collapse plugin adds the appropriate classes that we
                                            use to style each element. These classes control the overall appearance,
                                            as well as the showing and hiding via CSS transitions. You can modify
                                            any of this with custom CSS or overriding our default variables. It's
                                            also worth noting that just about any HTML can go within the
                                            <code>.accordion-body</code>, though the transition does limit overflow.
                                        </div>
                                    </div>
                                </div>
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="headingTwo">
                                        <button class="accordion-button collapsed" type="button"
                                            data-bs-toggle="collapse" data-bs-target="#collapseTwo"
                                            aria-expanded="false" aria-controls="collapseTwo">
                                            <P> I am not able to see question and images? What should I do?</P>

                                        </button>
                                    </h2>
                                    <div id="collapseTwo" class="accordion-collapse collapse"
                                        aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                            <strong>This is the second item's accordion body.</strong> It is hidden
                                            by default, until the collapse plugin adds the appropriate classes that
                                            we use to style each element. These classes control the overall
                                            appearance, as well as the showing and hiding via CSS transitions. You
                                            can modify any of this with custom CSS or overriding our default
                                            variables. It's also worth noting that just about any HTML can go within
                                            the <code>.accordion-body</code>, though the transition does limit
                                            overflow.
                                        </div>
                                    </div>
                                </div>
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="headingThree">
                                        <button class="accordion-button collapsed" type="button"
                                            data-bs-toggle="collapse" data-bs-target="#collapseThree"
                                            aria-expanded="false" aria-controls="collapseThree">
                                            <p>My screen is freezed and I am not able to navigate between the
                                                problems? What should I do?</p>

                                        </button>
                                    </h2>
                                    <div id="collapseThree" class="accordion-collapse collapse"
                                        aria-labelledby="headingThree" data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                            <strong>This is the third item's accordion body.</strong> It is hidden
                                            by default, until the collapse plugin adds the appropriate classes that
                                            we use to style each element. These classes control the overall
                                            appearance, as well as the showing and hiding via CSS transitions. You
                                            can modify any of this with custom CSS or overriding our default
                                            variables. It's also worth noting that just about any HTML can go within
                                            the <code>.accordion-body</code>, though the transition does limit
                                            overflow.
                                        </div>
                                    </div>
                                </div>
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="headingfour">
                                        <button class="accordion-button collapsed" type="button"
                                            data-bs-toggle="collapse" data-bs-target="#collapsefour"
                                            aria-expanded="false" aria-controls="collapsefour">
                                            <p>What happens to my assessment if I lose the internet connectivity
                                                while taking a assessment?</p>

                                        </button>
                                    </h2>
                                    <div id="collapsefour" class="accordion-collapse collapse"
                                        aria-labelledby="headingfour" data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                            <strong>This is the third item's accordion body.</strong> It is hidden
                                            by default, until the collapse plugin adds the appropriate classes that
                                            we use to style each element. These classes control the overall
                                            appearance, as well as the showing and hiding via CSS transitions. You
                                            can modify any of this with custom CSS or overriding our default
                                            variables. It's also worth noting that just about any HTML can go within
                                            the <code>.accordion-body</code>, though the transition does limit
                                            overflow.
                                        </div>
                                    </div>
                                </div>
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="headingfive">
                                        <button class="accordion-button collapsed" type="button"
                                            data-bs-toggle="collapse" data-bs-target="#collapsefive"
                                            aria-expanded="false" aria-controls="collapsefive">
                                            <p>I'm getting "Webcam.js (or) Waiting for webcam* error or "AGREE AND
                                                CONTINUE" button is disabled. What should I do?</p>

                                        </button>
                                    </h2>
                                    <div id="collapsefive" class="accordion-collapse collapse"
                                        aria-labelledby="headingfive" data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Perferendis
                                            voluptas obcaecati consequatur praesentium voluptatem reiciendis totam quam
                                            labore dolore tempora officiis, ad pariatur neque vero debitis itaque sit
                                            dolores quos!
                                        </div>
                                    </div>
                                </div>
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="headingsix">
                                        <button class="accordion-button collapsed" type="button"
                                            data-bs-toggle="collapse" data-bs-target="#collapsesix"
                                            aria-expanded="false" aria-controls="collapsesix">
                                            <p>I'm getting "System time" error or "AGREE AND CONTINUE* button is
                                                disabled. What should I do?</p>

                                        </button>
                                    </h2>
                                    <div id="collapsesix" class="accordion-collapse collapse"
                                        aria-labelledby="headingsix" data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Perferendis
                                            voluptas obcaecati consequatur praesentium voluptatem reiciendis totam quam
                                            labore dolore tempora officiis, ad pariatur neque vero debitis itaque sit
                                            dolores quos!
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="button-type">
<!--                             <button type="button" onclick="location.href = 'start-assignment.html';">START ASSESSMENT <i class="fa-solid fa-play"></i></button> -->
<c:choose>
								<c:when test="${practice != null && practice == true}">
									 <button type="button" class="btn btn-primary" onClick="start2()">Start Practice</button>
									<br />
								</c:when>    
								<c:when test="${test.webProctoring}">
									 <button type="button" class="btn btn-primary" onClick="checkCamera()" id="cam">Check Web Cam</button>
									<br />
								</c:when>    
								<c:otherwise>
									 <button type="button" class="btn btn-primary" onClick="start2()" id="sss">${testIntro.startAssessment }</button>
									<br />
								</c:otherwise>
							</c:choose>
                        </div>
                        <div class="information">
                            <p><i class="fa-solid fa-circle-info"></i> Go through <a href="javascript:void(0);">environment detals FAQs, and the help section</a>  before you start taking this
                                assessment.</p>


                        </div>
                    </div>

                </div>
            </div>
        </div>
        </div>
    </section>
</form:form>

    <script src="quaintpro/admin/assets/js/jquery.slim.min.js"></script>
    <script src="quaintpro/admin/assets/js/bootstrap.bundle.min.js"></script>
    <script src="quaintpro/admin/assets/js/jquery-slider-min.js"></script>
    <script src="quaintpro/admin/assets/js/apexcharts.js"></script>
    <script src="quaintpro/admin/assets/js/fontawesome.min.js"></script>
    <script src="quaintpro/admin/assets/js/custom.js"></script>
    
    <script>
    var randomNumber = Math.floor(Math.random() * (6 - 1 + 1)) + 1;
    $('header').attr('style', "background-image: url(images_new/banner_" + randomNumber + ".jpg);");
    /*   function handleClick(cb) {
          if(cb.checked){
             document.getElementById("startTest").disabled = false;
          }
          else{
          document.getElementById("startTest").disabled = true;
          }
     } */
     
     function start2(){
         //fullScreen();
         document.getElementById("studentIntro").submit();
     }
     
 	function checkCamera(){

			navigator.getMedia = ( navigator.getUserMedia || // use the proper vendor prefix
		            navigator.webkitGetUserMedia ||
		            navigator.mozGetUserMedia ||
		            navigator.msGetUserMedia);
		
				navigator.getMedia({video: true}, function() {
					sweetAlert("Info","You can start test Now","success")
					$("#cam").attr("onclick","start2()");
					$("#cam").text("Start Assessment");
				}, function() {
					sweetAlert("Information","You can not start test without camera","warning")
//							alert("Test cannot start without camera");
		});	
		}
     
    
    function fullScreen(){
        $("#cls").click()
        var elem = document.documentElement;
        if (elem.requestFullscreen) {
            elem.requestFullscreen();
        } else if (elem.mozRequestFullScreen) { /* Firefox */
            elem.mozRequestFullScreen();
        } else if (elem.webkitRequestFullscreen) { /* Chrome, Safari and Opera */
            elem.webkitRequestFullscreen();
        } else if (elem.msRequestFullscreen) { /* IE/Edge */
            elem.msRequestFullscreen();
        }
    } 
    
	function changeLang(lang){
		var url = new URL(window.location.href);
		url.searchParams.set('lang',lang);
		var new_url = url.toString();
		window.location = new_url
// 		var search_params = url.searchParams;
// 		alert(new_url);
	}
	
    function sweetAlert(msgtype,message,icon){
		  Swal.fire(
			       msgtype,
			       message,
			       icon
			    )
		}
</script>
</body>
</html>