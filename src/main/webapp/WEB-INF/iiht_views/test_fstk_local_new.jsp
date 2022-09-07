<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>eAssess</title>
      <link href="images/E-assess_E.png" rel="shortcut icon">
      	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Open+Sans+Condensed:wght@300&display=swap">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="css_new/bootstrap.min.css">
	<link rel="stylesheet" href="css_new/styles_new.css">
	<link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link href="css/font-awesome_new.css" rel="stylesheet" type="text/css">
	<link href="css_new/responsive_new.css" rel="stylesheet" type="text/css">
	<link href="css/style_testjourney.css" rel="stylesheet" type="text/css">
	<link href="css_new/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.nonblock.js"></script>
	<script type="text/javascript" src="scripts/pnotify.buttons.js"></script>
</head>
    <body class="post-login">
		<form>
			<header>
				<div class="container-fluid pt-2 pb-2">
					<h1>
						${studentTestForm.testName}
						<div class="text-right company">
							${studentTestForm.userName} - ${currentQuestion.questionMapperInstance.questionMapper.question.companyName}
						</div>
						<a href="signoff" style="display:none" id="signoff">Sign Off</a>
					</h1>
					<!-- <div class="timing">
						<span class="my-auto">Time Remaining</span>
						<div>
							<input type="text" class="text-center mr-1" id="hours" disabled maxlength="2" value="00">
							<input type="text" class="text-center mr-1" id="min" disabled maxlength="2" value="00">
							<input type="text" class="text-center" id="sec" disabled maxlength="2" value="00">
						</div>
					</div> -->
				</div>
			</header>
			<section>
				<ul class="section-lists">
					<c:forEach var="sectionInstance" varStatus="status" items="${sectionInstanceDtos}">
						<li ${sectionInstance.style} onclick="javascript:changeSection('${sectionInstance.section.sectionName}');">
							<a href="#">
								${sectionInstance.section.sectionName}
							</a>
						</li>
					</c:forEach>
				</ul>
				<div class="main-content multifile mb-3">
					<div class="question my-auto mb-2">
						<span class="mr-2">01</span>
						<div>
							${currentQuestion.questionMapperInstance.questionMapper.question.questionText}
						</div>
					</div>
				</div>
				<div class="descriptions multifile mb-3">
					<div class="description-content">
						<h4>Description</h4>
						<p>${(currentQuestion.questionMapperInstance.questionMapper.question.instructionsIfAny==null || currentQuestion.questionMapperInstance.questionMapper.question.instructionsIfAny.trim().length()== 0)?"<p>&#8226; Do not move mouse pointer to a different tab  </p><p>&#8226; Use F11 windows for Test if required   </p><p >&#8226; Non Compliance can result in your Test Declared Invalid   </p>":currentQuestion.questionMapperInstance.questionMapper.question.instructionsIfAny}</p>
						
						<h3>Constraints</h3>
						<p>${currentQuestion.questionMapperInstance.questionMapper.question.constrnt == null || currentQuestion.questionMapperInstance.questionMapper.question.constrnt.length() == 0?"NA":currentQuestion.questionMapperInstance.questionMapper.question.constrnt}</p>
							   
						<p>
							<a href="javascript:showAndNavigate();">
								<i class="material-icons">
									insert_drive_file
								</i>
								Download project documentation template
							</a>
						</p>
						<c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl != null && currentQuestion.questionMapperInstance.questionMapper.question.imageUrl.trim().length() > 0}">
							<img src="${currentQuestion.questionMapperInstance.questionMapper.question.imageUrl}">
						</c:if>
						<c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.audioURL != null && currentQuestion.questionMapperInstance.questionMapper.question.audioURL.trim().length() > 0}">
							<audio controls src="${currentQuestion.questionMapperInstance.questionMapper.question.audioURL}">
								Your browser does not support the <code>audio</code> element.
							</audio>
						</c:if>

						<c:if test = "${currentQuestion.questionMapperInstance.questionMapper.question.videoURL != null && currentQuestion.questionMapperInstance.questionMapper.question.videoURL.trim().length() > 0}">
							<video controls>
								<source src="${currentQuestion.questionMapperInstance.questionMapper.question.videoURL}" >
								Your browser does not support the video tag.
							</video>
						</c:if>

								
						
							
								<div class="form-row" id="auth2">
									 <div class="form-group col-md-12">
									 <span class="label label-primary" >Enter your Github User</span>
									
									  <input type="text" id="gitid" name="gitid" class="form-control" placeholder="Enter your Github user"> 
										<select name="repos" id="repos" class="form-control" >
											<option value="NA">NA</option>
									 
										</select>
									<button type="button" id="fetchRepos" onclick="javascript:fetchRepositories();" class="btn btn-secondary" >
										Fetch Repos
									</button>
									</div>
									
									
									
									
								</div>
								
								<div class="form-row" id="auth3" style="display:none">
									 <div class="form-group col-md-12">
									 <span class="label label-primary" >Enter your Github URL</span>
									
									  <input type="text" id="giturl" name="giturl" class="form-control" placeholder="Enter your Github URL"> 
										
									<button type="button" id="saveURL" onclick="javascript:saveGitURL();" class="btn btn-secondary" >
										Save Your Git workspace URL.
									</button>
									</div>
									
									
									
									
								</div>
							
							
									
						
					</div>
				</div>
				<div class="text-right p-3">
					<button type="button" class="btn btn-primary" id="submitGithubCode" onclick="confirmWorkspace('${currentQuestion.questionMapperInstance.id}');" disabled>
						Submit my GIThub Workspace
					</button>
				</div>
			</section>
			<footer>
				<div class="container-fluid pt-3 pb-3">
					<span>
						&copy; Copyright 2020 :: Yaksha :: Powered by IIHT
					</span>
					<a href="javascript:void(0)" class="mr-5">
						Terms and Conditions
					</a>
					<a href="javascript:void(0)">
						Privacy Policy
					</a>
				</div>
			</footer>
		</form>
		<script>
		var randomNumber = Math.floor(Math.random() * (6 - 1 + 1)) + 1;

		$('header').attr('style', "background-image: url(images_new/banner_" + randomNumber + ".jpg);");
		
		$(function () {
                $("#upload").on('click', function (e) {
                    e.preventDefault();
                    $("#addimage").trigger('click');
                });
               
            });
	
	 $('#addimage').change(function () {
                var file = $('#addimage')[0].files[0].name;
                $('.queimage').text('Document to upload: '+file);
				uploadProjectDocs();
            });
	
	
	function get_center_pos(width, top){
    // top is empty when creating a new notification and is set when recentering
		if (!top){
			top = 30;
			// this part is needed to avoid notification stacking on top of each other
			$('.ui-pnotify').each(function() { top += $(this).outerHeight() + 20; });
		}

		return {"top": top, "left": ($(window).width() / 2) - (width / 2)}
	}
	
	
	
	
	function storeTimeLocal(){
	localStorage.setItem('${studentTestForm.firstName}${studentTestForm.lastName}', 'yes');
	localStorage.setItem('testName-${studentTestForm.firstName}${studentTestForm.lastName}', '${studentTestForm.firstName}${studentTestForm.lastName}-'+

'${studentTestForm.testName}');
	localStorage.setItem('timeCounter-${studentTestForm.firstName}${studentTestForm.lastName}', timeCounter);
	}
	
	function resetTimeLocal(){
	localStorage.setItem('${studentTestForm.firstName}${studentTestForm.lastName}', 'no');
	localStorage.setItem('testName-${studentTestForm.firstName}${studentTestForm.lastName}', null);
	localStorage.setItem('timeCounter-${studentTestForm.firstName}${studentTestForm.lastName}', 0);
	}
	
	
	
	function confirmWorkspace(qMapperInstanceId){
		(new PNotify({
		    title: 'Confirmation Needed',
		    text: 'Are you sure you have uploaded the project documentation. Your reviewer may take a dim view of your efforts if no documentation is found? If yes you can submit the workspace?',
		    icon: 'glyphicon glyphicon-question-sign',
		    hide: false,
		    confirm: {
			confirm: true
		    },
		    buttons: {
			closer: true,
			sticker: true
		    },
		    history: {
			history: false
		    }
		})).get().on('pnotify.confirm', function() {
		    submitFullStackCode(qMapperInstanceId);
		}).on('pnotify.cancel', function() {
		   
		});
	}
	
	function showAndNavigate(){
		(new PNotify({
		    title: 'About this',
		    text: 'Yes to this will download a Project Documentation template that you are expected to update and then upload. Kindly make sure the How-to use part is crystal clear for your reviewer to gro through and start your app. Do you want to proceed now?',
		    icon: 'glyphicon glyphicon-question-sign',
		    hide: false,
		    confirm: {
			confirm: true
		    },
		    buttons: {
			closer: false,
			sticker: false
		    },
		    history: {
			history: false
		    }
		})).get().on('pnotify.confirm', function() {
		    window.open('https://ide.yaksha.online/file-server/assessment/Project_ReadMe.docx', '_blank');
		}).on('pnotify.cancel', function() {
		   
		});
	}
	
	
	function submitFullStackCode(qMapperInstanceId){
		
		var e = document.getElementById("repos");
		var selectedOp = e.options[e.selectedIndex].text;
		var gitRepository = encodeURIComponent(selectedOp);
		var url = 'submitLocalFullStackCode?qMapperInstanceId='+qMapperInstanceId+'&gitRepository='+gitRepository;
	
	var data = {}; 
	
	
		$.ajax({
				type : 'GET',
				url : url,
				contentType: "application/json; charset=utf-8",
				data: data,
				success : function(data) {
					console.log("SUCCESS: ", data);
					notify('Information', "Your codebase has been submitted successfully. You will be signed off in 30 seconds.");
					setTimeout(function(){document.getElementById("signoff").click()}, 30000);
					
				},
				error : function(e) {
					console.log("Contact System Admin - ERROR: ", e);
					
				}
			});
	}


		
	
	
	function uploadProjectDocs(){
		qMapperInstanceId = '${currentQuestion.questionMapperInstance.id}';
		 var formData = new FormData();
		  var file = $('#addimage')[0].files[0];
		  console.log('1 file is '+file);
		  if(file != null){
			formData.append('addimage', file);
            console.log("form data " + formData);
            $.ajax({
                url : 'uploadProjectDocs?qMapperInstanceId='+qMapperInstanceId,
				enctype: 'multipart/form-data',
                data : formData,
                processData : false,
                contentType : false,
                type : 'POST',
                success : function(data) {
                     notify('Information','Your file has been uploaded. Check this link <br\>-<a href="'+data+'" >Click here</a> ');
                },
                error : function(err) {
                   notify('Information',err);
                }
            });
		  }
		  else{
			  notify('Information', 'Kindly upload the Project Documentations word file');
		  }
            
	}
	
	
	function removeOptions(selectElement) {
	   var i, L = selectElement.options.length - 1;
	   for(i = L; i >= 0; i--) {
		  selectElement.remove(i);
	   }
	}
	
	
	var countGit = 0;
	function fetchRepositories(){
		var gituser = document.getElementById("gitid").value;
		
		if(gituser == null || gituser.trim().length < 4){
			notify('Info', 'Enter a valid Github User');
			return;
		}
		
		$.ajax({
				type : 'GET',
				url : 'publicRepositories?account='+gituser,
				contentType: "application/json; charset=utf-8",
				success : function(jsonArray) {
					removeOptions(document.getElementById('repos'));	
					//var jsonArray = JSON.parse(data);
						if(jsonArray != null && jsonArray.length == 1){
							first = jsonArray[0];
							if(first == 'NA_Fail_To_Connect'){
								countGit ++;
								if(countGit >= 4){
									notify('Info', 'It seems there is some problem fetching your repository. Enter & Save your public Github repositiory URL. Yaksha BackEnd team will fetch and analyze code on your behalf');	
									document.getElementById("auth2").style.display = "none";
									document.getElementById("auth3").style.display = "";
									document.getElementById("submitGithubCode").style.display = "none";
									
								}
								else{
									notify('Info', 'Invalid Github Repository. You can try up to 4 times. This is your '+countGit+' attempt');
								}
								
									
								
								return;
							}
						}
						
						if(jsonArray != null && jsonArray.length == 0){
							countGit ++;
							if(countGit >= 4){
								notify('Info', 'It seems there is some problem fetching your repository. Enter & Save your public Github repositiory URL. Yaksha BackEnd team will fetch and analyze code on your behalf');	
								document.getElementById("auth2").style.display = "none";
								document.getElementById("auth3").style.display = "";
								document.getElementById("submitGithubCode").style.display = "none";
							}
							else{
								notify('Info', 'It seems there are not Githib repositories for the Github user entered. You can try up to 4 times. You can try up to 4 times. This is your '+countGit+' attempt');
							}
							
							
							return;
						}
						
						for (var i = 0; i < jsonArray.length; i++) {
						  option = document.createElement('option');
						  option.text = jsonArray[i];
						  document.getElementById('repos').add(option);
						}
					document.getElementById("submitGithubCode").disabled = false;	
					
					notify('Info', 'Repositories fetched. Select your repository from the Repository Drop down. If you are sure to proceed, click on \'SUBMIT MY GITHUB WORKSPACE\' ');	
					},
					error : function(e) {
						notify('Info', 'Problem in fetching Repos');
						document.getElementById("submitGithubCode").disabled = true;	
					}
			});
	}
	
	
	function saveGitURL(){
		var instanceId = '${currentQuestion.questionMapperInstance.id}';
		var giturl = document.getElementById('giturl').value;
		giturl = encodeURIComponent(giturl);
		$.ajax({
				type : 'GET',
				url : 'saveGitUrl?instanceId='+instanceId+'&githubUrl='+giturl,
				contentType: "application/json; charset=utf-8",
				success : function(data) {
						notify('Information', "Your Github repository is saved successfully. You will be signed off in 20 seconds.");
					setTimeout(function(){document.getElementById("signoff").click()}, 20000);
					
					},
					error : function(e) {
						notify('Info', 'It looks like Github Api are not working as of now. Please connect with Admin');
						
					}
			});
	}

	</script>
		
		<script>
		// Get the modal
		var modal = document.getElementById('myModal');

		// Get the button that opens the modal
		var btn = document.getElementById("myBtn");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];
			
		// When the user clicks on <span> (x), close the modal
		//span.onclick = function() {
		    //modal.style.display = "none";
		//}	
		
		function notify(messageType, message){
		 var notification = 'Information';
		 //PNotify.prototype.options.styling = "jqueryui";
			 $(function(){
				 new PNotify({
				 title: notification,
				 text: message,
				 type: messageType,
				 width: '60%',
				 hide: false,
				 buttons: {
            				closer: true,
            				sticker: false
       					 },
				 history: {
            				history: false
        			 }
			 });
				 
			 }); 	
		}


		
	</script>
    </body>

</html>