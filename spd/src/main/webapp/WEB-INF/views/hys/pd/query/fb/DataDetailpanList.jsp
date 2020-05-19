<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品盘点单明细</title>
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
			<h4>药品盘点单明细</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="dataDetailpan" action="${ctx}/pd/dataDetailpan/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<input id="masterid" name="masterid" type="hidden" value="${dataDetailpan.masterid}"/>
					<label style="margin-left:5px;">药品名称 </label>
					<form:input path="medname" autocomplete="off" value="${dataDetailpan.medname}"/>
					<label style="margin-left:5px;">药品批次 </label>
					<form:input path="batch" autocomplete="off" value="${dataDetailpan.batch}"/>
					<label style="margin-left:5px;">药品批号 </label>
					<form:input path="batchno" autocomplete="off" value="${dataDetailpan.batchno}"/>
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
							<th>批次</th>
							<th>电脑数量</th>
							<th>盘点数量</th>
							<th>购进价</th>
							<th>零售价</th>
							<th>批号</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="dataDetailpan">
							<tr>
								<td>${dataDetailpan.medname }</td>
								<td>${dataDetailpan.batch }</td>
								<td>${dataDetailpan.qty }</td>
								<td>${dataDetailpan.realqty }</td>
								<td>${dataDetailpan.inprice }</td>
								<td>${dataDetailpan.clinprice }</td>
								<td>${dataDetailpan.batchno }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagination">${page}</div>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
			<a href="${ctx}/pd/dataMasterpan/list" class="hcy-btn hcy-back" >返回</a>
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