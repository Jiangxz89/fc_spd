<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>his采购订单管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<style>
		.searchBox label{width: 85px;text-align: left;margin:0;}
	</style>
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
			<h4>HIS药品采购订单</h4>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="hisPurchaseOrder" action="${ctx}/pd/hisPurchaseOrder/list" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>机构编码</label>
					<form:input path="jgbm" htmlEscape="false"  maxlength="64" class="input-medium"/>
				</div>
				<div>
					<label>采购单号</label>
					<form:input path="purNo" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<input id="btnSubmit" onclick="querydata()"  style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search"  type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>


		<div class="showTableBox" style="width:100%;overflow:auto;">
			<div class="tableBox">
				<table id="contentTable" class="hcy-public-table" style="padding:0;width: 100%;">
					<thead>
						<tr>
							<th>机构编码</th>
							<th>采购单号</th>
							<th>采购日期</th>
							<th>经手人</th>
							<th>审核人</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.list}" var="hisPurchaseOrder">
						<tr>
							<td>${hisPurchaseOrder.jgbm}</td>
							<td>${hisPurchaseOrder.purNo}</td>
							<td>${hisPurchaseOrder.purDate}</td>
							<td>${hisPurchaseOrder.operName}</td>
							<td>${hisPurchaseOrder.checkName}</td>
							<td>
								<%--<a href="javascript:queryItem('+${hisPurchaseOrder.id}+');" style="color: #0f93ce">详情</a>--%>
								<a href="${ctx}/pd/hisPurchaseOrderItem/list?pId=${hisPurchaseOrder.id}" style="color: #0f93ce">详情</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//重置
			$(".hcy-reset").click(function(){
				$(".searchBox div input[type='text']").val("");
			});
		})
		//查询加遮罩
		function querydata(){
			var index = loading('正在查询，请稍等...');
			return true;
		};

		function queryItem(pId){
			layer.open({
				type:2,
				title:"采购单明细",
				// content:$(".addChargeCodeBox").html(),
				content:'${ctx}/pd/hisPurchaseOrderItem/list?pId='+pId,
				area:["90%","600px"],
				shade: [0.8, '#393D49'],
				btn:["返回"],
				yes:function(index,layero){
					layer.closeAll();
				}
			})
		}
	</script>
</body>
</html>