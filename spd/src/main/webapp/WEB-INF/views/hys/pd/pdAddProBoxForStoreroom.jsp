<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<title>添加产品</title>
</head>
<body>
	<div class="addProBox">
	<form:form id="searchForm" modelAttribute="pdProduct" method="post" action="${ctx}/pd/pdProduct/chooseProductListForStoreroom">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="searchBox" style="text-align: left;">
			<label for="">产品编号</label>
			<input type="text" name="number" value="${pdProduct.number }"/>
			<label for="">产品名称</label>
			<input type="text" name="name" value="${pdProduct.name }"/>
			<label for="">产品分类</label>
			<form:select path="categoryId">
				<form:option value="">全部</form:option>		
				<form:options items="${spd:findPdCategoryList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>	
			</form:select>
			<label for="">产品组别</label>			
			<form:select path="groupId" class="input-medium">
				<form:option value="" label="全部"/>
				<form:options items="${spd:findPdGroupList() }" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</form:select>
			<br />
			<label for="">产品型号</label>
			<input type="text" name="version" value="${pdProduct.version }"/>
			<label for="">生产厂家</label>			
			<form:select path="venderId" class="input-medium">
				<form:option value="" label="全部"/>
				<form:options items="${spd:findPdVenderList() }" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</form:select>
			<input type="submit"  value="查询" class="hcy-btn hcy-btn-primary" />
		</div>
	</form:form>
		<table class="hcy-public-table" id="addContentTab" style="padding-top:0;">
			<thead>
				<tr>
					<th><input type="checkbox" id="allchoose"/></th>
					<th>产品编号</th>
					<th>产品名称</th>
					<th>产品分类</th>
					<th>产品组别</th>
					<th>单位</th>
					<th>规格</th>
					<th>型号</th>
					<th>生产厂家</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="prod">
					<tr data-id="${prod.id }" data-number="${prod.number }" data-name="${prod.name }" data-unit="${prod.unit }" data-spec="${prod.spec }" 
					    data-version="${prod.version }"  data-vender="${prod.venderName }" data-registration="${prod.registration }" data-stock="0" data-price="${prod.pruPrice }" data-categoryname="${prod.categoryName }" data-groupname="${prod.groupName }">
						<td><input type="checkbox" /></td>
						<td class="addProNum"><a href="###" title="${prod.number }"  >${prod.number }</a></td>
						<td class="addProName"><a href="###"  title="${prod.name }">${prod.name }</a></td>
						<td class="addProType">${prod.categoryName }</td>
						<td class="addProGroup">${prod.groupName }</td>
						<td class="addProUnit">${prod.unit }</td>
						<td class="addProformat">${prod.spec }</td>
						<td class="addProCode">${prod.version }</td>
						<td class="addProFoc">${prod.venderName }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page }</div>
	</div>
	<!--  -->
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//全选与反选
			$("#allchoose").click(function(){
				if($(this).attr('checked')){
					$("input[type='checkbox']").attr('checked','true');
				}else{
					$("input[type='checkbox']").removeAttr('checked');
				}
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		//-采购单新增产品
		function compositeHtml(index){debugger;
			var chObj = $("input[type='checkbox']:gt(0):checked");
			if(chObj.length < 1)
				return 0;
			var html = '', indexVue = null;
			chObj.each(function(i,v){
				indexVue = Number(index) + Number(i);
				var $this = $(this).parent().parent();
				if($.inArray($this.data("id"),parent.prodIds) > -1){
					return true;
				}
				html += '<tr class="remove_'+$this.data("id")+'"  data-id="'+$this.data("id")+'">'
					 +      '<td><input type="checkbox"/></td>'
					 +		'<td><i class="fa fa-trash-o" onclick="removeDiv(\''+$this.data("id")+'\');"></i><i class="fa fa-search"></i></td>'
					 +  	'<td><a href="###"  title="'+$this.data("number")+'">'+$this.data("number")+'</a><input type="hidden" name="PdStoreroomProduct['+indexVue+'].productId" value="'+$this.data("id")+'"/></td>'
					 +		'<td><a href="###" title="'+$this.data("name")+'"  >'+$this.data("name")+'</a></td>'
					 +		'<td><a href="###" title="'+$this.data("categoryname")+'" class="overLook">'+$this.data("categoryname")+'</a></td>' 
					 +		'<td><a href="###" title="'+$this.data("groupname")+'" class="overLook">'+$this.data("groupname")+'</a></td>' 
					 +		'<td>'+$this.data("unit")+'</td>'
					 +		'<td>'+$this.data("spec")+'</td>'
					 +		'<td>'+$this.data("version")+'</td>'
					 +		'<td>'+$this.data("vender")+'</td>'
					 +		'<td>'+$this.data("registration")+'</td>'
					 +      '<td class="tdLeast"><input type="text" name="PdStoreroomProduct['+indexVue+'].lowStock" /></td>'
					 +      '<td class="tdMost"><input type="text" name="PdStoreroomProduct['+indexVue+'].highStock"/></td>'
					 + '</tr>';
				parent.prodIds.push($this.data('id'));
			});
			return html;
		}
	</script>
</body>
</html>