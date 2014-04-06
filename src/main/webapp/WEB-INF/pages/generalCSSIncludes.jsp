<%@ include file="/WEB-INF/pages/jspIncludes.jsp" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
       <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> --> 
        <title>EMay</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
		
		<!--  CSS includes -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/itemTable/reset.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/normalize.css"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/bootstrap.min.css"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/bootstrap-responsive.min.css"/>
		<link type="text/css" rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/font-awesome/css/font-awesome.min.css" />
		
		<!-- Login pop-up includes -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/loginPopUp/reveal.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/headerAndSignUpStyle.css" />
		
		<!-- Javascript includes to be executed before page load -->		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/vendor/modernizr-2.6.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>       
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/startPage/windowFunctionInitialization.js"></script>    
		<script>			
			var rootUrl = '<c:url value="/"/>';
		</script>