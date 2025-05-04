package krakow.university.dormitory.repositories;

import krakow.university.dormitory.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(
            value="SELECT * FROM `users`",
            nativeQuery = true)
    List<User> getAllUsers();

    @Query(
            value="SELECT * FROM `users` where user_email=?1",
            nativeQuery = true)
    Optional<User> findByUserEmail(String userEmail);

}
