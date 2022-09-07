<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.assessment.data.*, java.text.*, java.util.*,com.assessment.web.dto.*, org.apache.commons.lang3.StringEscapeUtils"%>
<!DOCTYPE html>
<html>

<head>
<link href="https://fonts.googleapis.com/css?family=Cabin:700"     rel="stylesheet"  />
 <link href="../adaptive/css/bootstrap.min.css" rel="stylesheet" />

<style>

@import url('https://fonts.googleapis.com/css?family=Poppins');
$bgcolor :#21ac70 ;
$btncolor :#fb6a30;
$headingbg : #f0f4f2;


*{
  margin:0;
  padding:0;
  -webkit-box-sizing:border-box;
  box-sizing:border-box;
}

body{
  background:#7c7ea9;
  font-family: 'Poppins', sans-serif;
}
.big-heading{
  color:#fff;
  text-align:center;
  font-size:20pt;
  padding:1px;
  text-transform:capitalize;
}

.table{
  background:#fff;
  width:19%;
  height: 1350px !important;
  height:auto;
  display:block;
  float:left;
  position:relative;
  left:2%;
  margin:1px;
  border-radius:4px;
  overflow:hidden;
  -moz-box-shadow: 0px 0px 0px rgba(0,0,0,.0);
-webkit-box-shadow: 0px 0px 0px rgba(0,0,0,.0);
box-shadow: 0px 0px 0px rgba(0,0,0,.0);
  -webkit-transform:translateY(0%);
  transform:translateY(0%);
  -webkit-transition:.3s;
  transition:.3s;
  &:hover{
     -webkit-transform:translateY(-2%);
      transform:translateY(-2%);
    -moz-box-shadow: 1px 8px 5px rgba(0,0,0,.2);
-webkit-box-shadow: 1px 8px 5px rgba(0,0,0,.2);
box-shadow: 1px 8px 5px rgba(0,0,0,.2);
  }
  .heading{
    background:#f0f4f2;
    width:100%;
    padding:20px;
    text-align:left;
    font-size:15pt;
    color:#272625;
    margin-bottom:40px;
    text-transform:capitalize;
  }
  .block{
    display:block;
    width:78%;
    margin:40px 50%;
    -webkit-transform:translate(-50%);
    transform:translate(-50%);
    border-bottom:1px dashed rgba(0,0,0,.2);
    .price{
      float:right;
      position:relative;
      right:30px;
      sub{
       position:absolute;
       right:-30px;
       top:5px;
       font-weight:lighter;
       font-size:9pt;
      }
    }
    .options{
      display:block;
      list-style:none;
      margin:10px auto;
      li{
        font-size:13px;
        color:#676462;
        text-transform:capitalize;
      }
    }
  }
  .btn{
      display:block;
      margin:20px auto 30px;
      width:60%;
      height:50px;
      background:#3881b5;
      border:none;
      -webkit-border-radius:50px;
       border-radius:50px;
      -webkit-transition:.3s;
       transition:.3s;
     overflow:hidden;
    p{
      font-weight:bolder;
      color:#fff;
      font-size:10pt;
      text-align:center;
      line-height:50px;
      font-family: 'Poppins', sans-serif;
      text-transform:uppercase;
      -webkit-transform:translateX(0);
      transform:translateX(0);
      -webkit-transition: all 600ms cubic-bezier(1, 0, 0, 1);
  transition:         all 600ms cubic-bezier(1, 0, 0, 1);
     
    }
    span{
      font-size:20px;
      -webkit-transform:translate(-50%);
      transform:translate(-50%);
      position:relative;
      top:-35px;
      left:-100%;
      color:#fff;
      -webkit-transform:scale(1);
      transform:scale(1);
       -webkit-transition: all 600ms cubic-bezier(0.68, -0.55, 0.265, 1.55);
  transition:         all 600ms cubic-bezier(0.68, -0.55, 0.265, 1.55);
    }
    &:hover{
      cursor:pointer;
      span{
        left:0%;
        -webkit-transform:scale(1.5);
      transform:scale(1.5);
      }
      p{
        -webkit-transform:translateX(100%);
        transform:translateX(100%);
      }
    }
   }
}
 p{
    text-align:left;
    color:#272625;
    font-weight:600;
    font-size:15px;
    text-transform:capitalize;
 }


h4{
  position:absolute;
  top:0;
  left:20px;
  color:#fff;
  font-size:1.6em;
  a{
    text-decoration:none;
    color:#fff;
    -webkit-transition:.5s;
    transition:.5s;
    &:hover{
      font-size:1.8em;
      text-decoration:underline;
    }
  }
}

.table .btn {
    display: block;
    margin: 20px auto 30px;
    width: 60%;
    height: 50px;
    background: white;
    border: none;
    -webkit-border-radius: 50px;
    border-radius: 50px;
    -webkit-transition: 0.3s;
    transition: 0.3s;
    overflow: hidden;
}

.table .heading {
    background: #f0f4f2;
    width: 100%;
    padding: 20px;
    text-align: left;
    font-size: 15pt;
    color: #272625;
    margin-bottom: 40px;
    text-transform: capitalize;
}

.table .block {
    display: block;
    width: 78%;
    margin: 40px 50%;
    -webkit-transform: translate(-50%);
    transform: translate(-50%);
    border-bottom: 1px dashed rgba(0, 0, 0, 0.2);
}

</style>
<script src="../adaptive/js/jquery-3.5.1.min.js"></script>
   <script src="../adaptive/js/popper.min.js"></script>
   <script src="../adaptive/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.css">
<script>
		function fff(){
			alert('hhh');
		}
		
		function campaignComingSoon(){
		notify('Campaign coming soon!');
		}

		function adaptiveAssessmentComingSoon(){
		notify('Adaptive Assessment coming soon!');
		}

		function assessmentComingSoon(){
		notify('Assessment coming soon!');
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
</head>
<body >
<h2 class="big-heading">eAssess demo links</h2>

<div class="table">
  <h2 class="heading">
    Healthcare
  </h2>
  <div class="block">
  <p>Experienced Nurse
   
    <ul class="options">
      <li><a href="https://www.eassess.in/testtaker-campaign?campaignName=UmVjcnVpdG1lbnQgQ2FtcGFpZ24gZm9yIEV4cGVyaWVuY2VkIE51cnNl&companyId=ZS1hc3Nlc3M%3D&email=bW9oYW5AdGVzdC5jb20%3D&firstName=TW9oYW4%3D&lastName=U2luZ2g%3D&startdate=MjAyMC0xMC0xMA%3D%3D&enddate=MjAyMy0xMC0xMA%3D%3D&lang=eng" target="_blank">Campaign Link</a>  </li>
      <li><a href="https://eassess.in/adaptiveAssessment1?startDate=MTU4NjMzMDUyMDAwMA%3D%3D&endDate=MTc0NDA5NjkyMDAwMA%3D%3D&userId=jobseeker@nurse.com&companyId=e-assess&testId=2512" target="_blank">Adaptive Assessment Link</a>  </li>
      <li>Assessments
		<ul>
          <li><a href="https://www.eassess.in/publicTest?&testId=5106&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY3MjQyNTAwMDAwMA%3D%3D&lang=eng" target="_blank">Experienced Nurse - Basic Skills</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=5065&companyId=e-assess&startTime=MTYyMTM2MjYwMDAwMA%3D%3D&endTime=MTY3MjQyNTAwMDAwMA%3D%3D&lang=eng" target="_blank">Experienced Nurse - Behaviour Skills</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=5078&companyId=e-assess&startTime=MTYyMTM2MjYwMDAwMA%3D%3D&endTime=MTY3MjQyNTAwMDAwMA%3D%3D&lang=eng" target="_blank">Experienced Nurse - General Awareness</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=5200&companyId=e-assess&startTime=MTYyMTM2MjYwMDAwMA%3D%3D&endTime=MTY3MjQyNTAwMDAwMA%3D%3D&lang=eng" target="_blank">Experienced Nurse - Patient Examination & Basic Diagnosis</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=5188&companyId=e-assess&startTime=MTYyMTM2MjYwMDAwMA%3D%3D&endTime=MTY3MjQyNTAwMDAwMA%3D%3D&lang=eng" target="_blank">Experienced Nurse - Tooling Knowledge</a></li>




                    
        </ul>
	  </li>
    </ul>
 </p>
</div>
<div class="block">
<p>CSSD Technician
   <ul class="options">
      <li><a href="https://www.eassess.in/testtaker-campaign?campaignName=UmVjcnVpdG1lbnQgQ2FtcGFpZ24gZm9yIENCQkQgVGVjaG5pY2lhbg%3D%3D&companyId=ZS1hc3Nlc3M%3D&email=bW9oYW5AdGVzdC5jb20%3D&firstName=TW9oYW4%3D&lastName=U2luZ2g%3D&startdate=MjAyMC0xMC0xMA%3D%3D&enddate=MjAyNC0xMC0xMA%3D%3D&lang=eng" target="_blank">Campaign Link</a>  </li>
      <li>Adaptive Assessment Link</li>
      <li>Assessments
		<ul>
          <li><a href="https://www.eassess.in/publicTest?&testId=3756&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1NzczNzAwMDAwMA%3D%3D&lang=eng" target="_blank">Autoclaves and Instrumentation</a></li>
          <li><a href="https://www.eassess.in/publicTest?&testId=4076&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Safety Procedures</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=4054&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Infection Control</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=4088&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Cost Effectiveness</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=4278&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1NzczNzAwMDAwMA%3D%3D&lang=eng" target="_blank">Communication</a></li>
	


        </ul>
	  </li>
    </ul>
 </p>
</div>
<div class="block">
<p>Group Procurement Officer
   <ul class="options">
      <li><a href="https://www.eassess.in/testtaker-campaign?campaignName=R3JvdXAgUHJvY3VyZW1lbnQgT2ZmaWNlcg%3D%3D&companyId=ZS1hc3Nlc3M%3D&email=dGVzdEB0ZXN0MTYuY29t&firstName=dGU%3D&lastName=dGVzdA%3D%3D&startdate=MjAyMC0xMC0xMA%3D%3D&enddate=MjAyNS0xMC0xMA%3D%3D&lang=eng" target="_blank">Campaign Link</a>  </li>
      <li><a href="https://eassess.in/adaptiveAssessment1?startDate=MTU4NjMzMDUyMDAwMA%3D%3D&endDate=MTc0NDA5NjkyMDAwMA%3D%3D&userId=jobseeker@nurse.com&companyId=e-assess&testId=5335" target="_blank">Adaptive Assessment Link</a>  </li>
      <li>Assessments
		<ul>
         <li><a href="https://www.eassess.in/publicTest?&testId=3789&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Service Delivery</a></li>
	 <li><a href="https://www.eassess.in/publicTest?&testId=3806&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Relationship Management </a></li>
	 <li><a href="https://www.eassess.in/publicTest?&testId=3823&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Vendor Management </a></li>
	 <li><a href="https://www.eassess.in/publicTest?&testId=3852&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Inventory Management </a></li>
	 <li><a href="https://www.eassess.in/publicTest?&testId=3869&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Procurement </a></li>
	        </ul>
	  </li>
    </ul>
 </p>
</div>

<button class="btn">
  <p style="text-align: left;color: #f0f4f2;"><a href="https://eassess.in" target="_blank" >Go to Admin Page</a></p>

  <span class="fa fa-cart-plus" aria-hidden="true"></span>
</button>
</div>


<div class="table">
  <h2 class="heading">
    Healthcare 2
  </h2>
  <div class="block">
  <p>Enrolled Nurse
   
    <ul class="options">
      <li> <a href="https://www.eassess.in/testtaker-campaign?campaignName=UmVjcnVpdG1lbnQgQ2FtcGFpZ24gZm9yIEVucm9sbGVkIE51cnNl&companyId=ZS1hc3Nlc3M%3D&email=bW9oYW5AdGVzdC5jb20%3D&firstName=TW9oYW4%3D&lastName=U2luZ2g%3D&startdate=MjAxOS0xMC0xMA%3D%3D&enddate=MjAyNC0xMC0xMA%3D%3D&lang=eng" target="_blank">Campaign Link</a>  </li>
      <li><a href="https://eassess.in/adaptiveAssessment1?startDate=MTU4NjMzMDUyMDAwMA%3D%3D&endDate=MTc0NDA5NjkyMDAwMA%3D%3D&userId=jobseeker@nurse.com&companyId=e-assess&testId=5263" target="_blank">Adaptive Assessment Link</a>  </li>
      <li>Assessments
		<ul>
          <li><a href="https://www.eassess.in/publicTest?&testId=3677&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Occupant Health & Safety Act</a></li>
          <li><a href="https://www.eassess.in/publicTest?&testId=4180&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Labour Relations</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=3767&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1NzczNzAwMDAwMA%3D%3D&lang=eng" target="_blank">Admission of Patients</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=3708&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Stress Management</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=3757&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Problem Solving</a></li>



        </ul>
	  </li>
    </ul>
 </p>
</div>
<div class="block">
<p>Payroll Supervisor
   <ul class="options">
      <li> <a href="https://www.eassess.in/testtaker-campaign?campaignName=UmVjcnVpdG1lbnQgQ2FtcGFpZ24gZm9yIFBheXJvbGwgU3VwZXJ2aXNvcg%3D%3D&companyId=ZS1hc3Nlc3M%3D&email=dGVzdEB0ZXN0MTYuY29t&firstName=dGU%3D&lastName=dGVzdA%3D%3D&startdate=MjAyMC0xMC0xMA%3D%3D&enddate=MjAyMy0xMC0xMA%3D%3D&lang=eng" target="_blank">Campaign Link</a>  </li>
      <li>Adaptive Assessment Link</li>
      <li>Assessments
		<ul>
         <li><a href="https://www.eassess.in/publicTest?&testId=4217&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Payroll Management</a></li>
         <li><a href="https://www.eassess.in/publicTest?&testId=4205&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Supervision</a></li>
	 <li><a href="https://www.eassess.in/publicTest?&testId=4229&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Leave and Bonus Provision Transactions</a></li>
	 <li><a href="https://www.eassess.in/publicTest?&testId=4241&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Internal and External Audit </a></li>
         <li><a href="https://www.eassess.in/publicTest?&testId=4254&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Decision Making </a></li>





        </ul>
	  </li>
    </ul>
 </p>
</div>
<div class="block">
<p>Ward Clerk
   <ul class="options">
      <li> <a href="https://www.eassess.in/testtaker-campaign?campaignName=UmVjcnVpdG1lbnQgQ2FtcGFpZ24gZm9yIFdhcmQgQ2xlcms%3D&companyId=ZS1hc3Nlc3M%3D&email=dGVzdEBnbWFpbC5jb20%3D&firstName=dGVzdA%3D%3D&lastName=TWVy&startdate=MjAyMC0xMC0xMA%3D%3D&enddate=MjAyMy0xMC0xMA%3D%3D&lang=eng" target="_blank">Campaign Link</a>  </li>
      <li>Adaptive Assessment Link</li>
      <li>Assessments
		<ul>
          <li><a href="https://www.eassess.in/publicTest?&testId=4266&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">All Charges Inspection</a></li>
          <li><a href="https://www.eassess.in/publicTest?&testId=3892&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1NzczNzAwMDAwMA%3D%3D&lang=eng" target="_blank">Computer Literacy</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=3923&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1NzczNzAwMDAwMA%3D%3D&lang=eng" target="_blank">Admin Management</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=3935&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Stock Reconciliation</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=4006&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1NzczNzAwMDAwMA%3D%3D&lang=eng" target="_blank">Bill Audit</a></li>
        
        </ul>
	  </li>
    </ul>
 </p>
</div>

<button class="btn">
  <p style="text-align: left;color: #f0f4f2;"><a href="https://eassess.in" target="_blank" >Go to Admin Page</a></p>
  <span class="fa fa-cart-plus" aria-hidden="true"></span>
</button>
</div>




<div class="table">
  <h2 class="heading">
    Health Care 3
  </h2>
  <div class="block">
  <p>Infection Prevention & Quality Facilitator       
    <ul class="options">
      <li> <a href="https://www.eassess.in/testtaker-campaign?campaignName=UmVjcnVpdG1lbnQgQ2FtcGFpZ24gZm9yIEluZmVjdGlvbiBQcmV2ZW50aW9uL1F1YWxpdHkgRmFjaWxpdGF0b3I%3D&companyId=ZS1hc3Nlc3M%3D&email=dGVzdEBnbWFpbC5jb20%3D&firstName=dGVzdA%3D%3D&lastName=TWVy&startdate=MjAyMC0xMC0xMA%3D%3D&enddate=MjAyMy0xMC0xMA%3D%3D&lang=eng" target="_blank">Campaign Link</a>  </li>
      <li>Adaptive Assessment Link</li>
      <li>Assessments
	<ul>
            <li><a href="https://www.eassess.in/publicTest?&testId=4018&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Waste Management</a></li>
            <li><a href="https://www.eassess.in/publicTest?&testId=4030&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Reviewing/Analysing Laboratory Results</a></li>
            <li><a href="https://www.eassess.in/publicTest?&testId=4031&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Supervision of the Cleaning Department </a></li>
   	    <li><a href="https://www.eassess.in/publicTest?&testId=4278&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1NzczNzAwMDAwMA%3D%3D&lang=eng" target="_blank">Communication</a></li>


        </ul>
	  </li>
    </ul>
 </p>
</div>
<div class="block">
<p>Unit Manager - Theatre      
   <ul class="options">
      <li> <a href="https://www.eassess.in/testtaker-campaign?campaignName=UmVjcnVpdG1lbnQgQ2FtcGFpZ24gZm9yIFVuaXQgTWFuYWdlciAtIFRoZWF0cmU%3D&companyId=ZS1hc3Nlc3M%3D&email=bW9oYW5AdGVzdC5jb20%3D&firstName=TW9oYW4%3D&lastName=U2luZ2g%3D&startdate=MjAyMC0xMC0xMA%3D%3D&enddate=MjAyMy0xMC0xMA%3D%3D&lang=eng" target="_blank">Campaign Link</a>  </li>
      <li>Adaptive Assessment Link</li>
      <li>Assessments
		<ul>
          <li><a href="https://www.eassess.in/publicTest?&testId=3880&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Critical Thinking</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=4144&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1NzczNzAwMDAwMA%3D%3D&lang=eng" target="_blank">BCEA & LRA</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=4156&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Cost Management</a></li>
  	  <li><a href="https://www.eassess.in/publicTest?&testId=4168&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Continuous Quality Improvement</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=4180&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Labour Relations</a></li>
	  <li><a href="https://www.eassess.in/publicTest?&testId=4192&companyId=e-assess&startTime=MTYyMTQ0OTAwMDAwMA%3D%3D&endTime=MTY1ODQyODIwMDAwMA%3D%3D&lang=eng" target="_blank">Cost Containment</a></li>

        </ul>
	  </li>
    </ul>
 </p>
</div>
<div class="block">
<p>Junior Doctor
      
    <ul class="options">
      <li> <a href="javascript:void(0);" onclick="campaignComingSoon()">Campaign Link</a>  </li>
      <li><a href="javascript:void(0);" onclick="adaptiveAssessmentComingSoon()">Adaptive Assessment Link</a>  </li>

      <li>Assessments
		<ul>
          <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Community Medicine</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Anatomy</a>  </li>
	   <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Forensic Medicine</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Pharmacology</a>  </li>
    	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Gynaecology</a>  </li>
 	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Paediatrics</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Obstetrics </a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Dermatology</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">OPDs </a>  </li>

        </ul>
	  </li>
    </ul>
 </p>
</div>
<button class="btn">
  <p style="text-align: left;color: #f0f4f2;"><a href="https://eassess.in" target="_blank" >Go to Admin Page</a></p>
  <span class="fa fa-cart-plus" aria-hidden="true"></span>
</button>
</div>

<div class="table">
  <h2 class="heading">
    oil & Gas
  </h2>
  <div class="block">
  <p>Mining  Engineering
   <ul class="options">
       <li> <a href="javascript:void(0);" onclick="campaignComingSoon()">Campaign Link</a>  </li>
      <li><a href="javascript:void(0);" onclick="adaptiveAssessmentComingSoon()">Adaptive Assessment Link</a>  </li>

      <li>Assessments
		<ul>
          <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Microseismic Monitoring</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Civil Engineering</a>  </li>
	   <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Excavation</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Pharmacology</a>  </li>
    	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Petroleum Engineering</a>  </li>
 	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Coal Mining</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Rock Mechanic</a>  </li>
	  

        </ul>
	  </li>
    </ul>
 </p>
</div>
<div class="block">
<p>Geotechnical Engineering
    <ul class="options">
       <li> <a href="javascript:void(0);" onclick="campaignComingSoon()">Campaign Link</a>  </li>
      <li><a href="javascript:void(0);" onclick="adaptiveAssessmentComingSoon()">Adaptive Assessment Link</a>  </li>

      <li>Assessments
		<ul>
          <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Advanced Soil Mechanics</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Advanced Foundation Engineering</a>  </li>
	   <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Geotechnical Laboratory</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Theoretical Soil Mechanics</a>  </li>
    	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Dynamics of Soil and Foundation</a>  </li>
 	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Computer Application in Engineering</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Geotechnical Investigation Practice</a>  </li>
	  

        </ul>
	  </li>
    </ul>
 </p>
</div>
<div class="block">
<p>Hydraulic  Engineering
   <ul class="options">
     <li> <a href="javascript:void(0);" onclick="campaignComingSoon()">Campaign Link</a>  </li>
      <li><a href="javascript:void(0);" onclick="adaptiveAssessmentComingSoon()">Adaptive Assessment Link</a>  </li>

      <li>Assessments
		<ul>
          <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Fluid Mechanics</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Hydrodynamics</a>  </li>
	   
	  

        </ul>
	  </li>
    </ul>
 </p>
</div>
<button class="btn">
  <p style="text-align: left;color: #f0f4f2;"><a href="https://eassess.in" target="_blank" >Go to Admin Page</a></p>

  <span class="fa fa-cart-plus" aria-hidden="true"></span>
</button>
</div>


<!-- 5 row -->
<div class="table">
  <h2 class="heading">
    IT
  </h2>
  <div class="block">
  <p>Experienced Java Developer
       
    <ul class="options">
       <li> <a href="javascript:void(0);" onclick="campaignComingSoon()">Campaign Link</a>  </li>
      <li><a href="javascript:void(0);" onclick="adaptiveAssessmentComingSoon()">Adaptive Assessment Link</a>  </li>

      <li>Assessments
		<ul>
          <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">JavaServer Faces</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Context and Dependency Injection</a>  </li>
	   <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Expression Language </a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Enterprise Java Beans (EJB)</a>  </li>
    	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Java Messaging Service </a>  </li>
 	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Java Persistence API </a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Java Persistence API </a>  </li>
	  

        </ul>
	  </li>
    </ul>
 </p>
</div>
<div class="block">
<p>Experienced Python Developer
      
   <ul class="options">
       <li> <a href="javascript:void(0);" onclick="campaignComingSoon()">Campaign Link</a>  </li>
      <li><a href="javascript:void(0);" onclick="adaptiveAssessmentComingSoon()">Adaptive Assessment Link</a>  </li>

      <li>Assessments
		<ul>
          <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Python Iterator</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Python Generator</a>  </li>
	   <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Python Closure </a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Python Decorators</a>  </li>
    	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Python Property </a>  </li>
 	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Python RegEx  </a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Image Processing</a>  </li>
	  

        </ul>
	  </li>
    </ul>
 </p>
</div>
<div class="block">
<p>Experienced AI/ML Developer
      
    <ul class="options">
       <li> <a href="javascript:void(0);" onclick="campaignComingSoon()">Campaign Link</a>  </li>
      <li><a href="javascript:void(0);" onclick="adaptiveAssessmentComingSoon()">Adaptive Assessment Link</a>  </li>

      <li>Assessments
		<ul>
          <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Machine Learning</a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Deep Learning</a>  </li>
	   <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Reinforcement Learning </a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Robotics</a>  </li>
    	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Natural Language Processing </a>  </li>
 	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Recommender Systems  </a>  </li>
	  <li><a href="javascript:void(0);" onclick="assessmentComingSoon()">Computer Vision</a>  </li>
	  

        </ul>
	  </li>
    </ul>
 </p>
</div>
<button class="btn">
  <p style="text-align: left;color: #f0f4f2;"><a href="https://eassess.in" target="_blank" >Go to Admin Page</a></p>
  <span class="fa fa-cart-plus" aria-hidden="true"></span>
</button>
</div>

<!-- end 5 row -- >


</body>


 

</html>