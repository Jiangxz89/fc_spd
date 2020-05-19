<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>病人管理</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/spd/css/base.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css">
    <link rel="stylesheet" href="${ctxStatic}/spd/css/newSearchBox.css">
    <style>
        .layui-layer-content{text-align:center;}
        #submit{width:80px;height:32px;opacity:0;position:absolute;}
        .layui-layer-content>h4{font-size:14px;font-weight: 400;line-height: 60px;color:#666;}

        .newSearchBox1{background:#DEE9F1;padding:10px 40px;line-height: 40px;margin:5px 0 0 0;border-radius: 12px;}
        .newSearchBox1 label,.newSearchBox input,.newSearchBox select{vertical-align: middle; color:#666;display: inline-block;height: 28px;line-height: 28px;border-radius:2px;font-size: 12px;}
        .newSearchBox1 label{text-align: right;margin:0;}
        .newSearchBox1 select{width: 129px;margin:0 20px 0 0;}
        .newSearchBox1 input[type='text']{width: 150px;margin:0 20px 0 0;padding:0 0 0 5px;}
        .newSearchBox1 input,.newSearchBox select{background: #fff;border:1px solid #ccc;box-shadow:none;}
        .newSearchBox1 input[type='button'],.newSearchBox input[type='submit'],.newSearchBox>a{line-height:1.5;height:auto;cursor: pointer;border:none;font-size:12px;}
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
        <h4>病人管理</h4>
        <a href="${ctx}/pd/pdPatien/form?flag=add" class="hcy-btn hcy-btn-primary">添加病人</a>
    </div>
    <form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdPatien" action="${ctx}/pd/pdPatien/list" method="post" class="breadcrumb form-search">
        <div class="newSearchBox1">
            <label for="">姓名：</label>
            <input type="text" autocomplete="off" id="name" name="name" value="${pdPatien.name }"/>
            <label for="">身份证号：</label>
            <input type="text" autocomplete="off" id="idNumber" name="idNumber" value="${pdPatien.idNumber }"/>
            <label for="">住院号/门诊号：</label>
            <input type="text" autocomplete="off" id="inhospitalNo" name="inhospitalNo" value="${pdPatien.inhospitalNo }"/>

            <input id="btnSubmit" onclick="return page();" class="hcy-btn hcy-search" style="height: inherit;line-height:1.5;"  type="submit" value="查询"/>
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
                <th>姓名</th>
                <th>身份证号</th>
                <th>住院号/门诊号</th>
                <th>备注</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <form:form id="dataForm" method="post" class="breadcrumb form-search">
                <c:forEach items="${page.list }" var="pdPatien" varStatus="a">
                    <tr>
                        <td>
                            <input type="checkbox" name="checkProduct" value="${pdPatien.id }"/>
                            <input type="hidden" value="${pdPatien.id }">
                        </td>
                        <td>${pdPatien.name }</td>
                        <td>${pdPatien.idNumber}</td>
                        <td>${pdPatien.inhospitalNo}</td>
                        <td>${pdPatien.remarks}</td>
                        <td><fmt:formatDate value="${pdPatien.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <%--<td>${pdPatien.createDate }</td>--%>
                        <td style="width:120px;">
                            <a href="${ctx }/pd/pdPatien/form?id=${pdPatien.id}&flag=see" class="hcy-btn hcy-btn-o">查看</a>
                            <a href="${ctx }/pd/pdPatien/form?id=${pdPatien.id}&flag=update" class="hcy-btn hcy-btn-o">修改</a>
                            <a href="###" data-id="${pdPatien.id}" class="hcy-btn hcy-btn-o removeProBtn">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </form:form>
            </tbody>
        </table>
        <div class="pagination">${page}</div>

    </div>
</div>


<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
<script src="${ctxStatic}/spd/js/layer.js"></script>
<script type="text/javascript">

    $(function(){

        //重置
        $(".hcy-reset").click(function(){
            $(".newSearchBox1 input[type='text']").val("");
        })

        //删除病人
        $(document).on("click",".removeProBtn",function(){

            var id=$(this).attr("data-id");
            layer.confirm(
                "确认删除当前病人？",
                {//options这里写对应的参数需求，如图标，提示文字，按钮名称等
                    icon:3,
                    title:"提示",
                    btn:["确定","取消"]
                },
                function(index){
                    $.ajax({
                        url:"${ctx }/pd/pdPatien/delete",
                        type:"POST",
                        data:{id:id},
                        dataType:"text",
                        success:function(data){
                            layer.close(layer.index);
                            // if(data == 'yes'){
                                window.location.href = "${ctx}/pd/pdPatien/list";
                            // }else if(data == 'no'){

                            // }else{

                            // }
                        }
                    })
                },
                function(index){
                    layer.close(index);
                }
            )
        })

        //全选
        $("#chkAll").click(function(){
            if($(this).attr("checked") == 'checked'){
                $("input[name='checkProduct']").attr("checked",$(this).attr("checked"));
            }else{
                $("input[name='checkProduct']").removeAttr("checked");
            }

        })
    })
</script>
</body>
</html>