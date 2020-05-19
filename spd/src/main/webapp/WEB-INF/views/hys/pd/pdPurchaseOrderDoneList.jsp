<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申购单管理</title>
	<meta name="decorator" content="default"/>
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pd/pdPurchaseOrder/">申购单列表</a></li>
		<shiro:hasPermission name="pd:pdPurchaseOrder:edit"><li><a href="${ctx}/pd/pdPurchaseOrder/form">申购单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pdPurchaseOrder" action="${ctx}/pd/pdPurchaseOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申购日期：</label>
				<input name="orderDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pdPurchaseOrder.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>申购科室ID：</label>
				<form:input path="deptId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>申购单状态：</label>
				<form:input path="orderStatus" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>申购单号</th>
				<th>申购人</th>
				<th>申购日期</th>
				<th>申购科室ID</th>
				<th>申购科室名称</th>
				<th>申购单状态</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="pd:pdPurchaseOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pdPurchaseOrder">
			<tr>
				<td><a href="${ctx}/pd/pdPurchaseOrder/form?id=${pdPurchaseOrder.id}">
					${pdPurchaseOrder.orderNo}
				</a></td>
				<td>
					${pdPurchaseOrder.orderBy}
				</td>
				<td>
					<fmt:formatDate value="${pdPurchaseOrder.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pdPurchaseOrder.deptId}
				</td>
				<td>
					${pdPurchaseOrder.deptName}
				</td>
				<td>
					${pdPurchaseOrder.orderStatus}
				</td>
				<td>
					${pdPurchaseOrder.remarks}
				</td>
				<td>
					<fmt:formatDate value="${pdPurchaseOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pd:pdPurchaseOrder:edit"><td>
    				<a href="${ctx}/pd/pdPurchaseOrder/form?id=${pdPurchaseOrder.id}">修改</a>
					<a href="${ctx}/pd/pdPurchaseOrder/delete?id=${pdPurchaseOrder.id}" onclick="return confirmx('确认要删除该申购单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>