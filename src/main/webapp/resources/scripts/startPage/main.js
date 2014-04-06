$(function() {
	$(".category").click(function() {
		var id = $(this).attr('id');
		replaceData(id);
	});
	$(".subcategory").click(function() {
		var subcategory = $(this).attr('id');
		var category = "clothes";//$(this).parents('ul')[0].attr('id');
		replaceData(category, subcategory);
	});
	
	$( "input[type=submit], button" )
    .button()
    .click(function( event ) {
    });
});
