<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实体包实体产品表管理</title>
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
		<li class="active"><a href="${ctx}/pd/pdPackageEntityChild/">实体包实体产品表列表</a></li>
		<shiro:hasPermission name="pd:pdPackageEntityChild:edit"><li><a href="${ctx}/pd/pdPackageEntityChild/form">实体包实体产品表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pdPackageEntityChild" action="${ctx}/pd/pdPackageEntityChild/" method="post" class="breadcrumb form-search">
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
				<shiro:hasPermission name="pd:pdPackageEntityChild:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pdPackageEntityChild">
			<tr>
				<td><a href="${ctx}/pd/pdPackageEntityChild/form?id=${pdPackageEntityChild.id}">
					<fmt:formatDate value="${pdPackageEntityChild.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${pdPackageEntityChild.remarks}
				</td>
				<shiro:hasPermission name="pd:pdPackageEntityChild:edit"><td>
    				<a href="${ctx}/pd/pdPackageEntityChild/form?id=${pdPackageEntityChild.id}">修改</a>
					<a href="${ctx}/pd/pdPackageEntityChild/delete?id=${pdPackageEntityChild.id}" onclick="return confirmx('确认要删除该实体包实体产品表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>