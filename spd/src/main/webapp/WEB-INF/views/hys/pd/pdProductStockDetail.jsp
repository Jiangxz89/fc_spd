<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存记录管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<style type="text/css">
	.numberWARAP{width:100%;height:60px;line-height:60px;margin:20px 0;}
	.numberWARAP>div{float:left;width:33%;height:60px;line-height:60px;color:#666;font-size:16px;text-align:center;border-right:1px solid #ccc;}
	.numberWARAP>div:nth-child(3){border:none;}	
	.changeColor .red td,.changeColor .red td a{color: red}	
	</style>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
				<h4>库存明细</h4>
		</div>
		<div class="numberWARAP">
			<div class="total">总数量：<span id="totalNum">${pdCount}</span></div>
			<div class="nearTime">近效期数量：<span id="nearNum">${jCount}</span></div>
			<div class="overTime">过期数量：<span id="overNum">${gCount}</span></div>
		</div>
		<div class="newSearchBox">
			<form:form id="searchForm"  modelAttribute="pdProductStock" action="${ctx}/pd/pdProductStock/queryPdStockDetail" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<input id="isSel" type="hidden" name="isSel" value="${pdProductStock.isSel}"/>
				<form:hidden path="storeroomId"/>
				<form:hidden path="productId"/>
				<label>库房</label>
				<form:input path="" htmlEscape="false"  maxlength="64" value="${fns:getUser().storeroomName}" disabled="true"/>
				<%--<label>生产厂家</label>
				<form:select path="pdProduct.venderName" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${spd:findPdVenderList() }" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>--%>
				<label for="" >生产厂家</label>
				<form:select path="pdProduct.venderName" class="input-medium" style="width: 260px;">
					<form:option value="" label="全部(qb)"/>
					<form:options items="${spd:findPdVenderList() }" itemLabel="nameAndpinyin" itemValue="id" htmlEscape="false"/>
				</form:select>
				<label>产品批号</label>
				<form:input path="batchNo" autocomplete="off" htmlEscape="false" maxlength="64" />
				<label>有效期</label>
				<input name="queryDate" style="width:160px;" autocomplete="off" type="text" readonly="readonly" maxlength="20" id="validDate" class="input-medium Wdate" value="${pdProductStock.queryDate}"/>
				<input type="hidden" id="startDate" name="startDate"><input type="hidden" id="endDate" name="endDate">
				<input id="btnSubmit" onclick="return page();"  class="hcy-btn hcy-search" style="height: inherit;line-height:1.5;" type="button" value="查询"/><br>
				<!-- <a href="###" id="batchDown"  class="hcy-btn hcy-btn-primary" style="line-height:1.5;float:right;margin:5px 6px 0 0;">批量设置库存下限</a>
				<a href="###" id="batchUp" class="hcy-btn hcy-btn-primary" style="line-height:1.5;float:right;margin:5px 6px 0 0;">批量设置库存上限</a> -->
				<a href="###" id="btnExport" class="hcy-btn hcy-btn-primary" style="line-height:1.5;margin:5px 6px 0 0;">导出Excel</a>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table changeColor">
				<thead>
					<tr>
						<!-- <th><input type="checkbox" id="allchoose" /></th> -->
						<!-- <th>库房名称</th> -->
						<th>产品名称</th>
						<th>产品编号</th>
						<th>产品条码</th>
						<th>规格</th>
						<th>型号</th>
						<th>批号</th>
						<th>有效期</th>
						<th>单位</th>
						<th>生产厂家</th>
						<th>注册证号</th>
						<th>数量</th>
						<%--<th>入库单价</th>--%>
						<%--<th>金额</th>--%>
						<th>是否过期</th>
						<th>是否久存</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="pdProductStock">
						<c:if test="${not empty pdProductStock.pdState}">
						<c:choose>
							<c:when test="${pdProductStock.pdState==1||pdProductStock.pdState==0}"><tr class="red"></c:when>							
						</c:choose>
						</c:if>
						<c:if test="${empty pdProductStock.pdState}"><tr></c:if>
								<td>
								${pdProductStock.pdProduct.name}
								</td>
								<td>
									${pdProductStock.pdProduct.number}
								</td>
								<td>
									${pdProductStock.productBarCode }
								</td>
								<td>
									${pdProductStock.pdProduct.spec}
								</td>
								<td>
									${pdProductStock.pdProduct.version}
								</td>
								<td>${pdProductStock.batchNo}</td>
								<td><fmt:formatDate value="${pdProductStock.validDate}"/></td>								
								<td>${pdProductStock.pdProduct.unit}</td>
								<td>
									${pdProductStock.pdProduct.venderName}
								</td>
								<td>${pdProductStock.pdProduct.registration}</td>
								<td>${pdProductStock.stockNum}</td>
								<%--<td>${pdProductStock.inPrice}</td>--%>
								<%--<td>${pdProductStock.pdTotalPrice}</td>--%>
								<td>${fns:getDictLabel(pdProductStock.pdState, 'pd_state', '--')}</td>
								<td>${fns:getDictLabel(pdProductStock.isLong, 'is_long', '--')}</td>																	
							</tr>															
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
		<div class="addChargeCodeBox">
			<h4>冠状动脉球囊扩张导管等11个产品</h4>
			<input type="text" class="addChargeCodeInp"/>		
		</div>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
			<a href="${ctx}/pd/pdProductStockTotal/queryPdStock" class="hcy-btn hcy-back" >返回</a>
		</div>
	</div>
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			/* var isSel=$("#isSel").val();
			if(isSel!="0"){
				//debugger;
				autoPrompt();
			} */
			$("#btnExport").click(function(){
				$("#searchForm").attr("action","${ctx}/pd/pdProductStock/export");
				$("#searchForm").submit();
				$("#searchForm").attr("action","${ctx}/pd/pdProductStock/queryPdStockDetail");
				/* top.$.jBox.confirm("确认要导出库存数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/pd/pdProductStock/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/pd/pdProductStock/queryPdStockDetail");
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px'); */
			});
			//全选与反选
			/* $("#allchoose").click(function(){
				if($(this).attr('checked')){
					$("input[type='checkbox']").attr('checked','true');
				}else{
					$("input[type='checkbox']").removeAttr('checked');
				}
			}); */
			//日期范围
			laydate.render({
			  elem: '#validDate',
			  range: true
			});
			//提示
			if('${message}'){
				layer.alert('${message}',{icon:1});
			}
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<script type="text/javascript">
		$("#btnSubmit").click(function(){
						
			$("#searchForm").submit();
		});	
		
	</script>
</body>
</html>