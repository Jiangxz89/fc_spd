<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库房巡查管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
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
	<script type="text/javascript">
		$(document).ready(function() {
			var tab = '${pdStoreroomPatrol.showFlag}';
			if(tab)
				$('.timeList>li').eq(tab).addClass('on').siblings().removeClass('on');
			if(tab=='4')
				$('#customInp').show();
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
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pd/pdStoreroomPatrol/">库房巡查列表</a></li>
		<shiro:hasPermission name="pd:pdStoreroomPatrol:edit"><li><a href="${ctx}/pd/pdStoreroomPatrol/form">库房巡查添加</a></li></shiro:hasPermission>
	</ul> --%>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>巡查管理</h4>
			<a href="${ctx }/pd/pdProductStock/addPatrolForm" class="hcy-btn hcy-btn-primary">开始巡查</a>
		</div>
		<form:form id="searchForm" modelAttribute="pdStoreroomPatrol" action="${ctx}/pd/pdStoreroomPatrol/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="reasonBox">
				<div class="line">
					<h3>巡查日期</h3>
					<ul class="timeList">
						<li class="on" data-value="0">全部</li>
						<li data-value="1">今天</li>
						<li data-value="2">昨天</li>
						<li data-value="3">本周</li>
						<li class="selfTime" data-value="4">自定义</li>
						<input type="text" name="queryDate" id="customInp" style="width:200px" value="${pdStoreroomPatrol.queryDate }"/>
					</ul>
					<input type="hidden" name="showFlag" id="showFlag" value="${pdStoreroomPatrol.showFlag}"/>
				</div>
				<div class="line">
					<label for="">库房</label>
					<form:select style="width:140px;height:30px;border-radius:2px;" path="storeroomId" id="storeroomId">
						<c:if test="${fns:getUser().storeroomType == '0' }">
							<form:option value="">全部</form:option>
							<c:forEach items="${spd:findStoreroomList()}" var="li">
								<form:option value="${li.id }">${li.storeroomName }</form:option>
							</c:forEach>
						</c:if>
						<c:if test="${fns:getUser().storeroomType == '1' }">
							<form:option value="${fns:getUser().storeroomId }">${fns:getUser().storeroomName}</form:option>
						</c:if>
					</form:select>
					<label for="">巡查员</label>
					<c:choose>
						<c:when test="${fns:getUser().storeroomType == '1' }">
							<form:select path="patrolMan">
								<c:set var="kufangId" value="${fns:getUser().storeroomId }"/>
								<form:option value="">全部</form:option>
								<form:options items="${spd:findRoomAdmins(kufangId) }" itemValue="adminId" itemLabel="adminName"/>
							</form:select>
						</c:when>
						<c:when test="${pdStoreroomPatrol.storeroomId != null && pdStoreroomPatrol.storeroomId != '' && pdStoreroomPatrol.storeroomId !=fns:getUser().storeroomId}">
							<form:select path="patrolMan">
								<c:set var="kufangId" value="${pdStoreroomPatrol.storeroomId}"/>
								<form:option value="">全部</form:option>
								<form:options items="${spd:findRoomAdmins(kufangId) }" itemValue="adminId" itemLabel="adminName"/>
							</form:select>
						</c:when>
						<c:otherwise>
							<form:select path="patrolMan">
								<form:option value="">全部</form:option>
								<form:options items="${spd:findRoomAdminAll() }" itemValue="adminId" itemLabel="adminName"/>
							</form:select>
						</c:otherwise>
					</c:choose>
					<%-- <form:input type="text" path="patrolMan"  style="width:140px;height:30px;border-radius:2px;"/> --%>
					<a href="###" class="hcy-btn hcy-search" style="line-height: 1.5;" onclick="query();">查询</a>
				</div>
			</div>
		</form:form>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>巡查单号</th>
						<th>巡查时间</th>
						<th>库房名称</th>
						<th>巡查产品量</th>
						<th>合格产品量</th>
						<th>不合格产品量</th>
						<th>温度</th>
						<th>湿度</th>
						<th>巡查员</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="li">
						<tr>
							<td>${li.patrolCode }</td>
							<td><fmt:formatDate value="${li.patrolDate }" pattern="yyyy-MM-dd"/></td>
							<td>${spd:getStoreroomName(li.storeroomId)} </td>
							<td>${li.patrolCount }</td>
							<td>${li.passCount }</td>
							<td>${li.failCount }</td>
							<td>${li.temperature }℃</td>
							<td>${li.wetness }%</td>
							<td>${li.patrolManName }</td>
							<td>
								<a href="${ctx }/pd/pdStoreroomPatrolRecord/list?patrolCode=${li.patrolCode}" class="hcy-btn hcy-btn-o">查看</a>
								<a href="${ctx }/pd/pdStoreroomPatrolRecord/form?patrolCode=${li.patrolCode}&oprt=view" class="hcy-btn hcy-btn-o">修改</a>
								<a href="javascript:;" onclick="killIt('${ctx }/pd/pdStoreroomPatrol/delete?id=${li.id}')" class="hcy-btn hcy-btn-o">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.11.1.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//日期范围
			laydate.render({
			  elem: '#customInp',
			  range: true
			});
			//tab
			$('.timeList>li').click(function(){
				$(this).addClass('on').siblings().removeClass('on');
				var sf = $(this).attr('data-value');
				$('#showFlag').val(sf);
				if(sf=='4'){
					$('#customInp').show();
				}else{
					$('#customInp').hide();
					query();
				}
			});
		});
		//查询
		function query(){
			$('#searchForm').submit();
			return false;
		}
		function killIt(url){
			layer.confirm('确认删除?', {icon: 3, title:'提示'}, function(index){
				  //do something
				  location.href = url;
				  layer.close(index);
			}); 
		}
		
		//库房改变事件
		$("#storeroomId").change(function(){
			var storeroomId =  $("#storeroomId").find("option:selected").val();
 			$.ajax({
			    url:"${ctx}/pd/pdStoreroomPatrol/findpdStoreroomPatrolByStoreroomId", //请求的url地址
			    dataType: "json", //返回格式为json
			    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
			    data: {
			        "storeroomId": storeroomId
			    }, //参数值
			    type: "POST", //请求方式
			    success: function(data) {
			        //请求成功时处理
			        var html = "<option value=''>全部</option>";
			        for(var i = 0;i<data.length;i++){
			        	html+= "<option value = '"+data[i].adminId+"'>"+data[i].adminName+"</option>"
			        }
			        $("#patrolMan").empty().append(html);
			    }
			})
		})
	</script>
</body>
</html>