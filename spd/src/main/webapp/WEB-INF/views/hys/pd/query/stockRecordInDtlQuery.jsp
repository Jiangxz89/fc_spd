<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库明细查询</title>
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
			<h4>入库明细查询</h4>
			<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>
		</div>
		<form:form id="searchForm" modelAttribute="pdStockRecord" action="${ctx}/pd/pdStockRecord/pdStockRecordInDtlQuery" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<label>入库时间</label>
					<form:input path="queryDate" autocomplete="off" htmlEscape="false"  id="inHomeTime" style="width:165px;" readonly="readonly" maxlength="64" class="input-medium"/>
                    <label>库房</label>
                    <c:choose>
                        <c:when test="${user.storeroomType==1}">
                            <form:select path="storeroomId" class="input-medium">
                                <form:option value="${fns:getUser().storeroomId}" label="${fns:getUser().storeroomName}"/>
                            </form:select>
                        </c:when>
                        <c:otherwise>
                            <form:select path="storeroomId" class="input-medium">
                                <form:options items="${fns:findStoreroomList()}" itemLabel="storeroomName" itemValue="id" htmlEscape="false"/>
                            </form:select>
                        </c:otherwise>
                    </c:choose>
                    <label style="margin-left:5px;">产品编号</label>
                    <form:input path="number" autocomplete="off" value="${pdStockRecord.number}"/>
					<label>产品名称</label>
					<%--<form:input path="productName" htmlEscape="false" maxlength="64" class="input-medium" value="${pdStockRecord.productName}"/>--%>
                    <input class="select2_material form-control" style="width: 260px" id="productNameSelect" name="productName" >
                    </input>
					</br>
					<label>产品型号 </label>
					<form:input path="version" autocomplete="off" style="width:165px;" value="${pdStockRecord.version}"/>
					<label>产品批号</label>
					<form:input path="batchNo" autocomplete="off" value="${pdStockRecord.batchNo}"/>
                    <label>供应商 </label>
                    <form:select path="supplierId"  class="input-medium" style="width: 260px">
                        <form:option value="" label="全部(qb)"/>
                        <form:options items="${fns:findSupplierList() }" itemLabel="nameAndpinyin" itemValue="id" htmlEscape="false"/>
                    </form:select>
					<input id="btnSubmit" onclick="return page();" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
					<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</div>
		</form:form>

		<sys:message content="${message}"/>
		<div class="showTableBox" style="width:100%;overflow:auto;">
			<div class="tableBox">
				<table id="contentTable" class="hcy-public-table" style="padding:0;width:2000px;">
					<thead>
						<tr>
							<th>入库日期</th>
							<th>入库库房</th>
							<th>产品名称 </th>
							<th>产品编号 </th>
							<th>产品条码 </th>
							<th>规格</th>
							<th>型号 </th>
							<th>批号 </th>
							<th>有效期</th>
							<th>数量</th>
							<th>单位</th>
							<th>单价</th>
							<th>金额</th>
							<th>生产厂家</th>
							<th>供应商</th>
	<!-- 					<th>供应商销售日期</th>
							<th>供应商发票日期</th>
							<th>供应商发票号</th> -->
							<th>订单号</th>
							<th>备注</th>
							<th>入库类型</th>
							<th>操作人</th>						
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.list}" var="pdStockRecord">
						<tr>
							<td>
								<fmt:formatDate value="${pdStockRecord.recordDate}" pattern="yyyy-MM-dd"/>
							</td>
							<td>
								${spd:getStoreroomName(pdStockRecord.inId)}
							</td>
							<td>
								${pdStockRecord.pdName}
							</td>
							<td>
								${pdStockRecord.number}
							</td>
							<td>
								${pdStockRecord.productBarCode}
							</td>
							<td>${pdStockRecord.spec }</td>
							<td>${pdStockRecord.version}</td>
							<td>${pdStockRecord.batchNo}</td>
							<td><fmt:formatDate value="${pdStockRecord.limitDate}" pattern="yyyy-MM-dd"/></td>
							<td>${pdStockRecord.productNum}</td>
							<td>${pdStockRecord.unit}</td>
							<td>${pdStockRecord.sellingPrice}</td>
							<td>${pdStockRecord.pdTotalPrice}</td>
							<td>${pdStockRecord.venderName}</td>
							<td>${pdStockRecord.supplierName}</td>
	<%-- 					<td>${pdStockRecord.saleDate}</td>
							<td>${pdStockRecord.invoiceDate}</td>
							<td>${pdStockRecord.invoiceNo}</td> --%>
							<td>${pdStockRecord.recordNo}</td>
							<td><a href="###" title="${pdStockRecord.remarks}" class="overLook">${pdStockRecord.remarks}</a></td>
							<td>${fns:getDictLabel(pdStockRecord.inType, 'pd_inType', '')}</td>
							<td>
								${pdStockRecord.recordPeople}
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){

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

			//日期范围			
			laydate.render({
				elem: '#inHomeTime',
				range: true
			});
			
			//重置
			$(".hcy-reset").click(function(){
				var sel ="${fns:getUser().storeroomId}";//默认下拉
				$(".newSearchBox input[type='text']").val("");
                $("#storeroomId").select2("val", sel);
                $("#supplierId").select2("val", "");
                $("#productNameSelect").val("")
                $("#productNameSelect").select2("data", "");
			})
			
			//导出数据
			$('#exportBtn').one('click',function(){
				$(this).css("background-color","#B3BDC3");
				var form = $('<form>');
				form.attr('style', 'display:none');
				form.attr('method', 'post');
				form.attr('action', '${ctx}/excelExport/stockRecordInDtlQueryExport');
				var input = $('<input>');
				input.attr('type', 'hidden');
				input.attr('name', 'exportData');
				input.attr('value', '${exportDataList}');
				form.append(input);
				$('body').append(form);
				form.submit();
				form.remove();
			});
		})
		
		//查询加遮罩
		function query(){
			var index = loading('正在查询，请稍等...');
			return true;
		}
	</script>
</body>
</html>