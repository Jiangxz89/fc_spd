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
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<style>
		.timeLIistBox{height: 180px;overflow: auto;padding:0 15px;border:1px solid #ccc;}
		.timeLIistBox .trackList{margin:0;}
		.timeLIistBox .trackList>li{ position: relative;padding: 9px 0 0 15px;line-height: 22px;border-left: 1px solid #ccc;color: #666;}
		.timeLIistBox .trackList>li.afterdate{padding-top: 0; }
		.timeLIistBox .trackList>li.afterdate>i.fa{top: -4px;padding-top: 10px;color:#62BC62;}
		.timeLIistBox .trackList>li.afterdate>.time{margin-left: 2px;}
		.timeLIistBox .trackList>li>i.fa{position: absolute;left: -6px;top: 15px;width: 11px;height: 11px;color: #ccc;background: #fff;}
		.timeLIistBox .trackList>li>.time{display: inline-block;width: 50px;margin-right: 20px;vertical-align: top;font-size: 12px;}
		.timeLIistBox .trackList>li>.txt{display: inline-block;max-width: 520px;color: #666;font-size: 12px;vertical-align: top;}
		.timeLIistBox .trackList>li>.date{width: 70px;color: #666;border-radius: 14px;font-size: 12px;text-align: left;display: inline-block;vertical-align: top;}
		.timeLIistBox .trackList>li>.week{padding: 0 10px;}
		.logisticsBox{margin-top:30px;}
		.logisticsBox>h3,.littleTip{float: left;font-weight: 400;color: #666;font-size: 14px;padding-right: 10px;width: 100px;line-height:30px;}
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;margin:0;}
		.otherText>h3{font-weight:400;display:inline-block;font-size:14px;color:#666;width:100px;font-size:14px;line-height:30px;vertical-align:top;}
	</style>
	<title>院内物流追溯</title>
	<script type="text/x-jquery-tmpl" id="temp_1">  
		<tr>
			<td><a href="###" class="hcy-btn hcy-btn-o" onclick="chooseOneProductFlow('{%= productBarCode%}','{%= productId%}','{%= batchNo%}','{%= showValidDate%}');">选择</a></td>
			<td>{%= name%}</td>
			<td>{%= number%}</td>
			<td>{%= productBarCode%}</td>
			<td>{%= spec%}</td>
			<td>{%= version%}</td>
			<td>{%= batchNo%}</td>
			<td>{%= showValidDate%}</td>
			<td>{%= unit%}</td>
			<td>{%= venderName%}</td>
			<td>{%= registration%}</td>
		</tr>
    </script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#recordForm").validate({
				submitHandler: function(form){

					if (!($.trim($('tbody').html()))) {
						layer.alert('请查询产品',{icon:0});
						return false;
					}
					var pbc = $('#productBarCode').val();
					var pdi = $('#productId').val();
					var bcp = $('#batchNo').val();
					if(!pbc || !pdi || !pbc){
						layer.alert('请选择产品',{icon:0});
						return false;
					}
					var dsp = $.trim($('#describeInfo').val());
					var ser = $('#seriousGrade option:selected').val();
					if(!dsp || !ser){
						layer.alert('请选择严重等级并描述',{icon:0});
						return false;
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
			<h4 style="padding-left:0;">院内物流追溯</h4>
		</div>
		<form id="searchForm">
			<div class="newSearchBox">
				<label>产品编号</label>
				<input type="text" autocomplete="off" name="productNo" htmlEscape="false" maxlength="64" />
				<label>产品名称</label>
				<%--<input type="text" autocomplete="off" name="name" htmlEscape="false" maxlength="64" />--%>
				<input class="select2_material form-control" style="width: 260px" id="productNameSelect" name="name" >
				<label style="margin-left:5px;">产品型号</label>
				<input type="text" autocomplete="off" name="version" htmlEscape="false" maxlength="64"/>
				<label for="">产品规格</label>
				<input type="text" autocomplete="off" name="spec"/>
				</br>
				<label for="">住院号</label>
				<input type="text" autocomplete="off" name="inHospitalNo"/>
				<a href="###" class="hcy-btn hcy-search" style="line-height: 1.5;" id="queryMyProd">查询</a>
				<%--<a href="###" class="hcy-btn hcy-btn-primary" style="line-height: 1.5; display: none">外网查询</a>--%>
				<a href="###" class="hcy-btn hcy-reset" style="line-height: 1.5;" id="clearCondition">重置</a>
			</div>
		</form>
		<sys:message content="${message}"/>
		<div class="searchProBox tableBox" style="width:100%;overflow: auto;">
			<table id="contentTable" class="hcy-public-table">
				<thead>
					<tr>
						<th>操作</th>
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
				</tbody>
			</table>
		</div>
		<%-- <div class="pagination">${page}</div> --%>
		<div class="logisticsBox">
			<h3>院内物流追溯</h3>
			<div class="timeLIistBox">
				<ul class="trackList">
				</ul>
			</div>
		</div>
		<form id="recordForm" action="${ctx}/pd/pdRejectsRecord/save" method="post">
			<div class="importantBox" style="margin-top: 20px;">
				<h3 class="littleTip">严重等级</h3>
				<select name="seriousGrade" id="seriousGrade" style="width:100px;height: 30px;margin:0">
					<option value="">请选择</option>
					<c:forEach items="${fns:getDictList('bad_prod_level') }" var="li">
						<option value="${li.value }">${li.label }</option>
					</c:forEach>
				</select>
			</div>
			<div class="otherText" style="margin-top: 20px;">
				<h3>描述</h3>
				<textarea name="describeInfo" id="describeInfo" class="remarkArea"></textarea>
			</div>
			<input id="productId" name="productId" type="hidden"/>
			<input id="batchNo" name="batchNo" type="hidden"/>
			<input id="productBarCode" name="productBarCode" type="hidden"/>
			<input id="validDate" name="validDate" type="hidden"/>
   		</form>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
   			<input type="submit" value="保存" class="hcy-btn hcy-save" id="recordBtn" />
   			<!-- <a href="javascript:history.go(-1);" class="hcy-btn hcy-back">返回</a> -->
   		</div>
	</div>
	<%--<script src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>--%>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.tmpl.js"></script>
	<script type="text/javascript">
		$(function(){

            var select2 = function () {
                $("#productNameSelect").select2({
                    dropdownParent: $('.modal-content'),
                    /*placeholder: "产品名称",*/
                    allowClear: true,
                    ajax: {
                        url: '${ctx}/pd/pdProduct/findList',
                        dataType: 'json',
                        data: function (params) {
                            var query = {
                                name: params //params.term 搜索参数值
                            }
                            return query;
                        },
                        results: function (data) {
                            //返回最终数据data 给dataArray
                            var dataArray = [];
                            for (var i = 0; i < data.length; i++) {
                                var dataObj = {};
                                dataObj.id = data[i].name;
                                dataObj.text = data[i].name ;
                                dataArray.push(dataObj);
                            }
                            return {
                                results: dataArray
                            };
                        },
                        error: function (error) {
                        }
                    }
                });
            }
            select2();

			//登记日期
			laydate.render({
				elem: '#registerTime',
				range: true
			});
			//清空
			$('#clearCondition').click(function(){
				$('#searchForm input').val('');
                $("#productNameSelect").val("")
                $("#productNameSelect").select2("data", "");
			});
			//查询
			$("#queryMyProd").click(function(){
				getProdListData();
			});
			//保存
			$('#recordBtn').click(function(){
				$('#recordForm').submit();
			});
		});
		
		//根据条件获取耗材列表
		function getProdListData(){
			var flag = false;
			$("input[name='productNo'],input[name='name'],input[name='version'],input[name='spec'],input[name='inHospitalNo']").each(function(){
				if($.trim($(this).val())){
					flag = true;
					return false;
				}
			});
			if(flag){
				$.ajax({
					url:'${ctx}/pd/pdProductStock/queryOriginalProduct',
					type:'post',
					data:$('#searchForm').serialize(),
					dataType:'json',
					success:function(data){
						$('tbody').empty();
						$('.trackList').empty();
						$('#temp_1').tmpl(data).appendTo('tbody'); 
					}
				});
			}
		}
		
		//该产品的物流信息
		function chooseOneProductFlow(barcode,prodId,batchNo,validDate){
			$('#productBarCode').val(barcode);
			$('#productId').val(prodId);
			$('#batchNo').val(batchNo);
			$('#validDate').val(validDate);
			if(barcode){
				$.ajax({
					url:'${ctx}/pd/pdStockLog/getProdFlowInfo',
					type:'post',
					data:{productBarCode:barcode, productId:prodId, batchNo:batchNo},
					dataType:'json',
					success:function(data){
						$('.trackList').empty();
						showOneProdFlow(data);
					}
				});
			}
		}
		
		//获取每个实物产品的院内物流
		function showOneProdFlow(data){
			var len = data.length,
				html = '';
			
			for(var i = 0; i < len; i++){
				html += '<li>'
					 +		'<i class="fa fa-dot-circle-o"></i>'
					 +		'<span class="date">'+data[i].timeStr[0]+'</span>'
					 +		'<span class="week">'+data[i].timeStr[1]+'</span>'
					 +		'<span class="time">'+data[i].timeStr[2]+'</span>'
					 +		'<span class="txt">'
					 +			'<span class="txtType">'+data[i].flowType+'</span>&nbsp;&nbsp;&nbsp;&nbsp;'
					 +			'<span class="txtNum">数量：'+data[i].productNum+'</span>&nbsp;&nbsp;&nbsp;&nbsp;'
					 +			'<span class="txtVenderName">'+data[i].inFrom+'</span>'
					 +          '<span class="txtHomeLevel">';
					 if(data[i].outTo){
					 	html +=	'→'+data[i].outTo;
						 
					 }
					 html +=   '</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="txtHosCode">';
					 if(data[i].patientInfo){
					 	html +=	data[i].patientInfo;
						 
					 }
					 html +=	'</span>'
					 +     '</span>'
				     +  '</li>';
			}
			$('.trackList').append(html);
		}
	</script>
</body>
</html>