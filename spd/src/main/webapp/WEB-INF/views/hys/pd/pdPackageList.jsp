<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>定数包表管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
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
			<h4>定数包表管理</h4>
			<a href="${ctx}/pd/pdPackage/form" class="hcy-btn hcy-btn-primary">添加定数包</a>
			<!-- <a href="###" class="hcy-btn hcy-btn-primary" id="import">批量导入</a> -->
		</div>
		<div class="searchBox">
			<form:form id="searchForm" style="padding:0;background:none;margin:0;" modelAttribute="pdPackage" action="${ctx}/pd/pdPackage/" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<label for="">定数包编号</label>
				<input type="text" id="number" name="number" value="${pdPackage.number }" />
				<label for="">定数包名称</label>
				<input type="text" id="name" name="name" value="${pdPackage.name }" />
				<input id="btnSubmit" class="hcy-btn hcy-search" style="height: inherit;line-height:1.5;" type="submit" value="查询"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table">
				<thead>
					<tr>
						<th>定数包编号</th>
						<th>定数包名称</th>
						<th>产品数量</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list}" var="pdPackage">
						<tr>
							<td>${pdPackage.number }</td>
							<td>
								${pdPackage.name }
							</td>
							<td>
								${pdPackage.sum}
							</td>
							<td>
								<a href="${ctx}/pd/pdPackage/detail?id=${pdPackage.id}&flag=detail" class="hcy-btn hcy-btn-o">查看</a>
			    				<a href="${ctx}/pd/pdPackage/detail?id=${pdPackage.id}&flag=update" class="hcy-btn hcy-btn-o">修改</a>
			    				<a href="${ctx}/pd/pdPackage/detail?id=${pdPackage.id}&flag=copy" class="hcy-btn hcy-btn-o">复制</a>
								<a href="###" onclick="del('${pdPackage.id}')" class="hcy-btn hcy-btn-o">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/js/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		
		function del(id){
			var id = id;
			layer.confirm(
					"确认删除当前定数包？",
					{//options这里写对应的参数需求，如图标，提示文字，按钮名称等
						icon:3,
						title:"提示",
						btn:["确定","取消"]
					},
					function(index){
						layer.close(index);
						$.ajax({
							url:"${ctx}/pd/pdPackage/delete",
							type:"POST",
							data:{id:id},
							dataType:"text",
							success:function(data){
								if(data == 'alloError'){
									layer.alert("该定数包与调拨单关联，无法删除",{icon:0},function(index){
										layer.close(index);
									});
								}else if(data == 'applyError'){
									layer.alert("该产品与申购单关联，无法删除",{icon:0},function(index){
										layer.close(index);
									});
								}else if(data == 'success'){
									layer.alert("删除成功",{icon:0},function(index){
										layer.close(index);
									});
									window.location = "${ctx}/pd/pdPackage/";
								}else{
									layer.alert("发生未知错误，请联系管理员",{icon:0},function(index){
										layer.close(index);
									});
								}
							}
						})
					},
					function(index){
						layer.close(index);
					}
				)
		}
		
	</script>
</body>
</html>