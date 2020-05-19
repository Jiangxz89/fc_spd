<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品调价单明细</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<style>
		.layui-layer-content{text-align:center;}
		.searchBox label{width: 85px;text-align: left;margin:0;}
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
		resetTip();
	</script>
</head>
<body>
	<div class="right-main-box"> 
		<div class="btnBox">
			<h4>药品调价单明细</h4>
			<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="medstoYpdjd" action="${ctx}/pd/medstoYpdjd/findDrugPriceAdjustment" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>药库名称</label>
					<form:input path="storeName" />
				</div>
				<div>
					<label>药品编码</label>
					<form:input path="drugId" />
				</div>
				<div>
					<label>药品名称</label>
					<form:input path="drugName" />
				</div>
				<div>
					<label>药品规格</label>
					<form:input path="drugSpec" />
				</div>
				</br>
				<div>
					<label>国家基药标志</label>
					<form:select path="isjbyw">
						<form:option value="">全部</form:option>
						<form:option value="1">是</form:option>
						<form:option value="2">否</form:option>
					</form:select>
				</div>
				<div>
					<label>是否抗生素药品</label>
					<form:select path="kss">
						<form:option value="">全部</form:option>
						<form:option value="1">是</form:option>
						<form:option value="2">否</form:option>
					</form:select>
				</div>
				<div>
					<label>药品生产企业</label>
					<form:input path="sdrugmanufacturers" />
				</div>
				<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="showTableBox" style="width:100%;overflow:auto;">
			<div class="tableBox">
				<table id="contentTable" class="hcy-public-table" style="padding:0;width:2000px;">
					<thead>
						<tr>
							<th>药品编码</th>
							<th>药品名称</th>
							<th>药品规格</th>
							<th>药品默认扣率</th>
							<th>药库名称</th>
							<th>医保统一编码</th>
							<th>是否基药</th>
							<th>是否抗生素药品</th>
							<th>中药使用类别代码</th>
							<th>药物类型</th>
							<th>药物剂型代码</th>
							<th>大包装单位</th>
							<th>大包装数量</th>
							<th>小包装单位</th>
							<th>小包装数量</th>
							<th>包装单位数量关系</th>
							<th>供货商名称</th>
							<th>进价单价</th>
							<th>零售单价</th>
							<th>进价金额合计</th>
							<th>零售金额合计</th>
							<th>生产批号</th>
							<th>药品生产企业</th>
							<th>药品产地</th>
							<th>批准文号</th>
							<th>生产日期</th>
							<th>失效日期</th>
							<th>调价前价格</th>
							<th>调价后价格</th>
							<th>上传日期</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="drug">
							<tr>
								<td>${drug.drugId}</td>
								<td>${drug.drugName}</td>
								<td>${drug.drugSpec}</td>
								<td>${drug.ypmrkl}</td>
								<td>${drug.storeName}</td>
								<td>${drug.ybtybm}</td>
								<c:choose>
									<c:when test="${drug.isjbyw==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${drug.kss==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<td>${drug.cmedCode}</td>
								<td>${drug.drugDetailType}</td>
								<td>${drug.doseCode}</td>
								<td>${drug.packageUnit}</td>
								<td>${drug.packageQty}</td>
								<td>${drug.salesUnit}</td>
								<td>${drug.salesQty}</td>
								<td>${drug.salesRelation}</td>
								<td>${drug.producerName}</td>
								<td>${drug.costPrice}</td>
								<td>${drug.salePrice}</td>
								<td>${drug.costAmt}</td>
								<td>${drug.saleAmt}</td>
								<td>${drug.batchNo}</td>
								<td>${drug.sdrugmanufacturers}</td>
								<td>${drug.sdrughabitat}</td>
								<td>${drug.sratificationno}</td>
								<td><fmt:formatDate value="${drug.ddateproduce}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${drug.expiry}" pattern="yyyy-MM-dd"/></td>
								<td>${drug.beforeprice}</td>
								<td>${drug.afterprice}</td>
								<td><fmt:formatDate value="${drug.scrq}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function() {
			//重置
			$(".hcy-reset").click(function () {
				$(".searchBox div input[type='text']").val("");
				$(".searchBox div select").val("");
			})

			//导出数据
			$('#exportBtn').one('click',function(){
				$(this).css("background-color","#B3BDC3");
				var form = $('<form>');
				form.attr('style', 'display:none');
				form.attr('method', 'post');
				form.attr('action', '${ctx}/excelExport/drugDrugPriceAdjustmentExport');
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
	</script>
</body>
</html>