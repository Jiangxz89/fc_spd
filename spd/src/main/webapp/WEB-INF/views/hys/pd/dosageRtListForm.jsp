<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<style>
		.addRoomBox{line-height: 40px;padding:10px 5px;margin-bottom: 20px;}
		.addRoomBox label{width:85px;display: inline-block;text-align: left;}
		.addRoomBox>input[type='text'],.addRoomBox>select{display:inline-block;width: 160px;height:30px;border:1px solid #ccc;margin:0 10px 0 5px;}
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#allMoney,#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.sickerInfo{width: 680px;  margin-left: 5px;height: 75px;  vertical-align: top;}
		.checkChargeDiv>input[type='checkbox']{vertical-align: middle;margin:0 5px}
		.checkChargeDiv>span{font-size: 13px;color:#000;font-weight: 600;padding-right: 10px;}
	</style>
	<title>库存退回</title>
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
		<form:form id="inputForm" style="padding:0;" modelAttribute="pdDosageDetail" action="${ctx}/pd/pdDosageDetail/saveCharge" method="post" class="form-horizontal">
			<sys:message content="${message}"/>		
			<div class="addRoomBox">
				<label for="">用量单号</label>
				<input type="text" value="${pdDosage.dosageNo }"/>
				<label for="">用量日期</label>
				<input type="text" id="useTime" value='<fmt:formatDate value="${pdDosage.dosageDate }" pattern="yyyy-MM-dd HH:mm:ss"/>'/><br />
				<label for="">用量库房</label>
				<input type="text" value="${pdDosage.warehouseName }"/>
				<%--<label for="">销售人员</label>
				<input type="text" value="${pdDosage.salesman }"/>--%>
				<br />
				<!-- <label for="">产品编码</label>
				<input type="text" />
				<label for="">二级条码</label>
				<input type="text" /> -->
				<input type="hidden" name="isCharge" value="3"/>  <!-- 只做库存扣减 -->
				<input type="hidden" name="ids"/>
				<input type="hidden" name="dosageNo" value="${pdDosage.dosageNo }"/>
			</div>
			<div class="tableBox">
				<table class="hcy-table-gray">
					<thead>
						<tr>
							<th><input type="checkbox" id="allchoose"/>库存还回</th>
							<th>产品名称</th>
							<th>产品编号</th>
							<th>产品条码</th>
							<th>规格</th>
							<th>型号</th>
							<th>批号</th>
							<th>单位</th>
							<th>有效期</th>
							<th>用数数量</th>
							<th>库存数量</th>
							<th>产品单价</th>
							<th>金额</th>
							<th>收费项目代码</th>
							<th>是否计费</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${detailList }" var="li" varStatus="status">

								<c:choose>
									<c:when test="${li.isCharges=='0' || li.chargeCode==''}">
										<tr style="background-color:yellow">
										<td><input type="checkbox" value="${li.id }" name="needChargeProd"/></td>
									</c:when>
									<c:otherwise>
										<tr>
										<td><input type="checkbox" value="${li.id }" name="needChargeProd"/></td>
									</c:otherwise>
								</c:choose>

								<td><a href="###" class="overLook" title="${li.pdProduct.name }">${li.pdProduct.name }</a></td>
								<td>
									<a href="###" title="${li.pdProduct.number }" class="overLook">${li.pdProduct.number }</a>
									<input type="hidden" name="detailList[${status.index }].prodNo" value="${li.pdProduct.number }"/>
								</td>
								<td>
									<a href="###" title="${li.prodBarcode }" class="overLook">${li.prodBarcode }</a>
								</td>
								<td>${li.pdProduct.spec }</td>
								<td>${li.pdProduct.version }</td>
								<td>${li.batchNo }</td>
								<td>${li.pdProduct.unit }</td>
								<td><fmt:formatDate value="${li.expireDate }" pattern="yyyy-MM-dd"/></td>
								<td>${li.dosageCount }<input type="hidden" name="detailList[${status.index }].dosageCount" value="${li.dosageCount }"/></td>
								<td>${li.stockNum }</td>
								<td>${li.pdProduct.sellingPrice }</td>
								<td>${li.amountMoney }</td>
								<td>${li.chargeCode }<input type="hidden" name="detailList[${status.index }].chargeCode" value="${li.chargeCode }"/></td>
								<c:if test="${li.isCharges == '1'}">
									<td>是</td>
								</c:if>
								<c:if test="${li.isCharges == '0'}">
									<td>否</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="totalText">
					<%-- <span id="allNum">收费产品总数:<span class="all_count">${resultMap['p_num'] }</span></span>
					<span id="allMoney">总金额:<span class="all_money">${resultMap['p_money'] }</span></span> --%>
					<span style="width:100%;">提示：<span style="color:red;">黄色</span>表示产品没有维护收费代码或是不计费类型,产品只会在系统内扣减库存不会发往his系统</span>
					<span id="allNum">收费产品总数:<span class="all_count">0</span></span>
					<span id="allMoney">总金额:<span class="all_money">0.00</span></span>
				</div>
			</div>
			<div class="addRoomBox">
				<h4>收费信息</h4>
                <label for="">病人姓名</label>
                <input type="text"  id="patientInfo" value="${pdDosage.patientInfo}" style="width:165px;" readonly="readonly"/>
                <label for="">住院号</label>
                <input type="text"  style="width:165px;" value="${pdDosage.inHospitalNo}" readonly="readonly" id="inHospitalNoHInput"/>
				<label for="">就诊流水号</label>
				<input type="text"  style="width:165px;" value="${pdDosage.visitNo}" readonly="readonly" />
                <br />
                <label for="">所属科室</label>
                <input type="text"  value="${pdDosage.exeDeptName}" id="exeDeptName" style="width:165px;" readonly="readonly"/>
                <label for="">所属病区</label>
                <input type="text"  value="${pdDosage.subordinateWard}" id="subordinateWardIn" style="width:165px;" readonly="readonly"/>
                <label for="">申请医生姓名</label>
                <input type="text"  value="${pdDosage.sqrtDoctor}" id="sqrtDoctor" style="width:165px;" readonly="readonly"/>
                <br />
                <label for="">门诊号</label>
                <input type="text" value="${pdDosage.outpatientNumber}" id="outpatientNumber" style="width:165px;" readonly="readonly"/>
				<label for="">手术编号</label>
				<input type="text"  style="width:165px;" value="${pdDosage.operativeNumber}" readonly="readonly" id="operativeNumberHInput"/>
                <label for="">手术科室</label>
                <input type="text" value="${pdDosage.oprDeptName}"  id="oprDeptName" style="width:165px;" readonly="readonly"/>
                </br>
				<label for="">病人详细信息</label>
				<textarea path="patientDetailInfo" class="sickerInfo" readonly="readonly">${pdDosage.patientDetailInfo }</textarea><br />
			</div>
			<!-- <div class="checkChargeDiv">
				<input type="checkbox" />
				<span>执行收费</span>
				不选中的情况下，只在当前系统保存病人信息，医院系统中并不记账。此功能只作产品追溯用。
			</div> -->
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
      			<input type="button" value="保存" class="hcy-btn hcy-btn-primary" id="submitbtn"/>
				<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
      		</div>
		</form:form>
		<%-- <form id="myForm" action="${ctx}/pd/pdDosageDetail/saveCharge" method="post">
			<input type="hidden" name="isCharge" value="1"/>
			<input type="hidden" name="ids"/>
		</form> --%>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			var detailListSize = '${detailListSize }';
		    if(!detailListSize ||detailListSize==0){
                layer.alert('产品全部已经收费了',{icon:0});
			}

			//申购时间
			laydate.render({
				elem: '#useTime'
			});
			$(':input').attr('readonly','true');
			$('select').attr('disabled','true');
			//全选与反选
			$('#allchoose').click(function(){
				if($(this).is(':checked')){
                    $("input[name='needChargeProd']").attr('checked','true');
				}else{
					$("input[name='needChargeProd']").removeAttr('checked');
				}
				helloEveryOne();
			});
			//保存收费产品
			$('#submitbtn').click(function(){
				var obj = $("input[name='needChargeProd']:checked");
				var cont = $('tbody').html().trim();
				if(!cont){
					layer.alert('还没有收费的产品',{icon:0});
				}else if(obj.length < 1){
					layer.alert('请选择收费的产品',{icon:0});
				}else{
					var ids = [];
					$.each(obj, function(i,v){
						ids.push($(this).val());
					});
					$("input[name='ids']").val('\''+ids.join('\',\'')+'\'');
					var index = loading('正在提交，请稍等...');
					$('#inputForm').submit();
				}
				return false;
				
			});
			//--数量展示问题
			var trlen = $('tbody>tr').length;
			if(trlen < 1){
				$('.all_count').html(0);
				$('.all_money').html(0.00);
			}
			
			//动态计算收费产品数量、金额
			$("input[name='needChargeProd']").click(function(){
				helloEveryOne();
			});
		})
		//计算收费产品总数、金额
		function helloEveryOne() {
			var c_t = 0;
			var m_t = 0.00;
			$("input[name='needChargeProd']:checked").each(function() {
				var c = $(this).parent().parent().find('input[name$=dosageCount]').val();
				var m = $(this).parent().parent().find('td:eq(-3)').text();
				c_t += Number.parseInt(c || 0);
				m_t += parseFloat(m || 0);
			});
			$('.all_count').html(c_t);
			$('.all_money').html(m_t.toFixed(4));
		}
	</script>
</body>
</html>