<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<style>
		.hcy-public-table>tbody>tr>td>input[type="text"]{width: 60px;height: 30px;border:1px solid #ccc;}
		.littleTip{font-size: 14px;color: #000;padding-right: 10px;display: inline-block;width: 70px;}
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;}
		.otherText>h3{font-weight:400;display:inline-block;padding:3px 10px 0 5px;font-size:12px;color:#666;width:93px;}
	</style>
	<title>添加定数包</title>
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
		<input type="hidden" name="delKey" value="${delKey }" />
		<form:form id="searchForm"  modelAttribute="pdPackage"  method="post" class="breadcrumb form-search">
			<div class="right-main-box">
				<div class="btnBox">
					<h4>添加定数包</h4>
				</div>
				<input type="hidden" id="packageNumberFlag" value="${packageNumber }" />
				<div class="searchBox">
					<input type="hidden" id="flag" value="${flag }" />
					<input type="hidden" id="packageNumberFlag" value="${packageNumber }" />
					<input type="hidden" id="info" value="${info }" />
					<label for="">定数包名称</label>
					<input type="text" id="packageName" required name="name" value="${pdPackage.name }" />
					<label for="">定数包编号</label>
					<input type="text" id="packageNumber" required name="number" onkeypress="return false" value="${pdPackage.number }" />
					<input type="hidden" id="idFlag" name="id" value="${pdPackage.id }"/>
					<c:if test="${flag == 'save' || flag == 'copy' }">
						<!-- <a href="###" onclick="scan()" class="hcy-btn hcy-btn-primary" style="line-height: 1.5;;">扫描</a> -->		
						<!-- <a href="###" onclick="generateNumber()" class="hcy-btn hcy-btn-primary" style="line-height: 1.5;;">生成</a> -->
						<!-- <a href="###" class="hcy-btn hcy-btn-primary" style="line-height: 1.5;;">条码打印</a> -->	
					</c:if>
				</div>
				<div class="tableBox">
					<table class="hcy-public-table" id="fixedNumTable">
						<thead>
							<tr>
								<th>产品编号</th>
								<th>产品名称</th>
								<th>产品分类</th>
								<th>产品组别</th>
								<th>单位</th>
								<th>规格</th> 
								<th>型号</th>
								<th>生产厂家</th>
								<th>注册证号</th>
								<th>数量</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tbody">
							<!-- <c:if test="${flag != 'save' }"> -->
								<c:forEach items="${list }" var="a" varStatus="aa">
									<tr id="${a.id }">
										<input type="hidden" class="idInput" name="child[${aa.index }].id" value="${a.id }" />
										<input type="hidden" name="child[${aa.index }].productId" class="prodIdClass" value="${a.pdProduct.id }" />
										<td>${a.pdProduct.number }</td>
										<td>${a.pdProduct.name }</td>
										<td>${a.pdProduct.categoryName }</td>
										<td>${a.pdProduct.groupName }</td>
										<td>${a.pdProduct.unit }</td>
										<td>${a.pdProduct.spec }</td>
										<td>${a.pdProduct.version }</td>
										<td><a href="###" class="overLook" title="${a.pdProduct.venderName }">${a.pdProduct.venderName }</a></td>
										<td><a href="###" class="overLook" title="${a.pdProduct.registration }">${a.pdProduct.registration }</a></td>
										<td><input name="child[${aa.index }].productCount" type="text" style="width:60px;" value="${a.productCount }" required class="productCount"/></td>
										<td>${a.pdProduct.remarks }</td>
										<td>
											<a href="${ctx }/pd/pdProduct/toUpdate?id=${a.pdProduct.id}&flag=see" class="hcy-btn hcy-btn-o" style="margin-right:3px;">查看</a>
											<c:if test="${flag != 'detail' }">
												<a href="###" class="hcy-btn hcy-btn-o removeProBtn">删除</a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							<!-- </c:if> -->
						</tbody>
					</table>
					<c:if test="${flag != 'detail' }">
						<a href="###" class="hcy-btn hcy-btn-primary" id="addProBtn" style="margin-top:15px;" >添加产品</a>
					</c:if>
					
					<div class="otherText" style="margin-top: 30px;">
						<h3>备注</h3>
						<textarea name="description" class="remarkArea">${pdPackage.description }</textarea>
					</div>
				</div>
				<div class="bottomBtn" style="text-align: center;margin:30px 0;">
					<c:if test="${flag == 'save' || flag == 'copy' }">
						<input type="submit" onclick="save('save')" style="line-height:1.5;" class="hcy-btn hcy-save" value="保存" />
						<!-- <a href="###" onsubmit="" onclick="save('save')" style="line-height:1.5;" class="hcy-btn hcy-save">保存</a>  -->
					</c:if>
					<c:if test="${flag == 'update' }">
						<input type="submit" onclick="save('update')" style="line-height:1.5;" class="hcy-btn hcy-save" value="保存" />
						<!-- <a href="###" onclick="save('update')" style="line-height:1.5;" class="hcy-btn hcy-save">保存</a> -->
					</c:if>	
	      			<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
	      		</div>
			</div>
		</form:form>
		<!-- 记录添加的行号 -->
		<input type="hidden" id="rowsRecord" value="0"/>
		<%-- <div class="addProBox" style="display:none;">
			<form:form id="searchProdForm" style="padding:0;background:#fff;" modelAttribute="pdProduct" onsubmit="showData()" method="post" class="breadcrumb form-search">
			<div class="searchBox">
				<div>
					<label for="">产品编号</label>
					<input type="text" id="number" name="numberSearch" value="${pdProduct.number }"/>
				</div>
				<div>
					<label for="">产品名称</label>
					<input type="text" id="name" name="nameSearch" value="${pdProduct.name }"/>
				</div>
				<div>
					<label for="">产品分类</label>
					<select id="categoryName" name="categoryName">
						<option value="">--全部--</option>
						<c:forEach var="pdCategory" items="${pdCategoryList}">  
	                        <option value="${pdCategory.name}" <c:if test="${pdProduct.categoryName eq pdCategory.name}">selected</c:if>>${pdCategory.name}</option>  
	                    </c:forEach>
					</select>
				</div>
				<div>
					<label for="">产品组别</label>
					<select id="groupName" name="groupName">
						<option value="">--全部--</option>
						<c:forEach var="pdGroup" items="${pdGroupList}">  
	                        <option value="${pdGroup.name}" <c:if test="${pdProduct.groupName eq pdGroup.name}">selected</c:if>>${pdGroup.name}</option>  
	                    </c:forEach>
					</select>
				</div>
				<div>
					<label for="">产品型号</label>
					<input type="text" id="version" name="version" value="${pdProduct.version }"/>
				</div>
				<div>
					<label for="">生产厂家</label>
					<input type="hidden" name="venderName" value="" />
					<select id="venderId" name="venderId" style="width:346px;">
						<option value="">--全部--</option>
						<c:forEach var="pdVender" items="${pdVenderList}">  
	                        <option value="${pdVender.id}" <c:if test="${pdProduct.venderName eq pdVender.name}">selected</c:if>>${pdVender.name}</option>  
	                    </c:forEach> 
					</select>
				</div>
				<div>
					<label for="">产品权限</label>
					<select id="power" name="power">
						<option value="">--全部--</option>
						<c:forEach var="dict" items="${fns:getDictList('product_power') }">
							<option value="${dict.label eq '公共产品'?'0':'1' }" <c:if test="${pdProduct.power eq dict.label}">selected</c:if>>${dict.label }</option>
						</c:forEach>
						<option value="公共产品" <c:if test="${pdProduct.power eq '公共产品'}">selected</c:if>>公共产品</option>
						<option value="自有产品" <c:if test="${pdProduct.power eq '自有产品'}">selected</c:if>>自有产品</option>
					</select>
				</div>
				<!-- <input id="search" class="hcy-btn hcy-btn-primary" type="button" value="查询"/> -->
				<input id="btnSubmit" class="hcy-btn hcy-search" style="height: inherit;line-height:1.5;" type="button" onclick="showData()" value="查询"/>
				<a href="###" onclick="clearConditions()" class="hcy-btn hcy-reset">重置</a>
			</div>
		
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<table class="hcy-public-table" id="addContentTab" style="padding-top:0;">
				<thead>
					<tr>
						<th><input type="checkbox" id="chkAll" name="chkAll" /></th>
						<th>产品编号</th>
						<th>产品名称</th>
						<th>产品分类</th>
						<th>产品组别</th>
						<th>单位</th>
						<th>规格</th>
						<th>型号</th>
						<th>生产厂家</th>
					</tr>
				</thead>
				<tbody id="tbody">
					
				</tbody>
			</table>
			<div class="pagination">${page}</div>
			</form:form>
		</div> --%>
		<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
		<script src="${ctxStatic}/spd/js/layer.js"></script>
		<script type="text/javascript">
		$(function(){
			//定位光标
			$("#packageName").focus();
			
			//标记判断
			var b = $("#flag").val();
			if(b == 'detail'){
				$("#packageNumber").attr("disabled","disabled");
				$("#packageName").attr("disabled","disabled");
				$(".productCount").attr("disabled","disabled");
			}else if(b == 'update'){
				$("#packageNumber").attr("disabled","disabled");
			}else if(b == 'copy'){
				$(".idInput").attr("value","");
				$("#idFlag").attr("value","");
				$("#packageName").attr("value","");
				$("#packageNumber").attr("value","");
			}
			
			//删除失败判断
			var delKey = $("[name=delKey]").val();
			if(delKey == 'alloError'){
				layer.alert("该定数包与调拨单关联，无法删除",{icon:0},function(index){
					layer.close(index);
				});
			}
			if(delKey == 'applyError'){
				layer.alert("该产品与申购单关联，无法删除",{icon:0},function(index){
					layer.close(index);
				});
			}
			
			//判断回退编号标记是否为空
			if($("#packageNumberFlag").val() != "" && $("#packageNumberFlag").val() != null){
				var packageNumFl = $("#packageNumberFlag").val();
				$("#packageNumber").val(packageNumFl);
			}
			//生成编号
			if($("#packageNumber").val() == "" || $("#packageNumber").val() == null){
				$.ajax({
					type:"POST",
					url:"${ctx}/pd/pdPackage/generateNumber",
					dataType:"json",
					success:function(data){
						var data = eval(data);
						var site = (data.site).substring(1);
						$("#packageNumber").val(data.number);
						//$("#barcodeImg").attr("src","${ctxImg}"+site);
					}
				})
			}
			//
			if($("#info").val() != null && $("#info").val() != ""){
				var infoChar = $("#info").val();
				var infoCharArr = infoChar.split(";");
				for(var i = 0 ; i < infoCharArr.length ; i ++){
					if(infoCharArr[i] == null || infoCharArr[i].split(",")[0] == null || infoCharArr[i].split(",")[0] == ''){
						continue;
					}
					var prodId = infoCharArr[i].split(",")[0];
					var prodNum = infoCharArr[i].split(",")[1];
					$.ajax({
						type:"POST",
						url:"${ctx}/pd/pdPackage/backForm",
						data:{id:prodId},
						dataType:"json",
						success:function(data){
							var data = eval(data);
							var html = '';
							var trSize = $(".trLn").size();
							html += '<tr id="'+data.id+'" class="trLn"><input type="hidden" name="child['+trSize+'].productId" value="'+data.id+'" />'+
							'<td><a href="###" title="'+data.number+'" >'+data.number+'</a></td>'+
							'<td><a href="###"  title="'+data.name+'">'+data.name+'</a></td>'+
							'<td>'+data.categoryName+'</td>'+
							'<td>'+data.groupName+'</td>'+
							'<td>'+data.unit+'</td>'+
							'<td>'+data.spec+'</td>'+
							'<td>'+data.version+'</td>'+
							'<td>'+data.venderName+'</td>'+
							'<td>'+data.registration+'</td>'+
							'<td class="vue"><input name="child['+trSize+'].productCount" value="'+prodNum+'" type="text" style="width:60px;" required /></td>'+
							'<td>'+data.description+'</td>'+
							'<td>'+
								'<a href="###" onclick="toProductDetail(this)" class="hcy-btn hcy-btn-o" style="margin-right:3px;">查看</a>'+
								'<a href="###" onclick="del("'+data.id+'")" class="hcy-btn hcy-btn-o delTrBtn">删除</a>'+
							'</td>'+
						'</tr>';
						$("#tbody").append(html);
						
						}
					})
				}
				
			}
			//生产厂家改变
			$("[name=venderId]").change(function(){
				var name = $("[name=venderId]>option:checked").text();
				var id = $("[name=venderId]>option:checked").val();
				$("[name=venderName]").val(name);
				$("[name=venderId]").val(id);
			})
			
			//添加产品
			$("#addProBtn").click(function(){
			//	showData();
				layer.open({
					type:2,
					title:"添加产品",
					content:'${ctx}/pd/pdPackage/getAllProduct',
					area:["965px","527px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var vue = $('tbody>tr').length;
						var childWindow = layero.find('iframe')[0].contentWindow;
						var result = childWindow.compositeHtml(vue);
						if(result === 0){
							layer.alert('请选择产品',{icon: 0});
						}else{
							$('tbody').append(result);
							$('#rowsRecord').val($('tbody>tr').length);
							layer.close(index);
						}
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			});
			
			//全选
			$("#chkAll").click(function(){
				if($(this).attr("checked") == 'checked'){
					$("input[name='chkList']").attr("checked",$(this).attr("checked"));
				}else{
					$("input[name='chkList']").removeAttr("checked");
				}
				
			})
		
			//删除
			$(".removeProBtn").click(function(){
				$(this).parent().parent().remove();
			})	
		})
		//请求数据
		 function showData(){
			$.ajax({
				type:"post",
				url:"${ctx}/pd/pdPackage/getAllProduct",
				data:{
					number:$("[name=numberSearch]").val(),
					name:$("[name=nameSearch]").val(),
					categoryName:$("[name=categoryName]").val(),
					groupName:$("[name=groupName]").val(),
					version:$("[name=version]").val(),
					venderId:$("[name=venderId]").val(),
					power:$("[name=power]").val(),
					pageNo:$("[name=pageNo]").val(),
					pageSize:$("[name=pageSize]").val()
				},
				dataType:"json",
				success:function(data){
					showTable(data);
				}
			})
		} 
		//加载数据列表
		function showTable(data){
			$("#addContentTab>tbody").empty();
			var data=eval(data);
			$(".pagination").append(data);
			var html='';
			var pageHtml='';
			for(var i=0;i<data.list.length;i++){
				html+='<tr>'+
						'<td><input type="checkbox" name="chkList" /></td>'+
						'<input type="hidden" class="addProId" value="'+data.list[i].id+'" />'+
						'<td class="addProNum"><a href="###" title="'+data.list[i].number+'" >'+data.list[i].number+'</a></td>'+
						'<td class="addProName"><a href="###" title="'+data.list[i].name+'">'+data.list[i].name+'</a></td>'+
						'<td class="addProType">'+data.list[i].categoryName+'</td>'+
						'<td class="addProGroup">'+data.list[i].groupName+'</td>'+
						'<td class="addProUnit">'+data.list[i].unit+'</td>'+
						'<td class="addProformat">'+data.list[i].spec+'</td>'+
						'<td class="addProCode">'+data.list[i].version+'</td>'+
						'<td class="addProFoc">'+data.list[i].venderName+'</td>'+
						'<input type="hidden" class="addProRegistration" value="'+data.list[i].registration+'" />'+
						'<input type="hidden" class="addProDescription" value="'+data.list[i].description+'" />'+
					'</tr>'
			}
		}
		
		//提交
		function save(flag){
			var tr = $("#fixedNumTable>tbody>tr").size();
			if(tr <= 0){
				layer.alert('需要添加产品。',{icon: 0});
				return false;
			}else{
				$("#searchForm").attr("action","${ctx}/pd/pdPackage/save?flag="+flag);
				return true;
			}
		}
		
		//重复产品不能添加
		function repetitionDel(target){
			var target = target;
			var f = 0;
			$("#fixedNumTable>tbody>tr").each(function(){
				var id = $(this).children('input').eq(1).val();
				if(id == target){
					f = $(this).children('td').eq(1).text();
					layer.alert('产品 '+f+' 已被添加。',{icon: 0});
				//	alert('产品 '+f+' 已被添加。');
				}
			})
			if(f!=0){
				return f;
			}else{
				return 'success';
			}
		}
		
		//删除
		function del(id){
			var aid = id;
			var aaid = "#"+aid;
			$(aaid).remove();
		}
		//键盘键入开放
		function scan(){
			$("#packageNumber").removeAttr("onkeypress");
		}
		
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
		//重置
		function clearConditions(){
			$("#searchProdForm").find("input[type='text']").val("");
			$("#searchProdForm").find("select").val("");
		}
		
		//跳转产品详情页
		function toProductDetail(obj){
			//保留临时信息
			var id = obj.parentNode.parentNode.firstChild.value;
			var packageNumber = $("#packageNumber").val();
			var info = '';
			
			var a = 0;
			$("tr").each(function(){
				if(a == 0){
					
				}else{
					info += $(this).children(1).val() + ',';
					info += $(this).find(".vue").children(1).val() + ';';	
				}
				a++;
			})
			
			$.ajax({
				type:'post',
				url:'${ctx}/pd/pdPackage/packageSession',
				dataType:'text',
				data:{prodId:id,packageNumber:packageNumber,info:info},
				success:function(){
					window.location = "${ctx}/pd/pdProduct/toUpdateFromPackage?flag=see&packageFlag=yes&id="+id;
				}
			})
			
		}
		
		</script>
</body>
</html>