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
	
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
	
	
	
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	
	<script type="text/javascript" src="scripts/jquery.base64.js"></script>
	
	 <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
	<script>
	$.fn.bootstrapBtn = $.fn.button.noConflict();
	function goback(){
	window.location = "goback";
	}
	</script>
	
	<style>
	.rightside .leftdivListTenant{
	    float: left;
	    width: 90%;
	}
	
	.modal-open .modal {
		overflow-x: hidden;
		overflow-y: auto;
		margin-left: 40%;
		margin-top: 10%;
		width: 120%;
		max-width: 730px;
		height: 680px;
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

                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main" >

                        <div class="rightside" >

                            <div class="leftdivListTenant" >

                                <div class="topmenu text-right">
					 
					
					  <a href="signoff">Sign Off</a>
				
                                   
					  <div class="pagination">
						<c:if test="${showPreviousPage}">
						<a href="${callingMethod}?page=${previousPage}${queryParam}"><i class="fa fa-arrow-left"></i></a>
					    </c:if>
					    
					     <c:if test="${selectedPage != null &&  selectedPage > 0}">
						    ${selectedPage} / ${totalNumberOfPages}
					    </c:if>
					    
					    <c:if test="${showNextPage}">
						    <a href="${callingMethod}?page=${nextPage}${queryParam}"><i class="fa fa-arrow-right"></i></a>
					    </c:if>
					</div>
                                </div>

                                <div class="questiontable" >
                                    <div class="questionheading">
                                        <div class="left">
                                            <h3><b>Single File Results</b></h3>
                                        </div>
                                        
                                    </div>


                                    <div class="questiontablelist" >
                                    <table class="table" >
                                        <thead>
                                            <tr>
                                                <th><b>No</b></th>
                                                <th>Test Name</th>
                                                <th>Coding Question</th>
										<th>Date</th>
										<th>Coding Answer Score</th>
                                        
										<th>Coding Answer Score</th>
                                         <th>See Details</th>      
                                            </tr>
                                        </thead>
                                        <tbody>
					<tbody>
						                     
						                       <c:forEach  items="${answers}" var="ans" varStatus="loop">  
												
						                      	<tr>

										<td>${loop.count}</td>		
						                      		
						                      		<td> ${ans.testName}</td>
						                      		<td> ${ans.questionMapper.question.questionText}</td>
													<td> ${ans.timeOfAnswer}</td>
						                <td> ${ans.codingScore}</td>
										<td> ${ans.correct  == true?"Yes":"No"}</td>
										<td><a  href="javascript:fetchDetails('${ans.id}')">Click </a> </td>
						                      	</tr>
						                      	</c:forEach>   
						                      </tbody>
                                           
                                        </tbody>
                                    </table>
                                </div>

                                </div>

                            </div>	

                           

                        </div>

                    </div>
                    <!-- /main -->
                </div>
            </div>
   
        </div>
		
		
		
	<!-- Modal HTML embedded directly into document -->
	<div id="ex1" class="modal" style="border: 3px solid darkblue;">
		<div id="single">
		 
		</div>		 
	</div>	
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

     

        <script>
           function closeModal(){
			   $('#ex1').modal('hide');
		   }
			
			
			
			function fetchDetails(ansid){
				//$('#ex1').modal();
				 $.ajax({
				url : 'fetchCodingAnswerDetails?ansid='+ansid,
				type: "GET",
				success : function(data) {
				console.log(' data ret ');
					$("#single").empty();
				$("#single").append(data);
				$('#ex1').modal('show');
				},
				error : function(e) {
					console.log("ERROR: ", e);
					notify("Info", "Can not generate detailed report yet. Try later");
				}
				}); 
			}
			
        </script>

        <script type="text/javascript">
          
            
        </script>

        <script>
           
	    
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
