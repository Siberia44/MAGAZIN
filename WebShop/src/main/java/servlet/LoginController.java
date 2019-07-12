package servlet;

import constant.Constant;
import entity.User;
import service.IUserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private IUserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        userService = (IUserService) context.getAttribute(Constant.USER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> user = userService.getUserByLoginAndPassword(req.getParameter(Constant.NAME), req.getParameter(Constant.PASSWORD));
        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
            resp.sendRedirect("index.jsp");
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
