<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		$("body").addClass("animated fadeIn");
		
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/selectAdminListForStoreroom");
			$("#searchForm").submit();
	    	return false;
	    }
		//获取父页面的单选
		function loadUserHtml() {
			var chObj = $(":checkbox:gt(0):checked");
			if(chObj.length < 1)
				return 0;
			var html = '';
			chObj.each(function() {
					var $user = $(this);
					var userId = $user.data("user-id");
					var userLoginName = $user.data("user-loginname");
					var userName = $user.data("user-name");
					html += "<tr>";
					html += "<td>"+ userLoginName+ "<input type='hidden' value='" + userId + "' /></td>";
					html += "<td>"+ userName+ "</td>";
					html += "<td class='delectStoreScope' data-area-id='" + userId + "'><a class='selectStore' style='cursor: pointer;'>删除</a></td>";
					html += "</tr>";
			});
			return html;
		}
	</script>
	<script type="text/javascript">
	<%-- 进行全选和取消全选 --%>
		$(function() {
			$("#selectAll").click(function() {
				if ($(this).attr("checked")) { // 全选 
	
	                $("input[name='subBox']").each(function () {
	                    $(this).attr("checked", true);
	                });
	            }
	            else { // 取消全选 
	
	                $("input[name='subBox']").each(function () {
	                    $(this).attr("checked", false);
	                });
	            }
			})
		})
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/selectAdminListForStoreroom" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="storeroomType" name="storeroomType" type="hidden" value="${storeroomType}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
            <!-- 		
			<li><label>归属公司：</label><sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}" 
				title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true"/></li>
				 -->
			<li><label>登录名：</label><form:input path="loginName" autocomplete="off" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			
			<!-- 
			<li><label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}" 
				title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
				 -->
			<li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="name" autocomplete="off" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<!-- <li class="btns"><input id="btnConfirm" class="btn btn-primary"
				type="button" value="确定" /></li> -->
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th><input type="checkbox" id="selectAll" /></th><th class="sort-column login_name">登录名</th><th class="sort-column name">姓名</th><th>电话</th><th>手机</th><%--<th>角色</th> --%></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
			   <td>
					<input type="checkbox" name="subBox"
						data-user-id="${user.id}"
						data-user-loginname="${user.loginName}"
						data-user-name="${user.name}"
						 />
				</td>
				<td>${user.loginName}</td>
				<td>${user.name}</td>
				<td>${user.phone}</td>
				<td>${user.mobile}</td><%--
				<td>${user.roleNames}</td> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>