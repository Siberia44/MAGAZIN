package service;

import captcha.CaptchaHandler;
import entity.Captcha;

import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.http.HttpServletRequest;

public interface ICaptchaService {
    Captcha create() throws NoSuchAttributeException;

    void removeOldCaptcha();

    boolean checkCaptchaOnValid(HttpServletRequest request, CaptchaHandler handler);
}
