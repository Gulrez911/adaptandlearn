<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://fonts.googleapis.com/new/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="new/css/font-awesome.min.css">

<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">

<script src="new/campaign/js/jquery.min.js"></script>
<script src="new/campaign/js/bootstrap.min.js"></script>
<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
<script type="text/javascript" src="scripts/custom.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">

<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="new/css/font-awesome.min.css">
<link rel="stylesheet" href="new/campaign/css/bootstrap.min.css">
<link rel="stylesheet" href="new/campaign/css/style.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<style>
/*  iframe {  */
/*             margin: 0px;  */
/*             padding: 0px;  */
/*             height: 100%;  */
/*             border: none;  */
/*         }  */
iframe {
	height: 750px;
	display: block;
	width: 100%;
	border: none;
	overflow-y: auto;
	overflow-x: hidden;
}

table, th, td {
	/*   border: 1px solid black; */
	border-collapse: collapse;
}

table.center {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<header>
		<div class="container-fluid">
			<button class="nav-bar" type="button">
				<i class="fa fa-bars"></i>
			</button>
			<a href="#" class="yaksha-logo my-auto"> E<span>ASSESS</span>
			</a>

		</div>
	</header>
	<iframe src="${url}" frameborder="0" marginheight="0" marginwidth="0"
		width="100%" height="700px%" scrolling="no"></iframe>
	<iframe src="${url3}" frameborder="0" marginheight="0" marginwidth="0"
		width="100%" height="700px%" scrolling="no"></iframe>
	<iframe src="${url2}" frameborder="0" marginheight="0" marginwidth="0"
		width="100%" height="500px%" scrolling="no"></iframe>

<!-- 	<hr style="width: 70%; border-top: 3px solid blue;" /> -->
<!-- 	<b style="margin-left: 450px">FEEDBACK AREA</b> -->
<!-- 	<hr style="width: 70%; border-top: 3px solid blue;" /> -->

<!-- 	<div class="container" -->
<!-- 		style="width: 70%; font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; border-bottom: 3px solid blue; border-top: 3px solid blue; padding-top: 11px; padding-bottom: 11px;"> -->
<%-- 		<form:form method="post" modelAttribute="reviewerComment" --%>
<%-- 			action="saveReviewerComment2" name="reviewerComment"> --%>
<%-- 			<form:hidden path="id" /> --%>
<%-- 			<form:hidden path="campaignName" /> --%>
<%-- 			<form:hidden path="campaignId" /> --%>
<%-- 			<form:hidden path="candidateEmail" /> --%>
<%-- 			<form:hidden path="candidateFirstName" /> --%>
<%-- 			<form:hidden path="candidateLastName" /> --%>
<%-- 			<form:hidden path="companyId" /> --%>
<%-- 			<form:hidden path="companyName" /> --%>
<!-- 			<div class="form-row"> -->
<!-- 				<div class="form-group col-md-6"> -->
<!-- 					<label for="technicalCompetencyInCoreAreas">Core Technical -->
<!-- 						Competency</label> -->
<%-- 					<form:input path="technicalCompetencyInCoreAreas" --%>
<%-- 						class="form-control" /> --%>
<!-- 				</div> -->
<!-- 				<div class="form-group col-md-6"> -->
<!-- 					<label for="technicalCompetencyInAncillaryAreas">Technical -->
<!-- 						Competency in Ancillary Areas</label> -->
<%-- 					<form:input path="technicalCompetencyInAncillaryAreas" --%>
<%-- 						class="form-control" /> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="form-row"> -->
<!-- 				<div class="form-group col-md-6"> -->
<!-- 					<label for="analyticalSkills">Analytical Skills</label> -->
<%-- 					<form:input path="analyticalSkills" class="form-control" /> --%>
<!-- 				</div> -->
<!-- 				<div class="form-group col-md-6"> -->
<!-- 					<label for="communicationSkills">Communiction Skills</label> -->
<%-- 					<form:input path="communicationSkills" class="form-control" /> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="form-row"> -->
<!-- 				<div class="form-group col-md-6"> -->
<!-- 					<label for="overallReviewStatus">OverAll Review of the -->
<!-- 						Candidate</label> -->
<%-- 					<form:input path="overallReviewStatus" class="form-control" /> --%>
<!-- 				</div> -->

<!-- 				<div class="form-group col-md-6"> -->
<!-- 					<label for="inputEmail4">Current Reviewer</label> -->
<%-- 					<form:input path="actualReviewerEmail" class="form-control" --%>
<%-- 						disabled="true" /> --%>
<!-- 				</div> -->

<!-- 			</div> -->



<!-- 			<div class="form-group"> -->
<!-- 				<div class="form-check"> -->
<%-- 					<form:checkbox path="status" class="form-check-input" --%>
<%-- 						id="gridCheck" /> --%>
<!-- 					<label class="form-check-label" for="gridCheck"> Shortlist -->
<!-- 						Candidate </label> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<button type="submit" class="btn btn-primary" -->
<!-- 				style="margin-left: 40%">Save your Feedback</button> -->
<%-- 		</form:form> --%>
<!-- 	</div> -->

	<script type="text/javascript">
		$(function() {
			/*  Submit form using Ajax */
			$('button[type=submit]')
					.click(
							function(e) {

								//Prevent default submission of form
								e.preventDefault();

								//Remove all errors
								$
										.post({
											url : 'saveReviewerComment',
											data : $(
													'form[name=reviewerComment]')
													.serialize(),
											success : function(res) {
												sweetAlert(
														"Your feedback has been saved. Kindly close th window.",
														res.msg, "success")

											}
										})
							});
		});

		function sweetAlert(msgtype, message, icon) {
			Swal.fire(msgtype, message, icon)
		}
	</script>
</body>
</html>