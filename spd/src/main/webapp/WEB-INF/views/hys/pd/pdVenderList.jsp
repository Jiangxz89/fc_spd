<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<style>
		.layui-layer-content{text-align:center;}
		#submit{width:80px;height:32px;opacity:0;position:absolute;}
		.factoryTab .red td,.factoryTab .red td a{color: red}
	</style>
	<title>生产厂家管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
	<input type="hidden" name="delKey" value="${delKey }" />
	<input type="hidden" name="delAllKey" value="${delAllKey }" />
	<div class="right-main-box">
		<div class="btnBox">
			<h4>生产厂家管理</h4>
			<a href="${ctx }/pd/pdVender/form?flag=save" class="hcy-btn hcy-btn-primary">添加生产厂家</a>
			<a href="###" class="hcy-btn hcy-btn-primary" id="removeAll">批量删除</a>
			<a href="###" class="hcy-btn hcy-btn-primary" id="moreImport">批量导入</a>
			<c:if test="${ifPlatform == '0' ||isUrgent == '0'}">
				<a href="###" class="hcy-btn hcy-btn-primary" id="synPdVender">同步厂家至中心平台</a>
			</c:if>
		</div>
		<div class="newSearchBox">
			<form:form id="searchForm" style="padding:0;background:none;margin:0;" modelAttribute="pdVender" action="${ctx}/pd/pdVender/" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<label style="width:120px;">生产厂家名称：</label>
				<%--<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>--%>
				<form:select path="name" class="input-medium" style="width: 260px;">
					<form:option value="" label="全部(qb)"/>
					<form:options items="${spd:findPdVenderList() }" itemLabel="nameAndpinyin" itemValue="name" htmlEscape="false"/>
				</form:select>
				<input id="btnSubmit" onclick="return page();"  class="hcy-btn hcy-search" style="height:inherit;line-height:1.5 ;" type="submit" value="查询"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table class="hcy-public-table factoryTab">
				<thead>
					<tr>
						<th><input type="checkbox" name="" id="chkAll" /></th>
						<th>生产厂家</th>
						<th>创建时间</th>
						<th>最近修改时间</th>
						<th>证照是否过期</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list}" var="pdVender">
						<%-- <tr class="red">--%>
						 <c:if test="${not empty pdVender.avlType}">
							<c:choose>
								<c:when test="${pdVender.avlType=='1'|| pdVender.avlType==2}"><tr class="red"></c:when>
							</c:choose>
						</c:if>
						<c:if test="${empty pdVender.avlType}"><tr></c:if>

							<td><input type="checkbox" name="chkList" /><input type="hidden" value="${pdVender.id }"></td>
							<td>${pdVender.name }</td>
							<td><fmt:formatDate value="${pdVender.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${pdVender.updateDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${fns:getDictLabel(pdVender.avlType, 'avl_type', '')}</td>
							<td>
								<a href="${ctx}/pd/pdVender/form?id=${pdVender.id}&flag=see" class="hcy-btn hcy-btn-o">查看</a>
								<a href="${ctx}/pd/pdVender/form?id=${pdVender.id}&flag=update" class="hcy-btn hcy-btn-o modifyBtn">修改</a>
								<a href="###" data-id="${pdVender.id}" onclick="del('${pdVender.id}')" class="hcy-btn hcy-btn-o removeProBtn">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="importBox">
		<a href="###" style="margin-top: 80px;margin-right: 60px;" onclick="template()" class="hcy-btn hcy-btn-primary">下载模板</a>
		<div  style="margin-top: 80px;position:relative;" class="hcy-btn hcy-btn-primary">直接导入
			<form id="importForm" method="post"  style="margin:0;position:absolute;left:0;top:0;" enctype="multipart/form-data" action="${ctx }/pd/pdVender/importData" />
				<input type="file" data-code='0' name="file" style="width:80px;height:32px;;margin:0;opacity:0;z-index:6666;"  class="hcy-btn hcy-btn-primary" value="直接导入"/>
				<input type="submit" id="submit" />
			</form>
		</div>
	</div>
	<div class="pagination">${page}</div>

	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script>
		$(function(){
			//批量删除
			/* $("#removeAll").click(function(){
				//获取选中项的长度，根据长度判断显示弹框内容
				var len=$(".factoryTab>tbody>tr input[type='checkbox']:checked").length;
				if(len==0){//等于零时，提示请选择厂家
					layer.alert("请先选择要删除的生产厂家！",{icon:2},function(index){
						layer.close(index);
					});
				}
				if(len>0){//大于零时判断是否删除
					layer.confirm(
						"确认删除所选生产厂家？",
						{
							icon:"3",
							title:"提示",
							btn:["确定","取消"]
						},
						function(index){
							$(".factoryTab>tbody>tr input[type='checkbox']:checked").each(function(){
								var ind=$(this).parents("tr").index();
								$(".factoryTab>tbody>tr").eq(ind).remove();
							})
							layer.close(index);
						},
						function(index){
							layer.close(index);
						}
					)
				}
			}); */

			$("#synPdVender").on("click",function(){
				$.ajax({
					url:"${ctx}/pd/pdVender/synPdVender",
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

			var delKey = $("[name=delKey]").val();
			var delAllKey = $("[name=delAllKey]").val();
			if(delKey == 'error'){
				layer.alert("该厂家有关联产品，删除失败",{icon:0},function(index){
					layer.close(index);
				});
				//alert("该厂家有关联产品，删除失败");
			}
			if(delAllKey != ''){
				layer.alert(delAllKey+" 等厂家有关联产品，删除失败",{icon:0},function(index){
					layer.close(index);
				});
				//alert(delAllKey+" 等厂家有关联产品，删除失败");
			}
			if(delKey == 'success' && delAllKey == ''){
				layer.alert("删除成功！",{icon:0},function(index){
					layer.close(index);
				});
			}
			
			
			$(document).on("change",".layui-layer-content input[name='file']",function(){
				$(document).find(".layui-layer-content #submit").click();

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
			/* $(document).on("click",".removeProBtn",function(){
				var that=$(this);
				var href="${ctx}/pd/pdVender/delete"
				var id=$(this).attr("data-id");
				//${ctx}/pd/pdGroup/delete?id=${pdGroup.id}
				layer.confirm(
					"确定要删除该生产厂家吗？",//这里写询问层里面的文本
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
			}) */
			
			//批量删除
			$("#removeAll").click(function(){
				var len=$("#tbody>tr input[type='checkbox']:checked").length;
				var ids="";
				if(len==0){
					layer.alert("请选择要删除的生产厂家！",{icon:0},function(index){
						layer.close(index);
					});
				}
				if(len>0){
					$("#tbody>tr input[type='checkbox']:checked").each(function(){
						ids += ','+$(this).next().val();
					});
					layer.confirm(
						"确定要删除该生产厂家吗？",//这里写询问层里面的文本
						{
							icon:3,
							title:"提示",
							btn:["确定","取消"]
						},
						function(index,layero){
							//layero.find(that.parent().parent().remove())
							
							layer.close(index);
							//alert(href+"?id="+id);
							window.location="${ctx}/pd/pdVender/deleteAll?ids="+ids;
						},
						function(index){
							layer.close(index);
						}
					)
				}
			})
			
			/* //批量删除
			$("#removeAll").click(function(){
				if(window.confirm("确定删除了吗！")){
					var ids = '';
					$("[name=chkList]").each(function(){
						if($(this).attr("checked") == 'checked'){
							ids += ','+$(this).next().val();
						}
					})
					
					if(ids == ''){
						alert("请选择最少一个！");
					}else{
						window.location = "${ctx}/pd/pdVender/deleteAll?ids="+ids;
					}
				}else{
					
				}
			}) */
			//批量导入
			$("#moreImport").click(function(){
				layer.open({
					type:1,
					title:"批量导入",
					content:$(".importBox").html(),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["关闭"],
					yes:function(){
						layer.closeAll();
					}
				})
			})
		})
		
		//删除
		function del(id){
			var id = id;
			
			layer.confirm(
					"确认删除当前生产厂家？",
					{//options这里写对应的参数需求，如图标，提示文字，按钮名称等
						icon:3,
						title:"提示",
						btn:["确定","取消"]
					},
					function(index){
						layer.close(index);
						window.location = "${ctx}/pd/pdVender/delete?id="+id;
					},
					function(index){
						layer.close(index);
					}
				)
		}
		
		//生成模板
		function template(){
			window.location = "${ctx}/pd/pdVender/generateTemplate";
		}
	</script>
</body>
</html>