<%@ include file="/WEB-INF/pages/generalCSSIncludes.jsp"%>
<!-- Item details includes -->
<!-- <link type="text/css" rel="stylesheet" -->
<%-- 	href="${pageContext.request.contextPath}/resources/styles/itemPage/itemDetails.css" /> --%>

<script>
	function replaceAll(find, replace, str) {
		return str.replace(new RegExp(find, 'g'), replace);
	}
	var uploadsLocation = '<c:url value="resources/images/uploads"/>';
	var unparsedItem = '<c:out value="${item}"/>';
	unparsedItem = replaceAll('&#034;', '"', unparsedItem);
	unparsedItem = replaceAll("&#039;", "'", unparsedItem);
	var item = JSON.parse(unparsedItem);

	var auctionId = item["id"];
	var bidStep = item["step"];
	var minBid = item["minimumBid"];
	var bouyoutVal = item["buyout"];
	if(item["lastBid"] == null) {
		item["lastBid"] = {"value": minBid};
	}
	var lastBid = item["lastBid"]["value"];
	var userEmail = '<%= session.getAttribute( "user_name" ) %>';
</script>
<title>Item details</title>
</head>

<body>
	<!--[if lt IE 7]>
	            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
	        <![endif]-->
	<%@ include file="/WEB-INF/pages/header.jsp"%>

	<section class="center">
		<h1 id="name"></h1>
		<p id="end-time"></p>
		<img id="image" />
		<p id="description"></p>
		<ul id="details">
			<li class="bidClass"><span id="last-bid"></span> <input
				id="bidTextbox" /> <span id="min-bid"></span>
				<button class="placebid" id="bid-button">Place bid</button></li>
			<li class="bidClass"><span id="buyout"></span>
				<button class="placebid" id="buyout-button">Buy it Now</button></li>
			<li id="user"></li>
			<li>
				<ul id="restrictions">
				</ul>
			</li>
		</ul>
		<div class="footer">
			<p id="payId">Payment details:</p>
			<ul class="ulfooter">
				<li class="commentClass"><label id="comm1">Payment
						method</label> <label id="comm2">Preferred/Accepted</label></li>
			</ul>
			<img id="paypal"
				src="${pageContext.request.contextPath}/resources/images/itemPage/paypal.gif" />
			<p id="paypalpreff">PayPal Preferred</p>
			<p id="sellInstruct">Seller's payment instructions</p>
			<p id="instruction">All payments are due within 5 days of auction
				end unless other arrangements have been agreed upon, Paypal is my
				prefered method of payment. If you are still shopping after items
				end on my weekly auctions, please let me know. Thank You, Kevin</p>
		</div>
	</section>
	</div>
	</div>
	<%@ include file="/WEB-INF/pages/generalJSIncludes.jsp"%>
	<!-- Item details includes -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/scripts/itemPage/itemdetails.js"></script>
</body>
</html>
