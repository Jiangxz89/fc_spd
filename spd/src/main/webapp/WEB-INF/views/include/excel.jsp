<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div id="importExcel" class="hide" >
 <div class="form"  style="height: 50px;float:left;clear:both;margin:5px 0px;background-color:#f4f4f4;border:1px solid #fff;width:100%;padding:5px;margin-left:5px;-moz-border-radius:5px;-webkit-border-radius:5px;border-radius:5px;-moz-box-shadow:0px 0px 3px #aaa;-webkit-box-shadow:0px 0px 3px #aaa;box-shadow:0px 0px 3px #aaa;">
   <form action="${ctx}/excel/upload/clnic/templete" method="post" id="downtemplete">
	  <input type="submit"  class="hcy-btn hcy-btn-o" value="下载模板"/>
   </form>
 </div>
 <form action="${ctx}/excel/upload/upExcel"  class="form" method="post" enctype="multipart/form-data" id="uploadfrm">
   <input type="file" name="fiels" value="" id="fiels" style="display:none;">
   <img id="imgupload"  style="cursor:pointer;" width="145" height="52" src="${ctxStatic}/images/upload.png" onclick="$('#fiels').click();" />
   <div>
   <div class="progress" style="margin-top:10px;height:20px;margin-bottom:20px;overflow:hidden;background-color:#f5f5f5;border-radius:4px;-webkit-box-shadow:inset 0 1px 2px rgba(0,0,0,0.1);box-shadow:inset 0 1px 2px rgba(0,0,0,0.1);">
	<div class="progress-bar progress-bar-success" style="width: 0%;background-color: #5cb85c;" id="proBar">
	<span class="sr-only" id="progressmessage"></span>
	</div>
   </div>
  </div>
 </form>
 <!-- 验证失败excel 文件下载  -->
 <div class="form"  style="height: 100px; <c:if test="${isExcel!='true'}">display: none;</c:if>"   id="errlist"> 
  <div>
  <label style="font-weight:bold;">excel共:</label><span style="color:green;">${totalcount}</span><label style="font-weight:bold;">条</label>   <c:if test="${errcount>0}">  &nbsp;&nbsp;    <label style="font-weight:bold;">验证失败数：</label> <span style="color:red;">${errcount}</span><label style="font-weight:bold;">条</label></c:if>
  <c:if test="${rockbackCount>0}"> &nbsp;&nbsp;<label>保存失败数：</label> <span style="color:red;">${rockbackCount}</span><label>条</label></c:if>
  <c:if test="${rockbackCount==0 && errcount==0}"> &nbsp;&nbsp;<label>保存成功数：</label> <span style="color:red;">${totalcount}</span><label>条</label></c:if>
  </div>
  <div style="margin-top:10px;">
  <form action="${ctx}/excel/upload/downdemo" method="post" id="downexcelfrm">
      <c:if test="${totalcount>0 && oldfilepath!=null}"> 
	  <input type="button"  class="hcy-btn hcy-btn-o"  value="下载原文件"  onclick="downloadexcel('${oldfilepath}');"/>
	  </c:if>
	  <c:if test="${errcount>0 && errfilepath!=null}">
	  <input type="button"   class="hcy-btn hcy-btn-o" value="下载错误文件" onclick="downloadexcel('${errfilepath}');"/>
	  </c:if>
	  <c:if test="${rockbackCount>0}">
	  <input type="button"  class="hcy-btn hcy-btn-o" value="下载保存失败文件" onclick="downloadexcel('${rockbackFilePath}');"/>
	  </c:if>
	  <input  type="hidden" name="filename" id="hid_exceldowning" value=""/>
  </form>
  </div>
 </div>
</div>	
<script type="text/javascript">
$(document).ready(function(){  
 //提交表单
  $("#fiels").on("change",function(){
   uploadfile();
   $("#uploadfrm").submit();
   //禁止选择文件
   $("#imgupload").attr("onclick","");
  });
})

//下载错误文件
function downloadexcel(para){
document.getElementById("hid_exceldowning").value=para;
document.getElementById("downexcelfrm").submit();
}

//后台提示信息
var errmsg="${errmsg}";
if(errmsg!=null &&  errmsg!=""  && errmsg!=undefined && errmsg.length>0){
   alert(errmsg);
}

//点击下载
function  imgclick(){
 $("#errlist").hide();
 document.getElementById("fiels").click(); 
}

//显示错误列表
var totalcount="${totalcount}";
if(totalcount>0){
  $("#errlist").show();
}

//进度条
function  uploadfile(){
var eventFun = function(){  
$.ajax({  
        type: 'GET',  
        url: '${ctx}/excel/upload/process',  
        data: {},  
        dataType: 'json',  
        success : function(data){  
       	 if(data!=null && data!="" && data !=undefined){
       		$('#proBar').css('width',data.rate+''+'%');  
            $('#progressmessage').empty();  
            $('#progressmessage').append(data.msg);   
       	 }
        }
     }) 
  }
  var intId = window.setInterval(eventFun,500);  
}
</script>