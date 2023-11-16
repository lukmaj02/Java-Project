package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.Przelew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrzelewRepo extends JpaRepository<Przelew,String> {
}
