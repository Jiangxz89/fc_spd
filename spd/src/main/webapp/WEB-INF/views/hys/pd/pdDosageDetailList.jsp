<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>器械用量详情管理</title>
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
		<li class="active"><a href="${ctx}/pd/pdDosageDetail/">器械用量详情列表</a></li>
		<shiro:hasPermission name="pd:pdDosageDetail:edit"><li><a href="${ctx}/pd/pdDosageDetail/form">器械用量详情添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pdDosageDetail" action="${ctx}/pd/pdDosageDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用量单号：</label>
				<form:input path="dosageNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>产品ID：</label>
				<form:input path="prodId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>产品编码：</label>
				<form:input path="prodNo" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>产品条码：</label>
				<form:input path="prodBarcode" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>用量单号</th>
				<th>产品ID</th>
				<th>产品编码</th>
				<th>产品条码</th>
				<th>批号</th>
				<th>有效期</th>
				<th>用量数量</th>
				<th>金额</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="pd:pdDosageDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pdDosageDetail">
			<tr>
				<td><a href="${ctx}/pd/pdDosageDetail/form?id=${pdDosageDetail.id}">
					${pdDosageDetail.dosageNo}
				</a></td>
				<td>
					${pdDosageDetail.prodId}
				</td>
				<td>
					${pdDosageDetail.prodNo}
				</td>
				<td>
					${pdDosageDetail.prodBarcode}
				</td>
				<td>
					${pdDosageDetail.batchNo}
				</td>
				<td>
					<fmt:formatDate value="${pdDosageDetail.expireDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pdDosageDetail.dosageCount}
				</td>
				<td>
					${pdDosageDetail.amountMoney}
				</td>
				<td>
					${pdDosageDetail.remarks}
				</td>
				<td>
					<fmt:formatDate value="${pdDosageDetail.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pd:pdDosageDetail:edit"><td>
    				<a href="${ctx}/pd/pdDosageDetail/form?id=${pdDosageDetail.id}">修改</a>
					<a href="${ctx}/pd/pdDosageDetail/delete?id=${pdDosageDetail.id}" onclick="return confirmx('确认要删除该器械用量详情吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>