package service.impl;

import container.CaptchaParameterContainer;
import creator.CaptchaCreator;
import entity.Captcha;
import service.ICaptchaService;

import javax.imageio.ImageIO;
import javax.naming.directory.NoSuchAttributeException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class CaptchaServiceImpl implements ICaptchaService {
    @Override
    public Captcha createCaptcha() {
        CaptchaCreator creator = new CaptchaCreator().setHeight(40)
                .setWidth(220)
                .setSymbolCount(9);
        BufferedImage image = null;
        try {
            image = creator.createImage();
        } catch (NoSuchAttributeException e) {
            e.printStackTrace();
        }
        createImageFile(image);
        return createCaptcha(creator.getCaptchaNumbers());
    }

    private void createImageFile(BufferedImage image){
        try {
            File file = new File(CaptchaParameterContainer.CAPTCHA_FILE_PATH);
            ImageIO.write(image, "jpeg", file);
        } catch (IOException e) {
            System.out.println();
        }
    }

    private Captcha createCaptcha(String captchaNumbers) {
        return new Captcha.CaptchaBuilder().setExpirationTime(new Date().getTime())
                .setNumbers(captchaNumbers).build();
    }

}
