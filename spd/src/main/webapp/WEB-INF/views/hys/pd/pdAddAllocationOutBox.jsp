<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<title>添加产品</title>
</head>
<body>
	<div class="right-main-box">
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="pdAllocationRecord" method="post" action="${ctx}/pd/pdAllocationRecord/pdAddAllocationOutBox">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<input id="recordState" type="hidden" name="recordState" value="${pdAllocationRecord.recordState}">
				<form:hidden path="recordStates"/>
				<div>
					<label>调拨单号</label>
					<form:input autocomplete="off" path="allocationCode" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
					<div><label>申请日期</label>
						<form:input autocomplete="off" path="queryDate" htmlEscape="false" id="allotTime" style="width:160px;" readonly="readonly" maxlength="64" class="input-medium" value="${pdAllocationRecord.queryDate}"/>
					</div>			
					<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
			</form:form>
		</div>
		<div class="tableBox">
		<table class="hcy-public-table" id="addContentTab" style="padding-top:0;">
			<thead>
				<tr>
					<th></th>
					<th>申请单号</th>
					<th>申请时间</th>
					<th>申请科室</th>
					<th>操作人员</th>					
				</tr>
			</thead>
			<tbody id="tdodyID">
				<c:forEach items="${page.list}" var="pdAllocationRecord">
					<tr style="cursor:pointer">
						<td><input type="radio" name="subBox" data-order-no="${pdAllocationRecord.allocationCode}"/><input type="hidden" id="inId" value="${pdAllocationRecord.inId}"/></td>
						<td>${pdAllocationRecord.allocationCode }</td>
						<td><fmt:formatDate value="${pdAllocationRecord.allocationDate}" pattern="yyyy-MM-dd"/></td>
						<td>${pdAllocationRecord.inName}</td>
						<td>${pdAllocationRecord.recordPeople }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<div class="pagination">${page }</div>
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

			//调拨日期
			laydate.render({
				elem: '#allotTime',
				range: true
			});
		})
		//选择已经选中的数据
		function compositeHtml(){
			var orderNo = null;
			$("input[name=subBox]:checked").each(function() {
				orderNo = $(this).attr("data-order-no"); //订单号
			})
			return orderNo;
		}
		function compositeInStroomId(){
			var inId=$("#inId").val();
			return inId;
		}
	</script>
		
</body>
</html>