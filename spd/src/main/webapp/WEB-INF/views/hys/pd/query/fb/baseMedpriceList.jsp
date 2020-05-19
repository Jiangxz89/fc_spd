<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>本院药品目录</title>
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
			<h4>本院药品目录</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="baseMedprice" action="${ctx}/pd/baseMedprice/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<label style="margin-left:5px;">厂家名称 </label>
					<form:input path="facname" autocomplete="off" value="${baseMedprice.facname}"/>
					<input id="btnSubmit" onclick="return page();" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
					<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</div>
		</form:form>

		<sys:message content="${message}"/>
		<div class="showTableBox" style="width:100%;overflow:auto;">
			<div class="tableBox">
				<table id="contentTable" class="hcy-public-table" style="padding:0;width:125rem;">
					<thead>
						<tr>
							<%--<th>医疗机构名称</th>--%>
							<th>自定义码</th>
							<th>社保编码 </th>
							<th>厂家名称 </th>
							<th>批准文号 </th>
							<th>购进价</th>
							<th>零售价 </th>
							<th>用法名称 </th>
							<th>频次名称</th>
							<th>禁忌</th>
							<th>库存上限</th>
							<th>库存下限</th>
							<th>存放位置</th>
							<th>使用范围</th>
							<th>是否停用</th>
							<th>修改人</th>
							<th>修改时间</th>
							<th>是否可拆</th>
							<th>注射室取药</th>
							<th>住院取整发药</th>
							<th>住院免发药</th>
							<th>门诊免发药</th>
							<th>门诊不打标签</th>
							<th>药品资料</th>
							<th>说明</th>
							<th>剂量限制</th>
							<th>用法限定</th>
							<th>特别提示</th>
							<th>门诊停用</th>
							<th>住院停用</th>
							<th>皮试方法</th>
							<th>权限</th>
							<th>农合编码</th>
							<th>是否通过社保审核</th>
							<th>项目分类</th>
							<th>审核状态</th>
							<th>审核人</th>
							<th>审核时间</th>
							<th>医嘱打印分类</th>
							<th>说明书</th>
							<th>说明书更新时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="baseMedprice">
							<tr>
								<%--<td>${baseMedprice.hospitalname }</td>--%>
								<td>${baseMedprice.cuscode }</td>
								<td>${baseMedprice.sicode }</td>
								<td>${baseMedprice.facname }</td>
								<td>${baseMedprice.number }</td>
								<td>${baseMedprice.inprice }</td>
								<td>${baseMedprice.sellprice }</td>
								<td>${baseMedprice.usgname }</td>
								<td>${baseMedprice.fcyname }</td>
								<td>${baseMedprice.taboo }</td>
								<td>${baseMedprice.maxstock }</td>
								<td>${baseMedprice.minstock }</td>
								<td>${baseMedprice.position }</td>
								<c:choose>
									<c:when test="${baseMedprice.userange==0}">
										<td>门诊住院可用</td>
									</c:when>
									<c:when test="${baseMedprice.userange==1}">
										<td>门诊可用</td>
									</c:when>
									<c:otherwise>
										<td>住院可用</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${baseMedprice.isstop==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<td>${baseMedprice.modstaffname }</td>
								<td><fmt:formatDate value="${baseMedprice.moddate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<c:choose>
									<c:when test="${baseMedprice.split==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${baseMedprice.injection==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${baseMedprice.hospitalized==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${baseMedprice.freestrike==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${baseMedprice.outpatient==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${baseMedprice.label==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<td>${baseMedprice.detail }</td>
								<td>${baseMedprice.explain }</td>
								<td>${baseMedprice.limit }</td>
								<td>${baseMedprice.limited }</td>
								<td>${baseMedprice.prompt }</td>
								<c:choose>
									<c:when test="${baseMedprice.mzstop==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${baseMedprice.zystop==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<td>${baseMedprice.skinmethod }</td>
								<c:choose>
									<c:when test="${baseMedprice.competence==0}">
										<td>否</td>
									</c:when>
									<c:otherwise>
										<td>是</td>
									</c:otherwise>
								</c:choose>
								<td>${baseMedprice.nhcode }</td>
								<c:choose>
									<c:when test="${baseMedprice.audstate==0}">
										<td>未上传</td>
									</c:when>
									<c:when test="${baseMedprice.audstate==1}">
										<td>已上传</td>
									</c:when>
									<c:when test="${baseMedprice.audstate==2}">
										<td>通过审核</td>
									</c:when>
									<c:otherwise>
										<td>未通过审核</td>
									</c:otherwise>
								</c:choose>
								<td>${baseMedprice.invclass }</td>
								<c:choose>
									<c:when test="${baseMedprice.auditstatus==0}">
										<td>未审核</td>
									</c:when>
									<c:otherwise>
										<td>已审核</td>
									</c:otherwise>
								</c:choose>
								<td>${baseMedprice.auditorname }</td>
								<td><fmt:formatDate value="${baseMedprice.audittime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<c:choose>
									<c:when test="${baseMedprice.adviceprintclass==0}">
										<td>注射剂</td>
									</c:when>
									<c:when test="${baseMedprice.adviceprintclass==1}">
										<td>大输液</td>
									</c:when>
									<c:when test="${baseMedprice.adviceprintclass==2}">
										<td>口服药</td>
									</c:when>
									<c:when test="${baseMedprice.adviceprintclass==3}">
										<td>药械</td>
									</c:when>
									<c:when test="${baseMedprice.adviceprintclass==4}">
										<td>外用</td>
									</c:when>
									<c:otherwise>
										<td>其他</td>
									</c:otherwise>
								</c:choose>
								<td>${baseMedprice.directions }</td>
								<td><fmt:formatDate value="${baseMedprice.dirupdatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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