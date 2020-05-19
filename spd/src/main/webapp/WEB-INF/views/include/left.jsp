<%@ page contentType="text/html;charset=UTF-8" %>
<div class="rightFixed">
	<img  src="${ctxStatic}/mall/image/loadApp.jpg" id="loadAppBtn" alt="" style="margin-top:240px;display:inline-block;padding-top:0;"><br>
	<a style="display:inline-block;padding-top:35px;position:relative;" href="${ctxp}/hcy/mail/hcyCart/list"><img  src="${ctxStatic}/mall/image/shopCar.jpg" alt=""><span id="sysCartCount">0</span></a><br>
	<c:if test="${not empty fns:getUser().id && fns:getUser().status ne '1'}">
	<a style="display:inline-block;padding-top:20px;" href='javascript:void(0);' onclick="easemobim.bind({configId: '252ad070-edb5-482c-8ee9-38cf3d75d4b9',visitor: {trueName: '${fns:getUser().mobile}',phone: '${fns:getUser().hcyClinic.contactPhone}',companyName: '${fns:getUser().hcyClinic.orgName}',userNickname: '${fns:getUser().hcyClinic.orgName}',description: '意向客户',email: '${fns:getUser().hcyClinic.contactEmail}'}})"><img  src="${ctxStatic}/mall/image/service.jpg" alt=""></a><br>
	</c:if>
	<c:if test="${fns:getUser().status eq'1'}">
	<a style="display:inline-block;padding-top:20px;" href='javascript:void(0);' onclick="easemobim.bind({configId: '252ad070-edb5-482c-8ee9-38cf3d75d4b9',visitor: {trueName: '${fns:getUser().mobile}',userNickname: '${fns:getUser().mobile}',description: '意向客户'}})"><img  src="${ctxStatic}/mall/image/service.jpg" alt=""></a><br>
	</c:if>
	<c:if test="${empty fns:getUser().id}">
	<a style="display:inline-block;padding-top:20px;" href='javascript:void(0);' onclick="easemobim.bind({configId: '252ad070-edb5-482c-8ee9-38cf3d75d4b9',visitor: {trueName: '我是访客',userNickname: '我是访客',description: '临时客户'}})"><img  src="${ctxStatic}/mall/image/service.jpg" alt=""></a><br>
	</c:if>
	<a style="display:inline-block;padding-top:20px;" href="${ctxp}/hcy/adviceFeedBack/turnToAdvice"><img  src="${ctxStatic}/mall/image/suggestion.jpg" alt=""></a><br>
	<img id="goodTop"  src="${ctxStatic}/mall/image/goTop.jpg" alt=""><br>
</div>
<script>
window.easemobim = window.easemobim || {};
easemobim.config = {
    hide: true,
    autoConnect: true    
};
''
</script>
<script src='//kefu.easemob.com/webim/easemob.js'></script>