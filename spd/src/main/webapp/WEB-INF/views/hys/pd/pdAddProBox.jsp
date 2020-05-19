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
	<form:form id="searchForm" modelAttribute="pdProduct" method="post" action="${ctx}/pd/pdProduct/chooseProductList?remarks=${pdProduct.remarks }">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="newSearchBox" style="text-align: left;">
			<label for="">产品编号</label>
			<input type="text" autocomplete="off" id="productNameInput" name="number" value="${pdProduct.number }"/>
			<label for="">产品分类</label>
			<form:select path="categoryId" class="input-medium">
				<form:option value="">全部</form:option>
				<form:options items="${spd:findPdCategoryList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</form:select>
			<label for="" style="margin-left:5px;">产品组别</label>
			<form:select path="groupId"  class="input-medium">
				<form:option value="" label="全部"/>
				<form:options items="${spd:findPdGroupList() }" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</form:select>
			</br>
			<label for="">产品名称</label>
			<input class="select2_material form-control" style="width: 260px" id="productNameSelect" name="name" >
			<label for="" style="margin-left:40px;" >生产厂家</label>
			<form:select path="venderId" class="input-medium" style="width: 260px;">
				<form:option value="" label="全部(qb)"/>
				<form:options items="${spd:findPdVenderList() }" itemLabel="nameAndpinyin" itemValue="id" htmlEscape="false"/>
			</form:select>
			</br>
			<c:if test="${fns:getUser().storeroomType == '0'}">
				<label for="" <%--style="margin-left:5px;"--%>>供应商</label>
				<form:select path="supplierId" class="input-medium" style="width: 260px;">
					<form:option value="" label="全部(qb)"/>
					<form:options items="${spd:findSupplierList()}" itemLabel="nameAndpinyin" itemValue="id" htmlEscape="false"/>
				</form:select>
				<label style="margin-left:25px;" for="" >注册证号</label>
				<input type="text" autocomplete="off" id="productRegistrationInput" name="registration" value="${pdProduct.registration }"/>
				</br>
			</c:if>
			<c:if test="${fns:getUser().storeroomType == '1'}">
				<label  for="" >注册证号</label>
				<input type="text" autocomplete="off" id="productRegistrationInput" name="registration" value="${pdProduct.registration }"/>
			</c:if>
			<label for="" >产品规格</label>
			<input type="text" autocomplete="off" id="productSpecInput" name="spec" value="${pdProduct.spec }"/>
			<label for="" >产品型号</label>
			<input type="text" autocomplete="off" id="productVersionInput" name="version" value="${pdProduct.version }"/>

            <!--add by jiangxz 20190904 -->
			<c:if test="${fns:getUser().storeroomType == '0'}">
            <input type="checkbox" id="checkboxInput" /> <span id="isLimitDownSpan" style="padding-right: 50px;font-size: 12px;">超出库房下限产品</span>
            <input type="text" name="isLimitDown" style="display: none"/>
			</c:if>
			<input type="submit"  onclick="return page();" value="查询" class="hcy-btn hcy-btn-primary" />
			<a href="###" onclick="clearConditions()" class="hcy-btn hcy-reset" >重置</a>
		</div>
	</form:form>
		<div class="showTableBox" style="width:100%;overflow:auto;">
			<div class="tableBox">
				<table class="hcy-public-table" id="addContentTab">
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
							<th>注册证号</th>
							<th>生产厂家</th>
							<c:if test="${fns:getUser().storeroomType == '0'}">
								<th>供应商</th>
							</c:if>
						</tr>
					</thead>
					<tbody id= "tdodyID">
						<c:forEach items="${page.list }" var="prod">
							<tr style="cursor:pointer"  data-id="${prod.id }" data-number="${prod.number }" data-supplierId="${prod.supplierId }" data-name="${prod.name }" data-unit="${prod.unit }" data-spec="${prod.spec }"
								data-version="${prod.version }"  data-vender="${prod.venderName }" data-stock="${prod.stockNum }"
								data-price="${prod.sellingPrice }" data-inprice="${prod.pruPrice }" data-validdate="${prod.validDate }">
								<td><input type="checkbox" name="checkboxTable" /></td>
								<%--<td class="addProNum"><a href="###" title="${prod.number }" >${prod.number }</a></td>
								<td class="addProName"><a href="###"  title="${prod.name }">${prod.name }</a></td> 2019年7月1日10:53:25 输入优化--%>
								<td class="addProNum">${prod.number }</td>
								<td class="addProName">${prod.name }</td>
								<td class="addProType">${prod.categoryName }</td>
								<td class="addProGroup">${prod.groupName }</td>
								<td class="addProUnit">${prod.unit }</td>
								<td class="addProformat">${prod.spec }</td>
								<td class="addProCode">${prod.version }</td>
								<td class="addProFoc">${prod.registration }</td>
								<td class="addProFoc">${prod.venderNameShow }</td>
								<c:if test="${fns:getUser().storeroomType == '0'}">
									<td class="addProFoc">${prod.supplierNameShow }</td>
								</c:if>
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
	<script src="${ctxStatic}/spd/js/pinying.js"></script>
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
                var input = $(this).find("input[name='checkboxTable']");//获取checkbox
                if(input.attr("checked")){
                    input.attr("checked",false);
                }else{
                    input.attr("checked",true);
                }
            });

            //checkbox冒泡事件
            $("input[name='checkboxTable']").click(function(e) {
                e.stopPropagation();
            })
                //全选与反选
            $("#allchoose").click(function(){
                if($(this).attr('checked')){
                    $("input[name='checkboxTable']").attr('checked','true');
                }else{
                    $("input[name='checkboxTable']").removeAttr('checked');
                }
            });

            // add by jiangxz 20190904 点击选中超出库房下限产品checkbox
			$("#isLimitDownSpan").on("click",function(){
                if($("#checkboxInput").is(':checked')){
                    $("#checkboxInput").attr('checked', false);
                    $("[name='isLimitDown']").val("false");
                }else{
                    $("#checkboxInput").attr('checked', true);
                    $("[name='isLimitDown']").val("true");
                }
			});

            $("#checkboxInput").on("click",function(){
                if($("#checkboxInput").is(':checked')){
                    $("[name='isLimitDown']").val("true");
                }else{
                    $("[name='isLimitDown']").val("false");
                }
            });

            //add by jiangxz 20190905 用于查询后回显是否勾选了超出库房下限产品
            var isLimitDown = '${isLimitDown}';
            if(isLimitDown == "true"){
                $("#checkboxInput").attr('checked', true);
                $("[name='isLimitDown']").val("true");
            }else{
                $("#checkboxInput").attr('checked', false);
                $("[name='isLimitDown']").val("false");
            }
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		//-采购单新增产品
		function compositeHtmlForPurchaseOrder(index){
			// var chObj = $("input[type='checkbox']:gt(0):checked");
			var chObj = $("input[name='checkboxTable']:checked");// modified by jiangxz 20190904 不建议用gt(0)来遍历元素，gt(0)不是精确的
			if(chObj.length < 1)
				return 0;
			var html = '', indexVue = null;
			var resultHtml = "",resultCode="";
			chObj.each(function(i,v){
				indexVue = Number(index) + Number(i);
				var $this = $(this).parent().parent();
				if($.inArray($this.data('id'), parent.prods) > -1)
					return true;//继续下一循环，类似for里的continue;
				if(resultHtml==""){
					if(!$this.data("supplierid")){
						resultCode = i;
						resultHtml ="-编号为<"+$this.data("number")+">,名称为<"+$this.data("name")+">的耗材没有关联供应商,请到<产品管理>中进行关联";
					}
				}
				html += '<tr class="remove_'+$this.data("id")+'" data-id="'+$this.data("id")+'">'
						+		'<td><i class="fa fa-trash-o" onclick="removeDiv(\''+$this.data("id")+'\');"></i></td>'
						/* +  	'<td><a href="###"  title="'+$this.data("name")+'">'+$this.data("name")+'</a></td>'
                         +		'<td><a href="###" title="'+$this.data("number")+'" >'+$this.data("number")+'</a></td>'2019年7月1日10:56:28 输入优化*/
						+  		'<td>'+$this.data("name")+'</td>'
						+		'<td>'+$this.data("number")+'</td>'
						// +		'<td><a href="###" title="" class="overLook">'+$this.data("barcode")+'</a></td>'
						+		'<td>'+$this.data("spec")+'</td>'
						+		'<td>'+$this.data("version")+'</td>'
						+		'<td>'+$this.data("unit")+'</td>'
						+		'<td>'+$this.data("stock")+'</td>'
						+		'<td><input type="text" autocomplete="off" name="purchaseDetailList['+indexVue+'].applyCount" style="width:60px;" maxlength="10" class="inputPurchaseCount" data-flag="'+$this.data("id")+'"/></td>'
						+		'<td id="inPrice'+$this.data("id")+'">'+$this.data("inprice")+'</td>'
						+		'<td id="finalMoney'+$this.data("id")+'">'+0+'</td>'
						+		'<td id="venderName'+$this.data("id")+'">'+$this.data("vender")+'</td>'
						+      '<input type="hidden" name="purchaseDetailList['+indexVue+'].prodId" value="'+$this.data("id")+'"/>'
						+      '<input type="hidden" name="purchaseDetailList['+indexVue+'].prodNo" value="'+$this.data("number")+'"/>'
						+      '<input type="hidden" name="purchaseDetailList['+indexVue+'].prodPrice" value="'+$this.data("inprice")+'"/>'
                        +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].inPrice" value="'+$this.data("inprice")+'"/>'
						+      '<input type="hidden" id="amountMoney'+$this.data("id")+'" name="purchaseDetailList['+indexVue+'].amountMoney"/>'
						+      '<input type="hidden" name="purchaseDetailList['+indexVue+'].stockNum" value="'+$this.data("stock")+'"/>'
						+ '</tr>';
				parent.prods.push($this.data("id"));
			});
			if(resultCode!="" || resultCode=="0"){
				resultCode = "";
				parent.prods.length=0;
				return resultHtml;
			}else{
				return html;
			}

		}

		//新增产品
		function compositeHtml(index){
            // var chObj = $("input[type='checkbox']:gt(0):checked");
            var chObj = $("input[name='checkboxTable']:checked");// modified by jiangxz 20190904 不建议用gt(0)来遍历元素，gt(0)不是精确的
			if(chObj.length < 1)
				return 0;
			var html = '', indexVue = null;
			var resultHtml = "",resultCode="";
			chObj.each(function(i,v){
				indexVue = Number(index) + Number(i);
				var $this = $(this).parent().parent();
				if($.inArray($this.data('id'), parent.prods) > -1)
					return true;//继续下一循环，类似for里的continue;
				if(resultHtml==""){
					if(!$this.data("supplierid")){
						resultCode = i;
						resultHtml ="-编号为<"+$this.data("number")+">,名称为<"+$this.data("name")+">的耗材没有关联供应商,请到<产品管理>中进行关联";
					}
				}
				html += '<tr class="remove_'+$this.data("id")+'" data-id="'+$this.data("id")+'">'
					 +		'<td><i class="fa fa-trash-o" onclick="removeDiv(\''+$this.data("id")+'\');"></i></td>'
					/* +  	'<td><a href="###"  title="'+$this.data("name")+'">'+$this.data("name")+'</a></td>'
					 +		'<td><a href="###" title="'+$this.data("number")+'" >'+$this.data("number")+'</a></td>'2019年7月1日10:56:28 输入优化*/
					+  		'<td>'+$this.data("name")+'</td>'
					+		'<td>'+$this.data("number")+'</td>'
					// +		'<td><a href="###" title="" class="overLook">'+$this.data("barcode")+'</a></td>'
					 +		'<td>'+$this.data("spec")+'</td>'
					 +		'<td>'+$this.data("version")+'</td>'
					 +		'<td>'+$this.data("unit")+'</td>'
					 +		'<td>'+$this.data("stock")+'</td>'
					 +		'<td><input type="text" autocomplete="off" name="purchaseDetailList['+indexVue+'].applyCount" style="width:60px;" maxlength="10" class="inputPurchaseCount" data-flag="'+$this.data("id")+'"/></td>'
					// +		'<td id="sellingPrice'+$this.data("id")+'">'+$this.data("price")+'</td>'
					// +		'<td id="finalMoney'+$this.data("id")+'"></td>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].prodId" value="'+$this.data("id")+'"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].prodNo" value="'+$this.data("number")+'"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].prodPrice" value="'+$this.data("inprice")+'"/>'
					 //+      '<input type="hidden" name="purchaseDetailList['+indexVue+'].amountMoney"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].stockNum" value="'+$this.data("stock")+'"/>'
					 + '</tr>';
					 parent.prods.push($this.data("id"));
			});
			if(resultCode!="" || resultCode=="0"){
				resultCode = "";
				parent.prods.length=0;
				return resultHtml;
			}else{
				return html;
			}
			
		}
		//-申领单新增产品
		function showProdHtml(index){
            // var chObj = $("input[type='checkbox']:gt(0):checked");
            var chObj = $("input[name='checkboxTable']:checked");// modified by jiangxz 20190904 不建议用gt(0)来遍历元素，gt(0)不是精确的
			if(chObj.length < 1)
				return 0;
			var html = '', indexVue = null;
			chObj.each(function(i,v){
				var $this = $(this).parent().parent(),
					prodId = $this.data("id"),
					indexVue = Number(index) + Number(i);
				if($.inArray(prodId, parent.idplus) > -1)
					return true;
				html += '<tr class="myselfprod remove_'+prodId+'">'
					 +  '<input type="hidden" name="applyDetailList['+indexVue+'].prodId" value="'+prodId+'"/>'
					 +  '<input type="hidden" name="applyDetailList['+indexVue+'].prodNo" value="'+$this.data("number")+'"/>'
					 +		'<td><i class="fa fa-trash-o" onclick="removeDiv(\''+prodId+'\',\'\');"></i></td>'
					 +		'<td></td>'
					 +		'<td></td>'
					/* +		'<td><a href="###"  title="'+$this.data("name")+'">'+$this.data("name")+'</a></td>'
					 +		'<td><a href="###" title="'+$this.data("number")+'">'+$this.data("number")+'</a></td>' 2019年7月3日10:43:41 申领时使用tab键快速定位*/
					 +		'<td>'+$this.data("name")+'</td>'
					 +		'<td>'+$this.data("number")+'</td>'
					 +		'<td>'+$this.data("spec")+'</td>'
					 +		'<td>'+$this.data("version")+'</td>'
					 +		'<td>--</td>'
					 +		'<td>'+$this.data("unit")+'</td>'
					 +		'<td>'+$this.data("stock")+'</td>'
					 +		'<td><input type="text" style="width:60px;" autocomplete="off" name="applyDetailList['+indexVue+'].applyCount" maxlength="10" class="fillNumberSeat"/></td>'
					 +		'<input type="hidden" name="applyDetailList['+indexVue+'].stockNum" value="'+$this.data("stock")+'"/>'
					 +	'</tr>';
				parent.idplus.push(prodId);
			});
			return html;
		}
        //清空查询条件
        function clearConditions(){
            $("#productNameInput").val("")
            $("#productNameSelect").val("")
            $("#productNameSelect").select2("data", "");
            $("#categoryId").select2("val", "");
            $("#groupId").select2("val", "");
            $("#venderId").select2("val", "");
            $("#supplierId").select2("val", "");
            $("#productSpecInput").val("");
            $("#productVersionInput").val("");
            $("#productRegistrationInput").val("");
            $("#checkboxInput").attr('checked', false);
            $("[name='isLimitDown']").val("");
        }
	</script>
</body>
</html>