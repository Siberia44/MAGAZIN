package servlet;

import captcha.CaptchaHandler;
import constant.Constant;
import creator.CaptchaCreator;
import dto.CaptchaDTO;
import entity.Captcha;
import exception.SessionTimeOutException;
import sender.CaptchaSender;
import service.ICaptchaService;
import service.IUserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/check-login")
public class RegistrationServlet extends HttpServlet {
    private IUserService userService;
    private ICaptchaService captchaService;
    private CaptchaHandler captchaHandler;
    private Captcha captcha;
    private CaptchaCreator captchaCreator;
    private long captchaLiveTime;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        userService = (IUserService) context.getAttribute(Constant.USER_SERVICE);
        captchaService = (ICaptchaService) context.getAttribute(Constant.CAPTCHA_SERVICE);
        captchaHandler = (CaptchaHandler) context.getAttribute(Constant.CAPTCHA_PRESERVER);
        captchaLiveTime = Long.parseLong((String) context.getAttribute(Constant.CAPTCHA_LIVE_TIME));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CaptchaSender sender = new CaptchaSender(req);
        captchaCreator = captchaService.create();
        captcha = captchaService.createCaptcha(captchaCreator.getCaptchaNumbers(), captchaLiveTime);
        sender.setCaptchaId(captcha.getId());
        captchaHandler.addCaptcha(req, resp, captcha);
        HttpSession session = req.getSession();
        session.setAttribute("captchaCreator", captchaCreator);
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            captcha = captchaHandler.getCaptcha(req);
        } catch (SessionTimeOutException e) {
            e.printStackTrace();
        }
        String name = req.getParameter("userName");
        CaptchaDTO captchaDTO = new CaptchaDTO();
        captchaDTO.setCaptchaNumbers(req.getParameter(Constant.CAPTCHA));
        if (userService.isUserPresent(name) || !captchaService.checkCaptchaOnValid(captchaDTO, captcha)) {
            saveAllInformation(req);
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("index.html");
        }
    }

    private void saveAllInformation(HttpServletRequest req) {
        req.setAttribute("surname", req.getParameter("userSurname"));
        req.setAttribute("password", req.getParameter("userPassword"));
        req.setAttribute("email", req.getParameter("userEmail"));
    }
}
