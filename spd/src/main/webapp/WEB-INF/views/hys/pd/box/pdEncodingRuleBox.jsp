<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<title>添加编码规则</title>
</head>
<body>
<div class="addProBox">
	<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdEncodingRule" action="${ctx}/pd/pdEncodingRule/getList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="100"/>
		<div class="searchBox">
			<label style="width:130px;">编码名称：</label>
			<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			<input id="btnSubmit" class="hcy-btn hcy-search" style="height:inherit;line-height:1.5 ;" type="submit" value="查询"/></li>
		</div>
		<table id="contentTable" class="hcy-public-table" style="padding:0;">
			<thead>
			<tr>
				<th></th>
				<th>名称</th>
				<th>规则描述</th>
				<th>总位数</th>
				<th>备注</th>
			</tr>
			</thead>
			<tbody  id= "tdodyID">
			<c:forEach items="${page.list}" var="pdEncodingRule">
				<tr style="cursor:pointer">
					<td><input type="checkbox" name="encodingName" data-name = "${pdEncodingRule.name }" value="${pdEncodingRule.id }" /></td>
					<td>
							${pdEncodingRule.name}
					</td>
					<td>
							${pdEncodingRule.codeDesc}
					</td>
					<td>
							${pdEncodingRule.totalDigit}
					</td >
					<td><a href="###" title="${pdEncodingRule.remarks}" class="overLook">${pdEncodingRule.remarks}</a></td>
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

    })
    function page(n,s){
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
    }

    $("#tdodyID tr").click(function () {
        var input = $(this).find("input[name='encodingName']");//获取checkbox
        if(input.attr("checked")){
            input.attr("checked",false);
        }else{
            input.attr("checked",true);
        }
    });

    //checkbox冒泡事件
    $("input[name='encodingName']").click(function(e) {
        e.stopPropagation();
    })

    function compositeHtml() {
        var jsonArray = new Array();
        var $this = $('input[name="encodingName"]:checked');
        $this.each(function(i,v){
            var json = {
                "encodingRuleName":$(this).attr("data-name"),
                "encodingRule":$(this).val()
            }
            jsonArray.push(json);
        })
        return jsonArray;
    }
</script>
</body>
</html>