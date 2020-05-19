<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品管理</title>
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
			<h4>药品退货管理</h4>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="medstoYpthzd" action="${ctx}/pd/medstoYpthzd/" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>退货单号</label>
					<form:input path="djh" />
				</div>
				<div>
					<label>退货日期</label>
					<input type="text" name="queryDate" id="refundDate" style="width:160px;" value="${medstoYpthzd.queryDate }"/>
				</div>
				<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>退货单号</th>
						<th>退货日期</th>
						<th>退货库房</th>
						<th>供应商</th>
						<th>操作人员</th>
						<th>操作时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="drug">
						<tr>
							<td>${drug.djh }</td>
							<td>${drug.lrrq }</td>
							<td>${drug.ksdm }</td>
							<td>${drug.ghdwmc }</td>
							<td>${drug.czyh }</td>
							<td>${drug.jzrq }</td>
							<td>${drug.jlzt }</td>
							<td>
								<a class="hcy-btn hcy-btn-o" href="${ctx }/pd/medstoYpthmx/form?zdXh=${drug.xh }">查看</a>
								<%-- <a class="hcy-btn hcy-btn-o" href="${ctx }/pd/medstoYpthzd/upload?id=${drug.xh }">上传</a> --%>
							</td>
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
		$(function(){
			//日期范围
			laydate.render({
			  elem: '#refundDate',
			  range: true
			});
		})
	</script>
</body>
</html>