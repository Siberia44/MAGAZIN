package dao;

import entity.Captcha;

import java.util.Map;

public interface ICaptchaDao {
    Captcha getCaptcha(int key);

    void removeCaptcha(int key);

    Map<Integer, Captcha> getAllCaptches();
}
