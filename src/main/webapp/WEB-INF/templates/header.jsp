<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/templates/include.jsp" %>
<html>
<head>
<link type="text/css" media="all" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/jquery-ui-1.8.14.custom.css"/>
<link type="text/css" media="all" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/style.css"/>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/itemTable/tableStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/itemTable/vendor/modernizr-2.6.2.min.js"></script>
<script>
var jsonUrl = '<c:url value="/pages/list"/>';
var uploadsLocation = '<c:url value="../resources/images/uploads"/>';
</script>
</head>
<body>
<h1>header</h1>