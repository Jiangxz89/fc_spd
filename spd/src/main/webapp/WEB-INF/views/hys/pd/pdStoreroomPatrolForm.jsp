<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库记录管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<style>
		.condotionBox{padding:10px 0;}
		.condotionBox>label{display: inline-block;width: 40px;text-align: left;font-size: 12px;color: #666;}
		.condotionBox>input[type='text']{padding:0 0 0 5px;margin:0 5px 0 0;width:60px;height:30px;border-radius:1px;vertical-align:middle;box-shadow:none;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
	</script>
	<!-- html模板块 -->
    <script type="text/x-jquery-tmpl" id="temp_0">  
            <tr>
						<td>{%= storeroomName%}</td>
						<td>{%= pdProduct.name%}</td>
						<td>{%= pdProduct.number%}</td>
						<td>{%= productBarCode%}</td>
						<td>{%= pdProduct.spec%}</td>
						<td>{%= pdProduct.version%}</td>
						<td>{%= batchNo%}</td>
						<td>{%= validDate%}</td>
						<td>{%= stockNum%}</td>
						<td>{%= pdProduct.unit%}</td>
						<td><a href="###" class="overLook" title="{%= pdProduct.venderName%}"</a>{%= pdProduct.venderName%}</a></td>
						<td><a href="###" class="overLook" title="{%= pdProduct.registration%}"</a>{%= pdProduct.registration%}</a></td>
						{%if pdState=='0'%}
							<td>即将过期</td>
						{%elif pdState=='1'%}
							<td>过期</td>
						{%else%}
							<td>--</td>
						{%/if%}
						<td data-id="{%= id%}" data-prodid="{%= productId%}" data-batchno="{%= batchNo%}" data-barcode="{%= productBarCode%}" data-stocknum="{%= stockNum%}">
							<select name="patrolResult" style="width:100px;">
								<option value="0">合格</option>
								<option value="1">不合格</option>
								<option value="2">其他</option>
							</select>
						</td>
						<td data-id="{%= id%}" data-prodid="{%= productId%}" data-batchno="{%= batchNo%}" data-barcode="{%= productBarCode%}" data-stocknum="{%= stockNum%}">
							<input type='text'style="width:100px;" name="remarks"/>
						</td>
			</tr>
    </script>
</head>
<body>
	<div class="right-main-box"> 
		<div class="btnBox">
			<h4>新增巡查</h4>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="pdProductStock" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>巡查日期</label>
					<input type="text" name="recordDate" value="<fmt:formatDate value='${patrolDate }' pattern='yyyy-MM-dd'/>" readonly="readonly" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<div>
					<label>库房</label>
					<c:set var="user" value="${fns:getUser() }"/>
					<form:select path="storeroomId">
						<c:if test="${user.storeroomType=='0' }">
							<form:option value="">请选择</form:option>
							<form:options items="${spd:findStoreroomList()}" itemValue="id" itemLabel="storeroomName"/>
						</c:if>
						<c:if test="${user.storeroomType=='1' }">
							<form:option value="${user.storeroomId }">${user.storeroomName }</form:option>
						</c:if>
					</form:select>
				</div>
				<div>
					<label>产品名称</label>
					<form:input path="pdProduct.name" maxlength="20" />
				</div>
				<div>
					<label>有效期</label>
					<form:input path="validDate" style="width:160px;" id="effectivTime" readonly="readonly" maxlength="20" />
					<input type="hidden" name="startDate" id="startDate"/>
					<input type="hidden" name="endDate" id="endDate"/>
				</div>
				<div>
					<label>产品编号</label>
					<form:input path="pdProduct.number" maxlength="64" />
				</div>
				<div>
					<label>产品批号</label>
					<form:input path="batchNo" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<a href="###" class="hcy-btn hcy-search" onclick="queryData();">查询</a>
				<a href="###" class="hcy-btn hcy-reset" onclick="resetParams();">重置</a>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table">
				<thead>
					<tr>
						<th>库房名称</th>
						<th>产品名称</th>
						<th>产品编号 </th>
						<th>产品条码 </th>
						<th>规格</th>
						<th>型号</th>
						<th>批号 </th>
						<th>有效期 </th>
						<th>数量 </th>
						<th>单位</th>
						<th>生产厂家 </th>
						<th>注册证号</th>
						<th>是否过期 </th>
						<th>检查结果</th>
						<th>备注 </th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="pagination"></div>
		<form id="myForm" action="${ctx}/pd/pdStoreroomPatrol/save" method="post" enctype="application/x-www-form-urlencoded" accept-charset="utf-8" >
			<div class="condotionBox">
				<label>温度</label><input type="text" name="temperature" id="temperature"/>℃
			</div>
			<div class="condotionBox">
				<label>湿度</label><input type="text" name="wetness" id="wetness"/>%
			</div>
			<input type="hidden" name="patrolRecord" id="patrolRecord"/>
			<input type="hidden" name="chooseRoomId" id="chooseRoomId"/>
			<div class="form-actions" style="background:#fff;border:none;text-align:center;padding:0;margin-top:30px;">
				<shiro:hasPermission name="pd:pdStockRecord:edit"><input id="btnSubmit" class="hcy-btn hcy-save" type="button" value="保 存"/>&nbsp;</shiro:hasPermission>
				<input id="btnCancel" class="hcy-btn hcy-back" type="button" value="返 回" onclick="location.href=document.referrer;"/>
			</div>
		</form>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.tmpl.js"></script>
	<script type="text/javascript">
		//定义json数据
		var jsonData = [];
		
		$(function(){
			//申购时间
			laydate.render({
				elem: '#effectivTime',
				range: true
			});
			//保存
			$('#btnSubmit').click(function(){
				if(jsonData.length < 1){
					layer.alert('请查询巡查的产品',{icon:0});
					return false;
				}
				//温度
				var wendu = $.trim($('#temperature').val());
				//湿度
				var shidu = $.trim($('#wetness').val());
				if (!wendu || !shidu) {
					layer.alert('请填写温度和湿度',{icon:0});
					return false;
				}
				//处理最后传递的json数据
				var myJsonArray = [];
				$.each(jsonData, function(i,v){
					for(var k in jsonData[i]){
						myJsonArray.push(jsonData[i][k]);
					}
				});
				$.ajax({
					url:'${ctx}/pd/pdStoreroomPatrol/savePatrol',
					type:'post',
					data:{"patrolRecord":JSON.stringify(myJsonArray),
						  "storeroomId":$('#chooseRoomId').val() || '${fns:getUser().storeroomId}',
						  "temperature":wendu,
						  "wetness":shidu
						},
					dataType:'json',
					//async:false,
					contentType: "application/x-www-form-urlencoded; charset=utf-8",
					success:function(data){
						if(data.code==200){
							layer.alert(data.info, {icon:1, closeBtn:0}, function(){
								window.location.href = '${ctx}'+data.uri;
							});
						}else{
							layer.alert('系统异常，稍后重试', {icon:2});
						}
					}
				});
			});
			//选择库房
			$('#storeroomId').change(function(){
				$('#chooseRoomId').val($(this).find('option:selected').val());
			});
		});
		//检查结果
		$(document).on('change',"select[name='patrolResult']",function(){
			updateInitData($(this), 1);
		});
		//检查结果-输入备注
		$(document).on('input propertychange',"input[name='remarks']",function(){
			updateInitData($(this), 2);
		});
		//及时更新初始化数据
		function updateInitData(obj,flag){
			var presult = null,
				remarks = null;
			if(flag==1){//下拉选择
				presult = obj.find('option:selected').val();
				remarks = obj.parent().next().find('input').val();
			}else if(flag==2){//input输入
				presult = obj.parent().prev().find('select option:selected').val();
				remarks = obj.val();
			}
			var cid = obj.parent().data('id'),
			    cobj = obj.parent(),
			    jsonObj = {},
			    dataModel = {"productId":cobj.data('prodid'), 
							 "batchNo":cobj.data('batchno'), 
							 "productBarCode":cobj.data('barcode'),
							 "stockNum":cobj.data('stocknum'),
							 "patrolResult":presult, 
							 "remarks":remarks};
			$.each(jsonData, function(i,v){
				for(var k in jsonData[i]){
					if(k==cid){
						jsonData.splice(i,1);
						return false;
					}
				}
			});
		    jsonObj[cid] = dataModel;
			jsonData.push(jsonObj);
			$('#patrolRecord').val(JSON.stringify(jsonData,null,4));
		}
		//查询数据
		function queryData(){
			var storeroomId = $('#storeroomId option:selected').val();
			if(!storeroomId){
				layer.alert('请选择库房',{icon:0});
				return false;
			}
			$('tbody').empty();
			$.ajax({
				url:'${ctx}/pd/pdProductStock/queryPatrolProduct',
				type:'post',
				data:$('#searchForm').serialize(),
				dataType:'json',
				success:function(data){
					getAllData(data);
					if(data.pageList && data.pageList.html)
						$('.pagination').html(data.pageList.html);
					if(data.pageList && data.pageList.list)
						$('#temp_0').tmpl(data.pageList.list).appendTo('tbody'); 
				}
			});
		}
		//获取全部列表数据（即包含分页的所有数据）
		function getAllData(data){
			jsonData = [];
			if(data && data.dataList){
				$.each(data.dataList, function(i,v){
					var temp = {};
					var dataModel = {"productId":v.productId, 
									 "batchNo":v.batchNo, 
									 "productBarCode":v.productBarCode,
									 "stockNum":v.stockNum,
									 "patrolResult":"0", 
									 "remarks":""};
					temp[v.id] = dataModel;
					jsonData.push(temp);
				});
				//alert(JSON.stringify(jsonData, null, 4))
			}
		}
		
		//清除条件
		function resetParams(){
			$('#searchForm select,#searchForm input:gt(2)').val('');
		}
		
		//分页
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			queryPageData();
        	return false;
        }
		//查询分页数据
		function queryPageData(){
			var storeroomId = $('#storeroomId option:selected').val();
			if(!storeroomId){
				layer.alert('请选择库房',{icon:0});
				return false;
			}
			$('tbody').empty();
			$.ajax({
				url:'${ctx}/pd/pdProductStock/queryPatrolProduct',
				type:'post',
				data:$('#searchForm').serialize(),
				dataType:'json',
				success:function(data){
					if(data.pageList && data.pageList.html)
						$('.pagination').html(data.pageList.html);
					if(data.pageList && data.pageList.list)
						$('#temp_0').tmpl(data.pageList.list).appendTo('tbody'); 
				}
			});
		}
	</script>
</body>
</html>