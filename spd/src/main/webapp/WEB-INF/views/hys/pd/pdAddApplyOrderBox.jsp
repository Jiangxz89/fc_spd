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
		<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdApplyOrder" action="${ctx}/pd/pdApplyOrder/pdAddApplyOrderBox" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<form:hidden path="applyStatus" value="${pdApplyOrder.applyStatus}"/>
			<form:hidden path="applyStatuses"/>
			<form:hidden path="deptId"/>
			<div class="searchBox">
				<div>
					<label for="">申领单号</label>
					<form:input autocomplete="off" path="applyNo" />
				</div>				
				<div>
					<label for="">领用日期</label>
					<form:input autocomplete="off" path="queryDate" htmlEscape="false" id="applyDate" style="width:160px;" readonly="readonly" maxlength="64" class="input-medium"/>
				</div>
				<input id="btnSubmit"  onclick="return page();"  style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
			</div>
		</form:form>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>选择</th>
						<th>申领单号</th>
						<th>领用日期</th>
						<th>申领科室</th>						
						<th>操作人员</th>						
					</tr>
				</thead>
				<tbody id= "tdodyID">
					<c:forEach items="${page.list }" var="li">
						<tr style="cursor:pointer">
							<td><input type="radio" name="subBox" data-order-no="${li.applyNo}"><input type="hidden" id="inId" value="${li.deptId }"/></td>
							<td>${li.applyNo }</td>
							<td><fmt:formatDate value="${li.applyDate }" pattern="yyyy-MM-dd"/></td>
							<td>${li.deptName }</td>							
							<td>${li.applyName }</td>							
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
				elem: '#applyDate',
				range: true,
				done: function(value, date, endDate){
					var dateArr = value.split(" - ");
					$("#startDate").val(dateArr[0]);
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