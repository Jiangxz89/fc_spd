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
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<style>
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#allMoney,#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;}
		.otherText>h3{font-weight:400;display:inline-block;padding:3px 10px 0 5px;font-size:13px;color:#666;width:72px;}
	</style>
	<title>申请调拨</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
                    var outId = $("#outId").val();
                    if(outId==''){
                        layer.alert('请选择出库库房', {icon:0});
                        return false;
                    }
                    if($("table tbody").html()==''){
                        layer.alert('请添加产品	', {icon:0});
                        return false;
                    }
                    var flag = true;
                    $("#productTbody input[name$='allocationNum']").each(function(i,v){
                        var result = alertWarn('请输入大于0的整数', $(this));
                        if(result == false){
                            flag = false;
                        }
                    });

                    if(!flag){
                        return false;
                    }
                    //--red校验用量大于库存的数据
                    var rgb = true;
                    $('tr').each(function(){
                        var csv = $(this).css('background-color');
                        if(csv && (csv == 'red'||csv=="rgb(255, 0, 0)")){
                            rgb = false;
                            return false;
                        }
                    });

                    if (rgb==false) {
                        layer.alert('产品调拨数量不能大于库存', {icon: 0});
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
	</script>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>申请调拨</h4>
		</div>
		<%--<form:form id="inputForm" modelAttribute="pdAllocationRecord" action="${ctx}/pd/pdAllocationRecord/save" method="post" onsubmit="return checkData();" class="form-horizontal">--%>
		<form:form id="inputForm" modelAttribute="pdAllocationRecord" action="${ctx}/pd/pdAllocationRecord/save" method="post" class="form-horizontal">
		<div class="newSearchBox">
			<label for="">调拨单号</label>
			<input type="text" name="allocationCode" style="width:165px;" id="alloCode" value="${allocationCode }" readonly="readonly"/>
			<label for="">调拨日期</label>
			<input type="text" name="allocationDate" value="${allocationDate }" readonly="readonly"/>
			<label for="">操作人员</label>
			<input type="text" name="recordPeople" value="${user.name }" readonly="readonly"/>
			<label for="">入库库房</label>
			<input type="hidden" name="inId" value="${user.storeroomId }"/>
			<input type="text" name="inName" value="${user.storeroomName }" readonly="readonly"/>
			</br>
			<label for=""><span class="mustIcon">*</span>出库库房</label>
			<input type="hidden" name="outId" id="outId" value=""/>
			<input type="hidden" name="outName" id="outName" value=""/>
			<select name="" id="selectOutId" class="input-medium"  onchange="giveSelectValue()">
				<option value="">请选择</option>
				<c:forEach var="storeroom" items="${storeroomList }">
					<c:if test="${storeroom.id!=fns:getUser().storeroomId}">
						<option value="${storeroom.id }">${storeroom.storeroomName }</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>操作</th>
						<th>定数包名称</th>
						<th>定数包编号</th>
						<th>产品名称</th>
						<th>产品编号</th>
						<th>规格</th>
						<th>型号</th>
						<th>产品数量</th>
						<th>单位</th>
						<th>本库库存数量</th>
						<c:if test="${user.storeroomType eq '0'}">
							<th>出库库存数量</th>
						</c:if>
						<th>调拨数量</th>
					</tr>
				</thead>
				<tbody id="productTbody"></tbody>
			</table>
			<div class="totalText">
				<span id="allNum">产品总数量:<span id="allocationSum">0</span></span>
				<input type="hidden" id="allocationNumId" name="allocationNum" value="0"/>
			</div>
			<a href="###"class="hcy-btn hcy-btn-primary" id="addProBtn">添加产品</a>
			<%--<a href="###"class="hcy-btn hcy-btn-primary" id="addPackageBtn">添加定数包</a>--%>
		</div>
		<div class="otherText">
			<h3>备注</h3>
			<textarea name="remarks" class="remarkArea" placeholder="请输入备注" value="" maxlength="125"></textarea>
		</div>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
   			<input type="submit" id="forbiddenButton" value="保存" class="hcy-btn hcy-save" />
   			<a href="javascript:location=document.referrer;" class="hcy-btn hcy-back">返回</a>
   		</div>
   		</form:form>
	</div>
	<!-- 记录添加的行号 -->
	<input type="hidden" id="rowsRecord" value="0"/>
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
        var prods = [];//保存添加的产品编码
		$(function(){

			//添加产品
			$("#addProBtn").click(function(){
				var outId = $("#outId").val();
				if(outId==''){
					layer.alert('请选择出库库房', {icon:0});
					return false;
				}
	        	/*var tdArr = new Array();
				var tdArray = $(".productClass");
	        	  for(var i=0;i<tdArray.length;i++){
	        		  tdArr[i] = tdArray[i].getAttribute("data-id");
	        	}
	        	var ids = tdArr.join(',');*/
				layer.open({
					type:2,
					title:"添加产品",
					/*content:'${ctx}/pd/pdPackage/getAllProductByAllocation?storeroomId='+outId+'&ids='+ids,//添加产品页面路径  pdAddAlloactionBox.jsp*/
                    content:'${ctx}/pd/pdPackage/getAllProductByAllocation?storeroomId='+outId,//添加产品页面路径  pdAddAlloactionBox.jsp
					area:["965px","527px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var childWindow = layero.find('iframe')[0].contentWindow;
						var result = childWindow.compositeHtml($('#rowsRecord').val());
						if(result === 0){
							layer.alert('请选择产品',{icon: 0});
						}else{
							$('tbody').append(result);
							$('#rowsRecord').val($('tbody>tr').length);
							$('.allocationCodeClass').val($("#alloCode").val());
							layer.closeAll();
						} 
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			})
			//添加定数包
			$("#addPackageBtn").click(function(){
				var outId = $("#outId").val();
				if(outId==''){
					layer.alert('请选择出库库房', {icon:0});
					return false;
				}
				var tdArr = new Array();
				var tdArray = $(".packageClass");
	        	  for(var i=0;i<tdArray.length;i++){
	        		  tdArr[i] = tdArray[i].getAttribute("data-id");
	        	}
	        	var ids = tdArr.join(',');
				layer.open({
					type:2,
					title:"添加定数包",
					content:'${ctx}/pd/pdPackage/pdAllocationAddPackage?storeroomId='+outId+'&ids='+ids,//添加产品页面路径  pdAddAlloactionBox.jsp
					area:["965px","527px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var childWindow = layero.find('iframe')[0].contentWindow;
						var result = childWindow.addPackageHtml($('#rowsRecord').val());
						if(result == 0){
							layer.alert('请选择定数包',{icon: 0});
						}else{
							$('tbody').append(result);
							$('#rowsRecord').val($('tbody>tr').length);
							$('.allocationCodeClass').val($("#alloCode").val());
							layer.closeAll();
						}
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			})

            //动态计算产品总数量
            $(document).on('input propertychange', '.sumClass', function(){
                alertWarn('请输入大于0的整数', $(this));
                var isValidate = '${user.storeroomType}';
                if(isValidate=='0'){
                    computeDosage($(this));
				}
                computeTotalMoney();
                return false;
            });

            //计算调拨数量不得大于库存数量
            function computeDosage(inputObj){
                var count = $.trim(inputObj.val());//调拨数量
                var stock = $.trim(inputObj.parent().parent().children('td').eq(10).text());
                if(Number.parseInt(count) > Number.parseInt(stock)){
                    layer.alert('调拨数量已大于出库库存数量', {icon:0});
                    inputObj.parent().parent().css('background-color','red');
                }else{
                    inputObj.parent().parent().css('background-color','');
                }
            }

		})

        //动态计算总数量和总金额
        function computeTotalMoney(){
            //计算总数量
            allcount = 0;
            //计算总金额
            alltotal = 0.00;
            $("#productTbody input[name$='allocationNum']").each(function(i,v){
                var productNum = $(this).val();
                if (!isNumber(productNum)) {
                    productNum = 0;
                }
                allcount += parseInt(productNum);
            })
            $("#allocationSum").html(allcount);
            $("#allocationNumId").val(allcount);
        }
		
		//删除该产品
		function removeDiv(id){
			/* var applyNum = $('.remove_'+id+' .applyNumClass .sumClass').val();
			var appNum = parseInt(isNull(applyNum)?0:applyNum);
			var sumNum = $('#allocationNumId').val();
			$('#allocationNumId').val(parseInt(sumNum) - parseInt(appNum));
			$('#allocationSum').html(parseInt(sumNum) - parseInt(appNum)); */
			$('.remove_'+id).remove();
            var delIndex = prods.indexOf(id);
            if(delIndex > -1){
                prods.splice(delIndex,1);
            }
            computeTotalMoney();

			//allocationSum('');
		}
		//选择出库仓库
		function giveSelectValue(){
			var options=$("#selectOutId option:selected");
			$("#outId").val(options.val());
			$("#outName").val(options.text());
			$("table tbody").html('');
            prods.length=0;
            $("#allocationSum").html("");
            $("#allocationNumId").val("");

		}
		//计算调拨的数量
		function allocationSum(flag){//1是产品，2是定数包
			var sum = 0;
			var reg = /^[0-9]+$/;
			var inputArray=$("input[class='sumClass']"); 
			var isValidate = '${user.storeroomType}';
		    inputArray.each(function (){
		          var input =$(this);//循环中的每一个input元素  
		          var objectId = input.parent().parent()[0].className.split(' ')[0];
		          var productOrPackage = input.parent().parent()[0].className.split(' ')[1];
		          var appNum = parseInt(input.val()==''?0:input.val());
		          if(!reg.test(appNum)){
		        	  layer.alert('请输入1-9999之间的整数', {icon:0});
		        	input.val('');
		        	appNum = 0;
		        	$('.'+objectId).css('background-color', 'red');
		          }
		          if(isValidate=='0'){
		        	  var stockNum;
			          if(productOrPackage=='productClass'){
				          stockNum = parseInt(input.parent().prev().html());
			          }else{
			        	  var tdArray = $("."+objectId).find(".stockNumClass");
			        	  var prodArray = $("."+objectId).find(".productNumClass");
			        	  var tdArr = new Array();
			        	  for(var i=0;i<tdArray.length;i++){
			        		  tdArr[i] = parseInt(tdArray[i].innerHTML)/parseInt(prodArray[i].innerHTML);
			        	  }
			        	  stockNum = Math.min.apply(null,tdArr);
			          }
			          if(appNum>stockNum){
			        	layer.alert('调拨数量大于库存数量', {icon:0});
			        	input.val('');
			        	appNum = 0;
			        	$('.'+objectId).css('background-color', 'red');
			          }
		          }
		          if(productOrPackage=='packageClass'){
		        	  var temp = 0;
		        	  var proNumArray = $("."+objectId).find(".productNumClass");
		        	  for(var i=0;i<proNumArray.length;i++){
		        		  temp += parseInt(proNumArray[i].innerHTML)*appNum;
		        	  }
		        	  appNum = temp;
		          }
		          sum += appNum;
		     })
		     $("#allocationSum").html(sum);
		     $("#allocationNumId").val(sum);
		}
		//验证提交的表单
		function checkData(){
			if($("table tbody").html()==''){
				layer.alert('请添加产品或者定数包', {icon:0});
				return false;
			}
			var inputArray=$("input[class='sumClass']"); 
			var flag = true;
		    inputArray.each(function (){
	          var input =$(this);//循环中的每一个input元素  
	          var appNum = parseInt(input.val()==''?0:input.val());
	          if(appNum<=0){
	        	  layer.alert('请输入正确的调拨数量', {icon:0});
	        	  flag = false;
	        	  return false;
		       }
		    })
		    if(flag){
		    	document.getElementById("forbiddenButton").disabled=true;
				return true;
		    }else{
		    	return false;
		    }
		}
		//判空
		function isNull(a){
			if(a == "" || a == null || a == undefined){ 
		        return true;
		    }
			return false;
		}
		
	</script>
</body>
</html>