<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<title>采购管理</title>
	<style type="text/css">
		.none{display:none;}
	</style>
	<!-- html模板块 -->
    <script type="text/x-jquery-tmpl" id="temp_0">  
            <tr>
						<td><a title="{%= orderNo%}" href="${ctx}/pd/pdPurchaseDetail/form?orderNo={%= orderNo%}">{%= orderNo%}</a>
						</td>
						<td>{%= purchaseDate%}</td>
						<td>{%= deptName%}</td>
						{%if orderStatus==0%}
							<td>待审核</td>
						{%/if%}
						{%if orderStatus==1%}
							<td>已审核</td>
						{%/if%}
						{%if orderStatus==2%}
							<td>已拒绝</td>
						{%/if%}
						{%if orderStatus==3%}
							<td>已入库</td>
						{%/if%}
						<td>{%= purchaseName%}</td>
						<td>{%= remarks%}</td>
						<td><a href="${ctx}/pd/pdPurchaseDetail/form?orderNo={%= orderNo%}" class="hcy-btn hcy-btn-o">查看</a></td>
			</tr>
    </script>
    <script type="text/x-jquery-tmpl" id="temp_1">  
            <tr>
						<td>{%= deptName%}</td>
						<td>{%= brandName%}</td>
						<td>{%= prodUnit%}</td>
						<td>{%= prodName%}</td>
						<td>{%= prodSpec%}</td>
						<td>{%= prodVersion%}</td>
						<td>{%= purchaseNum%}</td>
			</tr>
    </script>
    <script type="text/x-jquery-tmpl" id="temp_2">  
            <tr>
						<td>{%= brandName%}</td>
						<td>{%= prodUnit%}</td>
						<td>{%= prodName%}</td>
						<td>{%= prodSpec%}</td>
						<td>{%= prodVersion%}</td>
						<td>{%= purchaseNum%}</td>
			</tr>
    </script>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pd/pdPurchaseOrder/">申购单列表</a></li>
		<shiro:hasPermission name="pd:pdPurchaseOrder:edit"><li><a href="${ctx}/pd/pdPurchaseOrder/form">申购单添加</a></li></shiro:hasPermission>
	</ul> --%>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>采购管理</h4>
			<a href="${ctx}/pd/pdPurchaseOrder/form" class="hcy-btn hcy-btn-primary">申请采购</a>
			<a href="###" class="hcy-btn hcy-btn-primary" onclick="viewGather(0)">查看明细</a>
			<%-- <c:if test="${fns:getUser().storeroomType eq 0}">
                <a href="###" class="hcy-btn hcy-btn-primary" onclick="viewGather(1)">按科室汇总</a>
            </c:if> --%>
			<a href="###" class="hcy-btn hcy-btn-primary" onclick="viewGather(2)">按品名汇总</a>
		</div>
		<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdPurchaseOrder" action="${ctx}/pd/pdPurchaseOrder/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="newSearchBox">
				<label for="">产品名称</label>
				<%--<input type="text" id="prodName"/>--%>
				<input class="select2_material form-control" style="width: 200px" id="prodName" name="prodName" >
				<label style="margin-left:5px;" for="">规格</label>
				<input type="text" autocomplete="off" id="prodSpec"/>
				<label for="">型号</label>
				<input type="text" autocomplete="off" id="prodVersion"/>
			<%-- <c:if test="${fns:getUser().storeroomType eq 0}">
				<div>
					<label for="">申购科室</label>
					<input type="text" id="deptName"/>
				</div>
			</c:if> --%>
				<label for="">申购日期</label>
				<input type="text" autocomplete="off" id="purchaseDate" placeholder="" style="text-align:center;width:160px;"/>
				<a href="###" class="hcy-btn hcy-search" onclick="queryResult()">查询</a>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
				</div>
			</div>
		</form:form>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable_0" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>申购单号</th>
						<th>申购日期</th>
						<th>申购科室</th>
						<th>状态</th>
						<th>申购人员</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<table id="contentTable_1" class="hcy-public-table none">
				<thead>
					<tr>
						<th>申购科室</th>
						<th>品牌</th>
						<th>单位</th>
						<th>产品名称</th>
						<th>规格</th>
						<th>型号</th>
						<th>申购数量</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<table id="contentTable_2" class="hcy-public-table none">
				<thead>
					<tr>
						<th>品牌</th>
						<th>单位</th>
						<th>产品名称</th>
						<th>规格</th>
						<th>型号</th>
						<th>申购数量</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<!-- 保存当前分类值 -->
	<input type="hidden" value="0" id="curClassValue"/>
	<%-- <script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script> --%>
	<%--<script src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>--%>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.tmpl.js"></script>
	<script type="text/javascript">
		$(function(){

            var select2 = function () {
                $("#prodName").select2({
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

			//初始化数据
			loadData(0,null,null);
			//日期范围
			laydate.render({
			  elem: '#purchaseDate',
			  range: true
			});
			//提示
			/* if('${message}'){
				layer.alert('${message}',{icon:1});
			} */
		});
		//查询
		function queryResult(){
		    $("#pageNo").val("");
            $("#pageSize").val("");
			var curTab = $('#curClassValue').val();
			loadData(curTab,null,null);
		}
		//分类查看
		function viewGather(curTab){
			//保存当前Tab值
			$('#curClassValue').val(curTab);
			//展示不同列表
			$('#contentTable_'+curTab).show().siblings('table').hide();
			//加载列表数据
			loadData(curTab,null,null);
		}
		//获取列表数据
		function loadData(curTab, pageNo, pageSize){
			//产品名称
			var prodName = $.trim($('#prodName').val());
			//规格
			var prodSpec = $.trim($('#prodSpec').val());
			//型号
			var prodVersion = $.trim($('#prodVersion').val());
			//申购科室
			var deptName = $.trim($('#deptName').val());
			//日期范围
			var purcDate = $('#purchaseDate').val();
			var reqUrl = '${ctx}/pd/pdPurchaseOrder/getData';
			var objData = {
					  "prodName":prodName,
					  "prodSpec":prodSpec,
					  "prodVersion":prodVersion,
					  "deptName":deptName,
					  "queryDate":purcDate,
					  "deptId":'${fns:getUser().storeroomId}',
					  "purchaseBy":'${fns:getUser().id}',
					  "pageNo":pageNo || 1,
					  "pageSize":pageSize || 10
			};
			if(curTab==1 || curTab==2){
				reqUrl = '${ctx}/pd/pdPurchaseDetail/gatherList';
				objData['curTab'] = curTab;
			}
			//当前
			$.ajax({
				url:reqUrl,
				type:'post',
				data:objData,
				dataType:'json',
				success:function(data){
					dealData(curTab,data);
				}
			});
		}
		//处理数据
		function dealData(curTab,data){
			clearData(curTab);
			$('.pagination').html(data.html);
			if(data.list && data.list.length>0)
				$('#temp_'+curTab).tmpl(data.list).appendTo('#contentTable_'+curTab+'>tbody'); 
		}
		//清空数据
		function clearData(curTab){
			$('#contentTable_'+curTab+'>tbody').empty();
		}
		//分页
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			var curTab = $('#curClassValue').val();
			loadData(curTab,n,s);
        	return false;
        }

        //重置
        $(".hcy-reset").click(function(){
            $(".newSearchBox input[type='text']").val("");
            $("#prodName").val("")
            $("#prodName").select2("data", "");
        })
	</script>
</body>
</html>