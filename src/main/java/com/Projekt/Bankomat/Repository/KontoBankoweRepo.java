package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.KontoBankowe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KontoBankoweRepo extends JpaRepository<KontoBankowe,String> {
}
