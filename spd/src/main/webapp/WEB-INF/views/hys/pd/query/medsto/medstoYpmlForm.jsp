<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/pic-upload/pic-upload.css" />
	<style>
		.areaTxtBox{display: inline-block; border: 1px solid #ccc;padding: 5px; border-radius: 2px;}
		.addProductBox select option{color:#666}
		.addOption{z-index:66;}
	</style>
	<title>药品基础目录</title>
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
			
			//查看 禁用
			if ('${oprt}' == 'view') {
				$('input,select').attr('disabled','true');
			}
			resetTip();
		});
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="medstoYpml" action="" enctype="multipart/form-data" method="post">
		<form:hidden path="xh" value="${medstoYpml.xh}"/>
		<c:if test="${empty oprt }">
			<sys:message content="${message}"/>
		</c:if>
		<div class="right-main-box">
			<div class="btnBox" style="border-bottom:1px solid #ccc;height:36px;">
				<h4 style="padding-left:0;">药品基础目录</h4>
			</div>
			<div class="addProductBox">
				<label for="">药品代码</label>
				<form:input path="ypdm"  maxlength="18" disabled="true"/>
				<label for="">药品名称</label>
				<form:input path="ypmc"  maxlength="64"/><br />
				<label for="">药品规格</label>
				<form:input path="ypgg"  maxlength="2"/>
				<label for="">国家基药标志</label>
				<form:select path="basicdrugFlag" class="input-large required" cssStyle="width: 260px">
					<form:option value="">请选择</form:option>
					<form:options items="${fns:getDictList('yes_no') }" itemLabel="label" itemValue="value"/>
				</form:select><br />
				<label for="">抗生素级别</label>
				<form:select path="kssjb" class="input-large" cssStyle="width: 260px">
                    <form:option value="">非限制使用级</form:option>
                    <form:option value="">否</form:option>
				</form:select>
				<label for="">处方药品</label>
				<form:input path="cfyp"  maxlength="32"/><br />
				<label for="">规格idm</label>
				<form:input path="ggIdm"  maxlength="8"/>
				<label for="">临床idm</label>
				<form:input path="lcIdm"  /><br />
				<label for="">药品类号</label>
				<form:input path="yplh"  />
				<label for="">剂型代码</label>
                <form:input path="jxdm"  /></br>
				<label for="">分类代码</label>
				<form:input path="fldm"  />
				<label for="">最小单位</label>
				<form:input path="zxdw"  /></br>
				<label for="">规格单位</label>
				<form:input path="ggdw"  />
				<label for="">规格系数</label>
				<form:input path="ggxs"  /></br>
				<label for="">厂家代码</label>
				<form:input path="cjdm"  />
				<label for="">厂家名称</label>
				<form:input path="cjmc"  /></br>
				<label for="">有效期</label>
				<form:input path="yxq"  />
				<label for="">药库单位</label>
				<form:input path="ykdw"  /></br>
				<label for="">药库系数</label>
				<form:input path="ykxs"  />
                <label for="">进货单位</label>
                <form:input path="jhdw"  /></br>
                <label for="">进货系数</label>
                <form:input path="jhxs"  />
                <label for="">门诊单位</label>
                <form:input path="mzdw"  /></br>
                <label for="">门诊系数</label>
                <form:input path="mzxs"  />
                <label for="">住院单位</label>
                <form:input path="zydw"  /></br>
                <label for="">住院系数</label>
                <form:input path="zyxs"  />
                <label for="">零售价</label>
                <form:input path="ylsj"  /></br>
                <label for="">批发价</label>
                <form:input path="ypfj"  />
                <label for="">默认进价</label>
                <form:input path="mrjj"  /></br>
                <label for="">上限价格</label>
                <form:input path="sxjg"  />
                <label for="">自费标志</label>
                <form:input path="zfbz"  /></br>
                <label for="">自费比例</label>
                <form:input path="zfbl"  />
                <label for="">住院自费标志</label>
                <form:input path="zyzfbz"  /></br>
                <label for="">住院自费比例</label>
                <form:input path="zyzfbl"  />
                <label for="">贵重标志</label>
                <form:input path="gzbz"  /></br>
                <label for="">特殊标志</label>
                <form:input path="tsbz"  />
                <label for="">累计领药标志</label>
                <form:input path="ljlybz"  /></br>
                <label for="">皮试标志</label>
                <form:input path="psbz"  />
                <label for="">停用标志</label>
                <form:input path="tybz"  /></br>
                <label for="">取整标志</label>
                <form:input path="qzbz"  />
                <label for="">医保限制标志</label>
                <form:input path="ybxzbz"  /></br>
                <label for="">医保控制标志</label>
                <form:input path="ybkzbz"  />
                <label for="">药品扣率</label>
                <form:input path="ypkl"  /></br>
                <label for="">招标标志</label>
                <form:input path="zbbz"  />
                <label for="">录入日期</label>
                <fmt:formatDate value="${medstoYpml.lrrq }" pattern="yyyy-MM-dd" var="czrqDate"/>
                <form:input path="lrrq" value="${czrqDate } " /></br>
                <label for="">账目类别</label>
                <form:input path="zmlb"  />
                <label for="">供货单位代码</label>
                <form:input path="ghdwId"  /></br>
                <label for="">供货单位名称</label>
                <form:input path="ghdwMc"  />
                <label for="">药品来源</label>
                <form:input path="yply"  /></br>
                <label for="">批准文号</label>
                <form:input path="pzwh"  />
                <label for="">gmp标志</label>
                <form:input path="gmpbz"  /></br>
                <label for="">操作员</label>
                <form:input path="czyh"  />
                <label for="">操作日期</label>
                <fmt:formatDate value="${medstoYpml.czrq }" pattern="yyyy-MM-dd" var="czrqDate"/>
                <form:input path="czrq"   value="${czrqDate } " /></br>
                <label for="">招标单位</label>
                <form:input path="zbdw"  />
                <label for="">默认招标价</label>
                <form:input path="mrzbj"  /></br>
                <label for="">招标期号</label>
                <form:input path="zbqh"  />
                <label for="">单复方标志</label>
                <form:input path="dffbz"  /></br>
                <label for="">对应代码</label>
                <form:input path="dydm"  />
                <label for="">说明</label>
                <form:input path="memo"  /></br>
                <label for="">备案标志</label>
                <form:input path="babz"  />
                <label for="">otc标志</label>
                <form:input path="otcbz"  /></br>
                <label for="">报销标志</label>
                <form:input path="bxbz"  />
                <label for="">药品说明书</label>
                <form:input path="ypsms"  /></br>
                <label for="">官方价格</label>
                <form:input path="govprice"  />
                <label for="">价格依据</label>
                <form:input path="jgyj"  /></br>
                <label for="">官方名称</label>
                <form:input path="gfmc"  />
                <label for="">医保名称</label>
                <form:input path="ybmc"  /></br>
                <label for="">公费标志</label>
                <form:input path="gfbz"  />
                <label for="">医保费用等级</label>
                <form:input path="ybfydj"  /></br>
                <label for="">适用范围</label>
                <form:input path="syfw"  />
                <label for="">适应症标志</label>
                <form:input path="syzbz"  /></br>
                <label for="">换药标志</label>
                <form:input path="hybz"  />
                <label for="">医嘱管理标志</label>
                <form:input path="yzglbz"  /></br>
                <label for="">省医保标志</label>
                <form:input path="sybbz"  />
                <label for="">省公费标志</label>
                <form:input path="sgfbz"  /></br>
                <label for="">零差结算药品</label>
                <form:input path="islcjsyp"  />
                <label for="">零差结算单价</label>
                <form:input path="lcjsdj"  /></br>
                <label for="">特殊药品管理标志</label>
                <form:input path="tsypglbz"  />
                <label for="">特殊药品采购方式</label>
                <form:input path="tsypcgfs"  /></br>
                <label for="">公用标志</label>
                <form:input path="gybz"  />
                <label for="">批准文号效期</label>
                <fmt:formatDate value="${medstoYpml.pzwhxq }" pattern="yyyy-MM-dd" var="pzwhxqDate"/>
                <form:input path="pzwhxq" value="${pzwhxqDate}" /></br>
                <label for="">同步代码</label>
                <form:input path="tbid"  />
                <label for="">计划生育药品标志</label>
                <form:input path="jhsyypFlag"  /></br>
                <label for="">抗结核药品标</label>
                <form:input path="kjhypFlag"  />
                <label for="">存放说明</label>
                <form:input path="cfsm"  /></br>
                <label for="">麻醉标志</label>
                <form:input path="mzbz"  />
                <label for="">省补报销比例</label>
                <form:input path="sbbxbl"  /></br>
                <label for="">说明书地址</label>
                <form:input path="icon"  />
                <label for="">省补基药标志</label>
                <form:input path="sbjybz"  /></br>
                <label for="">低价药标志</label>
                <form:input path="djybz"  />
                <label for="">其他药标志</label>
                <form:input path="qtybz"  /></br>
                <label for="">医保标志</label>
                <form:input path="ybbz"  />
                <label for="">精神药品级别</label>
                <form:input path="jsypjb"  /></br>
                <label for="">控制类药品标志</label>
                <form:input path="kzlypbz"  />
			</div>
		</div>
        <div class="bottomBtn" style="text-align: center;margin:30px 0;">
            <a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
        </div>
	</form:form>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/pic-upload/pic-upload.js"></script>
	<script type="text/javascript">
		$(function(){

            $("input").attr("disabled","disabled");
            $("select").attr("disabled","disabled");
		});

    </script>
</body>
</html>