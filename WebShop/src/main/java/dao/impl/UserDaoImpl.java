package dao.impl;

import constant.Constant;
import dao.IUserDao;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements IUserDao {
    private List<User> userList;
    private static final String INSERT_NEW_USER = "insert into users values (?, ?, ?, ?, ?)";
    private static final String GET_USER_BY_ID = "select * from users where id=?";
    private static final String GET_USER_BY_LOGIN = "select * from users where login=?";
    private static final String GET_USER_BY_LOGIN_AND_PASSWORD = "select * from users where userName=? and userPassword=?";

    public UserDaoImpl(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public List<User> getListOfAllUsers() {
        return userList;
    }

    @Override
    public void add(Connection connection, User user){
        int k = 1;
        try(PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER)){
            statement.setInt(k++, user.getId());
            statement.setString(k++, user.getEmail());
            statement.setString(k++, user.getName());
            statement.setString(k++, user.getPassword());
          //  statement.setString(k++, user.);
            statement.executeUpdate();
            System.out.println("created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromQueue(ResultSet resultSet) throws SQLException {
        User user = new User.UserBuilder().setEmail(resultSet.getString(Constant.EMAIL))
                .setName(resultSet.getString(Constant.NAME))
                .setPassword(resultSet.getString(Constant.PASSWORD))
                .build();
     //   user.setAvatarFullname(resultSet.getString(ControllerConstant.AVATAR));
        return user;
    }


    @Override
    public Optional<User> getUserByLoginAndPassword(Connection connection, String login, String password) {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD)){
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(getUserFromQueue(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
          resultSetClose(resultSet);
        }
        return Optional.empty();
    }


    protected void resultSetClose(ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
