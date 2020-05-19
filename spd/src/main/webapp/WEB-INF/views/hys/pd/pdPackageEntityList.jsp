<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实体包表管理</title>
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
		<li class="active"><a href="${ctx}/pd/pdPackageEntity/">实体包表列表</a></li>
		<shiro:hasPermission name="pd:pdPackageEntity:edit"><li><a href="${ctx}/pd/pdPackageEntity/form">实体包表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pdPackageEntity" action="${ctx}/pd/pdPackageEntity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>update_date</th>
				<th>remarks</th>
				<shiro:hasPermission name="pd:pdPackageEntity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pdPackageEntity">
			<tr>
				<td><a href="${ctx}/pd/pdPackageEntity/form?id=${pdPackageEntity.id}">
					<fmt:formatDate value="${pdPackageEntity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${pdPackageEntity.remarks}
				</td>
				<shiro:hasPermission name="pd:pdPackageEntity:edit"><td>
    				<a href="${ctx}/pd/pdPackageEntity/form?id=${pdPackageEntity.id}">修改</a>
					<a href="${ctx}/pd/pdPackageEntity/delete?id=${pdPackageEntity.id}" onclick="return confirmx('确认要删除该实体包表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>