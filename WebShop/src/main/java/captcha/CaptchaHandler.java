package captcha;

import entity.Captcha;
import exception.SessionTimeOutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaHandler {

    /**
     * Adds captcha to selected container
     * depending on implementation.
     *
     * @param request
     * @param response
     * @param captcha
     */
    void addCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha);

    /**
     * Receives captcha from the selected container
     * depending on the implementation
     *
     * @param request
     * @return
     * @throws SessionTimeOutException
     */
    Captcha getCaptcha(HttpServletRequest request) throws SessionTimeOutException;
}
