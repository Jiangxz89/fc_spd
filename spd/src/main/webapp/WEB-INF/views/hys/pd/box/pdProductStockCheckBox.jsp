<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<title>添加产品</title>
</head>
<body>
	<div class="addProBox">
	<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdProduct" action="${ctx}/pd/pdProductStockCheck/findStockCheckList?storeroomId=${storeroomId }" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="searchBox">
				<label for="">产品名称</label>
				<input type="text" name="productName" id="name" value="${stock.productName }"/>
				<label for="">产品编号</label>
				<input type="text" name="productNo" id="number" value="${stock.productNo }"/>
				<label for="">规格</label>
				<input type="text" name="spec" id="spec" value="${stock.spec }"/>
				<label for="">型号</label>
				<input type="text" name="version" id="version" value="${stock.version }"/><br />
				<label for="">批号</label>
				<input type="text" name="batchNo" value="${stock.batchNo }"/>
				<label for="">有效期</label>
				<input type="text" name="validDateMore" id=customInp value="${stock.validDateMore }"/>
				<input type="hidden" id="beginDate" name="beginDate" />
				<input type="hidden" id="endDate" name="endDate" value=""/>
				<input type="submit" id="" value="查询" class="hcy-btn hcy-btn-primary" />
			</div>
	</form:form>
			<table class="hcy-public-table" id="addContentTab" style="padding-top:0;">
				<thead>
					<tr>
						<th>产品编号</th>
						<th>产品名称</th>
						<th>产品分类</th>
						<th>产品组别</th>
						<th>单位</th>
						<th>规格</th>
						<th>型号</th>
						<th>批号</th>
						<th>有效期</th>
						<th>生产厂家</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${voList }" var="vo">
						<tr>
							<td>${vo.pdProduct.number }</td>
							<td>${vo.pdProduct.name }</td>
							<td>${vo.pdProduct.categoryName }</td>
							<td>${vo.pdProduct.groupName }</td>
							<td>${vo.pdProduct.unit }</td>
							<td>${vo.pdProduct.spec }</td>
							<td>${vo.pdProduct.version }</td>
							<td>${vo.pdProductStock.batchNo }</td>
							<td><fmt:formatDate value="${vo.pdProductStock.validDate }" pattern="yyyy-MM-dd" /></td>
							<td>${vo.pdProductStock.venderName }</td>
							<td><input type="button" class="hcy-btn hcy-btn-o" value="选中" onclick="getStock('${vo.pdProductStock.id}')" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination">${page}</div>
		</div>
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
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function getStock(id){
			var id = id;
			window.parent.getStockAjax(id);
		}
		
	</script>
</body>
</html>