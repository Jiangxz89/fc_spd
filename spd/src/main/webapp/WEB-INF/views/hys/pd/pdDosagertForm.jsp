<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<style>
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#allMoney,#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
	</style>
	<title>用量退回</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/print.css" media="print">
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
	<form:form id="inputForm" style="margin:0;" modelAttribute="pdDosagert" action="${ctx}/pd/pdDosagert/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="right-main-box">
			<div class="btnBox">
				<c:choose>
					<c:when test="${flag == 'save' }">
						<h4>用量退回添加</h4>
					</c:when>
					<c:otherwise>
						<h4>用量退回查看</h4>
					</c:otherwise>
				</c:choose>
				
				
			</div>
			<div class="searchBox">
				<input type="hidden" name="flag" value="${flag }" />
				<input type="hidden" name="dosagertNoBack" value="${pdDosagert.dosagertNo }" />
				<input type="hidden" name="dosagertDateBack" value="<fmt:formatDate value="${pdDosagert.dosagertDate }" pattern="yyyy-MM-dd"/>" />
				<input type="hidden" name="dosagertInroomIdBack" value="${spd:getStoreroomName(pdDosagert.dosagertInroomId)}" />
				<div>
					<label for="">用量退单号</label>
					<input path="dosagertNo" type="text" style="width:165px;" name="dosagertNo" htmlEscape="false" maxlength="64" class="input-xlarge " readOnly="true" value="${number }" />
				</div>
				<div>
					<label for="">退货时间</label>
					<input path="dosagertDate" type="text" name="dosagertDate" htmlEscape="false" maxlength="64" class="input-xlarge " readOnly="true" value="${date }"/>
				</div>
				<div>
					<label for="">退入到</label>
					<input type="hidden" name="dosagertInroomId" value="${storeroomId }" />
					<input path="dosagertInroomId" htmlEscape="false" maxlength="64" class="input-xlarge " style="width:129px;" name="dosagertInroomName" readOnly="true" value="${spd:getStoreroomName(storeroomId)}"/>
			 		<input type="hidden" name="dosagertRoomId" value="${storeroomId }" />
				<%--	<input path="dosagertInroomId" type="text" htmlEscape="false" maxlength="64" class="input-xlarge " readOnly="true" value="${spd:getStoreroomName(storeroomId)}"/> --%>
				</div>
				<br>
			</div>
			<div class="tableBox">
				<table id="contentTableTbody2" class="hcy-public-table" >
				<thead>
						<tr>
							<%--<th style="width:80px;"><input type="checkbox" checked="checked" id="allchoose"/>执行退费</th>--%>
							<th>产品名称</th>
							<th>产品编号</th>
							<th>产品条码</th>
							<th>规格</th>
							<th>型号</th>
							<th>单位</th>
							<th>批号</th>
							<th>有效期</th>
							<th>退货数量</th>
							<th>产品单价</th>
							<th>金额</th>
						</tr>
					</thead>
					<tbody id="productTbody">
						<c:forEach items="${pdDosagert.detailList }" var="list" varStatus="a">
							<tr>
								<td>${list.pdProduct.name }</td>
								<td>${list.prodNo }</td>
								<td>${list.prodBarcode }</td>
								<td>${list.pdProduct.spec }</td>
								<td>${list.pdProduct.version }</td>
								<td>${list.pdProduct.unit }</td>
								<td>${list.batchNo }</td>
								<td><fmt:formatDate value="${list.expireDate }" pattern="yyyy-MM-dd"/></td>
								<td>${list.rtCount }</td>
								<td>${list.unitPrice }</td>
								<td>${list.amountMoney }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					<div class="totalText">
						<span id="allNum">退货产品总数:<span class="total_number">${pdDosagert.dosagertCount }</span></span>
						<span id="allMoney">总金额:<span class="total_money">${pdDosagert.dosagertMoney }</span></span>
					</div>
<%-- 				<c:if test="${flag == 'save' }">
					<input type="checkbox" id="isRefund" />执行退费
				</c:if> --%>
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
				<c:if test="${flag == 'save' }">
	          		<input id ='submitForm' type="button" value="保存" class="hcy-btn hcy-btn-primary" />
	          		<%--<input id ='submitForm1' type="button" value="保存并打印" class="hcy-btn hcy-btn-primary" /> 查看时打印--%>
          		</c:if>
				<c:if test="${flag == 'detail' }">
					<input id ='btnSubmitPrint' type="button" value="打印" class="hcy-btn hcy-btn-primary" />
				</c:if>
				<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
          	</div>
		</div>
	</form:form>
		<div id="printBox">
		<div class="printHeader">
			<h3>${fns:getUser().companyName}</h3>
			<h3>用量退回单</h3>
		</div>
		<div class="printPage">
			页码：1/1
		</div>
			<div style="margin-left: 50%" id="div_id"></div>
		<div class="printList">
			<ul>
				<li>
					<label>用量退单号：</label>
					<span>${pdDosagert.dosagertNo }</span>
				</li>
				<li>
					<label>退用量日期：</label>
					<span><fmt:formatDate value="${pdDosagert.dosagertDate }" pattern="yyyy-MM-dd"/></span>
				</li>
				<li>
					<label>  操作人员：</label>
					<span>${fns:getUser().name}</span>
				</li>

			</ul>
			<ul>
				<li>
					<label>退入库房：</label>
					<span>${spd:getStoreroomName(pdDosagert.dosagertInroomId)}</span>
				</li>
				<li>
					<label>  病人信息：</label>
					<span id="patientInformation" >${pdDosagert.patientName}</span>
				</li>

				<li class="otherWord">
					<label style="float:left;">备注：</label>
					<span style="width:auto;">${pdDosagert.remarks }</span>
				</li>
			</ul>
		</div>
		<div class="printTab">
			<table>
				<thead style="border-bottom:2px solid #666">
					<tr>
						<th>产品编号</th>
						<th>产品名称</th>
						<th>规格</th>
						<th>型号</th>
						<th>批号</th>
						<th>数量</th>
						<th>有效期</th>
						<%--<th>诸位</th>--%>
					</tr>
				</thead>
				<tbody id="printDiv">
				</tbody>
			</table>
		</div>
		<div class="signBox">
			<h3>仓库管理员人员：___________</h3>
		</div>
	</div>
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/pic-upload/pic-upload.js"></script>
	<!-- <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script> -->
	<script src="${ctxStatic}/spd/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
	<script src="${ctxStatic}/jquery/jquery-barcode.js"></script>
	<script type="text/javascript">
		$(function(){
		    alltotal = 0.00,//总金额
	        allcount = 0;//总数量
			//全选与反选
			$('#allchoose').click(function(){
				if($(this).is(':checked')){
					$("#productTbody :checkbox").attr('checked','true');
				}else{
					$("#productTbody :checkbox").removeAttr('checked');
				}
			});
			
			var flag = $("[name=flag]").val();
			if(flag == 'detail'){
				$("[name=dosagertNo]").val($("[name=dosagertNoBack]").val());
				$("[name=dosagertDate]").val($("[name=dosagertDateBack]").val());
				$("[name=dosagertInroomName]").val($("[name=dosagertInroomIdBack]").val());
			}
			
			$("#addFormOrder").click(function(){
				 if($("#hospitalNo").val() == ''){
					layer.alert("需要填写住院号哦~~");
				}else{
					var hospitalNo = $("#hospitalNo").val();
					$.ajax({
						type:"GET",
						url:"${ctx}/pd/pdDosagert/searchPatientName?hospitalNo="+hospitalNo,
						dataType:"text",
						success:function(data){
							if(data == 'none'){
								layer.alert("无病人信息");
							}else{
								$("#patientName").val(data);
								$("#patientInformation").text(data);
								searchInfo();
							}
						}
					})
				} 
			})
			
			function searchInfo(){
				if($("#hospitalNo").val() == ''){
					layer.alert("需要填写住院号哦~~");
				}else{
					$.ajax({
						type:"POST",
						url:"${ctx}/pd/pdDosagert/searchInfo",
						data:{hospitalNo:$("#hospitalNo").val(),patientName:$("#patientName").val()},
						dataType:"json",
						success:function(data){
							if(data == 'none'){
								layer.alert("无病人信息");
							}else{}
							show(data);
						}
					})
				}
			}
			
			function show(data){
				var data = eval(data);
				var html = '';
				$("#productTbody").empty();
				for(var i = 0 ; i < data.length ; i++){
					var date = data[i].expireDate;
					var dateStr;
					if(date == null){
						dateStr = '';
					}else{
						dateStr = dateFormat(date);
					}
					var sellingPrice = data[i].pdProduct.sellingPrice;
					if(sellingPrice == null){
						sellingPrice = 0;
					}
					if(data[i].dosageCount != 0){
						html += '<tr><input type="hidden" name="detailList['+i+'].prodId" value="'+data[i].prodId+'"/>'+
						'<input type="hidden" name="detailList['+i+'].dosageNo" value="'+data[i].dosageNo+'"/>'+
						'<input type="hidden" name="detailList['+i+'].dosagertNo" value="'+data[i].dosagertNo+'"/>'+
						'<td><input type="checkbox" checked="checked" name="detailList['+i+'].isRefund" value="1" class="checkbox"  /></td>'+
						'<td><input type="hidden" name="detailList['+i+'].prodName" value="'+data[i].pdProduct.name+'" />'+data[i].pdProduct.name+'</td>'+
						'<td><input type="hidden" name="detailList['+i+'].prodNo" value="'+data[i].pdProduct.number+'" />'+data[i].pdProduct.number+'</td>'+
						'<td><input type="hidden" name="detailList['+i+'].prodBarcode" value="'+data[i].prodBarcode+'" />'+data[i].prodBarcode+'</td>'+
						'<td><input type="hidden" name="detailList['+i+'].prodSpec" value="'+data[i].pdProduct.spec+'" />'+data[i].pdProduct.spec+'</td>'+
						'<td><input type="hidden" value="'+data[i].pdProduct.version+'" />'+data[i].pdProduct.version+'</td>'+
						'<td><input type="hidden" name="detailList['+i+'].prodUnit" value="'+data[i].pdProduct.unit+'" />'+data[i].pdProduct.unit+'</td>'+
						'<td><input type="hidden" name="detailList['+i+'].batchNo" value="'+data[i].batchNo+'" />'+data[i].batchNo+'</td>'+
						'<td><input type="hidden" name="detailList['+i+'].expireDate" value="'+dateFormat(data[i].expireDate)+'" />'+dateStr+'</td>'+
						'<td><input type="hidden" name="detailList['+i+'].dosageCount" value="'+data[i].dosageCount+'" />'+data[i].dosageCount+'</td>'+
						'<td><input type="hidden" name="detailList['+i+'].leftRefundNum" value="'+data[i].leftRefundNum+'" />'+data[i].leftRefundNum+'</td>'+
						'<td><input class="rtCount" type="text" style="width:30px" name="detailList['+i+'].rtCount" value="0" required /></td>'+
						'<td class="sellingPrice"><input type="hidden" name="detailList['+i+'].unitPrice" value="'+sellingPrice+'" />'+sellingPrice+'</td>'+
						'<td class="finalMoney"></td>'+
						'<td type="hidden"><input name="detailList['+i+'].dosageDetailId" value ="'+data[i].id+'" type="hidden" /></td>'+
						'<td type="hidden"><input name="detailList['+i+'].chargeCode" value="'+data[i].pdProduct.chargeCode+'" type="hidden" /></td>'+
						'</tr>'
					}
				}
				if(data.length == 0){
					layer.alert("病人在当前库房并没有用量或用量已全部退完~");
				}else{
					$("#productTbody").append(html);
					html = '';
				}
			}
			
			//普通提交保存
			$("#submitForm").click(function(){
				//可退回产品条数
				var len = $('#productTbody>tr').length;
				//校验退货数量需要的标识
				var flag = true; 
				//校验是否有填写大于零的数				
				var isEffectiveVal = false;
				//校验表格中所有退货数量是否填写正确
				$("input[class*='rtCount']").each(function(i,v){
					if(isNaN(v.value) || !v.value || v.value<0){
						flag = false;
						return false;
					}
					if(!isEffectiveVal){
						if(v.value>0){
							isEffectiveVal = true;
						}
					}
				})
				
				var rgb = true;
				$("#productTbody").find("tr").each(function(){
					var csv = $(this).css('background-color');
					if(csv && (csv == 'red'||csv=="rgb(255, 0, 0)")){
						rgb = false;
						return false;
					}
				});
				//校验
				if(len < 1){
					layer.alert('请添加退货数量',{icon:0});
				}else if(flag==false){
					layer.alert('请填写有效的退货数量',{icon:0});
				}else if (rgb==false){
					layer.alert('退货数量不能大于可退货数',{icon:0});
				}else if(isEffectiveVal==false){
					layer.alert('退货数量不能全部为零',{icon:0});
				}else{
					$('#inputForm').submit();
				}
			})


			//查看时打印
			$("#btnSubmitPrint").click(function(){
				//拼接打印内容
				var temp = "";
				$("#productTbody").find("tr").each(function(){
					var tdArr = $(this).children();
					var ptoductNumberHtml = tdArr.eq(1)[0].innerHTML;//产品编号
					var ptoductNameHtml = tdArr.eq(0)[0].innerHTML;//产品名称
					var ptoductSpecHtml = tdArr.eq(3)[0].innerHTML;//产品规格
					var ptoductVersionHtml = tdArr.eq(4)[0].innerHTML;//产品型号
					var ptoductBatchNoHtml = tdArr.eq(6)[0].innerHTML;//产品批号
					var ptoductRtCount = tdArr.eq(8)[0].innerHTML;//退货数量
					var ptoductRtCountHtml = "<input value="+ptoductRtCount+" type='hidden'>"+ptoductRtCount+"";
					var ptoductExpireDateHtml = tdArr.eq(7)[0].innerHTML;//有效期
					//var ptoductLocationHtml = tdArr.eq(2)[0].innerHTML;//储位
					temp +="<tr><td>"+ptoductNumberHtml
							+"</td><td>"+ptoductNameHtml
							+"</td><td>"+ptoductSpecHtml
							+"</td><td>"+ptoductVersionHtml
							+"</td><td>"+ptoductBatchNoHtml
							+"</td><td>"+ptoductRtCountHtml
							+"</td><td>"+ptoductExpireDateHtml
							//+"</td><td>"+ptoductLocationHtml
							+"</td></tr>";
				});

				//打印
				$("#printDiv").append(temp);
                barCode();
				$("#printBox").jqprint();
			})

			//打印并提交
			$("#submitForm1").click(function(){
				//可退回产品条数
				var len = $('#productTbody>tr').length;
				//校验退货数量需要的标识
				var flag = true; 
				//校验是否有填写大于零的数				
				var isEffectiveVal = false;
				//校验表格中所有退货数量是否填写正确
				$("input[class*='rtCount']").each(function(i,v){
					if(isNaN(v.value) || !v.value || v.value<0){
						flag = false;
						return false;
					}
					if(!isEffectiveVal){
						if(v.value>0){
							isEffectiveVal = true;
						}
					}
				})
				
				var rgb = true;
				$("#productTbody").find("tr").each(function(){
					var csv = $(this).css('background-color');
					if(csv && (csv == 'red'||csv=="rgb(255, 0, 0)")){
						rgb = false;
						return false;
					}
				});
				//校验
				if(len < 1){
					layer.alert('请添加退货数量',{icon:0});
				}else if(flag==false){
					layer.alert('请填写有效的退货数量',{icon:0});
				}else if (rgb==false){
					layer.alert('退货数量不能大于可退货数',{icon:0});
				}else if(isEffectiveVal==false){
					layer.alert('退货数量不能全部为零',{icon:0});
				}else{
					//拼接打印内容
					var temp = "";
					$("#productTbody").find("tr").each(function(){
						var tdArr = $(this).children();
						var ptoductNumberHtml = tdArr.eq(5)[0].innerHTML;//产品编号
						var ptoductNameHtml = tdArr.eq(4)[0].innerHTML;//产品名称
						var ptoductSpecHtml = tdArr.eq(7)[0].innerHTML;//产品规格
						var ptoductVersionHtml = tdArr.eq(8)[0].innerHTML;//产品型号
						var ptoductBatchNoHtml = tdArr.eq(10)[0].innerHTML;//产品批号
						var ptoductRtCount = tdArr.eq(14).find("input").val();//退货数量
						var ptoductRtCountHtml = "<input value="+ptoductRtCount+" type='hidden'>"+ptoductRtCount+"";
						var ptoductExpireDateHtml = tdArr.eq(11)[0].innerHTML;//有效期
						var ptoductLocationHtml = tdArr.eq(4)[0].innerHTML;//储位
						temp +="<tr><td>"+ptoductNumberHtml
								+"</td><td>"+ptoductNameHtml
								+"</td><td>"+ptoductSpecHtml
								+"</td><td>"+ptoductVersionHtml
								+"</td><td>"+ptoductBatchNoHtml
								+"</td><td>"+ptoductRtCountHtml
								+"</td><td>"+ptoductExpireDateHtml
								+"</td><td>"+ptoductLocationHtml
								+"</td></tr>";
					});
					   $.ajax({
				            type: "POST",   //提交的方法
				            url:"${ctx}/pd/pdDosagert/save", //提交的地址  
				            data:$('#inputForm').serialize(),// 序列化表单值  
				            async: false,    
				            success: function(data) {
								layer.alert("操作成功",{icon:1},function(index){
									layer.close(index);
									//打印
									$("#printDiv").append(temp);
									barCode();
									$("#printBox").jqprint();
					            	$('#submitForm,#submitForm1').hide();
								});
				            },
							error: function(XMLHttpRequest, textStatus, errorThrown) {
								layer.alert('保存用量退回信息失败',{icon:0});
							}
				         });
					//$('#inputForm').submit();
				}
			})
		})
  function barCode(){
      var code='${pdDosagert.dosagertNo }';
      $('#div_id').empty().barcode(code, "code128",{
          output:'css',
          color: '#000000',
          barWidth: 2,
          barHeight: 40,
          addQuietZone: false,
          fontSize: 0,
      });
  }



		//计算单个金额和总数量
		$(document).on('input propertychange', '.rtCount', function(){
			computeDosage($(this));
			computeSingleMoney($(this));
			computeTotalMoney();
			return false;
		});
		//动态计算总数量和总金额
		function computeTotalMoney(){
			//计算总数量
			allcount = 0;
			$("input[class*='rtCount']").each(function(i,v){
				allcount += parseInt($(this).val() || 0);
			});
			$('.total_number').html(allcount);
			//计算总金额
			alltotal = 0.00;
			$("td[class='finalMoney']").each(function(i,v){
				alltotal += parseFloat($(this).text() || 0);
			});
			$('.total_money').html(alltotal.toFixed(4));
		}
		//动态计算单个金额
		function computeSingleMoney(obj){
			var curValue = $.trim(obj.val()),
			    singlePrice = $.trim(obj.parent().parent().find('.sellingPrice').find('input').val()),
			    total = null;
			var ival = parseInt(curValue);
			if(isNaN(ival)){
				layer.alert('用量数量不能为空且是有效数字',{icon:2});
				return false;
			}
			try{
				if(singlePrice && !Number.isNaN(singlePrice) && Number.parseInt(singlePrice) > 0) {
					total = (parseFloat(curValue || 0) * parseFloat(singlePrice)).toFixed(4);
					obj.parent().parent().find('.finalMoney').text(total);
				}
			}catch(e){
				layer.alert('用量数量不能为空且是有效数字',{icon:2});
			}
		}
		
		//计算退货数量不能大于可退货数
		function computeDosage(inputObj){
			//可退货数,退货数
			var leftRefundNum ,rtCount;
			leftRefundNum = inputObj.parent().parent().find("input[name$='leftRefundNum']").val();//可退货数
			rtCount = inputObj.parent().parent().find("input[name$='rtCount']").val();//退货数
			if(Number.parseInt(rtCount) > Number.parseInt(leftRefundNum)){
				layer.alert('退货数量不能大于可退货数', {icon:0});
				inputObj.parent().parent().css('background-color','red');
			}else{
				inputObj.parent().parent().css('background-color','');
			}		
		}
		
		//时间类型转换
		function dateFormat(longTypeDate){ 
			var dateType = ""; 
			var date = new Date(); 
			date.setTime(longTypeDate); 
			dateType += date.getFullYear();  //年 
			dateType += "-" + getMonth(date); //月  
			dateType += "-" + getDay(date);  //日 
			return dateType;
		} 
		
		//返回 01-12 的月份值  
		function getMonth(date){ 
			var month = ""; 
			month = date.getMonth() + 1; //getMonth()得到的月份是0-11 
			if(month<10){ 
				month = "0" + month; 
			} 
			return month; 
		} 
		//返回01-30的日期 
		function getDay(date){ 
			var day = ""; 
			day = date.getDate(); 
			if(day<10){ 
				day = "0" + day; 
			} 
			return day; 
		}
	</script>
</body>
</html>