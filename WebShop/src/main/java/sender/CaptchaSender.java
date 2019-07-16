package sender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaSender {
    private HttpServletRequest request;
    private Integer captchaId = 0;
    private boolean isCaptcha = false;

    public CaptchaSender(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public CaptchaSender setRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    public CaptchaSender setCaptchaId(Integer captchaId) {
        this.captchaId = captchaId;
        return this;
    }

    public boolean isCaptcha() {
        return isCaptcha;
    }

    public CaptchaSender setCaptcha(boolean captcha) {
        isCaptcha = captcha;
        return this;
    }

    public void send() {
        request.setAttribute("sessionCaptchaId", captchaId);
        request.setAttribute("requestCaptchaId", captchaId);
        request.setAttribute("isCaptcha", isCaptcha);
        request.setAttribute("hiddenCaptcha", captchaId);
        //request.getSession().setAttribute("captchaId", captchaId);
    }
}
