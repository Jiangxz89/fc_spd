<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<title>添加产品</title>
</head>
<body>
	<div class="addAlloactionBox">
	<form:form id="searchForm" modelAttribute="pdProduct" method="post" action="${ctx}/pd/pdPackage/getAllProductByAllocation">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="" name="storeroomId" type="hidden" value="${pdProduct.storeroomId}"/>
		<input id="" name="ids" type="hidden" value="${ids}"/>
		<div class="newSearchBox" style="text-align: left;">
			<label for="">产品编号</label>
			<input type="text" autocomplete="off" name="number" value="${pdProduct.number }"/>
			<label for="">产品分类</label>
			<form:select path="categoryId" class="input-medium">
				<form:option value="">全部</form:option>
				<form:options items="${spd:findPdCategoryList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</form:select>
			<label style="margin-left:5px;" for="">产品组别</label>
			<form:select path="groupName" class="input-medium">
				<form:option value="" label="全部"/>
				<form:options items="${spd:findPdGroupList() }" itemLabel="name" itemValue="name" htmlEscape="false"/>
			</form:select>
			<br />
			<label for="">产品名称</label>
				<%--<input type="text"  name="name" value="${pdProduct.name }"/>--%>
			<input class="select2_material form-control" style="width: 260px" id="productNameSelect" name="name" >
			<label style="margin-left:40px;" for="">生产厂家</label>
			<form:select style="width: 260px;" path="venderId" class="input-medium">
				<form:option value="" label="全部"/>
				<form:options items="${spd:findPdVenderList() }" itemLabel="nameAndpinyin" itemValue="id" htmlEscape="false"/>
			</form:select>
			<br />
			<!-- <label for="">产品分类</label>
			<select name=categoryId id="">
				<option value="">全部</option>
			</select> -->
			<label for="" >产品规格</label>
			<input type="text" autocomplete="off" id="productSpecInput" name="spec" value="${pdProduct.spec }"/>
			<label for="">产品型号</label>
			<input type="text" autocomplete="off" id="productVersionInput"  name="version" value="${pdProduct.version }"/>
			<%--<input type="submit" style="line-height:0.4;" value="查询" class="hcy-btn hcy-btn-primary" />--%>
			<input type="submit"  onclick="return page();" value="查询" class="hcy-btn hcy-btn-primary" />
			<a href="###" class="hcy-btn hcy-reset" >重置</a>
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
					<th>规格</th>
					<th>型号</th>
					<!-- <th>产品数量</th> -->
					<th>单位</th>
					<th>本库库存数量</th>
					<c:if test="${viewPermission eq '0'}">
						<th>出库库存数量</th>
					</c:if>
					<th>生产厂家</th>
				</tr>
			</thead>
			<tbody id="tdodyID">
				<c:forEach var="pdProduct" items="${pdProductList }">
				<%-- <c:if test="${pdProduct.stockNum ne '0'}">
				</c:if> --%>
					<tr style="cursor:pointer"  data-id="${pdProduct.id }" data-number="${pdProduct.number }" data-name="${pdProduct.name }" data-unit="${pdProduct.unit }" data-spec="${pdProduct.spec }"
					    data-version="${pdProduct.version }"  data-vender="${pdProduct.venderName }" data-selfstock="${pdProduct.selfStockNum }" data-stock="${pdProduct.stockNum }" data-barcode="${pdProduct.barCode }" 
					    data-batchno="${pdProduct.batchNo}">
						<td><input type="checkbox" /></td>
						<td class="addProNum">${pdProduct.number }</td>
						<td class="addProName">${pdProduct.name }</td>
						<td class="addCategoryName">${pdProduct.categoryName }</td>
						<td class="addGroupName">${pdProduct.groupName }</td>
						<td class="addProformat">${pdProduct.spec }</td>
						<td class="addProCode">${pdProduct.version }</td>
						<!-- <td class="">-</td> -->
						<td class="addProUnit">${pdProduct.unit }</td>
						<td class="addSelfStockNum">${pdProduct.selfStockNum }</td>
						<c:if test="${viewPermission eq '0'}">
							<td class="addProStockNum">${pdProduct.stockNum }</td>
						</c:if>
						<td class="addVenderName"><a href="###" class="overLook" title="${pdProduct.venderName }">${pdProduct.venderName }</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page }</div>
	</div>
	<!--  -->
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){

            var select2 = function () {
                $("#productNameSelect").select2({
                    dropdownParent: $('.modal-content'),
                    /*placeholder: "产品名称",*/
                    allowClear: true,
                    ajax: {
                        url: '${ctx}/pd/pdProduct/findList',
                        dataType: 'json',
                        data: function (params) {
                            var query = {
                                name: params //params.term 搜索参数值
                            }
                            return query;
                        },
                        results: function (data) {
                            //返回最终数据data 给dataArray
                            var dataArray = [];
                            for (var i = 0; i < data.length; i++) {
                                var dataObj = {};
                                dataObj.id = data[i].name;
                                dataObj.text = data[i].name ;
                                dataArray.push(dataObj);
                            }
                            return {
                                results: dataArray
                            };
                        },
                        error: function (error) {
                        }
                    }
                });
            }
            select2();

            $("#tdodyID tr").click(function () {
                var input = $(this).find("input[type=checkbox]");//获取checkbox
                if(input.attr("checked")){
                    input.attr("checked",false);
                }else{
                    input.attr("checked",true);
                }
            });

            //checkbox冒泡事件
            $("input[type='checkbox']").click(function(e) {
                e.stopPropagation();
            })

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
		
		//-调拨单新增产品
		function compositeHtml(index){
			var chObj = $("input[type='checkbox']:gt(0):checked");
			if(chObj.length < 1)
				return 0;
			var html = '', indexVue = null;
			var isView = '${viewPermission}';
			chObj.each(function(i,v){
				indexVue = Number(index) + Number(i);
				var $this = $(this).parent().parent();
                if($.inArray($this.data('id'), parent.prods) > -1)
                    return true;//继续下一循环，类似for里的continue;
				var stock = $this.data("stock");
				html += '<tr class="remove_'+$this.data("id")+' productClass" data-id="'+$this.data("id")+'">'
					 +		'<td><i class="fa fa-trash-o" onclick="removeDiv(\''+$this.data("id")+'\');"></i><!--<i class="fa fa-search"></i>--></td>'
					 +		'<td>-</td>'
					 +		'<td>-</td>'
					/* +  	'<td><a href="###" class="overLook" title="'+$this.data("name")+'">'+$this.data("name")+'</a></td>'
					 +		'<td><a href="###" title="'+$this.data("number")+'"  class="overLook">'+$this.data("number")+'</a></td>'2019年7月3日10:50:56 调拨时使用tab快速定位输入数量*/
						+  	'<td>'+$this.data("name")+'</td>'
						+	'<td>'+$this.data("number")+'</td>'
					 +		'<td>'+$this.data("spec")+'</td>'
					 +		'<td>'+$this.data("version")+'</td>'
					 +		'<td>-</td>'
					 +		'<td>'+$this.data("unit")+'</td>'
					 +		'<td>'+$this.data("selfstock")+'</td>';
					 if(isView=='0'){
						 html += '<td>'+stock+'</td>';
					 }
					 /*html +='<td class="applyNumClass"><input type="text"  placeholder="请输入1-9999之间的整数" class="sumClass" onchange="allocationSum(1)" name="pdAllocationProductList['+indexVue+'].allocationNum" maxlength="4"/></td>'*/
                html +='<td class="applyNumClass"><input type="text" style="width:60px;" autocomplete="off"   class="sumClass"  name="pdAllocationProductList['+indexVue+'].allocationNum" /></td>'
					 +      '<input type="hidden" name="pdAllocationProductList['+indexVue+'].productCode" value="'+$this.data("id")+'"/>'
					 +      '<input type="hidden" name="pdAllocationProductList['+indexVue+'].productAttr" value="1"/>'
					 +      '<input type="hidden" class="allocationCodeClass" name="pdAllocationProductList['+indexVue+'].allocationCode" value=""/>'
					 + '</tr>';
                parent.prods.push($this.data("id"));
			});
			return html;
		}
        //重置
        $(".hcy-reset").click(function(){
            $(".newSearchBox input[type='text']").val("");
            $("#categoryId").select2("val", "");
            $("#groupId").select2("val", "");
            $("#venderId").select2("val", "");
            $("#productSpecInput").val("");
            $("#productVersionInput").val("");
        })
	</script>
</body>
</html>