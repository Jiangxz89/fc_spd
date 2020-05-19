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
		.layui-layer-content{text-align:center;padding:0 15px;}
		.layui-layer-content #refuseArea{width:100%;height:150px;border:1px solid #ccc;padding:5px;margin-top: 20px;}
		.allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.totalText{text-align: right;height: 50px;line-height: 50px;}
	</style>
	<title>入库审核</title>
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//防止修改
			/* if($("#id").val() != null && $("#id").val() != "" ){
				$("#btnSubmit").attr("style","display:none;"); 
			} */
			//判断发票是否显示
			var ono = "${pdStockRecord.orderNo}";
			if(ono != null && ono != ""){
				showBillBox();
			}
			
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
	</script>
</head>
<body>
	<form:form id="inputForm" style="margin:0;" modelAttribute="pdStockRecord" action="${ctx}/pd/pdStockRecord/examineIn" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="right-main-box">
			<div class="btnBox">
				<h4>入库审核</h4>
			</div>
			<div class="searchBox">
				<div>
					<label for="">入库单号</label>
					<form:input path="recordNo" htmlEscape="false" maxlength="64" class="input-xlarge " readOnly="true"/>
				</div>
				<div>
				<label for="">入库日期</label>
				<input name="recordDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pdStockRecord.recordDate}"/>" />
				</div>
				<div>
					<label for="">入库类型</label>
					<form:select path="inType" disabled="true" class="input-medium">
						<form:option value="" label="全部"/>
						<form:options items="${fns:getDictList('stock_in_type') }" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<br>
				<div>
					<label for="">操作人员</label>
					<form:input path="recordPeople" htmlEscape="false" maxlength="64" class="input-xlarge " readOnly="true"/>
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
					<label for="">出库方</label>
					<form:input path="outName" htmlEscape="false" readOnly="true" maxlength="64" class="input-medium"/>
				</div>
			</c:otherwise>
			</c:choose>
				<form:hidden path="orderNo" id="orderNo"/>
				<form:hidden path="applyNo" id="applyNo"/>
				
			</div>
			<div class="tableBox">
				<table id="contentTableTbody2" class="hcy-public-table" >
					<thead>
						<tr>
							<th>产品名称</th>
							<th>产品编号</th>
							<th>产品条码</th>
							<th>规格</th>
							<th>型号</th>
							<th>单位</th>
							<th>批号</th>
							<th>有效期</th>
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
								<td>${product.productName }</td>
								<td>${product.number}</td>
								<td>${product.productBarCode}</td>
								<td>${product.spec }</td>
								<td>${product.version }</td>
								<td>${product.unit }</td>
								<td>${product.batchNo }</td>
								<td><fmt:formatDate value="${product.limitDate}" pattern="yyyy-MM-dd"/></td>
								<td>${product.productNum}</td>
								<td>${product.inPrice}</td>
								<td>${product.pdTotalPrice}</td>
								<td>${product.stockPosition}</td>
								<td><a href='###' class='overLook' title="${product.importNo}">${product.importNo}</a></td>
								<td><a href="###" title="${product.registration }" class="overLook">${product.registration }</a></td>
								<td><a href="###" class="overLook" title="${product.venderName }">${product.venderName }</a></td>
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
				<h4 style="padding:10px 0 20px 0;font-weight:400;">发票信息</h4>
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
								<td>${invoice.invoiceDate }</td>
								<td>${invoice.invoiceNo }</td>
								<td>${invoice.invoiceAmount }</td>
								<td>${invoice.saleDate }</td>
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
				<br />
				<label for="">温度</label>
				<form:input path="temperature" htmlEscape="false" maxlength="3" readonly="readonly" class="input-xlarge "/>
				<span>℃</span>
				<label for="">湿度</label>
				<form:input path="humidity" htmlEscape="false" maxlength="3" readonly="readonly" class="input-xlarge "/>
				<span>%</span>
			</div>
			<div class="otherText">
				<h3>备注</h3>
				<form:textarea path="remarks" class="remarkArea" readonly="readonly" maxlength="200"></form:textarea>
			</div>
			<c:if test="${pdStockRecord.recordState == 2}">
				<div id="refuseBox" class="refuseBox otherText" style=";">
					<h3>拒绝理由</h3>
					<form:textarea path="rejectReason" htmlEscape="false" rows="4" maxlength="255" class="remarkArea"/>
				</div>			
			</c:if>
			<div class="form-actions" style="background:#fff;border:none;text-align:center;padding:0;margin-top:30px;">
				<c:if test="${pdStockRecord.recordState == 0}">
					<shiro:hasPermission name="pd:pdStockRecord:edit"><input id="btnSubmit" class="hcy-btn hcy-btn-primary" type="button" value="通过并打印"/>&nbsp;</shiro:hasPermission>
					<shiro:hasPermission name="pd:pdStockRecord:edit"><input id="reBtn" class="hcy-btn hcy-btn-primary" type="button" value="整单驳回"/>&nbsp;</shiro:hasPermission>				
				</c:if>
				<input id="btnCancel" class="hcy-btn hcy-back" type="button" value="返 回"/>
			</div>
		</div>
	</form:form>
	<div class="refuseBox" style="display:none;">
		<form:form id="inputForm2" style="margin:0;" modelAttribute="pdStockRecord" action="${ctx}/pd/pdStockRecord/rejectAll" method="post" class="form-horizontal">
			<form:textarea id="refuseArea" path="rejectReason" htmlEscape="false" rows="4" maxlength="255"/>
			<form:hidden path="id" />
		</form:form>
	</div>
	<div id="printBox">
		<%-- <div class="printHeader">
			<h3>${fns:getUser().companyName}</h3>
			<h3>入库单</h3>
		</div>
		<div class="printPage">
			页码：1/1
		</div> --%>
		<div class="printTab">
			<table>
				<thead style="display:table-header-group;">
					<tr>
						<td colspan="8" style="border:0;font-size:20px;color:#202020;">${fns:getUser().companyName}<span style="padding-left:40px;">入库单</span></td>
					</tr>
					<tr>
						<td colspan="8" style="border:0;">
							<div class="printList">
								<ul>
									<li>
										<label>入库单号：</label>
										<span id="outNo">${pdStockRecord.recordNo }</span>
									</li>
									<li>
										<label>入库日期：</label>
										<span id="outDate"><fmt:formatDate value="${pdStockRecord.recordDate }" pattern="yyyy-MM-dd"/></span>
									</li>
									<li>
										<label>入库库房：</label>
										<span id="oName">${spd:getStoreroomName(pdStockRecord.inId)}</span>
									</li>
								</ul>
								<ul>
									<li>
										<c:choose>
										<c:when test="${pdStockRecord.inType=='0' and fns:getUser().storeroomType == '0'}">
											<label>供应商：</label>
											<span id="outName">${spd:getSupplierName(pdStockRecord.supplierId)}</span>
										</c:when>
										<c:otherwise>
											<label>出库方：</label>
											<span id="outName">${pdStockRecord.outName }</span>
										</c:otherwise>
										</c:choose>
									</li>
									<li>
										<label>入库类型：</label>
										<span>${fns:getDictLabel(pdStockRecord.inType, 'pd_inType', '')}</span>
									</li>
									<li>
										<label>供应商：</label>
										<span>${spd:getSupplierName(pdStockRecord.supplierId)}</span>
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
						<th>产品名称</th>
						<%--<th>注册证号</th>--%>
						<th>规格</th>
						<th>单位</th>
						<%--<th>型号</th>--%>
						<th>数量</th>
						<th>采购单价</th>
						<th>采购金额</th>
						<%--<th>批号</th>--%>
						<%--<th>有效期</th>--%>
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
							<td>${product.pdTotalPrice}</td>
							<%--<td>${product.batchNo }</td>--%>
							<td><fmt:formatDate value="${product.limitDate}" pattern="yyyy-MM-dd"/></td>
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
	<!-- <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script> -->
	<script src="${ctxStatic}/spd/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript">
		$(function(){
			//拒绝
			$("#reBtn").click(function(){
				layer.open({
					type:1,
					title:"整单驳回理由",
					content:$(".refuseBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						loading('正在提交，请稍等...');
						$("#inputForm2").submit();
						layer.closeAll();
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			});
			
			//通过
			$("#btnSubmit").click(function(){
				var id = $("#id").val();
				var remarks = $("#remarks").val();
				var urlPath ='${ctx}/pd/pdStockRecord/examineIn';
				loading('正在提交，请稍等...');
				$.ajax({
			 		url:urlPath,
			 		type:"post",
					data:{id:id,remarks:remarks},  
					async:false, 
					success:function(data){
						layer.closeAll();
						if(data.code == "200"){
							$("#btnSubmit").hide();
							$("#reBtn").hide();
							printFn();
							//location.href="${ctx}/pd/pdStockRecord/inExamineList";
						}else{
							var message = data.message;
							layer.alert(message,{icon: 0});
						}
					},
					error:function(){
				    	layer.closeAll();
				    	layer.alert(("入库审核失败！",{icon:0}));
					}
				});
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
		//显示发票
		function showRefuseBox(){
			$("#refuseBox").show();
		} 
		//隐藏发票
		function hideRefuseBox(){
			$("#refuseBox").hide();
		}
		//打印
		function printFn(){
			//event.preventDefault();	
			$("#printBox").jqprint();
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
		
		$("#btnCancel").click(function(){
			window.location.href="${ctx}/pd/pdStockRecord/inExamineList";
		});
	</script>
</body>
</html>