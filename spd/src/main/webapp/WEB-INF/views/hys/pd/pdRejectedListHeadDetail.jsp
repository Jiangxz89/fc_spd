<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<meta name="decorator" content="default"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta http-equiv="X-UA-Compatible" content="ie=edge" />
		<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
		<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
		<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
		<link rel="stylesheet" href="${ctxStatic}/spd/css/print.css" media="print">
		<style>
			#addRoomBox{line-height: 40px;padding:10px 5px;margin-bottom: 20px;}
			.addRoomBox label{width:75px;display: inline-block;text-align: left;}
			.addRoomBox>input[type='text'],.addRoomBox>select{display:inline-block;width: 160px;height:30px;border:1px solid #ccc;margin:0 10px 0 5px;}
		</style>
		<title>退货详情</title>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>退货详情</h4>
		</div>
			<div class="searchBox">
				<label>退货单号</label>
				<input type="text" style="width:165px;" readOnly value="${head.number }" />
				<label>退货日期</label>
				<input type="text" readOnly value="${head.rejectedDate }" />
				<label>退货库房</label>
				<input type="text" readOnly value="${head.rejectedRepoName }" /><br>
				<%--<label>供应商</label>
				<input type="text" style="width:165px;" readOnly value="${spd:getSupplierName(head.supplierId) }" />--%>
				<label>操作人员</label>
				<input type="text" readOnly value="${head.operPerson }" />
				<label>操作时间</label>
				<input type="text" id="todoTime" readOnly value="<fmt:formatDate value="${head.createDate }" pattern="yyyy-MM-dd HH:mm:ss" />" />
				<%-- <span>退货单号：${head.number }</span>
				<span>退货日期：：<fmt:formatDate value="${head.rejectedDate }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
				<span>退货库房：${head.rejectedRepoName }</span><br />
				<span>供应商：${head.supplierName }</span>
				<span>操作人员：${head.operPerson }</span>
				<span>操作时间：<fmt:formatDate value="${head.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></span><br />
				<span>操作时间：${head.createDate }</span><br /> --%>
			</div>
			<div class="tableBox">
				<table class="hcy-public-table">
					<thead>
						<tr>
							<th>产品名称</th>
							<th>产品编号</th>
							<th>产品条码</th>
							<th>规格</th>
							<th>批号</th>
							<th>单位</th>
							<th>有效期</th>
							<th>退货数量</th>
							<th>产品分类</th>
							<th>产品组别</th>
							<th>供应商名称</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${head.child }" var="a">
							<tr>
								<td>${a.productBean.name }</td>
								<td>${a.productBean.number }</td>
								<td>${a.productStockBean.productBarCode }</td>
								<td>${a.productBean.spec }</td>
								<td>${a.productStockBean.batchNo }</td>
								<td>${a.productBean.unit }</td>
								<td><fmt:formatDate value="${a.productStockBean.validDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${a.rejectedCount }</td>
								<td>${a.productBean.categoryName }</td>
								<td>${a.productBean.groupName }</td>
								<td>${spd:getSupplierName(head.supplierId) }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
				<a href="###" class="hcy-btn hcy-btn-primary"  id="printBtn">打印</a>
      			<a href="javascript:history.go(-1)" class="hcy-btn hcy-back">返回</a>
      		</div>
		</div>
		<div id="printBox">
			<div style="margin-left: 50%" id="div_id"></div>
			<div class="printTab">
				<table>
					<thead style="display:table-header-group;">
					<tr>
						<td colspan="8" style="border:0;font-size:20px;color:#202020">${fns:getUser().companyName }<span style="padding-left:40px;">退货单</span></td>
					</tr>
					<tr>
						<td colspan="8" style='border:0;'>
							<div class="printList">
								<ul>
									<li>
										<label>退货单号：</label>
										<span>${head.number }</span>
									</li>
									<li>
										<label>操作人员：</label>
										<span>${head.operPerson }</span>
									</li>
									<li>
										<label>审核时间：</label>
										<span><fmt:formatDate value="${head.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
									</li>
								</ul>
								<ul>
									<li>
										<label>退货时间：</label>
										<span><fmt:formatDate value="${head.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
									</li>
									<li>
										<label>审核人员：</label>
										<span>${head.operPerson }</span>
									</li>
									<li>
										<label>供应商名称：</label>
										<span>${spd:getSupplierName(head.supplierId) }</span>
									</li>
								</ul>
							</div>
							<div>
								<ul style="padding:0;margin:0;text-align:left;">
									<li>
										<label style="display: inline-block;width: 80px;font-size: 13px;">备注：</label>
										<span style="width:auto;font-weight:400;" >${head.remarks }</span>
									</li>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<th>产品名称</th>
						<th>产品编号</th>
						<th>产品条码</th>
						<th>规格</th>
						<th>批号</th>
						<th>单位</th>
						<th>有效期</th>
						<th>退货数量</th>
						<th>产品分类</th>
						<th>产品组别</th>
					</tr>
					</thead>
					<tbody id="printDiv">
						<c:forEach items="${head.child }" var="a">
							<tr>
								<td>${a.productBean.name }</td>
								<td>${a.productBean.number }</td>
								<td>${a.productStockBean.productBarCode }</td>
								<td>${a.productBean.spec }</td>
								<td>${a.productStockBean.batchNo }</td>
								<td>${a.productBean.unit }</td>
								<td><fmt:formatDate value="${a.productStockBean.validDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${a.rejectedCount }</td>
								<td>${a.productBean.categoryName }</td>
								<td>${a.productBean.groupName }</td>
							</tr>
						</c:forEach>
						<tr class="total">
							<td colspan="10" style="text-align:right;">合计：${head.productTotNum }</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="signBox">
				<h3>操作人员签字：____________</h3>
				<h3>审核人员签字：____________</h3>
			</div>
		</div>
		<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
		<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
		<script src="${ctxStatic}/spd/js/layer.js"></script>
		<script src="${ctxStatic}/spd/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
		<script src="${ctxStatic}/jquery/jquery-barcode.js"></script>
		<script type="text/javascript">

            //rfidCode:单据号，需要获取
            $('#div_id').empty().barcode('${head.number }', "code128",{
                output:'css',
                color: '#000000',
                barWidth: 2,
                barHeight: 40,
                addQuietZone: false,
                fontSize: 0,
            });

			$(document).ready(function() {
				$("#printBtn").click(function(){
					printFn();
				})
			})
			function printFn(){
				//打印
				$("#printBox").jqprint();
			}
		</script>
</body>
</html>