<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医院药品报损丢失单明细</title>
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
			<h4>医院药品报损丢失单明细</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="dataDetailprofit" action="${ctx}/pd/dataDetailprofit/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<input id="masterid" name="masterid" type="hidden" value="${dataDetailprofit.masterid}"/>
					<label style="margin-left:5px;">药品名称 </label>
					<form:input path="medname" autocomplete="off" value="${dataDetailprofit.medname}"/>
					<label style="margin-left:5px;">药品批次 </label>
					<form:input path="batch" autocomplete="off" value="${dataDetailprofit.batch}"/>
					<label style="margin-left:5px;">药品批号 </label>
					<form:input path="batchno" autocomplete="off" value="${dataDetailprofit.batchno}"/>
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
							<th>单位</th>
							<th>规格</th>
							<th>购进价</th>
							<th>零售价</th>
							<th>批次</th>
							<th>有效期</th>
							<th>类型</th>
							<th>损益数</th>
							<th>调整原因</th>
							<th>批号</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="dataDetailprofit">
							<tr>
								<td>${dataDetailprofit.medname }</td>
								<td>${dataDetailprofit.unit }</td>
								<td>${dataDetailprofit.spec }</td>
								<td>${dataDetailprofit.inprice }</td>
								<td>${dataDetailprofit.sellprice }</td>
								<td>${dataDetailprofit.batch }</td>
								<td><fmt:formatDate value="${dataDetailprofit.expidate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<c:choose>
									<c:when test="${dataDetailprofit.type==1}">
										<td>益</td>
									</c:when>
									<c:otherwise>
										<td>损</td>
									</c:otherwise>
								</c:choose>
								<td>${dataDetailprofit.qty }</td>
								<td>${dataDetailprofit.reason }</td>
								<td>${dataDetailprofit.batchno }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagination">${page}</div>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
			<a href="${ctx}/pd/dataMasterprofit/list" class="hcy-btn hcy-back" >返回</a>
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