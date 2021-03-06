package captcha.impl;

import captcha.AbstractCaptchaHandler;
import container.CaptchaParameterContainer;
import entity.Captcha;
import exception.SessionTimeOutException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class CookieCaptchaHandler extends AbstractCaptchaHandler {

    private int oldestCookieId = 0;
    private Cookie oldestCookie;

    public CookieCaptchaHandler(Map<String, Captcha> captches) {
        super(captches);
    }

    @Override
    public void addCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        String captchaID = String.valueOf(captcha.getId());
        captches.put(captchaID, captcha);
        Cookie cookie = new Cookie(CaptchaParameterContainer.CAPTCHA +
                captchaID, "" + captchaID);
        response.addCookie(cookie);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) throws SessionTimeOutException {
        String cookieId = getOldestCaptchaIdByCookie(request);
        Captcha captcha = captches.get(cookieId);
        if (captcha.isValid()) {
            return captcha;
        }
        throw new SessionTimeOutException();
    }

    private String getOldestCaptchaIdByCookie(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().startsWith(CaptchaParameterContainer.CAPTCHA)) {
                findValidCookie(cookie);
            }
        }
        return String.valueOf(oldestCookie.getValue());
    }

    private void findValidCookie(Cookie cookie) {
        if (oldestCookie == null) {
            firstCookie(cookie);
        } else {
            findOlderCookie(cookie);
        }
    }

    private void firstCookie(Cookie cookie) {
        oldestCookie = cookie;
        oldestCookieId = Integer.parseInt(cookie.getName()
                .replace(CaptchaParameterContainer.CAPTCHA, ""));
    }

    private void findOlderCookie(Cookie cookie) {
        int idCaptcha = Integer.parseInt(cookie.getName()
                .replace(CaptchaParameterContainer.CAPTCHA, ""));
        if (idCaptcha >= oldestCookieId) {
            oldestCookie = cookie;
            oldestCookieId = idCaptcha;
        }
    }
}
