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
		.addRoomBox label{width:75px;display: inline-block;text-align: left;}
		.addRoomBox>input[type='text'],.addRoomBox>select{display:inline-block;width: 160px;height:30px;border:1px solid #ccc;margin:0 10px 0 5px;}
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#allMoney,#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.sickerInfo{width: 680px;  margin-left: 5px;height: 75px;  vertical-align: top;}
		.checkChargeDiv>input[type='checkbox']{vertical-align: middle;margin:0 5px}
		.checkChargeDiv>span{font-size: 13px;color:#000;font-weight: 600;padding-right: 10px;}
	</style>
	<title>新增用量</title>
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
			<h4>器械使用管理详情</h4>
		</div>
		<form:form id="inputForm" style="padding:0;" modelAttribute="pdDosage" action="${ctx}/pd/pdDosage/save" method="post" class="form-horizontal">
			<sys:message content="${message}"/>		
			<div class="searchBox">
				<label for="">用量单号</label>
				<input type="text" style="width:165px;" value="${pdDosage.dosageNo }"/>
				<label for="">用量日期</label>
				<input type="text" id="useTime" value='<fmt:formatDate value="${pdDosage.dosageDate }" pattern="yyyy-MM-dd HH:mm:ss"/>'/><br />
				<label for="">用量库房</label>
				<input type="text" style="width:165px;" value="${pdDosage.warehouseName }"/>
				<%--<label for="">销售人员</label>
				<input type="text" value="${pdDosage.salesman }"/>--%>
				<!-- <label for="">产品编码</label>
				<input type="text" />
				<label for="">二级条码</label>
				<input type="text" /> -->
			</div>
			<div class="tableBox">
				<table class="hcy-public-table">
					<thead>
						<tr>
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
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${detailList }" var="li">
							<tr>
								<td><a href="###" class="overLook" title="${li.pdProduct.name }">${li.pdProduct.name }</a></td>
								<td>
									<a href="###" title="${li.pdProduct.number }" class="overLook">${li.pdProduct.number }</a>
								</td>
								<td>
									<a href="###" title="${li.prodBarcode }" class="overLook">${li.prodBarcode }</a>
								</td>
								<td>${li.pdProduct.spec }</td>
								<td>${li.pdProduct.version }</td>
								<td>${li.batchNo }</td>
								<td>${li.pdProduct.unit }</td>
								<td><fmt:formatDate value="${li.expireDate }" pattern="yyyy-MM-dd"/></td>
								<td>${li.dosageCount }</td>
								<td>${li.stockNum }</td>
								<td>${li.pdProduct.sellingPrice }</td>
								<td>${li.amountMoney }</td>
								<td>${li.chargeCode }</td>
                                <c:if test="${li.isCharge == '0'}">
                                    <td>未收费</td>
                                </c:if>
                                <c:if test="${li.isCharge == '1'}">
                                    <td>已收费</td>
                                </c:if>
                                <c:if test="${li.isCharge == '2'}">
                                    <td>已退费</td>
                                </c:if>
								<c:if test="${li.isCharge == '3'}">
									<td>库存已还回</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="totalText">
					<span id="allNum">总数量:<span>${pdDosage.amountCount }</span></span>
					<span id="allMoney">总金额:<span>${pdDosage.amountMoney }</span></span>
				</div>
			</div>

			<c:choose>
				<c:when test="${pdDosage.displayFlag==1}">
					<div id="chargingSelect" class="addRoomBox">
						<h4>收费信息</h4>
						<label for="">病人姓名</label>
						<input type="text"  id="patientInfo" value="${pdDosage.oprDeptName}" style="width:165px;" readonly="readonly"/>
						<label for="">住院号</label>
						<input type="text"  style="width:165px;" value="${pdDosage.inHospitalNo}" readonly="readonly" id="inHospitalNoHInput"/>
						<label for="" style="margin-left: 10px">手术编号</label>
						<input type="text"  style="width:165px;" value="${pdDosage.operativeNumber}" readonly="readonly" id="operativeNumberHInput"/>
						<br />
						<label for="">所属科室</label>
						<input type="text"  value="${pdDosage.exeDeptName}" id="exeDeptName" style="width:165px;" readonly="readonly"/>
						<label for="">所属病区</label>
						<input type="text"  value="${pdDosage.subordinateWard}" id="subordinateWardIn" style="width:165px;" readonly="readonly"/>
						<label for="" style="margin-left: 10px">申请医生姓名</label>
						<input type="text"  value="${pdDosage.sqrtDoctor}" id="sqrtDoctor" style="width:165px;" readonly="readonly"/>
						<br />
						<label for="">门诊号</label>
						<input type="text" value="${pdDosage.outpatientNumber}" id="outpatientNumber" style="width:165px;" readonly="readonly"/>
						<label for="">手术科室</label>
						<input type="text" value="${pdDosage.oprDeptName}"  id="oprDeptName" style="width:165px;" readonly="readonly"/>
						</br>
						<label for="">病人详细信息</label>
						<textarea path="patientDetailInfo" class="sickerInfo" readonly="readonly">${pdDosage.patientDetailInfo }</textarea><br />
					</div>
				</c:when>
				<c:otherwise>
					<div id="chargingInput" class="addRoomBox">
						<h4 style="font-size:14px;color:#000;padding-top:20px;">收费信息</h4>
						<label for="">执行科室</label>
						<input type="text" style="width:165px;" value="${pdDosage.exeDeptName }"/>
						<label for="">手术科室</label>
						<input type="text" style="width:165px;" value="${pdDosage.oprDeptName }"/>
						<label for="">手术医生</label>
						<input type="text" style="width:165px;" value="${pdDosage.surgeon }"/>
						<br />
						<label for="">开方医生</label>
						<input type="text" style="width:165px;" value="${pdDosage.sqrtDoctor }"/>
						<label for="">病人信息</label>
						<input type="text" style="width:165px;" value="${pdDosage.patientInfo }"/>
						<label for="">住院号</label>
						<input type="text" style="width:165px;" value="${pdDosage.inHospitalNo }"/>
						<br />
						<label for="">病人详细信息</label>
						<form:textarea path="patientDetailInfo" class="sickerInfo" readonly="readonly"></form:textarea><br />
					</div>
				</c:otherwise>
			</c:choose>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
                <a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
      		</div>
		</form:form>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//申购时间
			laydate.render({
				elem: '#useTime'
			});
			$(':input').attr('disabled','true');
		})
	</script>
</body>
</html>