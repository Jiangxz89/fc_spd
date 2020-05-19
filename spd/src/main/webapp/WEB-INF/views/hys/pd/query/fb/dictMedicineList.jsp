<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>中心药品目录</title>
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
			<h4>中心药品目录</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="dictMedicine" action="${ctx}/pd/dictMedicine/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<label style="margin-left:5px;">药品名称 </label>
					<form:input path="cname" autocomplete="off" value="${dictMedicine.cname}"/>
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
							<th>数字码</th>
							<th>批准文号 </th>
							<th>中文名称 </th>
							<th>英文名称 </th>
							<th>通用名</th>
							<th>拉丁名 </th>
							<th>拼音码 </th>
							<th>五笔码</th>
							<th>PY代码</th>
							<th>wb代码</th>
							<th>自定义码</th>
							<th>项目分类</th>
							<th>药品大类</th>
							<th>药品类别</th>
							<th>药品剂型</th>
							<th>药物剂型</th>
							<th>大单位</th>
							<th>大小单位系数</th>
							<th>小单位</th>
							<th>剂量单位</th>
							<th>剂量数</th>
							<th>规格</th>
							<th>剂量单位</th>
							<th>剂量单位转换系数</th>
							<th>默认用量</th>
							<th>最大剂量</th>
							<th>购进价</th>
							<th>零售价</th>
							<th>最高零售价</th>
							<th>是否GMP认证</th>
							<th>是否麻醉药</th>
							<th>是否毒性药</th>
							<th>是否基本用药</th>
							<th>是否贵重药</th>
							<th>是否精神药</th>
							<th>是否中标</th>
							<th>是否皮试药</th>
							<th>是否医保类型</th>
							<th>是否停用</th>
							<th>是否抗生素</th>
							<th>修改人</th>
							<th>修改时间</th>
							<th>使用范围</th>
							<th>医嘱打印分类</th>
							<th>社保编码</th>
							<th>农合编码</th>
							<th>厂家名称</th>
							<th>状态</th>
							<th>审核人</th>
							<th>取整方式</th>
							<th>医疗机构名称</th>
							<th>是否激素药</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="dictMedicine">
							<tr>
								<td>${dictMedicine.numcode }</td>
								<td>${dictMedicine.number }</td>
								<td>${dictMedicine.cname }</td>
								<td>${dictMedicine.ename }</td>
								<td>${dictMedicine.tname }</td>
								<td>${dictMedicine.lname }</td>
								<td>${dictMedicine.pycode }</td>
								<td>${dictMedicine.wbcode }</td>
								<td>${dictMedicine.pycodet }</td>
								<td>${dictMedicine.wbcodet }</td>
								<td>${dictMedicine.cuscode }</td>
								<td>${dictMedicine.invclass }</td>
								<td>${dictMedicine.itemclass }</td>
								<td>${dictMedicine.drugclass }</td>
								<td>${dictMedicine.drugtype }</td>
								<td>${dictMedicine.drugform }</td>
								<td>${dictMedicine.packunit }</td>
								<td>${dictMedicine.packamount }</td>
								<td>${dictMedicine.miniunit }</td>
								<td>${dictMedicine.doseunit }</td>
								<td>${dictMedicine.dosenum }</td>
								<td>${dictMedicine.spec }</td>
								<td>${dictMedicine.dosunit }</td>
								<td>${dictMedicine.dosage }</td>
								<td>${dictMedicine.basedos }</td>
								<td>${dictMedicine.maxdos }</td>
								<td>${dictMedicine.inprice }</td>
								<td>${dictMedicine.sellprice }</td>
								<td>${dictMedicine.hstprice }</td>
								<c:choose>
									<c:when test="${dictMedicine.isgmp==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMedicine.isaze==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMedicine.isposion==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMedicine.isbasic==1}">
										<td>省基目录</td>
									</c:when>
									<c:when test="${dictMedicine.isbasic==2}">
										<td>国基目录</td>
									</c:when>
									<c:when test="${dictMedicine.isbasic==3}">
										<td>国、省基目录</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMedicine.iscostly==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMedicine.islunacy==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMedicine.isbid==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMedicine.isskin==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMedicine.mretype==1}">
										<td>甲类</td>
									</c:when>
									<c:when test="${dictMedicine.mretype==2}">
										<td>乙类</td>
									</c:when>
									<c:when test="${dictMedicine.mretype==3}">
										<td>丙类</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMedicine.isstop==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMedicine.isantibiotic==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<td>${dictMedicine.modstaffname }</td>
								<td><fmt:formatDate value="${dictMedicine.moddate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<c:choose>
									<c:when test="${dictMedicine.userange==1}">
										<td>门诊可用</td>
									</c:when>
									<c:when test="${dictMedicine.userange==2}">
										<td>住院可用</td>
									</c:when>
									<c:otherwise>
										<td>门诊住院可用</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dictMedicine.printclass==0}">
										<td>注射剂</td>
									</c:when>
									<c:when test="${dictMedicine.printclass==1}">
										<td>大输液</td>
									</c:when>
									<c:when test="${dictMedicine.printclass==2}">
										<td>口服药</td>
									</c:when>
									<c:when test="${dictMedicine.printclass==3}">
										<td>药械</td>
									</c:when>
									<c:when test="${dictMedicine.printclass==4}">
										<td>外用</td>
									</c:when>
									<c:otherwise>
										<td>其他</td>
									</c:otherwise>
								</c:choose>
								<td>${dictMedicine.sicode }</td>
								<td>${dictMedicine.nhcode }</td>
								<td>${dictMedicine.facname }</td>
								<c:choose>
									<c:when test="${dictMedicine.zxauditstatus==1}">
										<td>已审核</td>
									</c:when>
									<c:when test="${dictMedicine.zxauditstatus==2}">
										<td>审核未通过</td>
									</c:when>
									<c:otherwise>
										<td>未审核</td>
									</c:otherwise>
								</c:choose>
								<td>${dictMedicine.zxauditorname }</td>
								<c:choose>
									<c:when test="${dictMedicine.roundingmode==0}">
										<td>总量取整</td>
									</c:when>
									<c:otherwise>
										<td>单次取整</td>
									</c:otherwise>
								</c:choose>
								<td>${dictMedicine.hospitalname }</td>
								<c:choose>
									<c:when test="${dictMedicine.ishormone==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
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