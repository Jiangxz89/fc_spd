<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<title>调拨管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

		});
	</script>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>调拨管理</h4>
			<a href="${ctx}/pd/pdAllocationRecord/form" class="hcy-btn hcy-btn-primary">申请调拨</a>
		</div>
		<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdAllocationRecord" action="${ctx}/pd/pdAllocationRecord/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox">
				<label for="">入库库房</label>
				<form:input path="inId" htmlEscape="false" value="${fns:getUser().storeroomName}" readOnly="true" maxlength="64" class="input-medium"/>
				<input id="inId" name="inId" type="hidden" value="${fns:getUser().storeroomId}"/>
				<label>调拨单号</label>
				<form:input path="allocationCode" autocomplete="off" htmlEscape="false" maxlength="64" class="input-medium"/>
				<label for="">调拨日期</label>
				<form:input path="queryDate" autocomplete="off"  htmlEscape="false" id="allotTime" readonly="readonly" maxlength="64" class="input-medium"/>
				<%--<div>
					<label>产品名称</label>
					<form:input path="productName" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>--%>
				</br>
				<!-- <label for="">产品名称</label>
				<input type="text" name="" value=""/> -->
				<label for="">状态</label>
				<select name="recordState" class="input-medium" id="recordState">
					<option value="">全部</option>
					<c:forEach var="dict" items="${fns:getDictList('apply_status') }">
						<option value="${dict.value }" <c:if test="${pdAllocationRecord.recordState eq dict.value}">selected</c:if>>${dict.label }</option>
					</c:forEach>
				</select>
				<label style="margin-left:5px;" for="">出库库房</label>
				<select name="outId" class="input-medium" id="outId">
					<option value="">全部</option>
					<c:forEach var="storeroom" items="${storeroomList }">
						<c:if test="${storeroom.id!=fns:getUser().storeroomId}">
							<option value="${storeroom.id }" <c:if test="${pdAllocationRecord.outId eq storeroom.id}">selected</c:if>>${storeroom.storeroomName }</option>
						</c:if>
					</c:forEach>
				</select>
				<label style="margin-left:5px;" for="">操作人员</label>
				<select name="recordPeople" class="input-medium" id="recordPeople">
					<option value="">全部</option>
					<c:forEach var="storeroomAdmin" items="${storeroomAdminList }">
						<option value="${storeroomAdmin.adminName }" <c:if test="${pdAllocationRecord.recordPeople eq storeroomAdmin.adminName}">selected</c:if>>${storeroomAdmin.adminName }</option>
					</c:forEach>
				</select>
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<input type="submit" onclick="return page();" id="queryButton" class="hcy-btn hcy-search" style="line-height:1.5;height: inherit;" value="查询" />
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</div>
		</form:form>
		<div class="tabBox">
			<table class="hcy-public-table" style="padding:0">
				<thead>
					<tr>
						<th>调拨单号</th>
						<th>调拨日期</th>
						<th>出库库房</th>
						<th>入库库房</th>
						<th>操作人员</th>
						<th>备注</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="allocationRecord">
						<tr>
							<td>
								<a title="${allocationRecord.allocationCode}" href="${ctx}/pd/pdAllocationRecord/toDetail?id=${allocationRecord.id}">${allocationRecord.allocationCode }</a>
							</td>
							<td><fmt:formatDate value="${allocationRecord.allocationDate }" pattern="yyyy-MM-dd"/></td>
							<td>${allocationRecord.outName }</td>
							<td>${allocationRecord.inName }</td>
							<td>${allocationRecord.recordPeople }</td>
							<td>
								<a href="###" class="overLook" title="${allocationRecord.remarks}">${allocationRecord.remarks}</a>
							</td>
							<td>${fns:getMyDictLabel(allocationRecord.recordState, 'apply_status', '') }</td>
							<td>
								<a href="${ctx}/pd/pdAllocationRecord/toDetail?id=${allocationRecord.id}" class="hcy-btn hcy-btn-o">查看</a>
								<c:if test="${allocationRecord.recordState eq '2'}">
									<a href="${ctx}/pd/pdAllocationRecord/allocationRecordModify?id=${allocationRecord.id}" class="hcy-btn hcy-btn-o">修改</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination">${page}</div>
		</div>
	</div>
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//调拨日期
			laydate.render({
				elem: '#allotTime',
				range: true,
				done: function(value, date, endDate){
					/* var dateArr = value.split(" - ");
					$("#beginDate").val(dateArr[0]);
					$("#endDate").val(dateArr[1]); */
				}
			});
			/*//重置
			$(".hcy-reset").click(function(){
				var sel ="${fns:getUser().storeroomId}";//默认下拉
				var inp ="${fns:getUser().storeroomName}";//默认input
				$(".searchBox div input[type='text']").val("");
				$(".searchBox div select").val("");
				$("#inId").val(inp);
				/!*$(".searchBox div select[name='inId']").val(sel);*!/
			})*/

            //重置
            $(".hcy-reset").click(function(){
                $(".newSearchBox input[type='text']").val("");
                var inp ="${fns:getUser().storeroomName}";//默认input
                $("#inId").val(inp);
              /*  $("#productNameSelect").val("")
                $("#productNameSelect").select2("data", "");*/
                $("#recordState").select2("val", "");
                $("#outId").select2("val", "");
                $("#recordPeople").select2("val", "");
            })
		})
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</body>
</html>