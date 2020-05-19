<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库审核列表</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
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
			<h4>入库审核</h4>
		</div>
		<div class="newSearchBox">
			<form:form id="searchForm" modelAttribute="pdStockRecord" action="${ctx}/pd/pdStockRecord/inExamineList" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<label>入库库房</label>
				<!--<form:input path="inId" htmlEscape="false" maxlength="64" class="input-medium"/> -->
				<form:input path="inName" htmlEscape="false" value="${fns:getUser().storeroomName}" readOnly="true" class="input-medium"/>
				<label>入库单号</label>
				<form:input path="recordNo" autocomplete="off" htmlEscape="false" maxlength="64" class="input-medium"/>
				<label>入库日期</label>
				<%--<input name="recordDate" autocomplete="off" type="text" style="width:160px;" readonly="readonly" maxlength="20" id="inWareHouseTime" class="input-medium Wdate" value="<fmt:formatDate value="${pdStockRecord.recordDate}" />"/>--%>
				<form:input path="queryDate" autocomplete="off" htmlEscape="false" id="inWareHouseTime" style="width:160px;" readonly="readonly" maxlength="64" class="input-medium"/>
				<label>产品名称</label>
				<%--<form:input path="productName" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
				<input class="select2_material form-control" style="width: 260px" id="productNameSelect" name="productName" >
				</br>
				<label>状态 </label>
				<form:select path="recordState" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('stock_record_state') }" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<label style="margin-left:5px;">出库方</label>
				<c:choose>
					<c:when test="${fns:getUser().storeroomType == '0'}">
						<form:select path="outId" class="input-medium" >
							<form:option value="" label="全部"/>
							<c:forEach var="storeroom" items="${spd:findSuperStoroomList()}">
								<c:if test="${storeroom.id!=fns:getUser().storeroomId}">
									<option value="${storeroom.id }" <c:if test="${pdStockRecord.outId eq storeroom.id}">selected</c:if>>${storeroom.storeroomName }</option>
								</c:if>
							</c:forEach>
						</form:select>
					</c:when>
					<c:otherwise>
						<form:select path="outId" class="input-medium" >
							<form:option value="" label="全部"/>
							<c:forEach var="storeroom" items="${spd:findStoreroomList()}">
								<c:if test="${storeroom.id!=fns:getUser().storeroomId}">
									<option value="${storeroom.id }" <c:if test="${pdStockRecord.outId eq storeroom.id}">selected</c:if>>${storeroom.storeroomName }</option>
								</c:if>
							</c:forEach>
						</form:select>
					</c:otherwise>
				</c:choose>
				<label style="margin-left:5px;">入库类型 </label>
				<form:select path="inType"  cssStyle="width: 160px" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('stock_in_type') }" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<label style="margin-left:5px;">操作人</label>
					<form:select path="recordPeople" class="input-medium" >
						<form:option value="" label="全部"/>
						<form:options items="${spd:findRoomAdmins(fns:getUser().storeroomId)}" itemLabel="adminName" itemValue="adminName" htmlEscape="false"/>
					</form:select>
				<!-- <form:input path="recordPeople" htmlEscape="false" maxlength="64" class="input-medium"/> -->
				<input id="btnSubmit" onclick="return page();" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th><input type="checkbox" id="allCheck" /></th>
						<th>入库单号</th>
						<th>入库日期</th>
						<th>出库方 </th>
						<th>入库库房 </th>
						<th>入库类型 </th>
						<th>操作人员</th>
						<th>备注 </th>
						<th>状态 </th>
						<shiro:hasPermission name="pd:pdStockRecord:edit"><th>操作</th></shiro:hasPermission>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="pdStockRecord">
					<tr>
						<td><input type="checkbox"/></td>
						<td><a href="${ctx}/pd/pdStockRecord/viewIn?id=${pdStockRecord.id}&formType=2">
							${pdStockRecord.recordNo}
						</a></td>
						<td>
							<fmt:formatDate value="${pdStockRecord.recordDate}" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							<c:choose>
							<c:when test="${pdStockRecord.inType=='0' and fns:getUser().storeroomType == '0'}">
								${spd:getSupplierName(pdStockRecord.supplierId)}
							</c:when>
							<c:otherwise>
								${spd:getStoreroomName(pdStockRecord.outId)}
							</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a class="overLook" title="${spd:getStoreroomName(pdStockRecord.inId)}" href="###">${spd:getStoreroomName(pdStockRecord.inId)}</a>
						</td>
						<td>
							${fns:getDictLabel(pdStockRecord.inType, 'pd_inType', '')}
						</td>
						<td>
							${pdStockRecord.recordPeople}
						</td>
						<td>
							<a href="###" title="${pdStockRecord.remarks }" style="width:100px;" class="overLook">${pdStockRecord.remarks }</a>
						</td>
						<td>
							${fns:getDictLabel(pdStockRecord.recordState, 'stock_record_state', '')}
						</td>
						<shiro:hasPermission name="pd:pdStockRecord:view"><td>
							<c:if test="${pdStockRecord.recordState == 0}">
								<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/pdStockRecord/formIn?id=${pdStockRecord.id}&formType=2">审核</a>
							</c:if>
							<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/pdStockRecord/viewIn?id=${pdStockRecord.id}&formType=2">查看</a>
						</td></shiro:hasPermission>
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
		$(function(){

            //全选
            $("#allCheck").click(function(){
                if($(this).prop("checked")){
                    $(".hcy-public-table>tbody>tr").find("input[type='checkbox']").prop("checked",true);
                }else{
                    $(".hcy-public-table>tbody>tr").find("input[type='checkbox']").prop("checked",false);
                }
            });

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
				elem: '#inWareHouseTime',
				range: true
			});
			/*//重置
			$(".hcy-reset").click(function(){
				var sel ="${fns:getUser().storeroomId}";//默认下拉
				var inp ="${fns:getUser().storeroomName}";//默认input
				$(".searchBox div input[type='text']").val("");
				$("#inName").val(inp);
				$(".searchBox div select").val("");
			})*/
            //重置
            $(".hcy-reset").click(function(){
                $(".newSearchBox input[type='text']").val("");
                var inp ="${fns:getUser().storeroomName}";//默认input
                $("#inName").val(inp);
                $("#productNameSelect").val("")
                $("#productNameSelect").select2("data", "");
                $("#recordState").select2("val", "");
                $("#outId").select2("val", "");
                $("#inType").select2("val", "");
                $("#recordPeople").select2("val", "");
            })
		})
	</script>
</body>
</html>