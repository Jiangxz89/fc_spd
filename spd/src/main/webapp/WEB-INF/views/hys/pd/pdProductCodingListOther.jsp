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
			<li ><a href="${ctx}/pd/pdProductCoding/productList">产品信息</a></li>
			<li class="active"><a href="${ctx}/pd/pdProductCoding/list">已绑定产品</a></li>
		</ul>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="pdProductCoding" action="${ctx}/pd/pdProductCoding" method="post" class="breadcrumb form-search">
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
				<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
				<a href="###" class="hcy-btn hcy-search" id="binding">取消绑定</a>
			</form:form>
		</div>
		<sys:message content="${message}"/>
			<div class="tableBox">
				<table id="contentTable" class="hcy-public-table" style="padding:0;">
					<thead>
						<tr>
                            <th><input type="checkbox" id="chkAll"/></th>
							<th>产品名称</th>
							<th>产品编号</th>
							<th>二级条码</th>
							<th>REID码</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
					<c:forEach items="${page.list }" var="pdProduct" varStatus="a">
						<tr>
                            <td>
                                <input type="checkbox" name="checkProduct" value="${pdProduct.id }"/>
                                <input type="hidden" value="${pdProduct.id }">
                            </td>
							<td>${pdProduct.productName }</td>
							<td>${pdProduct.number }</td>
							<td>${pdProduct.prodBarcode }</td>
							<td>${pdProduct.rfidCode }</td>
							<td><a productName = "${pdProduct.productName }"
								   productSpec = "${pdProduct.spec }"
								   productVersion ="${pdProduct.version }"
								   productVender = "${pdProduct.venderNameShow}"
								   rfidCode = "${pdProduct.rfidCode}"
								   href="###" style="color: #000079;text-decoration:underline" id="codePrintBtn">条码打印</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		<div class="pagination">${page}</div>
	</div>

	<!-- 打印 -->
	<div id="codePrintBox" class="codePrintBox" style="display:none;">
		<div class="codeListBox" style="margin-top:20px;"><h5 style="display:inline-block;">厂商：</h5><span id="venderNameSpan">美敦力（上海）管理有限公司</span></div>
		<div class="codeListBox"><h5 style="display:inline-block;">产品名称：</h5><span id="proNameSpan">冠状动脉球囊扩张导管</span></div>
		<div class="specLine codeListBox"><h5 style="display:inline-block;">规格：</h5><span id="specSpan">2.00mm*15mm</span></div>
		<div class="versionLine codeListBox" style="margin-bottom:40px;"><h5 style="display:inline-block;">型号：</h5><span id="versionSpan">LA20015</span></div>
		<div style="margin-left: 15%" id="div_id"></div>
	</div>
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/jquery/jquery-barcode.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript">
		$(function() {
			//重置
			$(".hcy-reset").click(function () {
				$(".searchBox div input[type='text']").val("");
				$(".searchBox div select").val("");
			})

            //全选
            $("#chkAll").click(function(){
                if($(this).attr("checked") == 'checked'){
                    $("input[name='checkProduct']").attr("checked",$(this).attr("checked"));
                }else{
                    $("input[name='checkProduct']").removeAttr("checked");
                }

            })
            //取消绑定
            //绑定
            $("#binding").click(function(){
                var checkObj=$("#tbody>tr input[type='checkbox']:checked");
                if(checkObj.length==0){
                    layer.alert("请选择要取消绑定的产品！",{icon:1},function(index){
                        layer.close(index);
                    });
                }
                if(checkObj.length>0){
                    var ids="";
                    $("#tbody>tr input[type='checkbox']:checked").each(function(){
                        ids += ','+$(this).next().val();
                    });
                    layer.confirm(
                        "确定要取消这些绑定的产品？",//这里写询问层里面的文本
                        {
                            icon:3,
                            title:"提示",
                            btn:["确定","取消"]
                        },
                        function(index,layero){
                            layer.close(index);
                            window.location="${ctx}/pd/pdProductCoding/deleteAll?ids="+ids;
                        },
                        function(index){
                            layer.close(index);
                        }
                    )

                }
            })

			/**
			 * 条码打印
			 */
			$(document).on("click","#codePrintBtn",function(){
				var venderName = $(this).attr("productVender");
				var proName = $(this).attr("productName");
				var spec = $(this).attr("productSpec");
				var version = $(this).attr("productVersion");
				var rfidCode = $(this).attr("rfidCode");
				//将选中、填写的赋值到弹框中
				$("#venderNameSpan").text(venderName);
				$("#proNameSpan").text(proName);
				$("#specSpan").text(spec);
				$("#versionSpan").text(version);

				$('#div_id').empty().barcode(rfidCode, "code128",{//code128为条形码样式
					output:'css',
					color: '#000000',
					barWidth: 2,        //单条条码宽度
					barHeight: 80,     //单体条码高度
					addQuietZone: false,
				});

				layer.open({
					type:1,
					title:"条码打印",
					content:$(".codePrintBox").html(),
					area:["500px","400px"],
					shade: [0.8, '#393D49'],
					btn:["打印"],
					yes:function(index,layero){
						printFn();
						layer.closeAll();
					}
				})
			})

			//打印
			function printFn(){
				$(".codePrintBox").removeAttr("style");
				$(".codePrintBox").jqprint();
				$(".codePrintBox").attr("style","display:none;");
			}

		})
	</script>
</body>
</html>