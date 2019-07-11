package servlet;

import captcha.CaptchaHandler;
import com.mysql.cj.Session;
import constant.Constant;
import entity.User;
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
import java.util.Optional;

@WebServlet("/check-login")
public class ValidationDataInput extends HttpServlet {
    private IUserService userService;
    private ICaptchaService captchaService;
    private CaptchaHandler captchaHandler;

    @Override
    public void init(ServletConfig config) {
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
            req.getRequestDispatcher(Constant.REGISTRATION_JSP).forward(req, resp);
        } else {
            req.setAttribute("name", req.getParameter(Constant.NAME));
            saveAllInformation(req);
            createUser(req);
            resp.sendRedirect("index.jsp");
        }
    }

    private void saveAllInformation(HttpServletRequest req) {
        req.setAttribute("password", req.getParameter(Constant.PASSWORD));
        req.setAttribute("email", req.getParameter(Constant.EMAIL));
    }

    private void createUser(HttpServletRequest req){
        User user = userService.add(req, "");
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
    }
}
