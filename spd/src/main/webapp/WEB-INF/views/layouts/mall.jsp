<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<style>
.hcy-loop{width: 100%;height: 450px;margin:0 auto;position:relative;}
.hcy-loop img{width: 100%;height: 450px;}
.hcy-hotProduct{margin: 40px 0;}
.hcy-hotProduct .hotTitle{width: 100%;height: 24px;line-height: 24px;text-align: center;font-size: 20px;color: #666;font-weight: 400;}
.hcy-hotProduct .hotImgList{padding-top: 35px;}
.hcy-hotProduct .hotImgBox{margin-right:10px;float: left;}
.hcy-hotProduct .hotImgBox img{display: inline-block;width: 166px;height: 123px;border:1px solid #ccc;}
.hcy-hotProduct .hotImgBox:last-child{margin-right: 0;}
.hcy-hotProduct .hotImgBox>.hotImgName{padding-top: 20px;}
.hcy-hotProduct .hotImgBox>.hotImgPrice{padding-top: 5px;}
.hcy-hotProduct .hotImgBox>.hotImgName>a{display: inline-block;width: 166px;overflow: hidden;text-overflow:ellipsis;white-space: nowrap; font-size: 12px;color: #666;}
.hcy-hotProduct .hotImgBox>.hotImgPrice>p{font-size: 12px;color: #666;}
.floorList .floorBox{height: 400px;padding:0 30px;}
.floorList .floorBox:nth-child(2n){background: #fff;}
.floorList .floorBox:nth-child(2n+1){background: #f8f8f8;}
.floorList .floorBox .floorTitle{color:#62BD60;font-size: 20px;padding:20px 0;}
.floorList .floorBox .floorTitle>span{font-size: 15px;font-weight: 400;padding-left:10px;}
.floorList .floorBox .imgItem{float: left;margin-right: 30px;}
.floorList .floorBox .imgItem>a>img{width: 320px;height: 250px;border:1px solid #ccc;}
.floorList .floorBox .imgItem:last-child{margin-right: 0;}
.floorList .floorBox .floorImg>a>img{float: left;width: 420px;height: 280px;}
.floorList .floorBox .floorImg .imgText{padding-left: 450px;}
.floorList .floorBox .floorImg .imgText>.imgTextTitle{font-size: 15px;color: #666;font-weight: 400;padding:20px 0;}
.floorList .floorBox .floorImg .imgText>p{line-height: 22px;font-size: 12px;color: #666;}
.floorList .floorBox .imgItem .floorImgName{padding:15px 0 5px 0;font-size: 13px;color:#666;width: 320px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;}
.floorList .floorBox .imgItem>.showPrice>.needLogin,.floorList .floorBox .imgItem>.showPrice>.login{font-size: 12px;color: #666;}
.floorList .floorBox .imgItem>.showPrice>.needLogin>span{color: #999;}
.floorList .floorBox .imgItem>.showPrice>.login>span{color:red;}
#floorNav{position: fixed;top:150px;left: 30px;display: none;margin:0;}
#floorNav li{width: 73px;height: 33px;background: #62BD60;color: #fff;font-size: 12px;text-align: center;line-height: 33px;margin-bottom: 5px;cursor:pointer;}
#floorNav li.active{background: #fff;color:#62BD60;font-weight: 600;border:1px solid #62BD60}
.showProNav{display: none;}
.showProNav ul{width: 204px;height:450px;background: #6e6568;margin:0;}
.showProNav ul li{height: 63px;background: #6e6568;padding-left: 40px;color: #fff;font-size: 14px;line-height: 63px;border-bottom: 1px solid #5C5C5C;}
.showProNav ul li>a{color:#fff;}
.showProNav ul li.on{background: #fff;}
.showProNav ul li.on>a{color: #62BD60;}
.showProItem{padding-left: 204px;height: 450px;width:600px;background:#fff;border:1px solid #ccc;display:none;}
.showProItem.showStyle{background:#fff;border:1px solid #ccc;}
.showProItem>div{width: 540px; font-size: 12px;color: #666;height: 450px;background: #fff;display: none;line-height:30px;padding:10px;}
.showProItem>div>a{padding:0 5px;}
.listBox{height: 450px;margin: 0 auto;position: absolute;z-index:666666;display:none;}
.showListBox{position: absolute;background: #fff;padding: 0 5px;display: none;border:1px solid #ccc;z-index:9999999;left: -1px;width: 80px;text-align: center;border-top: 0;top:33px;}
.myOrderIcon{display: inline-block;width:80px;text-align:center;}
.myOrderIcon.onmouse{background:#fff;border:1px solid #ccc;border-bottom:0;position:relative;border-top:0;width:78px;}
.userShow{display:inline-block;width: 140px;text-align: center;}
.userShow.onUser{background: #fff;position: relative;border: 1px solid #ccc;height:33px;width: 138px;text-align: center;border-top:0;}
.rightFixed{z-index:999;}
.rightFixed>img{padding-top:20px;}
.rightFixed>img:nth-child(1){padding-top:240px;}
#sysCartCount{position: absolute;left: 9px;top: 25px;text-align: center;height: 14px;font-size: 11px;background: red;color: #fff;border-radius:6px;padding: 1px 2px;width: 23px;line-height: 15px;}
</style>
<head>
	<title><sitemesh:title/> - 汇创宜电商平台</title>
	<%@include file="/WEB-INF/views/include/phead.jsp" %>		
	<sitemesh:head/>
</head>
<body>
    <%@include file="/WEB-INF/views/include/menu.jsp" %>	
	<sitemesh:body/>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>