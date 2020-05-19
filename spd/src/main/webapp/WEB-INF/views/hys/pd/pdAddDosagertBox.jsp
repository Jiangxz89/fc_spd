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
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="pdDosagert" action="${ctx}/pd/pdDosagert/pdAddDosagertBox" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>退回单号</label>
					<form:input path="dosagertNo" htmlEscape="false" maxlength="64" class="input-medium"  />
				</div>
				<div>
					<label>退回日期</label>
					<input name="dosagertDate" style="width:160px;" type="text" readonly="readonly" maxlength="20" id="inHomeTime" class="input-medium Wdate" value="<fmt:formatDate value="${pdStockRecord.recordDate}" />"/>
				</div>
				<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th style="width:60px;">选择</th>
						<th style="width:160px">用量退单号</th>
						<th>申请日期</th>
						<th>申请科室</th>
						<th>操作人员</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="pdDosagert">
					<tr>
						<td>
							<input type="hidden" id="dosagertId" value="${pdDosagert.id}"/>
							<input type="radio" name="subBox" data-order-no="${pdDosagert.dosagertNo}"/>
							<input type="hidden" id="inId" value="${pdDosagert.dosagertInroomId}"/>
						</td>
						<td>${pdDosagert.dosagertNo}</td>
						<td>
							<fmt:formatDate value="${pdDosagert.dosagertDate}" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							${spd:getStoreroomName(pdDosagert.dosagertRoomId)}
						</td>
						<td>
							${pdDosagert.operaterName}
						</td>					
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<script type="text/javascript">
	//选择已经选中的数据
	function compositeHtml(){
		var orderNo = null;
		$("input[name=subBox]:checked").each(function() {
			orderNo = $(this).attr("data-order-no"); //订单号
		})
		return orderNo;
	}
	function compositeInId(){
		var inId = $("#inId").val();			
		return inId;
	}
	function compositeDosagertId(){
		var dosagertId = $("#dosagertId").val();			
		return dosagertId;
	}
	</script>
</body>
</html>