package pl.patryk.videogamerental.utilities;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtilities {

    public static String getLoggedUser() {
        String userEmail = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            userEmail = auth.getName();
        }

        return userEmail;
    }
}