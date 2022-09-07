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
			<div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Check your Booking history. Select a Time slot to order the assessment</h5>
                            
                        </div>
							<div class="row" style="display:${noBookingStyle}">
							<p style="margin-left:100px">${noBookings} </p>
							</div>
							 <div class="row" style="display:${bookingStyle}">
								<table class="table table-bordered" style="margin-left: 30px;margin-right: 30px;">
								<thead>
								  <tr>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Email</th>
									<th>Booking Slot</th>
									<th>Test Name</th>
									<th>Test URL</th>
									
								  </tr>
								</thead>
								<tbody>
								 <c:forEach  items="${bookings}" var="booking" >  
								  <tr>
									<td>${booking.firstName}</td>
									<td>${booking.lastName}</td>
									<td>${booking.email}</td>
									<td>${booking.slot.dateStr}</td>
									<td>${booking.testUrl}</td>
								  </tr>
								 </c:forEach> 
								</tbody>
							  </table>
							</div>
					  <hr/>
					  
					  
                      
                        <div class="modal-body">
								
								
							
                                <div class="row">
								<form name="dayForm" id="dayForm"  method="post" modelAttribute="user" action="bookingStep3">
                                    <div class="col-xs-12 col-md-12">
                                        <div style="display:inline-block;margin-left: 10px;">
										<p class="text-justify">Select your day when you want to book your test. Then click 'Check Available Time Slots' button on the right to view the available test slots.&nbsp;&nbsp;
										<select id="timeType" name="timeType" style="width:135px;margin-top:30px;margin-right:30px"> 
											 <c:forEach  items="${times}" var="time" varStatus="loop">  
												<option value="${time.day}/${time.month}/${time.year}">${time.day}/${time.month}/${time.year}</option>
											</c:forEach>
										</select>&nbsp;&nbsp;
										<button style="width:450px" type="button" onClick="javascript:fetchTimeSlots();">Check Available Time Slots</button></p>
										</div>
                                    </div>
									<form:hidden path="user.id" id="uid"/>
									<input type="hidden" name="datestr" id="datestr" value="" />
									<!-- <button type="button" class="btn btn-primary" onClick="javascript:step4('${user.id}')">Next</button>  -->
                                 </form> 
                                    
                                </div>
								<hr/>
								
								<div class="row" style="display:${display}">
									<form name="selectForm" id="selectForm"  method="post" modelAttribute="user" action="bookingStep4">
										<div class="col-xs-12 col-md-12">
                                        <div style="display:inline-block;margin-left: 10px;">
										
										SELECT your slot on <b>${datestr}</b>. If you proceed with a selected slot, you can appear for the assessment at the specified time. The test link will be shared by you by email and will become active exactly at the selected time. Please note all times are IST.
										<select id="slotTime" name="slotTime" style="width:135px;margin-top:30px;margin-right:30px"> 
											 <c:forEach  items="${availableSlots}" var="slot" varStatus="loop">  
												<option value="${slot.id}">${slot.timeStr}</option>
											</c:forEach>
										</select>
										
										<button type="button" class="btn btn-primary" onClick="javascript:step4('${user.id}')">Proceed to Summary Page</button>
										
										</div>
                                    </div>
										<form:hidden path="user.id" id="uid"/>
										
										
										<input type="hidden" name="slotid" id="slotid" value="" />
										<input type="hidden" name="userid" id="userid" value="" />
										
									 
                                 </form> 
								</div>
							</div>
								
                        </div>
                        <div class="modal-footer">
                           
                           
                        </div>
                       </form>
                    </div>
			<!--
            <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                    
                </div>   --> 
                
            <!-- <div  id="exampleModalCenter"  aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                
            </div> -->
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
		
			
		
			function fetchTimeSlots(){
				
				var e = document.getElementById("timeType");
				var strUser = e.options[e.selectedIndex].text;
				document.getElementById("datestr").value = strUser;
				
				document.getElementById("dayForm").action = 'bookingStep3';
				
				document.getElementById('dayForm').submit();
			}
		
			function step4(uid){
				var e = document.getElementById("slotTime");
				var sid = e.options[e.selectedIndex].value;
				document.getElementById("slotid").value = sid;
				document.getElementById("userid").value = uid;
				
				///document.getElementById('selectForm').submit();
				window.location = 'bookingStep4?userid='+uid+'&slotid='+sid;
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