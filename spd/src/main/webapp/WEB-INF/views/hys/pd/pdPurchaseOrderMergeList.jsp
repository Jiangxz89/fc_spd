<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<title>采购订单</title>
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
	<div class="right-main-box">
		<div class="btnBox">
			<h4>采购订单</h4>
		</div>
		<form:form id="searchForm" modelAttribute="pdPurchaseOrderMerge" action="${ctx}/pd/pdPurchaseOrderMerge/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="searchBox">
				<label for="">申购单号</label>
				<form:input autocomplete="off" style="width:165px;" path="orderNo" />
				<label for="">申购日期</label>
				<%-- <form:input path="purchaseDate" id="applyBuyTime"  name = "applyBuyTime" style="width:160px;" value="${pdPurchaseOrderMerge.applyBuyTime}"/> --%>
				<input name="queryDate"  autocomplete="off" style="width:165px;" type="text" readonly="readonly" maxlength="20" id="applyBuyTime" class="input-medium Wdate" value="${pdPurchaseOrderMerge.queryDate}"/>
				<a href="###" class="hcy-btn hcy-search" onclick="query();">查询</a>
			</div>
		</form:form>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>申购单号</th>
						<th>申购日期</th>
						<th>申购科室</th>
						<th>状态</th>
						<c:if test="${ifPlatform == '0' ||isUrgent == '0'}">
							<th>供应商受理</th>
						</c:if>
						<th>申购人员</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="li">
						<tr>
							<td>
								<a title="${li.orderNo}" href="${ctx}/pd/pdPurchaseOrderMerge/form?mergeOrderNo=${li.orderNo}">${li.orderNo}</a>
							</td>
							<td><fmt:formatDate value="${li.purchaseDate }" pattern="yyyy-MM-dd"/></td>
							<td>${li.deptName }</td>
							<td>${fns:getDictLabel(li.orderStatus,'order_status','') }</td>
							<c:if test="${ifPlatform == '0' ||isUrgent == '0'}">
								<c:choose>
									<c:when test="${li.supplierStatus=='4' || li.supplierStatus==null}">
										<td><a href="${ctx}/pd/pdPurchaseOrderMerge/uploadPlatform?mergeOrderNo=${li.orderNo}" class="hcy-btn hcy-btn-o">上传</a></td>
									</c:when>
									<c:otherwise>
										<td>${fns:getDictLabel(li.supplierStatus,'purchase_order_status','') }</td>
									</c:otherwise>
								</c:choose>
							</c:if>
							<td>${li.purchaseName }</td>
							<td>${li.remarks }</td>
							<td><a href="${ctx}/pd/pdPurchaseOrderMerge/form?mergeOrderNo=${li.orderNo}" class="hcy-btn hcy-btn-o">查看</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//申购时间
			laydate.render({
				elem: '#applyBuyTime',
				range: true
			});
		})
		function query(){
			$('#searchForm').submit();
			return false;
		}
	</script>
</body>
</html>