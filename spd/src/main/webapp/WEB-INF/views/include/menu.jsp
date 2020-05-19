<%@ page contentType="text/html;charset=UTF-8" %>
<div class="hcy-top">
<div class="hcy-top-content">
	<div class="fl">
	<c:if test="${not empty fns:getUser().id}">
	<div class="userShow">
		<label class="showListBtn" style="color: red">${fns:getUser().mobile},你好∨</label>
		<div class="showListBox">
		    <a href="${ctxp}/mail/modifypwdPage">账号安全</a><br>
		    <c:if test="${fns:getUser().loginType eq 'p'}">
			    <c:if test="${fns:getUser().status ne'1'}">
			    <a href="${ctxp}/mail/hcyClinic/modifyPage">我的诊所</a><br>
			    </c:if>
			    <c:if test="${fns:getUser().status eq '1'}">
			    <a href="${ctxp}/register/perfeClinicPage">我的诊所</a><br>
			    </c:if>
			</c:if> 
			<c:if test="${fns:getUser().loginType eq 'm'}">
			   <a href="${ctxp}/mail/hcyClinic/modifyAgentPage">我的代理</a><br>
			</c:if>
			<a href="${ctxp}/hcy/storeup/list">我的收藏</a><br>
	        <c:if test="${fns:getUser().loginType eq 'p'}">		
			<a href="${ctxp}/hcy/hcyLease/list">我的租赁</a><br>
		    <a href="${ctxp}/hcy/hcyEquipment/list">我的设备</a><br>
		    </c:if>
	    </div>
	</div>
	<a href="javascript:void(0);" id="loginOut">退出</a>
	</c:if>
	&nbsp;
	<c:if test="${  empty fns:getUser().id}">
    <a href="javascript:void(0);"  class="login" id="login">登录</a>
	<a href="javascript:void(0);" class="register" id="register">免费注册</a>
	</c:if>
	</div>
	<div class="hcy-top-info fr">
		<a href="${ctxp}/hcy/mail/hcyCart/list">我的购物车(<span id="myCartCount">0</span>)</a>
		<span>|</span>
		<div class="myOrderIcon">
			<a style="display: block;height: 34px;" href="${ctxp}/hcy/mail/mallMyorderInfo/myOrder" class='myOrder'>我的订单∨</a>
			<a href="${ctxp}/hcy/mail/mallAfterSale/afterSaleList" style="display:none;position:absolute;height: 34px;left:-1px;border:1px solid #ccc;border-top:0;width:78px;text-align:center;background:#fff;" class="myAfter">我的售后</a>
		</div>
		<span>|</span>
		<a href="${ctxp}/hcy/message/messageCenter">消息中心<span id="msgCount">(0)</span></a>
		<span>|</span>
		<a href="${ctxp}/hcy/mail/hcyFeedback/list">帮助中心</a>
		<span>|</span>
		<a href="javascript:void(0);">客服：400-0567-4280</a>
	</div>
</div>
</div>
<div class="hcy-wrapper">
	<div class="hcy-search cl">
		<div class="fl" style="padding-top:8px;">
			<a href="javascript:void(0);" id="goindex"><img src="${ctxStatic}/mall/image/logo.jpg" alt=""></a>
		</div>
		<div class="fr" style="padding-top:30px;">
			<form id="searchForm" style="padding:0;background:#fff;" action="${ctxp}/hcy/mail/mailGoods/goShopping.html" method="post" class="breadcrumb form-search">
				<input class="searchInput" maxlength="50" name="keywords" value="${keywords}" placeholder="设备名称/设备规格/试剂名称/试剂规格" id="selectValue" />
				<input type="button" class="searchButton" value="搜索" onclick="selectGoods();">
			</form>
		</div>
	</div>
	<div class="hcy-nav cl">
		<div class="productListWrap fl" style="padding-right:80px;">
			<a href="javascript:void(0);" class="productType">产品分类</a>
			<div class="listBox">
				<c:set value="${fns:getHcyProductClassForPC()}" var="ClassList"></c:set>
				<div class="showList cl">
					<div class="showProNav fl">
						<ul>
							<c:forEach items="${ClassList}" var="hcyProductClass">
								<li><a href="${ctxp}/hcy/mail/mailGoods/goShopping.html?productClassificationA=${hcyProductClass.code}">${hcyProductClass.name }</a></li>
							</c:forEach>
						</ul>
					</div>
					<div class="showProItem">
						<c:forEach items="${ClassList}" var="hcyProductClass">
							<div>
								<c:forEach items="${hcyProductClass.childeClassList}" var="hcyProductClass2">
										<a href="${ctxp}/hcy/mail/mailGoods/goShopping.html?productClassificationB=${hcyProductClass2.code}&productClassificationA=${hcyProductClass.code}">${hcyProductClass2.name }</a>
								</c:forEach>
							</div>
						</c:forEach>
					
					</div>
				</div>
			</div>
		</div>
		<div class="fl">
			<ul class="hcy-nav-list cl">
				<li><a id="title_index" href="${ctxp}/index">首页</a></li>
				<li><a id="title_market" href="${ctxp}/hcy/hcyMarketActivity/marketList">抢优惠</a></li>
				<li><a id="title_goShopping" href="${ctxp}/hcy/mail/mailGoods/goShopping.html">去采购</a></li>
				<li><a id="title_shang" href="${ctxp}/hcy/hcyOnlineTeache/pcOnlineTeach.html?type=shang&pageSize=6">商学院</a></li>
				<li><a id="title_hui" href="${ctxp}/hcy/hcyOnlineTeache/pcOnlineTeach.html?type=hui&pageSize=6">汇学院</a></li>
			</ul>
		</div>
		<div class="fr" style="padding:10px 0 0 0">
			<span class="quickBuy"><a class="quickBuy" href="${ctxp}/hcy/mail/mailGoods/goShopping.html">快速采购></a></span>
		</div>
	</div>
	<div class="loadAppBox" style="position:fixed;top:215px;right:40px;width:245px;height:114px;display:none;z-index:9999999999">
		<img  src="${ctxStatic}/mall/image/productApp.jpg" alt="" style="width:245px;height:114px;">
	</div>
</div>
<form action="" method="get" style="padding:0;margin:0;" id="goPage"> </form>

<script type="text/javascript">
<!-- 登录   start -->
$(document).ready(function() {
	$("#login").bind("click", function() {
		window.location.href = "${ctxp}/login";
	});
	$("#loginOut").bind("click", function() {
		window.location.href = "${ctxp}/logout";
	});
	$("#register").bind("click", function() {
		window.location.href = "${ctxp}/register/index";
	});
	var keywords = '${keywords}';
	$("#selectValue").val(keywords);
	//划过我的订单显示下拉
	$(".myOrderIcon").mouseenter(function(){
		$(this).addClass("onmouse");
		$(".myAfter").show();
	})
	 $(".myOrderIcon").mouseleave(function(){
		 $(this).removeClass("onmouse");
	 	$(".myAfter").hide();
	 })
	 //划过用户显示下拉
	 $(".userShow").mouseenter(function(){
		$(this).addClass("onUser");
		$(".showListBox").show();
	});
	 $(".userShow").mouseleave(function(){
		$(this).removeClass("onUser");
		$(".showListBox").hide();
	})
	 //单机产品分类显示产品
	$(".productType").mouseover(function(){
		$(".listBox").css("left",$(".productType").offset().left);
		$(".listBox").css("top",$(".productType").offset().top+$(".productType").outerHeight()+1);
		$(".listBox").show();
		$(".showProItem>div").hide();
		$(".showProNav ul li").removeClass("on");
		$(".showProNav").show();
	});
	 $(".productListWrap").mouseleave(function(){
		 $(".listBox").hide();
	 })
	 //下载APP
	 $("#loadAppBtn").mouseover(function(){
		 $(".loadAppBox").show();
	 });
	 $("#loadAppBtn").mouseleave(function(){
		 $(".loadAppBox").hide();
	 });
	/*  $(".loadAppBox").mouseleave(function(){
		 $(".loadAppBox").hide();
	 }) */
	//划过出现产品列表
	var isShowClass = "${isShowClass}";
	console.log(isShowClass);
	if(isShowClass == '1'){
		$(".showProNav ul li").on("mouseover",function(){
			$(this).addClass("on").siblings().removeClass("on");
			$(".showProItem").show();
			$(".showProItem").addClass("showStyle")
			$(".showProItem>div").eq($(this).index()).show().siblings().hide();
		});
		$(".showList").mouseleave(function(){
			$(".showProNav ul li").removeClass("on");
			$(".showProItem").hide();
			$(".showProItem").removeClass("showStyle")
		})
		$(".showProItem>div").mouseleave(function(){
			$(".showProNav ul li").removeClass("on");
			$(this).hide();
		})
	}else{
		$(".showProNav ul li").on("mouseover",function(){
			$(this).addClass("on").siblings().removeClass("on");
			$(".showProItem").show();
			$(".showProItem").addClass("showStyle")
			$(".showProItem>div").eq($(this).index()).show().siblings().hide();
		});
		$(".showList").mouseleave(function(){
			$(".showProNav ul li").removeClass("on");
			$(".showProItem").hide();
			$(".showProItem").removeClass("showStyle")
			$(".showProNav").hide();
			$(".listBox").hide();
		})
		$(".showProItem>div").mouseleave(function(){
			$(".showProNav ul li").removeClass("on");
			$(this).hide();
		})
	}
	
});
</script>
<!-- 登录   end -->
<!-- 自定义客服按钮   start  
<script src='//kefu.easemob.com/webim/easemob.js'></script>
<script type="text/javascript">
window.easemobim.bind({
   configId: '79fb9905-74f9-401d-ae08-dd57dac1abe3',
   visitor: {
     trueName: '黎小冷',
     qq: '12345678',
     phone: '18812345678',
     companyName: '环信',
     userNickname: '小冷',
     description: '意向客户',
     email: 'xiaoleng@easemob.com'
   }
});
</script>
-->
 <!--自定义客服按钮   end -->
<script type="text/javascript">
/*window.easemobim = window.easemobim || {};
easemobim.config = {
	configId: '79fb9905-74f9-401d-ae08-dd57dac1abe3',
    dialogWidth: '360px',
    dialogHeight: '550px',
    dialogPosition: { x: '30px', y: '230px' }
};*/

var userId = "${fns:getUser().id}";
function getMsgCount(){ //获取未读消息数量
	if(userId!=''){
		$.ajax({
			   type:"post", //请求方式
			   url:"${ctxp}/hcy/message/msgTotalCount", //发送请求地址
			   //请求成功后的回调函数有两个参数
			   success:function(data,textStatus){
				 $("#msgCount").text("("+data.count+")");
			   }
			   });	
	}else{
		$("#msgCount").text("(0)");
	}
	
}

getMsgCount();	

//搜索
function selectGoods(){
	$("#pageNo").val(1);
	$("#searchForm").submit();
	return false;
}
 //返回首页
$("#goindex").bind("click",function(){
  $("#goPage").attr("action","${ctxp}/index.html"); 
  $("#goPage").submit();
});

//加载购物车数量
$.post("${ctxp}/hcy/mail/hcyCart/getCount",{},function(data){
	data=JSON.parse(data);
	if(data!=null && data.code=="200"){
	  $("#myCartCount").html(data.data || "0");
	  $("#sysCartCount").html(data.data || "0");
	}
});

//异步请求，传入增加商品的数量。修改页面 购物车总数量
function addPageCartCount(num){
   if(num>0){
	  var  cartTotalCount=$("#myCartCount").html() || "0";
	  cartTotalCount=parseInt(cartTotalCount)+parseInt(num);
	  $("#myCartCount").html(cartTotalCount || "0");
	  $("#sysCartCount").html(cartTotalCount || "0");
   }
}

//异步请求，传入减少商品的数量。修改页面 购物车总数量
function minPageCartCount(num){
   var  cartTotalCount=$("#myCartCount").html() || "0";
   cartTotalCount=parseInt(cartTotalCount);
   cartTotalCount=cartTotalCount-parseInt(num);
   if(cartTotalCount<=0){
	 cartTotalCount=0;
   }
   $("#myCartCount").html(cartTotalCount || "0");
   $("#sysCartCount").html(cartTotalCount || "0");
}
</script>
