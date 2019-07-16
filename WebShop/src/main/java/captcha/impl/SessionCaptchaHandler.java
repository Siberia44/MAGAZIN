package captcha.impl;

import captcha.AbstractCaptchaHandler;
import entity.Captcha;
import exception.SessionTimeOutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class SessionCaptchaHandler extends AbstractCaptchaHandler {

    public SessionCaptchaHandler(Map<String, Captcha> captches) {
        super(captches);
    }

    @Override
    public void addCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        String captchaID = String.valueOf(captcha.getId());
        captches.put(captchaID, captcha);
        request.setAttribute(CAPTCHA_ID, captchaID);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) throws SessionTimeOutException {
        int captchaId = (int) request.getSession().getAttribute(CAPTCHA_ID);
        Captcha captcha = captches.get(captchaId);
        if (captcha.isValid()) {
            return captcha;
        }
        throw new SessionTimeOutException();
    }
}
