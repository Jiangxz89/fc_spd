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
		.addRoomBox{line-height: 40px;padding:10px 5px;margin-bottom: 20px;}
		.addRoomBox label{width:75px;display: inline-block;text-align: left;}
		.addRoomBox>input[type='text'],.addRoomBox>select{display:inline-block;width: 160px;height:30px;border:1px solid #ccc;margin:0 10px 0 5px;}
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#allMoney,#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.otherText{margin:20px 0;line-height: 40px;}
		.otherText>h3{font-size:14px;color:#666;font-weight: 600;}
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;margin:0;}
		.otherText>h3{font-weight:400;float:left;padding:5px 10px 0 5px;line-height:30px;width:72px;font-size:13px;}
	</style>
	<title>申请调拨详情</title>
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
			<h4>调拨详情</h4>
		</div>
		<div class="searchBox">
			<label for="">调拨单号</label>
			<input type="text" style="width:165px;" value="${pdAllocationRecord.allocationCode }" disabled="disabled"/>
			<label for="">调拨日期</label>
			<input type="text" value="<fmt:formatDate value='${pdAllocationRecord.allocationDate }' pattern='yyyy-MM-dd'/>" disabled="disabled"/>
			<label for="">操作人员</label>
			<input type="text" value="${pdAllocationRecord.recordPeople }" disabled="disabled"/><br />
			<label for="">入库库房</label>
			<input type="text" style="width:165px;" value="${pdAllocationRecord.inName }" disabled="disabled"/>
			<label for="">出库库房</label>
			<input type="text" value="${pdAllocationRecord.outName }" disabled="disabled"/>
			<label for="">审核人</label>
			<input type="text" value="${pdAllocationRecord.checkPeople }" disabled="disabled"/>
			<label for="">审核时间</label>
			<input type="text" value="<fmt:formatDate value='${pdAllocationRecord.checkDate }' pattern='yyyy-MM-dd HH:mm:ss'/>" disabled="disabled"/>
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
								<th>出库库房库存数量</th>
							</c:if>
						</c:if>
						<th>申请数量</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pdProduct" items="${prodList }">
						<tr>
							<td>-</td>
							<td>-</td>
							<td>${pdProduct.name }</td>
							<td>${pdProduct.number }</td>
							<td>${pdProduct.spec }</td>
							<td>${pdProduct.version }</td>
							<td>-</td>
							<td>${pdProduct.unit }</td>
							<td>${pdProduct.selfStockNum }</td>
							<c:if test="${pdAllocationRecord.recordState eq '0' }">
								<c:if test="${fns:getUser().storeroomType eq '0'}">
									<td>${pdProduct.stockNum }</td>
								</c:if>
							</c:if>
							<td>${pdProduct.applyNum }</td>
						</tr>
					</c:forEach>
					<c:forEach var="pdPackage" items="${packageList }">
						<c:forEach var="pdProduct" items="${pdPackage.pdProductList }" varStatus="status">
							<tr>
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
								<td>${pdProduct.selfStockNum }</td>
								<c:if test="${pdAllocationRecord.recordState eq '0' }">
									<c:if test="${fns:getUser().storeroomType eq '0'}">
										<td>${pdProduct.stockNum }</td>
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
			<div class="otherText">
				<c:if test="${pdAllocationRecord.recordState eq '2'}">
					<h3 id="">拒绝理由</h3>
					<textarea class="remarkArea" readOnly>${pdAllocationRecord.rejectReason}</textarea>
				</c:if>
			</div>
			<div class="otherText" style="margin:20px 0;">
				<h3>备注</h3>
				<textarea class="remarkArea" readonly="readonly">${pdAllocationRecord.remarks}</textarea>
			</div>
		</div>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
			<c:if test="${pdAllocationRecord.recordState eq '2' }">
	   			<input id="btnSubmitPrint" type="submit" value="打印" class="hcy-btn hcy-btn-primary" />
			</c:if>
   			<a href="javascript:history.go(-1);" class="hcy-btn hcy-back">返回</a>
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
	<script src="${ctxStatic}/spd/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript">
		$(function(){
			
			//打印
			$("#btnSubmitPrint").click(function(){
				$("#printBox").jqprint();
			})
			
			//添加发票
			$(document).on("click",".fa-plus-square",function(){
				var newHtml='';
				newHtml='<tr>'+
							'<td><i class="fa fa-plus-square"></i><i class="fa fa-trash-o"></i></td>'+
							'<td><input type="text" /></td>'+
							'<td><input type="text" /></td>'+
							'<td><input type="text" /></td>'+
							'<td><input type="text" /></td>'+
						'</tr>'
				$(".billTable>tbody").append(newHtml)
			});
			//删除发票
			$(document).on("click",".billTable .fa-trash-o",function(){
				$(this).parents("tr").remove()
			})
		})
	</script>
</body>
</html>