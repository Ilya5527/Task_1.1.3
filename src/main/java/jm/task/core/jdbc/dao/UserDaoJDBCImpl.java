package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private PreparedStatement preparedStatement;


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement("create table user\n" +
                    "(\n" +
                    "    id       bigint auto_increment,\n" +
                    "    name     varchar(50) not null,\n" +
                    "    lastName varchar(50) not null,\n" +
                    "    age      tinyint     not null,\n" +
                    "    constraint user_pk\n" +
                    "        primary key (id)\n" +
                    ");");
            preparedStatement.executeUpdate();
            System.out.println("Таблица успешно создана");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement("DROP TABLE user");
            preparedStatement.executeUpdate();
            System.out.println("Таблица успешно удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement("INSERT INTO user(name, lastName, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT * FROM user");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                User user = new User(result.getString("name"), result.getString("lastName"), result.getByte("age"));
                user.setId(result.getLong("id"));
                userList.add(user);
            }

            System.out.println(userList);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = id");
            preparedStatement.executeUpdate();
            System.out.println("Таблица успешно очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
