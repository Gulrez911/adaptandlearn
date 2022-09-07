<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*, com.assessment.web.dto.*" %>

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>eAssess</title>
      <link href="images/E-assess_E.png" rel="shortcut icon">
      
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

		
		
		
		
        <script src="https://kit.fontawesome.com/dcf0f9173b.js" crossorigin="anonymous"></script>
		
		<link href="user_profile_j/css/styles.css" rel="stylesheet" type="text/css">
		<link href="user_profile_j/css/dashboard.css" rel="stylesheet" type="text/css">
		
		
    </head>
    <body>
        <div class="master-container">
            <header class="post-login">
                <div class="container">
				<div class="logo">
                    <a href="#" class="my-auto">
                        <img src="user_profile_j/images/logo.jpg" alt="Yaksha Assessment Platform">
                    </a>
					</div>
					<div class="navmenu">
						<nav>
                        <ul>
                            
                           <!-- <li class="search my-auto">
                                <form action="" class="search-form">
                                    <input type="text" placeholder="search...">
                                    <button class="icon" type="button">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </form>
                                <div class="drop-menu-bg"></div>
                                <a href="#" class="tempclick">
                                    <i class="fa fa-search"></i>
                                </a>
                            </li> -->
                            
                            <li class="user-info my-auto active">
                                <span class="my-auto">${user.firstName}, ${user.lastName}</span>
                                <span class="thumbnail"></span>
                                <div class="drop-menu-bg"></div>
                                <ul class="user-navs drop-menu">
                                    <li>
                                        <a href="learnerlogoff">
                                            Log Out
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
					</div>
                    
                </div>
                <div class="submenu">
                    <div class="container">
                        <a href="learnerDashboardJ" class="active">
                            Dashboard
                        </a>
                        <a href="showUserSessions">
                            Assessments
                        </a>
                        <a href="getAllRecommendationsForUser">
                            User Profile
                        </a>
                    </div>
                </div>
            </header>
            <section>

		<%
		List<LicenseModuleDTO> licModules= (List<LicenseModuleDTO>) request.getAttribute("licModules");
		System.out.println("licModules size "+licModules.size() );

		List<List<LicenseModuleDTO>> collections = new ArrayList(); 
		int count = 0;
		List<LicenseModuleDTO> coll = new  ArrayList();
			for(int i=0;i<licModules.size(); i++){
			coll.add(licModules.get(i));
			count ++;
				if(count == 3){
				collections.add(coll);
				System.out.println("adding to coll of cooll "+coll.size());
				count = 0;
				coll = new ArrayList();

				}

				if(i == licModules.size() - 1){
				collections.add(coll);
				System.out.println("final adding to coll of cooll "+coll.size());

				}
			}
		System.out.println("all coll size "+collections.size() );

			for(List<LicenseModuleDTO> l : collections){
			System.out.println("l coll size "+l.size() );

			}
			request.setAttribute("collections", collections);
		%>
		<c:forEach  items="${collections}" var="partition" >  
		 

                <div class="container">
				
                    <div class="quick-access">
					<c:forEach  items="${partition}" var="lic" varStatus="loop">  
                        <div class="${lic.styleClass} dataitem">
                            <h2>${lic.license.licenseName}</h2>
                            <ul class="expandibleitem">
								<c:forEach  items="${lic.modules}" var="module" varStatus="loop">  
                                <li>
                                    <div class="name">
                                        <div class="image">
                                            <!--  <img src="user_profile_j/images/default.jpg" alt=""> -->
                                        </div>
                                        <div class="my-auto">
                                            ${module.module.moduleName}
                                        </div>
                                    </div>
                                    <div class="interaction">
                                        <button class="btn btn-alternate" type="button" onclick="viewModal();loadModuleItems('${module.module.id}')">Continue</button>
                                    </div>
                                </li>
								
                                </c:forEach>
					 <c:choose>
                                               <c:when test="${lic.modules.size() < 3}">
                                                        
       						<span class="loadMore" style="display:none">More <i class="fa fa-chevron-down" aria-hidden="true"></i></span>	
						<span class="ShowLess" style="display:none;">Less <i class="fa fa-chevron-up" aria-hidden="true"></i></span>
                                                </c:when>    
                                               <c:otherwise>
							<span class="loadMore" >More <i class="fa fa-chevron-down" aria-hidden="true"></i></span>	
						<span class="ShowLess" style="display:none;" >Less <i class="fa fa-chevron-up" aria-hidden="true"></i></span>

                                                </c:otherwise>
                                               </c:choose>								
                            </ul>
                        </div>
					
					</c:forEach>           
					</div>		
						
					
                </div>
		</c:forEach>   		

		<div class="container">
				<div class="quick-access">
					<c:forEach  items="${freeLicModules}" var="lic" varStatus="loop">  
                        <div class="${lic.styleClass} dataitem">
                            <h2>${lic.license.licenseName}</h2>
                            <ul class="expandibleitem">
								<c:forEach  items="${lic.modules}" var="module" varStatus="loop">  
                                <li >
                                    <div class="name">
                                        <div class="image">
                                            <!--  <img src="user_profile_j/images/default.jpg" alt=""> -->
                                        </div>
                                        <div class="my-auto">
                                            ${module.module.moduleName}
                                        </div>
                                    </div>
                                    <div class="interaction">
                                        <button class="btn btn-alternate" type="button" onclick="viewModal();loadModuleItems('${module.module.id}')">Continue</button>
                                    </div>
                                </li>
								
                                </c:forEach>  
						<c:choose>
                                               <c:when test="${lic.modules.size() < 3}">
                                                        
       						<span class="loadMore" style="display:none">More <i class="fa fa-chevron-down" aria-hidden="true"></i></span>	
						<span class="ShowLess" style="display:none;">Less <i class="fa fa-chevron-up" aria-hidden="true"></i></span>
                                                </c:when>    
                                               <c:otherwise>
							<span class="loadMore" >More <i class="fa fa-chevron-down" aria-hidden="true"></i></span>	
						<span class="ShowLess" style="display:none;">Less <i class="fa fa-chevron-up" aria-hidden="true"></i></span>

                                                </c:otherwise>
                                               </c:choose>								
								
                            </ul>
                        </div>
					
					</c:forEach>           
					</div>
				</div>
            </section>
			
			
            <footer>
                <div class="container">
                    <a >
                        &copy; Copyright 2020
                    </a>
                    <div>
                        <a href="#">
                            Terms and Conditions
                        </a>
                        <a href="#">
                            Privacy Policy
                        </a>
                        <a href="#">
                            Contact us
                        </a>
                    </div>
                </div>
            </footer>
        </div>
         <div class="dialog-modal-bg"></div>
        <div class="dialog-modal popupadd">
		   <div id="moduleItemsContent">

		   </div>
        </div>
		
		<!--- iframe -->
		 <div class="modal fade" id="myModal1" role="dialog" >
         <div class="modal-dialog modal-lg" style="width:90%;">
            <div class="modal-content1" style="background:#fff !important;">
               <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
               </div>
               <div class="modal-body">
                  <iframe id="myifrem" class="iframes setsiteurl" src=""></iframe>
               </div>
            </div>
         </div>
      </div>
      <div class="modal fade" id="myModal2" role="dialog">
         <div class="modal-dialog modal-lg">
            <div class="modal-content1" style="background:#fff !important;">
               <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
               </div>
               <div class="modal-body">
                  <!-- <video width="100%" height="500" controls>
                     <source class="seturl" src="" type="video/mp4" controls>
                     <source class="seturl" src="" type="video/ogg" controls>
                     </video> -->
                  <iframe class="seturl" src="" width="100%" height="500"></iframe>
               </div>
            </div>
         </div>
      </div>
	  
	 <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script> -->
	 <script type="text/javascript" src="user_profile_j/js/jquery.min.js"></script>
		<script type="text/javascript" src="user_profile_j/js/scripts.js"></script>
	
	
	<script>
         $(document).on('click','.geturl',function(){
             var vurl = $(this).attr('href');
             $(".seturl").attr('src',vurl);
         });
		 
		 $(document).on('click','.getsiteurl',function(){
			 var siteurl = $(this).attr('href');
				
             $(".setsiteurl").attr('src',siteurl);
         });
      </script>
	
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	  
		 

    </body>
	
	
	
	
<script>






$(function(){
$('.expandibleitem li').hide();
$('.expandibleitem li:nth-child(n+1):nth-child(-n+2)').show();

$('.loadMore').click(function(){
    size_li = $(this).closest('.expandibleitem').find('li').size();
    if($(this).closest('.expandibleitem').find('li:not(:visible)').size() <= 2){
      $(this).closest('.expandibleitem').find('.ShowLess').show();
      $(this).closest('.expandibleitem').find('.loadMore').hide();
    }
   $(this).closest('.expandibleitem').find('li:not(:visible):lt(2)').show();
})

$('.ShowLess').click(function(){
   $(this).closest('.expandibleitem').find('li').hide();
   $(this).closest('.expandibleitem').find('li:lt(2)').show();
   size_li = $(this).closest('.expandibleitem').find('li').size();
   if($(this).closest('.expandibleitem').find('li:not(:visible)').size() >= 2){
      $(this).closest('.expandibleitem').find('.ShowLess').hide();
      $(this).closest('.expandibleitem').find('.loadMore').show();
   }
})

})


</script>
	
  <script>
	function viewModal() {
    $('.dialog-modal-bg').addClass('active');
    $('.dialog-modal').addClass('active');
	}

   $('.dialog-modal-bg').on('click', function () {
    $('.dialog-modal-bg').removeClass('active');
    $('.dialog-modal').removeClass('active');
   });

   $('.start-assessment').on('click', function () {
    $('.dialog-content .module-list').addClass('close');
    $('.dialog-content iframe').addClass('active');
   });
   
	$(document).on('click','.popupadd .closeicon',function(){
    $('.dialog-modal-bg').removeClass('active');
    $('.dialog-modal').removeClass('active');
   });
   
   $('#myModal1').modal({backdrop: 'static', keyboard: true,show: false}) 
   $('#myModal2').modal({backdrop: 'static', keyboard: true,show: false}) 


	function loadModuleItems(moduleId) {
				
				$.ajax({
					xhr : function() {
						var xhr = new window.XMLHttpRequest();

						return xhr;
					},
					url : 'fetchModuleItemsForModuleJ?moduleId=' + moduleId,
					type : "GET",
					processData : false,
					contentType : false
				}).done(function(data) {
					document.getElementById("moduleItemsContent").innerHTML = data;
					//notify('Info', 'Data fetched');

				}).fail(function(jqXHR, textStatus) {
					notify('Failure', 'Problem in loading Module Items. Contact Admin');
				});

			}

		function notify(msg){
			bootbox.alert({
				message: msg,
				size: 'large',
				backdrop: true
			});
		}	

	</script>
	
	

    
</html>