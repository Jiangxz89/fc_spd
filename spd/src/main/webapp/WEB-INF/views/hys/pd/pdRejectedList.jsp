<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>退货管理</title>
	<meta charset="UTF-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
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
		<h4>退货管理</h4>
		<a href="${ctx }/pd/pdRejected/form" class="hcy-btn hcy-btn-primary">新增退货</a>
	</div>
	<div class="searchBox">
		<form:form id="searchForm" modelAttribute="pdRejected" method="post" action="${ctx}/pd/pdRejected/list" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<label for="">退货单号</label>
			<form:input path="rejectedNo" htmlEscape="false" maxlength="30" class="input-medium"/>
			<label for="">退货日期</label>
			<form:input path="queryDate" htmlEscape="false" id="queryDate" style="width:160px;" readonly="readonly" maxlength="64" class="input-medium"/>
			<input id="btnSubmit" class="hcy-btn hcy-search" style="height:inherit;line-height:1.5 ;" type="submit" value="查询"/>
		</form:form>
	</div>
	<div class="tableBox">
		<table class="hcy-public-table">
			<thead>
				<tr>
					<th>退货单号</th>
					<th>退货日期</th>
					<th>退货库房</th>
					<th>供应商</th>
					<th>操作人员</th>
					<th>操作时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="pdRejected">
					<tr>
						<td>${pdRejected.rejectedNo }</td>
						<td>${pdRejected.rejectedDate }</td>
						<td>${pdRejected.storeroomName }</td>
						<td>${pdRejected.supplierName }</td>
						<td>${pdRejected.operPerson }</td>
						<td>
							<fmt:formatDate value="${pdRejected.createDate }" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
							<a href="${ctx }/pd/pdRejected/detail?id=${pdRejected.id}" class="hcy-btn hcy-btn-o">查看</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="pagination">${page}</div>
<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
<script src="${ctxStatic}/spd/js/layer.js"></script>
<script type="text/javascript">
	$(function(){
		//日期控件
		laydate.render({
			elem: '#queryDate',
			range: true
		});		
	})
	
</script>
</body>
</html>