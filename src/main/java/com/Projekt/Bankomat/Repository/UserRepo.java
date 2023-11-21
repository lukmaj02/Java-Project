package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
    boolean existsByPhoneNumber(String nrTelefonu);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

}
