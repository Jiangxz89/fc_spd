<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商信息管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<style>
		.factoryTab .red td,.factoryTab .red td a{color: red}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
		function query(){
			$("#pageNo").val(1);
			$("#searchForm").attr("action","${ctx}/pd/pdSupplier/");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<div class="right-main-box"> 
		<div class="btnBox">
			<%-- <a href="${ctx}/pd/pdSupplier/" class="hcy-btn hcy-btn-primary">供应商信息列表</a> --%>
			<h4>供应商信息管理</h4>
			<shiro:hasPermission name="pd:pdSupplier:edit"><a href="${ctx}/pd/pdSupplier/form?flag=add" class="hcy-btn hcy-btn-primary">供应商信息添加</a></shiro:hasPermission>
		</div>
		<div class="newSearchBox">
			<form:form id="searchForm" style="padding:0;background:none;margin:0;" modelAttribute="pdSupplier" action="${ctx}/pd/pdSupplier/" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<label style="width:80px;">供应商名称：</label>
				<%--<form:input path="supplierName" htmlEscape="false" maxlength="100" class="input-medium"/>--%>
				<form:select path="supplierName"  style="width: 260px"   class="input-medium">
					<form:option value="" label="全部(qb)"/>
					<form:options items="${fns:findSupplierList() }" itemLabel="nameAndpinyin" itemValue="supplierName" htmlEscape="false"/>
				</form:select>
				<input id="btnSubmit" onclick="return page();" class="hcy-btn hcy-search" style="height:inherit;line-height:1.5 ;" type="submit" value="查询"/>
				<input class="hcy-btn hcy-btn-primary"" type="button" style="height:inherit;line-height:1.5 ;" onclick="batchDelete()" value="批量删除"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table factoryTab" style="padding:0;">
				<thead>
					<tr>
						<th><input type="checkbox" name="find_watch_all" id="all" onclick="cli('find_watch');"></th>
						<th>供应商名称</th>
						<th>创建时间 </th>
						<th>更新时间 </th>
						<th>证照是否过期</th>
						<shiro:hasPermission name="pd:pdSupplier:edit"><th>操作</th></shiro:hasPermission>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="pdSupplier">
					<%--<tr>--%>
					<c:if test="${not empty pdSupplier.avlType}">
						<c:choose>
							<c:when test="${pdSupplier.avlType=='1'|| pdSupplier.avlType==2}"><tr class="red"></c:when>
						</c:choose>
					</c:if>
					<c:if test="${empty pdSupplier.avlType}"><tr></c:if>
						<td><input value="${pdSupplier.id }" class="findInp" name="find_watch" type="checkbox"  style="vertical-align:middle;margin-right:5px;"></td>
						<td><a href="${ctx}/pd/pdSupplier/toUpdate?id=${pdSupplier.id}&flag=see">
							${pdSupplier.supplierName}
						</a></td>
						<td>
							<fmt:formatDate value="${pdSupplier.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<fmt:formatDate value="${pdSupplier.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>${fns:getDictLabel(pdSupplier.avlType, 'avl_type', '')}</td>
						<shiro:hasPermission name="pd:pdSupplier:edit"><td>
							<a href="${ctx}/pd/pdSupplier/toUpdate?id=${pdSupplier.id}&flag=see" class="hcy-btn hcy-btn-o">查看</a>
		    				<a href="${ctx}/pd/pdSupplier/toUpdate?id=${pdSupplier.id}&flag=update" class="hcy-btn hcy-btn-o">修改</a>
							<a href="${ctx}/pd/pdSupplier/delete?id=${pdSupplier.id}" class="hcy-btn hcy-btn-o" onclick="return confirmx('确认要删除该供应商信息吗？', this.href)">删除</a>
						</td></shiro:hasPermission>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<script src="${ctxStatic}/hcy/js/layer.js"></script>
	<script type="text/javascript">
	//全选 
	function cli(Obj)
	 {
		 var collid = document.getElementById("all")
	 	 var coll = document.getElementsByName(Obj)
	 	 if (collid.checked){
	  	 	for(var i = 0; i < coll.length; i++)
	  		coll[i].checked = true;
	 	}else{
	  		for(var i = 0; i < coll.length; i++)
	  		coll[i].checked = false;
	 	}
	 }
	//批量删除
	 function batchDelete(){
		 var text="";  
	      $("input[name=find_watch]").each(function() {  
	         if ($(this).prop("checked")) {  
	             text += ","+$(this).val();  
	         }  
	      }); 
	      if(text != null && text != ''){
	    	  $("#searchForm").attr("action","${ctx}/pd/pdSupplier/batchDelete?pdSupplierIds="+text);
			  $("#searchForm").submit();
	      }else{
	    	  layer.alert("请选择供应商！",{icon:2},function(index){
					layer.close(index);
				});
				return false;
	      }
	      return false;
	 }
	</script>
</body>
</html>