<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用量退回</title>
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
	</script>
</head>
<body>
	<div class="right-main-box"> 
		<div class="btnBox">
			<h4>用量退回</h4>
			<%--<c:if test="${fns:getUser().storeroomId !=null}">--%>
				<%--<shiro:hasPermission name="pd:pdDosagert:edit"><a class="hcy-btn hcy-btn-primary" href="${ctx}/pd/pdDosagert/form">新增用量退回</a></shiro:hasPermission>--%>
			<%--</c:if>--%>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="pdDosagert" action="${ctx}/pd/pdDosagert/list" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>用量单号</label>
					<form:input path="dosageNumber" autocomplete="off" style="width:165px;" htmlEscape="false" maxlength="64" class="input-medium"  />
				</div>
				<div>
					<label>退回日期</label>
					<input name="dosagertDate" style="width:165px;" autocomplete="off" type="text" readonly="readonly" maxlength="20" id="customInp" class="input-medium Wdate" value="<fmt:formatDate value="${pdDosagert.dosagertDate}" />"/>
				</div>
				<input id="btnSubmit" onclick="return page();" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>用量单号</th>
						<th>退用量日期</th>
						<th>入库库房</th>
						<th>退用量数量</th>
						<th>操作人员</th>
						<shiro:hasPermission name="pd:pdDosagert:edit"><th>操作</th></shiro:hasPermission>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="pdDosagert">
					<tr>
						<td><a href="${ctx}/pd/pdDosagert/detail?id=${pdDosagert.id}">
								${pdDosagert.dosageNumber}
						</a>
						</td>
						<td>
							<fmt:formatDate value="${pdDosagert.dosagertDate}" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							${spd:getStoreroomName(pdDosagert.dosagertRoomId)}
						</td>
						<td>
							${pdDosagert.dosagertCount}
						</td>
						<td>
							${pdDosagert.operaterName}
						</td>
						<shiro:hasPermission name="pd:pdDosagert:view"><td>
							<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/pdDosagert/detail?id=${pdDosagert.id}">查看</a>
						</td></shiro:hasPermission>
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
	//日期控件
	laydate.render({
		elem: '#customInp'
	});
	</script>
</body>
</html>