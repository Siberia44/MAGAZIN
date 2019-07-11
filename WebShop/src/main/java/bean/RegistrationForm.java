package bean;

import constant.Constant;
import entity.User;

import javax.servlet.http.HttpServletRequest;

public class RegistrationForm {
    public User createUserByRequest(HttpServletRequest request){
        return new User.UserBuilder().setName(request.getParameter(Constant.NAME))
                .setPassword(request.getParameter(Constant.PASSWORD))
                .setEmail(request.getParameter(Constant.EMAIL))
                .build();
    }
}
