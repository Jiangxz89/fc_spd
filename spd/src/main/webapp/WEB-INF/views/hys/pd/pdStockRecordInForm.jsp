<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page import="com.thinkgem.hys.pd.constants.MinaGlobalConstants"%>
<%@ page import="java.util.Date" %>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
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
		.otherText>h3{font-weight:400;display:inline-block;padding:3px 10px 0 5px;}
		.allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.totalText{text-align: right;height: 50px;line-height: 50px;}
	</style>
	<title>新增入库</title>
	<%--<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>--%>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/js/barcode.js?time=<%= new Date().getTime() %>"></script>
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

			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var storeroomType = $("#storeroomType").val();
					var supplierId = $("#supplierId").val();
					var inType = $("#inType").val();
					if(inType == ""){
						layer.alert('请选择入库类型',{icon:0});
						$("#inType").focus();
						return false;
					}
					//校验是否已经扫码
					var len = $('#productTbody>tr').length;
					if(len < 1){
						layer.alert('请添加产品',{icon:0});
						return false;
					}

					if(storeroomType == "0" && inType == "0" && supplierId == ""){
						layer.alert('请选择供应商',{icon:0});
						$("#supplierId").focus();
						return false;
					}else{
						//校验供应商是否正确
						var bo = true;
						var supId = $("#supplierId").val();
						$("#productTbody tr").each(function(index, data) {
							var checked = $($(data).find("input")[0]).is(':checked');
							if(checked){
								var supplierId = $(this).children('td').find("input[class='supplierId']").val();
								if(supId!=supplierId){
									bo = false;
									layer.alert("第"+(index+1)+"行产品关联的供应商与选择的供应商不一致,请检查!",{icon:0});
									return false;
								}
							}
						})
						if(!bo){
							return bo;
						}
						/*$("input[class*='supplierId']").each(function(i,v){
							if(supId!=$(this).val()){
								bo = false;
								layer.alert("第"+(i+1)+"行产品关联的供应商与选择的供应商不一致,请检查!",{icon:0});
								return false;
							}
						});
						if(!bo){
							return bo; 
						}*/
					}
					var outId = $("#outId").val();
					if(storeroomType == "0" && inType != "0" && outId == ""){
						layer.alert('出库库房不能为空！',{icon:0});
						return false;
					}
					if(storeroomType != "0" && outId == ""){
						layer.alert('出库库房不能为空！',{icon:0});
						return false;
					}

					//扫码前校验是否勾选所需提交的内容
					var isLen = false;
					$("#productTbody tr").each(function(index, data) {
						var checked = $($(data).find("input")[0]).is(':checked');
						if(checked){
							isLen = true;
							return false;
						}
					})
					if (isLen==false) {
						layer.alert('请勾选您所需要提交的内容', {icon: 0});
						return false;
					}
					
					if(!validateProd()){
						return false;
					}
					if(storeroomType == "0" && inType == "0"){
						if(!validateProdIsurgent()){  //紧急产品
							return false;
						}
/* 						if(!validateInvoice()){  //发票
							return false;
						} */  //2018年6月28日16:16:48 取消发票校验
						if(!validateTemperature()){  //温度
							return false;
						}
						if(!validateHumidity()){   //湿度
							return false;
						}
					}
					
					dealStoreInfoTableName();
					dealInvoiceTableName();
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
		
		//提交时调用，产品校验
		function validateProd() {
			var bAndLValidateFlag = true;
			$("#productTbody tr").each(function(index, data){
				var checked = $($(data).find("input")[0]).is(':checked');
				if(checked){
					var batchNoObj = $(this).children('td').find("input[class*='batchNo']");
					var vf=alertWarnEmpty("批次号不能为空",batchNoObj);
					if(!vf){
						bAndLValidateFlag=false;
						return false;
					}
					var limitDateObj = $(this).children('td').find("input[class*='limitDate']");
					var vf=alertWarnEmpty("有效期不能为空",limitDateObj);
					if(!vf){
						bAndLValidateFlag=false;
						return false;
					}
                    var sameBol=isSameDay("有效期不能低于当天时间",limitDateObj);
                    if(!sameBol){
                        bAndLValidateFlag=false;
                        return false;
                    }
					var productNumObj = $(this).children('td').find("input[class*='productNum']");
					var ep=alertWarnEmpty("数量不能为空！",productNumObj);
					if(!ep){
						bAndLValidateFlag=false;
						return false;
					}
					var vf=alertWarn("请输入大于0的整数",productNumObj);
					if(!vf){
						bAndLValidateFlag=false;
						return false;
					}
				}
			})
			if(!bAndLValidateFlag){
				return false;
			}


			/*var batchNoValidateFlag = true;
			//校验批次号
			$("#productTbody tr").each(function(index, data){
				var checked = $($(data).find("input")[0]).is(':checked');
				if(checked){
					var batchNoObj = $(this).children('td').find("input[class*='batchNo']");
					var vf=alertWarnEmpty("批次号不能为空",batchNoObj);
					if(!vf){
						batchNoValidateFlag=false;
						return false;
					}
				}
			})
			if(!batchNoValidateFlag){
				return false;
			}*/

/*			$("input[class*='batchNo']").each(function(i,v){
				var vf=alertWarnEmpty("批次号不能为空",$(this));
				if(!vf){
					validateFlag=true;
					return false;
				}
			});*/

/*			var limitDateValidateFlag = true;
			$("#productTbody tr").each(function(index, data){
				var checked = $($(data).find("input")[0]).is(':checked');
				if(checked){
					var limitDateObj = $(this).children('td').find("input[class*='limitDate']");
					var vf=alertWarnEmpty("有效期不能为空",limitDateObj);
					if(!vf){
						limitDateValidateFlag=false;
						return false;
					}
				}
			})

			if(!limitDateValidateFlag){
				return false;
			}*/
/*			//校验有效期
			$("input[class*='limitDate']").each(function(i,v){
				var vf=alertWarnEmpty("有效期不能为空",$(this));
				if(!vf){
					validateFlag=true;
					return false;
				}
			});*/
			//校验数字
/*			var numValidateFlag = true;
			$("#productTbody tr").each(function(index, data){
				var checked = $($(data).find("input")[0]).is(':checked');
				if(checked){
					var productNumObj = $(this).children('td').find("input[class='productNum']");
					var ep=alertWarnEmpty("数量不能为空！",productNumObj);
					if(!ep){
						numValidateFlag=false;
						return false;
					}
					var vf=alertWarn("请输入大于0的整数",productNumObj);
					if(!vf){
						numValidateFlag=false;
						return false;
					}
				}
			})
			if(!numValidateFlag){
				return false;
			}*/
		/*	//校验数字
			$("input[class*='productNum']").each(function(i,v){
				var ep=alertWarnEmpty("数量不能为空！",$(this));
				if(!ep){
					validateFlag=true;
					return false;
				}
				var vf=alertWarn("请输入大于0的整数",$(this));
				if(!vf){
					validateFlag=true;
					return false;
				}
			});*/

			var validateFlag = true;
			$("#productTbody tr").each(function(index, data){
				var checked = $($(data).find("input")[0]).is(':checked');
				if(checked){
					var count = index + 1;
					//紧急产品校验
					var productNum = $($(data).find("input")[6]);  //数量
					var productNum_val = productNum.val();
					var productNum_int = parseInt(productNum_val);
					var isUrgent = $($(data).find("input")[9]).val();  //是否紧急产品
					var surplusPurCount = $($(data).find("input")[10]).val();
					if(isUrgent == "a1"){
						if(surplusPurCount == ""){
							layer.alert('产品第'+count+'行，紧急产品剩余数量不存在',{icon:0});
							validateFlag = false;
							return false;
						}else{
							var surplusPurCount_int = parseInt(surplusPurCount);
							if(productNum_int > surplusPurCount_int){
								layer.alert('产品第'+count+'行，紧急产品数量:'+productNum_int+'不能大于剩余采购量:'+surplusPurCount_int,{icon:0})
								validateFlag = false;
								return false;
							}
						}
					}
				}
			});
			if(!validateFlag){
				return false;
			}
			return true;
		}
		
		//提交时调用，紧急产品校验
		function validateProdIsurgent() {
			var validateFlag = false;
			$("#productTbody tr").each(function(index, data){
				var count = index + 1;
				var productNum_val = $($(data).find("input")[6]).val();  //数量
				
		        //紧急产品校验
		        var productNum_int = parseInt(productNum_val);
		        var isUrgent = $($(data).find("input")[9]).val();  //是否紧急产品
		        var surplusPurCount = $($(data).find("input")[10]).val();
		        if(isUrgent == "a1"){
		        	if(surplusPurCount == ""){
		    			layer.alert('产品第'+count+'行，紧急产品剩余数量不存在',{icon:0});
		    			validateFlag = true;
		    			return false;
		        	}else{
		        		var surplusPurCount_int = parseInt(surplusPurCount);
		        		if(productNum_int > surplusPurCount_int){
		        			layer.alert('产品第'+count+'行，紧急产品数量:'+productNum_int+'不能大于剩余采购量:'+surplusPurCount_int,{icon:0})
		        			validateFlag = true;
		        			return false;
		        		}
		        	}
		        }
			});
			if(validateFlag){
				return false;
			}
			return true;
		}
		
		//提交时调用，发票校验
		function validateInvoice(){
			var validateFlag = false;
			//发票日期校验
			$("input[class*='ticketTime']").each(function(i,v){
				var vf=alertWarnEmpty("发票日期不能为空!",$(this));
				if(!vf){
					validateFlag=true;
					return false;
				}
			});
			//发票号校验
			$("input[class*='ticketNum']").each(function(i,v){
				var vf=alertWarnEmpty("发票号不能为空！",$(this));
				if(!vf){
					validateFlag=true;
					return false;
				}
			});
			//发票金额校验
			$("input[class*='ticketMoney']").each(function(i,v){
				var vf=alertWarnEmpty("发票金额不能为空！",$(this));
				if(!vf){
					validateFlag=true;
					return false;
				}
				var mo = alertWarnMoney("发票金额不正确！",$(this));
				if(!mo){
					validateFlag=true;
					return false;
				}
			});
			//销售日期校验
			$("input[class*='sellTime']").each(function(i,v){
				var vf=alertWarnEmpty("销售日期不能为空！",$(this));
				if(!vf){
					validateFlag=true;
					return false;
				}
			});
			if(validateFlag){
				return false;
			}
			return true;
		}
		
		//温度
		function validateTemperature() {
			var vf=alertWarnEmpty("温度不能为空！",$("#temperature"));
			if(!vf){
				return false;
			}
			var vf=alertWarn("温度不正确！",$("#temperature"));
			if(!vf){
				return false;
			}
			return true;
		}
		//湿度校验
		function validateHumidity() {
			var vf=alertWarnEmpty("湿度不能为空！",$("#humidity"));
			if(!vf){
				return false;
			}
			var vf=alertWarn("湿度不正确！",$("#humidity"));
			if(!vf){
				return false;
			}
			return true;
		}
		
		//提交时调用，绑定产品参数
		function dealStoreInfoTableName() {
			$("#productTbody tr").each(function(index, data){
				var checked = $($(data).find("input")[0]).is(':checked');
				if(checked){
					var name = 'productList[' + index + '].productId';
					$($(data).find("input")[1]).attr("name", name);
					var name2 = 'productList[' + index + '].productBarCode';
					$($(data).find("input")[3]).attr("name", name2);
					var name3 = 'productList[' + index + '].batchNo';
					$($(data).find("input")[4]).attr("name", name3);
					var name4 = 'productList[' + index + '].limitDate';
					$($(data).find("input")[5]).attr("name", name4);
					var name5 = 'productList[' + index + '].productNum';
					$($(data).find("input")[6]).attr("name", name5);
					var name6 = 'productList[' + index + '].inPrice';
					$($(data).find("input")[7]).attr("name", name6);
					var name7 = 'productList[' + index + '].stockPosition';
					$($(data).find("input")[8]).attr("name", name7);
                    var name9 = 'productList[' + index + '].importNo';
                    $($(data).find("input")[9]).attr("name", name9);
				}
			});
		}
		
		//提交时调用，绑定发票参数
		function dealInvoiceTableName() {
			$("#contentTableTbody3 tr").each(function(index, data){
				var name = 'invoiceList[' + index + '].invoiceDate';
				$($(data).find("input")[0]).attr("name", name);
				var name1 = 'invoiceList[' + index + '].invoiceNo';
				$($(data).find("input")[1]).attr("name", name1);
				var name2 = 'invoiceList[' + index + '].invoiceAmount';
				$($(data).find("input")[2]).attr("name", name2);
				var name3 = 'invoiceList[' + index + '].saleDate';
				$($(data).find("input")[3]).attr("name", name3);
				
			});
		}
				
		//快捷键 ctrl+alt
		$(document).keydown(function (event) {
		    if(event.ctrlKey && event.keyCode == 18)  $("#number").focus();
		});
		
	</script>
</head>
<body>
	<form:form id="inputForm" style="margin:0;" modelAttribute="pdStockRecord" action="${ctx}/pd/pdStockRecord/saveIn" method="post" onkeydown="if(event.keyCode==13){return false;}" class="form-horizontal">
		<form:hidden path="id"/>
		<input id="storeroomType" name="storeroomType" type="hidden" value="${fns:getUser().storeroomType}"/>
		<sys:message content="${message}"/>		
		<div class="right-main-box">
			<div class="btnBox">
				<h4>新增入库</h4>
			</div>
			<div class="newSearchBox">
					<label for="">入库单号</label>
					<form:input path="recordNo" htmlEscape="false" style="width:165px;" maxlength="64" class="input-xlarge " readOnly="true"/>
					<label for="">入库日期</label>
					<input name="recordDate" type="text" readonly="readonly" maxlength="10" id="recordDate" class="input-xlarge"
						value="<fmt:formatDate value="${pdStockRecord.recordDate}" pattern="yyyy-MM-dd"/>"/>
					<label style="width:65px"><span class="mustIcon">*</span>入库类型</label>
					<%--<form:select path="inType" class="input-medium">//去除调拨入库和退货入库2019年4月17日11:34:08
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('stock_in_type') }" itemLabel="label" itemValue="value" htmlEscape="false"/> 
					</form:select>--%>
					<form:select path="inType" class="input-medium">
						<%--<form:option value="" label="请选择"/>--%>
						<form:option value="0" label="正常入库"/>
					</form:select>
				<br>
					<label for="">操作人员</label>
					<form:input path="recordPeople" style="width:165px;" htmlEscape="false" maxlength="64" class="input-xlarge " readOnly="true"/>
					<label for="">出库库房</label>
					<form:input path="outName" htmlEscape="false" readOnly="true" maxlength="64" class="input-medium"/>
					<input id="outId" name="outId" type="hidden"/>
				<%--<c:choose>
				<c:when test="${fns:getUser().storeroomType eq 0}">
					<div id="supplierDiv">
				</c:when>
				<c:otherwise>
					<div id="supplierDiv" style="display: none;">
				</c:otherwise>
				</c:choose>--%>
				<label style="width:65px" ><span class="mustIcon">*</span>&nbsp;&nbsp;供应商</label>
				<form:select path="supplierId" style="width:260px;margin-right: 0"  class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:findSupplierList() }" itemLabel="nameAndpinyin" itemValue="id" htmlEscape="false"/>
				</form:select>
				<a href="###" id="sanZheng" class="hcy-btn" style="color:#22A7FF;">查看三证</a>
				<%--</div>--%>
				<br>
				<a href="###" class="hcy-btn hcy-btn-primary" style="display: none;" style="line-height: 1.5;" id="addFormOrder">从订单导入</a>
				<form:hidden path="orderNo" id="orderNo"/>
				<input id="iptType" name="iptType" type="hidden"/>
				<a href="###" class="hcy-btn hcy-btn-primary" style="display: none;" style="line-height: 1.5;" id="addFormApply">从申领单导入</a>
				<form:hidden path="applyNo" id="applyNo"/>
				<a href="###" class="hcy-btn hcy-btn-primary" style="display: none;" style="line-height: 1.5;" id="addFormAllocation">从调拨单导入</a>
				<form:hidden path="allocationNo" id="allocationNo"/>
				<a href="###" class="hcy-btn hcy-btn-primary" style="display: none;" style="line-height: 1.5;" id="addFormDosagert">从退货单导入</a>
				<form:hidden path="dosagertNo" id="dosagertNo"/>
				<input id="inId" name="inId" type="hidden">
			</div>
			<div class="cgdTableBox" style="display: none;">
				<table id="contentTableTbody2" class="hcy-public-table" >
					<thead>
						<tr>
							<th style="width:120px">单号</th>
							<th style="width:160px">产品名称</th>
							<th style="width:160px">产品编号</th>
							<th style="width:100px">规格</th>
							<th style="width:100px">型号</th>
							<th style="width:100px">数量</th>
							<th style="width:100px">单位</th>
						</tr>
					</thead>
					<tbody id="canzTbody">
					</tbody>
				</table>
			</div>
			<div class="searchBox" style="display:none" id='scanCodeDiv'>
				<div>
					<label for="">产品编码</label>
					<input type="text" autocomplete="off" name="number" id="number"/>
				</div>
				<div>
					<label for="">二级条码</label>
					<input type="text" autocomplete="off" name="barCode" id="barCode" style="width:200px;"/>
				</div>
				<div><label style="width:100%;">提示：按<span style="color:red;">Ctrl+Alt</span>键快速定位到扫码输入框</label></div>
				</br>
				<div>
					<label for="">产品名称</label>
					<input type="text" autocomplete="off" name="name" id="pdoductName"/>
				</div>
				<div>
					<label for="">规格</label>
					<input type="text" autocomplete="off" name="spec" id="pdoductSpec"/>
				</div>
				<div>
					<label for="">型号</label>
					<input type="text" autocomplete="off" name="version" id="pdoductVersion"/>
				</div>
			</div>
			<div class="tableBox" style="overflow:auto; width:100%;height:300px;">
				<table id="contentTableTbody2" class="hcy-public-table" >
					<thead>
						<tr>
							<th id='operation' style="display:none";width:50px">操作</th>
							<th style="width:100px">产品名称</th>
							<th style="width:100px">产品编号</th>
							<th style="width:100px">产品条码</th>
							<th style="width:80px">规格</th>
							<th style="width:80px">型号</th>
							<th>单位</th>
							<th style="width:90px">批号</th>
							<th style="width:100px">有效期</th>
							<th>数量</th>
							<th>入库单价</th>
							<th>金额</th>
							<th>库位</th>
							<th>订单号</th>
							<th>注册证号</th>
							<th>生产厂家</th>
						</tr>
					</thead>
					<tbody id="productTbody">
						<c:forEach items="${pdStockRecord.productList}" var="product">
							<tr>
								<td><i class="fa fa-trash-o addProBtn"></i></td>
								<td>${product.productName }
								<input type='hidden' name='productId' value='${product.productId }'/></td>
								<td>${product.number }</td>
								<td><input type="text" style="width:60px;" value="${product.productBarCode }"/></td>
								<td>${product.spec }</td>
								<td>${product.version }</td>
								<td>${product.unit }</td>
								<td><input type="text" style="width:70px;" value="${product.batchNo }"/></td>
								<td><input type="text" class="limitDate" style="width:85px;" value='<fmt:formatDate value="${product.limitDate}" pattern="yyyy-MM-dd" />'/></td>
								<td><input type="text" style="width:40px;" value="${product.productNum }"/></td>
								<td>${product.inPrice }</td>
								<td>${product.pdTotalPrice }</td>
								<td><input type='text' name='' id='' value='${product.stockPosition }' style='width:40px;'/></td>
								<td><a href='###' class='overLook' style='width:60px;' title='${product.importNo }'>${product.importNo };
								<input type='hidden' name='importNo' value='${product.importNo }'/></td>
								<td>${product.registration }<%--<a href="###" title="${product.registration }" class="overLook">${product.registration }</a>--%></td>
								<td><a href="###" class="overLook" title="${product.venderName }">${product.venderName }</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="totalText">
				<span class="allNum">总数量:<span class="total_number">0</span></span>
                <span class="allNum">总金额:<span class="total_price">0.00</span></span>
			</div>
			<div class="billBox" style="display: none;">
				<h4 style="padding:10px 0 20px 0;">发票信息</h4>
				<table class="billTable">
					<thead>
						<tr>
							<th>操作</th>
							<th>发票日期</th>
							<th>发票号</th>
							<th>发票金额</th>
							<th>销售日期</th>
						</tr>
					</thead>
					<tbody id="contentTableTbody3">
						<c:forEach items="${pdStockRecord.invoiceList}" var="invoice">
							<tr>
								<td><i class="fa fa-plus-square addTicketTr"></i><i class="fa fa-trash-o delTicketTr"></i></td>
								<td><input type="text" class="ticketTime" style="background:#fff;" value="${invoice.invoiceDate }" /></td>
								<td><input type="text" class="ticketNum" value="${invoice.invoiceNo }" maxlength="30"/></td>
								<td><input type="text" class="ticketMoney" value="${invoice.invoiceAmount }" maxlength="8"/></td>
								<td><input type="text" class="sellTime" style="background:#fff;" value="${invoice.saleDate }" /></td>
							</tr>
						</c:forEach>
						<c:if test="${empty pdStockRecord.id}">
						<tr>
							<td><i class="fa fa-plus-square addTicketTr"></i><i class="fa fa-trash-o delTicketTr"></i></td>
							<td><input type="text" class="ticketTime" style="background:#fff;" readonly='readonly'/></td>
							<td><input type="text" class="ticketNum" maxlength="30"/></td>
							<td><input type="text" class="ticketMoney" maxlength="10"/></td>
							<td><input type="text" class="sellTime" style="background:#fff;" readonly='readonly'/></td>
						</tr>
						</c:if>
					</tbody>
				</table>
				<label for=""><span class="mustIcon">*</span>验收结果</label>
				<select name="testResult" id="">
					<option value="0">合格</option>
					<option value="1">不合格</option>
				</select>
				<label for="" style="margin-left:25px;"><span class="mustIcon">*</span>储运状态</label>
				<select name="storageResult" id="">
					<option value="0">合格</option>
					<option value="1">不合格</option>
				</select><br />
				<label for=""><span class="mustIcon">*</span>温度</label>
				<form:input path="temperature" htmlEscape="false" maxlength="2" value="25" class="input-xlarge "/>
				<span>℃</span>
				<label for=""><span class="mustIcon">*</span>湿度</label>
				<form:input path="humidity" htmlEscape="false" maxlength="2" value="50" class="input-xlarge "/>
				<span>%</span>
			</div>
			<div class="otherText">
				<h3>备注</h3>
				<form:textarea path="remarks" class="remarkArea" maxlength="200"></form:textarea>
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
				<shiro:hasPermission name="pd:pdStockRecord:edit">
					<input id="btnSubmit" class="hcy-btn hcy-save" type="submit" value="保 存"/>&nbsp;
				</shiro:hasPermission>
				<a href="${ctx}/pd/pdStockRecord/inList" class="hcy-btn hcy-back" >返回</a>
			</div>
		</div>
	</form:form>
<script type="text/javascript">
$(function(){
	//入库日期
	//laydate.render({
	//   elem: '#recordDate'
	//});

	//进来默认是正常入库的状态2019年8月5日09:39:58
	function initIntype(){
        if(${fns:getUser().storeroomType} == "0"){
            showScanCodeDiv();
            $("#addFormOrder").show();
            $(".cgdTableBox").show();
            $('#supplierDiv').show();
            //条件判断显示发票
            showBillBox();
        }
        if(${fns:getUser().storeroomType} == "1"){
            $("#addFormApply").show();
        }
        $("#addFormAllocation").hide();
        $("#addFormDosagert").hide();
    }
    initIntype();
	//入库类型变更
	$("#inType").change(function(){
		hideScanCodeDiv();//隐藏扫码框
		hideBillBox();  //隐藏发票
		$(".cgdTableBox").hide();  //隐藏产品参看列表
		$('#supplierDiv').hide();  //隐藏供应商
		$('#productTbody').empty();  //清空产品信息
		
		var inType = $("#inType").val();
		if(inType == "0"){
			if(${fns:getUser().storeroomType} == "0"){
				showScanCodeDiv();
				$("#addFormOrder").show();
				$(".cgdTableBox").show();
				$('#supplierDiv').show();
				//条件判断显示发票
				showBillBox();
			}
			if(${fns:getUser().storeroomType} == "1"){
				$("#addFormApply").show();
			}
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
		}else{
			$("#addFormOrder").hide();
			$("#addFormApply").hide();
			$("#addFormAllocation").hide();
			$("#addFormDosagert").hide();
		}
	});	
	
	//采购单导入
	$("#addFormOrder").click(function(){
		//操作按钮显示
		$("#operation").css('display','block'); 
		$("#iptType").val("1");
		var orderStatus="<%=MinaGlobalConstants.PURCHASE_ORDER_STATUS_PASSED%>";
		var storeroomId="${fns:getUser().storeroomId}";
		layer.open({
			type:2,
			title:"选择订单",
			content:'${ctx}/pd/pdPurchaseOrderMerge/chooseOrderList?deptId='+storeroomId+'&orderStatus='+orderStatus,
			area:["800px","450px"],
			shade: [0.8, '#393D49'],
			btn:["确定","取消"],
			yes:function(index,layero){
				var childWindow = layero.find('iframe')[0].contentWindow;
				var result = childWindow.compositeHtml();
				if(result == 0){
					layer.alert('请选择采购单',{icon: 0});
				}else{
					$("#orderNo").val(result);
					$('#canzTbody').empty();
						
					//通过订单号，查询商品列表，并回显
					var urlPath ='${ctx}/pd/pdPurchaseOrder/queryProducts';
		    		// post请求
		    		$.ajax({
		     		url:urlPath,
		     		type:"post",
		    			data:{orderNo:result},  
		    			async:false, 
		    			success:function(data){ 
		    				var jo = JSON.parse(data);
		    				if(jo.code == 200){
		    					var div="";
	    						for (var i = 0; i < jo.result.length; i++) {
	    							div +=" <tr><td>"+jo.result[i].orderNo+"</td>";
	    							div +=" <td>"+jo.result[i].productName+"</td>";
	    							div +=" <td>"+jo.result[i].productCode+"</a></td>";
	    							div +=" <td>"+jo.result[i].productSpec+"</td>";
	    							div +=" <td>"+jo.result[i].productVersion+"</td>";
	    							div +=" <td>"+jo.result[i].applyCount+"</td>";
	    							div +=" <td>"+jo.result[i].productUnit+"</td></tr>";
	    						}
	    						$('#canzTbody').append(div);
								$("#number").focus();
		    				}else{
		    					layer.alert(jo.msg,{icon: 0});
		    				}
		    		} });
		    		
					layer.closeAll();
				}
			},
			btn2:function(){
				layer.closeAll();
			}
		})
	});
	
	//从申领单导入
	<%--$("#addFormApply").click(function(){--%>
		<%--//操作按钮隐藏--%>
		<%--$("#operation").css('display','none'); --%>
		<%--$("#iptType").val("2");--%>
		<%--var applyStatus="<%=MinaGlobalConstants.APPLY_ORDER_STATUS_OUT_STOCK%>,<%=MinaGlobalConstants.APPLY_ORDER_STATUS_IN_REJECT%>";  //已出库,入库驳回--%>
		<%--var storeroomId="${fns:getUser().storeroomId}";--%>
		<%--layer.open({--%>
			<%--type:2,--%>
			<%--title:"选择申领单",--%>
			<%--content:'${ctx}/pd/pdApplyOrder/pdAddApplyOrderBox?deptId='+storeroomId+'&applyStatuses='+applyStatus, //导入出库方已出库的,入库驳回--%>
			<%--area:["800px","450px"],--%>
			<%--shade: [0.8, '#393D49'],--%>
			<%--btn:["确定","取消"],--%>
			<%--yes:function(index,layero){	--%>
				<%--$('#canzTbody').html("");--%>
				<%--var childWindow = layero.find('iframe')[0].contentWindow;--%>
				<%--var applyNo = childWindow.compositeHtml();--%>
				<%--var inStroomId=childWindow.compositeInId();--%>
				<%--if(applyNo == null){--%>
					<%--layer.alert('请选择申领单',{icon: 0});--%>
				<%--}else{--%>
				    <%--$("#inId").val(inStroomId);--%>
					<%--$("#applyNo").val(applyNo);--%>
					<%--$('#productTbody').empty();--%>
					<%--$.getJSON('${ctx}/pd/pdStockRecord/getStockRecordOutProds',{inFromNo:applyNo,stockImpType:1},function(data){--%>
						<%--var code = data.code;--%>
						<%--if(code != "200"){--%>
							<%--layer.alert(data.message,{icon: 0});--%>
							<%--return false;--%>
						<%--}--%>
						<%--var outRecord = data.outStockRecord;--%>
						<%--$("#outId").val(outRecord.outId);--%>
						<%--$("#outName").val(outRecord.outName);--%>
						<%--var applyNo = outRecord.applyNo;--%>
						<%--var productlst = data.productList;--%>
						<%--var div="";					--%>
						<%--$.each(productlst,function(i,jo){--%>
							<%--var stockPosition = jo.stockPosition; if(!stockPosition){stockPosition="";}--%>
							<%--var limitDateStr = jo.limitDateStr;  if(!limitDateStr){limitDateStr="";}--%>
							<%--var sellingPrice = jo.sellingPrice;  if(!sellingPrice){sellingPrice=""; }--%>
							<%--var pdTotalPrice = jo.pdTotalPrice;  if(!pdTotalPrice){pdTotalPrice=""; }--%>
							<%----%>
							<%--//div +=" <tr><td><i style='pointer-events: none;' class='fa fa-trash-o addTicketTr'></i></td>";去掉操作2018年7月31日15:41:47--%>
							<%--div +=" <tr><td>"+jo.productName;--%>
							<%--div +=" <input type='hidden' name='productId' value='" + jo.productId + "'/></td>";--%>
							<%--div +=" <td>"+jo.number;--%>
							<%--div +=" <input type='hidden' name='productCode' value='" + jo.number + "'/></td>";--%>
							<%--div +=" <input type='hidden' name='productBarCode' value='" + jo.productBarCode + "' readonly='readonly' style='width:70px;'/>";--%>
							<%--div +=" <td style='width:70px;'>" + jo.productBarCode + "</td>";--%>
							<%--div +=" <td>"+jo.spec+"</td>";--%>
							<%--div +=" <td>"+jo.version+"</td>";--%>
							<%--div +=" <td>"+jo.unit+"</td>";--%>
							<%--div +=" <td style='width:70px;'>" + jo.batchNo + "</td>";--%>
							<%--div +=" <input type='hidden' name='' value='" + jo.batchNo + "' readonly='readonly' style='width:70px;'/>";--%>
							<%--div +=" <td style='width:85px;'>" + limitDateStr + "</td>";--%>
							<%--div +=" <input type='hidden' name='' class='limitDate' value='" + limitDateStr + "' readonly='readonly' style='width:85px;'/>";--%>
							<%--div +=" <td style='width:40px;'>" + jo.productNum + "</td>";--%>
							<%--div +=" <input type='hidden' name='' id='' value='" + jo.productNum + "' readonly='readonly' style='width:40px;'/>";--%>
							<%--div +=" <td style='width:60px;'>"+sellingPrice+"</td>";--%>
							<%--div +=" <td style='width:60px;'>"+pdTotalPrice+"</td>";--%>
							<%--div +=" <td style='width:40px;'>" + stockPosition + "</td>";--%>
							<%--div +=" <input type='hidden' name='' value='" + stockPosition + "' readonly='readonly' style='width:40px;'/>";--%>
							<%--div +=" <td><a href='###' class='overLook' title='"+applyNo+"'>"+applyNo;--%>
							<%--div +=" <input type='hidden' name='importNo' value='"+applyNo+ "'/></td>";--%>
							<%--/*div +=" <td>/!*<a href='###' class='overLook' title='"+jo.registration+"'>"*!/+jo.registration+"</td>";*/--%>
                            <%--div +=" <td>"+jo.registration+"</td>";--%>
							<%--div +=" <td><a href='###' class='overLook' title='"+jo.venderName+"'>"+jo.venderName+"</td></tr>";							--%>
						<%--});--%>
						<%--if(div){--%>
							<%--$('#productTbody').append(div);--%>
							<%--$("#number").focus();--%>
						<%--}--%>
					<%--});--%>
					<%--layer.closeAll();--%>
				<%--}												--%>
			<%--},--%>
			<%--btn2:function(){--%>
				<%--layer.closeAll();--%>
			<%--}--%>
		<%--})--%>
	<%--});--%>
	
	//从调拨单导入
	<%--$("#addFormAllocation").click(function(){--%>
		<%--//操作按钮隐藏--%>
		<%--$("#operation").css('display','none'); --%>
		<%--$("#iptType").val("4");--%>
		<%--var recordStates="<%=MinaGlobalConstants.APPLY_ORDER_STATUS_OUT_STOCK%>,<%=MinaGlobalConstants.APPLY_ORDER_STATUS_IN_REJECT%>";  //已出库,入库驳回--%>
		<%--var storeroomId="${fns:getUser().storeroomId}";--%>
		<%--layer.open({--%>
			<%--type:2,--%>
			<%--title:"选择调拨单",--%>
			<%--content:'${ctx}/pd/pdAllocationRecord/addAllocationInBox?in_id='+storeroomId+'&recordStates='+recordStates, //导入出库方已出库的--%>
			<%--area:["800px","450px"],--%>
			<%--shade: [0.8, '#393D49'],--%>
			<%--btn:["确定","取消"],--%>
			<%--yes:function(index,layero){--%>
				<%--$('#canzTbody').html("");--%>
				<%--var childWindow = layero.find('iframe')[0].contentWindow;--%>
				<%--var allocationCode = childWindow.compositeHtml();--%>
				<%--var inSId=childWindow.compositeInStroomId();--%>
				<%--if(allocationCode == null){--%>
					<%--layer.alert('请选择调拨单',{icon: 0});--%>
				<%--}else{					--%>
				    <%--$("#inId").val(inSId);--%>
					<%--$("#allocationNo").val(allocationCode);--%>
					<%--$('#productTbody').empty();--%>
					<%--$.getJSON('${ctx}/pd/pdStockRecord/getStockRecordOutProds',{inFromNo:allocationCode,stockImpType:2},function(data){--%>
						<%--var outRecord = data.outStockRecord;--%>
						<%--$("#outId").val(outRecord.outId);--%>
						<%--$("#outName").val(outRecord.outName);--%>
						<%--//var applyNo = outRecord.applyNo;--%>
						<%--var productlst = data.productList;--%>
						<%--var div="";					--%>
						<%--$.each(productlst,function(i,jo){		--%>
							<%--var stockPosition = jo.stockPosition; if(!stockPosition){stockPosition="";}--%>
							<%--var limitDateStr = jo.limitDateStr;  if(!limitDateStr){limitDateStr="";}--%>
							<%--var sellingPrice = jo.sellingPrice;  if(!sellingPrice){sellingPrice=""; }--%>
							<%--var pdTotalPrice = jo.pdTotalPrice;  if(!pdTotalPrice){pdTotalPrice=""; }--%>
							<%----%>
							<%--//div +=" <tr><td><i style='pointer-events: none;' class='fa fa-trash-o addTicketTr'></i></td>"; 去掉操作2018年7月31日15:42:10--%>
							<%--div +=" <tr><td>"+jo.productName+"";--%>
							<%--div +=" <input type='hidden' name='productId' value='" + jo.productId + "'/></td>";--%>
							<%--div +=" <td>"+jo.number;--%>
							<%--div +=" <input type='hidden' name='productCode' value='" + jo.number + "'/></td>";--%>
							<%--div +=" <input type='hidden' name='productBarCode' value='" + jo.productBarCode + "' readonly='readonly' style='width:70px;'/>";--%>
							<%--div +=" <td style='width:70px;'>" + jo.productBarCode + "</td>";--%>
							<%--div +=" <td>"+jo.spec+"</td>";--%>
							<%--div +=" <td>"+jo.version+"</td>";--%>
							<%--div +=" <td>"+jo.unit+"</td>";--%>
							<%--div +=" <td style='width:70px;'>" + jo.batchNo + "</td>";--%>
							<%--div +=" <input type='hidden' name='' value='" + jo.batchNo + "' readonly='readonly' style='width:70px;'/>";--%>
							<%--div +=" <td style='width:85px;'>" + limitDateStr + "</td>";--%>
							<%--div +=" <input type='hidden' class='limitDate' name='' value='" + limitDateStr + "' readonly='readonly' style='width:85px;'/>";--%>
							<%--div +=" <td style='width:40px;'>" + jo.productNum + "</td>";--%>
							<%--div +=" <input type='hidden' name='' value='" + jo.productNum + "' readonly='readonly' style='width:40px;'/>";--%>
							<%--div +=" <td style='width:60px;'>"+sellingPrice+"</td>";--%>
							<%--div +=" <td style='width:60px;'>"+pdTotalPrice+"</td>";--%>
							<%--div +=" <td style='width:40px;'>" + stockPosition + "</td>";--%>
							<%--div +=" <input type='hidden' name='' value='" + stockPosition + "' readonly='readonly' style='width:40px;'/>";--%>
							<%--div +=" <td><a href='###' class='overLook' title='"+allocationCode+"'>"+allocationCode+"";--%>
							<%--div +=" <input type='hidden' name='importNo' value='"+allocationCode+ "'/></td>";--%>
							<%--/*div +=" <td><a href='###' class='overLook' title='"+jo.registration+"'>"+jo.registration+"</td>";*/--%>
                            <%--div +="<td>"+jo.registration+"</td>";--%>
							<%--div +=" <td><a href='###' class='overLook' title='"+jo.venderName+"'>"+jo.venderName+"</td></tr>";							--%>
						<%--});--%>
						<%--if(div){--%>
							<%--$('#productTbody').append(div);--%>
							<%--$("#number").focus();--%>
						<%--}--%>
					<%--});--%>
					<%--layer.closeAll();--%>
				<%--}--%>
			<%--},--%>
			<%--btn2:function(){--%>
				<%--layer.closeAll();--%>
			<%--}--%>
		<%--})--%>
	<%--});--%>
	
	//从退货单导入
	<%--$("#addFormDosagert").click(function(){--%>
		<%--//操作按钮隐藏--%>
		<%--$("#operation").css('display','none'); --%>
		<%--$("#iptType").val("3");--%>
		<%--var recordState="<%=MinaGlobalConstants.STOCK_RECORD_STATE_1%>";--%>
		<%--var storeroomId="${fns:getUser().storeroomId}";--%>
		<%--var outType="<%=MinaGlobalConstants.STOCK_OUT_TYPE_1%>";--%>
		<%--var returnState="<%=MinaGlobalConstants.RETURN_STATE_0%>";--%>
		<%--layer.open({--%>
			<%--type:2,--%>
			<%--title:"选择退货出库单",--%>
			<%--content:'${ctx}/pd/pdStockRecord/outListBox?inId='+storeroomId+'&recordState='+recordState+'&outType='+outType+'&returnState='+returnState,--%>
			<%--area:["800px","450px"],--%>
			<%--shade: [0.8, '#393D49'],--%>
			<%--btn:["确定","取消"],--%>
			<%--yes:function(index,layero){	--%>
				<%--$('#canzTbody').html("");--%>
				<%--var childWindow = layero.find('iframe')[0].contentWindow;--%>
				<%--var dosagertNo = childWindow.compositeHtml();				--%>
				<%--//var inSId=childWindow.compositeInStroomId();--%>
				<%--if(dosagertNo == null){--%>
					<%--layer.alert('请选择退货出库单',{icon: 0});--%>
				<%--}else{--%>
					<%--//$("#iptType").val("1");--%>
				    <%--//$("#inId").val(inSId);--%>
				    <%--$("#dosagertNo").val(dosagertNo);				   --%>
					<%--$('#productTbody').empty();--%>
					<%--$.getJSON('${ctx}/pd/pdStockRecord/getStockRecordOutProds',{inFromNo:dosagertNo,stockImpType:3},function(data){--%>
						<%--var outRecord = data.outStockRecord;--%>
						<%--$("#outId").val(outRecord.outId);--%>
						<%--$("#outName").val(outRecord.outName);--%>
						<%--var productlst = data.productList;--%>
						<%--var div="";--%>
						<%--$.each(productlst,function(i,jo){--%>
							<%--var stockPosition = jo.stockPosition; if(!stockPosition){stockPosition="";}--%>
							<%--var limitDateStr = jo.limitDateStr;  if(!limitDateStr){limitDateStr="";}--%>
							<%--var sellingPrice = jo.sellingPrice;  if(!sellingPrice){sellingPrice=""; }--%>
							<%--var pdTotalPrice = jo.pdTotalPrice;  if(!pdTotalPrice){pdTotalPrice=""; }--%>
							<%--//div +=" <tr><td><i style='pointer-events: none;' class='fa fa-trash-o addTicketTr'></i></td>";去掉操作2018年7月31日15:42:51--%>
							<%--div +=" <tr><td>"+jo.productName+"";--%>
							<%--div +=" <input type='hidden' name='productId' value='" + jo.productId + "'/></td>";--%>
							<%--div +=" <td>"+jo.number+"";--%>
							<%--div +=" <input type='hidden' name='productCode' value='" + jo.number + "'/></td>";--%>
							<%--div +=" <td style='width:70px;'>" + jo.productBarCode + "</td>";--%>
							<%--div +=" <input type='hidden' name='productBarCode' value='" + jo.productBarCode + "' readonly='readonly' style='width:70px;'/>";--%>
							<%--div +=" <td>"+jo.spec+"</td>";--%>
							<%--div +=" <td>"+jo.version+"</td>";--%>
							<%--div +=" <td >"+jo.unit+"</td>";--%>
							<%--div +=" <td style='width:70px;'>" + jo.batchNo + "</td>";--%>
							<%--div +=" <input type='hidden' name='' value='" + jo.batchNo + "' readonly='readonly' style='width:70px;'/>";--%>
							<%--div +=" <td style='width:85px;'>" + limitDateStr + "</td>";--%>
							<%--div +=" <input type='hidden' class='limitDate' name='' value='" + limitDateStr + "' readonly='readonly' style='width:85px;'/>";--%>
							<%--div +=" <td style='width:40px;'>" + jo.productNum + "</td>";--%>
							<%--div +=" <input type='hidden' name='' value='" + jo.productNum + "' readonly='readonly' style='width:40px;'/>";--%>
							<%--div +=" <td style='width:60px;'>"+sellingPrice+"</td>";--%>
							<%--div +=" <td style='width:60px;'>"+pdTotalPrice+"</td>";--%>
							<%--div +=" <td style='width:40px;'>" + stockPosition + "</td>";--%>
							<%--div +=" <input type='hidden' name='' value='" + stockPosition + "' readonly='readonly' style='width:40px;'/>";--%>
							<%--div +=" <td><a href='###' class='overLook' title='"+dosagertNo+"'>"+dosagertNo+"";--%>
							<%--div +=" <input type='hidden' name='importNo' value='"+dosagertNo+ "'/></td>";--%>
							<%--/*div +=" <td><a href='###' class='overLook' title='"+jo.registration+"'>"+jo.registration+"</td>";*/--%>
                            <%--div +=" <td>"+jo.registration+"</td>";--%>
							<%--div +=" <td><a href='###' class='overLook' title='"+jo.venderName+"'>"+jo.venderName+"</td></tr>";							--%>
						<%--});--%>
						<%--if(div){--%>
							<%--$('#productTbody').append(div);--%>
							<%--$("#number").focus();--%>
						<%--}--%>
					<%--});--%>
					<%--layer.closeAll();--%>
				<%--}--%>
			<%--},--%>
			<%--btn2:function(){--%>
				<%--layer.closeAll();--%>
			<%--}--%>
		<%--})--%>
	<%--});--%>
	
	//删除产品行
	$(document).on("click",".delProBtn",function(){
		$(this).parents("tr").remove();			
	});
	
	//添加发票行
	$(document).on("click",".addTicketTr",function(){
		var html="";
		html='<tr>'+
				'<td><i class="fa fa-plus-square addTicketTr"></i><i class="fa fa-trash-o delTicketTr"></i></td>'+
				'<td><input type="text" class="ticketTime" /></td>'+
				'<td><input type="text" class="ticketNum" maxlength="30"/></td>'+
				'<td><input type="text" class="ticketMoney" maxlength="10"/></td>'+
				'<td><input type="text" class="sellTime" /></td>'+
			'</tr>';
		$(".billTable>tbody").append(html);
		//日期插件
		lay('.ticketTime').each(function(){
			laydate.render({
		 		elem: this,
                trigger: 'click'
			});
		});
		lay('.sellTime').each(function(){
			laydate.render({
		 		elem: this,
                trigger: 'click'
			});
		})
	});
	
	//删除行
	$(document).on("click",".delTicketTr",function(){
		if($(".billTable>tbody>tr").length>1){
			$(this).parents("tr").remove();
		}
	});

    //复选框点击事件
    $("#productTbody").on('click',".checkInp",function(){
        computeTotalMoney();
    })

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
	
	//扫描产品  二级条码变化时
	$("#barCode").keydown(function(event){
		$("#operation").css('display','block'); 
    	if(event.keyCode != "13"){
    		return;
    	}
		/***
		if($("#iptType").val() == ""){
			layer.alert('请选择采购单导入后扫码!',{icon: 0});
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		if($("#iptType").val() != "1"){
			layer.alert('非采购单导入，不支持扫码!',{icon: 0});
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		***/
		var inType = $("#inType").val();
		var storeroomType = $("#storeroomType").val();
		if(inType == ""){
			layer.alert('请选择入库类型后扫码!',{icon: 0});
			$("#inType").focus();
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		if(inType != "<%=MinaGlobalConstants.STOCK_IN_TYPE_0%>"){
			layer.alert("非'正常入库'，不支持扫码!", {icon: 0});
			$("#number").focus();
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		if(storeroomType != "0"){
			layer.alert("非'一级库房'，不支持扫码!", {icon: 0});
			$("#number").focus();
			$("#number").val("");
			$("#barCode").val("");
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
		var limitDate = "";
		var secondCode = barCode;
		if(number.substring(0,2) != "93"){
			if(!scanCode(number, barCode)){  //解析
				$("#number").val("");
				$("#barCode").val("");
				$("#number").focus();
				return false;
			}  
			number = upn;
			if(_secondCode != ""){ secondCode = _secondCode; }
			if(_Lot != ""){ batchNo = _Lot;}
			if(_ExpDate != ""){ limitDate = _ExpDate;}
		}

		//产品条码修正
		if(initBarCode.indexOf(initNumber) != -1){
			secondCode =initBarCode;
		}else{
			secondCode = initNumber + initBarCode;
		}

		//已打钩的产品
		var idCheckList = new Array();
		//扫码前清空没有选中的
		$("#productTbody tr").each(function(index, data) {
			var checked = $($(data).find("input")[0]).is(':checked');
			if(!checked){
				$(this).remove();
			}else{
				var productCode = $($(data).find("input")[2]).val();
				var productBarCode = $($(data).find("input")[3]).val();
				idCheckList.push(productCode+"_"+productBarCode);
			}
		})
		
		//重复扫码+1
		var isHas = false;
		var lIndex = loading('扫码成功，请稍等...');
		$("#productTbody tr").each(function(index, data){
			var productCode = $($(data).find("input")[2]).val();
			var productBarCode = $($(data).find("input")[3]).val();
			if(number == productCode && secondCode == productBarCode){
				var productNum = $($(data).find("input")[6]).val();
				if(productNum != ""){
					$($(data).find("input")[6]).val(1 + parseInt(productNum));

					var productPrice =  Number($(data).find(".td_productPrice").text());
                    var productPriceTot = ((1 + parseInt(productNum)) * productPrice).toFixed(4);
                    $(data).find(".td_productPriceTot").text(productPriceTot);

                    computeTotalMoney();
					isHas = true;
					$("#number").val("");
					$("#barCode").val("");
					$("#number").focus();
					return false;
				}
			}
		});
		if(isHas){
			$("#number").val("");
			$("#barCode").val("");
			layer.closeAll();
			return false;
		}
		
		var orderNo = $("#orderNo").val();
		var urlPath ='${ctx}/pd/pdStockRecord/stockRecordInScan';
		$.ajax({
	 		url:urlPath,
	 		type:"post",
			data:{number:number,barCode:secondCode},  
			async:false, 
			success:function(data){
				var jo = JSON.parse(data);
				var supId = $("#supplierId").val();
				if(jo.code == "200"){
					if(jo.supplierId){
						if(supId && supId != jo.supplierId){
							layer.closeAll();
							$("#number").focus();
							layer.alert("产品关联的供应商和选择的供应商不一致，请确认后重新扫描,或更换供应商！ ",
									{icon:0},
									function(index) {
										layer.close(index);
										$("#number").val("");
										$("#barCode").val("");
										$("#number").focus();
					                });
							return false;
						}else if(!supId){
							$("#supplierId").select2("val",jo.supplierId);  //如果没有选默认选择第一次扫描的供应商
						}
						layer.closeAll();
						var productPrice = jo.productPrice;   if(!productPrice){productPrice="";}

						var div="";
						div +=" <tr><td><input type='checkbox' checked='checked' class='checkInp'/></td>";
						div +=" <td>"+jo.productName+"";
						div +=" <input type='hidden' name='productId' value='" + jo.productId + "'/></td>";
						div +=" <td>"+jo.productCode;
						div +=" <input type='hidden' name='productCode' value='" + jo.productCode + "'/></td>";
						div +=" <td><input type='text' readonly='readonly' style='width:60px;' value='" + jo.barCode + "''/></td>";
						div +=" <td>"+jo.productSpec+"</td>";
						div +=" <td>"+jo.productVersion+"</td>";
						div +=" <td>"+jo.productUnit+"</td>";
						div +=" <td><input type='text' name='' class='batchNo' value='"+batchNo+"' style='width:80px;'/></td>";
						div +=" <td><input type='text' class='limitDate' name='' value='"+limitDate+"' readonly='readonly' style='width:85px;'/></td>";
						div +=" <td><input type='text' name='' class='productNum' value='1' style='width:40px;'/>";
						div +=" <td class='td_productPrice' style='width:60px;'>"+productPrice+"<input type='hidden' name='inPrice' id='inPrice' value='" + productPrice + "'/>"+"</td>";
						div +=" <td class='td_productPriceTot' style='width:60px;'>"+productPrice+"</td>";
						div +=" <td><input type='text' name='' id='stockPosition' style='width:40px;'/></td>";
						div +=" <td><a href='###' class='overLook' style='width:60px;' title='"+orderNo+"'>"+orderNo+"";
						div +=" <input type='hidden' name='' id='importNo' value='"+orderNo+ "'/></td>";
						/*div +=" <td><a href='###' class='overLook' style='width:60px;' title='"+jo.registration+"'>"+jo.registration+"</td>";*/
                        div +=" <td>"+jo.registration+"</td>";
						div +=" <td><a href='###' class='overLook' style='width:60px;' title='"+jo.venderName+"'>"+jo.venderName;
						div +=" <input type='hidden' name='' class='isUrgent' value='"+jo.isUrgent+ "'/>";
						div +=" <input type='hidden' name='' class='surplusPurCount' value='"+jo.surplusPurCount+ "'/></td>";
						div +=" <td><input type='hidden'class='supplierId' name='suId' value='" + jo.supplierId + "'/></td>";
						div +="</tr>";
						
						$('#productTbody').append(div);
						$(".tableBox").scrollTop($("#productTbody").height());
						lay('.limitDate').each(function(){
							laydate.render({
						 		elem: this,
                                trigger: 'click'
							});
						});
						
						$("#number").val("");
						$("#barCode").val("");
						$("#number").focus();
                        computeTotalMoney();//计算金额
						return true;
					}else{
						layer.closeAll();
						$("#number").focus();
						layer.alert("产品没有关联供应商，请确认后重新扫描！ ",
								{icon:0},
								function(index) {
									layer.close(index);
									$("#number").val("");
									$("#barCode").val("");
									$("#number").focus();
				                });
						return false;
					}
				}else{
					layer.closeAll();
					$("#number").focus();
					layer.alert('产品编码：'+number+" 二级条码："+barCode+"产品信息不存在，请确认后重新扫描！ ",
							{icon:0},
							function(index) {
								layer.close(index);
								$("#number").val("");
								$("#barCode").val("");
								$("#number").focus();
			                });
					return false;
				}
			},
			error:function(){
				$("#number").focus();
				layer.alert("获取产品信息失败！",{icon:0},
					function(index) {
						layer.close(index);
						$("#number").val("");
						$("#barCode").val("");
						$("#number").focus();
                	}
				);
				return false;
			}
		});
	});
	 
	//产品名称回车事件
	$("#pdoductName").keydown(function(event){
		$("#operation").css('display','block'); 
    	if(event.keyCode != "13"){
    		return;
    	}
		var pdoductName = $("#pdoductName").val();			
		if(pdoductName == ""){
			layer.alert('产品名称为空',{icon:0})
			$("#pdoductName").val("");
			$("#pdoductName").focus();
			return false;
		}
		/*var barCode = $("#barCode").val();
		if(barCode == ""){
			$("#barCode").focus();
			layer.alert('二级条码为空',{icon:0});
			return false;
		}扫码时无需输入产品条码，自动使用产品编码当做条码2019年7月2日16:46:08*/
		var inType = $("#inType").val();
		var storeroomType = $("#storeroomType").val();
		if(inType == ""){
			layer.alert('请选择入库类型后扫码!',{icon: 0});
			$("#inType").focus();
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		if(inType != "<%=MinaGlobalConstants.STOCK_IN_TYPE_0%>"){
			layer.alert("非'正常入库'，不支持扫码!", {icon: 0});
			$("#number").focus();
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		if(storeroomType != "0"){
			layer.alert("非'一级库房'，不支持扫码!", {icon: 0});
			$("#number").focus();
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		var pdoductVersion = $.trim($("#pdoductVersion").val());
		var pdoductSpec = $.trim($("#pdoductSpec").val());
		getDataOther(pdoductName,pdoductVersion,pdoductSpec);
	});
	//产品型号回车事件
	$("#pdoductVersion").keydown(function(event){
		$("#operation").css('display','block'); 
    	if(event.keyCode != "13"){
    		return;
    	}
		var pdoductVersion = $("#pdoductVersion").val();			
		if(pdoductVersion == ""){
			layer.alert('型号为空',{icon:0})
			$("#pdoductVersion").val("");
			$("#pdoductVersion").focus();
			return false;
		}
		/*var barCode = $("#barCode").val();
		if(barCode == ""){
			$("#barCode").focus();
			layer.alert('二级条码为空',{icon:0});
			return false;
		}扫码时无需输入产品条码，自动使用产品编码当做条码2019年7月2日16:46:08*/
		var inType = $("#inType").val();
		var storeroomType = $("#storeroomType").val();
		if(inType == ""){
			layer.alert('请选择入库类型后扫码!',{icon: 0});
			$("#inType").focus();
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		if(inType != "<%=MinaGlobalConstants.STOCK_IN_TYPE_0%>"){
			layer.alert("非'正常入库'，不支持扫码!", {icon: 0});
			$("#number").focus();
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		if(storeroomType != "0"){
			layer.alert("非'一级库房'，不支持扫码!", {icon: 0});
			$("#number").focus();
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		var pdoductName = $.trim($("#pdoductName").val());
		var pdoductSpec = $.trim($("#pdoductSpec").val());
		getDataOther(pdoductName,pdoductVersion,pdoductSpec);
	});
	//产品规格回车事件
	$("#pdoductSpec").keydown(function(event){
		$("#operation").css('display','block'); 
    	if(event.keyCode != "13"){
    		return;
    	}
		var pdoductSpec = $("#pdoductSpec").val();			
		if(pdoductSpec == ""){
			layer.alert('规格为空',{icon:0})
			$("#pdoductSpec").val("");
			$("#pdoductSpec").focus();
			return false;
		}
		/*var barCode = $("#barCode").val();
		if(barCode == ""){
			$("#barCode").focus();
			layer.alert('二级条码为空',{icon:0});
			return false;
		}扫码时无需输入产品条码，自动使用产品编码当做条码2019年7月2日16:46:08*/
		var inType = $("#inType").val();
		var storeroomType = $("#storeroomType").val();
		if(inType == ""){
			layer.alert('请选择入库类型后扫码!',{icon: 0});
			$("#inType").focus();
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		if(inType != "<%=MinaGlobalConstants.STOCK_IN_TYPE_0%>"){
			layer.alert("非'正常入库'，不支持扫码!", {icon: 0});
			$("#number").focus();
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		if(storeroomType != "0"){
			layer.alert("非'一级库房'，不支持扫码!", {icon: 0});
			$("#number").focus();
			$("#number").val("");
			$("#barCode").val("");
			return;
		}
		var pdoductName = $.trim($("#pdoductName").val());
		var pdoductVersion = $.trim($("#pdoductVersion").val());
		getDataOther(pdoductName,pdoductVersion,pdoductSpec);
	});
	
	//根据产品名称,型号,规格,查询符合条件的产品
	function getDataOther(pdoductName,pdoductVersion,pdoductSpec){
		var orderNo = $("#orderNo").val();
		//var barCode = $("#barCode").val();扫码时无需输入产品条码，自动使用产品编码当做条码2019年7月2日16:46:08
		var batchNo = "";
		var limitDate = "";
		$.ajax({
	 		url:"${ctx}/pd/pdStockRecord/getStockRecordInScanByOther",
	 		type:"post",
			data:{
				name:pdoductName,
				spec:pdoductSpec,
				version:pdoductVersion
				//barCode :barCode扫码时无需输入产品条码，自动使用产品编码当做条码2019年7月2日16:46:08
				},  
			async:false, 
			success:function(data){
				var jo = JSON.parse(data);
				if(jo.code == "200"){
					//已打钩的产品
					var idCheckList = new Array();
					//扫码前清空没有选中的
					$("#productTbody tr").each(function(index, data) {
						var checked = $($(data).find("input")[0]).is(':checked');
						if(!checked){
							$(this).remove();
						}else{
							var productCode = $($(data).find("input")[2]).val();
							var productBarCode = $($(data).find("input")[3]).val();
							idCheckList.push(productCode+"_"+productBarCode);
						}
					})
					layer.closeAll();
					var jsonArray = jo.data;
					var div="";
					for(var i=0;i<jsonArray.length;i++){
                        var jsonData = jsonArray[i];
						/*var bo = true;*/
						/*//如果相同加1
						$("#productTbody tr").each(function(index, data){
							var productCode = $($(data).find("input")[1]).val();
							var productBarCode = $($(data).find("input")[2]).val();
							if(jsonData.productCode == productCode && barCode == productBarCode){
								var productNum = $($(data).find("input")[5]).val();
								if(productNum != ""){
									$($(data).find("input")[5]).val(1 + parseInt(productNum));
									bo = false;
								}
							}
						});*/
						//如果没加1 则拼接
						var productPrice = jsonData.productPrice;   if(!productPrice){productPrice="";}
						div +=" <tr><td><input type='checkbox' class='checkInp'/></td>";
						div +=" <td>"+jsonData.productName+"";
						div +=" <input type='hidden' name='productId' value='" + jsonData.productId + "'/></td>";
						div +=" <td>"+jsonData.productCode;
						div +=" <input type='hidden' name='productCode' value='" + jsonData.productCode + "'/></td>";
                        div +=" <td><input type='text' class='productBarCode' readonly='readonly' style='width:60px;' value=''/></td>";
						div +=" <td>"+jsonData.productSpec+"</td>";
						div +=" <td>"+jsonData.productVersion+"</td>";
						div +=" <td>"+jsonData.productUnit+"</td>";
						div +=" <td><input type='text' name='' class='batchNo' value='"+batchNo+"' style='width:80px;'/></td>";
						div +=" <td><input type='text' class='limitDate' name='' value='"+limitDate+"' readonly='readonly' style='width:85px;'/></td>";
						div +=" <td><input type='text' name='' class='productNum' value='1' style='width:40px;'/>";
						div +=" <td class='td_productPrice' style='width:60px;'>"+productPrice+"<input type='hidden' name='inPrice' id='inPrice' value='" + productPrice + "'/>"+"</td>";
						div +=" <td class='td_productPriceTot' style='width:60px;'>"+productPrice+"</td>";
						div +=" <td><input type='text' name='' id='stockPosition' style='width:40px;'/></td>";
						div +=" <td><a href='###' class='overLook' style='width:60px;' title='"+orderNo+"'>"+orderNo+"";
						div +=" <input type='hidden' name='' id='importNo' value='"+orderNo+ "'/></td>";
						/*div +=" <td><a href='###' class='overLook' style='width:60px;' title='"+jsonData.registration+"'>"+jsonData.registration+"</td>";*/
                        div +=" <td>"+jsonData.registration+"</td>";
						div +=" <td><a href='###' class='overLook' style='width:60px;' title='"+jsonData.venderName+"'>"+jsonData.venderName;
						div +=" <input type='hidden' name='' class='isUrgent' value='"+jsonData.isUrgent+ "'/>";
						div +=" <input type='hidden' name='' class='surplusPurCount' value='"+jsonData.surplusPurCount+ "'/></td>";
						div +=" <td><input type='hidden'class='supplierId' name='suId' value='" + jsonData.supplierId + "'/></td>";
						div +="</tr>";
					}
					$('#productTbody').append(div);
					$(".tableBox").scrollTop($("#productTbody").height());
					lay('.limitDate').each(function(){
						laydate.render({
					 		elem: this,
                            trigger: 'click',
							done: function(value, date){
								$(".batchNo").trigger("propertychange");
							}

						});
					});
					$("#pdoductName").val("");
					$("#pdoductVersion").val("");
					$("#pdoductSpec").val("");
					return true;
				}else{						
					layer.alert('产品名称/型号/规格/批号错误，请重新输入',{icon:0});
					$("#pdoductName").val("");
					$("#pdoductVersion").val("");
					$("#pdoductSpec").val("");
					return false;
				}
			},
			error:function(){
				layer.alert('产品名称/型号/规格/批号错误，请重新输入',{icon:0});
				$("#pdoductName").val("");
				$("#pdoductVersion").val("");
				$("#pdoductSpec").val("");
				return false;
			}
		});
	}


	//添加批号时
    $(document).on('input propertychange', '.batchNo', function(){
		var thisObj = $(this);
        var limitDate = thisObj.parent("td").parent("tr").find(".limitDate").val();
        if(limitDate && thisObj.val()){
			$(this).parent().parent().find(":checkbox").attr("checked",true);
			var productNumber = thisObj.parent("td").parent("tr").find("input[name=productCode]").val();
			var batchNo = thisObj.val();
            limitDate = limitDate.replace(/\-/g,"");
			thisObj.parent("td").parent("tr").find(".productBarCode").val(productNumber+batchNo+limitDate);
            computeTotalMoney();
        }

    })
	
    //填写数量时
    $(document).on('input propertychange', '.productNum', function(){
        var productNum_val = $(this).val();
    	var ep=alertWarnEmpty("数量不能为空！",$(this));
    	if(!ep){
			validateFlag=true;
			productNum_val.focus();
            productNum_val.val(1);
			return false;
    	}
    	var vf=alertWarn("请输入大于0的整数",$(this));
		if(!vf){
			validateFlag=true;
			productNum_val.focus();
            productNum_val.val(1);
			return false;
		}
		$(this).parent().parent().find(":checkbox").attr("checked",true);
        var productNum_int = parseInt(productNum_val);
        //单价
        var productPrice = $(this).parent("td").parent("tr").find(".td_productPrice").text();
        if(productPrice != ""){
        	var productPrice_f = parseFloat(productPrice);
        	var productPrice_tot = productPrice_f * productNum_int;
        	$(this).parent("td").parent("tr").find(".td_productPriceTot").text(productPrice_tot.toFixed(4));
        }
        
        //紧急产品校验
        var isUrgent = $(this).parent("td").parent("tr").find(".isUrgent").val();
        var surplusPurCount = $(this).parent("td").parent("tr").find(".surplusPurCount").val();
        if(isUrgent == "a1"){
        	if(surplusPurCount == ""){
    			layer.alert('紧急产品，产品剩余数量为空',{icon:0});
    			return false;
        	}else{
        		var surplusPurCount_int = parseInt(surplusPurCount);
        		if(productNum_int > surplusPurCount_int){
        			layer.alert('紧急产品，数量:'+productNum_int+'不能大于剩余采购量:'+surplusPurCount_int,{icon:0})
        			return false;
        		}
        	}
        }
        computeTotalMoney();
     });

    //动态计算总数量和总金额
    function computeTotalMoney(){
        //计算总数量
        var allcount = 0;
        //计算总金额
        var alltotal = 0.00;
        $("#productTbody tr").each(function(index, data) {
            var checked = $($(data).find("input")[0]).is(':checked');
            if(checked){
                //计算数量
                var productNum = $(this).children('td').find("input[class*='productNum']").val();
                if (!isNumber(productNum)) {
                    productNum = 0;
                }
                allcount += parseInt(productNum);

                //计算金额
                var productPriceTot = Number($(this).find(".td_productPriceTot").text());
                if (!productPriceTot) {
                    productPriceTot = 0;
                }
                alltotal += productPriceTot;

            }
        })
        $('.total_number').html(allcount);
        $('.total_price').html(alltotal.toFixed(4));

    }

	//保存
	$("#addFormOrder1").click(function(){

	});

	/***
    $(document).on("blur",".ticketTime",function(){
	    var ticketTime = $(this);
	    var ticketTime_val = ticketTime.val();
	    if(ticketTime_val == ""){
	    	layer.alert('发票日期不能为空！',{icon:0});
	      	//ticketTime.focus();
	      	return false;
	    }
    });***/
    
    $(document).on("blur",".ticketNum",function(){
    	var vf=alertWarnEmpty("发票号不能为空！",$(this));
		if(!vf){
			return false;
		}
    });
    
    $(document).on("blur",".ticketMoney",function(){
    	var vf=alertWarnEmpty("发票金额不能为空！",$(this));
		if(!vf){
			return false;
		}
        var mo = alertWarnMoney("发票金额不正确！",$(this));
		if(!mo){
			return false;
		}
    });
    
    //销售日期
    /***$(document).on("blur",".sellTime",function(){
  	    var ticketNum = $(this);   
  	    var ticketNum_val = ticketNum.val(); 
  	    if(ticketNum_val == ""){
  	      	layer.alert("销售日期不能为空！ ",{icon:0});
			//ticketNum.focus();              
  	      	return false;
  	    }
    });***/
    
     //批次号
    $(document).on("blur",".batchNo",function(){
  	    alertWarnEmpty("批次号不能为空",$(this));
    });
    
    //有效期
    /***$(document).on("blur",".limitDate",function(){
  	    var limitDate = $(this);   
  	    var limitDate_val = limitDate.val();
  	    if(limitDate_val == ""){
  	      	layer.alert("有效期不能为空！ ",{icon:0});
			//limitDate.focus();
  	      	return false;
  	    }
    });***/
    
	//查看三证
	$("#sanZheng").click(function(){
		var supplierId = $("#supplierId").val();
		if(supplierId == ""){
			layer.alert('请选择供应商！',{icon:0});
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
		
	//日期插件
	lay('.ticketTime').each(function(){
		laydate.render({
	 		elem: this,
            trigger: 'click'
		});
	});
	lay('.sellTime').each(function(){
		laydate.render({
	 		elem: this,
            trigger: 'click'
		});
	})
	
	lay('.limitDate').each(function(){
		laydate.render({
	 		elem: this,
            trigger: 'click'
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
	
	//显示扫码框
	function showScanCodeDiv(){
		$("#scanCodeDiv").show();
	}
	//隐藏扫码框
	function hideScanCodeDiv(){
		$("#scanCodeDiv").hide();
	}
</script>
</body>
</html>