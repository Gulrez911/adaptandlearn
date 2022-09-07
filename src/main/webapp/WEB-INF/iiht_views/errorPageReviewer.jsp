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
 <link href="https://fonts.googleapis.com/new/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="new/css/font-awesome.min.css">

        <link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	
	   <script src="new/campaign/js/jquery.min.js"></script>
        <script src="new/campaign/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
     	
     	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="new/css/font-awesome.min.css">
        <link rel="stylesheet" href="new/campaign/css/bootstrap.min.css">
        <link rel="stylesheet" href="new/campaign/css/style.css">
     <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<style>

</style>
</head>
<body>
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
<div class="container" style="width: 70%;font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif;border-bottom: 3px solid blue;padding-top: 11px;padding-bottom: 11px;">
<p>
${msg}
</p>
<p><b>Kindly close the page.</b></p>
</div>


</body>
</html>