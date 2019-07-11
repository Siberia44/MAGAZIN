package dao;

import entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface IUserDao {
    List<User> getListOfAllUsers();

    void add(Connection connection, User user);

    Optional<User> getUserByLoginAndPassword(Connection connection, String login, String password);
}
