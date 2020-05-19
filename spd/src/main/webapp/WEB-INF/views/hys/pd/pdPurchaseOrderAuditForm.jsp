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
		#refuseBtn{background:red;border-color: red;}
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;}
		.otherText>h3{font-weight:400;display:inline-block;padding:3px 10px 0 5px;font-size:12px;color:#666;width:70px;}
	</style>
	<title>采购审核单详情</title>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>采购审核详情</h4>
		</div>
		<form:form id="searchForm" modelAttribute="pdPurchaseOrder" action="${ctx}/pd/pdPurchaseOrder/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="searchBox">
				<label for="">采购单号</label>
				<input type="text" style="width:170px;" value="${pdPurchaseOrder.orderNo }" readonly="readonly"/>
				<label for="">申购科室</label>
				<input type="text" value="${pdPurchaseOrder.deptName }" readonly="readonly"/>
				<label for="">操作人员</label>
				<input type="text" value="${pdPurchaseOrder.purchaseName }" readonly="readonly"/>
			</div>
		</form:form>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>产品名称</th>
						<th>产品编号</th>
						<th>规格</th>
						<th>型号</th>
						<th>单位</th>
						<!-- <th>有效期</th> -->
						<th>库存数量</th>
						<c:if test="${oprt == 'audit' }">
							<th>本科室库存</th>
						</c:if>
						<th>申购数量</th>
						<th>产品单价</th>
						<th>金额</th>
						<th>生产厂家</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${detailList }" var="li">
						<tr>
							<td><a href="###"  title="${li.prodName }">${li.prodName }</a></td>
							<td><a href="###" title="${li.prodNo }"  >${li.prodNo }</a></td>
							<td>${li.prodSpec }</td>
							<td>${li.prodVersion }</td>
							<td>${li.prodUnit }</td>
							<td>${li.stockNum }</td>
							<c:if test="${oprt == 'audit' }">
								<td>${li.myStockNum }</td>
							</c:if>
							<td>${li.applyCount }</td>
							<%-- <td>3</td>--%>
							<td>${li.prodPrice }</td>
							<td>${li.amountMoney }</td>
							<td>${li.venderName }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="totalText">
				<span id="allNum">总数量:<span>${pdPurchaseOrder.amountCount }</span></span>
				<span id="allMoney">总金额:<span>${pdPurchaseOrder.amountMoney }</span></span>
			</div>
			<div class="otherText" style="margin-top: 30px;">
				<h3>备注</h3>
				<textarea name="remarks" class="remarkArea" readonly="readonly">${pdPurchaseOrder.remarks }</textarea>
			</div>
			<c:if test="${pdPurchaseOrder.orderStatus eq 2 }">
				<div class="refuseBox otherText">
					<h3>拒绝理由</h3>
					<textarea class="remarkArea" readonly="readonly">${pdPurchaseOrder.refuseReason }</textarea>
				</div>
			</c:if>
		</div>
		<div class="pagination">${page}</div>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
			<c:if test="${oprt=='audit' }">
   				<a href="###" class="hcy-btn hcy-btn-primary" id="printBtn">通过并打印</a>
   				<a href="###" class="hcy-btn hcy-btn-primary" id="refuseBtn">拒绝</a>
   			</c:if>
   			<a href="javascript:location.href=document.referrer" class="hcy-btn hcy-back">返回</a>
   		</div>
	</div>
	<div class="refuseBox" style="display:none;">
		<h4 style="text-align:left;padding:10px 0;">拒绝理由</h4>
		<textarea id="refuseArea" style="width:100%;height:130px;" maxlength="100"></textarea>
	</div>
	<div id="printBox">
		<div class="printHeader">
			<h3>${fns:getUser().companyName }</h3>
			<h3>采购单</h3>
		</div>
		<div class="printPage">
			页码：1/1
		</div>
		<div class="printList">
			<ul>
				<li>
					<label>采购单号：</label>
					<span id="outNo">${pdPurchaseOrder.orderNo }</span>
				</li>
				<li>
					<label>操作人员：</label>
					<span id="outDate">${pdPurchaseOrder.purchaseName }</span>
				</li>
				<li>
					<label>审核时间：</label>
					<span id="shenDate"></span>
				</li>
				<li>
					<label>备注：</label>
					<span id="oName">${pdPurchaseOrder.remarks }</span>
				</li>
			</ul>
			<ul>
				<li>
					<label>申领科室：</label>
					<span id="iName">${pdPurchaseOrder.deptName }</span>
				</li>
				<li>
					<label>审核人员：</label>
					<span>${fns:getUser().name }</span>
				</li>
			</ul>
		</div>
		<div class="printTab">
			<table>
				<thead>
					<tr>
						<th>产品名称</th>
						<th>产品编号</th>
						<th>规格</th>
						<th>型号</th>
						<th>单位</th>
						<th>库存数量</th>
						<th>申购数量</th>
						<th>产品单价</th>
						<th>金额</th>
						<th>生产厂家</th>
					</tr>
				</thead>
				<tbody id="printDiv">
					<c:forEach items="${detailList }" var="li">
						<tr>
							<td>${li.prodName }</td>
							<td>${li.prodNo }</td>
							<td>${li.prodSpec }</td>
							<td>${li.prodVersion }</td>
							<td>${li.prodUnit }</td>
							<td>${li.stockNum }</td>
							<td>${li.applyCount }</td>
							<td>${li.inPrice }</td>
							<td>${li.amountMoney }</td>
							<td>${li.venderName }</td>
						</tr>
					</c:forEach>
					<tr class="total">
						<td colspan="7" style="text-align:right;">数量合计：${pdPurchaseOrder.amountCount }</td>
						<td colspan="2" style="text-align:right;">金额合计：${pdPurchaseOrder.amountMoney }</td>
						<td colspan="1" style="text-align:right;"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="signBox">
			<h3>操作人员签字：__________</h3>
			<h3>审核人员签字：__________</h3>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<!-- <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script> -->
	<script src="${ctxStatic}/spd/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript">
		$(function(){
			//拒绝
			$("#refuseBtn").click(function(){
				layer.open({
					type:1,
					title:"拒绝采购申请",
					content:$(".refuseBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var refrea = $.trim($('#refuseArea').val());
						if(!refrea){
							layer.alert('请输入拒绝理由',{icon:0});
							return false;
						}else{
							handleYourPurchase('${pdPurchaseOrder.orderNo}','2',refrea, null);
							layer.closeAll();
						}
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			});
			//通过
			$("#printBtn").click(function(){
				loading('正在提交，请稍等...');
				handleYourPurchase('${pdPurchaseOrder.orderNo}','1',$.trim($('#refuseArea').val()), '0');
			})
		})
		function handleYourPurchase(orderNos, orderStatus, refuseReason, oprtSource){
			$.post('${ctx}/pd/pdPurchaseOrder/audit',{"orderNos":orderNos,"orderStatus":orderStatus,"refuseReason":refuseReason,"oprtSource":oprtSource},function(data){
				if("200" == data.code){
					layer.closeAll();
					//layer.alert("操作成功",{icon:1},function(index){
					//	layer.close(index);
						//打印
						if('1'==orderStatus){
							$('#shenDate').html(data.info);
							$('#printBtn,#refuseBtn').hide();
							printFn();
						}else{
							location.href = '${ctx}'+data.uri;
						}
					//});
				}else{
					layer.alert("操作失败:"+data.info,{icon:2},function(index){
						layer.close(index);
					});
				}
			})
		}
		function printFn(){
			//打印
			event.preventDefault();
			$("#printBox").jqprint();
		}
	</script>
</body>
</html>