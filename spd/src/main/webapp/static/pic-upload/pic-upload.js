/*---上传图片效果图----*/
$(document).on('click','.smallImg img',function(){
	$(".imgDilogBox").css("display","block");
	$(".bigImgBox img").attr("src",$(this).attr('src')); 
	var imgWidth = $(".bigImgBox img").width();
	var imgHeight = $(".bigImgBox img").height();
	var rWidth=$(document).Width()-300+'px';
	var rHeight=$(window).Height()-300+'px';
	$(".bigImgBox").height(rHeight);
});
$(".close_dalog_x").click(function(){
	$(".imgDilogBox").css("display","none");
});

var  imgUploadSucCallBack,imgUploadClearCallBack;

/*证书上传-函数*/
var imgSrc=null;
//var iMaxFilesize = 1048576;
var iMaxFilesize = 5242880;
$('.upPic').change(function(){
	var $smallImg = $(this).parent().parent().find(".smallImg");
	var html='<img src="" class="card-box_img" /><div class="smallImg_cloBtn"></div>';
	$smallImg.empty().append(html);
	$(this).next().next().hide();
    var oFile = $(this).get(0).files[0];
    var rFilter = /^(image\/bmp|image\/gif|image\/jpeg|image\/png|image\/tiff)$/i;
  	if (oFile && !rFilter.test(oFile.type)) {   
    	alert('上传文件非图片格式');
        return;
    }
    if (oFile && oFile.size > iMaxFilesize) {
    	alert('上传图片不能超过5M');
        return;
    }
    var oReader = new FileReader();
    oReader.onload = function(e){
		$smallImg.last().find('img').attr('src',e.target.result);
      	$smallImg.css('display','block');
    };
    oReader.readAsDataURL(oFile);	
    //图片上传完成回调
    if(imgUploadSucCallBack &&  typeof(imgUploadSucCallBack)== "function"){
      imgUploadSucCallBack($(this));
    }
});
$(document).on('click','.smallImg_cloBtn',function(){
	$(this).parents(".tR_upPic_icon").find(".addIcon").show();
	$(this).parent().html("");
	$("#" + $(this).data("hiddenid")).val("");
	 //图片清空完成回调
    if(imgUploadClearCallBack &&  typeof(imgUploadClearCallBack)== "function"){
      imgUploadClearCallBack($(this));
    }
});