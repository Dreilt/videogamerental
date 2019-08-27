package pl.patryk.videogamerental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.patryk.videogamerental.model.Role;
import pl.patryk.videogamerental.model.User;
import pl.patryk.videogamerental.repositories.RoleRepository;
import pl.patryk.videogamerental.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
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

    @Override
    public void updateUserPassword(String newPassword, String email) {
        userRepository.updateUserPassword(bCryptPasswordEncoder.encode(newPassword), email);
    }

    @Override
    public void updateUserProfile(String newFirstName, String newLastName, String newEmail, String newPhoneNumber, long id) {
        userRepository.updateUserProfile(newFirstName, newLastName, newEmail, newPhoneNumber, id);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            int roleId = user.getRoles().iterator().next().getId();
            user.setRoleId(roleId);
        }

        return userList;
    }

    @Override
    public User findUserById(long userId) {
        User user = userRepository.findById(userId).orElse(null);
        int roleId = user.getRoles().iterator().next().getId();
        user.setRoleId(roleId);

        return user;
    }

    @Override
    public void updateUserRoleOrActivity(int roleId, int activity, long userId) {
        userRepository.updateUserRole(roleId, userId);
        userRepository.updateUserActivity(activity, userId);
    }
}
