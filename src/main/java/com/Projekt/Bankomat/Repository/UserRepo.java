package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
    boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber);

}
