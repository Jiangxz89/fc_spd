<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>编码规则添加</title>
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
    <script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var isn = $.trim($('tbody').html());
					if(!isn){
						layer.alert('请添加应用标识符',{icon:0})
						return false;
					}
					var flag = true;
					$("input[name$='length']").each(function(i,v){
						if($(this).attr("readonly")!="readonly"){
							var result = checkAlertWarn('请输入大于0的整数', $(this));
							if(result == false){
								flag = false;
								return false;
							}
						}
						var rl = checkDigit($(this));
						if(rl == false){
							flag = false;
							return false;
						}
					});
					$("input[name$='codeOrder']").each(function(i,v){
						var result = checkAlertWarn('请输入大于0的整数', $(this));
						if(result == false){
							flag = false;
							return false;
						}
					});
					if(!flag)
						return false;
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
<form:form id="inputForm" modelAttribute="pdEncodingRule" action="${ctx}/pd/pdEncodingRule/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<input type="hidden" id="flag" name="flag" value="${flag }" />
	<sys:message content="${message}"/>
	<div class="right-main-box">
		<div class="btnBox" style="border-bottom:1px solid #ccc">
			<h4 id="titleH" style="padding:0;">编码规则添加</h4>
		</div>
		<div class="addProductBox">
			<label for=""><span class="mustIcon">*</span>名称</label>
			<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/><br/>
			<label for="">备注</label>
			<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
		</div>
		<div class="relatedBox">
			<h1 id="titleHOne">添加应用标识符</h1>
			<div class="tableBox">
				<table id="contentTable" class="hcy-public-table" style="padding:0;">
					<thead>
					<tr>
						<th><input type="checkbox" id="checkAll"></th>
						<th>应用标识符</th>
						<th>应用标识符含义 </th>
						<th>内容位数 </th>
                        <th>类型 </th>
						<th>顺序</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${pdEncodingRule.pdEncodingRuleDetails }" var="hos" varStatus="a">
						<tr>
                            <input type="hidden" class="identifierId" name="pdEncodingRuleDetails[${a.index }].identifier" value="${hos.identifier }" />
							<input type="hidden"  name="pdEncodingRuleDetails[${a.index }].value" value="${hos.value }" />
							<td><input type="checkbox" name="chkList" class="checkOne"/></td>
							<td>${hos.value }</td>
                            <td>${hos.meaning }</td>
                            <c:choose>
                                <c:when test="${hos.type == '1'}">
									<td><input type="text" value="${hos.size }" readonly="readonly" name="pdEncodingRuleDetails[${a.index }].length" style="text-align:center;width:60px;" maxlength="10" data-flag="${hos.identifier }"/></td>
                                </c:when>
                                <c:otherwise>
									<td><input type="text" value="${hos.length }" name="pdEncodingRuleDetails[${a.index }].length" style="text-align:center;width:60px;" maxlength="10" class="identifierLength" data-flag="${hos.identifier }"/></td>
                                </c:otherwise>
                            </c:choose>
                            <td>${fns:getDictLabel(hos.type, 'identifier_type', '')}</td>
							<td><input type="text" value="${hos.codeOrder }" name="pdEncodingRuleDetails[${a.index }].codeOrder" style="text-align:center; width:60px;" maxlength="10" class="identifierOrder" data-flag="${hos.identifier }"/></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<c:if test="${flag ne 'see' }">
					<a class="hcy-btn hcy-btn-primary" id="addIdentifier">选择标识符</a>
					<a class="hcy-btn hcy-btn-primary" onclick="del()">删除</a>
				</c:if>
			</div>
		</div>
	</div>
	<div class="form-actions" style="padding:0;background:#fff;text-align:center;margin-top:30px;border:none;">
		<c:if test="${flag ne 'see' }">
			<input type="submit" onclick="sub()" value="保存" class="hcy-btn hcy-save" />
		</c:if>
		<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
	</div>

</form:form>
<script type="text/javascript">
	$(function(){
	    //查询时隐藏输入框
		if($("#flag").val() == 'see'){
			$("input").attr("disabled","disabled");
            $("textarea").attr("disabled","disabled");
            $("#titleH").text("编码规则详情");
            $("#titleHOne").text("应用标识符列表");
		}
		//添加标识符
        $("#addIdentifier").click(function(){
            //	showData();
            layer.open({
                type:2,
                title:"添加标识符",
                content:'${ctx}/pd/pdEncodingIdentifier/getList',
                area:["965px","527px"],
                shade: [0.8, '#393D49'],
                btn:["确定","取消"],
                yes:function(index,layero){
                    var vue = $('tbody>tr').length;
                    var childWindow = layero.find('iframe')[0].contentWindow;
                    var result = childWindow.compositeHtml(vue);
                    if(result === 0){
                        layer.alert('请选择应用标识符',{icon: 0});
                    }else{
                        $('tbody').append(result);
                        layer.close(index);
                    }
                },
                btn2:function(){
                    layer.closeAll();
                }
            })
        });
	});

	//校验长度是否合理
	$(document).on('input propertychange', '.identifierLength', function(){
		checkAlertWarn('请输入大于0的整数', $(this));
		checkDigit($(this));
	})
	//校验顺序是否合理
	$(document).on('input propertychange', '.identifierOrder', function(){
		if($(this).attr("readonly")!="readonly"){
			checkAlertWarn('请输入大于0的整数', $(this));
		}
	})
	//保存按钮
    function sub(){
        var flag = $("#flag").val();
        if(flag == 'update'){
            $("#inputForm").attr("action","${ctx }/pd/pdEncodingRule/update");
        }else if(flag == 'add'){
            $("#inputForm").attr("action","${ctx }/pd/pdEncodingRule/save");
        }
    }
    //校验位数
	function checkDigit(obj){
		if(obj.val()-30>0){
			layer.tips("位数不能大于30",obj, {
				tips: [2, '#01BFBF'],
				area:['auto','30px'],
				tipsMore: true,
				time: 2000
			});
			return false;
		}else{
			obj.css('border-color','#ccc');
			return true;
		}
	}

	//校验提示
	function checkAlertWarn(msg,obj){
		if(msg=="" || msg==undefined){
			msg="请填写完整收据！"
		}
		if(isCheckNumber($(obj).val())){
			obj.css('border-color','#ccc');
			return true
		}else{
			layer.tips(msg,obj, {
				tips: [2, '#01BFBF'],
				area:['auto','30px'],
				tipsMore: true,
				time: 2000
			});
			obj.css('border-color','red');
			return false
		}
	}

	function isCheckNumber(s){
		if(!s) return false;
		s = parseInt(s);
		if(isNaN(s)){
			return false;
		}
		var re = /^\+?[1-9][0-9]*$/;
		return re.test(s);
	}


	//重复产品不能添加
    function repetitionDel(target){
        var target = target;
        var f = 0;
        $("tbody>tr").each(function(){
            var id = $(this).find(".identifierId").val();
            if(id == target){
                f = $(this).children('td').eq(2).text();
                layer.alert(f+' 已被添加。',{icon: 0});
            }
        })
        if(f!=0){
            return f;
        }else{
            return 'success';
        }
    }
	//全选按钮
	$("#checkAll").click(function(){
		if($(this).prop("checked")){
			$(this).parents("thead").siblings("tbody").find("input[type=checkbox]").attr("checked",true);
		}else{
			$(this).parents("thead").siblings("tbody").find("input[type=checkbox]").attr("checked",false);
		}
	});
	//删除按钮
	function del(){
		if($("#checkAll").prop("checked")){
			$("#checkAll").parents("thead").siblings("tbody").find("tr").remove();
			$("#checkAll").attr("checked",false);
		}
		$(".checkOne").each(function(){
			if($(this).prop("checked")){
				$(this).parent().parent().remove();
			}
		})
	}
</script>
</body>
</html>