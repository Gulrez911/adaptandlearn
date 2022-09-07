<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
      <link href="images/E-assess_E.png" rel="shortcut icon">
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
</head>
<body>
<%-- <form action="" method="get"> --%>
<!-- <input type="file" name="myFile" id="myFile"> -->
<!-- <input type="button" value="Upload" onclick="upload()"/> -->
 
<%-- </form> --%>

<form id="file-upload-form">
    <label for="file-upload-input">Select file to upload</label>
    <input type="file" id="file" name="file">
    <button type="submit">Start Upload</button>
</form>
<script type="text/javascript">

// $(document).ready(function () {
    // bind form submit event
    $("#file-upload-form").on("submit", function (e) {

        // cancel the default behavior
        e.preventDefault();

        // use $.ajax() to upload file
        	let formData = new FormData()
		    var d = $('#file')[0].files[0]
		
		    formData.append('file', d);
// 		    formData.append('name', name);
        
        $.ajax({
            url: "goToJobStep2",
            type: "GET",
//             data: new FormData(this),
 			data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (res) {
                console.log(res);
            },
            error: function (err) {
                console.error(err);
            }
        });
        
    });
    
// });

// function upload() {
		    
// 		var file = document.getElementById("myFile").value;

// 	 	let formData = new FormData()
// 	    var d = $('#myFile')[0].files[0]

// 	    formData.append('file', d);
// // 	    formData.append('name', name);
    
// 		    $.ajax({
// 	            type : "GET",
// 	            url : "goToJobStep2",
// 	            data: formData,
// 	            enctype: 'multipart/form-data',
// 	            contentType: false,
// 	            cache: false,
// 	            processData:false,
// 	            success : function(data) {
// 	                console.log("SUCCESS: ", data);
// 	                display(data);
// 	            },
// 	            error : function(e) {
// 	                console.log("ERROR: ", e);
// 	            },
// 	            done : function(e) {
// 	                console.log("DONE");
// 	            }
// 	        });
// }
</script>
</body>
</html>