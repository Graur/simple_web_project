package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private Connection jdbcConnection;

    public UsersDAO(Connection connection) {
       jdbcConnection = connection;
    }
//
//    protected void connect() throws SQLException {
//        if(jdbcConnection == null || jdbcConnection.isClosed()) {
//
//            jdbcConnection = SQL_Connection.getDBConnection();
//        }
//    }
//
//    private void disconnect() throws SQLException {
//        if(jdbcConnection != null || !jdbcConnection.isClosed()){
//            SQL_Connection.closeDBConnection();
//        }
//    }

    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO user (name, login, password) VALUES (?, ? , ?)";
//        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getLogin());
        statement.setString(3, user.getPassword());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
//        disconnect();
        return rowInserted;
    }

    public boolean deleteUser(User user) throws SQLException{
        String sql = "DELETE from user where id = ?";
//        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, user.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
//        disconnect();
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException{
        String sql = "UPDATE user SET name = ?, login = ?, password = ? WHERE id = ?";
//        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getLogin());
        statement.setString(3, user.getPassword());
        statement.setInt(4, user.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
//        disconnect();
        return rowUpdated;
    }

    public User getUser(int id) throws SQLException{
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";

//        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            String name = resultSet.getString( "name");
            String login = resultSet.getString( "login");
            String password = resultSet.getString( "password");

            user = new User(id, name, login, password);
        }

        resultSet.close();
        statement.close();
        return user;
    }

    public List<User> getAllUsers() throws SQLException{
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user";

//        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString( "name");
            String login = resultSet.getString( "login");
            String password = resultSet.getString( "password");
            User user = new User(id, name, login, password);
            list.add(user);
        }

        resultSet.close();
        statement.close();
//        disconnect();
        return list;
    }
}
