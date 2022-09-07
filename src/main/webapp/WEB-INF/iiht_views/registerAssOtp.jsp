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
                    
                    
                </div>
            </header>
            <section class="content-section">
                
                
            <div  id="exampleModalCenter"  aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Enter OTP (Check your Inbox)</h5>
                            
                        </div>
                       <form name="tempOtp"  method="post" modelAttribute="user" action="bookingStep2">
                        <div class="modal-body">
                                <div class="row">
                                    <div class="col-xs-12 col-md-6">
                                        <div class="form-group">
                                            <label for="">OTP</label>
                                            	    <form:input path="user.tempOtp" name="tempOtp" id="tempOtp" required="true" class="form-control"/>
                                            	    <form:hidden path="user.id" id="uid"/>
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
                                    
                                    
                                </div>
                        </div>
                        <div class="modal-footer">
                           
                            <button type="type" class="btn btn-primary" onclick="next();">Next</button>
                        </div>
                       </form>
                    </div>
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
            
        </div>
        <script>
			function next(){
				document.getElementById('tempOtp').submit();
			}
			

       	 function sweetAlert(msgtype,message,icon){
			  Swal.fire(
				       msgtype,
				       message,
				       icon
				    )
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