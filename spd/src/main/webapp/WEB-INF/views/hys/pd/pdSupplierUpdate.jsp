<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>修改供应商</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/pic-upload/pic-upload.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
	<style>
		.card-box{height: 320px;}
		.card-box-code{margin-top:10px;}
		.form-horizontal .control-label{font-size: 13px; color: #666; width: 100px;text-align: left;height: 30px;line-height: 30px;padding: 0}
		#showPic  img{display:inline-block; width:512px;height:320px;margin-top:30px;}
    	.tR_upPic_icon{width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;!important}
    	#inputForm .controls{margin-left:0;!important}
    	.showpic{cursor:pointer;height: 20px;line-height: 20px; text-align: right; padding-right: 20px;font-size: 13px;color: #00CFA5; padding-top: 4px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					supplierName: {remote: "${ctx}/pd/pdSupplier/checkSupplierName?id=" + encodeURIComponent('${pdSupplier.id}')},
				},
				messages: {
					supplierName: {remote: "供应商已存在"},
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
	<div class="right-main-box"> 
		<%--<div class="btnBox" style="border-bottom:1px solid #ccc">
			<h4 style="padding:0;">供应商信息修改</h4>
		</div>--%>
	<form:form id="inputForm" style="padding-top:20px;" modelAttribute="pdSupplier" enctype="multipart/form-data" action="${ctx}/pd/pdSupplier/update" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group addProductBox" style="padding-bottom:20px;">
			<%--<label class="control-label"><span class="mustIcon">*</span>供应商名称：</label>--%>
			<%--<div class="controls" style="margin:0;">--%>
				<input type="hidden" id="flag" name="flag" value="${flag }" />
				<%--<form:input path="supplierName" htmlEscape="false" required="required" value="${pdSupplier.supplierName }" style="height: 30px;border-radius: 2px;padding: 0 0 0 5px;" maxlength="100" class="input-xlarge required"/>--%>
			<%--</div>--%>

			<label for=""><span class="mustIcon">*</span>供应商名称：</label>
			<input type="text" class="createInp" style="width:300px;height:30px;border:1px solid #ccc;" name="supplierName" value="${pdSupplier.supplierName }" required/>
			<label for=""><span class="mustIcon">*</span>拼音简码</label>
			<input type="text" class="createInp" style="width:300px;height:30px;border:1px solid #ccc;" name="pinyin" value="${pdSupplier.pinyin }" required/><br />
		</div>
		<div class="control-group">
			<label class="control-label">扫描证件：</label>
			<div class="controls" style="margin:0;">
			<div class="card-box">
	            <div class="card-box-code">
	            	<label for="">证照名称</label>
	            	<input type="text" name="cardName1" value="${pdSupplier.cardName1 }"/><br />
	            	<label for="">证照号码</label>
	            	<input type="text" name="cardCode1" value="${pdSupplier.cardCode1 }"/><br />
	            	<label for="">有效期至</label>
	            	<input type="text" class="validTime" name="validityTerm1"  value="<fmt:formatDate value="${pdSupplier.validityTerm1 }" pattern="yyyy-MM-dd"/>"/>
	            </div>
	            <div class="showpic">查看大图</div>
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
						    <input type="file" data-code='0' class="upPic" name="imgUrl1Up" style="width: 100%; height: 100%;" multiple/>
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl1 == '' || pdSupplier.imgUrl1 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl1) }" class="card-box_img"/>
								<div <c:if test="${pdSupplier.imgUrl1 == '' || pdSupplier.imgUrl1 == null || flag == update}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<input type="hidden" name="imgUrl1" value="${pdSupplier.imgUrl1 }" />
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          	<div class="card-box">
	            <div class="card-box-code">
	            	<label for="">证照名称</label>
	            	<input type="text" name="cardName2" value="${pdSupplier.cardName2 }"/><br />
	            	<label for="">证照号码</label>
	            	<input type="text" name="cardCode2" value="${pdSupplier.cardCode2 }"/><br />
	            	<label for="">有效期至</label>
	            	<input type="text" class="validTime" name="validityTerm2"  value="<fmt:formatDate value="${pdSupplier.validityTerm2 }" pattern="yyyy-MM-dd"/>"/>
	            </div>
	            <div class="showpic">查看大图</div>
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
						    <input type="file" data-code='0' class="upPic" name="imgUrl2Up" style="width: 100%; height: 100%;" multiple/>
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl2 == '' || pdSupplier.imgUrl2 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl2) }" class="card-box_img"/>
								<div <c:if test="${pdSupplier.imgUrl2 == '' || pdSupplier.imgUrl2 == null || flag == update}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<input type="hidden" name="imgUrl2" value="${pdSupplier.imgUrl2 }" />
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          	<div class="card-box">
	            <div class="card-box-code">
	            	<label for="">证照名称</label>
	            	<input type="text" name="cardName3" value="${pdSupplier.cardName3 }"/><br />
	            	<label for="">证照号码</label>
	            	<input type="text" name="cardCode3" value="${pdSupplier.cardCode3 }"/><br />
	            	<label for="">有效期至</label>
	            	<input type="text" class="validTime" name="validityTerm3"  value="<fmt:formatDate value="${pdSupplier.validityTerm3 }" pattern="yyyy-MM-dd"/>"/>
	            </div>
	            <div class="showpic">查看大图</div>
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
						    <input type="file" data-code='0' class="upPic" name="imgUrl3Up" style="width: 100%; height: 100%;" multiple/>
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl3 == '' || pdSupplier.imgUrl3 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl3) }" class="card-box_img"/>
								<div <c:if test="${pdSupplier.imgUrl3 == '' || pdSupplier.imgUrl3 == null || flag == update}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<input type="hidden" name="imgUrl3" value="${pdSupplier.imgUrl3 }" />
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          	<div class="card-box">
	            <div class="card-box-code">
	            	<label for="">证照名称</label>
	            	<input type="text" name="cardName4" value="${pdSupplier.cardName4 }"/><br />
	            	<label for="">证照号码</label>
	            	<input type="text" name="cardCode4" value="${pdSupplier.cardCode4 }"/><br />
	            	<label for="">有效期至</label>
	            	<input type="text" class="validTime" name="validityTerm4"  value="<fmt:formatDate value="${pdSupplier.validityTerm4 }" pattern="yyyy-MM-dd"/>"/>
	            </div>
	            <div class="showpic">查看大图</div>
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
						    <input type="file" data-code='0' class="upPic" name="imgUrl4Up" style="width: 100%; height: 100%;" multiple/>
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl4 == '' || pdSupplier.imgUrl4 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl4) }" class="card-box_img"/>
								<div <c:if test="${pdSupplier.imgUrl4 == '' || pdSupplier.imgUrl4 == null || flag == update}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<input type="hidden" name="imgUrl4" value="${pdSupplier.imgUrl4 }" />
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          	<div class="card-box">
	            <div class="card-box-code">
	            	<label for="">证照名称</label>
	            	<input type="text" name="cardName5" value="${pdSupplier.cardName5 }"/><br />
	            	<label for="">证照号码</label>
	            	<input type="text" name="cardCode5" value="${pdSupplier.cardCode5 }"/><br />
	            	<label for="">有效期至</label>
	            	<input type="text" class="validTime" name="validityTerm5"  value="<fmt:formatDate value="${pdSupplier.validityTerm5 }" pattern="yyyy-MM-dd"/>"/>
	            </div>
	            <div class="showpic">查看大图</div>
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
						    <input type="file" data-code='0' class="upPic" name="imgUrl5Up" style="width: 100%; height: 100%;" multiple/>
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl5 == '' || pdSupplier.imgUrl5 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl5) }" class="card-box_img"/>
								<div <c:if test="${pdSupplier.imgUrl5 == '' || pdSupplier.imgUrl5 == null || update == flag}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<input type="hidden" name="imgUrl5" value="${pdSupplier.imgUrl5 }" />
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          	<div class="card-box">
	            <div class="card-box-code">
	            	<label for="">证照名称</label>
	            	<input type="text" name="cardName6" value="${pdSupplier.cardName6 }"/><br />
	            	<label for="">证照号码</label>
	            	<input type="text" name="cardCode6" value="${pdSupplier.cardCode6 }"/><br />
	            	<label for="">有效期至</label>
	            	<input type="text" class="validTime" name="validityTerm6"  value="<fmt:formatDate value="${pdSupplier.validityTerm6 }" pattern="yyyy-MM-dd"/>"/>
	            </div>
	            <div class="showpic">查看大图</div>
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
						    <input type="file" data-code='0' class="upPic" name="imgUrl6Up" style="width: 100%; height: 100%;" multiple/>
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl6 == '' || pdSupplier.imgUrl6 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl6) }" class="card-box_img"/>
								<div <c:if test="${pdSupplier.imgUrl6 == '' || pdSupplier.imgUrl6 == null || flag == updadte}">style="display:none;"</c:if>  class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<input type="hidden" name="imgUrl6" value="${pdSupplier.imgUrl6 }" />
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          	<div class="card-box">
	            <div class="card-box-code">
	            	<label for="">证照名称</label>
	            	<input type="text" name="cardName7" value="${pdSupplier.cardName7 }"/><br />
	            	<label for="">证照号码</label>
	            	<input type="text" name="cardCode7" value="${pdSupplier.cardCode7 }"/><br />
	            	<label for="">有效期至</label>
	            	<input type="text" class="validTime" name="validityTerm7"  value="<fmt:formatDate value="${pdSupplier.validityTerm7 }" pattern="yyyy-MM-dd"/>"/>
	            </div>
	            <div class="showpic">查看大图</div>
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
						    <input type="file" data-code='0' class="upPic" name="imgUrl7Up" style="width: 100%; height: 100%;" multiple/>
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl7 == '' || pdSupplier.imgUrl7 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl7) }" class="card-box_img"/>
								<div <c:if test="${pdSupplier.imgUrl7 == '' || pdSupplier.imgUrl7 == null || flag == update}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<input type="hidden" name="imgUrl7" value="${pdSupplier.imgUrl7 }" />
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          	<div class="card-box">
	            <div class="card-box-code">
	            	<label for="">证照名称</label>
	            	<input type="text" name="cardName8" value="${pdSupplier.cardName8 }"/><br />
	            	<label for="">证照号码</label>
	            	<input type="text" name="cardCode8" value="${pdSupplier.cardCode8 }"/><br />
	            	<label for="">有效期至</label>
	            	<input type="text" class="validTime" name="validityTerm8"  value="<fmt:formatDate value="${pdSupplier.validityTerm8 }" pattern="yyyy-MM-dd"/>"/>
	            </div>
	            <div class="showpic">查看大图</div>
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
						    <input type="file" data-code='0' class="upPic" name="imgUrl8Up" style="width: 100%; height: 100%;" multiple/>
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl8 == '' || pdSupplier.imgUrl8 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl8) }" class="card-box_img"/>
								<div <c:if test="${pdSupplier.imgUrl8 == '' || pdSupplier.imgUrl8 == null || update == flag}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<input type="hidden" name="imgUrl8" value="${pdSupplier.imgUrl8 }" />
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          	<div class="card-box">
	            <div class="card-box-code">
	            	<label for="">证照名称</label>
	            	<input type="text" name="cardName9" value="${pdSupplier.cardName9 }"/><br />
	            	<label for="">证照号码</label>
	            	<input type="text" name="cardCode9" value="${pdSupplier.cardCode9 }"/><br />
	            	<label for="">有效期至</label>
	            	<input type="text" class="validTime" name="validityTerm9"  value="<fmt:formatDate value="${pdSupplier.validityTerm9 }" pattern="yyyy-MM-dd"/>"/>
	            </div>
	            <div class="showpic">查看大图</div>
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
						    <input type="file" data-code='0' class="upPic" name="imgUrl9Up" style="width: 100%; height: 100%;" multiple/>
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl9 == '' || pdSupplier.imgUrl9 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl9) }" class="card-box_img"/>
								<div <c:if test="${pdSupplier.imgUrl9 == '' || pdSupplier.imgUrl9 == null || flag == update}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<input type="hidden" name="imgUrl9" value="${pdSupplier.imgUrl9 }" />
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          	<div class="card-box">
	            <div class="card-box-code">
	            	<label for="">证照名称</label>
	            	<input type="text" name="cardName10" value="${pdSupplier.cardName10 }"/><br />
	            	<label for="">证照号码</label>
	            	<input type="text" name="cardCode10" value="${pdSupplier.cardCode10 }"/><br />
	            	<label for="">有效期至</label>
	            	<input type="text" class="validTime" name="validityTerm10"  value="<fmt:formatDate value="${pdSupplier.validityTerm10 }" pattern="yyyy-MM-dd"/>"/>
	            </div>
	            <div class="showpic">查看大图</div>
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
						    <input type="file" data-code='0' class="upPic" name="imgUrl10Up" style="width: 100%; height: 100%;" multiple/>
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl10 == '' || pdSupplier.imgUrl10 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl10) }" class="card-box_img"/>
								<div <c:if test="${pdSupplier.imgUrl10 == '' || pdSupplier.imgUrl10 == null || flag == update}">style="display:none;"</c:if>  class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<input type="hidden" name="imgUrl10" value="${pdSupplier.imgUrl10 }" />
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          	<div class="card-box">
	            <div class="card-box-code">
	            	<label for="">证照名称</label>
	            	<input type="text" name="cardName11" value="${pdSupplier.cardName11 }"/><br />
	            	<label for="">证照号码</label>
	            	<input type="text" name="cardCode11" value="${pdSupplier.cardCode11 }"/><br />
	            	<label for="">有效期至</label>
	            	<input type="text" class="validTime" name="validityTerm11"  value="<fmt:formatDate value="${pdSupplier.validityTerm11 }" pattern="yyyy-MM-dd"/>"/>
	            </div>
	            <div class="showpic">查看大图</div>
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
						    <input type="file" data-code='0' class="upPic" name="imgUrl11Up" style="width: 100%; height: 100%;" multiple/>
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl11 == '' || pdSupplier.imgUrl11 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl11) }" class="card-box_img"/>
								<div <c:if test="${pdSupplier.imgUrl11 == '' || pdSupplier.imgUrl11 == null || flag == update}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<input type="hidden" name="imgUrl11" value="${pdSupplier.imgUrl11 }" />
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          	<div class="card-box">
	            <div class="card-box-code">
	            	<label for="">证照名称</label>
	            	<input type="text" name="cardName12" value="${pdSupplier.cardName12 }"/><br />
	            	<label for="">证照号码</label>
	            	<input type="text" name="cardCode12" value="${pdSupplier.cardCode12 }"/><br />
	            	<label for="">有效期至</label>
	            	<input type="text" class="validTime" name="validityTerm12"  value="<fmt:formatDate value="${pdSupplier.validityTerm12 }" pattern="yyyy-MM-dd"/>"/>
	            </div>
	            <div class="showpic">查看大图</div>
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
						    <input type="file" data-code='0' class="upPic" name="imgUrl12Up" style="width: 100%; height: 100%;" multiple/>
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl12 == '' || pdSupplier.imgUrl12 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl12) }" class="card-box_img"/>
								<div <c:if test="${pdSupplier.imgUrl12 == '' || pdSupplier.imgUrl12 == null || flag == update}">style="display:none;"</c:if>  class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<input type="hidden" name="imgUrl12" value="${pdSupplier.imgUrl12 }" />
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          </div>
		</div>
		
		<div class="form-actions" style="padding: 0;background: #fff;text-align: center; margin-top: 30px; border-top: 0;">
			<c:if test="${flag != 'see' }">
				<input type="submit" onclick="sub()" value="保存" class="hcy-btn hcy-save" />
			</c:if>
			<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
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
	<script src="${ctxStatic}/pic-upload/pic-upload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.js"></script>
	<script src="${ctxStatic}/spd/js/pinying.js"></script>
	<script type="text/javascript">
	$(function(){
        //判断input是否为可输入
        if($("#flag").val() == "see"){
            $("input").attr("disabled","disabled");
        }

		//图片删除
		$(".smallImg_cloBtn").click(function(){
			$(this).parent().next().val("");
		})
		
		//判断input是否为可输入
		if($("#flag").val() == "see"){
			$("input").attr("disabled","disabled");
		}
		
		//输入框获取焦点时出现下拉
		$(".proClassify").click(function(){
			$(this).next().show();
		});
		/*$("input[name='proClassify']").blur(function(){
			$(this).next().hide();
		})*/
		
		//时间插件
		lay('.validTime').each(function(){
		  laydate.render({
		    elem: this
		  });
		}); 
		 //显示图片
	    $(".showpic").click(function(){
            var imgUrl=$(this).parents(".card-box").find(".smallImg img").attr("src");
            if(imgUrl.indexOf(".")!=-1){
                window.open(imgUrl)
            }else{
                layer.alert('请先添加图片', {icon: 0});
            }
		  	/*if($(this).attr("data")=="idcard"){
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
		//遍历添加
		$(".addOption").each(function(index){
			//单击选中项目隐藏下拉
			$(document).on("click",".addOption:eq("+index+")>li:not(:last)",function(){
				$(this).parent().prev().focus();
				$(this).parent().prev().val($(this).text());
				$(this).parent().hide();
			})
			//单击添加项目
			$(this).find("li:last").click(function(){
				var addText=$(this).text().slice(2);
				var title=$(this).text();
				var ind=$(this).parent();
				$(".addSelsctBox>label").text(addText);
				$(this).parent().hide();
				layer.open({
					type:1,
					title:title,
					content:$(".addSelsctBox").html(),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var a = layero.find(".addSelInp").val();
						addData(a);
						layer.closeAll()
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			})
		})
		
		function addData(a){
			var t = $(".addSelsctBox").text();
			if(t.indexOf("产品分类")>0){
				var type = $("#prodType").val();
				if(type == '1'){
					$.ajax({
						url:"${ctx}/pd/pdCategory/saveAjax",
						type:"post",
						data:{name:a,type:"1"},
						dataType:"json",
						success:function(data){
							addCategoryRow(data);
						}
					})
				}else if(type == '0'){
					$.ajax({
						url:"${ctx}/pd/pdCategory/saveAjax",
						type:"post",
						data:{name:a,type:"0"},
						dataType:"json",
						success:function(data){
							addCategoryRow(data);
						}
					})
				}else{
					alert("要先选择一个产品类型 (´•灬•‘)");
				}
			}else{
				$.ajax({
					url:"${ctx}/pd/pdGroup/saveAjax",
					type:"post",
					data:{name:a},
					dataType:"json",
					success:function(data){
						data = eval(data);
						var html='<li>'+data.name+'</li>';
						$("#addGroup").prepend(html);
					}
				})
				//window.location = "${ctx}/pd/pdGroup/save?name="+a;
			}
		}
		
		//ajax的加入一行产品分类
		function addCategoryRow(data){
			var a = eval(data);
			var html='<li>'+a.name+'</li>';
			$("#addCategory").prepend(html);
		}


		$("input[name = 'supplierName']").on("keyup",function(){

			var pinyin = makePy(this.value);
			// $("input[name = 'pinyin']").val(pinyin);
			for(var j=0;j<pinyin.length;j++){
				$("input[name = 'pinyin']").val(pinyin[j],pinyin[j]);
			}
		});
	}) 
			//提交
		function sub(){
			if($("#supplierName").val()!=""){
				var index = loading('正在提交，请稍等...');
				$("#inputForm").attr("action","${ctx}/pd/pdSupplier/update?flag="+$("[name=flag]").val());
				return true;
			}
		}
		</script>
</body>
</html>