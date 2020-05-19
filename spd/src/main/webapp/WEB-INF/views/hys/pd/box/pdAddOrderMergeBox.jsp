<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<title>采购订单查询</title>
	<style type="text/css">

	</style>
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
	<div class="newSearchBox">
		<form:form id="searchForm" modelAttribute="pdPurchaseOrderMerge" action="${ctx}/pd/pdPurchaseOrderMerge/chooseOrderList" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<form:hidden path="orderStatus"/>
			<form:hidden path="deptId"/>
			<label>申购单号</label>
			<form:input path="orderNo" autocomplete="off" htmlEscape="false" maxlength="64" class="input-medium"/>
			<label>申购日期</label>
			<form:input path="queryDate" autocomplete="off" htmlEscape="false" id="inHomeTime" style="width:160px;" readonly="readonly" maxlength="64" class="input-medium" />
			</br>
			<label for="">供应商</label>
			<form:select path="supplierId" class="input-medium" style="width: 260px;">
				<form:option value="" label="全部(qb)"/>
				<form:options items="${spd:findSupplierList()}" itemLabel="nameAndpinyin" itemValue="id" htmlEscape="false"/>
			</form:select>
			<input id="btnSubmit" onclick="return page();"  style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
		</form:form>
	</div>
	<sys:message content="${message}"/>
	<div class="tableBox">
		<table class="hcy-public-table" style="padding:0;">
			<thead>
				<tr>
					<th style="width:60px;">选择</th>
					<th style="width:160px">申购单号</th>
					<th>申购日期</th>
					<th>申购科室</th>
					<th>状态</th>
					<!-- <th>申购人员</th> -->
					<th>审核人员</th>
				</tr>
			</thead>
			<tbody id= "tdodyID">
				<c:forEach items="${page.list }" var="li">
					<tr style="cursor:pointer">
						<td>
							<input type="radio" name="subBox" data-order-no="${li.orderNo}"/>
						</td>
						<td>${li.orderNo }</td>
						<td><fmt:formatDate value="${li.purchaseDate }" pattern="yyyy-MM-dd"/></td>
						<td>${li.deptName }</td>
						<td>${fns:getDictLabel(li.orderStatus,'order_status','') }</td>
						<td>${li.purchaseName }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="pagination">${page}</div>
</div>
	<!--  -->
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		//选择已经选中的数据
		function compositeHtml(){
			var orderNo = null;
			$("input[name=subBox]:checked").each(function() {
				orderNo = $(this).attr("data-order-no"); //订单号
			})
			return orderNo;
		}
		$(function(){

            $("#tdodyID tr").click(function () {
                var input = $(this).find("input[type=radio]");//获取checkbox
                if(input.attr("checked")){
                    input.attr("checked",false);
                }else{
                    input.attr("checked",true);
                }
            });

            //checkbox冒泡事件
            $("input[type='radio']").click(function(e) {
                e.stopPropagation();
            })

			//申购时间
			laydate.render({
				elem: '#inHomeTime',
				range: true,
                trigger: 'click'
			});
		})
	</script>
</body>
</html>