<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>添加病人</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
</head>
<body>
	<div class="searchBox">
		<form:form id="searchForm" modelAttribute="pdPatien" action="${ctx}/pd/pdPatien/choosePatienList" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<label for="" style="width: 100px;">病人姓名/简拼</label>
			<form:input type="text" id="name" path="name" />
			<label for="" style="width: 100px;">住院号/门诊号</label>
			<form:input type="text" id="inhospitalNo" path="inhospitalNo" />
			<input id="btnSubmit" class="hcy-btn hcy-btn-primary" type="submit" value="查询"/>
		</form:form>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="hcy-public-table">
		<thead>
			<tr>
				<th><input type="checkbox" id="allchoose" disabled="disabled"/> <%--定数包多选会有很多意外bug，禁止多选 modified by jiangxz 20191023 --%></th>
				<th>姓名</th>
				<th>身份证号</th>
				<th>住院号/门诊号</th>
				<th>备注</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pdPatien">
				<%--<c:forEach items="${pdPatien.child }" var="son" varStatus="status">--%>
					<tr data-id="${pdPatien.id }" data-name="${pdPatien.name }" data-idnumber="${pdPatien.idNumber }" data-inhospitalno="${pdPatien.inhospitalNo }"
                        data-operationdoctor="${pdPatien.operationDoctor }" data-operationdepartment="${pdPatien.operationDepartmentName }"
                        data-inhospitaldoctor="${pdPatien.inhospitalDoctor }" data-inhospitaldepartment="${pdPatien.inhospitalDepartmentName }"
                        data-prescriberdoctor="${pdPatien.prescriberDoctor }">
						<td>
							<input type="checkbox" name="checkProduct" value="${pdPatien.id }"/>
							<input type="hidden" value="${pdPatien.id }">
						</td>
						<td>${pdPatien.name }</td>
						<td>${pdPatien.idNumber}</td>
						<td>${pdPatien.inhospitalNo}</td>
						<td>${pdPatien.remarks}</td>
						<td><fmt:formatDate value="${pdPatien.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				<%--</c:forEach>--%>
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

		// 获取定数包中产品的产品编号以及数量 added by jiangxz 20191023
		function getPatienInfoForDosage(index){
			var chObj = $("input[type='checkbox']:gt(0):checked");
			var len = chObj.length, returnArray = [], returnObj = {}, message = "";
            if(len > 0){
                $.each(chObj,function(i,v){
                    var $this = $(this).parent().parent();
                    returnObj.name = $this.data('name');
                    returnObj.idNumber = $this.data('idnumber');
                    returnObj.inhospitalNo = $this.data('inhospitalno');
                    returnObj.operationDoctor = $this.data('operationdoctor');
                    returnObj.operationDepartment = $this.data('operationdepartment');
                    returnObj.inhospitalDoctor = $this.data('inhospitaldoctor');
                    returnObj.inhospitalDepartment = $this.data('inhospitaldepartment');
                    returnObj.prescriberDoctor = $this.data('prescriberdoctor');
                });
            }else{
                message = "请选择一位病人";
            }

			returnObj.message = message;
			return returnObj;
		}
	</script>
</body>
</html>