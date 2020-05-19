<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存明细查询</title>
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
			<h4>库存明细查询</h4>
			<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>
		</div>
		<div class="newSearchBox">
			<form:form id="searchForm" modelAttribute="pdProductStockTime" action="${ctx}/pd/pdProductStockTime/list" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<label>日期</label>
				<input name="stockDate" autocomplete="off" id="stockDate" type="text" style="width:165px;" value='<fmt:formatDate value="${pdProductStockTime.stockDate }" pattern="yyyy-MM-dd"/>' readonly="readonly" class="input-medium Wdate"/>
				<label>库房</label>
				<form:select path="storeroomId" class="input-medium" >
					<c:if test="${fns:getUser().storeroomType == '0' }">
						<form:option value="" label="全部"/>
						<form:options items="${spd:findStoreroomList() }" itemLabel="storeroomName" itemValue="id" htmlEscape="false"/>
					</c:if>
					<c:if test="${fns:getUser().storeroomType == '1' }">
						<form:option value="${fns:getUser().storeroomId }" label="${fns:getUser().storeroomName }"/>
					</c:if>
				</form:select>
				<label style="margin-left:5px;">产品编号 </label>
				<form:input path="prodNo" autocomplete="off"/>
				<label>产品名称</label>
				<%--<form:input path="productName" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
				<input class="select2_material form-control" style="width: 260px" id="productNameSelect" name="productName" >
				</br>
				<label>产品型号 </label>
				<form:input style="width:165px;" autocomplete="off" path="productVersion"/>
				<label>产品批号 </label>
				<form:input path="batchNo" autocomplete="off"/>
				<label>供应商</label>
				<form:select path="supplierId" class="input-medium"  style="width: 260px">
					<form:option value="" label="全部"/>
					<form:options items="${spd:findSupplierList()}" itemLabel="nameAndpinyin" itemValue="id" htmlEscape="false"/>
				</form:select>
				<input id="btnSubmit" onclick="return page();" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<!-- <input id="clearCondition" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="button" value="清空查询条件"/> -->
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>库房名称</th>
						<th>产品名称</th>
						<th>产品编号</th>
						<th>产品条码</th>
						<th>规格</th>
						<th>型号</th>
						<th>批号</th>
						<th>有效期</th>
						<th>数量</th>
						<th>生产厂家</th>
						<th>供应商</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="li">
						<tr>
							<td>${spd:getStoreroomName(li.storeroomId) }</td>
							<td>${li.productName }</td>
							<td>${li.prodNo }</td>
							<td>${li.barCode }</td>
							<td>${li.productSpec }</td>
							<td>${li.productVersion }</td>
							<td>${li.batchNo }</td>
							<td><fmt:formatDate value="${li.validDate }" pattern="yyyy-MM-dd"/></td>
							<td>${li.stockNum }</td>
							<td>${li.venderName }</td>
							<td>${li.supplierName }</td>
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
				elem: '#stockDate',
				format: 'yyyy-MM-dd',
				range: false
			});
			//清空条件
/* 			$('#clearCondition').click(function(){
				$('#searchForm input:gt(2):lt(4),select').val('');
			}); */
			
			//重置
			$(".hcy-reset").click(function(){
                $(".newSearchBox input[type='text']").val("");
                $("#storeroomId").select2("val", "");
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
				form.attr('action', '${ctx}/excelExport/stockTimeQueryExport');
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
		
		//js获取当前事件
		function getNowFormatDate() {
		    var date = new Date();
		    var seperator1 = "-";
		    var seperator2 = ":";
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
		    return currentdate;
		}
	</script>
</body>
</html>