package service;

import entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface IUserService {
    boolean isUserPresent(String name);

    Optional<User> add(HttpServletRequest request);

    Optional<User> getUserByLoginAndPassword(String login, String password);
}
