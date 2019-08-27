package pl.patryk.videogamerental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.patryk.videogamerental.model.Role;
import pl.patryk.videogamerental.model.User;
import pl.patryk.videogamerental.repositories.RoleRepository;
import pl.patryk.videogamerental.repositories.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByRole("ROLE_ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(role)));
        user.setActive(1);
        userRepository.save(user);
    }
}
