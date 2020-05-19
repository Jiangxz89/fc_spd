<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品调价单</title>
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
			<h4>药品调价单</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="dataMatpriceadj" action="${ctx}/pd/dataMatpriceadj/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<label>单据日期</label>
					<form:input path="queryDate" autocomplete="off" htmlEscape="false" id="inHomeTime" style="width:10.3rem;" readonly="readonly" maxlength="64" class="input-medium"/>
					<label style="margin-left:5px;">单据号 </label>
					<form:input path="billno" autocomplete="off" value="${dataMatpriceadj.billno}"/>
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
							<th>单据日期</th>
							<th>单据号</th>
							<th>类型</th>
							<th>调价原因</th>
							<th>调价方式</th>
							<th>制单人</th>
							<th>调价审核人</th>
							<th>状态</th>
							<th>创建时间</th>
							<th>审核时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="dataMatpriceadj">
							<tr>
								<%--<td>${baseMedbatch.hospitalname }</td>--%>
								<td>${dataMatpriceadj.deptname }</td>
								<td><fmt:formatDate value="${dataMatpriceadj.billdate }" pattern="yyyy-MM-dd"/></td>
								<td>${dataMatpriceadj.billno }</td>
								<c:choose>
									<c:when test="${dataMatpriceadj.billkd==1}">
										<td>药品</td>
									</c:when>
									<c:otherwise>
										<td>物品</td>
									</c:otherwise>
								</c:choose>
								<td>${dataMatpriceadj.reason }</td>
								<c:choose>
									<c:when test="${dataMatpriceadj.adjtype==10}">
										<td>立即调价</td>
									</c:when>
									<c:otherwise>
										<td>定时调价</td>
									</c:otherwise>
								</c:choose>
								<td>${dataMatpriceadj.billstfname }</td>
								<td>${dataMatpriceadj.hdlstfname }</td>
								<c:choose>
									<c:when test="${dataMatpriceadj.state==0}">
										<td>未审核</td>
									</c:when>
									<c:otherwise>
										<td>已审核</td>
									</c:otherwise>
								</c:choose>
								<td><fmt:formatDate value="${dataMatpriceadj.iptdate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td><fmt:formatDate value="${dataMatpriceadj.adjdate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/dataMatpriceadjdetail/list?chamedid=${dataMatpriceadj.id}">查看</a>
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