<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<html lang="en">

<head>
<title>Quantpro</title>
<link rel="shortcut icon" href="quaintpro/admin/assets/img/favicon.png" type="image/x-icon">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="quaintpro/admin/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="quaintpro/admin/assets/css/style.css" rel="stylesheet">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
	integrity="sha512-c42qTSw/wPZ3/5LBzD+Bw5f7bSF2oxou6wEb+I/lqeaKV5FDIfMvvRp772y4jcJLKuGUOpbJMdg/BTl50fJYAw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.1.9/sweetalert2.min.css"
	integrity="sha512-cyIcYOviYhF0bHIhzXWJQ/7xnaBuIIOecYoPZBgJHQKFPo+TOBA+BY1EnTpmM8yKDU4ZdI3UGccNGCEUdfbBqw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.1.9/sweetalert2.all.min.js"
	integrity="sha512-IZ95TbsPTDl3eT5GwqTJH/14xZ2feLEGJRbII6bRKtE/HC6x3N4cHye7yyikadgAsuiddCY2+6gMntpVHL1gHw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<style type="text/css">
.pagination2 {
	display: flex;
}

.btn2 {
	background-color: #4782BC;
	border: 1px solid #4782BC;
	color: #ffffff;
	height: 42px;
	width: 45px;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 0 10px;
}

.wrap {
	display: flex;
	justify-content: space-around;
	align-items: center;
	/*     margin-left: 25%; */
	width: auto !important;
	margin: 0px auto 30px 25% !important;
}
</style>
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
                            <li class="nav-item"> <a class="nav-link " href="tests.html">
                                    <img src="quaintpro/admin/assets/img/test_active.png" class="active">
                                    <img src="quaintpro/admin/assets/img/test.png" class="disactive">Tests</a> </li>
                            <li class="nav-item "> <a class="nav-link active" href="listTestLinksPro">
                                    <img src="quaintpro/admin/assets/img/test_active.png" class="active">
                                    <img src="quaintpro/admin/assets/img/test.png" class="disactive">List Test Links</a> </li>
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

    <section class="qb_section create_question_sec">
        <div class="container">
            <div class="row">
                <div class="col-md-12 mb-4">
                    <div class="question_bank_heading">
                        <h3>Create New Test Link</h3>
                    </div>
                </div>
            </div>

            <div class="Create_New_Question_box">
              <form name="linkForm"  method="post" modelAttribute="link" action="saveTestLinkPro">
                    <div class="row">
                        <div class="col-md-12 col-lg-12">
                            <div class="Create_New_Question_form">
                                <div class="row">

                                    <div class="col-md-12">
                                        <div class="Create_New_Question_field">
                                            <div class="mb-3">
                                                <label for="comment" class="form-label">Select Test</label>
                                                
                                                	<form:select id="testName" path="link.testName" class="form-select">
								<form:options items="${tests}"  />
							</form:select>
							<form:hidden path="link.id" />
                                                
<!--                                                 <select class="form-select"> -->
<!--                                                     <option>Select</option> -->
<!--                                                 </select> -->
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="Create_New_Question_field">
                                            <div class="mb-3">
                                                <label for="text" class="form-label">Link Start Date and Time</label>
<!--                                                  <input type="datetime-local" class="form-control" id="startDate" name="stDate" required="true"> -->
                                                
                                                	<div class='input-group date' id='datetimepicker1'>
									<form:input path="link.stDate" class="form-control" required="true" />
															<span class="input-group-addon">
																<span class="glyphicon glyphicon-calendar"></span>
															</span>
														</div>
														
<!--                                                 <input type="date" class="form-control" id="text" name="text" -->
<!--                                                     placeholder="Enter a number between 1 to 10"> -->
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="Create_New_Question_field">
                                            <div class="mb-3">
                                                <label for="text" class="form-label">Link End Date and Time</label>
<!--                                                 <input type="datetime-local" class="form-control" id="endDate" name="edDate" required="true"> -->
                                                <div class='input-group date' id='datetimepicker2'>
							<form:input path="link.edDate" class="form-control" required="true" />
															<span class="input-group-addon">
																<span class="glyphicon glyphicon-calendar"></span>
															</span>
														</div>
<!--                                                 <input type="date" class="form-control" id="text" name="text" -->
<!--                                                     placeholder="Enter a number between 1 to 10"> -->
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="Create_New_Question_field">
                                            <div class="mb-3">
                                                <label for="comment" class="form-label">Don't Check Time
                                                    Validity</label>
                                                <div class="input-group multi_check_box mb-3">
                                                    <div class="input-group-prepend">
                                                        <div class="input-group-text">
                                                        	<form:checkbox path="link.dontCheckTimeValidity" /> 
<!--                                                             <input type="checkbox" value=""> -->
                                                        </div>
                                                    </div>
                                                    <input class="form-control" type="text" value="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="Create_New_Question_field">
                                            <div class="mb-3">
                                                <label for="comment" class="form-label">Web Proctoring ON</label>
                                                <div class="input-group multi_check_box mb-3">
                                                    <div class="input-group-prepend">
                                                        <div class="input-group-text">
                                                        <form:checkbox path="link.webProctored" /> 
<!--                                                             <input type="checkbox" value=""> -->
                                                        </div>
                                                    </div>
                                                    <input class="form-control" type="text" value="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="col-md-12 col-lg-12">
                            <div class="question_save text-center">
                            
<!--                                 <input class="save" type="submit" value="Save Test Link"> -->
						
<!-- 						    <input type="button" value="Cancel" onClick="location.href='listTestLinks';"> -->
                            
                            
                                <button class="cancle_btn" onClick="location.href='listTestLinksPro';">Cancel</button>
                                <button  type="submit" class="save_btn">Save Test Link</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>



	<script src="quaintpro/admin/assets/js/jquery.slim.min.js"></script>
	<script src="quaintpro/admin/assets/js/bootstrap.bundle.min.js"></script>
	<script src="quaintpro/admin/assets/js/jquery-slider-min.js"></script>
	<script src="quaintpro/admin/assets/js/apexcharts.js"></script>
	<script src="quaintpro/admin/assets/js/fontawesome.min.js"></script>
	<script src="quaintpro/admin/assets/js/custom.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

	<script type="text/javascript">
    
    $(function () {
		
		
        $('#datetimepicker1').datetimepicker({
			format: 'DD/MM/YYYY hh:mm a',
			//minDate:new Date()
			
		});
        
		var date = new Date();

		// add a day
		date.setDate(date.getDate() + 5);
		
         $('#datetimepicker2').datetimepicker({
			format: 'DD/MM/YYYY hh:mm a',
			//minDate:date
		});
    });
    
	function getQualifer(qualifier) {
// 		alert(qualifier);
		  window.location = "proquestionbank?qualifier="+qualifier;
	}
	function confirm(id) {
    
    Swal.fire({
    	  title: 'Are you sure?',
    	  text: "You won't be able to revert this!",
    	  icon: 'warning',
    	  showCancelButton: true,
    	  confirmButtonColor: '#3085d6',
    	  cancelButtonColor: '#d33',
    	  confirmButtonText: 'Yes, delete it!'
    	}).then((result) => {
    	  if (result.isConfirmed) {
    		  window.location = "removeQuestionPro?qid="+id;
    		  
//     	    Swal.fire(
//     	      'Deleted!',
//     	      'Your file has been deleted.',
//     	      'success'
//     	    )
    	    
    	  }
    	})
    
	}
    
// 	function confirm(id) {
//         (new PNotify({
// 		    title: 'Confirmation Needed',
// 		    text: 'Are you sure? Do you really want to delete this Q?',
// 		    icon: 'glyphicon glyphicon-question-sign',
// 		    hide: false,
// 		    confirm: {
// 			confirm: true
// 		    },
// 		    buttons: {
// 			closer: false,
// 			sticker: false
// 		    },
// 		    history: {
// 			history: false
// 		    }
// 		})).get().on('pnotify.confirm', function() {
// 		    window.location = "removeQuestionFromList?qid="+id;
// 		}).on('pnotify.cancel', function() {
		   
// 		});
//      }
	
	
    $('#search').on('click',function(){
	    var text = document.getElementById("searchText").value;
		if(text.length != 0){
		window.location="searchQuestionsPro?searchText="+text;
		}
	    });
    
    
    </script>

	<c:if test="${msgtype != null}">
		<script>
			var notification = 'Information';
			$(function() {
				Swal.fire('${msgtype}', '${message}', '${icon}')
			});
		</script>
	</c:if>

</body>
</html>