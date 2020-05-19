<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<title>添加定数包</title>
	<style type="text/css">
		.searchBox label {
		    width: 70px;
		    text-align: left;
		}
	</style>
</head>
<body>
	<div class="addAlloactionBox">
	<form:form id="searchForm" modelAttribute="pdPackage" method="post" action="${ctx}/pd/pdPackage/pdAllocationAddPackage">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="" name="storeroomId" type="hidden" value="${pdPackage.storeroomId}"/>
		<input id="" name="ids" type="hidden" value="${ids}"/>
		<div class="searchBox" style="text-align: left;">
			<label for="">定数包编号</label>
			<input type="text" name="number" value="${pdPackage.number}"/>
			<label for="">定数包名称</label>
			<input type="text" name="name" value="${pdPackage.name}"/>
			<input type="submit" style="line-height:0.4;" value="查询" class="hcy-btn hcy-btn-primary" />
		</div>
	</form:form>
		<table class="hcy-public-table" id="addContentTab" style="padding-top:0;">
			<thead>
				<tr>
					<th><input type="checkbox" id="allchoose"/></th>
					<th>定数包编号</th>
					<th>定数包名称</th>
					<th>产品编号</th>
					<th>产品名称</th>
					<th>规格</th>
					<th>型号</th>
					<th>产品数量</th>
					<th>单位</th>
					<th>本库库存数量</th>
					<c:if test="${viewPermission eq '0'}">
						<th>出库库存数量</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pdPackage" items="${packageList }" varStatus="outStatus">
					<c:forEach var="pdProduct" items="${pdPackage.pdProductList }" varStatus="status">
						<tr data-id="${pdPackage.id }" data-number="${pdPackage.number }" data-name="${pdPackage.name }" data-length="${fn:length(pdPackage.pdProductList)}">
							<c:if test="${status.count eq 1}">
								<td rowspan="${fn:length(pdPackage.pdProductList)}"><input type="checkbox" value="${outStatus.count-1 }"/></td>
								<td rowspan="${fn:length(pdPackage.pdProductList)}"><a href="###" title="${pdPackage.number}"  class="overLook">${pdPackage.number}</a></td>
								<td rowspan="${fn:length(pdPackage.pdProductList)}"><a href="###" class="overLook" title="${pdPackage.name }">${pdPackage.name }</a></td>
							</c:if>
							<td>${pdProduct.number }<input type="hidden" class="productNumber${status.count}${outStatus.count-1}" value="${pdProduct.number }"/></td>
							<td>${pdProduct.name }<input type="hidden" class="productName${status.count}${outStatus.count-1}" value="${pdProduct.name }"/></td>
							<td>${pdProduct.spec }<input type="hidden" class="productSpec${status.count}${outStatus.count-1}" value="${pdProduct.spec }"/></td>
							<td>${pdProduct.version }<input type="hidden" class="productVersion${status.count}${outStatus.count-1}" value="${pdProduct.version }"/></td>
							<td>${pdProduct.productNumber }<input type="hidden" class="productNum${status.count}${outStatus.count-1}" value="${pdProduct.productNumber }"/></td>
							<td>${pdProduct.unit }<input type="hidden" class="productUnit${status.count}${outStatus.count-1}" value="${pdProduct.unit }"/></td>
							<td>${pdProduct.selfStockNum }<input type="hidden" class="productSelfStock${status.count}${outStatus.count-1}" value="${pdProduct.selfStockNum }"/></td>
							<c:if test="${viewPermission eq '0'}">
								<td>${pdProduct.stockNum }<input type="hidden" class="productStockNum${status.count}${outStatus.count-1}" value="${pdProduct.stockNum }"/></td>
							</c:if>
						</tr>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page }</div>
	</div>
	<!--  -->
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//全选与反选
			$("#allchoose").click(function(){
				if($(this).attr('checked')){
					$("input[type='checkbox']").attr('checked','true');
				}else{
					$("input[type='checkbox']").removeAttr('checked');
				}
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		//-调拨单新增定数包
		function addPackageHtml(index){
			var chObj = $("input[type='checkbox']:gt(0):checked");
			if(chObj.length < 1)
				return 0;
			var html = '', indexVue = null;
			var isView = '${viewPermission}';
			chObj.each(function(i,v){
				var row = $(this).val()
				indexVue = Number(index) + Number(i);
				var $this = $(this).parent().parent();
				var rowspanNum = $this.data("length");
				for(var j=1;j<=rowspanNum;j++){
					html += '<tr class="remove_'+$this.data("id")+' packageClass" data-id="'+$this.data("id")+'">';
					if(j==1){
						html += '<td rowspan="'+rowspanNum+'"><i class="fa fa-trash-o" onclick="removeDiv(\''+$this.data("id")+'\');"></i><i class="fa fa-search"></i></td>'
						 +		'<td rowspan="'+rowspanNum+'">'+$this.data("name")+'</td>'
						 +		'<td rowspan="'+rowspanNum+'">'+$this.data("number")+'</td>';
					}
					html += 	'<td><a href="###" class="overLook" title="">'+$(".productName"+j+row).val()+'</a></td>'
						 +		'<td><a href="###" title=""  class="overLook">'+$(".productNumber"+j+row).val()+'</a></td>'
						 +		'<td>'+$(".productSpec"+j+row).val()+'</td>'
						 +		'<td>'+$(".productVersion"+j+row).val()+'</td>'
						 +		'<td class="productNumClass">'+$(".productNum"+j+row).val()+'</td>'
						 +		'<td>'+$(".productUnit"+j+row).val()+'</td>'
						 +		'<td class="selfStockNumClass">'+$(".productSelfStock"+j+row).val()+'</td>';
						 if(isView=='0'){
						 	html +=	'<td class="stockNumClass">'+$(".productStockNum"+j+row).val()+'</td>';
						 }
					 if(j==1){
						html += 	'<td rowspan="'+rowspanNum+'" class="applyNumClass"><input type="text" placeholder="请输入1-9999之间的整数" class="sumClass" onchange="allocationSum(2)" name="pdAllocationProductList['+indexVue+'].allocationNum" maxlength="4"/></td>'
							 +      '<input type="hidden" name="pdAllocationProductList['+indexVue+'].productCode" value="'+$this.data("id")+'"/>'
							 +      '<input type="hidden" name="pdAllocationProductList['+indexVue+'].productAttr" value="2"/>'
							 +      '<input type="hidden" class="allocationCodeClass" name="pdAllocationProductList['+indexVue+'].allocationCode" value=""/>';
					 }
					 html += '</tr>';
				}

			});
			return html;
		}
	</script>
</body>
</html>