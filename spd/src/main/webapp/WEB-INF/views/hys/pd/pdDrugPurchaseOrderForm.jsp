<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<style>
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#allMoney,#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;}
		.otherText>h3{font-weight:400;display:inline-block;padding:3px 10px 0 5px;font-size:12px;color:#666;}
	</style>
	<title>申请采购</title>
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var isn = $.trim($('tbody').html());
					if(!isn){
						layer.alert('请添加产品',{icon:0})
						return false;
					}
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<div class="right-main-box">
		<!-- <div class="btnBox">
			<h4>申请采购</h4>
		</div> -->
		<form:form id="inputForm" modelAttribute="medstoYpcgzd" action="${ctx}/pd/medstoYpcgzd/save" method="post" class="form-horizontal">
			<div class="searchBox">
				<label for="">申购单号</label>
				<form:input path="djh" style="width:170px;" value="${medstoYpcgzd.xh }" readonly="true"/>
				<label for="">申购科室</label>
				<input type="text" value="${fns:getUser().storeroomName  }" readonly="true"/>
				<form:hidden path="ksdm" value="${fns:getUser().storeroomId  }"/>
				<label for="">申购人员</label>
				<input type="text" value="${fns:getUser().name  }" readonly="true"/>
				<form:hidden path="czyh" value="${fns:getUser().id  }"/>
			</div>
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>操作</th>
						<th>药品名称</th>
						<th>药品编号</th>
						<th>药品规格</th>
						<th>药品单位</th>
						<th>药库系数</th>
						<th>批发价</th>
						<th>零售价</th>
						<th>库存数量</th>
						<th>申购数量</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div class="totalText">
				<span id="allNum">总数量:<span class="total_number">0</span></span>
				<!-- <span id="allMoney">总金额:<span class="total_money">0.00</span></span> -->
			</div>
			<a href="###"class="hcy-btn hcy-btn-primary" id="addProBtn">添加药品</a>
		</div>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
   			<input type="button" value="保存" class="hcy-btn hcy-save" id="submitBtn"/>
   			<a href="javascript:location=document.referrer;" class="hcy-btn hcy-back" style="line-height: normal;">返回</a>
   		</div>
		<sys:message content="${message}"/>
	</form:form>
	</div>
	<!-- 记录添加的行号 -->
	<input type="hidden" id="rowsRecord" value="0"/>
	<script type="text/javascript">
		var prods = [],//保存添加的产品编码
		    alltotal = 0.00,//总金额
		    allcount = 0;//总数量
		$(function(){
			$('#submitBtn').click(function(){
				var len = $('tbody>tr').length;
				var flag = true;
				$("input[name$='cgsl']").each(function(i,v){
					var result = alertWarn('请输入大于0的整数', $(this));
					if(result == false){
						flag = false;
					}
				});
				if(!flag)
					return false;
				if(len<1){
					layer.alert('请添加药品',{icon:0});
				}else{
					loading('正在提交，请稍等...');
					$('#inputForm').submit();
				}
				return false;
			});
			//添加药品
			$("#addProBtn").click(function(){
				layer.open({
					type:2,
					title:"添加药品",
					content:'${ctx}/pd/medstoYpcdmlk/addDrug',
					area:["965px","527px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var childWindow = layero.find('iframe')[0].contentWindow;
						var result = childWindow.compositeHtml($('#rowsRecord').val());
						if(result === 0){
							layer.alert('请选择药品',{icon: 0});
						}else if(result.startsWith("-")){
							layer.alert(result.substring(1,result.length),{icon: 0});
						}else{
							$('tbody').append(result);
							$('#rowsRecord').val($('tbody>tr').length);
							layer.closeAll();
						}
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			})
		});
		//删除该产品
		function removeDiv(id){
			$('.remove_'+id).remove();
			var idx = prods.indexOf(Number(id));
			if(idx > -1)
				prods.splice(idx,1);
			//计算总数量
			allcount = 0;
			$("input[class*='inputPurchaseCount']").each(function(i,v){
				allcount += parseInt(!($(this).val())?'0':$(this).val());
			});
			$('.total_number').html(allcount);
			//计算总金额
			/* alltotal = 0.00;
			$("td[id^='finalMoney']").each(function(i,v){
				alltotal += parseInt($(this).text());
			});
			$('.total_money').html(alltotal.toFixed(4)); */
		}
		//计算单个金额和总数量
		$(document).on('input propertychange', '.inputPurchaseCount', function(){
			var prodId = $(this).attr('data-flag'),
			    curValue = $.trim($(this).val()),
			    singlePrice = $.trim($('#sellingPrice'+prodId).text()),
			    total = null;
			alertWarn('请输入大于0的整数', $(this));
			try{
				total = (parseInt(curValue) * parseFloat(singlePrice || 0)).toFixed(4);
				$('#finalMoney'+prodId).text(total);
			}catch(e){
				//layer.alert('请输入大于0的整数',{icon:2});
			}
			//计算总数量
			allcount = 0;
			$("input[class*='inputPurchaseCount']").each(function(i,v){
				var presentV = $.trim($(this).val());
				if(!isNumber(presentV))
					presentV = 0;
				allcount += parseInt(presentV);
			});
			$('.total_number').html(allcount);
			//计算总金额
			/* alltotal = 0.00;
			$("td[id^='finalMoney']").each(function(i,v){
				alltotal += parseInt($(this).text());
			});
			$('.total_money').html(alltotal.toFixed(2)); */
			return false;
		});
	</script>
</body>
</html>