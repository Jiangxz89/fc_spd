<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>供应商信息管理</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
    <link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
    <link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
    <style>
        .factoryTab .red td,.factoryTab .red td a{color: red}
    </style>
    <script type="text/javascript">
        $(document).ready(function() {

        });
        function page(n,s){
            // $("#pageNo").val(n);
            // $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        function query(){
            $("#pageNo").val(1);
            $("#searchForm").attr("action","${ctx}/pd/pdSupplier/");
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<div class="right-main-box">
    <div class="btnBox">
        <%-- <a href="${ctx}/pd/pdSupplier/" class="hcy-btn hcy-btn-primary">供应商信息列表</a> --%>
        <h4>HIS项目收费代码列表</h4>
    </div>
    <div class="newSearchBox">
        <form:form id="searchForm" style="padding:0;background:none;margin:0;" modelAttribute="hisChargeCode" action="${ctx}/pd/pdProduct/hisChargeCodeList" method="post" class="breadcrumb form-search">
            <%--<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>--%>
            <%--<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>--%>
            <label style="width:100px;">收费项目代码：</label>
            <form:input path="SFCODE" htmlEscape="false" maxlength="100" class="input-medium"/>
            <label style="width:100px;">收费项目名称：</label>
            <form:input path="SFNAME" htmlEscape="false" maxlength="100" class="input-medium"/>
            <input id="btnSubmit" onclick="return page();" class="hcy-btn hcy-search" style="height:inherit;line-height:1.5 ;" type="submit" value="查询"/>
        </form:form>
    </div>
    <sys:message content="${message}"/>
    <div class="tableBox">
        <table id="contentTable" class="hcy-public-table factoryTab" style="padding:0;">
            <thead>
            <tr>
                <th>收费项目编码</th>
                <th>收费项目名称</th>
                <th>收费项目代码</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${hisChargeCodeList}" var="item">
                <tr>
                    <td>${item.SFCODE}</td>
                    <td>${item.SFNO}</td>
                    <td>${item.SFNAME}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pagination">${page}</div>
</div>
<script src="${ctxStatic}/hcy/js/layer.js"></script>
<script type="text/javascript">
    //全选
    function cli(Obj)
    {
        var collid = document.getElementById("all")
        var coll = document.getElementsByName(Obj)
        if (collid.checked){
            for(var i = 0; i < coll.length; i++)
                coll[i].checked = true;
        }else{
            for(var i = 0; i < coll.length; i++)
                coll[i].checked = false;
        }
    }
    //批量删除
    function batchDelete(){
        var text="";
        $("input[name=find_watch]").each(function() {
            if ($(this).prop("checked")) {
                text += ","+$(this).val();
            }
        });
        if(text != null && text != ''){
            $("#searchForm").attr("action","${ctx}/pd/pdSupplier/batchDelete?pdSupplierIds="+text);
            $("#searchForm").submit();
        }else{
            layer.alert("请选择供应商！",{icon:2},function(index){
                layer.close(index);
            });
            return false;
        }
        return false;
    }
</script>
</body>
</html>