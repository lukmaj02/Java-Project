package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UzytkownikRepo extends JpaRepository<Uzytkownik,String> {
}
