$(function(){
	var tit=$("#header-title"),
		sub=$("#sub");
		//$("#nav-list h3").text().slice(0,7);
	$("#nav-list h3").on("click",function(){
		var navTxt=$(this).text().slice(0,7);
			tit.text(navTxt);
			sub.val("保存");
	})
	$("#container").hide();
	$(".back_sub").hide();
	var indLi=$("#swipe li");
	$("#nav-list h3").each(function(){
		//console.log($(this).attr("data-tip"));
		$(this).on("click",function(){
			var ind=$(this).attr("data-tip");
			indLi.eq(ind).css({
				"-webkit-transform":"translateX(-"+(ind*100)+"%)",
				"-webkit-transition":"all 0.4s ease-in-out"
			})
			$("#nav-list").css("display","none");
			$("#container").show();
			$(".back_sub").show();
		})
	})
	$(".back_sub").on("click",function(){
		$("#container").scrollTop(0);
			indLi.css({
			"-webkit-transform":"translateX(100%)",
			"-webkit-transition":"all 0.4s ease-in-out"
			})
			$(".back_sub").hide();
			setTimeout(function(){
				$("#nav-list").css("display","block");
			},500)
			sub.val("提交");
			tit.text("健康体检表");
	})
})