<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<style>
		.addRoomBox{line-height: 40px;padding:10px 5px;margin-bottom: 20px;}
		.addRoomBox label{width:75px;display: inline-block;text-align: left;}
		.addRoomBox>input[type='text'],.addRoomBox>select{display:inline-block;width: 160px;height:30px;border:1px solid #ccc;margin:0 10px 0 5px;}
		.priceRange{display: inline-block;}
		.priceRange>input[type='text']{width: 60px;height: 30px;border:1px solid #ccc;}
		.priceRange>input[type='text']:first-child{margin-left: 5px;}
		.timeLIistBox{height: 180px;overflow: auto;padding:0 15px;border:1px solid #ccc;}
		.timeLIistBox .trackList{margin:0;}
		.timeLIistBox .trackList>li{ position: relative;padding: 9px 0 0 15px;line-height: 22px;border-left: 1px solid #ccc;color: #666;}
		.timeLIistBox .trackList>li.afterdate{padding-top: 0; }
		.timeLIistBox .trackList>li.afterdate>i.fa{top: -4px;padding-top: 10px;color:#62BC62;}
		.timeLIistBox .trackList>li.afterdate>.time{margin-left: 2px;}
		.timeLIistBox .trackList>li>i.fa{position: absolute;left: -6px;top: 15px;width: 11px;height: 11px;color: #ccc;background: #fff;}
		.timeLIistBox .trackList>li>.time{display: inline-block;width: 50px;margin-right: 20px;vertical-align: top;font-size: 12px;}
		.timeLIistBox .trackList>li>.txt{display: inline-block;max-width: 520px;color: #666;font-size: 12px;vertical-align: top;}
		.timeLIistBox .trackList>li>.date{width: 120px;color: #666;border-radius: 14px;font-size: 12px;text-align: left;display: inline-block;vertical-align: top;}
		.timeLIistBox .trackList>li>.date>.week{padding: 0 10px;}
		.logisticsBox{margin-top:20px;}
		.iconTitle{float: left;font-weight: 400;color: #666;font-size: 14px;padding-right: 10px;width: 100px;line-height:30px;}
		.titleIcon{font-size:15px;color:#000;padding:20px 0;}
		#describeInfo{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;margin:0;}
	</style>
	<title>不良品预登记详单</title>
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
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/pd/pdRejectsRecord/">不良品记录列表</a></li>
		<li class="active"><a href="${ctx}/pd/pdRejectsRecord/form?id=${pdRejectsRecord.id}">不良品记录<shiro:hasPermission name="pd:pdRejectsRecord:edit">${not empty pdRejectsRecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pd:pdRejectsRecord:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/> --%>
	<div class="right-main-box">
		<div class="btnBox">
			<h4 style="padding-left:0;">产品基本信息</h4>
		</div>
		<!-- <h3 class="titleIcon" style="padding:20px 0 0 20px;">产品基本信息</h3> -->
		<div class="searchProBox" style="width:100%;overflow: auto;height: 90px;">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>产品名称</th>
						<th>产品编号</th>
						<th>产品条码</th>
						<th>规格</th>
						<th>型号</th>
						<th>批号</th>
						<th>有效期</th>
						<th>单位</th>
						<th>生产厂家</th>
						<th>注册证号</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${pdRejectsRecord.pdProduct.name }</td>
						<td>${pdRejectsRecord.pdProduct.number }</td>
						<td>${pdRejectsRecord.productBarCode }</td>
						<td>${pdRejectsRecord.pdProduct.spec }</td>
						<td>${pdRejectsRecord.pdProduct.version }</td>
						<td>${pdRejectsRecord.batchNo }</td>
						<td>${pdRejectsRecord.validDate }</td>
						<td>${pdRejectsRecord.pdProduct.unit }</td>
						<td>${pdRejectsRecord.pdProduct.venderName }</td>
						<td>${pdRejectsRecord.pdProduct.registration }</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="logisticsBox">
			<h3 class="iconTitle">院内物流追溯</h3>
			<div class="timeLIistBox">
				<ul class="trackList">
					<c:forEach items="${pdStockLogList }" var="li">
						<li class="afterdate">
							<i class="fa fa-dot-circle-o"></i>
							<span class="date">${li.timeStr[0] }<span class="week">${li.timeStr[1] }</span></span>
							<span class="time">${li.timeStr[2] }</span>
							<span class="txt">
								<span class="txtType">${fns:getDictLabel(li.logType, 'product_flow_type', '') }</span>&nbsp;&nbsp;&nbsp;&nbsp;
								<span class="txtNum">数量：${li.productNum }</span>&nbsp;&nbsp;&nbsp;&nbsp;
								<span class="txtHomeLevel">${li.inFrom }</span>
								<c:if test="${not empty li.outTo }">
									→
									<span class="txtHomeNext">${li.outTo }</span>&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
								<c:if test="${not empty li.patientInfo }">
									<span class="txtHosCode">${li.patientInfo }</span>
								</c:if>
							</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<form id="recordForm" style=>
			<input name="id" type="hidden" value="${pdRejectsRecord.id }"/>
			<div class="importantBox" style="margin-top: 20px;">
				<h3 class="iconTitle">严重等级</h3>
				<select name="seriousGrade" id="seriousGrade" style="width:80px;height: 30px;margin:0">
					<option value="">请选择</option>
					<c:forEach items="${fns:getDictList('bad_prod_level') }" var="my">
						<option value="${my.value }" <c:if test="${my.value eq pdRejectsRecord.seriousGrade}">selected</c:if> >${my.label }</option>
					</c:forEach>
				</select>
			</div>
			<div class="otherText" style="margin-top: 20px;">
				<h3 class="iconTitle">描述</h3>
				<textarea name="describeInfo" id="describeInfo">${pdRejectsRecord.describeInfo }</textarea>
			</div>
			<div class="bottomBtn" style="text-align: center;margin:20px 0 10px 0;">
				<c:if test="${not empty pdRejectsRecord.oprtType and pdRejectsRecord.oprtType=='cleanyou'}">
					<input type="submit" value="保存" class="hcy-btn hcy-save" id="recordBtn" />
				</c:if>
	   			<a href="javascript:history.go(-1);" class="hcy-btn hcy-back">返回</a>
	   		</div>
   		</form>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//保存
			$('#recordBtn').click(function(){
				var dsp = $.trim($('#describeInfo').val());
				var ser = $('#seriousGrade option:selected').val();
				if(!dsp || !ser){
					layer.alert('请选择严重等级并描述',{icon:0});
					return false;
				}
				$('#recordForm').attr('action', '${ctx}/pd/pdRejectsRecord/save');
				$('#recordForm').attr('method', 'post');
				$('#recordForm').submit();
				return false;
			});
		});
	</script>
</body>
</html>