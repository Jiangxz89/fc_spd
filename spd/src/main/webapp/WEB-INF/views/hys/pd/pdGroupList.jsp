<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<title>产品组别管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				rules: {
					name: {remote: "${ctx}/pd/pdGroup/checkGroupName"},
				},
				messages: {
					name: {remote: "组别已存在"},
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			})
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		resetTip();	
	</script>
</head>
<body>
	<input type="hidden" name="delKey" value="${delKey }" /><!-- 删除标记 -->
	<input type="hidden" name="delAllKey" value="${delAllKey }"><!-- 批量删除失败id集 -->
	<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdGroup" action="${ctx}/pd/pdGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>产品组别管理</h4>
			<a href="###" class="hcy-btn hcy-btn-primary" id="addNewClassify">新增组别</a>
			<a href="###" class="hcy-btn hcy-btn-primary" id="removeAll">批量删除</a>
			<c:if test="${ifPlatform == '0' ||isUrgent == '0'}">
				<a href="###" class="hcy-btn hcy-btn-primary" id="synPdGroup">同步产品组别至中心平台</a>
			</c:if>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table class="hcy-public-table proClassifyTab">
				<thead>
					<tr>
						<th><input type="checkbox" id="chkAll" name="" /></th>
						<th>组别名称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list}" var="pdGroup">
						<tr>
							<td><input type="checkbox" name="chkList" /><input name="id" type="hidden" value="${pdGroup.id }" /></td>
							<td>${pdGroup.name }</td>
							<td>
								<a href="###" data-id="${pdGroup.id}"  class="hcy-btn hcy-btn-o modifyBtn">修改</a>
								<a href="###" data-id="${pdGroup.id}"  class="hcy-btn hcy-btn-o removeProBtn">删除</a>
							</td>
						</tr>
					</c:forEach>
					
				
				</tbody>
			</table>
		</div>
	</div>
	<!-- ...添加 -->
	<div class="addClassifyBox" style="display: none;">
		<form:form id="inputForm" modelAttribute="pdGroup" style="padding:20px 0 0 15px;" action="${ctx}/pd/pdGroup/save" method="post" class="form-horizontal">
			<label class="addClassifyLab">组别名称</label>
			<input type="text" class="addClassifyInp" style="width:160px;height:30px;border:1px solid #ccc" name="name" />
			<span class="mustIcon" style="display: none;">请填写组别名称</span>
		</form:form>
	</div>
	
	<!-- ...修改 -->
	<div class="updateClassifyBox" style="display: none;">
		<form:form id="inputForm2" modelAttribute="pdGroup" style="padding:20px 0 0 15px;" action="${ctx}/pd/pdGroup/update" method="post" class="form-horizontal">
			<input type="hidden" name="id" id="updateId" />
			<label class="addClassifyLab" >组别名称</label>
			<input type="text" class="addClassifyInp" style="width:160px;height:30px;border:1px solid #ccc" name="name" />
			<span class="mustIcon" style="display: none;">请填写组别名称</span>
		</form:form>
	</div>
	
	<div class="pagination">${page}</div>
	
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/js/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.js"></script>
	<script type="text/javascript">
		$(function(){
			
			var delKey = $("[name = delKey]").val();
			var delAllKey = $("[name = delAllKey]").val();
			if(delKey == 'error' && delAllKey == ''){
				layer.alert("该组别有关联产品，不可删除！",{icon:0},function(index){
					layer.close(index);
				});
			}
			if(delKey == 'error' && delAllKey != '' && delAllKey != null){
				layer.alert(delAllKey +"因有关联产品，不可删除！",{icon:0},function(index){
					layer.close(index);
				});
			}
			
			
			//新增组别
			$("#addNewClassify").click(function(){
				layer.open({
					type:1,
					title:"新增产品组别",
					content:$(".addClassifyBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
					     if ($("#inputForm").valid()) {
					    	 $("#inputForm").submit();
					     }
					},
					btn2:function(){
						$(".mustIcon").hide();
						layer.closeAll();
					}
				})
			});
			//修改
			$(document).on("click",'.modifyBtn',function(){
				var modifyVal=$(this).parents("tr").find("td:eq(1)").text();
				$(".addClassifyInp").val(modifyVal);
				var id=$(this).attr("data-id");
				$("#updateId").val(id);
				layer.open({
					type:1,
					title:"修改产品组别",
					content:$(".updateClassifyBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
					     if ($("#inputForm2").valid()) {
					    	 $("#inputForm2").submit();
					     }
					},
					btn2:function(){
						$(".mustIcon").hide();
						layer.closeAll();
					}
				})
			})
			
			//全选
			$("#chkAll").click(function(){
				if($(this).attr("checked") == 'checked'){
					$("input[name='chkList']").attr("checked",$(this).attr("checked"));
				}else{
					$("input[name='chkList']").removeAttr("checked");
				}
				
			})
			//删除
			$(document).on("click",".removeProBtn",function(){
				var that=$(this);
				var href="${ctx}/pd/pdGroup/delete";
				var id=$(this).attr("data-id");
				//${ctx}/pd/pdGroup/delete?id=${pdGroup.id}
				layer.confirm(
					"确定要删除该组别吗？",//这里写询问层里面的文本
					{
						icon:3,
						title:"提示",
						btn:["确定","取消"]
					},
					function(index,layero){
					//	layero.find(that.parent().parent().remove())
						layer.close(index);
						//alert(href+"?id="+id);
						window.location.href=href+"?id="+id
					},
					function(index){
						layer.close(index);
					}
				)
			})
			//批量删除
			$("#removeAll").click(function(){
				var len=$(".proClassifyTab>tbody>tr input[type='checkbox']:checked").length;
				var ids="";
				if(len==0){
					layer.alert("请选择要删除的组别！",{icon:0},function(index){
						layer.close(index);
					});
				}
				if(len>0){
					$(".proClassifyTab>tbody>tr input[type='checkbox']:checked").each(function(){
						ids += ','+$(this).next().val();
					});
					layer.confirm(
						"确定要删除该组别吗？",//这里写询问层里面的文本
						{
							icon:3,
							title:"提示",
							btn:["确定","取消"]
						},
						function(index,layero){
							//layero.find(that.parent().parent().remove())
							$(".proClassifyTab>tbody>tr input[type='checkbox']:checked").each(function(){
								$(this).parent().parent().remove();
							})
							layer.close(index);
							//alert(href+"?id="+id);
							window.location="${ctx}/pd/pdGroup/deleteAll?ids="+ids;
						},
						function(index){
							layer.close(index);
						}
					)
				}
			})

			$("#synPdGroup").on("click",function(){
				$.ajax({
					url:"${ctx}/pd/pdGroup/synPdGroup",
					type:"post",
					data:{},
					dataType:"json",
					success:function(data){
						if(data.code == "-200"){
							layer.alert("同步失败");
						}else{
							layer.alert("同步成功");
						}
					}
				})
			})
		})
	</script>
</body>
</html>