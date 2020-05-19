<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<style>
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;}
		.otherText>h3{font-weight:400;display:inline-block;padding:3px 10px 0 5px;font-size:12px;color:#666;}
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
	</style>
	<title>申请领用</title>
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
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
			<h4>申请领用</h4>
		</div>
		<div class="searchBox">
			<label for="">申领单号</label>
			<input type="text" readOnly name="applyNo" style="width:170px;" value="${pdApplyOrder.applyNo }"/>
			<label for="">申领时间</label>
			<input type="text" readOnly name="applyDate" value="<fmt:formatDate value="${pdApplyOrder.applyDate }" pattern="yyyy-MM-dd"/>"/>
		</div>
		<form:form id="inputForm" modelAttribute="pdApplyOrder" action="${ctx}/pd/pdApplyOrder/save" method="post" class="form-horizontal">
			<form:hidden path="applyNo"/>
			<form:hidden path="applyDate"/>
			<sys:message content="${message}"/>		
			<div class="tableBox">
				<table class="hcy-public-table" style="padding-bottom:30px;">
					<thead>
						<tr>
							<th>操作</th>
							<th>定数包名称</th>
							<th>定数包编号</th>
							<th>产品名称</th>
							<th>产品编号</th>
							<th>规格</th>
							<th>型号</th>
							<th>产品数量</th>
							<th>单位</th>
							<th>库存数量</th>
							<th>申领数量</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="totalText">
					<span id="allNum">产品总数量:<span id="allocationSum">0</span></span>
				</div>
				<a href="###"class="hcy-btn hcy-btn-primary" id="addProBtn">添加产品</a>
				<%--<a href="###"class="hcy-btn hcy-btn-primary" id="addPackage">添加定数包</a>--%>
			</div>
			<div class="otherText" style="margin-top: 30px;">
				<h3>备注</h3>
				<textarea name="remarks" class="remarkArea" maxlength="200"></textarea>
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
      			<input type="button" value="保存" class="hcy-btn hcy-save" id="submitBtn"/>
      			<a href="javascript:location.href=document.referrer;" class="hcy-btn hcy-back" style="line-height: normal;padding:6px 12px;background: #fff;color: #666;border-color: #ccc;">返回</a>
      		</div>
		</form:form>
	</div>
	<!-- 记录添加的行号 -->
	<input type="hidden" id="rowsRecord" value="0"/>
	<input type="hidden" id="rowsPackage" value="0"/>
	<script type="text/javascript">
	var idplus = [];
	$(function(){
		$('#submitBtn').click(function(){
			var len = $('tbody>tr').length;
			var flag = true, paFlag = true;
			$("input[name$='applyCount']").each(function(i,v){
				var result = alertWarn('请输入大于0的整数', $(this));
				if(result == false){
					flag = false;
				}
			});
			$("input[name$='packageCount']").each(function(i,v){
				var result = alertWarn('请输入大于0的整数', $(this));
				if(result == false){
					paFlag = false;
				}
			});
			if(!flag || !paFlag){
				return false;
			}
			if(len<1){
				layer.alert('请添加产品或定数包',{icon:0});
			}else{
				$(this).attr('disabled','true');
				loading('正在提交，请稍等...');
				$('#inputForm').submit();
			}
			return false;
		});
		//添加产品
		$("#addProBtn").click(function(){
			layer.open({
				type:2,
				title:"添加产品",
				content:'${ctx}/pd/pdProduct/chooseProductList?remarks=fromApplyOrder',
				area:["965px","527px"],
				shade: [0.8, '#393D49'],
				btn:["确定","取消"],
				yes:function(index,layero){
					var childWindow = layero.find('iframe')[0].contentWindow;
					var result = childWindow.showProdHtml($('#rowsRecord').val());
					if(result === 0){
						layer.alert('请选择产品',{icon: 0});
					}else{
						$('tbody').append(result);
						var needLen = Number($('tbody>tr.myselfprod').length) + Number($('#rowsPackage').val());
						$('#rowsRecord').val(needLen);
						layer.closeAll();
					}
				},
				btn2:function(){
					layer.closeAll();
				}
			})
		});
		//添加定数包
		$("#addPackage").click(function(){
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
					var result = childWindow.showPackageHtml($('#rowsRecord').val());
					if(result[0] === 0){
						layer.alert('请选择定数包',{icon: 0});
					}else{
						$('tbody').append(result[1]);
						var needLen = Number($('tbody>tr.myselfprod').length) + Number(result[0]) + Number($('#rowsPackage').val());
						$('#rowsRecord').val(needLen);
						$('#rowsPackage').val(Number(result[0]) + Number($('#rowsPackage').val()));
						layer.closeAll();
					}
				},
				btn2:function(){
					layer.closeAll();
				}
			})
		});
		
		//动态计算产品总数量
		$(document).on('input propertychange', '.fillNumberSeat', function(){
			alertWarn('请输入大于0的整数', $(this));
			computeTotal();
		});
	});
	//计算产品总数
	function computeTotal(){
		var packNum = 0, prodNum = 0;
		$.each($('.fillNumberSeat'), function(){
			var pidx = $(this).attr('pack-idx');
			var curV = $.trim($(this).val()) || 0;
			if(!isNumber(curV))
				curV = 0;
			if(pidx){//包
				packNum += parseInt($('.packProdNum'+pidx).val()) * parseInt(curV); 
			}else{
				prodNum += parseInt(curV);
			}
		});
		$('#allocationSum').html(parseInt(packNum || 0) + parseInt(prodNum || 0));
	}
	//仅供添加定数包测试用
	function addPack(){
		var html = '';
		for(var i=0;i<3;i++){
			html += '<tr>';
				 if(i==0){
					 html += '<td rowspan="3"><i class="fa fa-trash-o"></i><i class="fa fa-search"></i></td>';
					 html += '<td rowspan="3">扩张导管A包</td>';
					 html += '<td rowspan="3">69474369</td>';
				 }
				 html +='<td>快速交换球囊扩张导管</td>'
				 +		'<td>00643169474369</td>'
				 +		'<td>3.25mm*12mm</td>'
				 +		'<td>PI12520</td>'
				 +		'<td>10</td>'
				 +		'<td>根</td>'
				 +		'<td>1</td>';
				 if(i==0){
					 html += '<td rowspan="3"><input type="text" style="width:60px;" value="45"/></td>';
				 }
			    html += '</tr>';
		}
		return html;
	}
	//删除该包
	function removeDiv(id,flag){
		$('.remove_'+id).remove();
		var delIndex = idplus.indexOf(id);
		if(delIndex > -1){
			idplus.splice(delIndex,1);
		}
		/* var len = Number($('#rowsRecord').val());
		if(len > 0)
			$('#rowsRecord').val(len - 1);
		if(flag==0){//删除包
			var plen = Number($('#rowsPackage').val());
			if(plen > 0)
				$('#rowsPackage').val(plen - 1 );
		} */
		computeTotal();	
	}
	
	
	</script>
</body>
</html>