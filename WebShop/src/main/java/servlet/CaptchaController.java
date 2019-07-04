package servlet;

import captcha.CaptchaHandler;
import constant.ContextConstant;
import constant.ControllerConstant;
import entity.Captcha;
import entity.User;
import sender.CaptchaSender;
import service.ICaptchaService;

import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/captcha")
public class CaptchaController extends HttpServlet {
    private ICaptchaService captchaService;
    private CaptchaHandler captchaHandler;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        captchaService = (ICaptchaService) context.getAttribute(ContextConstant.CAPTCHA_SERVICE);
        captchaHandler = (CaptchaHandler) config.getServletContext().getAttribute(ContextConstant.CAPTCHA_PRESERVER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  captchaService.removeOldCaptcha();
        getSenderWithNewCaptcha(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        captchaService.removeOldCaptcha();
        getSenderWithNewCaptcha(req, resp);
        if (captchaService.checkCaptchaOnValid(req, captchaHandler)){

        } else {
            returnToRegistrationByInvalidCaptcha(req, resp);
        }
    }

    private void returnToRegistrationByInvalidCaptcha(HttpServletRequest request, HttpServletResponse response) {
        getSenderWithNewCaptcha(request, response)
                .setCaptcha(Boolean.TRUE)
                .send();
        try {
            request.getRequestDispatcher(ControllerConstant.PAGE_FOLDER +  ControllerConstant.REGISTRATION_JSP).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CaptchaSender getSenderWithNewCaptcha(HttpServletRequest request, HttpServletResponse response) {
        CaptchaSender sender = new CaptchaSender(request, response);
        try {
            Captcha captcha = captchaService.create();
            captchaHandler.addCaptcha(request, response, captcha);
            sender.setCaptchaId(captcha.getId());
        } catch (NoSuchAttributeException e) {
            e.printStackTrace();
        }
        return sender;
    }
}
