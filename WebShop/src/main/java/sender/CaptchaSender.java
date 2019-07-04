package sender;

import entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaSender {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Integer captchaId = 0;
    private boolean isCaptcha = false;

    public CaptchaSender(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }


    public HttpServletRequest getRequest() {
        return request;
    }

    public CaptchaSender setRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public CaptchaSender setResponse(HttpServletResponse response) {
        this.response = response;
        return this;
    }

    public Integer getCaptchaId() {
        return captchaId;
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

    public void send(){
        request.getSession().setAttribute("sessionCaptchaId", captchaId);
        request.setAttribute("requestCaptchaId", captchaId);
        request.setAttribute("isCaptcha", isCaptcha);
        request.getSession().setAttribute("hiddenCaptcha", captchaId);
    }
}
