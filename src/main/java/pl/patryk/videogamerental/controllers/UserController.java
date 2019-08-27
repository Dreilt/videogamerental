package pl.patryk.videogamerental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.patryk.videogamerental.model.User;
import pl.patryk.videogamerental.services.UserService;
import pl.patryk.videogamerental.utilities.UserUtilities;
import pl.patryk.videogamerental.validator.ChangeUserDataValidator;
import pl.patryk.videogamerental.validator.ChangeUserPasswordValidator;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/register")
    public String createUser(@Valid User user, BindingResult bindingResult, Model model) {
        User userExist = userService.findUserByEmail(user.getEmail());
        if (userExist != null) {
            bindingResult.rejectValue("email", "empty", "Konto o podanym adresie email już istnieje.");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "Rejestracja zakończona pomyślnie. Za 3 sekundy zostaniesz przeniesiony do strony logowania.");
            return "afterregister";
        }
    }

    @GetMapping(value = "/profile")
    public String showUserProfile(Model model) {
        String userEmail = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(userEmail);
        int roleId = user.getRoles().iterator().next().getId();
        user.setRoleId(roleId);
        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping(value = "/updateprofile")
    public String updateUserProfile(User user, BindingResult bindingResult, Model model) {
        new ChangeUserDataValidator().validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "profile";
        } else {
            userService.updateUserProfile(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getId());
            model.addAttribute("message", "Dane zmienione pomyślnie. Za 3 sekundy zostaniesz wylogowany. Zaloguj się ponownie.");
            return "afteredit";
        }
    }

    @PostMapping(value = "/updatepass")
    public String updateUserPassword(User user, BindingResult bindingResult, Model model) {
        new ChangeUserPasswordValidator().validate(user, bindingResult);
        new ChangeUserPasswordValidator().checkPassword(user.getNewPassword(), bindingResult);

        if (bindingResult.hasErrors()) {
            return "profile";
        } else {
            userService.updateUserPassword(user.getNewPassword(), user.getEmail());
            model.addAttribute("message", "Hasło zostało zmienione. Za 3 sekundy zostaniesz wylogowany. Zaloguj się ponownie.");
            return "afteredit";
        }
    }


    @GetMapping(value = "/admin/users")
    public String showAllUsers(Model model) {
        model.addAttribute("userList", userService.findAllUsers());
        return "admin/users";
    }

    @GetMapping(value = "/admin/user/{userId}=edit")
    public String editUserRoleOrActivity(@PathVariable("userId") long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        return "admin/useredit";
    }

    @PostMapping(value = "/admin/user/{userId}=update")
    public String updateUserRoleOrActivity(@PathVariable("userId") long userId, User user) {
        int roleId = user.getRoleId();
        int isActive = user.getActive();
        userService.updateUserRoleOrActivity(roleId, isActive, userId);
        return "redirect:/admin/users";
    }
}
