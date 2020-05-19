<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>本院收费项目目录</title>
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
			<h4>本院收费项目目录</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="baseFeeprice" action="${ctx}/pd/baseFeeprice/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<%--<label>入库时间</label>
					<form:input path="queryDate" autocomplete="off" htmlEscape="false" id="inHomeTime" style="width:10.3rem;" readonly="readonly" maxlength="64" class="input-medium"/>
					<label style="margin-left:5px;">科室名称 </label>
					<form:input path="deptname" autocomplete="off" value="${baseFeeprice.deptname}"/>
					<label style="margin-left:5px;">药品名称 </label>
					<form:input path="medname" autocomplete="off" value="${baseFeeprice.medname}"/>--%>
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
							<th>自定义码</th>
							<th>项目编码 </th>
							<th>价格 </th>
							<th>处方可开 </th>
							<th>是否门诊套餐</th>
							<th>是否住院套餐 </th>
							<th>是否停用 </th>
							<th>社保编码</th>
							<th>农合编码</th>
							<th>修改人</th>
							<th>修改时间</th>
							<th>是否通过社保审核</th>
							<th>发票分类</th>
							<th>发票分类名称</th>
							<th>审核状态</th>
							<th>审核人</th>
							<th>审核时间</th>
							<th>一级价格</th>
							<th>二级价格</th>
							<th>三级价格</th>
							<th>本院名称</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="baseFeeprice">
							<tr>
								<%--<td>${baseFeeprice.hospitalname }</td>--%>
								<td>${baseFeeprice.cuscode }</td>
								<td>${baseFeeprice.feenumber }</td>
								<td>${baseFeeprice.price }</td>
								<c:choose>
									<c:when test="${baseFeeprice.isble==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${baseFeeprice.outpack==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${baseFeeprice.inpack==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${baseFeeprice.isstop==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<td>${baseFeeprice.sicode }</td>
								<td>${baseFeeprice.nhcode }</td>
								<td>${baseFeeprice.modstaffname }</td>
								<td><fmt:formatDate value="${baseFeeprice.moddate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<c:choose>
									<c:when test="${baseFeeprice.audstate==0}">
										<td>未上传</td>
									</c:when>
									<c:when test="${baseFeeprice.audstate==1}">
										<td>已上传</td>
									</c:when>
									<c:when test="${baseFeeprice.audstate==2}">
										<td>通过审核</td>
									</c:when>
									<c:otherwise>
										<td>未通过审核</td>
									</c:otherwise>
								</c:choose>
								<td>${baseFeeprice.invclass }</td>
								<td>${baseFeeprice.invclassname }</td>
								<c:choose>
									<c:when test="${baseFeeprice.auditstatus==0}">
										<td>未审核</td>
									</c:when>
									<c:otherwise>
										<td>已审核</td>
									</c:otherwise>
								</c:choose>
								<td>${baseFeeprice.auditorname }</td>
								<td><fmt:formatDate value="${baseFeeprice.audittime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${baseFeeprice.oneLevel }</td>
								<td>${baseFeeprice.twoLevel }</td>
								<td>${baseFeeprice.threeLevel }</td>
								<td>${baseFeeprice.hosfeename }</td>

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