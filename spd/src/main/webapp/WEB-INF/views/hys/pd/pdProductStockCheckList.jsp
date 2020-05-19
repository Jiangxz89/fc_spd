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
	<style>
		.reasonBox{padding-left:17px;}
		.reasonBox>.line>h3{display: inline-block;font-weight: 400;width:60px;margin-right: 20px;font-size:13px;color:#666;}
		.reasonBox>.line>ul{display: inline-block;padding-bottom: 15px;margin:0;}
		.reasonBox>.line>ul>li{cursor: pointer; display: inline-block;font-size:13px;color:#666;padding:5px 10px;margin-left:10px;line-height:normal;}
		.reasonBox>.line>ul>li.on{background: #03b8cc;color:#fff;}
		.reasonBox>.line>label{display: inline-block;width:60px;height:30px;line-height: 30px;font-size:13px;color:#666;}
		.reasonBox>.line>select{width:100px;height:30px;margin-right:15px;}
		.inventoryTd>input[type='text']{width:80px;height:30px;padding:0 0 0 5px;border:1px solid #ccc;}
		#customInp{width:100px;padding:0 0 0 5px;height:30px;border:1px solid #ccc;display: none;}
	</style>
	<title>盘点管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
				<h4>盘点管理</h4>
				<a href="${ctx }/pd/pdProductStockCheck/form" class="hcy-btn hcy-btn-primary">开始盘点</a>
			</div>
			<form:form id="searchForm" modelAttribute="pdProductStockCheck" method="post" class="breadcrumb form-search">
				<div class="reasonBox">
					<div class="line">
						<h3>盘点日期</h3>
						<input type="hidden" id="dateFlag" value="${dateFlag }" />
						<ul class="timeList">
							<li class="on" onclick="chooseDate('')" >全部</li>
							<li name="today" onclick="chooseDate('today')">今天</li>
							<li name= "yesterday" onclick="chooseDate('yesterday')">昨天</li>
							<li name="thisWeek" onclick="chooseDate('thisWeek')">本周</li>
							<li class="selfTime">自定义</li>
							<input type="text" id="customInp" style="width:160px;" value="${custom }" />
							<input type="hidden" id="beginDate" name="beginDate" value=""/>
							<input type="hidden" id="endDate" name="endDate" value=""/>
						</ul>
					</div>
					<div class="line">
						<label for="">库房</label>
						<select name="repoId" id="" onchange="repoChange()">
							<option value="">全部</option>
						<c:forEach var="storeroom" items="${storeroomList}">  
	                        <option <c:if test="${pdProductStockCheck.repoId eq storeroom.id }" >selected</c:if> value="${storeroom.id}" >${storeroom.storeroomName}</option>  
	                    </c:forEach>
						</select>
						<label for="">盘点人</label>
						<select name="operPerson" id="operPerson">
							<option value="">全部</option>
							<c:if test="${!empty pdProductStockCheck.operPerson}">
								<option selected value="${pdProductStockCheck.operPerson }">${pdProductStockCheck.operPerson }</option>
							</c:if>
						</select>
						<input type="hidden" id="operPersonFlag" value="${pdProductStockCheck.operPerson }" />
						<input type="submit" onclick="sub()" class="hcy-btn hcy-btn-primary" />
					</div>
				</div>
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			</form:form>
			<div class="tableBox">
				<table class="hcy-public-table">
					<thead>
						<tr>
							<th>编号</th>
							<th>盘点时间</th>
							<th>库房名称</th>
							<th>盘点产品量</th>
							<th>已盘产品量</th>
							<th>盘点人</th>
							<th>盘点状态</th>
							<th>盘盈盘亏</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="a">
							<tr>
								<td>${a.number }</td>
								<td>${a.checkDate }</td>
								<td>${a.repoName }</td>
								<td>${a.shouldCount }</td>
								<td>${a.alreadyCount }</td>
								<td>${spd:getUserNameById(a.operPerson)}</td>
								<td>${a.status == '0'?'临时保存':'盘点完成' }</td>
								<td>${a.profitLossCount }</td>
								<td><a href="${ctx }/pd/pdProductStockCheck/detail?id=${a.id}&status=${a.status}" class="hcy-btn hcy-btn-o">查看</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagination">${page}</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
		<script type="text/javascript">
			$(function(){
				repoChange();
				//时间回显
				var dateFlag = $("#dateFlag").val();
				if(dateFlag == 'today'){
					$("[name=today]").addClass("on").siblings().removeClass("on");
					if($(".selfTime").hasClass("on")){
						$("[name=today]").next().css("display","inline-block");
					}else{
						$(".selfTime").next().css("display","none");
					}
				}else if(dateFlag == 'yesterday'){
					$("[name=yesterday]").addClass("on").siblings().removeClass("on");
					if($(".selfTime").hasClass("on")){
						$("[name=yesterday]").next().css("display","inline-block");
					}else{
						$(".selfTime").next().css("display","none");
					}
				}else if(dateFlag == 'thisWeek'){
					$("[name=thisWeek]").addClass("on").siblings().removeClass("on");
					if($(".selfTime").hasClass("on")){
						$("[name=thisWeek]").next().css("display","inline-block");
					}else{
						$(".selfTime").next().css("display","none");
					}
				}else if(dateFlag == ''){
					$("[name=all]").addClass("on").siblings().removeClass("on");
					if($(".selfTime").hasClass("on")){
						$("[name=all]").next().css("display","inline-block");
					}else{
						$(".selfTime").next().css("display","none");
					}
				}
				//选择时间
				$(".timeList>li").click(function(){
					$(this).addClass("on").siblings().removeClass("on");
					if($(".selfTime").hasClass("on")){
						$(this).next().css("display","inline-block");
					}else{
						$(".selfTime").next().css("display","none");
					}
				});
				if ($("#customInp").val() != null && $("#customInp").val() != ''){
					$(".selfTime").addClass("on").siblings().removeClass("on");
						$(".selfTime").next().css("display","inline-block");
					//	$(".selfTime").next().css("display","none");
				}
				
				//日期控件
				laydate.render({
					elem: '#customInp',
					range: true,
					done: function(value, date, endDate){
						var dateArr = value.split(" - ");
						$("#beginDate").val(dateArr[0]);
						$("#endDate").val(dateArr[1]);
						chooseDate2(value);
					}
				});
				
			})
			function chooseDate(data){
				var date = data;
				window.location = "${ctx}/pd/pdProductStockCheck/list?date="+date;
			}
			function chooseDate2(value){
				var date = value;
				window.location = "${ctx}/pd/pdProductStockCheck/list?date="+date;
			}
			
			//submit
			function sub(){
				var date = $("#customInp").val();
				$("#searchForm").attr("action","${ctx}/pd/pdProductStockCheck/list?date="+date);
				return true;
			}
			
			//仓库盘点人级联
			function repoChange(){
				$.ajax({
					url:"${ctx}/pd/pdProductStockCheck/getUserList",
					type:"POST",
					data:{storeroomId:$("[name=repoId]").val()},
					dataType:"json",
					success:function(data){
						var data = eval(data);
						$("#operPerson>option:not(:first)").remove();
						var person = $("#operPersonFlag").val();
						var html = '';
						for(var i = 0 ; i < data.length ; i ++){
							if(data[i].adminName != person){
								html += '<option value="'+data[i].adminId+'" >'+data[i].adminName+'</option>';
							}else{
								html += '<option selected="selected" value="'+data[i].adminId+'" >'+data[i].adminName+'</option>';
							}
							
						}
						$("[name=operPerson]").append(html);
					}
				})
			}
		</script>
	</body>
</html>