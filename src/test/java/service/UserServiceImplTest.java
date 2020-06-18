package service;

import model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserServiceImplTest {

    private static UserService userService = UserServiceImpl.getInstance();

    private static List<User> users = List.of(
            new User("John", "12345"),
            new User("Mary", "qwerty"),
            new User("Jack", "88888"),
            new User("Joe", "98735321")
    );

    @Before
    public void setUp() {
        userService.deleteAllUsers();
    }

    @Test
    public void addUser() {
        userService.addUser(users.get(0));
        User user = userService.getAllUsers().get(0);
        assertEquals(users.get(0), user);
    }

    @Test
    public void addTwoDifferentUsers() {
        userService.addUser(users.get(0));
        userService.addUser(users.get(1));
        assertNotEquals(userService.getAllUsers().get(0), userService.getAllUsers().get(1));
    }

    @Test
    public void updateUser() {
        User firstUser = users.get(0);
        userService.addUser(firstUser);
        firstUser.setEmail("test123");
        firstUser.setPassword("test123");

        User secondUser = userService.getAllUsers().get(0);
        firstUser.setId(secondUser.getId());

        userService.updateUser(firstUser);
        User actual = userService.getAllUsers().get(0);
        assertEquals(firstUser, actual);

    }

    @Test
    public void deleteUserByEmail() {
        userService.addUser(users.get(0));
        userService.addUser(users.get(1));

        userService.deleteUser(users.get(0).getEmail());
        assertEquals(userService.getAllUsers(), List.of(users.get(1)));
    }

    @Test
    public void deleteUserById() {
        userService.addUser(users.get(0));
        userService.addUser(users.get(1));

        List<User> allUsers = userService.getAllUsers();
        userService.deleteUser(allUsers.get(0).getId());

        assertEquals(userService.getAllUsers(), List.of(users.get(1)));

    }

}