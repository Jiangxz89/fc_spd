<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="验证码输入框名称"%>
<%@ attribute name="inputCssStyle" type="java.lang.String" required="false" description="验证框样式"%>
<%@ attribute name="imageCssStyle" type="java.lang.String" required="false" description="验证码图片样式"%>
<%@ attribute name="buttonCssStyle" type="java.lang.String" required="false" description="看不清按钮样式"%>
<div class="codeBox">
<input type="text" id="${name}" name="${name}" autocomplete="off"  maxlength="5" class="txt required" placeholder="请输入验证码" />
</div>
<div style="float:left;width:155px;height:38px;" onclick="$('.${name}Refresh').click();"><img src="${ctx}/servlet/validateCodeServlet" id="codeVImg" onclick="$('.${name}Refresh').click();" class="mid ${name}" style="${imageCssStyle}"/></div>
<a href="javascript:" onclick="$('.${name}').attr('src','${ctx}/servlet/validateCodeServlet?'+new Date().getTime());" class="mid ${name}Refresh" style="${buttonCssStyle}"></a>