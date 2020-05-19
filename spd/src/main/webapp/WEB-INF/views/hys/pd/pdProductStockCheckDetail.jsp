<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存盘点信息详情</title>
	<meta charset="UTF-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<style>
		.topInfo{padding:10px 0 0 0;font-size:13px;color:#666;}
		.topInfo div>a.hcy-btn{margin-right:10px;}
		.bottomInfo{padding:0 0 10px 0;font-size: 13px;color: #666;}
		.bottomInfo span{padding-right: 60px;}
		.bottomInfo a{line-height: 1.5;}
		.selWarn{color:red;font-size:13px;padding-left:15px;display:none;}
		.searchBox span{display:inline-block;font-size:13px;color:#666;vertical-align: middle;padding-right: 20px;;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
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
		<div class="btnBox">
			<h4>盘点详情</h4>
		</div>
		<div class="searchBox">
				<label style="width:70px;">盘点时间：</label><span style="vertical-align:middle;">${checkBean.checkDate }</span>
				<input type="hidden" name="checkDate" value="${checkBean.checkDate }" />
				<div class="fr" style="padding:10px 0;">
					<c:if test="${checkBean.status == '0' }">
						<a href="###" onclick="exportData()" style="line-height:1.5;" class="hcy-btn hcy-btn-primary">导出</a>
						<a href="${ctx }/pd/pdProductStockCheck/delete?id=${checkBean.id}" onclick="return confirmx('确认要删除该盘点吗？', this.href)" style="line-height:1.5;" class="hcy-btn hcy-btn-primary">删除</a>
						<a href="${ctx }/pd/pdProductStockCheck/form?id=${checkBean.id}" onclick="#" style="line-height:1.5;" id="checkDone" class="hcy-btn hcy-btn-primary">编辑</a>
					</c:if>
				</div><br>
				<label>编号：</label><span class="checkCode">${checkBean.number }</span>
				<input type="hidden" name="number" />
				<label style="width:70px;">盘点仓库：</label><span id="warehouseName" class="checkHouse">${checkBean.repoName }</span>
				<input type="hidden" id="repoId" name="repoId" />
				<input type="hidden" id="repoName" name="repoName" value="${checkBean.repoName }"/>
				<label style="width:95px;">盘点产品量：</label><span id="checkNum" class="checkNum">${checkBean.shouldCount }</span>
				<input type="hidden" id="shouldCount" name="shouldCount" />
				<label style="width:95px;">已盘点产品量：</label><span style="width:105px;" id="checkedNum" class="checkedNum">${checkBean.alreadyCount }</span>
				<input type="hidden" id="alreadyCount" name="alreadyCount" />
			</div>
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>产品名称</th>
						<th>产品编号</th>
						<th>产品条码</th>
						<th>规格</th>
						<th>批号</th>
						<th>有效期</th>
						<th>单位</th>
						<th>理论数量</th>
						<th>盘点数量</th>
						<th>盘盈盘亏</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody id="contentTableTbody">
					<form:form id="dataForm" modelAttribute="stockCheckExcelList" method="post" class="breadcrumb form-search">
						<c:forEach items="${checkBean.child }" var="a" varStatus="status">
							<tr>
								<td><input type="hidden" value="${a.pdProduct.name }" name="list[${status.index }].prodName"/>${a.pdProduct.name }</td>
								<td><input type="hidden" value="${a.pdProduct.number }" name="list[${status.index }].prodNumber"/>${a.pdProduct.number }</td>
								<td><input type="hidden" value="${a.pdProductStock.productBarCode }" name="list[${status.index }].prodBarCode"/>${a.pdProductStock.productBarCode }</td>
								<td><input type="hidden" value="${a.pdProduct.spec }" name="list[${status.index }].prodSpec"/>${a.pdProduct.spec }</td>
								<td><input type="hidden" value="${a.pdProductStock.batchNo }" name="list[${status.index }].prodBatchNo"/>${a.pdProductStock.batchNo }</td>
								<td><input type="hidden" value="<fmt:formatDate value="${a.pdProductStock.validDate }" pattern="yyyy-MM-dd" />" name="list[${status.index }].prodValidDate"/><fmt:formatDate value="${a.pdProductStock.validDate }" pattern="yyyy-MM-dd" /></td>
								<td><input type="hidden" value="${a.pdProduct.unit }" name="list[${status.index }].prodUnit"/>${a.pdProduct.unit }</td>
								<td><input type="hidden" value="${a.pdProductStock.stockNum }" name="list[${status.index }].stockNum"/>${a.pdProductStock.stockNum }</td>
								<td><input type="hidden" value="${a.actualCount }" name="list[${status.index }].actualCount"/>${a.actualCount }</td>
								<input type="hidden" value="${a.profitLossCount }" name="list[${status.index }].profitLossCount"/>
								<td class="profitLossCount">${a.profitLossCount }</td>
								<td><input type="hidden" value="${a.remarks }" name="list[${status.index }].remarks"/>${a.remarks }</td>
							</tr>
						</c:forEach>
					</form:form>
				</tbody>
			</table>
		<div style="text-align:right;line-height:30px;padding-right:20px;" >
			盘盈：<span style="padding-right:20px;" id="result1">0</span>盘亏：<span id="result2">0</span>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/js/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script>
		$(function(){
			var eles = $(".profitLossCount");
			var sum = 0;
			for(var i = 0 ; i < eles.length ; i ++){
				var a = eles[i].innerHTML;
				sum += parseInt(a);
			}
			if(sum > 0){
				$("#result1").empty();
				$("#result1").text(sum);
			}else if(sum < 0){
				$("#result2").empty();
				$("#result2").text(sum);
			}else{
				
			}
		})
		
		
		//全部导出
		function exportData(){
			var head = $("#repoName").val()+"的盘点:"+$("[name=checkDate]").val();
			$("#dataForm").attr("action","${ctx}/pd/pdProductStockCheck/exportData?head="+head);
			$("#dataForm").submit();
		}
	</script>
</body>
</html>