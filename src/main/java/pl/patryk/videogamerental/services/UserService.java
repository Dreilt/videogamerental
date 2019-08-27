package pl.patryk.videogamerental.services;

import pl.patryk.videogamerental.model.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    void updateUserPassword(String newPassword, String email);

    void updateUserProfile(String newFirstName, String newLastName, String newEmail, String newPhoneNumber, long id);
}
