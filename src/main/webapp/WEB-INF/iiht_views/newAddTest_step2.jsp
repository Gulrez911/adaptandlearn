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
        <link href="https://fonts.googleapis.com/new/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
                <link rel="stylesheet" href="new/css/font-awesome.min.css">
        <link rel="stylesheet" href="new/css/bootstrap.min.css">
        <link rel="stylesheet" href="admindashboard/css/style.css">
		
		 <link rel="stylesheet" href="new/css/style.css">
        <link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
    
        <link rel="stylesheet" href="admindashboard/css/font-awesome.min.css">
        <link rel="stylesheet" href="admindashboard/css/bootstrap.min.css">
        <link rel="stylesheet" href="admindashboard/css/style.css">
	
        <style type="text/css">
        .mybtn{
            padding: 6px 11px;
        }
         </style>
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
             <section class="main-section">
                <div class="container-fluid">
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
                                <div class="col-12 " id="step-2">
                                    <div class="steps-form">
                                        <div class="row" style="overflow: scroll;max-height: 800px">
                                            <div class="col-12 mb-4">
                                                <div class="page-heading">
                                                    <h2>Import Questions into Section</h2>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-md-4 mb-2">
                                                <p>Sections
                                                 <button class="input-group-text text-success float-right mb-2" onclick="window.location='newAddNewSection'" >
                                                            <i class="fa fa-plus"></i>
                                                        </button>
                                                </p>
                                                <c:forEach items="${test.sectionDtos}" var="section" >
									          <div class="input-group mt-2">
                                                    <button class="btn btn-primary btn-block mybtn" style="width: 221px;" onclick="highlight('${section.sectionName}');return false">${section.sectionName}</button>
                                                    <div class="input-group-append">
                                                        <span class="input-group-text">
                                                            x ${section.noOfQuestions} 
                                                        </span>
                                                        <button class="input-group-text text-danger" onclick="removeSection('${section.sectionName}');">
                                                            <i class="fa fa-minus"></i>
                                                        </button>
                                                    </div>
                                                </div>
											    
											</c:forEach>
                                                
                                            </div>
                                            <div class="col-xs-12 col-md-8 mb-2">
                                                <div class="row ">
                                                    <div class="col-xs-12 col-md-5">
                                                    <form action="newSearchQs" method="get">
                                                        <div class="input-group  ">
                                                            <input type="text" class="form-control" placeholder="Search Questions" name="searchText" id="searchText">
                                                            <div class="input-group-append">
                                                                <button class="input-group-text text-white bg-primary" id="search">
                                                                    <i class="fa fa-search" ></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                        </form>
                                                    </div>
                                                    <div class="col-xs-12 col-md-3">
													 <p>Percent of Qs to be asked</p>
                                                    </div>
                                                    <div class="col-xs-12 col-md-3">
                                                        <div class="input-group">
                                                            <input type="number"  onKeyPress="return check(event,value)" onInput="checkLength()"  id="txtF" placeholder="Enter percentage" style="width: 7%;" value="${sectionDto.percentQuestionsAsked}" class="form-control"/>
                                                            <div class="input-group-append">
                                                                <span class="input-group-text">
                                                                    %
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row mb-3">
                                                    <div class="col-xs-12 col-md-6">
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" placeholder="Enter Section Name" value="${sectionDto.sectionName}"   id="sectionTopic">
                                                             
                                                        </div>
                                                    </div>
                                                    
                                                     <div class=" ">
                                                        <div class=" ">
                                                            <button class="btn btn-primary mybtn" type="button"onclick="saveSection()">Save Section</button>
                                                            <button class="btn btn-success  mybtn" type="button" onclick="showSelected();" id="showSelected">Show Selected</button>
                                                             
                                                        </div>
                                                    </div>
                                               
                                               </div>
                                                    
                                                <div class="row">
                                                    <div class="col-12 mb-2">
                                                        <div class="outline-block" style="height: 100px;overflow: scroll;padding: 4px">
                                                            <div class="user-detail my-auto" >
                                                               <c:forEach  items="${qs}" var="ques" >   
                                                                <p id="qCategory" class="text_div">
                                                             ${ques.testCategory} 
                                                                </p>
                                                                 </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     
                                                           <c:forEach  items="${qs1}" var="ques" varStatus="count">
                                                         <c:if test="${count.index==0}">
			                                                    <div class="col-xs-12 col-md-6 mb-3">
			                                                        <div class="outline-block category">
			                                                            <div class="user-detail my-auto">
			                                                                <p>
			                                                                   ${ques.testCategory} 
			                                                                </p>
			                                                                <p>
			                                                                    <span>Questions: <strong>${size}</strong></span>
			                                                                </p>
			                                                            </div>
			                                                            <div class="quick-action">
			                                                            <button class="btn-icon bg-success text-white" type="button" id="addAll" onclick="addAll()" >
			                                                                    <i class="fa fa-plus"></i>
			                                                                </button>
			                                                                <p style="display: none;">${ques.testCategoryToAddQs}</p>
			                                                                <button class="btn-icon bg-danger text-white" type="button" onclick="removeAll()">
			                                                                    <i class="fa fa-minus"></i>
			                                                                </button>
			                                                                <p style="display: none;" >${ques.testCategoryToRemoveQs}</p>
			                                                            </div>
			                                                        </div>
			                                                    </div>
                                                         </c:if> 
                                                    <div class="col-xs-12 col-md-6 mb-3">
                                                        <div class="outline-block">
                                                            <div class="user-detail my-auto">
                                                                <p>
                                                                 <c:out value="${ques.questionText}"></c:out>
                                                                </p>
                                                                <p>
                                                                    <span>${ques.testCategory}</span>
                                                                    <span>Difficulty: <strong>${ques.difficultyLevel.level}</strong></span>
                                                                    <span>Weight: <strong><c:out value="${ques.questionWeight == null?'1':ques.questionWeight}"></c:out> </strong></span>
                                                                </p>
                                                            </div>
                                                            <div class="quick-action">
                                                                <button class="btn-icon bg-success text-white "  style="${ques.selected? 'display: none;':''}" type="button" onclick="addQ('${ques.id}', 'Core Java');" id="${ques.id}-add">
                                                                    <i class="fa fa-plus"></i>
                                                                </button>
                                                                <button class="btn-icon bg-danger text-white "  style="${ques.selected? '':'display: none;'}" type="button" onclick="removeQ('${ques.id}', 'Core Java');" id="${ques.id}-remove">
                                                                    <i class="fa fa-minus"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </c:forEach>
                                                   
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 mt-5">
                                    <div class="steps-form">
                                        <div class="form-actions">
                                            <span>
                                                <button class="btn btn-outline-secondary mr-3" type="button" onclick="window.location.href = 'testsnew'">
                                                    Cancel
                                                </button>
                                                <button class="btn btn-secondary back" type="button" onclick="window.location.href = 'newGobackStep1Test'">
                                                    Back
                                                </button>
                                            </span>
                                            <button class="btn btn-primary next" type="button" onclick="window.location.href = 'newAddteststep3'">
                                                Next
                                            </button>
                                             
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                   
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
        </div>
        <script src="new/js/jquery.min.js"></script>
        <script src="new/js/bootstrap.min.js"></script>
        <script src="new/js/scripts.js"></script>
               	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
		
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
		
	$('a[href^="search').each(function(){ 
            var oldUrl = $(this).attr("href"); // Get current url
            var newUrl = oldUrl.replace("searchQBy", "newSearchQBy"); // Create new url
            $(this).attr("href", newUrl); // Set herf value
        });			
 
	$('a[href^="addQuestionsBy').each(function(){ 
        var oldUrl = $(this).attr("href"); // Get current url
        var newUrl = oldUrl.replace("addQuestionsBy", "newAddQuestionsBy"); // Create new url
        $(this).attr("href", newUrl); // Set herf value
    });	

	$('a[href^="removeQuestionsBy').each(function(){ 
        var oldUrl = $(this).attr("href"); // Get current url
        var newUrl = oldUrl.replace("removeQuestionsBy", "newRemoveQuestionsBy"); // Create new url
        $(this).attr("href", newUrl); // Set herf value
    });	

	function addAll(){
// 		alert("hi")
		$('#addAllQId')[0].click();
		}

	function removeAll(){
		$('#removeAllQId')[0].click();
		}
		
function saveSection(){
	var name = document.getElementById('sectionTopic').value;
	var txtFValue = document.getElementById('txtF').value;
		if(name.trim().length == 0){
			notify('Information', 'Please enter a meaningful name for your section before saving. ');
			
		}
		else{
			window.location = 'newSaveSection?sectionTopic='+name+'&percentage='+txtFValue;
			
		}
	
}

function addQ(qid, sectionName){
		
			    
		//window.location = "addQuestionToSection?sectionName="+sectionName+"&questionId="+qid;
		var url = "addQuestionToSectionAjax?sectionName="+sectionName+"&questionId="+qid;
		console.log('here url '+url);
		$.ajax({
				url : url,
				success : function(data) {
					console.log("SUCCESS: ", data);
// 					var tr = document.getElementById(qid);
// 					var tds = tr.getElementsByTagName("td");
					var tdadd = document.getElementById(qid+"-add");
					var tdremove = document.getElementById(qid+"-remove");
// 					console.log(tr);
// 					console.log(tds);
					console.log(tdadd);
// 					tr.style.backgroundColor = '#33FFF9';
					tdadd.style.display = "none";
					tdremove.style.display = "";
					
					//document.getElementById("no-"+sectionName).innerHTML = data;
					
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				}
			});
		    
	}

function removeQ(qid, sectionName){
	//window.location = "removeQuestionToSection?sectionName="+sectionName+"&questionId="+qid;
	    var url = "removeQuestionToSectionAjax?sectionName="+sectionName+"&questionId="+qid;
		console.log('here url '+url);
		$.ajax({
				url : url,
				success : function(data) {
					console.log("SUCCESS: ", data);
// 					var tr = document.getElementById(qid);
// 					var tds = tr.getElementsByTagName("button");
					var tdadd = document.getElementById(qid+"-add");
					var tdremove = document.getElementById(qid+"-remove");
// 					console.log(tr);
// 					console.log(tds);
					console.log(tdadd);
// 					tr.style.backgroundColor = 'transparent';
					tdadd.style.display = "";
					tdremove.style.display = "none";
					
					//document.getElementById("no-"+sectionName).innerHTML = data;
					
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				}
			});
}

function showSelected(){
	//$.ajax({url: "showSectionsQuestions", success: function(result){
	//		notify("Success", "All Questions Selected so far..");
	//	    }});
	window.location = "newShowSectionsQuestions";
}

function highlight(sectionName){
window.location = "newGoToSection?sectionName="+sectionName;
}

function removeSection(sectionName) {
	 Swal.fire({
	  title: 'Are you sure?',
	  text: "You want to delete this Section ?",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Yes, delete it!'
	}).then((result) => {
	  if (result.value) {
		  window.location = "newRemoveSection?sectionName="+sectionName;
	  }
	})
}


function notify(messageType, message){
		 var notification = 'Information';
			 $(function(){
				 new PNotify({
				 title: notification,
				 text: message,
				 type: messageType,
				 styling: 'bootstrap3',
				 hide: true
			     });
			 }); 	
		}


$('#search').on('click',function(){
	    var text = document.getElementById("searchText").value;
		if(text.length != 0){
		window.location="newSearchQs?searchText="+text;
		}
	    });

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