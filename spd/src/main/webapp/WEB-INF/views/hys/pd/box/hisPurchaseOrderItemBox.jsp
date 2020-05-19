<%--
  Created by IntelliJ IDEA.
  User: jxz
  Date: 2019-5-17
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
    <title>药品采购单明细</title>
</head>

<body>
<div class="addProBox right-main-box" style="width: 2000px;overflow:auto;">
    <div class="btnBox">
        <h4>药品采购单明细</h4>
    </div>
    <form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="hisPurchaseOrderItem" action="${ctx}/pd/hisPurchaseOrderItem/list" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <form:input path="PId" cssStyle="display: none;" />
        <div class="searchBox">
            <label for="">药品名称</label>
            <form:input path="drugName" />
            <label for="">药品编号</label>
            <form:input path="drugId" />
            <label for="">药品规格</label>
            <form:input path="drugSpec" />
            <input type="submit" id="" value="查询" class="hcy-btn hcy-btn-primary" />
            <input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
        </div>
    </form:form>
    <div style="width: 100%;overflow:auto;">
        <table class="hcy-public-table" id="addContentTab" style="padding-top:0;width: 2000px;">
            <thead>
            <tr>
                <th>药品编码</th>
                <th>药品名称</th>
                <th>医保统一编码</th>
                <th>是否基药</th>
                <th>是否抗生素药品</th>
                <th>中药使用类别代码</th>
                <th>药物类型</th>
                <th>药物剂型代码</th>
                <th>药品规格</th>
                <th>大包装数量</th>
                <th>大包装单位</th>
                <th>小包装数量</th>
                <th>小包装单位</th>
                <th>采购数量</th>
                <th>药品生产企业</th>
                <th>药品产地</th>
                <th>供货商名称</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${page.list}" var="item">
                <tr>
                    <td>${item.drugId }</td>
                    <td>${item.drugName }</td>
                    <td>${item.ybtybm }</td>
                    <td>${item.isjbyw }</td>
                    <td>${item.kss }</td>
                    <td>${item.cmedCode }</td>
                    <td>${item.drugType }</td>
                    <td>${item.doseCode }</td>
                    <td>${item.drugSpec }</td>
                    <td>${item.packageQty }</td>
                    <td>${item.packageUnit }</td>
                    <td>${item.salesQty }</td>
                    <td>${item.salesUnit }</td>
                    <td>${item.putQty }</td>
                    <td>${item.drugManft }</td>
                    <td>${item.drugHabitat }</td>
                    <td>${item.producerName }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pagination">${page}</div>
    <div class="bottomBtn" style="text-align: center;margin:30px 0;">
        <a href="javascript:location=document.referrer;" class="hcy-btn hcy-back" style="line-height: normal;">返回</a>
    </div>
</div>
<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
<script src="${ctxStatic}/spd/js/layer.js"></script>
<script type="text/javascript">
    $(function(){
        //重置
        $(".hcy-reset").click(function(){
            $(".searchBox input[type='text']").val("");
        });

        //日期控件
        // laydate.render({
        //     elem: '#customInp',
        //     range: true,
        //     done: function(value, date, endDate){
        //         var dateArr = value.split(" - ");
        //         $("#beginDate").val(dateArr[0]);
        //         $("#endDate").val(dateArr[1]);
        //     }
        // });
    })

    function page(n,s){
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
    }

    function getStock(id){
        var id = id;
        window.parent.getStockAjax(id);
    }

</script>
</body>
</html>
