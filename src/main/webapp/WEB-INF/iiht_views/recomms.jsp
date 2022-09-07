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
      	<link href="images/E-assess_E.png" rel="shortcut icon">     <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
     	
     	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
     	        <link rel="stylesheet" href="new/css/font-awesome.min.css">
<!--         <link rel="stylesheet" href="resources/newrecomm/css/font-awesome.min.css"> -->
        <link rel="stylesheet" href="resources/newrecomm/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/newrecomm/css/style.css">
         <link rel="stylesheet" href="resources/newrecomm/new_css/style.css">
        <script src="new/js/jquery.min.js"></script>
        <script src="new/js/bootstrap.min.js"></script>

</head>
<body>
 <div class="master-container">
            <header>
                <div class="container-fluid">
                    <button class="nav-bar" type="button">
                        <i class="fa fa-bars"></i>
                    </button>
                    <a href="#" class="yaksha-logo my-auto">
                  E<span>ASSESS</span>
                    </a>
                    <nav class="">
                        <button class="nav-bar-close" type="button">
                            <i class="fa fa-close"></i>
                        </button>
                        <a href="dashboardnew">
                            Dashboard
                        </a>
                        <a href="questionssnew" >
                            Question Bank
                        </a>
                        <a href="testsnew">
                            Tests
                        </a>
                       <a href="#" class="view-more active">
                            Recomm Settings<i class="fa fa-angle-down"></i>
                        </a>
                        <div class="more-nav">
                            <div class="more-nav">
                            <a href="newSkills">
                                Skills
                            </a>
                            <a href="newShowClusters">
                                Cluster Management
                            </a>
                            <a href="newShowAllResultsforMFA">
                                Multi-file Test Results
                            </a>
                            <a href="newDomainReport">
                                Domain based Results
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Modules Management
                            </a>
                            <a href="newLicenses">
                                License Management
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Results
                            </a>
                            
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Skill based Reports
                            </a>
                            <a href="recomms">
                                Recomm Settings
                            </a>
                            <a href="newSingleFileSessions">
                                Single File Test Reports
                            </a>
                            <a href="newListTestLinks">
                                Test Links Management
                            </a>
                            <a href="newLmsAdmins">
                                LMS Admin Users
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Job Description Management
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Test Domain Attemps Mangement
                            </a>
                            <a href="javascript:notify('Coming Soon! User the old Admin UI now')">
                                Schedule Management
                            </a>
                            <a href="newVerification">
                                Verify Data
                            </a>
                            <a href="newListTenants">
                                Tenant Management
                            </a>
                            <a href="newListUsers">
                                Users
                            </a>
							<a href="showCampaigns">
                                Campaigns
                            </a>
							<a href="reviewers">
                                 Campaign Reviewers
                            </a>
							<a href="showMeetings">
                                Meetings
                            </a>
                        </div>
                        </div>
                    </nav>
                    <div class="user-info">
                        <span class="my-auto">EASSESS, Admin</span>
                        <div class="thumbnail my-auto">
                            <span>TJ</span>
                        </div>
                        <button class="sign-out" type="button">
                            <i class="fa fa-sign-out"></i>
                        </button>
                    </div>
                </div>
            </header>
            <section class="content-section">
                <div class="container-fluid">
                    <div class="page-header mb-4">
                        <h1 class="my-auto">Recomm Settings</h1>
                        <div class="quick-actions my-auto">
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
                                Create New Setting
                            </button>
                        </div>
                    </div>
                    <div class="row">
                       
                        <div class="col-12 mb-3">
                            <ul class="alphabet-filter">
                            	<c:forEach  items="${characters}" var="character" varStatus="loop">   
<%--                             	<c:set var="cc" value="${character}"/> --%>
	                                <c:choose>
											  <c:when test="${selected == character}">
											   <li>
				                                    <a href="recomms?character=${character }" style="background-color: darkgray">${character}</a>
				                                </li>
											  </c:when>
											  <c:otherwise>
											    <li>
				                                    <a href="recomms?character=${character }">${character}</a>
				                                </li>
											  </c:otherwise>
									</c:choose>
                                
                              
                                 </c:forEach>
                            </ul>
                        </div>
                        	<c:forEach  items="${params}" var="prm" varStatus="loop">   
                        <div class="col-12 mb-3">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
										
                                         <li class="breadcrumb-item active">${prm.qualifier1}</li>
									      <li class="breadcrumb-item active">${prm.qualifier2}</li>
									      <li class="breadcrumb-item active">${prm.qualifier3}</li>
									      <li class="breadcrumb-item active">${prm.qualifier4}</li>
									      <li class="breadcrumb-item active">${prm.qualifier5}</li>
										  <a href="#" onclick="javascript:editRecomm('${prm.qualifier1}-${prm.qualifier2}-${prm.qualifier3}-${prm.qualifier4}-${prm.qualifier5}')">   &nbsp;&nbsp;&nbsp;- Click to Edit
										  </a>
                                </ol>
                            </nav>
                        </div>
<%--                         ${params} --%>
                        <div class="col-xs-12 col-md-4 col-xl-2 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <a href="#" data-toggle="modal" data-target="#exampleModalCenter">
                                    ${prm.LESS_THAN_TWENTY_PERCENT}
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4 col-xl-2 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <a href="#" data-toggle="modal" data-target="#exampleModalCenter">
                                      ${prm.BETWEEN_TWENTY_AND_FIFTY}
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4 col-xl-2 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <a href="#" data-toggle="modal" data-target="#exampleModalCenter">
                                      ${prm.BETWEEN_FIFTY_AND_SEVENTYFIVE}
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4 col-xl-2 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <a href="#" data-toggle="modal" data-target="#exampleModalCenter">
                                      ${prm.BETWEEN_SEVENTYFIVE_AND_NINETY}
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4 col-xl-2 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <a href="#" data-toggle="modal" data-target="#exampleModalCenter">
                                      ${prm.MORE_THAN_NINETY}
                                    </a>
                                </div>
                            </div>
                        </div>
                        </c:forEach>
<!--                         <div class="col-12 text-center mt-5"> -->
<!--                             <ul class="custom-pagination"> -->
<!--                                 <li class="page-item"> -->
<!--                                     <a class="page-link" href="#">Previous</a> -->
<!--                                 </li> -->
<!--                                 <li class="page-item"> -->
<!--                                     <a class="page-link">1 / 10</a> -->
<!--                                 </li> -->
<!--                                 <li class="page-item"> -->
<!--                                     <a class="page-link" href="#">Next</a> -->
<!--                                 </li> -->
<!--                             </ul> -->
<!--                         </div> -->
                    </div>
                </div>
            </section>
            <footer>
                <div class="container-fluid text-right">
                    <span class="copyright">
                        &copy; Copyright 2020-2021 - eAssess
                    </span>
                    <a href="#">
                        Terms and Conditions
                    </a>
                    <a href="#">
                        Privacy Policy
                    </a>
                </div>
            </footer>
            <div class="drop-down-bg"></div>
            <div class="modal fade bd-example-modal-lg" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Settings Update</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                             <form name="profileForm"  method="post" modelAttribute="params2" action="newSaveProfileParams">
                        <div class="modal-body">
                                <div class="row">
                                    <div class="col-xs-12 col-md-6">
                                    
                                     <div class="form-group mb-2">
                                            <label for="">Select Context</label>
                                            <form:select id="context" path="params2.context" onchange="changeContext()" class="form-control">
						   				<option>----Select Context----</option>
														 <form:options items="${qualifiers}"  />
													</form:select>
<!--                                             <input type="text" name="" id="" disabled class="form-control"> -->
                                        </div>
                                        
                                        <div class="form-group mb-2">
                                            <label for="">Qualifier 1</label>
                                            <form:input path="params2.qualifier1" name="qualifier1" id="qualifier1" readonly="true" class="form-control"/>
<!--                                             <input type="text" name="" id="" disabled class="form-control"> -->
                                        </div>
                                        <div class="form-group mb-2">
                                            <label for="">Qualifier 2</label>
                                            <form:input path="params2.qualifier2" name="qualifier2" id="qualifier2" readonly="true" class="form-control"/>
<!--                                             <input type="text" name="" id="" disabled class="form-control"> -->
                                        </div>
                                        <div class="form-group mb-2">
                                            <label for="">Qualifier 3</label>
                                            <form:input path="params2.qualifier3" name="qualifier3" id="qualifier3" readonly="true" class="form-control"/>
<!--                                             <input type="text" name="" id="" disabled class="form-control"> -->
                                        </div>
                                        <div class="form-group mb-2">
                                            <label for="">Qualifier 4</label>
                                            <form:input path="params2.qualifier4" name="qualifier4" id="qualifier4" readonly="true" class="form-control"/>
<!--                                             <input type="text" name="" id="" disabled class="form-control"> -->
                                        </div>
                                        <div class="form-group">
                                            <label for="">Qualifier 5</label>
                                            <form:input path="params2.qualifier5" name="qualifier5" id="qualifier5" readonly="true" class="form-control"/>
<!--                                             <input type="text" name="" id="" disabled class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-6">
                                        <div class="form-group mb-2">
                                            <label for="">Less than 20%</label>
                                            <form:textarea path="params2.LESS_THAN_TWENTY_PERCENT" name="LESS_THAN_TWENTY_PERCENT" id="LESS_THAN_TWENTY_PERCENT" rows="3" class="form-control" cols="60"/>
<!--                                             <textarea name="" id="" rows="3" class="form-control"></textarea> -->
                                        </div>
                                        <div class="form-group mb-2">
                                            <label for="">Between 20% and 50%</label>
                                            <form:textarea path="params2.BETWEEN_TWENTY_AND_FIFTY" name="BETWEEN_TWENTY_AND_FIFTY" id="BETWEEN_TWENTY_AND_FIFTY" rows="3" class="form-control" cols="60"/>
<!--                                             <textarea name="" id="" rows="3" class="form-control"></textarea> -->
                                        </div>
                                        <div class="form-group mb-2">
                                            <label for="">Between 50% and 70%</label>
                                            <form:textarea path="params2.BETWEEN_FIFTY_AND_SEVENTYFIVE" name="BETWEEN_FIFTY_AND_SEVENTYFIVE" id="BETWEEN_FIFTY_AND_SEVENTYFIVE" rows="3" class="form-control" cols="60"/>
<!--                                             <textarea name="" id="" rows="3" class="form-control"></textarea> -->
                                        </div>
                                        <div class="form-group mb-2">
                                            <label for="">Between 70% and 90%</label>
                                            <form:textarea path="params2.BETWEEN_SEVENTYFIVE_AND_NINETY" name="BETWEEN_SEVENTYFIVE_AND_NINETY" id="BETWEEN_SEVENTYFIVE_AND_NINETY" rows="3" class="form-control" cols="60"/>
<!--                                             <textarea name="" id="" rows="3" class="form-control"></textarea> -->
                                        </div>
                                        <div class="form-group mb-2">
                                            <label for="">Above 90%</label>
                                            <form:textarea path="params2.MORE_THAN_NINETY" name="MORE_THAN_NINETY" id="MORE_THAN_NINETY" rows="3" class="form-control" cols="60"/>
<!--                                             <textarea name="" id="" rows="3" class="form-control"></textarea> -->
                                        </div>
                                    </div>
                                </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save changes</button>
                        </div>
                            </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $('button.nav-bar, button.nav-bar-close').on('click', function () {
                $(this).closest('.container-fluid').find('nav').toggleClass('open');
                $('.drop-down-bg').toggleClass('open');
            });
            $('a.view-more').on('click', function () {
                var angle = $(this).find('.fa');
                if(angle.hasClass('fa-angle-down')) {
                    $(this).closest('nav').find('.more-nav').addClass('open');
                    $('.drop-down-bg').addClass('open');
                    angle.removeClass('fa-angle-down').addClass('fa-angle-up');
                } else {
                    $('.drop-down-bg').removeClass('open');
                    $(this).closest('nav').find('.more-nav').removeClass('open');
                    angle.addClass('fa-angle-down').removeClass('fa-angle-up');
                }
            });
            $('.drop-down-bg').on('click', function(){
                $(this).closest('body').find('.open').removeClass('open');
                if($('a.view-more i.fa').hasClass('fa-angle-up')) {
                    $('a.view-more i.fa').addClass('fa-angle-down').removeClass('fa-angle-up');
                }
            });
            $('.admin-view').on('click', function(){
                var options = $(this).closest('.actions').find('.options');
                if(options.hasClass('open')) {
                    options.removeClass('open');
                } else {
                    options.addClass('open');
                }
            });

       	 function changeContext(){
     		var  selectedValue= $("#context").val();
     		console.log(selectedValue);
     		selectedValue = encodeURIComponent(selectedValue);
     		console.log(selectedValue);
     		$.get("getProfileParams?qual="+selectedValue, function(data, status){
			$("#qualifier1").val(data.params3.qualifier1);
			$("#qualifier2").val(data.params3.qualifier2);
			$("#qualifier3").val(data.params3.qualifier3);
			$("#qualifier4").val(data.params3.qualifier4);
			$("#qualifier5").val(data.params3.qualifier5);
			console.log(data.params3);
			$("#LESS_THAN_TWENTY_PERCENT").val(data.params3.less_THAN_TWENTY_PERCENT);
			$("#BETWEEN_TWENTY_AND_FIFTY").val(data.params3.between_TWENTY_AND_FIFTY);
			$("#BETWEEN_FIFTY_AND_SEVENTYFIVE").val(data.params3.between_FIFTY_AND_SEVENTYFIVE);
			$("#BETWEEN_SEVENTYFIVE_AND_NINETY").val(data.params3.between_SEVENTYFIVE_AND_NINETY);
			$("#MORE_THAN_NINETY").val(data.params3.more_THAN_NINETY);
     		});
     			
     		}
			
			function editRecomm(recomm){
// 			console.log(recomm);
			var selectedValue = recomm;
			$.get("getProfileParams?qual="+selectedValue, function(data, status){
				console.log(data.params3.context);
				$("#context").val(data.params3.context);
				$("#qualifier1").val(data.params3.qualifier1);
				$("#qualifier2").val(data.params3.qualifier2);
				$("#qualifier3").val(data.params3.qualifier3);
				$("#qualifier4").val(data.params3.qualifier4);
				$("#qualifier5").val(data.params3.qualifier5);
				console.log(data.params3);
				$("#LESS_THAN_TWENTY_PERCENT").val(data.params3.less_THAN_TWENTY_PERCENT);
				$("#BETWEEN_TWENTY_AND_FIFTY").val(data.params3.between_TWENTY_AND_FIFTY);
				$("#BETWEEN_FIFTY_AND_SEVENTYFIVE").val(data.params3.between_FIFTY_AND_SEVENTYFIVE);
				$("#BETWEEN_SEVENTYFIVE_AND_NINETY").val(data.params3.between_SEVENTYFIVE_AND_NINETY);
				$("#MORE_THAN_NINETY").val(data.params3.more_THAN_NINETY);
				 $("#exampleModalCenter").modal("show");
			});
		}
        </script>
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