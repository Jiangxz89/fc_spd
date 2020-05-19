<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用量明细查询</title>
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
			<h4>用量明细查询</h4>
			<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>
		</div>
		<div class="newSearchBox">
			<form:form id="searchForm" modelAttribute="pdDosage" action="${ctx}/pd/pdDosage/detailList" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<label>用量日期</label>
				<form:input path="queryDate" autocomplete="off" htmlEscape="false" id="inHomeTime" style="width:165px;" readonly="readonly" maxlength="64" class="input-medium"/>
				<label for="">库房</label>
				<c:choose>
				<c:when test="${user.storeroomType==1}">
					<form:select path="warehouseId" class="input-medium">
						<form:option value="${fns:getUser().storeroomId}" label="${fns:getUser().storeroomName}"/>
					</form:select>
				</c:when>
				<c:otherwise>
					<form:select path="warehouseId" class="input-medium">
						<form:options items="${fns:findStoreroomList()}" itemLabel="storeroomName" itemValue="id" htmlEscape="false"/>
					</form:select>
				</c:otherwise>
				</c:choose>
				<label style="margin-left:5px;">产品编号 </label>
				<form:input path="number" autocomplete="off" value="${pdDosage.number}"/>
				<label>产品名称</label>
				<%--<form:input path="pdName" htmlEscape="false" maxlength="64" class="input-medium" value="${pdDosage.pdName}"/>--%>
				<input class="select2_material form-control" style="width: 260px" id="productNameSelect" name="pdName" >
				<br>
<%-- 				<div>
					<label>经销商 </label>
					<select class="input-medium" >
						<option value="" label="全部"/>
						<options items="${fns:getDictList('stock_record_state') }" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</select>
				</div> --%>
				<label>产品型号</label>
				<form:input path="version" autocomplete="off" style="width:165px;" value="${pdDosage.version}"/>
				<label>产品批号</label>
				<form:input path="batchNo" autocomplete="off" value="${pdDosage.batchNo}"/>
				<input id="btnSubmit" onclick="return page();" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置">
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="showTableBox" style="width:100%;overflow:auto;">
			<div class="tableBox">
				<table id="contentTable" class="hcy-public-table" style="padding:0;width:2000px;">
					<thead>
						<tr>
							<!-- <th>供应商</th> -->
							<th>用量单号</th>
							<th>用量库房 </th>
							<!-- <th>出库日期</th> -->
							<th>用量日期 </th>
							<th>产品名称</th>
							<th>产品编号</th>
							<th>产品条码</th>
							<th>规格</th>
							<th>型号</th>
							<th>批号</th>
							<th>有效期 </th>
							<th>数量</th>
							<th>单位</th>
							<th>单价</th>
							<th>金额</th>
							<!-- <th>出货单价</th> -->
							<th>生产厂家</th>
							<%--<th>销售人员</th>--%>
							<th>操作人员</th>
							<!-- <th>发票号</th>
							<th>发票日期</th>
							<th>回款日期</th>
							<th>回款金额</th> -->
							<th>执行科室</th>
							<th>手术科室</th>
							<th>住院号</th>
							<th>病人姓名</th>
							<th>手术医生</th>
							<th>HIS收费单价</th>
							<th>HIS收费数量</th>
							<th>HIS收费金额</th>
							<th>收费标志</th>
							<th>备注</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="pdDosage">
							<tr>
								<!-- <td></td> -->
								<td>${pdDosage.dosageNo }</td>
								<td>${pdDosage.warehouseName }</td>
								<!-- <td></td> -->
								<td><fmt:formatDate value="${pdDosage.dosageDate }" pattern="yyyy-MM-dd"/></td>
								<td>${pdDosage.pdName }</td>
								<td>${pdDosage.number }</td>
								<td>${pdDosage.productBarCode }</td>
								<td>${pdDosage.spec }</td>
								<td>${pdDosage.version }</td>
								<td>${pdDosage.batchNo }</td>
								<td><fmt:formatDate value="${pdDosage.limitDate}" pattern="yyyy-MM-dd"/></td>
								<td>${pdDosage.productNum }</td>
								<td>${pdDosage.unit }</td>
								<td>${pdDosage.sellingPrice }</td>
								<td>${pdDosage.amountMoney }</td>
								<!-- <td></td> -->
								<td>${pdDosage.venderName}</td>
								<%--<td>${pdDosage.salesman}</td>--%>
								<td>${pdDosage.dosageOperator}</td>
								<!-- <td></td>
								<td></td>
								<td></td>
								<td></td> -->
								<td>${pdDosage.exeDeptName}</td>
								<td>${pdDosage.oprDeptName}</td>
								<td>${pdDosage.inHospitalNo}</td>
								<td>${pdDosage.patientInfo}</td>
								<td>${pdDosage.surgeon}</td>
								<td>${pdDosage.sellingPrice }</td>
								<td>${pdDosage.productNum }</td>
								<td>${pdDosage.amountMoney }</td>
								<c:choose>
									<c:when test="${pdDosage.chargeFlag==1}">
										<td>已计费</td>
									</c:when>
									<c:otherwise>
										<td>未计费</td>
									</c:otherwise>
								</c:choose>
								<td><a href="###" title="${pdDosage.remarks}" class="overLook">${pdDosage.remarks}</a></td>
								
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

			//申购时间
			laydate.render({
				elem: '#inHomeTime',
				range: true
			});
			
			//重置
			$(".hcy-reset").click(function(){
                var sel ="${fns:getUser().storeroomId}";//默认下拉
                $(".newSearchBox input[type='text']").val("");
                $("#warehouseId").select2("val", sel);
                $("#productNameSelect").val("")
                $("#productNameSelect").select2("data", "");
            })
			
			//导出数据
			$('#exportBtn').one('click',function(){
				$(this).css("background-color","#B3BDC3");
				var form = $('<form>');
				form.attr('style', 'display:none');
				form.attr('method', 'post');
				form.attr('action', '${ctx}/excelExport/dosageQueryExport');
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