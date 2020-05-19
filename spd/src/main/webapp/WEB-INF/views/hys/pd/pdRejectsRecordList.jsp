<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<title>不良品预登记管理</title>
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
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pd/pdRejectsRecord/">不良品记录列表</a></li>
		<shiro:hasPermission name="pd:pdRejectsRecord:edit"><li><a href="${ctx}/pd/pdRejectsRecord/form">不良品记录添加</a></li></shiro:hasPermission>
	</ul> --%>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>不良品预登记管理</h4>
			<a href="${ctx }/pd/pdStockLog" class="hcy-btn hcy-btn-primary">新增登记</a>
			<a href="###" class="hcy-btn hcy-btn-primary" id="exportBtn">导出Excel</a>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="pdRejectsRecord" action="${ctx}/pd/pdRejectsRecord/" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>产品编号</label>
					<form:input path="pdProduct.number" htmlEscape="false" maxlength="64" class="clearcondition"/>
				</div>
				<div>
					<label>产品名称</label>
					<form:input path="pdProduct.name" htmlEscape="false" maxlength="64"  class="clearcondition"/>
				</div>
				<div>
					<label>产品型号</label>
					<form:input path="pdProduct.version" htmlEscape="false" maxlength="64"  class="clearcondition"/>
				</div>
				<div>
					<label>产品规格</label>
					<form:input path="pdProduct.spec"  class="clearcondition"/>
				</div>
				<div>
					<label>住院号</label>
					<form:input path="inHospitalNo"  class="clearcondition"/>
				</div>
				<div>
					<label>严重等级</label>
					<form:select path="seriousGrade">
						<form:option value="">全部</form:option>
						<form:options items="${fns:getDictList('bad_prod_level') }" itemLabel="label" itemValue="value"/>
					</form:select>
				</div>
				<div>
					<label>登记日期</label>
					<input type="text" style="width:160px;" id="registerTime"  class="clearcondition" name="queryDate"/>
				</div>
				<a href="###" class="hcy-btn hcy-search" style="line-height: 1.5;margin-left:30px;" id="queryData">查询</a>
				<a href="###" class="hcy-btn hcy-reset" style="line-height: 1.5;" id="clearup">重置</a>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table">
				<thead>
					<tr>
						<th>登记日期</th>
						<th>产品名称</th>
						<th>产品编号</th>
						<th>规格</th>
						<th>型号</th>
						<th>批号</th>
						<th>住院号</th>
						<th>严重等级</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="li">
						<tr>
							<td><fmt:formatDate value="${li.createDate }" pattern="yyyy-MM-dd"/></td>
							<td>${li.pdProduct.name }</td>
							<td>${li.pdProduct.number }</td>
							<td>${li.pdProduct.spec }</td>
							<td>${li.pdProduct.version }</td>
							<td>${li.batchNo }</td>
							<td>${li.inHospitalNo }</td>
							<td>${fns:getDictLabel(li.seriousGrade,'bad_prod_level','') }</td>
							<td>
								<a href="${ctx}/pd/pdRejectsRecord/form?id=${li.id}" class="hcy-btn hcy-btn-o">查看</a>
								<a href="${ctx}/pd/pdRejectsRecord/form?id=${li.id}&oprtType=cleanyou" class="hcy-btn hcy-btn-o">修改</a>
								<a href="javascript:;" onclick="killIt('${ctx}/pd/pdRejectsRecord/delete?id=${li.id}')" class="hcy-btn hcy-btn-o">删除</a>
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
			//登记日期
			laydate.render({
				elem: '#registerTime',
				range: true
			});
			//查询
			$('#queryData').click(function(){
				$("#searchForm").submit();
				return false;
			});
			//清空
			$('#clearup').click(function(){
				$('.clearcondition').val('');
				$("#seriousGrade").val('');
			});
			
			//导出数据
			$('#exportBtn').click(function(){
				var form = $('<form>');
				form.attr('style', 'display:none');
				form.attr('method', 'post');
				form.attr('action', '${ctx}/excelExport/rejectsProductRecord');
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
		function killIt(url){
			layer.confirm('确认删除?', {icon: 3, title:'提示'}, function(index){
				  //do something
				  location.href = url;
				  layer.close(index);
			}); 
		}
	</script>
</body>
</html>