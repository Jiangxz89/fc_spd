<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存盘点信息管理</title>
	<meta charset="UTF-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<style>
		.selWarn{color:red;font-size:13px;padding-left:15px;display:none;}
		.searchBox span{display:inline-block;font-size:13px;color:#666;vertical-align: middle;padding-right: 20px;;}
	</style>
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
		<div class="btnBox"><h4>开始盘点</h4></div>
		<form:form id="inputForm" style="padding:0;background:#fff;" modelAttribute="pdProductStockCheck" method="post" class="form-horizontal">
			<div class="searchBox">
				<label>盘点时间</label><span style="vertical-align:middle;">${checkBean.checkDate }</span>
				<input type="hidden" name="checkDate" value="${checkBean.checkDate }" />
				<div class="fr">
					<a href="javascript:history.go(-1)" style="line-height:1.5;" class="hcy-btn hcy-btn-primary">返回</a>
					<input type="submit" onclick="return save1()" style="line-height:1.5;" class="hcy-btn hcy-btn-primary" value="临时保存" />
					<input type="submit" onclick="return save2()" style="line-height:1.5;" class="hcy-btn hcy-btn-primary" value="盘点完成" />
					<!-- <a href="###" onclick="save1()" style="line-height:1.5;" class="hcy-btn hcy-btn-primary">临时保存</a>
					<a href="###" onclick="save2()" style="line-height:1.5;" id="checkDone" class="hcy-btn hcy-btn-primary">盘点完成</a> -->
				</div><br>
				<label>编号</label><span class="checkCode">${checkBean.number }</span>
				<input type="hidden" name="number" value="${checkBean.number }" />
				<input type="hidden" name="id" value="${checkBean.id }">
				<label style="width:70px;">盘点仓库</label><span id="warehouseName" class="checkHouse">${checkBean.repoName }</span>
				<input type="hidden" id="repoId" name="repoId" value="${checkBean.repoId }" />
				<input type="hidden" id="repoName" name="repoName" value="${checkBean.repoName }" />
				<label style="width:95px;">盘点产品量</label><span id="checkNum" class="checkNum"></span>
				<input type="hidden" id="shouldCount" name="shouldCount" value="${checkBean.shouldCount }" />
				<label style="width:95px;">已盘点产品量</label><span style="width:105px;" id="checkedNum" class="checkedNum">${checkBean.alreadyCount }</span>
				<input type="hidden" id="alreadyCount" name="alreadyCount" value="${checkBean.alreadyCount }" />
				<a id="addProBtn" href="###" style="line-height:1.5;"  class="hcy-btn hcy-btn-primary">添加产品</a>
			</div>
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>操作</th>
						<th>产品名称</th>
						<th>产品编号</th>
						<th>产品条码</th>
						<th>规格</th>
						<th>批号</th>
						<th>有效期</th>
						<th>单位</th>
						<th>理论数量</th>
						<th>盘点数量</th>
						<th>盘盈盘亏</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody id="contentTableTbody">
					<c:if test="${checkBean != null }">
						<c:forEach items="${checkBean.child }" var="a" varStatus="aa">
							<tr class="stockTr">
								<td><i class="fa fa-trash-o"></i></td>
								<input type="hidden" name="child[${aa.index }].id" value="${a.id }" />
								<input type="hidden" class="proIdClass" name="child[${aa.index }].stockId" value="${a.stockId }" />
								<td>${a.pdProduct.name }</td>
								<td>${a.pdProduct.number }</td>
								<td><a href="###" class="overLook" title="${a.pdProductStock.productBarCode }">${a.pdProductStock.productBarCode }</a></td>
								<td>${a.pdProduct.spec }</td>
								<td>${a.pdProductStock.batchNo }</td>
								<td><fmt:formatDate value="${a.pdProductStock.validDate }" pattern="yyyy-MM-dd" /></td>
								<td>${a.pdProduct.unit }</td>
								<td><input type="hidden" name="child[${aa.index }].stockCount" value="${a.pdProductStock.stockNum }">${a.pdProductStock.stockNum }</td>
								<td><input type="text" required class="actualCount" name="child[${aa.index }].actualCount" value="${a.actualCount }" style="width:50px;" onblur="sumCount(this,'${a.pdProductStock.stockNum }')" /></td>
								<td><input type="text" readonly="readonly" class="profitLossCount" name="child[${aa.index }].profitLossCount" value="${a.profitLossCount }" style="width:50px;" /></td>
								<td><input type="text" name="child[${aa.index }].remarks" class="remarks" value="${a.remarks }" /></td>
							</tr>
						</c:forEach>
						
					</c:if>
				</tbody>
			</table>
		</form:form>
		<div style="text-align:right;line-height:30px;padding-right:20px;" >
			盘盈：<span style="padding-right:20px;" id="result1">0</span>盘亏：<span id="result2">0</span>
		</div>
	</div>
	<div class="zhezhao_service" id="zhezhao_service" style="display:none">
		<div class="dalog">
			<ul id="medLi" style="margin:0;">
				<li style="padding:20px;"><label><span class="warn">*</span>请选择仓库：</label>
					<select id="warehouseIdSelect" name="warehouseId" class="input-medium required" style="margin:0;">
		  				  <option value="">请选择</option>
					</select>
					<span class="selWarn">请选择仓库！</span>
				</li>
			</ul>
		</div>
	</div>
	
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/js/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script>
		$(function(){
			//判断是新建盘点还是继续盘点
			if($("#repoName").val() == null || $("#repoName").val() == ''){
				//请求仓库列表
				 $.ajax({
					type:"POST",
					url:"${ctx}/pd/pdProductStockCheck/findStoreroomList",
					dataType:"json",
					success:function(data){
						var data = eval(data);
						html = '';
						for(var i = 0 ; i < data.length ; i ++){
							html += '<option value="'+data[i].id+'">'+data[i].storeroomName+'</option>';
						}
								
						$("#warehouseIdSelect").append(html);
					}
				}) 
				
				//页面加载时，弹出 选择仓库
				layer.open({
					type:1,//弹出层类型，默认为0(信息框)
					title:"新增盘点",//title设置标题名称
					content:$("#zhezhao_service"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					closeBtn:0,
					btn:["取消","开始盘点"],//自定义按钮的内容
					yes:function(){
						window.location.href="${ctx}/pd/pdProductStockCheck/list";
					},
					btn2:function(index, layero){
						var van = $("#warehouseIdSelect>option:selected").val();
						var vanText = $("#warehouseIdSelect>option:selected").text();
						 if(van ==''){
							$(".selWarn").show();
							return false;
						} 
					/* 	var warehouseId = $(layero).find("#warehouseIdSelect option:selected").val();
						var warehouseName = $(layero).find("#warehouseIdSelect option:selected").text();
						$("#warehouseId").val(warehouseId);
						$("#warehouseName").text(warehouseName); */
						$(".selWarn").hide();
						loadStock(van,vanText);
						showData();
						layer.closeAll();
					}
				})
			}else{
				showData();
			}
			
			//加载库存
			function loadStock(storeroomId, storeroomName){
				$("#warehouseName").html(storeroomName);
				$("#repoId").val(storeroomId);
				$("#repoName").val(storeroomName);
			}
			
			
			
			//加载库存列表
			$("#addProBtn").click(function(){
				//showData();
				layer.open({
					type:2,
					title:"添加盘点库存",
					content:"${ctx}/pd/pdProductStockCheck/findStockCheckList?storeroomId="+$("#warehouseIdSelect>option:selected").val(),
					area:["965px","527px"],
					shade: [0.8, '#393D49'],
					btn:["确定","关闭"],
					yes:function(index, layero){
						var childWindow = layero.find('iframe')[0].contentWindow;
						layer.closeAll();
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			});
			
			//请求数据
			 function showData(){
				var storeroomId = $("#repoId").val();
				 $.ajax({
						type:"POST",
						url:"${ctx}/pd/pdProductStockCheck/findStockByStoreroomId",
						dataType:"json",
						data:{
							storeroomId:storeroomId
							},
						success:function(data){
							showTable(data);
						}
					})
			}
			
			
			
			//加载数据列表
				function showTable(data){
					var data=eval(data);
					var html='';
					var pageHtml='';
					var stockSum = 0;//库存总量
					for(var i=0;i<data.length;i++){
						var date = new Date(data[i].pdProductStock.validDate);
						var dateFormat = date.getFullYear()+'-'+date.getMonth()+'-'+date.getDate();
						html+= '<tr>'+
						'<td>'+data[i].pdProduct.number+'</td>'+
						'<td>'+data[i].pdProduct.name+'</td>'+
						'<td>'+data[i].pdProduct.categoryName+'</td>'+
						'<td>'+data[i].pdProduct.groupName+'</td>'+
						'<td>'+data[i].pdProduct.unit+'</td>'+
						'<td>'+data[i].pdProduct.spec+'</td>'+
						'<td>'+data[i].pdProduct.version+'</td>'+
						'<td>'+data[i].pdProductStock.batchNo+'</td>'+
						'<td>'+dateFormat+'</td>'+
						'<td>'+data[i].pdProduct.venderName+'</td>'+
						'<td><input type="button" class="hcy-btn hcy-btn-o" value="选中" onclick="getStock(\''+data[i].pdProductStock.id+'\',this)" /></td>'+
						'</tr>';
						stockSum += data[i].pdProductStock.stockNum;
					}
					$("#checkNum").text(stockSum);
					$("#shouldCount").val(stockSum);
				//	$("#addContentTab>tbody").append(html);
				}
			
				
		});
		//删除行
		$("document").on("click",".delButton",function(){
			$(this).parents("tr").remove();
		})
		//获取单条库存展示到主列表
		function getStockAjax(id){
			var id = id;
			var list = $(".proIdClass");
			var listLen = $(".proIdClass").length;
			var flag = 0; //标记
			for(var i = 0 ; i < listLen ; i++){
				if(id == list[i].value){
					flag = 1;
				}
			}
			//验证是否已选中
			if(flag == 1){
				alert('已选中！不可重复选择！');
			}else{
				$.ajax({
					type:"POST",
					dataType:"json",
					data:{id:id},
					async:false,
					url:"${ctx}/pd/pdProductStockCheck/getStock",
					success:function(data){
						show(data);
					}
				})
			}
		}
		
		//选中数据展示到主列表
		function show(data){
			var data = eval(data);
			//var data = JSON.parse(data);
			var html='';
			var trCount=$("#contentTableTbody>tr").size();
			var date = new Date(data.pdProductStock.validDate);
			var dateFormat = date.getFullYear()+'-'+date.getMonth()+'-'+date.getDate();
				html+= '<tr class="stockTr">'+
						'<td><i class="fa fa-trash-o delButton"></i></td>'+
						'<input type="hidden" class="proIdClass" name="child['+trCount+'].stockId" value="'+data.pdProductStock.id+'" />'+
						'<td>'+data.pdProduct.name+'</td>'+
						'<td>'+data.pdProduct.number+'</td>'+
						'<td><a href="###" class="overLook" title="'+data.pdProductStock.productBarCode+'">'+data.pdProductStock.productBarCode+'</a></td>'+
						'<td>'+data.pdProduct.spec+'</td>'+
						'<td>'+data.pdProductStock.batchNo+'</td>'+
						'<td>'+dateFormat+'</td>'+
						'<td>'+data.pdProduct.unit+'</td>'+
						'<td><input type="hidden" name="child['+trCount+'].stockCount" value="'+data.pdProductStock.stockNum+'">'+data.pdProductStock.stockNum+'</td>'+
						'<td><input type="text" required class="actualCount" name="child['+trCount+'].actualCount" style="width:50px;" onblur="sumCount(this,'+data.pdProductStock.stockNum+')" /></td>'+
						'<td><input type="text" readonly="readonly" class="profitLossCount" name="child['+trCount+'].profitLossCount" style="width:50px;" /></td>'+
						'<td><input type="text" name="child['+trCount+'].remarks" class="remarks" /></td>'+
						'</tr>'
			$("#contentTableTbody").append(html);
		}
		
		//计算盈亏
			function sumCount(obj,n){
				//负数校验
				var vals = obj.value;
				if(vals < 0){
					var c = document.getElementsByClassName("actualCount");
					for(var f = 0 ; f < c.length ; f ++){
						if(c[f].value < 0){
							c[f].value = "";
							alertWarn('请输入大于0的整数！',obj)
						}
					}
					return false;
				}
				/* if(vals > 0){
					$(obj).css('border-color','#ccc')
				} */
			
				var re = obj.value - n;
				$(obj).parent().next().children().val(re);
				var a = 0;
				var eles = $(".profitLossCount");
				for(var i = 0 ; i < eles.length ; i++){
					if(eles[i].value != null && eles[i].value != NaN){
						a += parseInt(eles[i].value);
					}
				}
				if(a > 0){
					$("#result1").empty();
					$("#result2").empty();
					$("#result1").append(a);
					$("#result2").append(0);
				}else if(a < 0){
					$("#result1").empty();
					$("#result2").empty();
					$("#result1").append(0);
					$("#result2").append(a);
				}else{
					$("#result1").empty();
					$("#result2").empty();
					$("#result1").append(0);
					$("#result2").append(0);
				}
				
			}
		
		//临时保存
		function save1(){
			var size = $(".stockTr").size();
			
			if(size <= 0){
				alert("无库存");
				return false;
			}else{
				$("#inputForm").attr("action","${ctx}/pd/pdProductStockCheck/save?flag=0");
				return true;
			}
		}
		//盘点完成
		function save2(){
			var size = $(".stockTr").size();
			
			if(size <= 0){
				alert("无库存");
				return false;
			}else{
			$("#inputForm").attr("action","${ctx}/pd/pdProductStockCheck/save?flag=1");
			return true;
			}
		}
		
		
		
		
		//模糊查询的请求数据
		 function showDataDim(){
				var storeroomId = $("#repoId").val();
				 $.ajax({
						type:"POST",
						url:"${ctx}/pd/pdProductStockCheck/findStockByStoreroomId",
						dataType:"json",
						data:{
							storeroomId:storeroomId,
							name:$("#name").val(),
							number:$("#number").val(),
							spec:$("#spec").val(),
							version:$("#version").val(),
							batchNo:$("[name=batchNo]").val(),
							validDate:$("[name=validDate]").val()
							},
						success:function(data){
							showTable(data);
						}
					})
			}
		//加载数据列表
			function showTable(data){
				$("#addContentTab>tbody").empty();
				var data=eval(data);
				var html='';
				var pageHtml='';
				var stockSum = 0;//库存总量
				for(var i=0;i<data.length;i++){
					var date = new Date(data[i].pdProductStock.validDate);
					var dateFormat = date.getFullYear()+'-'+date.getMonth()+'-'+date.getDate();
					html+= '<tr>'+
					'<td>'+data[i].pdProduct.number+'</td>'+
					'<td>'+data[i].pdProduct.name+'</td>'+
					'<td>'+data[i].pdProduct.categoryName+'</td>'+
					'<td>'+data[i].pdProduct.groupName+'</td>'+
					'<td>'+data[i].pdProduct.unit+'</td>'+
					'<td>'+data[i].pdProduct.spec+'</td>'+
					'<td>'+data[i].pdProduct.version+'</td>'+
					'<td>'+data[i].pdProductStock.batchNo+'</td>'+
					'<td>'+dateFormat+'</td>'+
					'<td>'+data[i].pdProduct.venderName+'</td>'+
					'<td><input type="button" class="hcy-btn hcy-btn-o" value="选中" onclick="getStock(\''+data[i].pdProductStock.id+'\',this)" /></td>'+
					'</tr>';
					stockSum += data[i].pdProductStock.stockNum;
				}
				$("#checkNum").text(stockSum);
				$("#shouldCount").val(stockSum);
				$("#addContentTab>tbody").append(html);
			}
		
		
	</script>
</body>
</html>