<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>进销存报表</title>
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
			<h4>进销存报表</h4>
			<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>
		</div>
		<div class="newSearchBox">
			<form:form id="searchForm" modelAttribute="pdRpInventory" action="${ctx}/pd/pdProductStock/pdEntersSellsSavesDetail" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<label>日期</label>
				<form:input path="queryDate" autocomplete="off" htmlEscape="false" id="inHomeTime" style="width:165px;" readonly="readonly" maxlength="64" class="input-medium"/>
				<label for="">库房</label>
				<c:choose>
				<c:when test="${user.storeroomType==1}">
					<form:select path="storeroomId" class="input-medium">
						<form:option value="${fns:getUser().storeroomId}" label="${fns:getUser().storeroomName}"/>
					</form:select>
				</c:when>
				<c:when test="${user.storeroomType==0}">
					<form:select path="storeroomId" class="input-medium">
						<form:options items="${fns:findStoreroomList()}" itemLabel="storeroomName" itemValue="id" htmlEscape="false"/>
					</form:select>
				</c:when>
				<c:otherwise>
					<form:select path="storeroomId" class="input-medium">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:findStoreroomList()}" itemLabel="storeroomName" itemValue="id" htmlEscape="false"/>
					</form:select>
				</c:otherwise>
				</c:choose>
				<label style="margin-left:5px;">产品编号</label>
				<form:input path="productNo" autocomplete="off" value="${pdRpInventory.productNo}"/>
				<label>产品名称</label>
				<%--<form:input path="productName" style="width:165px;" htmlEscape="false" maxlength="64" class="input-medium"  value="${pdRpInventory.productName}"/>--%>
				<input class="select2_material form-control" style="width: 260px" id="productNameSelect" name="productName" >
				<br>
				<label>产品型号</label>
				<form:input path="version" autocomplete="off" style="width:165px;" value="${pdRpInventory.version}"/>
				<label>供应商 </label>
				<form:select path="supplierId"  style="width: 260px"   class="input-medium">
					<form:option value="" label="全部(qb)"/>
					<form:options items="${fns:findSupplierList() }" itemLabel="nameAndpinyin" itemValue="id" htmlEscape="false"/>
				</form:select>
				<input id="btnSubmit" onclick="return page();" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置">
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>供应商</th>
						<th>产品编号</th>
						<th>产品名称</th>
						<th>型号</th>
						<th>期初库存</th>
						<th>本期入库</th>
						<th>本期出库</th>
						<th>调出数量</th>
						<th>调入数量</th>
						<th>退货出库</th>
						<th>退货入库</th>
						<th>用量数量</th>
						<th>用量退回</th>
						<th>院外退货</th>
						<th>单价</th>
						<th>用量金额</th>
						<th>期末库存</th>
					</tr>
				</thead>
				<c:forEach items="${page.list}" var="pdRpInventory">
						<tr>
							<td>
								<a href="###" class="overLook" title="${pdRpInventory.supplier}">${pdRpInventory.supplier}</a>
							</td>
							<td>
								${pdRpInventory.productNo}
							</td>
							<td>
								${pdRpInventory.productName}
							</td>
							<td>
								${pdRpInventory.version}
							</td>
							<td>
								${pdRpInventory.beginStockNum}
							</td>
							<td>
								${pdRpInventory.rukNum}
							</td>
							<td>
								${pdRpInventory.zhengcNum}
							</td>
							<td>
								${pdRpInventory.diaocNum}
							</td>
							<td>
								${pdRpInventory.diaorNum}
							</td>
							<td>
								${pdRpInventory.tuihcNum}
							</td>
							<td>
								${pdRpInventory.tuihrNum}
							</td>
							<td>
								${pdRpInventory.shiyNum}
							</td>
							<td>
								${pdRpInventory.tuihNum}
							</td>
							<td>
								${pdRpInventory.yythNum}
							</td>
							<td>
								${pdRpInventory.sellingPrice}
							</td>
							<td>
								${pdRpInventory.dosageAmount}
							</td>
							<td>
								${pdRpInventory.endStockNum}
							</td>
							
						</tr>
					</c:forEach>
			</table>
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
		})

        //重置
        $(".hcy-reset").click(function(){
            var sel ="${fns:getUser().storeroomId}";//默认下拉
            $(".newSearchBox input[type='text']").val("");
            $("#storeroomId").select2("val", sel);
            $("#supplierId").select2("val", "");
            $("#productNameSelect").val("")
            $("#productNameSelect").select2("data", "");
        })
		
		//查询加遮罩
		function query(){
			var index = loading('正在查询，请稍等...');
			return true;
		}
		
		//导出数据
		$('#exportBtn').one('click',function(){
			$(this).css("background-color","#B3BDC3");
			var form = $('<form>');
			form.attr('style', 'display:none');
			form.attr('method', 'post');
			form.attr('action', '${ctx}/excelExport/pdEntersSellsSavesDetailExport');
			var input = $('<input>');
			input.attr('type', 'hidden');
			input.attr('name', 'exportData');
			input.attr('value', '${exportDataList}');
			form.append(input);
			$('body').append(form);
			form.submit();
			form.remove();
		});
	</script>
</body>
</html>