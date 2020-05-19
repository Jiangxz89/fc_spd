<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
	<style>
			.refuseBox,.submitBox{display: none;}
			.refuseBox>h4{font-size: 13px;padding:40px 0 20px 0;}
			.refuseInp{width:260px;height:30px;border: 1px solid #ccc;}
			.submitBox>h4{font-size:13px;color:#666;padding-top:40px;}
			.none{display:none;}
			.layui-layer-content{text-align:center;}
		</style>
	<title>采购审核</title>
	<%--<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>--%>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/js/jquery.tmpl.js"></script>
	<!-- html模板块 -->
    <script type="text/x-jquery-tmpl" id="temp_0">  
            <tr>
						<td><input type="checkbox" data-orderNo="{%= orderNo%}"/></td>
						<td><a title="{%= orderNo%}" href="${ctx}/pd/pdPurchaseDetail/form?orderNo={%= orderNo%}">{%= orderNo%}</a>
						<td>{%= orderDate%}</td>
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
						<td>{%= purchaseName%}</td>
						<td>{%= remarks%}</td>
						<td>
							<a href="${ctx}/pd/pdPurchaseOrder/auditForm?orderNo={%= orderNo%}&oprt=view" class="hcy-btn hcy-btn-o">查看</a>
							{%if orderStatus!='2'%}
								<a href="${ctx}/pd/pdPurchaseOrder/auditForm?orderNo={%= orderNo%}&oprt=audit" class="hcy-btn hcy-btn-o">审核</a>
							{%/if%}
						</td>
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
	<div class="right-main-box">
		<div class="btnBox">
			<h4>采购审核</h4>
            <a href="###" class="hcy-btn hcy-btn-primary" id="allAuditSubmit">批量审核</a>
			<a href="###" class="hcy-btn hcy-btn-primary" id="allSubmit">合并并提交</a>
			<a href="###" class="hcy-btn hcy-btn-primary" id="allRefuse">批量拒绝</a>
			<a href="###" class="hcy-btn hcy-btn-primary" onclick="viewGather(0)">查看明细</a>
			<a href="###" class="hcy-btn hcy-btn-primary" onclick="viewGather(1)">按科室汇总</a>
			<a href="###" class="hcy-btn hcy-btn-primary" onclick="viewGather(2)">按品名汇总</a>
		</div>
		<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdPurchaseOrder" action="${ctx}/pd/pdPurchaseOrder/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="newSearchBox">
				<label for="">产品名称</label>
				<%--<input type="text" id="prodName"/>--%>
				<input class="select2_material form-control" style="width: 200px" id="prodName" name="prodName" >
				<label  style="margin-left:15px;" for="">规格</label>
				<input autocomplete="off"  type="text" id="prodSpec"/>
				<label for="">型号</label>
				<input autocomplete="off"  type="text" id="prodVersion"/>
				<label for="">申购科室</label>
				<select id="deptId" style="width: 150px">
					<option value="">全部</option>
					<c:forEach items="${spd:findStoreroomList() }" var="li">
						<option value="${li.id }">${li.storeroomName }</option>
					</c:forEach>
				</select>
				</br>
				<label for="">申购日期</label>
				<input  type="text" id="purchaseDate" autocomplete="off" placeholder="" style="text-align:center;width: 200px"/>
				<a href="###" class="hcy-btn hcy-search" onclick="queryResult()">查询</a>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
				</div>
			</div>
		</form:form>
		<%-- <sys:message content="${message}"/> --%>
		<div class="tableBox">
			<table id="contentTable_0" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th><input type="checkbox" id="allCheck" /></th>
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
	<div class="refuseBox">
		<h4>确认拒绝这<span id="refuseNum">5</span>个申请？</h4>
		<input type="text" class="refuseInp" placeholder="请输入拒绝理由" />
	</div>
	<div class="submitBox">
		<h4>确认合并这<span id="submitNum">5</span>个申请并提交订单？</h4>
	</div>
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
			if('${message}'){
				layer.alert('${message}',{icon:1});
			}
			//全选
			$("#allCheck").click(function(){
				if($(this).prop("checked")){
					$(".hcy-public-table>tbody>tr").find("input[type='checkbox']").prop("checked",true);
				}else{
					$(".hcy-public-table>tbody>tr").find("input[type='checkbox']").prop("checked",false);
				}
			});
			function batchDeal(orderNos,orderStatus,refuseReason,oprtSource){
				$.post('${ctx}/pd/pdPurchaseOrder/audit',{"orderNos":orderNos,"orderStatus":orderStatus,"refuseReason":refuseReason,"oprtSource":oprtSource},function(data){
					if("200" == data.code){
						layer.alert("操作成功",{icon:1},function(index){
							layer.closeAll();
							location.href = '${ctx}'+data.uri;
						});
					}else{
						layer.alert("操作失败",{icon:2},function(index){
							layer.close(index);
						});
					}
				});
			}
			//批量拒绝
			$("#allRefuse").click(function(){
				var rowsObj = $(".hcy-public-table>tbody>tr input[type='checkbox']:checked");
				var len = rowsObj.length;
				if(len==0){
					layer.alert("请先选择申购单",{icon:0},function(index){
						layer.close(index);
					});
				}
				if(len>0){
					$("#refuseNum").text(len);
					var orderNos = [];
					$.each(rowsObj,function(i,v){
						orderNos.push($(this).data('orderno'));
					})
					layer.open({
						type:1,
						title:"提示",
						content:$(".refuseBox"),
						area:["400px","300px"],
						shade: [0.8, '#393D49'],
						btn:["确定","取消"],
						yes:function(index,layero){
							var refrea = $.trim($('.refuseInp').val());
							if(!refrea){
								layer.alert('请输入拒绝理由',{icon:0});
								return false;
							}else{
								//批量拒绝
								batchDeal(orderNos.join(','),'2',refrea,null);
								layer.closeAll();
							}
						},
						btn2:function(){
							layer.closeAll();
						}
					})
				}
			});
			//合并提交
			$("#allSubmit").click(function(){
				var rowsObj = $(".hcy-public-table>tbody>tr input[type='checkbox']:checked");
				var len = rowsObj.length;
				if(len==0){
					layer.alert("请先选择申购单",{icon:0},function(index){
						layer.close(index);
					});
				}
				if(len>0){
					$("#submitNum").text(len);
					var orderNos = [];
					$.each(rowsObj,function(i,v){
						orderNos.push($(this).data('orderno'));
					})
					layer.open({
							type:1,
							title:"提示",
							content:$(".submitBox"),
							area:["300px","200px"],
							shade: [0.8, '#393D49'],
							btn:["确定","取消"],
							yes:function(index,layero){
								//批量通过
								loading('正在提交，请稍等...');
								batchDeal(orderNos.join(','),'1',null,"0");
								//layer.closeAll();
							},
							btn2:function(){
								layer.closeAll();
							}
					})
				}
			})
            //合并审核
            $("#allAuditSubmit").click(function(){
                var rowsObj = $(".hcy-public-table>tbody>tr input[type='checkbox']:checked");
                var len = rowsObj.length;
                if(len==0){
                    layer.alert("请先选择申购单",{icon:0},function(index){
                        layer.close(index);
                    });
                }
                if(len>0){
                    $("#submitNum").text(len);
                    var orderNos = [];
                    $.each(rowsObj,function(i,v){
                        orderNos.push($(this).data('orderno'));
                    })
                    layer.open({
                        type:1,
                        title:"提示",
                        content:$(".submitBox"),
                        area:["300px","200px"],
                        shade: [0.8, '#393D49'],
                        btn:["确定","取消"],
                        yes:function(index,layero){
                            //批量通过
                            loading('正在提交，请稍等...');
                            batchDeal(orderNos.join(','),'1',null,'1');//最后一个一代表批量保存
                            //layer.closeAll();
                        },
                        btn2:function(){
                            layer.closeAll();
                        }
                    })
                }
            })

		})
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
			var deptId = $.trim($('#deptId option:selected').val());
			//日期范围
			var purcDate = $('#purchaseDate').val();
			var reqUrl = '${ctx}/pd/pdPurchaseOrder/getData';
			var objData = {
					  "prodName":prodName,
					  "prodSpec":prodSpec,
					  "prodVersion":prodVersion,
					  "deptId":deptId,
					  "queryDate":purcDate,
					  "pageNo":pageNo || 1,
					  "pageSize":pageSize || 10,
					  "isPurchaseAudit":"0"
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