<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
if(typeof($pn)=="undefined")
	var $pn,$repeatA,$last,prev,next,$lpn,$firstPage,$firstPage;
    $repeatA=false,$last="${page.last}", prev="${page.prev}",next="${page.next}",$lpn="${page.last}",$firstPage="${page.firstPage}",$lastPage="${page.lastPage}";
	function page(para){
		var  isfirst="${page.firstPage}";
		var  islast="${page.lastPage}";
		var  next="${page.next}";
		var  prev="${page.prev}";
		if(isfirst=="true"  && para=="prev"){
			alert("已经是第一页");
			return false;
		}else if(islast=="true" && para=="next"){
			alert("已经是第最后一页");
			return false;
		}
		if(para=="prev"){
		  $("#pageNo").val(prev);
		}else if(para=="next"){
		  $("#pageNo").val(next);	
		}
		$("#frm").submit();
	}
	
	function loadPage(form,para,url){
		if(form==null || form.length==0 || !existsElement(form)){
		  throw Error("function  loadPage(传参数错误)");
		}
		if($firstPage=="true"&& para=="prev"){
			$repeatA=false;
			alert("已经是第一页");
			return false;
		}else if($lastPage=="true" && para=="next"){
			$repeatA=false;
			alert("已经是第最后一页");
			return false;
		}
		if(para=="prev"){
		  $pn=prev;
		}else if(para=="next"){
		  $pn=next;
		}
		if(!existsElement("p_n")){
		 $("#"+form).append('<input type="hidden" name="pageNo"  value="'+$pn+'" id="p_n"/>');
		}
		//自定义函数，回调
	    $callbackPage();
   }
	
    $("#prev").one("click",function(){
	  try{
	    if($repeatA)
	    	return false;
		$repeatA=true;
		var form=null;
		var choose=$(this).attr("data");
		if(choose!=null)
			form=choose.split("_");
		if(form!=null && form[0]=="asyn")
			loadPage(form[1],"prev");
		else
		   page("prev");
	  }catch(e){
		  throw new Error(e);
	  };
   });

  $("#next").one("click",function(){
	try{
		if($repeatA)
	    	return false;
		var form=null;
		var choose=$(this).attr("data");
		if(choose!=null)
			form=choose.split("_");
		if(form!=null && form[0]=="asyn")
			loadPage(form[1],"next");
		else
			page("next");
	}catch(e){
		throw new Error(e);
	}
});	
  
 function $prevPage(pn){
  if(pn!=undefined)
	  $pn="1";
  $("#p_n").val($pn || "1");
 } 
 function $initPage(data){
  $last=data.page$last ||"";
  prev=data.page$prev ||"";
  next=data.page$next ||"";
  $lpn=data.page$last ||"";
  $firstPage=data.page$firstPage ||"";
  $lastPage=data.page$lastPage ||"";  
 }

 function $appendPage(){
 try{
   $("#lpn").html($lpn || "1");
   $("#pn").html($pn);
   $repeatA=false;
 }catch(e){
   throw new Error(e);
 }
 }  
 
function existsElement(divId){
  if($("#"+divId).length && $("#"+divId).length>0){
      return true;
  }
}
</script>