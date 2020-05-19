<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<style>
		.layui-layer-content{text-align:center;}
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
		<div class="btnBox">
			<h4>药品管理</h4>
			<%-- <a href="${ctx }/pd/medstoYpcdmlk/form?idm=${drug.idm }&flag='save'" class="hcy-btn hcy-btn-primary" id="">添加药品</a> --%>
			<a href="###" class="hcy-btn hcy-btn-primary" id="addSuppliers">批量添加供应商</a>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="medstoYpcdmlk" action="${ctx}/pd/medstoYpcdmlk/list" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>药品编号</label>
					<form:input path="ypdm" />
				</div>
				<div>
					<label>药品名称</label>
					<form:input path="ypmc" />
				</div>
				<div>
					<label>药品规格</label>
					<form:input path="ypgg" />
				</div>
				<div>
					<label>国家基药标志</label>
					<form:select path="basicdrugFlag">
						<form:option value="">全部</form:option>
						<form:option value="1">是</form:option>
						<form:option value="0">否</form:option>
					</form:select>
				</div>
				<div>
					<label>抗生素级别</label>
					<form:select path="kssjb">
						<form:option value="">非限制使用级</form:option>
						<form:option value="">否</form:option>
					</form:select>
				</div>
				<div>
					<label>处方药品</label>
					<form:select path="cfyp">
						<form:option value="">全部</form:option>
						<form:option value="1">是</form:option>
						<form:option value="0">否</form:option>
					</form:select>
				</div>
				<div>
					<label>厂家名称</label>
					<form:input path="cjmc" />
				</div>
				<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th><input type="checkbox" id="allchoose"/></th>
						<th>药品编号</th>
						<th>药品名称</th>
						<th>药品规格</th>
						<th>国家基药标志</th>
						<th>抗生素级别</th>
						<th>处方药品</th>
						<th>生产厂家</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="drug">
						<tr>
							<td><input type="checkbox" value="${drug.idm }"/></td>
							<td>${drug.idm }</td>
							<td>${drug.ypmc }</td>
							<td>${drug.ypgg }</td>
							<td>${fns:getDictLabel(drug.basicdrugFlag, 'yes_no','') }</td>
							<td>${drug.kssjb }</td>
							<td>${fns:getDictLabel(drug.cfyp,'yes_no','') }</td>
							<td>${drug.cjmc }</td>
							<td>
								<a class="hcy-btn hcy-btn-o" href="${ctx }/pd/medstoYpcdmlk/form?id=${drug.idm }&oprt=view">查看</a>
								<a class="hcy-btn hcy-btn-o" href="${ctx }/pd/medstoYpcdmlk/form?id=${drug.idm}">修改</a>
								<a class="hcy-btn hcy-btn-o" href="">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<div class="addChargeCodeBox">
		<h4 style="font-size: 14px;font-weight: 400;line-height: 60px;color: #666;"></h4>
		<select style="padding:0 0 0 5px;margin:0 auto;height:30px;width:220px;" class="addSupplierSelect">
			<option value="">请选择</option>
			<c:forEach items="${spd:findSupplierList()}" var="li">
				<option value="${li.id }">${li.supplierName }</option>
			</c:forEach>
		</select><span style="color:red;" class="nonSelect"></span>
	</div>
	<div class="importBox">
		<a href="###" style="margin-top: 80px;margin-right: 60px;" onclick="template()" class="hcy-btn hcy-btn-primary">下载模板</a>
		<div  style="margin-top: 80px;position:relative;" class="hcy-btn hcy-btn-primary">直接导入
			<form id="importForm" method="post" onsubmit="" style="margin:0;position:absolute;left:0;top:0;" enctype="multipart/form-data" action="${ctx }/pd/pdProduct/importData" />
				<input type="file" data-code='0' name="file" style="width:80px;height:32px;;margin:0;opacity:0;z-index:6666;"  class="hcy-btn hcy-btn-primary" value="直接导入"/>
				<input type="submit" id="submit" />
			</form>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//全选与反选
			$("#allchoose").click(function(){
				if($(this).attr('checked')){
					$("input[type='checkbox']").attr('checked','true');
				}else{
					$("input[type='checkbox']").removeAttr('checked');
				}
			});
			//添加供应商
			$("#addSuppliers").click(function(){
				var checkObj = $("#contentTable>tbody>tr input[type='checkbox']:checked");
				var checkText="";
				if (checkObj.length == 0){
					layer.alert("请先选择要添加供应商的药品！",{icon:0},function(index){
						layer.close(index);
					});
				}
				var ids = [];
				if (checkObj.length > 0){
					checkObj.each(function(){
						checkText = $(this).parent().next().next().text();
						$(".addChargeCodeBox>h4").text(checkText+"等"+checkObj.length+"个产品");
						ids.push($(this).val());
					});
					layer.open({
						type:1,
						title:"批量添加药品供应商",
						content:$(".addChargeCodeBox").html(),
						area:["400px","300px"],
						shade: [0.8, '#393D49'],
						btn:["确定","取消"],
						yes:function(index,layero){
							var val = layero.find(".addSupplierSelect option:selected");
							if (!val.val()) {
								$('.nonSelect').html('请选择供应商');
								return false;
							}
							window.location = "${ctx}/pd/medstoYpcdmlk/addSuppliers?ids="+ids.join(',')
									+'&supplierId='+val.val()+'&supplierName='+encodeURIComponent(encodeURIComponent(val.text()));
							layer.closeAll();
						},
						btn2:function(){
							layer.closeAll();
						}
					})
					
				}
			});
			$(document).on('change', ".addSupplierSelect", function(){
				$(this).next().html('');
			});
			//重置
			$(".hcy-reset").click(function(){
				$(".searchBox div input[type='text']").val("");
				$(".searchBox div select").val("");
			})
			//批量导入
			$("#import").click(function(){
				layer.open({
					type:1,
					title:"批量导入",
					content:$(".importBox").html(),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["关闭"],
					yes:function(){
						layer.closeAll();
					}
				})
			});
		})
		//下载模板
		function template(){
			var th = $("th");
			
			var arr = '';
			for(var i = 0 ; i < th.length ; i ++){
				if(i == 0){
					continue;
				}
				if(i == th.length - 1){
					continue;
				}
				if(i == th.length - 2){
					arr += th[i].innerText;
					continue;
				}else{
					arr += th[i].innerText+',';
				}
			}
			
			window.location = "${ctx}/pd/pdProduct/generateTemplate?head="+arr;
		}
		
		//全部导出
		function exportData(){
			var th = $("th");
			
			var arr = '';
			for(var i = 0 ; i < th.length ; i ++){
				if(i == 0){
					continue;
				}
				if(i == th.length - 1){
					continue;
				}
				if(i == th.length - 2){
					arr += th[i].innerText;
					continue;
				}else{
					arr += th[i].innerText+',';
				}
			}
			
			$("#dataForm").attr("action","${ctx}/pd/pdProduct/exportData?head="+arr);
			$("#dataForm").submit();
		}
		
		//导入
		function importData(){
			//$("#importForm").submit();
			$("#submit").click();
		}
	</script>
</body>
</html>