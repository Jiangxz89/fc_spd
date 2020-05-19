<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>应用标识符管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
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
			<h4>应用标识符管理</h4>
			<shiro:hasPermission name="pd:pdEncodingIdentifier:edit"><a class="hcy-btn hcy-btn-primary" href="${ctx}/pd/pdEncodingIdentifier/form">应用标识符添加</a></shiro:hasPermission>
		</div>
		<div class="searchBox">
			<form:form id="searchForm"  modelAttribute="pdEncodingIdentifier" action="${ctx}/pd/pdEncodingIdentifier/" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<label style="width:130px;">应用标识符值：</label>
				<form:input path="value" htmlEscape="false" maxlength="64" class="input-medium"/>
				<input id="btnSubmit" class="hcy-btn hcy-search" style="height:inherit;line-height:1.5 ;" type="submit" value="查询"/></li>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>值</th>
						<th>含义</th>
						<th>类型</th>
						<th>长度</th>
                        <th>备注</th>
						<shiro:hasPermission name="pd:pdEncodingIdentifier:edit"><th>操作</th></shiro:hasPermission>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="pdEncodingIdentifier">
					<tr>
						<td><a href="${ctx}/pd/pdEncodingIdentifier/form?id=${pdEncodingIdentifier.id}">
							${pdEncodingIdentifier.value}
						</a></td>
						<td>
							${pdEncodingIdentifier.meaning}
						</td>
						<td>
							${fns:getDictLabel(pdEncodingIdentifier.type, 'identifier_type', '')}
						</td>
						<td>${pdEncodingIdentifier.length}
						</td>
                        <td><a href="###" title="${pdEncodingIdentifier.remarks}" class="overLook">${pdEncodingIdentifier.remarks}</a></td>
						<shiro:hasPermission name="pd:pdEncodingIdentifier:edit"><td>
		    				<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/pdEncodingIdentifier/form?id=${pdEncodingIdentifier.id}">修改</a>
							<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/pdEncodingIdentifier/delete?id=${pdEncodingIdentifier.id}" onclick="return confirmx('确认要删除该应用标识符吗？', this.href)">删除</a>
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