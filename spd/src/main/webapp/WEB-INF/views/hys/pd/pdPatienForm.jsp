<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta http-equiv="X-UA-Compatible" content="ie=edge" />
		<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
		<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
		<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
		<link rel="stylesheet" href="${ctxStatic}/pic-upload/pic-upload.css" />
		<style>
			#showPic  img{display:inline-block; width:512px;height:320px;margin-top:30px;}
			.showpic{cursor:pointer}
			.areaTxtBox{display: inline-block; border: 1px solid #ccc;padding: 5px; border-radius: 2px;}
			.addProductBox select option{color:#666}
		</style>
		<title>添加病人</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
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
			});
		});
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="pdPatien" enctype="multipart/form-data" method="post">
		<input type="hidden" id="flag" value="${flag }" />
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="right-main-box">
			<div class="btnBox" style="border-bottom:1px solid #ccc;height:36px;">
				<h4 style="padding-left:0;">添加病人</h4>
			</div>
			<div class="addProductBox">
				<label for=""><span class="mustIcon">*</span>姓名</label>
				<input type="text" id="name" name="name" autocomplete="off" required value="${pdPatien.name}" onKeyUp="query()"  class="input-xlarge"/>
				<label for=""><span class="mustIcon">*</span>姓名简拼</label>
				<input type="text" id="pinyin" name="pinyin" autocomplete="off" required value="${pdPatien.pinyin}"  class="input-xlarge"/>
				<br />
				<label for=""><span class="mustIcon">*</span>住院号/门诊号</label>
				<input type="text" id="inhospitalNo" name="inhospitalNo" autocomplete="off" required value="${pdPatien.inhospitalNo}"  class="input-xlarge"/>
				<label for="">身份证号</label>
				<input type="text" id="idNumber" name="idNumber" autocomplete="off" value="${pdPatien.idNumber}"  class="input-xlarge"/>
				<br />
				<label for="">手术医生</label>
				<input type="text" id="operationDoctor" name="operationDoctor" autocomplete="off" value="${pdPatien.operationDoctor}"  class="input-xlarge"/>
				<label for="">手术科室</label>
				<form:select path="operationDepartment" class="input-xlarge" >
					<form:option value="" label="无"/>
                    <form:options items="${spd:findSuperStoroomList()}" itemLabel="storeroomName" itemValue="id"/>
				</form:select>

				<%--<input type="text" id="operationDepartment" name="operationDepartment" autocomplete="off" value=""  class="input-xlarge"/>--%>
				<br />
				<label for="">住院医生</label>
				<input type="text" id="inhospitalDoctor" name="inhospitalDoctor" autocomplete="off" value="${pdPatien.inhospitalDoctor}"  class="input-xlarge"/>
				<label for="">住院科室</label>
				<form:select path="inhospitalDepartment" class="input-xlarge" >
					<form:option value="" label="无"/>
                    <form:options items="${spd:findSuperStoroomList()}" itemLabel="storeroomName" itemValue="id"/>
				</form:select>
				<%--<input type="text" id="inhospitalDepartment" name="inhospitalDepartment" autocomplete="off" value=""  class="input-xlarge"/>--%>
				<br />
				<label for="">开方医生</label>
				<input type="text" id="prescriberDoctor" name="prescriberDoctor" autocomplete="off" value="${pdPatien.prescriberDoctor}"  class="input-xlarge"/>

				<br />
				<label for="" style="vertical-align:top;">备注</label>
				<div class="areaTxtBox">
					<textarea class="spdRemarks" autocomplete="off" id="remarks" name="remarks"
                              style='margin: 0;display: block;border: none;box-shadow: none' maxlength="200" >${pdPatien.remarks}</textarea>
					<label style="text-align: right;display: block;color: #666;" class="autoLab"><span class="keyUpNum">0</span>/200</label>
				</div>
				<br />


          		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
          			<input type="submit" onclick="sub()" value="保存" class="hcy-btn hcy-save" />
          			<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
          		</div>
			</div>
		</div>

	</form:form>

	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/pic-upload/pic-upload.js"></script>
	<script src="${ctxStatic}/spd/js/pinying.js"></script>
	<script src="${ctxStatic}/spd/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
	<script src="${ctxStatic}/spd/js/barcode.js"></script>
	<script type="text/javascript">
		$(function() {

			$(".spdRemarks").keyup(function(){
				var len=$(this).val().length;
				$(".keyUpNum").text(len);
			})

            var flag = $("#flag").val();
			if(flag == "see"){
                $("input").attr("disabled","disabled");
                $("textarea").attr("disabled","disabled");
                $("select").attr("disabled","disabled");
                $(".hcy-save").hide();
            }
		})

		//提交
		function sub(){
			var flag = $("#flag").val();
			if(flag == "add"){  // 新增
				$("#inputForm").attr("action","${ctx}/pd/pdPatien/save");
			}else if(flag == "update"){  //修改
				$("#inputForm").attr("action","${ctx}/pd/pdPatien/update");
			}else {
				return false;
			}
			return true;
		}

		//首拼码
		function query(){
			var str = document.getElementById("name").value.trim();
			if(str == "") return;
			var arrRslt = makePy(str);

			document.getElementById("pinyin").innerHTML="";

			for(var j=0;j<arrRslt.length;j++){
				var obj = document.getElementById("pinyin");
				obj.value=arrRslt[j],arrRslt[j];
			}
		}

	</script>
</body>
</html>