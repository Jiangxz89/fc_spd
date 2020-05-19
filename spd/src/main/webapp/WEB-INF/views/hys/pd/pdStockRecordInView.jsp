<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/print.css" media="print">
	<style>
		.billBox,.otherText{margin:20px 0;line-height: 40px;}
		.billBox>h4,.otherText>h3{font-size:14px;color:#666;font-weight: 600;}
		.billBox .billTable{width:660px;border-collapse: collapse;margin-bottom: 20px;}
		.billBox .billTable th,.billBox .billTable td{width:145px; height:40px;border: 1px solid #ccc;font-size: 13px;text-align: center;}
		.billBox .billTable i.fa{font-size:16px;color:#00CFA5;margin:0 5px;cursor: pointer;}
		.billBox .billTable input[type='text']{width:90%;height: 90%;display: inline-block;padding:10px 5px;border:none;outline: none;box-shadow:none;}
		.billBox>label{display: inline-block;width: 75px;text-align: left;margin-left:10px;}
		.billBox>input[type='text'],.billBox>select{display: inline-block;width: 80px;height: 30px;border:1px solid #ccc;}
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;}
		.otherText>h3{font-weight:400;display:inline-block;padding:3px 10px 0 5px;width:72px;}
		.allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.totalText{text-align: right;height: 50px;line-height: 50px;}
	</style>
	<title>入库信息</title>
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//防止修改
			if($("#id").val() != null && $("#id").val() != "" ){
				$("#btnSubmit").attr("style","display:none;"); 
			}
			var ono = "${pdStockRecord.orderNo}";
			if(ono != null && ono != ""){
				showBillBox();
			}
			
		});

	</script>
</head>
<body>
	<form:form id="inputForm" style="margin:0;" modelAttribute="pdStockRecord" action="${ctx}/pd/pdStockRecord/saveIn" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="right-main-box">
			<div class="btnBox">
				<h4>入库信息</h4>
			</div>
			<div class="searchBox">
				<div>
					<label for="">入库单号</label>
					<form:input path="recordNo" style="width:165px;" htmlEscape="false" maxlength="64" class="input-xlarge " readOnly="true"/>
				</div>
				<div>
					<label for="">入库日期</label>
					<input name="recordDate" type="text" readonly="readonly" maxlength="10" id="recordDate" class="input-xlarge"
						value="<fmt:formatDate value="${pdStockRecord.recordDate}" pattern="yyyy-MM-dd"/>"/>
				</div>
				<div>
					<label for="">入库类型</label>
					<form:select path="inType" class="input-medium" disabled="true">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('stock_in_type') }" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<div>
					<label for="">操作人员</label>
					<form:input path="recordPeople" style="width:165px;" htmlEscape="false" maxlength="64" class="input-xlarge " readOnly="true"/>
				</div>
				<br>
				<div>
					<label>审核人员</label>
					<form:input path="recordPeople" style="width:165px;" htmlEscape="false" maxlength="64" class="input-xlarge " readOnly="true"/>				
				</div>
				<div>
					<label>审核时间</label>
					<input name="checkTime" type="text" readonly="readonly" maxlength="10" id="recordDate" class="input-xlarge"
							value="<fmt:formatDate value="${pdStockRecord.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				</div>
			<c:choose>
			<c:when test="${fns:getUser().storeroomType eq 0 && pdStockRecord.inType eq 0}">
				<div>
					<label for="">供应商</label>
					<form:select path="supplierId" style="width:210px;margin-right:0" disabled="true" class="input-medium">
						<form:option value="" label="全部"/>
						<form:options items="${fns:findSupplierList() }" itemLabel="supplierName" itemValue="id" htmlEscape="false"/>
					</form:select>
					<a href="###" id="sanZheng" class="hcy-btn" style="color:#22A7FF;">查看三证</a>
				</div>
			</c:when>
			<c:otherwise>
				<div>
					<label for="">出库库房</label>
					<form:input path="outName" htmlEscape="false" readOnly="true" maxlength="64" class="input-medium"/>
					<input id="outId" name="inId" type="hidden"/>
				</div>
			</c:otherwise>
			</c:choose>
			</div>
			<div class="tableBox" style="overflow:auto; width:100%;">
				<table id="contentTableTbody2" class="hcy-public-table" >
					<thead>
						<tr>
							<th style="width:100px">产品名称</th>
							<th style="width:150px">产品编号</th>
							<th style="width:100px">产品条码</th>
							<th style="width:90px">规格</th>
							<th style="width:90px">型号</th>
							<th>单位</th>
							<th>批号</th>
							<th style="width:100px">有效期</th>
							<th>数量</th>
							<th>入库单价</th>
							<th>金额</th>
							<th>库位</th>
							<th>单号</th>
							<th>注册证号</th>
							<th>生产厂家</th>
						</tr>
					</thead>
					<tbody id="productTbody">
						<c:forEach items="${pdStockRecord.productList}" var="product">
							<tr>
								<td><a href="###" class="overLook" style="width:100px" title="${product.productName }">${product.productName }</a></td>
								<td>${product.number }</td>
								<td>${product.productBarCode }</td>
								<td>${product.spec }</td>
								<td>${product.version }</td>
								<td>${product.unit }</td>
								<td>${product.batchNo }</td>
								<td><fmt:formatDate value="${product.limitDate}" pattern="yyyy-MM-dd"/></td>
								<td>${product.productNum }</td>
								<td>${product.inPrice }</td>
								<td>${product.pdTotalPrice }</td>
								<td>${product.stockPosition }</td>
								<td><a href='###' class='overLook' title="${product.importNo}">${product.importNo}</a></td>
								<%--<td><a href="###" title="${product.registration }" class="overLook">${product.registration }</a></td>--%>
								<td>${product.registration }</td>
								<td><a href="###" class="overLook" title="${product.venderNameShow }">${product.venderNameShow }</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
            <div class="totalText">
                <span class="allNum">总数量:<span class="total_number">${pdStockRecord.productTotNum }</span></span>
				<span class="allNum">总金额:<span class="total_price">${pdStockRecord.sumPdTotalPrice }</span></span>
            </div>
			<div class="billBox" style="display: none;">
				<h4 style="padding:10px 0 20px 0;">发票信息</h4>
				<table class="billTable">
					<thead>
						<tr>
							<th>发票日期</th>
							<th>发票号</th>
							<th>发票金额</th>
							<th>销售日期</th>
						</tr>
					</thead>
					<tbody id="contentTableTbody3">
						<c:forEach items="${pdStockRecord.invoiceList}" var="invoice">
							<tr>
								<td><input type="text" class="ticketTime" value="${invoice.invoiceDate }" /></td>
								<td><input type="text" class="ticketNum" value="${invoice.invoiceNo }" /></td>
								<!-- 入库发票信息优化 2018年6月28日17:09:29 -->
								<c:choose>
									<c:when test="${invoice.invoiceAmount eq '0.00'}">
										<td><input type="text" class="ticketMoney" value="" /></td>
									</c:when>
									<c:otherwise>
										<td><input type="text" class="ticketMoney" value="${invoice.invoiceAmount}" /></td>
									</c:otherwise>
								</c:choose>
								<td><input type="text" class="sellTime" value="${invoice.saleDate }" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<label for="">验收结果</label>
				<form:select path="testResult" class="input-medium" disabled="true">
					<form:option value="0" label="合格"/>
					<form:option value="1" label="不合格"/>
				</form:select>
				<label for="" style="margin-left:25px;">储运状态</label>
				<form:select path="storageResult" class="input-medium" disabled="true">
					<form:option value="0" label="合格"/>
					<form:option value="1" label="不合格"/>
				</form:select>
				<br/>
				<label for="">温度</label>
				<form:input path="temperature" htmlEscape="false" readonly="readonly" maxlength="3" class="input-xlarge "/>
				<span>℃</span>
				<label for="" style="margin-left:25px;">湿度</label>
				<form:input path="humidity" htmlEscape="false" readonly="readonly" maxlength="3" class="input-xlarge "/>
				<span>%</span>
			</div>
			<div class="otherText">
				<h3>备注</h3>
				<textarea name="remarks" class="remarkArea" readonly="readonly">${pdStockRecord.remarks}</textarea>
			</div>
			<c:if test="${pdStockRecord.recordState == 2}">
			<div class="otherText">
				<h3>驳回原因</h3>
				<textarea name="rejectReason" class="remarkArea"  readonly="readonly">${pdStockRecord.rejectReason}</textarea>
			</div>
			</c:if>
			<div class="form-actions" style="background:#fff;border:none;text-align:center;padding:0;margin-top:30px;">
				<c:if test="${pdStockRecord.recordState == 1}">
					<input id="btnPrint" class="hcy-btn hcy-btn-primary" type="button" value="打印"/>		
				</c:if>
				<input id="btnCancel" class="hcy-btn hcy-back" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>

	<div id="printBox">
		<div style="margin-left: 50%" id="div_id"></div>
		<div class="printTab">
			<%--<table style="width: 750px;"> 如需纵向打印需要设置表格宽度TODO --%>
				<table>
				<thead style="display:table-header-group;">
					<tr>
						<td colspan="8" style="border:0;font-size:20px;color:#202020">${fns:getUser().companyName}<span style="padding-left:40px;">入库单</span></td>
					</tr>
					<tr>
						<td colspan="8" style="border:0;">
							<div class="printList">
								<ul>
									<li>
										<label>入库单号：</label>
										<span  id="outNo">${pdStockRecord.recordNo }</span>
									</li>
									<li>
										<label>入库类型：</label>
										<span>${fns:getDictLabel(pdStockRecord.inType, 'pd_inType', '')}</span>
									</li>
									<li>
										<label>入库库房：</label>
										<span id="oName">${spd:getStoreroomName(pdStockRecord.inId)}</span>
									</li>
								</ul>
								<ul>
									<li>
										<label>入库日期：</label>
										<span id="outDate"><fmt:formatDate value="${pdStockRecord.recordDate }" pattern="yyyy-MM-dd"/></span>
									</li>
									<li>
										<label>供应商：</label>
										<span>${spd:getSupplierName(pdStockRecord.supplierId)}</span>
									</li>
									<li>
										<c:choose>
											<c:when test="${pdStockRecord.inType=='0' and fns:getUser().storeroomType == '0'}">
												<%--<label>供应商：</label>
                                                <span id="outName">${spd:getSupplierName(pdStockRecord.supplierId)}</span>--%>
											</c:when>
											<c:otherwise>
												<label>出库方：</label>
												<span id="outName">${pdStockRecord.outName }</span>
											</c:otherwise>
										</c:choose>
									</li>
								</ul>
							</div>
							<div>
								<ul style="padding:0;margin:0;text-align:left;">
									<li>
										<label style="display: inline-block;width: 80px;font-size: 13px;">备注：</label>
										<span style="width:auto;font-weight:400;" id="remarks">${pdStockRecord.remarks }</span>
									</li>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<td>产品编号</td>
						<td>产品名称</td>
						<%--<td>注册证号</td>--%>
						<td>规格</td>
						<th>单位</th>
						<%--<td>型号</td>--%>
						<td>数量</td>
						<td>采购单价</td>
						<td>采购金额</td>
						<%--<td>批号</td>--%>
						<%--<td>有效期</td>--%>
						<th>发票号</th>
						<th>发票日期</th>
					</tr>
				</thead>
				<tbody id="printDiv">
					<c:forEach items="${pdStockRecord.productList}" var="product">
						<tr>
							<td>${product.number }</td>
							<td>${product.productName }</td>
							<%--<td>${product.registration}</td>--%>
							<td>${product.spec }</td>
							<td>${product.unit }</td>
							<%--<td>${product.version }</td>--%>
							<td>${product.productNum}</td>
							<td>${product.inPrice}</td>
							<td>${product.pdTotalPrice }</td>
							<%--<td>${product.batchNo }</td>--%>
							<%--<td><fmt:formatDate value="${product.limitDate}" pattern="yyyy-MM-dd"/></td>--%>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
					<tr class="total">
						<td colspan="5" style="text-align:right;">合计数量：${pdStockRecord.productTotNum }</td>
						<td colspan="5" style="text-align:center;">合计金额：${pdStockRecord.sumPdTotalPrice }</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="signBox">
			<h3>主任：___________</h3>
			<h3>采购员：____________</h3>
			<h3>财务审核：____________</h3>
			<h3>日期：${nowDate}</h3>
		</div>
	</div>
</form:form>
<!-- <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script> -->
<script src="${ctxStatic}/spd/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
<script src="${ctxStatic}/jquery/jquery-barcode.js"></script>
<script type="text/javascript">
$(function(){

    //rfidCode:单据号，需要获取
    $('#div_id').empty().barcode('${pdStockRecord.recordNo }', "code128",{
        output:'css',
        color: '#000000',
        barWidth: 2,
        barHeight: 40,
        addQuietZone: false,
        fontSize: 0,
    });

	//通过
	$("#btnPrint").click(function(){
		//event.preventDefault();	
		$("#printBox").jqprint();
	});
	
	//入库类型变更
	$("#inType").change(function(){
		var inType = $("#inType").val();
		if(inType == "0"){
			$("#addFormOrder").show();
			$("#addFormApply").show();
			$("#addFormAllocation").hide();
			$("#addFormDosagert").hide();
		}else if(inType == "1"){
			$("#addFormOrder").hide();
			$("#addFormApply").hide();
			$("#addFormAllocation").hide();
			$("#addFormDosagert").show();
		}else if(inType == "2"){
			$("#addFormOrder").hide();
			$("#addFormApply").hide();
			$("#addFormAllocation").show();
			$("#addFormDosagert").hide();
		}
	});	
	
});
	//显示发票
	function showBillBox(){
		$(".billBox").show();
	} 
	//隐藏发票
	function hideBillBox(){
		$(".billBox").hide();
	}
		
	//查看三证
	$("#sanZheng").click(function(){
		var supplierId = $("#supplierId").val();
		if(supplierId == ""){
			return false;
		}
		layer.open({
			type:2,
			title:"查看三证",
			content:'${ctx}/pd/pdSupplier/pdSupplierThreeCertificateQuery?id='+supplierId,
			area:["800px","450px"],
			shade: [0.8, '#393D49']
		});
	});
</script>
</body>
</html>