<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>出库记录查看</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/print.css" media="print">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<style>
		.addChargeCodeInp{width:100%;height:100px;margin:5px;resize:none;border:1px solid #ccc;}
		.otherText{margin:20px 0;height:60px;}
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align: text-top;}
		.otherText>h3{font-weight:400;float:left;padding:0 10px 0 5px;width:73px;font-size:14px;color:#666;line-height:30px;}
		.layui-layer-content{text-align:center;padding:0 15px;}
		.allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.totalText{text-align: right;height: 50px;line-height: 50px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#searchForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
					//printFn();
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
	<div class="right-main-box">
		<div class="btnBox">
			<h4>出库单查看</h4>			
		</div>	
		<form:form id="searchForm" modelAttribute="pdStockRecord" action="${ctx}/pd/pdStockRecord/examineOut" method="post" class="breadcrumb form-search">
		<div class="searchBox">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input id="recordState" type="hidden" name="recordState" value="1">
			<form:hidden path="id"/>
				<div>
					<label>出库单号</label>
					<form:input path="recordNo" htmlEscape="false" style="width:165px;" maxlength="64" class="input-medium" value="${pdStockRecord.recordNo }" readonly="readonly"/>				
				</div>
				<div>
					<label>出库日期</label>
					<input id="recordDate" type="text" htmlEscape="false" maxlength="64" class="input-medium" value="<fmt:formatDate value="${pdStockRecord.recordDate}" pattern="yyyy-MM-dd"/>" readonly="readonly"/>
				</div>		
				<div>
					<label>出库类型 </label>					
					<form:input path="recodeType" htmlEscape="false" maxlength="64" class="input-medium" value="${fns:getDictLabel(pdStockRecord.outType, 'stock_out_type', '')}" readonly="readonly"/>				
				</div>
				<div>
					<label>操作人员</label>
					<form:input path="recordPeople" htmlEscape="false" maxlength="64" class="input-medium" value="${pdStockRecord.recordPeople}" readonly="readonly"/>
				</div><br>
				<div>
					<label>出库库房</label>
					<form:input path="outId" htmlEscape="false" style="width:165px;" maxlength="64" class="input-medium" value="${spd:getStoreroomName(pdStockRecord.outId)}" readonly="readonly"/>
				</div>
				<div>
					<label for="">入库库房</label>
					<form:input path="inId" htmlEscape="false"  maxlength="64" class="input-medium" value="${spd:getStoreroomName(pdStockRecord.inId)}" readonly="readonly"/>
				</div>
				<c:if test="${pdStockRecord.recordState!=0}">
					<div>
						<label>审核人员</label>
						<form:input path="checkPeople" htmlEscape="false" maxlength="64" class="input-medium" value="${pdStockRecord.checkPeople }" readonly="readonly"/>				
					</div>
					<div>
						<label>审核时间</label>
						<input id="checkTime" type="text" htmlEscape="false" maxlength="64" class="input-medium" value="<fmt:formatDate value="${pdStockRecord.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"/>
					</div>
				</c:if>
		</div>	
		<div class="tableBox">
				<table id="contentTableTbody2" class="hcy-public-table" >
					<thead>
						<tr>							
							<th>产品名称</th>
							<th>产品编号</th>
							<th>规格</th>
							<th>批号</th>
							<th>单位</th>
							<th>产品条码</th>							
							<th>型号</th>
							<th>入库单价</th>
							<th>出库单价</th>
							<th>出库数量</th>
							<th>入库金额</th>
							<th>出库金额</th>
							<th>库存数量</th>							
							<th>单号</th>												
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pdStockRecord.productList }" var="pdStockRecordProduct">
						<tr>
							<td>${pdStockRecordProduct.productName }</td>
							<td>${pdStockRecordProduct.number }</td>
							<td>${pdStockRecordProduct.spec }</td>
							<td>${pdStockRecordProduct.batchNo }</td>
							<td>${pdStockRecordProduct.unit }</td>
							<td>${pdStockRecordProduct.productBarCode }</td>
							<td>${pdStockRecordProduct.version }</td>

							<td>${pdStockRecordProduct.inPrice }</td>
							<td>${pdStockRecordProduct.outPrice }</td>
							<td>${pdStockRecordProduct.productNum }</td>
							<td>${pdStockRecordProduct.pdTotalPrice }</td>
							<td class="pdOutTotalPrice">${pdStockRecordProduct.pdOutTotalPrice }</td>
							<td>${pdStockRecordProduct.stockNum }</td>
							<c:if test="${not empty pdStockRecord.applyNo }"><td>${pdStockRecord.applyNo }</td></c:if>						
							<c:if test="${not empty pdStockRecord.allocationNo}"><td>${pdStockRecord.allocationNo}</td></c:if>
							<c:if test="${pdStockRecord.outType==1}"><td>--</td></c:if>							
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="totalText">
					<span class="allNum">总数量:<span class="total_number">${pdStockRecord.productTotNum}</span></span>
					<span class="allNum">入库总金额:<span class="total_price">${pdStockRecord.sumPdTotalPrice}</span></span>
					<span class="allNum">出库总金额:<span class="total_price">${pdStockRecord.sumPdOutTotalPrice}</span></span>
				</div>
			</div>
			<div class="otherText">
				<c:if test="${pdStockRecord.recordState==2}">					
					<h3>驳回理由</h3>
					<form:textarea path="rejectReason"  htmlEscape="false" maxlength="64" class="remarkArea" value="${pdStockRecord.rejectReason }" readonly="readonly"/>				
				</c:if>
			</div>
			<div class="otherText">
				<h3>备注</h3>
				<textarea name="remarkArea" class="remarkArea">${pdStockRecord.remarks}</textarea>
			</div>
			<div class="form-actions" style="background:#fff;border:none;text-align:center;padding:0;margin-top:30px;">
				<c:if test="${pdStockRecord.recordState==1||pdStockRecord.recordState==2}">
				<shiro:hasPermission name="pd:pdStockRecord:edit"><input id="btnSubmitPrint" class="hcy-btn hcy-save" type="button" value="打印"/>&nbsp;</shiro:hasPermission>
				
				</c:if>
				<c:if test="${not empty isOut}">
					<c:if test="${pdStockRecord.recordState==0}">
					<input id="btnSubmitYes" class="hcy-btn hcy-save" type="button" value="通过并打印"/>&nbsp;
					<input id="btnSubmitRefuse" type="button" class="hcy-btn hcy-save" value="整单驳回"/>&nbsp;
					</c:if>
				</c:if>
				<input id="btnCancel" class="hcy-btn hcy-back" type="button" value="返 回" onclick="returnPage()"/>
			</div>
		</form:form>	
	<sys:message content="${message}"/>	
	<div class="addChargeCodeBox">
			<textarea style="margin:20px 0 0 0;height:160px;" class="addChargeCodeInp"></textarea>
		</div>
	</div>
	<div id="printBox">
		<div style="margin-left: 50%" id="div_id"></div>
		<%-- <div class="printHeader">
			<h3>${fns:getUser().companyName}</h3>
			<h3>出库单</h3>
		</div>
		<div class="printPage">
			页码：1/1
		</div> --%>
		<div class="printTab">
			<table>
				<thead style="display:table-header-group;">
					<tr>
						<td colspan="8" style="border:0;font-size:20px;color:#202020;">${fns:getUser().companyName}<span style="padding-left:40px;">出库单</span></td>
					</tr>
					<tr>
						<td colspan="8" style="border:0;">
							<div class="printList">
								<ul>
									<li>
										<label>出库单号：</label>
										<span>${pdStockRecord.recordNo }</span>
									</li>
									<li>
										<label>出库类型：</label>
										<span>${fns:getDictLabel(pdStockRecord.outType, 'stock_out_type', '')}</span>
									</li>
									<li>
										<label>出库库房：</label>
										<span>${spd:getStoreroomName(pdStockRecord.outId)}</span>
									</li>
									<li>
										<label>审核人员：</label>
										<span id="chUser">${pdStockRecord.checkPeople}</span>
									</li>
								</ul>
								<ul>
									<li>
										<label>出库日期：</label>
										<span><fmt:formatDate value="${pdStockRecord.recordDate}" pattern="yyyy-MM-dd"/></span>
									</li>
									<li>
										<label>操作人员：</label>
										<span>${pdStockRecord.recordPeople}</span>
									</li>
									<li>
										<label>入库库房：</label>
										<span>${spd:getStoreroomName(pdStockRecord.inId)}</span>
									</li>
									<li>
										<label>审核时间：</label>
										<span id="chTime"><fmt:formatDate value="${pdStockRecord.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
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
						<th>产品编号</th>
						<th>产品名称</th>
						<th>规格</th>
						<th>单位</th>
						<%--<th>型号</th>--%>
						<th>数量</th>
						<th>入库单价</th>
						<th>入库金额</th>
						<th>出库单价</th>
						<th>出库金额</th>
						<%--<th>有效期</th>--%>
						<th>批号</th>
						<th>生产厂商</th>
					</tr>
				</thead>
				<tbody id="printDiv">
					<c:forEach items="${pdStockRecord.productList }" var="pd">
						<tr>
							<td>${pd.number }</td>
							<td>${pd.productName }</td>
							<td>${pd.spec }</td>
							<td>${pd.unit }</td>
								<%--<td>${pd.version }</td>--%>
							<td>${pd.productNum }</td>
								<td>${pd.inPrice }</td>
								<td>${pd.pdTotalPrice }</td>
							<td>${pd.outPrice }</td>
							<td>${pd.pdOutTotalPrice }</td>
								<%--<td>${pd.limitDateStr}</td>--%>
							<td>${pd.batchNo }</td>
							<td>${pd.venderName }</td>
						</tr>
					</c:forEach>
					<tr class="total">
						<td colspan="5" style="text-align:right;">
							合计数量：${pdStockRecord.productTotNum }
						</td>
						<td colspan="3" style="text-align:right;">
						合计入库金额：${pdStockRecord.sumPdTotalPrice }
						</td>
						<td colspan="6" style="text-align:center;">
							合计出库金额：${pdStockRecord.sumPdOutTotalPrice }
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="signBox">
			<h3>出库管理：________</h3>
			<h3>领料人：________</h3>
			<h3>日期：${nowDate}</h3>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<!-- <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script> -->
	<script src="${ctxStatic}/spd/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
	<script src="${ctxStatic}/jquery/jquery-barcode.js"></script>
	
	<script type="text/javascript">

        //rfidCode:单据号，需要获取
        $('#div_id').empty().barcode('${pdStockRecord.recordNo }', "code128",{
            output:'css',
            color: '#000000',
            barWidth: 2,
            barHeight: 40,
            addQuietZone: false,
            fontSize: 0,
        });

		$(document).ready(function() {	
			$("#btnSubmitPrint").click(function(){
				printFn();
			})
			$("#btnSubmitRefuse").click(function(){
				layer.open({
					type:1,
					title:"驳回出库单",
					content:$(".addChargeCodeBox").html(),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						loading('正在提交，请稍等...');
						var val=layero.find(".addChargeCodeInp").val();	
						if(!val){
							layer.alert('请填写驳回原因',{icon: 0});
							return false;
						}
						window.location = "${ctx}/pd/pdStockRecord/rejectAll?rejectReason="+val+"&recordState=2&id=${pdStockRecord.id}";
						layer.closeAll();
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			})
			//通过并打印
			$("#btnSubmitYes").click(function(){				
				var flag=true;
				$(".hcy-public-table tbody>tr").each(function(){
					var oCount=$(this).find("td").eq(9).text();
					var sCount=$(this).find("td").eq(12).text();
					if(oCount&&sCount){
					if(parseInt(oCount)>parseInt(sCount)){
						$(this).css("background-color","red");
						flag=false;
					}}					
				})
				if(flag){
					var id = $("#id").val();
					var urlPath ='${ctx}/pd/pdStockRecord/examineOut';
					loading('正在提交，请稍等...');
					$.ajax({
				 		url:urlPath,
				 		type:"post",
						data:{id:id},  
						async:false, 
						success:function(data){
							layer.closeAll();
							if(data.code == "200"){
								$("#btnSubmitYes").hide();
								$("#btnSubmitRefuse").hide();
								var obj=data.data;								
								var t=new Date(obj.checkTime).toLocaleString();
								$("#chTime").html(t);
								$("#chUser").html(obj.checkPeople);
								printFn();
							}else{
								var message = data.message;
								layer.alert(message,{icon: 0});
							}
						}
					});
				}else{
					layer.alert('出库数量已大于库存数量', {icon:0});
				}
				
			});
		});
		function printFn(){			
			//打印
			//event.preventDefault();	
			$("#printBox").jqprint();			
		}
		//扩展原型方法
	 	 Date.prototype.toLocaleString = function() {
        	var mon=this.getMonth()+1;
        	var day=this.getDate();
        	var h=this.getHours();
        	var m=this.getMinutes();
        	var s=this.getSeconds();
           return this.getFullYear() + "-" + getzf(mon) + "-" + getzf(day)+ " " + getzf(h) + ":" + getzf(m) + ":" + getzf(s);
        };
        //补0  
        function getzf(num){    
            if(parseInt(num) < 10){    
                num = '0'+num;    
            }    
            return num;    
        }  
        function returnPage(){
        	window.location.href="${ctx}/pd/pdStockRecord/outExamineList";
        }


	</script>
	
</body>
</html>