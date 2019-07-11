package servlet;

import captcha.CaptchaHandler;
import com.mysql.cj.Session;
import constant.Constant;
import creator.ImageCreator;
import entity.Avatar;
import entity.User;
import service.ICaptchaService;
import service.IUserService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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

    private void createUser(HttpServletRequest req, Part part){
        Optional<User> user = userService.add(req);
        saveAvatar(user.get(), part);
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

    private void saveAvatar(User user, Part part) {
        if (part != null) {
            ImageCreator imageProvider = (ImageCreator) getServletContext().getAttribute("image");
            String fileName = imageProvider.add(part, user.getName());
            user.setImage(fileName);
        }
    }

}
