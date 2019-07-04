package dao.impl;

import dao.IUserDao;
import entity.User;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    private List<User> userList;

    public UserDaoImpl(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public List<User> getListOfAllUsers() {
        return userList;
    }
}
