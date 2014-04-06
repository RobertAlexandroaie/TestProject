$(function() {
     $("#next-button").click(function(e) {
		e.preventDefault();
		var circleId = $(".current")[0].getAttribute("id");
		$("#"+circleId).removeClass("current");
		var stepId = circleId.split("-circle")[0];		
		var stepNumber = parseInt(stepId[stepId.length - 1]+"") + 1;		
		$("#"+stepId).fadeOut('slow', function() {
			if (stepNumber == 3) {
				$("#next-button").attr("id", "finish-button");
				$("#finish-button").text("Finish");
				$("#finish-button").on("click", function(e) {
					alert("You have registered");
				});
			}
			$("#step-" + stepNumber + "-circle").addClass("current");
			$("#step-" + stepNumber).fadeIn('slow');
		});		
	});
	$(window).resize(function() {
		var width = $("form .circle")[0].offsetWidth;
		$("form .circle").css("height", width);
	});
	$(window).resize();
	$("button").button();
});
