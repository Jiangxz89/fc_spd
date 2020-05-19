<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存记录管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<style>
		.numberWARAP{width:100%;height:60px;line-height:60px;margin:20px 0;}
		.numberWARAP>div{float:left;width:33%;height:60px;line-height:60px;color:#666;font-size:16px;text-align:center;border-right:1px solid #ccc;}
		.numberWARAP>div:nth-child(3){border:none;}
		.layui-layer-content{padding:0 15px;}
		.changeColor .red td,.changeColor .red td a{color: red}	
		.alert_close_btn:hover{color:#fff;border-bottom:1px solid #dedede}
	</style>
	<script type="text/javascript" src="${ctxStatic}/common/jeesite.js"></script>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
				<h4>库存管理</h4>
		</div>		
		<div class="numberWARAP">
			<div class="total">总数量：<span id="totalNum">${pCount}</span></div>
			<div class="nearTime">近效期数量：<span id="nearNum">${jCount}</span></div>
			<div class="overTime">过期数量：<span id="overNum">${gCount}</span></div>
		</div>
		<div class="newSearchBox">
			<form:form id="searchForm"  modelAttribute="pdProductStockTotal" action="${ctx}/pd/pdProductStockTotal/queryPdStock" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<input id="isSel" name="isSel" type="hidden">
				<label>库房</label>
				<c:choose>
					<c:when test="${fns:getUser().storeroomType==1}">
						<form:input path="" htmlEscape="false" value="${fns:getUser().storeroomName}" readOnly="true" maxlength="64" class="input-medium"/>
					</c:when>
					<c:when test="${fns:getUser().storeroomType==0||empty fns:getUser().storeroomType}">
						<select name="storeroomId" class="input-medium">
						<option value="0" label="全部"/>
						<c:forEach items="${storeroomList}" var="storeRoom">
							<option value="${storeRoom.id}" <c:if test='${storeRoom.id==storeroomId}'>selected="selected"</c:if>>${storeRoom.storeroomName }</option>
						</c:forEach>
					</select>
					</c:when>
				</c:choose>
				<label>产品名称</label>
				<%--<form:input path="pdProduct.name" htmlEscape="false" maxlength="64" />--%>
				<input class="select2_material form-control" style="width: 260px" id="productNameSelect" name="pdProduct.name" >

				<label style="margin-left:5px;">产品编号</label>
				<form:input path="pdProduct.number" autocomplete="off" htmlEscape="false" maxlength="64" />
				<br/>
				<label style="" for="">产品分类</label> <!--add by jiangxz 20190903 -->
				<form:select id="category" path="categoryStr" class="input-medium" cssStyle="width: 300px" multiple="true">
					<%--<form:option value="" label="全部"/>--%>
					<form:options items="${spd:findPdCategoryList()}" itemLabel="name" itemValue="id" htmlEscape="false" />
				</form:select>
				<label for="">产品组别</label>  <!--add by jiangxz 20190903 -->
				<form:select id="group" path="groupStr" class="input-medium" cssStyle="width: 300px"  multiple="true" >
					<%--<form:option value="" label="全部"/>--%>
					<form:options items="${spd:findPdGroupList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<input id="btnSubmit" onclick="return page();" class="hcy-btn hcy-search" style="height: inherit;line-height:1.5;" type="button" value="查询"/><br>
				<a href="###" id="batchDown"  class="hcy-btn hcy-btn-primary" style="line-height:1.5;margin:5px 6px 0 0;">批量设置库存下限</a>
				<a href="###" id="batchUp" class="hcy-btn hcy-btn-primary" style="line-height:1.5;margin:5px 6px 0 0;">批量设置库存上限</a>
				<a href="###" id="btnExport" class="hcy-btn hcy-btn-primary" style="line-height:1.5;margin:5px 6px 0 0;">导出Excel</a>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table changeColor">
				<thead>
					<tr>
						<th><input type="checkbox" id="allchoose" /></th>
						<th>产品名称</th>
						<th>产品编号</th>
						<th>规格</th>
						<th>型号</th>
						<th>单位</th>						
						<th>库存上限</th>
						<th>库存下限</th>
						<th>数量</th>
						<th>是否过期</th>
						<th>是否久存</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="pdProductStockTotal">	
						<c:if test="${not empty pdProductStockTotal.expire}">
						<c:choose>
							<c:when test="${pdProductStockTotal.expire==1||pdProductStockTotal.expire==0}"><tr class="red"></c:when>							
						</c:choose>
						</c:if>
						<c:if test="${empty pdProductStockTotal.expire}"><tr></c:if>
					
						<td><input type="checkbox" /><input class="sid" type="hidden" value="${pdProductStockTotal.storeroomId}"><input class="pid" type="hidden" value="${pdProductStockTotal.productId}"></td>
						<td>
							<a href="${ctx}/pd/pdProductStock/queryPdStockDetail?productId=${pdProductStockTotal.productId}&&storeroomId=${pdProductStockTotal.storeroomId}" title="${pdProductStockTotal.pdProduct.name}">${pdProductStockTotal.pdProduct.name}</a>
						</td>
						<td>
							${pdProductStockTotal.pdProduct.number }
						</td>
						<td>
							${pdProductStockTotal.pdProduct.spec}
						</td>
						<td>
							${pdProductStockTotal.version}
						</td>
						
						<td>${pdProductStockTotal.pdProduct.unit}</td>						
						
						<td class="upNum">${pdProductStockTotal.limitUp}</td>
						<td class="downNum">${pdProductStockTotal.limitDown}</td>
						<td>${pdProductStockTotal.stockNum}</td>												
						<td>${fns:getDictLabel(pdProductStockTotal.expire, 'pd_state', '--')}</td>
						<td>${fns:getDictLabel(pdProductStockTotal.isLong, 'is_long', '--')}</td>
						<td>
							<a href="${ctx}/pd/pdProductStock/queryPdStockDetail?productId=${pdProductStockTotal.productId}&&storeroomId=${pdProductStockTotal.storeroomId}" class="hcy-btn hcy-btn-o detailTrBtn" data-pid="${pdProductStockTotal.productId}">库存明细</a>
							<a href="${ctx}/pd/pdProductStock/stockInAndOutRecordDetailQuery?productId=${pdProductStockTotal.productId}&&storeroomId=${pdProductStockTotal.storeroomId}" class="hcy-btn hcy-btn-o detailTrBtn" data-pid="${pdProductStockTotal.productId}">出入库明细</a>
						</td>
					</tr>					
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
		<div class="addChargeCodeBox">
			<h4 style="color:#666;font-size:13px;">冠状动脉球囊扩张导管等11个产品</h4>
			<input style="width:100%;height:30px;border:1px solid #ccc;" type="text" class="addChargeCodeInp valNum"/>
		</div>
		<div class="detailBox" style="display:none;">
			<table class="detailTrTab hcy-public-table">
				<thead>
					<tr>
						<th>产品编号</th>
						<th>条码</th>
						<th>批次</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>2223</td>
						<td>1009666</td>
						<td>5000</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 提示 -->
	<div class="bigShadow" style="display: none">
		<div class="alertBox" style="display: none">
			<div class="alert_title">提示</div>
			<div class="alert_content">
				<ul>
					<li>库存中过期产品数量：${gCount}</li>
					<li>近效期产品数量：${jCount}</li>
					<li>超出库房上下限产品数量：${limtCount}</li>
					<li>久存产品数量：${isLcount}</li>
				</ul>
			</div>
			<div class="alert_btnBox" >
				<a class="alert_close_btn">确定</a>
			</div>
		</div>
	</div>
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/common/jeesite.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

            var select2 = function () {
                $("#productNameSelect").select2({
                    dropdownParent: $('.modal-content'),
                    /*placeholder: "产品名称",*/
                    allowClear: true,
                    ajax: {
                        url: '${ctx}/pd/pdProduct/findList',
                        dataType: 'json',
                        data: function (params) {
                            var query = {
                                name: params //params.term 搜索参数值
                            }
                            return query;
                        },
                        results: function (data) {
                            //返回最终数据data 给dataArray
                            var dataArray = [];
                            for (var i = 0; i < data.length; i++) {
                                var dataObj = {};
                                dataObj.id = data[i].name;
                                dataObj.text = data[i].name ;
                                dataArray.push(dataObj);
                            }
                            return {
                                results: dataArray
                            };
                        },
                        error: function (error) {
                        }
                    }
                });
            }
            select2();

			var isP='${isSel}';
			if(!isP){
				queryAlert();
			}			
			function queryAlert(){				
				var isNone='${isNone}';
				if(isNone=='1'){
					$(".bigShadow").show();
					$(".alertBox").show();
				}
			}
			$(".alert_close_btn").click(function(){
				$(".bigShadow").hide();
				$(".alertBox").hide();
			})
			$("#btnExport").click(function(){
				$("#searchForm").attr("action","${ctx}/pd/pdProductStockTotal/export");
				$("#searchForm").submit();
				$("#searchForm").attr("action","${ctx}/pd/pdProductStockTotal/queryPdStock");				
			});
			//全选与反选
			$("#allchoose").click(function(){
				if($(this).attr('checked')){
					$("input[type='checkbox']").attr('checked','true');
				}else{
					$("input[type='checkbox']").removeAttr('checked');
				}
			});


			//Add by jiangxz 20190904 用于点击查询按钮后 产品分类和产品组别回显
			var categoryStr = '${categoryStr}';
			var groupStr = '${groupStr}';
			if(categoryStr != null || categoryStr != ''){
				var categoryArr = categoryStr.split(",");
				$("#category").val(categoryArr).trigger("change");
			}

			if(groupStr != null || groupStr != ''){
				var groupArr = groupStr.split(",");
				$("#group").val(groupArr).trigger("change");
			}


		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#isSel").val(0);
			$("#searchForm").submit();
        	return false;
        }		
	</script>
	<script type="text/javascript">		
		$("#btnSubmit").click(function(){				
			$("#searchForm").submit();				
		})
		
		$("#batchUp").click(function(){
			var checkNum=$(".hcy-public-table>tbody>tr input[type='checkbox']:checked").length;
			if(checkNum==0){
				layer.alert("请选择产品！",{icon:1},function(index){
					layer.close(index);
				});
			}else{				
				$(".hcy-public-table>tbody>tr input[type='checkbox']:checked").each(function(){
					
					checkText=$(this).parent().next().find("a").text();
					$(".addChargeCodeBox>h4").text(checkText+"等"+checkNum+"个产品");
				})
				layer.open({
						type:1,
						title:"批量设置库存上限",
						content:$(".addChargeCodeBox").html(),
						area:["400px","300px"],
						shade: [0.8, '#393D49'],
						btn:["确定","取消"],
						yes:function(index,layero){							
							var val=$.trim(layero.find(".addChargeCodeInp").val());
							if(val){
								//var reg = /^[0-9]+$/;
								if(isNumber(val)){
									var ids = '';
									var ind = '';
									var arr=[];
									$(".hcy-public-table>tbody>tr input[type='checkbox']:checked").each(function(){										
										ind=$(this).parents("tr").index();
										$(".hcy-public-table>tbody>tr").eq(ind).find(".upNum").text(val);
										//ids += $(this).next().val()+',';	
										var obj={};
										var storeroomId=$(this).parent().parent().find("input[class*='sid']").val();
										var productId=$(this).parent().parent().find("input[class*='pid']").val();
										obj.storeroomId=storeroomId;
										obj.productId=productId;
										arr.push(obj);
									})									
									$.ajax({
										url:'${ctx}/pd/pdProductStockTotal/updateProductStock',
										type:'post',
										data:{"ids":JSON.stringify(arr),"upNum":val},
										success:function(data){	
											if(data.code=="200"){
												location.href="${ctx}/pd/pdProductStockTotal/queryPdStock"
											}
										}
									})
									//window.location = "${ctx}/pd/pdProductStockTotal/updateProductStock?ids="+JSON.stringify(arr)+"&upNum="+val+"";
									layer.closeAll();	
								}else{
									layer.alert('请输入正整数',{icon:0})
								}								
							}else{
								layer.alert('请输入批量上限数目',{icon:0})
							}
							
						},
						btn2:function(){
							layer.closeAll();
						}
					})				
			}			
		})
		$("#batchDown").click(function(){
			var checkText;
			var checkNum=$(".hcy-public-table>tbody>tr input[type='checkbox']:checked").length;
			if(checkNum==0){
				layer.alert("请选择产品！",{icon:1},function(index){
					layer.close(index);
				});
			}else{
				$(".hcy-public-table>tbody>tr input[type='checkbox']:checked").each(function(){
					checkText=$(this).parent().next().find("a").text();
					$(".addChargeCodeBox>h4").text(checkText+"等"+checkNum+"个产品");
				})
				layer.open({
						type:1,
						title:"批量设置库存下限",
						content:$(".addChargeCodeBox").html(),
						area:["400px","300px"],
						shade: [0.8, '#393D49'],
						btn:["确定","取消"],
						yes:function(index,layero){							
							var val=$.trim(layero.find(".addChargeCodeInp").val());
							if(val){
								//var reg = /^[0-9]+$/;
								if(isNumber(val)){
									var ids = '';
									var ind = '';
									var arr=[];
									$(".hcy-public-table>tbody>tr input[type='checkbox']:checked").each(function(){
										ind=$(this).parents("tr").index();
										$(".hcy-public-table>tbody>tr").eq(ind).find(".downNum").text(val);
										//ids += $(this).next().val()+',';	
										var obj={};
										var storeroomId=$(this).parent().parent().find("input[class*='sid']").val();
										var productId=$(this).parent().parent().find("input[class*='pid']").val();
										obj.storeroomId=storeroomId;
										obj.productId=productId;
										arr.push(obj);
									})									
									$.ajax({
										url:'${ctx}/pd/pdProductStockTotal/updateProductStock',
										type:'post',
										data:{"ids":JSON.stringify(arr),"downNum":val},
										success:function(data){	
											if(data.code=="200"){
												location.href="${ctx}/pd/pdProductStockTotal/queryPdStock"
											}
										}
									})
									//window.location = "${ctx}/pd/pdProductStockTotal/updateProductStock?ids="+JSON.stringify(arr)+"&downNum="+val+"";
									layer.closeAll();
								}else{
									layer.alert('请输入正整数',{icon:0})
								}								
							}else{
								layer.alert('请输入批量上限数目',{icon:0})
							}							
						},
						btn2:function(){
							layer.closeAll();
						}
					})				
			}			
		})
	</script>
</body>
</html>