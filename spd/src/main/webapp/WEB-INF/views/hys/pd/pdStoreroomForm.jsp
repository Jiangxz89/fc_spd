<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库房信息管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link href="${ctxStatic}/artDialog4.1.4/skins/default.css" rel="stylesheet" />
	<style>
		.endSpan{display: inline-block;height: 30px; width: 170px;line-height: 15px; font-size: 11px;box-sizing: border-box;vertical-align: middle;color: red;padding-left: 10px;}
		.hcy-public-table>tbody>tr>td>input[type='text']{width: 65px;height: 28px;border:1px solid #ccc;}
		.handBtn{margin-top:10px;}
		.moreLeast,.moreMost{width: 80px; height: 30px;border: 1px solid #ccc;margin-left: 10px;}
		.addManagerTab{width: 500px;height: auto;border-collapse: collapse;}
		.addManager>h3{padding:20px 0 10px 0;}
		.addManagerTab th,.addManagerTab td{text-align: center;height: 40px;border:1px solid #CCCCCC;font-size:13px;}
		.addManagerTab>tbody>tr>td>i.fa{font-size:17px;color:#00CFA5;margin-left:6px;cursor: pointer;}
		.alertTab{width: 100%;}
		.alertTab th,.alertTab td{font-size: 13px;text-align: center;height: 30px;font-size: 13px;}
		.alertTab th{height: 50px;font-weight: 600;}
		div.allSet>input.moreLeast,input.moreMost{width:60px;height:30px;border-radius:1px;border:1px solid #ccc;box-shadow:none;padding:0 0 0 5px;margin:0;}
	</style>
	<script src="${ctxStatic}/artDialog4.1.4/artDialog.source.js"></script>
	<script src="${ctxStatic}/artDialog4.1.4/plugins/iframeTools.source.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var rs = dealAdminInfoTableName();
					if(!rs){
						layer.alert('请添加管理人员',{icon:0});
						return false;
					}
					var strCls = $('select[name="storeroomClass"] option:selected').val();
					if(strCls == '0'){
						var rst = dealStoreInfoTableName();
						if(rst === 0 || rst ===2){
							var info = rst === 0?'请选择智能柜产品':(rst === 2?'请输入合法的库存':'');
							layer.alert(info,{icon:0});
							return false;
						}
					}
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
			});
		
			//从区域选择弹窗
			$("#addAdmin input").click(function selectGoods(){
			    var storeroomType = $("#storeroomType").val();
				//添加管理员
				layer.open({
					type:2,
					title:"添加管理员",
					content:'${ctx}/sys/user/selectAdminListForStoreroom?storeroomType='+storeroomType,
					area:["800px","500px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var childWindow = layero.find('iframe')[0].contentWindow;
						var result = childWindow.loadUserHtml();
						if(result === 0){
							layer.alert('请选择管理员',{icon: 0});
						}else{
							$('tbody#contentTableTbody').append(result);
							layer.closeAll();
						}
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			});
			
		});
		//提交时调用，绑定产品参数
		function dealStoreInfoTableName() {
			var obj = $("#proTbody tr");
			if(obj.length < 1)
				return 0;
			var flag = 1;
			obj.each(function(index, data){
				var name = 'productList[' + index + '].productId';
				$($(data).find("input")[1]).attr("name", name);
				var name1 = 'productList[' + index + '].lowStock';
				$($(data).find("input")[2]).attr("name", name1);
				var name2 = 'productList[' + index + '].highStock';
				$($(data).find("input")[3]).attr("name", name2);
				var lowStock = $.trim($($(data).find("input")[2]).val()), highStock = $.trim($($(data).find("input")[3]).val());
				if(!isValidNumber(lowStock) || !isValidNumber(highStock)){
					flag = 2;
					return false;
				}
			});
			return flag;
		}
		
		//提交时封装citylist
		function dealAdminInfoTableName() {
			var chObj = $("#contentTableTbody tr");
			if(chObj.length < 1){
				return false;
			}
			chObj.each(function(index, data){
				var name = 'adminList[' + index + '].adminId';
				$($(data).find("input")[0]).attr("name", name);
			});
			return true;
		}
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="pdStoreroom" action="${ctx}/pd/pdStoreroom/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="right-main-box">
			<div class="btnBox" style="border-bottom:1px solid #ccc">
				<h4 style="padding:0;">库房信息添加</h4>
			</div>
		<div class="addProductBox">
				<label for=""><span class="mustIcon">*</span>库房编号</label>
				<form:input path="storeroomCode" htmlEscape="false" maxlength="64" readonly="true" class="input-xlarge required"/>
				<label for=""><span class="mustIcon">*</span>库房分类</label>
				<form:select path="storeroomClass" class="input-xlarge required">
					<form:option value="">请选择</form:option>
					<form:option value="1">普通库房</form:option>
					<form:option value="0">智能柜</form:option>
				</form:select><br/>
				<label for=""><span class="mustIcon">*</span>库房名称</label>
				<form:input path="storeroomName" htmlEscape="false" autocomplete="off"	 maxlength="64" class="input-xlarge required"/>
				<label for=""><span class="mustIcon">*</span>库房类型</label>
				<c:if test="${empty pdStoreroom.id }">
					<form:select path="storeroomType" class="input-xlarge required" id="storeroomType">
						<%--<form:option value="">请选择</form:option>--%>
						<%--<form:option value="0">一级库房</form:option>--%><!-- 一级库房自己定义2019年8月19日16:15:41 -->
						<form:option value="1">二级库房</form:option>
					</form:select><br/>
				</c:if>
				<c:if test="${not empty pdStoreroom.id }">
					<form:select path="storeroomType" class="input-xlarge required" id="storeroomType">
						<c:if test="${pdStoreroom.storeroomType eq 0 }">
							<form:option value="0">一级库房</form:option>
						</c:if>
						<c:if test="${pdStoreroom.storeroomType eq 1 }">
							<form:option value="1">二级库房</form:option>
						</c:if>
					</form:select><br/>
				</c:if>
				<label for="">联系人</label>
				<form:input path="linkman" htmlEscape="false" autocomplete="off" maxlength="64" class="input-xlarge "/>
				<label for="">联系电话</label>
				<form:input path="linkmanPhone" htmlEscape="false"  autocomplete="off" onblur="limit(this.value)" class="input-xlarge "/><br />
				<label for="">联系人邮箱</label>
				<form:input path="linkmanMail" htmlEscape="false" autocomplete="off" onblur="checkEamil(this.value)" maxlength="100" class="input-xlarge "/>
				<label for="">详细地址</label>
				<form:input path="linkmanAddss" htmlEscape="false" autocomplete="off" maxlength="255" class="input-xlarge "/><br />
				<label for="">效期提醒</label>
				<form:input path="dateRemind" htmlEscape="false" maxlength="11" class="input-xlarge numInp" style="width: 58px;"/>
				个月
				<span class="endSpan">当有效期剩余月份小于提醒月份，系统进行提示</span>
				<label for="">久存提醒</label>
				<form:input path="longtimeRemind" htmlEscape="false" maxlength="11" class="input-xlarge numInp" style="width: 58px;"/>
				个月
				<span class="endSpan">当入库时间与当前时间差大于提醒日期，系统进行提示</span>
		</div>
		<div class="smartLock cl" style="display:none;">
				<h3 style="font-size:14px;color:#333;font-weight:400;">智能柜产品目录</h3>
				<table class="hcy-public-table smartLockTab">
					<thead>
						<tr>
							<th><input type="checkbox" id="allCheck" /></th>
							<th>操作</th>
							<th>产品编号</th>
							<th>产品名称</th>
							<th>产品分类</th>
							<th>产品组别</th>
							<th>单位</th>
							<th>规格</th>
							<th>型号</th>
							<th>生产厂家</th>
							<th>注册证号</th>
							<th>最低库存</th>
							<th>最高库存</th>
						</tr>
					</thead>
					<tbody id="proTbody">
						<c:forEach items="${pdStoreroom.productList}" var="pdProduct">
							<tr class="remove_'${pdProduct.productId}'" data-id="${pdProduct.productId}">
								<td><input type="checkbox"/></td>
								<td><i class="fa fa-trash-o" ></i><i class="fa fa-search"></i></td>
								<td><a href="###" class="overLook" title="${pdProduct.number}">${pdProduct.number}</a><input type="hidden" value="${pdProduct.productId}"/></td>
								<td>${pdProduct.productName}</td>
								<td>${pdProduct.categoryName}</td>
								<td>${pdProduct.groupName}</td>
								<td>${pdProduct.unit}</td>
								<td>${pdProduct.spec}</td>
								<td>${pdProduct.version}</td>
								<td>${pdProduct.venderName}</td>
								<td>${pdProduct.registration}</td>
								<td class="tdLeast"><input type="text" value="${pdProduct.lowStock}"/></td>
								<td class="tdMost"><input type="text"  value="${pdProduct.highStock}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="handBtn fl"><a href="###" id="handAddBtn" class="hcy-btn hcy-btn-primary">手动添加</a></div>
				<div class="fr allSet" style="font-size:13px;margin-top:10px;">
					<input type="text" class="moreLeast" placeholder="最低库存"/>
					<input type="text" class="moreMost"  placeholder="最高库存"/>
					<a href="###" class="hcy-btn hcy-btn-primary" id="allSetup">批量设置</a>
				</div>
			</div>
			
			<div id="addAdmin" class="control-group" style="padding:0;margin:20px 0;font-size:13px;border:none;"><span class="help-inline"><font color="red">*</font> </span>管理人员：<input  style="margin-left:20px;height:inherit;line-height:1.5 ;" class="hcy-btn hcy-btn-primary saveBtn" type="button" value="新增" /></div>
			<div class="tableBox">
					<table id="contentTable" class="hcy-public-table" style="padding:0;">
						<thead>
							<tr>
								<th>用户名</th>
								<th>姓名</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="contentTableTbody">
							<c:forEach items="${pdStoreroom.adminList}" var="admin" varStatus="status">
								<tr>
									<td>${admin.adminLoginName}<input type="hidden" value="${admin.adminId}"/></td>
									<td>${admin.adminName}</td>
									<td class="delectStoreScope" data-area-id="${admin.adminId}" ><a class="selectStore" style="cursor: pointer;">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<ul id="userIdContent" style="display:none;">
						<c:forEach items="${pdStoreroom.adminList}" var="admin" varStatus="status">
								<li id="${admin.adminId}"></li>
						</c:forEach>
					</ul>
			</div>			
		</div>
		<div class="handAddBox" style="display:none;">
			<table class="hcy-public-table addsmartLockTab">
				<thead>
					<tr>
						<th><input type="checkbox" /></th>
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
				<tbody>
					<tr>
						<td><input type="checkbox" /></td>
						<td>04543660011529</td>
						<td>冠状动111111111</td>
						<td>介入类</td>
						<td>介入类</td>
						<td>盒</td>
						<td>25.00mm*20.00mm</td>
						<td>LA25020</td>
						<td>美敦力（上海）管理有限公司</td>
					</tr>
					<tr>
						<td><input type="checkbox" /></td>
						<td>09988002560045</td>
						<td>冠状动脉22222222222</td>
						<td>高值型耗材类</td>
						<td>介入类</td>
						<td>盒</td>
						<td>16.00mm*28.00mm</td>
						<td>004F啊</td>
						<td>美敦力（上海）管理有限公司</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="form-actions" style="padding:0;background:#fff;text-align:center;margin-top:30px;border:none;">
			<shiro:hasPermission name="pd:pdStoreroom:edit"><input id="btnSubmit" class="hcy-btn hcy-save" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="hcy-btn hcy-back" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<!-- 记录添加的行号 -->
	<input type="hidden" id="rowsRecord" value="0"/>
	</form:form>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script>
		var prodIds = [];
		$(function(){
			//全选
			$("#allCheck").click(function(){
				if($(this).prop("checked")){
					$(".smartLockTab>tbody>tr").find("input[type='checkbox']").prop("checked",true);
				}else{
					$(".smartLockTab>tbody>tr").find("input[type='checkbox']").prop("checked",false);
				}
			});
			//批量设置
			$("#allSetup").click(function(){
				var small = $.trim($(".moreLeast").val());
				var big = $.trim($(".moreMost").val());
				var choobj = $(".smartLockTab>tbody>tr input[type='checkbox']:checked");
				if(choobj.length < 1){
					layer.alert('请选择智能柜产品',{icon:0});
					return false;
				}
				if(isValidNumber(small) && isValidNumber(big)){
					choobj.each(function(){
						$(this).parents("tr").find("td.tdLeast>input").val(small);
						$(this).parents("tr").find("td.tdMost>input").val(big);
					});
				}
			});
			//手动添加
			$("#handAddBtn").click(function(){
				layer.open({
					type:2,
					title:"添加产品",
					content:'${ctx}/pd/pdProduct/chooseProductListForStoreroom',
					area:["965px","527px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var childWindow = layero.find('iframe')[0].contentWindow;
						var result = childWindow.compositeHtml($('#rowsRecord').val());
						if(result === 0){
							layer.alert('请选择产品',{icon: 0});
						}else{
							$('#proTbody').append(result);
							$('#rowsRecord').val($('tbody>tr').length);
							layer.closeAll();
						}
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			});
			
			//删除管理员
			$(document).on('click','.selectStore',function(){
				$(this).parent().parent().remove();
			});
			
			$("select[name='storeroomClass']").on('change', function(){
				var obj = $(this).find('option:selected');
				if(obj.val()=='0'){
					$('.smartLock').show();
				}else{
					$('.smartLock').hide();
				}
			});
			//显示隐藏智能柜目录
			$("#storeroomClass").change(function(){
				var val=$(this).val();
				if(val=="1"){
					$(".smartLock").hide();
				};
				if(val=="0"){
					$(".smartLock").show();
				}
			});
			
			$('select[name="storeroomClass"]').change();
		});
		//删除该产品
		function removeDiv(id){
			$('.remove_'+id).remove();
			var delIndex = prodIds.indexOf(id);
			if(delIndex > -1){
				prodIds.splice(delIndex,1);
			}
		}
		
		//检查输入是否是合法数字
		function isValidNumber(value){
			if(value && !isNaN(value) && parseInt(value) > 0 && Math.floor(value) == value)
				return true;
			return false;
		}
		//手机
		function limit(value){
			var phone = value;
			var reg =/^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
			var regTel1 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;//带区号的固定电话
		    var regTel2 = /^(\d{7,8})(-(\d{3,}))?$/;//不带区号的固定电话
		    if(phone.length==11){
		    	if(reg.test(phone) == false){
					layer.alert('联系电话格式不正确',{icon:0});
					$("input[name='linkmanPhone']").val("");
				 }
		    }else{
		    	if(!regTel1.test(phone) && !regTel2.test(phone)){
		    		layer.alert('联系电话格式不正确',{icon:0});
		    		$("input[name='linkmanPhone']").val("");
		    	}
		    }
			
		}
		//邮箱
		function checkEamil(value){
		　　var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
		　　if(!reg.test(value)){
			   layer.alert('邮箱格式不正确',{icon:0});
		　　　　$("input[name='linkmanMail']").val("")
		　　}
		}
	</script>
</body>
</html>