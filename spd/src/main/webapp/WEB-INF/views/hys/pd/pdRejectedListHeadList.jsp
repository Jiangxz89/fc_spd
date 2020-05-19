<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>退货管理</title>
	<meta charset="UTF-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
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
				<h4>退货管理</h4>
				<a href="${ctx }/pd/pdRejectedListHead/form" class="hcy-btn hcy-btn-primary">新增退货</a>
			</div>
			<div class="searchBox">
				<form:form id="searchForm" modelAttribute="pdRejectedListHead" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<label for="">退货单号</label>
					<input type="text" style="width:165px;" name="number"  autocomplete="off"  value="${pdRejectedListHead.number}"/>
					<label for="">退货日期</label>
					<input type="text" id="customInp" style="width:260px;" autocomplete="off" value="${pdRejectedListHead.rejectedDate}"/>
							<input type="hidden" id="beginDate" name="beginDate" />
							<input type="hidden" id="endDate" name="endDate" value=""/>
					<input id="btnSubmit" onclick="sub()" class="hcy-btn hcy-search" style="height:inherit;line-height:1.5 ;"  type="submit" value="查询"/>
				</form:form>
			</div>
			<sys:message content="${message}"/>
			<div class="tableBox">
				<table class="hcy-public-table">
					<thead>
						<tr>
							<th>退货单号</th>
							<th>退货日期</th>
							<th>退货库房</th>
							<th>供应商</th> <%--add by jiangxz 20190925--%>
							<th>操作人员</th>
							<th>操作时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list}" var="pdRejectedListHead">
							<tr>
								<td>
									<a title="${pdRejectedListHead.number}" href="${ctx }/pd/pdRejectedListHead/detail?id=${pdRejectedListHead.id}">${pdRejectedListHead.number }</a>
								</td>
								<td>${pdRejectedListHead.rejectedDate }</td>
								<td>${pdRejectedListHead.rejectedRepoName }</td>
								<td>${spd:getSupplierName(pdRejectedListHead.supplierId) }</td>  <%--add by jiangxz 20190925--%>
								<td>${pdRejectedListHead.operPerson }</td>
								<td>
									<fmt:formatDate value="${pdRejectedListHead.createDate }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<a href="${ctx }/pd/pdRejectedListHead/detail?id=${pdRejectedListHead.id}" class="hcy-btn hcy-btn-o">查看</a>
									<!-- <a href="${ctx }/pd/pdRejectedListHead/delete?id=${pdRejectedListHead.id}" onclick="return confirmx('确认要删除该产品吗？', this.href)" class="hcy-btn hcy-btn-o removeProBtn">删除</a> -->
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	<div class="pagination">${page}</div>
	
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//日期控件
			laydate.render({
				elem: '#customInp',
				range: true,
				done: function(value, date, endDate){
					var dateArr = value.split(" - ");
					$("#beginDate").val(dateArr[0]);
					$("#endDate").val(dateArr[1]);
				}
			});
			
		})
		
		function sub(){
            $("#pageNo").val("");
            $("#pageSize").val("");
			var date = $("#customInp").val();
			$("#searchForm").attr("action","${ctx}/pd/pdRejectedListHead/list?date="+date);
			return true;
		}
	</script>
</body>
</html>