<%@ page import="java.util.Date" %>
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
		.addRoomBox label{width:75px;display: inline-block;text-align: left;font-size:12px;}
		.addRoomBox>input[type='text'],.addRoomBox>select{display:inline-block;width: 160px;height:30px;border:1px solid #ccc;margin:0 10px 0 5px;font-size:12px;}
		.addRoomBox>h4{font-size:14px;color:#666;padding-top:20px;font-weight:400;}
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#allMoney,#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.sickerInfo{width: 680px;  margin-left: 5px;height: 75px;  vertical-align: top;}
		.checkChargeDiv>input[type='checkbox']{vertical-align: middle;margin:0 5px}
		.checkChargeDiv>span{font-size: 12px;color:#666;font-weight:400;padding-right: 10px;}
		.remindTit{font-size:13px;color:#666;font-weight: 400;padding:20px;text-align: center;}
		/*.layui-layer-content{height:102px;}*/  /* 这行css弹出框按钮显示bug,故注释 by jiangxz 20191023*/
	</style>
	<title>新增用量</title>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					//校验是否已经扫码
					var len = $('#productTbody>tr').length;
					if(len < 1){
						layer.alert('请扫码或输入条码添加用量',{icon:0});
						return false;
					}

					//扫码前校验是否勾选所需提交的内容
					var isLen = false;
					$("#productTbody tr").each(function(index, data) {
						var checked = $($(data).find("input")[1]).is(':checked');
						if(checked){
							isLen = true;
							return false;
						}
					})

					if (isLen==false) {
						layer.alert('请勾选您所需要提交的内容', {icon: 0});
						return false;
					}

					//校验输入退货数量是否正确
					var flag = true;
					$("#productTbody tr").each(function(index, data) {
						var checked = $($(data).find("input")[1]).is(':checked');
						if(checked){
							var inputRejectedCount = $(this).children('td').eq(9).find("input");
							var rst_1 = alertWarn('请输入大于0的整数', inputRejectedCount);
							if (!rst_1) {
								flag = false;
								return false;
							}
						}
					})

					if(!flag){return false;}

					//--red校验用量大于库存的数据
					var rgb = true;
					$('tr').each(function(){
						var csv = $(this).css('background-color');
						if(csv && (csv == 'red'||csv=="rgb(255, 0, 0)")){
							rgb = false;
							return false;
						}
					});

					if (rgb==false) {
						layer.alert('产品退货量不能大于库存', {icon: 0});
						return false;
					}

					//校验收费代码
					var tipflag = true;
					$("#productTbody tr").each(function(index, data) {
						var checked = $($(data).find("input")[1]).is(':checked');
                        var isCharges = $(this).children('td').find("input[class$=isCharges]").val();
						if(checked && "1"==isCharges){
							var chargeCode = $(this).children('td').eq(13).find("select[name$=chargeCode]");
							var rst = noneSelectWarn('请选择收费代码', chargeCode);
							if (!rst) {
								tipflag = false;
								return false;
							}
						}
					})
					if (tipflag==false) {
						return false;
					}
					//校验病人信息
					var patientInfo = true;
					if(!$("#patientInfoInput").val() && !$("#patientInfo").val()){
						patientInfo = false;
					}
					if(patientInfo==false) {
						layer.alert('请查询病人信息', {icon: 0});
						return false;
					}
/*					//校验住院号 2019年10月21日17:34:32   zxh 没有对接接口暂时取消校验
					var inHospitalNo = true;
					if(!$("#inHospitalNoHInput").val() &&!$("#operativeNumberHInput").val()){
						inHospitalNo = false;
					}
					if(inHospitalNo==false){
						layer.alert('请填写住院号和手术编号',{icon:0});
						return false;
					}*/

					var map = {};
					var charge_code_json = getCookie("charge_code_json");
					if(charge_code_json != "" && charge_code_json != "undefined" && charge_code_json != "null"){
						var json = $.parseJSON(charge_code_json);
						map = json;
					}
					$("#productTbody tr").each(function(index, data) {
						var checked = $($(data).find("input")[1]).is(':checked');
                        var isCharges = $(this).children('td').find("input[class$=isCharges]").val();
                        if(checked && "1"==isCharges){
							var prod_no = $($(data).find("input")[3]).val();
							var charge_code = $($(data).find("select")[0]).val();
							map[''+prod_no+''] = charge_code;
						}
					})
					setCookie(JSON.stringify(map).toString());
					$("#productTbody tr").each(function(index, data) {
						var checked = $($(data).find("input")[1]).is(':checked');
						if(!checked){
							$(data).find("input").not("input[type=checkbox]").attr("disabled",true);
							$(data).find("select").attr("disabled",true);
						}
					})
					//是否执行收费 0为否
					if($("#displayFlag").val()=="0"){
						$("#chargeFlag").val("0");
					}
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
			<h4>新增用量</h4>
		</div>
		<form:form id="inputForm" style="padding:0;" modelAttribute="pdDosage" action="${ctx}/pd/pdDosage/save" method="post" class="form-horizontal">
			<form:hidden path="warehouseId"/>
            <input type="text" style="display: none" name="displayFlag" id="displayFlag" value = "${displayFlag}"/>
			<sys:message content="${message}"/>		
			<div class="searchBox">
				<div>
					<label for="">用量单号</label>
					<input type="text" name="dosageNo" style="width:165px;" value="${pdDosage.dosageNo }"readonly="readonly"/>
				</div>
				<div>
					<label for="">用量日期</label>
					<input type="text" name="dosageDate" id="useTime"/>
				</div>
				<div>
					<label for="">用量库房</label>
					<input type="text" name="warehouseName" value="${pdDosage.warehouseName }" readonly="readonly"/>
				</div>
                <c:if test="${hospitalNumber == 'FCZY' || hospitalNumber == 'FCRM'}"> <%-- 适用于丰城医院 add by jiangxz 20191023 --%>
                    <input type="button" value="选择定数包" class="hcy-btn hcy-btn-primary" id="choosePackage"/>
                    <input type="button" value="选择病人" class="hcy-btn hcy-btn-primary" id="choosePatien"/>
                </c:if>
				<%--<div>2019年7月1日09:57:30   取消销售人员
					<label for="">销售人员</label>
					<input type="text" name="salesman" maxlength="64"/>
				</div>--%>
			</div>
			<div class="searchBox">
				<div>
					<label for="">产品编码</label>
					<input type="text" autocomplete="off" style="width:165px;" id="number" name="prodNo" autofocus="autofocus"/>
				</div>
				<div>
					<label for="">二级条码</label>
					<input type="text" autocomplete="off" style="width:165px;" id="barCode" name="prodBarcodeInput"/>
				</div>
				<div><label style="width:100%;">提示：按<span style="color:red;">Ctrl+Alt</span>键快速定位到扫码输入框</label></div>
                </br>
                <div>
                    <label for="">产品名称</label>
                    <input type="text" autocomplete="off" name="productName" style="width:165px;"  id="productName"/>
                </div>
                <div>
                    <label for="">规格</label>
                    <input type="text" autocomplete="off" name="spec" style="width:165px;"  id="productSpec"/>
                </div>
                <div>
                    <label for="">型号</label>
                    <input type="text" autocomplete="off" name="version"  id="productVersion"/>
                </div>
				<div>
					<label for="">批号</label>
					<input type="text" autocomplete="off" name="batchNo"  id="productBatchNo"/>
				</div>
			</div>
			<div class="tableBox">
				<table class="hcy-public-table">
					<thead>
						<tr>
							<th>操作</th>
							<th>产品名称</th>
							<th>产品编号</th>
							<th>产品条码</th>
							<th>规格</th>
							<th>型号</th>
							<th>批号</th>
							<th>单位</th>
							<th>有效期</th>
							<th>用量数量</th>
							<th>库存数量</th>
							<th>产品单价</th>
							<th>金额</th>
							<th>收费项目代码</th>
							<th>是否计费</th>
						</tr>
					</thead>
					<tbody id="productTbody">
					</tbody>
				</table>
				<div class="totalText">
					<span id="allNum">总数量:<span class="total_number">0</span></span>
					<span id="allMoney">总金额:<span class="total_money">0.00</span></span>
				</div>
			</div>
			<div id="chargingSelect" class="addRoomBox">
				<h4>收费信息（请输入住院号或申请编号，按回车查询病人信息）</h4>
				<label for="">住院号</label>
				<input type="text" autocomplete="off" style="width:165px;"  id="inHospitalNoInputN"/>
				<label for="">申请编号</label>
				<input type="text" autocomplete="off" style="width:165px;"  id="operativeNumberInput"/>
				<label for="">住院标识</label>
				<input type="text" autocomplete="off" style="width:165px;"  id="zyType" />
				</br>
				<label for="">病人姓名</label>
				<input type="text" name="patientInfo" id="patientInfo" style="width:165px;" />
				<label for="">住院号</label>
				<input type="text" name="inHospitalNo" style="width:165px;"  id="inHospitalNoHInput"/>
				<label for="">手术编号</label>
				<input type="text"  name="operativeNumber" style="width:165px;"  id="operativeNumberHInput"/>
				</br>
				<label for="">所属科室</label>
				<input type="text" name="exeDeptName" id="exeDeptName" style="width:165px;" />
				<input type="hidden" name="exeDeptId" id="exeDeptId"  style="width:165px;" />
				<label for="">所属病区</label>
				<input type="text"  name="subordinateWard" id="subordinateWardIn" style="width:165px;" />
				<input type="hidden" name="subordinateWardId" id="subordinateWardIdIn" style="width:165px;" />
				<label for="">申请医生姓名</label>
				<input type="text" name="sqrtDoctor" id="sqrtDoctor" style="width:165px;" />
				<input type="hidden" name="sqrtDoctorId" id="sqrtDoctorId" style="width:165px;" />
				<br />
				<label for="">门诊号</label>
				<input type="text" name="outpatientNumber" id="outpatientNumber" style="width:165px;" />
				<label for="">手术科室</label>
				<input type="text" name="oprDeptName" id="oprDeptName" style="width:165px;" />
				<input type="hidden" name="oprDeptId" id="oprDeptId" style="width:165px;" />
				<label for="">手术名称</label>
				<input type="text" name="operativeName" id="operativeName" style="width:165px;" />
				<br />
				<!-- 模拟数据用 -->
				<label style="width:80px;padding-top:10px;vertical-align:top;">病人详细信息</label>
				<form:textarea path="patientDetailInfo" style="margin-top:10px;" class="sickerInfo" readonly="true"></form:textarea><br />
			</div>


			<div id="chargingInput" class="addRoomBox">
				<h4>收费信息</h4>
				<label style="width:100px;" for=""><%--<span class="mustIcon">*</span>--%>执行科室</label>
				<input type="text" autocomplete="off" name="exeDeptName" id="exeDeptNameInput"/>
				<label style="width:100px;" for=""><%--<span class="mustIcon">*</span>--%>手术科室</label>
				<input type="text" autocomplete="off" name="oprDeptName" id="oprDeptNameInput"/>
				<label style="width:100px;" for=""><%--<span class="mustIcon">*</span>--%>手术医生</label>
				<input type="text" autocomplete="off" name="surgeon" id="surgeonInput"/><br />
				<label style="width:100px;" for=""><%--<span class="mustIcon">*</span>--%>开方医生</label>
				<input type="text" autocomplete="off" name="sqrtDoctor" id="sqrtDoctorInput"/>
				<label style="width:100px;" for=""><span class="mustIcon">*</span>病人姓名</label>
				<input name="patientInfo" autocomplete="off" id="patientInfoInput" type="text"/>
				<label style="width:100px;" for=""><span class="mustIcon">*</span>住院号/门诊号</label>
				<input name="inHospitalNo" autocomplete="off" id="inHospitalNoInput" type="text"/><br/>
				<label style="width:100px;padding-top:10px;vertical-align:top;">病人详细信息</label>
				<textarea path="patientDetailInfo" autocomplete="off" style="margin-top:10px;" class="sickerInfo" ></textarea><br />
			</div>

			<div id="implementDiv" class="checkChargeDiv">
				<input id="chargeFlag" type="checkbox" name="chargeFlag" value="1" checked/>
				<span>执行收费</span>
				不选中的情况下，只在当前系统保存病人信息，医院系统中并不记账。此功能只作产品追溯用。
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
      			<input type="button" value="保存" class="hcy-btn hcy-save" id="submitForm"/>
      			<a href="javascript:location.href=document.referrer;" class="hcy-btn hcy-back">返回</a>
      		</div>
		</form:form>
		<div class="remindBox" style="display:none;">
			<h2 class="remindTit">库存中有过期产品！！！</h2>
			<input type="text"/>
		</div>

		<div class="addHighBox" style="display: none;">
			<table class="hcy-public-table" id="addContentTab">
			<thead>
			<tr>
				<th>操作</th>
				<th>手术编号</th>
                <th>手术名称</th>
				<th>所属病区</th>
				<th>所属科室</th>
				<th>手术科室</th>
				<th>门诊号</th>
				<th>申请医生姓名</th>
				<th>登记日期</th>
			</tr>
			</thead>
			<tbody id= "tdodyID">
				<tr  style="cursor:pointer">
					<td><input type="radio" name="subBox"  data-op-number="11542"/></td>
					<td>11542</td>
					<td>肝胆外科</td>
					<td>肝胆外科</td>
					<td>肝胆外科</td>
					<td>00060847</td>
					<td>刘新海</td>
					<td>2019/7/27 18:47:00 </td>
				</tr>
				<tr style="cursor:pointer">
					<td><input type="radio" name="subBox" data-op-number="22222"/></td>
					<td>11542</td>
					<td>肝胆外科</td>
					<td>肝胆外科</td>
					<td>肝胆外科</td>
					<td>00060847</td>
					<td>张三</td>
					<td>2019/7/27 18:47:00 </td>
				</tr>
			</tbody>
			</table>
		</div>

	</div>
	<input type="hidden" id="rowsLen" value="0"/>
	<input type="hidden" id="isHasChargeCode" value=""/>
	<input type="hidden" id="validCode" value=""/>
<%-- 	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script> --%>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/js/dosage-getbaseinfo.js?time=<%= new Date().getTime() %>"></script>
	<script src="${ctxStatic}/spd/js/barcode.js?time=<%= new Date().getTime() %>"></script>
	<script type="text/javascript">
		var prods = [],//保存添加的产品编码
		    alltotal = 0.00,//总金额
	        allcount = 0;//总数量
		$(function(){

			$("#number").focus();

            //申购时间
            laydate.render({
                elem: '#useTime',
                istoday: true,
                value: new Date()
            });

			//复选框点击事件
			$("#productTbody").on('click',".checkInp",function(){
				if($(this).is(':checked')){
					var count = $(this).parent().parent().children('td').eq(9).find("input").val();//退货量
					var stock = $(this).parent().parent().children('td').eq(10).find("input").val();//库存
					if(Number.parseInt(count) > Number.parseInt(stock)){
						layer.alert('用量已大于库存', {icon:0});
						$(this).parent().parent().css('background-color','red');
					}else{
						$(this).parent().parent().css('background-color','');
					}
				}else{
					$(this).parent().parent().css('background-color','');
				}
				computeTotalMoney();
			})

			//时间戳转换成日期
			function dateFormat(time){
				var date = new Date(time);
				Y = date.getFullYear() + '-';
				M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
				D = (date.getDate()< 10 ? '0'+(date.getDate()) : date.getDate());
				return Y+M+D;
			}

			//初始化扫码状态
			function sweepCodeinit(){
				$("#number").val("");
				$("#barCode").val("");
				$("#productName").val("");
				$("#productSpec").val("");
				$("#productVersion").val("");
				$("#productBatchNo").val("");
				$("#number").focus();
			}

			//按Ctrl + Alt键聚焦
			$(document).keydown(function(event){
				if(event.ctrlKey && event.keyCode == 18){
					$("#number").focus();
				}
			});

			//产品码
			$("#number").keydown(function(event){
				if(event.keyCode == "13"){
					var number_val = $(this).val();
                    /* if(number_val != "" && number_val.length >= 16){*///取消gs1码限制2019年9月6日10:58:08
					if(number_val != ""){
						$("#barCode").val("");
						$("#barCode").focus();
						return true;
					}else{
						layer.alert("扫码错误，请重新扫描！",{icon:0});
						$("#number").val("");
						$("#number").focus();
						return false;
					}
				}
				return true;
			});

			//二级条码发生改变时
			//扫描产品  二级条码变化时
			$("#barCode").keydown(function(event) {
				if (event.keyCode != "13") {
					return;
				}
				var number = $.trim($("#number").val());
				var barCode = $.trim($("#barCode").val());
				var initNumber = number;
				var initBarCode = barCode;
				if(number == ""){
					layer.alert('产品编码为空',{icon:0});
					$("#number").focus();
					return false;
				}
				if(barCode == ""){
					$("#barCode").focus();
					layer.alert('二级条码为空',{icon:0});
					return false;
				}
				var batchNo = "";
				var secondCode = barCode;
				//判断是不是只以编号进行查询  1为是
				var findFlag = 0;
				if(number.substring(0,2) != "93"){
					//判断是不是只以编号进行查询
					if(number==secondCode){
						if(number.substr(0, 2) == "01" && number.length==16){
							number = number.substring(2,16);
							findFlag = 1;
						}
					}
					if(findFlag!=1){
						if(!scanCode(number, barCode)){  //解析
							sweepCodeinit();
							return false;
						}
						number = upn;
						if(_secondCode != ""){
							secondCode = _secondCode;
						}
						if(_Lot != ""){
							batchNo = _Lot;
						}
					}
				}
				//产品条码修正
				if(initBarCode.indexOf(initNumber) != -1){
					secondCode =initBarCode;
				}else{
					secondCode = initNumber + initBarCode;
				}
				//productNo编号
				//batchNo批次号
				//productBarCode产品条码
				$.ajax({
					url:'${ctx}/pd/pdProductStock/getStockRecordOutScan',
					type:'post',
					data:{
						productNo:number,
						productBarCode:secondCode,
						batchNo:batchNo,
						findFlag:findFlag
					},
					success:function(data){
						layer.closeAll();
						sweepCodeinit();
						if(data.length>0){
							//已打钩的产品
							var idCheckList = new Array();
							//扫码前清空没有选中的
							$("#productTbody tr").each(function(index, data) {
								var checked = $($(data).find("input")[1]).is(':checked');
								if(!checked){
									$(this).remove();
								}else{
									idCheckList.push($($(data).find("input")[0]).val());
								}
							})
							if(data.length==1){
								var productStockId = data[0].id;
								//重复扫码+1
								var isHas = false;
								$("#productTbody tr").each(function(index, data) {
									var stockId = $($(data).find("input")[0]).val();
									if(productStockId==stockId){
										var productNum = $($(data).children('td').eq(9).find("input")).val();
										if(productNum != ""){
											var cuProductNum = 1 + parseInt(productNum);
											$($(data).children('td').eq(9).find("input")).val(cuProductNum);
											var productInputRejectedCount = $($(data).children('td').eq(9).find("input"));
											computeDosage(productInputRejectedCount)
											var sellingPrice = $($(data).children('td').eq(11)).text();
											$($(data).children('td').eq(12)).text((cuProductNum * parseFloat(sellingPrice)).toFixed(4));
											computeTotalMoney();//计算金额
											isHas = true;
											return false;
										}
									}
								})
								if(!isHas){
									var html = getProdHtmlOne(data);
									$("#productTbody").append(html);
									computeTotalMoney();//计算金额
								}
							}else{
								var html = getProdHtmlTwo(data,idCheckList);
								$("#productTbody").append(html);
							}
						}else{
							layer.alert('查询无结果，请重新扫描或输入正确号码',
									{icon:0},
									function(index) {
										layer.close(index);
										sweepCodeinit();
									});
						}
					}
				});
			})

            //根据产品查询库存
            $("#productName").keydown(function(event) {
                if (event.keyCode != "13") {
                    return;
                }
                var productName = $.trim($("#productName").val());
                if(productName == ""){
                    layer.alert('产品名称为空',{icon:0});
                    $("#productName").focus();
                    return false;
                }
                var productSpec = $.trim($("#productSpec").val());
                var productVersion = $.trim($("#productVersion").val());
                var productBatchNo = $.trim($("#productBatchNo").val());
                getDataOther(productName,productSpec,productVersion,productBatchNo);
                /*$("#productSpec").val("");
                $("#productVersion").val("");*/
                $("#productName").focus();
            })

            //根据规格查询库存
            $("#productSpec").keydown(function(event) {
                if (event.keyCode != "13") {
                    return;
                }
                var productSpec = $.trim($("#productSpec").val());
                if(productSpec == ""){
                    layer.alert('产品规格为空',{icon:0});
                    $("#productSpec").focus();
                    return false;
                }
                var productName = $.trim($("#productName").val());
                var productVersion = $.trim($("#productVersion").val());
                var productBatchNo = $.trim($("#productBatchNo").val());
                getDataOther(productName,productSpec,productVersion,productBatchNo);
                /*$("#productVersion").val("");
                $("#productName").val("");*/
                $("#productSpec").focus();
            })
            //根据型号查询库存
            $("#productVersion").keydown(function(event) {
                if (event.keyCode != "13") {
                    return;
                }
                var productVersion = $.trim($("#productVersion").val());
                if(productVersion == ""){
                    layer.alert('产品名称为空',{icon:0});
                    $("#productVersion").focus();
                    return false;
                }
                var productSpec = $.trim($("#productSpec").val());
                var productName = $.trim($("#productName").val());
                var productBatchNo = $.trim($("#productBatchNo").val());
                getDataOther(productName,productSpec,productVersion,productBatchNo);
                /*	$("#productSpec").val("");
                    $("#productName").val("");*/
                $("#productVersion").focus();
            })

			//根据批号查询库存
			$("#productBatchNo").keydown(function(event) {
				if (event.keyCode != "13") {
					return;
				}
				var productBatchNo = $.trim($("#productBatchNo").val());
				if(productBatchNo == ""){
					layer.alert('产品批号为空',{icon:0});
					$("#productBatchNo").focus();
					return false;
				}
				var productSpec = $.trim($("#productSpec").val());
				var productName = $.trim($("#productName").val());
				var productVersion = $.trim($("#productVersion").val());
				getDataOther(productName,productSpec,productVersion,productBatchNo);
				/*	$("#productSpec").val("");
                    $("#productName").val("");*/
				$("#productBatchNo").focus();
			})

            //名称规格型号查询库存
            function getDataOther(productName,productSpec,productVersion,productBatchNo){
                $.ajax({
                    url:'${ctx}/pd/pdProductStock/getStockRecordOutScan',
                    type:'post',
                    data:{
                        productName:productName,
                        spec:productSpec,
                        version:productVersion,
						batchNo:productBatchNo
                    },
                    success:function(data){
                        if(data.length>0){
                            //已打钩的产品
                            var idCheckList = new Array();
                            //扫码前清空没有选中的
                            $("#productTbody tr").each(function(index, data) {
                                var checked = $($(data).find("input")[1]).is(':checked');
                                if(!checked){
                                    $(this).remove();
                                }else{
                                    idCheckList.push($($(data).find("input")[0]).val());
                                }
                            })
                            if(data.length==1){
                                var productStockId = data[0].id;
                                //重复扫码+1
                                var isHas = false;
                                $("#productTbody tr").each(function(index, data) {
                                    var stockId = $($(data).find("input")[0]).val();
                                    if(productStockId==stockId){
                                        var productNum = $($(data).children('td').eq(9).find("input")).val();
                                        if(productNum != ""){
                                            var cuProductNum = 1 + parseInt(productNum);
                                            $($(data).children('td').eq(9).find("input")).val(cuProductNum);
                                            var productInputRejectedCount = $($(data).children('td').eq(9).find("input"));
                                            computeDosage(productInputRejectedCount)
                                            var sellingPrice = $($(data).children('td').eq(11)).text();
                                            $($(data).children('td').eq(12)).text(cuProductNum * parseFloat(sellingPrice));
											computeTotalMoney();//计算金额
                                            isHas = true;
                                            return false;
                                        }
                                    }
                                })
                                if(!isHas){
                                    var html = getProdHtmlOne(data);
                                    $("#productTbody").append(html);
									computeTotalMoney();//计算金额
                                }
                            }else{
                                var html = getProdHtmlTwo(data,idCheckList);
                                $("#productTbody").append(html);
                            }
                        }else{
                            layer.alert('查询无结果，请重新扫描或输入正确号码',
                                {icon:0},
                                function(index) {
                                    layer.close(index);
                                });
                        }
                    }
                })
            }

			//拼接html第一种包含个数的；
			function getProdHtmlOne(data){
				var len = $('#rowsLen').val();
				var html="";
                var isChargeCode = true;
				for(var i=0; i<data.length; i++){
					var productStock = data[i];
					var finalMoney = parseFloat(productStock.pdProduct.sellingPrice).toFixed(4);
					var pChargeCode = productStock.pdProduct.chargeCode || '';
					//收费代码
					// var pChargeCodeList = data.chargeCodeList;
					//是否收费标志
					var isCharges = productStock.pdProduct.isCharge;
					var clen = 0;
                    var pChargeCodeArray = new Array();
					var chtm = "";
					//无需收费
					if("0"==isCharges){
                        html +=		'<tr id="ps'+productStock.id+'">';
                        html +=  	'<input type="hidden" name="dosageDetailList['+len+'].productStockId" value="'+productStock.id+'"/>'
                        html += 	'<td><input type="checkbox" checked="checked" class="checkInp"/></td>';
                        html = returnHtml(productStock,html,len);
                        html +=		'<td><input type="text" name="dosageDetailList['+len+'].dosageCount" autocomplete="off" class="inputRejectedCount" value="1" style="width:40px;"/>';
					}else{
                        if(pChargeCode && pChargeCode != "undefined"){
                            pChargeCodeArray = pChargeCode.split(',');
                            clen = pChargeCodeArray.length;
                        }
                        //有收费代码
                        if(clen>0){
                            chtm = returnChargeHtml(chtm,productStock,pChargeCodeArray,len);
                            html +=		'<tr id="ps'+productStock.id+'">';
                            html +=  	'<input type="hidden" name="dosageDetailList['+len+'].productStockId" value="'+productStock.id+'"/>'
                            html += 	'<td><input type="checkbox" checked="checked" class="checkInp"/></td>';
                            html = returnHtml(productStock,html,len);
                            html +=		'<td><input type="text" name="dosageDetailList['+len+'].dosageCount" autocomplete="off" class="inputRejectedCount" value="1" style="width:40px;"/>';
                        }else{
                            isChargeCode = false;
                            html +=		'<tr style="background-color:yellow" id="ps'+productStock.id+'">';
                            html +=  	'<input type="hidden" name="dosageDetailList['+len+'].productStockId" value="'+productStock.id+'"/>'
                            html += 	'<td><input type="checkbox" disabled="disabled" class="checkInp"/></td>';
                            html = returnHtml(productStock,html,len);
                            html +=		'<td><input type="text" disabled="disabled" name="dosageDetailList['+len+'].dosageCount" autocomplete="off" class="inputRejectedCount"  style="width:40px;"/></td>';
                        }
					}
					html +=		'<td><input type="hidden" name="stockNum" class="stockNum" value="'+productStock.stockNum+'" style="width:40px;"/>'+productStock.stockNum+'</td>';
					//html +=		'<td>'+productStock.pdProduct.sellingPrice+'</td>';
					html +=		'<td>'+productStock.pdProduct.sellingPrice+'<input type="hidden" name="dosageDetailList['+len+'].singlePrice" value="'+productStock.pdProduct.sellingPrice+'"/></td>'
					html +=		'<td class="finalMoney">'+finalMoney+'</td>';
					html +=     '<td>'+chtm+'</td>';
					html +=		'<input type="hidden" name="dosageDetailList['+len+'].expireDate" value="'+dateFormat(productStock.validDate)+'"/>';
					html +=		'<input type="hidden" name="dosageDetailList['+len+'].stockNum" value="'+productStock.stockNum+'"/>';
                    if("0"==isCharges){
                        html +=     '<td><input type="hidden" class="isCharges" name="dosageDetailList['+len+'].isCharges" value="'+isCharges+'"/>'+"否"+'</td>';
                    }else{
                        html +=     '<td><input type="hidden" class="isCharges" name="dosageDetailList['+len+'].isCharges" value="'+isCharges+'"/>'+"是"+'</td>';
                    }
					html +=		'</tr>';
					len = Number(len)+1
				}
                if(!isChargeCode){
                    layer.alert('该产品收费代码未维护，请联系管理员进行维护',{icon:0});
                }
				$('#rowsLen').val(len);
				return html;
			}

			function getProdHtmlTwo(data,idCheckList){
				var len = $('#rowsLen').val();
				var html="";
				var isChargeCode = true;
				for(var i=0; i<data.length; i++){
					var productStock = data[i];
					if(idCheckList.indexOf(productStock.id) > -1){
						continue;
					}
                    var finalMoney = parseFloat(productStock.pdProduct.sellingPrice).toFixed(4);
                    var pChargeCode = productStock.pdProduct.chargeCode || '';
                    //收费代码
                    // var pChargeCodeList = data.chargeCodeList;
                    //是否收费标志
                    var isCharges = productStock.pdProduct.isCharge;
                    var clen = 0;
                    var pChargeCodeArray = new Array();
                    var chtm = "";
                    //无需收费
                    if("0"==isCharges){
                        html +=		'<tr id="ps'+productStock.id+'">';
                        html +=  	'<input type="hidden" name="dosageDetailList['+len+'].productStockId" value="'+productStock.id+'"/>'
                        html +=     '<td><input type="checkbox" class="checkInp"/></td>';
                        html = returnHtml(productStock,html,len);
                        html +=		'<td><input type="text" name="dosageDetailList['+len+'].dosageCount" class="inputRejectedCount" autocomplete="off" value="1" style="width:40px;"/>';
                    }else{
                        if(pChargeCode && pChargeCode != "undefined"){
                            pChargeCodeArray = pChargeCode.split(',');
                            clen = pChargeCodeArray.length;
                        }
                        if(clen>0){
                            chtm = returnChargeHtml(chtm,productStock,pChargeCodeArray,len);
                            html +=		'<tr id="ps'+productStock.id+'">';
                            html +=  	'<input type="hidden" name="dosageDetailList['+len+'].productStockId" value="'+productStock.id+'"/>'
                            html +=     '<td><input type="checkbox" class="checkInp"/></td>';
                            html = returnHtml(productStock,html,len);
                            html +=		'<td><input type="text" name="dosageDetailList['+len+'].dosageCount" class="inputRejectedCount" autocomplete="off" value="1" style="width:40px;"/>';
                        }else{
                            isChargeCode = false;
                            html +=		'<tr style="background-color:yellow" id="ps'+productStock.id+'">';
                            html +=  	'<input type="hidden" name="dosageDetailList['+len+'].productStockId" value="'+productStock.id+'"/>'
                            html +=     '<td><input type="checkbox" disabled="disabled" class="checkInp"/></td>';
                            html = returnHtml(productStock,html,len);
                            html +=		'<td><input type="text" disabled="disabled" name="dosageDetailList['+len+'].dosageCount" autocomplete="off" class="inputRejectedCount"  style="width:40px;"/></td>';
                        }
					}
					html +=		'<td><input type="hidden" name="stockNum" class="stockNum" value="'+productStock.stockNum+'" style="width:40px;"/>'+productStock.stockNum+'</td>';
					//html +=		'<td>'+productStock.pdProduct.sellingPrice+'</td>';
					html +=		'<td>'+productStock.pdProduct.sellingPrice+'<input type="hidden" name="dosageDetailList['+len+'].singlePrice" value="'+productStock.pdProduct.sellingPrice+'"/></td>'
					html +=		'<td class="finalMoney">'+finalMoney+'</td>';
					html +=     '<td>'+chtm+'</td>';
					html +=		'<input type="hidden" name="dosageDetailList['+len+'].expireDate" value="'+dateFormat(productStock.validDate)+'"/>';
					html +=		'<input type="hidden" name="dosageDetailList['+len+'].stockNum" value="'+productStock.stockNum+'"/>';
                    if("0"==isCharges){
                        html +=     '<td><input type="hidden" class="isCharges" name="dosageDetailList['+len+'].isCharges" value="'+isCharges+'"/>'+"否"+'</td>';
                    }else{
                        html +=     '<td><input type="hidden" class="isCharges" name="dosageDetailList['+len+'].isCharges" value="'+isCharges+'"/>'+"是"+'</td>';
                    }
					html +=		'</tr>';
					len = Number(len)+1
				}
				if(!isChargeCode){
                    layer.alert('部分产品收费代码未维护，请联系管理员进行维护',{icon:0});
                }
				idCheckList  = new Array();
				$('#rowsLen').val(len);
				return html;
			}

			//返回公用的字符串
			function returnHtml(productStock,html,len){
                html +=  	'<input type="hidden" name="dosageDetailList['+len+'].prodId" value="'+productStock.productId+'"/>'
                html +=  	'<input type="hidden" name="dosageDetailList['+len+'].prodNo" value="'+productStock.pdProduct.number+'"/>'
                html +=  	'<input type="hidden" name="dosageDetailList['+len+'].prodBarcode" value="'+productStock.productBarCode+'"/>'
                html +=  	'<input type="hidden" name="dosageDetailList['+len+'].batchNo" value="'+productStock.batchNo+'"/>'
                html +=		'<td>'+productStock.pdProduct.name+'</td>';
                html +=		'<td>'+productStock.pdProduct.number+'</td>';
                html +=		'<td>'+productStock.productBarCode+'</td>';
                html +=		'<td>'+productStock.pdProduct.spec+'</td>';
                html +=		'<td>'+productStock.pdProduct.version+'</td>';
                html +=		'<td>'+productStock.batchNo+'</td>';
                html +=		'<td>'+productStock.pdProduct.unit+'</td>';
                html +=		'<td>'+dateFormat(productStock.validDate)+'</td>';
                return html;
			}

			//返回公用的字符串
			function returnChargeHtml(chtm,productStock,pChargeCodeArray,len){
                var charge_code_json = getCookie("charge_code_json");
                chtm += '<select name="dosageDetailList['+len+'].chargeCode" mainid="'+productStock.id+'" style="width:120px;" placeholder="请选择收费项目代码" >'
                //chtm += '<option value="">请选择</option>';
                for(var m=0; m<pChargeCodeArray.length; m++){
                    //解析cookie，如果cookie有值则默认选择
                    if(charge_code_json != "" && charge_code_json != "undefined" && charge_code_json != "null"){
                        var map = $.parseJSON(charge_code_json);
                        if (JSON.stringify(map) != JSON.stringify({})){
                            var bool = false;
                            for(var key in map){
                                if(key==productStock.pdProduct.number && pChargeCodeArray[m]== map[key]){
                                    bool = true;
                                    break;
                                }
                            }
                            if(bool){
                                chtm += '<option value="'+pChargeCodeArray[m]+'" selected="selected">'+pChargeCodeArray[m]+'</option>';
                            }else{
                                chtm += '<option value="'+pChargeCodeArray[m]+'">'+pChargeCodeArray[m]+'</option>';
                            }
                        }else{
                            chtm += '<option value="'+pChargeCodeArray[m]+'">'+pChargeCodeArray[m]+'</option>';
                        }
                    }else{
                        chtm += '<option value="'+pChargeCodeArray[m]+'">'+pChargeCodeArray[m]+'</option>';
                    }
                }
                chtm += '</select>';
                return chtm;
			}

            //计算单个金额和总数量
            $(document).on('input propertychange', '.inputRejectedCount', function(){
                //输入时默认勾选
                $(this).parent().parent().find(":checkbox").attr("checked",true);
                alertWarn('请输入大于0的整数', $(this));
                computeDosage($(this));
                computeSingleMoney($(this));
				computeTotalMoney();
                return false;
            });

            //计算用量数量不得大于库存数量
            function computeDosage(inputObj){
                var count = $.trim(inputObj.val());//用量
                var stock = $.trim(inputObj.parent().next().find('input[name$=stockNum]').val());//库存
                if(Number.parseInt(count) > Number.parseInt(stock)){
                    layer.alert('退货量已大于库存', {icon:0});
                    inputObj.parent().parent().css('background-color','red');
                }else{
                    inputObj.parent().parent().css('background-color','');
                }
            }

            //动态计算单个金额
            function computeSingleMoney(inputObj){
				var count = $.trim(inputObj.val());//用量
				var sellingPrice = $.trim(inputObj.parent().parent().children('td').eq(11).text());
				var total = null;
				var rst = alertWarn('请输入大于0的整数',inputObj);
				if (!rst)
					count = 0;
				if(sellingPrice && !Number.isNaN(sellingPrice) && Number.parseFloat(sellingPrice) > 0) {
					total = (parseFloat(count || 0) * parseFloat(sellingPrice)).toFixed(4);
					inputObj.parent().parent().children('td').eq(12).text(total);
				}
			}

			//动态计算总数量和总金额
			function computeTotalMoney(){
				//计算总数量
				allcount = 0;
				//计算总金额
				alltotal = 0.00;
				$("#productTbody tr").each(function(index, data) {
					var checked = $($(data).find("input")[1]).is(':checked');
					if(checked){
						var productNum = $($(data).children('td').eq(9).find("input")).val();
						if (!isNumber(productNum)) {
							presentV = 0;
						}
						allcount += parseInt(productNum);
						var productMoney = $($(data).children('td').eq(12)).text();
						alltotal += parseFloat(productMoney || 0);
					}
				})
				$('.total_number').html(allcount);
				$('.total_money').html(alltotal.toFixed(4));
            }

			//保存
			$("#submitForm").click(function(){
                $("#inputForm").submit();
			});

			//执行收费勾选
			$("#chargeFlag").click(function(){
				var check=document.getElementsByName("chargeFlag")[0].checked;
				if(check==true){//选中
					$("#chargeFlag").val('1');
				}else{//没选中
					$("#chargeFlag").val('0');
				}
			})

			//选择定数包
			$("#choosePackage").click(function(){
				// var outId = $("#warehouseId").val();
				// if(outId==''){
				// 	layer.alert('用量库房不存在！', {icon:0});
				// 	return false;
				// }
				layer.open({
					type:2,
					title:"添加定数包",
					content:'${ctx}/pd/pdPackage/choosePackage',
					area:["965px","527px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						//$('tbody').append(addPack());
						var childWindow = layero.find('iframe')[0].contentWindow;
						var returnObj = childWindow.getProductNumberForDosage($('#rowsRecord').val());
						var message = returnObj.message;
						var returnArray = returnObj.returnArray;

						if(message != ""){
							layer.alert(message.toString(),{icon: 0});
						}else{
							if(returnArray.length === 0){
								layer.alert('请选择定数包',{icon: 0});
							}else{
								for(var i=0; i< returnArray.length; i++){
									var number = returnArray[i].number;
									var productCount = returnArray[i].productCount;

									$.ajax({
										url:'${ctx}/pd/pdProductStock/getStockRecordOutScan',
										type:'post',
										async:false,
										data:{
											productNo:number,
											productBarCode:number,
											batchNo:"",
											findFlag:""
										},
										success:function(data){
											layer.closeAll();
											sweepCodeinit();
											if(data.length>0){
												//已打钩的产品
												var idCheckList = new Array();
												//扫码前清空没有选中的
												$("#productTbody tr").each(function(index, data) {
													var checked = $($(data).find("input")[1]).is(':checked');
													if(!checked){
														$(this).remove();
													}else{
														idCheckList.push($($(data).find("input")[0]).val());
													}
												})
												if(data.length==1){
													var productStockId = data[0].id;
													//重复扫码+1
													var isHas = false;
													$("#productTbody tr").each(function(index, data) {
														var stockId = $($(data).find("input")[0]).val();
														if(productStockId==stockId){
															var productNum = $($(data).children('td').eq(9).find("input")).val();
															if(productNum != ""){
																var cuProductNum = parseInt(productCount) + parseInt(productNum);
																$($(data).children('td').eq(9).find("input")).val(cuProductNum);

																var productInputRejectedCount = $($(data).children('td').eq(9).find("input"));
																computeDosage(productInputRejectedCount)
																var sellingPrice = $($(data).children('td').eq(11)).text();
																$($(data).children('td').eq(12)).text((cuProductNum * parseFloat(sellingPrice)).toFixed(4));
																computeTotalMoney();//计算金额
																isHas = true;
																return false;
															}
														}
													})
													if(!isHas){
														var html = getProdHtmlOne(data);
														$("#productTbody").append(html);

														$("#productTbody tr").each(function(index, data) {
															var stockId = $($(data).find("input")[0]).val();
															if(productStockId==stockId){
																var productNum = $($(data).children('td').eq(9).find("input")).val();
																if(productNum != ""){
																	var cuProductNum = parseInt(productCount);
																	$($(data).children('td').eq(9).find("input")).val(cuProductNum);

																	var productInputRejectedCount = $($(data).children('td').eq(9).find("input"));
																	computeDosage(productInputRejectedCount)
																	var sellingPrice = $($(data).children('td').eq(11)).text();
																	$($(data).children('td').eq(12)).text((cuProductNum * parseFloat(sellingPrice)).toFixed(4));
																	// computeTotalMoney();//计算金额
																	isHas = true;
																	return false;
																}
															}
														})
														computeTotalMoney();//计算金额
													}
												}else{
													var html = getProdHtmlTwo(data,idCheckList);
													$("#productTbody").append(html);
												}
											}else{
												layer.alert('查询无结果，请重新扫描或输入正确号码',
														{icon:0},
														function(index) {
															layer.close(index);
															sweepCodeinit();
														});
											}
										}
									});

								}

								layer.closeAll();
							}
						}
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			});

			$("#choosePatien").click(function(){
				layer.open({
					type:2,
					title:"添加病人",
					content:'${ctx}/pd/pdPatien/choosePatienList',
					area:["965px","527px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						//$('tbody').append(addPack());
						var childWindow = layero.find('iframe')[0].contentWindow;
						var returnObj = childWindow.getPatienInfoForDosage($('#rowsRecord').val());
						var message = returnObj.message;
						if(message != ""){
							layer.alert(message.toString(),{icon: 0});
						}else{
							// $("#exeDeptNameInput").val(returnObj.);
							$("#oprDeptNameInput").val(returnObj.operationDepartment);
							$("#surgeonInput").val(returnObj.operationDoctor);
							$("#sqrtDoctorInput").val(returnObj.prescriberDoctor);
							$("#patientInfoInput").val(returnObj.name);
							$("#inHospitalNoInput").val(returnObj.inhospitalNo);
							layer.closeAll();
						}
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			})

		})



		function setCookie(json){
			// 收费代码存入cookie
			//获取当前时间
			var date=new Date();
			//cookie期限为2天
			date.setTime(date.getTime() + 24*60*60*1000*2);
			//将userId这个cookie删除
			document.cookie="charge_code_json="+json+"; expires="+date.toGMTString();
		}

		function getCookie(cname){
			var name = cname + "=";
			var ca = document.cookie.split(';');
			for(var i=0; i<ca.length; i++)
			{
				var c = ca[i].trim();
				if (c.indexOf(cname)==0) {
					return c.substring(name.length,c.length);
				}
			}
			return "";
		}

</script>
</body>
</html>