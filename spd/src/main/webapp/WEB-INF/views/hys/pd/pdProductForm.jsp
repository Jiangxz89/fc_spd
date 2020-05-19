<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta http-equiv="X-UA-Compatible" content="ie=edge" />
		<link rel="stylesheet" href="${ctxStatic}/spd/css/base.css" />
		<link rel="stylesheet" href="${ctxStatic}/spd/css/addProduct.css" />
		<link rel="stylesheet" href="${ctxStatic}/spd/css/font-awesome.css" />
		<link rel="stylesheet" href="${ctxStatic}/pic-upload/pic-upload.css" />
		<style>
			#showPic  img{display:inline-block; width:512px;height:320px;margin-top:30px;}
			.showpic{cursor:pointer}
			.areaTxtBox{display: inline-block; border: 1px solid #ccc;padding: 5px; border-radius: 2px;}
			.addProductBox select option{color:#666}
		</style>
		<title>添加产品</title>
	<meta name="decorator" content="default"/>
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
	<form:form id="inputForm" modelAttribute="pdProduct" enctype="multipart/form-data" method="post">
		<input type="hidden" name="flag" value="${flag }" />
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="right-main-box">
			<div class="btnBox" style="border-bottom:1px solid #ccc;height:36px;">
				<h4 style="padding-left:0;">添加产品</h4>
			</div>
			<div class="addProductBox">
				<label for=""><span class="mustIcon">*</span>产品编号</label>
				<input type="text" name="number" autocomplete="off" required id="number" value=""  class="input-xlarge"/>
		<!-- 	<a href="###" onclick="scan()" class="hcy-btn hcy-btn-primary">扫描</a>   -->
				<a href="###" onclick="generateNumber()" class="hcy-btn hcy-btn-primary">生成</a>
				<%--<a href="###" class="hcy-btn hcy-btn-primary" id="codePrint">条码打印</a>2019年4月18日11:01:28 条码添加时不打印--%><br />
				<label for=""><span class="mustIcon">*</span>产品名称</label>
				<input type="text" name="name" id="proInp" autocomplete="off" onKeyUp="query()"  required="required" class="input-xlarge"/>
				<label for="">首拼码</label>
				<input type="text" id="proSpm" name="spm" autocomplete="off" class="input-xlarge"/><br />

				<label for="">产品别名</label> <!-- add  by jiangxz 20190927-->
				<input type="text" name="otherName" id="otherInp" autocomplete="off" onKeyUp="queryOther()"  class="input-xlarge"/>
				<label for="">别名首拼码</label>                              <!-- add  by jiangxz 20190927-->
				<input type="text" id="otherSpm" name="otherSpm" autocomplete="off" class="input-xlarge"/><br />

				<label for=""><span class="mustIcon"  >*</span>规格</label>
				<input type="text" name="spec" required autocomplete="off" class="input-xlarge"/>

				<label for="">型号</label> <!--modified by jiangxz 20190906 丰城人民医院需求，型号非必填-->
				<input type="text" name="version"   autocomplete="off" class="input-xlarge"/><br />

				<label for=""><span class="mustIcon">*</span>单位</label>
				<%--<input type="text" name="unit"  required class="input-xlarge"/>--%>
				<input type="hidden" id="unit" name="unit" value="" />
				<select id="unitSelect" value="${pdProduct.id }" name="unitSelect" class="input-xlarge" required style="width: 260px;">
					<option value="">--请选择--</option>
					<c:forEach var="pdUnit" items="${pdUnitList}">
						<option value="${pdUnit.id}">${pdUnit.name}</option>
					</c:forEach>
				</select>
				<label for=""><span class="mustIcon">*</span>是否计费</label>
				<form:select path="isCharge" style="width: 260px;">
					<form:option value="0">否</form:option>
					<form:option value="1">是</form:option>
				</form:select>
				<br />
                    <%--modified by jiangxz 20190927 丰城人民医院需求 产品类型取消关联产品分类--%>
                <label for=""><span class="mustIcon">*</span>产品分类</label>
                <input type="hidden" id="categoryName" name="categoryName" value="" />
                <form:select name="categoryId" path="categoryId" class="required input-xlarge" style="width: 260px;">
                    <form:option value="">--请选择--</form:option>
                    <form:options items="${spd:findPdCategoryList()}" itemLabel="name" itemValue="id"/>
                </form:select>

				<label for=""><span class="mustIcon">*</span>产品类型</label>
				<%--<select name="type" id="prodType" class="input-xlarge"  required onchange="typeChange()" style="width: 260px;">--%>
                    <%--<option id="" onclick="typeChange()" value="">--请选择--</option>--%>
                <%--modified by jiangxz 20190927 丰城人民医院需求 产品类型取消关联产品分类--%>
                <select name="type" id="prodType" class="input-xlarge"  required style="width: 260px;">
					<option id="" value="">--请选择--</option>
					<c:forEach var="dict" items="${fns:getDictList('product_type') }">
						<option value="${dict.label eq '高耗值型'?'1':'0'}">${dict.label }</option>
					</c:forEach>
				</select>
				<br />
				<label for=""><span class="mustIcon">*</span>生产厂家</label>
				<input type="hidden" id="venderName" name="venderName" value="" />
				<select id="venderId" value="${pdProduct.id }" name="venderId" class="input-xlarge" required style="width: 260px;">
					<option value="">--请选择--</option>
					<c:forEach var="pdVender" items="${pdVenderList}">
						<option value="${pdVender.id}" <c:if test="${pdVender.name eq pdProduct.venderName } ">selected="selected"</c:if>>${pdVender.nameAndpinyin}</option>
					</c:forEach>
				</select>
				<label for=""><span class="mustIcon">*</span>供应商</label>
				<form:select path="supplierId" class="required input-xlarge" style="width: 260px;">
					<form:option value="">--请选择--</form:option>
					<form:options items="${spd:findSupplierList()}" itemLabel="nameAndpinyin" itemValue="id"/>
				</form:select>
				<br />
				<div class="addSelect">
					<label for=""><span class="mustIcon">*</span>产品组别</label>
					<input type="hidden" name="groupId" class="input-xlarge" />
					<input type="text" name="groupName"  readonly  required autocomplete="off" placeholder="--请选择--" class="proClassify input-xlarge"/>
					<ul id="addGroup" class="addOption">
						<c:forEach var="pdGroup" items="${pdGroupList}">
							<li>${spd:getGroupName(pdGroup.id)}</li><input type="hidden" value="${pdGroup.id }">
						</c:forEach>
						<li><i class="fa fa-plus"></i>新增产品组别</li>
					</ul>
				</div>
				<%--<div class="addSelect">--%>
					<%--<label for=""><span class="mustIcon">*</span>产品分类</label>--%>
					<%--<input type="hidden" name="categoryId" />--%>
					<%--<input type="text" name="categoryName"  readonly required  autocomplete="off" class="proClassify input-xlarge" placeholder="--请选择--"/>--%>
					<%--<ul id="addCategory" class="addOption">--%>
						<%--<li><i class="fa fa-plus"></i>新增产品分类</li>--%>
					<%--</ul>--%>
				<%--</div>--%>
                <label for=""><span class="mustIcon">*</span>产品权限</label>
                <select id="power" name="power" class="input-xlarge" required style="width: 260px;">
                    <c:forEach var="dict" items="${fns:getDictList('product_power') }">
                        <option value="${dict.label eq '公共产品'?'0':'1' }">${dict.label }</option>
                    </c:forEach>
                </select>

				<c:if test="${flag == 'urgent' ||isUrgent == 'urgent'}">
					<br />
					<label for=""><span class="mustIcon">*</span><span style="color:red">紧急采购量</span></label>
					<input type="text" name="urgentPurCount" value="" required class="input-xlarge"/>
					<label for=""><span class="mustIcon">*</span><span style="color:red">剩余采购量</span></label>
					<input type="text" name="surplusPurCount" value="" required readonly="readonly" class="input-xlarge"/>
					<input type="button" value="重置" />
					<br />
				</c:if>
				<br />
				<label for=""><span class="mustIcon">*</span>产品进价(元)</label>
				<input type="text" name="pruPrice" autocomplete="off" required class="input-xlarge" onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,4})?).*$/g, '$1')"/>
				 <label for="">税率加成(%)</label>
				<input type="text" name="addRate" required class="input-xlarge" onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"/>
                <br/>
				<%--modified by jiangxz 20190906 丰城人民医院需求，产品出价非必填--%>
				<label for="">产品出价(元)</label>
				<input type="text" name="sellingPrice" autocomplete="off" class="input-xlarge" onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,4})?).*$/g, '$1')"/>
                <%--<br/>--%>
                <label for="">编码规则</label>
				<input type="text" id="encodingRuleName" disabled="disabled" class="input-xlarge"/>
				<input type="hidden" id="encodingRule" name="encodingRule" />
				<a class="hcy-btn hcy-btn-primary" id="addEncodingRule">选择编码规则</a>
				<a class="hcy-btn hcy-btn-primary" id="deEncodingRule">删除编码规则</a>
				</br>
				<%--modified by jiangxz 20190906 丰城人民医院需求，注册证非必填--%>
				<label for="">注册证</label>
				<input type="text" style="width:632px;" autocomplete="off" name="registration" /><label class="autoLab">多个注册证以“；”分开</label><br />

				<label for="" style="vertical-align:top;">备注</label>
				<div class="areaTxtBox">
					<textarea class="spdRemarks" autocomplete="off" name="description" style='margin: 0;display: block;border: none;box-shadow: none' maxlength="200" ></textarea>
					<label style="text-align: right;display: block;color: #666;" class="autoLab"><span class="keyUpNum">0</span>/200</label>
				</div><br />
				<label style="padding-top:15px;float:left">产品三证扫描件</label>
				<div class="cardBoxWrap" style="padding-left:105px;">
	          		<div class="card-box">
	          			<div class="card-box-top">
				            <h4 class="fl">产品授权书</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgAuthNumber"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgAuthDate" />
			            </div>
			            <div class="controls">
			            	<input type="text" class="validate_hidden" value=""/>
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgAuthSiteUp" style="width: 100%; height: 100%;" multiple/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">产品注册证1</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgRegisterNumber1"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgRegisterDate1"/>
			            </div>
			            <div class="controls"> 
			            	<input type="text" class="validate_hidden" value=""/>
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgRegisterSite1Up" style="width: 100%; height: 100%;" multiple/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">产品注册证2</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgRegisterNumber2"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgRegisterDate2"/>
			            </div>
			            <div class="controls"> 
			            	<input type="text" class="validate_hidden" value=""/>
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgRegisterSite2Up" style="width: 100%; height: 100%;" multiple/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">产品注册证3</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgRegisterNumber3"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgRegisterDate3"/>
			            </div>
			            <div class="controls"> 
			            	<input type="text" class="validate_hidden" value=""/>
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgRegisterSite3Up" style="width: 100%; height: 100%;" multiple/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">供应商营业执照</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgLicenseNumber1"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgLicenseDate1"/>
			            </div>
			            <div class="controls"> 
			            	<input type="text" class="validate_hidden" value=""/>
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgLicenseSite1Up" style="width: 100%; height: 100%;" multiple/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">供应商经营许可证</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgLicenseNumber2"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgLicenseDate2"/>
			            </div>
			            <div class="controls"> 
			            	<input type="text" class="validate_hidden" value=""/>
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgLicenseSite2Up" style="width: 100%; height: 100%;" multiple/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">生产经营企业执照</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgLicenseNumber3"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgLicenseDate3"/>
			            </div>
			            <div class="controls"> 
			            	<input type="text" class="validate_hidden" value=""/>
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgLicenseSite3Up" style="width: 100%; height: 100%;" multiple="multiple"/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">生产企业生产许可证</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgLicenseNumber4"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgLicenseDate4"/>
			            </div>
			            <div class="controls"> 
			            	<input type="text" class="validate_hidden" value="">
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgLicenseSite4Up" style="width: 100%; height: 100%;" multiple="multiple"/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
          		</div>
          		<label  style="padding-top:15px;float:left">产品照片</label>
          		<div class="cardBoxWrap" style="padding-left:105px;">
	          		<div class="card-box" style="height:208px;">
	          			<div class="controls"> 
			            	<input type="text" class="validate_hidden" value=""/>
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgProduct1Up" style="width: 100%; height: 100%;" multiple="multiple"/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
						<div style="text-align:center;line-height: 40px;font-size:13px;color:#666;">正面</div>
	          		</div>
	          		<div class="card-box" style="height:208px;">
	          			<div class="controls"> 
			            	<input type="text" class="validate_hidden" value=""/>
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgProduct2Up" style="width: 100%; height: 100%;" multiple="multiple"/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
						<div style="text-align:center;line-height: 40px;font-size:13px;color:#666;">背面</div>
	          		</div>
	          		<div class="card-box" style="height:208px;">
	          			<div class="controls"> 
			            	<input type="text" class="validate_hidden" value=""/>
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgProduct3Up" style="width: 100%; height: 100%;" multiple="multiple"/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
						<div style="text-align:center;line-height: 40px;font-size:13px;color:#666;">侧面</div>
	          		</div>
	          	</div>
          		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
          			<input type="submit" onclick="sub()" value="保存" class="hcy-btn hcy-save" />
          			<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
          		</div>
			</div>
		</div>
		<div class="addSelsctBox">
			<label class="addSelLab" style="padding-left:30px;"></label>
			<input type="text" style="width:160px;height:30px;margin:0;" class="addSelInp" id="addselInp1" value="" />
		</div>
		<div id="codePrintBox" class="codePrintBox" style="display:none;">
			<div><h5>厂商：</h5><span class="venderNameSpan">美敦力（上海）管理有限公司</span></div>
			<div><h5>产品名称：</h5><span class="proNameSpan">冠状动脉球囊扩张导管</span></div>
			<div class="specLine"><h5>规格：</h5><span class="specSpan">2.00mm*15mm</span></div>
			<div class="versionLine"><h5>型号：</h5><span class="versionSpan">LA20015</span></div>
			<div><img src = "" id="barcodeImg" /></div>
		</div>
		<input id="isUrgent" name="isUrgent" type="hidden" value="${flag == 'urgent' ? 'a1' : 'a0'}"/>
	</form:form>
	<div id="showPic" style="display:none;">
		<img alt="" src=""  style="">
		<div style="display: none;">
		<p></p>
		<img alt="" src=""  height="300" width="200" >
		</div>
	 </div>
	    <%--<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
		<script src="${ctxStatic}/spd/js/layer.js"></script>
		<script src="${ctxStatic}/spd/laydate/laydate.js"></script>
		<script src="${ctxStatic}/pic-upload/pic-upload.js"></script>
		<script src="${ctxStatic}/spd/js/pinying.js"></script>
		<!-- <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script> -->
		<script src="${ctxStatic}/spd/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="${ctxStatic}/spd/js/jquery.jqprint-0.3.js"></script>
		<script src="${ctxStatic}/spd/js/barcode.js"></script>
		<script type="text/javascript">
			$(function(){
                // add by jiangxz 20190927 初始化产品分类
                // initCategorySelect();

				//禁用Enter键表单自动提交
				document.onkeydown = function(event) {
					var target, code, tag;
					if (!event) {
						event = window.event; //针对ie浏览器
						target = event.srcElement;
						code = event.keyCode;
						if (code == 13) {
							tag = target.tagName;
							if (tag == "TEXTAREA") { return true; }
							else { return false; }
						}
					}
					else {
						target = event.target; //针对遵循w3c标准的浏览器，如Firefox
						code = event.keyCode;
						if (code == 13) {
							tag = target.tagName;
							if (tag == "INPUT") { return false; }
							else { return true; }
						}
					}
				};

				//定位光标
				$("#number").focus();
				
				//输入框获取焦点时出现下拉
				// $(".proClassify").click(function(){
				// 	$(this).next().show();
				// });
				$("input[name='categoryName']").click(function(){
					$("#addCategory").show();
				});
				$("input[name='groupName']").click(function(){
					$("#addGroup").show();
				});


				//条形码解析
				$("#number").bind('change',function(){
					var no = $("#number").val();
					if(no.substring(0,2)!='93'){
						var coded = no;
						getPrdNumber(no);
						$("#number").val(upn);
						//$("#codePrint").hide();2019年4月18日11:01:15
					}
					
				});
				//点击空白处隐藏
				 window.onload = function(){
		            var body = document.getElementsByTagName('body')[0];
		            body.onclick = function(e){
		                e = e || window.event;
		                var target = e.target || e.srcElement;
		                var tables = document.getElementsByClassName('proClassify');
		                // if(target.className != 'proClassify') {
						if(target.className.indexOf("proClassify")==-1){
		                    $(".addOption").hide();
		                }
		            }
		        }

				//图片删除
				$(".smallImg_cloBtn").click(function(){
					
				})
				
				//备注限制字数
				$(".spdRemarks").keyup(function(){
					var len=$(this).val().length;
					$(".keyUpNum").text(len);
				})

				$("#addGroup").each(function(index){
					//单击选中项目隐藏下拉
					$(document).on("click","#addGroup:eq("+index+")>li:not(:last)",function(){
						$("input[name='groupName']").focus();
						$("input[name='groupName']").val($(this).text());
						$("input[name='groupId']").val($(this).next().val());
						$("#addGroup").hide();

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
								layer.closeAll();
							}
						})
					})
				});
				//遍历添加

			// <input type="hidden" name="categoryId" />
			// 			<input type="text" name="categoryName"  readonly required  autocomplete="off" class="proClassify input-xlarge" placeholder="--请选择--"/>
			// 			<ul id="addCategory" class="addOption">
				$("#addCategory").each(function(index){
					//单击选中项目隐藏下拉
					$(document).on("click","#addCategory:eq("+index+")>li:not(:last)",function(){
						// $(this).parent().prev().focus();
						// $(this).parent().prev().val($(this).text());
						// $(this).parent().prev().prev().val($(this).next().val());
						// $(this).parent().hide();
						$("input[name='categoryName']").focus();
						$("input[name='categoryName']").val($(this).text());
						$("input[name='categoryId']").val($(this).next().val());
						$("#addCategory").hide();

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
								layer.closeAll();
							}
						})
					})
				});
				 //显示图片
			    $(".showpic").click(function(){
			    	var imgUrl=$(this).parents(".card-box").find(".smallImg img").attr("src");
			    	if(imgUrl == "" ){
			    		layer.alert('请先添加图片', {icon: 0});
			    		return false;
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
				   		});
				   	});
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
								var json = eval(data);
								if(json.statusCode=="200"){
									var html='<li>'+json.name+'</li><input type="hidden" value="'+json.id+'" />';
									$("#addGroup").prepend(html);	
									
								}
								layer.alert(json.msg);
							}
						})
					}
				}
				
				//ajax的加入一行产品分类
				function addCategoryRow(data){
					var json = eval(data);
					if(json.statusCode=="200"){
						var html='<li>'+json.name+'</li><input type="hidden" value="'+json.id+'" />';
						$("#addCategory").prepend(html);	
					}
					layer.alert(json.msg);
				}
				
				
				//时间插件
				lay('.validTime').each(function(){
				  laydate.render({
				    elem: this
				  });
				}); 
				//条码打印
				$("#codePrint").click(function(){
					//判断对应数据是否填完整
					var number=$("input[name='number']").val();//产品编号
					var venderName=$("select[name='venderId']>option:checked").text();//生产厂家
					var proName=$("input[name='name']").val();//产品名称
					var spec=$("input[name='spec']").val();//规格
					var version=$("input[name='version']").val();//型号
					if(number=="" || number==undefined || number==null){
						layer.alert("请生成产品编号！",{icon:0},function(index){
							layer.close(index);
						});
						return false;
					}
					if(venderName=="" || venderName==undefined || venderName==null){
						layer.alert("请选择生产厂家！",{icon:0},function(index){
							layer.close(index);
						});
						return false;
					}
					if(proName=="" || proName==undefined || proName==null){
						layer.alert("请填写产品名称！",{icon:0},function(index){
							layer.close(index);
						});
						return false;
					}
					if( (spec=="" && version=="")  || (spec==undefined && version==undefined)|| (spec==null && version==null )){
						layer.alert("请填写规格或型号！",{icon:0},function(index){
							layer.close(index);
						});
						return false;
					}
					if( (spec=="" && version!="")  || (spec==undefined && version!=undefined)|| (spec==null && version!=null )){
						$(".specLine").hide();
						$(".versionSpan").text(version);
					}
					if( (spec!="" && version=="")  || (spec!=undefined && version==undefined)|| (spec!=null && version==null )){
						$(".version").hide();
						$(".specSpan").text(spec);
					}
					//将选中、填写的赋值到弹框中
					$(".venderNameSpan").text(venderName);
					$(".proNameSpan").text(proName);
					$(".specSpan").text(spec);
					$(".versionSpan").text(version);
					layer.open({
						type:1,
						title:"条码打印",
						content:$(".codePrintBox").html(),
						area:["500px","400px"],
						shade: [0.8, '#393D49'],
						btn:["打印"],
						yes:function(index,layero){
							printFn();
							layer.closeAll();
						}
					})
					
				})
				//紧急采购量关联
				$("[name=urgentPurCount]").change(function(){
					var val = $("[name=urgentPurCount]").val();
					$("[name=surplusPurCount]").val(val);
				})
				
				//生产厂家改变
				$("[name=venderId]").change(function(){
					var name = $("[name=venderId]>option:checked").text();
					var id = $("[name=venderId]>option:checked").val();
					$("[name=venderName]").val(name);
					$("[name=venderId]").val(id);
				})
				$("[name=unitSelect]").change(function(){
					var name = $("[name=unitSelect]>option:checked").text();
					// var id = $("[name=unitSelect]>option:checked").val();
					$("[name=unit]").val(name);
				})

                // add by jiangxz 20190927 选中产品分类
                $("[name=categoryId]").change(function(){
                    var name = $("[name=categoryId]>option:checked").text();
                    $("[name=categoryName]").val(name);
                })

				//产品编号失去焦点事件
				$("#number").on("blur",function(){
					var number = this.value;
					if(number){
						$.ajax({
							url:'${ctx}/pd/pdProduct/eliminateRepetitive',
							type:'post',
							data:{"number":number},
							dataType:'json',
							success:function(data){
								if(data.code=="500"){
									$("#number").val("");
									layer.alert(data.message);
								}
							}
						})
					}
				})

				 $("[name=pruPrice]").on("keyup",function(){
					var pruPrice = $("[name=pruPrice]").val();
					var addRate = $("[name=addRate]").val();
					if(pruPrice!="" && addRate!="" && addRate!=0){
						var sellingPrice = Number(pruPrice)+Number(pruPrice)*(Number(addRate)/100);
						$("[name=sellingPrice]").val(Number(sellingPrice).toFixed(4));
					}
				})

				$("[name=addRate]").on("keyup",function(){
					var pruPrice = $("[name=pruPrice]").val();
					var addRate = $("[name=addRate]").val();
					if(pruPrice!="" && addRate!="" && addRate!=0){
						var sellingPrice = Number(pruPrice)+Number(pruPrice)*(Number(addRate)/100);
						$("[name=sellingPrice]").val(Number(sellingPrice).toFixed(4));
					}
				})

			})
			//document.ready end
			
			
			//打印
			function printFn(){
				$(".codePrintBox").removeAttr("style");
				$(".codePrintBox").jqprint();	
				$(".codePrintBox").attr("style","display:none;");
	//			var bdhtml = document.body.innerHTML;      
	//	        var sprnstr = "<!--startprint-->";      
	//	        var eprnstr = "<!--endprint-->";      
	//	        var prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr)+17);      
	//	        prnhtml = prnhtml.substring(0,prnhtml.indexOf(eprnstr));      
	//	        document.body.innerHTML = prnhtml;   
	//	        window.print();    
	//			document.body.innerHTML = bdhtml;
			}
			
			//生成编号
			function generateNumber(){
				$.ajax({
					type:"POST",
					url:"${ctx}/pd/pdProduct/generateNumber",
					dataType:"json",
					success:function(data){
						//$("#codePrint").show();2019年4月18日11:01:15
						var data = eval(data);
						//var site = (data.site).substring(0);2019年4月18日17:06:58从前台生成图片
						$("#number").val(data.number);
					//	$("#barcodeImg").attr("src","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523855034892&di=545d64fce923a20295b472d8bc90735c&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F0eb30f2442a7d933b1feb756a74bd11373f00144.jpg");
					//	$("#barcodeImg").attr("src","");
						//$("#barcodeImg").attr("src","${ctxImg}"+site);2019年4月18日17:06:58从前台生成图片
						$(".codePrintBox").removeAttr("style");
						$(".codePrintBox").attr("style","display:none;");
					}
				})
			}
			//类型改变
			function typeChange(){
				$.ajax({
					type:"POST",
					url:"${ctx}/pd/pdProduct/categoryList",
					dataType:"json",
					success:function(data){
						categoryChange(data);
					}
				})
			}

			//改变分类列表
			function categoryChange(data){
				data = eval(data);
				var html = '';
				var type = $("#prodType").val();
				$("#addCategory>li:not(:last)").empty();
				if(type == '1'){
					for(var i = 0 ; i < data.length ; i ++){
						if(data[i].type == '1'){
							html += '<li>'+data[i].name+'</li><input type="hidden" value="'+data[i].id+'" />'
						}
					}
				}else if(type == '0'){
					for(var i = 0 ; i < data.length ; i ++){
						if(data[i].type == '0'){
							html += '<li>'+data[i].name+'</li><input type="hidden" value="'+data[i].id+'" />'
						}
					}
				}
				//html += '<li><i class="fa fa-plus"></i>新增产品分类</li>';
				$("#addCategory").prepend(html);
			}

            // add by jiangxz 20190927 初始化产品分类
            function initCategorySelect(){
                $.ajax({
                    type:"POST",
                    url:"${ctx}/pd/pdProduct/categoryList",
                    dataType:"json",
                    success:function(data){
                        data = eval(data);
                        var html = '';
                        var type = $("#prodType").val();
                        $("#addCategory>li:not(:last)").empty();

                        for(var i = 0 ; i < data.length ; i ++){
                            html += '<li>'+data[i].name+'</li><input type="hidden" value="'+data[i].id+'" />'
                        }

                        //html += '<li><i class="fa fa-plus"></i>新增产品分类</li>';
                        $("#addCategory").prepend(html);
                    }
                })
            }


			//扫描开放
			/* function scan(){
				document.getElementById("number").focus();
				$("#number").removeAttr("onkeypress");
			} */
			
			//提交
			function sub(){
				$("#inputForm").attr("action","${ctx}/pd/pdProduct/save?flag="+$("[name=flag]").val());
				return true;
			}
			//首拼码
			function query(){
			    var str = document.getElementById("proInp").value.trim();
			    if(str == "") return;
			    var arrRslt = makePy(str);
			    //循环将值到下拉框
			    var option = null;
			    document.getElementById("proSpm").innerHTML="";//清空下拉框
			    var first = document.getElementById("proSpm");
			    for(var j=0;j<arrRslt.length;j++){
							var obj = document.getElementById("proSpm");
							obj.value=arrRslt[j],arrRslt[j];
			    }
			}


            //别名首拼码  add by jiangxz 20190927
            function queryOther(){
                var str = document.getElementById("otherInp").value.trim();
                if(str == "") return;
                var arrRslt = makePy(str);
                //循环将值到下拉框
                var option = null;
                document.getElementById("otherSpm").innerHTML="";//清空下拉框
                var first = document.getElementById("otherSpm");
                for(var j=0;j<arrRslt.length;j++){
                    var obj = document.getElementById("otherSpm");
                    obj.value=arrRslt[j],arrRslt[j];
                }
            }

			//选择编码规则
			$("#addEncodingRule").click(function(){
				layer.open({
					type:2,
					title:"添加标识符",
					content:'${ctx}/pd/pdEncodingRule/getList',
					area:["965px","527px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var childWindow = layero.find('iframe')[0].contentWindow;
						var result = childWindow.compositeHtml();
						if(result === 0){
							layer.alert('请选择应用标识符',{icon: 0});
						}else{
							$("#encodingRuleName").val(result.encodingRuleName);
							$("#encodingRule").val(result.encodingRule);
							layer.close(index);
						}
					},
					btn2:function(){
						layer.closeAll();
					}
				})
			});
			//清空编码规则
			$("#deEncodingRule").click(function(){
				$("#encodingRuleName").val("");
				$("#encodingRule").val("");
			});

		</script>
</body>
</html>