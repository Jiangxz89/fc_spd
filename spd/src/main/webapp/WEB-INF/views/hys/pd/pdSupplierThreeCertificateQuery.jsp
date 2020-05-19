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
		.showBigPic{height: 20px;line-height: 20px; text-align: right; padding-right: 20px;font-size: 13px;color: #00CFA5; padding-top: 4px;cursor: pointer;}
		.card-box{height: 320px;}
		.card-box-code{margin-top:10px;}
		.form-horizontal .control-label{font-size: 13px; color: #666; width: 100px;text-align: left;height: 30px;line-height: 30px;padding: 0}
		.smallImg{z-index:6;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
	
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
	<form:form id="inputForm" style="padding-top:20px;" modelAttribute="pdSupplier" enctype="multipart/form-data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group" style="padding-bottom:20px;">
			<label class="control-label">供应商名称：</label>
			<div class="controls" style="margin:0;">
				<input type="hidden" id="flag" name="flag" value="${flag }" />
				<form:input path="supplierName" htmlEscape="false" required="required" value="${pdSupplier.supplierName }" style="height: 30px;border-radius: 2px;padding: 0 0 0 5px;" maxlength="100" class="input-xlarge required"/>
			</div>
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
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl1 == '' || pdSupplier.imgUrl1 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl1) }" class="card-box_img"/>
								<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
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
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl2 == '' || pdSupplier.imgUrl2 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl2) }" class="card-box_img"/>
								<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
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
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl3 == '' || pdSupplier.imgUrl3 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl3) }" class="card-box_img"/>
								<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
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
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl4 == '' || pdSupplier.imgUrl4 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl4) }" class="card-box_img"/>
								<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
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
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl5 == '' || pdSupplier.imgUrl5 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl5) }" class="card-box_img"/>
								<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
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
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl6 == '' || pdSupplier.imgUrl6 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl6) }" class="card-box_img"/>
								<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
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
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl7 == '' || pdSupplier.imgUrl7 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl7) }" class="card-box_img"/>
								<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
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
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl8 == '' || pdSupplier.imgUrl8 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl8) }" class="card-box_img"/>
								<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
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
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl9 == '' || pdSupplier.imgUrl9 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl9) }" class="card-box_img"/>
								<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
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
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl10 == '' || pdSupplier.imgUrl10 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl10) }" class="card-box_img"/>
								<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
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
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl11 == '' || pdSupplier.imgUrl11 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl11) }" class="card-box_img"/>
								<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
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
	           <div class="controls" style="margin-left:0;"> 
		            <div class='pictureUploadDiv'>
						<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
							<div class="smallImg" style='display:block;width:256px;height:160px;' >
								<img <c:if test="${pdSupplier.imgUrl12 == '' || pdSupplier.imgUrl12 == null}">style="display:none;"</c:if> src="${ctxImg.concat(pdSupplier.imgUrl12) }" class="card-box_img"/>
								<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
							</div>
							<span class="addIcon">+</span>
						</div>
					</div>
				</div>
          	</div>
          </div>
		</div>
		
		<div class="form-actions" style="padding: 0;background: #fff;text-align: center; margin-top: 30px; border-top: 0;">
			<!--  <a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a> -->
		</div>
	</form:form>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd/js/layer.js"></script>
	<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
	<script src="${ctxStatic}/pic-upload/pic-upload.js"></script>
	<script src="${ctxStatic}/spd/js/pinying.js"></script>
	<script type="text/javascript">
	$(function(){
		$("input").attr("disabled","disabled");
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
				
		//ajax的加入一行产品分类
		function addCategoryRow(data){
			var a = eval(data);
			var html='<li>'+a.name+'</li>';
			$("#addCategory").prepend(html);
		}
	}) 
		</script>
</body>
</html>