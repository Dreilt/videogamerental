package pl.patryk.videogamerental.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.patryk.videogamerental.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeUserPasswordValidator implements Validator {

    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{6,}$";

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        @SuppressWarnings("unused")
        User u = (User) o;
        ValidationUtils.rejectIfEmpty(errors, "newPassword", "empty", "Wypełnij pole.");
    }

    public void checkPassword(String newPassword, Errors errors) {
        if (!newPassword.equals(null)) {
            Pattern p = Pattern.compile(PASSWORD_PATTERN);
            Matcher m = p.matcher(newPassword);
            boolean isMatch = m.matches();

            if (!isMatch) {
                errors.rejectValue("newPassword", "empty", "Hasło musi składać się z minimum 6 znaków i zawierać co najmniej jedną małą i jedną dużą literę, oraz cyfrę.");
            }
        }
    }
}