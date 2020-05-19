<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品入库主记录</title>
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
			<h4>药品入库主记录</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="dataMedmaster" action="${ctx}/pd/dataMedmaster/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<label>单据日期</label>
					<form:input path="queryDate" autocomplete="off" htmlEscape="false" id="inHomeTime" style="width:10.3rem;" readonly="readonly" maxlength="64" class="input-medium"/>
					<label style="margin-left:5px;">单据号 </label>
					<form:input path="billno" autocomplete="off" value="${dataMedmaster.billno}"/>
					<form:select path="billkd" class="input-medium">
						<form:option value="" label="全部"/>
						<form:option value="10" label="进货入库"/>
						<form:option value="20" label="退货出库"/>
						<form:option value="30" label="发药出库"/>
						<form:option value="40" label="退药入库"/>
					</form:select>
					<input id="btnSubmit" onclick="return page();" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
					<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</div>
		</form:form>

		<sys:message content="${message}"/>
		<div class="showTableBox" style="width:100%;overflow:auto;">
			<div class="tableBox">
				<table id="contentTable" class="hcy-public-table" style="padding:0;width:78rem;">
					<thead>
						<tr>
							<%--<th>医疗机构名称</th>--%>
							<th>科室名称</th>
							<th>相关科室名称</th>
							<th>单据日期</th>
							<th>单据号</th>
							<th>供应商名</th>
							<th>单据类型</th>
							<th>制单人</th>
							<th>单据状态</th>
							<th>单据审核人</th>
							<th>审核时间</th>
							<th>备注</th>
							<th>支付方式</th>
							<th>发票号</th>
							<th>送货单号</th>
							<th>发票日期</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="dataMedmaster">
							<tr>
								<td>${dataMedmaster.deptname }</td>
								<td>${dataMedmaster.redeptname }</td>
								<td><fmt:formatDate value="${dataMedmaster.billdate }" pattern="yyyy-MM-dd"/></td>
								<td>${dataMedmaster.billno }</td>
								<td>${dataMedmaster.suppliername }</td>
								<c:choose>
									<c:when test="${dataMedmaster.billkd==10}">
										<td>进货入库</td>
									</c:when>
									<c:when test="${dataMedmaster.billkd==20}">
										<td>退货出库</td>
									</c:when>
									<c:when test="${dataMedmaster.billkd==30}">
										<td>发药出库</td>
									</c:when>
									<c:otherwise>
										<td>退药入库</td>
									</c:otherwise>
								</c:choose>
								<td>${dataMedmaster.billstfname }</td>
								<c:choose>
									<c:when test="${dataMedmaster.state==1}">
										<td>已审核</td>
									</c:when>
									<c:otherwise>
										<td>未审核</td>
									</c:otherwise>
								</c:choose>
								<td>${dataMedmaster.audstfname }</td>
								<td><fmt:formatDate value="${dataMedmaster.audtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${dataMedmaster.dspt }</td>
								<td>${dataMedmaster.gatkdid }</td>
								<td>${dataMedmaster.invoiceno }</td>
								<td>${dataMedmaster.receiptno }</td>
								<td>${dataMedmaster.invoicedate }</td>
								<td>
									<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/dataMeddetail/list?masterid=${dataMedmaster.masterid}">查看</a>
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
            $(function(){
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
                $("#billkd").select2("val", "");
            })
		})
	</script>
</body>
</html>