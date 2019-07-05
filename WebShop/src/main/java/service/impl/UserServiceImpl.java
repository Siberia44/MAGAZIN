package service.impl;

import dao.IUserDao;
import entity.User;
import service.IUserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements IUserService {
    private IUserDao userDao;

    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isUserPresent(String name) {
        List<User> userList = userDao.getListOfAllUsers();
        System.out.println(userList.toArray());
        Optional<User> optionalUser = userList.stream().filter(nameInList -> nameInList.getName().equals(name)).findFirst();
        System.out.println(optionalUser.isPresent());
        return optionalUser.isPresent();
    }
}
