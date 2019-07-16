package captcha.impl;

import captcha.AbstractCaptchaHandler;
import entity.Captcha;
import exception.SessionTimeOutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HiddenFieldCaptchaHandler extends AbstractCaptchaHandler {

    public HiddenFieldCaptchaHandler(Map<String, Captcha> captches) {
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
        System.out.println(request.getParameter(HIDDEN_CAPTCHA));
        Captcha captcha = captches.get(request.getParameter(HIDDEN_CAPTCHA));
        if (captcha.isValid()) {
            return captcha;
        }
        throw new SessionTimeOutException();
    }
}
