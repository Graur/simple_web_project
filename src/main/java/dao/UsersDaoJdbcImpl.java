package dao;

import dbHelper.DBHelper;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoJdbcImpl implements UsersDAO {
    private Connection jdbcConnection;

    public UsersDaoJdbcImpl() {
    }

    private void connect() throws SQLException {
        if(jdbcConnection == null || jdbcConnection.isClosed()) {

            jdbcConnection = DBHelper.instance().getConnection();
        }
    }

    private void disconnect() throws SQLException {
        if(jdbcConnection != null || !jdbcConnection.isClosed()){
            jdbcConnection.close();
        }
    }

    public void insertUser(User user) {
        String sql = "INSERT INTO user (name, login, password) VALUES (?, ? , ?)";

        try {
            connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());

            statement.executeUpdate();
            statement.close();
            disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void deleteUser(int id){
        String sql = "DELETE from user where id = ?";

        try{
            connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();
            disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateUser(User user){
        String sql = "UPDATE user SET name = ?, login = ?, password = ? WHERE id = ?";

        try{
            connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());

            statement.executeUpdate();
            statement.close();
            disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User getUser(int id){
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        try{
            connect();
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
            disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    public List<User> getAllUsers(){
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try{
            connect();
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
            disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
