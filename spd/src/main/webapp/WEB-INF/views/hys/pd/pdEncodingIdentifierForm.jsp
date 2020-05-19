<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>应用标识符添加</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link href="${ctxStatic}/artDialog4.1.4/skins/default.css" rel="stylesheet" />
	<style>
		.hcy-public-table>tbody>tr>td>input[type='text']{width: 65px;height: 28px;border:1px solid #ccc;}
		.addManager>h3{padding:20px 0 10px 0;}
		.addManagerTab th,.addManagerTab td{text-align: center;height: 40px;border:1px solid #CCCCCC;font-size:13px;}
		.addManagerTab>tbody>tr>td>i{font-size:17px;color:#00CFA5;margin-left:6px;cursor: pointer;}
		.alertTab th,.alertTab td{font-size: 13px;text-align: center;height: 30px;font-size: 13px;}
		.alertTab th{height: 50px;font-weight: 600;}
		div.allSet>input,input{width:60px;height:30px;border-radius:1px;border:1px solid #ccc;box-shadow:none;padding:0 0 0 5px;margin:0;}
	</style>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					//如果是固定长度，需要做长度校验
					if($("#type").val()==1){
						var length = $("#length").val();
						if(isNumber(length)){
							$("#length").css('border-color','#ccc');
						}else{
							layer.tips("请输入大于0的整数",$("#length"), {
								tips: [2, '#01BFBF'],
								area:['auto','30px'],
								tipsMore: true,
								time: 2000
							});
							$("#length").css('border-color','red');
							return false
						}
						if(parseInt(length)>=20){
							layer.tips("请输入小于20的整数",$("#length"), {
								tips: [2, '#01BFBF'],
								area:['auto','30px'],
								tipsMore: true,
								time: 2000
							});
							$("#length").css('border-color','red');
							return false
						}else{
							$("#length").css('border-color','#ccc');
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
		});
	</script>
</head>
<body>
<form:form id="inputForm" modelAttribute="pdEncodingIdentifier" action="${ctx}/pd/pdEncodingIdentifier/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<sys:message content="${message}"/>
	<div class="right-main-box">
		<div class="btnBox" style="border-bottom:1px solid #ccc">
			<h4 style="padding:0;">应用标识符添加</h4>
		</div>
		<div class="addProductBox">
			<label for=""><span class="mustIcon">*</span>值</label>
			<form:input path="value" htmlEscape="false" maxlength="64" class="input-xlarge required"/><br/>
			<label for="">含义</label>
			<form:input path="meaning" htmlEscape="false" maxlength="64" class="input-xlarge"/><br/>
			<label for=""><span class="mustIcon">*</span>类型</label>
			<form:select path="type" id="type" class="input-xlarge required">
				<form:option value="" label="请选择"/>
				<form:options items="${fns:getDictList('identifier_type') }" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select><br/>
			<c:choose>
				<c:when test="${pdEncodingIdentifier.type==1}">
					<label id="lengthLabel"for=""><span class="mustIcon">*</span>长度</label>
					<form:input id="length" path="length" htmlEscape="false" maxlength="64" class="input-xlarge required"/><br/>
				</c:when>
				<c:otherwise>
					<label id="lengthLabel" style="display:none"  for=""><span class="mustIcon">*</span>长度</label>
					<form:input style="display:none" id="length" path="length" htmlEscape="false" maxlength="64" class="input-xlarge required"/><br/>
				</c:otherwise>
			</c:choose>
			<label for="">备注</label>
			<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
		</div>
	</div>
	<div class="form-actions" style="padding:0;background:#fff;text-align:center;margin-top:30px;border:none;">
		<shiro:hasPermission name="pd:pdEncodingIdentifier:edit"><input id="btnSubmit" class="hcy-btn hcy-save" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="hcy-btn hcy-back" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>

</form:form>
<script type="text/javascript">
	$(function(){
		$("#type").change(function(){
			//如果是固定长度
			if($(this).val()=="1"){
				$("#lengthLabel").show();
				$("#length").show();
			}else{
				$("#lengthLabel").hide();
				$("#length").hide();
				$("#length").val("");
			}
		})
	});
</script>
</body>
</html>