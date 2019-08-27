package pl.patryk.videogamerental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.patryk.videogamerental.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    @Modifying
    @Query(value = "UPDATE User u SET u.password = :newPassword WHERE u.email = :email")
    void updateUserPassword(@Param("newPassword") String newPassword, @Param("email") String email);

    @Modifying
    @Query(value = "UPDATE User u SET u.firstName = :newFirstName, u.lastName = :newLastName, u.email = :newEmail, u.phoneNumber = :newPhoneNumber WHERE u.id = :userId")
    void updateUserProfile(@Param("newFirstName") String newFirstName, @Param("newLastName") String newLastName, @Param("newEmail") String newEmail, @Param("newPhoneNumber") String newPhoneNumber, @Param("userId") long userId);

    @Modifying
    @Query(value = "UPDATE user_role r SET r.role_id = :roleId WHERE r.user_id = :userId", nativeQuery = true)
    void updateUserRole(@Param("roleId") int roleId, @Param("userId") long userId);

    @Modifying
    @Query(value = "UPDATE User u SET u.active = :active WHERE u.id = :userId")
    void updateUserActivity(@Param("active") int active, @Param("userId") long userId);
}
