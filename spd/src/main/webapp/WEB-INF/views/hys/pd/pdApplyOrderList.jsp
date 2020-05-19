<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<title>领用管理</title>
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
			<h4>领用管理</h4>
			<c:if test="${fns:getUser().storeroomType=='1'}">
				<a href="${ctx}/pd/pdApplyOrder/form" class="hcy-btn hcy-btn-primary">申请领用</a>
			</c:if>
		</div>
		<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdApplyOrder" action="${ctx}/pd/pdApplyOrder/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="searchBox">
				<div>
					<label for="">申领单号</label>
					<form:input style="width:165px;" autocomplete="off" path="applyNo" />
				</div>
				<div>
					<label for="">状态</label>
					<form:hidden path="multiFlag" value="multi"/>
					<form:select path="applyStatus" id="">
						<form:option value="">全部</form:option>
						<form:options items="${fns:getDictList('apply_status') }" itemLabel="label" itemValue="value"/>
						<!-- <option value="">完结</option>
						<option value="">未完结</option> -->
					</form:select>
				</div>
				<div>
				<label for="">单据类型</label>
					<select name="" id="">
						<option value="">全部</option>
						<option value="">科室申领</option>
						<option value="">终端自动补货</option>
						<option value="">终端手动补货</option>
					</select>
				</div>
				<div>
					<label for="">申领日期</label>
					<input type="text" autocomplete="off" name="queryDate" id="receiveTime" style="width:160px;" value="${pdApplyOrder.queryDate }"/>
				</div>
				<%--<a href="###" class="hcy-btn hcy-search" onclick="queryData();">查询</a>--%>
				<input type="submit"  onclick="return page();" id="queryButton" class="hcy-btn hcy-search" style="line-height:1.5;height: inherit;" value="查询" />
			</div>
		</form:form>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>申领单号</th>
						<th>申领日期</th>
						<th>申领科室</th>
						<th>申领数量</th>
						<th>实际领用数量</th>
						<th>状态</th>
						<th>单据类型</th>
						<th>操作人员</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="li">
						<tr>
							<td>
								<a title="${li.applyNo}" href="${ctx}/pd/pdApplyDetail/form?applyNo=${li.applyNo}">${li.applyNo }</a>
							</td>
							<td><fmt:formatDate value="${li.applyDate }" pattern="yyyy-MM-dd"/></td>
							<td>${li.deptName }</td>
							<td>${li.applyCount }</td>
							<td>${li.factCount }</td>
							<td>${fns:getMyDictLabel(li.applyStatus, 'apply_status', '') }</td>
							<td>科室申领</td>
							<td>${li.applyName }</td>
							<td><a href="${ctx}/pd/pdApplyDetail/form?applyNo=${li.applyNo}" class="hcy-btn hcy-btn-o">查看</a></td>
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
				elem: '#receiveTime',
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