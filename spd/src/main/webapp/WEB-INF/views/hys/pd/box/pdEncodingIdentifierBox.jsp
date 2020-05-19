<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<title>添加应用标识符</title>
</head>
<body>
	<div class="addProBox">
		<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdEncodingIdentifier" action="${ctx}/pd/pdEncodingIdentifier/getList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="searchBox">
			<label style="width:130px;">应用标识符值：</label>
			<form:input path="value" htmlEscape="false" maxlength="64" class="input-medium"/>
			<input id="btnSubmit" class="hcy-btn hcy-search" style="height:inherit;line-height:1.5 ;" type="submit" value="查询"/></li>
		</div>
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
				<tr>
					<th><input type="checkbox" id="chkAll" name="chkAll" /></th>
					<th>应用标识符</th>
					<th>含义</th>
					<th>类型</th>
					<th>长度</th>
					<th>备注</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="pdEncodingIdentifier">
					<tr>
						<td><input type="checkbox" name="chkList"  class="checkOne"/></td>
						<input type="hidden" class="addIdentifierId" value="${pdEncodingIdentifier.id }" />
						<td class="addValue">
							${pdEncodingIdentifier.value}
						</td>
						<td class="addMeaning">
							${pdEncodingIdentifier.meaning}
						</td>
						<td class="addType" value = "${pdEncodingIdentifier.type}">
							${fns:getDictLabel(pdEncodingIdentifier.type, 'identifier_type', '')}
						</td >
						<td class="addLength">${pdEncodingIdentifier.length}
						</td>
						<td><a href="###" title="${pdEncodingIdentifier.remarks}" class="overLook">${pdEncodingIdentifier.remarks}</a></td>
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
		})
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

				var b = window.parent.repetitionDel($this.find(".addIdentifierId").val());

				if(b != 'success')
					return true;

				html+='<tr>'+
						'<input type="hidden" class="IdentifierId" name="pdEncodingRuleDetails['+indexVue+'].identifier" value="'+$this.find(".addIdentifierId").val()+'">'+
						'<input type="hidden" name="pdEncodingRuleDetails['+indexVue+'].value" value="'+$this.find(".addValue").text()+'">'+
						'<td><input type="checkbox" name="chkList"  class="checkOne"/></td>'+
						'<td>'+$this.find(".addValue").text()+'</td>'+
						'<td>'+$this.find(".addMeaning").text()+'</td>';
						if($this.find(".addType").attr('value')==1){
							var length = $this.find(".addLength").text();
							html+='<td><input type="text" value="'+length+'" readonly="readonly" name="pdEncodingRuleDetails['+indexVue+'].length" style="text-align:center; width:60px;" maxlength="10" data-flag="'+$this.find(".addIdentifierId").val()+'"/></td>'
						}else{
							html+='<td><input type="text" name="pdEncodingRuleDetails['+indexVue+'].length" style="text-align:center; width:60px;" maxlength="10" class="identifierLength" data-flag="'+$this.find(".addIdentifierId").val()+'"/></td>'
						}
						html+='<td>'+$this.find(".addType").text()+'</td>'+
							'<td><input type="text" name="pdEncodingRuleDetails['+indexVue+'].codeOrder" style="text-align:center; width:60px;" maxlength="10" class="identifierOrder" data-flag="'+$this.find(".addIdentifierId").val()+'"/></td>'+
						'</tr>'
			});
			return html;
		}
	</script>
</body>
</html>