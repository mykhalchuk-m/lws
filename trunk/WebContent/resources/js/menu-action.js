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
