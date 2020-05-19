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
	<title>药品入库明细</title>
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>药品入库明细</h4>
		</div>
		<form:form id="inputForm" modelAttribute="medstoYprkmx" action="${ctx}/pd/medstoYpcgzd/save" method="post" class="form-horizontal">
			<div class="searchBox">
				<label for="">入库单号</label>
				<input type="text" name="djh" style="width:170px;" value="${medstoYprkzd.rkdh }" readonly="true"/>
				<label for="">入库科室</label>
				<input type="text" name="deptName" value="${medstoYprkzd.ksdm  }" readonly="true"/>
				<label for="">入库人员</label>
				<input type="text" value="${medstoYprkzd.rkczyh }" readonly="true"/>
			</div>
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>账单序号</th>
						<th>药品名称</th>
						<th>药品编号</th>
						<th>药品规格</th>
						<th>批次序号</th>
						<th>失效日期</th>
						<th>批号</th>
						<th>药库系数</th>
						<th>药品单位</th>
						<th>单位系数</th>
						<th>入库数量</th>
						<th>操作数量</th>
						<th>药品扣率</th>
						<th>药品进价</th>
						<th>批发价</th>
						<th>零售价</th>
						<th>进价金额</th>
						<th>进销差额</th>
						<th>到票标志</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${detailList }" var="li">
						<tr>
							<td>${li.zdXh }</td>
							<td>${li.ypmc }</td>
							<td>${li.ypdm }</td>
							<td>${li.ypgg }</td>
							<td>${li.pcxh }</td>
							<td>${li.sxrq }</td>
							<td>${li.ph }</td>
							<td>${li.ykxs }</td>
							<td>${li.ypdw }</td>
							<td>${li.dwxs }</td>
							<td>${li.rksl }</td>
							<td>${li.czsl }</td>
							<td>${li.ypkl }</td>
							<td>${li.ypjj }</td>
							<td>${li.ypfj }</td>
							<td>${li.ylsj }</td>
							<td>${li.jjje }</td>
							<td>${li.jxce }</td>
							<td>${li.dpbz }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
	   			<a href="javascript:location=document.referrer;" class="hcy-btn hcy-back" style="line-height: normal;">返回</a>
	   		</div>
		</div>
	</form:form>
	</div>
</body>
</html>