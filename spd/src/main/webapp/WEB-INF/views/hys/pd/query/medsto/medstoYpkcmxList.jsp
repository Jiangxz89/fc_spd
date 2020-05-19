<%--
  Created by IntelliJ IDEA.
  User: ZYL
  Date: 2019-4-19
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>药品库存明细查询</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
    <link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
    <style>
        .searchBox label{width: 85px;text-align: left;margin:0;}
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
    </script>
</head>
<body>
<div class="right-main-box">
    <div class="btnBox">
        <h4>库存明细查询</h4>
        <a class="hcy-btn hcy-btn-primary" href="###" id="exportBtn">导出Excel</a>
    </div>
    <div class="searchBox">
        <form:form id="searchForm" modelAttribute="medstoYpkcmx" action="${ctx}/pd/medstoYpkcmx/list" method="post" class="breadcrumb form-search">
            <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
            <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
            <div>
                <label>药品编号</label>
                <form:input path="drugId" htmlEscape="false"  maxlength="64" class="input-medium"/>
            </div>
            <div>
                <label>药品名称</label>
                <form:input path="drugName" htmlEscape="false" maxlength="64" class="input-medium"/>
            </div>
            <div>
                <label>药品规格</label>
                <form:input path="drugSpec" htmlEscape="false" maxlength="64" class="input-medium"/>
            </div>
            <div>
                <label>供应商名称</label>
                <form:input path="producerName" htmlEscape="false" maxlength="64" class="input-medium"/>
            </div>
            </br>
            <div>
                <label>国家基药标志</label>
                <form:select path="isjbyw">
                    <form:option value="">全部</form:option>
                    <form:option value="1">是</form:option>
                    <form:option value="2">否</form:option>
                </form:select>
            </div>
            <div>
                <label>是否抗生素药品</label>
                <form:select path="kss">
                    <form:option value="">全部</form:option>
                    <form:option value="1">是</form:option>
                    <form:option value="2">否</form:option>
                </form:select>
            </div>
<%--            <div>--%>
<%--                <label>药库名称 </label>--%>
<%--                <form:select path="recordState" class="input-medium" >--%>
<%--                    <form:option value="" label="全部"/>--%>
<%--                    <form:options items="${fns:getDictList('stock_record_state') }" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
<%--                </form:select>--%>
<%--            </div>--%>
            <input id="btnSubmit" onclick="querydata()"  style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search"  type="submit" value="查询"/>
            <input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
        </form:form>
    </div>
    <sys:message content="${message}"/>
    <div class="showTableBox" style="width:100%;overflow:auto;">
        <div class="tableBox">
            <table id="contentTable" class="hcy-public-table" style="padding:0;width: 2400px;">
                <thead>
                    <tr>
                        <th>机构编码</th>
                        <th>药库编码</th>
                        <th>药库名称 </th>
                        <th>药品编码 </th>
                        <th>医保统一编码 </th>
                        <th>是否基药</th>
                        <th>是否抗生素药品 </th>
                        <th>中药使用类别代码 </th>
                        <th>药物类别 </th>
                        <th>药物剂型代码 </th>
                        <th>药品名称 </th>
                        <th>药品规格 </th>
                        <th>大包装单位 </th>
                        <th>大包装数量 </th>
                        <th>小包装单位 </th>
                        <th>小包装数量 </th>
                        <th>包装单位数量关系 </th>
                        <th>供应商名称 </th>
                        <th>进价单价 </th>
                        <th>零售价 </th>
                        <th>进价金额合计 </th>
                        <th>零售金额合计 </th>
                        <th>生产批号 </th>
                        <th>药品生产企业 </th>
                        <th>药品产地 </th>
                        <th>批准文号 </th>
                        <th>生产日期 </th>
                        <th>失效日期 </th>
                        <th>上传日期 </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${page.list}" var="medstoYpkcmx">
                        <tr>
                            <td>
                                    ${medstoYpkcmx.jgbm}
                            </td>
                            <td>
                                    ${medstoYpkcmx.storeId}
                            </td>
                            <td>
                                    ${medstoYpkcmx.storeName}
                            </td>
                            <td>
                                    ${medstoYpkcmx.drugId}
                            </td>
                            <td>
                                    ${medstoYpkcmx.ybtybm}
                            </td>
                            <c:choose>
                                <c:when test="${medstoYpkcmx.isjbyw==1}">
                                    <td>是</td>
                                </c:when>
                                <c:otherwise>
                                    <td>否</td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${medstoYpkcmx.kss==1}">
                                    <td>是</td>
                                </c:when>
                                <c:otherwise>
                                    <td>否</td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                    ${medstoYpkcmx.cmedCode}
                            </td>
                            <td>
                                    ${medstoYpkcmx.drugDetailType}
                            </td>
                            <td>
                                    ${medstoYpkcmx.doseCode}
                            </td>
                            <td>
                                    ${medstoYpkcmx.drugName}
                            </td>
                            <td>
                                    ${medstoYpkcmx.drugSpec}
                            </td>
                            <td>
                                    ${medstoYpkcmx.packageUnit}
                            </td>
                            <td>
                                    ${medstoYpkcmx.packageQty}
                            </td>
                            <td>
                                    ${medstoYpkcmx.salesUnit}
                            </td>
                            <td>
                                    ${medstoYpkcmx.salesQty}
                            </td>
                            <td>
                                    ${medstoYpkcmx.salesRelation}
                            </td>
                            <td>
                                    ${medstoYpkcmx.producerName}
                            </td>
                            <td>
                                    ${medstoYpkcmx.costPrice}
                            </td>
                            <td>
                                    ${medstoYpkcmx.salePrice}
                            </td>
                            <td>
                                    ${medstoYpkcmx.costAmt}
                            </td>
                            <td>
                                    ${medstoYpkcmx.saleAmt}
                            </td>
                            <td>
                                    ${medstoYpkcmx.batchNo}
                            </td>
                            <td>
                                    ${medstoYpkcmx.sdrugmanufacturers}
                            </td>
                            <td>
                                    ${medstoYpkcmx.sdrughabitat}
                            </td>
                            <td>
                                    ${medstoYpkcmx.sratificationno}
                            </td>
                            <td>
                                <fmt:formatDate value="${medstoYpkcmx.ddateproduce}" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${medstoYpkcmx.expiry}" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${medstoYpkcmx.scrq}" pattern="yyyy-MM-dd hh:MM:ss"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="pagination">${page}</div>
</div>
<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
<script src="${ctxStatic}/spd/js/layer.js"></script>
<script type="text/javascript">
    $(function(){
        //申购时间
        laydate.render({
            elem: '#inHomeTime',
            range: true
        });

        //重置
        $(".hcy-reset").click(function(){
            var sel ="${fns:getUser().storeroomId}";//默认下拉
            var inp ="${fns:getUser().storeroomName}";//默认input
            $(".searchBox div input[type='text']").val("");
            $("#inName").val(inp);
            $(".searchBox div select").val("");
        });

        //导出数据
        $('#exportBtn').one('click',function(){
            $(this).css("background-color","#B3BDC3");
            var form = $('<form>');
            form.attr('style', 'display:none');
            form.attr('method', 'post');
            form.attr('action', '${ctx}/excelExport/medstoYpkcmxList');
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
    //查询加遮罩
    function querydata(){
        var index = loading('正在查询，请稍等...');
        return true;
    };
</script>
</body>
</html>