<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<title>器械使用管理</title>
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
			<h4>器械使用管理</h4>
			<a href="${ctx}/pd/pdDosage/form" class="hcy-btn hcy-btn-primary">新增用量</a>
		</div>
		<form:form id="searchForm" style="padding:0;" modelAttribute="pdDosage" action="${ctx}/pd/pdDosage/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="searchBox">
				<div>
					<label for="">用量单号</label>
					<form:input autocomplete="off" style="width:165px;" type="text" path="dosageNo"/>
				</div>
				<div>
					<label for="">用量日期</label>
					<input type="text" autocomplete="off" id="useTime" style="width:160px;" name="queryDate" value="${pdDosage.queryDate }"/>
				</div>
				<div>
					<label for="">用量库房</label>
					<form:select path="warehouseId">
						<c:if test="${fns:getUser().storeroomType == '0' }">
							<form:option value="">请选择</form:option>
							<c:forEach items="${spd:findStoreroomList()}" var="li">
								<form:option value="${li.id }">${li.storeroomName }</form:option>
							</c:forEach>
						</c:if>
						<c:if test="${fns:getUser().storeroomType == '1' }">
							<form:option value="${fns:getUser().storeroomId  }">${fns:getUser().storeroomName  }</form:option>
						</c:if>
					</form:select>
				</div>
				<%--<a href="###" class="hcy-btn hcy-search" onclick="queryData();">查询</a>--%>
				<input type="submit"  onclick="return page();" id="queryButton" class="hcy-btn hcy-search" style="line-height:1.5;height: inherit;" value="查询" />
			</div>
		</form:form>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>用量单号</th>
						<th>用量日期</th>
						<th>用量库房</th>
						<th>用量数量</th>
						<th>病人信息</th>
						<th>操作人员</th>
						<th>操作时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="li">
						<tr>
							<td><a href="${ctx}/pd/pdDosageDetail/form?dosageNo=${li.dosageNo}">
									${li.dosageNo }
							</a></td>
							<td><fmt:formatDate value="${li.dosageDate }" pattern="yyyy-MM-dd"/></td>
							<td>${li.warehouseName }</td>
							<td>${li.amountCount }</td>
							<td>${li.patientInfo }</td>
							<td>${li.dosageOperator }</td>
							<td><fmt:formatDate value="${li.updateDate }" pattern="yyyy-MM-dd"/></td>
							<td>
								<a href="${ctx}/pd/pdDosageDetail/form?dosageNo=${li.dosageNo}" class="hcy-btn hcy-btn-o">查看</a>
								<c:if test="${displayFlag == '1' }">
									<a href="${ctx}/pd/pdDosageDetail/inChargeForm?dosageNo=${li.dosageNo}" class="hcy-btn hcy-btn-o">收费</a>
									<a href="${ctx}/pd/pdDosageDetail/offChargeForm?dosageNo=${li.dosageNo}" class="hcy-btn hcy-btn-o">取消收费</a>
									<a href="${ctx}/pd/pdDosageDetail/dosageRtList?dosageNo=${li.dosageNo}" class="hcy-btn hcy-btn-o">库存还回</a>
								</c:if>

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
			//申购时间
			laydate.render({
				elem: '#useTime',
				range: true
			});
		});
		//查询
		function queryData(){
			$("#searchForm").submit();
			return false;
		}
		
	</script>
</body>
</html>