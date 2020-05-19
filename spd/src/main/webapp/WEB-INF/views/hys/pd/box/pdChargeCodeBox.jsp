<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
    <title>添加收费代码</title>
</head>
<body>
<div class="addProduct">
    <input type="hidden" id="isHisInterface" name="isHisInterface" value="${isHisInterface }" />
    <%--<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdProduct" action="${ctx}/pd/pdPackage/getAllProduct" method="post" class="breadcrumb form-search">--%>
        <div class="" style="text-align: center">
            <br/>
            <h5>${context}</h5><br/>
            <div id="input_div">
                <input id="chargeCodeInp" autocomplete="off" type="text" placeholder="请输入HIS收费项目代码"
                       style="padding:0 0 0 5px;margin:0;height:30px;border-radius:2px;width:220px;"
                       onkeyup="javascript:this.value=this.value.replace(/，/ig,',');"/>
                <br/>
                <span style="color: red">若有多个，请以英文逗号","隔开</span>
            </div>
            <div id="select_div">
                <select id="chargeCodeSel" multiple="multiple" placeholder="请选择HIS收费项目代码"
                        style="padding:0 0 0 5px;margin:0 auto;height:30px;border-radius:2px;width:220px;" >
                    <c:forEach items="${hisChargeCodeList}" var="item">
                        <option value="${item}">${item}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    <%--</form:form>--%>
</div>
<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
<script src="${ctxStatic}/spd/js/layer.js"></script>
<script type="text/javascript">
    var isHisInterface = "";
    $(function(){
        isHisInterface = $("#isHisInterface").val();
        if(isHisInterface == "false"){
            $("#input_div").show();
            $("#select_div").hide();
        }else{
            $("#input_div").hide();
            $("#select_div").show();
        }
    });

    function getChargeCode(){
        var chargeCode = "";
        if(isHisInterface == "false"){
            chargeCode = $("#chargeCodeInp").val();
        }else{
            // his 接口
            chargeCode = $("#chargeCodeSel").val();
        }
        return chargeCode;
    }
</script>
</body>
</html>