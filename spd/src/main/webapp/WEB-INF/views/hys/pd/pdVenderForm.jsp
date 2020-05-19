<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/pic-upload/pic-upload.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<style type="text/css">
		.addProductBox .card-box{height:330px;!important}
		.addProductBox .card-box>.card-box-code{margin:0;}
		.addProductBox .card-box>.showpic{line-height: 30px; padding-left: 12px;}
		 .showPic{text-align:center;cursor:pointer}
    	#showPic  img{display:inline-block; width:512px;height:320px;margin-top:30px;}
    	.tR_upPic_icon{width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;!important}
    	#inputForm .controls{margin-left:0;!important}
    	.showpic{cursor:pointer}
    	.smallImg img {width: 100%;height: 100%;position: relative; z-index: 6;!important}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					name: {remote: "${ctx}/pd/pdVender/checkVenderName?id=" + encodeURIComponent('${pdVender.id}')},
				},
				messages: {
					name: {remote: "生产厂家已存在"},
				},
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
	<input id="flag" type="hidden" value="${flag }" />
	<form:form id="inputForm" modelAttribute="pdVender" method="post" class="form-horizontal" enctype="multipart/form-data">
		<div class="right-main-box">
			<div class="addProductBox">
				<input type="hidden" name="id" value="${pdVender.id }" />
				<label for="">生产厂家名称</label>
				<input type="text" class="createInp" autocomplete="off" style="width:300px;height:30px;border:1px solid #ccc;" name="name" value="${pdVender.name }" required/>
				<label for="">拼音简码</label>
				<input type="text" class="createInp" style="width:300px;height:30px;border:1px solid #ccc;" autocomplete="off" name="pinyin" value="${pdVender.pinyin }" required/><br />
				<label for="" style="float:left;padding-top:15px;">证照扫描件</label>
				<div class="all-card-box" style="padding-left:105px;">
				<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="licenceName1" value="${pdVender.licenceName1 }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="licenceNum1" value="${pdVender.licenceNum1 }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="licenceDate1" value="${pdVender.licenceDate1 }"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite1Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.licenceSite1 =='' || pdVender.licenceSite1 == null }">style="display:none;"</c:if> src="${ctxImg }${pdVender.licenceSite1 }" class="card-box_img"/>
									<div <c:if test="${pdVender.licenceSite1 =='' || pdVender.licenceSite1 == null || flag  == 'see' }">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="licenceSite1" value="${pdVender.licenceSite1 }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="licenceName2" value="${pdVender.licenceName2 }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="licenceNum2" value="${pdVender.licenceNum2 }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="licenceDate2" value="${pdVender.licenceDate2 }"/>
		            </div>
		            <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							    <input type="file" data-code='0' class="upPic" name="licenceSite2Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.licenceSite2 =='' || pdVender.licenceSite2 == null }">style="display:none;"</c:if> src="${ctxImg }${pdVender.licenceSite2 }" class="card-box_img"/>
									<div <c:if test="${pdVender.licenceSite2 =='' || pdVender.licenceSite2 == null || flag  == 'see' }">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="licenceSite2" value="${pdVender.licenceSite2 }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="licenceName3" value="${pdVender.licenceName3 }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="licenceNum3" value="${pdVender.licenceNum3 }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="licenceDate3" value="${pdVender.licenceDate3 }"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite3Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.licenceSite3 =='' || pdVender.licenceSite3 == null }">style="display:none;"</c:if> src="${ctxImg }${pdVender.licenceSite3 }" class="card-box_img"/>
									<div <c:if test="${pdVender.licenceSite3 =='' || pdVender.licenceSite3 == null  || flag  == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="licenceSite3" value="${pdVender.licenceSite3 }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="licenceName4" value="${pdVender.licenceName4 }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="licenceNum4" value="${pdVender.licenceNum4 }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="licenceDate4" value="${pdVender.licenceDate4 }"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite4Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.licenceSite4 =='' || pdVender.licenceSite4 == null }">style="display:none;"</c:if> src="${ctxImg }${pdVender.licenceSite4 }" class="card-box_img"/>
									<div <c:if test="${pdVender.licenceSite4 =='' || pdVender.licenceSite4 == null || flag  == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="licenceSite4" value="${pdVender.licenceSite4 }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="licenceName5" value="${pdVender.licenceName5 }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="licenceNum5" value="${pdVender.licenceNum5 }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="licenceDate5" value="${pdVender.licenceDate5 }"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite5Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.licenceSite5 =='' || pdVender.licenceSite5 == null }">style="display:none;"</c:if> src="${ctxImg }${pdVender.licenceSite5 }" class="card-box_img"/>
									<div <c:if test="${pdVender.licenceSite5 =='' || pdVender.licenceSite5 == null || flag  == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="licenceSite5" value="${pdVender.licenceSite5 }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="licenceName6" value="${pdVender.licenceName6 }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="licenceNum6" value="${pdVender.licenceNum6 }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="licenceDate6" value="${pdVender.licenceDate6 }"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite6Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.licenceSite6 =='' || pdVender.licenceSite6 == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.licenceSite6 }" class="card-box_img"/>
									<div <c:if test="${pdVender.licenceSite6 =='' || pdVender.licenceSite6 == null || flag  == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="licenceSite6" value="${pdVender.licenceSite6 }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="licenceName7" value="${pdVender.licenceName7 }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="licenceNum7" value="${pdVender.licenceNum7 }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="licenceDate7" value="${pdVender.licenceDate7 }"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite7Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.licenceSite7 =='' || pdVender.licenceSite7 == null }">style="display:none;"</c:if> src="${ctxImg }${pdVender.licenceSite7 }" class="card-box_img"/>
									<div <c:if test="${pdVender.licenceSite7 =='' || pdVender.licenceSite7 == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="licenceSite7" value="${pdVender.licenceSite7 }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="licenceName8" value="${pdVender.licenceName8 }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="licenceNum8" value="${pdVender.licenceNum8 }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="licenceDate8" value="${pdVender.licenceDate8 }"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite8Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.licenceSite8 =='' || pdVender.licenceSite8 == null }">style="display:none;"</c:if> src="${ctxImg }${pdVender.licenceSite8 }" class="card-box_img"/>
									<div <c:if test="${pdVender.licenceSite8 =='' || pdVender.licenceSite8 == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="licenceSite8" value="${pdVender.licenceSite8 }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="licenceName9" value="${pdVender.licenceName9 }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="licenceNum9" value="${pdVender.licenceNum9 }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="licenceDate9" value="${pdVender.licenceDate9 }"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite9Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.licenceSite9 =='' || pdVender.licenceSite9 == null }">style="display:none;"</c:if> src="${ctxImg }${pdVender.licenceSite9 }" class="card-box_img"/>
									<div <c:if test="${pdVender.licenceSite9 =='' || pdVender.licenceSite9 == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="licenceSite9" value="${pdVender.licenceSite9 }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="licenceName10" value="${pdVender.licenceName10 }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="licenceNum10" value="${pdVender.licenceNum10 }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="licenceDate10" value="${pdVender.licenceDate10 }"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite10Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.licenceSite10 =='' || pdVender.licenceSite10 == null }">style="display:none;"</c:if> src="${ctxImg }${pdVender.licenceSite10 }" class="card-box_img"/>
									<div <c:if test="${pdVender.licenceSite10 =='' || pdVender.licenceSite10 == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="licenceSite10" value="${pdVender.licenceSite10 }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="licenceName11" value="${pdVender.licenceName11 }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="licenceNum11" value="${pdVender.licenceNum11 }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="licenceDate11" value="${pdVender.licenceDate11 }"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite11Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.licenceSite11 =='' || pdVender.licenceSite11 == null }">style="display:none;"</c:if> src="${ctxImg }${pdVender.licenceSite11 }" class="card-box_img"/>
									<div <c:if test="${pdVender.licenceSite11 =='' || pdVender.licenceSite11 == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="licenceSite11" value="${pdVender.licenceSite11 }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="licenceName12" value="${pdVender.licenceName12 }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="licenceNum12" value="${pdVender.licenceNum12 }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="licenceDate12" value="${pdVender.licenceDate12 }"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite12Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img <c:if test="${pdVender.licenceSite12 =='' || pdVender.licenceSite12 == null }">style="display:none;"</c:if> src="${ctxImg }${pdVender.licenceSite12 }" class="card-box_img"/>
										<div <c:if test="${pdVender.licenceSite12 =='' || pdVender.licenceSite12 == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="licenceSite12" value="${pdVender.licenceSite12 }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		</div>
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
				<c:if test="${flag != 'see' }">
					<input type="submit" onclick="sub()" value="保存" class="hcy-btn hcy-btn-primary" />
				</c:if>
				
      			<a href="javascript:history.go(-1)" class="hcy-btn hcy-btn-o" style="line-height: normal;padding:6px 12px;background: #fff;color: #666;border-color: #ccc;">返回</a>
      		</div>
		</div>
	</form:form>
	<div id="showPic" style="display:none;">
		<img alt="" src=""  style="">
		<div style="display: none;">
		<p></p>
		<img alt="" src=""  height="300" width="200" >
		</div>
	 </div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/js/pinying.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<%--<script src="${ctxStatic}/spd/laydate/pinying.js"></script>--%>
	<script src="${ctxStatic}/pic-upload/pic-upload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.js"></script>
	<script>
		$(function(){
			
			//定位光标
			$("[name=name]").focus();
			
			//图片删除
			$(".smallImg_cloBtn").click(function(){
				$(this).parent().next().val("");
			})
			
			 //显示图片
		    $(".showpic").click(function(){
                var imgUrl=$(this).parents(".card-box").find(".smallImg img").attr("src");
                if(imgUrl.indexOf(".")!=-1){
                    window.open(imgUrl)
                }else{
                    layer.alert('请先添加图片', {icon: 0});
                }

		    	/*var showFlag=$("#flag").val();
		    	if(showFlag != 'see'){
		    		if($(this).parents(".card-box").find(".smallImg .smallImg_cloBtn").is(":hidden")){
			    		layer.alert('请先添加图片', {icon:0});
			    		return false
			    	}
		    	}
			  	if($(this).attr("data")=="idcard"){
				  $("#showPic img:last").attr("src",$(this).parents(".card-box").find(".card-box_img").attr("data"));
				  $("#showPic div").show();
			    }else{
				  $("#showPic div").hide();
			    }
		   		$("#showPic img:first").attr("src",$(this).parents(".card-box").find(".card-box_img").attr("src"));
			   	layer.open({
			   			type:1,
			   			title:"查看图片",
			   			content:$("#showPic"),
			   			area:["650px","460px"],
			   			shade: [0.8, '#393D49'],
			   			btn:["关闭"],
			   			btn2:function(){
			   			  layer.closeAll();
			   			}
			   		});*/
			   	});
			 if($("#flag").val() == 'see'){
				$("input").attr("disabled","true");	 
			 };
			 
			//时间插件
			lay('.validTime').each(function(){
			  laydate.render({
			    elem: this
			  });
			});

			$("input[name = 'name']").on("keyup",function(){
				var pinyin = makePy(this.value);
				// var pinyin = pinyin.getCamelChars(this.value);
				// $("input[name = 'pinyin']").val(pinyin);
				for(var j=0;j<pinyin.length;j++){
					$("input[name = 'pinyin']").val(pinyin[j],pinyin[j]);
				}
			});
		})
		
		
		
		function sub(){
			var flag = $("#flag").val();
			$("#inputForm").attr("action","${ctx}/pd/pdVender/save?flag="+flag);
			return true;
		}
	</script>
</body>
</html>