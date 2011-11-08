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
});

$(document).ready(function() {
	$(".amount-data tr").click(function(){
		window.location = $(this).children("td.hidden a:first").get(0).href; 
	});
});