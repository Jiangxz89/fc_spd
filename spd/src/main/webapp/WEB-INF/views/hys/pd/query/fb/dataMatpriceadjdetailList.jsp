<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品调价单明细</title>
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
			<h4>药品调价单明细</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="dataMatpriceadjdetail" action="${ctx}/pd/dataMatpriceadjdetail/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<input id="chamedid" name="chamedid" type="hidden" value="${dataMatpriceadjdetail.chamedid}"/>
					<label style="margin-left:5px;">药品名称 </label>
					<form:input path="medname" autocomplete="off" value="${dataMatpriceadjdetail.medname}"/>
					<label style="margin-left:5px;">药品批次 </label>
					<form:input path="batch" autocomplete="off" value="${dataMatpriceadjdetail.batch}"/>
					<label style="margin-left:5px;">药品批号 </label>
					<form:input path="batchno" autocomplete="off" value="${dataMatpriceadjdetail.batchno}"/>
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
							<th>调价数量</th>
							<th>单位</th>
							<th>原购进价</th>
							<th>新购进价</th>
							<th>原零售价</th>
							<th>新零售价</th>
							<th>批次</th>
							<th>批号</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="dataMatpriceadjdetail">
							<tr>
								<td>${dataMatpriceadjdetail.medname }</td>
								<td>${dataMatpriceadjdetail.qty }</td>
								<td>${dataMatpriceadjdetail.unit }</td>
								<td>${dataMatpriceadjdetail.oldbprice }</td>
								<td>${dataMatpriceadjdetail.newbprice }</td>
								<td>${dataMatpriceadjdetail.oldsprice }</td>
								<td>${dataMatpriceadjdetail.newsprice }</td>
								<td>${dataMatpriceadjdetail.batch }</td>
								<td>${dataMatpriceadjdetail.batchno }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagination">${page}</div>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
			<a href="${ctx}/pd/dataMatpriceadj/list" class="hcy-btn hcy-back" >返回</a>
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