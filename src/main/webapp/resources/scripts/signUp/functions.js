$(document).ready(function(){
	setCities(document.getElementById("countries"));
	$("#jsonBtn").click(function(){
		getList();
	});
	
	$("#countries").change(function() {
		setCities(this);
	});	
});

function setCities(countries) {
	var cityContainer = document.getElementById("cities");
	$(cityContainer).html('');
	addOption(cityContainer, "City");
	addOption(cityContainer, "");
	var iso = $(countries).find(":selected").attr("value");

	if(iso != "country" && iso != "") {
		$.ajax({
			url : "http://api.geonames.org/searchJSON?username=RobertG&country="+iso,
			dataType : 'json',
			success : function(data) {
				var cities = data.geonames;
				var cityNames = [];
				for ( var i = 0; i < cities.length; i++) {
					cityNames.push(cities[i].toponymName);
				}
				cityNames.sort();
				for ( var i = 0; i < cityNames.length; i++) {
					addOption(cityContainer, cityNames[i]);
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert('error');
			}
		});
	}
}

function addOption(parent, name) {
	if(parent != null) {
		var city = document.createElement("option");
		city.setAttribute("value", name);
		city.appendChild(document.createTextNode(name));
		parent.appendChild(city);
	}
}

function getList() {
	$.ajax({
		url : jsonUrl,
		dataType : 'json',
		success : function(data) {
			$('#json_resp').text(data[0]);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert('error');
		}
	});
}

