$(document).ready(function(){
	var options = document.getElementById('select-categories').childNodes;
	for(i = 0; i < options.length; i++) {
		if(options[i].toString() === "[object HTMLOptionElement]") {
			if(options[i].getAttribute("value") == selectedOption) {	
				options[i].selected = true;
				break;
			}
		}
	}
	
	setCategories(document.getElementById("select-categories"));
	
	
	$("#select-categories").change(function() {
		setCategories(this);
	});	
	
//for keywords	
	var config = {
			rowClass : 'keyword',
			addRowId : 'addKeyword',
			removeRowClass : 'removeKeyword',
			formId : 'main',
			rowContainerId : 'keywordsListContainer',
			indexedPropertyName : 'keywordsListContainer.keywordsList',
			indexedPropertyMemberNames : 'name',
			rowAddedListener : rowAdded,
			rowRemovedListener : rowRemoved,
			beforeSubmit : beforeSubmit
		};
		new DynamicListHelper(config);
		
//for locations
		var configLocations = {
				rowClass : 'location',
				addRowId : 'addLocation',
				removeRowClass : 'removeLocation',
				formId : 'main',
				rowContainerId : 'locationsListContainer',
				indexedPropertyName : 'locationsListContainer.locationsList',
				indexedPropertyMemberNames : 'name',
				rowAddedListener : rowAdded2,
				rowRemovedListener : rowRemoved2,
				beforeSubmit : beforeSubmit2
			};
			new DynamicListHelper2(configLocations);
			
//for images
	var configImages = {
			rowClass : 'image',
			addRowId : 'addImage',
			removeRowClass : 'removeImage',
			formId : 'main',
			rowContainerId : 'imagesListContainer',
			indexedPropertyName : 'imagesListContainer.imagesList',
			indexedPropertyMemberNames : 'name',
			rowAddedListener : rowAdded3,
			rowRemovedListener : rowRemoved3,
			beforeSubmit : beforeSubmit3
		};
		new DynamicListHelper3(configImages);
		

});

function setCategories(category) {
	var subcategoryContainer = document.getElementById("subcategory-select");
	$(subcategoryContainer).html('');
	
	var categoryName = $(category).find(":selected").attr("value");

		$.ajax({
			url : "http://localhost:9080/EnCoders/auction/getSubcategory?category="+categoryName,
			dataType : 'json',
			success : function(data) {
				var subcategories = data;
				var subcategoriesNames = [];
				for ( var i = 0; i < subcategories.length; i++) {
					subcategoriesNames.push(subcategories[i].name);
				}
				subcategoriesNames.sort();
				for ( var i = 0; i < subcategoriesNames.length; i++) {
					addOption(subcategoryContainer, subcategoriesNames[i]);
				}
				
				var subcategories = document.getElementById('subcategory-select').childNodes;

				for(i = 0; i < subcategories.length; i++) {
					if(subcategories[i].toString() === "[object HTMLOptionElement]") {
						if(subcategories[i].getAttribute("value") == selectedSubcategory) {	
							subcategories[i].selected = true;
							break;
						}
					}
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert('error');
			}
		});
}

function addOption(parent, name) {
	var subcategory = document.createElement("option");
	subcategory.setAttribute("value", name);
	subcategory.appendChild(document.createTextNode(name));
	parent.appendChild(subcategory);
}

//for keywords 

function rowAdded(rowElement) {
	//clear the imput fields for the row
	$(rowElement).find(".keyword-input").val('');
	//may want to reset <select> options etc

	//in fact you may want to submit the form
	saveNeeded();
}
function rowRemoved(rowElement) {
	saveNeeded();
}

function saveNeeded() {
	$('#submit').css('color', 'red');
	$('#submit').css('font-weight', 'bold');
	if ($('#submit').val().indexOf('!') != 0) {
		$('#submit').val('!' + $('#submit').val());
	}
}

function beforeSubmit() {
	return true;
}

//for locations

function rowAdded2(rowElement) {
	//clear the imput fields for the row
	$(rowElement).find(".location-input").val('');
	//may want to reset <select> options etc

	//in fact you may want to submit the form
	saveNeeded2();
}
function rowRemoved2(rowElement) {
	saveNeeded2();
}

function saveNeeded2() {
	$('#submit-location').css('color', 'red');
	$('#submit-location').css('font-weight', 'bold');
	if ($('#submit-location').val().indexOf('!') != 0) {
		$('#submit-location').val('!' + $('#submit-location').val());
	}
}

function beforeSubmit2() {
	return true;
}

//for images

function rowAdded3(rowElement) {
	//clear the imput fields for the row
	$(rowElement).find(".image-input").val('');
	//may want to reset <select> options etc

	//in fact you may want to submit the form
	saveNeeded3();
}
function rowRemoved3(rowElement) {
	saveNeeded3();
}

function saveNeeded3() {
	$('#submit-location').css('color', 'red');
	$('#submit-location').css('font-weight', 'bold');
	if ($('#submit-location').val().indexOf('!') != 0) {
		$('#submit-location').val('!' + $('#submit-location').val());
	}
}

function beforeSubmit3() {
	return true;
}
