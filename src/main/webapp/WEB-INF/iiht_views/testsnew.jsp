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
        <link rel="stylesheet" href="admindashboard/css/font-awesome.min.css">
        <link rel="stylesheet" href="admindashboard/css/bootstrap.min.css">
        <link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="admindashboard/css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
	
    </head>
    <body>
	<%
	String filterKey = request.getParameter("filterkey");
	String filterValue = request.getParameter("filtervalue");
	System.out.println("filterkey is "+filterKey+" val is "+filterValue);
	String show = "";
		if(filterKey != null){
			show += "Test Type - "+filterKey.toUpperCase();
		}
		
		if(filterValue != null && filterKey != null){
			show += " Greater than "+filterValue.toUpperCase();
		}
	%>
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
                        <a href="questionssnew">
                            Question Bank
                        </a>
                        <a href="testsnew" class="active">
                            Tests
                        </a>
                        <a href="#" class="view-more">
                            More <i class="fa fa-angle-down"></i>
                        </a>
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
                    </nav>
                    <div class="user-info">
                        <span class="my-auto">EASSESS, Admin</span>
                        <div class="thumbnail my-auto">
                            <span><i class="fa fa-user-o"></i></span>
                        </div>
                        <button class="sign-out" type="button" onClick="javascript:signoff()">
                            <i class="fa fa-sign-out" ></i>
                        </button>
                    </div>
                </div>
            </header>
            <section class="content-section">
                <div class="container-fluid">
                    <div class="page-header mb-4">
                        <h1 class="my-auto">Tests</h1>
                        <div class="quick-actions my-auto">
                            <button class="btn btn-primary" onclick="window.location.href = 'newAddtest'" type="button">
                                Create New Test
                            </button>
                        </div>
                    </div>
                    <div class="contents">
                        <aside>
                            <div class="quick-search">
                                <form action="newSearchTests" method="get">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Search here..." name="searchText" id="searchText" value="${param.searchText}">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button" id="search"><i class="fa fa-search"></i></button>
                                            <button class="btn btn-secondary" type="button"><i class="fa fa-filter"></i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="filter-block mt-3">
                                <h2>Test Type</h2>
                                <ul>
                                    <li>
                                        <a href="filtertestsnew?filterkey=general">
                                            General Tests
                                        </a>
                                    </li>
                                    <li>
                                        <a href="filtertestsnew?filterkey=fullstack">
                                            Fullstack Tests
                                        </a>
                                    </li>
                                    <li>
                                        <a href="filtertestsnew?filterkey=confidence">
                                            Confidence Based Tests
                                        </a>
                                    </li>
                                    <li>
                                        <a href="filtertestsnew?filterkey=webproctoronboarding">
                                            Web Proctor Onboarding Tests
                                        </a>
                                    </li>
                                    <li>
                                        <a href="filtertestsnew?filterkey=recommendation">
                                            Recommendation Based Tests
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="filter-block mt-3">
                                <h2>Difficulty</h2>
                                <ul>
                                    <li>
                                        <a href="filtertestsnew?filterkey=VERY_EASY">
                                            very easy
                                        </a>
                                    </li>
                                    <li>
                                        <a href="filtertestsnew?filterkey=EASY">
                                            easy
                                        </a>
                                    </li>
                                    <li>
                                        <a href="filtertestsnew?filterkey=MEDIUM">
                                            medium
                                        </a>
                                    </li>
                                    <li>
                                        <a href="filtertestsnew?filterkey=DIFFICULT">
                                            hard
                                        </a>
                                    </li>
                                    <li>
                                        <a href="filtertestsnew?filterkey=VERY_DIFFICULT">
                                            very hard
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="filter-block mt-3 pass-filter">
                                <h2>
                                    Pass Percentage
                                </h2>
                                <ul>
                                    <li class="percentage p-3 pb-5">
                                        <input type="range" min="1" max="100" value="50" class="range" id="passRange">
                                        <div>
                                            <span class="rangeValue" id="rangevalue">
                                                50
                                            </span>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </aside>
                        <article>
                            <div class="filter-lists mb-3">
                                <c:forEach  items="${skills}" var="skill" varStatus="loop">
                                    <span class="keyword">
                                        <a href="javascript:searchBySkill('${skill}');">${skill}</a>
                                    </span>
                                </c:forEach>
                            </div>
                            <h3 class="mb-3 text-center"><%= show %></h3>
                            <div class="row">
                                <c:forEach  items="${tests}" var="test" varStatus="loop">
                                    <div class="col-xs-12 col-md-6 col-xl-4 mb-3">
                                        <div class="card">
                                            <div class="card-header">
                                                Skills: ${test.skillInstring}
                                                <div class="actions">
                                                    <a href="#" class="admin-view">
                                                        <i class="fa fa-ellipsis-h"></i>
                                                    </a>
                                                    <div class="options">
                                                        <a href="#" class="text-warning" onclick="window.location='newUpdateTest?testId=${test.id}'">
                                                            <i class="fa fa-edit mr-2"></i> Edit
                                                        </a>
                                                        <a href="#" class="text-danger" onclick="deleteTest(${test.id})">
                                                            <i class="fa fa-trash-o mr-2"></i> Delete
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <h5 class="card-title">${test.testName}</h5>
                                                <p class="card-text"><i class="fa fa-hourglass-1 mr-2"></i>Duration: <span class="text-primary">${test.testTimeInMinutes}</span></p>
                                                <p class="card-text"><i class="fa fa-graduation-cap mr-1"></i>Pass Percentage: <span class="text-primary">${test.passPercent}%</span></p>
                                                <p class="card-text"><i class="fa fa-rotate-left mr-2"></i>Attempts: <span class="text-primary">${test.noOfConfigurableAttempts == null?1:test.noOfConfigurableAttempts}</span></p>
                                            </div>
                                            <div class="card-footer text-muted">
                                                <ul>
                                                    <li>
                                                        <small>Created On</small> <br>
                                                        ${test.cDate}
                                                    </li>
                                                    <li>
                                                        <small>Updated On</small> <br>
                                                        ${test.uDate}
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                <div class="col-12 text-center mt-5">
                                    <ul class="custom-pagination">
                                        <c:if test="${showPreviousPage}">
                                            <li class="page-item">
                                                <a class="page-link" href="${callingMethod}?page=${previousPage}${queryParam}">Previous</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${selectedPage != null &&  selectedPage > 0}">
                                            <li class="page-item">
                                                <a class="page-link">${selectedPage} / ${totalNumberOfPages}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${showNextPage}">
                                            <li class="page-item">
                                                <a class="page-link" href="${callingMethod}?page=${nextPage}${queryParam}">Next</a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </article>
                    </div>
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
            <div class="drop-down-bg">
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Iste consequatur accusantium perspiciatis omnis blanditiis repudiandae enim officiis vel? Recusandae, blanditiis itaque? Quia aut architecto odio voluptatibus, repellat distinctio consequatur veniam.
            </div>
        </div>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="new/js/bootstrap.min.js"></script>
        	
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


			let params = (new URL(document.location)).searchParams;
			let percentage = params.get("percentage");
			console.log('params is '+params);
			console.log('percentage is '+percentage	);
				if(percentage != null){
					document.getElementById('rangevalue').innerHTML = percentage;
					document.getElementById('passRange').value = percentage;
					
				}
		
			$('#passRange').on('change', function () {
				var $this = $(this), val = $this.val(), $display = $this.closest('.percentage').find('.rangeValue');
				$display.html(val).attr('style', `left: calc(${val}% - 20px);`);
				var doc = document.getElementById('rangevalue');
				var val = document.getElementById('rangevalue').innerHTML;
				
				window.location = "filtertestsPercentnew?filterkey=percentage&filtervalue="+val;
			});
			
			function signoff(){
				window.location = 'signoff';
			}
					
			function searchBySkill(skill){
				skill = encodeURIComponent(skill);
				window.location = 'testsnew?skill='+skill;
			}
			
			function notify(msg){
				var notification = 'Information';
				$(function() {
				    Swal.fire(
			       'Information',
			       msg,
			       'warning'
			    )
			});
			}

			 $('#search').on('click',function(){
				    var text = document.getElementById("searchText").value;
					if(text.length != 0){
					window.location="newSearchTests?searchText="+text;
					}
				    });

				function deleteTest(id){
				 	 Swal.fire({
					  title: 'Are you sure?',
					  text: "You want to delete this Test ?",
					  icon: 'warning',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'Yes, delete it!'
					}).then((result) => {
					  if (result.value) {
						 window.location = "lmsRetireTest?testId=" + id;
					  }
					})
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