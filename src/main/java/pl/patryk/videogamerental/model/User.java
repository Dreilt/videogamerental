package pl.patryk.videogamerental.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Wypełnij pole.")
    private String firstName;

    @NotEmpty(message = "Wypełnij pole.")
    private String lastName;

    @NotEmpty(message = "Wypełnij pole.")
    @Email(regexp = "^[a-zA-Z0-9]+[\\._a-zA-Z0-9]*@[a-zA-Z0-9]+\\.[a-z]{2,}[\\.a-z]*$", message = "Podany adres email jest nieprawidłowy.")
    private String email;

    @NotEmpty(message = "Wypełnij pole.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{6,}$", message = "Hasło musi składać się z minimum 6 znaków i zawierać co najmniej jedną małą i jedną dużą literę, oraz cyfrę.")
    private String password;

    @NotEmpty(message = "Wypełnij pole.")
    @Pattern(regexp = "^[0-9]{9}$", message = "Podany numer telefonu jest nieprawidłowy.")
    private String phoneNumber;

    @NotNull
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<Copy> copies;

    @OneToMany(mappedBy = "user")
    private Set<ReservationHistory> reservationHistories;

    public User() {
    }

    public User(@NotEmpty(message = "Wypełnij pole.") String firstName, @NotEmpty(message = "Wypełnij pole.") String lastName, @NotEmpty(message = "Wypełnij pole.") @Email(regexp = "^[a-zA-Z0-9]+[\\._a-zA-Z0-9]*@[a-zA-Z0-9]+\\.[a-z]{2,}[\\.a-z]*$", message = "Podany adres email jest nieprawidłowy.") String email, @NotEmpty(message = "Wypełnij pole.") @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{6,}$", message = "Hasło musi składać się z minimum 6 znaków i zawierać co najmniej jedną małą i jedną dużą literę, oraz cyfrę.") String password, @NotEmpty(message = "Wypełnij pole.") @Pattern(regexp = "^[0-9]{9}$", message = "Podany numer telefonu jest nieprawidłowy.") String phoneNumber, @NotNull int active, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Copy> getCopies() {
        return copies;
    }

    public void setCopies(Set<Copy> copies) {
        this.copies = copies;
    }

    public Set<ReservationHistory> getReservationHistories() {
        return reservationHistories;
    }

    public void setReservationHistories(Set<ReservationHistory> reservationHistories) {
        this.reservationHistories = reservationHistories;
    }
}
