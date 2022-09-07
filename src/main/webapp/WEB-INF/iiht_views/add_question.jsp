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
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	
	 <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
	<script>
	function goback(){
	window.location = "goback";
	}
	</script>
	
    </head>
    <body>

        <div class="maincontainer">            

            <div class="wrapper">
                <div class="row row-offcanvas row-offcanvas-left">
                    <!-- sidebar -->
					
					<%
					User user = (User) request.getSession().getAttribute("user");
					System.out.println("user is "+user.getEmail() );
						if(user == null){
								response.sendRedirect("login");
						}
					
					if(user.getUserType().getType().equals("LMS_ADMIN")){
						
						System.out.println("LMS_ADMIN true");
				  %>
					<jsp:include page="side_lms_admin.jsp" /> 
				   <%	  
					}
					else{
					%>
					<jsp:include page="side.jsp" /> 
					<%
					}
					%>
                    <!-- /sidebar -->

                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main" style="overflow-x:scroll;overflow-y:scroll;">

                        <div class="rightside" >

                            <div class="leftdiv" >

                                <div class="topmenu text-right">
                                   
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
                                            <h4>Question Bank</h4>
                                        </div>
                                        <div class="right">
                                            <div class="searchdata">
                                                <input type="text" placeholder="Search a Q" name="searchText" id="searchText">
                                            <i class="fa fa-search" id="search"></i>
                                            </div>

                                            <div class="filter">
                                                <a href="javascript:notify('Information', 'Feature coming soon')"><img src="images/ic_sort.png">Sort</a>
                                                <a href="javascript:notify('Information', 'Feature coming soon')"><img src="images/ic_filter.png">Filter</a>
                                            </div>

                                        </div>
                                    </div>


                                    <div class="questiontablelist" >
                                    <table class="table" >
                                        <thead>
                                            <tr>
                                                <th><b>No</b></th>
                                                <th>Question</th>
                                                <th  style="white-space:nowrap;">Category</th>
                                                <th>Difficulty Level</th>
                                                <th  style="white-space:nowrap;">Update</th>
						<th  style="white-space:nowrap;">Delete</th>
                                            </tr>
                                        </thead>
                                        <tbody>
					<tbody>
						                     
						                       <c:forEach  items="${qs}" var="question" varStatus="loop">   
						                      	<tr>

										<td>${loop.count}</td>		
						                      		<td><c:out value="${question.questionText}"></c:out>  </td>
						                      		<td> ${question.category}</td>
						                      		<td><c:out value="${question.difficultyLevel.level}"></c:out>   </td>
						                      		<td><a  href="addQuestion?qid=${question.id}">Click </a>   </td>
						                      		<td><a  href="javascript:confirm('${question.id}')">Click </a> </td>
						                      	</tr>
						                      	</c:forEach>   
						                      </tbody>
                                           
                                        </tbody>
                                    </table>
                                </div>

                                </div>

                            </div>	

                            <div class="rightdiv" >
                                <h4 class="heading">${question_label}</h4>
                                <div class="addqueform" >
                                     <form name="questionForm"  method="post" modelAttribute="question" action="saveQuestion" enctype="multipart/form-data">
                                        <div class="formfield">
                                            <label>Question</label>
                                            
					    <form:textarea path="question.questionText" required="true" />
					    <form:hidden path="question.id" />
                                        </div>

                                      <!--  <div class="formfield">
					
                                            <select>
                                                <option>Select Question Type</option>
						<option selected>Multi Choice Question</option>
						<option>Coding Assignment</option>
						<option>Project Assessment</option>
                                            </select>
					   
                                        </div> -->
					
					 <div class="formfield">
					 <label>Question Type</label>
					<form:select id="questionType" path="question.type" onchange="changeQType()">
						  
						     <form:options items="${types}" itemValue="type" itemLabel="type" />
						</form:select>
					</div>

                                        <div class="formfield">
                                            
					     <form:select path="question.level">
						  
						     <form:options items="${levels}" itemValue="level" itemLabel="level" />
						</form:select>
                                        </div>
					
					<c:choose>
					    <c:when test="${question.type=='MCQ'}">
						<div id="mcqDiv" class="formfield"  >
					    </c:when>    
					    <c:when test="${question.type=='CODING'}">
						<div id="mcqDiv" class="formfield"  style="display:none">
						
					    </c:when> 
						<c:when test="${question.type=='FULL_STACK_JAVA' || question.type=='FULLSTACK'}">
						<div id="mcqDiv" class="formfield"  style="display:none">
						
					    </c:when>

						<c:when test="${question.type=='FILL_BLANKS_MCQ' || question.type=='MATCH_FOLLOWING_MCQ'}">
						<div id="mcqDiv" class="formfield"  style="display:none">
						
					    </c:when> 
						
						<c:when test="${question.type=='IMAGE_UPLOAD_BY_USER' || question.type=='VIDEO_UPLOAD_BY_USER' || question.type=='SUBJECTIVE_TEXT'}">
						<div id="mcqDiv" class="formfield"  style="display:none">
						
					    </c:when> 
					</c:choose>

                                        
                                            <div class="selectoptions"  >
                                                <span>Options</span>
                                                <span style="float: right;padding-right: 20px;">Correct Choice</span>

                                                <div id="maindivforaddmore">
                                                    <div class="option">
                                                        <span>A</span>
                                                       <form:input path="question.choice1" name="choice1" id="choice1" required="true"/>
                                                        <div class="choice">
                                                            <form:checkbox path="question.one" /> 
                                                        </div>
                                                    </div>
                                                    <div class="option">
                                                        <span>B</span>
                                                       <form:input path="question.choice2" name="choice2" id="choice2" required="true"/>
                                                        <div class="choice">
                                                            <form:checkbox path="question.two" /> 
                                                        </div>
                                                    </div>
                                                    <div class="option">
                                                        <span>C</span>
                                                        <form:input path="question.choice3" name="choice3" id="choice3" />
                                                        <div class="choice">
                                                            <form:checkbox path="question.three" /> 
                                                        </div>
                                                    </div>
                                                    <div class="option">
                                                        <span>D</span>
                                                        <form:input path="question.choice4" name="choice4" id="choice4" />
                                                        <div class="choice">
                                                            <form:checkbox path="question.four" /> 
                                                        </div>
                                                    </div>
                                                    <div class="option">
                                                        <span>E</span>
                                                        <form:input path="question.choice5" name="choice5" id="choice5" />
                                                        <div class="choice">
                                                            <form:checkbox path="question.five" /> 
                                                        </div>
                                                    </div>
						    
						    <div class="option">
                                                        <span>F</span>
                                                        <form:input path="question.choice6" name="choice6" id="choice6" />
                                                        <div class="choice">
                                                            <form:checkbox path="question.six" /> 
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
										<div class="formfield">
										 <label>Justification for Answer</label>
										<form:textarea path="question.justification"  id="justification" />
									</div>
                                            
                                        </div>
										
									
					
					<c:choose>
					    <c:when test="${question.type=='MCQ'}">
						<div id="codingDiv" class="formfield" style="display:none">
					    </c:when>    
					    <c:when test="${question.type=='CODING'}">
						<div id="codingDiv" class="formfield" >
					    </c:when>  
						<c:when test="${question.type=='FULL_STACK_JAVA' || question.type=='FULLSTACK' }">
						<div id="codingDiv" class="formfield"  style="display:none">
						
					    </c:when> 			
					
						<c:when test="${question.type=='FILL_BLANKS_MCQ' || question.type=='MATCH_FOLLOWING_MCQ'}">
						<div id="codingDiv" class="formfield"  style="display:none">
						
					    </c:when> 	

						<c:when test="${question.type=='IMAGE_UPLOAD_BY_USER' || question.type=='VIDEO_UPLOAD_BY_USER' || question.type=='SUBJECTIVE_TEXT'}">
						<div id="codingDiv" class="formfield"  style="display:none">
						
					    </c:when> 
				
					</c:choose>
					
					
					    <div class="formfield">
						 <label>Programming Language</label>
						<form:select path="question.lang">
							  
							     <form:options items="${languages}" itemValue="language" itemLabel="language" />
							</form:select>
					    </div>
					  
					  <div class="formfield">
                                            <label>Code Input</label>
                                            
					    <form:textarea path="question.inputCode" style="height:150px;overflow-y: scroll" id="inputCode" />
					    
                                          </div>
					  
					  <div class="formfield">
                                            <label>System Input 1</label>
                                            
					    <form:textarea path="question.hiddenInputPositive" style="height:30px" id="hiddenInputPositive" />
					  
                                          </div>
									<div class="formfield">
                                            <label>System Output 1</label>
										  
						<form:textarea path="question.hiddenOutputPositive" style="height:30px" id="hiddenOutputPositive" />
					  
                                          </div>
										  
							<div class="formfield">
                                            <label>Weight for System Input/Output 1 (from 1 to 10)</label>
										  
						<form:textarea path="question.weightInputPositive" style="height:30px" id="weightInputPositive" />
					  
                                          </div>
										  <br/><br/>
										  
										  
						<div class="formfield">
                                            <label>System Input 2</label>
                                            
					    <form:textarea path="question.hiddenInputNegative" style="height:30px" id="hiddenInputNegative" />
					  
                                          </div>
						 <div class="formfield">
                                            <label>System Output 2</label>
										  
						<form:textarea path="question.hiddenOutputNegative" style="height:30px" id="hiddenOutputNegative" />
					  
                                          </div>
										  
										  <div class="formfield">
                                            <label>Weight for System Input/Output 2 (from 1 to 10)</label>
										  
						<form:textarea path="question.weightInputNegative" style="height:30px" id="weightInputNegative" />
					  
                                          </div>
								<br/><br/>		  
										  
						<div class="formfield">
                                            <label>Extreme Minimal Input</label>
                                            
					    <form:textarea path="question.hiddenInputExtremeMinimalValue" style="height:30px" id="hiddenInputExtremeMinimalValue" />
					  
                                          </div>
						 <div class="formfield">
                                            <label>Extreme Minimal Output</label>
										  
						<form:textarea path="question.hiddenOutputExtremeMinimalValue" style="height:30px" id="hiddenOutputExtremeMinimalValue" />
					  
                                          </div>
										  
					  <div class="formfield">
						<label>Weight for Extreme Minimal Input/Output (from 1 to 10)</label>
					  
	<form:textarea path="question.weightExtremeMinimalValue" style="height:30px" id="weightExtremeMinimalValue" />
  
					  </div>
						<br/><br/>
										  
						<div class="formfield">
                                            <label>Extreme Positive Input</label>
                                            
					    <form:textarea path="question.hiddenInputExtremePositiveValue" style="height:30px" id="hiddenInputExtremePositiveValue" />
					  
                                          </div>
						 <div class="formfield">
                                            <label>Extreme Positive Output</label>
										  
						<form:textarea path="question.hiddenOutputExtremePositiveValue" style="height:30px" id="hiddenOutputExtremePositiveValue" />
					  
                                          </div>
										  
						<div class="formfield">
						<label>Weight for Extreme Positive Input/Output (from 1 to 10)</label>
					  
	<form:textarea path="question.weightExtremePositiveValue" style="height:30px" id="weightExtremePositiveValue" />
  
					  </div>
							<br/><br/>			  
										  
						<div class="formfield">
                                            <label>Invalid Data Input</label>
                                            
					    <form:textarea path="question.hiddenInputInvalidDataValue" style="height:30px" id="hiddenInputInvalidDataValue" />
					  
                                          </div>
						 <div class="formfield">
                                            <label>Invalid Data Output</label>
										  
						<form:textarea path="question.hiddenOutputInvalidDataValue" style="height:30px" id="hiddenOutputInvalidDataValue" />
					  
                                          </div>
										  
						<div class="formfield">
						<label>Weight for Invalid Data Input/Output (from 1 to 10)</label>
					  
	<form:textarea path="question.weightInvalidDataValue" style="height:30px" id="weightInvalidDataValue" />
  
					  </div>
					  
					  <div class="formfield">
						<label><b>Pass Criteria for Coding Q</b></label>
					  
	<form:textarea path="question.passPercentforCodingQAsPerWeightedScore" style="height:30px" id="passPercentforCodingQAsPerWeightedScore" />
  
					  </div>
					 
								
					</div>
					
					
					<!--Support for full Stack Programs -->
					<c:choose>
					    <c:when test="${question.type=='FULL_STACK_JAVA'  || question.type=='FULLSTACK'}"> 
						<div id="fullstackDiv" class="formfield">
					    </c:when>    
					     <c:otherwise>
							<div id="fullstackDiv" class="formfield" style="display:none">
						</c:otherwise> 
					</c:choose>
					
					
					    <div class="formfield">
						 <label>Select Stack</label>
						<form:select path="question.stack">
							  
							     <form:options items="${stacks}" itemValue="stack" itemLabel="stack" />
							</form:select>
					    </div>
						
						<div class="formfield">
						 <label>GitHub Codebase Url</label>
						<form:input path="question.fullStackGitHupCodeUrl" name="fullStackGitHupCodeUrl" id="fullStackGitHupCodeUrl" />
					    </div>
						
						<div class="formfield">
						 <label>Reviewer Email</label>
						<form:input path="question.reviewerEmail" name="reviewerEmail" id="reviewerEmail" />
					    </div>
						
						<div class="formfield">
						<label class="quetestcases">Upload Test Cases Xml</label>
                                        
                        <input type="file" name="addtestcases" id="addtestcases" >
						</div>
						
					  
					   <div class="formfield">
                                            <label>Problem Statement In Depth</label>
                                            
					    <form:textarea path="question.stackProblemDetails"  style="height:100px" />
					    
                                          </div>
					  
					  <!--<div class="formfield">
                                            <label>Constraints</label>
                                            
					    <form:textarea path="question.constrnt"  style="height:100px" />
					    
                                          </div> -->
					</div>
					<!-- End full stack -->
					
					<!--Support for full Stack Programs -->
					<!-- Fill in blank Qs -->
					<c:choose>
					    <c:when test="${question.type=='FILL_BLANKS_MCQ'}"> 
						<div id="fillBlanksDiv" class="formfield">
					    </c:when>    
					     <c:otherwise>
							<div id="fillBlanksDiv" class="formfield" style="display:none">
						</c:otherwise> 
					</c:choose>
					
							<div class="formfield">
							 <label>No of Fill in Blanks in a Q</label>
							<form:input path="question.noOfFillBlanks" name="noOfFillBlanks" id="noOfFillBlanks" />
							</div>
							
							<div class="formfield">
							 <label>Enter comma separated blank answers</label>
							<form:textarea path="question.fillInBlankOptions" name="fillInBlankOptions" id="fillInBlankOptions" />
							</div>
					
						</div>
						<!-- End Fill in blanks-->
						
						
						<!-- Match the following Qs -->
					<c:choose>
					    <c:when test="${question.type=='MATCH_FOLLOWING_MCQ'}"> 
						<div id="matchFollowingDiv" class="formfield">
					    </c:when>    
					     <c:otherwise>
							<div id="matchFollowingDiv" class="formfield" style="display:none">
						</c:otherwise> 
					</c:choose>
					
							<div class="formfield">
							 <label>MTF - Option 1 Left</label>
							<form:input path="question.matchLeft1" name="matchLeft1" id="matchLeft1" />
							<label>MTF - Option 1 Right</label>
							<form:input path="question.matchRight1" name="matchRight1" id="matchRight1" />
							</div>
							
							<div class="formfield">
							 <label>MTF - Option 2 Left</label>
							<form:input path="question.matchLeft2" name="matchLeft2" id="matchLeft2" />
							<label>MTF - Option 2 Right</label>
							<form:input path="question.matchRight2" name="matchRight2" id="matchRight2" />
							</div>
							
							<div class="formfield">
							 <label>MTF - Option 3 Left</label>
							<form:input path="question.matchLeft3" name="matchLeft3" id="matchLeft3" />
							<label>MTF - Option 3 Right</label>
							<form:input path="question.matchRight3" name="matchRight3" id="matchRight3" />
							</div>
							
							<div class="formfield">
							 <label>MTF - Option 4 Left</label>
							<form:input path="question.matchLeft4" name="matchLeft4" id="matchLeft4" />
							<label>MTF - Option 4 Right</label>
							<form:input path="question.matchRight4" name="matchRight4" id="matchRight4" />
							</div>
							
							<div class="formfield">
							 <label>MTF - Option 5 Left</label>
							<form:input path="question.matchLeft5" name="matchLeft5" id="matchLeft5" />
							<label>MTF - Option 5 Right</label>
							<form:input path="question.matchRight5" name="matchRight5" id="matchRight5" />
							</div>
							
							<div class="formfield">
							 <label>MTF - Option 6 Left</label>
							<form:input path="question.matchLeft6" name="matchLeft6" id="matchLeft6" />
							<label>MTF - Option 6 Right</label>
							<form:input path="question.matchRight6" name="matchRight6" id="matchRight6" />
							</div>
					
						</div>
						<!-- End match the following-->


                                        <div class="formfield addimagevideo">
                                            <a class="addimage" href="#">Add image</a>
                                            <a class="addaudio" href="#">Add audio</a>
                                            <a class="addvideo" href="#">Add Video</a>
                                        </div>
					
					<div class="formfield">
                                            <div class="selectoptions">
                                                <span>Choose Categories for Question</span>
                                               
                                                <div id="maindivforaddmore">
                                                    <div class="option">
                                                        <span>A</span>
                                                        <form:input path="question.qualifier1" name="qualifier1" id="qualifier1" required="true"/>
                                                        
                                                    </div>
                                                    <div class="option">
                                                        <span>B</span>
                                                        <form:input path="question.qualifier2" name="qualifier2" id="qualifier2" />
                                                       
                                                    </div>
                                                    <div class="option">
                                                        <span>C</span>
                                                        <form:input path="question.qualifier3" name="qualifier3" id="qualifier3" />
                                                       
                                                    </div>
                                                    
                                                </div>
                                            </div>
											
											
						
						
					</div>
                                        
                                        <label class="queimage"></label>
										 
                                        <label class="queaudio"></label>
                                        <label class="quevideo"></label>
                                        
                                       <input type="file" name="addimage" id="addimage" style="display: none;">
                                        <input type="file" name="addaudio" id="addaudio" style="display: none;">
                                        <input type="file" name="addvideo" id="addvideo" style="display: none;">
										
										<div class="formfield">
                                            <label>Constraints</label>
                                            
					    <form:textarea path="question.constrnt"  style="height:50px" />
					    
                                          </div>
										  
										   <div class="formfield">
                                            <label>Question Weight</label>
                                            <form:input path="question.questionWeight" name="questionWeight" id="questionWeight" placeholder="Enter a number between 1 to 10" onkeypress="return isNumberKey(event)"/>
                                        </div>

                                        <div class="formfield">
                                            <label>Instructions, if any</label>
                                            <form:textarea path="question.instructionsIfAny" style="overflow-y: scroll" id="output" />
                                        </div>
										
										<div class="formfield">
                                            <label>Course Qualifier Description, if any</label>
                                            <form:input path="question.qualifierDescription" name="qualifierDescription" id="qualifierDescription" />
                                        </div>
										
										<div class="formfield">
                                            <label>Course Context, if any</label>
                                            <form:input path="question.courseContext" name="courseContext" id="courseContext" />
                                        </div>
										
										 <div class="formfield">
                                            <label>Weight for Course Context, if any</label>
                                            <form:input path="question.courseWeight" name="courseWeight" id="courseWeight" placeholder="Enter a number between 1 to 10"/>
                                        </div>


                                        <div class="formfield savebtn">
                                           
                                         <!--   <input type="submit" value="Save and add another"> -->
                                            <input type="button" value="Cancel" onClick="goback()">
                                        </div>
										 <input class="save" type="submit" value="Save">
                                    </form>
                                </div>

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
	
	 window.onload = function() {
		var qtype = '${question.type}';
		manageQuestionTypeUI(qtype);
			/* if(qtype == 'CODING'){
			document.getElementById("choice1").required = false;
			document.getElementById("choice2").required = false;
			
			document.getElementById("input").required = true;
			document.getElementById("output").required = true;
			}
			else if(qtype == 'FULL_STACK_JAVA' || qtype == 'FULLSTACK'){
			document.getElementById("choice1").required = false;
			document.getElementById("choice2").required = false;
			
			document.getElementById("input").required = false;
			document.getElementById("output").required = false;
			
			}
			else{
				document.getElementById("choice1").required = true;
			document.getElementById("choice2").required = true;
			
			document.getElementById("input").required = false;
			document.getElementById("output").required = false;
			} */
		
	    }
		
	
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
            $(function () {
                $(".addimage").on('click', function (e) {
                    e.preventDefault();
                    $("#addimage").trigger('click');
                });
                $(".addaudio").on('click', function (e) {
                    e.preventDefault();
                    $("#addaudio").trigger('click');
                });
                $(".addvideo").on('click', function (e) {
                    e.preventDefault();
                    $("#addvideo").trigger('click');
                });
            });


            $('#addimage').change(function () {
                var file = $('#addimage')[0].files[0].name;
                $('.queimage').text('Image: '+file);
            });
            $('#addaudio').change(function () {
                var file = $('#addaudio')[0].files[0].name;
                $('.queaudio').text('Audio: '+file);
            });
            $('#addvideo').change(function () {
                var file = $('#addvideo')[0].files[0].name;
                $('.quevideo').text('Video: '+file);
            });

	    
	      $('#search').on('click',function(){
	    var text = document.getElementById("searchText").value;
		if(text.length != 0){
		window.location="searchQuestions2?searchText="+text;
		}
	    });
	    
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
		
		function confirm(id) {
           (new PNotify({
		    title: 'Confirmation Needed',
		    text: 'Are you sure? Do you really want to delete this Q?',
		    icon: 'glyphicon glyphicon-question-sign',
		    hide: false,
		    confirm: {
			confirm: true
		    },
		    buttons: {
			closer: false,
			sticker: false
		    },
		    history: {
			history: false
		    }
		})).get().on('pnotify.confirm', function() {
		    window.location = "removeQuestion?qid="+id;
		}).on('pnotify.cancel', function() {
		   
		});
        }
		
		
	function manageQuestionTypeUI(selectedValue){
		if(selectedValue == 'CODING'){
		
			document.getElementById("mcqDiv").style.display = 'none';
			document.getElementById("codingDiv").style.display = '';
			
			document.getElementById("choice1").required = false;
			document.getElementById("choice2").required = false;
			
			if(document.getElementById("input") != null){
				document.getElementById("input").required = false;
			}
			
			if(document.getElementById("output") != null){
				document.getElementById("output").required = false;
			}
			//document.getElementById("justification").required = false;
			
			if(document.getElementById("fillBlanksDiv") != null){
				document.getElementById("fillBlanksDiv").style.display = 'none';
			}
			
			if(document.getElementById("noOfFillBlanks") != null){
				document.getElementById("noOfFillBlanks").required = false;
			}
			
			if(document.getElementById("fillInBlankOptions") != null){
				document.getElementById("fillInBlankOptions").required = false;
			}
			
		
			if(document.getElementById("matchFollowingDiv") != null){
				document.getElementById("matchFollowingDiv").style.display = 'none';
			}
			if(document.getElementById("matchLeft1") != null){
				document.getElementById("matchLeft1").required = false;
			}
			if(document.getElementById("matchLeft2") != null){
				document.getElementById("matchLeft2").required = false;
			}
			
			if(document.getElementById("matchRight1") != null){
				document.getElementById("matchRight1").required = false;
			}
			if(document.getElementById("matchRight2") != null){
				document.getElementById("matchRight2").required = false;
			}
			
			document.getElementById("inputCode").required = true;
			document.getElementById("hiddenInputPositive").required = true;
			document.getElementById("hiddenOutputPositive").required = true;
			document.getElementById("hiddenInputNegative").required = true;
			document.getElementById("hiddenOutputNegative").required = true;
			
			document.getElementById("hiddenInputExtremeMinimalValue").required = true;
			document.getElementById("hiddenOutputExtremeMinimalValue").required = true;
			
			document.getElementById("hiddenInputExtremePositiveValue").required = true;
			document.getElementById("hiddenOutputExtremePositiveValue").required = true;
			
			document.getElementById("hiddenInputInvalidDataValue").required = true;
			document.getElementById("hiddenOutputInvalidDataValue").required = true;
			
			
			
		}
		else if(selectedValue == 'FULL_STACK_JAVA' || selectedValue == 'FULLSTACK'){
			document.getElementById("mcqDiv").style.display = 'none';
			document.getElementById("codingDiv").style.display = 'none';
			document.getElementById("fullstackDiv").style.display = '';
			document.getElementById("choice1").required = false;
			document.getElementById("choice2").required = false;
			if(document.getElementById("input") != null){
				document.getElementById("input").required = false;
			}
			
			if(document.getElementById("output") != null){
				document.getElementById("output").required = false;
			}
			//document.getElementById("justification").required = false;
			
			if(document.getElementById("fillBlanksDiv") != null){
				document.getElementById("fillBlanksDiv").style.display = 'none';
			}
			
			if(document.getElementById("noOfFillBlanks") != null){
				document.getElementById("noOfFillBlanks").required = false;
			}
			
			if(document.getElementById("fillInBlankOptions") != null){
				document.getElementById("fillInBlankOptions").required = false;
			}
			
		
			if(document.getElementById("matchFollowingDiv") != null){
				document.getElementById("matchFollowingDiv").style.display = 'none';
			}
			if(document.getElementById("matchLeft1") != null){
				document.getElementById("matchLeft1").required = false;
			}
			if(document.getElementById("matchLeft2") != null){
				document.getElementById("matchLeft2").required = false;
			}
			
			if(document.getElementById("matchRight1") != null){
				document.getElementById("matchRight1").required = false;
			}
			if(document.getElementById("matchRight2") != null){
				document.getElementById("matchRight2").required = false;
			}
			setCodeInputsReqFalse();
		}
		else if(selectedValue == 'MCQ'){
			document.getElementById("mcqDiv").style.display = '';
			document.getElementById("codingDiv").style.display = 'none';
			document.getElementById("fullstackDiv").style.display = 'none';
			
			document.getElementById("choice1").required = true;
			document.getElementById("choice2").required = true;
			if(document.getElementById("input") != null){
				document.getElementById("input").required = false;
			}
			
			if(document.getElementById("output") != null){
				document.getElementById("output").required = false;
			}
			
			document.getElementById("fullstackDiv").style.display = 'none';
			document.getElementById("noOfFillBlanks").required = false;
			document.getElementById("fillInBlankOptions").required = false;
		//	document.getElementById("justification").required = true;
		
			if(document.getElementById("fillBlanksDiv") != null){
				document.getElementById("fillBlanksDiv").style.display = 'none';
			}
			
			if(document.getElementById("noOfFillBlanks") != null){
				document.getElementById("noOfFillBlanks").required = false;
			}
			
			if(document.getElementById("fillInBlankOptions") != null){
				document.getElementById("fillInBlankOptions").required = false;
			}
			
		
			if(document.getElementById("matchFollowingDiv") != null){
				document.getElementById("matchFollowingDiv").style.display = 'none';
			}
			if(document.getElementById("matchLeft1") != null){
				document.getElementById("matchLeft1").required = false;
			}
			if(document.getElementById("matchLeft2") != null){
				document.getElementById("matchLeft2").required = false;
			}
			
			if(document.getElementById("matchRight1") != null){
				document.getElementById("matchRight1").required = false;
			}
			if(document.getElementById("matchRight2") != null){
				document.getElementById("matchRight2").required = false;
			}
			setCodeInputsReqFalse();
		}
		else if(selectedValue == 'FILL_BLANKS_MCQ'){
			if(document.getElementById("mcqDiv") != null){
				document.getElementById("mcqDiv").style.display = 'none';
			}
			
			if(document.getElementById("codingDiv") != null){
				document.getElementById("codingDiv").style.display = 'none';
			}
			
			if(document.getElementById("fullstackDiv") != null){
				document.getElementById("fullstackDiv").style.display = 'none';
			}
			
			if(document.getElementById("choice1") != null){
				document.getElementById("choice1").required = false;
			}
			
			if(document.getElementById("choice2") != null){
				document.getElementById("choice2").required = false;
			}
			
			if(document.getElementById("input") != null){
				document.getElementById("input").required = false;
			}
			
			if(document.getElementById("output") != null){
				document.getElementById("output").required = false;
			}
			
			document.getElementById("fillBlanksDiv").style.display = '';
			document.getElementById("noOfFillBlanks").required = true;
			document.getElementById("fillInBlankOptions").required = true;
			
			if(document.getElementById("matchFollowingDiv") != null){
				document.getElementById("matchFollowingDiv").style.display = 'none';
			}
			if(document.getElementById("matchLeft1") != null){
				document.getElementById("matchLeft1").required = false;
			}
			if(document.getElementById("matchLeft2") != null){
				document.getElementById("matchLeft2").required = false;
			}
			
			if(document.getElementById("matchRight1") != null){
				document.getElementById("matchRight1").required = false;
			}
			if(document.getElementById("matchRight2") != null){
				document.getElementById("matchRight2").required = false;
			}
			setCodeInputsReqFalse();
		}
		
		else if(selectedValue == 'MATCH_FOLLOWING_MCQ'){
			
			if(document.getElementById("mcqDiv") != null){
				document.getElementById("mcqDiv").style.display = 'none';
			}
			
			if(document.getElementById("codingDiv") != null){
				document.getElementById("codingDiv").style.display = 'none';
			}
			
			if(document.getElementById("fullstackDiv") != null){
				document.getElementById("fullstackDiv").style.display = 'none';
			}
			
			
			if(document.getElementById("choice1") != null){
				document.getElementById("choice1").required = false;
			}
			
			if(document.getElementById("choice2") != null){
				document.getElementById("choice2").required = false;
			}
			
			
			
			if(document.getElementById("input") != null){
				document.getElementById("input").required = false;
			}
			
			if(document.getElementById("output") != null){
				document.getElementById("output").required = false;
			}
			
			if(document.getElementById("fillBlanksDiv") != null){
				document.getElementById("fillBlanksDiv").style.display = 'none';
			}
			
			if(document.getElementById("noOfFillBlanks") != null){
				document.getElementById("noOfFillBlanks").required = false;
			}
			
			if(document.getElementById("fillInBlankOptions") != null){
				document.getElementById("fillInBlankOptions").required = false;
			}
			
			
			
			
			document.getElementById("matchFollowingDiv").style.display = '';
			document.getElementById("matchLeft1").required = true;
			document.getElementById("matchLeft2").required = true;
			document.getElementById("matchRight1").required = true;
			document.getElementById("matchRight2").required = true;
			setCodeInputsReqFalse();
		//	document.getElementById("justification").required = true;
		}
		else if(selectedValue == 'IMAGE_UPLOAD_BY_USER' || selectedValue == 'VIDEO_UPLOAD_BY_USER' || selectedValue == 'SUBJECTIVE_TEXT'){
			if(document.getElementById("mcqDiv") != null){
				document.getElementById("mcqDiv").style.display = 'none';
			}
			
			if(document.getElementById("codingDiv") != null){
				document.getElementById("codingDiv").style.display = 'none';
			}
			
			if(document.getElementById("fullstackDiv") != null){
				document.getElementById("fullstackDiv").style.display = 'none';
			}
			
			
			if(document.getElementById("choice1") != null){
				document.getElementById("choice1").required = false;
			}
			
			if(document.getElementById("choice2") != null){
				document.getElementById("choice2").required = false;
			}
			
			
			
			if(document.getElementById("input") != null){
				document.getElementById("input").required = false;
			}
			
			if(document.getElementById("output") != null){
				document.getElementById("output").required = false;
			}
			
			if(document.getElementById("fillBlanksDiv") != null){
				document.getElementById("fillBlanksDiv").style.display = 'none';
			}
			
			if(document.getElementById("noOfFillBlanks") != null){
				document.getElementById("noOfFillBlanks").required = false;
			}
			
			if(document.getElementById("fillInBlankOptions") != null){
				document.getElementById("fillInBlankOptions").required = false;
			}
			
			
			
			if(document.getElementById("matchFollowingDiv") != null){
				document.getElementById("matchFollowingDiv").style.display = 'none';
			}
			
			if(document.getElementById("matchLeft1") != null){
				document.getElementById("matchLeft1").required = false;
			}
			
			if(document.getElementById("matchLeft2") != null){
				document.getElementById("matchLeft2").required = false;
			}
			
			if(document.getElementById("matchRight1") != null){
				document.getElementById("matchRight1").required = false;
			}
			if(document.getElementById("matchRight2") != null){
				document.getElementById("matchRight2").required = false;
			}
			
			
		}
	}
	
	function changeQType(){
	var  selectedValue= $("#questionType").val();
		manageQuestionTypeUI(selectedValue);
	}
	
	function setCodeInputsReqFalse(){
			if(document.getElementById("inputCode") != null){
				document.getElementById("inputCode").required = false;
			}
			
			if(document.getElementById("hiddenInputPositive") != null){
				document.getElementById("hiddenInputPositive").required = false;
			}
			
			if(document.getElementById("hiddenOutputPositive") != null){
				document.getElementById("hiddenOutputPositive").required = false;
			}
		
			if(document.getElementById("hiddenInputNegative") != null){
				document.getElementById("hiddenInputNegative").required = false;
			}
			
			if(document.getElementById("hiddenOutputNegative") != null){
				document.getElementById("hiddenOutputNegative").required = false;
			}
			
			if(document.getElementById("hiddenInputExtremeMinimalValue") != null){
				document.getElementById("hiddenInputExtremeMinimalValue").required = false;
			}
			if(document.getElementById("hiddenOutputExtremeMinimalValue") != null){
				document.getElementById("hiddenOutputExtremeMinimalValue").required = false;
			}
			
			if(document.getElementById("hiddenInputExtremePositiveValue") != null){
				document.getElementById("hiddenInputExtremePositiveValue").required = false;
			}
			if(document.getElementById("hiddenOutputExtremePositiveValue") != null){
				document.getElementById("hiddenOutputExtremePositiveValue").required = false;
			}
			
			if(document.getElementById("hiddenInputInvalidDataValue") != null){
				document.getElementById("hiddenInputInvalidDataValue").required = false;
			}
			if(document.getElementById("hiddenOutputInvalidDataValue") != null){
				document.getElementById("hiddenOutputInvalidDataValue").required = false;
			}
			
			
			
	}
	
		function isNumberKey(e){
			var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
			  var value = Number(e.target.value + e.key) || 0;

			  if ((keyCode >= 37 && keyCode <= 40) || (keyCode == 8 || keyCode == 9 || keyCode == 13) || (keyCode >= 48 && keyCode <= 57)) {
				return isValidNumber(value);
			  }
			  return false;
		}   
		
		function isValidNumber (number) {
		  return (1 <= number && number <= 10 )
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
