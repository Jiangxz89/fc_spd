<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/print.css" media="print">
	<style>
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#allMoney,#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.hcy-btn-deny{background-color: #c71c22;color: white;border-width: 1px;border-style: solid;border-color: #c71c22;border-image: initial;}
		.hcy-btn-deny:hover {background-color: #c71c22;}
		.denyReason{color: #666;font-size: 12px;font-weight:400;}
		.otherText{margin:20px 0;line-height: 40px;}
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;}
		.otherText>h3{font-weight:400;float:left;padding:5px 10px 0 5px;font-size:13px;color:#666;width:72px;}
		.bottomBtn input[type='checkbox']{vertical-align: middle;margin:0 5px 0 0;}
		.bottomBtn label{display: inline-block;color:#666;font-size:13px;}
	</style>
	<title>调拨确认</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
	<div class="right-main-box">
		<div class="btnBox">
			<h4>调拨确认</h4>
		</div>
		<div class="searchBox">
			<input type="hidden" id="allocationId" value="${pdAllocationRecord.id }"/>
			<div>
				<label for="">调拨单号</label>
				<input type="text" style="width:165px;" value="${pdAllocationRecord.allocationCode }" disabled="disabled"/>
			</div>
			<div>
				<label for="">调拨日期</label>
				<input type="text" value="<fmt:formatDate value='${pdAllocationRecord.allocationDate }' pattern='yyyy-MM-dd'/>" disabled="disabled"/>
			</div>
			<div>
				<label for="">操作人员</label>
				<input type="text" value="${pdAllocationRecord.recordPeople }" disabled="disabled"/>
			</div>
			<div>
				<label for="">入库库房</label>
				<input type="text" value="${pdAllocationRecord.inName }" disabled="disabled"/>
			</div>
			<div>
				<label for="">出库库房</label>
				<input type="text" value="${pdAllocationRecord.outName }" disabled="disabled"/>
			</div>
			<div>
				<label for="">审核人</label>
				<input type="text" style="width:165px;" value="${pdAllocationRecord.checkPeople }" disabled="disabled"/>
			</div>
			<div>
				<label for="">审核时间</label>
				<input type="text" value="<fmt:formatDate value='${pdAllocationRecord.checkDate }' pattern='yyyy-MM-dd HH:mm:ss'/>" disabled="disabled"/>
			</div>
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
						<th>本库库房库存数量</th>
						<c:if test="${pdAllocationRecord.recordState eq '0' }">
							<c:if test="${fns:getUser().storeroomType eq '0'}">
								<th>入库库房库存数量</th>
							</c:if>
						</c:if>
						<th>调拨数量</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pdProduct" items="${prodList }">
						<tr class="${pdProduct.id }">
							<td>-</td>
							<td>-</td>
							<td>${pdProduct.name }</td>
							<td>${pdProduct.number }</td>
							<td>${pdProduct.spec }</td>
							<td>${pdProduct.version }</td>
							<td>-</td>
							<td>${pdProduct.unit }</td>
							<td>${pdProduct.stockNum }</td>
							<c:if test="${pdAllocationRecord.recordState eq '0' }">
								<c:if test="${fns:getUser().storeroomType eq '0'}">
									<td>${pdProduct.selfStockNum }</td>
								</c:if>
							</c:if>
							<td>${pdProduct.applyNum }</td>
						</tr>
					</c:forEach>
					<c:forEach var="pdPackage" items="${packageList }">
						<c:forEach var="pdProduct" items="${pdPackage.pdProductList }" varStatus="status">
							<tr class="${pdProduct.id }">
								<c:if test="${status.count eq 1}">
									<td rowspan="${fn:length(pdPackage.pdProductList)}">${pdPackage.name }</td>
									<td rowspan="${fn:length(pdPackage.pdProductList)}">${pdPackage.number}</td>
								</c:if>
								<td>${pdProduct.name }</td>
								<td>${pdProduct.number }</td>
								<td>${pdProduct.spec }</td>
								<td>${pdProduct.version }</td>
								<td>${pdProduct.productNumber }</td>
								<td>${pdProduct.unit }</td>
								<td>${pdProduct.stockNum }</td>
								<c:if test="${pdAllocationRecord.recordState eq '0' }">
									<c:if test="${fns:getUser().storeroomType eq '0'}">
										<td>${pdProduct.selfStockNum }</td>
									</c:if>
								</c:if>
								<c:if test="${status.count eq 1}">
									<td rowspan="${fn:length(pdPackage.pdProductList)}">${pdPackage.applyNum }</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:forEach>
				</tbody>
			</table>
			<div class="totalText">
				<span id="allNum">产品总数量:<span>${pdAllocationRecord.allocationNum }</span></span>
			</div>
		</div>
		<div class="denyReason otherText">
			<c:if test="${pdAllocationRecord.recordState eq '2'}">
				<h3 id="">拒绝理由</h3>
				<textarea class="remarkArea" disabled>${pdAllocationRecord.rejectReason}</textarea>
			</c:if>
		</div>
		<div class="otherText">
			<h3>备注</h3>
			<textarea class="remarkArea" readonly="readonly">${pdAllocationRecord.remarks}</textarea>
		</div>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
			<c:if test="${(pdAllocationRecord.recordState eq '1')||(pdAllocationRecord.recordState eq '3')}">
				<button id="btnSubmitPrint" class="hcy-btn hcy-save" style='margin-top:15px;'/>打印</button>
			</c:if>
			<!-- <input type="checkbox" name="print" /><label for="">打印</label>
			<input type="checkbox" name="outRoom" /><label for="">立即出库</label><br /> -->
			<c:if test="${pdAllocationRecord.recordState eq '0'}">
	   			<button onclick="updateAllocation('1','')" style='margin-top:15px;' class="hcy-btn hcy-btn-primary hcy-btn-pass">通过并出库</button>
	   			<button class="hcy-btn hcy-btn-deny" style='margin-top:15px;'>拒绝</button>
			</c:if>
   			<a href="javascript:history.go(-1);" style='margin-top:15px;' class="hcy-btn hcy-back" >返回</a>
   		</div>
	</div>
	<div id="printBox">
		<div class="printTab">
			<table>
				<thead style="display:table-header-group;">
					<tr>
						<td colspan="8" style="border:0;font-size:20px;color:#202020">${fns:getUser().companyName}<span style="padding-left:40px;">调拨单</span></td>
					</tr>
					<tr>
						<td colspan="8" style="border:0;">
							<div class="printList">
								<ul>
									<li>
										<label>调拨单号：</label>
										<span>${pdAllocationRecord.allocationCode }</span>
									</li>
									<li>
										<label>调拨日期：</label>
										<span><fmt:formatDate value='${pdAllocationRecord.allocationDate }' pattern='yyyy-MM-dd'/></span>
									</li>
									<li>
										<label>出库库房：</label>
										<span>${pdAllocationRecord.outName }</span>
									</li>
								</ul>
								<ul>
									<li>
										<label>入库库房：</label>
										<span>${pdAllocationRecord.inName }</span>
									</li>
									<li>
										<label>审核人：</label>
										<span>${pdAllocationRecord.checkPeople }</span>
									</li>
									<li>
										<label>审核时间：</label>
										<span><fmt:formatDate value='${pdAllocationRecord.checkDate }' pattern='yyyy-MM-dd HH:mm:ss'/></span>
									</li>
								</ul>
							</div>
							<div>
								<ul style="padding:0;margin:0;text-align:left;">
									<li style="height:40px;line-height:40px;">
										<label style="display: inline-block;width: 90px;font-size: 14px;">备注：</label>
										<span style="width:auto;font-weight:400;" id="remarks">${pdAllocationRecord.remarks}</span>
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
						<th>调拨数量</th>
					</tr>
				</thead>
				<tbody id="printDiv">
					<c:forEach var="pdProduct" items="${prodList }">
						<tr class="${pdProduct.id }">
							<td>-</td>
							<td>-</td>
							<td>${pdProduct.name }</td>
							<td>${pdProduct.number }</td>
							<td>${pdProduct.spec }</td>
							<td>${pdProduct.version }</td>
							<td>-</td>
							<td>${pdProduct.unit }</td>
							<td>${pdProduct.applyNum }</td>
						</tr>
					</c:forEach>
					<c:forEach var="pdPackage" items="${packageList }">
						<c:forEach var="pdProduct" items="${pdPackage.pdProductList }" varStatus="status">
							<tr class="${pdProduct.id }">
								<c:if test="${status.count eq 1}">
									<td rowspan="${fn:length(pdPackage.pdProductList)}">${pdPackage.name }</td>
									<td rowspan="${fn:length(pdPackage.pdProductList)}">${pdPackage.number}</td>
								</c:if>
								<td>${pdProduct.name }</td>
								<td>${pdProduct.number }</td>
								<td>${pdProduct.spec }</td>
								<td>${pdProduct.version }</td>
								<td>${pdProduct.productNumber }</td>
								<td>${pdProduct.unit }</td>
								<c:if test="${status.count eq 1}">
									<td rowspan="${fn:length(pdPackage.pdProductList)}">${pdPackage.applyNum }</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="signBox">
			<h3>仓库人员签字：________</h3>
			<h3>调拨人员签字：________</h3>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<!-- <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script> -->
	<script src="${ctxStatic}/spd/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript">
		$(function(){
			//打印
			$("#btnSubmitPrint").click(function(){
				$("#printBox").jqprint();
			})
			//拒绝
			$(document).on("click",".hcy-btn-deny",function(){
				layer.open({
				  title: '拒绝理由'
				  ,area: ['390px', '260px']
			      ,shade: 0
			      ,maxmin: true
				  ,btn: ['确定', '取消']
				  ,content: '<textarea id="denyText" maxlength="400" style="width:280px;height:90px;"></textarea>'
				  ,yes: function(index, layero){
				  	var denyText = $("#denyText").val();
				  	layer.close(index);
				  	updateAllocation('2',denyText);
				  },
				  btn2:function(){
					layer.closeAll();
				  }
				}); 
			});
			//删除发票
			$(document).on("click",".billTable .fa-trash-o",function(){
				$(this).parents("tr").remove()
			})
		})
		//改变调拨单状态
		function updateAllocation(status,denyText){
			var id = $("#allocationId").val();
			$.ajax({
				data:{id:id,recordState:status,rejectReason:denyText},
				type:"post",
				url:"${ctx}/pd/pdAllocationRecord/updateAllocation",
				success:function(data){
					if(data.code=='200'){
						window.location.href = '${ctx}/pd/pdStockRecord/pdPassAddStockRecord?allocationNo=${pdAllocationRecord.allocationCode}&&flag=0';
					}else if(data.code=='0'){
						window.location = "${ctx}/pd/pdAllocationRecord/examineList"
					}else{
						var prod = data.data;
						/* layer.open({
							title: '警告'
						 	,content: '<font size="4" color="red">'+prod.name+'</font><br>'+'库存已不足，无法审核！'//+'库存数量：'+prod.stockNum+'，调拨数量：'+prod.applyNum
						}); */
						layer.alert('调拨数量已大于库存数量', {icon:0});
						$('.'+prod.id).css('background-color', 'red');
					}
				}
			})
		}
		
	</script>
</body>
</html>