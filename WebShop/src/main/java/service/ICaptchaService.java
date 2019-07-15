package service;

import creator.CaptchaCreator;
import dto.CaptchaDTO;
import entity.Captcha;

import javax.naming.directory.NoSuchAttributeException;
import java.awt.image.BufferedImage;

public interface ICaptchaService {

    /**
     * @return return a captcha creator object with a filled field
     */
    CaptchaCreator create();

    /**
     * create image from captcha creator
     *
     * @param captchaCreator
     * @return
     * @throws NoSuchAttributeException
     */
    BufferedImage bufferedImage(CaptchaCreator captchaCreator) throws NoSuchAttributeException;

    /**
     * @param captchaNumbers
     * @return return captcha entity with fields filled in.
     */
    Captcha createCaptcha(String captchaNumbers, long captchaLiveTime);

    /**
     * removes not valid captcha
     */
    void removeOldCaptcha();

    /**
     * compares the entered captcha and captcha on the server
     *
     * @param captchaDTO
     * @param captcha
     * @return true if captcha matches
     */
    boolean checkCaptchaOnValid(CaptchaDTO captchaDTO, Captcha captcha);
}
