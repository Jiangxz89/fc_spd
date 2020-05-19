<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品入库明细</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<style>
		.layui-layer-content{text-align:center;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		resetTip();
	</script>
</head>
<body>
	<div class="right-main-box"> 
		<div class="btnBox">
			<h4>药品入库明细</h4>
			<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="medstoRkzjl" action="${ctx}/pd/medstoRkzjl/findDrugInDetaile" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>机构代码</label>
					<form:input path="jgbm" />
				</div>
				<div>
					<label>入库单编号</label>
					<form:input path="entryNo" />
				</div>
				<div>
					<label>配送单编号</label>
					<form:input path="deliveryNo" />
				</div>
				<div>
					<label>供货商名称</label>
					<form:input path="producerName" />
				</div>
				<div>
					<label>药品名称</label>
					<form:input path="drugName" />
				</div>
				<div>
					<label>药品规格</label>
					<form:input path="drugSpec" />
				</div>
				<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="showTableBox" style="width:100%;overflow:auto;">
			<div class="tableBox">
				<table id="contentTable" class="hcy-public-table" style="padding:0;width:2000px;">
					<thead>
						<tr>
							<th>机构编码</th>
							<th>药库编码</th>
							<th>药库名称</th>
							<th>入库单编号</th>
							<th>配送单编号</th>
							<th>经手人</th>
							<th>审核员</th>
							<th>审核标记</th>
							<th>发票号</th>
							<th>入库日期</th>
							<%--<th>进价金额</th>--%>
							<th>零售金额</th>
							<th>供货商名称</th>
							<th>开票日期</th>
							<th>到票日期</th>
							<th>操作代码</th>
							<th>产地idm</th>
							<th>规格idm</th>
							<th>药品名称</th>
							<th>药品规格</th>
							<th>药品代码</th>
							<th>批次序号</th>
							<th>失效日期</th>
							<th>批号</th>
							<th>药库系数</th>
							<th>药品单位</th>
							<th>单位系数</th>
							<th>入库数量</th>
							<th>操作数量</th>
							<th>药品扣率</th>
							<th>药品进价</th>
							<th>批发价</th>
							<th>零售价</th>
							<th>进价金额</th>
							<th>进销差额</th>
							<th>相关序号</th>
							<th>到票标志</th>
							<th>默认招标价</th>
							<th>招标期号</th>
							<th>中标单位</th>
							<th>药品默认扣率</th>
							<th>上传日期</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="drug">
							<tr>
								<td>${drug.jgbm}</td>
								<td>${drug.storeId}</td>
								<td>${drug.storeName}</td>
								<td>${drug.entryNo}</td>
								<td>${drug.deliveryNo}</td>
								<td>${drug.operName}</td>
								<td>${drug.checkName}</td>
                                <c:choose>
                                    <c:when test="${drug.checkFlag==0}">
                                        <td>未审核</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>已审核</td>
                                    </c:otherwise>
                                </c:choose>
								<td>${drug.fph}</td>
								<td><fmt:formatDate value="${drug.rkrq}" pattern="yyyy-MM-dd"/></td>
								<%--<td>${drug.jjje}</td>--%>
								<td>${drug.lsje}</td>
								<td>${drug.producerName}</td>
								<td><fmt:formatDate value="${drug.medstoYprkmxNew.kprq}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${drug.medstoYprkmxNew.dprq}" pattern="yyyy-MM-dd"/></td>
								<td>${drug.medstoYprkmxNew.czdm}</td>
								<td>${drug.medstoYprkmxNew.cdIdm}</td>
								<td>${drug.medstoYprkmxNew.ggIdm}</td>
								<td>${drug.medstoYprkmxNew.drugName}</td>
								<td>${drug.medstoYprkmxNew.drugSpec}</td>
								<td>${drug.medstoYprkmxNew.ypdm}</td>
								<td>${drug.medstoYprkmxNew.pcxh}</td>
								<td><fmt:formatDate value="${drug.medstoYprkmxNew.expiry}" pattern="yyyy-MM-dd"/></td>
								<td>${drug.medstoYprkmxNew.ph}</td>
								<td>${drug.medstoYprkmxNew.ykxs}</td>
								<td>${drug.medstoYprkmxNew.ypdw}</td>
								<td>${drug.medstoYprkmxNew.dwxs}</td>
								<td>${drug.medstoYprkmxNew.rksl}</td>
								<td>${drug.medstoYprkmxNew.czsl}</td>
								<td>${drug.medstoYprkmxNew.ypkl}</td>
								<td>${drug.medstoYprkmxNew.ypjj}</td>
								<td>${drug.medstoYprkmxNew.ypfj}</td>
								<td>${drug.medstoYprkmxNew.ylsj}</td>
								<td>${drug.medstoYprkmxNew.jjje}</td>
								<td>${drug.medstoYprkmxNew.jxce}</td>
								<td>${drug.medstoYprkmxNew.xgxh}</td>
								<td>${drug.medstoYprkmxNew.dpbz}</td>
								<td>${drug.medstoYprkmxNew.mrzbj}</td>
								<td>${drug.medstoYprkmxNew.zbqh}</td>
								<td>${drug.medstoYprkmxNew.zbdw}</td>
								<td>${drug.medstoYprkmxNew.ypmrkl}</td>
								<td><fmt:formatDate value="${drug.medstoYprkmxNew.scrq}" pattern="yyyy-MM-dd"/></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="pagination">${page}</div>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function() {
			$(document).on('change', ".addSupplierSelect", function () {
				$(this).next().html('');
			});
			//重置
			$(".hcy-reset").click(function () {
				$(".searchBox div input[type='text']").val("");
				$(".searchBox div select").val("");
			})

			//导出数据
			$('#exportBtn').one('click',function(){
				$(this).css("background-color","#B3BDC3");
				var form = $('<form>');
				form.attr('style', 'display:none');
				form.attr('method', 'post');
				form.attr('action', '${ctx}/excelExport/drugInDetaileExport');
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
	</script>
</body>
</html>