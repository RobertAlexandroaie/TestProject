//<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
//add a element with the id dynamicTable
var oTable;
var th = [
			{ "sTitle": "Engine" },
			{ "sTitle": "Browser" },
			{ "sTitle": "Platform" },
			{ "sTitle": "Version", "sClass": "center" },
			{ "sTitle": "Grade", "sClass": "center" }
		];

var tableHeaders = [
					{ "mData": "product.name", "sTitle": "Name", "sClass": "center table-name"  },
					{ "mData": "product.description", "sTitle": "Description", "sClass": "center table-description"  },
					{ "mData": "minimumBid", "sTitle": "Last bid", "sClass": "center table-last-bid"  },
					{ "mData": "image", "sTitle":'', sClass:'table-thumbnail', bSortable:false }
                 ];

var items = {
		"clothes":{
			"men":[
				{"name": "Make T-shirt", "description": "It's a chinese Nike T-shirt", "last-bid": 2, "bid-step": 2, "buyout": 10, "end-time": "01-08-2013", "user": "Yan Veng Bo", "image": "image1.jpg", "restrictions": {"locations": []}},
				{"name": "Men 1", "description": "Description of men 1", "last-bid": 10, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Men 2", "description": "Description of men 1", "last-bid": 5, "buyout": 30, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}}
				],
			"women":[
				{"name": "Women 1", "description": "Description of women 1", "last-bid": 50, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Women 2", "description": "Description of women 2", "last-bid": 10, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Women 3", "description": "Description of women 3", "last-bid": 20, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}}
				],
			"kids":[
				{"name": "Kids 1", "description": "Description of kids 1", "last-bid": 7, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Kids 2", "description": "Description of kids 2", "last-bid": 14, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Kids 3", "description": "Description of kids 3", "last-bid": 90, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}}
				]
			
		},
		"electronics":{
			"tv":[
				{"name": "TV 1", "description": "Description of tv 1", "last-bid": 110, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "TV 2", "description": "Description of tv 2", "last-bid": 253, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "TV 3", "description": "Description of tv 3", "last-bid": 78, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}}
				],
			"pc":[
				{"name": "PC 1", "description": "Description of pc 1", "last-bid": 502, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "PC 2", "description": "Description of pc 2", "last-bid": 345, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "PC 3", "description": "Description of pc 3", "last-bid": 865, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}}
				],
			"monitor":[
				{"name": "Monitor 1", "description": "Description of monitor 1", "last-bid": 132, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 2", "description": "Description of monitor 2", "last-bid": 76, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 3", "description": "Description of monitor 3", "last-bid": 267, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 1", "description": "Description of monitor 1", "last-bid": 132, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 2", "description": "Description of monitor 2", "last-bid": 76, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 3", "description": "Description of monitor 3", "last-bid": 267, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 1", "description": "Description of monitor 1", "last-bid": 132, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 2", "description": "Description of monitor 2", "last-bid": 76, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 3", "description": "Description of monitor 3", "last-bid": 267, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 1", "description": "Description of monitor 1", "last-bid": 132, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 2", "description": "Description of monitor 2", "last-bid": 76, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 3", "description": "Description of monitor 3", "last-bid": 267, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 1", "description": "Description of monitor 1", "last-bid": 132, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 2", "description": "Description of monitor 2", "last-bid": 76, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 3", "description": "Description of monitor 3", "last-bid": 267, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 1", "description": "Description of monitor 1", "last-bid": 132, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 2", "description": "Description of monitor 2", "last-bid": 76, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 3", "description": "Description of monitor 3", "last-bid": 267, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 1", "description": "Description of monitor 1", "last-bid": 132, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 2", "description": "Description of monitor 2", "last-bid": 76, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 3", "description": "Description of monitor 3", "last-bid": 267, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 1", "description": "Description of monitor 1", "last-bid": 132, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 2", "description": "Description of monitor 2", "last-bid": 76, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 3", "description": "Description of monitor 3", "last-bid": 267, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 1", "description": "Description of monitor 1", "last-bid": 132, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 2", "description": "Description of monitor 2", "last-bid": 76, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 3", "description": "Description of monitor 3", "last-bid": 267, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 1", "description": "Description of monitor 1", "last-bid": 132, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 2", "description": "Description of monitor 2", "last-bid": 76, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}},
				{"name": "Monitor 3", "description": "Description of monitor 3", "last-bid": 267, "buyout": 0, "end-time": "dd-MM-yyyy", "user": "",	"image": "", "restrictions": {"locations": []}}
				]	
		}
};
/*
$.get("http://localhost:9080/EnCoders/getAuctionList", function(data) {
	items = data;
	replaceData("Clothes");
	});
*/
function getItems(category, subcategory) {
	var categoryItems = items[category];
	
	if (subcategory != undefined) {
		if (categoryItems.hasOwnProperty(subcategory)) {
			return categoryItems[subcategory];
		}
		return null;
	}
	var allItemsFromCategory = [];
	for (var key in categoryItems) {
	  if (categoryItems.hasOwnProperty(key)) {
	    for(var i=0; i < categoryItems[key].length; i++) {
	    	allItemsFromCategory.push(categoryItems[key][i]);
	    }	    
	  }
	}
	return allItemsFromCategory;
}

function getAllItems() {	
	var allItems = [];
	for (var category in items) {
		if (items.hasOwnProperty(category)) {
			var categoryItems = items[category];
			for (var key in categoryItems) {
			  if (categoryItems.hasOwnProperty(key)) {
			    for(var i=0; i < categoryItems[key].length; i++) {
			    	allItems.push(categoryItems[key][i]);
			    }	    
			  }
			}
		}
	}
	return allItems;
}

function addImgSrc(items) {
	var duplicateArray = items.slice();
	for(var i=0; i < duplicateArray.length; i++) {
		duplicateArray[i].image = "<img src='" + duplicateArray[i].product.images[0] + "' class='item-image' />";
	}
	return items;
}

function replaceData(category, subcategory) {
	oTable.fnClearTable();
	oTable.fnAddData(addImgSrc(getItems(category, subcategory)));
}

function post_to_url(path, params, method) {
    method = method || "post"; // Set method to post by default if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
         }
    }

    document.body.appendChild(form);
    form.submit();
}

$(function() {
	
	$.get(rootUrl + "getAuctionList", function(data) {
		items = data;
		$('#dynamicTable').html( '<table cellpadding="0" cellspacing="0" border="0" class="pretty" id="itemTable"></table>' );
		oTable = $('#itemTable').dataTable( {
			"sPaginationType": "full_numbers",
			"bJQueryUI": true,
			"aaData": addImgSrc(getAllItems()), //change to whatever is on first
			"aoColumns": tableHeaders
			
		});
		
		$('#itemTable tbody').on('click', 'td', function () {
	        var nTr = $(this).parents('tr')[0];
	        
	        var aData = oTable.fnGetData( nTr );
	        $.get(rootUrl + "getAuction?id=" + aData.id, function(auction) {
	        	//TODO show details
	        	post_to_url(rootUrl + "details", {"item": JSON.stringify(auction)}, "POST");
	        });
	    });
	});
	
	$("#women").click(function(e) {
		e.preventDefault();
		replaceData("Clothes");
	});
});