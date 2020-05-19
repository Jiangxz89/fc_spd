<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品赋码</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<style>
		.searchBox label{width: 85px;text-align: left;margin:0;}
		.addproductBox {display: none;}
		.codeListBox>h5{display:inline-block;font-size:15px;color:#666;padding:15px 20px 0 15px;font-weight:400;width:110px;text-align:right;}
		.codeListBox>span{display:inline-block;font-size:13px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		resetTip();
	</script>
</head>
<body>
	<div class="right-main-box">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/pd/pdProductCoding/productList">产品信息</a></li>
			<li ><a href="${ctx}/pd/pdProductCoding/list">已绑定产品</a></li>
		</ul>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="pdProductCoding" action="${ctx}/pd/pdProductCoding/productList" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>产品编号</label>
					<form:input path="number" />
				</div>
				<div>
					<label>产品名称</label>
					<form:input path="productName" />
				</div>
				<input id="btnSubmit" onclick="return page();" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
				<a href="###" class="hcy-btn hcy-search" id="binding">绑定</a>
			</form:form>
		</div>
		<sys:message content="${message}"/>
        <div id="messageBoxOther" class="alert alert-success hide">
            <button id="buttonClose" class="close">×</button><span id="messageBoxOtherText"></span>
        </div>
		<div class="showTableBox" style="width:100%;overflow:auto;">
			<div class="tableBox">
				<table id="contentTable" class="hcy-public-table" style="padding:0;width:2000px;">
					<thead>
						<tr>
							<th></th>
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
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.list }" var="pdProduct" varStatus="a">
						<tr>
							<td>
								<input type="radio" name="checkProduct" value="${pdProduct.id }"
									   productName = "${pdProduct.name }"
									   productSpec = "${pdProduct.spec }"
									   productVersion ="${pdProduct.version }"
									   productVender = "${pdProduct.venderNameShow}"/>
								<input type="hidden" value="${pdProduct.id }">
							</td>
							<td>${pdProduct.number }</td>
							<td>${pdProduct.name }</td>
							<td>${pdProduct.categoryNameShow}</td>
							<td>${pdProduct.groupNameShow}</td>
							<td>${pdProduct.unit }</td>
							<td>${pdProduct.spec }</td>
							<td>${pdProduct.version }</td>
							<td>${pdProduct.venderNameShow}</td>
							<td>${pdProduct.chargeCode }</td>
							<td>${pdProduct.registration }</td>
							<td>${pdProduct.sellingPrice }</td>
							<td>${pdProduct.description }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<div class="addproductBox">
			<h4 style="margin:10px;">条码信息</h4>
			<label style="margin:10px;"><span class="mustIcon">*</span>产品编号:</label>
			<input type="text" class="productNumber"  required="required" style="margin:10px;height:30px;border-radius:2px;width:220px;" />
			</br>
			<label style="margin:10px;"><span class="mustIcon">*</span>二级条码:</label>
			<input type="text" class="productBarcode" required="required" style="margin:10px;height:30px;border-radius:2px;width:220px;"  />
	</div>

	<!-- 打印 -->
	<div id="codePrintBox" class="codePrintBox" style="display:none;">
		<div class="codeListBox" style="margin-top:20px;"><h5 style="display:inline-block;">厂商：</h5><span id="venderNameSpan">美敦力（上海）管理有限公司</span></div>
		<div class="codeListBox"><h5 style="display:inline-block;">产品名称：</h5><span id="proNameSpan">冠状动脉球囊扩张导管</span></div>
		<div class="specLine codeListBox"><h5 style="display:inline-block;">规格：</h5><span id="specSpan">2.00mm*15mm</span></div>
		<div class="versionLine codeListBox" style="margin-bottom:40px;"><h5 style="display:inline-block;">型号：</h5><span id="versionSpan">LA20015</span></div>
		<div style="margin-left: 8%" id="div_id"></div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-barcode.js"></script>
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
    <script src="${ctxStatic}/spd/js/barcode.js?time=<%= new Date().getTime() %>"></script>
	<script type="text/javascript">
		$(function() {
			//重置
			$(".hcy-reset").click(function () {
				$(".searchBox div input[type='text']").val("");
				$(".searchBox div select").val("");
			})

            //关闭按钮点击事件
            $("#buttonClose").click(function(){
                $("#messageBoxOther").hide();
            })

			//绑定
			$("#binding").click(function(){
				var checkObj=$(".hcy-public-table>tbody>tr input[type='radio']:checked");
				if(checkObj.length==0){
					layer.alert("请先选择要绑定的产品！",{icon:1},function(index){
						layer.close(index);
					});
				}
				if(checkObj.length>0){
					layer.open({
						type:1,
						title:"绑定二级条码",
						content:$(".addproductBox").html(),
						area:["500px","400px"],
						shade: [0.8, '#393D49'],
						btn:["绑定","取消"],
						yes:function(index,layero){
							var currentTime=(new Date()).valueOf();
							currentTime = "99"+'${fns:getUser().companyNumber}'+currentTime;
                            var productNumber = layero.find(".productNumber").val();
                            var productBarcode = layero.find(".productBarcode").val();
                            var productId = checkObj.val();
							if(!productNumber){
                                layer.alert("请输入产品编号！")
                                return false;
                            }
							if(!productBarcode){
                                layer.alert("请输入二级条码！")
                                return false;
                            }
							if(!checknum(productNumber)){
								layer.alert("请输入正确的产品编号！")
								return false;
							}
							if(!checknum(productBarcode)){
								layer.alert("请输入正确的二级条码！")
								return false;
							}
							var venderName = checkObj.attr("productVender");
							var proName = checkObj.attr("productName");
							var spec = checkObj.attr("productSpec");
							var version = checkObj.attr("productVersion");
							//将选中、填写的赋值到弹框中
							$("#venderNameSpan").text(venderName);
							$("#proNameSpan").text(proName);
							$("#specSpan").text(spec);
							$("#versionSpan").text(version);
							$('#div_id').empty().barcode(currentTime, "code128",{//code128为条形码样式
								output:'css',
								color: '#000000',
								barWidth: 2,        //单条条码宽度
								barHeight: 80,     //单体条码高度
								addQuietZone: false,
							});

                            var batchNo = "";
                            var limitDate = "";
                            var secondCode = productBarcode;
                            if(productNumber.substring(0,2) != "93"){
                                if(scanCode(productNumber, productBarcode)){  //解析
                                    productNumber = upn;
                                    if(_secondCode != ""){ secondCode = _secondCode; }
                                    if(_Lot != ""){ batchNo = _Lot;}
                                    if(_ExpDate != ""){ limitDate = _ExpDate;}
                                }
                            }

							layer.open({
								type:1,
								title:"条码打印",
								content:$(".codePrintBox").html(),
								area:["500px","400px"],
								shade: [0.8, '#393D49'],
								btn:["保存并打印"],
								yes:function(index,layero){
                                    printFn();
                                    layer.closeAll();
                                    $.ajax({
                                        async:false,
                                        type:"POST",
                                        url:"${ctx }/pd/pdProductCoding/save",
                                        data:{
                                            "number":productNumber,
                                            "prodBarcode":productBarcode,
                                            "productId":productId,
                                            "rfidCode":currentTime,
                                            "batchNo":batchNo,
                                            "limitDate":limitDate
                                        },
                                        dataType:"json",
                                        success:function(data){

                                        }
                                    })
                                    $("#messageBoxOtherText").text("保存成功");
                                    $("#messageBoxOther").show();
									/*window.location = "${ctx }/pd/pdProductCoding/save?number="+productNumber+
											"&prodBarcode="+productBarcode+"&productId="+productId+"&rfidCode="+currentTime
									        +"&batchNo="+batchNo+"&limitDate="+limitDate;*/
                                    //printFn();

								}
							})
						},
						btn2:function(){
							layer.closeAll();
						}
					})

				}
			});

			//打印
			function printFn(){
				$(".codePrintBox").removeAttr("style");
				$(".codePrintBox").jqprint();
				$(".codePrintBox").attr("style","display:none;");
			}

			/**
			 * 校验
			 * @param value
			 * @returns {boolean}
			 */
			function checknum(value) {
				var Regx = /^[A-Za-z0-9]*$/;
				if (Regx.test(value)) {
					return true;
				}
				else {
					return false;
				}
			}
		})
	</script>
</body>
</html>