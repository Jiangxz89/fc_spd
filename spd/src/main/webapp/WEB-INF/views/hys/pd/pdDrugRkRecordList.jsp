<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品入库管理</title>
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
			<h4>药品入库管理</h4>
			<%-- <a href="${ctx }/pd/medstoYprkzd/form" class="hcy-btn hcy-btn-primary">申请采购</a> --%>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="medstoYprkzd" action="${ctx}/pd/medstoYprkzd/list" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>入库单号</label>
					<form:input path="rkdh" />
				</div>
				<div>
					<label>入库日期</label>
					<input type="text" name="queryDate" id="purchaseDate" style="width:160px;" value="${medstoYprkzd.queryDate }"/>
				</div>
				<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>序号</th>
						<th>入库单号</th>
						<th>入库日期</th>
						<th>入库科室</th>
						<th>入库人员</th>
						<th>零售金额</th>
						<th>批发金额</th>
						<th>进价金额</th>
						<th>优惠金额</th>
						<th>到票标志</th>
						<th>记账标志</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="li" varStatus="status">
						<tr>
							<td>${page.pageSize * (page.pageNo-1) + status.count }</td>
							<td>${li.rkdh }</td>
							<td>${li.rkrq }</td>
							<td>${li.ksdm }</td>
							<td>${li.rkczyh }</td>
							<td>${li.lsje }</td>
							<td>${li.pfje }</td>
							<td>${li.jjje }</td>
							<td>${li.yhje }</td>
							<td>${li.dpbz }</td>
							<td>${li.jzbz }</td>
							<td>${li.jlzt }</td>
							<td>
								<a class="hcy-btn hcy-btn-o" href="${ctx }/pd/medstoYprkmx/form?zdXh=${li.xh}">查看</a>
								<%-- <a class="hcy-btn hcy-btn-o" href="${ctx }/pd/medstoYprkzd/upload?id=${li.xh}">上传</a> --%>
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
			  elem: '#purchaseDate',
			  range: true
			});
			//重置
			$(".hcy-reset").click(function(){
				$(".searchBox div input[type='text']").val("");
				$(".searchBox div select").val("");
			})
		})
	</script>
</body>
</html>