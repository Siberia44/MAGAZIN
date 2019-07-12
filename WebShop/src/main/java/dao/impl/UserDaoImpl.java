package dao.impl;

import constant.Constant;
import dao.IUserDao;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements IUserDao {
    private static final String FIND_BY_NAME = "select * from users where userName = ?";
    private static final String INSERT_NEW_USER = "insert into users values (?, ?, ?, ?)";
    private static final String GET_USER_BY_LOGIN_AND_PASSWORD = "select * from users where userName=? and userPassword=?";

    @Override
    public boolean isUserExist(Connection connection, String name) {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME)) {
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSetClose(resultSet);
        }
        return false;
    }

    @Override
    public void add(Connection connection, User user) {
        int k = 1;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER)) {
            statement.setString(k++, user.getEmail());
            statement.setString(k++, user.getName());
            statement.setString(k++, user.getPassword());
            statement.setString(k, user.getImage());
            statement.executeUpdate();
            System.out.println("created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromQueue(ResultSet resultSet) throws SQLException {
        return new User.UserBuilder().setEmail(resultSet.getString(Constant.EMAIL))
                .setName(resultSet.getString(Constant.NAME))
                .setPassword(resultSet.getString(Constant.PASSWORD))
                .setImage(resultSet.getString(Constant.AVATAR))
                .build();
    }

    @Override
    public Optional<User> getUserByLoginAndPassword(Connection connection, String login, String password) {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD)) {
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

    protected void resultSetClose(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
