<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申购单详细管理</title>
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
		<li class="active"><a href="${ctx}/pd/pdPurchaseDetail/">申购单详细列表</a></li>
		<shiro:hasPermission name="pd:pdPurchaseDetail:edit"><li><a href="${ctx}/pd/pdPurchaseDetail/form">申购单详细添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pdPurchaseDetail" action="${ctx}/pd/pdPurchaseDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申购单号：</label>
				<form:input path="orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>产品ID：</label>
				<form:input path="prodId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>产品编码：</label>
				<form:input path="prodNo" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>批号：</label>
				<form:input path="batchNo" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>产品编码</th>
				<th>批号</th>
				<th>有效期</th>
				<th>申领数量</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="pd:pdPurchaseDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pdPurchaseDetail">
			<tr>
				<td><a href="${ctx}/pd/pdPurchaseDetail/form?id=${pdPurchaseDetail.id}">
					${pdPurchaseDetail.orderNo}
				</a></td>
				<td>
					${pdPurchaseDetail.prodNo}
				</td>
				<td>
					${pdPurchaseDetail.batchNo}
				</td>
				<td>
					<fmt:formatDate value="${pdPurchaseDetail.expireDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pdPurchaseDetail.applyCount}
				</td>
				<td>
					${pdPurchaseDetail.remarks}
				</td>
				<td>
					<fmt:formatDate value="${pdPurchaseDetail.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pd:pdPurchaseDetail:edit"><td>
    				<a href="${ctx}/pd/pdPurchaseDetail/form?id=${pdPurchaseDetail.id}">修改</a>
					<a href="${ctx}/pd/pdPurchaseDetail/delete?id=${pdPurchaseDetail.id}" onclick="return confirmx('确认要删除该申购单详细吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>