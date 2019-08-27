package pl.patryk.videogamerental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.patryk.videogamerental.model.User;
import pl.patryk.videogamerental.services.UserService;

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
}
