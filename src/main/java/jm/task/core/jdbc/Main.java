package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Name1", "LastName1", (byte) 20);
        System.out.println("User � ������ � Name1 �������� � ���� ������");
        service.saveUser("Name2", "LastName2", (byte) 25);
        System.out.println("User � ������ � Name2 �������� � ���� ������");
        service.saveUser("Name3", "LastName3", (byte) 31);
        System.out.println("User � ������ � Name3 �������� � ���� ������");
        service.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println("User � ������ � Name4 �������� � ���� ������");

        service.removeUserById(1);
        List<User> users = service.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
