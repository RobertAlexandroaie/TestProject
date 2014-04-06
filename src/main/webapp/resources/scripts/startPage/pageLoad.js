$(document).ready(function() {
  $('body').addClass('js');

  var $menu = $('#menu'),
	$menulink = $('.menu-link'),
	$wrap = $('#wrap');

  $menulink.click(function() {
	$menulink.toggleClass('active');
	$wrap.toggleClass('active');
	return false;
	});
});

if (document.location.search.match(/type=embed/gi)) {
    window.parent.postMessage('resize', "*");
  }