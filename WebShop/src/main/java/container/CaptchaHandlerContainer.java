package container;

import captcha.CaptchaHandler;
import captcha.impl.CookieCaptchaHandler;
import captcha.impl.HiddenFieldCaptchaHandler;
import captcha.impl.SessionCaptchaHandler;
import constant.ContextConstant;
import entity.Captcha;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CaptchaHandlerContainer {

    private Map<String, CaptchaHandler> handlers = new HashMap<>();
    private final Map<Integer, Captcha> captches = new LinkedHashMap<>();

    public CaptchaHandlerContainer() {
        handlersInit();
    }

    private void handlersInit() {
        handlers.put(ContextConstant.HIDDEN_FIELD_CAPTCHA_HANDLER, new HiddenFieldCaptchaHandler(captches));
        handlers.put(ContextConstant.COOKIE_CAPTCHA_HANDLER, new CookieCaptchaHandler(captches));
        handlers.put(ContextConstant.SESSION_CAPTCHA_HANDLER, new SessionCaptchaHandler(captches));
    }

    public CaptchaHandler getCaptchaHandler(String handlerName){
        return handlers.get(handlerName);
    }
}
