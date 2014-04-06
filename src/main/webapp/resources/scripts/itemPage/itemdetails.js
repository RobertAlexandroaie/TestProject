$(function() {
	$("#name").text(item.product.name);

	$("#description").text(item.product.description);

	$("#min-bid").text("Enter US $" + (lastBid + bidStep) + " or more");
	if(item.buyout!=undefined){
		$("#buyout").text("Price: US $" + item.buyout);
	}
	var daysAndHours = calculateTimeRemaining(item["endingTime"]);
	$("#end-time").text("Time left: " + daysAndHours.days + "d, " + daysAndHours.hours + "h (" + prettyDate(daysAndHours.date) + ")" );
	$("#user").text("Seller: " + item.user.firstName + ' ' + item.user.lastName);
	$("#last-bid").text("Current bid: US $" + lastBid);
	
	var label = document.createElement("label");
	label.appendChild(document.createTextNode("Ships to: "));
	document.getElementById("restrictions").appendChild(label);
	
	var restrictions = item.restrictions;
	
	if(restrictions!=undefined && restrictions!=null){
		if(restrictions.length > 0){
			var locations = restrictions;
			for ( var i = 0; i < locations.length - 1; i++) {
				var li = document.createElement("li");
				li.className = "locations";
				$(li).css("clear", "none");
				li.appendChild(document.createTextNode(locations[i] + ", "));
				document.getElementById("restrictions").appendChild(li);
			}
			var li = document.createElement("li");
			li.className = "locations";
			$(li).css("clear", "none");
			li.appendChild(document.createTextNode(locations[i]));
			document.getElementById("restrictions").appendChild(li);
		} else {
			noRestrictions();
		}
	} else {
		noRestrictions();
	}
	
	$("#image").attr("src", item.product.images[0]);
	
	
	$("#bid-button").click(function() {
		bid();
	});
	
	$("#buyout-button").click(function() {
		buyout();
	});
});
function noRestrictions() {
	var li = document.createElement("li");
	li.className = "locations";
	$(li).css("clear", "none");
	li.appendChild(document.createTextNode("All"));
	document.getElementById("restrictions").appendChild(li);
}

function calculateTimeRemaining(endTime) {
	var one_day=1000*60*60*24;
	var one_hour=1000*60*60;
	var endDate = new Date(endTime);
	var today = new Date();
	var days = Math.floor((endDate.getTime()-today.getTime())/(one_day));
	return {"days": days,
		"hours": Math.floor((endDate.getTime()-today.getTime())/(one_hour)) - days * 24,
		"date": endDate};
}

function prettyDate(date) {
	var month_names = new Array ( );
	month_names[month_names.length] = "January";
	month_names[month_names.length] = "February";
	month_names[month_names.length] = "March";
	month_names[month_names.length] = "April";
	month_names[month_names.length] = "May";
	month_names[month_names.length] = "June";
	month_names[month_names.length] = "July";
	month_names[month_names.length] = "August";
	month_names[month_names.length] = "September";
	month_names[month_names.length] = "October";
	month_names[month_names.length] = "November";
	month_names[month_names.length] = "December";

	var day_names = new Array ( );
	day_names[day_names.length] = "Sunday";
	day_names[day_names.length] = "Monday";
	day_names[day_names.length] = "Tuesday";
	day_names[day_names.length] = "Wednesday";
	day_names[day_names.length] = "Thursday";
	day_names[day_names.length] = "Friday";
	day_names[day_names.length] = "Saturday";
	
	return day_names[date.getDay()] + ", " + month_names[date.getMonth()] + " " + date.getDate() + " " + date.getFullYear();
}

function bid(){
	if(userEmail == "null") {
		alert("Please login befor trying to bid/buout.");
		return;
	}
	
	var bidValue = $("#bidTextbox").val();
	if(bidValue<bidStep+lastBid) {
		alert("Bid value too low!");
		return ;
	}

	var json = {"value": bidValue, "auctionId": auctionId};
	$.ajax({
		  type: "POST",
		  url: 'http://localhost:9080/EnCoders/details/bid',
		  data: json
		});
}

function buyout() {
	if(userEmail==null || userEmail == undefined || userEmail=="") {
		alert("You are not logged in");
		return;
	}

	var json = {"auctionId": auctionId};
	
	$.ajax({
		  type: "POST",
		  url: 'http://localhost:9080/EnCoders/details/buyout',
		  data: json
		});
}

