<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="info" %>

<%@ attribute name="captchaId" required="false" %>
<%@ attribute name="image" required="true" %>


<div>
   <img src="captcha">
   <c:choose>
    <c:when test="${initParam.captchaHandler == 'hiddenFieldCaptchaHandler'}">
    <form action = "check-login">
        <input type="hidden" name="hiddenCaptcha" value="${captchaId}">
        </form>
    </c:when>


</c:choose>
</div>