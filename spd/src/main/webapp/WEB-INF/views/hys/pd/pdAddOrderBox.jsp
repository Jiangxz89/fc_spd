<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<title>采购管理</title>
	<style type="text/css">
		.none{display:none;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
<div class="right-main-box"> 
	<div class="searchBox">
		<form:form id="searchForm" modelAttribute="pdPurchaseOrder" action="${ctx}/pd/pdPurchaseOrder/chooseOrderList" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<form:hidden path="orderStatus"/>
			<form:hidden path="deptId"/>
			<div>
				<label>申购单号</label>
				<form:input path="orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</div>
			<div>
				<label>申购日期</label>
				<form:input path="queryDate" htmlEscape="false" id="inHomeTime" style="width:160px;" readonly="readonly" maxlength="64" class="input-medium" />
			</div>
			<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
		</form:form>
	</div>
	<sys:message content="${message}"/>
	<div class="tableBox">
		<table id="contentTable" class="hcy-public-table" style="padding:0;">
			<thead>
				<tr>
					<th style="width:60px;">选择</th>
					<th style="width:160px">申购单号</th>
					<th>申购日期</th>
					<th>申购科室</th>
					<th>状态</th>
					<th>申购人员</th>
					<!--  <th>操作</th> -->
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="pdPurchaseOrder">
				<tr>
					<td>
						<input type="radio" name="subBox" data-order-no="${pdPurchaseOrder.orderNo}"/>
					</td>
					<td>${pdPurchaseOrder.orderNo}</td>
					<td>
						<fmt:formatDate value="${pdPurchaseOrder.orderDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>${pdPurchaseOrder.deptName}</td>
					<td>${fns:getDictLabel(pdPurchaseOrder.orderStatus, 'order_status', '')}</td>
					<td>${pdPurchaseOrder.purchaseName}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="pagination">${page}</div>
</div>
	<!--  -->
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		//选择已经选中的数据
		function compositeHtml(){
			var orderNo = null;
			$("input[name=subBox]:checked").each(function() {
				orderNo = $(this).attr("data-order-no"); //订单号
			})
			return orderNo;
		}
		$(function(){
			//申购时间
			laydate.render({
				elem: '#inHomeTime',
				range: true
			});
		})
	</script>
</body>
</html>