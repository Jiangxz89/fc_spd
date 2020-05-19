<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品采购管理</title>
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
		resetTip();
	</script>
</head>
<body>
	<div class="right-main-box"> 
		<div class="btnBox">
			<h4>药品采购</h4>
			<a href="${ctx }/pd/medstoYpcgzd/form" class="hcy-btn hcy-btn-primary">申请采购</a>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="medstoYpcgzd" action="${ctx}/pd/medstoYpcgzd/" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>申购单号</label>
					<form:input path="djh" />
				</div>
				<div>
					<label>申购日期</label>
					<input type="text" name="queryDate" id="purchaseDate" style="width:160px;" value="${medstoYpcgzd.queryDate }"/>
				</div>
				<div>
					<label>申购科室</label>
					<form:select path="ksdm">
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
				<div>
					<label>申购人员</label>
					<c:choose>
						<c:when test="${fns:getUser().storeroomType == '1' }">
							<form:select path="czyh">
								<c:set var="kufangId" value="${fns:getUser().storeroomId }"/>
								<form:option value="">全部</form:option>
								<form:options items="${spd:findRoomAdmins(kufangId) }" itemValue="adminId" itemLabel="adminName"/>
							</form:select>
						</c:when>
						<c:when test="${medstoYpcgzd.ksdm != null && medstoYpcgzd.ksdm != '' && medstoYpcgzd.ksdm !=fns:getUser().storeroomId}">
							<form:select path="czyh">
								<c:set var="kufangId" value="${medstoYpcgzd.ksdm}"/>
								<form:option value="">全部</form:option>
								<form:options items="${spd:findRoomAdmins(kufangId) }" itemValue="adminId" itemLabel="adminName"/>
							</form:select>
						</c:when>
						<c:otherwise>
							<form:select path="czyh">
								<form:option value="">全部</form:option>
								<form:options items="${spd:findRoomAdminAll() }" itemValue="adminId" itemLabel="adminName"/>
							</form:select>
						</c:otherwise>
					</c:choose>
				</div>
				<div>
					<label>记录状态</label>
					<form:select path="jlzt">
						<form:option value="">全部</form:option>
						<form:options items="${fns:getDictList('drug_order_status') }" itemValue="value" itemLabel="label"/>
					</form:select>
				</div>
				<div>
					<label>记账标志</label>
					<form:select path="jzbz">
						<form:option value="">全部</form:option>
						<%-- <form:options items="${spd:findRoomAdminAll() }" itemValue="adminId" itemLabel="adminName"/> --%>
					</form:select>
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
						<th>申购单号</th>
						<th>申购日期</th>
						<th>申购科室</th>
						<th>申购人员 </th>
						<th>记录状态 </th>
						<th>记账标志 </th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="li">
						<tr>
							<td>${li.xh }</td>
							<td>${li.djh }</td>
							<td>${li.cjrq }</td>
							<td>${spd:getStoreroomName(li.ksdm) }</td>
							<td>${spd:getUserNameById(li.czyh) }</td>
							<td>${fns:getDictLabel(li.jlzt, 'drug_order_status', '') }</td>
							<td>${li.jzbz }</td>
							<td>
								<a class="hcy-btn hcy-btn-o" href="${ctx }/pd/medstoYpcgmx/list?cgxh=${li.xh}">查看</a>
								<c:if test="${li.status ne 1 }">
									<a class="hcy-btn hcy-btn-o" href="${ctx }/pd/medstoYpcgzd/syncData?id=${li.xh}">上传</a>
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
			
		});
		
	</script>
</body>
</html>