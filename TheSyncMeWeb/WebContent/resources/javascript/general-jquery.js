// account bar, perspective bar and activities-title-bar click function
$(document).ready(function(){
	   $("#account-bar li em").click(function() {
			var hidden = $(this).parents("li").children("ul").is(":hidden");
			$("#account-bar>ul>li>ul").hide()
			$("#account-bar>ul>li>a").removeClass();
			if (hidden) {
				$(this)
		   			.parents("li").children("ul").toggle()
		   			.parents("li").children("a").addClass("zoneCur");
		   	}
	   });
		$("#perspective-bar li em").click(function() {
	   		var hidden = $(this).parents("li").children("ul").is(":hidden");
			$("#perspective-bar>ul>li>ul").hide()
		   	$("#perspective-bar>ul>li>a").removeClass();
		   	if (hidden) {
		   		$(this)
			   		.parents("li").children("ul").toggle()
			   		.parents("li").children("a").addClass("zoneCur");
			   	}
		});
	   $("#activities-title-bar li em").click(function() {
	   		var hidden = $(this).parents("li").children("ul").is(":hidden");
			$("#activities-title-bar>ul>li>ul").hide()
		   	$("#activities-title-bar>ul>li>a").removeClass();
		   	if (hidden) {
		   		$(this)
			   		.parents("li").children("ul").toggle()
			   		.parents("li").children("a").addClass("zoneCur");
			   	}
		});
});
