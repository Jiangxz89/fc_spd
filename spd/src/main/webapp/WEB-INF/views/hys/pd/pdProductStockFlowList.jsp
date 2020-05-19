<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存流水管理</title>
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
		<li class="active"><a href="${ctx}/pd/pdProductStockFlow/">库存流水列表</a></li>
		<shiro:hasPermission name="pd:pdProductStockFlow:edit"><li><a href="${ctx}/pd/pdProductStockFlow/form">库存流水添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pdProductStockFlow" action="${ctx}/pd/pdProductStockFlow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>出入库id : 出入库id：</label>
				<form:input path="recordId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>出入库日期 : 出入库日期：</label>
				<input name="recordDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pdProductStockFlow.recordDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>出入库类型 : 0入库，1出库：</label>
				<form:input path="recordType" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>产品id：</label>
				<form:input path="productId" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间 : 更新时间</th>
				<th>备注信息 : 备注信息</th>
				<shiro:hasPermission name="pd:pdProductStockFlow:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pdProductStockFlow">
			<tr>
				<td><a href="${ctx}/pd/pdProductStockFlow/form?id=${pdProductStockFlow.id}">
					<fmt:formatDate value="${pdProductStockFlow.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${pdProductStockFlow.remarks}
				</td>
				<shiro:hasPermission name="pd:pdProductStockFlow:edit"><td>
    				<a href="${ctx}/pd/pdProductStockFlow/form?id=${pdProductStockFlow.id}">修改</a>
					<a href="${ctx}/pd/pdProductStockFlow/delete?id=${pdProductStockFlow.id}" onclick="return confirmx('确认要删除该库存流水吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>