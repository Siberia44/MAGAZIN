package service.impl;

import bean.RegistrationForm;
import dao.IUserDao;
import dao.transaction.TransactionManager;
import entity.User;
import service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements IUserService {
    private IUserDao userDao;
    private TransactionManager transactionManager;

    public UserServiceImpl(IUserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean isUserPresent(String name) {
        List<User> userList = userDao.getListOfAllUsers();
        Optional<User> optionalUser = userList.stream().filter(nameInList -> nameInList.getName().equals(name)).findFirst();
        return optionalUser.isPresent();
    }

     @Override
    public User add(HttpServletRequest request, String avatarFilename) {
        User user = new RegistrationForm().createUserByRequest(request);
        transactionManager.doInTransaction(connection -> {
            userDao.add(connection, user);
            return null;
        });
        return user;
    }
}
