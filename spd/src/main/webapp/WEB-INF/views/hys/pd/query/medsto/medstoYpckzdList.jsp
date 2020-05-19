<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品出库明细</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<style>
		.layui-layer-content{text-align:center;}
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
			<h4>药品出库明细</h4>
			<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="medstoYpckzd" action="${ctx}/pd/medstoYpckzd/findDrugOutDetaile" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>出库单号</label>
					<form:input path="rkdh" />
				</div>
				<div>
					<label>科室代码</label>
					<form:input path="ksdm" />
				</div>
				<div>
					<label>供货单位</label>
					<form:input path="ghdwdm" />
				</div>
				<div>
					<label>配送单号</label>
					<form:input path="psdh" />
				</div>
				<div>
					<label>药品名称</label>
					<form:input path="drugName" />
				</div>
				<div>
					<label>药品规格</label>
					<form:input path="drugSpec" />
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
							<th>出库代码</th>
							<th>出库单号</th>
							<th>科室代码</th>
							<th>供货单位</th>
							<th>发票号</th>
							<th>开票日期</th>
							<th>到票日期</th>
							<th>出库日期</th>
							<th>出库操作员</th>
							<th>零售金额</th>
							<th>批发金额</th>
							<th>进价金额</th>
							<th>优惠金额</th>
							<th>配送单号</th>
							<th>药品名称</th>
							<th>药品规格</th>
							<th>失效日期</th>
							<th>批号</th>
							<th>操作数量</th>
							<th>药品进价</th>
							<th>批发价</th>
							<th>零售价</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="drug">
							<tr>
								<td>${drug.rkdm}</td>
								<td>${drug.rkdh}</td>
								<td>${drug.ksdm}</td>
								<td>${drug.ghdwdm}</td>
								<td>${drug.fph}</td>
								<td><fmt:formatDate value="${drug.kprq}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${drug.dprq}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${drug.rkrq}" pattern="yyyy-MM-dd"/></td>
								<td>${drug.rkczyh}</td>
								<td>${drug.lsje}</td>
								<td>${drug.pfje}</td>
								<td>${drug.jjje}</td>
								<td>${drug.yhje}</td>
								<td>${drug.psdh}</td>
								<td>${drug.medstoYpckmx.drugName}</td>
								<td>${drug.medstoYpckmx.drugSpec}</td>
								<td><fmt:formatDate value="${drug.medstoYpckmx.sxrq}" pattern="yyyy-MM-dd"/></td>
								<td>${drug.medstoYpckmx.ph}</td>
								<td>${drug.medstoYpckmx.czsl}</td>
								<td>${drug.medstoYpckmx.ypjj}</td>
								<td>${drug.medstoYpckmx.ypfj}</td>
								<td>${drug.medstoYpckmx.ylsj}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="pagination">${page}</div>
		</div>
	</div>
	<div class="addChargeCodeBox">
		<h4 style="font-size: 14px;font-weight: 400;line-height: 60px;color: #666;"></h4>
		<select style="padding:0 0 0 5px;margin:0 auto;height:30px;width:220px;" class="addSupplierSelect">
			<option value="">请选择</option>
			<c:forEach items="${spd:findSupplierList()}" var="li">
				<option value="${li.id }">${li.supplierName }</option>
			</c:forEach>
		</select><span style="color:red;" class="nonSelect"></span>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function() {
			$(document).on('change', ".addSupplierSelect", function () {
				$(this).next().html('');
			});
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
				form.attr('action', '${ctx}/excelExport/drugOutDetaileExport');
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