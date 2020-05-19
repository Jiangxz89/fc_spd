<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品基础目录</title>
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
			<h4>药品基础目录</h4>
			<%--<a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>--%>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="medstoYpml" action="${ctx}/pd/medstoYpml/findDrugBasicsCatalog" method="post" class="breadcrumb form-search">
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
						<form:option value="2">否</form:option>
					</form:select>
				</div>
				<div>
					<label>抗生素级别</label>
					<form:select path="kssjb">
						<form:option value="">全部</form:option>
						<form:option value="1">非限制使用级</form:option>
						<form:option value="2">否</form:option>
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
				<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
			<div class="tableBox">
				<table id="contentTable" class="hcy-public-table" style="padding:0;">
					<thead>
						<tr>
							<th>药品代码</th>
							<th>药品名称</th>
							<th>药品规格</th>
							<th>国家基药标志</th>
							<th>抗生素级别</th>
							<th>处方药品</th>
							<th>操作</th>
							<%--<th>规格idm</th>
							<th>临床idm</th>
							<th>药品类号</th>
							<th>剂型代码</th>
							<th>分类代码</th>
							<th>最小单位</th>
							<th>规格单位</th>
							<th>规格系数</th>
							<th>厂家代码</th>
							<th>厂家名称</th>
							<th>有效期</th>
							<th>药库单位</th>
							<th>药库系数</th>
							<th>进货单位</th>
							<th>进货系数</th>
							<th>门诊单位</th>
							<th>门诊系数</th>
							<th>住院单位</th>
							<th>住院系数</th>
							<th>零售价</th>
							<th>批发价</th>
							<th>默认进价</th>
							<th>上限价格</th>
							<th>自费标志</th>
							<th>自费比例</th>
							<th>住院自费标志</th>
							<th>住院自费比例</th>
							<th>贵重标志</th>
							<th>特殊标志</th>
							<th>累计领药标志</th>
							<th>皮试标志</th>
							<th>停用标志</th>
							<th>取整标志</th>
							<th>医保限制标志</th>
							<th>医保控制标志</th>
							<th>药品扣率</th>
							<th>招标标志</th>
							<th>录入日期</th>
							<th>账目类别</th>
							<th>供货单位代码</th>
							<th>供货单位名称</th>
							<th>药品来源</th>
							<th>批准文号</th>
							<th>gmp标志</th>
							<th>操作员</th>
							<th>操作日期</th>
							<th>招标单位</th>
							<th>默认招标价</th>
							<th>招标期号</th>
							<th>单复方标志</th>
							<th>对应代码</th>
							<th>说明</th>
							<th>备案标志</th>
							<th>otc标志</th>
							<th>报销标志</th>
							<th>药品说明书</th>
							<th>官方价格</th>
							<th>价格依据</th>
							<th>官方名称</th>
							<th>医保名称</th>
							<th>公费标志</th>
							<th>医保费用等级</th>
							<th>适用范围</th>
							<th>适应症标志</th>
							<th>换药标志</th>
							<th>医嘱管理标志</th>
							<th>省医保标志</th>
							<th>省公费标志</th>
							<th>零差结算药品</th>
							<th>零差结算单价</th>
							<th>特殊药品管理标志</th>
							<th>特殊药品采购方式</th>
							<th>公用标志</th>
							<th>批准文号效期</th>
							<th>同步代码</th>
							<th>计划生育药品标志</th>
							<th>抗结核药品标</th>
							<th>存放说明</th>
							<th>麻醉标志</th>
							<th>省补报销比例</th>
							<th>说明书地址</th>
							<th>省补基药标志</th>
							<th>低价药标志</th>
							<th>其他药标志</th>
							<th>医保标志</th>
							<th>精神药品级别</th>
							<th>控制类药品标志</th>--%>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="drug">
							<tr>
								<td>${drug.ypdm}</td>
								<td>${drug.ypmc}</td>
								<td>${drug.ypgg}</td>
								<c:choose>
									<c:when test="${drug.basicdrugFlag==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${drug.kssjb==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
									</c:choose>
								<c:choose>
									<c:when test="${drug.cfyp==1}">
										<td>是</td>
									</c:when>
									<c:otherwise>
										<td>否</td>
									</c:otherwise>
								</c:choose>
								<td>
									<a class="hcy-btn hcy-btn-o" href="${ctx }/pd/medstoYpml/form?xh=${drug.xh }&oprt=view">查看</a>
								</td>
								<%--<td>${drug.ggIdm}</td>
								<td>${drug.lcIdm}</td>
								<td>${drug.yplh}</td>
								<td>${drug.jxdm}</td>
								<td>${drug.fldm}</td>
								<td>${drug.zxdw}</td>
								<td>${drug.ggdw}</td>
								<td>${drug.ggxs}</td>
								<td>${drug.cjdm}</td>
								<td>${drug.cjmc}</td>
								<td>${drug.yxq}</td>
								<td>${drug.ykdw}</td>
								<td>${drug.ykxs}</td>
								<td>${drug.jhdw}</td>
								<td>${drug.jhxs}</td>
								<td>${drug.mzdw}</td>
								<td>${drug.mzxs}</td>
								<td>${drug.zydw}</td>
								<td>${drug.zyxs}</td>
								<td>${drug.ylsj}</td>
								<td>${drug.ypfj}</td>
								<td>${drug.mrjj}</td>
								<td>${drug.sxjg}</td>
								<td>${drug.zfbz}</td>
								<td>${drug.zfbl}</td>
								<td>${drug.zyzfbz}</td>
								<td>${drug.zyzfbl}</td>
								<td>${drug.gzbz}</td>
								<td>${drug.tsbz}</td>
								<td>${drug.ljlybz}</td>
								<td>${drug.psbz}</td>
								<td>${drug.tybz}</td>
								<td>${drug.qzbz}</td>
								<td>${drug.ybxzbz}</td>
								<td>${drug.ybkzbz}</td>
								<td>${drug.ypkl}</td>
								<td>${drug.zbbz}</td>
								<td>${drug.lrrq}</td>
								<td>${drug.zmlb}</td>
								<td>${drug.ghdwId}</td>
								<td>${drug.ghdwMc}</td>
								<td>${drug.yply}</td>
								<td>${drug.pzwh}</td>
								<td>${drug.gmpbz}</td>
								<td>${drug.czyh}</td>
								<td>${drug.czrq}</td>
								<td>${drug.zbdw}</td>
								<td>${drug.mrzbj}</td>
								<td>${drug.zbqh}</td>
								<td>${drug.dffbz}</td>
								<td>${drug.dydm}</td>
								<td>${drug.memo}</td>
								<td>${drug.babz}</td>
								<td>${drug.otcbz}</td>
								<td>${drug.bxbz}</td>
								<td>${drug.ypsms}</td>
								<td>${drug.govprice}</td>
								<td>${drug.jgyj}</td>
								<td>${drug.gfmc}</td>
								<td>${drug.ybmc}</td>
								<td>${drug.gfbz}</td>
								<td>${drug.ybfydj}</td>
								<td>${drug.syfw}</td>
								<td>${drug.syzbz}</td>
								<td>${drug.hybz}</td>
								<td>${drug.yzglbz}</td>
								<td>${drug.sybbz}</td>
								<td>${drug.sgfbz}</td>
								<td>${drug.islcjsyp}</td>
								<td>${drug.lcjsdj}</td>
								<td>${drug.tsypglbz}</td>
								<td>${drug.tsypcgfs}</td>
								<td>${drug.gybz}</td>
								<td>${drug.pzwhxq}</td>
								<td>${drug.tbid}</td>
								<td>${drug.jhsyypFlag}</td>
								<td>${drug.kjhypFlag}</td>
								<td>${drug.cfsm}</td>
								<td>${drug.mzbz}</td>
								<td>${drug.sbbxbl}</td>
								<td>${drug.icon}</td>
								<td>${drug.sbjybz}</td>
								<td>${drug.djybz}</td>
								<td>${drug.qtybz}</td>
								<td>${drug.ybbz}</td>
								<td>${drug.jsypjb}</td>
								<td>${drug.kzlypbz}</td>--%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		<div class="pagination">${page}</div>
	<div class="addChargeCodeBox">
		<h4 style="font-size: 14px;font-weight: 400;line-height: 60px;color: #666;"></h4>
		<select style="padding:0 0 0 5px;margin:0 auto;height:30px;width:220px;" class="addSupplierSelect">
			<option value="">请选择</option>
			<c:forEach items="${spd:findSupplierList()}" var="li">
				<option value="${li.id }">${li.supplierName }</option>
			</c:forEach>
		</select><span style="color:red;" class="nonSelect"></span>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script type="text/javascript">
		$(function() {
			$(document).on('change', ".addSupplierSelect", function () {
				$(this).next().html('');
			});
			//重置
			$(".hcy-reset").click(function () {
				$(".searchBox div input[type='text']").val("");
				$(".searchBox div select").val("");
			})

			//导出数据
			$('#exportBtn').one('click',function(){
				$(this).css("background-color","#B3BDC3");
				var form = $('<form>');
				form.attr('style', 'display:none');
				form.attr('method', 'post');
				//form.attr('action', '${ctx}/excelExport/medstoYpkcmxList');
				var input = $('<input>');
				input.attr('type', 'hidden');
				input.attr('name', 'exportData');
				input.attr('value', '${exportDataList}');
				form.append(input);
				$('body').append(form);
				form.submit();
				form.remove();
			});
		})
	</script>
</body>
</html>