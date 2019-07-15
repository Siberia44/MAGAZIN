package dao;

import entity.Captcha;

import java.util.Map;

public interface ICaptchaDao {

    /**
     * @param key
     * @return captcha
     */
    Captcha getCaptcha(int key);

    /**
     * delete captcha from map
     *
     * @param key
     */
    void removeCaptcha(int key);

    /**
     * get list of all captches
     *
     * @return
     */
    Map<Integer, Captcha> getAllCaptches();
}
