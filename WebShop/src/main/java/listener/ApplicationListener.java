package listener;

import constant.Constant;
import container.UserContainer;
import dao.IUserDao;
import dao.impl.UserDaoImpl;
import service.IUserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserContainer container = new UserContainer();
        IUserDao userDao = new UserDaoImpl(container.getUsersList());
        IUserService userService = new UserServiceImpl(userDao);

        sce.getServletContext().setAttribute(Constant.USER_SERVICE, userService);
    }
}
