<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<style>
		.highProList .hightTop,.lowProList .lowTop,.lowProList1 .lowTop1,.lowProList2 .lowTop2,.lowProList3 .lowTop3,.lowProList4 .lowTop4,.lowProList5 .lowTop5,.lowProList6 .lowTop6{height: 32px;line-height: 32px; padding-left: 32px;margin:8px 0;}
		.highProList .hightTop>h3,.lowProList .lowTop>h3,.lowProList1 .lowTop1>h3,.lowProList2 .lowTop2>h3,.lowProList3 .lowTop3>h3,.lowProList4 .lowTop4>h3,.lowProList5 .lowTop5>h3,.lowProList6 .lowTop6>h3{font-size:13px;color:#666;font-weight:400;float:left;cursor:pointer;}
		.highProList .hightTop>#addNewHigh,.lowProList .lowTop>#addNewLow,.lowProList1 .lowTop1>#addNewLow1,.lowProList2 .lowTop2>#addNewLow2,.lowProList3 .lowTop3>#addNewLow3,.lowProList4 .lowTop4>#addNewLow4,.lowProList5 .lowTop5>#addNewLow5,.lowProList6 .lowTop6>#addNewLow6{float:right;}
		.highProList .highBottom,.lowProList .lowBottom,.lowProList1 .lowBottom1,.lowProList2 .lowBottom2,.lowProList3 .lowBottom3,.lowProList4 .lowBottom4,.lowProList5 .lowBottom5,.lowProList6 .lowBottom6{padding:0 15px;}
		.highProList .highBottom>ul,.lowProList .lowBottom>ul,.lowProList1 .lowBottom1>ul,.lowProList2 .lowBottom2>ul,.lowProList3 .lowBottom3>ul,.lowProList4 .lowBottom4>ul,.lowProList5 .lowBottom5>ul,.lowProList6 .lowBottom6>ul{margin:0;}
		.highProList .highBottom>ul>li,.lowProList .lowBottom>ul>li,.lowProList1 .lowBottom1>ul>li,.lowProList2 .lowBottom2>ul>li,.lowProList3 .lowBottom3>ul>li,.lowProList4 .lowBottom4>ul>li,.lowProList5 .lowBottom5>ul>li,.lowProList6 .lowBottom6>ul>li{height:40px;line-height:40px;background:#F2FAFF;}
		.highProList .highBottom>ul>li:nth-child(2n),.lowProList .lowBottom>ul>li:nth-child(2n),.lowProList1 .lowBottom1>ul>li:nth-child(2n),.lowProList2 .lowBottom2>ul>li:nth-child(2n),.lowProList3 .lowBottom3>ul>li:nth-child(2n),.lowProList4 .lowBottom4>ul>li:nth-child(2n),.lowProList5 .lowBottom5>ul>li:nth-child(2n),.lowProList6 .lowBottom6>ul>li:nth-child(2n){background:#fff}
		.highProList .highBottom>ul>li:hover,.lowProList .lowBottom>ul>li:hover,.lowProList1 .lowBottom1>ul>li:hover,.lowProList2 .lowBottom2>ul>li:hover,.lowProList3 .lowBottom3>ul>li:hover,.lowProList4 .lowBottom4>ul>li:hover,.lowProList5 .lowBottom5>ul>li:hover,.lowProList6 .lowBottom6>ul>li:hover{background:#D3EDFF;}
		.highProList .highBottom>ul>li>span,.lowProList .lowBottom>ul>li>span,.lowProList1 .lowBottom1>ul>li>span,.lowProList2 .lowBottom2>ul>li>span,.lowProList3 .lowBottom3>ul>li>span,.lowProList4 .lowBottom4>ul>li>span,.lowProList5 .lowBottom5>ul>li>span,.lowProList6 .lowBottom6>ul>li>span{padding-left:33px;float:left;}
		.highProList .highBottom>ul>li>a,.lowProList .lowBottom>ul>li>a,.lowProList1 .lowBottom1>ul>li>a,.lowProList2 .lowBottom2>ul>li>a,.lowProList3 .lowBottom3>ul>li>a,.lowProList4 .lowBottom4>ul>li>a,.lowProList5 .lowBottom5>ul>li>a,.lowProList6 .lowBottom6>ul>li>a{float:right;margin: 6px 20px 0 0;}
		.fa-sort-down{vertical-align: text-top; padding-left: 5px;}
		.fa-sort-up{vertical-align: text-bottom; padding-left: 5px;}
	</style>
	<title>产品分类管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		resetTip();	
	</script>
</head>
<body>
	
	<!--<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdCategory" action="${ctx}/pd/pdGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form> -->
	<input type="hidden" name="delKey" value="${delKey }" />
	<div class="right-main-box">
		<div class="btnBox" style="border-bottom:1px solid #ccc;padding-bottom:5px;">
			<h4 style="height:36px;">产品分类管理</h4>
			<!-- <a href="###" class="hcy-btn hcy-btn-primary"  id="addNewClassify">新增分类</a>
			<a href="###" class="hcy-btn hcy-btn-primary" id="removeAll">批量删除</a> -->
			<c:if test="${ifPlatform == '0' ||isUrgent == '0'}">
				<a href="###" class="hcy-btn hcy-btn-primary" id="synPdCategory" style="padding: 5px 13px;">同步产品分类至中心平台</a>
			</c:if>
		</div>
		<div class="tableBox">
			
		</div>
		
	</div>
	<!-- ...添加高值耗材 -->
	<div class="addHighBox" style="display: none;">
		<form:form id="inputForm" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/pd/pdCategory/save?type=1" method="post" class="form-horizontal">
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addClassifyInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>
	<sys:message content="${message}"/>
	<!-- ...修改高值耗材 -->
	<div class="updateClassifyBox" style="display: none;">
		<form:form id="inputForm2" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/pd/pdCategory/update" method="post" class="form-horizontal">
			<input type="hidden" id="categoryId" name="id" />
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addClassifyInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>
	
	<!-- ...添加低值耗材 -->
	<div class="addLowBox" style="display: none;">
		<form:form id="inputForm3" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/pd/pdCategory/save?type=0" method="post" class="form-horizontal">
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addLowInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>
	
	<!-- ...修改低值耗材 -->
	<div class="updateLowBox" style="display: none;">
		<form:form id="inputForm4" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/pd/pdCategory/update" method="post" class="form-horizontal">
			<input type="hidden" id="categoryId" name="id" />
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addLowInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>
	<%--//---------------------	--%>
	<!-- ...添加医用气体耗材 -->
	<div class="addLowBox1" style="display: none;">
		<form:form id="inputForm3" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/pd/pdCategory/save?type=2" method="post" class="form-horizontal">
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addLowInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>
	<!-- ...添加影像资料耗材 -->
	<div class="addLowBox2" style="display: none;">
		<form:form id="inputForm3" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/pd/pdCategory/save?type=3" method="post" class="form-horizontal">
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addLowInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>
	<!-- ...添加检验材料耗材 -->
	<div class="addLowBox3" style="display: none;">
		<form:form id="inputForm3" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/pd/pdCategory/save?type=4" method="post" class="form-horizontal">
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addLowInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>

	<!-- ...添加设备耗材 -->
	<div class="addLowBox4" style="display: none;">
		<form:form id="inputForm3" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/pd/pdCategory/save?type=5" method="post" class="form-horizontal">
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addLowInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>
	<!-- ...添加医疗器械耗材 -->
	<div class="addLowBox5" style="display: none;">
		<form:form id="inputForm3" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/pd/pdCategory/save?type=6" method="post" class="form-horizontal">
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addLowInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>
	<!-- ...添加备品备件耗材 -->
	<div class="addLowBox6" style="display: none;">
		<form:form id="inputForm3" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/pd/pdCategory/save?type=7" method="post" class="form-horizontal">
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addLowInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>

	<%--//---------------------	--%>
	<div class="listBox">
		<div class="highProList">
			<div class="hightTop">
				<h3>高值耗材<i class="fa fa-sort-down"></i></h3>
				<a href="###" id="addNewHigh" class="hcy-btn hcy-btn-primary">新增分类</a>
			</div>
			<div class="highBottom">
				<ul>
					<c:forEach items="${list }" var="list">
						<c:if test="${list.type eq '1'}">
							<li>
								<input type="hidden" name="id" value="${list.id }" />
								<span>${list.name }</span>
								<%-- <a href="${ctx}/pd/pdCategory/delete" data-id="${list.id}"   class="hcy-btn hcy-btn-o delHighBtn">删除</a> --%>
								<a href="###" data-id="${list.id}"   class="hcy-btn hcy-btn-o delHighBtn">删除</a>
								<a href="###" class="hcy-btn hcy-btn-o modifyHighBtn">修改</a>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="lowProList">
			<div class="lowTop">
				<h3>低值耗材<i class="fa fa-sort-down"></i></h3>
				<a href="###" id="addNewLow" class="hcy-btn hcy-btn-primary">新增分类</a>
			</div>
			<div class="lowBottom">
				<ul>
					<c:forEach items="${list }" var="list">
						<c:if test="${list.type eq '0'}">
							<li>
								<input type="hidden" name="id" value="${list.id }" />
								<span>${list.name }</span>
								<a href="###" data-id="${list.id }"   class="hcy-btn hcy-btn-o delLowBtn">删除</a>
								<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn">修改</a>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>


<%--//--------------%>

	<div class="lowProList1">
		<div class="lowTop1">
			<h3>医用气体<i class="fa fa-sort-down"></i></h3>
			<a href="###" id="addNewLow1" class="hcy-btn hcy-btn-primary">新增分类</a>
		</div>
		<div class="lowBottom1">
			<ul>
				<c:forEach items="${list }" var="list">
					<c:if test="${list.type eq '2'}">
						<li>
							<input type="hidden" name="id" value="${list.id }" />
							<span>${list.name }</span>
							<a href="###" data-id="${list.id }"   class="hcy-btn hcy-btn-o delLowBtn1">删除</a>
							<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn1">修改</a>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>

	<div class="lowProList2">
		<div class="lowTop2">
			<h3>影像材料<i class="fa fa-sort-down"></i></h3>
			<a href="###" id="addNewLow2" class="hcy-btn hcy-btn-primary">新增分类</a>
		</div>
		<div class="lowBottom2">
			<ul>
				<c:forEach items="${list }" var="list">
					<c:if test="${list.type eq '3'}">
						<li>
							<input type="hidden" name="id" value="${list.id }" />
							<span>${list.name }</span>
							<a href="###" data-id="${list.id }"   class="hcy-btn hcy-btn-o delLowBtn2">删除</a>
							<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn2">修改</a>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>

	<div class="lowProList3">
		<div class="lowTop3">
			<h3>检验材料<i class="fa fa-sort-down"></i></h3>
			<a href="###" id="addNewLow3" class="hcy-btn hcy-btn-primary">新增分类</a>
		</div>
		<div class="lowBottom3">
			<ul>
				<c:forEach items="${list }" var="list">
					<c:if test="${list.type eq '4'}">
						<li>
							<input type="hidden" name="id" value="${list.id }" />
							<span>${list.name }</span>
							<a href="###" data-id="${list.id }"   class="hcy-btn hcy-btn-o delLowBtn3">删除</a>
							<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn3">修改</a>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>

	<div class="lowProList4">
		<div class="lowTop4">
			<h3>设备<i class="fa fa-sort-down"></i></h3>
			<a href="###" id="addNewLow4" class="hcy-btn hcy-btn-primary">新增分类</a>
		</div>
		<div class="lowBottom4">
			<ul>
				<c:forEach items="${list }" var="list">
					<c:if test="${list.type eq '5'}">
						<li>
							<input type="hidden" name="id" value="${list.id }" />
							<span>${list.name }</span>
							<a href="###" data-id="${list.id }"   class="hcy-btn hcy-btn-o delLowBtn4">删除</a>
							<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn4">修改</a>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="lowProList5">
		<div class="lowTop5">
			<h3>医疗器械<i class="fa fa-sort-down"></i></h3>
			<a href="###" id="addNewLow5" class="hcy-btn hcy-btn-primary">新增分类</a>
		</div>
		<div class="lowBottom5">
			<ul>
				<c:forEach items="${list }" var="list">
					<c:if test="${list.type eq '6'}">
						<li>
							<input type="hidden" name="id" value="${list.id }" />
							<span>${list.name }</span>
							<a href="###" data-id="${list.id }"   class="hcy-btn hcy-btn-o delLowBtn5">删除</a>
							<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn5">修改</a>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="lowProList6">
		<div class="lowTop6">
			<h3>备品备件<i class="fa fa-sort-down"></i></h3>
			<a href="###" id="addNewLow6" class="hcy-btn hcy-btn-primary">新增分类</a>
		</div>
		<div class="lowBottom6">
			<ul>
				<c:forEach items="${list }" var="list">
					<c:if test="${list.type eq '7'}">
						<li>
							<input type="hidden" name="id" value="${list.id }" />
							<span>${list.name }</span>
							<a href="###" data-id="${list.id }"   class="hcy-btn hcy-btn-o delLowBtn6">删除</a>
							<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn6">修改</a>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
	<%--//---------------------	--%>
	</div>

	<!-- <div class="pagination">${page}</div> -->
	
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/js/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){

			$("#synPdCategory").on("click",function(){
				$.ajax({
					url:"${ctx}/pd/pdCategory/synPdCategory",
					type:"post",
					data:{},
					dataType:"json",
					success:function(data){
						if(data.code == "-200"){
							layer.alert("同步失败");
						}else{
							layer.alert("同步成功");
						}
					}
				})
			})

			//重置
			$(".hcy-reset").click(function(){
				$(".searchBox div input[type='text']").val("");
			})
			
			//删除标记判断，error删除失败
			var delKey = $("[name = delKey]").val();
			if(delKey == 'error'){
				layer.alert("该分类有关联产品，不可删除！",{icon:0},function(index){
					layer.close(index);
				});
			}
			
			//高值耗材切换隐藏
			$(".highProList>.hightTop>h3").click(function(){
				$(".highBottom>ul").toggle();
				if($(".highBottom>ul").is(":hidden")){
					$(this).find(".fa").attr("class","fa fa-sort-up")
				}else{
					$(this).find(".fa").attr("class","fa fa-sort-down")
				}
			})
			//高值耗材    新增分类
			$("#addNewHigh").click(function(){
				$(".addClassifyInp").val("");
				layer.open({
					type:1,
					title:"新增产品分类",
					content:$(".addHighBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var addInpText=layero.find(".addClassifyInp").val();
						if(addInpText!="" && addInpText!=undefined){
							var newLi='<li>'+
										'<span>'+addInpText+'</span>'+
										'<a href="###" class="hcy-btn hcy-btn-o delHighBtn">删除</a>'+
										'<a href="###" class="hcy-btn hcy-btn-o modifyHighBtn">修改</a>'+
									'</li>'
							$(".highProList>.highBottom>ul").append(newLi);
							$(".mustIcon").hide();
							
							
							var a = layero.find("[name = name]").val();
							layero.find(".addClassifyInp").val("")
							window.location = "${ctx}/pd/pdCategory/save?name="+a+"&type=1";
							//$("#inputForm").submit();
							layer.closeAll();
						}else{
							$(".mustIcon").show();
							return false;
						}
					},
					btn2:function(){
						$(".mustIcon").hide();
						layer.closeAll();
					}
				})
			});
			//高值耗材  删除
		$(document).on("click",".delHighBtn",function(){
				var that=$(this);
				var href="${ctx}/pd/pdCategory/delete";
				var id=$(this).attr("data-id");
				//${ctx}/pd/pdGroup/delete?id=${pdGroup.id}
				layer.confirm(
					"确定要删除该分类吗？",//这里写询问层里面的文本
					{
						icon:3,
						title:"提示",
						btn:["确定","取消"]
					},
					function(index,layero){
					//	layero.find(that.parent().remove())
						layer.close(index);
						//alert(href+"?id="+id);
						window.location.href=href+"?id="+id
					},
					function(index){
						layer.close(index);
					}
				)
			})
 			//高值耗材   修改
			$(document).on("click",'.modifyHighBtn',function(){
				var modifyVal=$(this).parent().find("span").text();
				var ind=$(this).parent().index();
				$(".addClassifyInp").val(modifyVal);
				layer.open({
					type:1,
					title:"修改产品分类",
					content:$(".updateClassifyBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var addInpText=layero.find(".addClassifyInp").val();
						if(addInpText!="" && addInpText!=undefined){
							$(".highProList>.highBottom>ul>li").eq(ind).find("span").text(addInpText);
							var id = $(".highProList>.highBottom>ul>li").eq(ind).find("input").val();
							$(".mustIcon").hide();
							
							window.location = "${ctx}/pd/pdCategory/update?id="+id+"&type=1&name="+addInpText;
							
							layer.closeAll();
						}else{
							$(".mustIcon").hide();
							layer.closeAll();
						}
					},
					btn2:function(){
						$(".mustIcon").hide();
						layer.closeAll();
					}
				})
			});
			
			//低值耗材切换隐藏
			$(".lowTop>h3").click(function(){
				$(".lowBottom>ul").toggle();
				if($(".lowBottom>ul").is(":hidden")){
					$(this).find(".fa").attr("class","fa fa-sort-up")
				}else{
					$(this).find(".fa").attr("class","fa fa-sort-down")
				}
			})
			//低值耗材    新增分类
			$("#addNewLow").click(function(){
				$(".addLowInp").val("");
				layer.open({
					type:1,
					title:"新增产品分类",
					content:$(".addLowBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var addInpText=layero.find(".addLowInp").val();
						if(addInpText!="" && addInpText!=undefined){
							var newLi='<li>'+
										'<span>'+addInpText+'</span>'+
										'<a href="###" class="hcy-btn hcy-btn-o delLowBtn">删除</a>'+
										'<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn">修改</a>'+
									'</li>'
							$(".lowBottom>ul").append(newLi);
							$(".mustIcon").hide();
							layero.find(".addClassifyInp").val("")
							//$("#inputForm").submit();
							
							var a = layero.find("[name = name]").val();
							window.location = "${ctx}/pd/pdCategory/save?name="+a+"&type=0";
							layer.closeAll();
						}else{
							$(".mustIcon").show();
							return false;
						}
					},
					btn2:function(){
						$(".mustIcon").hide();
						layer.closeAll();
					}
				})
			});
			//低值耗材  删除
			$(document).on("click",".delLowBtn",function(){
				var that=$(this);
				var href="${ctx}/pd/pdCategory/delete";
				var id=$(this).attr("data-id");
				layer.confirm(
					"确定要删除吗？",//这里写询问层里面的文本
					{
						icon:3,
						title:"提示",
						btn:["确定","取消"]
					},
					function(index,layero){
					//	layero.find(that.parent().remove())
						layer.close(index);
						window.location.href=href+"?id="+id
					},
					function(index){
						layer.close(index);
					}
				)
			})
			//低值耗材   修改
			$(document).on("click",'.modifyLowBtn',function(){
				var modifyVal=$(this).parent().find("span").text();
				var ind=$(this).parent().index();
				$(".addLowInp").val(modifyVal);
				layer.open({
					type:1,
					title:"修改产品分类",
					content:$(".updateLowBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var addInpText=layero.find(".addLowInp").val();
						if(addInpText!="" && addInpText!=undefined){
							$(".lowBottom>ul>li").eq(ind).find("span").text(addInpText);
							var id = $(".lowBottom>ul>li").eq(ind).find("input").val();
							$(".mustIcon").hide();
							window.location = "${ctx}/pd/pdCategory/update?id="+id+"&type=0&name="+addInpText;
							layer.closeAll();
						}else{
							$(".mustIcon").hide();
							layer.closeAll();
						}
					},
					btn2:function(){
						$(".mustIcon").hide();
						layer.closeAll();
					}
				})
			});
//----------------------

            //医用气体 切换隐藏
            $(".lowTop1>h3").click(function(){
                $(".lowBottom1>ul").toggle();
                if($(".lowBottom1>ul").is(":hidden")){
                    $(this).find(".fa").attr("class","fa fa-sort-up")
                }else{
                    $(this).find(".fa").attr("class","fa fa-sort-down")
                }
            })


            //医用气体    新增分类
            $("#addNewLow1").click(function(){
                $(".addLowInp1").val("");
                layer.open({
                    type:1,
                    title:"新增产品分类",
                    content:$(".addLowBox1"),
                    area:["400px","300px"],
                    shade: [0.8, '#393D49'],
                    btn:["确定","取消"],
                    yes:function(index,layero){
                        var addInpText=layero.find(".addLowInp").val();
                        if(addInpText!="" && addInpText!=undefined){
                            var newLi='<li>'+
                                '<span>'+addInpText+'</span>'+
                                '<a href="###" class="hcy-btn hcy-btn-o delLowBtn1">删除</a>'+
                                '<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn1">修改</a>'+
                                '</li>'
                            $(".lowBottom1>ul").append(newLi);
                            $(".mustIcon").hide();
                            layero.find(".addClassifyInp").val("")
                            //$("#inputForm").submit();

                            var a = layero.find("[name = name]").val();
                            window.location = "${ctx}/pd/pdCategory/save?name="+a+"&type=2";
                            layer.closeAll();
                        }else{
                            $(".mustIcon").show();
                            return false;
                        }
                    },
                    btn2:function(){
                        $(".mustIcon").hide();
                        layer.closeAll();
                    }
                })
            });
            //医用气体  删除
            $(document).on("click",".delLowBtn1",function(){
                var that=$(this);
                var href="${ctx}/pd/pdCategory/delete";
                var id=$(this).attr("data-id");
                layer.confirm(
                    "确定要删除吗？",//这里写询问层里面的文本
                    {
                        icon:3,
                        title:"提示",
                        btn:["确定","取消"]
                    },
                    function(index,layero){
                        //	layero.find(that.parent().remove())
                        layer.close(index);
                        window.location.href=href+"?id="+id
                    },
                    function(index){
                        layer.close(index);
                    }
                )
            })
            //医用气体   修改
             $(document).on("click",'.modifyLowBtn1',function(){
                var modifyVal=$(this).parent().find("span").text();
                var ind=$(this).parent().index();
                $(".addLowInp").val(modifyVal);
                layer.open({
                    type:1,
                    title:"修改产品分类",
                    content:$(".updateLowBox"),
                    area:["400px","300px"],
                    shade: [0.8, '#393D49'],
                    btn:["确定","取消"],
                    yes:function(index,layero){
                        var addInpText=layero.find(".addLowInp").val();
                        if(addInpText!="" && addInpText!=undefined){
                            $(".lowBottom1>ul>li").eq(ind).find("span").text(addInpText);
                            var id = $(".lowBottom1>ul>li").eq(ind).find("input").val();
                            $(".mustIcon").hide();
                            window.location = "${ctx}/pd/pdCategory/update?id="+id+"&type=2&name="+addInpText;
                            layer.closeAll();
                        }else{
                            $(".mustIcon").hide();
                            layer.closeAll();
                        }
                    },
                    btn2:function(){
                        $(".mustIcon").hide();
                        layer.closeAll();
                    }
                })
            });


            //影像资料 切换隐藏
            $(".lowTop2>h3").click(function(){
                $(".lowBottom2>ul").toggle();
                if($(".lowBottom2>ul").is(":hidden")){
                    $(this).find(".fa").attr("class","fa fa-sort-up")
                }else{
                    $(this).find(".fa").attr("class","fa fa-sort-down")
                }
            })


            //影像资料   新增分类
            $("#addNewLow2").click(function(){
                $(".addLowInp2").val("");
                layer.open({
                    type:1,
                    title:"新增产品分类",
                    content:$(".addLowBox2"),
                    area:["400px","300px"],
                    shade: [0.8, '#393D49'],
                    btn:["确定","取消"],
                    yes:function(index,layero){
                        var addInpText=layero.find(".addLowInp").val();
                        if(addInpText!="" && addInpText!=undefined){
                            var newLi='<li>'+
                                '<span>'+addInpText+'</span>'+
                                '<a href="###" class="hcy-btn hcy-btn-o delLowBtn2">删除</a>'+
                                '<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn2">修改</a>'+
                                '</li>'
                            $(".lowBottom2>ul").append(newLi);
                            $(".mustIcon").hide();
                            layero.find(".addClassifyInp").val("")
                            //$("#inputForm").submit();

                            var a = layero.find("[name = name]").val();
                            window.location = "${ctx}/pd/pdCategory/save?name="+a+"&type=3";
                            layer.closeAll();
                        }else{
                            $(".mustIcon").show();
                            return false;
                        }
                    },
                    btn2:function(){
                        $(".mustIcon").hide();
                        layer.closeAll();
                    }
                })
            });
            //影像资料  删除
            $(document).on("click",".delLowBtn2",function(){
                var that=$(this);
                var href="${ctx}/pd/pdCategory/delete";
                var id=$(this).attr("data-id");
                layer.confirm(
                    "确定要删除吗？",//这里写询问层里面的文本
                    {
                        icon:3,
                        title:"提示",
                        btn:["确定","取消"]
                    },
                    function(index,layero){
                        //	layero.find(that.parent().remove())
                        layer.close(index);
                        window.location.href=href+"?id="+id
                    },
                    function(index){
                        layer.close(index);
                    }
                )
            })
            //影像资料   修改
            $(document).on("click",'.modifyLowBtn2',function(){
                var modifyVal=$(this).parent().find("span").text();
                var ind=$(this).parent().index();
                $(".addLowInp").val(modifyVal);
                layer.open({
                    type:1,
                    title:"修改产品分类",
                    content:$(".updateLowBox"),
                    area:["400px","300px"],
                    shade: [0.8, '#393D49'],
                    btn:["确定","取消"],
                    yes:function(index,layero){
                        var addInpText=layero.find(".addLowInp").val();
                        if(addInpText!="" && addInpText!=undefined){
                            $(".lowBottom2>ul>li").eq(ind).find("span").text(addInpText);
                            var id = $(".lowBottom2>ul>li").eq(ind).find("input").val();
                            $(".mustIcon").hide();
                            window.location = "${ctx}/pd/pdCategory/update?id="+id+"&type=3&name="+addInpText;
                            layer.closeAll();
                        }else{
                            $(".mustIcon").hide();
                            layer.closeAll();
                        }
                    },
                    btn2:function(){
                        $(".mustIcon").hide();
                        layer.closeAll();
                    }
                })
            });



            //检验材料 切换隐藏
            $(".lowTop3>h3").click(function(){
                $(".lowBottom3>ul").toggle();
                if($(".lowBottom3>ul").is(":hidden")){
                    $(this).find(".fa").attr("class","fa fa-sort-up")
                }else{
                    $(this).find(".fa").attr("class","fa fa-sort-down")
                }
            })


            //检验材料   新增分类
            $("#addNewLow3").click(function(){
                $(".addLowInp3").val("");
                layer.open({
                    type:1,
                    title:"新增产品分类",
                    content:$(".addLowBox3"),
                    area:["400px","300px"],
                    shade: [0.8, '#393D49'],
                    btn:["确定","取消"],
                    yes:function(index,layero){
                        var addInpText=layero.find(".addLowInp").val();
                        if(addInpText!="" && addInpText!=undefined){
                            var newLi='<li>'+
                                '<span>'+addInpText+'</span>'+
                                '<a href="###" class="hcy-btn hcy-btn-o delLowBtn3">删除</a>'+
                                '<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn3">修改</a>'+
                                '</li>'
                            $(".lowBottom3>ul").append(newLi);
                            $(".mustIcon").hide();
                            layero.find(".addClassifyInp").val("")
                            //$("#inputForm").submit();

                            var a = layero.find("[name = name]").val();
                            window.location = "${ctx}/pd/pdCategory/save?name="+a+"&type=4";
                            layer.closeAll();
                        }else{
                            $(".mustIcon").show();
                            return false;
                        }
                    },
                    btn2:function(){
                        $(".mustIcon").hide();
                        layer.closeAll();
                    }
                })
            });
            //影像资料  删除
            $(document).on("click",".delLowBtn3",function(){
                var that=$(this);
                var href="${ctx}/pd/pdCategory/delete";
                var id=$(this).attr("data-id");
                layer.confirm(
                    "确定要删除吗？",//这里写询问层里面的文本
                    {
                        icon:3,
                        title:"提示",
                        btn:["确定","取消"]
                    },
                    function(index,layero){
                        //	layero.find(that.parent().remove())
                        layer.close(index);
                        window.location.href=href+"?id="+id
                    },
                    function(index){
                        layer.close(index);
                    }
                )
            })
            //影像资料   修改
            $(document).on("click",'.modifyLowBtn3',function(){
                var modifyVal=$(this).parent().find("span").text();
                var ind=$(this).parent().index();
                $(".addLowInp").val(modifyVal);
                layer.open({
                    type:1,
                    title:"修改产品分类",
                    content:$(".updateLowBox"),
                    area:["400px","300px"],
                    shade: [0.8, '#393D49'],
                    btn:["确定","取消"],
                    yes:function(index,layero){
                        var addInpText=layero.find(".addLowInp").val();
                        if(addInpText!="" && addInpText!=undefined){
                            $(".lowBottom3>ul>li").eq(ind).find("span").text(addInpText);
                            var id = $(".lowBottom3>ul>li").eq(ind).find("input").val();
                            $(".mustIcon").hide();
                            window.location = "${ctx}/pd/pdCategory/update?id="+id+"&type=4&name="+addInpText;
                            layer.closeAll();
                        }else{
                            $(".mustIcon").hide();
                            layer.closeAll();
                        }
                    },
                    btn2:function(){
                        $(".mustIcon").hide();
                        layer.closeAll();
                    }
                })
            });

            //设备 切换隐藏
            $(".lowTop4>h3").click(function(){
                $(".lowBottom4>ul").toggle();
                if($(".lowBottom4>ul").is(":hidden")){
                    $(this).find(".fa").attr("class","fa fa-sort-up")
                }else{
                    $(this).find(".fa").attr("class","fa fa-sort-down")
                }
            })


            //设备   新增分类
            $("#addNewLow4").click(function(){
                $(".addLowInp4").val("");
                layer.open({
                    type:1,
                    title:"新增产品分类",
                    content:$(".addLowBox4"),
                    area:["400px","300px"],
                    shade: [0.8, '#393D49'],
                    btn:["确定","取消"],
                    yes:function(index,layero){
                        var addInpText=layero.find(".addLowInp").val();
                        if(addInpText!="" && addInpText!=undefined){
                            var newLi='<li>'+
                                '<span>'+addInpText+'</span>'+
                                '<a href="###" class="hcy-btn hcy-btn-o delLowBtn4">删除</a>'+
                                '<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn4">修改</a>'+
                                '</li>'
                            $(".lowBottom4>ul").append(newLi);
                            $(".mustIcon").hide();
                            layero.find(".addClassifyInp").val("")
                            //$("#inputForm").submit();

                            var a = layero.find("[name = name]").val();
                            window.location = "${ctx}/pd/pdCategory/save?name="+a+"&type=5";
                            layer.closeAll();
                        }else{
                            $(".mustIcon").show();
                            return false;
                        }
                    },
                    btn2:function(){
                        $(".mustIcon").hide();
                        layer.closeAll();
                    }
                })
            });
            //设备  删除
            $(document).on("click",".delLowBtn4",function(){
                var that=$(this);
                var href="${ctx}/pd/pdCategory/delete";
                var id=$(this).attr("data-id");
                layer.confirm(
                    "确定要删除吗？",//这里写询问层里面的文本
                    {
                        icon:3,
                        title:"提示",
                        btn:["确定","取消"]
                    },
                    function(index,layero){
                        //	layero.find(that.parent().remove())
                        layer.close(index);
                        window.location.href=href+"?id="+id
                    },
                    function(index){
                        layer.close(index);
                    }
                )
            })
            //设备   修改
            $(document).on("click",'.modifyLowBtn4',function(){
                var modifyVal=$(this).parent().find("span").text();
                var ind=$(this).parent().index();
                $(".addLowInp").val(modifyVal);
                layer.open({
                    type:1,
                    title:"修改产品分类",
                    content:$(".updateLowBox"),
                    area:["400px","300px"],
                    shade: [0.8, '#393D49'],
                    btn:["确定","取消"],
                    yes:function(index,layero){
                        var addInpText=layero.find(".addLowInp").val();
                        if(addInpText!="" && addInpText!=undefined){
                            $(".lowBottom4>ul>li").eq(ind).find("span").text(addInpText);
                            var id = $(".lowBottom4>ul>li").eq(ind).find("input").val();
                            $(".mustIcon").hide();
                            window.location = "${ctx}/pd/pdCategory/update?id="+id+"&type=5&name="+addInpText;
                            layer.closeAll();
                        }else{
                            $(".mustIcon").hide();
                            layer.closeAll();
                        }
                    },
                    btn2:function(){
                        $(".mustIcon").hide();
                        layer.closeAll();
                    }
                })
            });

            //医疗器械 切换隐藏
            $(".lowTop5>h3").click(function(){
                $(".lowBottom5>ul").toggle();
                if($(".lowBottom5>ul").is(":hidden")){
                    $(this).find(".fa").attr("class","fa fa-sort-up")
                }else{
                    $(this).find(".fa").attr("class","fa fa-sort-down")
                }
            })


            //医疗器械   新增分类
            $("#addNewLow5").click(function(){
                $(".addLowInp5").val("");
                layer.open({
                    type:1,
                    title:"新增产品分类",
                    content:$(".addLowBox5"),
                    area:["400px","300px"],
                    shade: [0.8, '#393D49'],
                    btn:["确定","取消"],
                    yes:function(index,layero){
                        var addInpText=layero.find(".addLowInp").val();
                        if(addInpText!="" && addInpText!=undefined){
                            var newLi='<li>'+
                                '<span>'+addInpText+'</span>'+
                                '<a href="###" class="hcy-btn hcy-btn-o delLowBtn5">删除</a>'+
                                '<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn5">修改</a>'+
                                '</li>'
                            $(".lowBottom5>ul").append(newLi);
                            $(".mustIcon").hide();
                            layero.find(".addClassifyInp").val("")
                            //$("#inputForm").submit();

                            var a = layero.find("[name = name]").val();
                            window.location = "${ctx}/pd/pdCategory/save?name="+a+"&type=6";
                            layer.closeAll();
                        }else{
                            $(".mustIcon").show();
                            return false;
                        }
                    },
                    btn2:function(){
                        $(".mustIcon").hide();
                        layer.closeAll();
                    }
                })
            });
            //医疗器械  删除
            $(document).on("click",".delLowBtn5",function(){
                var that=$(this);
                var href="${ctx}/pd/pdCategory/delete";
                var id=$(this).attr("data-id");
                layer.confirm(
                    "确定要删除吗？",//这里写询问层里面的文本
                    {
                        icon:3,
                        title:"提示",
                        btn:["确定","取消"]
                    },
                    function(index,layero){
                        //	layero.find(that.parent().remove())
                        layer.close(index);
                        window.location.href=href+"?id="+id
                    },
                    function(index){
                        layer.close(index);
                    }
                )
            })
            //医疗器械   修改
            $(document).on("click",'.modifyLowBtn5',function(){
                var modifyVal=$(this).parent().find("span").text();
                var ind=$(this).parent().index();
                $(".addLowInp").val(modifyVal);
                layer.open({
                    type:1,
                    title:"修改产品分类",
                    content:$(".updateLowBox"),
                    area:["400px","300px"],
                    shade: [0.8, '#393D49'],
                    btn:["确定","取消"],
                    yes:function(index,layero){
                        var addInpText=layero.find(".addLowInp").val();
                        if(addInpText!="" && addInpText!=undefined){
                            $(".lowBottom5>ul>li").eq(ind).find("span").text(addInpText);
                            var id = $(".lowBottom5>ul>li").eq(ind).find("input").val();
                            $(".mustIcon").hide();
                            window.location = "${ctx}/pd/pdCategory/update?id="+id+"&type=6&name="+addInpText;
                            layer.closeAll();
                        }else{
                            $(".mustIcon").hide();
                            layer.closeAll();
                        }
                    },
                    btn2:function(){
                        $(".mustIcon").hide();
                        layer.closeAll();
                    }
                })
            });

            //备品备件 切换隐藏
            $(".lowTop6>h3").click(function(){
                $(".lowBottom6>ul").toggle();
                if($(".lowBottom6>ul").is(":hidden")){
                    $(this).find(".fa").attr("class","fa fa-sort-up")
                }else{
                    $(this).find(".fa").attr("class","fa fa-sort-down")
                }
            })


            //备品备件   新增分类
            $("#addNewLow6").click(function(){
                $(".addLowInp6").val("");
                layer.open({
                    type:1,
                    title:"新增产品分类",
                    content:$(".addLowBox6"),
                    area:["400px","300px"],
                    shade: [0.8, '#393D49'],
                    btn:["确定","取消"],
                    yes:function(index,layero){
                        var addInpText=layero.find(".addLowInp").val();
                        if(addInpText!="" && addInpText!=undefined){
                            var newLi='<li>'+
                                '<span>'+addInpText+'</span>'+
                                '<a href="###" class="hcy-btn hcy-btn-o delLowBtn6">删除</a>'+
                                '<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn6">修改</a>'+
                                '</li>'
                            $(".lowBottom6>ul").append(newLi);
                            $(".mustIcon").hide();
                            layero.find(".addClassifyInp").val("")
                            //$("#inputForm").submit();

                            var a = layero.find("[name = name]").val();
                            window.location = "${ctx}/pd/pdCategory/save?name="+a+"&type=7";
                            layer.closeAll();
                        }else{
                            $(".mustIcon").show();
                            return false;
                        }
                    },
                    btn2:function(){
                        $(".mustIcon").hide();
                        layer.closeAll();
                    }
                })
            });
            //备品备件  删除
            $(document).on("click",".delLowBtn6",function(){
                var that=$(this);
                var href="${ctx}/pd/pdCategory/delete";
                var id=$(this).attr("data-id");
                layer.confirm(
                    "确定要删除吗？",//这里写询问层里面的文本
                    {
                        icon:3,
                        title:"提示",
                        btn:["确定","取消"]
                    },
                    function(index,layero){
                        //	layero.find(that.parent().remove())
                        layer.close(index);
                        window.location.href=href+"?id="+id
                    },
                    function(index){
                        layer.close(index);
                    }
                )
            })
            //备品备件   修改
            $(document).on("click",'.modifyLowBtn6',function(){
                var modifyVal=$(this).parent().find("span").text();
                var ind=$(this).parent().index();
                $(".addLowInp").val(modifyVal);
                layer.open({
                    type:1,
                    title:"修改产品分类",
                    content:$(".updateLowBox"),
                    area:["400px","300px"],
                    shade: [0.8, '#393D49'],
                    btn:["确定","取消"],
                    yes:function(index,layero){
                        var addInpText=layero.find(".addLowInp").val();
                        if(addInpText!="" && addInpText!=undefined){
                            $(".lowBottom6>ul>li").eq(ind).find("span").text(addInpText);
                            var id = $(".lowBottom6>ul>li").eq(ind).find("input").val();
                            $(".mustIcon").hide();
                            window.location = "${ctx}/pd/pdCategory/update?id="+id+"&type=7&name="+addInpText;
                            layer.closeAll();
                        }else{
                            $(".mustIcon").hide();
                            layer.closeAll();
                        }
                    },
                    btn2:function(){
                        $(".mustIcon").hide();
                        layer.closeAll();
                    }
                })
            });
 <%--//---------------------	--%>
		})
	</script>
</body>
</html>