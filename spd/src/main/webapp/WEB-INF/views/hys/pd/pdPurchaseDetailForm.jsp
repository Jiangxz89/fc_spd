<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/print.css" media="print">
    <style>
        .addRoomBox{line-height: 40px;padding:10px 5px;margin-bottom: 20px;}
        .addRoomBox label{width:75px;display: inline-block;text-align: left;}
        .addRoomBox>input[type='text'],.addRoomBox>select{display:inline-block;width: 160px;height:30px;border:1px solid #ccc;margin:0 10px 0 5px;}
        .totalText{text-align: right;height: 50px;line-height: 50px;}
        #allMoney,#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
        .refuseBox{padding:30px 0;}
        .refuseBox>input.refuseInp{width:300px;height:30px;border:1px solid #ccc;padding-left:5px;}
        .otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;margin:0;}
        .otherText>h3{font-weight:400;float:left;padding:0px 10px 0 5px;font-size:13px;color:#666;width:72px;line-height:30px;}
    </style>
    <title>申请采购单详情</title>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<%-- <ul class="nav nav-tabs">
    <li><a href="${ctx}/pd/pdPurchaseDetail/">申购单详细列表</a></li>
    <li class="active"><a href="${ctx}/pd/pdPurchaseDetail/form?id=${pdPurchaseDetail.id}">申购单详细<shiro:hasPermission name="pd:pdPurchaseDetail:edit">${not empty pdPurchaseDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pd:pdPurchaseDetail:edit">查看</shiro:lacksPermission></a></li>
</ul><br/> --%>
<div class="right-main-box">
    <div class="btnBox">
        <h4>采购管理详情</h4>
    </div>
    <form:form id="inputForm" modelAttribute="pdPurchaseDetail" action="${ctx}/pd/pdPurchaseDetail/save" method="post" class="form-horizontal">
        <form:hidden path="id"/>
        <sys:message content="${message}"/>
        <div class="searchBox">
            <label for="">采购单号</label>
            <input type="text" style="width:165px;" value="${pdPurchaseOrder.orderNo }" readonly="readonly"/>
            <label for="">申购科室</label>
            <input type="text" value="${pdPurchaseOrder.deptName }" readonly="readonly"/>
            <label for="">操作人员</label>
            <input type="text" value="${pdPurchaseOrder.purchaseName }" readonly="readonly"/><br>
            <label for="">审核人员</label>
            <input type="text" style="width:165px;" value="${pdPurchaseOrder.auditName }" readonly="readonly"/>
            <label for="">审核时间</label>
            <input type="text" value='<fmt:formatDate value="${pdPurchaseOrder.auditDate }" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly"/>
        </div>
    </form:form>
    <div class="tableBox">
        <table class="hcy-public-table">
            <thead>
            <tr>
                <!-- <th>操作</th> -->
                <th>产品名称</th>
                <th>产品编号</th>
                <th>交易平台产品ID</th>
                <th>规格</th>
                <th>型号</th>
                <th>单位</th>
                <th>库存数量</th>
                <th>申购数量</th>
                <th>产品单价</th>
                <th>金额</th>
                <th>生产厂家</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${detailList }" var="li">
                <tr>
                    <!-- <td><i class="fa fa-trash-o"></i><i class="fa fa-search"></i></td> -->
                    <td>${li.prodName }</td>
                    <td>${li.prodNo }</td>
                    <td>${li.tpProdId }</td>
                    <td>${li.prodSpec }</td>
                    <td>${li.prodVersion }</td>
                    <td>${li.prodUnit }</td>
                    <td>${li.stockNum }</td>
                    <td>${li.applyCount }</td>
                    <td>${li.inPrice }</td>
                    <td>${li.amountMoney }</td>
                    <td>${li.venderName }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="totalText">
            <span id="allNum">总数量:<span>${pdPurchaseOrder.amountCount }</span></span>
            <span id="allMoney">总金额:<span>${pdPurchaseOrder.amountMoney }</span></span>
        </div>
        <div class="otherText" style="margin-top: 30px;">
            <h3>备注</h3>
            <textarea name="remarks" class="remarkArea" readonly="readonly">${pdPurchaseOrder.remarks }</textarea>
        </div>
        <c:if test="${pdPurchaseOrder.orderStatus eq 2 }">
            <div class="refuseBox otherText">
                <h3>拒绝理由</h3>
                <textarea class="remarkArea" readonly="readonly">${pdPurchaseOrder.refuseReason }</textarea>
                    <%-- <input type="text" value="${pdPurchaseOrder.refuseReason }"  readonly="readonly"/> --%>
            </div>
        </c:if>
    </div>
    <div class="bottomBtn" style="text-align: center;margin:30px 0;">
        <input type="button" value="打印" onclick="printFn()" class="hcy-btn hcy-btn-primary" />
        <a class="hcy-btn hcy-btn-primary" href="###" id="drivExcel">导出Excel</a>
        <a href="javascript:location=document.referrer;" class="hcy-btn hcy-back">返回</a>
    </div>
</div>
<div id="printBox">
    <%-- <div class="printHeader">
        <h3>${fns:getUser().companyName }</h3>
        <h3>采购单</h3>
    </div>
    <div class="printPage">
        页码：1/1
    </div> --%>
    <div style="margin-left: 50%" id="div_id"></div>
    <div class="printTab">
        <table>
            <thead style="display:table-header-group;">
            <tr>
                <td colspan="8" style="border:0;font-size:20px;color:#202020">${fns:getUser().companyName }<span style="padding-left:40px;">采购单</span></td>
            </tr>
            <tr>
                <td colspan="8" style="border:0;">
                    <div class="printList">
                        <ul>
                            <li>
                                <label>申领单号：</label>
                                <span id="outNo">${pdPurchaseOrder.orderNo }</span>
                            </li>
                            <li>
                                <label>操作人员：</label>
                                <span id="outDate">${pdPurchaseOrder.purchaseName }</span>
                            </li>
                            <li>
                                <label>审核时间：</label>
                                <span id="oName"><fmt:formatDate value="${pdPurchaseOrder.auditDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            </li>
                        </ul>
                        <ul>
                            <li>
                                <label>申购科室：</label>
                                <span id="iName">${pdPurchaseOrder.deptName }</span>
                            </li>
                            <li>
                                <label>审核人员：</label>
                                <span>${pdPurchaseOrder.auditName }</span>
                            </li>
                        </ul>
                    </div>
                    <div>
                        <ul style="padding:0;margin:0;text-align:left;">
                            <li style="height:40px;line-height:40px;">
                                <label style="display: inline-block;width: 90px;font-size: 14px;">备注：</label>
                                <span style="width:auto;font-weight:400;" id="oName">${pdPurchaseOrder.remarks }</span>
                            </li>
                        </ul>
                    </div>
                </td>
            </tr>
            <tr>
                <th>产品名称</th>
                <th>产品编号</th>
                <th>规格</th>
                <th>型号</th>
                <th>单位</th>
                <th>库存数量</th>
                <th>申购数量</th>
                <th>产品单价</th>
                <th>金额</th>
                <th>生产厂家</th>
            </tr>
            </thead>
            <tbody id="printDiv">
            <c:forEach items="${detailList }" var="li">
                <tr>
                    <td>${li.prodName }</td>
                    <td>${li.prodNo }</td>
                    <td>${li.prodSpec }</td>
                    <td>${li.prodVersion }</td>
                    <td>${li.prodUnit }</td>
                    <td>${li.stockNum }</td>
                    <td>${li.applyCount }</td>
                    <td>${li.inPrice }</td>
                    <td>${li.amountMoney }</td>
                    <td>${li.venderName }</td>
                </tr>
            </c:forEach>
            <tr class="total">
                <td colspan="7" style="text-align:right;">数量合计：${pdPurchaseOrder.amountCount }</td>
                <td colspan="2" style="text-align:right;">金额合计：${pdPurchaseOrder.amountMoney }</td>
                <td colspan="1" style="text-align:right;"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="signBox">
        <h3>操作人员签字：__________</h3>
        <h3>审核人员签字：__________</h3>
    </div>
</div>
<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js"></script>
<script src="${ctxStatic}/spd/js/laydate.js"></script>
<script src="${ctxStatic}/spd/js/layer.js"></script>
<!-- <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script> -->
<script src="${ctxStatic}/spd/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
<script src="${ctxStatic}/jquery/jquery-barcode.js"></script>
<script>

    //rfidCode:单据号，需要获取
    $('#div_id').empty().barcode('${pdPurchaseOrder.orderNo }', "code128",{
        output:'css',
        color: '#000000',
        barWidth: 2,
        barHeight: 40,
        addQuietZone: false,
        fontSize: 0,
    });

    //导出明细数据
    $('#drivExcel').one('click',function(){
        $(this).css("background-color","#B3BDC3");
        var form = $('<form>');
        form.attr('style', 'display:none');
        form.attr('method', 'post');
        form.attr('action', '${ctx}/excelExport/pdPurchaseDetailExport');
        var input = $('<input>');
        input.attr('type', 'hidden');
        input.attr('name', 'exportData');
        input.attr('value', '${exportDataList}');
        form.append(input);
        $('body').append(form);
        form.submit();
        form.remove();
    });
    function printFn(){

        //打印
        event.preventDefault();
        $("#printBox").jqprint();
    }
</script>
</body>
</html>