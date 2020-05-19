<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品入库明细</title>
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
			<h4>药品入库明细</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="dataMeddetail" action="${ctx}/pd/dataMeddetail/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<input id="masterid" name="masterid" type="hidden" value="${dataMeddetail.masterid}"/>
					<label style="margin-left:5px;">药品名称 </label>
					<form:input path="medname" autocomplete="off" value="${dataMeddetail.medname}"/>
					<label style="margin-left:5px;">生产厂家 </label>
					<form:input path="facname" autocomplete="off" value="${dataMeddetail.facname}"/>
					<input id="btnSubmit" onclick="return page();" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
					<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</div>
		</form:form>

		<sys:message content="${message}"/>
		<div class="showTableBox" style="width:100%;overflow:auto;">
			<div class="tableBox">
				<table id="contentTable" class="hcy-public-table" style="padding:0;width:80rem;">
					<thead>
						<tr>
							<%--<th>医疗机构名称</th>--%>
							<th>药品名称</th>
							<th>药品批次</th>
							<th>数量</th>
							<th>实际数量</th>
							<th>药品单位</th>
							<th>药品规格</th>
							<th>购进价</th>
							<th>零售价</th>
							<th>预计零售价</th>
							<th>批号</th>
							<th>有效期</th>
							<th>结算日期</th>
							<th>发票号</th>
							<th>发票日期</th>
							<th>备注</th>
							<th>验收结论</th>
							<th>批准文号</th>
							<th>生产厂家</th>
							<th>批次总库存</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="dataMeddetail">
							<tr>
								<td>${dataMeddetail.medname }</td>
								<td>${dataMeddetail.batch }</td>
								<td>${dataMeddetail.qty }</td>
								<td>${dataMeddetail.actualqty }</td>
								<td>${dataMeddetail.unit }</td>
								<td>${dataMeddetail.spec }</td>
								<td>${dataMeddetail.inprice }</td>
								<td>${dataMeddetail.clinprice }</td>
								<td>${dataMeddetail.wardprice }</td>
								<td>${dataMeddetail.batchno }</td>
								<td><fmt:formatDate value="${dataMeddetail.expidate }" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${dataMeddetail.matpaydate }" pattern="yyyy-MM-dd"/></td>
								<td>${dataMeddetail.invno }</td>
								<td><fmt:formatDate value="${dataMeddetail.invdate }" pattern="yyyy-MM-dd"/></td>
								<td>${dataMeddetail.dspt }</td>
								<td>${dataMeddetail.checkdspt }</td>
								<td>${dataMeddetail.permdoc }</td>
								<td>${dataMeddetail.facname }</td>
								<td>${dataMeddetail.totalbatchqty }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagination">${page}</div>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
			<a href="${ctx}/pd/dataMedmaster/list" class="hcy-btn hcy-back" >返回</a>
		</div>
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
            })
		})
	</script>
</body>
</html>