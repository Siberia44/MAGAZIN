package servlet;

import captcha.CaptchaHandler;
import constant.Constant;
import service.ICaptchaService;
import service.IUserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/check-login")
public class ValidationDataInput extends HttpServlet {
    private IUserService userService;
    private ICaptchaService captchaService;
    private CaptchaHandler captchaHandler;

    @Override
    public void init(ServletConfig config)  {
        ServletContext context = config.getServletContext();
        userService = (IUserService) context.getAttribute(Constant.USER_SERVICE);
        captchaService = (ICaptchaService) context.getAttribute(Constant.CAPTCHA_SERVICE);
        captchaHandler = (CaptchaHandler) context.getAttribute(Constant.CAPTCHA_PRESERVER);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("userName");
        if (userService.isUserPresent(name) || !captchaService.checkCaptchaOnValid(req, captchaHandler)) {
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
