package servlet;

import captcha.CaptchaHandler;
import constant.Constant;
import creator.CaptchaCreator;
import entity.Captcha;
import exception.SessionTimeOutException;
import sender.CaptchaSender;
import service.ICaptchaService;

import javax.imageio.ImageIO;
import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/captcha")
public class CaptchaController extends HttpServlet {
    private ICaptchaService captchaService;
    private CaptchaHandler captchaHandler;
    private long captchaLiveTime;
    private Captcha captcha;


    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        captchaService = (ICaptchaService) context.getAttribute(Constant.CAPTCHA_SERVICE);
        captchaHandler = (CaptchaHandler) context.getAttribute(Constant.CAPTCHA_PRESERVER);
        captchaLiveTime = Long.parseLong((String) context.getAttribute(Constant.CAPTCHA_LIVE_TIME));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            captchaService.removeOldCaptcha();
            getSenderWithNewCaptcha(req, resp).send();
    }

    private CaptchaSender getSenderWithNewCaptcha(HttpServletRequest request, HttpServletResponse response) {
        CaptchaSender sender = new CaptchaSender(request);
        try {
            CaptchaCreator captchaCreator = captchaService.create();
            BufferedImage bufferedImage = captchaService.bufferedImage(captchaCreator);
            OutputStream osImage = response.getOutputStream();
            captcha = captchaService.createCaptcha(captchaCreator.getCaptchaNumbers(), captchaLiveTime);
            sender.setCaptchaId(captcha.getId());
            captchaHandler.addCaptcha(request, response, captcha);
            ImageIO.write(bufferedImage, "jpeg", osImage);
            request.setAttribute("captcha", bufferedImage);
        } catch (NoSuchAttributeException | IOException e) {
            e.printStackTrace();
        }
        return sender;
    }
}
