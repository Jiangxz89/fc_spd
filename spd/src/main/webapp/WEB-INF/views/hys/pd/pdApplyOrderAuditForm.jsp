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
		#describe{width: 500px;height: 100px;vertical-align: top;}
		.littleTip{font-size: 14px;color: #000;padding-right: 10px;display: inline-block;width: 70px;}
		#refuseBtn{background:red;border-color: red;}
		.layui-layer-content{padding:0 15px;text-align:center;}
	</style>
	<title>领用审核详情</title>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>领用审核详情</h4>
		</div>
		<div class="searchBox">
			<label for="">申领单号</label>
			<input type="text" style="width:170px;" value="${pdApplyOrder.applyNo }" readonly="readonly"/>
			<label for="">申领时间</label>
			<input type="text" value='<fmt:formatDate value="${pdApplyOrder.applyDate }" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly"/>
		</div>
		<form:form id="inputForm" modelAttribute="pdApplyOrder" action="${ctx}/pd/pdApplyOrder/save" method="post" class="form-horizontal">
			<form:hidden path="id"/>
			<sys:message content="${message}"/>		
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
							<th>申领科室库存数量</th>
							<th>本科室库存数量</th>
							<th>申领数量</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="otherText" style="margin-top: 30px;">
				<h3 class="littleTip">备注</h3>
				<textarea name="" id="describe" readonly="readonly">${pdApplyOrder.remarks }</textarea>
			</div>
		</form:form>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
   			<a href="###" class="hcy-btn hcy-btn-primary" id="printBtn">通过并出库</a>
   			<a href="###" class="hcy-btn hcy-btn-primary" id="refuseBtn">拒绝</a>
   			<a href="javascript:location.href=document.referrer;" class="hcy-btn hcy-btn-o" style="line-height: normal;padding:6px 12px;background: #fff;color: #666;border-color: #ccc;">返回</a>
   		</div>
	</div>
	<div class="refuseBox" style="display:none;">
		<textarea id="refuseArea" style="width:100%;height:130px;margin-top:20px;" maxlength="100"></textarea>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/js/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//加载
			loadHtml('${pdApplyOrder.applyNo}');
			//拒绝
			$("#refuseBtn").click(function(){
				layer.open({
					type:1,
					title:"拒绝领用审核",
					content:$(".refuseBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var reason = $.trim($('#refuseArea').val());
						if(!reason){
							layer.alert('请填写拒绝理由',{icon:0});
							return false;
						}
						auditApplyOrder('2',reason);
						layer.closeAll();
					},
					btn2:function(){
						layer.closeAll();
					}
				});
			});
			//通过并出库
			$('#printBtn').click(function(){
				//校验
				var success = true;
				$('table>tbody>tr').each(function(){
					var ihava = $(this).find('input:eq(0)').val();
					var yneed = $(this).find('input:eq(1)').val();
					if(ihava && yneed){
						if (Number(yneed) > Number(ihava)){
							layer.alert('申领数量已大于库存数量', {icon:0});
							success = false;
							$(this).css('background-color', 'red');
						}
					}
				});
				if(success)
					auditApplyOrder('1', null);
			});
			
		});
		//审核申请单
		function auditApplyOrder(status, reason){
			$.ajax({
				url:'${ctx}/pd/pdApplyOrder/auditApplyOrder',
				type:'post',
				data:{applyNo:'${pdApplyOrder.applyNo}',applyStatus:status,refuseReason:reason},
				success:function(data){
					if(data.code==200){
						layer.alert('操作成功', {icon:1});
						if(status==1){//调转到出库
							window.location.href = '${ctx}/pd/pdStockRecord/pdPassAddStockRecord?applyNo=${pdApplyOrder.applyNo}&&flag=1';
						}else if(status==2){
							window.location.href = '${ctx}/pd/pdApplyOrder/auditList';
						}
					}else{
						layer.alert('操作失败，请联系管理员', {icon:2});
					}
				}
			});
		}
		//加载详情
		function loadHtml(applyNo){
			$.getJSON('${ctx}/pd/pdApplyDetail/getData',{applyNo:applyNo,flag:'auditForm'},function(data){
				var html = '', datap = data.productList, data = data.packageList;
				//定数包
				$.each(data,function(i,v){
					var rowslen = data[i].list.length;
					 for(var j=0;j<rowslen;j++){
						var obj = data[i].list[j];
						var totP = Number(data[i].packageCount || '0') * Number(obj.productCount || '0');//包内每个产品的申领总数
						html += '<tr>';
						 if(j==0){
							 html += '<td rowspan="'+rowslen+'">'+data[i].packageName+'</td>';
							 html += '<td rowspan="'+rowslen+'">'+data[i].packageNumber+'</td>';
						 }
						 html +='<td>'+obj.pdProduct.name+'</td>'
						 +		'<td>'+obj.pdProduct.number+'</td>'
						 +		'<td>'+obj.pdProduct.spec+'</td>'
						 +		'<td>'+obj.pdProduct.version+'</td>'
						 +		'<td>'+obj.productCount+'</td>'
						 +		'<td>'+obj.pdProduct.unit+'</td>'
						 +		'<td>'+obj.yourStockNum+'</td>'
						 +		'<td>'+obj.stockNum+'</td>';
						 if(j==0){
						 	html += '<td rowspan="'+rowslen+'">'+data[i].packageCount+'</td>';
						 }
						 html += '<input type="hidden" value="'+obj.stockNum+'"/>';
						 html += '<input type="hidden" value="'+totP+'"/>';
					    html += '</tr>';
					 }
				});
				//产品
				$.each(datap, function(i,v){
					html += '<tr>'
						 +		'<td></td>'
						 +		'<td></td>'
						 +		'<td>'+datap[i].pdProduct.name+'</td>'
						 +		'<td>'+datap[i].prodNo+'</td>'
						 +		'<td>'+datap[i].pdProduct.spec+'</td>'
						 +		'<td>'+datap[i].pdProduct.version+'</td>'
						 +		'<td>--</td>'
						 +		'<td>'+datap[i].pdProduct.unit+'</td>'
						 +		'<td>'+datap[i].yourStockNum+'</td>'
						 +		'<td>'+datap[i].mineStockNum+'</td>'
						 +		'<td>'+datap[i].applyCount+'</td>'
						 +		'<input type="hidden" value="'+datap[i].mineStockNum+'"/>'
						 +		'<input type="hidden" value="'+datap[i].applyCount+'"/>'
						 +	'</tr>';
				});
				//追加html
				if(html)
					$('tbody').append(html);
			});
		}
	</script>
</body>
</html>