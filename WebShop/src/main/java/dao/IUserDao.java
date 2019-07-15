package dao;

import entity.User;

import java.util.List;

public interface IUserDao {

    /**
     * @return a list of users
     */
    List<User> getListOfAllUsers();
}
