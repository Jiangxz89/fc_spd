<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/print.css" media="print">
	<style>
		.addRoomBox{line-height: 40px;padding:10px 5px;margin-bottom: 20px;}
		.addRoomBox label{width:75px;display: inline-block;text-align: left;}
		.addRoomBox>input[type='text'],.addRoomBox>select{display:inline-block;width: 160px;height:30px;border:1px solid #ccc;margin:0 10px 0 5px;}
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#allMoney,#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.littleTip{font-size: 14px;color: #000;padding-right: 10px;display: inline-block;width: 70px;}
		#describe,#reArea{width: 500px;height: 100px;vertical-align: top;}
	</style>
	<title>领用申请单详情</title>
	<script src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
		</script>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>领用详情</h4>
		</div>
		<form:form id="inputForm" modelAttribute="pdApplyDetail" action="${ctx}/pd/pdApplyDetail/save" method="post" class="form-horizontal">
			<form:hidden path="id"/>
			<sys:message content="${message}"/>		
			<div class="searchBox">
				<label for="">申领单号</label>
				<input type="text" style="width:165px;" value="${pdApplyOrder.applyNo }" readonly="readonly"/>
				<label for="">申领时间</label>
				<input type="text" value='<fmt:formatDate value="${pdApplyOrder.applyDate }" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly"/>
				<label for="">操作人员</label>
				<input type="text" value="${pdApplyOrder.applyName }" readonly="readonly"/><br>
				<label for="">审核人员</label>
				<input type="text" style="width:165px;" value="${pdApplyOrder.auditName }" readonly="readonly"/>
				<label for="">审核时间</label>
				<input type="text" value='<fmt:formatDate value="${pdApplyOrder.auditDate }" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly"/>
			</div>
			<div class="tableBox">
				<table class="hcy-public-table">
					<thead>
						<tr>
							<th>定数包名称</th>
							<th>定数包编号</th>
							<th>产品名称</th>
							<th>产品编号</th>
							<th>规格</th>
							<th>型号</th>
							<th>产品数量</th>
							<th>单位</th>
							<th>库存数量</th>
							<th>申领数量</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="totalText">
				<span id="allNum">产品总数量:<span id="allocationSum">${pdApplyOrder.applyCount }</span></span>
			</div>
			<div class="otherText" style="margin-top: 30px;">
				<h3 class="littleTip">备注</h3>
				<textarea name="" id="describe" readOnly>${pdApplyOrder.remarks }</textarea>
			</div>
			<c:if test="${pdApplyOrder.applyStatus eq 2 }">
				<div class="otherText" style="margin-top: 30px;">
					<h3 class="littleTip">拒绝理由</h3>
					<textarea name="" id="reArea" readOnly>${pdApplyOrder.refuseReason }</textarea>
				</div>
			</c:if>
		</form:form>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
   			<a href="###" class="hcy-btn hcy-btn-primary" onclick="printFn()" id="printBtn">打印</a>
   			<a href="javascript:location.href=document.referrer;" class="hcy-btn hcy-btn-o" style="line-height: normal;padding:6px 12px;background: #fff;color: #666;border-color: #ccc;">返回</a>
   		</div>
	</div>
	<div id="printBox">
		<div style="margin-left: 50%" id="div_id"></div>
		<%-- <div class="printHeader">
			<h3>${fns:getUser().companyName }</h3>
			<h3>申领单</h3>
		</div>
		<div class="printPage">
			页码：1/1
		</div> --%>
		<div class="printTab">
			<table>
				<thead style="display:table-header-group;">
					<tr>
						<td colspan="8" style="border:0;font-size:20px;color:#202020">${fns:getUser().companyName }<span style="padding-left:40px;">申领单</span></td>
					</tr>
					<tr>
						<td colspan="8" style='border:0;'>
							<div class="printList">
								<ul>
									<li>
										<label>申领单号：</label>
										<span id="outNo">${pdApplyOrder.applyNo }</span>
									</li>
									<li>
										<label>操作人员：</label>
										<span id="outDate">${pdApplyOrder.applyName }</span>
									</li>
									<li>
										<label>审核时间：</label>
										<span id="oName"><fmt:formatDate value="${pdApplyOrder.auditDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
									</li>
								</ul>
								<ul>
									<li>
										<label>申领时间：</label>
										<span id="iName"><fmt:formatDate value="${pdApplyOrder.applyDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
									</li>
									<li>
										<label>审核人员：</label>
										<span>${pdApplyOrder.auditName }</span>
									</li>
								</ul>
							</div>
							<div>
								<ul style="padding:0;margin:0;text-align:left;">
									<li>
										<label style="display: inline-block;width: 80px;font-size: 13px;">备注：</label>
										<span style="width:auto;font-weight:400;" id="oName">${pdApplyOrder.remarks }</span>
									</li>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<th>定数包名称</th>
						<th>定数包编号</th>
						<th>产品名称</th>
						<th>产品编号</th>
						<th>规格</th>
						<th>型号</th>
						<th>产品数量</th>
						<th>单位</th>
						<th>库存数量</th>
						<th>申领数量</th>
						<th>入库单价</th>
						<th>入库金额</th>
						<th>出库单价</th>
						<th>出库金额</th>
					</tr>
				</thead>
				<tbody id="printDiv">
				</tbody>
			</table>
		</div>
		<div class="signBox">
			<h3>操作人员签字：____________</h3>
			<h3>审核人员签字：____________</h3>
		</div>
	</div>
	<!-- <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script> -->
	<script src="${ctxStatic}/spd/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
	<script src="${ctxStatic}/jquery/jquery-barcode.js"></script>
	<script type="text/javascript">

        //rfidCode:单据号，需要获取
        $('#div_id').empty().barcode('${pdApplyOrder.applyNo }', "code128",{
            output:'css',
            color: '#000000',
            barWidth: 2,
            barHeight: 40,
            addQuietZone: false,
            fontSize: 0,
        });

		$(function(){
			//初始化
			loadHtml('${pdApplyDetail.applyNo}');
		});
		function loadHtml(applyNo){
			$.getJSON('${ctx}/pd/pdApplyDetail/getData',{applyNo:applyNo, flag:'viewForm'},function(data){
				var html = '', pHtml='',datap = data.productList;
				var productTotNum = data.productTotNum;
				var sumInPrice = data.sumInPrice;
				var sumOutPrice = data.sumOutPrice;
				data = data.packageList;
				//定数包
				$.each(data,function(i,v){
					var rowslen = data[i].detail.length;
					 for(var j=0;j<rowslen;j++){
						var obj = data[i].detail[j];
						html += '<tr>';
						 if(j==0){
							 html += '<td valign="middle" rowspan="'+rowslen+'">'+data[i].packageName+'</td>';
							 html += '<td valign="middle" rowspan="'+rowslen+'">'+data[i].packageNumber+'</td>';
						 }
						 html +='<td>'+obj.pdProduct.name+'</td>'
						 +		'<td>'+obj.pdProduct.number+'</td>'
						 +		'<td>'+obj.pdProduct.spec+'</td>'
						 +		'<td>'+obj.pdProduct.version+'</td>'
						 +		'<td>'+obj.productCount+'</td>'
						 +		'<td>'+obj.pdProduct.unit+'</td>'
						 +		'<td>'+obj.stockNum+'</td>';
						 if(j==0){
						 	html += '<td valign="middle" rowspan="'+rowslen+'">'+data[i].packageCount+'</td>';
						 }
					    html += '</tr>';


						 pHtml += '<tr>';
						 if(j==0){
							 pHtml += '<td valign="middle" rowspan="'+rowslen+'">'+data[i].packageName+'</td>';
							 pHtml += '<td valign="middle" rowspan="'+rowslen+'">'+data[i].packageNumber+'</td>';
						 }
						 pHtml +='<td>'+obj.pdProduct.name+'</td>'
								 +		'<td>'+obj.pdProduct.number+'</td>'
								 +		'<td>'+obj.pdProduct.spec+'</td>'
								 +		'<td>'+obj.pdProduct.version+'</td>'
								 +		'<td>'+obj.productCount+'</td>'
								 +		'<td>'+obj.pdProduct.unit+'</td>'
								 +		'<td>'+obj.stockNum+'</td>';
						 if(j==0){
							 pHtml += '<td valign="middle" rowspan="'+rowslen+'">'+data[i].packageCount+'</td>';
						 }
						 pHtml += '</tr>';
					 }
				});

				//产品
				$.each(datap, function(i,v){
					html += '<tr>'
						 +		'<td></td>'
						 +		'<td></td>'
						 +		'<td>'+datap[i].pdProduct.name+'</td>'
						 +		'<td>'+datap[i].prodNo+'</td>'
						 +		'<td>'+datap[i].pdProduct.spec+'</td>'
						 +		'<td>'+datap[i].pdProduct.version+'</td>'
						 +		'<td>--</td>'
						 +		'<td>'+datap[i].pdProduct.unit+'</td>'
						 +		'<td>'+datap[i].stockNum+'</td>'
						 +		'<td>'+datap[i].applyCount+'</td>'
						 +	'</tr>';

					pHtml += '<tr>'
							+		'<td></td>'
							+		'<td></td>'
							+		'<td>'+datap[i].pdProduct.name+'</td>'
							+		'<td>'+datap[i].prodNo+'</td>'
							+		'<td>'+datap[i].pdProduct.spec+'</td>'
							+		'<td>'+datap[i].pdProduct.version+'</td>'
							+		'<td>--</td>'
							+		'<td>'+datap[i].pdProduct.unit+'</td>'
							+		'<td>'+datap[i].stockNum+'</td>'
							+		'<td>'+datap[i].applyCount+'</td>'
							+		'<td>'+datap[i].inPrice+'</td>'
							+		'<td>'+datap[i].pdTotalPrice+'</td>'
							+		'<td>'+datap[i].outPrice+'</td>'
							+		'<td>'+datap[i].pdOutTotalPrice+'</td>'
							+	'</tr>';
				});
				//追加html
				if(html)
					$('.hcy-public-table>tbody').append(html);
				//打印数据
				var printHtml = pHtml.replace(/<a href="###"/g, '<span').replace(/<\/a>/g, '</span>');
				$('#printDiv').append(printHtml);
				$('#printDiv').append('<tr class="total">');
				$('#printDiv').append('<td colspan="6" style="text-align:right;">合计数量：${pdApplyOrder.applyCount }</td>');
				$('#printDiv').append('<td colspan="4" style="text-align:center;">合计入库金额：'+sumInPrice+'</td>');
				$('#printDiv').append('<td colspan="4" style="text-align:center;">合计出库金额：'+sumOutPrice+'</td>');
				$('#printDiv').append('</tr>');
			});
		}
		function printFn(){
			//打印
			//event.preventDefault();	
			$("#printBox").jqprint();	
		}		
	</script>
</body>
</html>