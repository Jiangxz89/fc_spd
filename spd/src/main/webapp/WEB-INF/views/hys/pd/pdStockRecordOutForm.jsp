<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page import="com.thinkgem.hys.pd.constants.MinaGlobalConstants"%>
<%@ page import="java.util.Date" %>
<html>
<head>
	<title>出入库记录管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<style type="text/css">
		.otherText{margin:20px 0;line-height: 40px;}
		.otherText>h3{font-size:14px;color:#666;font-weight: 600;}
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;}
		.otherText>h3{font-weight:400;display:inline-block;padding:3px 10px 0 5px;}
		.allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#productTbody>tr>td>select{width:80px;}
		.checkInp{width:14px;height:14px;}
	</style>
	<script type="text/javascript" src="${ctxStatic}/common/jeesite.js"></script>
	<script src="${ctxStatic}/spd/js/barcode.js?time=<%= new Date().getTime() %>"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#searchForm").validate({
				submitHandler: function(form){
					var inId = $("#inId").val();
					if(!inId){
						layer.alert('请选择入库库房',{icon:0});
						return false;
					}

					//校验是否已经扫码
					var len = $('#productTbody>tr').length;
					if(len < 1){
						layer.alert('请扫码或输入条码添加出库数量',{icon:0});
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
						var inputRejectedCount = $(this).children('td').eq(11).find("input");
						var rst_1 = alertWarn('请输入大于0的整数', inputRejectedCount);
						if (!rst_1) {
							flag = false;
							return false;
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
						layer.alert('产品出库数量不能大于库存', {icon: 0});
						return false;
					}
					loading('正在提交，请稍等...');
					$("#productTbody tr").each(function(index, data) {
						var checked = $($(data).find("input")[1]).is(':checked');
						if(!checked){
							$(data).find("input").not("input[type=checkbox]").attr("disabled",true);
						}
					})
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
		<h4>新增出库</h4>
	</div>
	<form:form id="searchForm" modelAttribute="pdStockRecord" action="${ctx}/pd/pdStockRecord/saveOut" method="post" class="breadcrumb form-search">
		<div class="newSearchBox">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input id="pdjson" name="pdjson" type="hidden">
			<input id="inStoreId" name="inStoreId" type="hidden">
			<input id="rowsLen" value="0" type="hidden"/>
			<label>出库单号</label>
			<form:input path="recordNo" maxlength="64" class="input-xlarge" value="${pdStockRecord.recordNo}" readonly="readonly"/>
			<label>出库时间</label>
			<input name="recordDate" type="text" readonly="readonly" maxlength="10" id="recordDate" class="input-xlarge"
				   value="<fmt:formatDate value="${pdStockRecord.recordDate}" pattern="yyyy-MM-dd"/>"/>
			<label>出库类型 </label>
			<c:if test="${user.storeroomType==0}">
				<form:select path="outType" class="input-medium">
					<%-- <form:option value="" label="请选择"/> --%>
					<form:options items="${fns:getDictList('stock_out_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</c:if>
			<c:if test="${user.storeroomType==1}">
				<select id="outType" name="outType" class="input-medium">
					<!-- <option value="">请选择</option> -->
					<option value="1">退货出库</option>
					<option value="2">调拨出库</option>
				</select>
			</c:if>
			<label>操作人</label>
			<form:input path="recordPeople" htmlEscape="false" maxlength="64" class="input-medium" value="${pdStockRecord.recordPeople }" readonly="readonly"/>
			<label>出库库房</label>
			<form:input path="outName" htmlEscape="false" maxlength="64" class="input-medium" value="${fns:getUser().storeroomName}" readonly="readonly"/>
			<form:hidden path="outId" id="outId"/>
			</br>
			<label for=""><span class="mustIcon">*</span>入库库房</label>
			<form:select path="inId" class="input-medium">
				<form:option value="" label="请选择"/>
				<c:forEach var="storeroom" items="${storeroomList}">
					<c:if test="${storeroom.id!=fns:getUser().storeroomId}">
						<option value="${storeroom.id }">${storeroom.storeroomName }</option>
					</c:if>
				</c:forEach>
			</form:select>
			<a href="###" class="hcy-btn hcy-btn-primary" style="line-height: 1.5;display:none;" id="addAllocationOrder">从调拨单导入</a>
			<form:hidden path="allocationNo" id="allocationNo"/>
			<a href="###" class="hcy-btn hcy-btn-primary" style="line-height: 1.5;" id="addApplyOrder">从申领单导入</a>
			<form:hidden path="applyNo" id="applyNo"/>
				<%-- <a href="###" class="hcy-btn hcy-btn-primary" style="line-height: 1.5;display:none;" id="addDosagertOrder">从退货单导入</a>
				<form:hidden path="dosagertNo" id="dosagertNo"/> --%>
			<br>
			<input type="hidden" id="len" value=0>
		</div>
		<div class="tableBox isImport" style="">
			<table id="" class="hcy-public-table" >
				<thead>
				<tr>
					<th>单号</th>
					<th>定数包名称</th>
					<th>定数包编号</th>
					<th>产品名称</th>
					<th>产品编号</th>
					<th>规格</th>
					<th>型号</th>
					<th>申请数量</th>
					<th>单位</th>
				</tr>
				</thead>
				<tbody id="pdReference">

				</tbody>
			</table>
		</div>
		<div class="searchBox">
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
				<input type="text" autocomplete="off" name="name" id="productName"/>
			</div>
			<div>
				<label for="">规格</label>
				<input type="text" autocomplete="off" name="spec" id="productSpec"/>
			</div>
			<div>
				<label for="">型号</label>
				<input type="text" autocomplete="off" name="version" id="productVersion"/>
			</div>
			<div>
				<label for="">批号</label>
				<input type="text" autocomplete="off" name="batchNo" id="productBatchNo"/>
			</div>
		</div>
		<div class="tableBox">
			<table id="contentTableTbody2" class="hcy-public-table" >
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
					<th>入库单价</th>
					<th>出库单价</th>
					<th>出库数量</th>
					<th>出库金额</th>
					<th>库存数量</th>
				</tr>
				</thead>
				<tbody id="productTbody">

				</tbody>
			</table>
			<div class="totalText">
				<span class="allNum">总数量:<span class="total_number">0</span></span>
				<span class="allNum">出库总金额:<span class="total_price">0.00</span></span>
			</div>
		</div>
		<div class="otherText">
			<h3>备注</h3>
			<textarea name="remarks" class="remarkArea"></textarea>
		</div>
		<div class="form-actions" style="background:#fff;border:none;text-align:center;padding:0;margin-top:30px;">
			<shiro:hasPermission name="pd:pdStockRecord:edit"><input type="button" id="btnSubmit" class="hcy-btn hcy-save" value="保 存"/>&nbsp;</shiro:hasPermission>
			<a href="${ctx}/pd/pdStockRecord/outList" class="hcy-btn hcy-back" >返回</a>
		</div>
	</form:form>
	<sys:message content="${message}"/>
</div>
<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
<script src="${ctxStatic}/spd/js/layer.js"></script>
<script type="text/javascript">
	//var allocationlArr=[];
	//var appOrderArr=[];
	$(document).ready(function() {
		var selectStoreroomType = '${user.storeroomType}';
		//如果是二级库房则需要初始化退货下拉
		if(selectStoreroomType=="1"){
			$("#outType").trigger("change");
		}

		/*			$("body").delegate(".checkInp","click",function(){
                        //debugger;
                        if($(this).prop("checked")){
                            $(this).parent("td").parent("tr").find("input").not("input[type=checkbox]").attr("disabled",false);
                            $(this).parent("td").parent("tr").find("select").attr("disabled",false);
                        }else{
                            $(this).parent("td").parent("tr").find("input").not("input[type=checkbox]").attr("disabled",true);
                            $(this).parent("td").parent("tr").find("select").attr("disabled",true);
                        }
                    });*/
		$("#btnSubmit").click(function(){
			$("#searchForm").submit();
		});
		//调拨单导入
		$("#addAllocationOrder").click(function(){
			$('#productTbody').html("");
			$('#pdReference').html("");
			var recordStates="<%=MinaGlobalConstants.APPLY_ORDER_STATUS_PASSED%>,<%=MinaGlobalConstants.APPLY_ORDER_STATUS_OUT_REJECT%>";
			layer.open({
				type:2,
				title:"选择调拨单",
				content:'${ctx}/pd/pdAllocationRecord/pdAddAllocationOutBox?recordStates='+recordStates,//导入已审核
				area:["800px","450px"],
				shade: [0.8, '#393D49'],
				btn:["确定","取消"],
				yes:function(index,layero){
					$('#pdReference').html("");
					var childWindow = layero.find('iframe')[0].contentWindow;
					var allocationCode = childWindow.compositeHtml();
					var inSId=childWindow.compositeInStroomId();
					if(allocationCode == null){
						layer.alert('请选择调拨单',{icon: 0});
					}else{

						/*var select = document.getElementById("inId");
                        for(var i=0; i<select.options.length; i++){
                            if(select.options[i].value == inSId){
                                select.options[i].selected = true;
                                break;
                            }
                        }*/
						$("#inId").select2("val",inSId);
						$("#inId").attr("disabled",true);
						$("#inStoreId").val(inSId);
						$("#allocationNo").val(allocationCode);
						$.getJSON('${ctx}/pd/pdAllocationRecord/getData',{allocationCode:allocationCode},function(data){
							var html = '', datap = data.productList, data = data.packageList;
							var htmlRef='';
							//allocationlArr=[];
							//定数包
							$.each(data,function(i,v){
								var rowslen = data[i].pdProductList.length;
								for(var j=0;j<rowslen;j++){
									var obj = data[i].pdProductList[j];
									//allocationlArr.push(obj.number);
									htmlRef+='<tr><td>'+allocationCode+'</td>'
									if(j==0){
										htmlRef+='<td rowspan="'+rowslen+'">'+data[i].name+'</td>'
												+'<td rowspan="'+rowslen+'">'+data[i].number+'</td>'
									}
									htmlRef+='<td>'+obj.name+'</td>'
											+'<td>'+obj.number+'</td>'
											+'<td>'+obj.spec+'</td>'
											+'<td>'+obj.version+'</td>'
									if(j==0){
										htmlRef+='<td rowspan="'+rowslen+'">'+(data[i].sum*data[i].applyNum)+'</td>'
									}
									htmlRef+='<td>'+obj.unit+'</td>'
											+'</tr>';
								}
							});
							//产品
							$.each(datap, function(i,v){
								//allocationlArr.push(datap[i].number);
								htmlRef+='<tr><td>'+allocationCode+'</td>'
										+'<td>--</td>'
										+'<td>--</td>'
										+'<td>'+datap[i].name+'</td>'
										+'<td>'+datap[i].number+'</td>'
										+'<td>'+datap[i].spec+'</td>'
										+'<td>'+datap[i].version+'</td>'
										+'<td>'+datap[i].applyNum+'</td>'
										+'<td>'+datap[i].unit+'</td>'
										+'</tr>';
							});
							//追加html
							if(htmlRef)
								$('#pdReference').append(htmlRef);
							$("input[name='number']").focus();
						});
						layer.closeAll();
					}
				},
				btn2:function(){
					layer.closeAll();
				}
			})
		});
		//申领单导入
		$("#addApplyOrder").click(function(){
			$('#productTbody').html("");
			$('#pdReference').html("");
			var applyStatuses="<%=MinaGlobalConstants.APPLY_ORDER_STATUS_PASSED%>,<%=MinaGlobalConstants.APPLY_ORDER_STATUS_OUT_REJECT%>";
			layer.open({
				type:2,
				title:"选择申领单",
				content:'${ctx}/pd/pdApplyOrder/pdAddApplyOrderBox?applyStatuses='+applyStatuses,//导入已审核,出库驳回
				area:["800px","450px"],
				shade: [0.8, '#393D49'],
				btn:["确定","取消"],
				yes:function(index,layero){
					$('#pdReference').html("");
					var childWindow = layero.find('iframe')[0].contentWindow;
					var applyNo = childWindow.compositeHtml();
					var inStroomId=childWindow.compositeInId();
					if(applyNo == null){
						layer.alert('请选择申领单',{icon: 0});
					}else{
						//
						/*var select = document.getElementById("inId");
                        for(var i=0; i<select.options.length; i++){
                            if(select.options[i].value == inStroomId){
                                select.options[i].selected = true;
                                break;
                            }
                        }*/
						$("#inId").select2("val",inStroomId);
						$("#inId").attr("disabled",true);
						$("#inStoreId").val(inStroomId);
						$("#applyNo").val(applyNo);
						$.getJSON('${ctx}/pd/pdApplyDetail/getData',{applyNo:applyNo},function(data){
							var html = '', datap = data.productList, data = data.packageList;
							var htmlRef='';
							//appOrderArr=[];
							//定数包
							$.each(data,function(i,v){
								var rowslen = data[i].list.length;
								for(var j=0;j<rowslen;j++){
									var obj = data[i].list[j];
									//appOrderArr.push(obj.pdProduct.number);
									htmlRef+='<tr><td>'+applyNo+'</td>'
									if(j==0){
										htmlRef+='<td rowspan="'+rowslen+'">'+data[i].packageName+'</td>'
												+'<td rowspan="'+rowslen+'">'+data[i].packageNumber+'</td>'
									}
									htmlRef+='<td>'+obj.pdProduct.name+'</td>'
											+'<td>'+obj.pdProduct.number+'</td>'
											+'<td>'+obj.pdProduct.spec+'</td>'
											+'<td>'+obj.pdProduct.version+'</td>'
											+'<td>'+(data[i].packageCount)*(obj.productCount)+'</td>'
											+'<td>'+obj.pdProduct.unit+'</td>'
											+'</tr>';
								}
							});
							//产品
							$.each(datap, function(i,v){
								//appOrderArr.push(datap[i].prodNo);
								htmlRef +='<tr><td>'+applyNo+'</td>'
										+'<td></td>'
										+'<td></td>'
										+'<td>'+datap[i].pdProduct.name+'</td>'
										+'<td>'+datap[i].prodNo+'</td>'
										+'<td>'+datap[i].pdProduct.spec+'</td>'
										+'<td>'+datap[i].pdProduct.version+'</td>'
										+'<td>'+datap[i].applyCount+'</td>'
										+'<td>'+datap[i].pdProduct.unit+'</td>'
										+'</tr>';
							});
							//追加html

							if(htmlRef)
								$('#pdReference').append(htmlRef);
							$("#number").focus();
						});
						layer.closeAll();
					}
				},
				btn2:function(){
					layer.closeAll();
				}
			})
		});
	});

</script>
<script type="text/javascript">
	$(document).ready(function() {

		//复选框点击事件
		$("#productTbody").on('click',".checkInp",function(){
			if($(this).is(':checked')){
				var count = $(this).parent().parent().children('td').eq(11).find("input").val();//出库数量
				var stock = $(this).parent().parent().children('td').eq(13).find("input").val();//库存
				if(Number.parseInt(count) > Number.parseInt(stock)){
					layer.alert('出库数量已大于库存', {icon:0});
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

		//校验单据是否齐全
		function validateBill(){
			var outType=$("#outType").val();
			if(outType==""){
				layer.alert('请选择出库类型',{icon:0})
				sweepCodeinit();
				return false;
			}
			var applyNo=$("#applyNo").val();
			var allocationNo=$("#allocationNo").val();
			if(outType==0&&applyNo==''){
				layer.alert('请导入申领单',{icon:0})
				sweepCodeinit();
				return false;
			}else if(outType==2&&allocationNo==''){
				layer.alert('请导入调拨单',{icon:0})
				sweepCodeinit();
				return false;
			}else{
				if($("#inId").val()==''){
					layer.alert('请选择入库库房',{icon:0})
					sweepCodeinit();
					return false;
				}
			}
			return true;
		}
		//入库库房改变事件
		$("#inId").change(function(){
			$("#number").focus();
		})

		//按Ctrl + Alt键聚焦
		$(document).keydown(function(event){
			if(event.ctrlKey && event.keyCode == 18){
				$("#number").focus();
			}
		});
		//产品码
		$("#number").keydown(function(event){
			if(event.keyCode == "13"){
				var bl = validateBill();
				if(!bl){
					return bl;
				}
				$("#barCode").focus();
			}
		})

		$("#barCode").keydown(function(event){
			if(event.keyCode != "13"){
				return;
			}
			var number = $.trim($("#number").val());
			var barCode = $.trim($("#barCode").val());
			var initNumber = number;
			var initBarCode = barCode;
			if(number == ""){
				layer.alert('产品编码为空',{icon:0})
				$("#number").focus();
				return false;
			}
			if(barCode == ""){
				$("#barCode").focus();
				layer.alert('二级条码为空',{icon:0});
				return false;
			}
			var bl = validateBill();
			if(!bl){
				return bl;
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
									var productNum = $($(data).children('td').eq(11).find("input")).val();
									if(productNum != ""){
										$($(data).children('td').eq(11).find("input")).val(1 + parseInt(productNum));
										// add by jiangxz 20190910 计算出价
										var productSellingPrice =  Number($(data).find(".td_sellingPrice").text());
										var productSellingPriceTot = ((1 + parseInt(productNum)) * productSellingPrice).toFixed(4);
										$(data).find(".td_sellingPriceTot").text(productSellingPriceTot);

										var productInputRejectedCount = $($(data).children('td').eq(11).find("input"));
										computeDosage(productInputRejectedCount)
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
		});

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
			var bl = validateBill();
			if(!bl){
				return bl;
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
			var bl = validateBill();
			if(!bl){
				return bl;
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
				layer.alert('产品型号为空',{icon:0});
				$("#productVersion").focus();
				return false;
			}
			var bl = validateBill();
			if(!bl){
				return bl;
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
			var bl = validateBill();
			if(!bl){
				return bl;
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
									var productNum = $($(data).children('td').eq(11).find("input")).val();
									if(productNum != ""){
										$($(data).children('td').eq(11).find("input")).val(1 + parseInt(productNum));
										// add by jiangxz 20190910 计算出价
										var productSellingPrice =  Number($(data).find(".td_sellingPrice").text());
										var productSellingPriceTot = ((1 + parseInt(productNum)) * productSellingPrice).toFixed(4);
										$(data).find(".td_sellingPriceTot").text(productSellingPriceTot);

										var productInputRejectedCount = $($(data).children('td').eq(11).find("input"));
										computeDosage(productInputRejectedCount)
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
		// <th>入库单价</th>
		// <th>出库单价</th>
		// <th>出库数量</th>
		// <th>出库金额</th>
		//拼接html第一种包含个数的；
		function getProdHtmlOne(data){
			var len = $('#rowsLen').val();
			var html="";
			for(var i=0; i<data.length; i++){
				var productStock = data[i];
				html +=		'<tr id="ps'+productStock.id+'">';
				html +=  	'<input type="hidden" name="productList['+len+'].productStockId" value="'+productStock.id+'"/>'
				html +=     '<td><input type="checkbox" checked="checked" class="checkInp"/></td>';
				html +=  	'<input type="hidden" name="productList['+len+'].productId" value="'+productStock.productId+'"/>'
				html +=  	'<input type="hidden" name="productList['+len+'].number" value="'+productStock.pdProduct.number+'"/>'
				html +=  	'<input type="hidden" name="productList['+len+'].productBarCode" value="'+productStock.productBarCode+'"/>'
				html +=  	'<input type="hidden" name="productList['+len+'].batchNo" value="'+productStock.batchNo+'"/>'
				html +=		'<td>'+productStock.pdProduct.name+'</td>';
				html +=		'<td>'+productStock.pdProduct.number+'</td>';
				html +=		'<td>'+productStock.productBarCode+'</td>';
				html +=		'<td>'+productStock.pdProduct.spec+'</td>';
				html +=		'<td>'+productStock.pdProduct.version+'</td>';
				html +=		'<td>'+productStock.batchNo+'</td>';
				html +=		'<td>'+productStock.pdProduct.unit+'</td>';
				html +=		'<td>'+dateFormat(productStock.validDate)+'</td>';
				<!-- 入库单价 add by jiangxz 20190909 -->
				html +=		'<td>'+productStock.inPrice+'<input type="hidden" name="productList['+len+'].inPrice" value="' + productStock.inPrice + '"/>'+'</td>';
				<!-- 出库单价 add by jiangxz 20190909 -->
				html +=		'<td class="td_sellingPrice">'+productStock.sellingPrice+'<input type="hidden" name="productList['+len+'].outPrice" value="' + productStock.sellingPrice + '"/>'+'</td>';
				html +=		'<td><input type="text" name="productList['+len+'].productNum" autocomplete="off" class="inputRejectedCount" value="1" style="width:40px;"/>';
				html +=		'<td class="td_sellingPriceTot">'+productStock.sellingPrice+'</td>';  <!-- 出库金额 add by jiangxz 20190909 -->
				html +=		'<td><input type="hidden" name="stockNum" class="stockNum" value="'+productStock.stockNum+'" style="width:40px;"/>'+productStock.stockNum+'</td>';
				html +=		'<input type="hidden" name="productList['+len+'].limitDate" value="'+dateFormat(productStock.validDate)+'"/>';
				html +=		'</tr>';
				len = Number(len)+1
			}
			$('#rowsLen').val(len);
			return html;
		}

		function getProdHtmlTwo(data,idCheckList){
			var len = $('#rowsLen').val();
			var html="";
			for(var i=0; i<data.length; i++){
				var productStock = data[i];
				if(idCheckList.indexOf(productStock.id) > -1){
					continue;
				}
				html +=		'<tr id="ps'+productStock.id+'">';
				html +=  	'<input type="hidden" name="productList['+len+'].productStockId" value="'+productStock.id+'"/>'
				html +=     '<td><input type="checkbox" class="checkInp"/></td>';
				html +=  	'<input type="hidden" name="productList['+len+'].productId" value="'+productStock.productId+'"/>'
				html +=  	'<input type="hidden" name="productList['+len+'].number" value="'+productStock.pdProduct.number+'"/>'
				html +=  	'<input type="hidden" name="productList['+len+'].productBarCode" value="'+productStock.productBarCode+'"/>'
				html +=  	'<input type="hidden" name="productList['+len+'].batchNo" value="'+productStock.batchNo+'"/>'
				html +=		'<td>'+productStock.pdProduct.name+'</td>';
				html +=		'<td>'+productStock.pdProduct.number+'</td>';
				html +=		'<td>'+productStock.productBarCode+'</td>';
				html +=		'<td>'+productStock.pdProduct.spec+'</td>';
				html +=		'<td>'+productStock.pdProduct.version+'</td>';
				html +=		'<td>'+productStock.batchNo+'</td>';
				html +=		'<td>'+productStock.pdProduct.unit+'</td>';
				html +=		'<td>'+dateFormat(productStock.validDate)+'</td>';
				<!-- 入库单价 add by jiangxz 20190909 -->
				html +=		'<td>'+productStock.inPrice+'<input type="hidden" name="productList['+len+'].inPrice" value="' + productStock.inPrice + '"/>'+'</td>';
								<!-- 出库单价 add by jiangxz 20190909 -->
				html +=		'<td class="td_sellingPrice">'+productStock.sellingPrice+'<input type="hidden" name="productList['+len+'].outPrice" value="' + productStock.sellingPrice + '"/>'+'</td>';
				html +=		'<td><input type="text" name="productList['+len+'].productNum" autocomplete="off" class="inputRejectedCount" value="1" style="width:40px;"/>';
				html +=		'<td class="td_sellingPriceTot">'+productStock.sellingPrice+'</td>';  <!-- 出库金额 add by jiangxz 20190909 TODO-->
				html +=		'<td><input type="hidden" name="stockNum" class="stockNum" value="'+productStock.stockNum+'" style="width:40px;"/>'+productStock.stockNum+'</td>';
				html +=		'<input type="hidden" name="productList['+len+'].limitDate" value="'+dateFormat(productStock.validDate)+'"/>';
				html +=		'</tr>';
				len = Number(len)+1
			}
			idCheckList  = new Array();
			$('#rowsLen').val(len);
			return html;
		}

		//计算单个金额和总数量
		$(document).on('input propertychange', '.inputRejectedCount', function(){
			//输入时默认勾选
			$(this).parent().parent().find(":checkbox").attr("checked",true);
			alertWarn('请输入大于0的整数', $(this));

			var productNum = $(this).val();
			// add by jiangxz 20190910 计算出价
			var productSellingPrice =  Number($(this).parent().parent().find(".td_sellingPrice").text());
			var productSellingPriceTot = ((parseInt(productNum)) * productSellingPrice).toFixed(4);
			$(this).parent().parent().find(".td_sellingPriceTot").text(productSellingPriceTot);

			computeDosage($(this));
			computeTotalMoney();
			return false;
		});

		//计算用量数量不得大于库存数量
		function computeDosage(inputObj){
			var count = $.trim(inputObj.val());//用量
			var stock = $.trim(inputObj.parent().next().next().find('input[name$=stockNum]').val());//库存
			if(Number.parseInt(count) > Number.parseInt(stock)){
				layer.alert('出库数量已大于库存', {icon:0});
				inputObj.parent().parent().css('background-color','red');
			}else{
				inputObj.parent().parent().css('background-color','');
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
					var productNum = $($(data).children('td').eq(11).find("input")).val();
					if (!isNumber(productNum)) {
						productNum = 0;
					}
					allcount += parseInt(productNum);
					var productMoney = $($(data).children('td').eq(12)).text();
					alltotal += parseFloat(productMoney || 0);
				}
			})
			$('.total_number').html(allcount);
			$('.total_price').html(alltotal);
		}

		$(document).on('blur','.checkNum',function(){
			checkNum(this);
		});
		$(document).on('click','.delTr',function(){
			delTr(this);
		});
		function delTr (obj){
			$(obj).parent().remove();
		}
		function checkNum(obj){
			var reg = /^[0-9]+$/;
			var num=$(obj).val();
			if(num){
				num=parseInt(num);
				if(!reg.test(num)){
					$(obj).val("");
					layer.open({
						title: '警告'
						,content: '请输入正整数'
					});
				}else{
					var stockNum = parseInt($(obj).parent().prev().html());
					if(num>stockNum){
						$(obj).val("");
						layer.open({
							title: '警告'
							,content: '库存不足'
						});
					}
				}
			}else{
				layer.open({
					title: '警告'
					,content: '请输入正整数'
				});
			}
		}
	});
	//扩展原型方法
	Date.prototype.toLocaleString = function() {
		var mon=this.getMonth()+1;
		var day=this.getDate();
		return this.getFullYear() + "-" + getzf(mon) + "-" + getzf(day)
	};
	//补0
	function getzf(num){
		if(parseInt(num) < 10){
			num = '0'+num;
		}
		return num;
	}

	$("#outType").change(function(){
		$("#allocationNo").val("");
		$("#applyNo").val("");
		$("#dosagertNo").val("");
		$("#len").val(0);
		$("#pdReference").html("");
		$("#productTbody").html("");
		$("#inId").removeAttr("disabled");
		$("#inId").select2("val","");
		var v=$(this).val();
		if(v){
			$(".isImport").show();
			$("#inId").select2("val","");
			if(v==0){
				$("#addApplyOrder").show();
				$("#addAllocationOrder").hide();
				$("#number").val("");
				$("#barCode").val("");

			}else if(v==2){
				$("#addApplyOrder").hide();
				$("#addAllocationOrder").show();
				$("#number").val("");
				$("#barCode").val("");
			} else if(v==1){
				$(".isImport").hide();
				$("#addApplyOrder").hide();
				$("#addAllocationOrder").hide();
				$("#number").val("");
				$("#barCode").val("");
				$("input[name='number']").focus();
			}
		}else{
			$("#addApplyOrder").hide();
			$("#addAllocationOrder").hide();
			$("#number").val("");
			$("#barCode").val("");
			$(".isImport").hide();
		}
	})
</script>
</body>
</html>