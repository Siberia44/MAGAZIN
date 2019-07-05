package servlet;

import captcha.CaptchaHandler;
import constant.Constant;
import constant.ContextConstant;
import constant.ControllerConstant;
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
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        userService = (IUserService) servletContext.getAttribute(Constant.USER_SERVICE);
        captchaService = (ICaptchaService) servletContext.getAttribute(ContextConstant.CAPTCHA_SERVICE);
        captchaHandler = (CaptchaHandler) config.getServletContext().getAttribute(ContextConstant.CAPTCHA_PRESERVER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("userName");
        if (userService.isUserPresent(name)) {
            saveAllInformation(req);
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        } else {
            if (captchaService.checkCaptchaOnValid(req, captchaHandler)){
                req.getRequestDispatcher("index.html").forward(req, resp);
            } else {
                req.getRequestDispatcher(ControllerConstant.REGISTRATION_JSP).forward(req, resp);
            }
          //  req.getRequestDispatcher("index.html").forward(req, resp);
        }
    }

    private void saveAllInformation(HttpServletRequest req) {
        req.setAttribute("surname", req.getParameter("userSurname"));
        req.setAttribute("password", req.getParameter("userPassword"));
        req.setAttribute("email", req.getParameter("userEmail"));
    }
}
