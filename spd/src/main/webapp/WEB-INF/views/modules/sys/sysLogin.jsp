1<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html style="overflow:hidden;">
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<link rel="stylesheet" href="${ctxStatic}/hcy/css/font-awesome.css">
	<meta name="decorator" content="blank"/>
	<style type="text/css">
      html,body,table{background-color:#fff;width:100%;text-align:center;overfolw:hidden;!important}
      .form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#0663a2;}
      .form-signin{position:relative;text-align:left;width:300px;padding:25px 29px 29px;margin:0 auto 20px;}
      .form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#999;margin-bottom:5px;}
      .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
      .form-signin .btn.btn-large{font-size:16px;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}
      .form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
      .header{height:80px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
      .loginBox{position:relative;width:100%;height:450px;position:absolute;left:50%;top:50%;margin-left:-50%;margin-top:-225px;background:#22A7FF;/* background:url(${ctxStatic}/spd/img/login_bg.png);background-size:cover; */}
      .loginBox>img{display:block; width:100%;height:267px;}
      .userNameBox,.passwordBox,.validateCode{width:328px;height:63px;border:none;background:#fff;clear:both;overflow:hidden;margin:20px 0 0 0;}
      .userNameBox>input#username,.passwordBox>input#password{width:100%;float:left;height:38px;padding:0 0 0 13px;border:1px solid #ccc;outline:none;background:#fff;border-radius:none;box-shadow:none;margin:0;font-size:13px;}
      .userNameBox>label,.passwordBox>label,.validateCode>label{font-size:14px;color:#666;padding-bottom:3px;}
      .validateCode{background:none;margin-bottom:10px;}
      .codeBox{width:155px;height:38px;float:left;margin-right:18px;}
      .codeBox>input[type='text']{width:140px;height:36px;border:1px solid #ccc;padding:0 0 0 13px;margin:0;box-shadow:none;outline:none;border-radius:5px;}
      #codeVImg{width:100%;height:100%;margin:0;}
      #loginSubmit{width:330px;height:40px;border:none;outline:none;background:#22A7FF;font-size:14px;color:#fff;border-radius:5px;padding:0;text-shadow: none}
      .loginTit{font-size:26px;color:#333;line-height:40px;display:inline-block;margin-left:300px;font-weight:400;}
      .topTitle{width:100%;position:absolute;top:-60px;text-align:left;}
      .letterTit{display:inline-block;color:#63BE61;font-size:25px;margin-right:20px;}
      .otherLatter{display:inline-block;color:#63BE61;font-size:25px;color:#1DAEE5;}
      .bottomBox{position:fixed;bottom:30px;width:100%;color:#fff;text-align:center;}
      label.error{width:300px;padding:0;line-height:40px;}
      .formLoginBox{position:absolute;right:200px;top:35px;width:380px;height:380px;background:#fff;border-radius:5px;padding-top:20px;box-sizing:border-box;overflow:hidden;}
      .login_left_box{position:absolute;width:289px;height:287px;top:50%;margin-top:-145px;left:20%;}
       .login_left_box>img{width:100%;height:auto;}
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${ctx}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名"},password: {required: "请填写密码"},
					validateCode: {remote: "验证码不正确", required: "请填写验证码"}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('登录已超时，请重新登录！');
			top.location = "${ctx}";
		}
	</script>
</head>
<body>
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
	
	<%-- <img src='${ctxStatic}/hcy/img/logo.jpg' style="position:absolute;left:0;top:0;"> --%>
	<div class="loginBox">
		<div class="topTitle">
			<h3 class="loginTit">院内SPD管理系统</h3>
		</div>
		<div class="login_left_box">
			<img alt="" src="${ctxStatic}/spd/img/login_left_icon.png" />
		</div>
		<div class="formLoginBox">
			<form id="loginForm" class="form-signin" action="${ctx}/login" method="post" style="width:380px;margin:0;padding:0 26px;box-sizing:border-box;">
				<input type="hidden" name="logintype" value="a">
				<div class="userNameBox">
					<label>用户名</label>
					<input placeholder="请输入用户名" type="text" id="username" name="username" class="input-block-level required" value="${username}">
				</div>
				<div class="passwordBox">
					<label>密码</label>
					<input  placeholder="请输入密码" type="password" id="password" name="password" class="input-block-level required">
				</div>
				<!--c:if test="${isValidateCodeLogin}">-->
				<div class="validateCode">
					<label style="display: inherit;">验证码</label>
					<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
				</div><%--</c:if>
				<label for="mobile" title="手机登录"><input type="checkbox" id="mobileLogin" name="mobileLogin" ${mobileLogin ? 'checked' : ''}/></label> --%>
				<input class="btn btn-large btn-primary" id="loginSubmit" type="submit" value="登 录"/>
				<%-- <label for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我<!-- （公共场所慎用） --></label> --%>
				<%-- <div id="themeSwitch" class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}<b class="caret"></b></a>
					<ul class="dropdown-menu">
					  <c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
					</ul>
					<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
				</div> --%>
				<div class="header" style="padding:0;">
					<div id="messageBox" style="padding:0;margin:0;background:none;border:none;text-align:center;" class="alert alert-error ${empty message ? 'hide' : ''}">
						<!-- <button data-dismiss="alert" class="close">×</button> -->
						<label id="loginError" style="width:300px;padding:0;line-height:40px;" class="error">${message}</label>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="bottomBox">© 2017 HyTriage All Rights Reserved</div>
	<%-- <script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script> --%>
</body>
</html>