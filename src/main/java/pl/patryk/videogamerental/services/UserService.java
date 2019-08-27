package pl.patryk.videogamerental.services;

import pl.patryk.videogamerental.model.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    void updateUserPassword(String newPassword, String email);

    void updateUserProfile(String newFirstName, String newLastName, String newEmail, String newPhoneNumber, long id);

    List<User> findAllUsers();

    User findUserById(long userId);

    void updateUserRoleOrActivity(int roleId, int activity, long userId);
}
