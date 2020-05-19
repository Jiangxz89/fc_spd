<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡查详情</title>
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
			<h4>巡查详情</h4>
			<a href="###" class="hcy-btn hcy-btn-primary" id="exportBtn">导出Excel</a>
			<a href="###" class="hcy-btn hcy-btn-primary">打印</a>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="pdStoreroomPatrolRecord" action="${ctx}/pd/pdStoreroomPatrolRecord/list?patrolCode=${pdStoreroomPatrol.patrolCode }" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>库存日期</label>
					<input name="patrolDate" value='<fmt:formatDate value="${pdStoreroomPatrol.patrolDate}" pattern="yyyy-MM-dd"/>' disabled="true" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<div>
					<label>库房</label>
					<input name="storeroomName" value="${spd:getStoreroomName(pdStoreroomPatrol.storeroomId) }" disabled="true" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<div>
					<label>温度</label>
					<input type="text" value="${pdStoreroomPatrol.temperature }" disabled="true" maxlength="20"  class="input-medium Wdate" value=""/>℃
				</div>
				<div>
					<label>湿度</label>
					<input name="wetness" value="${pdStoreroomPatrol.wetness }" disabled="true" htmlEscape="false" maxlength="64" class="input-medium"/>%
				</div>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table">
				<thead>
					<tr>
						<th>库房名称</th>
						<th>产品名称</th>
						<th>产品编号 </th>
						<th>产品条码 </th>
						<th>规格</th>
						<th>型号</th>
						<th>批号 </th>
						<th>有效期 </th>
						<th>数量 </th>
						<th>单位</th>
						<th>生产厂家 </th>
						<th>注册证号</th>
						<th>是否过期 </th>
						<th>检查结果</th>
						<th>备注 </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="li">
						<tr>
							<td>${spd:getStoreroomName(pdStoreroomPatrol.storeroomId) }</td>
							<td>${li.pdProduct.name }</td>
							<td>${li.pdProduct.number }</td>
							<td>${li.productBarCode }</td>
							<td>${li.pdProduct.spec }</td>
							<td>${li.pdProduct.version }</td>
							<td>${li.batchNo }</td>
							<td><fmt:formatDate value="${li.validDate }" pattern="yyyy-MM-dd"/></td>
							<td>${li.stockNum }</td>
							<td>${li.pdProduct.unit }</td>
							<td>${li.pdProduct.venderName }</td>
							<td>${li.pdProduct.registration }</td>
							<td>
								<c:choose>
									<c:when test="${li.isExpire=='0' }">即将过期</c:when>
									<c:when test="${li.isExpire=='1' }">过期</c:when>
									<c:otherwise>--</c:otherwise>
								</c:choose>
							</td>
							<td>
								${fns:getDictLabel(li.patrolResult,'pd_storeroom_patrol_result','') }
							</td>
							<td>
								<c:if test="${empty li.remarks}">无</c:if>
								<c:if test="${not empty li.remarks}">${li.remarks }</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
		<%-- <div class="condotionBox">
			<label>温度</label><input type="text" name="temperature" id="temperature" value="${pdStoreroomPatrol.temperature }"/>℃
		</div>
		<div class="condotionBox">
			<label>湿度</label><input type="text" name="wetness" id="wetness" value="${pdStoreroomPatrol.wetness }"/>%
		</div> --%>
		<div class="form-actions" style="background:#fff;border:none;text-align:center;padding:0;margin-top:30px;">
			<input id="btnCancel" class="hcy-btn hcy-back" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//申购时间
			laydate.render({
				elem: '#inHomeTime',
				range: true
			});
			
			//导出数据
			$('#exportBtn').click(function(){
				var form = $('<form>');
				form.attr('style', 'display:none');
				form.attr('method', 'post');
				form.attr('action', '${ctx}/excelExport/patrolRecord');
				var input = $('<input>');
				input.attr('type', 'hidden');
				input.attr('name', 'exportData');
				input.attr('value', '${exportDataList}');
				form.append(input);
				$('body').append(form);
				form.submit();
				form.remove();
			});
		})
	</script>
</body>
</html>