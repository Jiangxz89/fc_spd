<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
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
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="medstoYpcdmlk" action="${ctx}/pd/medstoYpcdmlk/addDrug" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>药品编号</label>
					<form:input path="ypdm" />
				</div>
				<div>
					<label>药品名称</label>
					<form:input path="ypmc" />
				</div>
				<div>
					<label>药品规格</label>
					<form:input path="ypgg" />
				</div>
				<div>
					<label>国家基药标志</label>
					<form:select path="basicdrugFlag">
						<form:option value="">全部</form:option>
						<form:options items="${fns:getDictList('yes_no') }" itemLabel="label" itemValue="value"/>
					</form:select>
				</div>
				<div>
					<label>抗生素级别</label>
					<form:select path="kssjb">
						<form:option value="">非限制使用级</form:option>
						<form:option value="">否</form:option>
					</form:select>
				</div>
				<div>
					<label>处方药品</label>
					<form:select path="cfyp">
						<form:option value="">全部</form:option>
						<form:options items="${fns:getDictList('yes_no') }" itemLabel="label" itemValue="value"/>
					</form:select>
				</div>
				<div>
					<label>厂家名称</label>
					<form:input path="cjmc" />
				</div>
				<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</form:form>
		</div>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
					    <th><input type="checkbox" id="allchoose"/></th>
						<th>药品编号</th>
						<th>药品名称</th>
						<th>药品规格</th>
						<th>国家基药标志</th>
						<th>抗生素级别</th>
						<th>处方药品</th>
						<th>生产厂家</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="drug">
						<tr data-id="${drug.idm }" data-ypmc="${drug.ypmc }" data-ypdm="${drug.ypdm }"  
							data-ypgg="${drug.ypgg }" data-ykxs="${drug.ykxs }" data-ypfj="${drug.ypfj }" data-mrjj="${drug.mrjj }"
						    data-ykdw="${drug.ykdw }" data-ypsj="${drug.ylsj }" data-ypkc="${drug.ypkc }" 
						    data-ghdwid="${drug.ghdwId }" data-ghdwmc="${drug.ghdwMc }">
							<td><input type="checkbox" /></td>
							<td>${drug.ypdm }</td>
							<td>${drug.ypmc }</td>
							<td>${drug.ypgg }</td>
							<td>${fns:getDictLabel(drug.basicdrugFlag, 'yes_no', '') }</td>
							<td>${drug.kssjb }</td>
							<td>${fns:getDictLabel(drug.cfyp, 'yes_no', '') }</td>
							<td>${drug.cjmc }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<script type="text/javascript">
		$(function(){
			
			//重置
			$(".hcy-reset").click(function(){
				$(".searchBox div input[type='text']").val("");
				$(".searchBox div select").val("");
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
		
		//-采购单新增药品
		function compositeHtml(index){
			var chObj = $("input[type='checkbox']:gt(0):checked");
			if(chObj.length < 1)
				return 0;
			var html = '', indexVue = null;
			var resultHtml = "",resultCode="";
			chObj.each(function(i,v){
				indexVue = Number(index) + Number(i);
				var $this = $(this).parent().parent();
				if($.inArray($this.data('id'), parent.prods) > -1)
					return true;//继续下一循环，类似for里的continue;
				//判断是否已有供应商
				if(resultHtml==""){
					if ($this.data("ghdwid").trim()=="" || $this.data("ghdwid").trim().length==0) {
						resultCode = i;
						resultHtml ="-编号为<"+$this.data("ypdm")+">,名称为<"+$this.data("ypmc")+">的药品没有关联供应商,请到<药品管理>中进行关联";
					}
				}
				html += '<tr class="remove_'+$this.data("id")+'" data-id="'+$this.data("id")+'">'
					 +		'<td><i class="fa fa-trash-o" onclick="removeDiv(\''+$this.data("id")+'\');"></i></td>'
					 +		'<td>'+$this.data("ypmc")+'</td>'
					 +		'<td>'+$this.data("ypdm")+'</td>'
					 +		'<td>'+$this.data("ypgg")+'</td>'
					 +		'<td>'+$this.data("ykdw")+'</td>'
					 +		'<td>'+$this.data("ykxs")+'</td>'
					 +		'<td>'+$this.data("ypfj")+'</td>'
					 +		'<td>'+$this.data("ypsj")+'</td>'
					 +		'<td>'+$this.data("ypkc")+'</td>'
					 +		'<td><input type="text" name="purchaseDetailList['+indexVue+'].cgsl" style="width:60px;" maxlength="10" class="inputPurchaseCount" data-flag="'+$this.data("id")+'"/></td>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].ypdm" value="'+$this.data("ypdm")+'"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].ykdw" value="'+$this.data("ykdw")+'"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].cdIdm" value="'+$this.data("id")+'"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].ypmc" value="'+$this.data("ypmc")+'"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].ypgg" value="'+$this.data("ypgg")+'"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].ykxs" value="'+$this.data("ykxs")+'"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].ypfj" value="'+$this.data("ypfj")+'"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].ylsj" value="'+$this.data("ypsj")+'"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].ypjj" value="'+$this.data("mrjj")+'"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].ghdwId" value="'+$this.data("ghdwid")+'"/>'
					 +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].ghdwMc" value="'+$this.data("ghdwmc")+'"/>'
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
	</script>
</body>
</html>