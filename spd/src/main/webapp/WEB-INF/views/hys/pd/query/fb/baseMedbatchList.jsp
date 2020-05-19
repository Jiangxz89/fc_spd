<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品库存明细</title>
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
			<h4>药品库存明细</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="baseMedbatch" action="${ctx}/pd/baseMedbatch/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<label>入库时间</label>
					<form:input path="queryDate" autocomplete="off" htmlEscape="false" id="inHomeTime" style="width:10.3rem;" readonly="readonly" maxlength="64" class="input-medium"/>
					<label style="margin-left:5px;">科室名称 </label>
					<form:input path="deptname" autocomplete="off" value="${baseMedbatch.deptname}"/>
					<label style="margin-left:5px;">药品名称 </label>
					<form:input path="medname" autocomplete="off" value="${baseMedbatch.medname}"/>
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
							<th>药品名称 </th>
							<th>批次 </th>
							<th>实际库存 </th>
							<th>单位</th>
							<th>购进价 </th>
							<th>零售价 </th>
							<th>预计零售价</th>
							<th>有效期</th>
							<th>厂家名称</th>
							<th>状态</th>
							<th>批准文号</th>
							<th>批次入库次数</th>
							<th>入库时间</th>
							<th>供应商名</th>
							<th>有效库存</th>
							<th>批号</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="baseMedbatch">
							<tr>
								<%--<td>${baseMedbatch.hospitalname }</td>--%>
								<td>${baseMedbatch.deptname }</td>
								<td>${baseMedbatch.medname }</td>
								<td>${baseMedbatch.batch }</td>
								<td>${baseMedbatch.qty }</td>
								<td>${baseMedbatch.unit }</td>
								<td>${baseMedbatch.inprice }</td>
								<td>${baseMedbatch.clinprice }</td>
								<td>${baseMedbatch.wardprice }</td>
								<td><fmt:formatDate value="${baseMedbatch.expidate }" pattern="yyyy-MM-dd"/></td>
								<td>${baseMedbatch.facname }</td>
								<c:choose>
									<c:when test="${baseMedbatch.state==1}">
										<td>作废</td>
									</c:when>
									<c:otherwise>
										<td>在用</td>
									</c:otherwise>
								</c:choose>
								<td>${baseMedbatch.permdoc }</td>
								<td>${baseMedbatch.refercnt }</td>
								<td><fmt:formatDate value="${baseMedbatch.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${baseMedbatch.suppliername }</td>
								<td>${baseMedbatch.validqty }</td>
								<td>${baseMedbatch.batchno }</td>
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