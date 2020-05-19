<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>库存记录管理</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
    <style type="text/css">
        .numberWARAP{width:100%;height:60px;line-height:60px;margin:20px 0;}
        .numberWARAP>div{float:left;width:33%;height:60px;line-height:60px;color:#666;font-size:16px;text-align:center;border-right:1px solid #ccc;}
        .numberWARAP>div:nth-child(3){border:none;}
        .changeColor .red td,.changeColor .red td a{color: red}
        .allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
        .totalText{text-align: right;height: 50px;line-height: 50px;}
    </style>
</head>
<body>
<div class="right-main-box">
    <div class="btnBox">
        <h4>出入库明细</h4>
    </div>
    <%--<div class="numberWARAP">--%>
        <%--<div class="total">总数量：<span id="totalNum">${pdCount}</span></div>--%>
        <%--<div class="nearTime">近效期数量：<span id="nearNum">${jCount}</span></div>--%>
        <%--<div class="overTime">过期数量：<span id="overNum">${gCount}</span></div>--%>
    <%--</div>--%>
    <div class="newSearchBox">
        <form:form id="searchForm"  modelAttribute="pdStockRecordProduct" action="${ctx}/pd/pdProductStock/stockInAndOutRecordDetailQuery" method="post" class="breadcrumb form-search">
            <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
            <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
            <form:hidden path="storeroomId"/>
            <form:hidden path="productId"/>
            <label>库房</label>
            <form:input path="" htmlEscape="false"  maxlength="64" value="${fns:getUser().storeroomName}" disabled="true"/>
            <label for="" >生产厂家</label>
            <form:select path="venderName" class="input-medium" style="width: 260px;">
                <form:option value="" label="全部(qb)"/>
                <form:options items="${spd:findPdVenderList() }" itemLabel="nameAndpinyin" itemValue="id" htmlEscape="false"/>
            </form:select>
            <label>产品批号</label>
            <form:input path="batchNo" autocomplete="off" htmlEscape="false" maxlength="64" />
            <label>有效期</label>
            <input name="queryDate" style="width:160px;" autocomplete="off" type="text" readonly="readonly" maxlength="20" id="validDate" class="input-medium Wdate" value="${pdStockRecordProduct.queryDate}"/>
            <input type="hidden" id="startDate" name="startDate"><input type="hidden" id="endDate" name="endDate">
            <input id="btnSubmit" onclick="return page();"  class="hcy-btn hcy-search" style="height: inherit;line-height:1.5;" type="button" value="查询"/><br>
            <%--<a href="###" id="btnExport" class="hcy-btn hcy-btn-primary" style="line-height:1.5;margin:5px 6px 0 0;">导出Excel</a>--%>
        </form:form>
    </div>
    <sys:message content="${message}"/>
    <div class="showTableBox" style="width:100%;overflow:auto;">
        <div class="tableBox" >
        <table id="contentTable" class="hcy-public-table" style="padding:0;width:1850px;">
            <thead>
            <tr>
                <th>产品名称</th>
                <th>产品编号</th>
                <th>规格</th>
                <th>型号</th>
                <th>批号</th>
                <th>有效期</th>
                <th>单位</th>
                <th>出入库类型</th>
                <th>数量</th>
                <th>入库单价</th>
                <th>入库金额</th>
                <th>出库单价</th>
                <th>出库金额</th>
                <th>入库科室</th>
                <th>出库科室</th>
                <th>出入库时间</th>
                <th>生产厂家</th>
                <th>注册证号</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.list}" var="pdStockRecordProduct">
                <tr>
                <td>${pdStockRecordProduct.productName}</td>
                <td>${pdStockRecordProduct.number}</td>
                <td>${pdStockRecordProduct.spec }</td>
                <td>${pdStockRecordProduct.version}</td>
                <td>${pdStockRecordProduct.batchNo}</td>
                <td><fmt:formatDate value="${pdStockRecordProduct.limitDate}"/></td>
                <td>${pdStockRecordProduct.unit}</td>
                <td>${pdStockRecordProduct.recodeType}</td>
                <td>${pdStockRecordProduct.productNum}</td>
                <td>${pdStockRecordProduct.inPrice}</td>
                <td>${pdStockRecordProduct.pdTotalPrice}</td>
                <td>${pdStockRecordProduct.outPrice}</td>
                <td>${pdStockRecordProduct.pdOutTotalPrice}</td>
                <td>${pdStockRecordProduct.inName}</td>
                <td>${pdStockRecordProduct.outName}</td>
                <td><fmt:formatDate value="${pdStockRecordProduct.checkTime}"/></td>
                <td>${pdStockRecordProduct.venderName}</td>
                <td>${pdStockRecordProduct.registration}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
    <div class="pagination">${page}</div>
    <div class="totalText">
        <span class="allNum">入库总数量:<span class="total_number">${productTotNum}</span></span>
        <span class="allNum">入库总金额:<span class="total_price">${sumPdTotalPrice}</span></span>
        <span class="allNum">出库总数量:<span class="total_number">${productOutTotNum}</span></span>
        <span class="allNum">出库总金额:<span class="total_price">${sumPdOutTotalPrice}</span></span>
    </div>
    <div class="bottomBtn" style="text-align: center;margin:30px 0;">
        <a href="${ctx}/pd/pdProductStockTotal/queryPdStock" class="hcy-btn hcy-back" >返回</a>
    </div>
</div>
<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
<script src="${ctxStatic}/spd/js/layer.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        /* var isSel=$("#isSel").val();
        if(isSel!="0"){
            //debugger;
            autoPrompt();
        } */
        $("#btnExport").click(function(){
            <%--$("#searchForm").attr("action","${ctx}/pd/pdProductStock/export");--%>
            $("#searchForm").submit();
            $("#searchForm").attr("action","${ctx}/pd/pdProductStock/stockInAndOutRecordDetailQuery");
        });

        //日期范围
        laydate.render({
            elem: '#validDate',
            range: true
        });
        //提示
        if('${message}'){
            layer.alert('${message}',{icon:1});
        }
    });
    function page(n,s){
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
    }
</script>
<script type="text/javascript">
    $("#btnSubmit").click(function(){

        $("#searchForm").submit();
    });

</script>
</body>
</html>