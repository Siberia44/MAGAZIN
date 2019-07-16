package captcha;

import entity.Captcha;

import java.util.Map;

public abstract class AbstractCaptchaHandler implements CaptchaHandler {

    protected static final String CAPTCHA_ID = "captchaId";
    protected static final String HIDDEN_CAPTCHA = "hiddenCaptcha";

    protected Map<String, Captcha> captches;

    protected AbstractCaptchaHandler(Map<String, Captcha> captches) {
        this.captches = captches;
    }
}
