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
     <style>
	 .table thead th {
		vertical-align: bottom;
		border-bottom: 2px solid #dee2e6;
		background-color: #3e7dbd;
		color: white;
	}
	
	.btn-primary, .bg-primary {
		background-color: #3e7dbd!important;
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
                    
                    
                </div>
            </header>
            <section class="content-section">
                
                
            
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Your Order Details</h5>
                            
                        </div>
                       
                        <div class="modal-body">
                                <div class="row">
                                    <div class="col-xs-12 col-md-12">
                                        <div class="form-group">
                                            <label for="">Assessment Name: </label>
                                           <font style="color:red"> ${testName}	   </font>
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-12">
                                        <div class="form-group">
                                           Thank you, ${fullName}, for initiating payment for the above assessment. Please note down the following payment details
                                               
<!--                                             <input type="text" class="form-control"> -->
                                        </div>
                                    </div>
									<div class="col-xs-12 col-md-12">
									<table class="table table-bordered" style="margin-right: 30px;">
										<thead >
										  <tr>
											<th background-color="#326fad">Payment Status</th>
											<th background-color="#326fad">Payment Id</th>
											<th background-color="#326fad">Payment Request Id</th>
											
											
											
										  </tr>
										</thead>
										<tbody>
										 
										  <tr>
											<td> ${payment_status}</td>
											<td>${payment_id}</td>
											<td>${payment_request_id}</td>
											
										  </tr>
										
										</tbody>
									  </table>
									  As soon as the payment is confirmed, we will share the assessment link by email.
									  </div>
									
									
                                    
                                </div>
                        </div>
                        <div class="modal-footer">
                           
                            <button type="type" class="btn btn-primary" style="background-color:#3e7dbd;">Initiate Payment</button>
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

            function openModel(){
        		$("#firstName").val("");
				$("#lastName").val("");
				$("#email").val("");
				$("#email").val() 
				$("#id").val("");
				$("#groupOfUser").val("");
				$("#mobileNumber").val("");
				$("#department").val("");
				$("#grade").val("");
				$("#internalUser").prop( "checked", false);
				$("#exampleModalLongTitle").text("Add User");
				$("#exampleModalCenter").modal("show");
				
                }
            
            function editUser(email){
            	$.get("getUser?email=" + email, function(data,	status) {
				$("#firstName").val(data.user.firstName);
				$("#lastName").val(data.user.lastName);
				$("#email").val(data.user.email);
				$("#email").attr("readonly", "readonly"); 
				$("#id").val(data.user.id);
				$("#groupOfUser").val(data.user.groupOfUser);
				$("#mobileNumber").val(data.user.mobileNumber);
				$("#department").val(data.user.department);
				$("#grade").val(data.user.grade);
				$("#internalUser").prop( "checked", data.user.internalUser);
				$("#exampleModalLongTitle").text("Edit User");
				$("#exampleModalCenter").modal("show");
            	});
                }

        function    deleteUser(email){
	        	 Swal.fire({
					  title: 'Are you sure?',
					  text: "Do you want to delete this User ?",
					  icon: 'warning',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'Yes, delete it!'
					}).then((result) => {
					  if (result.value) {
						 window.location = "deleteUser?email=" + email;
					  }
					})
                }
        
        function showFileDialog(){
        	$("#fileFromUserForm").click();
        	}
        
   	 var isXlsx = function(name) {
 	    return name.match(/xlsx$/i)
 	    };
 	    
   	 $(document).ready(function() {
 	    
 	    var file = $('[name="fileFromUserForm"]');
 	var fileU = document.getElementById('fileFromUserForm');
 	fileU.addEventListener("change", function () {
 		  if (fileU.files.length > 0) {
 		   var filename = $.trim(file.val());
 		
 		if (!(isXlsx(filename) )) {
 			sweetAlert('Information', 'Please select an xlsx file to upload',"error");
 		    return;
 		}
 		
 		$.ajax({
 		   xhr: function() {
 		    var xhr = new window.XMLHttpRequest();

 		    return xhr;
 		  },
 		   url: 'uploadUsers',
 		    type: "POST",
 		    data: new FormData(document.getElementById("fileFormUsers")),
 		    enctype: 'multipart/form-data',
 		    processData: false,
 		    contentType: false
 		  }) .done(function(data) {
 		    sweetAlert('Information', 'File Upload Successful',"success");
 		  }).fail(function(jqXHR, textStatus) {
 			 sweetAlert('Information', 'File Upload Failed. Please contact Administrator',"failure");
 		  });
 		  document.getElementById('fileFromUserForm').value = null;
 		    return;
 		  }
 		 
 		});
 	
 	 });
        	
            $('#search').on('click',function(){
        	    var text = document.getElementById("searchText").value;
        		if(text.length != 0){
        		window.location="newSearchusers?searchText="+text;
        		}
        	    });

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