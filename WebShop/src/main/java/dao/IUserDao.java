package dao;

import entity.User;

import java.util.List;

public interface IUserDao {
    List<User> getListOfAllUsers();
}
