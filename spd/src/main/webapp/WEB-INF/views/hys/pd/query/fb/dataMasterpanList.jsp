<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品盘点单</title>
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
			<h4>药品盘点单</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="dataMasterpan" action="${ctx}/pd/dataMasterpan/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<label>单据日期</label>
					<form:input path="queryDate" autocomplete="off" htmlEscape="false" id="inHomeTime" style="width:10.3rem;" readonly="readonly" maxlength="64" class="input-medium"/>
					<label style="margin-left:5px;">单据号 </label>
					<form:input path="billno" autocomplete="off" value="${dataMasterpan.billno}"/>
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
							<th>科室名称</th>
							<th>类型</th>
							<th>单据日期</th>
							<th>单据号</th>
							<th>制单人名</th>
							<th>状态</th>
							<th>单据审核人</th>
							<th>审核时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="dataMasterpan">
							<tr>
								<%--<td>${baseMedbatch.hospitalname }</td>--%>
								<td>${dataMasterpan.deptname }</td>
								<c:choose>
									<c:when test="${dataMasterpan.type==10}">
										<td>药库</td>
									</c:when>
									<c:when test="${dataMasterpan.type==20}">
										<td>药房</td>
									</c:when>
									<c:otherwise>
										<td>卫材仓库</td>
									</c:otherwise>
								</c:choose>
								<td><fmt:formatDate value="${dataMasterpan.billdate }" pattern="yyyy-MM-dd"/></td>
								<td>${dataMasterpan.billno }</td>
								<td>${dataMasterpan.billstfname }</td>
								<c:choose>
									<c:when test="${dataMasterpan.state==1}">
										<td>已审核</td>
									</c:when>
									<c:otherwise>
										<td>未审核</td>
									</c:otherwise>
								</c:choose>
								<td>${dataMasterpan.audstfname }</td>
								<td><fmt:formatDate value="${dataMasterpan.audtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/dataDetailpan/list?masterid=${dataMasterpan.masterid}">查看</a>
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
            })
		})
	</script>
</body>
</html>