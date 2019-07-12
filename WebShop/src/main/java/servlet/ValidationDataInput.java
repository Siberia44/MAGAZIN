package servlet;

import captcha.CaptchaHandler;
import constant.Constant;
import creator.ImageCreator;
import entity.User;
import service.ICaptchaService;
import service.IUserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/check-login")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ValidationDataInput extends HttpServlet {
    private IUserService userService;
    private ICaptchaService captchaService;
    private CaptchaHandler captchaHandler;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        userService = (IUserService) context.getAttribute(Constant.USER_SERVICE);
        captchaService = (ICaptchaService) context.getAttribute(Constant.CAPTCHA_SERVICE);
        captchaHandler = (CaptchaHandler) context.getAttribute(Constant.CAPTCHA_PRESERVER);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = tryGetPart(req);
        String name = req.getParameter("userName");
        if (userService.isUserPresent(name) || !captchaService.checkCaptchaOnValid(req, captchaHandler)) {
            saveAllInformation(req);
            req.getRequestDispatcher(Constant.REGISTRATION_JSP).forward(req, resp);
        } else {
            req.setAttribute("name", req.getParameter(Constant.NAME));
            saveAllInformation(req);
            createUser(req, part);
            resp.sendRedirect("index.jsp");
        }
    }

    private void saveAllInformation(HttpServletRequest req) {
        req.setAttribute("password", req.getParameter(Constant.PASSWORD));
        req.setAttribute("email", req.getParameter(Constant.EMAIL));
    }

    private void createUser(HttpServletRequest req, Part part) {
        ImageCreator imageCreator = (ImageCreator) getServletContext().getAttribute("image");
        Optional<User> user = userService.add(req, part, imageCreator);
        HttpSession session = req.getSession();
        session.setAttribute("user", user.get());
    }

    private Part tryGetPart(HttpServletRequest req) {
        Part part = null;
        try {
            part = req.getPart("image");
        } catch (IOException | ServletException e) {
            System.out.println(e);
        }
        return part;
    }
}
