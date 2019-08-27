package pl.patryk.videogamerental.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.patryk.videogamerental.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeUserDataValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[a-zA-z0-9]+[\\._a-zA-Z0-9]*@[a-zA-Z0-9]+{2,}\\.[a-zA-Z]{2,}[\\.a-zA-Z0-9]*$";
    private static final String PHONENUMBER_PATTERN = "^[0-9]{9}$";


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User u = (User) o;

        ValidationUtils.rejectIfEmpty(errors, "firstName", "empty", "Wypełnij pole.");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "empty", "Wypełnij pole.");
        ValidationUtils.rejectIfEmpty(errors, "email", "empty", "Wypełnij pole.");
        ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "empty", "Wypełnij pole.");

        if (!u.getEmail().equals(null)) {
            Pattern p = Pattern.compile(EMAIL_PATTERN);
            Matcher m = p.matcher(u.getEmail());
            boolean isMatch = m.matches();

            if(!isMatch) {
                errors.rejectValue("email", "empty", "Adres email jest nieprawidłowy.");
            }
        }

        if (!u.getPhoneNumber().equals(null)) {
            Pattern p = Pattern.compile(PHONENUMBER_PATTERN);
            Matcher m = p.matcher(u.getPhoneNumber());
            boolean isMatch = m.matches();

            if(!isMatch) {
                errors.rejectValue("phoneNumber", "empty", "Podany numer telefonu jest nieprawidłowy.");
            }
        }
    }
}
