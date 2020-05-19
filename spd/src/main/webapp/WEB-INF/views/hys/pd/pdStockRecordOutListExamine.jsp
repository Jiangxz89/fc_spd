<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出库审核</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<script type="text/javascript">
		$(document).ready(function() {
			
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>出库审核列表</h4>			
		</div>
		<div class="newSearchBox">
			<form:form id="searchForm" modelAttribute="pdStockRecord" action="${ctx}/pd/pdStockRecord/outExamineList" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<label>出库库房</label>
				<form:input path="outName" htmlEscape="false" value="${fns:getUser().storeroomName}" readOnly="true" maxlength="64" class="input-medium"/>
				<input id="outId" name="outId" type="hidden" value="${fns:getUser().storeroomId}"/>
				<label>出库单号</label>
				<form:input path="recordNo" autocomplete="off" htmlEscape="false" maxlength="64" class="input-medium"/>
				<label>出库日期</label>
				<input name="queryDate" autocomplete="off" style="width:160px;" type="text" readonly="readonly" maxlength="20" id="inHomeTime" class="input-medium Wdate" value="${pdStockRecord.queryDate}"/>
				<label>产品名称</label>
				<%--<form:input path="productName" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
				<input class="select2_material form-control" style="width: 260px" id="productNameSelect" name="productName" >
				</br>
				<label>状态 </label>
				<form:select path="recordState" class="input-medium" >
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('stock_record_state') }" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<label style="margin-left:5px;" for="">入库库房</label>
				<form:select path="inId" class="input-medium">
					<form:option value="" label="全部"/>
					<c:forEach var="storeroom" items="${fns:findStoreroomList()}">
						<c:if test="${storeroom.id!=fns:getUser().storeroomId}">
							<option value="${storeroom.id }" <c:if test="${pdStockRecord.inId eq storeroom.id}">selected</c:if>>${storeroom.storeroomName }</option>
						</c:if>
					</c:forEach>
				</form:select>
				<label style="margin-left:5px;" >出库类型 </label>
				<form:select path="outType" style="width:160px;" class="input-xlarge required">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('stock_out_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<label style="margin-left:20px;" >操作人</label>
				<form:select path="recordPeople" class="input-medium" >
					<form:option value="" label="全部"/>
					<form:options items="${spd:findRoomAdmins(fns:getUser().storeroomId)}" itemLabel="adminName" itemValue="adminName" htmlEscape="false"/>
				</form:select>
				<input id="btnSubmit" onclick="return page();" class="hcy-btn hcy-search" style="height: inherit;line-height:1.5;" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</form:form>
	</div>			
	<sys:message content="${message}"/>
	<div class="tableBox">
		<table id="contentTable" class="hcy-public-table" style="padding:0;">
			<thead>
				<tr>
					<th>出库单号</th>
					<th>出库日期</th>
					<th>出库库房 </th>
					<th>入库库房 </th>
					<th>出库类型 </th>
					<th>操作人</th>
					<th>备注 </th>
					<th>状态 </th>
					<shiro:hasPermission name="pd:pdStockRecord:edit"><th>操作</th></shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="pdStockRecord">
				<tr>
					<td>
						<a  title="${pdStockRecord.recordNo}" href="${ctx}/pd/pdStockRecord/pdStockRecordDetail?id=${pdStockRecord.id}&formType=1">${pdStockRecord.recordNo}</a>
					</td>
					<td>
						<fmt:formatDate value="${pdStockRecord.recordDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						${spd:getStoreroomName(pdStockRecord.outId)}
					</td>
					<td>
						<a  title="${spd:getStoreroomName(pdStockRecord.inId)}" href="###">${spd:getStoreroomName(pdStockRecord.inId)}</a>
					</td>
					<td>
						${fns:getDictLabel(pdStockRecord.outType, 'stock_out_type', '')}
					</td>
					<td>
						${pdStockRecord.recordPeople}
					</td>
					<td>
						<a href="###" class="overLook" title="${pdStockRecord.remarks}">${pdStockRecord.remarks}</a>
					</td>
					<td>
						${fns:getDictLabel(pdStockRecord.recordState, 'stock_record_state', '')}
					</td>
					<td>
						<c:if test="${pdStockRecord.recordState==0}"><a class="hcy-btn hcy-btn-o" href="${ctx}/pd/pdStockRecord/pdStockRecordDetail?recordState=${pdStockRecord.recordState}&&id=${pdStockRecord.id}&&isOut=1">出库确认</a></c:if>
						<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/pdStockRecord/pdStockRecordDetail?id=${pdStockRecord.id}">查看</a>			
					</td>	
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="pagination">${page}</div>
	</div>
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
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

			//申购时间
			laydate.render({
				elem: '#inHomeTime',
				range: true
			});
			/*//重置
			$(".hcy-reset").click(function(){
				var sel ="${fns:getUser().storeroomId}";//默认下拉
				var inp ="${fns:getUser().storeroomName}";//默认input
				$(".searchBox div input[type='text']").val("");
				$("#outName").val(inp);
				$(".searchBox div select").val("");
			})*/
			//重置
			$(".hcy-reset").click(function(){
				$(".newSearchBox input[type='text']").val("");
				var inp ="${fns:getUser().storeroomName}";//默认input
				$("#outName").val(inp);
				$("#productNameSelect").val("")
				$("#productNameSelect").select2("data", "");
				$("#recordState").select2("val", "");
				$("#inId").select2("val", "");
				$("#outType").select2("val", "");
				$("#recordPeople").select2("val", "");
			})
		});
	</script>
</body>
</html>