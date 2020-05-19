<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta name="format-detection" content="telephone=no"/>
	<meta name="format-detection" content="email=no"/>
	<link rel="stylesheet" href="${ctxStatic}/portal/css/reset.css">
	<style>
		*,*:after,*:before{-webkit-box-sizing:border-box;}
		.contetnBox{padding:10px 20px;width:100%;height: 100%;overflow-y:scroll;}
		.imgBox{width:100%;height:300px;}
		.imgItem{display: inline-block;width:100%;height:300px;}
		.textBox{font-size: 12px;margin-top: 10px;color:#666;}
		.bottomBox{clear:both;overflow: hidden;width:100%;height:30px;line-height: 30px;}
		.bottomTime{float:left;font-size: 11px;color:#ccc;}
		.bottomLook,.bottomSave{float: right;font-size: 11px;color: #ccc;margin-right: 10px;}
		.textBox img{display:inline-block; width:100%;height:auto;padding:10px 0;margin:10px 0;}
		.textBox *{padding:10px 0;margin:10px 0;}
	</style>
	<title>${hcyOnlineTeache.title}</title>
</head>
<body>
	
	<div class="contetnBox">
		<%-- <div class="imgBox">
			<img class="imgItem" src="${ctxImg}${hcyOnlineTeache.imgUrl}" alt="">
		</div> --%>
		<div style="width:100%;">
			<p style="line-height:23px;font-size:12px;width:100%;padding:10px;">
				${hcyOnlineTeache.abstracts}
			</p>
		</div>
		<div class="textBox" style="line-height:30px;">
			${hcyOnlineTeache.content}
		</div>
		<div class="bottomBox">
			<span class="bottomTime"><fmt:formatDate value="${hcyOnlineTeache.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			<span class="bottomLook">浏览量：${hcyOnlineTeache.borwerCount}</span>
			<span class="bottomSave">收藏量：${hcyOnlineTeache.storeCount}</span>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script>
		function remSet(){
			var width=750/2;
			var rem=$(window).width()/width * 20;
			$("html").css({
				"font-size":rem
			})
		}
		remSet();
		
		function videoHtml(){
			var html = '<video width="320" height="240" webkit-playsinline="true" src="" style="background:#000;"  controls></video>';
			$("embed").each(function(){
				var src = $(this).attr("src");
				$(this).after('<video width="320" height="240" style="background:#000;" webkit-playsinline="true" src="'+src+'" controls="controls" loop="loop"></video>');
				$(this).remove();
			})
		}
		videoHtml();
		
		function imgShow(){
			
			 $('img').each(function() {
			     	var maxWidth = $(window).width(); // 图片最大宽度
			     	var maxHeight = $(window).height();   // 图片最大高度
			     	var ratio = 0;  // 缩放比例
			     	var width = $(this).width();    // 图片实际宽度
			     	var height = $(this).height();  // 图片实际高度
			   
			     // 检查图片是否超宽
			     if(width > maxWidth){
			         ratio = maxWidth / width;   // 计算缩放比例
			         $(this).css("width", maxWidth); // 设定实际显示宽度
			         height = height * ratio;    // 计算等比例缩放后的高度 
			         $(this).css("height", height);  // 设定等比例缩放后的高度
			     }
			   
			     // 检查图片是否超高
			     if(height > maxHeight){
			         ratio = maxHeight / height; // 计算缩放比例
			         $(this).css("height", maxHeight);   // 设定实际显示高度
			         width = width * ratio;    // 计算等比例缩放后的高度
			         $(this).css("width", width * ratio);    // 设定等比例缩放后的高度
			     }
			 });
		}
		imgShow();
		
	</script>
</body>
</html>