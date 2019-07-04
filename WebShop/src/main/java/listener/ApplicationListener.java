package listener;

import captcha.CaptchaHandler;
import constant.Constant;
import constant.ContextConstant;
import container.CaptchaHandlerContainer;
import container.UserContainer;
import dao.ICaptchaDao;
import dao.IUserDao;
import dao.impl.CaptchaDaoImpl;
import dao.impl.UserDaoImpl;
import service.ICaptchaService;
import service.IUserService;
import service.impl.CaptchaServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserContainer container = new UserContainer();
        IUserDao userDao = new UserDaoImpl(container.getUsersList());
        ICaptchaDao captchaDao = new CaptchaDaoImpl();
        ICaptchaService captchaService = new CaptchaServiceImpl(captchaDao);
        IUserService userService = new UserServiceImpl(userDao);

        sce.getServletContext().setAttribute(Constant.USER_SERVICE, userService);
        sce.getServletContext().setAttribute(ContextConstant.CAPTCHA_SERVICE, captchaService);

        ServletContext context = sce.getServletContext();

        String handlerName = context.getInitParameter(ContextConstant.CAPTCHA_HANDLER);
        CaptchaHandler handler = new CaptchaHandlerContainer().getCaptchaHandler(handlerName);
        context.setAttribute(ContextConstant.CAPTCHA_PRESERVER, handler);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
