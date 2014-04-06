<%@ include file="/WEB-INF/pages/generalCSSIncludes.jsp"%>
	<!-- Start page includes -->
	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/styles/startPage/main.css" />
	
	<!-- Item table includes -->
	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/styles/itemTable/main.css" />
	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/styles/itemTable/tableStyle.css" />
</head>

<body>
	<!--[if lt IE 7]>
	            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
	        <![endif]-->
	<%@ include file="/WEB-INF/pages/header.jsp"%>
	<%@ include file="/WEB-INF/pages/startPage.jsp"%>
	</div>		
</div>
	<%@ include file="/WEB-INF/pages/generalJSIncludes.jsp"%>
	<!-- Item table includes -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/scripts/itemTable/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/scripts/itemTable/dataTable.js"></script>

</body>
</html>
