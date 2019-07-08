package servlet;

import captcha.CaptchaHandler;
import constant.Constant;
import creator.CaptchaCreator;
import entity.Captcha;
import sender.CaptchaSender;
import service.ICaptchaService;

import javax.imageio.ImageIO;
import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        captchaService = (ICaptchaService) context.getAttribute(Constant.CAPTCHA_SERVICE);
        captchaHandler = (CaptchaHandler) context.getAttribute(Constant.CAPTCHA_PRESERVER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        captchaService.removeOldCaptcha();
        getSenderWithNewCaptcha(req, resp).send();
        req.getRequestDispatcher(Constant.REGISTRATION_JSP).forward(req, resp);
    }

    private CaptchaSender getSenderWithNewCaptcha(HttpServletRequest request, HttpServletResponse response) {
        CaptchaSender sender = new CaptchaSender(request, response);
        try {
            CaptchaCreator captchaCreator = captchaService.create();
            BufferedImage bufferedImage = captchaService.bufferedImage(captchaCreator);
            OutputStream osImage = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpeg", osImage);
            Captcha captcha = captchaService.createCaptcha(captchaCreator.getCaptchaNumbers());
            captchaHandler.addCaptcha(request, response, captcha);
            sender.setCaptchaId(captcha.getId());
        } catch (NoSuchAttributeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sender;
    }
}
