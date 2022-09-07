<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Quantpro</title>
<link rel="shortcut icon" href="quaintpro/admin/assets/img/favicon.png" type="image/x-icon">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="quaintpro/admin/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="quaintpro/admin/assets/css/style.css" rel="stylesheet">


<!--  gulrez -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
	integrity="sha512-c42qTSw/wPZ3/5LBzD+Bw5f7bSF2oxou6wEb+I/lqeaKV5FDIfMvvRp772y4jcJLKuGUOpbJMdg/BTl50fJYAw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.1.9/sweetalert2.min.css"
	integrity="sha512-cyIcYOviYhF0bHIhzXWJQ/7xnaBuIIOecYoPZBgJHQKFPo+TOBA+BY1EnTpmM8yKDU4ZdI3UGccNGCEUdfbBqw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.1.9/sweetalert2.all.min.js"
	integrity="sha512-IZ95TbsPTDl3eT5GwqTJH/14xZ2feLEGJRbII6bRKtE/HC6x3N4cHye7yyikadgAsuiddCY2+6gMntpVHL1gHw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<style type="text/css">
.swal2-styled.swal2-confirm {
	padding: 10px 17px;
}

.submit2 {
	font-size: 1.1rem;
	border-radius: 5px;
	outline: none;
	border: none;
	width: 85%;
	background: #4782BC;
	color: white;
}
</style>
</head>
<body class="login_page">

	<div class="wrapper">
		<h1>Hello Again!</h1>
		<p>
			Welcome back you've <br> been missed!
		</p>

		<!-- 		<span>Sign in</span> -->

		<form:form id="loginForm" method="post" modelAttribute="user" action="proindex">
			<form:input type="email" path="email" name="email" id="username" placeholder="Enter username" required="true" />
			<form:password path="password" name="password" id="password" placeholder="Password" required="true" />
			<button type="button" id="myButton">Sign in</button>
		</form:form>

		<%--     <form> --%>
		<!--       <input type="text" placeholder="Enter username"> -->
		<!--       <input type="password" placeholder="Password"> -->
		<%--     </form> --%>
		<!--     <button type="submit" id="myButton">Sign in</button> -->








	</div>


	<script>
		// 		document.getElementById("myButton").onclick = function() {
		// 			location.href = "proindex";
		// 		};

		$("#myButton").click(
				function() {
					var username = $("#username").val();
					var login_password = $("#password").val();
					console.log(":   "+username  );
					console.log(":   "+  login_password);
					if (!username ||  !login_password ) {
						return;
// 							 	alert();
					}
					$.get("loginCheck?email=" + username + "&password="
							+ login_password, function(data, status) {
						console.log(": ////  "+data.user44  );
// 		 				alert(data.user44);
						if (data.user44 === undefined || data.user44 == null) {
										    Swal.fire( 'Failure!', 'Invalid Credentials!',  'error')
						} 
						else {
							location.href = "proindex";
// 							$("#loginForm").submit();
						}

					});

				});

		// 			Swal.fire('Good job!', 'You clicked the button!', 'success')
		// 			});

		// 	 		Swal.fire({
		// 	 			  icon: 'error',
		// 	 			  title: 'Oops...',
		// 	 			  text: 'Something went wrong!',
		// 	 			  footer: '<a href="">Why do I have this issue?</a>'
		// 	 			})
	</script>

	<c:if test="${msgtype != null}">
		<script>
			var notification = 'Information';
			$(function() {
				Swal.fire('${msgtype}', '${message}', '${icon}')
			});
		</script>
	</c:if>

</body>
</html>
