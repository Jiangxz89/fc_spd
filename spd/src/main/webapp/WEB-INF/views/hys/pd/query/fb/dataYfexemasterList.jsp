<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药房发药单</title>
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
			<h4>药房发药单</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="dataYfexemaster" action="${ctx}/pd/dataYfexemaster/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<label>单据日期</label>
					<form:input path="queryDate" autocomplete="off" htmlEscape="false" id="inHomeTime" style="width:10.3rem;" readonly="readonly" maxlength="64" class="input-medium"/>
					<label style="margin-left:5px;">单据号 </label>
					<form:input path="invoiceno" autocomplete="off" value="${dataYfexemaster.invoiceno}"/>
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
							<th>执行科室名称</th>
							<th>零售金额</th>
							<th>发票号</th>
							<th>住院号</th>
							<th>副数</th>
							<th>门诊号</th>
							<th>姓名</th>
							<th>配药单时间</th>
							<th>配药人</th>
							<th>发药人名</th>
							<th>发药时间</th>
							<th>单据创建时间</th>
							<th>处方类型</th>
							<th>病人类型</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="dataYfexemaster">
							<tr>
								<td>${dataYfexemaster.exedeptname }</td>
								<td>${dataYfexemaster.reatilfee }</td>
								<td>${dataYfexemaster.invoiceno }</td>
								<td>${dataYfexemaster.inid }</td>
								<td>${dataYfexemaster.fuqty }</td>
								<td>${dataYfexemaster.patlstno }</td>
								<td>${dataYfexemaster.patname }</td>
								<td><fmt:formatDate value="${dataYfexemaster.prntime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${dataYfexemaster.dosstaffname }</td>
								<td>${dataYfexemaster.opstaffname }</td>
								<td><fmt:formatDate value="${dataYfexemaster.optime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td><fmt:formatDate value="${dataYfexemaster.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${dataYfexemaster.orderclassname }</td>
								<td>${dataYfexemaster.pattypename }</td>
								<td>
									<a class="hcy-btn hcy-btn-o" href="${ctx}/pd/dataYfexedetail/list?masterid=${dataYfexemaster.masterid}">查看</a>
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