<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>定数包表管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
</head>
<body>
	<div class="searchBox">
		<form:form id="searchForm" modelAttribute="pdPackage" action="${ctx}/pd/pdPackage/choosePackage" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<label for="">定数包编号</label>
			<form:input type="text" id="number" path="number" />
			<label for="">定数包名称</label>
			<form:input type="text" id="name" path="name" />
			<input id="btnSubmit" class="hcy-btn hcy-btn-primary" type="submit" value="查询"/>
		</form:form>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="hcy-public-table">
		<thead>
			<tr>
				<th><input type="checkbox" id="allchoose" disabled="disabled"/> <%--定数包多选会有很多意外bug，禁止多选 modified by jiangxz 20191023 --%></th>
				<th>定数包编号</th>
				<th>定数包名称</th>
				<!-- <th>产品总数</th> -->
				<th>产品编号</th>
				<th>产品名称</th>
				<th>规格</th>
				<th>型号</th>
				<th>产品数量</th>
				<th>单位</th>
				<th>本库库存数量</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pdPackage">
				<c:forEach items="${pdPackage.child }" var="son" varStatus="status">
					<tr data-id="${pdPackage.id }" data-name="${pdPackage.name }" data-number="${pdPackage.number }" data-prods='${pdPackage.jsonStr }'>
						<c:if test="${status.first }">
							<td rowspan="${fn:length(pdPackage.child) }"><input type="checkbox"/></td>
							<td rowspan="${fn:length(pdPackage.child) }">${pdPackage.number }</td>
							<td rowspan="${fn:length(pdPackage.child) }">${pdPackage.name }</td>
							<%-- <td rowspan="${fn:length(pdPackage.child) }">${pdPackage.sum}</td> --%>
						</c:if>
						<td>${son.pdProduct.number }</td>
						<td>${son.pdProduct.name }</td>
						<td>${son.pdProduct.spec }</td>
						<td>${son.pdProduct.version }</td>
						<td>${son.productCount }</td>
						<td>${son.pdProduct.unit }</td>
						<td>${son.stockNum }</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
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

			$("input[type='checkbox']").click(function(){
				$("input[type='checkbox']").removeAttr('checked');
				$(this).attr('checked','true');
			});
		});

		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}

		//-申领单新增定数包
		function showPackageHtml(index){
			var chObj = $("input[type='checkbox']:gt(0):checked");
			var len = chObj.length, returnArray = [];
			if(len < 1){
				returnArray[0] = 0;
				returnArray[1] = 0;
			}
			var html = '', indexVue = null;
			$.each(chObj,function(i,v){
				var $this = $(this).parent().parent(),
					prods = $this.data('prods'),
					rowslen = prods.length,
					rowsId = $this.data('id');
				if($.inArray(rowsId, parent.idplus) > -1)
					return true;
				parent.idplus.push(rowsId);
				indexVue = Number(index) + Number(i);
				var prodNum = 0;
				 for(var j=0;j<rowslen;j++){
					 var obj = prods[j];
					 prodNum += parseInt(!(obj.productCount)?'0':obj.productCount);
					 html += '<tr class="remove_'+$this.data("id")+'">';
					 if(j==0){
						html += '<input type="hidden" name="applyDetailList['+indexVue+'].packageId" value="'+rowsId+'"/>';
						 html += '<td rowspan="'+rowslen+'"><i class="fa fa-trash-o" onclick="removeDiv(\''+rowsId+'\',\'0\');"></i></td>';
						 html += '<td rowspan="'+rowslen+'">'+$this.data("name")+'</td>';
						 html += '<td rowspan="'+rowslen+'">'+$this.data("number")+'</td>';
					 }
					 html +='<td>'+obj.pdProduct.name+'</td>'
					 +		'<td>'+obj.pdProduct.number+'</td>'
					 +		'<td>'+obj.pdProduct.spec+'</td>'
					 +		'<td>'+obj.pdProduct.version+'</td>'
					 +		'<td>'+obj.productCount+'</td>'
					 +		'<td>'+obj.pdProduct.unit+'</td>'
					 +		'<td>'+obj.stockNum+'</td>';
					 if(j==0){
					 	html += '<td rowspan="'+rowslen+'"><input type="text" name="applyDetailList['+indexVue+'].packageCount" pack-idx="'+indexVue+'" style="width:60px;" maxlength="10" class="fillNumberSeat"/></td>';
					 }
				    html += '</tr>';
				 }
				 html += '<input type="hidden" value="'+prodNum+'" class="packProdNum'+indexVue+'"/>';
			});
			returnArray[0] = len;
			returnArray[1] = html;
			return returnArray;
		}

		// 获取定数包中产品的产品编号以及数量 added by jiangxz 20191023
		function getProductNumberForDosage(index){
			var chObj = $("input[type='checkbox']:gt(0):checked");
			var len = chObj.length, returnArray = [], returnObj = {}, message = "";

			$.each(chObj,function(i,v){
				var $this = $(this).parent().parent();
				var prods = $this.data('prods');
				var rowslen = prods.length;

				for(var j=0;j<rowslen;j++) {
					var prodsObj = prods[j];
					var product = {};

					var stockNum = prodsObj.stockNum;
					if(stockNum == 0){
						message = '产品['+prodsObj.pdProduct.name+']在本库库存数量为0，不能添加定数包！';
					}else{
						product.number = prodsObj.pdProduct.number;
						product.productCount = prodsObj.productCount;
					}
					returnArray[j] = product;
				}
			});
			returnObj.returnArray = returnArray;
			returnObj.message = message;
			return returnObj;
		}
	</script>
</body>
</html>