package service.impl;

import creator.CaptchaCreator;
import dao.ICaptchaDao;
import dto.CaptchaDTO;
import entity.Captcha;
import service.ICaptchaService;

import javax.naming.directory.NoSuchAttributeException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CaptchaServiceImpl implements ICaptchaService {
    ICaptchaDao captchaDao;

    public CaptchaServiceImpl(ICaptchaDao captchaDao) {
        this.captchaDao = captchaDao;
    }

    @Override
    public CaptchaCreator create() {
        return new CaptchaCreator().setHeight(40)
                .setWidth(220)
                .setSymbolCount(9);
    }

    @Override
    public BufferedImage bufferedImage(CaptchaCreator captchaCreator) throws NoSuchAttributeException {
        return captchaCreator.createImage();
    }

    @Override
    public void removeOldCaptcha() {
        List<Integer> removeId = new ArrayList<>();
        for (Map.Entry<Integer, Captcha> entry : captchaDao.getAllCaptches().entrySet()) {
            if (!entry.getValue().isValid()) {
                removeId.add(entry.getKey());
            }
        }
        for (Integer id : removeId) {
            captchaDao.removeCaptcha(id);
        }
    }

    @Override
    public boolean checkCaptchaOnValid(CaptchaDTO captchaDTO, Captcha captcha) {
        return captcha.getNumbers().equals(captchaDTO.getCaptchaNumbers());
    }

    @Override
    public Captcha createCaptcha(String captchaNumbers, long captchaLiveTIme) {
        return new Captcha.CaptchaBuilder().setExpirationTime(new Date().getTime())
                .setNumbers(captchaNumbers)
                .setCaptchaLiveTime(captchaLiveTIme)
                .build();
    }

}
