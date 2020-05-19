<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>中心物资目录</title>
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
			<h4>中心物资目录</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="dictMaterial" action="${ctx}/pd/dictMaterial/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<label style="margin-left:5px;">名称 </label>
					<form:input path="name" autocomplete="off" value="${dictMaterial.name}"/>
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
							<th>批准文号 </th>
							<th>中文名称 </th>
							<th>his收费代码 </th>
							<th>拼音码 </th>
							<th>五笔码 </th>
							<th>自定义码 </th>
							<th>大单位</th>
							<th>小单位</th>
							<th>大小单位转换系数</th>
							<th>规格</th>
							<th>购进价</th>
							<th>零售价</th>
							<th>物品大类</th>
							<th>物品大类名称</th>
							<th>是否医保类型</th>
							<th>是否收费项目</th>
							<th>是否科室成本</th>
							<th>是否停用</th>
							<th>修改人</th>
							<th>修改时间</th>
							<th>社保代码</th>
							<th>农合编码</th>
							<th>厂家名称</th>
							<th>状态</th>
							<th>审核人</th>
							<th>审核时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="dictMaterial">
							<tr>
								<%--<td>${dictMaterial.hospitalname }</td>--%>
								<td>${dictMaterial.numcode }</td>
								<td>${dictMaterial.name }</td>
								<td>${dictMaterial.hisCode }</td>
								<td>${dictMaterial.pycode }</td>
								<td>${dictMaterial.wbcode }</td>
								<td>${dictMaterial.cuscode }</td>
								<td>${dictMaterial.packunit }</td>
								<td>${dictMaterial.miniunit }</td>
								<td>${dictMaterial.packamount }</td>
								<td>${dictMaterial.spec }</td>
								<td>${dictMaterial.inprice }</td>
								<td>${dictMaterial.sellprice }</td>
								<td>${dictMaterial.matclass }</td>
								<td>${dictMaterial.matclassname }</td>
								<c:choose>
									<c:when test="${dictMaterial.mretype==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMaterial.isfee==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMaterial.iscost==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMaterial.isstop==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<td>${dictMaterial.modstaffname }</td>
								<td><fmt:formatDate value="${dictMaterial.moddate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${dictMaterial.sicode }</td>
								<td>${dictMaterial.nhcode }</td>
								<td>${dictMaterial.facname }</td>
								<c:choose>
									<c:when test="${dictMaterial.zxauditstatus==1}">
										<td>已审核</td>
									</c:when>
									<c:when test="${dictMaterial.zxauditstatus==2}">
										<td>审核未通过</td>
									</c:when>
									<c:otherwise>
										<td>未审核</td>
									</c:otherwise>
								</c:choose>
								<td>${dictMaterial.zxauditorname }</td>
								<td><fmt:formatDate value="${dictMaterial.zxaudittime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>

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