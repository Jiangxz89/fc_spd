<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>紧急产品管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<link rel="stylesheet" href="css/base.css" />
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
		<div class="btnBox">
			<h4>紧急产品管理</h4>
			<a href="${ctx}/pd/pdProduct/form?flag=urgent" class="hcy-btn hcy-btn-primary">添加紧急产品</a>
			<a href="###" id="addChargeCode" class="hcy-btn hcy-btn-primary">添加HIS项目收费代码</a>
		</div>
		<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdProduct" action="${ctx}/pd/pdProduct/urgentList" method="post" class="breadcrumb form-search">
			<div class="searchBox">
				<div>
					<label for="">产品编号</label>
					<input type="text" id="number" name="number" value="${pdProduct.number }"/>
				</div>
				<div>
					<label for="">产品名称</label>
					<input type="text" id="name" name="name" value="${pdProduct.name }"/>
				</div>
				<div>
					<label for="">产品分类</label>
					<select id="categoryId" name="categoryId">
						<option value="">--全部--</option>
						<c:forEach var="pdCategory" items="${pdCategoryList}">  
	                        <option value="${pdCategory.id}" <c:if test="${pdProduct.categoryId eq pdCategory.id}">selected</c:if>>${pdCategory.name}</option>  
	                    </c:forEach>
					</select>
				</div>
				<div>
					<label for="">产品组别</label>
					<select id="groupId" name="groupId">
						<option value="">--全部--</option>
						<c:forEach var="pdGroup" items="${pdGroupList}">  
	                        <option value="${pdGroup.id}" <c:if test="${pdProduct.groupId eq pdGroup.id}">selected</c:if>>${pdGroup.name}</option>  
	                    </c:forEach>
					</select>
				</div>
				<div>
					<label for="">产品型号</label>
					<input type="text" id="version" name="version" value="${pdProduct.version }"/>
				</div>
				<div>
					<label for="">生产厂家</label>
					<select id="venderId" name="venderId" style="width:346px;">
						<option value="">--全部--</option>
						<c:forEach var="pdVender" items="${pdVenderList}">  
	                        <option value="${pdVender.id}" <c:if test="${pdProduct.venderId eq pdVender.id}">selected</c:if>>${pdVender.name}</option>  
	                    </c:forEach> 
					</select>
				</div>
				<div>
					<label for="">产品权限</label>
					<select id="power" name="power">
						<option value="">--全部--</option>
						<c:forEach var="dict" items="${fns:getDictList('product_power') }">
							<option value="${dict.label eq '公共产品'?'0':'1' }" <c:if test="${pdProduct.power eq (dict.label eq '公共产品'?'0':'1')}">selected</c:if>>${dict.label }</option>
						</c:forEach>
						<%-- <option value="公共产品" <c:if test="${pdProduct.power eq '公共产品'}">selected</c:if>>公共产品</option>
						<option value="自有产品" <c:if test="${pdProduct.power eq '自有产品'}">selected</c:if>>自有产品</option> --%>
					</select>
				</div>
				<!-- <input id="search" class="hcy-btn hcy-btn-primary" type="button" value="查询"/> -->
				<input id="btnSubmit" class="hcy-btn hcy-search" style="height: inherit;line-height:1.5;" type="submit" value="查询"/>
				<a href="###" onclick="clearConditions()" class="hcy-btn hcy-reset">重置</a>
			</div>

		
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		</form:form>
		
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th><input type="checkbox" id="chkAll"/></th>
						<th>产品编号</th>
						<th>产品名称</th>
						<th>产品分类</th>
						<th>产品组别</th>
						<th>单位</th>
						<th>规格</th>
						<th>型号</th>
						<th>生产厂家</th>
						<th>收费项目代码</th>
						<th>注册证号</th>
						<th>出货单价</th>
						<th>备注</th>
						<th>紧急采购量</th>
						<th>剩余采购量</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="pdProduct">
						<tr>
							<td>
							<input type="checkbox" name="checkProduct" />
							<input type="hidden" value="${pdProduct.id }">
							</td>
							<td><a href="###" title="${pdProduct.number }">${pdProduct.number }</a></td>
							<td><a href="###" title="${pdProduct.name }">${pdProduct.name }</a></td>
							<td>${spd:getCategoryName(pdProduct.categoryId) }</td>
							<td>${spd:getGroupName(pdProduct.groupId) }</td>
							<td>${pdProduct.unit }</td>
							<td>${pdProduct.spec }</td>
							<td>${pdProduct.version }</td>
							<td><a href="###" title="${spd:getVenderName(pdProduct.venderId) }" class="overLook">${spd:getVenderName(pdProduct.venderId) }</a></td>
							<td class="chargeCodeTd">${pdProduct.chargeCode }</td>
							<td><a href="###" title="${pdProduct.registration }" class="overLook">${pdProduct.registration }</a></td>
							<td>${pdProduct.sellingPrice }</td>
							<td>${pdProduct.description }</td>
							<td>${pdProduct.urgentPurCount }</td>
							<td>${pdProduct.surplusPurCount }</td>
							<td>
							<a href="${ctx }/pd/pdProduct/toUpdate?id=${pdProduct.id}&flag=see&isUrgent=urgent" class="hcy-btn hcy-btn-o">查看</a>
							<a href="${ctx }/pd/pdProduct/toUpdate?id=${pdProduct.id}&flag=update&isUrgent=urgent" class="hcy-btn hcy-btn-o">修改</a>
							<a href="###"  data-id="${pdProduct.id}" class="hcy-btn hcy-btn-o removeProBtn">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="addChargeCodeBox">
		<h4>冠状动脉球囊扩张导管等11个产品</h4>
		<input type="text" class="addChargeCodeInp" />
	</div>
	<div class="importBox">
		<a href="###" style="margin-top: 80px;margin-right: 60px;" class="hcy-btn hcy-btn-primary">下载模板</a>
		<a href="###" style="margin-top: 80px;"  class="hcy-btn hcy-btn-primary">直接导入</a>
	</div>
	
	
	<div class="pagination">${page}</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/js/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			
			//模糊查询
			//$("#search").click(function(){
			//	window.location ="${ctx}/pd/pdProduct/list?number="+$("#number").val()+"&name="+$("#name").val()+"&categoryName="+$("#categoryName").val()+"&groupName="+$("#groupName").val()+"&version="+$("#version").val()+"&venderName="+$("#venderName").val()+"&power="+$("#power").val();
			//})
			
			//添加收费代码
			$("#addChargeCode").click(function(){
				var checkNum=$(".hcy-public-table>tbody>tr input[type='checkbox']:checked").length;
				var checkText="";
				if(checkNum==0){
					layer.alert("请先选择要添加HIS收费项目的产品！",{icon:1},function(index){
						layer.close(index);
					});
				}
				if(checkNum>0){
					$(".hcy-public-table>tbody>tr input[type='checkbox']:checked").each(function(){
						checkText=$(this).parent().next().next().find("a").text();
						$(".addChargeCodeBox>h4").text(checkText+"等"+checkNum+"个产品");
					})
					layer.open({
						type:1,
						title:"添加HIS收费项目代码",
						content:$(".addChargeCodeBox").html(),
						area:["400px","300px"],
						shade: [0.8, '#393D49'],
						btn:["确定","取消"],
						yes:function(index,layero){
							var val=layero.find(".addChargeCodeInp").val();
							var ids = '';
							var ind = '';
							$(".hcy-public-table>tbody>tr input[type='checkbox']:checked").each(function(){
								var ind=$(this).parents("tr").index();
								$(".hcy-public-table>tbody>tr").eq(ind).find(".chargeCodeTd").text(val);
								ids += $(this).next().val()+',';
							})
							window.location = "${ctx}/pd/pdProduct/updateChargeCode?ids="+ids+"&chargeCode="+val+"&flag=urgent";
							layer.closeAll();
						},
						btn2:function(){
							layer.closeAll();
						}
					})
					
				}
			});
			//全选
			$("#chkAll").click(function(){
				if($(this).attr("checked") == 'checked'){
					$("input[name='checkProduct']").attr("checked",$(this).attr("checked"));
				}else{
					$("input[name='checkProduct']").removeAttr("checked");
				}
				
			})
			//批量导入
			$("#import").click(function(){
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
			});
			//删除产品
			$(document).on("click",".removeProBtn",function(){
				var ind=$(this).parent().parent().index();
				var id=$(this).attr("data-id");
				layer.confirm(
					"确认删除当前产品？",
					{//options这里写对应的参数需求，如图标，提示文字，按钮名称等
						icon:3,
						title:"提示",
						btn:["确定","取消"]
					},
					function(index){
						$.ajax({
							url:"${ctx }/pd/pdProduct/delete",
							type:"POST",
							data:{id:id},
							dataType:"text",
							success:function(data){
								if(data == 'yes'){
									window.location.href = "${ctx}/pd/pdProduct/urgentList";
								}else if(data == 'no'){
									layer.alert("该商品有库存，不能删除！",{icon:0},function(index){
										layer.close(index);
									});
									//alert("该商品有库存，不能删除");
								}else{
									layer.alert("该产品正在采购，无法删除",{icon:0},function(index){
										layer.close(index);
									});
									//alert("发生了一点奇怪的错误。。");
								} 
							}
						})
					},
					function(index){
						layer.close(index);
					}
				)
			})
		})
		
		//清空查询条件
		function clearConditions(){
			$("#number").val("");
			$("#name").val("");
			$("#categoryName").val("");
			$("#groupName").val("");
			$("#version").val("");
			$("#venderName").val("");
			$("#power").val("");
			
		}
		
		//删除
		function del(id){
			if(confirm("确认要删除该产品吗？")){
				var id = id;
				$.ajax({
					url:"${ctx }/pd/pdProduct/delete",
					type:"POST",
					data:{id:id},
					dataType:"text",
					success:function(data){
						if(data == 'yes'){
							window.location.href = "${ctx}/pd/pdProduct/urgentList";
						}else if(data == 'no'){
							alert("该商品有库存，不能删除");
						}else{
							alert("发生了一点奇怪的错误。。");
						} 
					}
				})
			}
		}
	</script>
</body>
</html>