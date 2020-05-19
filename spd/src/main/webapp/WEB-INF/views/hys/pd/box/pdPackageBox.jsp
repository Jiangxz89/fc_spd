<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdProduct" action="${ctx}/pd/pdPackage/getAllProduct" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="searchBox">
			<div>
				<label for="">产品编号</label>
				<input type="text" id="number" name="number" value="${pdProduct.number }"/>
			</div>
			<div>
				<label for="">产品名称</label>
				<input type="text" id="name" name="name" value="${pdProduct.name }"/>
			</div>
			<div>
				<label for="">产品分类</label>
				<select id="categoryId" name="categoryId">
					<option value="">--全部--</option>
					<c:forEach var="pdCategory" items="${pdCategoryList}">  
                        <option value="${pdCategory.id}" <c:if test="${pdProduct.categoryId eq pdCategory.id}">selected</c:if>>${pdCategory.name}</option>  
                    </c:forEach>
				</select>
			</div>
			<div>
				<label for="">产品组别</label>
				<select id="groupId" name="groupId">
					<option value="">--全部--</option>
					<c:forEach var="pdGroup" items="${pdGroupList}">  
                        <option value="${pdGroup.id}" <c:if test="${pdProduct.groupId eq pdGroup.id}">selected</c:if>>${pdGroup.name}</option>  
                    </c:forEach>
				</select>
			</div>
			<div>
				<label for="">产品型号</label>
				<input type="text" id="version" name="version" value="${pdProduct.version }"/>
			</div>
			<div>
				<label for="">生产厂家</label>
				<input type="hidden" name="venderName" value="" />
				<select id="venderId" name="venderId" style="width:346px;">
					<option value="">--全部--</option>
					<c:forEach var="pdVender" items="${pdVenderList}">  
                        <option value="${pdVender.id}" <c:if test="${pdProduct.venderId eq pdVender.id}">selected</c:if>>${pdVender.name}</option>  
                    </c:forEach> 
				</select>
			</div>
			<div>
				<label for="">产品权限</label>
				<select id="power" name="power">
					<option value="">--全部--</option>
					<c:forEach var="dict" items="${fns:getDictList('product_power') }">
						<option value="${dict.label eq '公共产品'?'0':'1' }" <c:if test="${pdProduct.power eq dict.label}">selected</c:if>>${dict.label }</option>
					</c:forEach>
					<%-- <option value="公共产品" <c:if test="${pdProduct.power eq '公共产品'}">selected</c:if>>公共产品</option>
					<option value="自有产品" <c:if test="${pdProduct.power eq '自有产品'}">selected</c:if>>自有产品</option> --%>
				</select>
			</div>
			<!-- <input id="search" class="hcy-btn hcy-btn-primary" type="button" value="查询"/> -->
			<input id="btnSubmit" class="hcy-btn hcy-search" style="height: inherit;line-height:1.5;" type="submit" value="查询"/>
			<a href="###" onclick="clearConditions()" class="hcy-btn hcy-reset">重置</a>
		</div>
	
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table class="hcy-public-table" id="addContentTab" style="padding-top:0;">
			<thead>
				<tr>
					<th><input type="checkbox" id="chkAll" name="chkAll" /></th>
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
			<tbody id="tbody">
				<c:forEach items="${page.list }" var="prod">
					<tr>
						<td><input type="checkbox" name="chkList" /></td>
						<input type="hidden" class="addProId" value="${prod.id }" />
						<td class="addProNum"><a href="###" title="${prod.number }" >${prod.number }</a></td>
						<td class="addProName"><a href="###" title="${prod.name }">${prod.name }</a></td>
						<td class="addProType">${spd:getCategoryName(prod.categoryId) }</td>
						<td class="addProGroup">${spd:getGroupName(prod.groupId) }</td>
						<td class="addProUnit">${prod.unit }</td>
						<td class="addProformat">${prod.spec }</td>
						<td class="addProCode">${prod.version }</td>
						<td class="addProFoc">${spd:getVenderName(prod.venderId) }</td>
						<input type="hidden" class="addProRegistration" value="${prod.registration }" />
						<input type="hidden" class="addProDescription" value="${prod.description }" />
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
		</form:form>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//全选与反选
			$("#chkAll").click(function(){
				if($(this).attr('checked')){
					$("input[type='checkbox']").attr('checked','true');
				}else{
					$("input[type='checkbox']").removeAttr('checked');
				}
			});
			
			//生产厂家改变
	/* 		$("[name=venderId]").change(function(){
				var name = $("[name=venderId]>option:checked").text();
				var id = $("[name=venderId]>option:checked").val();
				$("[name=venderName]").val(name);
				$("[name=venderId]").val(id);
			}) */
			
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function compositeHtml(index){
			var chObj = $("input[type='checkbox']:gt(0):checked");
			if(chObj.length < 1)
				return 0;
			var html = '', indexVue = null;
			chObj.each(function(i,v){
				indexVue = Number(index) + Number(i);
				var $this = $(this).parent().parent();
				if($.inArray($this.data('id'), parent.prods) > -1)
					return true;//继续下一循环，类似for里的continue;
					
				var b = window.parent.repetitionDel($this.find(".addProId").val());
				
				if(b != 'success')
					return true;
				
				html+='<tr id="'+$this.find(".addProId").val()+'"><input type="hidden" /><input type="hidden" name="child['+indexVue+'].productId" value="'+$this.find(".addProId").val()+'" />'+
				'<td><a href="###" title="'+$this.find(".addProNum>a").text()+'" >'+$this.find(".addProNum>a").text()+'</a></td>'+
				'<td><a href="###"  title="'+$this.find(".addProName>a").text()+'">'+$this.find(".addProName>a").text()+'</a></td>'+
				'<td>'+$this.find(".addProType").text()+'</td>'+
				'<td>'+$this.find(".addProGroup").text()+'</td>'+
				'<td>'+$this.find(".addProUnit").text()+'</td>'+
				'<td>'+$this.find(".addProformat").text()+'</td>'+
				'<td>'+$this.find(".addProCode").text()+'</td>'+
				'<td>'+$this.find(".addProFoc").text()+'</td>'+
				'<td>'+$this.find(".addProRegistration").val()+'</td>'+
				'<td class="vue"><input name="child['+indexVue+'].productCount" type="text" style="width:60px;" required /></td>'+
				'<td>'+$this.find(".addProDescription").val()+'</td>'+
				'<td>'+
					'<a href="###" onclick="toProductDetail(this)" class="hcy-btn hcy-btn-o" style="margin-right:3px;">查看</a>'+
					'<a href="###" onclick="del(\''+$this.find(".addProId").val()+'\')" class="hcy-btn hcy-btn-o delTrBtn">删除</a>'+
				'</td>'+
			'</tr>'
			//$("#fixedNumTable>tbody").html("");
			});
			return html;
		}
		
		//清空查询条件
		function clearConditions(){
			$("#number").val("");
			$("#name").val("");
			$("#categoryId").val("");
			$("#groupId").val("");
			$("#version").val("");
			$("#venderId").val("");
			$("#power").val("");
		}
	</script>
</body>
</html>