<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>sss Pro</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Security-Policy"  content="connect-src * 'unsafe-inline';">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
    	
    	<style type="text/css">
    	*{
    	   padding: 0;
    margin: 0;
    	}
    	</style>
    	</head>
<body>
		

<iframe src=" " name="myIframe2" frameborder="0" marginheight="0" marginwidth="0"
		width="100%" height="5000px" scrolling="no"></iframe>
<form:form  action="${studentJourneyPro}" method="post" modelAttribute="studentForm" name="myForm" target="myIframe2">
</form:form>
			
			<c:if test="${test.webProctoring}">
			<iframe id="myFrame" src="https://159.203.181.209/proctoring?cid=${user.companyId}&user=${user.email}&testid=${test.id}&testattempt=${studentForm.noOfAttempts}" allow="camera;microphone"  frameborder="0" marginheight="0" marginwidth="0"
					width="100%" height="0px" scrolling="no"></iframe>
			</c:if>
  
		
<script type="text/javascript">
window.onload=function(){
	
	if(${test.webProctoring}){
		navigator.getMedia = ( navigator.getUserMedia || // use the proper vendor prefix
	            navigator.webkitGetUserMedia ||
	            navigator.mozGetUserMedia ||
	            navigator.msGetUserMedia);
		navigator.getMedia({video: true}, function() {
			document.forms["myForm"].submit();
		}, function() {
			sweetAlert("Information","Test cannot start without camera","warning");
			});
		
	}else{
		document.forms["myForm"].submit();
	}
}
  
function sweetAlert(msgtype,message,icon){
	  Swal.fire(
		       msgtype,
		       message,
		       icon
		    )
	}
</script>
</body>
</html>