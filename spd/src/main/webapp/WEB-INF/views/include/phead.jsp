<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'cerulean'}/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/common/jeesite.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/common/jeesite.js" type="text/javascript"></script>
<script src="${ctxStatic}/laydate/laydate.js" type="text/javascript"></script>
<script src="${ctxStatic}/hcy/js/qrcode.js" type="text/javascript"></script>
<script src="${ctxStatic}/hcy/js/layer.js"></script>
<link rel="stylesheet" href="${ctxStatic}/mall/css/hcy-common.css">
<link rel="stylesheet" href="${ctxStatic}/mall/css/font-awesome.css">
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
<script type="text/javascript">var ctxp = '${ctxp}', ctxStatic='${ctxStatic}';</script>
<script type="text/javascript">
function isJson(obj){  
  var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;   
  return isjson;  
}  
function  loginCallBack(data){
 if(!isJson(data)){
   data=JSON.parse(data);
 }
 if(data.code=='109'){
   layer.alert(data.message,{icon:2},function(index){
	 layer.close(index);
   });
 }else if(data.code=='118'){
    window.location.href="${ctxp}/login";
 }else{
   layer.alert(data.message,{icon:1},function(index){
	 layer.close(index);
   });
 }
}
</script>
