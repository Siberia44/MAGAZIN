package listener;

import captcha.CaptchaHandler;
import constant.Constant;
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
    private UserContainer container = new UserContainer();
    private IUserDao userDao;
    private ICaptchaDao captchaDao;
    private ICaptchaService captchaService;
    private IUserService userService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        createDao();
        createServices();
        setCaptchaHandler(sce);
        sce.getServletContext().setAttribute(Constant.USER_SERVICE, userService);
        sce.getServletContext().setAttribute(Constant.CAPTCHA_SERVICE, captchaService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void createDao(){
        userDao = new UserDaoImpl(container.getUsersList());
        captchaDao = new CaptchaDaoImpl();
    }

    private void createServices(){
        captchaService = new CaptchaServiceImpl(captchaDao);
        userService = new UserServiceImpl(userDao);
    }

    private void setCaptchaHandler(ServletContextEvent sce){
        ServletContext context = sce.getServletContext();
        String handlerName = context.getInitParameter(Constant.CAPTCHA_HANDLER);
        CaptchaHandler handler = new CaptchaHandlerContainer().getCaptchaHandler(handlerName);
        context.setAttribute(Constant.CAPTCHA_PRESERVER, handler);
    }
}
