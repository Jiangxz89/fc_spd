<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>中心收费项目目录</title>
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
			<h4>中心收费项目目录</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="dictFee" action="${ctx}/pd/dictFee/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<%--<label>入库时间</label>
					<form:input path="queryDate" autocomplete="off" htmlEscape="false" id="inHomeTime" style="width:10.3rem;" readonly="readonly" maxlength="64" class="input-medium"/>
					<label style="margin-left:5px;">科室名称 </label>
					<form:input path="deptname" autocomplete="off" value="${dictFee.deptname}"/>
					<label style="margin-left:5px;">药品名称 </label>
					<form:input path="medname" autocomplete="off" value="${dictFee.medname}"/>--%>
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
							<th>名称 </th>
							<th>标准名称 </th>
							<th>项目编码 </th>
							<th>拼音码</th>
							<th>五笔码 </th>
							<th>自定义码 </th>
							<th>单位</th>
							<th>价格</th>
							<th>项目说明</th>
							<th>除外内容</th>
							<th>解释说明</th>
							<th>是否医保类型</th>
							<th>是否停用</th>
							<th>修改人</th>
							<th>修改时间</th>
							<th>社保代码</th>
							<th>农合编码</th>
							<th>状态</th>
							<th>审核人</th>
							<th>审核时间</th>
							<th>一级价格</th>
							<th>二级价格</th>
							<th>三级价格</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="dictFee">
							<tr>
								<%--<td>${dictFee.hospitalname }</td>--%>
								<td>${dictFee.xname }</td>
								<td>${dictFee.bname }</td>
								<td>${dictFee.feenumber }</td>
								<td>${dictFee.pycode }</td>
								<td>${dictFee.wbcode }</td>
								<td>${dictFee.cuscode }</td>
								<td>${dictFee.unit }</td>
								<td>${dictFee.price }</td>
								<td>${dictFee.contain }</td>
								<td>${dictFee.cwcontent }</td>
								<td>${dictFee.explain }</td>
								<c:choose>
									<c:when test="${dictFee.mretype==1}">
										<td>甲类</td>
									</c:when>
									<c:when test="${dictFee.mretype==2}">
										<td>乙类</td>
									</c:when>
									<c:when test="${dictFee.mretype==3}">
										<td>丙类</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictFee.isstop==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<td>${dictFee.modstaff }</td>
								<td><fmt:formatDate value="${dictFee.moddate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${dictFee.sicode }</td>
								<td>${dictFee.nhcode }</td>
								<c:choose>
									<c:when test="${dictFee.zxauditstatus==1}">
										<td>已审核</td>
									</c:when>
									<c:when test="${dictFee.zxauditstatus==2}">
										<td>审核未通过</td>
									</c:when>
									<c:otherwise>
										<td>未审核</td>
									</c:otherwise>
								</c:choose>
								<td>${dictFee.zxauditorname }</td>
								<td><fmt:formatDate value="${dictFee.zxaudittime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${dictFee.oneLevel }</td>
								<td>${dictFee.twoLevel }</td>
								<td>${dictFee.threeLevel }</td>
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