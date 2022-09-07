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
        <link href="https://fonts.googleapis.com/css?family=Segoe+UI" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>


	<script>
	function goback(){
	window.location = "lmsAdmins";
	}
	</script>
	

	
    </head>
    <body>

        <div class="maincontainer">            

            <div class="wrapper">
                <div class="row row-offcanvas row-offcanvas-left">
                    <!-- sidebar -->
                   <jsp:include page="side.jsp" /> 
                    <!-- /sidebar -->

                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main">

                        <div class="rightdivU" >
						
						<!-- -->
						 <h2class="heading">Add/Update User</h2>
                                <div class="addqueform" >
                                    <form name="userForm"  method="post" modelAttribute="usr" action="savelmsadmin">
                                       

                                       
					
					<div class="formfield">
                                            <div class="selectoptions">
                                             

                                                <div id="maindivforaddmore">
                                                    <div class="option">
                                                   <label>First Name</label>
                                            
<form:input path="usr.firstName" name="firstName" id="firstName" required="true"/>
                                                    </div>
                                                     <div class="option">
                                                   <label>Last Name </label>
                                            
							<form:input path="usr.lastName" name="lastName" id="lastName" required="true"/>
							
                                                    </div>
													
													<div class="option">
                                                        <label>Email</label>
							 <form:input path="usr.email" name="email" id="email" required="true"/>	
                                                    </div>
													
													<div class="option">
                                                        <label>Password</label>
							  <form:password path="usr.password" name="password" id="password"  required="true"/>
                                                    </div>
													
													
													<div class="option">
                                                        <label>Mobile</label>
							  <form:input path="usr.mobileNumber" name="mobileNumber" id="mobileNumber"  required="true"/>
                                                    </div>
													
													<div class="option">
                                                        <label>College Name</label>
							  <form:input path="usr.collegeName" name="collegeName" id="collegeName"  required="true"/>
                                                    </div>
													
													<div class="option">
                                                        <label>Grade</label>
							  <form:input path="usr.grade" name="grade" id="grade"  required="true"/>
                                                    </div>
													
													<div class="option">
                                                        <label>Classifier</label>
							  <form:input path="usr.classifier" name="classifier" id="classifier"  required="true"/>
                                                    </div>
													
													<div class="option">
                                            <label>Select Licenses (One or More)</label>
                                            
										<form:select path="usr.lic" multiple="true">
											<form:options items="${licenses}"/>
										</form:select>
                                        </div>
                                                    
						    
						     <div class="formfield savebtn">
						    <input class="save" type="submit" value="Save User">
						
						    <input type="button" value="Cancel" onClick="location.href='lmsAdmins';">
						</div>
                                                </div>
                                            </div>
				
                                            
                                        </div>

                                       
					
					

                                    </form>
                                </div>
						<!-- -->
						

                            

                            
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
            /* off-canvas sidebar toggle */
            $('[data-toggle=offcanvas]').click(function () {
                $('.row-offcanvas').toggleClass('active');
                $('.collapse').toggleClass('in').toggleClass('hidden-xs').toggleClass('visible-xs');
            });
        </script>

        <script type="text/javascript">
            $(document).on('click', '#addanother', function () {
                var alphabet = nextString($("#maindivforaddmore").children().last().children().first().text());
                $("#maindivforaddmore").append("<div class='option'><span>" + alphabet + "</span><input type='text'><div class='choice'><input name='option' type='radio'><a href='javascript:void(0);' class='removenewdiv'>-</a></div></div>");
            });

            $(document).on('click', '.removenewdiv', function () {
                $(this).parent().parent().remove();
            });

            function nextString(str) {
                if (!str)
                    return 'A'  // return 'A' if str is empty or null

                let tail = ''
                let
                i = str.length - 1
                let
                char = str[i]
                // find the index of the first character from the right that is not a 'Z'
                while (char === 'Z' && i > 0) {
                    i--
                    char = str[i]
                    tail = 'A' + tail   // tail contains a string of 'A'
                }
                if (char === 'Z')   // the string was made only of 'Z'
                    return 'AA' + tail
                // increment the character that was not a 'Z'
                return str.slice(0, i) + String.fromCharCode(char.charCodeAt(0) + 1) + tail
            }

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
