package pl.patryk.videogamerental.services;

import pl.patryk.videogamerental.model.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);
}
