<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出库列表</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
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
			<form:form id="searchForm" modelAttribute="pdStockRecord" action="${ctx}/pd/pdStockRecord/outListBox" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<form:hidden path="inId"/>
				<form:hidden path="recordState"/>
				<form:hidden path="outType"/>
				<form:hidden path="returnState"/>
				<div
					><label>出库单号</label>
					<form:input path="recordNo" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<div>
					<label>出库日期</label>
					<form:input path="queryDate" htmlEscape="false" id="recordDate" style="width:160px;" readonly="readonly" maxlength="64" class="input-medium"/>
				</div>	
				<input id="btnSubmit" class="hcy-btn hcy-search" style="height: inherit;line-height:1.5;" type="submit" value="查询"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
		<table id="contentTable" class="hcy-public-table" style="padding:0;">
			<thead>
				<tr>
					<th>选择</th>
					<th>出库单号</th>
					<th>出库日期</th>
					<th>出库库房 </th>
					<th>入库库房 </th>
					<th>出库类型 </th>
					<th>操作人</th>
					<th>备注 </th>
					<th>状态 </th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="pdStockRecord">
				<tr>
					<td><input type="radio" name="subBox" data-order-no="${pdStockRecord.recordNo}"/><input type="hidden" id="inId" value="${pdStockRecord.inId}"/></td>
					<td>
						<a  title="${pdStockRecord.recordNo}" href="${ctx}/pd/pdStockRecord/formOut?id=${pdStockRecord.id}&formType=1">${pdStockRecord.recordNo}</a>
					</td>
					<td>
						<fmt:formatDate value="${pdStockRecord.recordDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						${spd:getStoreroomName(pdStockRecord.outId)}
					</td>
					<td>
						<a class="overLook" title="${spd:getStoreroomName(pdStockRecord.inId)}" href="###">${spd:getStoreroomName(pdStockRecord.inId)}</a>
					</td>
					<td>
						${fns:getDictLabel(pdStockRecord.outType, 'stock_out_type', '')}
					</td>
					<td>
						${pdStockRecord.recordPeople}
					</td>
					<td>
						${pdStockRecord.remarks}
					</td>
					<td>
						${fns:getDictLabel(pdStockRecord.recordState, 'stock_record_state', '')}
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
		$(document).ready(function() {
			//日期范围			
			laydate.render({
				elem: '#recordDate',
				range: true,
				done: function(value, date, endDate){
					var dateArr = value.split(" - ");
					$("#beginDate").val(dateArr[0]);
					$("#endDate").val(dateArr[1]);
				}
			});
		});
		
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
	</script>
</body>
</html>