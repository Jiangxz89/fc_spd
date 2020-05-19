<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药房发药单明细</title>
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
			<h4>药房发药单明细</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<form:form id="searchForm" modelAttribute="dataYfexedetail" action="${ctx}/pd/dataYfexedetail/list" method="post" class="breadcrumb form-search">
			<div class="newSearchBox" style="text-align: left;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<input id="masterid" name="masterid" type="hidden" value="${dataYfexedetail.masterid}"/>
					<label style="margin-left:5px;">项目名称 </label>
					<form:input path="feename" autocomplete="off" value="${dataYfexedetail.feename}"/>
					<label style="margin-left:5px;">厂家名称 </label>
					<form:input path="facname" autocomplete="off" value="${dataYfexedetail.facname}"/>
					<label style="margin-left:5px;">药品批次 </label>
					<form:input path="batch" autocomplete="off" value="${dataYfexedetail.batch}"/>
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
							<th>项目名称</th>
							<th>项目规格</th>
							<th>厂家名称</th>
							<th>单位</th>
							<th>副数</th>
							<th>发药数量</th>
							<th>单价</th>
							<th>总金额</th>
							<th>开方科室名称</th>
							<th>用量</th>
							<th>注射次数</th>
							<th>用法名称</th>
							<th>频次名称</th>
							<th>用量单位</th>
							<th>天数</th>
							<th>分组</th>
							<th>中药剂数</th>
							<th>方号</th>
							<th>状态</th>
							<th>批次</th>
							<th>备注</th>
							<th>是否自备药</th>
							<th>发药数量</th>
							<th>发药单位</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="dataYfexedetail">
							<tr>
								<td>${dataYfexedetail.feename }</td>
								<td>${dataYfexedetail.feespec }</td>
								<td>${dataYfexedetail.facname }</td>
								<td>${dataYfexedetail.unit }</td>
								<td>${dataYfexedetail.fuqty }</td>
								<td>${dataYfexedetail.amount }</td>
								<td>${dataYfexedetail.price }</td>
								<td>${dataYfexedetail.totalamt }</td>
								<td>${dataYfexedetail.diagndeptname }</td>
								<td>${dataYfexedetail.dosage }</td>
								<td>${dataYfexedetail.injecttimes }</td>
								<td>${dataYfexedetail.channelname }</td>
								<td>${dataYfexedetail.frequencryname }</td>
								<td>${dataYfexedetail.dosageunit }</td>
								<td>${dataYfexedetail.days }</td>
								<td>${dataYfexedetail.groupid }</td>
								<td>${dataYfexedetail.num }</td>
								<td>${dataYfexedetail.listno }</td>
								<td>${dataYfexedetail.status }</td>
								<td>${dataYfexedetail.batch }</td>
								<td>${dataYfexedetail.dspt }</td>
								<td>${dataYfexedetail.istake }</td>
								<td>${dataYfexedetail.presamount }</td>
								<td>${dataYfexedetail.presamountunit }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagination">${page}</div>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
			<a href="${ctx}/pd/dataYfexemaster/list" class="hcy-btn hcy-back" >返回</a>
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