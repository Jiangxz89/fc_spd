<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<title>采购订单详情</title>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox" style="border-bottom:1px solid #ccc;height:35px;">
			<h4>采购订单详情</h4>
		</div>
		<form:form id="inputForm" modelAttribute="pdPurchaseOrderMerge" action="${ctx}/pd/pdPurchaseOrderMerge/save" method="post" class="form-horizontal">
			<form:hidden path="id"/>
			<sys:message content="${message}"/>		
			<div class="tableBox">
				<table class="hcy-public-table">
					<thead>
						<tr>
							<th>申购单号</th>
							<th>申购日期</th>
							<th>申购科室</th>
							<th>状态</th>
							<th>申购人员</th>
							<th>备注</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderList }" var="li">
							<tr>
								<td>
									<a title="${li.orderNo}" href="${ctx}/pd/pdPurchaseDetail/form?orderNo=${li.orderNo}">${li.orderNo}</a>
								</td>
								<td><fmt:formatDate value="${li.orderDate }" pattern="yyyy-MM-dd"/></td>
								<td>${li.deptName }</td>
								<td>${fns:getDictLabel(li.orderStatus,'order_status','') }</td>
								<td>${li.purchaseName }</td>
								<td>${li.remarks }</td>
								<td><a href="${ctx}/pd/pdPurchaseDetail/form?orderNo=${li.orderNo}" class="hcy-btn hcy-btn-o">查看</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
      			<a href="${ctx }/pd/pdPurchaseOrderMerge" class="hcy-btn hcy-back">返回</a>
      		</div>
		</form:form>
	</div>
</body>
</html>