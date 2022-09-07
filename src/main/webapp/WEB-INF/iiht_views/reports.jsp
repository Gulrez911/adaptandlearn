<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>eAssess</title>
      	<link href="images/E-assess_E.png" rel="shortcut icon">   
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	
	 <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
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
                                <a href="downloadTestReport"><img src="images/testsReport.png"> All Tests Report</a>
				 <a href="downloadUserReport"><img src="images/usersReport.png">All User Sessions Report</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="downloadReport"><img
								src="images/testsReport.png">Report by Filter</a>
                                <a href="signoff">Sign Off</a>
                            </div>
			    
			   
                            <div class="questiontable">
                                <div class="questionheading">
                                    <div class="left">
                                        <h3>${reportType}</h3>
                                    </div>
                                    <div class="right">
                                       
                                        <div class="filter">
                                          
					   
                                            
                                        </div>
					<div class="filter">
                                          
					   
                                        </div>
                                    </div>
                                </div>
                                <div class="questiontablelist" style="overflow-x:scroll;overflow-y:auto;">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th><b>No</b></th>
                                                <th><b>Test Title</b></th>
                                                
                                                <th><b>Sections</b></th>
                                                <th><b>Sessions</b></th>
						<th><b>Passed </b></th>
                                                <th><b>(Weighted if applicable) Average Score</b></th>
						<th><b>Highest Score</b></th>
						<th><b>Top Candidates</b></th>
						<th><b>Contact</b></th>
						<th><b>Pass Score</b></th>
						<th ><b>Download Users Report</b></th>
						<th ><b>Download Users Report With Extra Attrs</b></th>
						
                                            </tr>
                                        </thead>
                                         <tbody>
					<tbody>
						                     
						                       <c:forEach  items="${testsessions}" var="session"  varStatus="loop">   
						                      	<tr>
										<td>${loop.count}</td>
												
						                      		<td>${session.testName} </td>
										
						                      		<td>${session.sectionsInfo} </td>
										<td>${session.noOfSessions} </td>
										<td>${session.noOfPassResults} </td>
										
						                      		<td>${session.averageScore} </td>
						                      		<td>${session.highestScore} </td>
						                      		<td>${session.topCandidates} </td>
										<td>${session.topCandidatesEmail} </td>
										<td><input type="text" value="${session.passPercentageForTest}" id="test${loop.count}" name="test${loop.count}" onkeypress="return isNumberKey(event)" style="width:40px"/>
										<!--<td> <a  href="downloadUserReportsForTest?testName=${session.testName}">Click </a>  </td> -->
				<td> <a  href="javascript:void(0);" onclick="encodeAndSend('downloadUserReportsForTest','testName','${session.testName}', 'passScore', document.getElementById('test${loop.count}').value);">Click </a>  </td>
										<!--<td> <a  href="downloadUserReportsForTestWithExtraAttrs?testName=${session.testName}">Click </a>  </td> --> 
				<td> <a  href="javascript:void(0);" onclick="encodeAndSend('downloadUserReportsForTestWithExtraAttrs','testName','${session.testName}', 'passScore', document.getElementById('test${loop.count}').value);">Click </a>  </td> 
								
						                      	</tr>
						                      	</c:forEach>   
						                      </tbody>
                                           
                                        </tbody>
                                    </table>
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
	
	 
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
      
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
    </body>
</html>
