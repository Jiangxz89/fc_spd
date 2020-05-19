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
		<style>
			.addRoomBox{line-height: 40px;padding:10px 5px;margin-bottom: 20px;}
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
				<input type="text" readOnly value="${head.number }" />
				<label>退货日期</label>
				<input type="text" readOnly value="${head.rejectedDate }" />
				<label>退货库房</label>
				<input type="text" readOnly value="${head.rejectedRepoName }" /><br>
				<label>供应商</label>
				<input type="text" readOnly value="${head.supplierName }" />
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
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
      			<a href="javascript:history.go(-1)" class="hcy-btn hcy-btn-o" style="line-height: normal;padding:6px 12px;background: #fff;color: #666;border-color: #ccc;">返回</a>
      		</div>
		</div>
		<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
		<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
		<script src="${ctxStatic}/spd/js/layer.js"></script>
		<script type="text/javascript">
		
		</script>
</body>
</html>