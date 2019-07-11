package service;

import entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface IUserService {
    boolean isUserPresent(String name);

    User add(HttpServletRequest request, String avatarFilename);
}
