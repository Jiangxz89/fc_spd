<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>产品表管理</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
    <style>
        .layui-layer-content {
            text-align: center;
        }

        #submit {
            width: 80px;
            height: 32px;
            opacity: 0;
            position: absolute;
        }

        .layui-layer-content > h4 {
            font-size: 14px;
            font-weight: 400;
            line-height: 60px;
            color: #666;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function page(n, s) {
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
    <input type="hidden" name="importKey" value="${importKey }"/><!-- 批导错误信息标记 -->
    <input type="hidden" name="saveKey" value="${saveKey }"/><!-- 保存错误信息标记 -->
    <div class="btnBox">
        <h4>产品管理</h4>
        <a href="${ctx}/pd/pdProduct/form?flag=add" class="hcy-btn hcy-btn-primary">添加产品</a>
        <a href="###" class="hcy-btn hcy-btn-primary" id="addSuppliers">批量添加供应商</a>
        <a href="${ctx}/pd/pdProduct/list?chargeCode=a0" class="hcy-btn hcy-btn-primary">显示全部未维护HIS代码记录</a>
        <a href="###" class="hcy-btn hcy-btn-primary" id="addChargeCode">添加HIS收费项目代码</a>
        <a href="###" class="hcy-btn hcy-btn-primary" id="import">批量导入</a>
        <a class="hcy-btn hcy-btn-primary" href="###" id="drivExcel">导出Excel</a>
        <c:if test="${ifPlatform == '0' ||isUrgent == '0'}">
            <a href="###" class="hcy-btn hcy-btn-primary" id="synProduct">同步产品至中心平台</a>
        </c:if>
    </div>
    <form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdProduct"
               action="${ctx}/pd/pdProduct" method="post" class="breadcrumb form-search">
        <div class="newSearchBox">
            <input type="hidden" name="chargeCode" value="${chargeCode }"/>
            <label for="">产品编号</label>
            <input type="text" autocomplete="off" id="number" name="number" value="${pdProduct.number }"/>
            <label for="">产品名称</label>
                <%--<input type="text" id="name" name="name" value="${pdProduct.name }"/>--%>
            <input class="select2_material form-control" style="width: 260px" id="productNameSelect" name="name">
            <label style="margin-left:15px;" for="">产品分类</label>
            <form:select path="categoryId" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${spd:findPdCategoryList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
            </form:select>
            <label for="">产品组别</label>
            <form:select path="groupId" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${spd:findPdGroupList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
            </form:select>
                <%--<label for="">产品权限</label>
                <select id="power" name="power">
                    <option value="">--全部--</option>
                    <c:forEach var="dict" items="${fns:getDictList('product_power') }">
                        <option value="${dict.label eq '公共产品'?'0':'1' }" <c:if test="${pdProduct.power eq (dict.label eq '公共产品'?'0':'1')}">selected</c:if>>${dict.label }</option>
                    </c:forEach>
                        &lt;%&ndash; <option value="公共产品" <c:if test="${pdProduct.power eq '公共产品'}">selected</c:if>>公共产品</option>
                        <option value="自有产品" <c:if test="${pdProduct.power eq '自有产品'}">selected</c:if>>自有产品</option> &ndash;%&gt;
                </select>--%>
            </br>
            <label for="">产品型号</label>
            <input type="text" autocomplete="off" id="version" name="version" value="${pdProduct.version }"/>
            <label for="">生产厂家</label>
            <form:select path="venderId" class="input-medium" style="width: 260px;">
                <form:option value="" label="全部(qb)"/>
                <form:options items="${spd:findPdVenderList() }" itemLabel="nameAndpinyin" itemValue="id"
                              htmlEscape="false"/>
            </form:select>
            <label for="">供应商</label>
            <form:select path="supplierId" class="input-medium" style="width: 260px;">
                <form:option value="" label="全部(qb)"/>
                <form:options items="${spd:findSupplierList()}" itemLabel="nameAndpinyin" itemValue="id"
                              htmlEscape="false"/>
            </form:select>
            <!-- <input id="search" class="hcy-btn hcy-btn-primary" type="button" value="查询"/> -->
            <input id="btnSubmit" onclick="return page();" class="hcy-btn hcy-search"
                   style="height: inherit;line-height:1.5;" type="submit" value="查询"/>
            <input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
        </div>

        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    </form:form>
    <sys:message content="${message}"/>
    <div class="tableBox">
        <table id="contentTable" class="hcy-public-table">
            <thead>
            <tr>
                <th><input type="checkbox" id="chkAll"/></th>
                <th>产品编号</th>
                <th>产品名称</th>
                <th>产品别名</th>
                <th>产品分类</th>
                <th>产品组别</th>
                <th>单位</th>
                <th>规格</th>
                <th>型号</th>
                <th>生产厂家</th>
                <th>供应商</th>
                <th>是否计费</th>
                <th>收费项目代码</th>
                <th>注册证号</th>
                <th>出货单价</th>
                <th>备注</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <form:form id="dataForm" modelAttribute="productExcelList" method="post" class="breadcrumb form-search">
                <c:forEach items="${page.list }" var="pdProduct" varStatus="a">
                    <tr>
                        <td>
                            <input type="checkbox" name="checkProduct" value="${pdProduct.id }"/>
                            <input type="hidden" value="${pdProduct.id }">
                        </td>
                        <td><input type="hidden" name="list[${a.index }].number" value="${pdProduct.number }"/><a
                                href="${ctx }/pd/pdProduct/toUpdate?id=${pdProduct.id}&flag=see"
                                title="${pdProduct.number }">${pdProduct.number }</a></td>
                        <td><input type="hidden" name="list[${a.index }].name" value="${pdProduct.name }"/><a
                                href="${ctx }/pd/pdProduct/toUpdate?id=${pdProduct.id}&flag=see"
                                title="${pdProduct.name }">${pdProduct.name }</a></td>
                        <td><input type="hidden" name="list[${a.index }].name"
                                   value="${pdProduct.otherName }"/>${pdProduct.otherName }</td>
                        <td><input type="hidden" name="list[${a.index }].category"
                                   value="${pdProduct.categoryName }"/>${pdProduct.categoryNameShow}</td>
                        <td><input type="hidden" name="list[${a.index }].group"
                                   value="${pdProduct.groupName }"/>${pdProduct.groupNameShow}</td>
                        <td><input type="hidden" name="list[${a.index }].unit"
                                   value="${pdProduct.unit }"/>${pdProduct.unit }</td>
                        <td><input type="hidden" name="list[${a.index }].spec"
                                   value="${pdProduct.spec }"/>${pdProduct.spec }</td>
                        <td><input type="hidden" name="list[${a.index }].version"
                                   value="${pdProduct.version }"/>${pdProduct.version }</td>
                        <td><input type="hidden" name="list[${a.index }].vender" value="${pdProduct.venderName }"/><a
                                href="${ctx}/pd/pdVender/form?id=${pdProduct.venderId}&flag=see"
                                title="${pdProduct.venderName }" class="overLook">${pdProduct.venderNameShow}</a></td>
                        <td><a href="${ctx}/pd/pdSupplier/form?id=${pdProduct.supplierId}&flag=see"
                               title="${pdProduct.supplierNameShow }"
                               class="overLook">${pdProduct.supplierNameShow }</a></td>
                        <c:if test="${pdProduct.isCharge eq '1'}">
                            <td>是</td>
                        </c:if>
                        <c:if test="${pdProduct.isCharge eq '0'}">
                            <td>否</td>
                        </c:if>
                        <td class="chargeCodeTd">${pdProduct.chargeCode }</td>
                        <input type="hidden" name="list[${a.index }].chargeCode" value="${pdProduct.chargeCode }"/>
                        <td><a href="###" title="${pdProduct.registration }" class="overLook"><input type="hidden"
                                                                                                     name="list[${a.index }].registration"
                                                                                                     value="${pdProduct.registration }"/>${pdProduct.registration }
                        </a></td>
                        <td><input type="hidden" name="list[${a.index }].sellingPrice"
                                   value="${pdProduct.sellingPrice }"/>${pdProduct.sellingPrice }</td>
                        <td><input type="hidden" name="list[${a.index }].description"
                                   value="${pdProduct.description }"/><a href="###" class="overLook"
                                                                         title="${pdProduct.description }">${pdProduct.description }</a>
                        </td>
                        <td style="width:120px;">
                            <a href="${ctx }/pd/pdProduct/toUpdate?id=${pdProduct.id}&flag=see"
                               class="hcy-btn hcy-btn-o">查看</a>
                            <a href="${ctx }/pd/pdProduct/toUpdate?id=${pdProduct.id}&flag=update"
                               class="hcy-btn hcy-btn-o">修改</a>
                            <a href="###" data-id="${pdProduct.id}" class="hcy-btn hcy-btn-o removeProBtn">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </form:form>
            </tbody>
        </table>
        <div class="pagination">${page}</div>

    </div>
</div>
<%--<div class="addChargeCodeBox">--%>
<%--<h4>冠状动脉球囊扩张导管等11个产品</h4>--%>
<%--<input type="text" style="padding:0 0 0 5px;margin:0;height:30px;border-radius:2px;width:220px;" class="addChargeCodeInp" />--%>
<%--<select style="padding:0 0 0 5px;margin:0 auto;height:30px;border-radius:2px;width:220px;" class="addChargeCodeSel">--%>
<%--<option value="">请选择</option>--%>
<%--<c:forEach items="${hisChargeCodeList}" var="item">--%>
<%--<option value="${item}">${item}</option>--%>
<%--</c:forEach>--%>
<%--</select><span style="color:red;" class="nonSelect"></span>--%>
<%--</div>--%>
<div class="importBox">
    <a href="###" style="margin-top: 80px;margin-right: 60px;" onclick="template()"
       class="hcy-btn hcy-btn-primary">下载模板</a>
    <div style="margin-top: 80px;position:relative;" class="hcy-btn hcy-btn-primary">直接导入
        <form id="importForm" method="post" onsubmit="" style="margin:0;position:absolute;left:0;top:0;"
              enctype="multipart/form-data" action="${ctx }/pd/pdProduct/importData"/>
        <input type="file" data-code='0' name="file" style="width:80px;height:32px;;margin:0;opacity:0;z-index:6666;"
               class="hcy-btn hcy-btn-primary" value="直接导入"/>
        <input type="submit" id="submit"/>
        </form>
    </div>
    <%-- <form:form id="importForm" enctype="multipart/
    'form-data" action="${ctx }/pd/pdProduct/importData" method="post" >
        <input type="file"  data-code='0' name="file" style="margin-top: 80px;"  class="hcy-btn hcy-btn-primary" value="直接导入" multiple/>
        <input type="submit" value="submit" />
    </form:form> --%>
    <!-- <a href="###" onclick="importData()" style="margin-top: 80px;"  class="hcy-btn hcy-btn-primary">直接导入</a> -->
</div>
<!-- 供应商导入窗口 -->
<%--<div class="addSuppliersBox" style="display:none">
    <h4></h4>
    <select style="padding:0 0 0 5px;margin:0 auto;height:30px;width:220px;" id="addSupplierSelect" class="addSupplierSelect">
        <option value="">请选择</option>
        <c:forEach items="${spd:findSupplierList()}" var="li">
            <option value="${li.id }">${li.supplierName }</option>
        </c:forEach>
    </select><span style="color:red;" class="nonSelect"></span>
</div>--%>

<%-- <div class="pagination">${page}</div> --%>
<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
<script src="${ctxStatic}/spd/js/layer.js"></script>
<script type="text/javascript">
    $(function () {

        var select2 = function () {
            $("#productNameSelect").select2({
                dropdownParent: $('.modal-content'),
                /*placeholder: "产品名称",*/
                allowClear: true,
                ajax: {
                    url: '${ctx}/pd/pdProduct/findList',
                    dataType: 'json',
                    data: function (params) {
                        var query = {
                            name: params //params.term 搜索参数值
                        }
                        return query;
                    },
                    results: function (data) {
                        //返回最终数据data 给dataArray
                        var dataArray = [];
                        for (var i = 0; i < data.length; i++) {
                            var dataObj = {};
                            dataObj.id = data[i].name;
                            dataObj.text = data[i].name;
                            dataArray.push(dataObj);
                        }
                        return {
                            results: dataArray
                        };
                    },
                    error: function (error) {
                    }
                }
            });
        }
        select2();

        $("#synProduct").on("click", function () {
            $.ajax({
                url: "${ctx}/pd/pdProduct/synProduct",
                type: "post",
                data: {},
                dataType: "json",
                success: function (data) {
                    if (data.code == "-200") {
                        layer.alert("同步失败");
                    } else {
                        layer.alert("同步成功");
                    }
                }
            })
        })

        //添加供应商
        $("#addSuppliers").click(function () {
            var checkObj = $("#contentTable>tbody>tr input[type='checkbox']:checked");
            var checkText = "";
            if (checkObj.length == 0) {
                layer.alert("请先选择要添加供应商的产品！", {icon: 0}, function (index) {
                    layer.close(index);
                });
            }
            var ids = [];
            if (checkObj.length > 0) {
                checkObj.each(function () {
                    //console.log($(this));
                    checkText = $(this).parent().next().next().text();
                    $(".addSuppliersBox>h4").text(checkText + "等" + checkObj.length + "个产品");
                    ids.push($(this).val());
                });
                var html = '<div class="addSuppliersBox">';
                html += '<input class="select2_material form-control" autocomplete="off"  style="width: 260px" id="productNameSelect2" name="name" >'
                html += '<span style="color:red;" class="nonSelect"></span>'
                html += '</div>';
                layer.open({
                    type: 1,
                    title: "批量添加产品供应商",
                    // content:$(".addSuppliersBox").html(),
                    content: html,
                    area: ["400px", "300px"],
                    shade: [0.8, '#393D49'],
                    btn: ["确定", "取消"],
                    zIndex: '99',
                    yes: function (index, layero) {
                        /*var val = layero.find(".addSupplierSelect option:selected");*/
                        var val = $("#productNameSelect2");
                        var supplierName = $("#productNameSelect2").select2('data').text
                        if (!val.val()) {
                            $('.nonSelect').html('请选择供应商');
                            return false;
                        }
                        window.location = "${ctx}/pd/pdProduct/addSuppliers?ids=" + ids.join(',')
                            + '&supplierId=' + val.val() + '&supplierName=' + supplierName;
                        layer.closeAll();
                    },
                    btn2: function () {
                        layer.closeAll();
                    }
                })

                $("#productNameSelect2").select2({
                    dropdownParent: $('.modal-content'),
                    /*placeholder: "产品名称",*/
                    allowClear: true,
                    ajax: {
                        url: '${ctx}/pd/pdProduct/findSupplierList',
                        dataType: 'json',
                        data: function (params) {
                            var query = {
                                supplierName: params //params.term 搜索参数值
                            }
                            return query;
                        },
                        results: function (data) {
                            //返回最终数据data 给dataArray
                            var dataArray = [];
                            for (var i = 0; i < data.length; i++) {
                                var dataObj = {};
                                dataObj.id = data[i].id;
                                dataObj.text = data[i].nameAndpinyin;
                                dataArray.push(dataObj);
                            }
                            return {
                                results: dataArray
                            };
                        },
                        error: function (error) {
                        }
                    }
                });

            }
        });
        $(document).on('change', ".addSupplierSelect", function () {
            $(this).next().html('');
        });

        //模糊查询
        //$("#search").click(function(){
        //	window.location = "${ctx}/pd/pdProduct/list?number="+$("#number").val()+"&name="+$("#name").val()+"&categoryName="+$("#categoryName").val()+"&groupName="+$("#groupName").val()+"&version="+$("#version").val()+"&venderName="+$("#venderName").val()+"&power="+$("#power").val();
        //})
        $(document).on("change", ".layui-layer-content input[name='file']", function () {
            $(document).find(".layui-layer-content #submit").click();

        })

        //批量导入行号
        var importKey = $("[name=importKey]").val();
        if (importKey != '') {
            layer.alert("第" + importKey + "行数据导入失败，请检查", {icon: 0}, function (index) {
                layer.close(index);
            });
        }
        //保存错误信息
        var saveKey = $("[name=saveKey]").val();
        if (saveKey != '') {
            layer.alert("产品编码重复，不可添加", {icon: 0}, function (index) {
                layer.close(index);
            });
        }
        /* $(".layui-layer-content input[name='file']").change(function(){
            alert("123")
            $("#importForm").submit();
        }) */
        //添加收费代码
        $("#addChargeCode").click(function () {
            var checkNum = $(".hcy-public-table>tbody>tr input[type='checkbox']:checked").length;
            var checkText = "";
            if (checkNum == 0) {
                layer.alert("请先选择要添加HIS收费项目的产品！", {icon: 1}, function (index) {
                    layer.close(index);
                });
            }
            if (checkNum > 1) {
                layer.alert("请选择一条要添加HIS收费项目的产品！", {icon: 1}, function (index) {
                    layer.close(index);
                });
            }
            if (checkNum == 1) {
                var context = "";
                $(".hcy-public-table>tbody>tr input[type='checkbox']:checked").each(function () {
                    checkText = $(this).parent().next().next().find("a").text();
                    // $(".addChargeCodeBox>h4").text(checkText+"等"+checkNum+"个产品");
                    context = checkText + "等" + checkNum + "个产品";
                })
                layer.open({
                    type: 2,
                    title: "添加HIS收费项目代码",
                    // content:$(".addChargeCodeBox").html(),
                    content: '${ctx}/pd/pdProduct/showChargeCodeBox?context=' + context,
                    area: ["400px", "300px"],
                    shade: [0.8, '#393D49'],
                    btn: ["保存", "取消"],
                    yes: function (index, layero) {
                        var childWindow = layero.find('iframe')[0].contentWindow;
                        var chargeCode = childWindow.getChargeCode();

                        var ids = '';
                        var ind = '';
                        $(".hcy-public-table>tbody>tr input[type='checkbox']:checked").each(function () {
                            ind = $(this).parents("tr").index();
                            $(".hcy-public-table>tbody>tr").eq(ind - 1).find(".chargeCodeTd").text(chargeCode);
                            ids += $(this).next().val() + ',';
                        })

                        if (chargeCode == null) {
                            chargeCode = "";
                        }
                        window.location = "${ctx}/pd/pdProduct/updateChargeCode?ids=" + ids + "&chargeCode=" + chargeCode + "";

                        layer.closeAll();
                    },
                    btn2: function () {
                        layer.closeAll();
                    }
                })

            }
        });
        //批量导入
        $("#import").click(function () {
            layer.open({
                type: 1,
                title: "批量导入",
                content: $(".importBox").html(),
                area: ["400px", "300px"],
                shade: [0.8, '#393D49'],
                btn: ["关闭"],
                yes: function () {
                    layer.closeAll();
                }
            })
        });


        //导出数据
        $('#drivExcel').one('click', function () {
            $(this).css("background-color", "#B3BDC3");
            var form = $('<form>');
            form.attr('style', 'display:none');
            form.attr('method', 'post');
            form.attr('action', '${ctx}/excelExport/productListExport');
            var input = $('<input>');
            input.attr('type', 'hidden');
            input.attr('name', 'exportData');
            input.attr('value', '${exportDataList}');
            form.append(input);
            $('body').append(form);
            form.submit();
            form.remove();


        });


        $("#importForm").on("click", function () {

            $("#importing").show();
        });

        //全选
        $("#chkAll").click(function () {
            if ($(this).attr("checked") == 'checked') {
                $("input[name='checkProduct']").attr("checked", $(this).attr("checked"));
            } else {
                $("input[name='checkProduct']").removeAttr("checked");
            }

        })

        //删除产品
        $(document).on("click", ".removeProBtn", function () {
            var ind = $(this).parent().parent().index();
            var id = $(this).attr("data-id");
            layer.confirm(
                "确认删除当前产品？",
                {//options这里写对应的参数需求，如图标，提示文字，按钮名称等
                    icon: 3,
                    title: "提示",
                    btn: ["确定", "取消"]
                },
                function (index) {
                    $.ajax({
                        url: "${ctx }/pd/pdProduct/delete",
                        type: "POST",
                        data: {id: id},
                        dataType: "text",
                        success: function (data) {
                            if (data == 'yes') {
                                window.location.href = "${ctx}/pd/pdProduct/list";
                            } else if (data == 'no') {
                                layer.alert("该商品有库存，不能删除！", {icon: 0}, function (index) {
                                    layer.close(index);
                                });
                                //alert("该商品有库存，不能删除");
                            } else {
                                layer.alert("该产品正在采购，无法删除", {icon: 0}, function (index) {
                                    layer.close(index);
                                });
                                //alert("发生了一点奇怪的错误。。");
                            }
                        }
                    })
                },
                function (index) {
                    layer.close(index);
                }
            )
        })
    })

    //下载模板
    function template() {
        var th = $("th");

        var arr = '';
        for (var i = 0; i < th.length; i++) {
            if (i == 0) {
                continue;
            }
            if (i == th.length - 1) {
                continue;
            }
            if (i == th.length - 2) {
                arr += th[i].innerText;
                continue;
            } else {
                arr += th[i].innerText + ',';
            }
        }

        window.location = "${ctx}/pd/pdProduct/generateTemplate?head=" + arr;
    }

    //全部导出
    function exportData() {
        var th = $("th");

        var arr = '';
        for (var i = 0; i < th.length; i++) {
            if (i == 0) {
                continue;
            }
            if (i == th.length - 1) {
                continue;
            }
            if (i == th.length - 2) {
                arr += th[i].innerText;
                continue;
            } else {
                arr += th[i].innerText + ',';
            }
        }

        $("#dataForm").attr("action", "${ctx}/pd/pdProduct/exportData?head=" + arr);
        $("#dataForm").submit();
    }

    //导入
    function importData() {
        //$("#importForm").submit();
        $("#submit").click();
    }


    //重置
    $(".hcy-reset").click(function () {
        var sel = "${fns:getUser().storeroomId}";//默认下拉
        $(".newSearchBox input[type='text']").val("");
        $("#storeroomId").select2("val", sel);
        $("#supplierId").select2("val", "");
        $("#categoryId").select2("val", "");
        $("#power").select2("val", "");
        $("#groupId").select2("val", "");
        $("#venderId").select2("val", "");
        $("#productNameSelect").val("")
        $("#productNameSelect").select2("data", "");
    })

    //删除
    function del(id) {
        if (confirm("确认要删除该产品吗？")) {
            var id = id;
            $.ajax({
                url: "${ctx }/pd/pdProduct/delete",
                type: "POST",
                data: {id: id},
                dataType: "text",
                success: function (data) {
                    if (data == 'yes') {
                        window.location.href = "${ctx}/pd/pdProduct/list";
                    } else if (data == 'no') {
                        alert("该商品有库存或正在申购，不能删除");
                    } else {
                        alert("发生了一点奇怪的错误，请通知管理员");
                    }
                }
            })
        }
    }
</script>
</body>
</html>