图片上传form页面需要引用的js和css提取出来，以便公共使用。
<div class="control-group">
	<label class="control-label">选择图片：</label>
	<div class="controls">
		<form:hidden path="$[hiddenImgUrlInputName]" />
		<div class='triageAdvUpPic'>
			<span class='tR_upPic_icon'>
				<input type="file" data-code='0' class='upPic' name="$[image]"/>
			</span>
			<div class="smallImg" style='display:block'>
				<img src="${ctxImg}${replaceOBJECT.imageUrl}" alt="${replaceOBJECT.name}"/>
				<div class="smallImg_cloBtn" data-hiddenid="$[hiddenImgUrlInputName]"></div>
			</div>
		</div>
	</div>
</div>

如何使用图片上传.
1:引用css 和 js文件
<link rel="stylesheet" href="${ctxStatic}/pic-upload/pic-upload.css?version=${version}"/>
<script type="text/javascript" src="${ctxStatic}/pic-upload/pic-upload.js?version=${version}"></script>

2：给form添加属性和值		enctype="multipart/form-data"

3: 给controller.save方法添加属性 @RequestParam(required=false) MultipartFile $[image],

$[hiddenImgUrlInputName]为实体对象的imageUrl的名称 例如，User.photo对象，这里的photo
$[image]为(<input type="file" name="$[image]" >)这里的name