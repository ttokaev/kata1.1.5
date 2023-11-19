package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Constants;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        connection  = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute(Constants.CREATE_TABLE_USER);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ignored) {}
        }
    }

    public void dropUsersTable() {
        connection  = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute(Constants.DROP_TABLE_USER);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ignored) {}
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        connection  = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute(String.format(Constants.INSERT_INTO_USER, name, lastName, age));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ignored) {}
        }
    }

    public void removeUserById(long id) {
        connection  = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute(String.format(Constants.DELETE_FROM_USER, id));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ignored) {}
        }
    }

    public List<User> getAllUsers() {
        connection  = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Constants.GET_ALL_USERS);
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4))
                );
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ignored) {}
        }
    }

    public void cleanUsersTable() {
        connection  = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute(String.format(Constants.DELETE_TABLE_USER));
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ignored) {}
        }
    }
}
