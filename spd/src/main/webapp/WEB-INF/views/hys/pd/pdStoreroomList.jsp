<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库房信息管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
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
			<h4>库房管理</h4>
			<shiro:hasPermission name="pd:pdStoreroom:edit"><a class="hcy-btn hcy-btn-primary" href="${ctx}/pd/pdStoreroom/form">库房信息添加</a></shiro:hasPermission>
		</div>
		<div class="newSearchBox">
			<form:form id="searchForm"  modelAttribute="pdStoreroom" action="${ctx}/pd/pdStoreroom/" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<label style="width:70px;">库房编号：</label>
				<form:input path="storeroomCode" htmlEscape="false" maxlength="64" class="input-medium"/>
				<label style="width:70px;">库房名称：</label>
				<%--<form:input path="storeroomName" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
				<form:select path="storeroomName" class="input-medium">
					<form:option value="" label="全部(qb)"/>
					<form:options items="${fns:findStoreroomList()}" itemLabel="storeroomName" itemValue="storeroomName" htmlEscape="false"/>
				</form:select>
				<input id="btnSubmit" onclick="return page();" class="hcy-btn hcy-search" style="height:inherit;line-height:1.5 ;" type="submit" value="查询"/></li>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>库房编号</th>
						<th>库房名称</th>
						<th>库房分类</th>
						<th>库房类型</th>
						<shiro:hasPermission name="pd:pdStoreroom:edit"><th>操作</th></shiro:hasPermission>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="pdStoreroom">
					<tr>
						<td><a href="${ctx}/pd/pdStoreroom/form?id=${pdStoreroom.id}">
							${pdStoreroom.storeroomCode}
						</a></td>
						<td>
							${pdStoreroom.storeroomName}
						</td>
						<td>
							${fns:getDictLabel(pdStoreroom.storeroomClass, 'storeroom_class', '')}
						</td>
						<td>
							<c:if test="${pdStoreroom.storeroomType == '0'}">
								一级库房
							</c:if>
							<c:if test="${pdStoreroom.storeroomType == '1'}">
								二级库房
							</c:if>
						</td>
						<shiro:hasPermission name="pd:pdStoreroom:edit"><td>
		    				<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/pdStoreroom/form?id=${pdStoreroom.id}">修改</a>
							<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/pdStoreroom/delete?id=${pdStoreroom.id}" onclick="return confirmx('确认要删除该库房信息吗？', this.href)">删除</a>
						</td></shiro:hasPermission>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
	</div>
</body>
</html>