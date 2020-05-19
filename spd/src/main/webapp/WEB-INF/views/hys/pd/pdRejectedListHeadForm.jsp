<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<style>
		.addRoomBox{line-height: 40px;padding:10px 5px;margin-bottom: 20px;}
		.addRoomBox label{width:75px;display: inline-block;text-align: left;}
		.addRoomBox>input[type='text'],.addRoomBox>select{display:inline-block;width: 160px;height:30px;border:1px solid #ccc;margin:0 10px 0 5px;}
	</style>
	<title>新增退货</title>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                	//校验是否已经扫码
					var len = $('#productTbody>tr').length;
					if(len < 1){
						layer.alert('请扫码或输入条码添加退货量',{icon:0});
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
						var inputRejectedCount = $(this).children('td').eq(9).find("input");
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
						layer.alert('产品退货量不能大于库存', {icon: 0});
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

  <form:form id="inputForm" modelAttribute="pdRejectedListHead" autocomplete="off" action="${ctx}/pd/pdRejectedListHead/newSave" method="post" class="form-horizontal">
	<div class="right-main-box">
			<div class="btnBox">
				<h4>新增退货</h4>
			</div>
			<div class="searchBox">
				<label for="">退货单号</label>
				<input type="text" name="number" style="width:165px;" value="${number }" required="required" /><br />
				<label for="">产品编码</label>
				<input type="text" name="productNumber" autocomplete="off" style="width:165px;" id="number" />
				<label for="">二级条码</label>
				<input type="text" name="barCode" autocomplete="off" style="width:165px;" id="barCode" />
				<div><label style="width:100%;">提示：按<span style="color:red;">Ctrl+Alt</span>键快速定位到扫码输入框</label></div>
				</br>
				<div>
					<label for="">产品名称</label>
					<input type="text" name="productName"  autocomplete="off" style="width:165px;" id="productName"/>
				</div>
				<div>
					<label for="">规格</label>
					<input type="text" name="spec" autocomplete="off" style="width:165px;" id="productSpec"/>
				</div>
				<div>
					<label for="">型号</label>
					<input type="text" name="version" autocomplete="off" style="width:165px;" id="productVersion"/>
				</div>
				<div>
					<label for="">批号</label>
					<input type="text" name="batchNo" autocomplete="off" style="width:165px;" id="productBatchNo"/>
				</div>

				<input type="hidden" name="supplierId" />
				<input type="hidden" name="supplierName" />
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
							<th>退货数量</th>
							<th>库存数量</th>
							<th>供应商</th>
						</tr>
					</thead>
					<tbody id="productTbody">
						
					</tbody>
				</table>
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
      			<input type="button"  value="保存"  id="submitForm" class="hcy-btn hcy-save" />
      			<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
      		</div>
		</div>
	</form:form>
  <input type="hidden" id="rowsLen" value="0"/>
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd/js/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/js/barcode.js?time=<%= new Date().getTime() %>"></script>
	<script type="text/javascript">
	$(function(){
		$("#number").focus();
		//复选框点击事件
		$("#productTbody").on('click',".checkInp",function(){
			if($(this).is(':checked')){
				var count = $(this).parent().parent().children('td').eq(9).find("input").val();//退货量
				var stock = $(this).parent().parent().children('td').eq(10).find("input").val();//库存
				if(Number.parseInt(count) > Number.parseInt(stock)){
					layer.alert('退货量已大于库存', {icon:0});
					$(this).parent().parent().css('background-color','red');
				}else{
					$(this).parent().parent().css('background-color','');
				}
			}else{
				$(this).parent().parent().css('background-color','');
			}
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

				//if(number_val != "" && number_val.length >= 16){
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
                                        $($(data).children('td').eq(9).find("input")).val(1 + parseInt(productNum));
                                        var productInputRejectedCount = $($(data).children('td').eq(9).find("input"));
                                        computeDosage(productInputRejectedCount)
                                        isHas = true;
                                        return false;
                                    }
                                }
                            })
                            if(!isHas){
                                var html = getProdHtmlOne(data);
                                $("#productTbody").append(html);
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

		//根据型号查询库存
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
                                        $($(data).children('td').eq(9).find("input")).val(1 + parseInt(productNum));
                                        var productInputRejectedCount = $($(data).children('td').eq(9).find("input"));
                                        computeDosage(productInputRejectedCount)
                                        isHas = true;
                                        return false;
                                    }
                                }
                            })
                            if(!isHas){
                                var html = getProdHtmlOne(data);
                                $("#productTbody").append(html);
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
			for(var i=0; i<data.length; i++){
				var productStock = data[i];
				//校验供应商名称是否一样 add by jiangxz 20190925
				if(isSupplierRepeat(productStock.supplierId)){
					layer.alert('不同供应商不能一起退货！', {icon:0});
					return;
				}
				$("[name=supplierId]").val(productStock.supplierId);
				$("[name=supplierName]").val(productStock.supplierName);


				html +=		'<tr id="ps'+productStock.id+'">';
				html +=  	'<input type="hidden" name="child['+len+'].productStockId" value="'+productStock.id+'"/>'
				html +=     '<td><input type="checkbox" checked="checked" class="checkInp"/></td>';
				html +=  	'<input type="hidden" name="child['+len+'].prodId" value="'+productStock.productId+'"/>'
				html +=  	'<input type="hidden" name="child['+len+'].prodNo" value="'+productStock.pdProduct.number+'"/>'
				html +=  	'<input type="hidden" name="child['+len+'].barcode" value="'+productStock.productBarCode+'"/>'
				html +=  	'<input type="hidden" name="child['+len+'].batchNo" value="'+productStock.batchNo+'"/>'
				html +=		'<td>'+productStock.pdProduct.name+'</td>';
				html +=		'<td>'+productStock.pdProduct.number+'</td>';
				html +=		'<td>'+productStock.productBarCode+'</td>';
				html +=		'<td>'+productStock.pdProduct.spec+'</td>';
				html +=		'<td>'+productStock.pdProduct.version+'</td>';
				html +=		'<td>'+productStock.batchNo+'</td>';
				html +=		'<td>'+productStock.pdProduct.unit+'</td>';
				html +=		'<td>'+dateFormat(productStock.validDate)+'</td>';
				html +=		'<td><input type="text" name="child['+len+'].rejectedCount" class="inputRejectedCount" value="1" style="width:40px;"/>';
                html +=		'<td><input type="hidden" name="stockNum" class="stockNum" value="'+productStock.stockNum+'" style="width:40px;"/>'+productStock.stockNum+'</td>';
				html +=		'<td><input type="hidden" name="sId" class="sId" value="'+productStock.supplierId+'"/>'+productStock.supplierName+'</td>';
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
				//校验供应商名称是否一样 add by jiangxz 20190925
				if(isSupplierRepeat(productStock.supplierId)){
					layer.alert('不同供应商不能一起退货！', {icon:0});
					return;
				}
				$("[name=supplierId]").val(productStock.supplierId);
				$("[name=supplierName]").val(productStock.supplierName);

				html +=		'<tr id="ps'+productStock.id+'">';
				html +=  	'<input type="hidden" name="child['+len+'].productStockId" value="'+productStock.id+'"/>'
				html +=     '<td><input type="checkbox" class="checkInp"/></td>';
				html +=  	'<input type="hidden" name="child['+len+'].prodId" value="'+productStock.productId+'"/>'
				html +=  	'<input type="hidden" name="child['+len+'].prodNo" value="'+productStock.pdProduct.number+'"/>'
				html +=  	'<input type="hidden" name="child['+len+'].barcode" value="'+productStock.productBarCode+'"/>'
				html +=  	'<input type="hidden" name="child['+len+'].batchNo" value="'+productStock.batchNo+'"/>'
				html +=		'<td>'+productStock.pdProduct.name+'</td>';
				html +=		'<td>'+productStock.pdProduct.number+'</td>';
				html +=		'<td>'+productStock.productBarCode+'</td>';
				html +=		'<td>'+productStock.pdProduct.spec+'</td>';
				html +=		'<td>'+productStock.pdProduct.version+'</td>';
				html +=		'<td>'+productStock.batchNo+'</td>';
				html +=		'<td>'+productStock.pdProduct.unit+'</td>';
				html +=		'<td>'+dateFormat(productStock.validDate)+'</td>';
				html +=		'<td><input type="text" name="child['+len+'].rejectedCount" class="inputRejectedCount" value="1" style="width:40px;"/>';
				html +=		'<td><input type="hidden" name="stockNum" class="stockNum" value="'+productStock.stockNum+'" style="width:40px;"/>'+productStock.stockNum+'</td>';
				html +=		'<td><input type="hidden" name="sId" class="sId" value="'+productStock.supplierId+'"/>'+productStock.supplierName+'</td>';
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
			computeDosage($(this));
			return false;
		});

		//保存
		$("#submitForm").click(function(){
			$("#inputForm").submit();
		});

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

	function isSupplierRepeat(supplierId){
		var bool = false;
		$("#productTbody tr").each(function(index, data) {
			var sId = $($(data).children('td').eq(11).find("input")).val();
			if(supplierId != sId){
				bool = true;
			}
		})
		return bool;
	}
	</script>
</body>
</html>