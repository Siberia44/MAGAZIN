<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="info" %>

<%@ attribute name="image" required="true" %>

<div>
   <img src="captcha" method = "post">
   <c:choose>
    <c:when test="${initParam.captchaHandler == 'hiddenFieldCaptchaHandler'}">
        <input type="hidden" name="hiddenCaptcha" value=${captchaId}>
    </c:when>
</c:choose>
</div>