<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>编码规则管理</title>
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
			<h4>编码规则管理</h4>
			<shiro:hasPermission name="pd:pdEncodingRule:edit"><a class="hcy-btn hcy-btn-primary" href="${ctx}/pd/pdEncodingRule/form?flag=add">编码规则添加</a></shiro:hasPermission>
		</div>
		<div class="searchBox">
			<form:form id="searchForm"  modelAttribute="pdEncodingRule" action="${ctx}/pd/pdEncodingRule/" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<label style="width:130px;">编码名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
				<input id="btnSubmit" onclick="return page();" class="hcy-btn hcy-search" style="height:inherit;line-height:1.5 ;" type="submit" value="查询"/></li>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>名称</th>
						<th>规则描述</th>
						<th>总位数</th>
                        <th>备注</th>
						<shiro:hasPermission name="pd:pdEncodingRule:edit"><th>操作</th></shiro:hasPermission>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="pdEncodingRule">
					<tr>
						<td><a href="${ctx}/pd/pdEncodingRule/form?id=${pdEncodingRule.id}">
							${pdEncodingRule.name}
						</a></td>
						<td>
							${pdEncodingRule.codeDesc}
						</td>
						<td>
							${pdEncodingRule.totalDigit}
						</td>
                        <td><a href="###" title="${pdEncodingRule.remarks}" class="overLook">${pdEncodingRule.remarks}</a></td>
						<shiro:hasPermission name="pd:pdEncodingRule:edit"><td>
							<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/pdEncodingRule/toUpdate?id=${pdEncodingRule.id}&flag=see">查看</a>
		    				<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/pdEncodingRule/toUpdate?id=${pdEncodingRule.id}&flag=update">修改</a>
							<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/pdEncodingRule/delete?id=${pdEncodingRule.id}" onclick="return confirmx('确认要删除该编码规则吗？', this.href)">删除</a>
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