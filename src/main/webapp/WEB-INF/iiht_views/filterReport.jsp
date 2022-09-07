<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<html>
<head>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js"
	type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />

<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
<script type="text/javascript" src="scripts/custom.js"></script>

<style>

h1,h2,h3,h4,h5,h6,label,span,p,a,
input[type="submit"],
input[type="text"],
input[type="email"]{
/*     font-family: "Segoe UI" !important; */        I've only commented this line
}
</style>

</head>

<body>

	<div class="maincontainer">

		<div class="wrapper">
			<div class="row row-offcanvas row-offcanvas-left">
				<!-- sidebar -->
				<jsp:include page="side.jsp" />

				<!-- /sidebar -->

				<div class="column col-sm-10 col-xs-11" id="main">
					<div class="rightside">
						<div class="topmenu text-left">
							<a href="downloadTestReport"><img
								src="images/testsReport.png"> All Tests Report</a> <a
								href="downloadUserReport"><img src="images/usersReport.png">All
								User Sessions Report</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="signoff">Sign Off</a>
						</div>


						<div class="questiontable">
							<div class="questionheading">
								<div class="left">
									<h3>${reportType}</h3>



									<div class="filter"></div>
									<div class="filter"></div>

								</div>


								<div class="left" style="width: 30%">
									<form action="dateWiseReport" method="get">
										Start Date*: <input id="datepicker1" name="startDate"
											required="required" width="270" /><br/>
											End Date*: <input
											id="datepicker2" name="endDate" required="required"
											width="270" /><br/>

											Company List*:
										  <select
											class="form-control" name="companyId" onchange="Change()"
											id="name" style="width:270">
											<option>Select Company Name</option>
											<c:forEach items="${list}" var="company">
												<option value="${company.companyId}">${company.companyName}</option>
											</c:forEach>
										</select> <br/>
										Test List*:
										<select class="form-control"  id="slct" name="testName" required="required" style="width:270"></select><br/>
										<br>
										<input class="btn btn-primary"  type="submit" value="Download">

									</form>
								</div>
							</div>

							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
						</div>
					</div>
				</div>
				<!-- /main -->
			</div>
		</div>
	</div>

	<script>
	function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }
	
	function encodeAndSend(method, key, value, key1, value1){
		val = encodeURIComponent(value);
		val2 = null;
		if(value1 != null && value1.length > 0){
			if(value1 <=0 || value1 >=100){
				notify('Info', 'Enter a valid Pass Percentage number (Between 1 to 99)');
				return;
			}
			
			val2 = encodeURIComponent(value1);
			url = ''+method+'?'+key+'='+val+'&'+key1+'='+val2;
			window.location = url;
		}	
		else{
			notify('Info', 'Enter a valid Pass Percentage');
		}
		
		
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
		
		
	</script>
	<script>

					function Change() {
						var companyId = $('#name').val();
						console.log(companyId)
						$.ajax({
							url : "fetchTestList?companyId=" + companyId,
							type : 'GET',
							success : function(response) {
								console.log(response.listTest.length)
								$('.opt').remove();
								for (i = 0; i < response.listTest.length; i++) {
									console.log(response.listTest[i]);
									$("#slct").append(
											"<option class='opt'>" + response.listTest[i].testName
													+ "</option>");
								}
							},
						});
					}
</script>

	

	<c:if test="${msgtype != null}">
		<script>
		 var notification = 'Information';
		 $(function(){
			 new PNotify({
			 title: notification,
			 text: '${message}',
			 type: '${msgtype}',
			 styling: 'bootstrap3',
			 hide: true
		     });
		 }); 	 
	      </script>
	</c:if>

	<input id="datepicker" width="270" />
	<script>
        $('#datepicker1').datepicker({
            format: 'dd-mm-yyyy',
            uiLibrary: 'bootstrap'
        });
        $('#datepicker2').datepicker({
            format: 'dd-mm-yyyy',
            uiLibrary: 'bootstrap'
        });
    </script>
</body>
</html>
