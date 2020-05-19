<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<style>
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#allMoney,#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;}
		.otherText>h3{font-weight:400;display:inline-block;padding:3px 10px 0 5px;font-size:12px;color:#666;}
	</style>
	<title>药品采购明细</title>
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//计算总数量
			var tot = 0;
			$('tbody>tr').each(function(i,v) {
				var cur = $(this).children('td:last').text().trim();
				tot += !cur?0:Number.parseInt(cur);
			});
			$('.total_number').html(tot);
		});
	</script>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>药品采购明细</h4>
		</div>
		<form:form id="inputForm" modelAttribute="medstoYpcgmx" action="${ctx}/pd/medstoYpcgzd/save" method="post" class="form-horizontal">
			<div class="searchBox">
				<label for="">申购单号</label>
				<input type="text" name="djh" style="width:170px;" value="${medstoYpcgzd.djh }" readonly="true"/>
				<label for="">申购科室</label>
				<input type="text" name="deptName" value="${spd:getStoreroomName(medstoYpcgzd.ksdm)}" readonly="true"/>
				<label for="">申购人员</label>
				<input type="text" value="${spd:getUserNameById(medstoYpcgzd.czyh) }" readonly="true"/>
				<%-- <form:hidden path="purchaseBy" value="${userInfo.id }"/> --%>
			</div>
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>序号</th>
						<th>药品名称</th>
						<th>药品编号</th>
						<th>药品规格</th>
						<th>药库单位</th>
						<th>药库系数</th>
						<th>批发价</th>
						<th>零售价</th>
						<th>申购数量</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pageList }" var="li" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>${li.ypmc }</td>
							<td>${li.ypdm }</td>
							<td>${li.ypgg }</td>
							<td>${li.ykdw }</td>
							<td>${li.ykxs }</td>
							<td>${li.ypfj }</td>
							<td>${li.ylsj }</td>
							<td>${li.cgsl }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="totalText">
				<span id="allNum">总数量:<span class="total_number">0</span></span>
				<!-- <span id="allMoney">总金额:<span class="total_money">0.00</span></span> -->
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
	   			<a href="javascript:location=document.referrer;" class="hcy-btn hcy-back" style="line-height: normal;">返回</a>
	   		</div>
		</div>
	</form:form>
	</div>
</body>
</html>