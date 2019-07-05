package service.impl;

import captcha.CaptchaHandler;
import constant.ControllerConstant;
import container.CaptchaParameterContainer;
import creator.CaptchaCreator;
import dao.ICaptchaDao;
import entity.Captcha;
import exception.SessionTimeOutException;
import service.ICaptchaService;

import javax.imageio.ImageIO;
import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class CaptchaServiceImpl implements ICaptchaService {
    ICaptchaDao captchaDao;

    public CaptchaServiceImpl(ICaptchaDao captchaDao) {
        this.captchaDao = captchaDao;
    }

//    @Override
//    public Captcha create() throws NoSuchAttributeException {
//        CaptchaCreator creator = new CaptchaCreator().setHeight(40)
//                .setWidth(220)
//                .setSymbolCount(9);
//        BufferedImage image = creator.createImage();
//        createImageFile(image);
//        return createCaptcha(creator.getCaptchaNumbers());
//    }


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
        for (Map.Entry<Integer, Captcha> entry : captchaDao.getAllCaptches().entrySet()){
            if (!entry.getValue().isValid()){
                removeId.add(entry.getKey());
            }
        }
        for (Integer id : removeId){
            captchaDao.removeCaptcha(id);
        }
    }

    @Override
    public boolean checkCaptchaOnValid(HttpServletRequest request, CaptchaHandler handler) {
        try {
            Captcha captcha = handler.getCaptcha(request);
            return captcha.getNumbers().equals(request.getParameter(ControllerConstant.CAPTCHA));
        } catch (SessionTimeOutException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void createImageFile(BufferedImage image){
        try {
            File file = new File(CaptchaParameterContainer.CAPTCHA_FILE_PATH);
            ImageIO.write(image, "jpeg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Captcha createCaptcha(String captchaNumbers) {
        return new Captcha.CaptchaBuilder().setExpirationTime(new Date().getTime())
                .setNumbers(captchaNumbers).build();
    }

}
