$(document).ready(function () {
	$("#main-menu").children("li").each(function(index) {
		if ($(this).children("a").length > 0) {
			$(this).children("a").each(function(index) {
				$(this).click(function() {
					$("#main-menu").children("li").removeClass("active");
					$(this).parent().addClass("active");
				});
			});
		}
	});
});
