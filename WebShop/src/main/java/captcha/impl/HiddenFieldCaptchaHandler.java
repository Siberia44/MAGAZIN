package captcha.impl;

import captcha.AbstractCaptchaHandler;
import entity.Captcha;
import exception.SessionTimeOutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HiddenFieldCaptchaHandler extends AbstractCaptchaHandler {

    public HiddenFieldCaptchaHandler(Map<Integer, Captcha> captches) {
        super(captches);
    }

    @Override
    public void addCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        int captchaID = captcha.getId();
        captches.put(captchaID, captcha);
        request.setAttribute(CAPTCHA_ID, captchaID);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) throws SessionTimeOutException {
        String captchaId = request.getParameter(HIDDEN_CAPTCHA);
        Captcha captcha = captches.get(Integer.parseInt(captchaId));
        if (captcha.isValid()) {
            return captcha;
        }
        throw new SessionTimeOutException();
    }
}
