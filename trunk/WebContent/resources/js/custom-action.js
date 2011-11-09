$(document).ready(function() {
	path = location.pathname.split("/").pop();
	if (path) {
		$('#main-menu a[href$="' + path + '"]').parent().addClass("active");
	} else {
		$('#main-menu a').each(function(index){
			$(this).parent().addClass("active");
			brake;
		});
	}
	// -------------------------------------
	$(".amount-data tr").click(function(){
		window.location = $(this).children("td.hidden a:first").get(0).href; 
	});
	// -------------------------------------
	$(".choice-period-link").click(function(){
		$(".all-periods").css("display", "block");
	});
});

